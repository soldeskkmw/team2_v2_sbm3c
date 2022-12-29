package dev.mvc.cate;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.post.PostProcInter;

@Controller
public class CateCont {
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc") 
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.post.PostProc")
  private PostProcInter postProc;
  
  public CateCont() {
    System.out.println("-> CateCont created.");
  }
  
  // 등록 폼 http://localhost:9093/cate/create.do
  @RequestMapping(value="/cate/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("-> create()");
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cate/create"); // /webapp/WEB-INF/views/cate/create.jsp
    
    return mav;
  }
  
  // 등록 처리
  // <FORM name='frm' method='POST' action='./create.do'>
  // http://localhost:9093/cate/create.do
  @RequestMapping(value="/cate/create.do", method = RequestMethod.POST)
  public ModelAndView create(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.create(cateVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }

  /**
   * 모든 레코드 목록, http://localhost:9093/cate/list_all.do
   * @return
   */
  @RequestMapping(value="/cate/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<CateVO> list = this.cateProc.list_all();
    mav.addObject("list", list);
    mav.setViewName("/cate/list_all_ajax"); // /webapp/WEB-INF/views/cate/list_all_ajax.jsp
    
    return mav;
  }
  
  /**
   * Ajax, JSON 지원 읽기, http://localhost:9093/cate/read_ajax_json.do?cateno=1
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cate/read_ajax_json.do", method=RequestMethod.GET)
  public String read_ajax_json(int cateno) {
    
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    CateVO cateVO = this.cateProc.read(cateno);
    
    JSONObject json = new JSONObject();
    json.put("cateno", cateVO.getCateno());
    json.put("catename", cateVO.getCatename());
    json.put("seqno", cateVO.getSeqno());
    json.put("visible", cateVO.getVisible());
    
    return json.toString();
  }
  
  /**
   * cateno를 FK로 사용하는 레코드 갯수 읽기, http://localhost:9093/cate/read_ajax_json_fk.do?cateno=1
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cate/read_ajax_json_fk.do", method=RequestMethod.GET)
  public String read_ajax_json_fk(int cateno) {
    
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    CateVO cateVO = this.cateProc.read(cateno);
    
    JSONObject json = new JSONObject();
    json.put("cateno", cateVO.getCateno());
    json.put("catename", cateVO.getCatename());
    json.put("seqno", cateVO.getSeqno());
    json.put("visible", cateVO.getVisible());
    
    int count_by_cateno = this.postProc.count_by_cateno(cateno); // cateno가 사용되는 레코드 갯수 파악
    json.put("count_by_cateno", count_by_cateno);
    
    return json.toString();
  }
  
  // 수정 처리
  // <FORM name='frm' method='POST' action='./read_update.do'>
  // http://localhost:9093/cate/read_update.do
  @RequestMapping(value="/cate/read_update.do", method = RequestMethod.POST)
  public ModelAndView read_update(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    int cnt = this.cateProc.update(cateVO);
    if (cnt == 0) {
      mav.addObject("code", "update_fail");
    }
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    return mav;
  }
  
  // 삭제 처리
  // <FORM name='frm' method='POST' action='./read_delete.do'>
  // http://localhost:9093/cate/read_delete.do
  @RequestMapping(value="/cate/read_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int cateno) {
    ModelAndView mav = new ModelAndView();
    int cnt = this.cateProc.delete(cateno);
    if (cnt == 0) {
      mav.addObject("code", "delete_fail");
    }
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 삭제
      mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/cate/list_all"); // /webapp/WEB-INF/views/cate/list_all.jsp X
    } else { // 등록 실패
      mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }
  
  // 출력 순서 올림(상향, 10 등 -> 1 등), seqno: 10 -> 1
  // http://localhost:9093/cate/update_seqno_up.do?cateno=1
  @RequestMapping(value="/cate/update_seqno_up.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_up(int cateno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.cateProc.update_seqno_up(cateno);
    
    mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }

  // 출력 순서 내림(상향, 1 등 -> 10 등), seqno: 1 -> 10
  // http://localhost:9093/cate/update_seqno_down.do?cateno=2
  @RequestMapping(value="/cate/update_seqno_down.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_down(int cateno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.cateProc.update_seqno_down(cateno);
    
    mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 모드 Y로 변경
  // http://localhost:9093/cate/update_visible_y.do?cateno=1
  @RequestMapping(value="/cate/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int cateno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.cateProc.update_visible_y(cateno);
    
    mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 모드 N로 변경
  // http://localhost:9093/cate/update_visible_n.do?cateno=1
  @RequestMapping(value="/cate/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int cateno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.cateProc.update_visible_n(cateno);
    
    mav.setViewName("redirect:/cate/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
}


