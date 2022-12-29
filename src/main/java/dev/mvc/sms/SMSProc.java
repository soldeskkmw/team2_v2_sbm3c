package dev.mvc.sms;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;




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
  
  

  
}
