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
    sms 관리자 인증번호 목록
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
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    </colgroup>
    <TR>
      <TH class='th_bs'>찾기</TH>
      <TH class='th_bs'>순서</TH>
      <TH class='th_bs'>관리자 번호</TH>
      <TH class='th_bs'>아이피</TH>
      <TH class='th_bs'>인증번호</TH>
      <TH class='th_bs'>발행일</TH>
      <TH class='th_bs'> </TH>
    </TR>
   
    <c:forEach var="smsVO" items="${admin_sms_list }">
      <c:set var="search" value ="${smsVO.search}" />
      <c:set var="smsno" value ="${smsVO.smsno}" />
      <c:set var="adminno" value ="${smsVO.adminno}" />
      <c:set var="ip" value ="${smsVO.ip}" />
      <c:set var="authno" value ="${smsVO.authno}" />
      <c:set var="mdate" value ="${smsVO.mdate}" />
       
    <TR>
    
      <TD class='td_basic'>${search}</TD>
      <TD class='td_basic'>${smsno}</TD>
      <TD class='td_basic'>${adminno}</TD>
      <TD class='td_basic'>${ip}</TD>
      <TD class='td_basic'>${authno}</TD>
      <TD class='td_basic'>${mdate.substring(0, 10)}</TD> <%-- 년월일 --%>
      <TD class='td_basic'>
      <A href="./delete.do?smsno=${smsno}"><IMG src='/images/trash.png' title='삭제' class="icon"></A>
     
      </TD>
      
    </TR>
    </c:forEach>
    
  </TABLE>
   
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.reload();" class="btn btn-secondary">새로 고침</button>
  </DIV>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

