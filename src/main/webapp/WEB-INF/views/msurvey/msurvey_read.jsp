<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="msurveyno" value="${msurveyVO.msurveyno }" />
<c:set var="surveyno" value="${surveyVO.surveyno }" />
<c:set var="surveyitemno" value="${surveyVO.surveyitemno }" />
<c:set var="surveyitemno" value="${surveyVO.surveytopic }" />
<c:set var="surveyanswerno" value="${surveyVO.surveyanswerno }" />
<c:set var="memberno" value="${memberVO.memberno }" />
<c:set var="adminno" value="${adminVO.adminno }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

</script>
 <link rel="icon" href="/images/travel.png">    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <LI class='li_none_left' >
        <span class="span_fail">설문 참여가 완료되었습니다.</span>
      </LI>

      <LI class='li_none'>
        <br>
        <button type='button' onclick="location.href='/survey/survey_list.do'" class="btn btn-secondary">결과 확인</button>    
        <button type='button'  onclick="history.back()" class="btn btn-secondary">돌아가기</button>
      </LI>
    </UL>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

