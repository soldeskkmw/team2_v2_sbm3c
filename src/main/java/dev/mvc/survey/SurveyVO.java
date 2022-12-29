package dev.mvc.survey;

//CREATE TABLE SURVEY(
//    SURVEYNO                          NUMBER(8)    NOT NULL    PRIMARY KEY,
//    SURVEYTOPIC                       VARCHAR2(100)    NOT NULL,
//    STARTDATE                         DATE     NOT NULL ,
//    ENDDATE                           DATE     NOT NULL ,
//    YN                                VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
//    RDATE                             DATE     NOT NULL
//);

public class SurveyVO {
  /**설문 제목 번호*/
  private int surveyno;
  /**설문 제목*/
  private String surveytopic;
  /**시작 날짜*/
  private String startdate;
  /**종료 날짜*/
  private String enddate;
  
  /**진행 여부*/
  private int yn;
  /**등록일*/
  private String rdate;
  
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
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }
  public int getYn() {
    return yn;
  }
  public void setYn(int yn) {
    this.yn = yn;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  
}