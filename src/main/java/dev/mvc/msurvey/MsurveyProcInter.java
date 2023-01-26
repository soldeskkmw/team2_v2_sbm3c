package dev.mvc.msurvey;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

public interface MsurveyProcInter {
  /**
   * 전체 목록
   * @return 전체 목록
   */
  public ArrayList<MsurveyVO> list_all();
}
=======

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



>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
