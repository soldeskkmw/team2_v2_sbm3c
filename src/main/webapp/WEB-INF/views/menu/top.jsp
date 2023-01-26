<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.cate.CateVO" %>

<script type="text/javascript">
  function chatbot() {
//     var url = 'http://127.0.0.1:8000/chatbot/chatting';
    var url = 'http://whwjdrb1009.asuscomm.com:8000/chatbot/chatting';
    var win = window.open(url, 'AI 서비스', 'width=800px, height=700px');

    var x = (screen.width - 800) / 2;
    var y = (screen.height - 660) / 2;

    win.moveTo(x, y); // 화면 중앙으로 이동
  }
<<<<<<< HEAD

  function type2_recommend_post() {
	    var url = 'http://localhost:9093/type2_recommend_post/start.do';
	    var win = window.open(url, 'AI 서비스', 'width=1000px, height=750px');

	    var x = (screen.width - 1000) / 2;
	    var y = (screen.height - 750) / 2;

	    win.moveTo(x, y); // 화면 중앙으로 이동
	  }
=======
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
</script>

<DIV class='container_main'> 
  <!-- 헤더 -->
  <div class="header">
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <li class="nav-item dropdown" style='list-style-type: none;'>
            <a class="nav-link" data-toggle="dropdown"  id="navbardrop" href="#" ><img alt="menu" src="/cate/images/menu.png" style='width: 24px;'></a>
              <div class="dropdown-menu">
 <!--             <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=1">Festival</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=2">Hotels</a>  -->
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=3">Trip</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=4">Life</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=5">Food</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=6">Cafe</a>
               <a class="dropdown-item" href="/notice/notice_list.do">공지사항</a>
               <a class="dropdown-item" href="/survey/list_all.do">설문조사</a>
               <a class="dropdown-item" href="/service/servicecate/list_all.do">고객센터</a>
               <c:choose>
               <c:when test="${sessionScope.memberpasswd != null}">
                  <div class="dropdown-divider"></div>     
                  <a class="dropdown-item" href="/member/mypage.do">마이페이지</a>
                </c:when>
                </c:choose>
              </div>
          </li>
          <a class="navbar-brand" href="/">GoingShare</a>
          <div class="collapse navbar-collapse" id="navbarCollapse">
              <ul class="navbar-nav mr-auto">
                  <%
                  ArrayList<CateVO> list = (ArrayList<CateVO>)request.getAttribute("list");
                  for (int index=0; index < list.size(); index++) {
                    CateVO cateVO = list.get(index);
                  %>
                    <li class="nav-item">
                      <a class="nav-link" href="/post/list_by_cateno_search_paging.do?cateno=<%=cateVO.getCateno() %>"><%=cateVO.getCatename() %></a>
                    </li>
                  <%
                  }      
                  %>
                  </ul>
                  <ul class="navbar-nav mr-end">
                  <li class="nav-item">
                      <c:choose>
                          <c:when test="${sessionScope.memberpasswd == null}">
                              <a class="nav-link" href="/member/login.do">로그인</a>
                          </c:when>
                          <c:otherwise>
                              <a class="nav-link" href='/member/logout.do'>${sessionScope.membername } 로그아웃</a>
                          </c:otherwise>
                      </c:choose>
                  </li>
                  </ul>
                  
                  <ul class="navbar-nav mr-end">
                  <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
                      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">회원</a>
                      <div id ="dropdown-menu" class="dropdown-menu">
                      <c:choose>
                          <c:when test="${sessionScope.memberpasswd == null}">
                            <a class="dropdown-item" href="/member/create.do">회원 가입</a>
                            <a class="dropdown-item" href="/member/id_search.do">아이디 찾기</a>
                            <a class="dropdown-item" href="/member/passwd_search.do">비밀번호 찾기</a>
                          </c:when>
                          <c:otherwise>
                            <a class="dropdown-item" href="/member/read.do">가입 정보</a>
                            <a class="dropdown-item" href="/member/passwd_update.do">비밀번호 변경</a>
                            <a class="dropdown-item" href="/member/read.do">회원 정보 수정</a>
                            <a class="dropdown-item" href="/member/unregister.do">회원 탈퇴</a>
                          </c:otherwise>
                      </c:choose>
                      </div>
                  </li>
                  </ul>

                 <ul class="navbar-nav mr-end">
                   <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
                    <c:choose>
                          <c:when test="${sessionScope.adminpasswd == null && grade > 0 && grade <=10}">
                              <div class="btn-group">
                                <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" 
                                                  aria-expanded="false" style="background-color:#343A40; border:none; color:#FFFFFF80;">관리자</button>
                              <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="/admin/login.do">관리자 로그인</a>
                                <a class="dropdown-item" href="/admin/create.do">관리자 회원 가입</a>
                                <a class="dropdown-item" href="/admin/id_search.do">아이디 찾기</a>
                                <a class="dropdown-item" href="/admin/passwd_search.do">비밀번호 찾기</a>
                              </div>
                             </div>
                          </c:when>
                          <c:when test="${sessionScope.adminpasswd == null}">
                              <a class="nav-link" href="/admin/login.do">관리자 로그인</a>
                          </c:when>
                          <c:otherwise>
                              <a class="nav-link" href='/admin/logout.do'>관리자 ${sessionScope.adminid } 로그아웃</a>
                          </c:otherwise>
                      </c:choose>
                      </li>
                                       
                  <c:choose>
                      <c:when test="${sessionScope.adminpasswd != null}"> <%-- 관리자로 로그인 한 경우 --%>                    
                          <div class="btn-group">
                             <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" \
                                                  aria-expanded="false" style="background-color:#343A40; border:none; color:#FFFFFF80;">관리자</button>
                              <div class="dropdown-menu dropdown-menu-right">
                                  <a class="dropdown-item" href="/member/list.do">회원 목록</a>
                                  <a class="dropdown-item" href="/admin/list.do">관리자 목록</a>
                                  <a class="dropdown-item" href="/admin/permission_list.do">가입 허가 목록</a>
                                  <a class="dropdown-item" href="/sms/sms_list.do">sms 인증번호 목록</a>
                                  <a class="dropdown-item" href="/email/email_list.do">이메일 인증번호 목록</a>
                                  <a class="dropdown-item" href="/cate/list_all.do">카테고리 목록</a>
                                  <a class="dropdown-item" href="/review/list_all.do">리뷰 목록</a>
                                  <a class="dropdown-item" href="/sms/admin_sms_list.do">관리자 sms 인증번호 목록</a>
                                  <a class="dropdown-item" href="/admin/passwd_update.do">비밀번호 변경</a>
                                  <a class="dropdown-item" href="/admin/read.do">관리자 정보 수정</a>
                                  <a class="dropdown-item" href="/admin/unregister.do">관리자 탈퇴</a>
                              </div>
                          </div>
                      </c:when>
                  </c:choose>
