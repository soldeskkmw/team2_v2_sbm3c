package dev.mvc.admin_reply;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE ADMIN_REPLY(
    ADMINREPLYNO                      NUMBER(10)     NOT NULL    PRIMARY KEY,
    SERVICENO                         NUMBER(10)     NOT NULL,
    ADMINNO                           NUMBER(10)     NOT NULL,
    ADMINREPLYTITLE                   VARCHAR2(50)     NOT NULL,
    ADMINREPLYCONTENT                 VARCHAR2(500)    NOT NULL,
    ADMINREPLYVISIBLE                 CHAR(1)    NOT NULL,
    RDATE                             DATE     NOT NULL,
    UDATE                             DATE     NULL ,
  FOREIGN KEY (SERVICENO) REFERENCES CUSTOMER_POST (SERVICENO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);
 */

public class Admin_replyVO {
 int adminreplyno;
 int serviceno;
 int adminno;
 String adminreplytitle;
 String adminreplycontent;
 String adminreplyvisible;
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
 이미지 파일
 <input type='file' class="form-control" name='file1MF' id='file1MF' 
            value='' placeholder="파일 선택">
 */
private MultipartFile file1MF;
 
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
public int getAdminreplyno() {
  return adminreplyno;
  }
  public void setAdminreplyno(int adminreplyno) {
    this.adminreplyno = adminreplyno;
  }
  public int getServiceno() {
    return serviceno;
  }
  public void setServiceno(int serviceno) {
    this.serviceno = serviceno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getAdminreplytitle() {
    return adminreplytitle;
  }
  public void setAdminreplytitle(String adminreplytitle) {
    this.adminreplytitle = adminreplytitle;
  }
  public String getAdminreplycontent() {
    return adminreplycontent;
  }
  public void setAdminreplycontent(String adminreplycontent) {
    this.adminreplycontent = adminreplycontent;
  }
  public String getAdminreplyvisible() {
    return adminreplyvisible;
  }
  public void setAdminreplyvisible(String adminreplyvisible) {
    this.adminreplyvisible = adminreplyvisible;
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
}
