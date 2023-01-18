<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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


 
<script type="text/javascript">
  $(function(){
 
  });
</script>

<link rel="icon" href="/images/travel.png">
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line' style="font-size:1.5rem;">
    관리자 가입 허가(메인 관리자 전용)
  </DIV>

  <DIV class='content_body'>

    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='../sms/sms_list.do'>sms 인증번호 목록</A>
      <span class='menu_divide' >│</span> 
      <A href='../member/list.do'>목록</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
    
   
    <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style='width: 5%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 25%;'/>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
    </colgroup>
    <TR>
      <TH class='th_bs'> </TH>
      <TH class='th_bs'>성별</TH>
      <TH class='th_bs'>ID</TH>
      <TH class='th_bs'>성명</TH>
      <TH class='th_bs'>전화번호</TH>
      <TH class='th_bs'>등록일</TH>
      <TH class='th_bs'> </TH>
    </TR>
   
    <c:forEach var="adminVO" items="${permission_list }">
      <c:set var="adminno" value ="${adminVO.adminno}" />
      <c:set var="admingrade" value ="${adminVO.admingrade}" />
      <c:set var="adminid" value ="${adminVO.adminid}" />
      <c:set var="adminname" value ="${adminVO.adminname}" />
      <c:set var="admintel" value ="${adminVO.admintel}" />
      <c:set var="mdate" value ="${adminVO.mdate}" />
       
    <TR>
      <TD class='td_basic' style="vertical-align:middle;">
        <nav class="nav-item active">
	        <c:choose>
		        <c:when test="${admingrade >= 1 and admingrade <= 10}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/man_crown.png' title="메인 관리자" class="icon"></A></c:when>    
		        <c:when test="${admingrade >= 11 and admingrade <= 20}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/girl_student.png' title="관리자" class="icon"></A></c:when>
		        <c:when test="${admingrade >= 21 and admingrade <= 30}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/suspended.png' title="정지 관리자" class="icon"></A></c:when>
		        <c:when test="${admingrade >= 31 and admingrade <= 40}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/ghost.png' title="탈퇴 관리자" class="icon"></A></c:when>
	        </c:choose>
			    <div class="dropdown-menu">
			      <A class="dropdown-item" href="./grade_update.do?adminno=${adminno}&admingrade=1"><img src='/member/image/man_crown.png' title="메인 관리자" class="icon"> 메인 관리자</A>
			      <A class="dropdown-item" href="./grade_update.do?adminno=${adminno}&admingrade=11"><img src='/member/image/girl_student.png' title="관리자" class="icon"> 관리자</A>
			      <A class="dropdown-item" href="./grade_update.do?adminno=${adminno}&admingrade=21"><img src='/member/image/suspended.png' title="정지 관리자" class="icon"> 정지 관리자</A>
			      <A class="dropdown-item" href="./grade_update.do?adminno=${adminno}&admingrade=31"><img src='/member/image/ghost.png' title="탈퇴 관리자" class="icon"> 탈퇴 관리자</A>
				  </div>
	      </nav>
      </TD>
      
      <TD class='td_basic' style="vertical-align:middle;">
        <c:choose>
          <c:when test="${adminVO.admingender == 'M'}">남</c:when>    
          <c:when test="${adminVO.admingender == 'W'}">여</c:when>
        </c:choose>  
      </TD>
      <TD class='td_basic' style="vertical-align:middle;"><A href="./read.do?adminno=${adminno}">${adminid}</A></TD>
      <TD class='td_basic' style="vertical-align:middle;"><A href="./read.do?adminno=${adminno}">${adminname}</A></TD>
      <TD class='td_basic' style="vertical-align:middle;">${admintel}</TD>
      <TD class='td_basic' style="vertical-align:middle;">${mdate.substring(0, 10)}</TD> <%-- 년월일 --%>
      
      <TD class='td_basic' style="vertical-align:middle;">
        <A href="./update_permission.do?adminno=${adminno}"><IMG src='/admin/images/permission.png' title='허가' class="icon"></A>
        <A href="./delete.do?adminno=${adminno}"><IMG src='/images/delete1.png' title='삭제' class="icon"></A>
      </TD>
      
    </TR>
    </c:forEach>
    
  </TABLE>
   
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./create.do'" class="btn btn-secondary">등록</button>
    <button type='button' onclick="location.reload();" class="btn btn-secondary">새로 고침</button>
  </DIV>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

