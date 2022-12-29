package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewVO;


public interface ReviewDAOInter {
  
  public int create(ReviewVO ReviewVO);
  
  public ArrayList<ReviewVO> list_by_postno(int postno);
  
  /**
   * post + review INNER JOIN 목록
   * @return
   */
  public ArrayList<PostReviewVO> list_all(); 
  
  
  /**
   * 조회
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  
  /**
   * 글 정보 수정
   * @param contentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(ReviewVO reviewVO);
  
  /**
   * 삭제
   * @param contentsno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int reviewno);
 
}



