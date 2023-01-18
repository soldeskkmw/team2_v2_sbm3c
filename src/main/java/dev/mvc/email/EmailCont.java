package dev.mvc.email;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.sms.SMSProcInter;
import dev.mvc.sms.SMSVO;
import dev.mvc.tool.MailTool;

@Controller
public class EmailCont {
  
    @Autowired
    @Qualifier("dev.mvc.email.EmailProc")
    private EmailProcInter emailProc = null;
  
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc = null;
    
    @Autowired
    @Qualifier("dev.mvc.admin.AdminProc")
    private AdminProcInter adminProc = null;
    
    public EmailCont() {
        System.out.println("-> EmailCont created.");
      }
    

//     http://localhost:9093/email/form.do
    /**
     * 메일 입력 폼
     * @return
     */
    @RequestMapping(value = {"/email/form_id.do"}, method=RequestMethod.GET)
    public ModelAndView form_id(HttpSession session) {
      ModelAndView mav = new ModelAndView();
      
      String auth_no = "";
      
      Random random = new Random();
      for (int i=0; i<=5; i++) {
        auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
      }
      session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
      
      mav.setViewName("/email/form_id");  // /WEB-INF/views/email/sended.jsp
      
      return mav;
    }
    
    // http://localhost:9093/email/send.do
    /**
     * 메일 전송
     * @return
     */
    @RequestMapping(value = {"/email/send.do"}, method=RequestMethod.POST)
    public ModelAndView send(String receiver, String from, String title, String content,
                                        HttpSession session,
                                        HttpServletRequest request, 
                                        EmailVO emailVO) {
      ModelAndView mav = new ModelAndView();
      
   
      String search = "아이디찾기";  // search에 ID 찾기 저장
      String ip = request.getRemoteAddr(); // ip를 확인하기 위해 ip에 저장
      String auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄

      emailVO.setIp(ip);
      emailVO.setAuthno(auth_no);
      emailVO.setSearch(search);
      
      System.out.println("-> IP : " + ip); // 접속자의 IP 수집
      System.out.println("인증번호 -> " + auth_no);
      System.out.println("입력받은 email주소 -> " + receiver);
      
      MemberVO memberVO = memberProc.readByReceiver(receiver);
      
      if (memberProc.readByReceiver(receiver) != null) {  // 입력된 tel 값이 memberVO에 없을 경우
        
        session.setAttribute("receiver", memberVO.getReceiver()); // 생성된 이메일 비교를 위하여 session에 저장
        String session_receiver = (String)session.getAttribute("receiver"); // 사용자에게 전송된 번호 session에서 꺼냄
        
        session.setAttribute("memberno", memberVO.getMemberno());
        int session_memberno = (int)session.getAttribute("memberno"); // 사용자에게 전송된 번호 session에서 꺼냄
        
        emailVO.setMemberno(session_memberno);
        
        System.out.println("-> session_receiver : " + session_receiver); // 접속자의 이메일 수집
        System.out.println("-> session_memberno : " + session_memberno); // 회원 정보 수집
          
        int cnt = this.emailProc.send(emailVO);
        
        
        if (cnt == 1) {
          mav.addObject("code", "create_success");
          // request.setAttribute("code", "create_success");
        } else {
          mav.addObject("code", "create_fail");
        }
        
          if (cnt > 0) { // 정상 등록
            mav.setViewName("redirect:/email/proc_next.do"); // 콘트롤러의 주소 요청, 자동 이동
            // mav.setViewName("/email/proc_next"); // /webapp/WEB-INF/views/sms/proc_next.jsp X
            MailTool mailTool = new MailTool();
            mailTool.send(receiver, from, title, content); // 메일 전송
          } else { // 등록 실패
            mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
          }

        mav.addObject("cnt", cnt);
        mav.addObject("emailVO", emailVO);
        }else {
          mav.setViewName("redirect:/email/receiver_fail.do");
        }
           
        return mav;
      }
       
