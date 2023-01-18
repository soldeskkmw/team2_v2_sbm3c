package dev.mvc.post_ratings;

/*
    SELECT m.memberno, m.memberid, m.memberpasswd, m.membername, m.tel, m.mdate,
    r.ratingno, r.memberno, r.postno, r.ratings, r.rdate
    FROM member m, post_ratings r
    WHERE m.memberno = r.memberno;
*/
public class MemberPost_ratingsVO {
  /** 회원 번호 */
  public int memberno;
  
  /** 회원 아이디 */
  private String memberid = "";
  /** 회원 비밀번호 */
  private String memberpasswd = "";
  /** 회원 성명 */
  private String membername = "";
  /** 전화 번호 */
  private String tel = "";
  /** 가입일 */
  private String mdate = "";
  /** 등록된 회원 비밀번호 */
  private String old_passwd = "";
  /** 회원 id 저장 여부 */
  private String id_save = "";
  /** 회원 passwd 저장 여부 */
  private String passwd_save = "";
  
  /** 평점 번호 */
  private int ratingno;
  /** 관광지 데이터 번호 */
  private int postno;
  /** 평점 */
  private int ratings;
  /** 등록일 */
  private String rdate;
  
  
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getMemberid() {
    return memberid;
  }
  public void setMemberid(String memberid) {
    this.memberid = memberid;
  }
  public String getMemberpasswd() {
    return memberpasswd;
  }
  public void setMemberpasswd(String memberpasswd) {
    this.memberpasswd = memberpasswd;
  }
  public String getMembername() {
    return membername;
  }
  public void setMembername(String membername) {
    this.membername = membername;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getOld_passwd() {
    return old_passwd;
  }
  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }
  public String getId_save() {
    return id_save;
  }
  public void setId_save(String id_save) {
    this.id_save = id_save;
  }
  public String getPasswd_save() {
    return passwd_save;
  }
  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }
  public int getRatingno() {
    return ratingno;
  }
  public void setRatingno(int ratingno) {
    this.ratingno = ratingno;
  }
  public int getPostno() {
    return postno;
  }
  public void setPostno(int postno) {
    this.postno = postno;
  }
  public int getRatings() {
    return ratings;
  }
  public void setRatings(int ratings) {
    this.ratings = ratings;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

}
