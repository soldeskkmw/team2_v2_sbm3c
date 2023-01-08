package dev.mvc.reply;

/*
   REVIEWNO                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)       NULL ,
      POSTNO                              NUMBER(10)       NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB       NOT NULL,
      REVIEWGOOD                          CHAR(1)       DEFAULT 'N'       NOT NULL,
      REPLYCNT                            NUMBER(10)       NOT NULL,
      CNT                                 NUMBER(30)       NOT NULL,
      REVIEWWORD                          VARCHAR2(400)       NULL ,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
      REVIEWFILE1                         VARCHAR2(200)       NULL ,
      REVIEWFILE1SAVED                    VARCHAR2(400)       NULL ,
      REVIEWTHUMB1                        VARCHAR2(200)       NULL ,
      REVIEWSIZE1                         NUMBER(10)       NULL ,
 */
public class ReplyVO {
 /**리뷰 번호 */
  private int reviewno;
 /**회원 번호*/
  private int memberno;
  /**댓글 번호*/
  private int replyno;
  /**댓글 내용*/
  private String replycontent="";
  /**등록일*/
  private String rdate="";
  /**수정일*/
  private String udate="";
  /**좋아요*/
  private String reviewgood="N";
  
  public int getReviewno() {
    return reviewno;
  }
  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getReplyno() {
    return replyno;
  }
  public void setReplyno(int replyno) {
    this.replyno = replyno;
  }
  public String getReplycontent() {
    return replycontent;
  }
  public void setReplycontent(String replycontent) {
    this.replycontent = replycontent;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getUdate() {
    return udate;
  }
  public void setUdate(String udate) {
    this.udate = udate;
  }
  public String getReviewgood() {
    return reviewgood;
  }
  public void setReviewgood(String reviewgood) {
    this.reviewgood = reviewgood;
  }

  

}