    // http://localhost:9093
    // http://localhost:9093/email/proc_next.do
    @RequestMapping(value = {"/email/proc_next.do"}, method=RequestMethod.GET)
    public ModelAndView proc_next() {
      
      ModelAndView mav = new ModelAndView();
      
      mav.setViewName("/email/proc_next");  // /WEB-INF/views/sms/proc_next.jsp
      
      return mav;
    }
    
 // http://localhost:9093
    // http://localhost:9093/email/confirm.do
    @RequestMapping(value = {"/email/confirm.do"}, method=RequestMethod.GET)
    public ModelAndView confirm() {  
      ModelAndView mav = new ModelAndView();
      
      mav.setViewName("/email/confirm");  // /WEB-INF/views/sms/proc_next.jsp
      
      return mav;
    }

    
    //http://localhost:9093/email/confirm.do
    /**
     * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
     * @param session 사용자당 할당된 서버의 메모리
     * @param auth_no 사용자가 입력한 번호
     * @return
     */
   @RequestMapping(value = {"/email/confirm.do"}, method=RequestMethod.POST)
   public ModelAndView confirm(HttpSession session, String auth_no) {
     ModelAndView mav = new ModelAndView();
     
     String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
     
     if (session_auth_no.equals(auth_no)) {
         mav.setViewName("redirect:/member/id_search_result.do"); // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
     }else {
         mav.setViewName("redirect:/email/confirm.do"); // 콘트롤러의 주소 요청, 자동 이동
       }
     return mav;
   }
    
   /**
    * 목록 출력 가능
    * http://localhost:9093/email/email_list.do
    * @param session
    * @return
    */
    @RequestMapping(value="/email/email_list.do", method=RequestMethod.GET)
    public ModelAndView email_list(HttpSession session) {
      ModelAndView mav = new ModelAndView();
      
      if (this.adminProc.isAdmin(session)) {
        ArrayList<EmailVO> email_list = emailProc.email_list();
        
        mav.addObject("email_list", email_list);

        mav.setViewName("/email/email_list"); // /webapp/WEB-INF/views/email/email_list.jsp
        
      } else {
        mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
      }
      return mav;
    }  
    
    /**
     * 이메일 인증번호 삭제
     * http://localhost:9091/email/delete.do?emailno=1
     * @param memberno
     * @return
     */
    @RequestMapping(value="/email/delete.do", method=RequestMethod.GET)
    public ModelAndView delete(int emailno){
      ModelAndView mav = new ModelAndView();
      
      EmailVO emailVO = this.emailProc.read(emailno); // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
      mav.addObject("emailVO", emailVO);
      
      mav.setViewName("/email/delete"); // /email/delete.jsp
      
      return mav; // forward
    }
    
