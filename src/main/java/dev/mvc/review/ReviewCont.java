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

import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
import dev.mvc.reply.ReviewReplyVO;
import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.review.ReviewVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;


@Controller
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc") 
  private ReviewProcInter reviewProc;
  
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc") 
  private ReplyProcInter replyProc;
  
  public ReviewCont () {
    System.out.println("-> ReviewCont created.");
  }
  
  
  
  
  // 등록 폼
  // http://localhost:9093/review/create.do?postno=1
  @RequestMapping(value="/review/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("-> create()");
//  public ModelAndView create(HttpServletRequest request,  int cateno) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/review/create"); // /webapp/WEB-INF/views/contents/create.jsp
    
    return mav;
  }    
  
  //이 위에는 포스트 즉 여행지 글 본문 번호를 받아야 함으로 그거에 맞게 수정 ,파일쪽은 일단 제거함 추가요망
  
  /**
   * 등록 처리 http://localhost:9093/review/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.POST)
  public ModelAndView create( ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.reviewProc.create(reviewVO); 
    
    // ------------------------------------------------------------------------------
    // PK의 return
    // ------------------------------------------------------------------------------
    // System.out.println("--> reviewno: " + contentsVO.getreviewno());
    // mav.addObject("reviewno", contentsVO.getreviewno()); // redirect parameter 적용
    // ------------------------------------------------------------------------------
    
    if (cnt == 1) {
        mav.addObject("code", "create_success");
        // cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
    } else {
        mav.addObject("code", "create_fail");
    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    
    ArrayList<PostReviewVO> list = this.reviewProc.list_all();
    mav.addObject("list", list);   
    
    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    // mav.addObject("cateno", contentsVO.getCateno()); // redirect parameter 적용
    // mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
    //mav.setViewName("redirect:/contents/msg.do"); 
    mav.setViewName("redirect:/review/list_all.do"); // msg.jsp
    
    return mav; // forward
  }
  
  /**
   * post + review INNER JOIN 목록, http://localhost:9093/review/list_all.do
   * @return
   */
  @RequestMapping(value="/review/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    
    
    ArrayList<PostReviewVO> list = this.reviewProc.list_all();
    mav.addObject("list", list);
    
    mav.setViewName("/review/list_all"); // /webapp/WEB-INF/views/contents/list_all.jsp
    
    return mav;
  }

