package dev.mvc.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TensorflowCont {
  // http://localhost:9093/type2_recommend_post/start.do
  @RequestMapping(value = {"/type2_recommend_post/start.do"}, method = RequestMethod.GET)
  public ModelAndView start() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/start");  // /WEB-INF/views/type2_recommend_post/start.jsp
    
    return mav;
  }

  // http://localhost:9093/type2_recommend_post/form1.do
  @RequestMapping(value = {"/type2_recommend_post/form1.do"}, method = RequestMethod.GET)
  public ModelAndView form1() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form1");  // /WEB-INF/views/type2_recommend_post/form1.jsp
    
    return mav;
  }

  // http://localhost:9093/type2_recommend_post/form2.do
  @RequestMapping(value = {"/type2_recommend_post/form2.do"}, method = RequestMethod.GET)
  public ModelAndView form2() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form2");  // /WEB-INF/views/type2_recommend_post/form2.jsp
    
    return mav;
  }

  // http://localhost:9093/type2_recommend_post/form3.do
  @RequestMapping(value = {"/type2_recommend_post/form3.do"}, method = RequestMethod.GET)
  public ModelAndView form3() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form3");  // /WEB-INF/views/type2_recommend_post/form3.jsp
    
    return mav;
  }
  
  // http://localhost:9093/type2_recommend_post/form4.do
  @RequestMapping(value = {"/type2_recommend_post/form4.do"}, method = RequestMethod.GET)
  public ModelAndView form4() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form4");  // /WEB-INF/views/type2_recommend_post/form4.jsp
    
    return mav;
  }

  // http://localhost:9093/type2_recommend_post/form5.do
  @RequestMapping(value = {"/type2_recommend_post/form5.do"}, method = RequestMethod.GET)
  public ModelAndView form5() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form5");  // /WEB-INF/views/type2_recommend_post/form5.jsp
    
    return mav;
  }

  // http://localhost:9093/type2_recommend_post/form6.do
  @RequestMapping(value = {"/type2_recommend_post/form6.do"}, method = RequestMethod.GET)
  public ModelAndView form6() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/form6");  // /WEB-INF/views/type2_recommend_post/form6.jsp
    
    return mav;
  }
  
  // http://localhost:9093/type2_recommend_post/end.do
  @RequestMapping(value = {"/type2_recommend_post/end.do"}, method = RequestMethod.GET)
  public ModelAndView end() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/type2_recommend_post/end");  // /WEB-INF/views/type2_recommend_post/end.jsp
    
    return mav;
  }
  
    
}