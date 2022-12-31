package dev.mvc.customer_post;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
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

import dev.mvc.cate.CateVO;
import dev.mvc.servicecate.ServiceCateProcInter;
import dev.mvc.servicecate.ServiceCateVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class Customer_postCont {
  @Autowired
  @Qualifier("dev.mvc.customer_post.Customer_postProc")
  private Customer_postProc customer_postProc = null;
  @Autowired
  @Qualifier("dev.mvc.servicecate.ServiceCateProc")
  private ServiceCateProcInter servicecateProc = null;

  public Customer_postCont() {
    System.out.println("-> Customer_postCont created.");
  }

  // 등록 폼
  // http://localhost:9093/service/customer_post/create.do
  @RequestMapping(value = "/service/customer_post/create.do", method = RequestMethod.GET)
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

    mav.setViewName("/service/customer_post/create");

    return mav;
  }

  // 등록 처리
  // <FORM name='frm' method='POST' action='/service/customer_post/create.do'>
  // http://localhost:9093/service/customer_post/create.do
  @RequestMapping(value = "/service/customer_post/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, Customer_postVO customer_postVO) {
    ModelAndView mav = new ModelAndView();

//  로그인 여부 확인
    if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
      mav.addObject("msg", "로그인 하신 고객님만 이용하실 수 있습니다.");
//      mav.setViewName("/service/msg");
//      return mav;
      // ***임시 코드***
      customer_postVO.setMemberno(1);
    }

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
    MultipartFile mf = customer_postVO.getFile1MF();

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

    customer_postVO.setFile1(file1); // 순수 원본 파일명
    customer_postVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
    customer_postVO.setThumb1(thumb1); // 원본이미지 축소판
    customer_postVO.setSize1(size1); // 파일 크기
    // ------------------------------------------------------------------------------
    // 파일 전송 코드 종료
    // ------------------------------------------------------------------------------

    int cnt = this.customer_postProc.create(customer_postVO);

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

  // 고객 문의글 목록
