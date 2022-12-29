package dev.mvc.cate;

/*
CREATE TABLE CATE(
      cateno                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      catename                            VARCHAR2(30)       NOT NULL,
      rdate                               DATE     NOT NULL,
      udate                             DATE     NULL,
      seqno                            NUMBER(10)   DEFAULT 0       NOT NULL,
      visible                            CHAR(1)      DEFAULT 'N'     NOT NULL -- Y, N
);
*/

public class CateVO {
  /** 카테고리 번호 */
  private int cateno;  
  /** 카테고리 이름 */
  private String catename;
  /** 등록일 */
  private String rdate;
  /** 변경일 */
  private String udate;
  /** 출력 순서, 기본값 0 */
  private int seqno;
  /** 출력 모드, 기본값 null */
  private String visible;
  
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  public String getCatename() {
    return catename;
  }
  public void setCatename(String catename) {
    this.catename = catename;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  @Override
  public String toString() {
    return "CateVO [cateno=" + cateno + ", catename=" + catename + ", rdate=" + rdate +"]";
  }
  
  public String getUdate() {
    return udate;
  }
  public void setUdate(String udate) {
    this.udate = udate;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  
  
}

