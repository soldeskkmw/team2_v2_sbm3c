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
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="icon" href="/images/travel.png">    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>설문조사</DIV>

<c:if test="${sessionScope.adminid != null }">
<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
    
      <label style="diplay:inline-block;">설문 주제</label>
      <input type='text' name='surveytopic' value='' required="required" style='width: 35%; display:inline-block;' autofocus="autofocus" class="form-control">
      <label style="diplay:inline-block;">진행 여부</label>
      <input type='text' name='yn' value='Y' required="required" style='width: 5%; display:inline-block;' autofocus="autofocus" class="form-control">

      &nbsp;&nbsp;
      <button type="submit" id='submit' class="btn btn-secondary btn-sm" style="display:inline-block;">등록</button>
      <button type="button" onclick="cancel();" class="btn btn-secondary btn-sm" style="display:inline-block;">취소</button>
    </FORM>
  </DIV>
  </c:if>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 25%;'/>
      <col style='width: 25%;'/>
      <col style='width: 15%;'/>    
      <col style='width: 20%;'/>
      <c:if test="${sessionScope.adminpasswd != null }">
      <col style='width: 15%;'/>
      </c:if>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">설문 번호</TH>
      <TH class="th_bs">주제</TH>
      <TH class="th_bs">진행 여부</TH>
      <TH class="th_bs">등록일</TH>
      <c:if test="${sessionScope.adminpasswd != null }">
      <TH class="th_bs">기타</TH>
      </c:if>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="surveyVO" items="${list_all }">
      <c:set var="surveyno" value="${surveyVO.surveyno }"/>
      <c:set var="surveytopic" value="${surveyVO.surveytopic }"/>
      <c:set var="yn" value="${surveyVO.yn }"/>
      <c:set var="rdate" value="${surveyVO.rdate.substring(0, 16) }"/>
 
    <TR>
        <TD class="td_bs">${surveyno}</TD>
        <TD class="td_bs_left"><A href="../surveyitem/list_by_surveyno.do?surveyno=${surveyno}">${surveytopic }</A></TD>     
       <TD style='vertical-align: middle; text-align:center;'>
        <c:choose>
              <c:when test="${yn eq 'Y' }">
                  <a>진행 중</a>
              </c:when>
              <c:when test="${yn eq 'N' }">
                  <a>종료</a>
              </c:when>
        </c:choose>
        </TD>
        <TD class="td_bs">${rdate}</TD>
        <c:if test="${sessionScope.adminpasswd != null }">
        <TD class="td_bs">
          <A href="../surveyitem/create.do?surveyno=${surveyno}" title="항목 등록"><IMG src="/survey/images/add_red.png" class="icon"></A>
          <A href="./read_update.do?surveyno=${surveyno}" title="수정"><IMG src="/survey/images/file_rename.png" class="icon"></A>
          <A href="./read_delete.do?surveyno=${surveyno}" ><IMG src='/images/trash.png' title='삭제' class="icon"></A> 
         </c:if>   
        </TD>   
      </TR>      
    </c:forEach>
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 