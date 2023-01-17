package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.post.PostVO;

public interface ReviewDAOInter {
  
  public int create(ReviewVO ReviewVO);
  
  public ArrayList<ReviewVO> list_by_postno(int postno);
  
  /**
   * post + review INNER JOIN ���
   * post + review INNER JOIN ���
   * @return
   */
  public ArrayList<PostReviewVO> list_all(); 
  
  
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

  
}



