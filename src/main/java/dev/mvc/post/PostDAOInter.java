package dev.mvc.post;

import java.util.ArrayList;
import java.util.HashMap;

public interface PostDAOInter {
  
  /**
   * 등록
   * @param postVO
   * @return 등록한 레코드 개수
   */
  public int create(PostVO postVO);
  
  /**
   * 전체 목록
   * <xmp><select id="list_by_cateno" resultType="dev.mvc.post.PostVO" parameterType="int"></xmp>
   * @return 레코드 전체 목록
   */
  public ArrayList<PostVO> list_by_cateno(int cateno);
  
  /**
   * 조회
   * @param postno 조회할 레코드 번호(PK)
   * @return 조회된 레코드
   */
  public PostVO read(int postno);
  
  /**
   * 검색 
   * @param hashMap 검색어
   * @return 검색된 레코드 목록
   */
  public ArrayList<PostVO> list_by_cateno_search(HashMap<String, Object>hashMap);
  
  /**
   * 검색된 레코드 수
   * @param hashMap 검색어
   * @return 검색된 레코드 수
   */
  public int search_count(HashMap hashMap);
  
  /**
   * admin + post INNER JOIN 목록
   * @return
   */
  public ArrayList<AdminPostVO> list_all_admin();
  
  /**
   * cate + post INNER JOIN 목록
   * @return
   */
  public ArrayList<CatePostVO> list_all_cate();
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public ArrayList<PostVO> list_by_cateno_search_paging(HashMap<String, Object> map);
  
  /**
   * 글 정보 수정
   * @param PostVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(PostVO postVO);
  
  /**
   * 파일 정보 수정
   * @param PostVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(PostVO postVO);
  
  /**
   * 삭제
   * @param postno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int postno);
  
  /**
   * 카테고리 번호에 의한 카운트 
   * @param cateno 카테고리 번호
   * @return 갯수
   */
  public int count_by_cateno(int cateno);
  
  /**
   * 게시글 조회수 증가 + 조회수 중복 방지
   * @param cnt
   * @return 조회수
   */
  public int visit_cnt(int postcnt);
  
  /**
   * post테이블의 평점(poststar) 컬럼 수정
   * @param PostVO
   * @return 평점
   */
  public int update_poststar(PostVO postVO);
  
  /**
   * 추천 목록
   * @return
   */
  public ArrayList<PostVO> mf_post_member(HashMap<String, Object> hashMap); 
  
  
}
