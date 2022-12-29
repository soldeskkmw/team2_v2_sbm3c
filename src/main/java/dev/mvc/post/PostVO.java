package dev.mvc.post;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE POST(
    POSTNO                              NUMBER(10)       NOT NULL       PRIMARY KEY,
    ADMINNO                             NUMBER(10)       NOT NULL,
    CATENO                              NUMBER(10)       NOT NULL,
    POSTTITLE                           VARCHAR2(400)       NOT NULL,
    POSTCONTENT                         CLOB       NOT NULL,
    POSTWORD                            VARCHAR2(400)       NULL ,
    RDATE                               DATE       NOT NULL,
    UDATE                               DATE       NULL ,
    POSTSTAR                            NUMBER(2,1)       NOT NULL,         -- 별점
    POSTCNT                             NUMBER(30)       DEFAULT 0    NOT NULL,       -- 게시글 조회수
    POSTFILE1                           VARCHAR2(200)       NULL ,          -- 원본 파일명 image
    POSTFILE1SAVED                      VARCHAR2(400)       NULL ,      -- 저장된 파일명, image
    POSTTHUMB1                          VARCHAR2(200)       NULL ,      -- preview image
    POSTSIZE1                           NUMBER(10)   DEFAULT 0    NULL ,              -- 파일 사이즈
FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);
*/
public class PostVO {
  /** 관광지 데이터 번호 */
  private int postno;
  
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
  /** 등록일 */
  private String rdate;
  /** 수정일 */
  private String udate;
  /** 별점 */
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
  /**
  이미지 파일
  <input type='file' class="form-control" name='postfile1MF' id='postfile1MF' 
             value='' placeholder="파일 선택">
  */
   private MultipartFile postfile1MF;
   /** 메인 이미지 크기 단위, 파일 크기 */
   private String postsize1_label = "";
   
    public int getPostno() {
      return postno;
    }
    public void setPostno(int postno) {
      this.postno = postno;
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
   
}
