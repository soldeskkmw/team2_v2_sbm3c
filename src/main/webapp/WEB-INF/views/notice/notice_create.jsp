<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script type="text/JavaScript">

   $(function() {
     CKEDITOR.replace('noticecontent');  // <TEXTAREA>태그 id 값
  });
   
  
</script>
<link rel="icon" href="/images/travel.png">     
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line' style="font-size:1.5rem;">공지사항 등록</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right" style="padding-bottom:7px;">
    <c:if test="${sessionScope.adminid != null }">
        <A href="./notice_create.do">등록</A>
        <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./notice_list.do">기본 목록형</A>    

  </ASIDE> 
  
  
  
  <DIV class='menu_line'></DIV>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='./notice_create.do' >

    <input type="hidden" name="adminno" value="1">  <%--관리자만 등록 가능 --%>
    
    <div>
       <label>제목</label>
       <input type='text' name='noticetitle' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='noticecontent' id="noticecontent" required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='noticeword' value='' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
   
    <div>
       <label>패스워드</label>
       <input type='password' name='passwd' value='1234' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-secondary">등록</button>
      <button type="button" onclick="location.href='./notice_list.do'" class="btn btn-secondary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

