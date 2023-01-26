package dev.mvc.sms;

import java.util.ArrayList;

import dev.mvc.cate.CateVO;
import dev.mvc.member.MemberVO;
import dev.mvc.admin.AdminVO;

public interface SMSDAOInter {
  
  /**
   * 회원 아이디 찾기 sms 등록
   * @param smsVO
   * @return 등록한 문자 정보
   */
  public int proc(SMSVO smsVO);
  
  /**
   * 회원 sms 목록
   * @return
   */
  public ArrayList<SMSVO> sms_list();
  
  /**
   * sms 삭제
   * @param smsno
   * @return 삭제된 레코드 수
   */
  public int delete(int smsno);
  
  /**
   * 조회
   * @param smsno
   * @return
   */
  public SMSVO read(int smsno);
  
  /**
   * 회원 비밀번호 찾기 sms 등록
   * @param smsVO
   * @return 등록한 문자 정보
   */
  public int proc_passwd(SMSVO smsVO);
  
  /**
   * 관리자 아이디 찾기 sms 등록
   * @param smsVO
   * @return 등록한 관리자 문자 정보
   */
  public int admin_proc(SMSVO smsVO);
  
  /**
   * 관리자 비밀번호 찾기 sms 등록
   * @param smsVO
   * @return 등록한 관리자 문자 정보
   */
  public int admin_proc_passwd(SMSVO smsVO);
  
  /**
   * 관리자 sms 목록
   * @return
   */
  public ArrayList<SMSVO> admin_sms_list();
}
