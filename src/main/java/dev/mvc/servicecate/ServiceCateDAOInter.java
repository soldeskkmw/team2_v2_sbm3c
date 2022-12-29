package dev.mvc.servicecate;

import java.util.ArrayList;

public interface ServiceCateDAOInter {
  /**
   * 카테고리 추가
   * @param servicetype_content
   * @return 성공여부
   */
  public int create(ServiceCateVO servicecateVO);
  
  /**
   * 카테고리 전체 목록을 가져옴
   * @return
   */
  public ArrayList<ServiceCateVO> list_all();
  
  public ServiceCateVO read(int servicecateno);
  
  public int update(ServiceCateVO servicecateVO);
  
  public int delete(int servicecateno);
  
  public int update_order_index_up(int servicecateno);
  
  public int update_order_index_down(int servicecateno);
  
  public int update_visible_y(int servicecateno);
  
  public int update_visible_n(int servicecateno);
}
