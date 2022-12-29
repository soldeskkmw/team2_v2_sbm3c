package dev.mvc.sms;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;



@Controller
public class SMSCont {
  
  @Autowired
  @Qualifier("dev.mvc.sms.SMSProc")
  private SMSProcInter smsProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;

  private MemberVO memberVO;
  
  public SMSCont() {
    System.out.println("-> SMSCont created.");
  }
  
  // http://localhost:9093/sms/form.do
  @RequestMapping(value = {"/sms/form.do"}, method=RequestMethod.GET)
  public ModelAndView form() {
    ModelAndView mav = new ModelAndView();
   
    
    
    mav.setViewName("/sms/form");  // /WEB-INF/views/sms/form.jsp
      
    return mav;
  }
  

  
  
  // 등록 폼
  // http://localhost:9093/sms/proc.do
  @RequestMapping(value="/sms/proc.do", method = RequestMethod.GET)
  public ModelAndView proc() {    
    ModelAndView mav = new ModelAndView();
    
//    SMSVO smsVO = this.smsProc.proc(smsVO);
//    mav.addObject("smsVO", smsVO);
    
//    MemberVO memberVO = this.memberProc.read(smsVO.getMemberno());
//    mav.addObject("memberVO", memberVO);

    mav.setViewName("/sms/proc"); // /webapp/WEB-INF/views/cate/create.jsp
    
    return mav;
  }
  
  
  // http://localhost:9093/sms/proc.do
  @RequestMapping(value = {"/sms/proc.do"}, method=RequestMethod.POST)
  public ModelAndView proc(HttpSession session, HttpServletRequest request, SMSVO smsVO) {

    ModelAndView mav = new ModelAndView();
    
//    smsVO.setMemberno(memberVO.memberno);
    
    // 원격 접속시 IPV4로 변경됨 : http://192.168.2.15:9091
    String auth_no = "";
    
    Random random = new Random();
    for (int i=0; i<=5; i++) {
      auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
    }
    session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
    String ip = request.getRemoteAddr(); // ip를 확인하기 위해 ip에 저장
    smsVO.setIp(ip);
    smsVO.setAuthno(auth_no);
    
    System.out.println("-> IP : " + ip); // 접속자의 IP 수집
    //  db 테이블 추가  [번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)]         // 번호, 회원 ID, msg 회원 -> SMS Oracle table 등록
    
    
    String msg = "[www.goingsware.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
    System.out.print(msg);
    
//    System.out.print(memberno);
    
    int cnt = this.smsProc.proc(smsVO);
    
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/sms/proc_next.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/cate/list_all"); // /webapp/WEB-INF/views/cate/list_all.jsp X
    } else { // 등록 실패
      mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
//    mav.addObject("memberno", memberno);
    mav.addObject("cnt", cnt);
    mav.addObject("msg", msg);  // request.setAttribute("msg")
//    mav.setViewName("/sms/proc");  // /WEB-INF/views/sms/proc.jsp
    
    return mav;
  }
  
  // http://localhost:9093
  // http://localhost:9093/sms/proc_next.do
  @RequestMapping(value = {"/sms/proc_next.do"}, method=RequestMethod.GET)
  public ModelAndView proc_next() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sms/proc_next");  // /WEB-INF/views/sms/proc_next.jsp
    
    return mav;
  }
  
  
  // http://localhost:9093
  // http://localhost:9093/sms/confirm.do
  @RequestMapping(value = {"/sms/confirm.do"}, method=RequestMethod.GET)
  public ModelAndView confirm() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sms/confirm");  // /WEB-INF/views/sms/proc_next.jsp
    
    return mav;
  }

  
  //http://localhost:9093/sms/confirm.do
  /**
   * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
   * @param session 사용자당 할당된 서버의 메모리
   * @param auth_no 사용자가 입력한 번호
   * @return
   */
 @RequestMapping(value = {"/sms/confirm.do"}, method=RequestMethod.POST)
 public ModelAndView confirm(HttpSession session, String auth_no) {
   ModelAndView mav = new ModelAndView();
   
   String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
   
   if (session_auth_no.equals(auth_no)) {
     
//     MemberVO memberVO = this.memberProc.readBytel(tel);
//     mav.addObject("memberVO", memberVO);
     
     mav.setViewName("/member/id_search_result"); // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
   }else {
//     mav.setViewName("/sms/confirm");

     mav.setViewName("redirect:/sms/confirm.do"); // 콘트롤러의 주소 요청, 자동 이동
   }
   
   
   
   return mav;
 }
  
 
 /**
 * 목록 출력 가능
 * http://localhost:9093/sms/sms_list.do
 * @param session
 * @return
 */
 @RequestMapping(value="/sms/sms_list.do", method=RequestMethod.GET)
 public ModelAndView sms_list(HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   if (this.adminProc.isAdmin(session)) {
     ArrayList<SMSVO> sms_list = smsProc.sms_list();
     
     mav.addObject("sms_list", sms_list);

     mav.setViewName("/sms/sms_list"); // /webapp/WEB-INF/views/member/list.jsp
     
   } else {
     mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
   }
   return mav;
 }  
 
 
 
 
}
