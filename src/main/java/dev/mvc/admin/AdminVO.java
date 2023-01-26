package dev.mvc.admin;

/*
      ADMINNO                            NUMBER(10)           NOT NULL           PRIMARY KEY,  -- 관리자 번호
      ADMINID                              VARCHAR2(20)       NOT NULL,  -- 관리자 아이디
      ADMINPASSWD                     VARCHAR2(20)       NOT NULL,  -- 관리자 비밀번호
      ADMINNAME                         VARCHAR2(20)       NOT NULL,  -- 관리자 이름
      ADMINTEL                             VARCHAR(14)         NOT NULL, -- 관리자 전화번호
      ADMINRECEIVER                    VARCHAR2(30)       NOT NULL, -- 관리자 이메일
      ZIPCODE                                VARCHAR(5)          NOT NULL, -- 우편번호, 12345
      ADDRESS1                             VARCHAR(80)         NOT NULL, -- 주소 1
      ADDRESS2                             VARCHAR(50)         NULL, -- 주소 2
      ADMINGRADE                        NUMBER(2)            NOT NULL, -- 관리자 등급(1~10: 메인 관리자, 11~20: 관리자, 21~29: 정지 관리자,31~40 탈퇴 관리자)
      ADMINGENDER                      CHAR(1)                 NOT NULL, -- 관리자 성별(남 : M, 여 : W)
      ADMINAGE                            NUMBER(2)            NOT NULL,  -- 나이
      PERMISSION                          CHAR(1)                 DEFAULT 'X'     NOT NULL, -- O, X  (가입 허가)
      MDATE                                  DATE                     NOT NULL -- 가입일
 */
public class AdminVO {
  
  /** 관리자 번호 */
  private int adminno;
  /** 관리자 아이디 */
  private String adminid = "";
  /** 관리자 비밀번호 */
  private String adminpasswd = "";
  /** 관리자 성명 */
  private String adminname = "";
  /** 관리자 전화 번호 */
  private String admintel = "";
  /** 관리자 이메일 */
  private String adminreceiver = "";
  /** 우편 번호 */
  private String zipcode = "";
  /** 주소 1 */
  private String address1 = "";
  /** 주소 2 */
  private String address2 = "";
  /** 관리자 등급 */
  private int admingrade = 0;
  /** 성별, 기본값 null */
  private String admingender = "";
  /** 나이 */
  private int adminage;
  /** 가입허가 */
  private String permission = "X";
  /** 가입일 */
  private String mdate = "";
  /** 관리자 id 저장 여부 */
  private String adminid_save = "";
  /** 관리자 passwd 저장 여부 */
  private String adminpasswd_save = "";
  
  
  
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
  public String getAdmintel() {
    return admintel;
  }
  public void setAdmintel(String admintel) {
    this.admintel = admintel;
  }
  public String getAdminreceiver() {
    return adminreceiver;
  }
  public void setAdminreceiver(String adminreceiver) {
    this.adminreceiver = adminreceiver;
  }
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  public int getAdmingrade() {
    return admingrade;
  }
  public void setAdmingrade(int admingrade) {
    this.admingrade = admingrade;
  }
  public String getAdmingender() {
    return admingender;
  }
  public void setAdmingender(String admingender) {
    this.admingender = admingender;
  }
  public int getAdminage() {
    return adminage;
  }
  public void setAdminage(int adminage) {
    this.adminage = adminage;
  }
  public String getPermission() {
    return permission;
  }
  public void setPermission(String permission) {
    this.permission = permission;
  }
  public String getMdate() {
    return mdate;
  }
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  public String getAdminid_save() {
    return adminid_save;
  }
  public void setAdminid_save(String adminid_save) {
    this.adminid_save = adminid_save;
  }
  public String getAdminpasswd_save() {
    return adminpasswd_save;
  }
  public void setAdminpasswd_save(String adminpasswd_save) {
    this.adminpasswd_save = adminpasswd_save;
  }

  
}
