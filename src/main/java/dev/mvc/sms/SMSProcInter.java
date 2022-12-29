package dev.mvc.sms;

import java.util.ArrayList;

import dev.mvc.member.MemberVO;

public interface SMSProcInter {

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
