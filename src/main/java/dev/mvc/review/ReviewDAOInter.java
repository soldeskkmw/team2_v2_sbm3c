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
   * @return
   */
  public ArrayList<PostReviewVO> list_all(); 
  
  
  /**
   * ��ȸ
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  
  /**
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
 
  

  
}



