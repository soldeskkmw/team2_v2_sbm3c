package dev.mvc.customer_post;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE CUSTOMER_POST(
    SERVICENO NUMBER(10) NOT NULL PRIMARY KEY,
    SERVICECATENO NUMERIC(10),
    MEMBERNO NUMBER(10) NOT NULL,
    SERVICETITLE VARCHAR2(50) NOT NULL,
    SERVICECONTENTS VARCHAR2(500) NOT NULL,
    SERVICEPASSWD NUMBER(10) NOT NULL,
    SERVICEVISIBLE CHAR(1) NOT NULL,
    RDATE DATE NOT NULL,
    UDATE DATE,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (SERVICECATENO) REFERENCES SERVICECATE (SERVICECATENO)
);
*/

public class Customer_postVO {
  int serviceno;
  int servicecateno;
  int memberno;
  String servicetitle;
  String servicecontents;
  String servicevisible;
  String rdate;
  String udate;

  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지 */
  private String file1saved = "";
  /** 메인 이미지 preview */
  private String thumb1 = "";
  /** 메인 이미지 크기 */
  private long size1;
  /**
   * 이미지 파일
   * <input type='file' class="form-control" name='file1MF' id='file1MF' value=''
   * placeholder="파일 선택">
   */
  private MultipartFile file1MF;

  /** 메인 이미지 크기 단위, 파일 크기 */
  private String size1_label = "";

  public int getServiceno() {
    return serviceno;
  }

  public void setServiceno(int serviceno) {
    this.serviceno = serviceno;
  }

  public int getServicecateno() {
    return servicecateno;
  }

  public void setServicecateno(int servicecateno) {
    this.servicecateno = servicecateno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getServicetitle() {
    return servicetitle;
  }

  public void setServicetitle(String servicetitle) {
    this.servicetitle = servicetitle;
  }

  public String getServicecontents() {
    return servicecontents;
  }

  public void setServicecontents(String servicecontents) {
    this.servicecontents = servicecontents;
  }

  public String getServicevisible() {
    return servicevisible;
  }

  public void setServicevisible(String servicevisible) {
    this.servicevisible = servicevisible;
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

  public String getFile1() {
    return file1;
  }

  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public String getFile1saved() {
    return file1saved;
  }

  public void setFile1saved(String file1saved) {
    this.file1saved = file1saved;
  }

  public String getThumb1() {
    return thumb1;
  }

  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }

  public long getSize1() {
    return size1;
  }

  public void setSize1(long size1) {
    this.size1 = size1;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public String getSize1_label() {
    return size1_label;
  }

  public void setSize1_label(String size1_label) {
    this.size1_label = size1_label;
  }

  @Override
  public String toString() {
    return "Customer_postVO [serviceno=" + serviceno + ", servicecateno=" + servicecateno + ", memberno=" + memberno
        + ", servicetitle=" + servicetitle + ", servicecontents=" + servicecontents + ", servicevisible="
        + servicevisible + ", rdate=" + rdate + ", udate=" + udate + ", file1=" + file1 + ", file1saved=" + file1saved
        + ", thumb1=" + thumb1 + ", size1=" + size1 + ", file1MF=" + file1MF + ", size1_label=" + size1_label + "]";
  }
  
  
}