<<<<<<< HEAD


              </ul> <!-- navbar-nav mr-auto -->
          </div>  <!-- collapse navbar-collapse -->
          
          <!-- 검색창 -->
          <!--         
          <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end" style='padding:0px;'>
           <form class="form-inline" action="/action_page.php">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-light" type="submit">Search</button>
           </form>
         </nav>
           -->

         <!-- 검색창 end -->
=======
                  
                  <li class="nav-item">
<<<<<<< HEAD
                        <c:choose>
                            <c:when test="${sessionScope.memberid != null}">
                                <a class="nav-link" href="/post/mf_post_member.do">포스트 추천 받기</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href='/member/login.do'>로그인하여 포스트 추천 받기</a>
                            </c:otherwise>
                        </c:choose>
                    </li> 

                    <li class="nav-item">
                      <a class="nav-link" href="javascript:chatbot();">챗봇</a>
                    </li> 
                    
                    <li class="nav-item">
                      <a class="nav-link" href="javascript:type2_recommend_post();">관심분야 추천 받기</a>
                    </li>
=======
                    <a class="nav-link" href="javascript:chatbot()">챗봇</a>
                  </li>
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
                  
              </ul> <!-- navbar-nav mr-auto -->
          </div>  <!-- collapse navbar-collapse -->
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
      </nav> <!-- navbar -->
  </div> <!-- 헤더 end -->
  
  <%-- 내용 --%> 
  <DIV class='content'>
    <div style='clear: both; height: 50px;'></div>
    
    