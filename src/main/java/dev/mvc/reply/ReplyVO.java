package dev.mvc.reply;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE REPLY(
<<<<<<< HEAD
    REPLYNO                           NUMBER(10)     NOT NULL        PRIMARY KEY,
    MEMBERNO                          NUMBER(10)     NOT NULL,
    REVIEWNO                          NUMBER(10)     NOT NULL,
    REPLYCONTENT                      VARCHAR2(400)  NOT NULL,
    REPLYRDATE                          DATE         NOT NULL,
    REPLYUDATE                          DATE         NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
     FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);
SELECT m.membername, m.memberpasswd,
        r.reviewno, r.memberno, r.cateno, r.reviewtitle, r.reviewcontent, r.replycnt, r.reviewcnt, r.reviewword,
        r.reviewrdate, r.reviewudate, r.reviewfile1, r.reviewfile1saved, r.reviewthumb1, r.reviewsize1,
        p.replyno, p.memberno as replymemberno, p.replycontent, p.replyrdate, p.replyudate
FROM member m, review r, reply p
WHERE r.reviewno = p.reviewno AND r.reviewno = 1 AND r.cateno = 6 AND m.memberno=r.memberno
 */
public class ReplyVO {
  /** 회원 비밀번호 */
  private String memberpasswd = "";
  /** 회원 성명 */
  private String membername = "";
=======
    REPLYNO                           NUMBER(10)     NOT NULL       PRIMARY KEY,
    MEMBERNO                          NUMBER(10)     NOT NULL,
    REVIEWNO                          NUMBER(10)     NOT NULL,
    REPLYCONTENT                      VARCHAR2(400)    NOT NULL,
    REPLYRDATE                             DATE     NOT NULL,
    REPLYUDATE                             DATE     NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

  <!-- 리뷰 조회 + 리뷰당 전체 댓글 목록 -->
  <select id="read_by_reviewno_reply" resultType="dev.mvc.reply.ReplyVO" parameterType="int">   
    SELECT r.reviewno, r.memberno, r.cateno, r.reviewtitle, r.reviewcontent, r.reviewcnt, r.reviewword, r.rdate, r.udate, r.reviewfile1, r.reviewfile1saved, r.reviewthumb1, r.reviewsize1,
              p.replyno, p.replycontent, p.replyrdate, p.replyudate,
              m.memberid
    FROM review r, reply p, member m
    WHERE r.reviewno = p.reviewno AND r.cateno=#{cateno} AND r.memberno = m.memberno
    ORDER BY replyno ASC
  </select>
  
 */
public class ReplyVO {
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  /**리뷰 번호 */
  private int reviewno;
 /**회원 번호*/
  private int memberno;
<<<<<<< HEAD
  /**관광지 데이터 번호*/
=======
  /**관광지 카테고리 번호*/
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  private int cateno;
  /**리뷰 제목*/
  private String reviewtitle="";
  /**리뷰 내용*/
  private String reviewcontent="";
  /**댓글수*/
<<<<<<< HEAD
  private int replycnt;
  /**리뷰 조회수*/
  private int reviewcnt;
  /**리뷰 검색어*/
  private String reviewword="";
  /**리뷰 등록일*/
  private String reviewrdate;
  /**리뷰 수정일*/
  private String reviewudate;
=======
  private int reviewcnt;
  /**리뷰 검색어*/
  private String reviewword = "";
  /**등록일*/
  private String rdate;
  /**수정일*/
  private String udate;
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  /**리뷰 메인 이미지*/
  private String reviewfile1="";
  /**리뷰 실제 저장된 메인 이미지*/
  private String reviewfile1saved="";
<<<<<<< HEAD
  /**리뷰 메인 이미지 Preview*/
  private String reviewthumb1="";
  /**리뷰 메인 이미지 크기*/
  private long reviewsize1;
  private MultipartFile reviewfile1MF;
  /**메인 이미지 크기 단위, 파일 크기*/
  private String reviewsize1_label = "";
  /**댓글 번호 */
=======
  /**리뷰 메인 이미지 Preview */
  private String reviewthumb1="";
  /**리뷰 메인 이미지 크기*/
  private long reviewsize1;
  /**댓글 번호*/
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  private int replyno;
  /**댓글 내용*/
  private String replycontent="";
  /**댓글 등록일*/
  private String replyrdate;
  /**댓글 수정일*/
  private String replyudate;
<<<<<<< HEAD
  /**댓글 단 회원 번호*/
  private int replymemberno;
=======
  /** 회원 아이디 */
  private String memberid = "";
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  
  
  
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
<<<<<<< HEAD
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
  public int getReplyno() {
    return replyno;
=======
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
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
  public String getReplyrdate() {
    return replyrdate;
  }
  public void setReplyrdate(String replyrdate) {
    this.replyrdate = replyrdate;
  }
  public String getReplyudate() {
    return replyudate;
  }
  public void setReplyudate(String replyudate) {
    this.replyudate = replyudate;
  }
<<<<<<< HEAD
  public int getReplymemberno() {
    return replymemberno;
  }
  public void setReplymemberno(int replymemberno) {
    this.replymemberno = replymemberno;
=======
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
  public String getReplyrdate() {
    return replyrdate;
  }
  public void setReplyrdate(String replyrdate) {
    this.replyrdate = replyrdate;
  }
  public String getReplyudate() {
    return replyudate;
  }
  public void setReplyudate(String replyudate) {
    this.replyudate = replyudate;
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  }
  @Override
  public String toString() {
    return "ReplyVO [replyno=" + replyno + ", replycontent=" + replycontent + ", replyrdate=" + replyrdate +"]";
  }
<<<<<<< HEAD
  
=======
  public void setMemberid(String memberid) {
    this.memberid = memberid;
  }

>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
}


