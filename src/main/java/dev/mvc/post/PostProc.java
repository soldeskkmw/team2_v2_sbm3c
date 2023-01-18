package dev.mvc.post;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.post.PostProc")
public class PostProc implements PostProcInter {
  
  @Autowired PostDAOInter postDAO;

  @Override
  public int create(PostVO postVO) {
    int cnt = this.postDAO.create(postVO);
    return cnt;
  }

  @Override
  public ArrayList<PostVO> list_by_cateno(int cateno) {
    ArrayList<PostVO> list = this.postDAO.list_by_cateno(cateno);

    for (int i=0; i<list.size(); i++) {
      PostVO postVO = list.get(i);
      
      String posttitle = postVO.getPosttitle();
      String postcontent = postVO.getPostcontent();
      
      posttitle = Tool.convertChar(posttitle);
      postcontent = Tool.convertChar(postcontent);
      
      postVO.setPosttitle(posttitle);
      postVO.setPostcontent(postcontent);
    }
    
    return list;
  }

  @Override
  public PostVO read(int postno) {
    PostVO postVO = this.postDAO.read(postno);
    
    String posttitle = postVO.getPosttitle();
    posttitle = Tool.convertChar(posttitle);
    postVO.setPosttitle(posttitle);
    
    return postVO;
  }

  @Override
  public ArrayList<PostVO> list_by_cateno_search(HashMap<String, Object> hashMap) {
    ArrayList<PostVO> list = this.postDAO.list_by_cateno_search(hashMap);
    
    for (int i=0; i<list.size(); i++) {
      PostVO postVO = list.get(i);
      String posttitle = postVO.getPosttitle();
      String postcontent = postVO.getPostcontent();
      
      posttitle = Tool.convertChar(posttitle);
      postcontent = Tool.convertChar(postcontent);
      
      postVO.setPosttitle(posttitle);
      postVO.setPostcontent(postcontent);
    }
    
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int cnt = this.postDAO.search_count(hashMap);
    return cnt;
  }

  @Override
  public ArrayList<AdminPostVO> list_all_admin() {
    ArrayList<AdminPostVO> list = this.postDAO.list_all_admin();
    return list;
  }

  @Override
  public ArrayList<CatePostVO> list_all_cate() {
    ArrayList<CatePostVO> list = this.postDAO.list_all_cate();
    return list;
  }

  @Override
  public ArrayList<PostVO> list_by_cateno_search_paging(HashMap<String, Object> map) {
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Post.RECORD_PER_PAGE;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + Post.RECORD_PER_PAGE;   
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<PostVO> list = this.postDAO.list_by_cateno_search_paging(map);
    
    for (int i=0; i<list.size(); i++) {
      PostVO postVO = list.get(i);
      String posttitle = postVO.getPosttitle();
      
      posttitle = Tool.convertChar(posttitle);
      
      postVO.setPosttitle(posttitle);
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
   * @param postword 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String pagingBox(int cateno, int search_count, int now_page, String postword) {
    int total_page = (int)(Math.ceil((double)search_count/Post.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Post.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Post.PAGE_PER_BLOCK));  // 현재 그룹 번호
   
    int start_page = ((now_grp - 1) * Post.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Post.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
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
    int _now_page = (now_grp - 1) * Post.PAGE_PER_BLOCK;  
    
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+Post.LIST_FILE+"?&postword="+postword+"&now_page="+_now_page+"&cateno="+cateno+"'>이전</A></span>"); 
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
        str.append("<span class='span_box_1'><A href='"+Post.LIST_FILE+"?postword="+postword+"&now_page="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
      } 
    }
    
    // 10개 다음 페이지로 이동
    _now_page = (now_grp * Post.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+Post.LIST_FILE+"?&postword="+postword+"&now_page="+_now_page+"&cateno="+cateno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
    
    return str.toString();
  }

  @Override
  public int update_text(PostVO postVO) {
    int cnt = this.postDAO.update_text(postVO);
    return cnt;
  }

  @Override
  public int update_file(PostVO postVO) {
    int cnt = this.postDAO.update_file(postVO);
    return cnt;
  }

  @Override
  public int delete(int postno) {
    int cnt = this.postDAO.delete(postno);
    return cnt;
  }

  @Override
  public int count_by_cateno(int cateno) {
    int cnt = this.postDAO.count_by_cateno(cateno);
    return cnt;
  }

  @Override
  public int visit_cnt(int postcnt) {
    int cnt = this.postDAO.visit_cnt(postcnt);
    return cnt;
  }

  @Override
  public int update_poststar(PostVO postVO) {
    int cnt = this.postDAO.update_poststar(postVO);
    return cnt;
  }

  @Override
  public ArrayList<PostVO> mf_post_member(HashMap<String, Object> hashMap) {
    ArrayList<PostVO> list = this.postDAO.mf_post_member(hashMap);
    return list;
  }
  
  }
