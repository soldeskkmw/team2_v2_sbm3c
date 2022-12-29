package dev.mvc.member;
  
/*
    MEMBERNO                          NUMBER(10)           NOT NULL      PRIMARY KEY,
    MEMBERID                            VARCHAR2(20)        NOT NULL,
    MEMBERPASSWD                   VARCHAR2(60)        NOT NULL,
    MEMBERNAME                      VARCHAR2(30)        NOT NULL,
    TEL                                     VARCHAR2(14)        NOT NULL,
    MDATE                                DATE                    NOT NULL
*/
public class MemberVO {
    

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
    
    
    
    
    
}
