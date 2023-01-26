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
 
<DIV class='title_line' style="font-size:1.5rem;"><A href="./notice_list" class='title_link'>${noticeVO.noticetitle }</A> > 글 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./notice_create.do">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./notice_list">기본 목록형</A>    

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
  <%--수정 폼 --%>
  <FORM name='frm' method='POST' action='./notice_read_update.do'>
    <input type="hidden" name="noticeno" value="${noticeno }">
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%><%--관리자만 수정 가능 --%>
    
    <div>
       <label>제목</label>
       <input type="text" name='noticetitle' value='${noticetitle }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='noticecontent' id="noticecontent" required="required" class="form-control" rows="12" style='width: 100%;'>${noticecontent }</textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='noticeword' value='${noticeword }' 
                 class="form-control" style='width: 100%;'>
    </div>   
   
    <div>
       <label>패스워드</label>
       <input type='password' name='passwd' value='1234' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>   
       
    <div class="content_body_bottom">
      <button type="submit" onclick="location.href='redirect:/notice_read&noticeno=${noticeno}'" class="btn btn-secondary">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-secondary">취소</button>
      <button type="button" onclick="location.href='./notice_list'" class="btn btn-secondary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

