package dev.mvc.customer_post;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
  public ArrayList<Customer_postVO> list_by_servicecateno_search_paging(HashMap<String, Object> hashmap) {
    ArrayList<Customer_postVO> list = this.customer_postDAOInter.list_by_servicecateno_search_paging(hashmap);
    return list;
  }

  @Override
  public int search_count(HashMap map) {
    int cnt = this.customer_postDAOInter.search_count(map);
    return cnt;
  }
  
}
