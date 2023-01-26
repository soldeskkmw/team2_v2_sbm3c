package dev.mvc.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.AddPortOffsetRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
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
  
  public SMSCont() {
    System.out.println("-> SMSCont created.");
  } 
  
  /**
  * 회원 아이디 찾기 sms 등록 폼
  * http://localhost:9093/sms/form.do
  * @return
  */
  @RequestMapping(value = {"/sms/form.do"}, method=RequestMethod.GET)
  public ModelAndView form() {
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/sms/form");  // /WEB-INF/views/sms/form.jsp
      
    return mav;
  }
  
  
  /**
  * 회원 아이디 찾기 sms 등록 처리
  * @param smsVO
  * @param rphone
  * @return
  */
  @RequestMapping(value = {"/sms/proc.do"}, method=RequestMethod.POST)
  public ModelAndView proc(HttpSession session, 
                                      HttpServletRequest request, 
                                      SMSVO smsVO,
                                      String rphone) {

    ModelAndView mav = new ModelAndView();
    
    // 원격 접속시 IPV4로 변경됨 : http://192.168.2.15:9093
    String auth_no = "";
    String search = "아이디찾기";                // search에 ID 찾기 저장
    String ip = request.getRemoteAddr();    // ip를 확인하기 위해 ip에 저장
    Random random = new Random();       // random 생성
    for (int i=0; i<=5; i++) {
      auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
    }
    session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
    
    smsVO.setIp(ip);                                    // smsVO DB에 아이피 저장
    smsVO.setAuthno(auth_no);                   // smsVO DB에 난수번호 저장
    smsVO.setSearch(search);                      // smsVO DB에 아이디찾기 저장
    
    System.out.println("-> IP : " + ip); // 접속자의 IP 수집
    //  db 테이블 추가  [번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)]
    
    String msg = "[www.goingsware.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
    System.out.println(msg);
    
    MemberVO memberVO = memberProc.readBytel(rphone);   // 등록된 전화번호로 회원 정보 읽기
   
    if (memberProc.readBytel(rphone) != null) {  // 입력된 rphone 값이 memberVO에 없을 경우
     
    session.setAttribute("tel", memberVO.getTel());               // 입력된 전화번호를 비교를 위하여 session에 저장
    String session_tel = (String)session.getAttribute("tel");     // 입력된 전화번호를 session에서 꺼냄
    
    session.setAttribute("memberno", memberVO.getMemberno()); // 전화번호와 관련된 회원 번호를 session에 저장
    int session_memberno = (int)session.getAttribute("memberno"); // 회원번호를 session에서 꺼냄
    
    smsVO.setMemberno(session_memberno);                                 // 회원번호를 smsVO DB에 저장
    
    System.out.println("-> session_tel : " + session_tel);                    // 전화번호 수집
    System.out.println("-> memberno : " + session_memberno);        // 회원번호 정보 수집
          
    System.out.println("입력한 전화번호 -> " + rphone);      
    
    int cnt = this.smsProc.proc(smsVO);                                           // sms 등록 처리
    
    if (cnt == 1) {       // cnt가 1일 경우 sms 등록 성공
      mav.addObject("code", "create_success");
      mav.addObject("msg", msg);
      mav.addObject("cnt", cnt);
      mav.addObject("smsVO", smsVO);
      mav.setViewName("/sms/proc");             // /webapp/WEB-INF/views/sms/proc.jsp 
      } else {
        mav.setViewName("redirect:/sms/tel_fail.do");
      }
    }
    return mav;
  }
  
  /**
  * sms 인증번호 입력 폼
  * http://localhost:9093/sms/proc_next.do
  * @return
  */
  @RequestMapping(value = {"/sms/proc_next.do"}, method=RequestMethod.GET)
  public ModelAndView proc_next() {
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/sms/proc_next");  // /WEB-INF/views/sms/proc_next.jsp
    
    return mav;
  }
  
  /**
  * sms 인증번호 재입력 이동 폼
  * http://localhost:9093/sms/confirm.do
  * @return
  */
  @RequestMapping(value = {"/sms/confirm.do"}, method=RequestMethod.GET)
  public ModelAndView confirm() {  
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/sms/confirm");  // /WEB-INF/views/sms/confirm.jsp
    
    return mav;
  }

  /**
   * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
   * @param session 사용자당 할당된 서버의 메모리
   * @param auth_no 사용자가 입력한 번호
   * @return
   */
 @RequestMapping(value = {"/sms/confirm.do"}, method=RequestMethod.POST)
 public ModelAndView confirm(HttpSession session, String auth_no) {
   ModelAndView mav = new ModelAndView();
   
   String session_auth_no = (String)session.getAttribute("auth_no");      // 사용자에게 전송된 번호 session에서 꺼냄
   
   if (session_auth_no.equals(auth_no)) {
       mav.setViewName("redirect:/member/id_search_result.do");         // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
   }else {
       mav.setViewName("redirect:/sms/confirm.do");                           // 콘트롤러의 주소 요청, 자동 이동
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
   
   if (this.adminProc.isAdmin(session)) {   // 관리자 등급이 1~ 20일 경우 회원 sms 목록 출력
     ArrayList<SMSVO> sms_list = smsProc.sms_list();
     
     mav.addObject("sms_list", sms_list);   // 출력

     mav.setViewName("/sms/sms_list");   // /webapp/WEB-INF/views/sms/sms_list.jsp
   } else {
     mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
   }
   return mav;
 }  
 
 /**
 * 관리자 sms 목록 출력 가능
 * http://localhost:9093/sms/admin_sms_list.do
 * @param session
 * @return
 */
 @RequestMapping(value="/sms/admin_sms_list.do", method=RequestMethod.GET)
 public ModelAndView admin_sms_list(HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   if (this.adminProc.isAdmin(session)) {   // 관리자 등급이 1~ 20일 경우 관리자 sms 목록 출력
     ArrayList<SMSVO> admin_sms_list = smsProc.admin_sms_list();
     
     mav.addObject("admin_sms_list", admin_sms_list); // 출력

     mav.setViewName("/sms/admin_sms_list"); // /webapp/WEB-INF/views/sms/admin_sms_list.jsp
     
   } else {
     mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
   }
   return mav;
 }  
 
 /**
  * sms 삭제
  * http://localhost:9093/sms/delete.do?smsno=1
  * @param smsno
  * @return
  */
 @RequestMapping(value="/sms/delete.do", method=RequestMethod.GET)
 public ModelAndView delete(int smsno){
   ModelAndView mav = new ModelAndView();
   
   SMSVO smsVO = this.smsProc.read(smsno);      // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
   mav.addObject("smsVO", smsVO);
   
   mav.setViewName("/sms/delete");                    // /sms/delete.jsp
   
   return mav;
 }

 /**
  * sms 삭제 처리
  * @param smsVO
  * @param smsno
  * @return
  */
 @RequestMapping(value="/sms/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(int smsno){
   ModelAndView mav = new ModelAndView();
   
   // System.out.println("id: " + memberVO.getId());
   SMSVO smsVO = this.smsProc.read(smsno); // 삭제된 정보를 출력하기위해 읽음.
   
   int cnt= smsProc.delete(smsno);

   if (cnt == 1) {
     mav.addObject("code", "delete_success");
     mav.addObject("memberno", smsVO.getMemberno());  // 회원번호 1의 회원 아이피 (0:0:0:0:0:0:0:1) 삭제합니다.
     mav.addObject("ip", smsVO.getIp());                            // 정말로 삭제하시겠습니까?
   } else {
     mav.addObject("code", "delete_fail");
   }

   mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
   mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
   
   mav.setViewName("redirect:/sms/sms_list.do");
   
   return mav;
 }
 
 /**
  * session 객체를 이용한 회원 조회
  * http://localhost:9093/sms/read.do
  * @param smsno
  * @return
  */
 @RequestMapping(value="/sms/read.do", method=RequestMethod.GET)
 public ModelAndView read(HttpSession session){
   ModelAndView mav = new ModelAndView();
   
   if (this.memberProc.isMember(session)) {        // 회원일때만  // 회원 등급이 1~ 20일 경우
     int smsno = (int)session.getAttribute("smsno");
     
     SMSVO smsVO = this.smsProc.read(smsno);    // smsno를 통해 문자의 정보를 읽음
     mav.addObject("smsVO", smsVO);
     mav.setViewName("/sms/read");                   // /sms/read.jsp
   } else {           // 회원이 아닐 경우
     mav.setViewName("/member/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp 
   }
   return mav;
 }
 
 
 /**
  * sms 전화번호가 등록된 전화번호랑 다를경우
  * http://localhost:9093/sms/tel_fail.do
  * @param
  * @return
  */
 @RequestMapping(value = {"/sms/tel_fail.do"}, method=RequestMethod.GET)
 public ModelAndView tel_fail() {
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/sms/tel_fail");  // /WEB-INF/views/sms/tel_fail.jsp
     
   return mav;
 }
 
 /**
  * 회원 비밀번호 찾기 sms 등록 폼
  * http://localhost:9093/sms/form_passwd.do
  * @param
  * @return
  */
@RequestMapping(value = {"/sms/form_passwd.do"}, method=RequestMethod.GET)
public ModelAndView form_passwd() {
  ModelAndView mav = new ModelAndView();
  
  mav.setViewName("/sms/form_passwd");  // /WEB-INF/views/sms/form_passwd.jsp
    
  return mav;
}

/**
  * 회원 비밀번호 찾기 sms 등록 처리
 * http://localhost:9093/sms/proc_passwd.do
 * @param smsVO
 * @param memberid
 * @param rphone
 * @return
 */
@RequestMapping(value = {"/sms/proc_passwd.do"}, method=RequestMethod.POST)
public ModelAndView proc_passwd(HttpSession session, 
                                    HttpServletRequest request, 
                                    SMSVO smsVO,
                                    String memberid,
                                    String rphone) {

  ModelAndView mav = new ModelAndView();
  
  // 원격 접속시 IPV4로 변경됨 : http://192.168.2.15:9093
  String auth_no = "";
  String search = "패스워드찾기";                         // search에 패스워드 찾기를 등록
  
  Random random = new Random();
  for (int i=0; i<=5; i++) {
    auth_no = auth_no + random.nextInt(10);       // 0~9 번호 6자리 생성
  }
  session.setAttribute("auth_no", auth_no);          // 생성된 번호를 비교를 위하여 session에 저장
  
  String ip = request.getRemoteAddr();               // ip를 확인하기 위해 ip에 저장
  smsVO.setIp(ip);
  smsVO.setAuthno(auth_no);
  smsVO.setSearch(search);
  System.out.println("-> IP : " + ip);                   // 접속자의 IP 수집
  //  db 테이블 추가  [번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)] 
  
  String msg = "[www.goingsware.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
  System.out.println(msg);
 
  MemberVO memberVO = memberProc.readByIdTel(memberid, rphone);   // 등록된 회원 아이디와 전화번호로 회원 정보 읽기

  if (memberProc.readByIdTel(memberid, rphone) != null) {  // 입력된 memberid, rphone 값이 memberVO에 있을 경우
    
    System.out.println(memberid);
   
  session.setAttribute("memberid", memberVO.getMemberid()); // 입력한 회원아이디 session에 저장
  String session_memberid = (String)session.getAttribute("memberid"); // session에 저장된 memberid를 꺼냄
  
  session.setAttribute("tel", memberVO.getTel());                      // 입력한 전화번호를 session에 저장
  String session_tel = (String)session.getAttribute("tel");            // session에 저장된 tel을 꺼냄
  
  session.setAttribute("memberno", memberVO.getMemberno());   // 등록된 memberno를 session에 저장
  int session_memberno = (int)session.getAttribute("memberno");  // session에 저장된 memberno을 꺼냄
  
  smsVO.setMemberno(session_memberno);                                  // session에 등록된 memberno를 smsVO에 저장

  System.out.println("-> session_memberid : " + session_memberid); // 접속자의 회원아이디 수집
  System.out.println("-> session_tel : " + session_tel);                       // 접속자의 전화번호 수집
  System.out.println("-> memberno : " + session_memberno);          // 접속자의 회원 번호 수집
  
  int cnt = this.smsProc.proc_passwd(smsVO);                                // 비밀번호 찾기 sms 등록 처리
  
    if (cnt == 1) { // cnt가 1일 경우 등록 성공
      mav.addObject("cnt", cnt);
      mav.addObject("msg", msg);
      mav.addObject("smsVO", smsVO);
      mav.setViewName("/sms/proc_passwd"); // /webapp/WEB-INF/views/sms/proc_passwd.jsp     
    } else {
      mav.setViewName("redirect:/sms/id_tel_fail.do");
    } 
  }
  return mav;
}

/**
* sms 비밀번호 찾기 인증번호 입력 폼
* http://localhost:9093/sms/proc_passwd_next.do
* @return
*/
@RequestMapping(value = {"/sms/proc_passwd_next.do"}, method=RequestMethod.GET)
public ModelAndView proc_passwd_next() {
  
  ModelAndView mav = new ModelAndView();
  
  mav.setViewName("/sms/proc_passwd_next");  // /WEB-INF/views/sms/proc_passwd_next.jsp
  
  return mav;
}

/**
* sms 인증번호 재입력 이동 폼
* http://localhost:9093/sms/confirm_passwd.do
* @return
*/
@RequestMapping(value = {"/sms/confirm_passwd.do"}, method=RequestMethod.GET)
public ModelAndView confirm_passwd() {  
  ModelAndView mav = new ModelAndView();
  
  mav.setViewName("/sms/confirm_passwd");  // /WEB-INF/views/sms/confirm_passwd.jsp
  
  return mav;
}

/**
 * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
 * //http://localhost:9093/sms/confirm_passwd.do
 * @param session 사용자당 할당된 서버의 메모리
 * @param auth_no 사용자가 입력한 번호
 * @return
 */
@RequestMapping(value = {"/sms/confirm_passwd.do"}, method=RequestMethod.POST)
public ModelAndView confirm_passwd(HttpSession session, String auth_no) {
 ModelAndView mav = new ModelAndView();
 
 String session_auth_no = (String)session.getAttribute("auth_no");          // 사용자에게 전송된 번호 session에서 꺼냄
 
 if (session_auth_no.equals(auth_no)) { // 문자로 전송한 번호와 사용자가 입력한 번호가 같을 때 실행
     mav.setViewName("redirect:/member/passwd_search_result.do");     // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
 }else { // 문자로 전송한 번호와 사용자가 입력한 번호가 다를때 실행
     mav.setViewName("redirect:/sms/confirm_passwd.do");                  // 콘트롤러의 주소 요청, 자동 이동
   }
 return mav;
}

/**
* 입력한 아이디와 전화번호가 다를때 이동하는 폼
* http://localhost:9093/sms/id_tel_fail.do
* @return
*/
@RequestMapping(value = {"/sms/id_tel_fail.do"}, method=RequestMethod.GET)
public ModelAndView id_tel_fail() {
  ModelAndView mav = new ModelAndView();
  
  mav.setViewName("/sms/id_tel_fail");  // /WEB-INF/views/sms/id_tel_fail.jsp
    
  return mav;
}
  
/**
* 관리자 아이디 찾기 sms 등록 폼
* http://localhost:9093/sms/admin_form.do
* @return
*/
 @RequestMapping(value = {"/sms/admin_form.do"}, method=RequestMethod.GET)
 public ModelAndView admin_form() {
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/sms/admin_form");  // /WEB-INF/views/sms/admin_form.jsp
     
   return mav;
 }
 
 
 /**
 * 관리자 아이디 찾기 sms 등록 처리
 * http://localhost:9093/sms/admin_proc.do
 * @param session 사용자당 할당된 서버의 메모리
 * @param smsVO
 * @param rphone
 * @return
 */
 @RequestMapping(value = {"/sms/admin_proc.do"}, method=RequestMethod.POST)
 public ModelAndView admin_proc(HttpSession session, 
                                     HttpServletRequest request, 
                                     SMSVO smsVO,
                                     String rphone) {

   ModelAndView mav = new ModelAndView();
   
   // 원격 접속시 IPV4로 변경됨 : http://192.168.2.15:9093
   String auth_no = "";
   String search = "아이디찾기";                       // search에 아이디 찾기 저장
   String ip = request.getRemoteAddr();           // ip를 확인하기 위해 ip에 저장
   
   Random random = new Random();
   for (int i=0; i<=5; i++) {
     auth_no = auth_no + random.nextInt(10);  // 0~9 번호 6자리 생성
   }
   session.setAttribute("auth_no", auth_no);     // 생성된 번호를 비교를 위하여 session에 저장
   
   smsVO.setIp(ip);                                        // smsVO DB에 아이피 저장
   smsVO.setAuthno(auth_no);                       // smsVO DB에 인증번호 저장
   smsVO.setSearch(search);                          // smsVO DB에 아이디 찾기 저장
   
   System.out.println("-> IP : " + ip); // 접속자의 IP 수집
   //  db 테이블 추가  [번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)]
   
   String msg = "[www.goingsware.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
   System.out.println(msg);
   System.out.println("입력한 전화번호 -> " + rphone);
   
   AdminVO adminVO = adminProc.readBytel(rphone);     // 등록된 전화번호로 관리자 정보 읽기
  
   if (adminProc.readBytel(rphone) != null) {                     // 입력된 rphone 값이 adminVO에 있을 경우
    
   session.setAttribute("admintel", adminVO.getAdmintel());                     // 입력된 관리자 전화번호를 session에 저장
   String session_admin_tel = (String)session.getAttribute("admintel");       // 입력된 관리자 전화번호를 session에서 꺼냄
   
   session.setAttribute("adminno", adminVO.getAdminno());                     // 입력된 관리자 번호를 session에 저장
   int session_adminno = (int)session.getAttribute("adminno");                 // 입력된 관리자 번호를 session에서 꺼냄
   
   smsVO.setAdminno(session_adminno);                                                // 입력된 관리자 번호를 smsVO에 저장
   
   System.out.println("-> session_admin_tel : " + session_admin_tel);        // 관리자의 전화번호 수집
   System.out.println("-> session_adminno : " + session_adminno);           // 관리자 정보 수집
     
   int cnt = this.smsProc.admin_proc(smsVO);                                          // sms 관리자 등록 처리
   
   
   if (cnt == 1) { // cnt가 1일 경우 관리자 sms 등록
     mav.addObject("code", "create_success");
     mav.addObject("msg", msg);
     mav.addObject("cnt", cnt);
     mav.addObject("smsVO", smsVO);
     mav.setViewName("/sms/admin_proc");                          // /webapp/WEB-INF/views/sms/admin_proc.jsp
     } else {        // cnt가 1이 아닐경우 이동
       mav.setViewName("redirect:/sms/tel_fail.do");  
     }
   }
   return mav;
 }
 
 /**
  * 관리자 sms 인증번호 입력 폼
 * http://localhost:9093/sms/admin_proc_next.do
 * @return
 */
 @RequestMapping(value = {"/sms/admin_proc_next.do"}, method=RequestMethod.GET)
 public ModelAndView admin_proc_next() {
   
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/sms/admin_proc_next");  // /WEB-INF/views/sms/admin_proc_next.jsp
   
   return mav;
 }
 
 /**
  * 관리자 sms 인증번호 재입력 이동 폼
 * http://localhost:9093/sms/admin_confirm.do
 * @return
 */
 @RequestMapping(value = {"/sms/admin_confirm.do"}, method=RequestMethod.GET)
 public ModelAndView admin_confirm() {  
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/sms/admin_confirm");  // /WEB-INF/views/sms/admin_confirm.jsp
   
   return mav;
 }

 /**
  * 문자로 전송된 번호와 관리자가 입력한 번호를 비교합니다.
  *  http://localhost:9093/sms/admin_confirm.do
  * @param session 사용자당 할당된 서버의 메모리
  * @param auth_no 사용자가 입력한 번호
  * @return
  */
@RequestMapping(value = {"/sms/admin_confirm.do"}, method=RequestMethod.POST)
public ModelAndView admin_confirm(HttpSession session, String auth_no) {
  ModelAndView mav = new ModelAndView();
  
  String session_auth_no = (String)session.getAttribute("auth_no");     // 사용자에게 전송된 번호 session에서 꺼냄
  
  if (session_auth_no.equals(auth_no)) {    // 사용자에게 보낸 인증번호와 사용자가 입력한 인증번호가 같을때 실행
      mav.setViewName("redirect:/admin/id_search_result.do");           // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
  }else { // 사용자에게 보낸 인증번호와 사용자가 입력한 인증번호가 다를때 실행
      mav.setViewName("redirect:/sms/admin_confirm.do");               // 콘트롤러의 주소 요청, 자동 이동
    }
  return mav;
}

/**
  * 관리자 비밀번호 찾기 sms 등록 폼
* http://localhost:9093/sms/admin_form_passwd.do
* @return
*/
@RequestMapping(value = {"/sms/admin_form_passwd.do"}, method=RequestMethod.GET)
public ModelAndView admin_form_passwd() {
ModelAndView mav = new ModelAndView();

mav.setViewName("/sms/admin_form_passwd");  // /WEB-INF/views/sms/admin_form_passwd.jsp
  
return mav;
}

/**
 * 관리자 비밀번호 찾기 sms 등록 처리
* http://localhost:9093/sms/admin_proc_passwd.do
* @param smsVO
* @param adminid
* @param rphone
* @return
*/
@RequestMapping(value = {"/sms/admin_proc_passwd.do"}, method=RequestMethod.POST)
public ModelAndView admin_proc_passwd(HttpSession session, 
                                  HttpServletRequest request, 
                                  SMSVO smsVO,
                                  String adminid,
                                  String rphone) {

ModelAndView mav = new ModelAndView();

// 원격 접속시 IPV4로 변경됨 : http://192.168.2.15:9093
String auth_no = "";
String search = "패스워드찾기";                        // search에 패스워드 찾기를 등록

Random random = new Random();
for (int i=0; i<=5; i++) {
  auth_no = auth_no + random.nextInt(10);      // 0~9 번호 난수 6자리 생성
}
session.setAttribute("auth_no", auth_no);         // 생성된 번호를 비교를 위하여 session에 저장

String ip = request.getRemoteAddr();              // ip를 확인하기 위해 ip에 저장
smsVO.setIp(ip);                                            // smsVO DB에 아이피 저장
smsVO.setAuthno(auth_no);                           // smsVO DB에 인증번호 저장
smsVO.setSearch(search);                              // smsVO DB에 비밀번호 찾기 저장
System.out.println("-> IP : " + ip); // 접속자의 IP 수집

//  db 테이블 추가  [번호, ip, auth_no, 날짜 -> SMS Oracle table 등록, 문자 전송 내역 관리 목적으로 저장(필수 아니나 권장)] 


String msg = "[www.goingsware.co.kr] [" + auth_no + "]을 인증번호란에 입력해주세요.";
System.out.println(msg);

AdminVO adminVO = adminProc.readByIdTel(adminid, rphone);   // 등록된 관리자 아이디와 전화번호로 관리자 정보 읽기

if (adminProc.readByIdTel(adminid, rphone) != null) {  // 입력된 adminid, rphone 값이 adminVO에 있을 경우
  
  System.out.println(adminid);
  
session.setAttribute("adminid", adminVO.getAdminid());                        // 입력한 관리자 아이디를 session에 저장
String session_adminid = (String)session.getAttribute("adminid");           // session에 저장된 tel을 꺼냄

session.setAttribute("admintel", adminVO.getAdmintel());                      // 입력한 관리자 전화번호를 session에 저장
String session_admintel = (String)session.getAttribute("admintel");         // session에 저장된 tel을 꺼냄

session.setAttribute("adminno", adminVO.getAdminno());                      // 입력한 관리자 번호를 session에 저장
int session_admnino = (int)session.getAttribute("adminno");                  // session에 저장된 tel을 꺼냄

smsVO.setMemberno(session_admnino);                                               // session에 등록된 adminno를 smsVO에 저장                                 

System.out.println("-> session_adminid : " + session_adminid); // 접속자의 전화번호 수집
System.out.println("-> session_admintel : " + session_admintel); // 접속자의 전화번호 수집
System.out.println("-> session_admnino : " + session_admnino); // 회원 정보 수집

int cnt = this.smsProc.admin_proc_passwd(smsVO);                              // 비밀번호 찾기 sms 등록 처리

  if (cnt == 1) {   // cnt가 1일 경우 등록 처리
    mav.addObject("cnt", cnt);
    mav.addObject("msg", msg);  // request.setAttribute("msg")
    mav.addObject("smsVO", smsVO);
    mav.setViewName("/sms/admin_proc_passwd"); // /webapp/WEB-INF/views/sms/admin_proc_passwd.jsp     
  } else {
    mav.setViewName("redirect:/sms/id_tel_fail.do");
  } 
}
return mav;
}

/**
* 관리자 sms 비밀번호 찾기 인증번호 입력 폼
* http://localhost:9093/sms/admin_proc_passwd_next.do
* @return
*/
@RequestMapping(value = {"/sms/admin_proc_passwd_next.do"}, method=RequestMethod.GET)
public ModelAndView admin_proc_passwd_next() {

ModelAndView mav = new ModelAndView();

mav.setViewName("/sms/admin_proc_passwd_next");  // /WEB-INF/views/sms/admin_proc_passwd_next.jsp

return mav;
}


/**
* 관리자 sms 인증번호 재입력 이동 폼
* http://localhost:9093/sms/admin_confirm_passwd.do
* @return
*/
@RequestMapping(value = {"/sms/admin_confirm_passwd.do"}, method=RequestMethod.GET)
public ModelAndView admin_confirm_passwd() {  
ModelAndView mav = new ModelAndView();

mav.setViewName("/sms/admin_confirm_passwd");  // /WEB-INF/views/sms/proc_next.jsp

return mav;
}

/**
* 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
* http://localhost:9093/sms/admin_confirm_passwd.do
* @param session 사용자당 할당된 서버의 메모리
* @param auth_no 사용자가 입력한 번호
* @return
*/
@RequestMapping(value = {"/sms/admin_confirm_passwd.do"}, method=RequestMethod.POST)
public ModelAndView admin_confirm_passwd(HttpSession session, String auth_no) {
ModelAndView mav = new ModelAndView();

String session_auth_no = (String)session.getAttribute("auth_no");     // 사용자에게 전송된 번호 session에서 꺼냄

if (session_auth_no.equals(auth_no)) {
   mav.setViewName("redirect:/admin/passwd_search_result.do");    // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
}else {
   mav.setViewName("redirect:/sms/confirm_passwd.do");              // 콘트롤러의 주소 요청, 자동 이동
 }
return mav;
}

  
}
