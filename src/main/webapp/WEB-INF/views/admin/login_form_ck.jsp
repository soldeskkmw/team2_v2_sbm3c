<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function() { // click 이벤트 핸들러 등록
    $('#btn_loadDefault').on('click', loadDefault); // 기본 로그인 정보 설정
  });

  // 테스트용 기본값 로딩
  function loadDefault() {
    $('#adminid').val('admin1');
    $('#adminpasswd').val('1234');
  }
    
</script> 

<link rel="icon" href="/images/travel.png">
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line' style="font-size:1.5rem;">관리자 로그인</DIV>

  <DIV class='content_body'> 
    <DIV style='width: 40%; margin: 0px auto;'>
      <FORM name='frm' method='POST' action='./login.do'>
      
         <%-- 로그인 후 자동으로 이동할 페이지 전달 ★ --%>
        <input type="hidden" name="return_url" value="${return_url}">
      
        <div class="form_input">
          <input type='text' class="form-control" name='adminid' id='adminid' 
                    value="${ck_admin_id }" required="required" 
                    style='width: 80%;' placeholder="아이디" autofocus="autofocus">
          <Label>   
            <input type='checkbox' name='adminid_save'  style='accent-color:gray;' value='Y' ${ck_admin_id_save == 'Y' ? "checked='checked'" : "" }> 저장
          </Label>    
        </div>   
     
        <div class="form_input">
          <input type='password' class="form-control" name='adminpasswd' id='adminpasswd' 
                    value='${ck_admin_passwd }' required="required" style='width: 80%;' placeholder="패스워드">
          <Label>
            <input type='checkbox' name='adminpasswd_save'  style='accent-color:gray;' value='Y' ${ck_admin_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
          </Label>                    
        </div>   
     
        <div class="form_input">
          <button type="submit" class="btn btn-secondary">로그인</button>
          <button type='button' id='btn_loadDefault' class="btn btn-secondary">테스트 계정</button>
        </div>   
        
      </FORM>
    </DIV>
  </DIV> <%-- <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

