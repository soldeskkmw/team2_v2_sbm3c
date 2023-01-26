package dev.mvc.email;

import java.util.ArrayList;

public interface EmailDAOInter {

  /**
<<<<<<< HEAD
   * 회원 아이디 찾기 email 등록
=======
   * 등록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int send(EmailVO emailVO);
  
  /**
<<<<<<< HEAD
   * 회원 email 목록
=======
   * 목록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @return
   */
  public ArrayList<EmailVO> email_list();
  
  /**
<<<<<<< HEAD
   * email 삭제
=======
   * 삭제
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
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
<<<<<<< HEAD
   * 회원 비밀번호 찾기 email 등록
=======
   * 등록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int send_passwd(EmailVO emailVO);
  
  /**
<<<<<<< HEAD
   * 관리자 아이디 찾기 email 등록
=======
   * 관리자 아이디 찾기 등록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int admin_send(EmailVO emailVO);
  
  /**
<<<<<<< HEAD
   * 관리자 email 목록
=======
   * 관리자 패스워드 찾기 email 목록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @return
   */
  public ArrayList<EmailVO> admin_email_list();
  
  /**
<<<<<<< HEAD
   * 관리자 비밀번호 찾기 email 등록
=======
   * 관리자 비밀번호 찾기 등록
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   * @param emailVO
   * @return 등록한 문자 정보
   */
  public int admin_send_passwd(EmailVO emailVO);
  
}
