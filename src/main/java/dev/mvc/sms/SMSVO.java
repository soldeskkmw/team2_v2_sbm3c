package dev.mvc.sms;

/*
SMSNO                                NUMBER(10)       NOT NULL    PRIMARY KEY,
IP                                        VARCHAR2(20)     NOT NULL,
AUTHNO                              NUMBER(10)       NOT NULL,
MDATE                                DATE                 NOT NULL,
MEMBERNO                          NUMBER(10)       NULL,
FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
*/

public class SMSVO {
  
  /** 문자 번호 */
  private int smsno;
  /** 아이피 */
  private String ip = "";
  /** 인증 번호 */
  private String authno = "";
  /** 생성 날짜 */
  private String mdate = "";
  /** 회원 번호 */
  private int memberno;
  
  public int getSmsno() {
    return smsno;
  }
  public void setSmsno(int smsno) {
    this.smsno = smsno;
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
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
 
  

  
}
