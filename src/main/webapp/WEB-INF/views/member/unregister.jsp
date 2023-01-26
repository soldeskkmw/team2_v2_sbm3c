<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
<link rel="icon" href="/images/travel.png"> 
</head> 


<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line' style="font-size:1.5rem;">회원 탈퇴</DIV>

  <DIV class='content_body'>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div>
  
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='get' action='./passwd_true.do'>
    <input type="hidden" name="memberid" id="memberid" value="${memberVO.memberid }">
    <div class="form_input">
      아이디: ${memberVO.memberid }<br> 
      회원 등급 : 
        <c:choose>
          <c:when test="${grade >= 1 and grade <= 10}"><img src='/member/image/man_crown.png' title="회원 관리자" class="icon"> 회원 관리자</c:when>    
          <c:when test="${grade >= 11 and grade <= 20}"><img src='/member/image/girl_student.png' title="회원" class="icon"> 회원</c:when>
          <c:when test="${grade >= 30 and grade <= 39}"><img src='/member/image/suspended.png' title="정지 회원" class="icon"> 정지 회원</c:when>
          <c:when test="${grade >= 40 and grade <= 49}"><img src='/member/image/ghost.png' title="탈퇴 회원" class="icon"> 탈퇴 회원</c:when>
        </c:choose>  <br>
      회원 이름 : ${memberVO.membername } <br>
      <c:choose>
        <c:when test="${memberVO.gender == 'M'}">성별 : 남자<br></c:when>    
        <c:when test="${memberVO.gender == 'W'}">성별 : 여자<br></c:when>
      </c:choose>  
      회원 전화번호 : ${memberVO.tel }<br>
      회원 이메일 : ${memberVO.receiver }<br>
      </div>
    
    <div class="form_input">
      <button type="submit" class="btn btn-secondary">회원탈퇴</button>
      <button type="button" onclick="history.back()" class="btn btn-secondary">취소</button>
    </div>   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

