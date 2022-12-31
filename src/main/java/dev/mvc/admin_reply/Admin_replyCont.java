package dev.mvc.admin_reply;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer_post.Customer_postProc;
import dev.mvc.customer_post.Customer_postVO;
import dev.mvc.servicecate.ServiceCateProcInter;
import dev.mvc.servicecate.ServiceCateVO;

@Controller
public class Admin_replyCont {
  @Autowired
  @Qualifier("dev.mvc.admin_reply.Admin_replyProc")
  private Admin_replyProc admin_replyProc = null;
  @Autowired
  @Qualifier("dev.mvc.customer_post.Customer_postProc")
  private Customer_postProc customer_postProc = null;
  @Autowired
  @Qualifier("dev.mvc.servicecate.ServiceCateProc")
  private ServiceCateProcInter servicecateProc = null;

  public Admin_replyCont() {
    System.out.println("-> Admin_replyCont created.");
  }
  
  // 등록 폼
  // http://localhost:9093/service/customer_post/create.do
  @RequestMapping(value = "/service/admin_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    ModelAndView mav = new ModelAndView();

    // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("id") == null || session.getAttribute("id") == "") {
      System.out.println("로그인되지 않은 회원");
//      mav.addObject("msg", "로그인 하신 고객님만 이용하실 수 있습니다.");
//      mav.setViewName("/service/msg");
//      return mav;
    }

    ArrayList<ServiceCateVO> serviceCateList = this.servicecateProc.list_all();
    mav.addObject("serviceCateList", serviceCateList);

    ArrayList<Customer_postVO> list = this.customer_postProc.list_all();
    mav.addObject("list", list);

    mav.setViewName("/service/admin_reply/create");

    return mav;
  }
}
