package dev.mvc.admin_reply;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer_post.Customer_postProc;
import dev.mvc.customer_post.Customer_postVO;
import dev.mvc.servicecate.ServiceCateProcInter;
import dev.mvc.servicecate.ServiceCateVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

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
  // http://localhost:9093/service/admin_reply/create.do?serviceno=1
  @RequestMapping(value = "/service/admin_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, int serviceno) {
    ModelAndView mav = new ModelAndView();

    // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("adminno") == null || session.getAttribute("adminno") == "") {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "관리자만 이용할 수 있는 기능입니다.");
      mav.setViewName("/service/msg");
      return mav;
    }

    ArrayList<Customer_postVO> list = this.customer_postProc.list_all();
    mav.addObject("list", list);
    
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);

    long size1 = customer_postVO.getSize1();
    customer_postVO.setSize1_label(Tool.unit(size1)); // 93848 -> 92 KB
    mav.addObject("customer_postVO", customer_postVO);

    mav.setViewName("/service/admin_reply/create");

    return mav;
  }
  

  // 등록 처리
  // <FORM name='frm' method='POST' action='/service/admin_reply/create.do'>
  // http://localhost:9093/service/admin_reply/create.do
  @RequestMapping(value = "/service/admin_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, Admin_replyVO admin_replyVO) {
    ModelAndView mav = new ModelAndView();

    // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("adminno") == null || session.getAttribute("adminno") == "") {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "관리자만 이용할 수 있는 기능입니다.");
      mav.setViewName("/service/msg");
      return mav;
    }

    // 이미 답글이 등록되었는지 확인
    if(this.admin_replyProc.read(admin_replyVO.getServiceno()) != null) {
      mav.addObject("type", "삭제 오류");
      mav.addObject("msg", "이미 답글이 달린 게시물입니다.");
      mav.setViewName("/service/msg"); 
      return mav;
    }
    
    admin_replyVO.setAdminno((int)session.getAttribute("adminno"));

    // ------------------------------------------------------------------------------
    // 파일 전송 코드 시작
    // ------------------------------------------------------------------------------
    String file1 = ""; // 원본 파일명 image
    String file1saved = ""; // 저장된 파일명, image
    String thumb1 = ""; // preview image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir"); // 시스템 제공
    // System.out.println("-> User dir: " + user_dir);
    // --> User dir: C:\kd\ws_java\resort_v1sbm3c

    // 파일 접근임으로 절대 경로 지정, static 폴더 지정
    // 완성된 경로
    // C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage
    String upDir = user_dir + "/src/main/resources/static/service/storage/"; // 절대 경로
    // System.out.println("-> upDir: " + upDir);

    // 전송 파일이 없어도 file1MF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF'
    // value='' placeholder="파일 선택">
    MultipartFile mf = admin_replyVO.getFile1MF();

    file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
    System.out.println("-> file1: " + file1);

    long size1 = mf.getSize(); // 파일 크기

    if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1saved = Upload.saveFileSpring(mf, upDir);

      if (Tool.isImage(file1saved)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
        thumb1 = Tool.preview(upDir, file1saved, 200, 150);
      }

    }

    admin_replyVO.setFile1(file1); // 순수 원본 파일명
    admin_replyVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
    admin_replyVO.setThumb1(thumb1); // 원본이미지 축소판
    admin_replyVO.setSize1(size1); // 파일 크기
    // ------------------------------------------------------------------------------
    // 파일 전송 코드 종료
    // ------------------------------------------------------------------------------

    int cnt = this.admin_replyProc.create(admin_replyVO);

    if (cnt == 1) {
      mav.addObject("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }

    mav.addObject("cnt", cnt);

    if (cnt > 0) { // 정상 등록
      System.out.println(1);
      mav.setViewName("redirect:/service/customer_post/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    } else { // 등록 실패
      System.out.println(0);
      mav.setViewName("/service/msg"); // /webapp/WEB-INF/views/service/msg.jsp
    }

    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/service/admin_reply/update.do?serviceno=1
   * 
   * @return
   */
  @RequestMapping(value = "/service/admin_reply/update.do", method = RequestMethod.GET)
  public ModelAndView update(HttpSession session, int serviceno) {
    ModelAndView mav = new ModelAndView();
    
    // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("adminno") == null || session.getAttribute("adminno") == "") {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "관리자만 이용할 수 있는 기능입니다.");
      mav.setViewName("/service/msg");
      return mav;
    }
    
    ArrayList<ServiceCateVO> serviceCateList = this.servicecateProc.list_all();
    mav.addObject("serviceCateList", serviceCateList);
    
    Admin_replyVO admin_replyVO = this.admin_replyProc.read(serviceno);
    mav.addObject("admin_replyVO", admin_replyVO);
    
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);
    mav.addObject("customer_postVO", customer_postVO);
    
    mav.setViewName("/service/admin_reply/update");

    return mav; // forward
  }
  
  /**
   * 수정 처리
   */
  @RequestMapping(value = "/service/admin_reply/update.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, @RequestParam(value = "checkBoxId", defaultValue = "0") boolean checkBoxId, Admin_replyVO admin_replyVO) {
    ModelAndView mav = new ModelAndView();
    
    // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
    Admin_replyVO admin_replyVO_old = admin_replyProc.read(admin_replyVO.getServiceno());
    
    if(session.getAttribute("adminno") != null && session.getAttribute("adminno") != "") {
      admin_replyVO.setAdminno((int)session.getAttribute("adminno"));      
      
      int cnt = 0;

      // 파일 수정 여부를 검사하여 true라면 작동
      if(checkBoxId == true) {
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        String file1saved = admin_replyVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = admin_replyVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
        long size1 = 0;
        boolean sw = false;
            
        // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
        String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/service/storage/"; // 절대 경로

        sw = Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
        sw = Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
        // -------------------------------------------------------------------
            
        // -------------------------------------------------------------------
        // 파일 전송 코드 시작
        // -------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image

        // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
        // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로
            
        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = admin_replyVO.getFile1MF();
            
        file1 = mf.getOriginalFilename(); // 원본 파일명
        size1 = mf.getSize();  // 파일 크기
            
        if (size1 > 0) { // 파일 크기 체크
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
            thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
          }
          
        } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
          file1="";
          file1saved="";
          thumb1="";
          size1=0;
        }
            
        admin_replyVO.setFile1(file1);
        admin_replyVO.setFile1saved(file1saved);
        admin_replyVO.setThumb1(thumb1);
        admin_replyVO.setSize1(size1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
      } else {  // 파일을 삭제하지 않을 경우
        // 기존의 값을 그대로 들고와서 덮어씌움
        admin_replyVO.setFile1(admin_replyVO_old.getFile1());
        admin_replyVO.setFile1saved(admin_replyVO_old.getFile1saved());
        admin_replyVO.setThumb1(admin_replyVO_old.getThumb1());
        admin_replyVO.setSize1(admin_replyVO_old.getSize1());
      }      
          
      cnt = this.admin_replyProc.update(admin_replyVO); // Oracle 처리

      mav.addObject("serviceno", admin_replyVO.getServiceno());
      mav.addObject("adminreplyno", admin_replyVO.getAdminreplyno());
      mav.setViewName("redirect:/service/customer_post/list_all.do"); // request -> param으로 접근 전환
                
    } else {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "관리자만 이용할 수 있는 기능입니다.");
      mav.setViewName("/service/msg"); 
    }

    return mav; // forward
  }   
  
  /**
   * 삭제 폼
   * @param contentsno
   * @return
   */
  @RequestMapping(value="/service/admin_reply/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(HttpSession session, int serviceno) { 
    ModelAndView mav = new  ModelAndView();
    
 // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("adminno") == null || session.getAttribute("adminno") == "") {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "로그인 하신 고객님만 이용하실 수 있습니다.");
      mav.setViewName("/service/msg");
      return mav;
    }
    
    // 삭제할 정보를 조회하여 확인
    Admin_replyVO admin_replyVO = this.admin_replyProc.read(serviceno);
    mav.addObject("admin_replyVO", admin_replyVO);
    
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);
    mav.addObject("customer_postVO", customer_postVO);
    
    ServiceCateVO servicecateVO = this.servicecateProc.read(customer_postVO.getServicecateno());
    mav.addObject("servicecateVO", servicecateVO);
    
    mav.setViewName("/service/admin_reply/delete");  // /webapp/WEB-INF/views/contents/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9093/service/admin_reply/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/service/admin_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpSession session, int serviceno, String word,
                                        @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
 // 로그인 여부 확인 후 처리 필요
    if (session.getAttribute("adminno") == null || session.getAttribute("adminno") == "") {
      mav.addObject("type", "권한 오류");
      mav.addObject("msg", "로그인 하신 고객님만 이용하실 수 있습니다.");
      mav.setViewName("/service/msg"); 
      return mav;
    }
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    Admin_replyVO admin_replyVO = this.admin_replyProc.read(serviceno);
        
    String file1saved = admin_replyVO.getFile1saved();
    String thumb1 = admin_replyVO.getThumb1();
    long size1 = 0;
    boolean sw = false;
        
    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/service/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
    sw = Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
        
    cnt = this.admin_replyProc.delete_by_serviceno(serviceno); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("serviceno", admin_replyVO.getServiceno());
    page_map.put("word", word);
    /*
     * 페이징을 아직 제대로 적용하지 않았으므로...
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    if (customer_postProc.search_count(page_map) % Contents.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1;
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    */
    // -------------------------------------------------------------------------------------

    mav.addObject("serviceno", admin_replyVO.getServiceno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/service/customer_post/list_all.do"); 
    
    return mav;
  }
}
