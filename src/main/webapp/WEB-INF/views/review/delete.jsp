<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cateno" value="${reviewVO.cateno }" />
<c:set var="reviewno" value="${reviewVO.reviewno }" />
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
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
           
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<<<<<<< HEAD
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
 <link rel="icon" href="/images/travel.png">   
=======
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

</script>
    
<link rel="icon" href="/images/travel.png">   
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</head> 
 
<body>
<c:import url="/menu/top.do" /> 

<DIV style='margin-top: 60px; '>
<A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.catename } > ${reviewtitle } 삭제</A>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.memberpasswd != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">리뷰등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }">기본목록형</A>
    <span class='menu_divide' >│</span>
    <A href="./update_text.do?reviewno=${reviewno}">리뷰 수정</A>
    <span class='menu_divide' >│</span>
    <A href="./update_file.do?reviewno=${reviewno}">이미지 수정</A>
  </ASIDE> 
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
  
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>

          <c:choose>
            <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
              <IMG src="/review/storage/${reviewfile1saved }" style='width: 90%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 없는 경우 -->
              리뷰 관련 이미지가 없습니다.
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
<<<<<<< HEAD
              <input type='hidden' name='cateno' value='${cateno}'>
=======
              <input type='hidden' name='postno' value='${postno}'>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
              <input type='hidden' name='now_page' value='${param.now_page}'>
              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type="submit" class="btn btn-primary">삭제 진행</button>
                <button type="button" onclick = "history.back()" class="btn btn-primary">취소</button>
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

