package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import dev.mvc.tool.Tool;

public interface MemberProcInter {
  /**
   * 중복 아이디 검사
   * @param memberid
   * @return 중복 아이디 갯수
   */
  public int checkID(String memberid);
  
  /**
   * 회원 가입
   * @param memberVO
   * @return
   */
  public int create(MemberVO memberVO);
  
  /**
   * 목록
   * @return
   */
  public ArrayList<MemberVO> list();
  
  /**
   * memberno로 회원 정보 조회
   * @param memberno
   * @return
   */
  public MemberVO read(int memberno);
  
  /**
   * memberid로 회원 정보 조회
   * @param memberid
   * @return
   */
  public MemberVO readById(String memberid);
  
  /**
   * 회원 수정 처리
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  /**
   * 회원 삭제 처리
   * @param memberno
   * @return
   */
  public int delete(int memberno);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<Object, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<Object, Object> map);
  
  /**
   * 로그인 처리
   * @param map
   */
  public int login(HashMap<String, Object> map);
  
  /**
   * 로그인된 회원 계정인지 검사합니다.
   * @param session
   * @return true: 사용자
   */
  public boolean isMember(HttpSession session);   
  
  
  /**
   * tel로 회원 정보 조회
   * @param tel
   * @return
   */
  public MemberVO readBytel(String tel);
  
  /**
   * tel로 회원 id 찾기
   * @param tel
   * @return
   */
  public MemberVO id_search_result(String tel);

  /**
   * memberid, tel로 회원 정보 조회
   * @param memberid
   * @param tel 
   * @return
   */
  public MemberVO readByIdTel(@Param("memberid")String memberid, @Param("tel")String tel);
  
  /**
   * 현재 회원 아이디, 전화번호 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int IdTel_check(HashMap<Object, Object> map);
  
  /**
   * 회원 비밀번호 찾기(새로운 패스워드로 변경)
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_search_result(HashMap<Object, Object> map);
  
  /**
   * receiver로 회원 정보 조회
   * @param receiver
   * @return
   */
  public MemberVO readByReceiver(String receiver);
  
  /**
   * memberid, receiver로 회원 정보 조회
   * @param memberid
   * @param receiver
   * @return
   */
  public MemberVO readByIdReceiver(@Param("memberid")String memberid, @Param("receiver")String receiver);
  
  /**
   * 현재 회원 아이디와 이메일 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int IdReceiver_check(HashMap<Object, Object> map);
  
  /**
   * 회원 탈퇴 처리
   * @param memberVO
   * @return
   */
  public int unregister(MemberVO memberVO);
  
}