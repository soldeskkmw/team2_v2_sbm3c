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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head> 
 
<body>
<%-- <c:import url="/menu/top.do" /> 신형 top --%>
 <jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'><A href="./list_by_postno_search_paging.do?postno=1<%--${postno } --%>" class='title_link'>포스트메뉴1<%--${postVO.name }--%></A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
     

    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_all.do?postno=1<%--${postno } --%>">기본 목록형</A>    
  
    
    <%--<c:if test="${sessionScope.admin_id != null }"> --%>
      <span class='menu_divide' >│</span>
    	<%--<A href="./create.do?postno=${postVO.postno  }">등록</A> --%>
    	<A href="./create.do?postno=1">등록</A>
    	<span class='menu_divide' >│</span>
    	<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">글 수정</A>
    	<span class='menu_divide' >│</span>
    	<%--<A href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">파일 수정</A>
    	<span class='menu_divide' >│</span>--%>
    	<A href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}&postno=1<%--${postno } --%>">삭제</A>  
  	<%--</c:if> --%>
  	
  </ASIDE> 
  
  <%-- 검색 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
      <input type='hidden' name='cateno' value='${param.cateno }'>
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
                     onclick="location.href='./list_by_postno_search_paging.do?postno=1<%--${postVO.postno}--%>&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 50%; float: left; margin-right: 10px;">
            <c:choose>
              <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
                <%-- /static/review/storage/ --%>
                <A href="/review/storage/${reviewfile1saved }"><IMG src="/review/storage/${reviewfile1saved }" style="width: 100%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/review/images/none1.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 47.5%; height: 260px; float: left; margin-right: 10px; margin-bottom: 30px;">
          <span style="font-size: 1.5em; font-weight: bold;">${reviewtitle}</span><br>
          <span style="color: #FF0000; font-size: 2.0em;">별점 = ${reviewstar}</span>          
          <span style="font-size: 1.0em;">좋아요 수 = ${goodcnt}</span><br>
          <span style="font-size: 1.0em;">조회수 = ${cnt}</span><br>
          <span style="font-size: 1.0em;">댓글수 = ${replycnt}</span><br>          
        </DIV> 

        <DIV>${reviewcontent }</DIV>
      </li>

           
     <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${reviewfile1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/review/storage&filename=${reviewfile1saved}&downname=${reviewfile1}'>${reviewfile1}</A> (${reviewsize1_label}) 
            <A href='/download?dir=/review/storage&filename=${reviewfile1saved}&downname=${reviewfile1}'><IMG SRC="/review/images/download.png"></A>
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
 <DIV class='title_line'><A  class='title_link' style="font-size:30px">댓글</A></DIV>
 
 
 <c:forEach var="ReplyVO" items="${replylist }">
        <c:set var="reviewno1" value="${ReplyVO.reviewno }" />      
  			<c:set var="replycontent1" value="${ReplyVO.replycontent }" />
        <c:set var="replyno1" value="${ReplyVO.replyno }" />
        <c:set var="memberno1" value="${ReplyVO.memberno }" />     
        회원번호 = ${memberno1 } 댓글번호 =${replyno1 }
         <A href="./replydelete.do?now_page=${param.now_page}&replyno=${replyno1}"><strong class="aside_right">댓글삭제</strong></A>  
        <input type='text' name='replycontent' readonly value='${replycontent1}' required="required" 
                  class="form-control" style='width: 100%;'>               
        <br>
</c:forEach>         
 
 
  <%--등록 폼  --%> <%--등록 폼 위쪽 코드들 제거 -> post no 및 vo 받아와서 설정 후 다시 코드 작성--%>
  <FORM name='frm' method='POST' action='./replycreate.do' enctype="multipart/form-data">
    <input type="hidden" name="postno" value="1">
    <input type="hidden" name="memberno" value="1"> <%-- 관리자 개발후 변경 필요 --%>
  	<input type="hidden" name="reviewno" value="${reviewno }">
    <div>
       <label>댓글 작성</label>
       <textarea name='replycontent' required="required" class="form-control" rows="1" style='width: 100%;'></textarea>
    </div>
      
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">댓글 등록</button>
    </div>
  
  </FORM>
 
 

  	
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

