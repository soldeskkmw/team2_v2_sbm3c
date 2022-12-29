package dev.mvc.survey;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.mvc.tool.Tool;

// import dev.mvc.contents.ContentsVO;

@Component("dev.mvc.survey.SurveyProc")
public class SurveyProc implements SurveyProcInter {
  // surveyDAOInter interface 만 존재하고 구현 class는 존재하지 않음.
  // interface는 객체를 만들 수 없고 할당만 받을 수 있음.
  
  @Autowired
  private SurveyDAOInter surveyDAO;
  
  public SurveyProc() {

  }

  @Override
  public int survey_create(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.survey_create(surveyVO);
    return cnt;
  }

  @Override
  public ArrayList<SurveyVO> survey_list() {
    ArrayList<SurveyVO> list = this.surveyDAO.survey_list();
    return list;
  }

  @Override
  public SurveyVO survey_read(int surveyno) {
    SurveyVO surveyVO = this.surveyDAO.survey_read(surveyno);
 
    return surveyVO;
  }

  @Override
  public int survey_read_update(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.survey_read_update(surveyVO);
    return cnt;
  }

  @Override
  public int survey_read_delete(int surveyno) {
    int cnt = this.surveyDAO.survey_read_delete(surveyno);
    return cnt;
  }
  


}

