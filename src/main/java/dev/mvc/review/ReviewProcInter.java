package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.post.PostVO;

public interface ReviewProcInter {
  
  public int create(ReviewVO ReviewVO);
  
  public ArrayList<ReviewVO> list_by_postno(int postno);
 
  /**
<<<<<<< HEAD
   * post + review INNER JOIN ���
=======
   * post + review INNER JOIN ���
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   * @return
   */
  public ArrayList<PostReviewVO> list_all(); 
  
  
  /**
<<<<<<< HEAD
   * ��ȸ
=======
   * ��ȸ
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
<<<<<<< HEAD
   * �� ���� ����
   * @param contentsVO
   * @return ó���� ���ڵ� ����
=======
   * �� ���� ����
   * @param contentsVO
   * @return ó���� ���ڵ� ����
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   */
  public int update_text(ReviewVO reviewVO);
  
  /**
<<<<<<< HEAD
   * ����
   * @param contentsno
   * @return ������ ���ڵ� ����
=======
   * 파일 정보 수정
   * @param contentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(ReviewVO reviewVO);
  
  /**
   * ����
   * @param contentsno
   * @return ������ ���ڵ� ����
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   */
  public int delete(int reviewno);
}



