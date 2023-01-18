package dev.mvc.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataCont {
  public DataCont() {
    System.out.println("-> DataCont created.");
  }
  
  /**
   * 포스트 추천
   * http://localhost:9093/recommend_post/mf_post.do
   * http://127.0.0.1:8000/recommend_post/mf_post?memberno=1
   * @return
   */
  @RequestMapping(value = "/recommend_post/mf_post.do", method = RequestMethod.GET)
  public ModelAndView json_list() {
    ModelAndView mav = new ModelAndView();
   
    // /WEB-INF/views/recommend_post/mf_post_list.jsp
    mav.setViewName("/recommend_post/mf_post_list");  

    return mav;
  }
  
}