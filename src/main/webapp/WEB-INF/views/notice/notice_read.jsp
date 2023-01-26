<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticeno" value="${noticeVO.noticeno }" />
<c:set var="adminno" value="${noticeVO.adminno }" />
<c:set var="noticetitle" value="${noticeVO.noticetitle }" />
<c:set var="noticecontent" value="${noticeVO.noticecontent }" />
<c:set var="cnt" value="${noticeVO.cnt }" />
<c:set var="noticeword" value="${noticeVO.noticeword }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

   $(function() {
     CKEDITOR.replace('noticecontent');  // <TEXTAREA>태그 id 값
  });
   
</script>
<link rel="icon" href="/images/travel.png">     
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line' style="font-size:1.5rem;"><A href="./notice_list" class='title_link'>공지사항</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./notice_list.do">기본 목록형</A>    


    <c:if test="${sessionScope.adminid != null }">
      <span class='menu_divide' >│</span>
      <A href="./notice_create.do">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./notice_read_update.do?noticeno=${noticeno}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./notice_read_delete.do?noticeno=${noticeno}">삭제</A>  
    </c:if>
    
  </ASIDE> 
  
  <%-- 검색 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./notice_list.do'>
 
      <c:choose>
        <c:when test="${param.noticeword != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='noticeword' id='noticeword' value='${param.noticeword }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='noticeword' id='noticeword' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class="btn btn-secondary btn-sm">검색</button>
      <c:if test="${param.noticeword.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./notice_read.do?noticeno=${noticeno}'" class="btn btn-secondary btn-sm">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        
        <DIV style="width: 80%; height: auto; float: left; margin-right: 10px; margin-bottom: 30px;">
          <span style="font-size: 1.5em; font-weight: bold;">${noticetitle }</span><br>
          <br>
          <span style="font-size: 1.2em; " >${noticecontent }</span><br>
          
        </DIV> 
       
      </li>
      
      <li class="li_none">
        
      </li>
     
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

