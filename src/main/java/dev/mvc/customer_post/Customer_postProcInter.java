package dev.mvc.customer_post;

import java.util.ArrayList;
import java.util.HashMap;

public interface Customer_postProcInter {
  public int create(Customer_postVO customer_postVO);
  
  public ArrayList<Customer_postVO> list_all();
  
  public Customer_postVO read(int serviceno);
  
  public int update(Customer_postVO customer_postVO);
  
  public int delete(int serviceno);
  
  public int count_all();
  
  public int count_all_by_servicecateno(int servicecateno);
  
  public int delete_all_by_servicecate(int servicecateno);
  
  public int search_count(HashMap map);
  
  public ArrayList<Customer_postVO> list_by_servicecateno_search(HashMap<String, Object> hashmap);
  
  public ArrayList<Customer_postVO> list_by_servicecateno_search_paging(HashMap<String, Object> hashmap);

}
