package dev.mvc.reply;

import java.util.ArrayList;

<<<<<<< HEAD
public interface ReplyProcInter {
  
  /**
   * 등록
   * @param replyVO
   * @return 등록한 레코드 개수
=======
import dev.mvc.review.ReviewVO;

public interface ReplyProcInter {
  
  /**
   * 리뷰 별 댓글 등록
   * @param replyVO
   * @return 등록한 댓글 개수
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
   */
  public int create(ReplyVO replyVO);
  
  /**
<<<<<<< HEAD
   * 특정 리뷰에 등록된 댓글 목록
   * <xmp><select id="list_by_reviewno" resultType="dev.mvc.reply.ReplyVO" parameterType="int"></xmp>
   * @return 레코드 전체 목록
   */
  public ArrayList<ReplyVO> list_by_reviewno(int reviewno);
  
  /**
   * 댓글 조회
   * @param replyno 조회할 레코드 번호(PK)
   * @return 조회된 레코드
   */
  public ReplyVO read(int replyno);
  
  /**
   * 리뷰 내용 + 리뷰 전체 댓글 목록 INNER JOIN + 회원
   * @param replyno
   * @return
   */
  public ReplyVO read_all_list(int replyno);
  
  /**
   * 댓글 수정
   * @param ReplyVO
   * @return 처리된 레코드 갯수
   */
  public int update_reply(ReplyVO replyVO);
  
  /**
   * 댓글 삭제
   * @param replyno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int replyno);
=======
   * 리뷰 조회 + 리뷰당 전체 댓글 목록
   * <xmp><select id="read_by_reviewno_reply" resultType="dev.mvc.reply.ReplyVO" parameterType="int"></xmp>
   * @return 조회된 리뷰와 댓글 전체 목록
   */
  public ArrayList<ReplyVO> read_by_reviewno_reply(int reviewno, int cateno);
  
  /**
   * 댓글 수정
   * @param ReviewVO
   * @return 처리된 레코드 갯수
   */
  public int update_reply(ReplyVO replyVO);
  
  /**
   * 댓글 삭제
   * @param reviewno
   * @return 삭제된 레코드 갯수
   */
  public int reply_delete(int replyno);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  
  /**
   * 리뷰 수정
   * @param ReviewVO
   * @return 처리된 레코드 갯수
   */
  public int update_reviewtext(ReviewVO reviewVO);
  
  /**
   * 리뷰 파일 수정
   * @param ReviewVO
   * @return 처리된 레코드 갯수
   */
  public int update_reviewfile(ReviewVO reviewVO);
  
  /**
   * 삭제
   * @param reviewno
   * @return 삭제된 레코드 갯수
   */
  public int review_delete(int reviewno);
  
  /**
   * 리뷰 조회수 증가 + 조회수 중복 방지
   * @param cnt
   * @return 조회수
   */
  public int visit_cnt(int cnt);
  
}



