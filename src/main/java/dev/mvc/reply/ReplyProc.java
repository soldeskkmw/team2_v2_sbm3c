package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import dev.mvc.reply.ReviewReplyVO;
import dev.mvc.reply.ReplyVO;
import dev.mvc.tool.Tool;
 
@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  @Autowired
  private ReplyDAOInter replyDAO;

  @Override
  public int replycreate(ReplyVO replyVO) {
    int replycnt=this.replyDAO.replycreate(replyVO);
    return replycnt;
  }

  @Override
  public ArrayList<ReplyVO> replylist_by_reviewno(int reviewno) {
    ArrayList<ReplyVO> replylist = this.replyDAO.replylist_by_reviewno(reviewno);
    
    for (int i=0; i<replylist.size(); i++) {
      ReplyVO replyVO = replylist.get(i);
      
      
      String reply = replyVO.getReplycontent();
      
      
      reply = Tool.convertChar(reply);
      
    
      replyVO.setReplycontent(reply);
    }
    
    return replylist;
  }
  
  
 
  
  @Override
  public int replydelete(int replyno) {
    int replycnt = this.replyDAO.replydelete(replyno);
    return replycnt;
  }
  
  /**
   * Á¶È¸
   */
  @Override
  public ReplyVO replyread(int replyno) {
    ReplyVO replyVO = this.replyDAO.replyread(replyno);
    
    
    String replycontent = replyVO.getReplycontent();
    
    
    replycontent = Tool.convertChar(replycontent); 
    
    
    replyVO.setReplycontent(replycontent);  
    
   
    
    return replyVO;
  }
  
  
  
}



