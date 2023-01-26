package dev.mvc.member;
  
/*
    MEMBERNO                        NUMBER(10)             NOT NULL    PRIMARY KEY,
    MEMBERID                          VARCHAR2(20)         NOT NULL,  -- 회원 아이디
    MEMBERPASSWD                 VARCHAR2(60)         NOT NULL,  -- 회원 비밀번호
    MEMBERNAME                     VARCHAR2(30)         NOT NULL,  -- 회원 이름
    TEL                                      VARCHAR2(14)         NOT NULL,  -- 전화번호
    RECEIVER                             VARCHAR2(30)         NOT NULL,  -- 회원 이메일
    GRADE                                 NUMBER(2)              NOT NULL, --(1~10: 회원 관리자, 11~20: 회원, 30~39: 정지 회원: 40~49: 탈퇴 회원)
    GENDER                               CHAR(1)                   NOT NULL, -- 성별(남 : M, 여 : W)
    AGE                                     NUMBER(2)               NOT NULL,  -- 나이
    MDATE                                DATE                        NOT NULL
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
    /** 이메일 */
    private String receiver = "";
    /** 가입일 */
    private String mdate = "";
    /** 등급 */
    private int grade = 0;
    /** 성별, 기본값 null */
    private String gender = "";
    /** 나이 */
    private String age = "";
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
    public String getReceiver() {
      return receiver;
    }
    public void setReceiver(String receiver) {
      this.receiver = receiver;
    }
    public String getMdate() {
      return mdate;
    }
    public void setMdate(String mdate) {
      this.mdate = mdate;
    }
    public int getGrade() {
      return grade;
    }
    public void setGrade(int grade) {
      this.grade = grade;
    }
    public String getGender() {
      return gender;
    }
    public void setGender(String gender) {
      this.gender = gender;
    }
    public String getAge() {
      return age;
    }
    public void setAge(String age) {
      this.age = age;
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
