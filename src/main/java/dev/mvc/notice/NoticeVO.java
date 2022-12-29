package dev.mvc.notice;

/*
CREATE TABLE notice(
noticeno                         NUMBER(10)  NOT NULL  PRIMARY KEY,
adminno NUMBER(10) NOT NULL, --FK
noticetitle VARCHAR2(300) NOT NULL,
noticecontent CLOB(4000) NOT NULL,
cnt NUMBER(7) NOT NULL,
word VARCHAR2(300) NOT NULL,
rdate DATE NOT NULL,
FOREIGN KEY (adminno) REFERENCES admin (adminno)
);
*/

public class NoticeVO {
  /**공지사항 번호*/
  private int noticeno;
  /**관리자 번호*/
  private int adminno;
  /**공지사항 제목*/
  private String noticetitle;
  /**공지사항 내용*/
  private String noticecontent;
  
  /**등록된 공지사항 수*/
  private int cnt;
  /**공지사항 검색어*/
  private String noticeword;
  /**공지사항 등록일*/
  private String rdate;
  
  public int getNoticeno() {
    return noticeno;
  }
  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getNoticetitle() {
    return noticetitle;
  }
  public void setNoticetitle(String noticetitle) {
    this.noticetitle = noticetitle;
  }
  public String getNoticecontent() {
    return noticecontent;
  }
  public void setNoticecontent(String noticecontent) {
    this.noticecontent = noticecontent;
  }
  public int getcnt() {
    return cnt;
  }
  public void setcnt(int cnt) {
    this.cnt = cnt;
  }
  public String getNoticeword() {
    return noticeword;
  }
  public void setNoticeword(String noticeword) {
    this.noticeword = noticeword;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
}