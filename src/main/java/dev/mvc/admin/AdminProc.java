package dev.mvc.admin;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.admin.AdminProc") // Autowired annotation에 사용하는 이름
public class AdminProc implements AdminProcInter {
  @Autowired
  private AdminDAOInter adminDAO; // 스프링이 자동 구현한 DAO 객체 자동 할당

  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.adminDAO.login(map);
    return cnt;
  }

  @Override
  public AdminVO readById(String adminid) {
    AdminVO adminVO = this.adminDAO.readById(adminid);
    return adminVO;
  }

  @Override
  public boolean isAdmin(HttpSession session) {
    boolean admin_sw = false; // 로그인하지 않은 것으로 초기화
    if (session != null) {
      String adminid = (String)session.getAttribute("adminid");
      if (adminid != null){ // 관리자
        admin_sw = true;  // 로그인 한 경우
      }
    }
    
    return admin_sw;
  }  
  
}