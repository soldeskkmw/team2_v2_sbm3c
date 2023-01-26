package dev.mvc.email;

/*
    EMAILNO                             NUMBER(10)             NOT NULL          PRIMARY KEY,
    MEMBERNO                         NUMBER(10)             NULL,
    ADMINNO                           NUMBER(10)              NULL,
    IP                                       VARCHAR2(20)           NOT NULL,
    AUTHNO                             VARCHAR2(10)           NOT NULL,
    MDATE                               DATE                         NOT NULL,
    SEARCH                              VARCHAR2(20)           NOT NULL,
    RECEIVER                            VARCHAR2(30)           NOT NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
     FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
*/

public class EmailVO {

  /** 이메일 번호 */
  private int emailno;
  /** 회원 번호 */
  private int memberno;
  /** 관리자 번호 */
  private int adminno;
  /** 아이피 */
  private String ip = "";
  /** 인증 번호 */
  private String authno = "";
  /** 생성 날짜 */
  private String mdate = "";
  /** 찾기 */
  private String search = "";
  /** 찾기 */
  private String receiver = "";
  public int getEmailno() {
    return emailno;
  }
  public void setEmailno(int emailno) {
    this.emailno = emailno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getAuthno() {
    return authno;
  }
  public void setAuthno(String authno) {
    this.authno = authno;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getSearch() {
    return search;
  }
  public void setSearch(String search) {
    this.search = search;
  }
  public String getReceiver() {
    return receiver;
  }
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
  
  
}
