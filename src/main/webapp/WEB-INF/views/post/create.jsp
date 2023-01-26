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

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->

<script type="text/javascript">
  $(function() {
    CKEDITOR.replace('postcontent');  // <TEXTAREA>태그 id 값
/*     if (CKEDITOR.instances['postcontent'].getData() == '') {
        window.alert('내용을 입력해 주세요.');
        CKEDITOR.instances['postcontent'].focus();
        return false;
    } */
  }); 
 
</script>

<link rel="icon" href="/images/travel.png"> 
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV style='margin-top: 60px; '>
<A style="font-size: 1.5rem; font-weight:600;">${cateVO.catename } > 글 등록</A>
<ASIDE class="aside_right" style="margin-top: 10px;">
  <c:if test="${sessionScope.adminid != null }">
    <A href="./create.do?cateno=${cateVO.cateno }">등록</A>    
    <span class='menu_divide' >│</span>
  </c:if>
  <A href="javascript:location.reload();">새로고침</A>
  <span class='menu_divide' >│</span>
  <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본 목록형</A>    
  <span class='menu_divide' >│</span>
  <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }">갤러리형</A>
</ASIDE>
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="cateno" value="${param.cateno }">
    <input type="hidden" name="adminno" value="1">
    <div style="margin: 40px 0;">
       <input type='text' name='posttitle' required="required" placeholder="제목을 입력하세요." autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>Contents</label>
       <!-- <textarea name='postcontent'  id='postcontent' required="required" placeholder="내용을 입력하세요." class="form-control" rows="12" style='width: 100%;'></textarea> -->
       <textarea class="form-control" name='postcontent' id='postcontent' rows='10' placeholder="내용을 입력하세요." style='width: 100%;'>${postcontent }</textarea>
    </div>
    <div style="margin: 40px 0;">
       <input type='text' name='postword' value=''  placeholder="검색어를 입력하세요." required="required" maxlength="400" class="form-control" style='width: 100%;'>
    </div>   
    <div>
       <label>대표 이미지 선택</label>
       <input type='file' class="form-control" name='postfile1MF' id='postfile1MF'  value='' placeholder="파일 선택">
    </div>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