    /**
     * 이메일 인증번호 삭제 처리
     * @param memberVO
     * @return
     */
    @RequestMapping(value="/email/delete.do", method=RequestMethod.POST)
    public ModelAndView delete_proc(int emailno){
      ModelAndView mav = new ModelAndView();
      
      EmailVO emailVO = this.emailProc.read(emailno); // 삭제된 정보를 출력하기위해 읽음.
      
      int cnt= emailProc.delete(emailno);

      if (cnt == 1) {
        mav.addObject("code", "delete_success");
        mav.addObject("memberno", emailVO.getMemberno());  // 홍길동님(user4) 회원 정보를 변경했습니다.
        mav.addObject("ip", emailVO.getIp());
      } else {
        mav.addObject("code", "delete_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      mav.addObject("url", "/member/msg");  // /member/msg -> /member/msg.jsp
      
      mav.setViewName("redirect:/email/email_list.do");
      
      return mav;
    }
    
    /**
     * session 객체를 이용한 회원 조회
     * http://localhost:9091/member/read.do
     * @param memberno
     * @return
     */
    @RequestMapping(value="/email/read.do", method=RequestMethod.GET)
    public ModelAndView read(HttpSession session){
      ModelAndView mav = new ModelAndView();
      
      if (this.memberProc.isMember(session)) {
        int emailno = (int)session.getAttribute("emailno");
        
        EmailVO emailVO = this.emailProc.read(emailno);
        mav.addObject("emailVO", emailVO);
        mav.setViewName("/email/read"); // /email/read.jsp
      } else {
        mav.setViewName("/member/login_need"); // /webapp/WEB-INF/views/member/login_need.jsp
      }
      return mav; // forward
    }
    
    // http://localhost:9093/email/receiver_fail.do
    @RequestMapping(value = {"/email/receiver_fail.do"}, method=RequestMethod.GET)
    public ModelAndView receiver_fail() {
      ModelAndView mav = new ModelAndView();
      
      mav.setViewName("/email/receiver_fail");  // /WEB-INF/views/sms/form.jsp
        
      return mav;
    } 
    
   //  http://localhost:9093/email/form.do
   /**
    * 메일 입력 폼
    * @return
    */
   @RequestMapping(value = {"/email/form_passwd.do"}, method=RequestMethod.GET)
   public ModelAndView form_passwd(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     String auth_no = "";
     
     Random random = new Random();
     for (int i=0; i<=5; i++) {
       auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
     }
     session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
     
     mav.setViewName("/email/form_passwd");  // /WEB-INF/views/email/sended.jsp
     
     return mav;
   }
    
// http://localhost:9093/email/send.do
   /**
    * 메일 전송
    * @return
    */
   @RequestMapping(value = {"/email/send_passwd.do"}, method=RequestMethod.POST)
   public ModelAndView send_passwd(String receiver, String from, String title, String content,
                                       HttpSession session,
                                       HttpServletRequest request, 
                                       String memberid,
                                       EmailVO emailVO) {
     ModelAndView mav = new ModelAndView();
     
    
     String search = "비밀번호찾기";  // search에 ID 찾기 저장
     String ip = request.getRemoteAddr(); // ip를 확인하기 위해 ip에 저장
     String auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
     
     emailVO.setIp(ip);
     emailVO.setAuthno(auth_no);
     emailVO.setSearch(search);
     
     System.out.println("-> IP : " + ip); // 접속자의 IP 수집
     System.out.println("인증번호 -> " + auth_no);
     System.out.println("입력받은 email주소 -> " + receiver);
     
     MemberVO memberVO = memberProc.readByIdReceiver(memberid, receiver);
     
     if (memberProc.readByIdReceiver(memberid, receiver) != null) {  // 입력된 tel 값이 memberVO에 없을 경우
       
       session.setAttribute("memberid", memberVO.getMemberid()); // 생성된 전화번호를 비교를 위하여 session에 저장
       String session_memberid = (String)session.getAttribute("memberid"); // 사용자에게 전송된 번호 session에서 꺼냄
       
       session.setAttribute("receiver", memberVO.getReceiver()); // 생성된 이메일 비교를 위하여 session에 저장
       String session_receiver = (String)session.getAttribute("receiver"); // 사용자에게 전송된 번호 session에서 꺼냄
       
       session.setAttribute("memberno", memberVO.getMemberno());
       int session_memberno = (int)session.getAttribute("memberno"); // 사용자에게 전송된 번호 session에서 꺼냄
       
       emailVO.setMemberno(session_memberno);
       
       System.out.println("-> session_memberid : " + session_memberid); // 접속자의 전화번호 수집
       System.out.println("-> session_tel : " + session_receiver); // 접속자의 이메일 수집
       System.out.println("-> memberno : " + session_memberno); // 회원 정보 수집
         
       System.out.println("입력한 회원 아이디 -> " + memberid);      
       System.out.println("입력한 전화번호 -> " + receiver);      
       
       int cnt = this.emailProc.send_passwd(emailVO);
       
       
       if (cnt == 1) {
         mav.addObject("code", "create_success");
         // request.setAttribute("code", "create_success");
       } else {
         mav.addObject("code", "create_fail");
       }
       
         if (cnt > 0) { // 정상 등록
           mav.setViewName("redirect:/email/proc_passwd_next.do"); // 콘트롤러의 주소 요청, 자동 이동
           // mav.setViewName("/email/proc_next"); // /webapp/WEB-INF/views/sms/proc_next.jsp X
           MailTool mailTool = new MailTool();
           mailTool.send(receiver, from, title, content); // 메일 전송
         } else { // 등록 실패
           mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
         }

       mav.addObject("cnt", cnt);
       mav.addObject("emailVO", emailVO);
       }else {
         mav.setViewName("redirect:/email/id_receiver_fail.do");
       }
     
       return mav;
     }
    
   // http://localhost:9093
   // http://localhost:9093/email/proc_next.do
   @RequestMapping(value = {"/email/proc_passwd_next.do"}, method=RequestMethod.GET)
   public ModelAndView proc_passwd_next() {
     
     ModelAndView mav = new ModelAndView();
     
     mav.setViewName("/email/proc_passwd_next");  // /WEB-INF/views/sms/proc_next.jsp
     
     return mav;
   }
   
// http://localhost:9093
   // http://localhost:9093/email/confirm_passwd.do
   @RequestMapping(value = {"/email/confirm_passwd.do"}, method=RequestMethod.GET)
   public ModelAndView confirm_passwd() {  
     ModelAndView mav = new ModelAndView();
     
     mav.setViewName("/email/confirm_passwd");  // /WEB-INF/views/sms/proc_next.jsp
     
     return mav;
   }

   
   //http://localhost:9093/email/confirm_passwd.do
   /**
    * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
    * @param session 사용자당 할당된 서버의 메모리
    * @param auth_no 사용자가 입력한 번호
    * @return
    */
  @RequestMapping(value = {"/email/confirm_passwd.do"}, method=RequestMethod.POST)
  public ModelAndView confirm_passwd(HttpSession session, String auth_no) {
    ModelAndView mav = new ModelAndView();
    
    String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
    
    if (session_auth_no.equals(auth_no)) {
        mav.setViewName("redirect:/member/passwd_search_result.do"); // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
    }else {
        mav.setViewName("redirect:/email/confirm_passwd.do"); // 콘트롤러의 주소 요청, 자동 이동
      }
    return mav;
  }
  
  // http://localhost:9093/sms/id_tel_fail.do
  @RequestMapping(value = {"/email/id_receiver_fail.do"}, method=RequestMethod.GET)
  public ModelAndView id_receiver_fail() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/email/id_receiver_fail");  // /WEB-INF/views/sms/form.jsp
      
    return mav;
  }
  
  
  
  
  
  
  
  
    
    // http://localhost:9093/email/form_file.do
    /**
     * 파일 첨부 메일 입력폼
     * @return
     */
    @RequestMapping(value = {"/email/form_file.do"}, method=RequestMethod.GET)
    public ModelAndView form_file() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/email/form_file");  // /WEB-INF/views/email/form_file.jsp
      
      return mav;
    }
    
//    // http://localhost:9091/mail/send_file.do
//    /**
//     * 메일 전송
//     * @return
//     */
//    @RequestMapping(value = {"/mail/send_file.do"}, method=RequestMethod.POST)
//    public ModelAndView send_file(String receiver, String from, String title, String content,
//                                             MultipartFile file1MF) {
//      ModelAndView mav = new ModelAndView();
//      mav.setViewName("/mail/sended");  // /WEB-INF/views/mail/sended.jsp
//
//      MailTool mailTool = new MailTool();
//      mailTool.send_file(receiver, from, title, content, file1MF, "C:/kd/deploy/mail/images/"); // 메일 전송
//      
//      return mav;
//    }
    
