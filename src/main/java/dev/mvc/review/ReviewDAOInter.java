package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReviewDAOInter {
  
  /**
   * 리뷰 등록
   * @param reviewVO
   * @return 등록한 레코드 개수
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * 특정 카테고리에 등록된 리뷰 목록
   * <xmp><select id="list_by_cateno" resultType="dev.mvc.review.ReviewVO" parameterType="int"></xmp>
   * @return 레코드 전체 목록
   */
  public ArrayList<ReviewVO> list_by_cateno(int cateno);
  
  /**
   * 리뷰 조회
   * @param reviewno 조회할 레코드 번호(PK)
   * @return 조회된 레코드
   */
  public ReviewVO read(int reviewno);
  
  /**
   * 검색 
   * @param hashMap 검색어
   * @return 검색된 레코드 목록
   */
  public ArrayList<ReviewVO> list_by_cateno_search(HashMap<String, Object>hashMap);
  
  /**
   * 검색된 레코드 수
   * @param hashMap 검색어
   * @return 검색된 레코드 수
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public ArrayList<ReviewVO> list_by_cateno_search_paging(HashMap<String, Object> map);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param cateno          카테고리번호 
   * @param search_count  검색(전체) 레코드수 
   * @param now_page      현재 페이지
   * @param reviewword 검색어
   * @return 페이징 생성 문자열
   */
  public String pagingBox(int cateno, int search_count, int now_page, String reviewword);
  
  /**
   * 리뷰 글 수정
   * @param ReviewVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(ReviewVO reviewVO);
  
  /**
   * 리뷰 파일 수정
   * @param ReviewVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(ReviewVO reviewVO);

  /**
   * 리뷰 삭제
   * @param reviewno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int reviewno);
  
  /**
   * 리뷰 조회수 증가 + 조회수 중복 방지
   * @param reviewcnt
   * @return 조회수
   */
  public int visit_reviewcnt(int reviewcnt);
  
  /**
   * 리뷰 댓글수 컬럼 업데이트
   * @param reviewno 리뷰번호
   * @return 조회수
   */
  public int count_by_replycnt(int reviewno);
  
  
}


