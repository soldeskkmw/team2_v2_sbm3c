package dev.mvc.post;

import org.springframework.web.multipart.MultipartFile;

/*
    SELECT a.adminno, a.adminid, a.adminpasswd, a.adminname,
              p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1
    FROM admin a, post p
    WHERE a.adminno = p.adminno;
*/

public class AdminPostVO {
  /** 관광지 데이터 번호 */
  private int postno;
  
  /** 관리자 번호 */
  private int adminno;
  /** 아이디 */
  private String adminid = "";
  /** 패스워드 */
  private String adminpasswd = "";
  /** 관리자 성명 */
  private String adminname = "";
  
  /** 게시글 제목 */
  private String posttitle = "";
  /** 게시글 내용 */
  private String postcontent = "";
  /** 게시글 검색어 */
  private String postword = "";
  /** 등록일 */
  private String rdate = "";
  /** 수정일 */
  private String udate = "";
  /** 별점 */
  private int poststar;
  /** 게시글 조회수 */
  private int postcnt = 0;
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
  public String getAdminid() {
    return adminid;
  }
  public void setAdminid(String adminid) {
    this.adminid = adminid;
  }
  public String getAdminpasswd() {
    return adminpasswd;
  }
  public void setAdminpasswd(String adminpasswd) {
    this.adminpasswd = adminpasswd;
  }
  public String getAdminname() {
    return adminname;
  }
  public void setAdminname(String adminname) {
    this.adminname = adminname;
  }
  public String getPosttitle() {
    return posttitle;
  }
  public void setPosttitle(String posttitle) {
    this.posttitle = posttitle;
  }
  public String getPoscontent() {
    return postcontent;
  }
  public void setPoscontent(String poscontent) {
    this.postcontent = poscontent;
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
