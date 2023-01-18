package dev.mvc.msurvey;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.mvc.survey.SurveyVO;
import dev.mvc.tool.Tool;

// import dev.mvc.contents.ContentsVO;

@Component("dev.mvc.msurvey.MsurveyProc")
public class MsurveyProc implements MsurveyProcInter {
  // surveyDAOInter interface 만 존재하고 구현 class는 존재하지 않음.
  // interface는 객체를 만들 수 없고 할당만 받을 수 있음.
  
  @Autowired
  private MsurveyDAOInter msurveyDAO;
  
  public MsurveyProc() {

  }
  
  @Override
  public int msurvey_create(MsurveyVO msurveyVO) {
    int cnt = this.msurveyDAO.msurvey_create(msurveyVO);
    return cnt;
  }

  @Override
  public ArrayList<MsurveyVO> msurvey_list() {
    ArrayList<MsurveyVO> list = this.msurveyDAO.msurvey_list();
    return list;
  }

  @Override
  public MsurveyVO msurvey_read(int msurveyno) {
    MsurveyVO msurveyVO = this.msurveyDAO.msurvey_read(msurveyno);
    return msurveyVO;
  }




}

