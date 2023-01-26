package dev.mvc.admin;

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
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;
  
  public AdminCont(){
    System.out.println("-> AdminCont created.");
  }
  
//http://localhost:9093/admin/checkID.do?adminid=admin1
 /**
 * ID 중복 체크, JSON 출력
 * @param adminid
 * @return
 */
 @ResponseBody
 @RequestMapping(value="/admin/checkID.do", method=RequestMethod.GET ,
                          produces = "text/plain;charset=UTF-8" )
 public String checkID(String adminid) {
   
   int cnt = this.adminProc.checkID(adminid); // 저장된 관리자 아이디를 중복 처리 하지 않기 위해 읽음.
  
   JSONObject json = new JSONObject();    // json으로 변환
   json.put("cnt", cnt);                              // put을 통해 json에 cnt 저장
  
   return json.toString(); 
 }
  
 // http://localhost:9093/admin/create.do
 /**
 * 회원가입 폼 (등록)
 * @return
 */
 @RequestMapping(value="/admin/create.do", method=RequestMethod.GET )
 public ModelAndView create() {
   
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/admin/create"); // /WEB-INF/views/admin/create.jsp
  
   return mav; 
 }

  /**
  * 관리자 회원가입 폼 (등록 처리)
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/create.do", method=RequestMethod.POST)
 public ModelAndView create(AdminVO adminVO){
   ModelAndView mav = new ModelAndView();
   
   adminVO.setAdmingrade(11); // 회원가입시 관리자 등급 11 등록
   
   int cnt= adminProc.create(adminVO); // 관리자 회원가입 처리
   
   if (cnt == 1) {  // cnt가 1일경우 관리자 회원가입 성공
     
     mav.addObject("code", "create_success");     // 회원가입 성공 create_success에 대한 내용을 출력
     mav.addObject("adminname", adminVO.getAdminname());
     mav.addObject("adminid", adminVO.getAdminid());            // 관리자님(admin1) 회원 가입을 축하합니다.
   } else { // 회원가입 실패
     mav.addObject("code", "create_fail");          // 회원가입 실패 create_fail에 대한 내용을 출력
   }
   
   mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)   // cnt 정보 저장
   
   mav.addObject("url", "/admin/msg");  // /admin/msg -> /admin/msg.jsp
   
   mav.setViewName("redirect:/admin/msg.do");   // 새로 고침 방지
   
   return mav;
 }
 
 /**
  * 새로고침 방지, EL에서 param으로 접근
  * @param url
  * @return
  */
 @RequestMapping(value="/admin/msg.do", method=RequestMethod.GET)
 public ModelAndView msg(String url){
   ModelAndView mav = new ModelAndView();

   mav.setViewName(url);
   
   return mav; // forward
 }
 
 /**
 * 관리자 등급을 확인후 관리자 목록 출력
 * http://localhost:9093/admin/list.do
 * @param session
 * @return
 */
 @RequestMapping(value="/admin/list.do", method=RequestMethod.GET)
 public ModelAndView list(HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   if (this.adminProc.isAdmin_grade(session)) {   // 관리자 등급 처리를 위해 읽음  // 관리자 등급이 1~ 20일 경우 목록 출력
     ArrayList<AdminVO> list = adminProc.list();
     
     mav.addObject("list", list);

     mav.setViewName("/admin/list"); // /webapp/WEB-INF/views/admin/list.jsp
     
   } else {   // 관리자 등급이 1~20이 아닐경우 이동
     mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
   }
   return mav;
 }  
 
 /**
  * session 객체를 이용한 관리자 조회
  * http://localhost:9093/admin/read.do
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/read.do", method=RequestMethod.GET)
 public ModelAndView read(HttpSession session){
   ModelAndView mav = new ModelAndView();
   
     int adminno = (int)session.getAttribute("adminno");  // session에 저장된 adminno를 꺼냄
     
     AdminVO adminVO = this.adminProc.read(adminno);  // adminno를 통해 adminVO에 저장된 내용들을 출력하기 위해 관리자 정보를 읽음
     mav.addObject("adminVO", adminVO);                      // adminVO에 대한 내용을 출력
     mav.setViewName("/admin/read");                           // /admin/read.jsp
      
   return mav;
 }
 
 /**
  * 관리자 정보 수정 처리
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/update.do", method=RequestMethod.POST)
 public ModelAndView update(HttpSession session, AdminVO adminVO){
   
   int adminno = (int)session.getAttribute("adminno");  // session에 저장된 adminno를 꺼냄
   adminVO.setAdminno(adminno);                             // DB에 adminno를 adminVO.setAdminino(adminno)를 통해 저장
   
   ModelAndView mav = new ModelAndView();
   
   int cnt= adminProc.update(adminVO);                     // 관리자 업데이트 처리
   
   if (cnt == 1) {          // cnt가 1일때 업데이트 처리 성공
     mav.addObject("code", "update_success");   // 업데이트 성공 update_success에 대한 내용을 출력
     mav.addObject("adminname", adminVO.getAdminname());  
     mav.addObject("adminid", adminVO.getAdminid());            // 관리자님(admin1) 관리자 정보를 변경했습니다.
   } else {                   // 업데이트 처리 실패
     mav.addObject("code", "update_fail");        // 업데이트 실패 update_fail에 대한 내용을 출력
   }

   mav.addObject("cnt", cnt);
   mav.addObject("url", "/admin/msg");        // /admin/msg -> /admin/msg.jsp
   
   mav.setViewName("redirect:/admin/msg.do");   // 새로 고침 방지
   
   return mav;
 }
 
 /**
  * 관리자 삭제
  * http://localhost:9093/admin/delete.do?adminno=1
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/delete.do", method=RequestMethod.GET)
 public ModelAndView delete(int adminno){
   ModelAndView mav = new ModelAndView();
   
   AdminVO adminVO = this.adminProc.read(adminno);         // 삭제할 레코드를 출력하기위해 관리자 정보를 읽음.
   mav.addObject("adminVO", adminVO);
   mav.setViewName("/admin/delete");                                // /admin/delete.jsp
   
   return mav;
 }

 /**
  * 관리자 삭제 처리
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/delete.do", method=RequestMethod.POST)
 public ModelAndView delete_proc(int adminno){
   ModelAndView mav = new ModelAndView();
   
   AdminVO adminVO = this.adminProc.read(adminno);            // 삭제된 정보를 출력하기위해 읽음.
   
   int cnt= adminProc.delete(adminno);                                   // 관리자 삭제 처리

   if (cnt == 1) {    // cnt가 1일 경우 관리자 삭제 성공
     mav.addObject("code", "delete_success");                           // 관리자 삭제 성공 delete_success에 대한 내용을 출력
     mav.addObject("adminname", adminVO.getAdminname());  // 관리자님(admin1) 관리자 정보 삭제에 성공했습니다.
     mav.addObject("adminid", adminVO.getAdminid());
   } else {
     mav.addObject("code", "delete_fail");                                // 관리자 삭제 실패 delete_fail에 대한 내용을 출력
   }

   mav.addObject("cnt", cnt);
   mav.addObject("url", "/admin/msg");                                  // /admin/msg -> /admin/msg.jsp
   
   mav.setViewName("redirect:/admin/msg.do");
   
   return mav;
 }
 
 /**
  * 관리자 패스워드를 변경합니다.
  * http://localhost:9093/admin/passwd_update.do
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.GET)
 public ModelAndView passwd_update(){
   
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/admin/passwd_update"); // /admin/passwd_update.jsp
   
   return mav;
 }
 
 /**
  * 관리자 패스워드 변경 처리
  * @param adminno 회원 번호
  * @param current_adminpasswd 현재 패스워드
  * @param new_adminpasswd 새로운 패스워드
  * @return
  */
 @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.POST)
 public ModelAndView passwd_update(HttpSession session, String current_adminpasswd, String new_adminpasswd){
   int adminno = (int)session.getAttribute("adminno");
       
   ModelAndView mav = new ModelAndView();
   
   AdminVO adminVO = this.adminProc.read(adminno);        // 패스워드를 변경하려는 관리자 정보를 읽음
   mav.addObject("adminname", adminVO.getAdminname());  
   mav.addObject("adminid", adminVO.getAdminid());          // 관리자님(admin1) 관리자 패스워드를 변경했습니다.
   
   // 현재 패스워드 검사용 데이터
   HashMap<Object, Object> map = new HashMap<Object, Object>();
   map.put("adminno", adminno);
   map.put("adminpasswd", current_adminpasswd);
   
   int cnt = adminProc.passwd_check(map); // 현재 패스워드 검사
   int update_cnt = 0; // 변경된 패스워드 수
   
   if (cnt == 1) { // cnt가 1일경우 현재 패스워드가 일치
     map.put("adminpasswd", new_adminpasswd);       // 새로운 패스워드를 저장
     update_cnt = adminProc.passwd_update(map);     // 패스워드 변경 처리
     
     if (update_cnt == 1) {   // update_cnt가 1일 경우 패스워드 변경처리 성공
       mav.addObject("code", "passwd_update_success");    // 관리자 패스워드 변경 성공 passwd_update_success에 대한 내용을 출력
     } else {
       cnt = 0;     // 관리자 패스워드는 일치했으나 변경하지는 못함.
       mav.addObject("code", "passwd_update_fail");         // 관리자 패스워드 변경 실패 passwd_update_fail에 대한 내용을 출력
     }
     mav.addObject("update_cnt", update_cnt);                // 변경된 패스워드의 갯수    
   } else {
     mav.addObject("code", "passwd_fail");                     // 패스워드가 일치하지 않는 경우
   }

   mav.addObject("cnt", cnt);
   mav.addObject("url", "/admin/msg");                        // /admin/msg -> /admin/msg.jsp
   
   mav.setViewName("redirect:/admin/msg.do");
   
   return mav;
 }
 
  /**
   * 관리자 로그인 폼 + 로그인 성공후 자동으로 주소 이동
   * http://localhost:9093/admin/login.do 
   * @param return_url 로그인 성공후 자동으로 이동할 주소
   * @return
   */
 
  @RequestMapping(value = "/admin/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request,
                                                @RequestParam(value="return_url", defaultValue="") String return_url ) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_admin_id = "";                   // adminid 저장
    String ck_admin_id_save = "";           // adminid 저장 여부를 체크
    String ck_admin_passwd = "";           // adminpasswd 저장
    String ck_admin_passwd_save = "";   // adminpasswd 저장 여부를 체크
  
    if (cookies != null) {      // 쿠키가 존재한다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i];  // 쿠키 객체 추출
      
        if (cookie.getName().equals("ck_admin_id")){
          ck_admin_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_admin_id_save")){
          ck_admin_id_save = cookie.getValue();         // Y, N
        }else if (cookie.getName().equals("ck_admin_passwd")){
          ck_admin_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_admin_passwd_save")){
          ck_admin_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
    mav.addObject("ck_admin_id", ck_admin_id);
    mav.addObject("ck_admin_id_save", ck_admin_id_save);
  
    mav.addObject("ck_admin_passwd", ck_admin_passwd);
    mav.addObject("ck_admin_passwd_save", ck_admin_passwd_save);
    
    mav.addObject("return_url", return_url);        // 로그인 성공후 자동으로 이동할 주소
  
    mav.setViewName("/admin/login_form_ck");  // /admin/login_form_ck.jsp
    return mav;
  }
   
  /**
  * Cookie 기반 로그인 처리
  * @param request Cookie를 읽기위해 필요
  * @param response Cookie를 쓰기위해 필요
  * @param session 관리자 로그인 정보를 메모리에 기록
  * @param adminid  관리자 아이디
  * @param adminpasswd 관리자 패스워드
  * @param adminid_save 관리자 아이디 Cookie에 저장 여부
  * @param adminpasswd_save 관리자 패스워드 Cookie에 저장 여부
  * @return
  */
  @RequestMapping(value = "/admin/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            String adminid,
                            String adminpasswd,
                            @RequestParam(value="adminid_save", defaultValue="") String adminid_save,
                            @RequestParam(value="adminpasswd_save", defaultValue="") String adminpasswd_save,
                            @RequestParam(value="return_url", defaultValue="") String return_url) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("adminid", adminid);
    map.put("adminpasswd", adminpasswd);
   
    int cnt = adminProc.login(map);     // 관리자 로그인 처리
    
    if (cnt == 1) {     // cnt가 1일 경우 관리자 로그인 성공
      
      AdminVO adminVO = adminProc.readById(adminid);                      // 관리자 아이디로 관리자 정보를 읽음
      session.setAttribute("adminno", adminVO.getAdminno());               // 서버의 메모리에 adminno기록
      session.setAttribute("adminid", adminid);                                      // 서버의 메모리에 adminid기록
      session.setAttribute("adminpasswd", adminVO.getAdminpasswd()); // 서버의 메모리에 adminpasswd기록
      session.setAttribute("adminname", adminVO.getAdminname());     // 서버의 메모리에 adminname기록
      session.setAttribute("admingrade", adminVO.getAdmingrade());    // 서버의 메모리에 admingrade기록
   
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (adminid_save.equals("Y")) { // adminid를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", adminid);
        ck_admin_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_admin_id); // adminid 저장
      } else { // N, adminid를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", "");
        ck_admin_id.setPath("/");
        ck_admin_id.setMaxAge(0);
        response.addCookie(ck_admin_id); // adminid 저장
      }
      
      // adminid를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_admin_id_save = new Cookie("ck_admin_id_save", adminid_save);
      ck_admin_id_save.setPath("/");
      ck_admin_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (adminpasswd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", adminpasswd);
        ck_admin_passwd.setPath("/");
        ck_admin_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_admin_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", "");
        ck_admin_passwd.setPath("/");
        ck_admin_passwd.setMaxAge(0);
        response.addCookie(ck_admin_passwd);
      }
      // adminpasswd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_admin_passwd_save = new Cookie("ck_admin_passwd_save", adminpasswd_save);
      ck_admin_passwd_save.setPath("/");
      ck_admin_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_passwd_save);
      // -------------------------------------------------------------------
   
      System.out.println("-> return_url: " + return_url);
      
      if (return_url.length() > 0) {                      // ★ 로그인 성공후 자동으로 이동할 주소
        mav.setViewName("redirect:" + return_url);  
      } else {
        mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동
      }
    } else {
      mav.addObject("url", "/admin/login_fail_msg");  // /WEB-INF/views/admin/login_fail_msg.jsp
      mav.setViewName("redirect:/admin/msg.do"); 
    }
    return mav;
  }
    
  /**
   * 로그아웃 처리
   * http://localhost:9093/admin/login.do 
   * @param session
   * @return
   */
  @RequestMapping(value="/admin/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    
    ModelAndView mav = new ModelAndView();
    
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * Cookie + Ajax 기반 로그인 처리
   * http://localhost:9093/admin/login_ajax.do 
   * @param request Cookie를 읽기위해 필요
   * @param response Cookie를 쓰기위해 필요
   * @param session 관리자 로그인 정보를 메모리에 기록
   * @param adminid  관리자 아이디
   * @param adminpasswd 관리자 패스워드
   * @param adminid_save 관리자 아이디 Cookie에 저장 여부
   * @param adminpasswd_save 관리자 패스워드 Cookie에 저장 여부
   * @return
   */
  @RequestMapping(value = "/admin/login_ajax.do", 
                             method = RequestMethod.POST)
  @ResponseBody
  public String login_cookie_proc_ajax (
                             HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             String adminid, String adminpasswd,
                             @RequestParam(value="adminid_save", defaultValue="") String adminid_save,
                             @RequestParam(value="adminpasswd_save", defaultValue="") String adminpasswd_save) {

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("adminid", adminid);
    map.put("adminpasswd", adminpasswd);
    
    int cnt = adminProc.login(map);   // 관리자 로그인 처리
    
    if (cnt == 1) { // 관리자 로그인 성공
      AdminVO adminVO = adminProc.readById(adminid);
      session.setAttribute("adminno", adminVO.getAdminno());           // 서버의 메모리에 adminno기록
      session.setAttribute("adminid", adminid);                                  // 서버의 메모리에 adminid기록
      session.setAttribute("adminname", adminVO.getAdminname());  // 서버의 메모리에 adminname기록
      session.setAttribute("admingrade", adminVO.getAdmingrade());  // 서버의 메모리에 admingrade기록
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      
      if (adminid_save.equals("Y")) { // adminid를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", adminid);
        ck_admin_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_admin_id); // adminid 저장
      } else { // N, adminid를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_admin_id = new Cookie("ck_admin_id", "");
        ck_admin_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_id.setMaxAge(0);
        response.addCookie(ck_admin_id); // adminid 저장
      }
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_admin_id_save = new Cookie("ck_admin_id_save", adminid_save);
      ck_admin_id_save.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
      ck_admin_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (adminpasswd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", adminpasswd);
        ck_admin_passwd.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_admin_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_admin_passwd = new Cookie("ck_admin_passwd", "");
        ck_admin_passwd.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_admin_passwd.setMaxAge(0);
        response.addCookie(ck_admin_passwd);
      }
      // adminpasswd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_admin_passwd_save = new Cookie("ck_admin_passwd_save", adminpasswd_save);
      ck_admin_passwd_save.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
      ck_admin_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_admin_passwd_save);
      // -------------------------------------------------------------------
    }
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); 
  }
  
  /**
   * Ajax 기반 회원 조회
   * http://localhost:9093/admin/read_ajax.do
   * {
   * "adminage":"25",
   * "adminname":"관리자1",
   * "admintel":"010-1234-5678",
   * "adminreceiver":"abcd@naver.com",
   * "zipcode":"12345",
   * "address1":"서울시 종로구"
   * }
   * @param adminno
   * @return
   */
  @RequestMapping(value="/admin/read_ajax.do", method=RequestMethod.GET)
  @ResponseBody
  public String read_ajax(HttpSession session){
    int adminno = (int)session.getAttribute("adminno");
    
    AdminVO adminVO = this.adminProc.read(adminno);
    
    JSONObject json = new JSONObject();
    json.put("adminage", adminVO.getAdminage());
    json.put("adminname", adminVO.getAdminname());
    json.put("admintel", adminVO.getAdmintel());
    json.put("adminreceiver", adminVO.getAdminreceiver());
    json.put("zipcode", adminVO.getZipcode());
    json.put("address1", adminVO.getAddress1());

    return json.toString();
  }
  
  /**
   * 관리자 아이디 찾기 폼
   * http://localhost:9093/admin/id_search.do
   * @param admintel
   * @param adminreceiver
   * @return
   */
  @RequestMapping(value="/admin/id_search.do", method=RequestMethod.GET)
  public ModelAndView id_search(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/admin/id_search"); // /admin/id_search.jsp
    
    return mav;
  }
  
  /**
   * 아이디 찾기로 찾은 아이디 조회
   * http://localhost:9093/admin/id_search_result.do
   * @param admintel
   * @param adminreceiver
   * @return
   */
  @RequestMapping(value="/admin/id_search_result.do", method=RequestMethod.GET)
  public ModelAndView id_search_result(HttpSession session, String admintel, String adminreceiver){
    ModelAndView mav = new ModelAndView();
    
    admintel = (String)session.getAttribute("admintel");                   // 관리자가 입력한 전화번호를 session에서 꺼냄
    adminreceiver = (String)session.getAttribute("adminreceiver");    // 관리자가 입력한 이메일을 session에서 꺼냄
    
    if (adminreceiver == null) {  // 입력받은 관리자 이메일이 없을 경우
    System.out.println("입력한 전화번호 : " + admintel);    
    
    AdminVO adminVO = this.adminProc.readBytel(admintel); // adminid를 찾으려는 관리자의 전화번호를 읽음
    mav.addObject("adminid", adminVO.getAdminid());          
    mav.addObject("adminname", adminVO.getAdminname());   
    }else {
      System.out.println("입력한 이메일 : " + adminreceiver);
      
      AdminVO adminVO = this.adminProc.readByReceiver(adminreceiver); // id를 찾으려는 관리자의 이메일을 읽음
      mav.addObject("adminid", adminVO.getAdminid());          
      mav.addObject("adminname", adminVO.getAdminname());   
    }
    
    mav.setViewName("/admin/id_search_result"); // /admin/id_search.jsp
    
    return mav;
  }
  
  /**
   * 관리자 비밀번호 찾기 폼
   * http://localhost:9093/admin/passwd_search.do
   * @param adminid
   * @param admintel
   * @param adminreceiver
   * @return
   */
  @RequestMapping(value="/admin/passwd_search.do", method=RequestMethod.GET)
  public ModelAndView passwd_search(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/admin/passwd_search"); // /admin/passwd_search.jsp
    
    return mav;
  }
  
  /**
   * 관리자 비밀번호 찾기로 새로운 패스워드로 변경하는 폼.
   * http://localhost:9093/admin/passwd_search_result.do
   * @param adminid
   * @param admintel
   * @param adminreceiver
   * @return
   */
  @RequestMapping(value="/admin/passwd_search_result.do", method=RequestMethod.GET)
  public ModelAndView passwd_search_result(){
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/admin/passwd_search_result"); //  /admin/passwd_search_result.jsp
    
    return mav;
  }
  
  /**
   * 관리자 패스워드 변경 처리
   * @param new_adminpasswd 새로운 패스워드
   * @param adminid 관리자 아이디
   * @param admintel 관리자 전화번호
   * @param adminreceiver 관리자 이메일   
   * @return
   */
  @RequestMapping(value="/admin/passwd_search_result.do", method=RequestMethod.POST)
  public ModelAndView passwd_search_result(HttpSession session, String new_adminpasswd,
                                                        String adminid, String admintel, String adminreceiver ){
  
    ModelAndView mav = new ModelAndView();
    
    adminid = (String)session.getAttribute("adminid");                      // 관리자가 입력한 관리자 아이디를 session에서 꺼냄
    admintel = (String)session.getAttribute("admintel");                    // 관리자가 입력한 관리자 전화번호를 session에서 꺼냄
    adminreceiver = (String)session.getAttribute("adminreceiver");    // 관리자가 입력한 관리자 이메일을 session에서 꺼냄
   
    if (adminreceiver == null) {    // 입력받은 관리자 이메일이 없을 경우
      
    AdminVO adminVO = this.adminProc.readByIdTel(adminid, admintel); 
    // 관리자 패스워드를 변경하려는 관리자 정보를 adminid와 admintel을 통해 읽음
  
    int adminno = adminVO.getAdminno();
    
    System.out.println("입력한 아이디 : " + adminid);
    System.out.println("입력한 전화번호 : " + admintel);
    System.out.println("입력한 회원번호 : " + adminno);
    
    mav.addObject("adminname", adminVO.getAdminname());  
    mav.addObject("adminid", adminVO.getAdminid());
    
    // 현재 패스워드 검사용 데이터
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("adminid", adminid);
    map.put("admintel", admintel);
    map.put("adminno", adminno);
   
    
    int cnt = adminProc.IdTel_check(map);         // 현재 아이디와 전화번호 검사
    int update_cnt = 0;                                     // 변경된 패스워드 수
    
    System.out.println(cnt);
    
    if (cnt == 1) { //  cnt가 1일때 현재 아이디와 전화번호가 일치하는 경우
      map.put("adminpasswd", new_adminpasswd);      // 새로운 패스워드를 저장
      update_cnt = adminProc.passwd_update(map);    // 패스워드 변경 처리
      
      System.out.println(new_adminpasswd);
      
      if (update_cnt == 1) {
        mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
      } else {
        cnt = 0;                                                              // 패스워드는 일치했으나 변경하지는 못함.
        mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
      }
      mav.addObject("update_cnt", update_cnt);              // 변경된 패스워드의 갯수    
    } else {
      mav.addObject("code", "passwd_fail");                   // 패스워드가 일치하지 않는 경우
    }
    mav.addObject("cnt", cnt);  // 패스워드 일치 여부
    
    }else {  // 입력받은 관리자 이메일이 있을 경우
      AdminVO adminVO = this.adminProc.readByIdReceiver(adminid, adminreceiver); 
      // 관리자 패스워드를 변경하려는 관리자 정보를 adminid와 adminreceiver을 통해 읽음
      
      int adminno = adminVO.getAdminno();
      
      System.out.println("입력한 아이디 : " + adminid);
      System.out.println("입력한 이메일 : " + adminreceiver);
      System.out.println("입력한 회원번호 : " + adminno);
      
      mav.addObject("adminname", adminVO.getAdminname());  
      mav.addObject("adminid", adminVO.getAdminid());
      
      // 현재 패스워드 검사용 데이터
      HashMap<Object, Object> map = new HashMap<Object, Object>();
      map.put("adminid", adminid);
      map.put("adminreceiver", adminreceiver);
      map.put("adminno", adminno);
     
      int cnt = adminProc.IdReceiver_check(map);  // 현재 아이디와 이메일 검사
      int update_cnt = 0;                                      // 변경된 패스워드 수
      
      System.out.println(cnt);
      
      if (cnt == 1) {  // cnt가 1일때 현재 아이디와 이메일이 일치하는 경우
        map.put("adminpasswd", new_adminpasswd); // 새로운 패스워드를 저장
        update_cnt = adminProc.passwd_update(map); // 패스워드 변경 처리
        
        System.out.println(new_adminpasswd);
        
        if (update_cnt == 1) {
          mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
        } else {
          cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
          mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
        }
        
        mav.addObject("update_cnt", update_cnt);               // 변경된 패스워드의 갯수    
      } else {
        mav.addObject("code", "passwd_fail");                     // 패스워드가 일치하지 않는 경우
      }
      mav.addObject("cnt", cnt);                                        // 패스워드 일치 여부
    }
    
    mav.addObject("url", "/admin/msg");                           // /admin/msg -> /admin/msg.jsp
    mav.setViewName("redirect:/admin/msg.do");
    
    return mav;
  }
  
  /**
   * 관리자 탈퇴 폼
   * http://localhost:9093/admin/unregister.do
   * @param adminno
   * @return
   */
  @RequestMapping(value="/admin/unregister.do", method=RequestMethod.GET)
  public ModelAndView unregister(HttpSession session){
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {  // 현재 관리자일 경우
      int adminno = (int)session.getAttribute("adminno");
      
      AdminVO adminVO = this.adminProc.read(adminno);   // 관리자 탈퇴를 위해 관리자 정보를 읽음
      mav.addObject("adminVO", adminVO);
      mav.setViewName("/admin/read"); // /admin/read.jsp
      
    } else {  // 관리자가 아닐경우
      mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp
    }
    mav.setViewName("/admin/unregister"); // admin/unregister.jsp
    
    return mav;
  }
  
  /**
   * 관리자 탈퇴를 위해 등록된 패스워드가 일치하는지 재확인 폼
   * http://localhost:9093/admin/passwd_true.do
   * @param adminno
   * @return
   */
  @RequestMapping(value="/admin/passwd_true.do", method=RequestMethod.GET)
  public ModelAndView passwd_true(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/admin/passwd_true"); // /admin/passwd_true.jsp
    
    return mav;
  }
  
  /**
   * 관리자 탈퇴를 위해 등록된 패스워드가 일치하는지 재확인 처리
   * @param adminno 회원 번호
   * @param adminpasswd 현재 패스워드
   * @param 
   * @return
   */
  @RequestMapping(value="/admin/passwd_true.do", method=RequestMethod.POST)
  public ModelAndView passwd_true(HttpSession session, String adminpasswd, int adminno){
        
    ModelAndView mav = new ModelAndView();

    
    // 현재 패스워드 검사용 데이터
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("adminno", adminno);
    map.put("adminpasswd", adminpasswd);
    
    int cnt = adminProc.passwd_check(map);      // 현재 패스워드 검사
    
    if (cnt == 1) {   // 현재 패스워드가 일치하는 경우
      map.put("adminpasswd", adminpasswd);    // 현재 패스워드를 저장
      
    } else {
      mav.addObject("code", "passwd_fail");       // 패스워드가 일치하지 않는 경우
    }

    mav.addObject("cnt", cnt);                          // 패스워드 일치 여부
    mav.addObject("url", "/admin/msg");          // /admin/msg -> /admin/msg.jsp
    
    mav.setViewName("redirect:/admin/msg.do");
    
    return mav;
  }
  
  /**
   * 관리자 탈퇴를 할것인지 재확인 폼
   * http://localhost:9093/admin/unregister_proc.do
   * @param adminno
   * @return
   */
  @RequestMapping(value="/admin/unregister_proc.do", method=RequestMethod.GET)
  public ModelAndView unregister_proc(int adminno){
    ModelAndView mav = new ModelAndView();
    
    AdminVO adminVO = this.adminProc.read(adminno);     // 관리자 탈퇴 재확인을 하기 위해 관리자 정보를 읽음
    mav.addObject("adminVO", adminVO);
    mav.setViewName("/admin/read");                               // /admin/read.jsp
    
    mav.setViewName("/admin/unregister_proc");               // /admin/unregister_proc.jsp
    
    return mav;
  }
  
  /**
   * 관리자 탈퇴를 할 것인지 재확인 처리
   * @param adminVO
   * @return
   */
  @RequestMapping(value="/admin/unregister_proc.do", method=RequestMethod.POST)
  public ModelAndView unregister_proc(HttpSession session, AdminVO adminVO){
    
    ModelAndView mav = new ModelAndView();
    
    int cnt= adminProc.unregister(adminVO);                             // 관리자 탈퇴 관리자 등급변경
    
    if (cnt == 1) {                                                                     // cnt가 1일경우 관리자 탈퇴 (관리자 등급 변경)
      mav.addObject("code", "unregister_success");                     // 관리자 탈퇴 성공
      mav.addObject("adminname", adminVO.getAdminname());  
      mav.addObject("adminid", adminVO.getAdminid());             // 관리자 탈퇴 하였습니다.
      session.invalidate();                                                           // 모든 session 변수 삭제
    }
    
    mav.addObject("cnt", cnt);
    mav.addObject("url", "/admin/msg");                                   // /admin/msg -> /admin/msg.jsp
    
    mav.setViewName("redirect:/admin/msg.do");                      // 새로 고침 방지
    
    return mav;
  }
  
  
  /**
   * 관리자 등급 변경
   * http://localhost:9093/admin/grade_update.do
   * @param adminno
   * @param admingrade
   * @return
   */
  @RequestMapping(value="/admin/grade_update.do", method=RequestMethod.GET)
  public ModelAndView grade_update(int adminno, int admingrade){
    ModelAndView mav = new ModelAndView();
    
    AdminVO adminVO = this.adminProc.read(adminno);
    mav.addObject("adminVO", adminVO);
    
    System.out.println(admingrade);
    
    String admingrade_msg = "";
    
    if(0 < admingrade && admingrade <= 10) {            // 관리자 등급이 0 보다 크고 10보다 작거나 같을때 메인 관리자
      admingrade_msg = "메인 관리자";
    }else if (10 < admingrade && admingrade <= 20) {  // 관리자 등급이 10 보다 크고 20보다 작거나 같을때 관리자
      admingrade_msg = "관리자";
    }else if (20 < admingrade && admingrade <= 30) {  // 관리자 등급이 20 보다 크고 30보다 작거나 같을때 정지 관리자
      admingrade_msg = "정지 관리자";
    }else if (30 < admingrade && admingrade <= 40) {  // 관리자 등급이 30 보다 크고 40보다 작거나 같을때 탈퇴 관리자
      admingrade_msg = "탈퇴 관리자";
    }else {                                                                   // 0 < admingrade <= 40이 아닐때 X
      admingrade_msg = "X";
    }
    
    mav.addObject("admingrade", admingrade);
    mav.addObject("admingrade_msg", admingrade_msg);
    
    mav.setViewName("/admin/grade_update"); // /admin/grade_update.jsp
    
    return mav;
  }
  
  
  /**
   * 관리자 등급 변경 처리
   * @param adminVO
   * @param admingrade
   * @return
   */
  @RequestMapping(value="/admin/grade_update.do", method=RequestMethod.POST)
  public ModelAndView grade_update(HttpSession session, AdminVO adminVO, int admingrade){
    
    ModelAndView mav = new ModelAndView();
    
    int cnt= adminProc.unregister(adminVO);        // 관리자 등급변경
    
    if (cnt == 1) { // cnt가 1일 경우 관리자 등급 변경 성공
      mav.addObject("code", "grade_update_success");
    }
    mav.addObject("cnt", cnt);
    mav.addObject("url", "/admin/msg");  // /admin/msg -> /admin/msg.jsp
    
    mav.setViewName("redirect:/admin/msg.do"); // 새로 고침 방지
    
    return mav;
  }

  /**
   * 관리자 회원가입을 한 회원을 메인 관리자가 O 표시로 받아주기 위한 처리 폼
   * http://localhost:9093/admin/update_permission.do
   * @param adminno
   * @return
   */
  @RequestMapping(value="/admin/update_permission.do", method = RequestMethod.GET)
  public ModelAndView update_permission(int adminno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.adminProc.update_permission(adminno);
    
    if (cnt == 1) { // 가입허가 성공
      mav.addObject("code", "grade_update_success");
    }
    
    mav.addObject("cnt", cnt); 
    mav.addObject("url", "/admin/msg");  // /admin/msg -> /admin/msg.jsp
    
    mav.setViewName("redirect:/admin/msg.do"); // 새로 고침 방지
    
    return mav;
  }
  
  /**
   * 관리자 회원가입을 한 회원 목록
   * http://localhost:9093/admin/permission_list.do
   * @param session
   * @return
   */
   @RequestMapping(value="/admin/permission_list.do", method=RequestMethod.GET)
   public ModelAndView permission_list(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     if (this.adminProc.isAdmin_grade(session)) { // 관리자 등급 확인
       ArrayList<AdminVO> permission_list = adminProc.permission_list();
       
       mav.addObject("permission_list", permission_list);

       mav.setViewName("/admin/permission_list"); // /webapp/WEB-INF/views/admin/permission_list.jsp
       
     } else {
       mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
     }
     return mav;
   }  
}


