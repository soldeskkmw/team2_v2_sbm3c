<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.cate.CateVO" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

</script>
    
<link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" />
    
<DIV class='title_line'>
  <A href="./list_all.do" class='title_link'>전체 글 목록</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
  <c:choose>
              <c:when test="${sessionScope.memberid == null}">
                              
               </c:when>
                <c:otherwise>
                    <A href="./create.do?postno=1">등록</A>
     	<span class='menu_divide' >│</span>          
               </c:otherwise>
</c:choose>
     	
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 20%;"></col>
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
      <c:forEach var="PostReviewVO" items="${list }">
        <c:set var="reviewtitle" value="${PostReviewVO.reviewtitle }" />
        <c:set var="reviewcontent" value="${PostReviewVO.reviewcontent }" />
       <%-- <c:set var="postno" value="${postReviewVO.cateno }" />--%>
        <c:set var="postno" value="1" />
        <c:set var="reviewno" value="${PostReviewVO.reviewno }" />
        <c:set var="reviewthumb1" value="${PostReviewVO.reviewthumb1 }" />
        <c:set var="reviewstar" value="${PostReviewVO.reviewstar }" />
        <c:set var="goodcnt" value="${PostReviewVO.goodcnt }" />
        <c:set var="replycnt" value="${PostReviewVO.replycnt }" />
        <c:set var="cnt" value="${PostReviewVO.cnt }" />  
        
        <tr style="height: 132px;">

          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- /static/contents/storage/ --%>
                <a href="./read.do?reviewno=${reviewno}&now_page=${param.now_page }"><IMG src="/review/storage/${reviewthumb1 }" style="width: 120px; height: 80px;"></a> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/review/images/none1.png" style="width: 120px; height: 80px;">
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?reviewno=${reviewno}&postno=<%--${cateno}--%>1"><strong>${reviewtitle}</strong> 
            <c:choose>
              <c:when test="${reviewcontent.length() > 160 }">
                  ${reviewcontent.substring(0, 160)}.....
              </c:when>
              <c:when test="${reviewcontent.length() <= 160 }">
                  ${reviewcontent}
              </c:when>
            </c:choose>        
            </a> 
          </td>    
         <td style='vertical-align: middle; text-align: center;'>
        별점 = ${reviewstar}
         </td>
          <td style='vertical-align: middle; text-align: center;'>
        좋아요 수 =${goodcnt}
         </td>
          <td style='vertical-align: middle; text-align: center;'>
        댓글수 = ${replycnt}
         </td>
          <td style='vertical-align: middle; text-align: center;'>
        조회수 = ${cnt}
         </td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

