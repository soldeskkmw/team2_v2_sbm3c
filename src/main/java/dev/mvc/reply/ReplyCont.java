package dev.mvc.reply;

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

import dev.mvc.reply.ReviewReplyVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.review.ReviewProcInter;


 
@Controller
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc") 
  private ReplyProcInter replyProc;
  
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
 
}

