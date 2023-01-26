package dev.mvc.reply;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReplyProcInter {
  
  /**
   * 등록
   * @param replyVO
   * @return 등록한 레코드 개수
   */
  public int create(ReplyVO replyVO);
  
  /**
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
  
  
}



