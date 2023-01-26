package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;
 
@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  
  @Autowired ReplyDAOInter replyDAO;

  @Override
  public int create(ReplyVO replyVO) {
    int cnt = this.replyDAO.create(replyVO);
    return cnt;
  }

  @Override
  public ArrayList<ReplyVO> list_by_reviewno(int reviewno) {
    ArrayList<ReplyVO> list = this.replyDAO.list_by_reviewno(reviewno);
    
    for (int i=0; i<list.size(); i++) {
      ReplyVO replyVO = list.get(i);
      
      String replycontent = replyVO.getReplycontent();
      
      replycontent = Tool.convertChar(replycontent);
      
      replyVO.setReplycontent(replycontent);
    }
    return list;
  }

  @Override
  public ReplyVO read(int replyno) {
    ReplyVO replyVO = this.replyDAO.read(replyno);
    
    String replycontent = replyVO.getReplycontent();
    replycontent = Tool.convertChar(replycontent);
    replyVO.setReplycontent(replycontent); 

    return replyVO;
  }

  @Override
  public ReplyVO read_all_list(int replyno) {
    ReplyVO replyVO = this.replyDAO.read_all_list(replyno);
    
    String replycontent = replyVO.getReplycontent();
    replycontent = Tool.convertChar(replycontent);
    replyVO.setReplycontent(replycontent); 

    return replyVO;
  }

  @Override
  public int update_reply(ReplyVO replyVO) {
    int cnt = this.replyDAO.update_reply(replyVO);
    return cnt;
  }

  @Override
  public int delete(int replyno) {
    int cnt = this.replyDAO.delete(replyno);
    return cnt;
  }

  

}