    // http://localhost:9091/email/send_file.do
    /**
     * 첨부 파일 메일 전송
     * @return
     */
    @RequestMapping(value = {"/email/send_file.do"}, method=RequestMethod.POST)
    public ModelAndView send_file(String receiver, String from, String title, String content,
                                             MultipartFile[] file1MF) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/email/sended");  // /WEB-INF/views/mail/sended.jsp

      MailTool mailTool = new MailTool();
      mailTool.send_file(receiver, from, title, content, file1MF, "E:/kd/deploy/mvc/email/images/"); // 메일 전송
      
      return mav;
    }
    
//  http://localhost:9093/email/form.do
 /**
  * 메일 입력 폼
  * @return
  */
 @RequestMapping(value = {"/email/admin_form_id.do"}, method=RequestMethod.GET)
 public ModelAndView admin_form_id(HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   String auth_no = "";
   
   Random random = new Random();
   for (int i=0; i<=5; i++) {
     auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
   }
   session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
   
   mav.setViewName("/email/admin_form_id");  // /WEB-INF/views/email/sended.jsp
   
   return mav;
 }
    
 
 // http://localhost:9093/email/send.do
 /**
  * 메일 전송
  * @return
  */
 @RequestMapping(value = {"/email/admin_send.do"}, method=RequestMethod.POST)
 public ModelAndView admin_send(String receiver, String from, String title, String content,
                                     HttpSession session,
                                     HttpServletRequest request, 
                                     EmailVO emailVO) {
   ModelAndView mav = new ModelAndView();
   

   String search = "아이디찾기";  // search에 ID 찾기 저장
   String ip = request.getRemoteAddr(); // ip를 확인하기 위해 ip에 저장
   String auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄

   emailVO.setIp(ip);
   emailVO.setAuthno(auth_no);
   emailVO.setSearch(search);
   
   System.out.println("-> IP : " + ip); // 접속자의 IP 수집
   System.out.println("인증번호 -> " + auth_no);
   System.out.println("입력받은 email주소 -> " + receiver);
   
   AdminVO adminVO = adminProc.readByReceiver(receiver);
   
   if (adminProc.readByReceiver(receiver) != null) {  // 입력된 tel 값이 memberVO에 없을 경우
     
     session.setAttribute("adminreceiver", adminVO.getAdminreceiver()); // 생성된 이메일 비교를 위하여 session에 저장
     String adminreceiver = (String)session.getAttribute("adminreceiver"); // 사용자에게 전송된 번호 session에서 꺼냄
     
     session.setAttribute("adminno", adminVO.getAdminno());
     int session_adminno = (int)session.getAttribute("adminno"); // 사용자에게 전송된 번호 session에서 꺼냄
     
     emailVO.setAdminno(session_adminno);
     
     System.out.println("-> session_admin_receiver : " + adminreceiver); // 접속자의 이메일 수집
     System.out.println("-> session_adminno : " + session_adminno); // 회원 정보 수집
       
     int cnt = this.emailProc.admin_send(emailVO);
     
     
     if (cnt == 1) {
       mav.addObject("code", "create_success");
       // request.setAttribute("code", "create_success");
     } else {
       mav.addObject("code", "create_fail");
     }
     
       if (cnt > 0) { // 정상 등록
         mav.setViewName("redirect:/email/admin_proc_next.do"); // 콘트롤러의 주소 요청, 자동 이동
         // mav.setViewName("/email/admin_proc_next"); // /webapp/WEB-INF/views/sms/proc_next.jsp X
         MailTool mailTool = new MailTool();
         mailTool.send(receiver, from, title, content); // 메일 전송
       } else { // 등록 실패
         mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
       }

     mav.addObject("cnt", cnt);
     mav.addObject("emailVO", emailVO);
     }else {
       mav.setViewName("redirect:/email/receiver_fail.do");
     }
        
     return mav;
   }
 
 // http://localhost:9093
 // http://localhost:9093/email/admin_proc_next.do
 @RequestMapping(value = {"/email/admin_proc_next.do"}, method=RequestMethod.GET)
 public ModelAndView admin_proc_next() {
   
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/email/admin_proc_next");  // /WEB-INF/views/sms/admin_proc_next.jsp
   
   return mav;
 }
 
