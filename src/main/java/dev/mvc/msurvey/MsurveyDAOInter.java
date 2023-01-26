package dev.mvc.msurvey;

import java.util.ArrayList;

<<<<<<< HEAD
// Spring framework에서 제공하는 기능
// application.properties를 읽어서 DBMS 연결/해제 자동 구현
// try ~ catch 자동 구현
// surveyVO 값을 SQL에 자동 전달 및 실행
// return 자동 구현
public interface MsurveyDAOInter {
  /**
   * 전체 목록
   * <xmp><select id="list_all" resultType="dev.mvc.msurvey.MsurveyVO"></xmp>
   * @return 전체 목록
   */
  public ArrayList<MsurveyVO> list_all();
=======
import dev.mvc.survey.SurveyVO;

public interface MsurveyDAOInter {
  
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
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

  
}


