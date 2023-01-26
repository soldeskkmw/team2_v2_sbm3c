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
 
<DIV class='title_line' style="font-size:1.5rem;"><A href="./notice_list" class='title_link'>${noticeVO.noticetitle }</A> > 글 삭제</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./notice_create.do">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./notice_list">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./notice_read_update.do?noticeno=${noticeno}">수정</A>
  </ASIDE> 
  
  <%-- 검색 폼 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./notice_list'>
      
      <c:choose>
        <c:when test="${param.noticeword != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./notice_list&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">  
        <DIV style='text-align: left; width: 80%; float: left;'>
          <span style='font-size: 1.5em;'>${noticetitle}</span>
          <br>
          <br>
          <span style='font-size: 1.5em;'>${noticecontent}</span>
          
          <br>
          <FORM name='frm' method='POST' action='./notice_read_delete.do'>
              <input type='hidden' name='noticeno' value='${noticeno}'>
              <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%><%--관리자만 삭제 가능 --%>

              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="btn btn-secondary">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="btn btn-secondary">취소</button>
              </div>   
          </FORM>
        </DIV>
      </li>
     </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

