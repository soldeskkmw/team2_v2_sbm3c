<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="serviceno" value="${customer_postVO.serviceno }" />
<c:set var="servicecateno" value="${customer_postVO.servicecateno }" />
<c:set var="memberno" value="${customer_postVO.memberno }" />
<c:set var="servicetitle" value="${customer_postVO.servicetitle }" />
<c:set var="servicecontents" value="${customer_postVO.servicecontents }" />        
<c:set var="servicevisible" value="${customer_postVO.servicevisible }" />
<c:set var="rdate" value="${customer_postVO.rdate }" />
<c:set var="udate" value="${customer_postVO.udate }" />
<c:set var="file1" value="${customer_postVO.file1 }" />
<c:set var="file1saved" value="${customer_postVO.file1saved }" />
<c:set var="thumb1" value="${customer_postVO.thumb1 }" />
<c:set var="size1" value="${customer_postVO.size1 }" />
<c:set var="file1MF" value="${customer_postVO.file1MF }" />
<c:set var="size1_label" value="${customer_postVO.size1_label }" />
 
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
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'><A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.name }</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }&now_page=${param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateno }">갤러리형</A>

    <c:if test="${sessionScope.admin_id != null }">
      <span class='menu_divide' >│</span>
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      <span class='menu_divide' >│</span>
	    <A href="./update_text.do?contentsno=${contentsno}&now_page=${param.now_page}">글 수정</A>
	    <span class='menu_divide' >│</span>
	    <A href="./delete.do?contentsno=${contentsno}&now_page=${param.now_page}&cateno=${cateno}">삭제</A>  
    </c:if>
    
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
                     onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 50%; float: left; margin-right: 10px;">
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                <%-- /static/contents/storage/ --%>
                <A href="/service/storage/${file1saved }"><IMG src="/service/storage/${file1saved }" style="width: 100%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/service/images/none1.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 47.5%; height: 260px; float: left; margin-right: 10px; margin-bottom: 30px;">
          <span style="font-size: 1.5em; font-weight: bold;">${servicetitle }</span><br>
        </DIV> 

        <DIV>${servicecontents }</DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/service/storage&filename=${file1saved}&downname=${file1}'>${file1}</A> (${size1_label}) 
            <A href='/download?dir=/service/storage&filename=${file1saved}&downname=${file1}'><IMG SRC="/service/images/download.png"></A>
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

<%--   <button onclick="location.href='/service/customer_post/update.do?serviceno=${serviceno}'">수정하기</button> --%>
  <button onclick="location.href='/service/customer_post/delete.do?serviceno=${serviceno}'">삭제하기</button>
  
  <DIV class='menu_line'></DIV>  
  
  <%-- ================================================================================ --%>
  <%-- 등록 폼 --%>
  <FORM name='frm' method='POST' action='/service/admin_reply/create.do' enctype="multipart/form-data">
    <DIV style="width: 100%; height: 15px; float: left; margin-right: 10px; margin-bottom: 30px; margin-top: 10px">
      <span style="font-size: 1.5em; font-weight: bold;">답글 등록</span><br>
    </DIV> 
    <input type="hidden" name="serviceno" value="${serviceno }">
    
    <div>
<!--        <label>문의 유형 선택</label> -->
<!--           <select name="servicecateno"> -->
<!--             <option value="-1" selected="selected">문의 유형을 선택해주세요</option> -->
<%--             <c:forEach var="ServiceCateVO" items="${serviceCateList }"> --%>
<%--               <c:set var="servicecateno" value="${ServiceCateVO.servicecateno }" /> --%>
<%--               <c:set var="servicetype_content" value="${ServiceCateVO.servicetype_content }" /> --%>
<%--               <option value="${servicecateno }">${servicetype_content }</option> --%>
<%--             </c:forEach> --%>
<!--         </select>     -->
    </div>    
    <div>
       <label>공개 여부 선택</label>
          <select name="adminreplyvisible">
            <option value="T" selected="selected">공개</option>
            <option value="F">비공개</option>
        </select>    
    </div> 
    
    <div>
       <label>답글 제목</label>
       <input type='text' name='adminreplytitle' value='' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>답글 내용</label>
       <textarea name='adminreplycontent' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
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
 
<c:import url="/menu/bottom.do" />
</body>
 
</html>

