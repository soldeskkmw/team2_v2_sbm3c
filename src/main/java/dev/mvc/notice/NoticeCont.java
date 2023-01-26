package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.tool.Tool;


// import dev.mvc.contents.ContentsProcInter;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc") 
  private NoticeProcInter noticeProc; // "dev.mvc.notice.NoticeProc" 이름으로 지정된 클래스의 객체가 자동 생성되어 할당
 
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;
  /*
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc") 
  private ContentsProcInter contentsProc;  // "dev.mvc.contents.ContentsProc" 이름으로 지정된 클래스의 객체가 자동 생성되어 할당
 */
  
  public NoticeCont() {
    System.out.println("-> NoticeCont created.");
    // System.out.println("-> CateCont: " + (cateProc == null));
  }
  
  // 등록 폼
  // http://localhost:9093/notice/create.do
  @RequestMapping(value="/notice/notice_create.do", method = RequestMethod.GET)
  public ModelAndView notice_create() {
    System.out.println("-> create()");
    
    ModelAndView mav = new ModelAndView();
//    JSP View path
//    spring.mvc.view.prefix=/WEB-INF/views/
//    spring.mvc.view.suffix=.jsp
    mav.setViewName("/notice/notice_create"); // /webapp/WEB-INF/views/notice/create.jsp
    
    return mav;
  }

  // 등록 처리
  // <FORM name='frm' method='POST' action='./create.do'>
  // http://localhost:9093/notice/notice.do
  @RequestMapping(value="/notice/notice_create.do", method = RequestMethod.POST)
  public ModelAndView notice_create(NoticeVO noticeVO) {
    // System.out.println("-> noticeVO name: " + noticeVO.getName());
    
//    System.out.println("-> create(NoticeVO noticeVO)");
//    System.out.println("-> NoticeCont create post: " + (noticeProc == null));
    
    // noticeVO 객체 자동 생성, <FORM> 태그의 값이 자동 저장됨.
    // NoticeVO noticeVO = new NoticeVO();
    // noticeVO.setName(request.getParameter("name"));
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.noticeProc.notice_create(noticeVO);
    
    if (cnt == 1) {
      mav.addObject("code", "create_success");
      // request.setAttribute("code", "create_success");
    } else {
      mav.addObject("code", "create_fail");
    }
    
    mav.addObject("cnt", cnt);
    // mav.addObject("code", "create_fail"); // 실패 테스트
    // mav.addObject("cnt", 0);  // 실패 테스트
    // request.setAttribute("cnt", cnt);
    
//    JSP View path
//    spring.mvc.view.prefix=/WEB-INF/views/
//    spring.mvc.view.suffix=.jsp
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/notice/notice_list.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/notice/notice_list"); // /webapp/WEB-INF/views/notice/notice_list.jsp X
    } else { // 등록 실패
      mav.setViewName("/notice/notice_msg"); // /webapp/WEB-INF/views/notice/msg.jsp      
    }
    
    return mav;
  }

  
  /**
   * 수정 폼
   * http://localhost:9093/notice/notice_read_update.do?noticeno=1
   * 
   * @return
   */
  @RequestMapping(value = "/notice/notice_read_update.do", method = RequestMethod.GET)
  public ModelAndView notice_read_update(int noticeno) {
    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    
    mav.setViewName("/notice/notice_read_update"); // /WEB-INF/views/contents/update_text.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/notice/notice_read_update.do?noticeno=1
   * 
   * @return
   */
  @RequestMapping(value = "/notice/notice_read_update.do", method = RequestMethod.POST)
  public ModelAndView notice_read_update(HttpSession session, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      int cnt = this.noticeProc.notice_read_update(noticeVO);
      
      // URL에 파라미터의 전송
      // mav.setViewName("redirect:/contents/read.do?contentsno=" + contentsVO.getContentsno() + "&cateno=" + contentsVO.getCateno());             

      // mav 객체 이용
      mav.addObject("noticeno", noticeVO.getNoticeno());

      mav.setViewName("redirect:/notice/notice_read.do");      
    } else {
      mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/notice/notice_msg.do"); // GET
    }
        
    return mav; // forward
  }
  
  /**
   * 삭제 폼
   * @param contentsno
   * @return
   */
  @RequestMapping(value="/notice/notice_read_delete.do", method=RequestMethod.GET )
  public ModelAndView notice_read_delete(int noticeno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    
    mav.setViewName("/notice/notice_read_delete");  // /webapp/WEB-INF/views/contents/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9093/notice/notice_read_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/notice_read_delete.do", method = RequestMethod.POST)
  public ModelAndView notice_read_delete(int noticeno, String noticeword) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    NoticeVO noticeVO = noticeProc.notice_read(noticeno);
        
    String noticetitle = noticeVO.getNoticetitle();
    String noticecontent = noticeVO.getNoticecontent();

    long size1 = 0;
    boolean sw = false;
        
    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notice/storage/"; // 절대 경로

    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
        
    cnt = this.noticeProc.notice_read_delete(noticeno); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
//    HashMap<String, Object> page_map = new HashMap<String, Object>();
//
//    page_map.put("noticeword", noticeword);
//    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
//    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
//    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
//    if (noticeProc.search_count(page_map) % Contents.RECORD_PER_PAGE == 0) {
//      now_page = now_page - 1;
//      if (now_page < 1) {
//        now_page = 1; // 시작 페이지
//      }
//    }
    // -------------------------------------------------------------------------------------

    mav.setViewName("redirect:/notice/notice_list.do"); 
    
    return mav;
  }   
  
  /**
   * 조회, http://localhost:9093/notice/notice_list.do
   * @return
   */
  @RequestMapping(value="/notice/notice_list.do", method=RequestMethod.GET)
  public ModelAndView notice_list() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<NoticeVO> notice_list = this.noticeProc.notice_list();
    mav.addObject("notice_list", notice_list);

    mav.setViewName("/notice/notice_list"); // /webapp/WEB-INF/views/notice/notice_read.jsp
    
    return mav;
  }
  
  /**
   * 조회, http://localhost:9093/notice/notice_read.do?noticeno=1&noticeword=&now_page=
   * @return
   */
  @RequestMapping(value="/notice/notice_read.do", method=RequestMethod.GET)
  public ModelAndView notice_read(int noticeno) {
    
    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.notice_read(noticeno);
    
//    long size1 = noticeVO.getSize1();
//    contentsVO.setSize1_label(Tool.unit(size1)); // 93848 -> 92 KB
        
    mav.addObject("noticeVO", noticeVO);
    
    mav.setViewName("/notice/notice_read"); // /webapp/WEB-INF/views/notice/notice_read.jsp
    
    return mav;
  }

  
}





