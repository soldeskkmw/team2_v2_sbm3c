package dev.mvc.servicecate;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("dev.mvc.servicecate.ServiceCateProc")
public class ServiceCateProc implements ServiceCateProcInter {
  // CateDAOInter interface 만 존재하고 구현 class는 존재하지 않음.
  // interface는 객체를 만들 수 없고 할당만 받을 수 있음.
  
  @Autowired
  private ServiceCateDAOInter servicecateDAO;
  
  public ServiceCateProc() {
  }
  
  @Override
  public int create(ServiceCateVO servicecateVO) {
    int cnt = this.servicecateDAO.create(servicecateVO); // MyBATIS가 처리한 레코드 갯수가 return됨
    return cnt;
  }

  @Override
  public ArrayList<ServiceCateVO> list_all() {
    ArrayList<ServiceCateVO> list = this.servicecateDAO.list_all();
    return list;
  }

  @Override
  public ServiceCateVO read(int servicecateno) {
    ServiceCateVO servicecateVO = this.servicecateDAO.read(servicecateno);
    return servicecateVO;
  }

  @Override
  public int update(ServiceCateVO servicecateVO) {
    int cnt = this.servicecateDAO.update(servicecateVO);
    return cnt;
  }

  @Override
  public int delete(int servicecateno) {
    int cnt = this.servicecateDAO.delete(servicecateno);
    return cnt;
  }

  @Override
  public int update_order_index_up(int servicecateno) {
    int cnt = this.servicecateDAO.update_order_index_up(servicecateno);
    return cnt;
  }

  @Override
  public int update_order_index_down(int servicecateno) {
    int cnt = this.servicecateDAO.update_order_index_down(servicecateno);
    return cnt;
  }

  @Override
  public int update_visible_y(int servicecateno) {
    int cnt = this.servicecateDAO.update_visible_y(servicecateno);
    return cnt;
  }

  @Override
  public int update_visible_n(int servicecateno) {
    int cnt = this.servicecateDAO.update_visible_n(servicecateno);
    return cnt;
  }
}