//http://localhost:9093/service/customer_post/list_all.do
  @RequestMapping(value = "/service/customer_post/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_by_cateno_search_paging(HttpServletRequest request,
      @RequestParam(value = "servicecateno", defaultValue = "1") int servicecateno,
      @RequestParam(value = "word", defaultValue = "") String word,
      @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    // System.out.println("--> now_page: " + now_page);

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("servicecateno", servicecateno);
    map.put("word", word); // #{word}

    // 검색된 레코드 갯수
    int search_count = customer_postProc.search_count(map);
    mav.addObject("search_count", search_count);

    ArrayList<ServiceCateVO> serviceCateList = this.servicecateProc.list_all();
    mav.addObject("serviceCateList", serviceCateList);
    
//    ServiceCateVO servicecateVO = servicecateProc.read(servicecateno);
//    mav.addObject("servicecateVO", servicecateVO);

    // map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
    // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
    int start_num = search_count - (now_page) * 10;
    int end_num = search_count - (now_page - 1) * 10;
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    // 검색 목록
    ArrayList<Customer_postVO> list = customer_postProc.list_by_servicecateno_search_paging(map);
    mav.addObject("list", list);

//    /*
//     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
//     * 18 19 20 [다음]
//     * @param cateno 카테고리번호
//     * @param search_count 검색(전체) 레코드수
//     * @param now_page 현재 페이지
//     * @param word 검색어
//     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
//     */
//    String paging = customer_postProc.pagingBox(servicecateno, search_count, now_page, word);
//    mav.addObject("paging", paging);

    // mav.addObject("now_page", now_page);

    // 로그인 Cookie + 쇼핑카트 + CKEditor
    mav.setViewName("/service/customer_post/list_all");
    // -------------------------------------------------------------------------------

    return mav;
  }

  /**
   * 조회,
   * http://localhost:9093/service/customer_post/read.do?servicecateno=1&serviceno=1
   * 
   * @return
   */
  @RequestMapping(value = "/service/customer_post/read.do", method = RequestMethod.GET)
  public ModelAndView read(int servicecateno, int serviceno) {
    ModelAndView mav = new ModelAndView();

    // 글쓰기 권한 확인해서 권한 없으면 메세지 창으로 보내는 단계 필요
    // 관리자이거나 글쓴 본인인지 확인하면 될 것으로 보임

    ServiceCateVO servicecateVO = this.servicecateProc.read(servicecateno);
    mav.addObject("servicecateVO", servicecateVO);

    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);

    long size1 = customer_postVO.getSize1();
    customer_postVO.setSize1_label(Tool.unit(size1)); // 93848 -> 92 KB

    mav.addObject("customer_postVO", customer_postVO);

    mav.setViewName("/service/customer_post/read");

    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9091/contents/update_text.do?contentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/service/customer_post/update.do", method = RequestMethod.GET)
  public ModelAndView update(int serviceno) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<ServiceCateVO> serviceCateList = this.servicecateProc.list_all();
    mav.addObject("serviceCateList", serviceCateList);
    
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);
    mav.addObject("customer_postVO", customer_postVO);
    
    mav.setViewName("/service/customer_post/update");

    return mav; // forward
  }
  
  /**
   * 수정 처리
   */
  @RequestMapping(value = "/service/customer_post/update.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, @RequestParam(value = "checkBoxId", defaultValue = "0") boolean checkBoxId, Customer_postVO customer_postVO) {
    ModelAndView mav = new ModelAndView();
    
  //memberno도 확인, 관리자 여부 검사할때 본인인지도 확인할 수 있도록
//    if (this.adminProc.isAdmin(session)) {    
    if(true) {
      customer_postVO.setMemberno(1);
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      Customer_postVO customer_postVO_old = customer_postProc.read(customer_postVO.getServiceno());
      
      int cnt = 0;

      // 파일 수정 여부를 검사하여 true라면 작동
      if(checkBoxId == true) {
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        String file1saved = customer_postVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = customer_postVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
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
        MultipartFile mf = customer_postVO.getFile1MF();
            
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
            
        customer_postVO.setFile1(file1);
        customer_postVO.setFile1saved(file1saved);
        customer_postVO.setThumb1(thumb1);
        customer_postVO.setSize1(size1);
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------
      } else {  // 파일을 삭제하지 않을 경우
        // 기존의 값을 그대로 들고와서 덮어씌움
        customer_postVO.setFile1(customer_postVO_old.getFile1());
        customer_postVO.setFile1saved(customer_postVO_old.getFile1saved());
        customer_postVO.setThumb1(customer_postVO_old.getThumb1());
        customer_postVO.setSize1(customer_postVO_old.getSize1());
      }      
          
      cnt = this.customer_postProc.update(customer_postVO); // Oracle 처리

      mav.addObject("serviceno", customer_postVO.getServiceno());
      mav.addObject("servicecateno", customer_postVO.getServicecateno());
      mav.setViewName("redirect:/service/customer_post/list_all.do"); // request -> param으로 접근 전환
                
    } else {
      mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/contents/msg.do"); // GET
    }

    return mav; // forward
  }   
  
  /**
   * 삭제 폼
   * @param contentsno
   * @return
   */
  @RequestMapping(value="/service/customer_post/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int serviceno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);
    mav.addObject("customer_postVO", customer_postVO);
    
    ServiceCateVO servicecateVO = this.servicecateProc.read(customer_postVO.getServicecateno());
    mav.addObject("servicecateVO", servicecateVO);
    
    mav.setViewName("/service/customer_post/delete");  // /webapp/WEB-INF/views/contents/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9091/service/customer_post/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/service/customer_post/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int serviceno, String word,
                                        @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    Customer_postVO customer_postVO = this.customer_postProc.read(serviceno);
        
    String file1saved = customer_postVO.getFile1saved();
    String thumb1 = customer_postVO.getThumb1();
    long size1 = 0;
    boolean sw = false;
        
    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/service/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
    sw = Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
        
    cnt = this.customer_postProc.delete(serviceno); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("servicecateno", customer_postVO.getServicecateno());
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

    mav.addObject("servicecateno", customer_postVO.getServicecateno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/service/customer_post/list_all.do"); 
    
    return mav;
  }   
}
