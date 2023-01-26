<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
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
<link rel="icon" href="/images/travel.png"> 
</head>
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }" class='title_link'>${cateVO.catename }</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.admin_id != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본 목록형</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 

  <%-- 검색 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
      <input type='hidden' name='cateno' value='${param.cateno }'>
      
      <c:choose>
        <c:when test="${param.postword != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='postword' id='postword' value='${param.postword }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='postword' id='postword' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.postword.length() > 0 }">
        <button type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&postword='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="postVO" items="${list }" varStatus="status">
      <c:set var="postno" value="${postVO.postno }" />
      <c:set var="posttitle" value="${postVO.posttitle }" />
      <c:set var="postcontent" value="${postVO.postcontent }" />
      <c:set var="postfile1" value="${postVO.postfile1 }" />
        <c:set var="postsize1" value="${postVO.postsize1 }" />
        <c:set var="postthumb1" value="${postVO.postthumb1 }" />

      <%-- 하나의 행에 이미지를 4개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.index % 4 == 0 && status.index != 0 }"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
      
      <!-- 하나의 이미지, 24 * 4 = 96% -->
      <DIV style='width: 24%; float: left; margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: center;'>
        <c:choose>
          <c:when test="${postsize1 > 0}"> <!-- 파일이 존재하면 -->
            <c:choose> 
              <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}"> <!-- 이미지 인경우 -->
                <a href="./read.do?postno=${postno}&cateno=${param.cateno}">               
                  <IMG src="./storage/${postthumb1 }" style='width: 100%; height: 150px;'>
                </a><br>
                ${posttitle} <br>
                <%--
                <del><fmt:formatNumber value="${price}" pattern="#,###" /></del>
                <span style="color: #FF0000; font-size: 1.0em;">${dc} %</span>
                <strong><fmt:formatNumber value="${saleprice}" pattern="#,###" /></strong>
                --%>
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                <DIV style='width: 100%; height: 150px; display: table; border: solid 1px #CCCCCC;'>
                  <DIV style='display: table-cell; vertical-align: middle; text-align: center;'> <!-- 수직 가운데 정렬 -->
                    <a href="./read.do?postno=${postno}&cateno=${param.cateno}">${postfile1}</a><br>
                  </DIV>
                </DIV>
                <DIV style='height: 48px;'>
                  ${posttitle}
                </DIV>
              </c:otherwise>
            </c:choose>
          </c:when>
          <c:otherwise> <%-- 파일이 없는 경우 기본 이미지 출력 --%>
            <a href="./read.do?postno=${postno}&cateno=${param.cateno}">
              <img src='/post/images/none1.png' style='width: 100%; height: 150px;'>
            </a><br>
            ${posttitle} <br>
            <%--
            <del><fmt:formatNumber value="${price}" pattern="#,###" /></del>
            <span style="color: #FF0000; font-size: 1.0em;">${dc} %</span>
            <strong><fmt:formatNumber value="${saleprice}" pattern="#,###" /></strong>
            --%>
          </c:otherwise>
        </c:choose>         
      </DIV>  
    </c:forEach>
    <!-- 갤러리 Layout 종료 -->
    <br><br>
  </div>

</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

