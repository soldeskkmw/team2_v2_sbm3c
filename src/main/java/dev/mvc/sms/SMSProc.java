package dev.mvc.sms;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.sms.SMSProc")
public class SMSProc implements SMSProcInter{
  
  @Autowired
  private SMSDAOInter smsDAO;

  @Override
  public int proc(SMSVO smsVO) {
    int cnt = this.smsDAO.proc(smsVO); 
    
    return cnt;
  }
  
  @Override
  public ArrayList<SMSVO> sms_list() {
    ArrayList<SMSVO> sms_list = this.smsDAO.sms_list();
    return sms_list;
  }
  
  @Override
  public SMSVO read(int smsno) {
    SMSVO smsVO = this.smsDAO.read(smsno);
    return smsVO;
  }
  
  @Override
  public int delete(int smsno) {
    int cnt = this.smsDAO.delete(smsno);
    return cnt;
  }
  
  @Override
  public int proc_passwd(SMSVO smsVO) {
    int cnt = this.smsDAO.proc_passwd(smsVO); 
    
    return cnt;
  }
  
  @Override
  public int admin_proc(SMSVO smsVO) {
    int cnt = this.smsDAO.admin_proc(smsVO); 
    
    return cnt;
  }
  
  @Override
  public int admin_proc_passwd(SMSVO smsVO) {
    int cnt = this.smsDAO.admin_proc_passwd(smsVO); 
    
    return cnt;
  }
  
  @Override
  public ArrayList<SMSVO> admin_sms_list() {
    ArrayList<SMSVO> admin_sms_list = this.smsDAO.admin_sms_list();
    return admin_sms_list;
  }

  
}
