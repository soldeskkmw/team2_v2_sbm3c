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
  
  public ReplyCont () {
    System.out.println("-> ReplyCont created.");
  }

  /**
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
 
}

