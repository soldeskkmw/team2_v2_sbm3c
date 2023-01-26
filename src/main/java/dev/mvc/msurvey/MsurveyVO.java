package dev.mvc.msurvey;

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
