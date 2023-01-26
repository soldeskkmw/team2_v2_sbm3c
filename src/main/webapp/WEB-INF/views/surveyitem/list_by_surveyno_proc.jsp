<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>투표 결과</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
<form name='topic' id='topic' method='get' action='./list_by_surveyno_proc.do'>
 <input type='hidden' name='surveyitem' value='${surveyVO.surveyno }'>
 
  설문조사 결과<br>
  ----------------------------------------<br>
  결과: <%=request.getParameter("surveyno") %><br>
  <br>
  <A href='./list_by_surveyno.jsp'>예약 계속</A>
 
<jsp:include page="../menu/bottom.jsp" />
</form>
</body>
 
</html>

