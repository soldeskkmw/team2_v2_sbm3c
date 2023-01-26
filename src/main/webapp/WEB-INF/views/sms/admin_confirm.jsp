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
<link rel="icon" href="/images/travel.png"> 
</head> 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line' style="font-size:1.5rem;">인증번호 입력</DIV>
    
    <DIV class='content_body'>
      <DIV style='width: 60%; margin: 0px auto;'>
          <label>입력된 번호가 일치하지 않습니다. 다시 인증 번호를 요청해주세요.</label><br>
	              <button type="button" onclick="location.href='../sms/admin_form.do'" class="btn btn-secondary">인증번호 재요청</button>
      </DIV>  
    </DIV> <%--  <DIV class='content_body'> END --%>
    
<jsp:include page="../menu/bottom.jsp" flush='false' />
<!-- -------------------------------------------- -->
</body>
</html>
 