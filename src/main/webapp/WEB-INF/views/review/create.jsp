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
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->

<script type="text/javascript">
  $(function() {
    CKEDITOR.replace('reviewcontent');  // <TEXTAREA>태그 id 값
  }); 
 
</script>

<link rel="icon" href="/images/travel.png">
</head> 
 
<body>
<c:import url="/menu/top.do" />

<DIV style='margin-top: 60px; '>
<A style="font-size: 1.5rem; font-weight:600;">${cateVO.catename } > 리뷰 등록</A>
<ASIDE class="aside_right" style="margin-top: 10px;">
  <A href="javascript:location.reload();">새로고침</A>
  <span class='menu_divide' >│</span>
  <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">리뷰 목록</A>    
</ASIDE>
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="cateno" value="${param.cateno }">
    <input type="hidden" name="memberno" value="${sessionScope.memberno }">
    <div style="margin: 40px 0;">
       <input type='text' name='reviewtitle' required="required" placeholder="제목을 입력하세요." autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>Review 작성</label>
       <textarea class="form-control" name='reviewcontent' id='reviewcontent' rows='10' placeholder="내용을 입력하세요." style='width: 100%;'>${reviewcontent }</textarea>
    </div>
    <div style="margin: 40px 0;">
       <input type='text' name='reviewword' value=''  placeholder="검색어를 입력하세요." required="required" maxlength="400" class="form-control" style='width: 100%;'>
    </div>  
    <div>
       <label>이미지 선택</label>
       <input type='file' class="form-control" name='reviewfile1MF' id='reviewfile1MF'  value='' placeholder="파일 선택">
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

