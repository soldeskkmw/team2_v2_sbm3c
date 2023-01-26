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
    회원(관리자 전용)
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
      <col style='width: 20%;'/>
      <col style='width: 30%;'/>
      <col style='width: 20%;'/>
      <col style='width: 25%;'/>
    </colgroup>
    <TR>
      <TH class='th_bs'> </TH>
      <TH class='th_bs'>ID</TH>
      <TH class='th_bs'>성명</TH>
      <TH class='th_bs'>전화번호</TH>
      <TH class='th_bs'>등록일</TH>
      <TH class='th_bs'> </TH>
    </TR>
   
    <c:forEach var="memberVO" items="${list }">
      <c:set var="memberno" value ="${memberVO.memberno}" />
      <c:set var="grade" value ="${memberVO.grade}" />
      <c:set var="memberid" value ="${memberVO.memberid}" />
      <c:set var="membername" value ="${memberVO.membername}" />
      <c:set var="tel" value ="${memberVO.tel}" />
      <c:set var="mdate" value ="${memberVO.mdate}" />
       
    <TR>
      <TD class='td_basic' style="vertical-align:middle;">
        <nav class="nav-item active"">

	        <c:choose>
		        <c:when test="${grade >= 1 and grade <= 10}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/man_crown.png' title=" 회원 관리자" class="icon" ></A></c:when>    
		        <c:when test="${grade >= 11 and grade <= 20}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/girl_student.png' title="회원" class="icon"></A></c:when>
		        <c:when test="${grade >= 21 and grade <= 30}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/suspened.png' title="정지 회원" class="icon"></A></c:when>
		        <c:when test="${grade >= 31 and grade <= 40}"><A class="nav-link dropdown-toggle" data-toggle="dropdown"><img src='/member/image/ghost.png' title="탈퇴 회원" class="icon"></A></c:when>
	        </c:choose>

			    <div class="dropdown-menu">
			      <A class="dropdown-item" href="./grade_update.do?memberno=${memberno}&grade=1" ><img src='/member/image/man_crown.png' title="회원 관리자" class="icon" >회원 관리자</A>
			      <A class="dropdown-item" href="./grade_update.do?memberno=${memberno}&grade=11"><img src='/member/image/girl_student.png' title="회원" class="icon"> 회원</A>
			      <A class="dropdown-item" href="./grade_update.do?memberno=${memberno}&grade=21"><img src='/member/image/suspended.png' title="정지 회원" class="icon"> 정지 회원</A>
			      <A class="dropdown-item" href="./grade_update.do?memberno=${memberno}&grade=31"><img src='/member/image/ghost.png' title="탈퇴 회원" class="icon"> 탈퇴 회원</A>
				  </div>
	      </nav>
      </TD>
      
      <TD class='td_basic' style="vertical-align:middle;"><A href="./read.do?memberno=${memberno}">${memberid}</A></TD>
      <TD class='td_basic' style="vertical-align:middle;"><A href="./read.do?memberno=${memberno}">${membername}</A></TD>
      <TD class='td_basic' style="vertical-align:middle;">${tel}</TD>
      <TD class='td_basic' style="vertical-align:middle;">${mdate.substring(0, 10)}</TD> <%-- 년월일 --%>
      
      <TD class='td_basic' style="vertical-align:middle;">
        <A href="./passwd_update.do?memberno=${memberno}"><IMG src='/images/lock.png' title='패스워드 변경' class="icon"></A>
        <A href="./read.do?memberno=${memberno}"><IMG src='/images/file_rename.png' title='수정' class="icon"></A>
        <A href="./delete.do?memberno=${memberno}"><IMG src='/images/trash.png' title='삭제' class="icon"></A>
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

