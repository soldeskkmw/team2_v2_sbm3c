package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.review.ReviewVO;



public interface ReplyProcInter {
  
  public int replycreate(ReplyVO ReplyVO);
  
  public ArrayList<ReplyVO> replylist_by_reviewno(int reviewno);
 

  
  /**
   * ����
   * @param contentsno
   * @return ������ ���ڵ� ����
   */
  public int replydelete(int replyno);
  
  /**
   * ��ȸ
   * @param reviewno
   * @return
   */
  public ReplyVO replyread(int replyno);
  
  
}



