package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.post.PostVO;

public interface ReviewProcInter {
  
  public int create(ReviewVO ReviewVO);
  
  public ArrayList<ReviewVO> list_by_postno(int postno);
 
  /**
<<<<<<< HEAD
   * post + review INNER JOIN ¸ñ·Ï
=======
   * post + review INNER JOIN ï¿½ï¿½ï¿½
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   * @return
   */
  public ArrayList<PostReviewVO> list_all(); 
  
  
  /**
<<<<<<< HEAD
   * Á¶È¸
=======
   * ï¿½ï¿½È¸
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
<<<<<<< HEAD
   * ±Û Á¤º¸ ¼öÁ¤
   * @param contentsVO
   * @return Ã³¸®µÈ ·¹ÄÚµå °¹¼ö
=======
   * ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
   * @param contentsVO
   * @return Ã³ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Úµï¿½ ï¿½ï¿½ï¿½ï¿½
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   */
  public int update_text(ReviewVO reviewVO);
  
  /**
<<<<<<< HEAD
   * »èÁ¦
   * @param contentsno
   * @return »èÁ¦µÈ ·¹ÄÚµå °¹¼ö
=======
   * íŒŒì¼ ì •ë³´ ìˆ˜ì •
   * @param contentsVO
   * @return ì²˜ë¦¬ëœ ë ˆì½”ë“œ ê°¯ìˆ˜
   */
  public int update_file(ReviewVO reviewVO);
  
  /**
   * ï¿½ï¿½ï¿½ï¿½
   * @param contentsno
   * @return ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½Úµï¿½ ï¿½ï¿½ï¿½ï¿½
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   */
  public int delete(int reviewno);
}



