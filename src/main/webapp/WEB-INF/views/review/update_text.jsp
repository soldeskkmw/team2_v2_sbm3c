<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cateno" value="${reviewVO.cateno }" />
<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />    
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<<<<<<< HEAD
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->

<script type="text/JavaScript">
$(function() {
   CKEDITOR.replace('reviewcontent');  // <TEXTAREA>태그 id 값
/*      if (CKEDITOR.instances['reviewcontent'].getData() == '') {
        window.alert('내용을 입력해 주세요.');
        CKEDITOR.instances['reviewcontent'].focus();
        return false;
    } */
});
=======
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</script>
    
<link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV style='margin-top: 60px; '>
<A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.catename } > 리뷰 수정</A>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminpasswd != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본목록형</A>    
  </ASIDE>
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
<<<<<<< HEAD
  <%--수정 폼 --%>
  <FORM name='frm' method='POST' action='./update_text.do'>
    <input type="hidden" name="reviewno" value="${reviewno }">
    <input type="hidden" name="cateno" value="${cateno }">
    <input type="hidden" name="memberno" value="${memberno }">
    <div style="margin: 40px 0;">
       <input type='text' name='reviewtitle' value='${reviewtitle }'  required="required" placeholder="제목을 입력하세요." autofocus="autofocus" class="form-control" style='width: 100%;'>
=======
  <ASIDE class="aside_right">
    
  <%--<c:if test="${sessionScope.admin_id != null }">--%>
    <%--<A href="./create.do?postno=${postVO.postno  }">등록</A> --%>
    <%--</c:if> --%>
    
    <A href="javascript:location.reload();">새로고침</A>
    
  </ASIDE> 
  
  <%-- 검색 폼 
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
      <input type='hidden' name='cateno' value='${cateVO.cateno }'>  게시판의 구분
      
      <c:choose>
        <c:when test="${param.word != '' }"> 검색하는 경우 
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> 검색하지 않는 경우 
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>--%>
  
  <DIV class='menu_line'></DIV>
  <%--수정 폼 --%>
  <FORM name='frm' method='POST' action='./update_text.do'>
    <input type="hidden" name="reviewno" value="${reviewno}">
     <%-- 포스트 연결후 변경 필요 --%>
     <%-- 관리자 개발후 변경 필요 --%>
    
    <div>
       <label>제목</label>
       <input type='text' name='reviewtitle' value='${reviewtitle }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    </div>
    <div>
       <label>Contents</label>
       <textarea class="form-control" name='reviewcontent' id='reviewcontent' rows='10' placeholder="내용을 입력하세요." style='width: 100%;'>${reviewcontent }</textarea>
    </div>
    <div style="margin: 40px 0;">
       <input type='text' name='reviewword' value='${reviewword }' placeholder="검색어를 입력하세요." required="required" maxlength="400" class="form-control" style='width: 100%;'>
    </div>   
    
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-primary">취소</button>
<<<<<<< HEAD
      <button type="button" onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateno}'" class="btn btn-primary">목록</button>
=======
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    </div>
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

