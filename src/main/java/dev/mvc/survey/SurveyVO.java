package dev.mvc.survey;

//CREATE TABLE survey(
//    surveyno                          NUMBER(8)    NOT NULL    PRIMARY KEY,
//    surveytopic                         VARCHAR2(100)    NOT NULL,
//    yn                                VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
//    rdate                             DATE     NOT NULL,
//    adminno                             NUMBER(10)          NOT NULL,
//        FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
//);

public class SurveyVO {
  /** 설문 번호 */
  private int surveyno;
  
  /** 설문 주제 */
  private String surveytopic = "";
  
  /** 여부 */
  private String yn = "";
  
  /** 등록일 */
  private String rdate = "";
  
  /**관리자번호 */
  private int adminno;
  
  public int getSurveyno() {
    return surveyno;
  }
  public void setSurveyno(int surveyno) {
    this.surveyno = surveyno;
  }
  
  public String getSurveytopic() {
    return surveytopic;
  }
  public void setSurveytopic(String surveytopic) {
    this.surveytopic = surveytopic;
  }
  
  public String getYn() {
    return yn;
  }
  public void setYn(String yn) {
    this.yn = yn;
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
