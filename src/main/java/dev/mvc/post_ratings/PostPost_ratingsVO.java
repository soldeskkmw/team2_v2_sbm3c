package dev.mvc.post_ratings;

import org.springframework.web.multipart.MultipartFile;

/*
    SELECT p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1,
    r.ratingno, r.memberno, r.postno, r.ratings, r.rdate
    FROM post p, post_ratings r
    WHERE p.postno = r.postno;
*/

public class PostPost_ratingsVO {
  /** 관광지 데이터 번호 */
  private int postno;
  
  /** 등록일 */
  private String rdate;
  
  /** 관리자 번호 */
  private int adminno;
  /** 카테고리 번호 */
  private int cateno;  
  /** 게시글 제목 */
  private String posttitle = "";
  /** 게시글 내용 */
  private String postcontent = "";
  /** 게시글 검색어 */
  private String postword = "";
  /** 수정일 */
  private String udate;
  /** 평점 */
  private int poststar;
  /** 게시글 조회수 */
  private int postcnt;
  /** 게시글 메인 이미지 */
  private String postfile1 = "";
  /** 게시글 실제 저장된 메인 이미지 */
  private String postfile1saved = "";
  /** 게시글 메인 이미지 preview */
  private String postthumb1 = "";
  /** 게시글 메인 이미지 크기 */
  private long postsize1;
  private MultipartFile postfile1MF;
  /** 메인 이미지 크기 단위, 파일 크기 */
  private String postsize1_label = "";
  
  /** 평점 번호 */
  private int ratingno;
  /** 회원 번호 */
  private int memberno;  
  /** 평점 */
  private int ratings;
  
  
  public int getPostno() {
    return postno;
  }
  public void setPostno(int postno) {
    this.postno = postno;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  public String getPosttitle() {
    return posttitle;
  }
  public void setPosttitle(String posttitle) {
    this.posttitle = posttitle;
  }
  public String getPostcontent() {
    return postcontent;
  }
  public void setPostcontent(String postcontent) {
    this.postcontent = postcontent;
  }
  public String getPostword() {
    return postword;
  }
  public void setPostword(String postword) {
    this.postword = postword;
  }
  public String getUdate() {
    return udate;
  }
  public void setUdate(String udate) {
    this.udate = udate;
  }
  public int getPoststar() {
    return poststar;
  }
  public void setPoststar(int poststar) {
    this.poststar = poststar;
  }
  public int getPostcnt() {
    return postcnt;
  }
  public void setPostcnt(int postcnt) {
    this.postcnt = postcnt;
  }
  public String getPostfile1() {
    return postfile1;
  }
  public void setPostfile1(String postfile1) {
    this.postfile1 = postfile1;
  }
  public String getPostfile1saved() {
    return postfile1saved;
  }
  public void setPostfile1saved(String postfile1saved) {
    this.postfile1saved = postfile1saved;
  }
  public String getPostthumb1() {
    return postthumb1;
  }
  public void setPostthumb1(String postthumb1) {
    this.postthumb1 = postthumb1;
  }
  public long getPostsize1() {
    return postsize1;
  }
  public void setPostsize1(long postsize1) {
    this.postsize1 = postsize1;
  }
  public MultipartFile getPostfile1MF() {
    return postfile1MF;
  }
  public void setPostfile1MF(MultipartFile postfile1mf) {
    postfile1MF = postfile1mf;
  }
  public String getPostsize1_label() {
    return postsize1_label;
  }
  public void setPostsize1_label(String postsize1_label) {
    this.postsize1_label = postsize1_label;
  }
  public int getRatingno() {
    return ratingno;
  }
  public void setRatingno(int ratingno) {
    this.ratingno = ratingno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getRatings() {
    return ratings;
  }
  public void setRatings(int ratings) {
    this.ratings = ratings;
  }
  
}
