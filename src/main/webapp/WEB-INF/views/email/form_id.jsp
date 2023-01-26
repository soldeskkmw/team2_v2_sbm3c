<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script type="text/javascript">

function setFocus() {  // focus 이동
    // console.log('btn_close click!');
    
    let tag = $('#btn_close').attr('data-focus'); // data-focus 속성에 선언된 값을 읽음
    // alert('tag: ' + tag);
    
    $('#' + tag).focus(); // data-focus 속성에 선언된 태그를 찾아서 포커스 이동
  }
  
  function send() { // 회원 가입 처리
      let receiver = $('#receiver').val().trim(); // 태그의 아이디가 'id'인 태그의 값
      if (receiver.length == 0) { // id를 입력받지 않은 경우
        msg = '· email을 입력하세요.<br>· email 입력은 필수입니다.';
        
        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
        $('#modal_title').html('email 입력 누락'); // 제목 
        $('#modal_content').html(msg);        // 내용
        $('#btn_close').attr("data-focus", "email");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
        $('#modal_panel').modal();               // 다이얼로그 출력
        return false;
      } 
  
   /*   // 패스워드를 정상적으로 2번 입력했는지 확인
      if ($('#tel').val() != $('#membertel').val()) {
        msg = '등록된 전화번호가 없습니다.<br>';
        msg += "전화번호를 다시 입력해주세요.<br>"; 
        
        $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
        $('#modal_title').html('전화번호 일치 여부  확인'); // 제목 
        $('#modal_content').html(msg);  // 내용
        $('#btn_close').attr('data-focus', 'tel');
        $('#modal_panel').modal();         // 다이얼로그 출력
        
        return false; // submit 중지
      } */
    $('#emailForm').submit(); // required="required" 작동 안됨.
  }  


</script> 
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
 
  <DIV class='title_line' style="font-size:1.5rem;">인증번호 받는 email 입력</DIV>
    
    <DIV class='content_body'>
      <DIV style='width: 60%; margin: 0px auto;'>
           <form name="emailForm" id="emailForm" action="./send.do" method="POST">
             <!-- 정상적으로 문자가 전송되고 나서 이동할 주소 -->
             <br>
              전송메세지<br>
              <textarea name="msg" cols="30" rows="2" style="width: 500px; margin-top:7px;">[www.goingshare.co.kr] [******]을 인증번호란에 입력해주세요.</textarea>
              <br>
              <div style="text-align:left;">
              <br>받는 이메일 <input type="text" name="receiver" id="receiver" value='' required="required"  style="width:35%;"> <br>
              
              <button type="button" id='btn_send' onclick="send()" class="btn btn-secondary"  style="margin-bottom:30px; float:right; margin-right:170px;">전송</button>
              </div>
               <!-- SMS 서비스를 가입한 기업의 관리자 전화번호 -->
              <br>
              <input type="hidden" name="from" value='fset663517@gmail.com' class='input_basic' style='width: 90%;'>
              <input type="hidden" name="title" value="OJT 메일을 보냅니다. IP: 100" class='input_basic' style='width: 90%;'>
              <textarea name="content" rows="15" hidden="" style='width: 100%; border: #AAAAAA 1px solid;'>[www.goingsware.co.kr] [${auth_no }]을 인증번호란에 입력해주세요.</textarea>
              
           </form>
      </DIV>  
    </DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

