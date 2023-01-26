<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="surveyno" value="${surveyVO.surveyno }" />
<c:set var="surveyitemno" value="${surveyVO.surveyitemno }" />
<c:set var="surveytopic" value="${surveyVO.surveytopic }" />
<c:set var="surveyitem1" value="${surveyVO.surveyitem1 }" />
<c:set var="surveyitem2" value="${surveyVO.surveyitem2 }" />
<c:set var="surveyitem3" value="${surveyVO.surveyitem3 }" />
<c:set var="surveyitem4" value="${surveyVO.surveyitem4 }" />
<c:set var="surveyitem5" value="${surveyVO.surveyitem5 }" />
<c:set var="surveyitem6" value="${surveyVO.surveyitem6 }" />
<c:set var="surveyitem7" value="${surveyVO.surveyitem7 }" />
<c:set var="etc" value="${surveyVO.etc }" />
<c:set var="surveyanswer1" value="${surveyVO.surveyanswer1 }" />
<c:set var="surveyanswer2" value="${surveyVO.surveyanswer2 }" />
<c:set var="surveyanswer3" value="${surveyVO.surveyanswer3 }" />
<c:set var="surveyanswer4" value="${surveyVO.surveyanswer4 }" />
<c:set var="surveyanswer5" value="${surveyVO.surveyanswer5 }" />
<c:set var="surveyanswer6" value="${surveyVO.surveyanswer6 }" />
<c:set var="surveyanswer7" value="${surveyVO.surveyanswer7 }" />
<c:set var="startdate" value="${surveyVO.startdate }" />
<c:set var="enddate" value="${surveyVO.enddate }" />
<c:set var="yn" value="${surveyVO.yn }" />
<c:set var="adminno" value="${surveyVO.adminno }" />
 
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
 
<DIV class='title_line' style="font-size:1.5rem;"><A href="./survey_list" class='title_link'>${surveyVO.surveytopic }</A> > 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./survey_create.do">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./survey_list">기본 목록형</A>    

  </ASIDE> 
  
  
  <DIV class='menu_line'></DIV>
  <%--수정 폼 --%>
    <FORM name='frm' id='frm' method='POST' action='./survey_read_update.do'  style="maring-left:20px;">
    <input type="hidden" name="surveyno" value="${surveyno }">
    
    <div>
       <label>설문 제목</label>
       <input type='text' id='surveytopic' name='surveytopic' required="required"  value="${surveytopic }"
                 autofocus="autofocus" class="form-control" style='width: 50%;'>
    </div>
    
    <div>
       <label>시작 날짜</label>
       <input type='date' name='startdate' value='' required="required"  value="${startdate }" 
                 class="form-control" style='width: 50%;'>
    </div>
    <div>
       <label>종료 날짜</label>
       <input type='date' name='enddate' value='' required="required"  value="${enddate }" 
                 class="form-control" style='width: 50%;'>
    </div>
   
    <div>
      <p>진행 여부</p>
      <label><input type="radio" name="yn" value='Y'> 진행 중</label> 
      <label><input type="radio" name="yn" value='N'> 종료</label>
      
    </div>   
    
    <div class="surveyitem">
    <br>
        <button type="button" name='a' class="btn btn-secondary btn-sm">설문 내용 추가</button>
        <br><br>
        
    </div>
    
      <div class='form-group'>
      <label for='question'>질문</label> &nbsp;&nbsp;
      <input type='text' class='form-control' name='surveyitem1' id='surveyitem1' value='${surveyitem1 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem2' id='surveyitem2' value='${surveyitem2 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem3' id='surveyitem3' value='${surveyitem3 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem4' id='surveyitem4' value='${surveyitem4 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem5' id='surveyitem5' value='${surveyitem5 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem6' id='surveyitem6' value='${surveyitem6 }' style='width: 700px;'><br>
      <input type='text' class='form-control' name='surveyitem7' id='surveyitem7' value='${surveyitem7 }' style='width: 700px;'><br></div>
    
    <div class="content_body_bottom">
      <button type="submit" onclick="location.href='redirect:/survey_read&surveyno=${surveyno}'"  class="btn btn-secondary">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-secondary">취소</button>
      <button type="button" onclick="location.href='./survey_list.do'" class="btn btn-secondary">목록</button>
    </div>
  
  </FORM>
 
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

