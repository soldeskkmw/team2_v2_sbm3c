<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="memberno" value="${reviewVO.memberno }" />
<c:set var="cateno" value="${reviewVO.cateno }" />
<c:set var="membername" value="${reviewVO.membername }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />    
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
<c:set var="reviewcnt" value="${reviewVO.reviewcnt }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
<c:set var="reviewrdate" value="${reviewVO.reviewrdate.substring(0, 10) }" />
<c:set var="reviewudate" value="${reviewVO.reviewudate.substring(0, 10) }" />
<c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
<c:set var="reviewfile1saved" value="${reviewVO.reviewfile1saved }" />
<c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
<c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
<c:set var="reviewsize1_label" value="${replyVO.reviewsize1_label }" />
<%-- <c:set var="replyno" value="${replyVO.replyno }" />
<c:set var="replymemberno" value="${replyVO.replymemberno }" />
<c:set var="replycontent" value="${replyVO.replycontent }" />
<c:set var="replyrdate" value="${replyVO.replyrdate.substring(0, 10) }" />
<c:set var="replyudate" value="${replyVO.replyudate.substring(0, 10) }" /> --%>

<!DOCTYPE html> 

<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 

<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/JavaScript">
</script>
    
<link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" /> 

<DIV class='title_line'  style="width:80%; margin-top: 60px;">
  <A href="../review/list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link' style="font-size:1.7rem; font-weight:600;">${cateVO.catename }</A>
  <ASIDE class="aside_right" style="margin-top: 10px; font-size:1rem;">
    <c:if test="${sessionScope.memberpasswd == memberVO.memberpasswd }">
      <A href="./update_text.do?reviewno=${reviewno}">리뷰 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?reviewno=${reviewno}">이미지 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?reviewno=${reviewno}&cateno=${cateno}">리뷰 삭제</A>  
      <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="../review/list_by_cateno_search_paging.do?cateno=${cateno }&now_page=${param.now_page}&reviewword=${param.reviewword }">리뷰 목록</A>    
  </ASIDE> 
</DIV>

<DIV class='content_body'>
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; margin-bottom: 30px;">
          <span style="font-size: 2em; font-weight: bold;">${reviewtitle }</span><br>
        </DIV>
        <DIV style="width: 100%; margin-bottom: 20px;">
          <span>✍🏻 BY ${membername } </span>
          <c:choose>
            <c:when test="${reviewudate != null}"><span>${reviewudate }</span></c:when>
            <c:otherwise><span>${reviewrdate }</span></c:otherwise>
          </c:choose>
        </DIV>
        <DIV style="width: 50%; float:left; margin-bottom:10px;">
            <c:choose>
              <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
                <A href="../review/storage/${reviewfile1saved }"><IMG src="../review/storage/${reviewfile1saved }" style="width: 70%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="../review/images/none1.png" style="width: 70%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 50%; margin-bottom:40px; font-size: 1.1em;">
          <c:if test="${reviewfile1.trim().length() > 0 }">
            📁 대표 이미지 다운받기: <A href='/download?dir=../review/storage&reviewfilename=${reviewfile1saved}&downname=${reviewfile1}'>${reviewfile1}</A>📥 (${reviewsize1_label}) 
            <A href='/download?dir=../review/storage&reviewfilename=${reviewfile1saved}&downname=${reviewfile1}'></A>
          </c:if>
        </DIV>
        <DIV class='menu_line'></DIV>
        <DIV style="width:80%; margin: 0 auto; margin-bottom:80px;">${reviewcontent }</DIV>
      </li>
      <li class="li_none">
        <DIV class='menu_line'></DIV>
        <DIV style='margin:30px 0;'>
          <h4><span class="badge badge-pill badge-dark">Keyword</span></h4>
          <DIV style='margin:10px;'>${reviewword }</DIV>
        </DIV>
      </li>
      <li class="li_none">
        <DIV class='menu_line'></DIV>
        <DIV style='margin:30px 0;'>
          <h4><span class="badge badge-pill badge-dark">댓글 ${replycnt }</span></h4>
          <DIV style='margin:10px;'>
		        <c:forEach var="replyVO" items="${list }">
		        <c:set var="replyno" value="${replyVO.replyno }" />
		        <c:set var="memberno" value="${replyVO.memberno }" />
		        <c:set var="reviewno" value="${replyVO.reviewno }" />
		        <c:set var="replycontent" value="${replyVO.replycontent }" />
		        <c:set var="replyrdate" value="${replyVO.replyrdate.substring(0, 10) }" />
		        <c:set var="replyudate" value="${replyVO.replyudate.substring(0, 10) }" />
		        <DIV style="height: 200px;"> 
		          <DIV style='vertical-align: middle; text-align: right;'>
		          <a>member: ${memberno}</a>
		          </DIV>  
		          <DIV style='vertical-align: middle;'>
		            <div>
		              <c:choose>
		                <c:when test="${replycontent.length() > 120 }">
		                    ${replycontent.substring(0, 120)}.....
		                </c:when>
		                <c:when test="${replycontent.length() <= 120 }">
		                    ${replycontent}
		                </c:when>
		              </c:choose>
		            </div>
		            <DIV  style="margin-top: 10%; ">
		              <div style="font-size: 0.9em;"><a>✍🏻 BY ${membername } </a>작성일 : ${replyrdate }
		                <c:if test="${replyudate != null }">
		                 <a style="font-size: 0.9em;">수정일 : ${replyudate }</a>
		                </c:if>
		              </div>
		            </DIV>
		          </DIV> 
		        </DIV>
		      </c:forEach>
          </DIV>
        </DIV>
      </li>
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

