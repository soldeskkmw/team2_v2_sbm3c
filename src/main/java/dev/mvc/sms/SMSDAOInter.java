package dev.mvc.sms;

import java.util.ArrayList;

import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberVO;
import dev.mvc.admin.AdminVO;

public interface SMSDAOInter {
  
  /**
   * 등록
   * @param smsVO
   * @return 등록한 문자 정보
   */
  public int proc(SMSVO smsVO);
  
  /**
   * 목록
   * @return
   */
  public ArrayList<SMSVO> sms_list();
  
  
  
}
