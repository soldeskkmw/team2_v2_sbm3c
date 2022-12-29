package dev.mvc.notice;

import java.util.ArrayList;
// import java.util.HashMap;


// import dev.mvc.notice.NoticeVO;

// import dev.mvc.contents.ContentsVO;

// 데이터 처리 관련 알고리즘 구현, 사칙연산, 제어문
public interface NoticeProcInter {
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



