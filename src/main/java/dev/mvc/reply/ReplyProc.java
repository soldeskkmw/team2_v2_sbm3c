package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import dev.mvc.reply.ReviewReplyVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.reply.ReplyVO;
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
  public ArrayList<ReplyVO> read_by_reviewno_reply(int reviewno, int cateno) {
    ArrayList<ReplyVO> list = this.replyDAO.read_by_reviewno_reply(reviewno, cateno);
    
    for(int i=0; i<list.size(); i++) {
      ReplyVO replyVO = list.get(i);
      
      String reviewtitle = replyVO.getReviewtitle();
      String reviewcontent = replyVO.getReviewcontent();
      
      reviewtitle = Tool.convertChar(reviewtitle);
      reviewcontent = Tool.convertChar(reviewcontent);
      
      replyVO.setReviewtitle(reviewtitle);
      replyVO.setReviewcontent(reviewcontent);
    }
    return list;
  }

  @Override
  public int update_reply(ReplyVO replyVO) {
    int cnt = this.replyDAO.update_reply(replyVO);
    return cnt;
  }

  @Override
  public int reply_delete(int replyno) {
    int cnt = this.replyDAO.reply_delete(replyno);
    return cnt;
  }
  
  @Override
  public int update_reviewtext(ReviewVO reviewVO) {
    int cnt = this.replyDAO.update_reviewtext(reviewVO);
    return cnt;
  }

  @Override
  public int update_reviewfile(ReviewVO reviewVO) {
    int cnt = this.replyDAO.update_reviewfile(reviewVO);
    return cnt;
  }

  @Override
  public int visit_cnt(int cnt) {
    int review_cnt = this.replyDAO.visit_cnt(cnt);
    return review_cnt;
  }
  
  @Override
  public int review_delete(int reviewno) {
    int cnt = this.replyDAO.review_delete(reviewno);
    return cnt;
  }
  
}



