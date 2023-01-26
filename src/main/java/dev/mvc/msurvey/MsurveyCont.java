package dev.mvc.msurvey;

import java.util.ArrayList;
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

=======
import java.util.HashMap;

import javax.mail.Session;
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
import dev.mvc.member.MemberProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberVO;
import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.tool.Tool;

>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
@Controller
public class MsurveyCont {
  @Autowired
  @Qualifier("dev.mvc.msurvey.MsurveyProc") 
<<<<<<< HEAD
  private MsurveyProcInter msurveyProc;
  
  public MsurveyCont() {
    System.out.println("-> MsurveyCont created.");
  }
  
  /**
   * 모든 레코드 목록, http://localhost:9093/msurvey/list_all.do
   * @return
   */
  @RequestMapping(value="/msurvey/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<MsurveyVO> list = this.msurveyProc.list_all();
    mav.addObject("list", list);
    
    mav.setViewName("/msurvey/list_all"); // /webapp/WEB-INF/views/msurvey/list_all.jsp
    
    return mav;
  }


  }


=======
  private MsurveyProcInter msurveyProc; // "dev.mvc.msurvey.MsurveyProc" 이름으로 지정된 클래스의 객체가 자동 생성되어 할당  

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc = null;

  public MsurveyCont() {
    System.out.println("-> MsurveyCont created.");

  }
  
  // 등록 폼
  // http://localhost:9093/msurvey/msurvey_create.do
  @RequestMapping(value="/msurvey/msurvey_create.do", method = RequestMethod.GET)
  public ModelAndView msurvey_create(HttpSession session, int surveyno) {
    
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.survey_read(surveyno);
    
    session.setAttribute("surveyno", surveyVO.getSurveyno());
    
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/msurvey/msurvey_create"); // /webapp/WEB-INF/views/msurvey/msurvey_create.jsp
    
    return mav;
  }
  
//등록 처리
 // <FORM name='frm' method='POST' action='./survey_create.do'>
 // http://localhost:9093/survey/survey_create.do
 @RequestMapping(value="/msurvey/msurvey_create.do", method = RequestMethod.POST)
 public ModelAndView msurvey_create(MsurveyVO msurveyVO, HttpSession session) {
   
   ModelAndView mav = new ModelAndView();  
   
   int surveyno = (int)session.getAttribute("surveyno");
   
   if (this.memberProc.isMember(session)) {
     
     int memberno = (int)session.getAttribute("memberno");
     
     msurveyVO.setMemberno(memberno);
     msurveyVO.setSurveyno(surveyno);
     
     int cnt = this.msurveyProc.msurvey_create(msurveyVO);

     if (cnt == 1) {

       mav.addObject("code", "create_success");
       mav.setViewName("redirect:/survey/survey_list.do"); // 콘트롤러의 주소 요청, 자동 이동
       // request.setAttribute("code", "create_success");
     } else {
       mav.addObject("code", "create_fail");
       mav.setViewName("/survey/survey_msg"); // /webapp/WEB-INF/views/survey/surveymsg.jsp      
     }
     
     mav.addObject("cnt", cnt);
     
   } else {
     mav.setViewName("/member/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp
   }
  return mav;
 
 }
  
 /**
  * 조회, http://localhost:9093/survey/survey_read.do?surveyno=1
  * @return
  */
 @RequestMapping(value="/msurvey/msurvey_read.do", method=RequestMethod.GET)
 public ModelAndView survey_read(int msurveyno, HttpSession session) {
   
   ModelAndView mav = new ModelAndView();
   
   MsurveyVO msurveyVO = this.msurveyProc.msurvey_read(msurveyno);
       
   mav.addObject("msurveyVO", msurveyVO);
   
   mav.setViewName("/msurvey/msurvey_read"); // /webapp/WEB-INF/views/survey/survey_read.jsp
   
   return mav;
 }
 
 
 
 /**
  * 조회, http://localhost:9093/msurvey/msurvey_read.do?msurveyno=1
  * @return
  */
 @RequestMapping(value="/msurvey/msurvey_read_all.do", method=RequestMethod.GET)
 public ModelAndView msurvey_read_all(int msurveyno, int surveyno, HttpSession session) {
   
   ModelAndView mav = new ModelAndView();
   
   SurveyVO surveyVO = surveyProc.survey_read(surveyno);
   
   MsurveyVO msurveyVO = this.msurveyProc.msurvey_read(msurveyno);
   
   mav.addObject("surveyVO", surveyVO); 
   mav.addObject("msurveyVO", msurveyVO);
   
   
   mav.setViewName("/msurvey/msurvey_read_all"); // /webapp/WEB-INF/views/msurvey/msurvey_read.jsp
   
   return mav;
 }

  
}
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f





