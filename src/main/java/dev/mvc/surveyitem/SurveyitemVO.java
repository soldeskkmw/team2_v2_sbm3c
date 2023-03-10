package dev.mvc.surveyitem;

/*
CREATE TABLE surveyitem(
    surveyitemno                   NUMBER(8)  NOT NULL  PRIMARY KEY,
    surveyno                       NUMBER(8)  DEFAULT 0  NOT NULL,
    surveyitem                     VARCHAR2(200)  NOT NULL,
    cnt                            NUMBER(8)  DEFAULT 0  NOT NULL,
    rdate                          DATE  NOT NULL,
    adminno                             NUMBER(10)          NOT NULL,
    FOREIGN KEY (adminno) REFERENCES admin (adminno),
    FOREIGN KEY (surveyno) REFERENCES survey (surveyno)
);
 */
public class SurveyitemVO {
    /** 설문 항목 번호 */
    private int surveyitemno;
    /** 설문 제목 번호 */
    private int surveyno;
    /** 항목 내용 */
    private String surveyitem = "";
    /** 선택 카운트 */
    private int cnt;
    /** 등록날짜 */
    private String rdate = "";    
    /**관리자번호 */
    private int adminno;

    public SurveyitemVO() { 
        
    }

    public int getSurveyitemno() {
        return surveyitemno;
    }

    public void setSurveyitemno(int surveyitemno) {
        this.surveyitemno = surveyitemno;
    }
    
    public int getSurveyno() {
      return surveyno;
    }

    public void setSurveyno(int surveyno) {
      this.surveyno = surveyno;
    }
    
    public String getSurveyitem() {
      return surveyitem;
    }

    public void setSurveyitem(String surveyitem) {
      this.surveyitem = surveyitem;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
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
