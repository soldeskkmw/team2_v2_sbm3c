<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="postno" value="${reviewVO.postno }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />        
<c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
<c:set var="reviewfile1saved" value="${reviewVO.reviewfile1saved }" />
<c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
<c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
<c:set var="reviewstar" value="${reviewVO.reviewstar }" />
<c:set var="goodcnt" value="${reviewVO.goodcnt }" />
<c:set var="cnt" value="${reviewVO.cnt }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
 

 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head> 
 
<body>
<%-- <c:import url="/menu/top.do" /> 신형 top --%>
 <jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>글 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    
  <%--<c:if test="${sessionScope.admin_id != null }">--%>
    <%--<A href="./create.do?postno=${postVO.postno  }">등록</A> --%>
    	<A href="./create.do?postno=1">새 등록</A>
    <span class='menu_divide' >│</span>
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
    <input type="hidden" name="postno" value="1"> <%-- 포스트 연결후 변경 필요 --%>
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%>
    
    <div>
       <label>제목</label>
       <input type='text' name='reviewtitle' value='${reviewtitle }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>글 내용</label>
       <textarea name='reviewcontent' required="required" class="form-control" rows="12" style='width: 100%;'>${reviewcontent }</textarea>
    </div>
    
    <div>
       <label>검색어</label>
       <input type='text' name='reviewword' value='${reviewword }' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-primary">취소</button>
      <button type="button" onclick="location.href='./list_all.do?postno=1'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

