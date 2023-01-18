package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ReviewCont {
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc") 
  private ReviewProcInter reviewProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
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
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url);
    
    return mav;
  }
  
  // 등록 폼
  // http://localhost:9093/review/create.do?cateno=1
  @RequestMapping(value="/review/create.do", method = RequestMethod.GET)
  public ModelAndView create(int cateno){
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
  @RequestMapping(value = "/review/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, ReviewVO reviewVO) {
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
      // System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
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
      
      reviewVO.setReviewfile1(reviewfile1);   // 순수 원본 파일명
      reviewVO.setReviewfile1saved(reviewfile1saved); // 저장된 파일명(파일명 중복 처리)
      reviewVO.setReviewthumb1(reviewthumb1);      // 원본이미지 축소판
      reviewVO.setReviewsize1(reviewsize1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int cnt = this.reviewProc.create(reviewVO);
      
      if (cnt == 1) {
        mav.addObject("code", "create_success");
      } else {
          mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      mav.addObject("cateno", reviewVO.getCateno());
      
      mav.addObject("url", "/review/msg");
      mav.setViewName("redirect:/review/msg.do"); // GET
    } else {
      mav.addObject("url", "/member/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/review/msg.do"); // GET
    }
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원 + Cookie
   * http://localhost:9093/review/list_by_cateno_search_paging.do?cateno=1&reviewword=테스트&now_page=1
   * @param cateno
   * @param reviewword
   * @param now_page
   * @return
   */
  @RequestMapping(value ="/review/list_by_cateno_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_cateno_search_paging_cookie(
                                                                HttpServletRequest request,
                                                                @RequestParam(value = "cateno", defaultValue = "1") int cateno,
                                                                @RequestParam(value = "reviewword", defaultValue = "") String reviewword,
                                                                @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno); // #{cateno}
    map.put("reviewword", reviewword); // #{reviewword}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
    
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
    mav.setViewName("/review/list_by_cateno_search_paging_cookie_ck");  // /review/list_by_cateno_search_paging_cookie_ck.jsp ★
    
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
  
}
