package dev.mvc.post_ratings;

import java.util.ArrayList;
import java.util.HashMap;

public interface Post_ratingsDAOInter {
  
  /**
   * 등록
   * @param PostVO
   * @return 등록한 레코드 개수
   */
  public int create(Post_ratingsVO post_ratingsVO);
  
  /**
   * 특정 포스트에 등록된 평점 목록
   * <xmp><select id="list_by_postno" resultType="dev.mvc.post_ratings.Post_ratingsVO" parameterType="int"></xmp>
   * @return 포스트 별 레코드 전체 목록
   */
  public ArrayList<Post_ratingsVO> list_by_postno(int postno);
  
  /**
   * post + post_ratings INNER JOIN 목록
   * @return
   */
  public ArrayList<PostPost_ratingsVO> list_all_post();
  
  /**
   * member + post_ratings INNER JOIN 목록
   * @return
   */
  public ArrayList<MemberPost_ratingsVO> list_all_member();
  
  /**
   * 평점 수정
   * @param PostVO
   * @return 처리된 레코드 갯수
   */
  public int update_ratings(Post_ratingsVO post_ratingsVO);
  
  /**
   * 삭제
   * @param ratingno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int ratingno);

}
