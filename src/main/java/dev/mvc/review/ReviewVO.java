package dev.mvc.review;

import org.springframework.web.multipart.MultipartFile;

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
public class ReviewVO {
 /**리뷰 번호 */
  private int reviewno;
 /**회원 번호*/
  private int memberno;
  /**관광지 데이터 번호*/
  private int postno;
  /**리뷰 제목*/
  private String reviewtitle="";
  /**리뷰 내용*/
  private String reviewcontent="";
  /**별점*/
  private int reviewstar;
  /**좋아요수*/
  private int goodcnt;
  /**댓글수*/
  private int replycnt;
  /**리뷰 조회수*/
  private int cnt;
  /**리뷰 검색어*/
  private String reviewword;
  /**등록일*/
  private String rdate="";
  /**수정일*/
  private String udate="";
  /**리뷰 메인 이미지*/
  private String reviewfile1="";
  /***/
  private String reviewfile1saved="";
  /***/
  private String reviewthumb1="";
  /***/
  private long reviewsize1;
  private String reviewsize1_label = "";
  
  private MultipartFile reviewfile1MF;
  
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
  public int getPostno() {
    return postno;
  }
  public void setPostno(int postno) {
    this.postno = postno;
  }
  public String getReviewtitle() {
    return reviewtitle;
  }
  public void setReviewtitle(String reviewtitle) {
    this.reviewtitle = reviewtitle;
  }
  public String getReviewcontent() {
    return reviewcontent;
  }
  public void setReviewcontent(String reviewcontent) {
    this.reviewcontent = reviewcontent;
  }

  public int getReplycnt() {
    return replycnt;
  }
  public void setReplycnt(int replycnt) {
    this.replycnt = replycnt;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getReviewword() {
    return reviewword;
  }
  public void setReviewword(String reviewword) {
    this.reviewword = reviewword;
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
  public String getReviewfile1() {
    return reviewfile1;
  }
  public void setReviewfile1(String reviewfile1) {
    this.reviewfile1 = reviewfile1;
  }
  public String getReviewfile1saved() {
    return reviewfile1saved;
  }
  public void setReviewfile1saved(String reviewfile1saved) {
    this.reviewfile1saved = reviewfile1saved;
  }
  public String getReviewthumb1() {
    return reviewthumb1;
  }
  public void setReviewthumb1(String reviewthumb1) {
    this.reviewthumb1 = reviewthumb1;
  }
  public long getReviewsize1() {
    return reviewsize1;
  }
  public void setReviewsize1(long reviewsize1) {
    this.reviewsize1 = reviewsize1;
  }
  public int getGoodcnt() {
    return goodcnt;
  }
  public void setGoodcnt(int goodcnt) {
    this.goodcnt = goodcnt;
  }
  public int getReviewstar() {
    return reviewstar;
  }
  public void setReviewstar(int reviewstar) {
    this.reviewstar = reviewstar;
  }
  public String getReviewsize1_label() {
    return reviewsize1_label;
  }
  public void setReviewsize1_label(String reviewsize1_label) {
    this.reviewsize1_label = reviewsize1_label;
  }
  public MultipartFile getReviewfile1MF() {
    return reviewfile1MF;
  }
  public void setReviewfile1MF(MultipartFile reviewfile1mf) {
    reviewfile1MF = reviewfile1mf;
  }
  

}
