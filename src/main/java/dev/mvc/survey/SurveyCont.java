package dev.mvc.survey;

import java.util.ArrayList;
import java.util.HashMap;

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

import dev.mvc.admin.AdminProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.tool.Tool;

@Controller
public class SurveyCont {
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc") 
  private SurveyProcInter surveyProc; // "dev.mvc.survey.SurveyProc" 이름으로 지정된 클래스의 객체가 자동 생성되어 할당
 
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;

  public SurveyCont() {
    System.out.println("-> SurveyCont created.");

  }
  
  // 등록 폼
  // http://localhost:9093/survey/survey_create.do
  @RequestMapping(value="/survey/survey_create.do", method = RequestMethod.GET)
  public ModelAndView survey_create() {
    System.out.println("-> create()");
    
    ModelAndView mav = new ModelAndView();
//    JSP View path
//    spring.mvc.view.prefix=/WEB-INF/views/
//    spring.mvc.view.suffix=.jsp
    mav.setViewName("/survey/survey_create"); // /webapp/WEB-INF/views/survey/survey_create.jsp
    
    return mav;
  }

  // 등록 처리
  // <FORM name='frm' method='POST' action='./survey_create.do'>
  // http://localhost:9093/survey/survey_create.do
  @RequestMapping(value="/survey/survey_create.do", method = RequestMethod.POST)
  public ModelAndView survey_create(SurveyVO surveyVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.survey_create(surveyVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    
//    JSP View path
//    spring.mvc.view.prefix=/WEB-INF/views/
//    spring.mvc.view.suffix=.jsp
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/survey/survey_list.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/survey/survey_list"); // /webapp/WEB-INF/views/survey/survey_list.jsp X
    } else { // 등록 실패
      mav.setViewName("/survey/survey_msg"); // /webapp/WEB-INF/views/survey/surveymsg.jsp      
    }
    
    return mav;
  }
  
  /**
   * 조회, http://localhost:9093/survey/survey_list.do
   * @return
   */
  @RequestMapping(value="/survey/survey_list.do", method=RequestMethod.GET)
  public ModelAndView survey_list() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<SurveyVO> survey_list = this.surveyProc.survey_list();
    mav.addObject("survey_list", survey_list);

    mav.setViewName("/survey/survey_list"); // /webapp/WEB-INF/views/survey/survey_read.jsp
    
    return mav;
  }
  
  /**
   * 조회, http://localhost:9093/survey/survey_read.do?surveyno=1
   * @return
   */
  @RequestMapping(value="/survey/survey_read.do", method=RequestMethod.GET)
  public ModelAndView survey_read(int surveyno) {
    
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.survey_read(surveyno);
        
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/survey/survey_read"); // /webapp/WEB-INF/views/survey/survey_read.jsp
    
    return mav;
  }
  
  
  /**
   * 수정 폼
   * http://localhost:9093/survey/survey_read_update.do?surveyno=1
   * 
   * @return
   */
  @RequestMapping(value = "/survey/survey_read_update.do", method = RequestMethod.GET)
  public ModelAndView surveyno_read_update(int surveyno) {
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.survey_read(surveyno);
    mav.addObject("surveyVO", surveyVO);

    
    mav.setViewName("/survey/survey_read_update"); // /WEB-INF/views/survey/survey_read_update.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/survey/survey_read_update.do?survey=1
   * 
   * @return
   */
  @RequestMapping(value = "/survey/survey_read_update.do", method = RequestMethod.POST)
  public ModelAndView survey_read_update(HttpSession session, SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      int cnt = this.surveyProc.survey_read_update(surveyVO);

      // mav 객체 이용
      mav.addObject("surveyno", surveyVO.getSurveyno());

      mav.setViewName("redirect:/survey/survey_read.do");      
    } else {
      mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/survey/survey_msg.do"); // GET
    }
        
    return mav; // forward
  }
  
  /**
   * 삭제 폼
   * @param surveyno
   * @return
   */
  @RequestMapping(value="/survey/survey_read_delete.do", method=RequestMethod.GET )
  public ModelAndView survey_read_delete(int surveyno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    SurveyVO surveyVO = this.surveyProc.survey_read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/survey/survey_read_delete");  // /webapp/WEB-INF/views/survey/survey_read_delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9093/survey/survey_read_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/survey_read_delete.do", method = RequestMethod.POST)
  public ModelAndView notice_read_delete(int surveyno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    SurveyVO surveyVO = surveyProc.survey_read(surveyno);
        
    String surveytopic = surveyVO.getSurveytopic();
//    String noticecontent = surveyVO.getNoticecontent();
        
    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
        
    cnt = this.surveyProc.survey_read_delete(surveyno); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
//    HashMap<String, Object> page_map = new HashMap<String, Object>();
//
//    page_map.put("noticeword", noticeword);
//    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
//    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
//    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
//    if (noticeProc.search_count(page_map) % Contents.RECORD_PER_PAGE == 0) {
//      now_page = now_page - 1;
//      if (now_page < 1) {
//        now_page = 1; // 시작 페이지
//      }
//    }
    // -------------------------------------------------------------------------------------

    mav.setViewName("redirect:/survey/survey_list.do"); 
    
    return mav;
  }   
  
//  /**
//   * cate + contents INNER JOIN 목록, http://localhost:9091/./notice_list.do
//   * @return
//   */
//  @RequestMapping(value="/notice/notice_list.do", method=RequestMethod.GET)
//  public ModelAndView notice_list() {
//    ModelAndView mav = new ModelAndView();
//
//    mav.setViewName("/notice/notice_list"); // /webapp/WEB-INF/views/notice/notice_list.jsp
//    
//    return mav;
//  }
  


  
}





