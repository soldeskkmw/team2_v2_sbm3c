<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 
</head> 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line' style="font-size:1.5rem;">
    인증번호 목록 삭제
  </DIV>

  <DIV class='content_body'>
    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='../email/email_list.do'>email 인증번호 목록</A>
      <span class='menu_divide' >│</span> 
      <A href='../member/list.do'>목록</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
   
   
    <DIV class='message'>
      <FORM name='frm' method='POST' action='./delete.do'>
        '회원 번호${emailVO.memberno }의 회원 아이피를(${emailVO.ip })'삭제합니다.<br><br>
        정말로 삭제하시겠습니까?<br><br>         
        <input type='hidden' name='emailno' value='${emailVO.emailno}'>     
            
        <button type="submit" class="btn btn-secondary">삭제</button>
        <button type="button" onclick="location.href='./email_list.do'" class="btn btn-secondary">취소(목록)</button>
     
      </FORM>
    </DIV>
  </DIV> <%--  <DIV class='content_body'> END --%>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 