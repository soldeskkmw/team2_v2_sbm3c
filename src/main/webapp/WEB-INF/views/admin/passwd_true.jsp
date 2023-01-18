<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  $(function() { // 자동 실행
    $('#btn_send').on('click', send); // id가 'btn_send'인 태그가 클릭되면 'send' 함수 실행 설정 
    $('#btn_close').on('click', setFocus); // Dialog창을 닫은후의 focus 이동
  });

  function send() {
    if ($('#adminpasswd').val() != $('#adminpasswd2').val()) {
      msg = '· 입력된 패스워드가 일치하지 않습니다.<br>';
      msg += "· 패스워드를 다시 입력해주세요.<br>"; 
      
      $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
      $('#modal_title').html('패스워드 일치 여부  확인'); // 제목 
      $('#modal_content').html(msg);  // 내용
      $('#modal_panel').modal();         // 다이얼로그 출력
      
      $('#btn_close').attr("data-focus", "adminpasswd"); // 모달창에서 닫기 버튼 클릭시 focus를 이동할 태그 설정
      
      return false; // submit 중지
    }

    $('#frm').submit();
  }
  
  function setFocus() {  // focus 이동
    var tag = $('#btn_close').attr('data-focus'); // 포커스를 적용할 태그 id 가져오기
    $('#' + tag).focus(); // 포커스 지정
  }
  
</script>

<link rel="icon" href="/images/travel.png">
</head> 
 
 
<body>
<c:import url="/menu/top.do" />
 
  <!-- ******************** Modal 알림창 시작 ******************** -->
  <div id="modal_panel" class="modal fade"  role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'></h4><%-- 제목 --%>
          <button type="button" id='btn_close_modal' class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>  <%-- 내용 --%>
        </div>
        <div class="modal-footer"> <%-- data-focus="": 캐럿을 보낼 태그의 id --%>
          <button type="button" id="btn_close" data-focus="" onclick="setFocus()" class="btn btn-default" 
                      data-dismiss="modal">닫기</button> <%-- data-focus: 캐럿이 이동할 태그 --%>
        </div>
      </div>
    </div>
  </div>
  <!-- ******************** Modal 알림창 종료 ******************** -->
 
  <DIV class='title_line' style="font-size:1.5rem;">
    관리자 탈퇴 패스워드 인증
  </DIV>

  <DIV class='content_body'>
    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./list.do'>목록</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
    
    <div style='width: 40%; margin: 0px auto;'>  
    <FORM name='frm' id='frm' method='get' action='./unregister_proc.do'>
    <input type='hidden' name='adminno' value='${adminno}'>     
      <div class="form_input">
        <label>현재 패스워드</label>    
        <input type='password' class="form-control" name='adminpasswd' 
                    id='adminpasswd' value='' required="required" style='width: 70%;' placeholder="현재 패스워드">
      </div>   
                      
      <div class="form_input">
        <label>현재 패스워드 확인</label>    
        <input type='password' class="form-control" name='adminpasswd2' 
                  id='adminpasswd2' value='' required="required" style='width: 70%;' placeholder="새로운 패스워드">
      </div>      
      
      <div class="form_input">
        <button type="button" id='btn_send' class="btn btn-secondary">확인</button>
        <button type="button" onclick="location.href='./unregister.do'" class="btn btn-secondary">취소</button>
      </div>   

    </FORM>
    </div>
  
</DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 