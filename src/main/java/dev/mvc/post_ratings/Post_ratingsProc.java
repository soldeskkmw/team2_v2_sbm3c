package dev.mvc.post_ratings;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.post_ratings.Post_ratingsProc")
public class Post_ratingsProc implements Post_ratingsProcInter {

  @Autowired Post_ratingsDAOInter post_ratingsDAO;

  @Override
  public int create(Post_ratingsVO post_ratingsVO) {
    int cnt = this.post_ratingsDAO.create(post_ratingsVO);
    return cnt;
  }

  @Override
  public ArrayList<Post_ratingsVO> list_by_postno(int postno) {
    ArrayList<Post_ratingsVO> list = this.post_ratingsDAO.list_by_postno(postno);
    
    for (int i=0; i<list.size(); i++) {
      Post_ratingsVO post_ratingsVO = list.get(i);
      
      int ratingno = post_ratingsVO.getRatingno();
      int memberno = post_ratingsVO.getMemberno();
      float ratings = post_ratingsVO.getRatings();
      
      post_ratingsVO.setRatingno(ratingno);
      post_ratingsVO.setMemberno(memberno);
      post_ratingsVO.setRatings(ratings);
    }
    return list;
  }

  @Override
  public ArrayList<PostPost_ratingsVO> list_all_post() {
    ArrayList<PostPost_ratingsVO> list = this.post_ratingsDAO.list_all_post();
    return list;
  }

  @Override
  public ArrayList<MemberPost_ratingsVO> list_all_member() {
    ArrayList<MemberPost_ratingsVO> list = this.post_ratingsDAO.list_all_member();
    return list;
  }

  @Override
  public int update_ratings(Post_ratingsVO post_ratingsVO) {
    int cnt = this.post_ratingsDAO.update_ratings(post_ratingsVO);
    return cnt;
  }
  
  @Override
  public int delete(int ratingno) {
    int cnt = this.post_ratingsDAO.delete(ratingno);
    return cnt;
  }


  
}
