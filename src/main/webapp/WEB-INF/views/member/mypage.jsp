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
</head> 


<body>
<c:import url="/menu/top.do" />


  <DIV class='title_line' style="font-size:1.5rem;">회원 정보 조회 및 수정</DIV>

  <DIV class='content_body'>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div>
  
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='POST' action='./read.do'>
    <input type="hidden" name="memberno" id="memberno" value="${memberVO.memberno }">
    <input type="hidden" name="memberid" id="memberid" value="${memberVO.memberid }">
      
    <div class="form_input">
      <div style="margin-bottom:2px;">아이디: ${memberVO.memberid } (변경 불가능)</div> <div style="margin-bottom:2px;"> 회원등급 : 
        <c:choose>
          <c:when test="${grade >= 1 and grade <= 10}"><img src='/member/image/man_crown.png' title="회원 관리자" class="icon"> 회원 관리자</c:when>    
          <c:when test="${grade >= 11 and grade <= 20}"><img src='/member/image/girl_student.png' title="회원" class="icon"> 회원</c:when>
          <c:when test="${grade >= 30 and grade <= 39}"><img src='/member/image/suspended.png' title="정지 회원" class="icon"> 정지 회원</c:when>
          <c:when test="${grade >= 40 and grade <= 49}"><img src='/member/image/ghost.png' title="탈퇴 회원" class="icon"> 탈퇴 회원</c:when>
        </c:choose>  
       </div>
        
        <c:choose>
          <c:when test="${memberVO.gender == 'M'}">성별 : 남자</c:when>    
          <c:when test="${memberVO.gender == 'W'}">성별 : 여자</c:when>
        </c:choose>  
    </div>   
    <input type='text' class="form-control" name='age' id='age' value="${memberVO.age }" required="required" style='width: 10%; margin-top:3px;' placeholder="나이" readonly>            
    <div class="form_input">
      <input type='text' class="form-control" name='membername' id='membername' 
                value='${memberVO.membername }' required="required" style='width: 35%;' placeholder="성명" readonly>
    </div>   

    <div class="form_input">
      <input type='text' class="form-control" name='tel' id='tel' 
                value='${memberVO.tel }' required="required" style='width: 35%;' placeholder="전화번호" readonly> 예) 010-0000-0000
    </div>
    
    <div class="form_input">
      <input type='text' class="form-control" name='receiver' id='receiver' 
                value='${memberVO.receiver }' required="required" style='width: 35%;' placeholder="이메일" readonly>
    </div>    
    
    <div class="form_input">
      <button type="button" id='btn_send' onclick="location.href='./read.do'" class="btn btn-secondary">수정</button>
      <button type="button" onclick="location.href='./unregister.do'" class="btn btn-secondary">회원 탈퇴</button>
    </div>   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