// http://localhost:9093
 // http://localhost:9093/email/admin_confirm.do
 @RequestMapping(value = {"/email/admin_confirm.do"}, method=RequestMethod.GET)
 public ModelAndView admin_confirm() {  
   ModelAndView mav = new ModelAndView();
   
   mav.setViewName("/email/admin_confirm");  // /WEB-INF/views/sms/admin_confirm.jsp
   
   return mav;
 }

 
 //http://localhost:9093/email/admin_confirm.do
 /**
  * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
  * @param session 사용자당 할당된 서버의 메모리
  * @param auth_no 사용자가 입력한 번호
  * @return
  */
@RequestMapping(value = {"/email/admin_confirm.do"}, method=RequestMethod.POST)
public ModelAndView admin_confirm(HttpSession session, String auth_no) {
  ModelAndView mav = new ModelAndView();
  
  String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
  
  if (session_auth_no.equals(auth_no)) {
      mav.setViewName("redirect:/admin/id_search_result.do"); // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
  }else {
      mav.setViewName("redirect:/email/admin_confirm.do"); // 콘트롤러의 주소 요청, 자동 이동
    }
  return mav;
}
 
    /**
     * 목록 출력 가능
     * http://localhost:9093/email/email_list.do
     * @param session
     * @return
     */
     @RequestMapping(value="/email/admin_email_list.do", method=RequestMethod.GET)
     public ModelAndView admin_email_list(HttpSession session) {
       ModelAndView mav = new ModelAndView();
       
       if (this.adminProc.isAdmin(session)) {
         ArrayList<EmailVO> admin_email_list = emailProc.admin_email_list();
         
         mav.addObject("admin_email_list", admin_email_list);

         mav.setViewName("/email/admin_email_list"); // /webapp/WEB-INF/views/email/email_list.jsp
         
       } else {
         mav.setViewName("/admin/login_need"); // /webapp/WEB-INF/views/admin/login_need.jsp 
       }
       return mav;
     }  

