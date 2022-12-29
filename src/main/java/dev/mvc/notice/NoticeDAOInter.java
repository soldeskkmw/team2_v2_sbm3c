package dev.mvc.notice;

import java.util.ArrayList;

// Spring framework에서 제공하는 기능
// application.properties를 읽어서 DBMS 연결/해제 자동 구현
// try ~ catch 자동 구현
// NoticeVO 값을 SQL에 자동 전달 및 실행
// return 자동 구현
public interface NoticeDAOInter {
  /**
   * 등록
   * @param ServeyVO
   * @return 등록한 레코드 개수
   */
  public int notice_create(NoticeVO noticeVO);
  
  /**
   * 전체 목록
   * @return 레코드 전체 목록
   */
  public ArrayList<NoticeVO> notice_list();
  
  /**
   * 조회
   * @param Noticeno
   * @return
   */
  public NoticeVO notice_read(int noticeno);
  
  /**
   * 수정
   * @param ServeyVO
   * @return 수정된 레코드 갯수
   */
  public int notice_read_update(NoticeVO noticeVO);
 
  /**
   * 삭제
   * @param Noticeno
   * @return 삭제된 레코드 수
   */
  public int notice_read_delete(int noticeno);
  
  
}


