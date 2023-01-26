<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<link rel="icon" href="/survey/images/travel.png">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>설문조사</DIV>

<DIV class='content_body'>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 5%;'/>
      <col style='width: 10%;'/>
      <col style='width: 40%;'/>    
      <col style='width: 25%;'/>
      <c:if test="${sessionScope.adminid != null }">
      <col style='width: 20%;'/>
     </c:if>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs"></TH>
      <TH class="th_bs">진행 여부</TH>
      <TH class="th_bs">설문</TH>
      <TH class="th_bs">등록일</TH>
      <c:if test="${sessionScope.adminid != null }">
      <TH class="th_bs">편집</TH>
      </c:if>

    </TR>
    </thead>
    
   <tbody>
    <c:forEach var="surveyVO" items="${survey_list }">
      <c:set var="surveyno" value="${surveyVO.surveyno }" />
      <c:set var="surveytopic" value="${surveyVO.surveytopic }" />
      <c:set var="rdate" value="${surveyVO.rdate }" />
      <c:set var="yn" value="${surveyVO.yn }" />
      
      
      <TR style="height: 40px;">
        <TD style='vertical-align: middle;'><div>${surveyno }</div></TD>
        <TD style='vertical-align: middle;'>
        <c:choose>
              <c:when test="${yn eq 'Y' }">
                  <a>진행 중</a>
              </c:when>
              <c:when test="${yn eq 'N' }">
                  <a>종료</a>
              </c:when>
        </c:choose>
        </TD>
        <TD style='vertical-align: middle;'><a href="../msurvey/msurvey_create.do?surveyno=${surveyno }">
        <c:choose>
              <c:when test="${surveytopic.length() > 25 }">
                  ${surveytopic.substring(0, 25)}.....
              </c:when>
              <c:when test="${surveytopic.length() <= 25 }">
                  ${surveytopic}
              </c:when>
        </c:choose>
        </a>        
         </TD>
  
       
        <TD class="td_bs"><div>${rdate }</div></TD>
        <c:if test="${sessionScope.adminid != null }">
        <TD class="td_bs">
          <A href="./survey_read_update.do?surveyno=${surveyno}" title="수정"><IMG src="/survey/images/edit.png" class="icon"></A>
          <A href="./survey_read_delete.do?surveyno=${surveyno}" title="삭제"><IMG src="/survey/images/delete.png" class="icon"></A>
        </TD>   
        </c:if>
      </TR> 
      
      
      
    </c:forEach>
    </tbody>
   
  </TABLE>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    
    <FORM name='frm_create' id='frm_create' method='GET' action='./survey_create.do'>
      <label></label>
    <c:if test="${sessionScope.adminid != null }">
    
      <button type="button" id='submit' onclick="location.href='./survey_create.do'" class="btn btn-secondary">등록</button>
      <button type="button" onclick="cancel();" class="btn btn-secondary">취소</button>
    </c:if>
    </FORM>
  </DIV>
  
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 