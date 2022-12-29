package dev.mvc.survey;

import java.util.ArrayList;

// 데이터 처리 관련 알고리즘 구현, 사칙연산, 제어문
public interface SurveyProcInter {
 
  /**
   * 등록
   * @param ServeyVO
   * @return 등록한 레코드 개수
   */
  public int survey_create(SurveyVO surveyVO);
  
  /**
   * 전체 목록
   * @return 레코드 전체 목록
   */
  public ArrayList<SurveyVO> survey_list();
  
  /**
   * 조회
   * @param surveyno
   * @return
   */
  public SurveyVO survey_read(int surveyno);

  /**
   * 수정
   * @param ServeyVO
   * @return 수정된 레코드 갯수
   */
  public int survey_read_update(SurveyVO surveyVO);
 
  /**
   * 삭제
   * @param surveyno
   * @return 삭제된 레코드 수
   */
  public int survey_read_delete(int surveyno);
  
  
}



