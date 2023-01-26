package dev.mvc.email;

import java.util.ArrayList;

public interface EmailDAOInter {

  /**
   * 회원 아이디 찾기 email 등록
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int send(EmailVO emailVO);
  
  /**
   * 회원 email 목록
   * @return
   */
  public ArrayList<EmailVO> email_list();
  
  /**
   * email 삭제
   * @param emailno
   * @return 삭제된 레코드 수
   */
  public int delete(int emailno);
  
  /**
   * 조회
   * @param emailno
   * @return
   */
  public EmailVO read(int emailno);
  
  /**
   * 회원 비밀번호 찾기 email 등록
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int send_passwd(EmailVO emailVO);
  
  /**
   * 관리자 아이디 찾기 email 등록
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int admin_send(EmailVO emailVO);
  
  /**
   * 관리자 email 목록
   * @return
   */
  public ArrayList<EmailVO> admin_email_list();
  
  /**
   * 관리자 비밀번호 찾기 email 등록
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int admin_send_passwd(EmailVO emailVO);
  
}