//http://localhost:9093/review/read.do
 /**
  * 조회
  * @return
  */
 @RequestMapping(value="/review/read.do", method=RequestMethod.GET )
 public ModelAndView read(int reviewno) {
   ModelAndView mav = new ModelAndView();

   ReviewVO reviewVO = this.reviewProc.read(reviewno);

   mav.addObject("reviewVO", reviewVO); // request.setAttribute("reviewVO", reviewVO);
  
//   ArrayList<ReplyVO> replylist = this.replyProc.replylist_by_reviewno(reviewno);
   ArrayList<ReplyVO> replylist = this.replyProc.replylist_by_reviewno(reviewno);
   mav.addObject("replylist", replylist);

   mav.setViewName("/review/read"); // /WEB-INF/views/contents/read.jsp
       
   return mav;
 }
 
 
 /**
  * 수정 폼
  * http://localhost:9093/review/update_text.do?reviewno=1
  * 
  * @return
  */
 @RequestMapping(value = "/review/update_text.do", method = RequestMethod.GET)
 public ModelAndView update_text(int reviewno) {
   ModelAndView mav = new ModelAndView();
   
   ReviewVO reviewVO = this.reviewProc.read(reviewno);
   mav.addObject("reviewVO", reviewVO);
   
//   PostVO postVO = this.postProc.read(reviewVO.getPostno());
//   mav.addObject("postVO", postVO);
   
   mav.setViewName("/review/update_text"); // /WEB-INF/views/contents/update_text.jsp
   // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
   // mav.addObject("content", content);

   return mav; // forward
 }
 
 /**
  * 수정 처리
  * http://localhost:9093/review/update_text.do?reviewno=1
  * 
  * @return
  */
 @RequestMapping(value = "/review/update_text.do", method = RequestMethod.POST)
 public ModelAndView update_text(HttpSession session,ReviewVO reviewVO) {
   ModelAndView mav = new ModelAndView();
   
//   if(this.adminProc.isAdmin(session)) {    -> 어드민 컬럼 조회 필요
   if(true) {
     int cnt = this.reviewProc.update_text(reviewVO);
     
     // URL에 파라미터의 전송
     // mav.setViewName("redirect:/contents/read.do?reviewno=" + contentsVO.getreviewno() + "&cateno=" + contentsVO.getCateno());             

     // mav 객체 이용
     mav.addObject("reviewno", reviewVO.getReviewno());
     mav.addObject("postno", reviewVO.getPostno());
     mav.setViewName("redirect:/review/read.do");
   } else {
     mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
     mav.setViewName("redirect:/review/msg.do"); // GET
   }
   
   
   return mav; // forward
 }
 
 
 /**
  * 삭제 폼
  * @param reviewno
  * @return
  */
 @RequestMapping(value="/review/delete.do", method=RequestMethod.GET )
 public ModelAndView delete(int reviewno) { 
   ModelAndView mav = new  ModelAndView();
   
   // 삭제할 정보를 조회하여 확인
   ReviewVO reviewVO = this.reviewProc.read(reviewno);
   mav.addObject("reviewVO", reviewVO);
   
//   PostVO postVO = this.postProc.read(reviewVO.getPostno());
//   mav.addObject("postVO", postVO);
//   
   mav.setViewName("/review/delete");  // /webapp/WEB-INF/views/contents/delete.jsp
   
   return mav; 
 }
 
 /**
  * 삭제 처리 http://localhost:9093/review/delete.do
  * 
  * @return
  */
 @RequestMapping(value = "/review/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(int reviewno, String word,
                                       @RequestParam(value="now_page", defaultValue="1") int now_page) {
   ModelAndView mav = new ModelAndView();
   
   int cnt = 0;
   // -------------------------------------------------------------------
   // 파일 삭제 코드 시작
   // -------------------------------------------------------------------
   // 삭제할 파일 정보를 읽어옴.
   ReviewVO reviewVO = reviewProc.read(reviewno);
       
   String reviewfile1saved = reviewVO.getReviewfile1saved();
   String reviewthumb1 = reviewVO.getReviewthumb1();
   long reviewsize1 = 0;
   boolean sw = false;
       
   // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
   String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/review/storage/"; // 절대 경로

   sw = Tool.deleteFile(upDir, reviewfile1saved);  // 실제 저장된 파일삭제
   sw = Tool.deleteFile(upDir, reviewthumb1);     // preview 이미지 삭제
   // -------------------------------------------------------------------
   // 파일 삭제 종료 시작
   // -------------------------------------------------------------------
       
   cnt = this.reviewProc.delete(reviewno); // DBMS 삭제
       
   // -------------------------------------------------------------------------------------
   // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
   // -------------------------------------------------------------------------------------    
   HashMap<String, Object> page_map = new HashMap<String, Object>();
   page_map.put("postno", reviewVO.getPostno());
   page_map.put("word", word);
   // 마지막 페이지의 마지막 10번째 레코드를 삭제후
   // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
   // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
//   if (reviewProc.search_count(page_map) % Review.RECORD_PER_PAGE == 0) {
//     now_page = now_page - 1;
//     if (now_page < 1) {
//       now_page = 1; // 시작 페이지
//     }
//   }
   // -------------------------------------------------------------------------------------

   mav.addObject("postno", reviewVO.getPostno());
   mav.addObject("now_page", now_page);
   mav.setViewName("redirect:/review/list_all.do"); 
   
   return mav;
 }   
 
}