//   http://localhost:9093/email/form.do
     /**
      * 메일 입력 폼
      * @return
      */
     @RequestMapping(value = {"/email/admin_form_passwd.do"}, method=RequestMethod.GET)
     public ModelAndView admin_form_passwd(HttpSession session) {
       ModelAndView mav = new ModelAndView();
       
       String auth_no = "";
       
       Random random = new Random();
       for (int i=0; i<=5; i++) {
         auth_no = auth_no + random.nextInt(10); // 0~9 번호 6자리 생성
       }
       session.setAttribute("auth_no", auth_no); // 생성된 번호를 비교를 위하여 session에 저장
       
       mav.setViewName("/email/admin_form_passwd");  // /WEB-INF/views/email/sended.jsp
       
       return mav;
     }
     
  // http://localhost:9093/email/admin_send_passwd.do
     /**
      * 메일 전송
      * @return
      */
     @RequestMapping(value = {"/email/admin_send_passwd.do"}, method=RequestMethod.POST)
     public ModelAndView admin_send_passwd(String receiver, String from, String title, String content,
                                         HttpSession session,
                                         HttpServletRequest request, 
                                         String adminid,
                                         EmailVO emailVO) {
       ModelAndView mav = new ModelAndView();
       
      
       String search = "비밀번호찾기";  // search에 ID 찾기 저장
       String ip = request.getRemoteAddr(); // ip를 확인하기 위해 ip에 저장
       String auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
       
       emailVO.setIp(ip);
       emailVO.setAuthno(auth_no);
       emailVO.setSearch(search);
       
       System.out.println("-> IP : " + ip); // 접속자의 IP 수집
       System.out.println("인증번호 -> " + auth_no);
       System.out.println("입력받은 email주소 -> " + receiver);
       
       AdminVO adminVO = adminProc.readByIdReceiver(adminid, receiver);
       
       if (adminProc.readByIdReceiver(adminid, receiver) != null) {  // 입력된 tel 값이 memberVO에 없을 경우
         
         session.setAttribute("adminid", adminVO.getAdminid()); // 생성된 전화번호를 비교를 위하여 session에 저장
         adminid = (String)session.getAttribute("adminid"); // 사용자에게 전송된 번호 session에서 꺼냄
         
         session.setAttribute("adminreceiver", adminVO.getAdminreceiver()); // 생성된 이메일 비교를 위하여 session에 저장
         String adminreceiver = (String)session.getAttribute("adminreceiver"); // 사용자에게 전송된 번호 session에서 꺼냄
         
         session.setAttribute("adminno", adminVO.getAdminno());
         int session_adminno = (int)session.getAttribute("adminno"); // 사용자에게 전송된 번호 session에서 꺼냄
         
         emailVO.setAdminno(session_adminno);
         
         System.out.println("-> session_adminid : " + adminid); // 접속자의 전화번호 수집
         System.out.println("-> session_adminreceiver : " + adminreceiver); // 접속자의 이메일 수집
         System.out.println("-> session_adminno : " + session_adminno); // 회원 정보 수집
           
         System.out.println("입력한 회원 아이디 -> " + adminid);      
         System.out.println("입력한 전화번호 -> " + receiver);      
         
         int cnt = this.emailProc.admin_send_passwd(emailVO);
         
         
         if (cnt == 1) {
           mav.addObject("code", "create_success");
           // request.setAttribute("code", "create_success");
         } else {
           mav.addObject("code", "create_fail");
         }
         
           if (cnt > 0) { // 정상 등록
             mav.setViewName("redirect:/email/admin_proc_passwd_next.do"); // 콘트롤러의 주소 요청, 자동 이동
             // mav.setViewName("/email/proc_next"); // /webapp/WEB-INF/views/sms/proc_next.jsp X
             MailTool mailTool = new MailTool();
             mailTool.send(receiver, from, title, content); // 메일 전송
           } else { // 등록 실패
             mav.setViewName("/cate/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
           }

         mav.addObject("cnt", cnt);
         mav.addObject("emailVO", emailVO);
         }else {
           mav.setViewName("redirect:/email/id_receiver_fail.do");
         }
       
         return mav;
       }
      
     // http://localhost:9093
     // http://localhost:9093/email/proc_next.do
     @RequestMapping(value = {"/email/admin_proc_passwd_next.do"}, method=RequestMethod.GET)
     public ModelAndView admin_proc_passwd_next() {
       
       ModelAndView mav = new ModelAndView();
       
       mav.setViewName("/email/admin_proc_passwd_next");  // /WEB-INF/views/sms/proc_next.jsp
       
       return mav;
     }
     
  // http://localhost:9093
     // http://localhost:9093/email/confirm_passwd.do
     @RequestMapping(value = {"/email/admin_confirm_passwd.do"}, method=RequestMethod.GET)
     public ModelAndView admin_confirm_passwd() {  
       ModelAndView mav = new ModelAndView();
       
       mav.setViewName("/email/admin_confirm_passwd");  // /WEB-INF/views/sms/proc_next.jsp
       
       return mav;
     }

     
     //http://localhost:9093/email/confirm_passwd.do
     /**
      * 문자로 전송된 번호와 사용자가 입력한 번호를 비교합니다.
      * @param session 사용자당 할당된 서버의 메모리
      * @param auth_no 사용자가 입력한 번호
      * @return
      */
    @RequestMapping(value = {"/email/admin_confirm_passwd.do"}, method=RequestMethod.POST)
    public ModelAndView admin_confirm_passwd(HttpSession session, String auth_no) {
      ModelAndView mav = new ModelAndView();
      
      String session_auth_no = (String)session.getAttribute("auth_no"); // 사용자에게 전송된 번호 session에서 꺼냄
      
      if (session_auth_no.equals(auth_no)) {
          mav.setViewName("redirect:/admin/passwd_search_result.do"); // 콘트롤러의 주소 요청, 자동 이동, id_search_result.jsp
      }else {
          mav.setViewName("redirect:/email/admin_confirm_passwd.do"); // 콘트롤러의 주소 요청, 자동 이동
        }
      return mav;
    }

    
}


