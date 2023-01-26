package dev.mvc.email;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.email.EmailProc")
public class EmailProc implements EmailProcInter{
  
  @Autowired
  private EmailDAOInter emailDAO;

  @Override
  public int send(EmailVO emailVO) {
    int cnt = this.emailDAO.send(emailVO); 
    
    return cnt;
  }
  
  @Override
  public ArrayList<EmailVO> email_list() {
    ArrayList<EmailVO> email_list = this.emailDAO.email_list();
    return email_list;
  }
  
  @Override
  public EmailVO read(int emailno) {
    EmailVO emailVO = this.emailDAO.read(emailno);
    return emailVO;
  }
  
  @Override
  public int delete(int emailno) {
    int cnt = this.emailDAO.delete(emailno);
    return cnt;
  }
  
  @Override
  public int send_passwd(EmailVO emailVO) {
    int cnt = this.emailDAO.send_passwd(emailVO); 
    
    return cnt;
  }
  
  @Override
  public int admin_send(EmailVO emailVO) {
    int cnt = this.emailDAO.admin_send(emailVO); 
    
    return cnt;
  }
  
  @Override
  public ArrayList<EmailVO> admin_email_list() {
    ArrayList<EmailVO> admin_email_list = this.emailDAO.admin_email_list();
    return admin_email_list;
  }
  
  @Override
  public int admin_send_passwd(EmailVO emailVO) {
    int cnt = this.emailDAO.admin_send_passwd(emailVO); 
    
    return cnt;
  }
  
}
