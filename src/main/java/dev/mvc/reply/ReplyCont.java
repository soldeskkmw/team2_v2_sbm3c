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
   * ��� ó�� http://localhost:9093/reply/replycreate.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/replycreate.do", method = RequestMethod.POST)
  public ModelAndView replycreate( ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    
    
    // Call By Reference: �޸� ����, Hashcode ����
    int cnt = this.replyProc.replycreate(replyVO); 
    
    // ------------------------------------------------------------------------------
    // PK�� return
    // ------------------------------------------------------------------------------
    // System.out.println("--> reviewno: " + contentsVO.getreviewno());
    // mav.addObject("reviewno", contentsVO.getreviewno()); // redirect parameter ����
    // ------------------------------------------------------------------------------
    
    if (cnt == 1) {
        mav.addObject("code", "create_success");
        // cateProc.increaseCnt(contentsVO.getCateno()); // �ۼ� ����
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
    // redirect�ÿ� hidden tag�� �����͵��� ������ �ȵ����� request�� �ٽ� ����
    // mav.addObject("cateno", contentsVO.getCateno()); // redirect parameter ����
    // mav.addObject("url", "/contents/msg"); // msg.jsp, redirect parameter ����
    //mav.setViewName("redirect:/contents/msg.do"); 
    mav.setViewName("/review/read"); // msg.jsp
    
    return mav; // forward
  }
  
  
  /**
   * ���� ��
   * @param reviewno
   * @return
   */
  @RequestMapping(value="/review/replydelete.do", method=RequestMethod.GET )
  public ModelAndView replydelete(int replyno) { 
    ModelAndView mav = new  ModelAndView();
    
    // ������ ������ ��ȸ�Ͽ� Ȯ��
    ReplyVO replyVO = this.replyProc.replyread(replyno);
    mav.addObject("replyVO", replyVO);
    
//    PostVO postVO = this.postProc.read(reviewVO.getPostno());
//    mav.addObject("postVO", postVO);
//    
    mav.setViewName("/review/replydelete");  // /webapp/WEB-INF/views/contents/delete.jsp
    
    return mav; 
  }
  
  
  /**
   * ���� ó�� http://localhost:9093/review/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/replydelete.do", method = RequestMethod.POST)
  public ModelAndView replydelete(int replyno, String word,
                                        @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    // ������ ���� ������ �о��.
    ReplyVO replyVO = replyProc.replyread(replyno);
        

 
        
    cnt = this.replyProc.replydelete(replyno); // DBMS ����
        
    // -------------------------------------------------------------------------------------
    // ������ �������� ������ ���ڵ� �������� ������ ��ȣ -1 ó��
    // -------------------------------------------------------------------------------------    
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("reviewno", replyVO.getReviewno());
    page_map.put("word", word);
    // ������ �������� ������ 10��° ���ڵ带 ������
    // �ϳ��� �������� 3���� ���ڵ�� �����Ǵ� ��� ���� 9���� ���ڵ尡 ���� ������
    // ���������� 4 -> 3���� ���� ���Ѿ���, ������ �������� ������ ���ڵ� ������ �������� 0 �߻�
//    if (reviewProc.search_count(page_map) % Review.RECORD_PER_PAGE == 0) {
//      now_page = now_page - 1;
//      if (now_page < 1) {
//        now_page = 1; // ���� ������
//      }
//    }
    // -------------------------------------------------------------------------------------

    mav.addObject("reviewno", replyVO.getReviewno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/review/read.do"); 
    
    return mav;
  }   

 
}

