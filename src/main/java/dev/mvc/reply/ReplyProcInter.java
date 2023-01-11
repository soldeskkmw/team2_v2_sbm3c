package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.review.ReviewVO;



public interface ReplyProcInter {
  
  public int replycreate(ReplyVO ReplyVO);
  
  public ArrayList<ReplyVO> replylist_by_reviewno(int reviewno);
 

  
  /**
   * 삭제
   * @param contentsno
   * @return 삭제된 레코드 갯수
   */
  public int replydelete(int replyno);
  
  /**
   * 조회
   * @param reviewno
   * @return
   */
  public ReplyVO replyread(int replyno);
  
  
}



