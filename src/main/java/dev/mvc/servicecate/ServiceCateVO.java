package dev.mvc.servicecate;

/*
CREATE TABLE SERVICECATE(
    SERVICECATENO NUMERIC(10),
    SERVICETYPE_CONTENT VARCHAR(50),
    CNT NUMERIC(10),
    ORDER_INDEX NUMERIC(10),
    VISIBLE CHARACTER(1)
);
*/

public class ServiceCateVO {
  int servicecateno;
  String servicetype_content;
  int cnt;  
  int order_index;
  String visible;
  
  public int getServicecateno() {
    return servicecateno;
  }
  public void setServicecateno(int servicecateno) {
    this.servicecateno = servicecateno;
  }
  public String getServicetype_content() {
    return servicetype_content;
  }
  public void setServicetype_content(String servicetype_content) {
    this.servicetype_content = servicetype_content;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public int getOrder_index() {
    return order_index;
  }
  public void setOrder_index(int order_index) {
    this.order_index = order_index;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
}
