<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
$(document).ready(function(){
    $("#checkBoxId").change(function(){
        if($("#checkBoxId").is(":checked")){
            //체크박스가 체크되었으며, 파일 input이 보여야 함
//             $("#service_update").attr('/service/customer_post/update.do?update_file=1');
            $("#file_box").show();
            
        }else{
        	  //체크박스가 체크해제되었으며, 파일 input이 비워지고, 안보여야 함
//         	  $("#service_update").attr('/service/customer_post/update.do?update_file=0');
        	  $("#file1MF").val("");
        	  $("#file_box").hide();
        }
    });
});
</script>
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>${cateVO.name } > 글 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 
  
  <%-- 검색 폼 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
      <input type='hidden' name='cateno' value='${cateVO.cateno }'>  <%-- 게시판의 구분 --%>
      
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
                     onclick="location.href='./list_by_cateno_search.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  <%--수정 폼 --%>
  <FORM name='frm' id='service_update' method='POST' action='/service/customer_post/update.do' enctype="multipart/form-data">
    <input type="hidden" name="serviceno" value="${serviceno }">
    
    <div>
       <label>문의 유형 선택</label>
          <select name="servicecateno">
            <c:forEach var="ServiceCateVO" items="${serviceCateList }">
	            <c:set var="servicecateno" value="${ServiceCateVO.servicecateno }" />
	            <c:set var="servicetype_content" value="${ServiceCateVO.servicetype_content }" />
	            <option 
	               value="${servicecateno }"
	               <c:if test="${servicecateno == customer_postVO.servicecateno }">
	                  selected="selected"
	               </c:if>
	             >${servicetype_content }
	             </option>
            </c:forEach>
        </select>    
    </div>    
    <div>
       <label>공개 여부 선택 </label>
          <select name="servicevisible">
            <option value="T" <c:if test="${fn:contains(servicevisible, 'T')}"> selected="selected"</c:if> >공개</option>
            <option value="F" <c:if test="${fn:contains(servicevisible, 'F')}"> selected="selected"</c:if> >비공개</option>
        </select>    
    </div> 
    
    <div>
       <label>문의 제목</label>
       <input type='text' name='servicetitle' value='${servicetitle }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>문의 내용</label>
       <textarea name='servicecontents' required="required" class="form-control" rows="12" style='width: 100%;'>${servicecontents } </textarea>
    </div>
       
    <div>
        <br>
        <input type="checkbox" id="checkBoxId" name="checkBoxId"> <label for="checkBoxId">이미지 수정/삭제 </label> <br>
        <label> 현재 이미지(파일)을 지우거나 다른 이미지로 대체합니다. </label> 
        <div id="file_box" style="display:none">          
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                 value='' placeholder="파일 선택">
        </div>       
    </div>   
    
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">수정</button>
      <button type="button" onclick="location.href='/service/customer_post/list_all.do'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>

<c:import url="/menu/bottom.do" />
</body>
 
</html>

