package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ReviewCont {
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc") 
  private ReviewProcInter reviewProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  
  /** 업로드 파일 절대 경로 */
  private String uploadDir = Tool.getOSPath() + "/review/storage";

  public ReviewCont () {
    System.out.println("-> ReviewCont created.");
  }
  
  /**
   * 새로고침 방지, REVIEW -> REVIEW 정보 삭제 -> GET -> msg.jsp
   * @return
   */
  @RequestMapping(value="/review/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName(url);
    
    return mav;
  }
  
  // 등록 폼
  // http://localhost:9093/review/create.do?cateno=1
  @RequestMapping(value="/review/create.do", method=RequestMethod.GET)
  public ModelAndView create(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/review/create"); // /webapp/WEB-INF/views/review/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/review/create.do
   * @return
   */
  @RequestMapping(value="/review/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request,  ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      int memberno = (int)session.getAttribute("memberno");
      reviewVO.setMemberno(memberno);
      
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String reviewfile1 = "";          // 원본 파일명 image
      String reviewfile1saved = "";   // 저장된 파일명, image
      String reviewthumb1 = "";     // preview image

      // 기준 경로 확인
      String user_dir = System.getProperty("user.dir"); // 시스템 제공

      String upDir =  user_dir + "/src/main/resources/static/review/storage/"; // 절대 경로
      
      // 전송 파일이 없어도 reviewfile1MF 객체가 생성됨.
      MultipartFile mf = reviewVO.getReviewfile1MF();
      
      reviewfile1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
      System.out.println("-> reviewfile1: " + reviewfile1);
      
      long reviewsize1 = mf.getSize();  // 파일 크기
      
      if (reviewsize1 > 0) { // 파일 크기 체크
        reviewfile1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(reviewfile1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          reviewthumb1 = Tool.preview(upDir, reviewfile1saved, 200, 150); 
        }
      }    
      
      reviewVO.setReviewfile1(reviewfile1);                  // 순수 원본 파일명
      reviewVO.setReviewfile1saved(reviewfile1saved); // 저장된 파일명(파일명 중복 처리)
      reviewVO.setReviewthumb1(reviewthumb1);      // 원본이미지 축소판
      reviewVO.setReviewsize1(reviewsize1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int cnt = this.reviewProc.create(reviewVO);
      
      if(cnt == 1) {
        mav.addObject("code", "create_success");
      } else {
        mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt);
      
      mav.addObject("cateno", reviewVO.getCateno());
      
      mav.addObject("url", "/review/msg");
      mav.setViewName("redirect:/review/msg.do");
    } else {
      mav.addObject("url", "/member/login_need");
      mav.setViewName("redirect:/review/msg.do");
    }
    return mav;
  }
  
  /**
   * 조회, http://localhost:9093/review/read.do?cateno=1&reviewno=1&reviewcnt=0
   * @return
   */
  @RequestMapping(value="/review/read.do", method=RequestMethod.GET)
  public ModelAndView read(int cateno, int reviewno,
                                      @RequestParam(value="reviewcnt", defaultValue="0")int reviewcnt,
                                      @RequestParam(value="memberno", defaultValue="1")int memberno,
                                      HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    long reviewsize1 = reviewVO.getReviewsize1();
    reviewVO.setReviewsize1_label(Tool.unit(reviewsize1));
    
    // 조회수 증가 및 cookie 사용 중복 증가 방지
    Cookie cookie = null;
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) { // 쿠키가 존재한다면
      for(Cookie oldcookie : cookies) {
        if (oldcookie.getName().equals("reviewView")) {
          cookie = oldcookie;
        }
      }
    }
    if(cookie != null) {
      if(!cookie.getValue().contains("[" + reviewno + "]")) {
        this.reviewProc.visit_reviewcnt(reviewno);
        cookie.setValue(cookie.getValue() + "_[" + reviewno + "]");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
      }
    } else {
      this.reviewProc.visit_reviewcnt(reviewno);
      Cookie newCookie = new Cookie("reviewView", "[" + reviewno + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }
    
    mav.addObject("reviewVO", reviewVO);
    mav.setViewName("/review/read"); // /webapp/WEB-INF/views/review/read.jsp
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원 + Cookie
   * http://localhost:9093/review/list_by_cateno_search_paging.do?cateno=1&reviewword=테스트&now_page=1
   * @param cateno 카테고리번호
   * @param reviewword 검색어
   * @param now_page 현재 페이지
   * @return
   */
  @RequestMapping(value="/review/list_by_cateno_search_paging.do", method=RequestMethod.GET)
  public ModelAndView list_by_cateno_search_paging_cookie(
                                                         HttpServletRequest request, HttpSession session,
                                                         @RequestParam(value="cateno", defaultValue="1")int cateno,
                                                         @RequestParam(value="reviewword", defaultValue="")String reviewword,
                                                         @RequestParam(value="now_page", defaultValue="1")int now_page) {
    
    ModelAndView mav = new ModelAndView();

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno);
    map.put("reviewword", reviewword);
    map.put("now_page", now_page);
    
    // 검색 목록
    ArrayList<ReviewVO> list = reviewProc.list_by_cateno_search_paging(map);
    mav.addObject("list", list);
    // 검색된 레코드 갯수
    int search_count = reviewProc.search_count(map);
    mav.addObject("search_count", search_count);

    CateVO cateVO = cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    String paging = reviewProc.pagingBox(cateno, search_count, now_page, reviewword);
    mav.addObject("paging", paging);
    
    // 로그인 Cookie + CKEditor
    mav.setViewName("/review/list_by_cateno_search_paging_cookie_ck"); // /review/list_by_cateno_search_paging_cookie_ck.jsp
    
    // -------------------------------------------------------------------------------
    // 로그인 폼 출력 관련 쿠기  
    // -------------------------------------------------------------------------------
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String memberid = ""; // id 저장
    String id_save = ""; // id 저장 여부를 체크
    String memberpasswd = ""; // passwd 저장
    String passwd_save = ""; // passwd 저장 여부를 체크

    if (cookies != null) {  // Cookie 변수가 있다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("memberid")){
          memberid = cookie.getValue();                                 // Cookie에 저장된 id
        }else if(cookie.getName().equals("id_save")){
          id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
        }else if (cookie.getName().equals("memberpasswd")){
          memberpasswd = cookie.getValue();                          // Cookie에 저장된 password
        }else if(cookie.getName().equals("passwd_save")){
          passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
        }
      }
    }
    mav.addObject("memberid", memberid); 
    mav.addObject("id_save", id_save);
    mav.addObject("memberpasswd", memberpasswd);
    mav.addObject("passwd_save", passwd_save);
    // -------------------------------------------------------------------------------
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/review/update_text.do?reviewno=1
   * @return
   */
  @RequestMapping(value="/review/update_text.do", method=RequestMethod.GET)
  public ModelAndView update_text(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    CateVO cateVO = this.cateProc.read(reviewVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/review/update_text"); // /WEB-INF/views/review/update_text.jsp
    
    return mav;
  }

  /**
   * 수정 처리
   * http://localhost:9093/review/update_text.do?reviewno=1
   * @return
   */
  @RequestMapping(value="/review/update_text.do", method=RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      int cnt = this.reviewProc.update_text(reviewVO);
      
      mav.addObject("reviewno", reviewVO.getReviewno());
      mav.addObject("cateno", reviewVO.getCateno());
      mav.setViewName("redirect:/review/read.do");
    } else {
      mav.addObject("url", "member/login_need");
      mav.setViewName("redirect:/review/msg.do");
    }
    return mav;
  }
  
  /**
   * 파일 수정 폼
   * http://localhost:9093/review/update_file.do?reviewno=1
   * @return
   */
  @RequestMapping(value="/review/update_file.do", method=RequestMethod.GET)
  public ModelAndView update_file(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    CateVO cateVO = this.cateProc.read(reviewVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/review/update_file"); // /WEB-INF/views/review/update_file.jsp
    
    return mav;
  }
  
  /**
   * 파일 수정 처리
   * http://localhost:9093/review/update_file.do
   * @return
   */
  @RequestMapping(value="/review/update_file.do", method=RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      ReviewVO reviewVO_old = reviewProc.read(reviewVO.getReviewno());
      int cnt = 0;
      
      // -------------------------------------------------------------------
      // 파일 삭제 코드 시작
      // -------------------------------------------------------------------
      String reviewfile1saved = reviewVO_old.getReviewfile1saved();  // 실제 저장된 파일명
      String reviewthumb1 = reviewVO_old.getReviewthumb1();       // 실제 저장된 preview 이미지 파일명
      long reviewsize1 = 0;
      boolean sw = false;
          
      // 완성된 경로 C:/kd/ws_java/resort_v2_sbm3c/src/main/resources/static/review/storage/
      String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/review/storage/"; // 절대 경로

      sw = Tool.deleteFile(upDir, reviewfile1saved);  // 실제 저장된 파일삭제
      sw = Tool.deleteFile(upDir, reviewthumb1);     // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료 시작
      // -------------------------------------------------------------------
      
      // -------------------------------------------------------------------
      // 파일 전송 코드 시작
      // -------------------------------------------------------------------
      String reviewfile1 = "";          // 원본 파일명 image
      
      MultipartFile mf = reviewVO.getReviewfile1MF();
      reviewfile1 = mf.getOriginalFilename();
      reviewsize1 = mf.getSize();
      
      if(reviewsize1 > 0) {
        reviewfile1saved = Upload.saveFileSpring(mf, upDir);
        
        if (Tool.isImage(reviewfile1saved)) { // 이미지인지 검사
          reviewthumb1 = Tool.preview(upDir, reviewfile1saved, 250, 200); 
        }
      } else {  // 파일이 삭제만 되고 새로 올리지 않는 경우
        reviewfile1="";
        reviewfile1saved="";
        reviewthumb1="";
        reviewsize1=0;
      }
      
      reviewVO.setReviewfile1(reviewfile1);
      reviewVO.setReviewfile1saved(reviewfile1saved);
      reviewVO.setReviewthumb1(reviewthumb1);
      reviewVO.setReviewsize1(reviewsize1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
      
      cnt = this.reviewProc.update_file(reviewVO);  //Oracle 처리
      
      mav.addObject("reviewno", reviewVO.getReviewno());
      mav.addObject("cateno", reviewVO.getCateno());
      mav.setViewName("redirect:/review/read.do");
    } else {
      mav.addObject("url", "member/login_need");
      mav.setViewName("redirect:/review/msg.do");
    }
    return mav;
  }
  
  /**
   * 삭제 폼 http://localhost:9093/review/delete.do?reviewno=1
   * @param reviewno
   * @return
   */
  @RequestMapping(value="/review/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    CateVO cateVO = this.cateProc.read(reviewVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/review/delete"); // /WEB-INF/views/review/delete.jsp
    
    return mav;
  }
  
  /**
   * 삭제 처리
   * http://localhost:9093/review/delete.do
   * @return
   */
  @RequestMapping(value="/review/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int reviewno, String reviewword,
                                        @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
    int cnt=0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    ReviewVO reviewVO = reviewProc.read(reviewno);
    
    String reviewfile1saved = reviewVO.getReviewfile1saved();  // 실제 저장된 파일명
    String reviewthumb1 = reviewVO.getReviewthumb1();       // 실제 저장된 preview 이미지 파일명
    long reviewsize1 = 0;
    boolean sw = false;
        
    // 완성된 경로 C:/kd/ws_java/resort_v2_sbm3c/src/main/resources/static/review/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/review/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, reviewfile1saved);  // 실제 저장된 파일삭제
    sw = Tool.deleteFile(upDir, reviewthumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    cnt = this.reviewProc.delete(reviewno); //DBMS 삭제
    
    // -------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("cateno", reviewVO.getCateno());
    page_map.put("reviewword", reviewword);
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    if (reviewProc.search_count(page_map) % Review.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1;
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    // -------------------------------------------------------------------------------------
    mav.addObject("cateno", reviewVO.getCateno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/review/list_by_cateno_search_paging.do");

    return mav;
  }
  
}




