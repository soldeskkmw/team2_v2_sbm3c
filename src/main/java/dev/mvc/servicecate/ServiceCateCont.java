package dev.mvc.servicecate;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceCateCont {
  @Autowired
  @Qualifier("dev.mvc.servicecate.ServiceCateProc")
  private ServiceCateProcInter servicecateProc = null;
  
  public ServiceCateCont(){
    System.out.println("-> ServiceCateCont created.");
  }
  
  // 등록 폼
  // http://localhost:9093/service/servicecate/list_all.do
  @RequestMapping(value="/service/servicecate/list_all.do", method = RequestMethod.GET)
  public ModelAndView create() {    
    ModelAndView mav = new ModelAndView();
    ArrayList<ServiceCateVO> list = this.servicecateProc.list_all();
    mav.addObject("list", list);
    
    mav.setViewName("/service/servicecate/list_all_ajax");
    
    return mav;
  }

  // 등록 처리
  // <FORM name='frm' method='POST' action='./create.do'>
  // http://localhost:9093/service/servicecate/create.do
  @RequestMapping(value="/service/servicecate/create.do", method = RequestMethod.POST)
  public ModelAndView create(ServiceCateVO servicecateVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.servicecateProc.create(servicecateVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      mav.setViewName("/service/servicecate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }
  
  /**
   * Ajax, JSON 지원 읽기, http://localhost:9093/servicecate/read_ajax_json.do?servicecateno=1
   * {"visible":"N","servicetype_content":"확인하기","cnt":0,"servicecateno":1,"order_index":2}
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/service/servicecate/read_ajax_json.do", method=RequestMethod.GET)
  public String read_ajax_json(int servicecateno) {
    
//    try {
//      Thread.sleep(500);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    
    ServiceCateVO servicecateVO = this.servicecateProc.read(servicecateno);
    
    JSONObject json = new JSONObject();
    json.put("servicecateno", servicecateVO.getServicecateno());
    json.put("servicetype_content", servicecateVO.getServicetype_content());
    json.put("cnt", servicecateVO.getCnt());
    json.put("order_index", servicecateVO.getOrder_index());
    json.put("visible", servicecateVO.getVisible());
    
    return json.toString();
  }
  
  /**
   * cateno를 FK로 사용하는 레코드 갯수 읽기, http://localhost:9093/servicecate/read_ajax_json_fk.do?servicecateno=1
   * {"visible":"Y","seqno":1,"name":"고전","cnt":100,"cateno":1}
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/service/servicecate/read_ajax_json_fk.do", method=RequestMethod.GET)
  public String read_ajax_json_fk(int servicecateno) {
    
//    try {
//      Thread.sleep(2000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    
    ServiceCateVO servicecateVO = this.servicecateProc.read(servicecateno);
    // cateno, name, cnt, rdate, udate, seqno, visible
    
    JSONObject json = new JSONObject();
    json.put("servicecateno", servicecateVO.getServicecateno());
    json.put("servicetype_content", servicecateVO.getServicetype_content());
    json.put("cnt", servicecateVO.getCnt());
    json.put("order_index", servicecateVO.getOrder_index());
    json.put("visible", servicecateVO.getVisible());
    
//    int count_by_cateno = this.contentsProc.count_by_cateno(cateno); // cateno가 사용되는 레코드 갯수 파악
//    json.put("count_by_cateno", count_by_cateno);
    json.put("count_by_servicecateno", servicecateVO.getCnt());
    
    return json.toString();
  }
  
  // 수정 처리
  // <FORM name='frm' method='POST' action='./read_update.do'>
  // http://localhost:9093/servicecate/read_update.do
  @RequestMapping(value="/service/servicecate/read_update.do", method = RequestMethod.POST)
  public ModelAndView read_update(ServiceCateVO servicecateVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.servicecateProc.update(servicecateVO);
    
    if (cnt == 0) {
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      mav.setViewName("/service/servicecate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }
  
  // 삭제 처리
  // <FORM name='frm' method='POST' action='./read_delete.do'>
  // http://localhost:9091/cate/read_delete.do
  @RequestMapping(value="/service/servicecate/read_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int servicecateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.servicecateProc.delete(servicecateno);
    
    if (cnt == 0) {
      mav.addObject("code", "delete_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      mav.setViewName("/service/servicecate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }

  // 출력 순서 올림(상향, 10 등 -> 1 등), seqno: 10 -> 1
  // http://localhost:9093/service/servicecate/update_order_index_up.do?servicecateno=1
  @RequestMapping(value="/service/servicecate/update_order_index_up.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_up(int servicecateno) {
    ModelAndView mav = new ModelAndView();

    System.out.println("-> update_order_index_up: " + servicecateno);
    int cnt = this.servicecateProc.update_order_index_up(servicecateno);
    System.out.println("-> cnt: " + cnt);
    
    mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }

  // 출력 순서 내림(상향, 1 등 -> 10 등), seqno: 1 -> 10
  // http://localhost:9093/service/servicecate/update_order_index_down.do?servicecateno=1
  @RequestMapping(value="/service/servicecate/update_order_index_down.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_down(int servicecateno) {
    ModelAndView mav = new ModelAndView();

    System.out.println("-> update_order_index_down: " + servicecateno);
    int cnt = this.servicecateProc.update_order_index_down(servicecateno);
    System.out.println("-> cnt: " + cnt);
    
    mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }

  // 출력 모드 Y로 변경
  // http://localhost:9093/service/servicecate/update_visible_y.do?cateno=1
  @RequestMapping(value="/service/servicecate/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int servicecateno) {
    ModelAndView mav = new ModelAndView();

//    System.out.println("-> update_visible_y: " + cateno);
    int cnt = this.servicecateProc.update_visible_y(servicecateno);
    
    mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 모드 N로 변경
  // http://localhost:9093/service/servicecate/update_visible_n.do?cateno=1
  @RequestMapping(value="/service/servicecate/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int servicecateno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.servicecateProc.update_visible_n(servicecateno);
    
    mav.setViewName("redirect:/service/servicecate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
//  // 글수 증가
//  // http://localhost:9091/cate/update_cnt_add.do?cateno=1
//  @RequestMapping(value="/cate/update_cnt_add.do", method = RequestMethod.GET)
//  public String update_cnt_add(int cateno) {
//    int cnt = this.cateProc.update_cnt_add(cateno);
//    return "변경된 글수: " + cnt;
//  }
//
//  // 글수 감소
//  // http://localhost:9091/cate/update_cnt_sub.do?cateno=1
//  @RequestMapping(value="/cate/update_cnt_sub.do", method = RequestMethod.GET)
//  public String update_cnt_sub(int cateno) {
//    int cnt = this.cateProc.update_cnt_sub(cateno);
//    return "변경된 글수: " + cnt;
//  }  
}


