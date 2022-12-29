<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>${cateVO.name } > 글 등록</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 
  
  <%-- 검색 폼 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
      <input type='hidden' name='cateno' value='${cateVO.cateno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_cateno_search.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='/service/customer_post/create.do' enctype="multipart/form-data">
<!--   <FORM name='frm' method='POST' action='/service/customer_post/create.do'> -->
    <input type="hidden" name="cateno" value="${param.cateno }">
    
    <div>
       <label>문의 유형 선택</label>
          <select name="servicecateno">
            <option value="-1" selected="selected">문의 유형을 선택해주세요</option>
            <c:forEach var="ServiceCateVO" items="${serviceCateList }">
	            <c:set var="servicecateno" value="${ServiceCateVO.servicecateno }" />
	            <c:set var="servicetype_content" value="${ServiceCateVO.servicetype_content }" />
	            <option value="${servicecateno }">${servicetype_content }</option>
            </c:forEach>
        </select>    
    </div>    
    <div>
       <label>공개 여부 선택</label>
          <select name="servicevisible">
            <option value="T" selected="selected">공개</option>
            <option value="F">비공개</option>
        </select>    
    </div> 
    
    <div>
       <label>문의 제목</label>
       <input type='text' name='servicetitle' value='가을 영화' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>문의 내용</label>
       <textarea name='servicecontents' required="required" class="form-control" rows="12" style='width: 100%;'>가을 단풍보며 멍때리기</textarea>
    </div>
       
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='file1MF' id='file1MF' 
                 value='' placeholder="파일 선택">
    </div>   
    
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='/service/customer_post/list_all.do'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<%-- <jsp:include page="../menu/bottom.jsp" flush='false' /> --%>
<c:import url="/menu/bottom.do" />
</body>
 
</html>

