package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;


import dev.mvc.reply.ReplyVO;
import dev.mvc.reply.ReviewReplyVO;
import dev.mvc.review.ReviewVO;


public interface ReplyDAOInter {
  
  public int replycreate(ReplyVO ReplyVO);
  
  public ArrayList<ReplyVO> replylist_by_reviewno(int reviewno);
  

  
  /**
   * ����
   * @param contentsno
   * @return ������ ���ڵ� ����
   */
  public int replydelete(int replyno);
  
  public ReplyVO replyread(int replyno);
  
  
}



