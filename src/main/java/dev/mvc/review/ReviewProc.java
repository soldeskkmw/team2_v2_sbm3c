package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import dev.mvc.post.PostVO;
import dev.mvc.review.PostReviewVO;
import dev.mvc.review.ReviewVO;
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
import dev.mvc.tool.Tool;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  
  @Autowired ReviewDAOInter reviewDAO;

  @Override
  public int create(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.create(reviewVO);
    return cnt;
  }
  
  @Override
  public ArrayList<ReviewVO> list_by_cateno(int cateno) {
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno(cateno);
    
    for(int i=0; i<list.size(); i++) {
      ReviewVO reviewVO = list.get(i);
      String reviewtitle = reviewVO.getReviewtitle();
      String reviewcontent = reviewVO.getReviewcontent(); 
      
      reviewtitle = Tool.convertChar(reviewtitle);
      reviewcontent = Tool.convertChar(reviewcontent);
      
      reviewVO.setReviewtitle(reviewtitle);
      reviewVO.setReviewcontent(reviewcontent);
    }
    return list;
  }

  @Override
<<<<<<< HEAD
  public ArrayList<ReviewVO> list_by_cateno(int cateno) {
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno(cateno);
=======
<<<<<<< HEAD
  public ArrayList<ReviewVO> list_by_cateno_search(HashMap<String, Object> hashMap) {
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno_search(hashMap);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    
    for(int i=0; i<list.size(); i++) {
      ReviewVO reviewVO = list.get(i);
      String reviewtitle = reviewVO.getReviewtitle();
      String reviewcontent = reviewVO.getReviewcontent(); 
=======
  public ArrayList<ReviewVO> list_by_postno(int postno) {
    ArrayList<ReviewVO> reviewlist = this.reviewDAO.list_by_postno(postno);
    
    for (int i=0; i<reviewlist.size(); i++) {
      ReviewVO reviewVO = reviewlist.get(i);
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
      
<<<<<<< HEAD
      String reviewtitle = reviewVO.getReviewtitle();
      String reviewcontent = reviewVO.getReviewcontent();
      
      reviewtitle = Tool.convertChar(reviewtitle);
      reviewcontent = Tool.convertChar(reviewcontent);
      
      reviewVO.setReviewtitle(reviewtitle);
      reviewVO.setReviewcontent(reviewcontent);
    }
    return list;
  }

  @Override
  public ReviewVO read(int reviewno) {
    ReviewVO reviewVO = this.reviewDAO.read(reviewno);
    
    String reviewtitle = reviewVO.getReviewtitle();
    reviewtitle = Tool.convertChar(reviewtitle);
    reviewVO.setReviewtitle(reviewtitle);

    return reviewVO;
  }

  @Override
  public ArrayList<ReviewVO> list_by_cateno_search(HashMap<String, Object> hashMap) {
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno_search(hashMap);
    
    for (int i=0; i<list.size(); i++) {
      ReviewVO reviewVO = list.get(i);
      String reviewtitle = reviewVO.getReviewtitle();
      String reviewcontent = reviewVO.getReviewcontent();
      
      reviewtitle = Tool.convertChar(reviewtitle);
      reviewcontent = Tool.convertChar(reviewcontent);
      
      reviewVO.setReviewtitle(reviewtitle);
      reviewVO.setReviewcontent(reviewcontent);
    }
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int cnt = this.reviewDAO.search_count(hashMap);
    return cnt;
  }

  @Override
  public ArrayList<ReviewVO> list_by_cateno_search_paging(HashMap<String, Object> map) {
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Review.RECORD_PER_PAGE;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + Review.RECORD_PER_PAGE;
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno_search_paging(map);
    
    for (int i=0; i<list.size(); i++) {
      ReviewVO reviewVO = list.get(i);
      String reviewtitle = reviewVO.getReviewtitle();
      
      reviewtitle = Tool.convertChar(reviewtitle);
      
      reviewVO.setReviewtitle(reviewtitle);
    }
    return list;
  }
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param cateno          카테고리번호 
   * @param search_count  검색(전체) 레코드수 
   * @param now_page      현재 페이지
   * @param reviewword 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String pagingBox(int cateno, int search_count, int now_page, String reviewword) {
    int total_page = (int)(Math.ceil((double)search_count/Review.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Review.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Review.PAGE_PER_BLOCK));  // 현재 그룹 번호
   
    int start_page = ((now_grp - 1) * Review.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Review.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
    StringBuffer str = new StringBuffer(); // String class 보다 문자열 추가등의 편집시 속도가 빠름 
    
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>");
    
    // 이전 10개 페이지로 이동
    int _now_page = (now_grp - 1) * Review.PAGE_PER_BLOCK;  
    
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?&reviewword="+reviewword+"&now_page="+_now_page+"&cateno="+cateno+"'>이전</A></span>"); 
    }
    
    // 중앙의 페이지 목록
    for(int i=start_page; i<=end_page; i++){ 
      if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?reviewword="+reviewword+"&now_page="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
      } 
    }
    
    // 10개 다음 페이지로 이동
    _now_page = (now_grp * Review.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?&reviewword="+reviewword+"&now_page="+_now_page+"&cateno="+cateno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
    
    return str.toString();
  }

  @Override
  public int update_text(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.update_text(reviewVO);
=======
      reviewtitle = Tool.convertChar(reviewtitle);
      reviewcontent = Tool.convertChar(reviewcontent);
      
<<<<<<< HEAD
      reviewVO.setReviewtitle(reviewtitle);
      reviewVO.setReviewcontent(reviewcontent);
    }
    return list;
=======
      title = Tool.convertChar(title);
      review = Tool.convertChar(review);
      if(review.length()>20) {
        
        String[] array_word;
        array_word = review.split("");
        review="";
        for(int k=0;k<20;k++) { //출력
//          System.out.println(array_word[k]);
          review=review+array_word[k];
        }
        review=review+"......";
      }
      reviewVO.setReviewtitle(title);
      reviewVO.setReviewcontent(review);
    }

    return reviewlist;
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
  }

  @Override
  public int search_count(HashMap hashMap) {
    int cnt = this.reviewDAO.search_count(hashMap);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    return cnt;
  }

  @Override
<<<<<<< HEAD
  public int update_file(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.update_file(reviewVO);
    return cnt;
  }

=======
  public ArrayList<ReviewVO> list_by_cateno_search_paging(HashMap<String, Object> map) {
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Review.RECORD_PER_PAGE;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + Review.RECORD_PER_PAGE;   
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<ReviewVO> list = this.reviewDAO.list_by_cateno_search_paging(map);
    
    for(int i=0; i<list.size(); i++) {
      ReviewVO reviewVO = list.get(i);
      String reviewtitle = reviewVO.getReviewtitle();
      
      reviewtitle = Tool.convertChar(reviewtitle);
      
      reviewVO.setReviewtitle(reviewtitle);
    }
    return list;
  }
  
<<<<<<< HEAD
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param cateno          카테고리번호 
   * @param search_count  검색(전체) 레코드수 
   * @param now_page      현재 페이지
   * @param reviewword 검색어
   * @return 페이징 생성 문자열
=======
  
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  @Override
  public int update_file(ReviewVO reviewVO) {
      int cnt = this.reviewDAO.update_file(reviewVO);
      return cnt;
  }
<<<<<<< HEAD

  @Override
  public int visit_reviewcnt(int reviewcnt) {
    int cnt = this.reviewDAO.visit_reviewcnt(reviewcnt);
    return cnt;
  }

  @Override
  public int count_by_replycnt(int reviewno) {
    int cnt = this.reviewDAO.count_by_replycnt(reviewno);
    return cnt;
=======
  
  
  /**
   * ��ȸ
   * ��ȸ
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
   */
  @Override
  public String pagingBox(int cateno, int search_count, int now_page, String reviewword) {
    int total_page = (int)(Math.ceil((double)search_count/Review.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Review.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Review.PAGE_PER_BLOCK));  // 현재 그룹 번호
   
    int start_page = ((now_grp - 1) * Review.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Review.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
    StringBuffer str = new StringBuffer(); // String class 보다 문자열 추가등의 편집시 속도가 빠름 
    
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>");
    
<<<<<<< HEAD
    // 이전 10개 페이지로 이동
    int _now_page = (now_grp - 1) * Review.PAGE_PER_BLOCK;  
=======
    reviewtitle = Tool.convertChar(reviewtitle);  // Ư�� ���� ó��
    reviewtitle = Tool.convertChar(reviewtitle);  // Ư�� ���� ó��
    reviewcontent = Tool.convertChar(reviewcontent); 
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?&reviewword="+reviewword+"&now_page="+_now_page+"&cateno="+cateno+"'>이전</A></span>"); 
    }
    
    // 중앙의 페이지 목록
    for(int i=start_page; i<=end_page; i++){ 
      if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?reviewword="+reviewword+"&now_page="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
      } 
    }
    
    // 10개 다음 페이지로 이동
    _now_page = (now_grp * Review.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+Review.LIST_FILE+"?&reviewword="+reviewword+"&now_page="+_now_page+"&cateno="+cateno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
    
    return str.toString();
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  }

}



