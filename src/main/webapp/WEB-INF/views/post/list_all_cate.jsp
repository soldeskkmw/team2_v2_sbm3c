<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
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
 
<DIV class='title_line'>
  <A href="./list_all_cate.do" class='title_link'>전체 글 목록</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 50%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>
<!--     <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>상품명</th>
        <th style='text-align: center;'>정가, 할인률, 판매가, 포인트</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead> -->
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="catePostVO" items="${list }">
        <c:set var="postno" value="${catePostVO.postno }" />
        <c:set var="cateno" value="${catePostVO.cateno }" />
        <c:set var="catename" value="${catePostVO.catename }" />
        <c:set var="posttitle" value="${catePostVO.posttitle }" />
        <c:set var="postcontent" value="${catePostVO.postcontent }" />
        <c:set var="postthumb1" value="${catePostVO.postthumb1 }" />
        
        <tr style="height: 132px;">
          <td style='vertical-align: middle; text-align: center;'>
            ${name }
          </td>
          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- /static/contents/storage/ --%>
                <a href="./read.do?postno=${postno}&now_page=${param.now_page }"><IMG src="/post/storage/${postthumb1 }" style="width: 120px; height: 80px;"></a> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/post/images/none1.png" style="width: 120px; height: 80px;">
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?postno=${postno}&cateno=${cateno}"><strong>${posttitle}</strong> 
            <c:choose>
              <c:when test="${postcontent.length() > 160 }">
                  ${postcontent.substring(0, 160)} .....
              </c:when>
              <c:when test="${postcontent.length() <= 160 }">
                  ${postcontent}
              </c:when>
            </c:choose>
            
            </a> 
          </td> 
          <td style='vertical-align: middle; text-align: center;'>
            <%-- 
            <del><fmt:formatNumber value="${price}" pattern="#,###" /></del><br>
            <span style="color: #FF0000; font-size: 1.2em;">${dc} %</span>
            <strong><fmt:formatNumber value="${saleprice}" pattern="#,###" /></strong><br>
            <span style="font-size: 0.8em;">포인트: <fmt:formatNumber value="${point}" pattern="#,###" /></span> 
            --%>
          </td>
          <td style='vertical-align: middle; text-align: center;'>
          <%--  
            <A href="/post/map.do?cateno=${cateno }&postno=${postno}" title="지도"><IMG src="/post/images/map.png" class="icon"></A>
            <A href="/post/youtube.do?cateno=${cateno }&postno=${postno}" title="Youtube"><IMG src="/post/images/youtube.png" class="icon"></A>
           --%>
           </td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

