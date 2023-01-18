package dev.mvc.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class PostCont {

  @Autowired
  @Qualifier("dev.mvc.post.PostProc")
  private PostProcInter postProc;
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;
  
  /** 업로드 파일 절대 경로 */
  private String uploadDir = Tool.getOSPath() + "/post/storage";
  
  public PostCont() {
    System.out.println("-> PostCont created");
  }
  
  /**
   * 새로고침 방지, POST -> POST 정보 삭제 -> GET -> msg.jsp
   * @return
   */
  @RequestMapping(value="/post/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url);
    
    return mav;
  }
  
  // 등록 폼
  // http://localhost:9093/post/create.do?cateno=1
  @RequestMapping(value="/post/create.do", method = RequestMethod.GET)
  public ModelAndView create(int cateno) {
    ModelAndView mav = new ModelAndView();

    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/post/create"); // /webapp/WEB-INF/views/post/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/post/create.do
   * @return
   */
  @RequestMapping(value = "/post/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, HttpServletRequest request, PostVO postVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      int adminno = (int)session.getAttribute("adminno");
      postVO.setAdminno(adminno);
      
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String postfile1 = "";          // 원본 파일명 image
      String postfile1saved = "";   // 저장된 파일명, image
      String postthumb1 = "";     // preview image

      // 기준 경로 확인
      String user_dir = System.getProperty("user.dir"); // 시스템 제공

      String upDir =  user_dir + "/src/main/resources/static/post/storage/"; // 절대 경로
      
      // 전송 파일이 없어도 postfile1MF 객체가 생성됨.
      MultipartFile mf = postVO.getPostfile1MF();
      
      postfile1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
      System.out.println("-> postfile1: " + postfile1);
      
      long postsize1 = mf.getSize();  // 파일 크기
      
      if (postsize1 > 0) { // 파일 크기 체크
        postfile1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(postfile1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          postthumb1 = Tool.preview(upDir, postfile1saved, 300, 200); 
        }
      }    
      
      postVO.setPostfile1(postfile1);                  // 순수 원본 파일명
      postVO.setPostfile1saved(postfile1saved); // 저장된 파일명(파일명 중복 처리)
      postVO.setPostthumb1(postthumb1);      // 원본이미지 축소판
      postVO.setPostsize1(postsize1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int cnt = this.postProc.create(postVO); 
      
      if (cnt == 1) {
          mav.addObject("code", "create_success");
      } else {
          mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      mav.addObject("cateno", postVO.getCateno()); // redirect parameter 적용
      
      mav.addObject("url", "/post/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/post/msg.do"); // GET
    } else {
      mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/post/msg.do"); // GET
    }
    return mav;
  }
  
  /**
   * 조회, http://localhost:9093/post/read.do?cateno=1&postno=1&postcnt=0
   * @return
   */
  @RequestMapping(value="/post/read.do", method=RequestMethod.GET)
  public ModelAndView read(int cateno, int postno,
                                         @RequestParam(value = "postcnt", defaultValue = "0") int postcnt,
                                         HttpServletRequest request, HttpServletResponse response) {
    
    ModelAndView mav = new ModelAndView();

//    int adminno = (int)session.getAttribute("adminno");
//    
//    AdminVO adminVO = this.adminProc.read(adminno);
//    mav.addObject("adminVO", adminVO);
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    PostVO postVO = this.postProc.read(postno);
    long postsize1=postVO.getPostsize1();
    postVO.setPostsize1_label(Tool.unit(postsize1));

 // 조회수 증가 및 cookie 사용 중복 증가 방지
    Cookie cookie = null;
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) { // 쿠키가 존재한다면
      for(Cookie oldcookie : cookies) {
        if (oldcookie.getName().equals("postView")) {
          cookie = oldcookie;
        }
      }
    }
    if(cookie != null) {
      if(!cookie.getValue().contains("[" + postno + "]")) {
        this.postProc.visit_cnt(postno);
        cookie.setValue(cookie.getValue() + "_[" + postno + "]");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
      }
    } else {
      this.postProc.visit_cnt(postno);
      Cookie newCookie = new Cookie("postView", "[" + postno + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }
    
    mav.addObject("postVO", postVO);
    mav.setViewName("/post/read"); // /webapp/WEB-INF/views/post/read.jsp
    return mav;
  }
  
  /**
   * admin + post INNER JOIN 목록, http://localhost:9093/post/list_all_admin.do
   * @return
   */
  @RequestMapping(value="/post/list_all_admin.do", method=RequestMethod.GET)
  public ModelAndView list_all_admin() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<AdminPostVO> list = this.postProc.list_all_admin();
    mav.addObject("list", list);
    
    mav.setViewName("/post/list_all_admin"); // /webapp/WEB-INF/views/post/list_all_admin.jsp
    
    return mav;
  }
  
  /**
   * cate + post INNER JOIN 목록, http://localhost:9093/post/list_all_cate.do
   * @return
   */
  @RequestMapping(value="/post/list_all_cate.do", method=RequestMethod.GET)
  public ModelAndView list_all_cate() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<CatePostVO> list = this.postProc.list_all_cate();
    mav.addObject("list", list);
    
    mav.setViewName("/post/list_all_cate"); // /webapp/WEB-INF/views/post/list_all_cate.jsp
    
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원 + Cookie
   * http://localhost:9093/post/list_by_cateno_search_paging.do?cateno=1&postword=카페&now_page=1
   * @param cateno
   * @param postword
   * @param now_page
   * @return
   */
  @RequestMapping(value ="/post/list_by_cateno_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_cateno_search_paging_cookie(
                                                                HttpServletRequest request,
                                                                @RequestParam(value = "cateno", defaultValue = "1") int cateno,
                                                                @RequestParam(value = "postword", defaultValue = "") String postword,
                                                                @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno); // #{cateno}
    map.put("postword", postword); // #{postword}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용
    
    // 검색 목록
    ArrayList<PostVO> list = postProc.list_by_cateno_search_paging(map);
    mav.addObject("list", list);
    // 검색된 레코드 갯수
    int search_count = postProc.search_count(map);
    mav.addObject("search_count", search_count);

    CateVO cateVO = cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);

    String paging = postProc.pagingBox(cateno, search_count, now_page, postword);
    mav.addObject("paging", paging);
    
    // 로그인 Cookie + CKEditor
    mav.setViewName("/post/list_by_cateno_search_paging_cookie_ck");  // /post/list_by_cateno_search_paging_cookie_ck.jsp ★
    
    // -------------------------------------------------------------------------------
    // 로그인 폼 출력 관련 쿠기  
    // -------------------------------------------------------------------------------
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String memberid = ""; // id 저장
    String id_save = ""; // id 저장 여부를 체크
    String memberpasswd = ""; // passwd 저장
    String passwd_save = ""; // passwd 저장 여부를 체크

    if (cookies != null) {  // Cookie 변수가 있다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("memberid")){
          memberid = cookie.getValue();                                 // Cookie에 저장된 id
        }else if(cookie.getName().equals("id_save")){
          id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
        }else if (cookie.getName().equals("memberpasswd")){
          memberpasswd = cookie.getValue();                          // Cookie에 저장된 password
        }else if(cookie.getName().equals("passwd_save")){
          passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
        }
      }
    }
    
    mav.addObject("memberid", memberid); 
    mav.addObject("id_save", id_save);
    mav.addObject("memberpasswd", memberpasswd);
    mav.addObject("passwd_save", passwd_save);
    // -------------------------------------------------------------------------------
    
    return mav;
    
  }
  
  /**
   * Grid 형태의 화면 구성 http://localhost:9093/post/list_by_cateno_grid.do
   * @return
   */
  @RequestMapping(value="/post/list_by_cateno_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_cateno_grid(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    ArrayList<PostVO> list = this.postProc.list_by_cateno(cateno);
    mav.addObject("list", list);

    // 테이블 이미지 기반, /webapp/WEB-INF/views/post/list_by_cateno_grid.jsp
    mav.setViewName("/post/list_by_cateno_grid");

    return mav; // forward
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/post/update_text.do?postno=1
   * @return
   */
  @RequestMapping(value="/post/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(int postno) {
    ModelAndView mav = new ModelAndView();
    
    PostVO postVO = this.postProc.read(postno);
    mav.addObject("postVO", postVO);
    
    CateVO cateVO = this.cateProc.read(postVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/post/update_text"); // /WEB-INF/views/post/update_text.jsp

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/post/update_text.do?postno=1
   * @return
   */
  @RequestMapping(value = "/post/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, PostVO postVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      int cnt = this.postProc.update_text(postVO);
      
      mav.addObject("postno", postVO.getPostno());
      mav.addObject("cateno", postVO.getCateno());
      mav.setViewName("redirect:/post/read.do");
    } else {
      mav.addObject("url", "/admin/login_need");
      mav.setViewName("redirect:/post/msg.do");
    }
    
    return mav;
  }
  
  /**
   * 파일 수정 폼
   * http://localhost:9093/post/update_file.do?postno=1
   * @return
   */
  @RequestMapping(value="/post/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file(int postno) {
    ModelAndView mav = new ModelAndView();
    
    PostVO postVO = this.postProc.read(postno);
    mav.addObject("postVO", postVO);
    
    CateVO cateVO = this.cateProc.read(postVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/post/update_file"); // /WEB-INF/views/post/update_file.jsp

    return mav; // forward
  }
  
  /**
   * 파일 수정 처리
   * http://localhost:9093/post/update_file.do
   * @return
   */
  @RequestMapping(value = "/post/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, PostVO postVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      PostVO postVO_old = postProc.read(postVO.getPostno());
      int cnt = 0;
      
      // -------------------------------------------------------------------
      // 파일 삭제 코드 시작
      // -------------------------------------------------------------------
      String postfile1saved = postVO_old.getPostfile1saved();  // 실제 저장된 파일명
      String postthumb1 = postVO_old.getPostthumb1();       // 실제 저장된 preview 이미지 파일명
      long postsize1 = 0;
      boolean sw = false;
          
      // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/post/storage/
      String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/post/storage/"; // 절대 경로

      sw = Tool.deleteFile(upDir, postfile1saved);  // 실제 저장된 파일삭제
      sw = Tool.deleteFile(upDir, postthumb1);     // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료 시작
      // -------------------------------------------------------------------
          
      // -------------------------------------------------------------------
      // 파일 전송 코드 시작
      // -------------------------------------------------------------------
      String postfile1 = "";          // 원본 파일명 image
      
      MultipartFile mf = postVO.getPostfile1MF();
      postfile1 = mf.getOriginalFilename();
      postsize1 = mf.getSize();
      
      if(postsize1 > 0) {
        postfile1saved = Upload.saveFileSpring(mf, upDir);
        
        if (Tool.isImage(postfile1saved)) { // 이미지인지 검사
          postthumb1 = Tool.preview(upDir, postfile1saved, 250, 200); 
        }
      } else {  // 파일이 삭제만 되고 새로 올리지 않는 경우
        postfile1="";
        postfile1saved="";
        postthumb1="";
        postsize1=0;
      }
      
      postVO.setPostfile1(postfile1);
      postVO.setPostfile1saved(postfile1saved);
      postVO.setPostthumb1(postthumb1);
      postVO.setPostsize1(postsize1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
      
      cnt = this.postProc.update_file(postVO);  //Oracle 처리
      
      mav.addObject("postno", postVO.getPostno());
      mav.addObject("cateno", postVO.getCateno());
      mav.setViewName("redirect:/post/read.do");
    } else {
      mav.addObject("url", "/admin/login_need");
      mav.setViewName("redirect:/post/msg.do");
    }
    
    return mav;
  }
  
  /**
   * 삭제 폼 http://localhost:9093/post/delete.do?postno=1
   * @param postno
   * @return
   */
  @RequestMapping(value="/post/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int postno) {
    ModelAndView mav = new ModelAndView();
    
    PostVO postVO = this.postProc.read(postno);
    mav.addObject("postVO", postVO);
    
    CateVO cateVO = this.cateProc.read(postVO.getCateno());
    mav.addObject("cateVO", cateVO);
    
    mav.setViewName("/post/delete"); // /WEB-INF/views/post/delete.jsp

    return mav; // forward
  }
  
  /**
   * 삭제 처리
   * http://localhost:9093/post/delete.do
   * @return
   */
  @RequestMapping(value = "/post/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int postno, String postword,
                                            @RequestParam(value="now_page", defaultValue="1") int now_page) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    PostVO postVO = postProc.read(postno);
    
    String postfile1saved = postVO.getPostfile1saved();  // 실제 저장된 파일명
    String postthumb1 = postVO.getPostthumb1();       // 실제 저장된 preview 이미지 파일명
    long postsize1 = 0;
    boolean sw = false;
        
    // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/post/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/post/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, postfile1saved);  // 실제 저장된 파일삭제
    sw = Tool.deleteFile(upDir, postthumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    cnt = this.postProc.delete(postno);  //DBMS 삭제
    
      // -------------------------------------------------------------------
      // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
      // -------------------------------------------------------------------
    HashMap<String, Object> page_map = new HashMap<String, Object>();
    page_map.put("cateno", postVO.getCateno());
    page_map.put("postword", postword);
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    if (postProc.search_count(page_map) % Post.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1;
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    // -------------------------------------------------------------------------------------
    mav.addObject("cateno", postVO.getCateno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/post/list_by_cateno_search_paging.do");
    
    return mav;
  }
  
  // GET 요청
  public String mf_recommend(String django_url) throws MalformedURLException, IOException {
    System.out.println("-> django_url: " + django_url);
    
    URL url = new URL(django_url);
    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
    
    int statusCode = httpConn.getResponseCode();        
<<<<<<< HEAD
//    System.out.println((statusCode == 200) ? "success" : "fail");
//    System.out.println("Response code: " + statusCode);
=======
    System.out.println((statusCode == 200) ? "success" : "fail");
    System.out.println("Response code: " + statusCode);
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    
    BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
    String line=br.readLine();
    br.close();
    
    return line;
  }
  
  /**
   * 추천 목록, http://localhost:9093/post/mf_post_member.do
   * @return
   */
  @RequestMapping(value="/post/mf_post_member.do", method=RequestMethod.GET)
  public ModelAndView mf_post_member(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    // Spring boot -> Django로 요청을 보냄 -> JSON 문자열 수신
    int memberno = (int)session.getAttribute("memberno");
    
    String source = "";
    try {
      source = mf_recommend("http://127.0.0.1:8000/recommend_post/mf_post?memberno=" + memberno);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
<<<<<<< HEAD
=======
    System.out.println("-> source: " + source);

>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    // 포스트 번호를 추출
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    JSONArray json = new JSONArray(source); // String -> JSON
    ArrayList<String> postno_list = new ArrayList<String>();
    
    for (int index=0; index < json.length(); index++) {
      JSONObject obj = (JSONObject)json.opt(index);
      String post_no = obj.optString("post_no"); // 포스트 번호만 추출
<<<<<<< HEAD
=======
      System.out.println("-> movie_id: " + post_no);
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
      postno_list.add(post_no);
    }
    
    hashMap.put("postno_list", postno_list);
    
    // 추천 상품 목록 읽기
    ArrayList<PostVO> list = this.postProc.mf_post_member(hashMap);
    mav.addObject("list", list);
    
    // 유형 1: 테이블
    // /webapp/WEB-INF/views/post/mf_post_member.jsp
    // mav.setViewName("/post/mf_post_member"); 

    // 유형 2: 그리드
    // /webapp/WEB-INF/views/post/mf_post_member_grid.jsp
    mav.setViewName("/post/mf_post_member_grid"); 
    
    return mav;
  }
  
  /**
   * 시작 페이지 추천 목록, http://localhost:9093/post/mf_post_member_grid_index.do
   * @return
   */
  @RequestMapping(value="/post/mf_post_member_grid_index.do", method=RequestMethod.GET)
  public ModelAndView mf_post_member_index(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    // Spring boot -> Django로 요청을 보냄 -> JSON 문자열 수신
    int memberno = (int)session.getAttribute("memberno");
    
    String source = "";
    try {
      source = mf_recommend("http://127.0.0.1:8000/recommend_post/mf_post?memberno=" + memberno);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
<<<<<<< HEAD
=======
    System.out.println("-> source: " + source);

>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    // 포스트 번호를 추출
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    JSONArray json = new JSONArray(source); // String -> JSON
    ArrayList<String> postno_list = new ArrayList<String>();
    
    for (int index=0; index < json.length(); index++) {
      JSONObject obj = (JSONObject)json.opt(index);
      String post_no = obj.optString("post_no"); // 포스트 번호만 추출
<<<<<<< HEAD
=======
      System.out.println("-> post_no: " + post_no);
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
      postno_list.add(post_no);
    }
    
    hashMap.put("postno_list", postno_list);
    
    // 추천 상품 목록 읽기
    ArrayList<PostVO> list = this.postProc.mf_post_member(hashMap);
    mav.addObject("list", list);
    
    // 유형 1: 테이블
    // /webapp/WEB-INF/views/post/mf_post_member.jsp
    // mav.setViewName("/post/mf_post_member"); 

    // 유형 2: 그리드
    // /webapp/WEB-INF/views/post/mf_post_member_grid_index.jsp
    mav.setViewName("/post/mf_post_member_grid_index"); 
    
    return mav;
  }
  
  
}



