package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;


@Component("dev.mvc.admin.AdminProc") // Autowired annotation에 사용하는 이름
public class AdminProc implements AdminProcInter {
  @Autowired
  private AdminDAOInter adminDAO; // 스프링이 자동 구현한 DAO 객체 자동 할당

  public AdminProc(){ // 기본 생성자, 객체 생성시 자동 호출
    // System.out.println("-> AdminProc created.");
  } 
  
  @Override // AdminProcInter 인터페이스의 추상 메소드 구현
  public int checkID(String adminid) {
    int cnt = this.adminDAO.checkID(adminid);
    return cnt;
  }
  
  @Override
  public int create(AdminVO adminVO) {
    int cnt = this.adminDAO.create(adminVO);
    return cnt;
  }
  
  @Override
  public ArrayList<AdminVO> list() {
    ArrayList<AdminVO> list = this.adminDAO.list();
    return list;
  }
  
  @Override
  public AdminVO read(int adminno) {
    AdminVO adminVO = this.adminDAO.read(adminno);
    return adminVO;
  }
  
  @Override
  public AdminVO readById(String adminid) {
    AdminVO adminVO = this.adminDAO.readById(adminid);
    return adminVO;
  }
  
  @Override
  public int update(AdminVO adminVO) {
    int cnt = this.adminDAO.update(adminVO);
    return cnt;
  }
  
  @Override
  public int delete(int adminno) {
    int cnt = this.adminDAO.delete(adminno);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap<Object, Object> map) {
    int cnt = this.adminDAO.passwd_check(map);
    return cnt;
  }

  @Override
  public int passwd_update(HashMap<Object, Object> map) {
    int cnt = this.adminDAO.passwd_update(map);
    return cnt;
  }
  
  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.adminDAO.login(map);
    return cnt;
  }
  
  @Override
  public boolean isAdmin(HttpSession session) {
    boolean admin_sw = false;     // 로그인하지 않은 것으로 초기화
    int admingrade = 0;              // 관리자 등급 기본값 0
    
    // System.out.println("-> grade: " + session.getAttribute("admin_grade"));
    if (session != null) {
      String adminid = (String)session.getAttribute("adminid");
      if (session.getAttribute("admingrade") != null) {
        admingrade = (int)session.getAttribute("admingrade");
      }
      if (adminid != null && admingrade <= 20 && admingrade >0){  // 관리자 등급이 0 < admingrade <= 20 인경우 관리자
        admin_sw = true;  // 로그인 한 경우
      }
    }
    return admin_sw;
  }  
  
  @Override
  public boolean isAdmin_grade(HttpSession session) {
    boolean admin_sw = false;     // 로그인하지 않은 것으로 초기화
    int admingrade = 0;               // 관리자 등급 기본값 0
    
    // System.out.println("-> grade: " + session.getAttribute("admin_grade"));
    if (session != null) {
      String adminid = (String)session.getAttribute("adminid");
      if (session.getAttribute("admingrade") != null) {
        admingrade = (int)session.getAttribute("admingrade");
      }
      
      if (adminid != null && admingrade <= 10 && admingrade > 0){   // 관리자 등급이 0 < admingrade <= 10 인경우 메인관리자
        admin_sw = true;  // 로그인 한 경우
      }
    }
    return admin_sw;
  }  
  
  @Override
  public AdminVO readBytel(String admintel) {
    AdminVO adminVO = this.adminDAO.readBytel(admintel);
    return adminVO;
  }
  
  @Override
  public AdminVO id_search_result(String admintel) {
    AdminVO adminVO = this.adminDAO.id_search_result(admintel);
    return adminVO;
  }
  
  @Override
  public AdminVO readByReceiver(String adminreceiver) {
    AdminVO adminVO = this.adminDAO.readByReceiver(adminreceiver);
    return adminVO;
  }
  
  @Override
  public int IdTel_check(HashMap<Object, Object> map) {
    int cnt = this.adminDAO.IdTel_check(map);
    return cnt;
  }
  
  @Override
  public AdminVO readByIdTel(String adminid, String admintel) {
    AdminVO adminVO = this.adminDAO.readByIdTel(adminid, admintel);
    return adminVO;
  }
  
  @Override
  public AdminVO readByIdReceiver(String adminid, String adminreceiver) {
    AdminVO adminVO = this.adminDAO.readByIdReceiver(adminid, adminreceiver);
    return adminVO;
  }
  
  @Override
  public int IdReceiver_check(HashMap<Object, Object> map) {
    int cnt = this.adminDAO.IdReceiver_check(map);
    return cnt;
  }
  
  @Override
  public int unregister(AdminVO adminVO) {
    int cnt = this.adminDAO.unregister(adminVO);
    return cnt;
  }
  
  @Override
  public int update_permission(int adminno) {
    int cnt = this.adminDAO.update_permission(adminno);
    return cnt;
  }
  
  @Override
  public ArrayList<AdminVO> permission_list() {
    ArrayList<AdminVO> permission_list = this.adminDAO.permission_list();
    return permission_list;
  }
  
}