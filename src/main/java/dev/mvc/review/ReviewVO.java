package dev.mvc.review;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE REVIEW(
      REVIEWNO                            NUMBER(10)          NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)          NOT NULL ,
      CATENO                              NUMBER(10)          NOT NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB                NOT NULL,
      REPLYCNT                            NUMBER(10)          NULL,
      REVIEWCNT                           NUMBER(30)          DEFAULT 0    NOT NULL,
      REVIEWWORD                          VARCHAR2(400)       NULL ,
      REVIEWRDATE                         DATE                NOT NULL,
      REVIEWUDATE                         DATE                NULL ,
      REVIEWFILE1                         VARCHAR2(200)       NULL ,
      REVIEWFILE1SAVED                    VARCHAR2(400)       NULL ,
      REVIEWTHUMB1                        VARCHAR2(200)       NULL ,
      REVIEWSIZE1                         NUMBER(10)          NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);
 */
public class ReviewVO {
 /**리뷰 번호 */
  private int reviewno;
 /**회원 번호*/
  private int memberno;
  /** 회원 성명 */
  private String membername = ""; 
  /**관광지 데이터 번호*/
  private int cateno;
  /**리뷰 제목*/
  private String reviewtitle="";
  /**리뷰 내용*/
  private String reviewcontent="";
  /**댓글수*/
  private int replycnt;
  /**리뷰 조회수*/
  private int reviewcnt;
  /**리뷰 검색어*/
  private String reviewword="";
  /**리뷰 등록일*/
  private String reviewrdate;
  /**리뷰 수정일*/
  private String reviewudate;
  /**리뷰 메인 이미지*/
  private String reviewfile1="";
  /**리뷰 실제 저장된 메인 이미지*/
  private String reviewfile1saved="";
  /**리뷰 메인 이미지 Preview*/
  private String reviewthumb1="";
  /**리뷰 메인 이미지 크기*/
  private long reviewsize1;
  /**
  이미지 파일
  <input type='file' class="form-control" name='reviewfile1MF' id='reviewfile1MF' 
             value='' placeholder="파일 선택">
  */
  private MultipartFile reviewfile1MF;
  /**메인 이미지 크기 단위, 파일 크기*/
  private String reviewsize1_label = "";
  
  
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
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
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
  public int getReviewcnt() {
    return reviewcnt;
  }
  public void setReviewcnt(int reviewcnt) {
    this.reviewcnt = reviewcnt;
  }
  public String getReviewword() {
    return reviewword;
  }
  public void setReviewword(String reviewword) {
    this.reviewword = reviewword;
  }
  public String getReviewrdate() {
    return reviewrdate;
  }
  public void setReviewrdate(String reviewrdate) {
    this.reviewrdate = reviewrdate;
  }
  public String getReviewudate() {
    return reviewudate;
  }
  public void setReviewudate(String reviewudate) {
    this.reviewudate = reviewudate;
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
  public MultipartFile getReviewfile1MF() {
    return reviewfile1MF;
  }
  public void setReviewfile1MF(MultipartFile reviewfile1mf) {
    reviewfile1MF = reviewfile1mf;
  }
  public String getReviewsize1_label() {
    return reviewsize1_label;
  }
  public void setReviewsize1_label(String reviewsize1_label) {
    this.reviewsize1_label = reviewsize1_label;
  }
  public String getMembername() {
    return membername;
  }
  public void setMembername(String membername) {
    this.membername = membername;
  }

}
