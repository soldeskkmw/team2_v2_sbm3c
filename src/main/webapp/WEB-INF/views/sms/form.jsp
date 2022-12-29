<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
    
</script> 
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>SMS 인증받는 번호 입력</DIV>
    
    <DIV class='content_body'>
      <DIV style='width: 60%; margin: 0px auto;'>
           <form name="smsForm" action="./proc.do" method="post">
              <input type="hidden" name="action" value="go"> 
              <input type="hidden" name="smsType" value="S"> <!-- 발송 타입 -->
              <input type="hidden" name="subject" value=""> <!-- 장문(LMS)인 경우(한글30자이내) -->
     
             <!-- 정상적으로 문자가 전송되고 나서 이동할 주소 -->
              <input type="hidden" name="returnurl" maxlength="64" value="./proc_next.do" size="80">
  
              전송메세지
              <textarea name="msg" cols="30" rows="2" style="width: 455px;">[www.goingshare.co.kr] [2020]을 인증번호란에 입력해주세요.</textarea><br>
               단문(SMS) : 최대 90byte까지 전송할 수 있습니다.
              <br>
              <br>받는 번호 <input type="text" name="rphone" value=""> 예) 011-0112-1112 , '-' 포함해서 입력. <br>
              보내는 번호 <!-- SMS 서비스를 가입한 기업의 관리자 전화번호 -->
              <input type="hidden" name="sphone1" value="010"> <!-- 전화번호 첫째자리 -->
              <input type="hidden" name="sphone2" value=""> <!-- 전화번호 둘째자리 2722-->
              <input type="hidden" name="sphone3" value="9751"> <!-- 전화번호 셋째자리 -->
              <br>
              <input type="submit" value="전송" class="btn btn-secondary"> 이통사 정책에 따라 발신번호와 수신번호가 같은 경우
              발송되지 않습니다.
           </form>
      </DIV>  
    </DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

