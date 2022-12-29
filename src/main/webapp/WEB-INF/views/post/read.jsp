<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="postno" value="${postVO.postno }" />
<c:set var="adminno" value="${postVO.adminno }" />
<c:set var="cateno" value="${postVO.cateno }" />
<c:set var="posttitle" value="${postVO.posttitle }" />
<c:set var="postcontent" value="${postVO.postcontent }" />
<c:set var="postword" value="${postVO.postword }" />
<c:set var="rdate" value="${postVO.rdate }" />
<c:set var="udate" value="${postVO.udate }" />
<c:set var="poststar" value="${postVO.poststar }" />
<c:set var="postcnt" value="${postVO.postcnt }" />
<c:set var="postfile1" value="${postVO.postfile1 }" />
<c:set var="postfile1saved" value="${postVO.postfile1saved }" />
<c:set var="postthumb1" value="${postVO.postthumb1 }" />
<c:set var="postsize1" value="${postVO.postsize1 }" />
<c:set var="postsize1_label" value="${postVO.postsize1_label }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'><A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.catename }</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }&now_page=${param.now_page}&postword=${param.postword }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateno }">갤러리형</A>

    <c:if test="${sessionScope.admin_id != null }">
      <span class='menu_divide' >│</span>
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      <span class='menu_divide' >│</span>
	    <A href="./update_text.do?postno=${postno}&now_page=${param.now_page}">글 수정</A>
	    <span class='menu_divide' >│</span>
	    <A href="./update_file.do?postno=${postno}&now_page=${param.now_page}">파일 수정</A>
	    <span class='menu_divide' >│</span>
	    <A href="./delete.do?postno=${postno}&now_page=${param.now_page}&cateno=${cateno}">삭제</A>  
    </c:if>
    
  </ASIDE> 
  
  <%-- 검색 --%>
  <DIV style="text-align: right; clear: both;">
  <nav class="navbar navbar-expand-sm" style='padding:0px;'>
    <form  class="form-inline justify-content-end" name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do' style='width: 100%;'>
      <input class="form-control mr-sm-2" placeholder="Search" type='hidden' name='cateno' value='${param.cateno }'>
      <c:choose>
        <c:when test="${param.postword != '' }"> <%-- 검색하는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='${param.postword }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button class='btn btn-secondary' type='submit'>검색</button>
      <c:if test="${param.postword.length() > 0 }">
        <button class='btn btn-danger ml-sm-2' type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&postword='">취소</button>  
      </c:if>    
    </form>
   </nav>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
  <FORM name='frm' method='POST' action='./read.do'>
    <ul>
      <li class="li_none">
        <DIV style="width: 50%; float: left; margin-right: 10px;">
            <c:choose>
              <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}">
                <%-- /static/post/storage/ --%>
                <A href="/post/storage/${postfile1saved }"><IMG src="/post/storage/${postfile1saved }" style="width: 100%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/post/images/none1.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 47.5%; height: 260px; float: left; margin-right: 10px; margin-bottom: 30px;">
          <span style="font-size: 1.5em; font-weight: bold;">${posttitle }</span><br>
        </DIV> 

        <DIV>${postcontent }</DIV>
      </li>

<%--
       <c:if test="${youtube.trim().length() > 0 }">
	      <li class="li_none" style="clear: both; padding-top: 15px; padding-bottom: 15px;">
				  <DIV style="text-align: center;">
				    ${youtube }
				  </DIV>
	      </li>
      </c:if> 
--%>
      
      <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${postword }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${postfile1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/post/storage&postfilename=${postfile1saved}&downname=${postfile1}'>${postfile1}</A> (${postsize1_label}) 
            <A href='/download?dir=/post/storage&postfilename=${postfile1saved}&downname=${postfile1}'><IMG SRC="/post/images/download.png"></A>
          </c:if>
        </DIV>
      </li>   
    </ul>
  </FORM>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

