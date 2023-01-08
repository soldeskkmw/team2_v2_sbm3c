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
  
  public ReplyCont () {
    System.out.println("-> ReplyCont created.");
  }
  
  
  /**
   * 등록 처리 http://localhost:9093/reply/replycreate.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/replycreate.do", method = RequestMethod.POST)
  public ModelAndView replycreate( ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.replyProc.replycreate(replyVO); 
    
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
     
    int reviewno1=replyVO.getReviewno();
    
    ReviewVO reviewVO = this.reviewProc.read(reviewno1);

    mav.addObject("reviewVO", reviewVO); // request.setAttribute("reviewVO", reviewVO);
   
//    ArrayList<ReplyVO> replylist = this.replyProc.replylist_by_reviewno(reviewno);
    ArrayList<ReplyVO> replylist = this.replyProc.replylist_by_reviewno(reviewno1);
    mav.addObject("replylist", replylist);
    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    // mav.addObject("cateno", contentsVO.getCateno()); // redirect parameter 적용
    // mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter 적용
    //mav.setViewName("redirect:/contents/msg.do"); 
    mav.setViewName("/review/read"); // msg.jsp
    
    return mav; // forward
  }
  
  
  /**
   * 삭제 폼
   * @param reviewno
   * @return
   */
  @RequestMapping(value="/review/replydelete.do", method=RequestMethod.GET )
  public ModelAndView replydelete(int replyno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    ReplyVO replyVO = this.replyProc.replyread(replyno);
    mav.addObject("replyVO", replyVO);
    
//    PostVO postVO = this.postProc.read(reviewVO.getPostno());
//    mav.addObject("postVO", postVO);
//    
    mav.setViewName("/review/replydelete");  // /webapp/WEB-INF/views/contents/delete.jsp
    
    return mav; 
  }
  
  
  /**
   * 삭제 처리 http://localhost:9093/review/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/replydelete.do", method = RequestMethod.POST)
  public ModelAndView replydelete(int replyno, String word,
                                        @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ReplyVO replyVO = replyProc.replyread(replyno);
        

 
        
    cnt = this.replyProc.replydelete(replyno); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("reviewno", replyVO.getReviewno());
    page_map.put("word", word);
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
//    if (reviewProc.search_count(page_map) % Review.RECORD_PER_PAGE == 0) {
//      now_page = now_page - 1;
//      if (now_page < 1) {
//        now_page = 1; // 시작 페이지
//      }
//    }
    // -------------------------------------------------------------------------------------

    mav.addObject("reviewno", replyVO.getReviewno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/review/read.do"); 
    
    return mav;
  }   

 
}

