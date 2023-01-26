<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="postno" value="${postVO.postno }" />
<c:set var="adminno" value="${postVO.adminno }" />
<c:set var="cateno" value="${postVO.cateno }" />
<c:set var="posttitle" value="${postVO.posttitle }" />
<c:set var="postcontent" value="${postVO.postcontent }" />
<c:set var="postword" value="${postVO.postword }" />
<c:set var="postfile1" value="${postVO.postfile1 }" />
<c:set var="postfile1saved" value="${postVO.postfile1saved }" />
<c:set var="postthumb1" value="${postVO.postthumb1.toLowerCase() }" />
<c:set var="postsize1" value="${postVO.postsize1 }" />
<c:set var="postsize1_label" value="${postVO.postsize1_label }" />
           
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
    
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->
 <link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV style='margin-top: 60px; '>
<A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.catename } > ${posttitle } 삭제</A>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }">기본 목록형</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateno }">갤러리형</A>
    <span class='menu_divide' >│</span>
    <A href="./update_text.do?postno=${postno}">수정</A>
    <span class='menu_divide' >│</span>
    <A href="./update_file.do?postno=${postno}">파일 수정</A>
  </ASIDE> 
</DIV>
<DIV class='title_line'></DIV>
<<<<<<< HEAD

=======
  
  <%-- 검색 폼 --%>
  <DIV style="text-align: right; clear: both;">
  <nav class="navbar navbar-expand-sm" style='padding:0px;'>
    <form  class="form-inline justify-content-end" name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do' style='width: 100%;'>
      <input type='hidden' name='cateno' value='${cateVO.cateno }'>  <%-- 게시판의 구분 --%>
      <input class="form-control mr-sm-2" placeholder="Search" type='hidden' name='cateno' value='${param.cateno }'>
      <c:choose>
        <c:when test="${param.postword != '' }"> <%-- 검색하는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='${param.postword }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button class='btn btn-secondary' type='submit'>검색</button>
      <c:if test="${param.postword.length() > 0 }">
        <button class='btn btn-danger ml-sm-2' type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&postword='">취소</button>  
      </c:if>    
    </form>
   </nav>
  </DIV>
  
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
<DIV class='content_body'>
  
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>

          <c:choose>
            <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}">
              <IMG src="/post/storage/${postfile1saved }" style='width: 90%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 없는 경우 -->
              상품 관련 이미지가 없습니다.
            </c:otherwise>
          </c:choose>
        </DIV>

        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${posttitle}</span>
          <c:if test="${postsize1 > 0 }">
            <br>삭제되는 파일: ${postfile1 }
          </c:if>
          <br>
          <FORM name='frm' method='POST' action='./delete.do'>
              <input type='hidden' name='postno' value='${postno}'>
              <input type='hidden' name='cateno' value='${cateno}'>
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

