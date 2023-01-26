package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import dev.mvc.member.MemberVO;

public interface AdminDAOInter {
  
  /**
   * 중복 아이디 검사
   * @param adminid
   * @return 중복 아이디 갯수
   */
  public int checkID(String adminid);
  
  /**
   * 관리자 회원 가입
   * @param adminVO
   * @return
   */
  public int create(AdminVO adminVO);
  
  /**
   * 관리자 목록
   * @return
   */
  public ArrayList<AdminVO> list();
  
  /**
   * adminno로 관리자 정보 조회
   * @param adminno
   * @return
   */
  public AdminVO read(int adminno);
  
  /**
   * adminid로 관리자 정보 조회
   * @param adminid
   * @return
   */
  public AdminVO readById(String adminid);
  
  /**
   * 관리자 정보 수정 처리
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  
  /**
   * 관리자 삭제 처리
   * @param adminno
   * @return
   */
  public int delete(int adminno);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<Object, Object> map);
  
  /**
   * 관리자 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<Object, Object> map);
  
  /**
   * 관리자 로그인 처리
   * @param map
   * @return
   */
  public int login(HashMap<String, Object> map);
  
  /**
   * 로그인된 관리자인지 검사합니다.
   * @param session
   * @return true: 관리자
   */
  public boolean isAdmin(HttpSession session);   
   
  /**
   * 로그인된 메인 관리자인지 검사합니다.
   * @param session
   * @return true: 관리자
   */
  public boolean isAdmin_grade(HttpSession session);   
  
  /**
   * admintel로 관리자 정보 조회
   * @param admintel
   * @return
   */
  public AdminVO readBytel(String admintel);
  
  /**
   * admintel로 관리자 id 찾기
   * @param admintel
   * @return
   */
  public AdminVO id_search_result(String admintel);
  
  /**
   * 관리자 아이디와 전화번호 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int IdTel_check(HashMap<Object, Object> map);
  
  /**
   * adminreceiver로 회원 정보 조회
   * @param adminreceiver
   * @return
   */
  public AdminVO readByReceiver(String adminreceiver);
  
  /**
   * adminid, admintel로 관리자 정보 조회
   * @param adminid
   * @param admintel
   * @return
   */
  public AdminVO readByIdTel(@Param("adminid")String adminid, @Param("admintel")String admintel);
  
  /**
   * adminid, adminreceiver로 관리자 정보 조회
   * @param adminid
   * @param adminreceiver
   * @return
   */
  public AdminVO readByIdReceiver(@Param("adminid")String adminid, @Param("adminreceiver")String adminreceiver);
  
  /**
   * 관리자 아이디와 이메일 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int IdReceiver_check(HashMap<Object, Object> map);
  
  /**
   * 관리자 탈퇴 처리
   * @param adminVO
   * @return
   */
  public int unregister(AdminVO adminVO);
  
  /**
   * 관리자 회원가입한 인원 가입 허가 O로 변경
   * @param adminno
   * @return 변경된 레코드 수
   */
  public int update_permission(int adminno);
  
  /**
   * 관리자 가입 허가 목록
   * @return
   */
  public ArrayList<AdminVO> permission_list();
}
