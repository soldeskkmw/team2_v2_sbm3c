package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

<<<<<<< HEAD
=======
import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.post.PostVO;

>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
public interface ReviewDAOInter {
  
  /**
   * 카테고리 별 리뷰 등록
   * @param reviewVO
   * @return 등록한 레코드 개수
   */
  public int create(ReviewVO reviewVO);
  
  /**
<<<<<<< HEAD
   * 카테고리별 리뷰 전체 목록
   * <xmp><select id="list_by_cateno" resultType="dev.mvc.review.ReviewVO" parameterType="int"></xmp>
   * @return 레코드 전체 목록
   */
  public ArrayList<ReviewVO> list_by_cateno(int cateno);
  
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
=======
   * post + review INNER JOIN ���
   * post + review INNER JOIN ���
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
   * @return
   */
  public ArrayList<ReviewVO> list_by_cateno_search_paging(HashMap<String, Object> map);
  
<<<<<<< HEAD
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
=======
  
  /**
   * ��ȸ
   * ��ȸ
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
   * �� ���� ����
   * @param contentsVO
   * @return ó���� ���ڵ� ����
   * �� ���� ����
   * @param contentsVO
   * @return ó���� ���ڵ� ����
   */
  public int update_text(ReviewVO reviewVO);
  
  /**
   * ����
   * @param contentsno
   * @return ������ ���ڵ� ����
   */
  public int delete(int reviewno);
  
  /**
   * 파일 정보 수정
   * @param contentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(ReviewVO reviewVO);

>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
  
}



