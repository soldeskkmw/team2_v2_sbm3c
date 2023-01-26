package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import dev.mvc.member.MemberVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
<<<<<<< HEAD
=======
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ReplyCont {
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc") 
  private ReplyProcInter replyProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc") 
  private ReviewProcInter reviewProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  public ReplyCont () {
    System.out.println("-> ReplyCont created.");
  }

  /**
<<<<<<< HEAD
   * 새로고침 방지, REVIEW -> REVIEW 정보 삭제 -> GET -> msg.jsp
   * @return
   */
  @RequestMapping(value="/reply/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName(url);
    return mav;
  }
  
  // 등록 폼
  // http://localhost:9093/reply/create.do?reviewno=1
  @RequestMapping(value="/reply/create.do", method=RequestMethod.GET)
  public ModelAndView create(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/reply/create"); // /webapp/WEB-INF/views/reply/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/reply/create.do
   * @return
   */
  @RequestMapping(value="/reply/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      int memberno = (int)session.getAttribute("memberno");
      replyVO.setMemberno(memberno);
      
      int cnt =this.replyProc.create(replyVO);
      
      if(cnt == 1) {
        mav.addObject("code", "create_success");
      } else {
        mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt);
      mav.addObject("reviewno", replyVO.getReviewno());
      
      mav.addObject("url", "/reply/msg");
      mav.setViewName("redirect:/reply/msg.do");
    } else {
      mav.addObject("url", "/member/login_need");
      mav.setViewName("redirect:/reply/msg.do");
    }
    return mav;
  }
  
  /**
   * 특정 리뷰에 등록된 댓글 목록, http://localhost:9093/reply/list_by_reviewno.do
   * @return
   */
  @RequestMapping(value="/reply/list_by_reviewno.do", method=RequestMethod.GET)
  public ModelAndView list_by_reviewno(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    ReplyVO replyVO = this.replyProc.read(reviewno);
    mav.addObject("replyVO", replyVO);
    
    ArrayList<ReplyVO> list = this.replyProc.list_by_reviewno(reviewno);
    mav.addObject("list", list);
    
    mav.setViewName("/reply/list_by_reviewno"); // /webapp/WEB-INF/views/reply/list_by_reviewno.jsp
    
    return mav;
  }
  
  /**
   * 리뷰 내용 + 리뷰 전체 댓글 목록 INNER JOIN + 회원 조회
   * http://localhost:9093/reply/read_all_list.do?reviewno=1&cateno=6&reviewcnt=3&reviewword=&now_page=
   * @return
   */
  @RequestMapping(value="/reply/read_all_list.do", method=RequestMethod.GET)
  public ModelAndView read_all_list(int cateno, int reviewno, 
                                                @RequestParam(value="reviewcnt", defaultValue="0")int reviewcnt,
                                                @RequestParam(value="memberno", defaultValue="1")int memberno,
                                                HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);

    ReviewVO reviewVO = this.reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    ReplyVO replyVO = this.replyProc.read(reviewno);
    long reviewsize1 = replyVO.getReviewsize1();
    replyVO.setReviewsize1_label(Tool.unit(reviewsize1));
    
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
    
    mav.addObject("replyVO", replyVO);
    mav.setViewName("/reply/read_all_list"); // /webapp/WEB-INF/views/reply/read_all_list.jsp
    return mav;
    
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/reply/update_reply.do?replyno=1
   * @return
   */
  @RequestMapping(value="/reply/update_reply.do", method=RequestMethod.GET)
  public ModelAndView update_reply(int replyno) {
    ModelAndView mav = new ModelAndView();

    ReplyVO replyVO = this.replyProc.read(replyno);
    mav.addObject("replyVO", replyVO);
    
    ReviewVO reviewVO = this.reviewProc.read(replyVO.getCateno());
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/reply/update_reply"); // /WEB-INF/views/reply/update_reply.jsp
    
    return mav;
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/reply/update_reply.do?replyno=1
   * @return
   */
  @RequestMapping(value="/reply/update_reply.do", method=RequestMethod.POST)
  public ModelAndView update_reply(HttpSession session, ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      int cnt = this.replyProc.update_reply(replyVO);
      
      mav.addObject("replyno", replyVO.getReplyno());
      mav.addObject("reviewno", replyVO.getReviewno());
      mav.setViewName("redirect:/reply/read_all_list.do");
    } else {
      mav.addObject("url", "member/login_need");
      mav.setViewName("redirect:/reply/msg.do");
    }
    return mav;
  }

  /**
   * 삭제 폼
   * http://localhost:9093/reply/delete.do?replyno=1
   * @return
   */
  @RequestMapping(value="/reply/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int replyno) {
    ModelAndView mav = new ModelAndView();

    ReplyVO replyVO = this.replyProc.read(replyno);
    mav.addObject("replyVO", replyVO);
    
    ReviewVO reviewVO = this.reviewProc.read(replyVO.getCateno());
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/reply/delete"); // /WEB-INF/views/reply/delete.jsp
    
    return mav;
  }
  
  /**
   * 삭제 처리
   * http://localhost:9093/reply/delete.do
   * @return
   */
  @RequestMapping(value="/reply/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int replyno, ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt=0;
    
    cnt = this.replyProc.delete(replyno); // DBMS 삭제
    
    mav.addObject("reviewno", replyVO.getReviewno());
    mav.setViewName("redirect:/reply/read_all_list.do");
    
    return mav;
  }
=======
   * 수정 폼
   * http://localhost:9093/reply/update_reviewtext.do?reviewno=1&cateno=5
   * @return
   */
  @RequestMapping(value="/reply/update_reviewtext.do", method = RequestMethod.GET)
  public ModelAndView update_reviewtext(int reviewno, int cateno) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<ReplyVO> list = this.replyProc.read_by_reviewno_reply(reviewno, cateno);
    mav.addObject("list", list);
    
    mav.setViewName("/reply/update_reviewtext"); // /WEB-INF/views/reply/update_reviewtext.jsp

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/reply/update_reviewtext.do?reviewno=1&cateno=5
   * @return
   */
  @RequestMapping(value = "/reply/update_reviewtext.do", method = RequestMethod.POST)
  public ModelAndView update_reviewtext(HttpSession session, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    if(this.memberProc.isMember(session)) {
      int cnt = this.replyProc.update_reviewtext(reviewVO);

      mav.addObject("reviewno", reviewVO.getReviewno());
      mav.addObject("cateno", reviewVO.getCateno());
      mav.setViewName("redirect:/reply/read.do");
    } else {
      mav.addObject("url", "/member/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/review/msg.do"); // GET
    }
    
    return mav; // forward
  }
  
//  /**
//   * 파일 수정 폼
//   * http://localhost:9093/review/update_reviewfile.do?reviewno=1&cateno=5
//   * @return
//   */
//  @RequestMapping(value = "/review/update_reviewfile.do", method = RequestMethod.GET)
//  public ModelAndView update_file(int reviewno, int cateno) {
//    ModelAndView mav = new ModelAndView();
//    
//    ArrayList<ReplyVO> list = this.replyProc.read_by_reviewno_reply(reviewno, cateno);
//    mav.addObject("list", list);
//    
//    mav.setViewName("/review/update_reviewfile"); // /WEB-INF/views/review/update_reviewfile.jsp
//
//    return mav; // forward
//  }
//  
//  /**
//   * 파일 수정 처리
//   * http://localhost:9093/review/update_reviewfile.do
//   * @return
//   */
//  @RequestMapping(value = "/review/update_reviewfile.do", method = RequestMethod.POST)
//  public ModelAndView update_reviewfile(HttpSession session, ReviewVO reviewVO, int reviewno, int cateno) {
//    ModelAndView mav = new ModelAndView();
//
//    if (this.memberProc.isMember(session)) {
//      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
//      ArrayList<ReviewVO> reviewVO_old = reviewProc.list_by_cateno(reviewno);
//      int cnt = 0;
//
//      // -------------------------------------------------------------------
//      // 파일 삭제 코드 시작
//      // -------------------------------------------------------------------
//      String reviewfile1saved = reviewVO_old.getReviewfile1saved();  // 실제 저장된 파일명
//      String reviewthumb1 = reviewVO_old.getReviewthumb1();       // 실제 저장된 preview 이미지 파일명
//      long reviewsize1 = 0;
//      boolean sw = false;
//          
//      // 완성된 경로 C:/kd/ws_java/resort_v2_sbm3c/src/main/resources/static/review/storage/
//      String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/review/storage/"; // 절대 경로
//
//      sw = Tool.deleteFile(upDir, reviewfile1saved);  // 실제 저장된 파일삭제
//      sw = Tool.deleteFile(upDir, reviewthumb1);     // preview 이미지 삭제
//      // -------------------------------------------------------------------
//      // 파일 삭제 종료 시작
//      // -------------------------------------------------------------------
//          
//      // ------------------------------------------------------------------------------
//      // 파일 전송 코드 시작
//      // ------------------------------------------------------------------------------
//      String reviewfile1 = "";          // 원본 파일명 image
//      
//      MultipartFile mf = reviewVO.getReviewfile1MF();
//      reviewfile1 = mf.getOriginalFilename();
//      reviewsize1 = mf.getSize();
//
//      if (reviewsize1 > 0) { // 파일 크기 체크
//        reviewfile1saved = Upload.saveFileSpring(mf, upDir); 
//        
//        if (Tool.isImage(reviewfile1saved)) { // 이미지인지 검사
//          reviewthumb1 = Tool.preview(upDir, reviewfile1saved, 200, 150); 
//        }
//      } else {  // 파일이 삭제만 되고 새로 올리지 않는 경우
//        reviewfile1="";
//        reviewfile1saved="";
//        reviewthumb1="";
//        reviewsize1=0;
//      }
//          
//      reviewVO.setReviewfile1(reviewfile1);   // 순수 원본 파일명
//      reviewVO.setReviewfile1saved(reviewfile1saved); // 저장된 파일명(파일명 중복 처리)
//      reviewVO.setReviewthumb1(reviewthumb1);      // 원본이미지 축소판
//      reviewVO.setReviewsize1(reviewsize1);  // 파일 크기
//      // ------------------------------------------------------------------------------
//      // 파일 전송 코드 종료
//      // ------------------------------------------------------------------------------ 
//          
//      cnt = this.reviewProc.update_reviewfile(reviewVO); // Oracle 처리
//
//      mav.addObject("reviewno", reviewVO.getReviewno());
//      mav.addObject("cateno", reviewVO.getCateno());
//      mav.setViewName("redirect:../reply/read.do"); // request -> param으로 접근 전환
//    } else {
//      mav.addObject("url", "/member/login_need"); // login_need.jsp, redirect parameter 적용
//      mav.setViewName("redirect:/review/msg.do"); // GET
//    }
//    return mav;
//  }   
  
//  /**
//   * 삭제 폼 http://localhost:9093/review/review_delete.do?reviewno=1&cateno=5
//   * @param reviewno
//   * @return
//   */
//  @RequestMapping(value="/review/review_delete.do", method=RequestMethod.GET )
//  public ModelAndView review_delete(int reviewno, int cateno) { 
//    ModelAndView mav = new  ModelAndView();
//    
//    ArrayList<ReplyVO> list = this.replyProc.read_by_reviewno_reply(reviewno, cateno);
//    mav.addObject("list", list);
//    
//    mav.setViewName("/reply/review_delete");  // /webapp/WEB-INF/views/reply/review_delete.jsp
//    
//    return mav; 
//  }
//  
//  /**
//   * 삭제 처리 http://localhost:9093/review/review_delete.do
//   * @return
//   */
//  @RequestMapping(value = "/review/review_delete.do", method = RequestMethod.POST)
//  public ModelAndView review_delete(int reviewno, int cateno, String reviewword,
//                                        @RequestParam(value="now_page", defaultValue="1") int now_page){
//    ModelAndView mav = new ModelAndView();
//
//    int cnt = 0;
//    // -------------------------------------------------------------------
//    // 파일 삭제 코드 시작
//    // -------------------------------------------------------------------
//    // 삭제할 파일 정보를 읽어옴.
//    ArrayList<ReplyVO> list = this.replyProc.read_by_reviewno_reply(reviewno, cateno);
//        
//    String reviewfile1saved = list.getReviewfile1saved();
//    String reviewthumb1 = reviewVO.getReviewthumb1();
//    long reviewsize1 = 0;
//    boolean sw = false;
//        
//    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
//    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/review/storage/"; // 절대 경로
//
//    sw = Tool.deleteFile(upDir, reviewfile1saved);  // 실제 저장된 파일삭제
//    sw = Tool.deleteFile(upDir, reviewthumb1);     // preview 이미지 삭제
//    // -------------------------------------------------------------------
//    // 파일 삭제 종료 시작
//    // -------------------------------------------------------------------
//        
//    cnt = this.reviewProc.delete(reviewno); // DBMS 삭제
//        
//    // -------------------------------------------------------------------------------------
//    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
//    // -------------------------------------------------------------------------------------    
//    HashMap<String, Object> page_map = new HashMap<String, Object>();
//    page_map.put("word", word);
//    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
//    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
//    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
////    if (reviewProc.search_count(page_map) % Review.RECORD_PER_PAGE == 0) {
////      now_page = now_page - 1;
////      if (now_page < 1) {
////        now_page = 1; // 시작 페이지
////      }
////    }
//    // -------------------------------------------------------------------------------------
//    mav.addObject("cateno", cateno);   
//    mav.addObject("postno", postno);   
//    mav.addObject("now_page", now_page);
//    System.out.println(postno);
//    System.out.println(cateno);
//    mav.setViewName("redirect:/post/read.do?postno="+postno+"&cateno="+cateno); 
//    
//    return mav;
//  }   
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
 
}

