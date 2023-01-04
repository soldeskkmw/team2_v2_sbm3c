<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  <A href="./list_all.do" class='title_link'>문의 글 목록</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <select onchange = "location.href='/service/customer_post/list_all.do?word=${param.word }&servicecateno=' + (this.value)">
      <option value="">전체</option>
      <c:forEach var="ServiceCateVO" items="${serviceCateList }">
        <c:set var="servicecateno" value="${ServiceCateVO.servicecateno }" />
        <c:set var="servicetype_content" value="${ServiceCateVO.servicetype_content }" />
        <option 
           value="${servicecateno}" 
           <c:if test="${servicecateno == param.servicecateno }">
              selected="selected"
           </c:if>
         >${servicetype_content }
         </option>
       </c:forEach>
    </select>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 20%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
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
      <c:forEach var="customer_postVO" items="${list }">
        <c:set var="serviceno" value="${customer_postVO.serviceno }" />
        <c:set var="memberno" value="${customer_postVO.memberno }" />
        <c:set var="servicecateno" value="${customer_postVO.servicecateno }" />
        <c:set var="servicetitle" value="${customer_postVO.servicetitle }" />
        <c:set var="servicecontents" value="${customer_postVO.servicecontents }" />        
        <c:set var="servicevisible" value="${customer_postVO.servicevisible }" />
        <c:set var="thumb1" value="${customer_postVO.thumb1 }" />
        
        <tr style="height: 132px;">
          <td style='vertical-align: middle; text-align: center;'>
          
            <c:choose>
              <c:when test="${fn:contains(servicevisible, 'T') || sessionScope.adminno != null || sessionScope.memberno == memberno}">
                ${servicetitle }
                </td>
                <td style='vertical-align: middle;'>
                <a href="./read.do?serviceno=${serviceno}&servicecateno=${servicecateno}"><strong>${title}</strong> 
                  <c:choose>
                    <c:when test="${servicecontents.length() > 160 }">
                        ${servicecontents.substring(0, 160)}.....
                    </c:when>
                    <c:when test="${servicecontents.length() <= 160 }">
                        ${servicecontents}
                    </c:when>
                  </c:choose>
              </c:when>
              <c:otherwise>
                비공개글
                </td>
                <td style='vertical-align: middle;'>
                해당 글에 대한 권한이 없습니다.
              </c:otherwise>
            </c:choose>            
            
            </a> 
          </td> 
          <td style='vertical-align: middle; text-align: center;'>
<%--             <A href="/contents/map.do?servicecateno=${servicecateno }&serviceno=${serviceno}" title="지도"><IMG src="/contents/images/map.png" class="icon"></A> --%>
<%--             <A href="/contents/youtube.do?servicecateno=${servicecateno }&serviceno=${serviceno}" title="Youtube"><IMG src="/contents/images/youtube.png" class="icon"></A> --%>
            <!-- 공개상태, 답변 여부 -->
            공개상태 : ${servicevisible }
          </td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
  
  <button onclick="location.href='/service/customer_post/create.do'">글쓰기</button>
</DIV>
 
<c:import url="/menu/bottom.do" />
</body>
 
</html>