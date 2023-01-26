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
 
<DIV class='title_line' style="font-size:1.5rem;"><A href="./survey_list" class='title_link'>${SurveyVO.surveytopic }</A> > 글 삭제</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./notice_list">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    </c:if>
    
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">  
        <DIV style='text-align: left; width: 80%; float: left;'>
          <span style='font-size: 1.5em;'>${surveytopic}</span>
          <br>

          
          <br>
          <FORM name='frm' method='POST' action='./survey_read_delete.do'>
              <input type='hidden' name='surveyno' value='${surveyno}'>
              
<span style="font-size: 1.2em; " >1. ${surveyitem1 }</span><br>
          <br>
          <div class="surveyanswer1">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >2. ${surveyitem2 }</span><br>
          <br>
          <div class="surveyanswer2">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >3. ${surveyitem3 }</span><br>
          <br>
          <div class="surveyanswer3">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >4. ${surveyitem4 }</span><br>
          <br>
          <div class="surveyanswer4">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >5. ${surveyitem5 }</span><br>
          <br>
          <div class="surveyanswer5">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >6. ${surveyitem6 }</span><br>
          <br>
          <div class="surveyanswer6">
           <input type="radio" name="surveyanswer" value='A'> <span style="font-size: 1.2em; " > 매우 만족</span><br>
           <input type="radio" name="surveyanswer" value='B'> <span style="font-size: 1.2em; " > 만족</span><br>
           <input type="radio" name="surveyanswer" value='C'> <span style="font-size: 1.2em; " > 보통</span><br>
           <input type="radio" name="surveyanswer" value='D'> <span style="font-size: 1.2em; " > 불만족</span><br>
           <input type="radio" name="surveyanswer" value='E'>  <span style="font-size: 1.2em; " > 매우 불만족</span><br><br><br>
           </div>
           
          <span style="font-size: 1.2em; " >7. ${surveyitem7 }</span><br>
          <br>
          <div class="surveyanswer7">
           <textarea name="etc" placeholder="개선점을 자유롭게 남겨주세요"  rows="4" cols="80" style="resize: both;"></textarea>
           </div>

              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="btn btn-secondary">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="btn btn-secondary">취소</button>
              </div>   
          </FORM>
        </DIV>
      </li>
     </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

