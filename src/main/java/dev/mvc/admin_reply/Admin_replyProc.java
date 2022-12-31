package dev.mvc.admin_reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.admin_reply.Admin_replyProc")
public class Admin_replyProc implements Admin_replyProcInter{

  @Autowired
  private Admin_replyDAOInter admin_replyDAOInter;
  
  @Override
  public int create(Admin_replyVO admin_replyVO) {
    int cnt = this.admin_replyDAOInter.create(admin_replyVO);
    return cnt;
  }

  @Override
  public Admin_replyVO read(int adminreplyno) {
    Admin_replyVO admin_replyVO = this.admin_replyDAOInter.read(adminreplyno);
    return admin_replyVO;
  }

  @Override
  public int update(Admin_replyVO admin_replyVO) {
    int cnt = this.admin_replyDAOInter.update(admin_replyVO);
    return cnt;
  }

  @Override
  public int delete(int adminreplyno) {
    int cnt = this.admin_replyDAOInter.delete(adminreplyno);
    return cnt;
  }

  @Override
  public int delete_all_by_post(int serviceno) {
    int cnt = this.admin_replyDAOInter.delete_all_by_post(serviceno);
    return cnt;
  }

}
