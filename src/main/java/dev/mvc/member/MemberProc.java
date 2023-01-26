package dev.mvc.member;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component("dev.mvc.member.MemberProc") // Autowired annotation에 사용하는 이름
public class MemberProc implements MemberProcInter {
  @Autowired
  private MemberDAOInter memberDAO; // 스프링이 자동 구현한 DAO 객체 자동 할당
  
  public MemberProc(){ // 기본 생성자, 객체 생성시 자동 호출
    // System.out.println("-> MemberProc created.");
  }

  @Override // MemberProcInter 인터페이스의 추상 메소드 구현
  public int checkID(String memberid) {
    int cnt = this.memberDAO.checkID(memberid);
    return cnt;
  }
  
  @Override
  public int create(MemberVO memberVO) {
    int cnt = this.memberDAO.create(memberVO);
    return cnt;
  }

  @Override
  public ArrayList<MemberVO> list() {
    ArrayList<MemberVO> list = this.memberDAO.list();
    return list;
  }

  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = this.memberDAO.read(memberno);
    return memberVO;
  }

  @Override
  public MemberVO readById(String memberid) {
    MemberVO memberVO = this.memberDAO.readById(memberid);
    return memberVO;
  }
  
  
  @Override
  public int update(MemberVO memberVO) {
    int cnt = this.memberDAO.update(memberVO);
    return cnt;
  }
  
  @Override
  public int delete(int memberno) {
    int cnt = this.memberDAO.delete(memberno);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap<Object, Object> map) {
    int cnt = this.memberDAO.passwd_check(map);
    return cnt;
  }

  @Override
  public int passwd_update(HashMap<Object, Object> map) {
    int cnt = this.memberDAO.passwd_update(map);
    return cnt;
  }
  
  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.memberDAO.login(map);
    return cnt;
  }
  
  @Override
  public boolean isMember(HttpSession session){
    boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 0;         // 회원 등급 기본값 0
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String memberid = (String)session.getAttribute("memberid");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      if (memberid != null && grade <= 20 && grade > 0){ // 회원 등급이 0 < grade <= 20 인경우
        sw = true;  // 로그인 한 경우
      }
    }
    return sw;
  }
 
  @Override
  public MemberVO readBytel(String tel) {
    MemberVO memberVO = this.memberDAO.readBytel(tel);
    return memberVO;
  }
  
  @Override
  public MemberVO id_search_result(String tel) {
    MemberVO memberVO = this.memberDAO.id_search_result(tel);
    return memberVO;
  }
 
  @Override
  public MemberVO readByIdTel(String memberid, String tel) {
    MemberVO memberVO = this.memberDAO.readByIdTel(memberid, tel);
    return memberVO;
  }
  
  @Override
  public int IdTel_check(HashMap<Object, Object> map) {
    int cnt = this.memberDAO.IdTel_check(map);
    return cnt;
  }
  
  @Override
  public int passwd_search_result(HashMap<Object, Object> map) {
    int cnt = this.memberDAO.passwd_search_result(map);
    return cnt;
  }
  
  @Override
  public MemberVO readByReceiver(String receiver) {
    MemberVO memberVO = this.memberDAO.readByReceiver(receiver);
    return memberVO;
  }
  
  @Override
  public MemberVO readByIdReceiver(String memberid, String receiver) {
    MemberVO memberVO = this.memberDAO.readByIdReceiver(memberid, receiver);
    return memberVO;
  }
  
  @Override
  public int IdReceiver_check(HashMap<Object, Object> map) {
    int cnt = this.memberDAO.IdReceiver_check(map);
    return cnt;
  }
  
  @Override
  public int unregister(MemberVO memberVO) {
    int cnt = this.memberDAO.unregister(memberVO);
    return cnt;
  }
}
 


