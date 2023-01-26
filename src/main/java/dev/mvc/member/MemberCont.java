package dev.mvc.member;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dev.mvc.admin.AdminProcInter;
import dev.mvc.cate.CateVO;
 
@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;
  
  public MemberCont(){
    System.out.println("-> MemberCont created.");
  }
  
  // http://localhost:9093/member/checkID.do?memberid=user1
  /**
  * ID 중복 체크, JSON 출력
  * @param adminid
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/member/checkID.do", method=RequestMethod.GET ,
                           produces = "text/plain;charset=UTF-8" )
  public String checkID(String memberid) {
    int cnt = this.memberProc.checkID(memberid); // 저장된 회원 아이디를 중복 처리 하지 않기 위해 읽음.
   
    JSONObject json = new JSONObject();     // json으로 변환
    json.put("cnt", cnt);                               // put을 통해 json에 cnt 저장
   
    return json.toString(); 
  }

  /**
  * 회원가입 폼 (등록)
  * http://localhost:9093/member/create.do
  * @return
  */
  @RequestMapping(value="/member/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/create"); // /WEB-INF/views/member/create.jsp
   
    return mav;
  }

   /**
   * 회원 회원가입 폼 (등록 처리)
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO){
    ModelAndView mav = new ModelAndView();
    
    memberVO.setGrade(11); // 회원가입시 회원 등급 11 등록
    
    int cnt= memberProc.create(memberVO); // 회원가입 처리
    
    if (cnt == 1) {   // cnt가 1일경우 회원가입 성공
      mav.addObject("code", "create_success");   // 회원가입 성공 create_success에 대한 내용을 출력
      mav.addObject("membername", memberVO.getMembername());  
      mav.addObject("memberid", memberVO.getMemberid());            // 회원1님(user1) 회원 가입을 축하합니다.
    } else {    // 회원가입 실패
      mav.addObject("code", "create_fail");         // 회원가입 실패 create_fail에 대한 내용을 출력
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)   // cnt 정보 저장
    
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");      // 새로 고침 방지
    
    return mav;
  }
  
  /**
   * 새로고침 방지, EL에서 param으로 접근
  * @param url
   * @return
   */
  @RequestMapping(value="/member/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url);
    
    return mav; // forward
  }


  /**
  * 회원 등급을 확인후 회원 목록 출력
  * http://localhost:9093/member/list.do
  * @param session
  * @return
  */
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {    // 회원 등급 처리를 위해 읽음  // 관리자 등급이 1~ 20일 경우 목록 출력
      ArrayList<MemberVO> list = memberProc.list();
      
      mav.addObject("list", list);

      mav.setViewName("/member/list"); // /webapp/WEB-INF/views/member/list.jsp
      
    } else {
      mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
    }
    return mav;
  }  
  
  /**
   * session 객체를 이용한 회원 조회
   * http://localhost:9093/member/read.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session){
    ModelAndView mav = new ModelAndView();
      
      int memberno = (int)session.getAttribute("memberno");   // session에 저장된 memberno를 꺼냄
    
      MemberVO memberVO = this.memberProc.read(memberno); // memberno를 통해 memberVO에 저장된 내용들을 출력하기 위해 회원 정보를 읽음
      mav.addObject("memberVO", memberVO);                           // memberVO에 대한 내용을 출력
      mav.setViewName("/member/read");                                   // /member/read.jsp
       
    return mav; 
  }
  
  /**
   * session 객체를 이용한 마이페이지 회원 조회
   * http://localhost:9093/member/mypage.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/mypage.do", method=RequestMethod.GET)
  public ModelAndView mypage(HttpSession session){
    ModelAndView mav = new ModelAndView();
    
      int memberno = (int)session.getAttribute("memberno"); // session에 저장된 memberno를 꺼냄
      
      MemberVO memberVO = this.memberProc.read(memberno); // memberno를 통해 memberVO에 저장된 내용들을 출력하기 위해 회원 정보를 읽음
      mav.addObject("memberVO", memberVO);                           // memberVO에 대한 내용을 출력
      mav.setViewName("/member/mypage");                              // /member/read.jsp
       
    return mav;
  }

  /**
   * 회원 정보 수정 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update( HttpSession session, MemberVO memberVO){
   
    int memberno = (int)session.getAttribute("memberno"); // session에 저장된 memberno를 꺼냄
    memberVO.setMemberno(memberno);                          // DB에 adminno를 adminVO.setAdminino(adminno)를 통해 저장
   
    ModelAndView mav = new ModelAndView();  
    
    int cnt= memberProc.update(memberVO);                     // 회원 업데이트 처리
    
    if (cnt == 1) {   // cnt가 1일때 업데이트 처리 성공
      mav.addObject("code", "update_success");                              // 업데이트 성공 update_success에 대한 내용을 출력
      mav.addObject("membername", memberVO.getMembername()); 
      mav.addObject("memberid", memberVO.getMemberid());        // 회원1님(user1) 회원 정보를 변경했습니다.
    } else {             // 업데이트 처리 실패
      mav.addObject("code", "update_fail");                                   // 업데이트 실패 update_fail에 대한 내용을 출력
    }

    mav.addObject("cnt", cnt);
    mav.addObject("url", "/member/msg");              // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do"); // 새로 고침 방지
    
    return mav;
  }
  
  /**
   * 회원 삭제
   * http://localhost:9091/member/delete.do?memberno=1
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno); // 삭제할 레코드를 출력하기위해 회원 정보를 읽음.
    mav.addObject("memberVO", memberVO);
    mav.setViewName("/member/delete");                                // /member/delete.jsp
    
    return mav;
  }
 
  /**
   * 회원 삭제 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);         // 삭제된 정보를 출력하기위해 읽음.
    
    int cnt= memberProc.delete(memberno);                                     // 회원 삭제 처리

    if (cnt == 1) {         // cnt가 1일 경우 회원 삭제 성공
      mav.addObject("code", "delete_success");                                  // 회원 삭제 성공 delete_success에 대한 내용을 출력
      mav.addObject("membername", memberVO.getMembername()); 
      mav.addObject("memberid", memberVO.getMemberid());           // 회원1님(user1) 회원 정보 삭제에 성공했습니다.
    } else {
      mav.addObject("code", "delete_fail");                                       // 회원 삭제 실패 delete_fail에 대한 내용을 출력
    }

    mav.addObject("cnt", cnt);
    mav.addObject("url", "/member/msg");                                      // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 패스워드를 변경합니다.
   * http://localhost:9093/member/passwd_update.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(){
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/passwd_update"); // /member/passwd_update.jsp
    
    return mav;
  }
  
  /**
   * 회원 패스워드 변경 처리
   * @param memberno 회원 번호
   * @param current_memberpasswd 현재 패스워드
   * @param new_memberpasswd 새로운 패스워드
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(HttpSession session, String current_memberpasswd, String new_memberpasswd){
    int memberno = (int)session.getAttribute("memberno");
        
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);       // 패스워드를 변경하려는 회원 정보를 읽음
    mav.addObject("membername", memberVO.getMembername());  
    mav.addObject("memberid", memberVO.getMemberid());            // 회원1님(user1) 회원 패스워드를 변경했습니다.
    
    // 현재 패스워드 검사용 데이터
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("memberno", memberno);
    map.put("memberpasswd", current_memberpasswd);
    
    int cnt = memberProc.passwd_check(map); // 현재 패스워드 검사
    int update_cnt = 0; // 변경된 패스워드 수
    
    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
      map.put("memberpasswd", new_memberpasswd); // 새로운 패스워드를 저장
      update_cnt = memberProc.passwd_update(map); // 패스워드 변경 처리
      
      if (update_cnt == 1) {    // update_cnt가 1일 경우 패스워드 변경처리 성공
        mav.addObject("code", "passwd_update_success");    // 회원 패스워드 변경 성공 passwd_update_success에 대한 내용을 출력
      } else {
        cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
        mav.addObject("code", "passwd_update_fail");         // 회원 패스워드 변경 실패 passwd_update_fail에 대한 내용을 출력
      }
      mav.addObject("update_cnt", update_cnt);                // 변경된 패스워드의 갯수    
    } else {
      mav.addObject("code", "passwd_fail");                     // 패스워드가 일치하지 않는 경우
    }

    mav.addObject("cnt", cnt);
    mav.addObject("url", "/member/msg");                     // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 로그인 폼 + 로그인 성공후 자동으로 주소 이동
   * http://localhost:9093/member/login.do 
   * @param return_url 로그인 성공후 자동으로 이동할 주소
   * @return
   */
  @RequestMapping(value = "/member/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request,
                                                  @RequestParam(value="return_url", defaultValue="") String return_url ) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_id = "";                      // memberid 저장
    String ck_id_save = "";              // memberid 저장 여부를 체크
    String ck_passwd = "";              // memberpasswd 저장
    String ck_passwd_save = "";      // memberpasswd 저장 여부를 체크
  
    if (cookies != null) {          // 쿠키가 존재한다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i];      // 쿠키 객체 추출
      
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();         // Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
    mav.addObject("ck_id", ck_id);
    //    <input type='checkbox' name='id_save' value='Y' 
    //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
    mav.addObject("ck_id_save", ck_id_save);
  
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
    
    mav.addObject("return_url", return_url);          // 로그인 성공후 자동으로 이동할 주소
  
    mav.setViewName("/member/login_form_ck"); // /member/login_form_ck.jsp
    return mav;
  }

  /**
  * Cookie 기반 로그인 처리
  * @param request Cookie를 읽기위해 필요
  * @param response Cookie를 쓰기위해 필요
  * @param session 회원 로그인 정보를 메모리에 기록
  * @param memberid  회원 아이디
  * @param memberpasswd 회원 패스워드
  * @param id_save 회원 아이디 Cookie에 저장 여부
  * @param passwd_save 회원 패스워드 Cookie에 저장 여부
  * @return
  */
  @RequestMapping(value = "/member/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            String memberid,
                            String memberpasswd,
                            @RequestParam(value="id_save", defaultValue="") String id_save,
                            @RequestParam(value="passwd_save", defaultValue="") String passwd_save,
                            @RequestParam(value="return_url", defaultValue="") String return_url) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memberid", memberid);
    map.put("memberpasswd", memberpasswd);
    
    int cnt = memberProc.login(map);    // 회원 로그인 처리
    
    if (cnt == 1) {       // cnt가 1일 경우 관리자 로그인 성공
    
      MemberVO memberVO = memberProc.readById(memberid);                    // 회원 아이디로 관리자 정보를 읽음
      session.setAttribute("memberno", memberVO.getMemberno());                // 서버의 메모리에 memberno기록
      session.setAttribute("memberid", memberid);                                          // 서버의 메모리에 memberid기록
      session.setAttribute("memberpasswd", memberVO.getMemberpasswd());   // 서버의 메모리에 memberpasswd기록
      session.setAttribute("membername", memberVO.getMembername());       // 서버의 메모리에 membername기록
      session.setAttribute("grade", memberVO.getGrade());                              // 서버의 메모리에 grade기록
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // memberid를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_id = new Cookie("ck_id", memberid);
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_id); // memberid 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // memberid 저장
      }
      
      // memberid를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", memberpasswd);
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // memberpasswd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setPath("/");
      ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
   
      System.out.println("-> return_url: " + return_url);
      
      if (return_url.length() > 0) {                      // ★ 로그인 성공후 자동으로 이동할 주소
        mav.setViewName("redirect:" + return_url);  
      } else {
        mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동
      }
    } else {
      mav.addObject("url", "/member/login_fail_msg"); // /WEB-INF/views/member/login_fail_msg.jsp
      mav.setViewName("redirect:/member/msg.do"); 
    }
    return mav;
  }
    
  /**
   * 로그아웃 처리
   * http://localhost:9093/member/login.do 
   * @param session
   * @return
   */
  @RequestMapping(value="/member/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    
    ModelAndView mav = new ModelAndView();
    
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * Cookie + Ajax 기반 로그인 처리
   * http://localhost:9093/member/login_ajax.do 
   * @param request Cookie를 읽기위해 필요
   * @param response Cookie를 쓰기위해 필요
   * @param session 로그인 정보를 메모리에 기록
   * @param memberid  회원 아이디
   * @param memberpasswd 회원 패스워드
   * @param id_save 회원 아이디 Cookie에 저장 여부
   * @param passwd_save 패스워드 Cookie에 저장 여부
   * @return
   */
  @RequestMapping(value = "/member/login_ajax.do", 
                             method = RequestMethod.POST)
  @ResponseBody
  public String login_cookie_proc_ajax (
                             HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             String memberid, String memberpasswd,
                             @RequestParam(value="id_save", defaultValue="") String id_save,
                             @RequestParam(value="passwd_save", defaultValue="") String passwd_save) {

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memberid", memberid);
    map.put("memberpasswd", memberpasswd);
    
    int cnt = memberProc.login(map);    // 회원 로그인 처리
    
    if (cnt == 1) { // 로그인 성공
      MemberVO memberVO = memberProc.readById(memberid);               
      session.setAttribute("memberno", memberVO.getMemberno());            // 서버의 메모리에 memberno기록
      session.setAttribute("memberid", memberid);                                      // 서버의 메모리에 memberid기록
      session.setAttribute("membername", memberVO.getMembername());   // 서버의 메모리에 membername기록
      session.setAttribute("grade", memberVO.getGrade());                          // 서버의 메모리에 grade기록
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // memberid를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_id = new Cookie("ck_id", memberid);
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_id); // memberid 저장
      } else { // N, memberid를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // memberid 저장
      }
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", memberpasswd);
        ck_passwd.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // memberpasswd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
      ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
    }
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
   
    return json.toString(); 
  }
  
  /**
   * Ajax 기반 회원 조회
   * http://localhost:9091/member/read_ajax.do
   * {
   * "memberage":"25",
   * "membername":"회원1",
   * "tel":"010-1234-5678",
   * "receiver":"abcd@naver.com",
   * }
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/read_ajax.do", method=RequestMethod.GET)
  @ResponseBody
  public String read_ajax(HttpSession session){
    int memberno = (int)session.getAttribute("memberno");
    
    MemberVO memberVO = this.memberProc.read(memberno);
    
    JSONObject json = new JSONObject();
    json.put("membername", memberVO.getMembername());
    json.put("tel", memberVO.getTel());
    json.put("receiver", memberVO.getReceiver());
    json.put("age", memberVO.getAge());
   
    return json.toString();
  }
  
  /**
   * 회원 아이디 찾기 폼
   * http://localhost:9093/member/id_search.do
   * @param tel
   * @param receiver
   * @return
   */
  @RequestMapping(value="/member/id_search.do", method=RequestMethod.GET)
  public ModelAndView id_search(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/id_search"); // /member/id_search.jsp
    
    return mav;
  }
  
  /**
   * 아이디 찾기로 찾은 아이디 조회
   * http://localhost:9093/member/id_search_result.do
   * @param tel
   * @param receiver
   * @return
   */
  @RequestMapping(value="/member/id_search_result.do", method=RequestMethod.GET)
  public ModelAndView id_search_result(HttpSession session, String tel, String receiver){
    ModelAndView mav = new ModelAndView();
    
    tel = (String)session.getAttribute("tel");                    // 회원이 입력한 전화번호를 session에서 꺼냄
    receiver = (String)session.getAttribute("receiver");    // 회원이 입력한 이메일을 session에서 꺼냄
    
    if (receiver == null) {   // 입력받은 회원 이메일이 없을 경우
    System.out.println("입력한 전화번호 : " + tel);    
    
    MemberVO memberVO = this.memberProc.readBytel(tel);   // adminid를 찾으려는 관리자의 전화번호를 읽음
    mav.addObject("memberid", memberVO.getMemberid());          
    mav.addObject("membername", memberVO.getMembername());   
    }else {
      System.out.println("입력한 이메일 : " + receiver);
      MemberVO memberVO = this.memberProc.readByReceiver(receiver); // id를 찾으려는 회원의 이메일을 읽음
      mav.addObject("memberid", memberVO.getMemberid());          
      mav.addObject("membername", memberVO.getMembername());   
    }
    
    mav.setViewName("/member/id_search_result"); // /member/id_search.jsp
    
    return mav;
  }
  
  /**
   * 회원 비밀번호 찾기 폼
   * http://localhost:9093/member/passwd_search.do
   * @param memberid
   * @param tel
   * @param receiver
   * @return
   */
  @RequestMapping(value="/member/passwd_search.do", method=RequestMethod.GET)
  public ModelAndView passwd_search(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/passwd_search"); // /member/passwd_search.jsp
    
    return mav;
  }
  
  /**
   * 회원 비밀번호 찾기로 새로운 패스워드로 변경하는 폼.
   * http://localhost:9093/member/passwd_search_result.do
   * @param memberid
   * @param tel
   * @param receiver
   * @return
   */
  @RequestMapping(value="/member/passwd_search_result.do", method=RequestMethod.GET)
  public ModelAndView passwd_search_result(){
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/passwd_search_result"); // /member/passwd_search_result.jsp
    
    return mav;
  }
  
  /**
   * 회원 패스워드 변경 처리
   * @param new_memberpasswd 새로운 패스워드
   * @param memberid 관리자 아이디
   * @param tel 관리자 전화번호
   * @param receiver 관리자 이메일   
   * @return
   */
  @RequestMapping(value="/member/passwd_search_result.do", method=RequestMethod.POST)
  public ModelAndView passwd_search_result(HttpSession session, String new_memberpasswd, 
                                                                        String memberid, String tel, String receiver){
  
    ModelAndView mav = new ModelAndView();
    
    memberid = (String)session.getAttribute("memberid");              // 회원이 입력한 회원 아이디를 session에서 꺼냄
    tel = (String)session.getAttribute("tel");                                    // 회원이 입력한 회원 전화번호를 session에서 꺼냄
    receiver = (String)session.getAttribute("receiver");                     // 회원이 입력한 회원 이메일을 session에서 꺼냄
   
    if (receiver == null) {         // 입력받은 회원 이메일이 없을 경우
      
    MemberVO memberVO = this.memberProc.readByIdTel(memberid, tel); 
    // 관리자 패스워드를 변경하려는 관리자 정보를 memberid와 tel을 통해 읽음
    
    int memberno = memberVO.getMemberno();
    
    System.out.println("입력한 아이디 : " + memberid);
    System.out.println("입력한 전화번호 : " + tel);
    System.out.println("입력한 회원번호 : " + memberno);
    
    mav.addObject("membername", memberVO.getMembername());  
    mav.addObject("memberid", memberVO.getMemberid());
    
    // 현재 패스워드 검사용 데이터
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("memberid", memberid);
    map.put("tel", tel);
    map.put("memberno", memberno);
   
    
    int cnt = memberProc.IdTel_check(map);        // 현재 아이디와 전화번호 검사
    int update_cnt = 0;                                       // 변경된 패스워드 수
    
    System.out.println(cnt);
    
    if (cnt == 1) {         //  cnt가 1일때 현재 아이디와 전화번호가 일치하는 경우
      map.put("memberpasswd", new_memberpasswd);    // 새로운 패스워드를 저장
      update_cnt = memberProc.passwd_update(map);     // 패스워드 변경 처리
      
      System.out.println(new_memberpasswd);
      
      if (update_cnt == 1) {
        mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
      } else {
        cnt = 0;                                                              // 패스워드는 일치했으나 변경하지는 못함.
        mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
      }
      
      mav.addObject("update_cnt", update_cnt);               // 변경된 패스워드의 갯수    
    } else {
      mav.addObject("code", "passwd_fail");                     // 패스워드가 일치하지 않는 경우
    }
    mav.addObject("cnt", cnt);  // 패스워드 일치 여부
    
    }else {
      MemberVO memberVO = this.memberProc.readByIdReceiver(memberid, receiver);
      // 회원 패스워드를 변경하려는 회원 정보를 memberid와 receiver을 통해 읽음
      
      int memberno = memberVO.getMemberno();
      
      System.out.println("입력한 아이디 : " + memberid);
      System.out.println("입력한 이메일 : " + receiver);
      System.out.println("입력한 회원번호 : " + memberno);
      
      mav.addObject("membername", memberVO.getMembername());  
      mav.addObject("memberid", memberVO.getMemberid());
      
      // 현재 패스워드 검사용 데이터
      HashMap<Object, Object> map = new HashMap<Object, Object>();
      map.put("memberid", memberid);
      map.put("receiver", receiver);
      map.put("memberno", memberno);
     
      int cnt = memberProc.IdReceiver_check(map);   // 현재 아이디와 이메일 검사
      int update_cnt = 0;                                          // 변경된 패스워드 수
      
      System.out.println(cnt);
      
      if (cnt == 1) { // cnt가 1일때 현재 아이디와 이메일이 일치하는 경우
        map.put("memberpasswd", new_memberpasswd);  // 새로운 패스워드를 저장
        update_cnt = memberProc.passwd_update(map);   // 패스워드 변경 처리
        
        System.out.println(new_memberpasswd);
        
        if (update_cnt == 1) {
          mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
        } else {
          cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
          mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
        }
        mav.addObject("update_cnt", update_cnt);              // 변경된 패스워드의 갯수    
      } else {
        mav.addObject("code", "passwd_fail");                   // 패스워드가 일치하지 않는 경우
      }
      mav.addObject("cnt", cnt);                                      // 패스워드 일치 여부
    }
    
    mav.addObject("url", "/member/msg");                     // /member/msg -> /member/msg.jsp
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 탈퇴 폼
   * http://localhost:9093/member/unregister.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/unregister.do", method=RequestMethod.GET)
  public ModelAndView unregister(HttpSession session){
    ModelAndView mav = new ModelAndView();
    
    if (this.memberProc.isMember(session)) {  // 현재 관리자일 경우
      int memberno = (int)session.getAttribute("memberno");
      
      MemberVO memberVO = this.memberProc.read(memberno);     // 회원 탈퇴를 위해 관리자 정보를 읽음
      mav.addObject("memberVO", memberVO);
      mav.setViewName("/member/read"); // /member/read.jsp
      
    } else {  // 관리자가 아닐경우
      mav.setViewName("/member/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp   
    }
    mav.setViewName("/member/unregister"); //   /member/unregister.jsp
    
    return mav;
  }
  
  /**
   * 회원 탈퇴를 위해 등록된 패스워드가 일치하는지 재확인 폼
   * http://localhost:9093/member/passwd_true.do
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/passwd_true.do", method=RequestMethod.GET)
  public ModelAndView passwd_true(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/member/passwd_true"); // /member/passwd_update.jsp
    
    return mav;
  }
  
  /**
   * 회원 탈퇴를 위해 등록된 패스워드가 일치하는지 재확인 처리
   * @param memberno 회원 번호
   * @param memberpasswd 현재 패스워드
   * @return
   */
  @RequestMapping(value="/member/passwd_true.do", method=RequestMethod.POST)
  public ModelAndView passwd_true(HttpSession session, String memberpasswd, int memberno){
        
    ModelAndView mav = new ModelAndView();

    
    // 현재 패스워드 검사용 데이터
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("memberno", memberno);
    map.put("memberpasswd", memberpasswd);
    
    int cnt = memberProc.passwd_check(map);       // 현재 패스워드 검사
    
    if (cnt == 1) {                                                 // 현재 패스워드가 일치하는 경우
      map.put("memberpasswd", memberpasswd);  // 새로운 패스워드를 저장
      
    } else {
      mav.addObject("code", "passwd_fail");           // 패스워드가 일치하지 않는 경우
    }

    mav.addObject("cnt", cnt);                              // 패스워드 일치 여부
    mav.addObject("url", "/member/msg");           // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 탈퇴를 할것인지 재확인 폼
   * @param memberno
   * @return
   */
  @RequestMapping(value="/member/unregister_proc.do", method=RequestMethod.GET)
  public ModelAndView unregister_proc(int memberno){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);     // 회원 탈퇴 재확인을 하기 위해 회원 정보를 읽음
    mav.addObject("memberVO", memberVO);
    mav.setViewName("/member/read");                                       // /member/read.jsp
    
    mav.setViewName("/member/unregister_proc");                       // /member/unregister_proc.jsp
    
    return mav;
  }
  
  /**
   * 회원 탈퇴를 할 것인지 재확인 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/unregister_proc.do", method=RequestMethod.POST)
  public ModelAndView unregister_proc(HttpSession session, MemberVO memberVO){
    
    ModelAndView mav = new ModelAndView();
    
    int cnt= memberProc.unregister(memberVO);                                // 회원 탈퇴 회원 등급변경
    
    if (cnt == 1) {                                                                              // cnt가 1일경우 회원 탈퇴 (회원 등급 변경)
      mav.addObject("code", "unregister_success");                              // 회원 탈퇴 성공              
      mav.addObject("membername", memberVO.getMembername());  
      mav.addObject("memberid", memberVO.getMemberid());             // 관리자 탈퇴 하였습니다.
      session.invalidate();                                                                   // 모든 session 변수 삭제
    }
    
    mav.addObject("cnt", cnt);
    mav.addObject("url", "/member/msg");                                        // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do");                           // 새로 고침 방지
    
    return mav;
  }
  
  /**
   * 회원 등급 변경
   * http://localhost:9093/member/grade_update.do
   * @param memberno
   * @param grade
   * @return
   */
  @RequestMapping(value="/member/grade_update.do", method=RequestMethod.GET)
  public ModelAndView grade_update(int memberno, int grade){
    ModelAndView mav = new ModelAndView();
    
    MemberVO memberVO = this.memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    System.out.println(grade);
    
    String grade_msg = "";
    
    if(0 < grade && grade <= 10) {                // 회원 등급이 0 보다 크고 10보다 작거나 같을때 회원 관리자
      grade_msg = "회원 관리자";
    }else if (10 < grade && grade <= 20) {     // 회원 등급이 10 보다 크고 20보다 작거나 같을때 회원
      grade_msg = "회원";
    }else if (20 < grade && grade <= 30) {     // 회원 등급이 20 보다 크고 30보다 작거나 같을때 정지 회원
      grade_msg = "정지 회원";
    }else if (30 < grade && grade <= 40) {     // 회원 등급이 30 보다 크고 40보다 작거나 같을때 탈퇴 회원
      grade_msg = "탈퇴 회원";
    }else {                                                   // 0 < grade <= 40이 아닐때 비회원
      grade_msg = "비회원";
    }
    
    mav.addObject("grade", grade);
    mav.addObject("grade_msg", grade_msg);
    
    mav.setViewName("/member/grade_update"); // /member/grade_update.jsp
    
    return mav;
  }
  
  /**
   * 회원 등급 변경 처리
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/grade_update.do", method=RequestMethod.POST)
  public ModelAndView grade_update(HttpSession session, MemberVO memberVO, int grade){
    
    ModelAndView mav = new ModelAndView();
    
    int cnt= memberProc.unregister(memberVO);       // 회원 등급변경
    
    if (cnt == 1) {           // cnt가 1일 경우 회원 등급 변경 성공
      mav.addObject("code", "grade_update_success");
    }
    mav.addObject("cnt", cnt);
    mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
    
    mav.setViewName("redirect:/member/msg.do"); // 새로 고침 방지
    
    return mav;
  }
}



