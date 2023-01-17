package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.post.PostVO;
import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  @Autowired
  private ReviewDAOInter reviewDAO;

  @Override
  public int create(ReviewVO reviewVO) {
    int cnt=this.reviewDAO.create(reviewVO);
    return cnt;
  }

  @Override
  public ArrayList<ReviewVO> list_by_postno(int postno) {
    ArrayList<ReviewVO> reviewlist = this.reviewDAO.list_by_postno(postno);
    
    for (int i=0; i<reviewlist.size(); i++) {
      ReviewVO reviewVO = reviewlist.get(i);
      
      String title = reviewVO.getReviewtitle();
      String review = reviewVO.getReviewcontent();
      
      title = Tool.convertChar(title);
      review = Tool.convertChar(review);
      if(review.length()>20) {
        
        String[] array_word;
        array_word = review.split("");
        review="";
        for(int k=0;k<20;k++) { //ì¶œë ¥
//          System.out.println(array_word[k]);
          review=review+array_word[k];
        }
        review=review+"......";
      }
      reviewVO.setReviewtitle(title);
      reviewVO.setReviewcontent(review);
    }

    return reviewlist;
  }
  
  @Override
  public ArrayList<PostReviewVO> list_all() {
    ArrayList<PostReviewVO> list = this.reviewDAO.list_all();
    return list;
  }
  
  
  @Override
  public int update_file(ReviewVO reviewVO) {
      int cnt = this.reviewDAO.update_file(reviewVO);
      return cnt;
  }
  
  
  /**
<<<<<<< HEAD
   * Á¶È¸
=======
   * ï¿½ï¿½È¸
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
   */
  @Override
  public ReviewVO read(int reviewno) {
    ReviewVO reviewVO = this.reviewDAO.read(reviewno);
    
    String reviewtitle = reviewVO.getReviewtitle();
    String reviewcontent = reviewVO.getReviewcontent();
    
<<<<<<< HEAD
    reviewtitle = Tool.convertChar(reviewtitle);  // Æ¯¼ö ¹®ÀÚ Ã³¸®
=======
    reviewtitle = Tool.convertChar(reviewtitle);  // Æ¯ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Ã³ï¿½ï¿½
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
    reviewcontent = Tool.convertChar(reviewcontent); 
    
    reviewVO.setReviewtitle(reviewtitle);
    reviewVO.setReviewcontent(reviewcontent);  
    
    long reviewsize1 = reviewVO.getReviewsize1();
    reviewVO.setReviewsize1_label(Tool.unit(reviewsize1));
    
    return reviewVO;
  }

  @Override
  public int update_text(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.update_text(reviewVO);
    return cnt;
  }
  
  @Override
  public int delete(int reviewno) {
    int cnt = this.reviewDAO.delete(reviewno);
    return cnt;
  }
}



