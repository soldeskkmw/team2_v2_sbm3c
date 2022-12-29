<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.cate.CateVO" %>
<DIV class='container_main'> 
  <!-- 헤더 -->
  <div class="header">
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <li class="nav-item dropdown" style='list-style-type: none;'>
            <a class="nav-link" data-toggle="dropdown"  id="navbardrop" href="#" ><img alt="menu" src="/cate/images/menu.png" style='width: 24px;'></a>
              <div class="dropdown-menu">
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=1">Festival</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=2">Hotels</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=3">Trip</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=4">Life</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=5">Food</a>
               <a class="dropdown-item" href="/post/list_by_cateno_search_paging.do?cateno=6">Cafe</a>
               <a class="dropdown-item" href="/notice/notice_list.do">공지사항</a>
               <a class="dropdown-item" href="/survey/survey_list.do">설문조사</a>
               <a class="dropdown-item" href="/service/servicecate/list_all.do">고객센터</a>
              </div>
          </li>
          <a class="navbar-brand" href="/">Going Share</a>
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
                          <c:when test="${sessionScope.memberid == null}">
                              <a class="nav-link" href="/member/login.do">로그인</a>
                          </c:when>
                          <c:otherwise>
                              <a class="nav-link" href='/member/logout.do'>${sessionScope.memberid } 로그아웃</a>
                          </c:otherwise>
                      </c:choose>
                  </li>
                  </ul>
                  <ul class="navbar-nav mr-end">
                  <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
                      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">회원</a>
                      <div class="dropdown-menu">
                          <a class="dropdown-item" href="/member/create.do">회원 가입</a>
                          <a class="dropdown-item" href="/member/id_search.do">아이디 찾기</a>
                          <a class="dropdown-item" href="/member/passwd_search.do">비밀번호 찾기</a>
                          <c:choose>
                             <c:when test="${sessionScope.memberid != null}">
                               <a class="dropdown-item" href="/member/read.do">가입 정보</a>
                               <a class="dropdown-item" href="/member/passwd_update.do">비밀번호 변경</a>
                               <a class="dropdown-item" href="/member/read.do">회원 정보 수정</a>
                               <a class="dropdown-item" href="#">회원 탈퇴</a>
                             </c:when>
                          </c:choose>
                      </div>
                  </li>

                  
                  <li class="nav-item">
                      <c:choose>
                          <c:when test="${sessionScope.adminid == null}">
                              <a class="nav-link" href="/admin/login.do">관리자 로그인</a>
                          </c:when>
                          <c:otherwise>
                              <a class="nav-link" href='/admin/logout.do'>관리자 ${sessionScope.adminid } 로그아웃</a>
                          </c:otherwise>
                      </c:choose>
                  </li> 
                  
                  
                  <c:choose>
                      <c:when test="${sessionScope.adminid != null}"> <%-- 관리자로 로그인 한 경우 --%>                    
                          <li class="nav-item dropdown">
                              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">관리자</a>
                              <div class="dropdown-menu">
                                  <a class="dropdown-item" href="/member/list.do">회원 목록</a>
                                  <a class="dropdown-item" href="/sms/sms_list.do">인증번호 목록</a>
                                  <a class="dropdown-item" href="/cate/list_all.do">카테고리 목록</a>
                                  <a class="dropdown-item" href="/review/list_all.do">리뷰 목록</a>
                              </div>
                          </li>
                      </c:when>
                  </c:choose>
              </ul> <!-- navbar-nav mr-auto -->
          </div>  <!-- collapse navbar-collapse -->
          <!-- 검색창 -->
          <nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end" style='padding:0px;'>
           <form class="form-inline" action="/action_page.php">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-light" type="submit">Search</button>
           </form>
         </nav>
         <!-- 검색창 end -->
      </nav> <!-- navbar -->
  </div> <!-- 헤더 end -->
  
  <%-- 내용 --%> 
  <DIV class='content'>
    <div style='clear: both; height: 50px;'></div>
    
    