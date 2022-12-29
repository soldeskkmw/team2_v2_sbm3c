package dev.mvc.admin;

/*
    ADMINNO                          NUMBER(10)        NOT NULL    PRIMARY KEY,
    ADMINID                            VARCHAR2(20)     NOT NULL,
    ADMINPASSWD                   VARCHAR2(20)      NOT NULL,
    ADMINNAME                      VARCHAR2(20)      NOT NULL 
 
 */
public class AdminVO {
  
  /** 관리자 번호 */
  private int adminno;
  /** 관리자 아이디 */
  private String adminid = "";
  /** 관리자 비밀번호 */
  private String adminpasswd = "";
  /** 관리자 성명 */
  private String adminname = "";
  
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getAdminid() {
    return adminid;
  }
  public void setAdminid(String adminid) {
    this.adminid = adminid;
  }
  public String getAdminpasswd() {
    return adminpasswd;
  }
  public void setAdminpasswd(String adminpasswd) {
    this.adminpasswd = adminpasswd;
  }
  public String getAdminname() {
    return adminname;
  }
  public void setAdminname(String adminname) {
    this.adminname = adminname;
  }
  

   
  
}
