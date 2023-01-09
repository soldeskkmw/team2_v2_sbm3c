<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<%-- <c:import url="/menu/top.do" /> 신형 top --%>
 <jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'> ${reviewtitle } 삭제</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
      
  <%--<c:if test="${sessionScope.admin_id != null }"> --%>
    <A href="./create.do?postno=1">새 등록</A>
    <span class='menu_divide' >│</span>
   <%-- </c:if> --%>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_all.do?postno=1">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./update_text.do?revoewno=${reviewno}">수정</A>
      
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>

          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <IMG src="/review/storage/${reviewfile1saved }" style='width: 90%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 없는 경우 -->
              상품 관련 이미지가 없습니다.
            </c:otherwise>
          </c:choose>
        </DIV>

        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${reviewtitle}</span>
          <c:if test="${reviewsize1 > 0 }">
            <br>삭제되는 파일: ${reviewfile1 }
          </c:if>
          <br>
          <FORM name='frm' method='POST' action='./delete.do'>
              <input type='hidden' name='reviewno' value='${reviewno}'>
              <input type='hidden' name='postno' value='1'>
              <input type='hidden' name='now_page' value='${param.now_page}'>
              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="btn btn-primary">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="btn btn-primary">취소</button>
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

