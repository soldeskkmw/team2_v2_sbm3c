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
    <input type="hidden" name="adminid" id="adminid" value="${adminVO.adminid }">
    <div class="form_input">
      아이디: ${adminVO.adminid }<br> 
      회원등급 : 
        <c:choose>
          <c:when test="${admingrade >= 1 and admingrade <= 10}"><img src='/member/images/admin.png' title="메인 관리자" class="icon"> 메인 관리자</c:when>    
          <c:when test="${admingrade >= 11 and admingrade <= 20}"><img src='/member/images/user.png' title="관리자" class="icon"> 관리자</c:when>
          <c:when test="${admingrade >= 30 and admingrade <= 39}"><img src='/member/images/pause.png' title="정지 관리자" class="icon"> 정지 관리자</c:when>
          <c:when test="${admingrade >= 40 and admingrade <= 49}"><img src='/member/images/x.png' title="탈퇴 관리자" class="icon"> 탈퇴 관리자</c:when>
        </c:choose>  <br>
      회원 이름 : ${adminVO.adminname } <br>
      <c:choose>
        <c:when test="${adminVO.admingender == 'M'}">성별 : 남자<br></c:when>    
        <c:when test="${adminVO.admingender == 'W'}">성별 : 여자<br></c:when>
      </c:choose>  
      나이 : ${adminVO.adminage }세<br>
      회원 전화번호 : ${adminVO.admintel }<br>
      회원 이메일 : ${adminVO.adminreceiver }<br>
      우편번호 : ${adminVO.zipcode }<br>
      주소 : ${adminVO.address1 } <br>상세 주소 :  ${adminVO.address2 }<br>
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

