package dev.mvc.msurvey;

<<<<<<< HEAD
/*
CREATE TABLE msurvey(
    msurveyno                      NUMBER(8)  NOT NULL  PRIMARY KEY,
    surveyno                       NUMBER(8)  NULL ,
    MEMBERNO                       NUMBER(10)  NULL ,
    surveyitemno                   NUMBER(8)  NULL ,
    rdate                          DATE  NOT NULL,
    adminno                             NUMBER(10)          NOT NULL,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
  FOREIGN KEY (surveyitemno) REFERENCES surveyitem (surveyitemno),
  FOREIGN KEY (surveyno) REFERENCES survey (surveyno),
  FOREIGN KEY (MEMBERNO) REFERENCES member (MEMBERNO)
);
 */
public class MsurveyVO {
    /** 설문 참여 번호 */
    private int msurveyno;
    /** 설문 번호 */
    private int surveyno;
    /** 멤버 번호 */
    private int MEMBERNO;
    /** 항목 번호 */
    private int surveyitemno;
    /** 등록날짜 */
    private String rdate = "";   
    /**관리자번호 */
    private int adminno;

    public MsurveyVO() { 
        
    }

    public int getMsurveyno() {
        return msurveyno;
    }

    public void setMsurveyno(int msurveyno) {
        this.msurveyno = msurveyno;
    }
    
    public int getSurveyno() {
      return surveyno;
    }

    public void setSurveyno(int surveyno) {
      this.surveyno = surveyno;
    }
    
    public int getMEMBERNO() {
      return MEMBERNO;
    }

    public void setMEMBERNO(int MEMBERNO) {
      this.MEMBERNO = MEMBERNO;
    }

    public int getSurveyitemno() {
        return surveyitemno;
    }

    public void setSurveyitemno(int surveyitemno) {
        this.surveyitemno = surveyitemno;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public int getAdminno() {
      return adminno;
    }

    public void setAdminno(int adminno) {
      this.adminno = adminno;
    }
  
}
=======

//CREATE TABLE MSURVEY(
//    MSURVEYNO                         NUMBER(8)    NOT NULL    PRIMARY KEY,
//    SURVEYNO                          NUMBER(8)    NOT NULL ,
//    SURVEYANSWERNO                NUMBER(8)     NOT NULL,
//    MEMBERNO                          NUMBER(10)     NOT NULL ,
//    SURVEYITEMNO                      NUMBER(8)    NOT NULL ,
//    RDATE                             DATE     NOT NULL,
//    ADMINNO                           NUMBER(10)     NOT NULL,
//    FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),  
//  FOREIGN KEY (SURVEYNO) REFERENCES SURVEY (SURVEYNO),
//  FOREIGN KEY (SURVEYITEMNO) REFERENCES SURVEY (SURVEYITEMNO),
//  FOREIGN KEY (SURVEYANSWERNO) REFERENCES SURVEY (SURVEYANSWERNO),
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);

public class MsurveyVO {
  /**설문 참여 번호*/
  private int msurveyno;
  /**설문 제목 번호*/
  private int surveyno;
  /**회원 번호*/
  private int memberno;
  /**질문 1번 응답*/
  private String surveyanswer1;
  /**질문 2번 응답*/
  private String surveyanswer2;
  /**질문 3번 응답*/
  private String surveyanswer3;
  /**질문 4번 응답*/
  private String surveyanswer4;
  /**질문 5번 응답*/
  private String surveyanswer5;
  /**질문 6번 응답*/
  private String surveyanswer6;
  /**설문 응답 기타*/
  private String etc;
  
  
  public int getMsurveyno() {
    return msurveyno;
  }
  public void setMsurveyno(int msurveyno) {
    this.msurveyno = msurveyno;
  }
  public int getSurveyno() {
    return surveyno;
  }
  public void setSurveyno(int surveyno) {
    this.surveyno = surveyno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getSurveyanswer1() {
    return surveyanswer1;
  }
  public void setSurveyanswer1(String surveyanswer1) {
    this.surveyanswer1 = surveyanswer1;
  }
  public String getSurveyanswer2() {
    return surveyanswer2;
  }
  public void setSurveyanswer2(String surveyanswer2) {
    this.surveyanswer2 = surveyanswer2;
  }
  public String getSurveyanswer3() {
    return surveyanswer3;
  }
  public void setSurveyanswer3(String surveyanswer3) {
    this.surveyanswer3 = surveyanswer3;
  }
  public String getSurveyanswer4() {
    return surveyanswer4;
  }
  public void setSurveyanswer4(String surveyanswer4) {
    this.surveyanswer4 = surveyanswer4;
  }
  public String getSurveyanswer5() {
    return surveyanswer5;
  }
  public void setSurveyanswer5(String surveyanswer5) {
    this.surveyanswer5 = surveyanswer5;
  }
  public String getSurveyanswer6() {
    return surveyanswer6;
  }
  public void setSurveyanswer6(String surveyanswer6) {
    this.surveyanswer6 = surveyanswer6;
  }
  public String getEtc() {
    return etc;
  }
  public void setEtc(String etc) {
    this.etc = etc;
  }
  

  
  
  
}
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
