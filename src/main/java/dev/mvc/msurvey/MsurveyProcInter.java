package dev.mvc.msurvey;

import java.util.ArrayList;

// 데이터 처리 관련 알고리즘 구현, 사칙연산, 제어문
public interface MsurveyProcInter {
 
  
  /**
   * 등록
   * @param MserveyVO
   * @return 등록한 레코드 개수
   */
  public int msurvey_create(MsurveyVO msurveyVO);
  
  /**
   * 전체 목록
   * @return 레코드 전체 목록
   */
  public ArrayList<MsurveyVO> msurvey_list();
  
  /**
   * 조회
   * @param surveyno
   * @return
   */
  public MsurveyVO msurvey_read(int msurveyno);
  
  
}



