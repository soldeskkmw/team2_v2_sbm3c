package dev.mvc.post_ratings;

/*
CREATE TABLE POST_RATINGS(
    RATINGNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    MEMBERNO                          NUMBER(10)     NOT NULL,
    POSTNO                            NUMBER(10)     NOT NULL,
    RATINGS                           NUMBER(3,2)    DEFAULT 1     NOT NULL,
    RDATE                             DATE     NOT NULL,
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/
public class Post_ratingsVO {
  /** 평점 번호 */
  private int ratingno;
  /** 회원 번호 */
  private int memberno;  
  /** 관광지 데이터 번호 */
  private int postno;
  /** 평점 */
  private float ratings;
  /** 등록일 */
  private String rdate;
  
  
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
  public int getPostno() {
    return postno;
  }
  public void setPostno(int postno) {
    this.postno = postno;
  }
  public float getRatings() {
    return ratings;
  }
  public void setRatings(float ratings) {
    this.ratings = ratings;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
}
