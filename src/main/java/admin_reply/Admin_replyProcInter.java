package admin_reply;

public interface Admin_replyProcInter {
  public int create(Admin_replyVO admin_replyVO);
  
  public Admin_replyVO read(int adminreplyno);
  
  public int update(Admin_replyVO admin_replyVO);
  
  public int delete(int adminreplyno);
  
  public int delete_all_by_post(int serviceno);
}