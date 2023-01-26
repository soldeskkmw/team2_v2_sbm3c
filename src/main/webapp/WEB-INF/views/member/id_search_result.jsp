<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
  
</script>

<link rel="icon" href="/images/travel.png">
</head> 


<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line' style="font-size:1.5rem;">아이디 찾기</DIV>

  <DIV class='content_body'>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./passwd_search.do'>비밀번호 찾기</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
  </ASIDE> 

  <div class='menu_line'></div>
  
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='POST' action='./id_search_result.do'>
  
    <div class="form_input">
      <input type='text' class="form-control" name='membername' id='membername' 
                value='${membername}의 아이디 입니다' required="required" style='width: 30%;'>     <!-- ${memberVO.membername } -->
                
      <input type='text' class="form-control" name='memberid' id='memberid' 
                value='${memberid }' required="required" style='width: 30%;'>     <!-- ${memberVO.memberid } -->
    </div>   

    <div class="form_input">
      <button type="button" onclick="location.href='../member/login.do'" class="btn btn-secondary">로그인</button>
      <button type="button" onclick="location.href='../member/passwd_search.do'" class="btn btn-secondary">비밀번호 찾기</button>
    </div>
   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

