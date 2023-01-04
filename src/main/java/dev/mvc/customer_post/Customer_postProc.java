package dev.mvc.customer_post;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.mvc.tool.Tool;

@Component("dev.mvc.customer_post.Customer_postProc")
public class Customer_postProc implements Customer_postProcInter{
  
  @Autowired
  private Customer_postDAOInter customer_postDAOInter;

  @Override
  public int create(Customer_postVO customer_postVO) {
    int cnt = this.customer_postDAOInter.create(customer_postVO);
    return cnt;
  }

  @Override
  public ArrayList<Customer_postVO> list_all() {
    ArrayList<Customer_postVO> list = this.customer_postDAOInter.list_all();
    return list;
  }

  @Override
  public Customer_postVO read(int serviceno) {
    Customer_postVO customer_postVO = this.customer_postDAOInter.read(serviceno);
    return customer_postVO;
  }

  @Override
  public int update(Customer_postVO customer_postVO) {
    int cnt = this.customer_postDAOInter.update(customer_postVO);
    return cnt;
  }

  @Override
  public int delete(int serviceno) {
    int cnt = this.customer_postDAOInter.delete(serviceno);
    return cnt;
  }

  @Override
  public int count_all() {
    int cnt = this.customer_postDAOInter.count_all();
    return cnt;
  }

  @Override
  public int count_all_by_servicecateno(int servicecateno) {
    int cnt = this.customer_postDAOInter.count_all_by_servicecateno(servicecateno);
    return cnt;
  }

  @Override
  public int delete_all_by_servicecate(int servicecateno) {
    int cnt = this.delete_all_by_servicecate(servicecateno);
    return cnt;
  }

  @Override
  public ArrayList<Customer_postVO> list_by_servicecateno_search(HashMap<String, Object> hashmap) {
    ArrayList<Customer_postVO> list = this.customer_postDAOInter.list_by_servicecateno_search(hashmap);
    return list;
  }

  @Override
  public ArrayList<Customer_postVO> list_by_servicecateno_search_paging(HashMap<String, Object> map) {
//    ArrayList<Customer_postVO> list = this.customer_postDAOInter.list_by_servicecateno_search_paging(hashmap);
//    return list;
    
    /*
    예) 페이지당 10개의 레코드 출력
    1 page: WHERE r >= 1 AND r <= 10
    2 page: WHERE r >= 11 AND r <= 20
    3 page: WHERE r >= 21 AND r <= 30
      
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: now_page = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: now_page = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: now_page = 3, (3 - 1) * 10 --> 20
    */
    int begin_of_page = ((Integer)map.get("now_page") - 1) * Customer_post.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1: 1
    // 2 페이지 = 10 + 1: 11
    // 3 페이지 = 20 + 1: 21 
    int start_num = begin_of_page + 1;
    
    //  종료 rownum
    // 1 페이지 = 0 + 10: 10
    // 2 페이지 = 0 + 20: 20
    // 3 페이지 = 0 + 30: 30
    int end_num = begin_of_page + Customer_post.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
    map.put("start_num", start_num);
    map.put("end_num", end_num);
   
    ArrayList<Customer_postVO> list = this.customer_postDAOInter.list_by_servicecateno_search_paging(map);
    
    for (int i=0; i<list.size(); i++) {
      Customer_postVO customer_postVO = list.get(i);
      
      String title = customer_postVO.getServicetitle();
      String content = customer_postVO.getServicecontents();
      
      title = Tool.convertChar(title);
      content = Tool.convertChar(content);
      
      customer_postVO.setServicetitle(title);
      customer_postVO.setServicecontents(content);
    }
    
    return list;
  }

  @Override
  public int search_count(HashMap map) {
    int cnt = this.customer_postDAOInter.search_count(map);
    return cnt;
  }
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   * 페이징 UI는 개발자가 개별적으로 개발이 안되고 회사가 제공하는 소스를 이용하고 규칙을 따를 것.
   * @param cateno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param now_page     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int cateno, int search_count, int now_page, String word){ 
    int total_page = (int)(Math.ceil((double)search_count/Customer_post.RECORD_PER_PAGE)); // 전체 페이지 수 
    int total_grp = (int)(Math.ceil((double)total_page/Customer_post.PAGE_PER_BLOCK)); // 전체 그룹  수
    int now_grp = (int)(Math.ceil((double)now_page/Customer_post.PAGE_PER_BLOCK));  // 현재 그룹 번호
    
    // 1 group: 1, 2, 3 ... 9, 10
    // 2 group: 11, 12 ... 19, 20
    // 3 group: 21, 22 ... 29, 30
    int start_page = ((now_grp - 1) * Customer_post.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Customer_post.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
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
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    // 이전 10개 페이지로 이동
    // now_grp: 1 (1 ~ 10 page)
    // now_grp: 2 (11 ~ 20 page)
    // now_grp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
    int _now_page = (now_grp - 1) * Customer_post.PAGE_PER_BLOCK;  
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+Customer_post.LIST_FILE+"?&word="+word+"&now_page="+_now_page+"&cateno="+cateno+"'>이전</A></span>"); 
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
        str.append("<span class='span_box_1'><A href='"+Customer_post.LIST_FILE+"?word="+word+"&now_page="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
    // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
    // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
    _now_page = (now_grp * Customer_post.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+Customer_post.LIST_FILE+"?&word="+word+"&now_page="+_now_page+"&cateno="+cateno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
}
