package dev.mvc.post_ratings;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.post.PostProcInter;
import dev.mvc.post.PostVO;

@Controller
public class Post_ratingsCont {
  @Autowired
  @Qualifier("dev.mvc.post_ratings.Post_ratingsProc")
  private Post_ratingsProcInter post_ratingsProc;
  
  @Autowired
  @Qualifier("dev.mvc.post.PostProc")
  private PostProcInter postProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public Post_ratingsCont() {
    System.out.println("-> Post_ratingsCont created");
  }
  
  /**
   * 새로고침 방지, POST -> POST 정보 삭제 -> GET -> msg.jsp
   * @return
   */
  @RequestMapping(value="/post_ratings/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url);
    
    return mav;
  }
  
  // 등록 폼
  // http://localhost:9093/post_ratings/create.do?postno=1
  @RequestMapping(value="/post_ratings/create.do", method=RequestMethod.GET)
  public ModelAndView create(int postno) {
    ModelAndView mav = new ModelAndView();
    
    PostVO postVO = this.postProc.read(postno);
    mav.addObject("postVO", postVO);
    mav.setViewName("/post_ratings/create"); // /webapp/WEB-INF/views/post_ratings/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/post_ratings/create.do
   * @return
   */
  @RequestMapping(value="/post_ratings/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, Post_ratingsVO post_ratingsVO, PostVO postVO) {
    ModelAndView mav = new ModelAndView();

    if(this.memberProc.isMember(session)) {
      int memberno = (int)session.getAttribute("memberno");
      post_ratingsVO.setMemberno(memberno);
      
      int cnt = this.post_ratingsProc.create(post_ratingsVO);
      
      if(cnt==1) {
        mav.addObject("code", "create_success");
      } else {
        mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt);
      mav.addObject("postno", post_ratingsVO.getPostno());
      mav.addObject("cateno", postVO.getCateno());
      mav.addObject("postcnt", postVO.getPostcnt());
      mav.addObject("postword", postVO.getPostword());
      
      mav.addObject("url", "/post_ratings/msg");
      mav.setViewName("redirect:/post_ratings/msg.do");
    } else {
      mav.addObject("url", "/member/login_need");
      mav.setViewName("redirect:/post_ratings/msg.do");
    }
    return mav;
  }
  
  /**
   * post + post_ratings INNER JOIN 목록, http://localhost:9093/post_ratings/list_all_post.do
   * @return
   */
  @RequestMapping(value="/post_ratings/list_all_post.do", method=RequestMethod.GET)
  public ModelAndView list_all_post() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<PostPost_ratingsVO> list = this.post_ratingsProc.list_all_post();
    mav.addObject("list", list);
    
    mav.setViewName("/post_ratings/list_all_post");  // /webapp/WEB-INF/views/post_ratings/list_all_post.jsp
    
    return mav;
  }
  
  /**
   * member + post_ratings INNER JOIN 목록, http://localhost:9093/post_ratings/list_all_member.do
   * @return
   */
  @RequestMapping(value="/post_ratings/list_all_member.do", method=RequestMethod.GET)
  public ModelAndView list_all_member() {
    ModelAndView mav = new ModelAndView();

    ArrayList<MemberPost_ratingsVO> list = this.post_ratingsProc.list_all_member();
    mav.addObject("list", list);
    
    mav.setViewName("/post_ratings/list_all_member");  // /webapp/WEB-INF/views/post_ratings/list_all_member.jsp
    
    return mav;
  }
  
  /**
   * 특정 포스트에 등록된 평점 목록
   * http://localhost:9093/post_ratings/list_by_postno.do?postno=1
   * @param postno
   * @return 포스트 별 레코드 전체 목록
   */
  @RequestMapping(value="/post_ratings/list_by_postno.do", method=RequestMethod.GET)
  public ModelAndView list_by_postno(int postno, 
      @RequestParam(value="ratings", defaultValue="1") float ratings, 
      Post_ratingsVO post_ratingsVO) {
    ModelAndView mav = new ModelAndView();
    
    PostVO postVO = this.postProc.read(postno);
    mav.addObject("postVO", postVO);
    
    mav.addObject("ratings", post_ratingsVO.getRatings());
    
    mav.setViewName("/post_ratings/list_by_postno"); // /webapp/WEB-INF/views/post_ratings/list_by_postno.jsp
    
//    System.out.println("-> postno: " + postno);
//    System.out.println("-> ratings: " + ratings);
    
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/post_ratings/update_ratings.do?memberno=1&postno=1
   * @return
   */
  @RequestMapping(value="/post_ratings/update_ratings.do", method=RequestMethod.GET)
  public ModelAndView update_ratings(int memberno, int postno, Post_ratingsVO post_ratingsVO) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<Post_ratingsVO> list = this.post_ratingsProc.list_by_postno(postno);
    mav.addObject("list", list);    
    
    MemberVO memberVO = this.memberProc.read(post_ratingsVO.getMemberno());
    mav.addObject("memberVO", memberVO);
    
    mav.setViewName("/post_ratings/update_ratings"); // /WEB-INF/views/post_ratings/update_ratings.jsp
    
    return mav;
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/post_ratings/update_ratings.do?memberno=1&postno=1
   * @return
   */
  @RequestMapping(value="/post_ratings/update_ratings.do", method=RequestMethod.POST)
  public ModelAndView delete(HttpSession session, Post_ratingsVO post_ratingsVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.memberProc.isMember(session)) {
      int cnt = this.post_ratingsProc.update_ratings(post_ratingsVO);
      mav.addObject("postno", post_ratingsVO.getPostno());
      mav.addObject("memberno", post_ratingsVO.getMemberno());
      mav.setViewName("redirect:/post_ratings/list_by_postno.do");
    } else {
      mav.addObject("url", "/member/login_need");
      mav.setViewName("redirect:/post_ratings/msg.do");
    }
    return mav;
  }
  
  
  /**
   * 삭제 폼 http://localhost:9093/post_ratings/delete.do?ratingno=1
   * @param ratingno
   * @return
   */
  @RequestMapping(value="/post_ratings/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int ratingno) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<Post_ratingsVO> list = this.post_ratingsProc.list_by_postno(ratingno);
    mav.addObject("list", list);
    
    mav.setViewName("/post_ratings/delete");  // /WEB-INF/views/post_ratings/delete.jsp
    
    return mav;
  }
  
  
  /**
   * 삭제 처리
   * http://localhost:9093/post_ratings/delete.do
   * @return
   */
  @RequestMapping(value="/post_ratings/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int ratingno, int postno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    PostVO postVO = postProc.read(postno);
    cnt = this.post_ratingsProc.delete(ratingno);
    
    mav.addObject("postno", postVO.getPostno());
    mav.setViewName("redirect:/post_ratings/list_by_postno.do");

    return mav;
  }
  
  
}


