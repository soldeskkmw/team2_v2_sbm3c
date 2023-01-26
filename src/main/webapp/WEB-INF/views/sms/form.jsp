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
	    let rphone = $('#rphone').val().trim(); // 태그의 아이디가 'id'인 태그의 값
	    if (rphone.length == 0) { // id를 입력받지 않은 경우
	      msg = '· 전화번호를 입력하세요.<br>· 전화번호 입력은 필수입니다.';
	      
	      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	      $('#modal_title').html('전화번호 입력 누락'); // 제목 
	      $('#modal_content').html(msg);        // 내용
	      $('#btn_close').attr("data-focus", "rphone");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
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
    $('#smsForm').submit(); // required="required" 작동 안됨.
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
 
  <DIV class='title_line' style="font-size:1.5rem;">SMS 인증</DIV>
    
    <DIV class='content_body'>
      <DIV style='width: 60%; margin: 0px auto;'>
           <form name="smsForm" id="smsForm" action="./proc.do" method="post">
              <input type="hidden" name="action" value="go"> 
              <input type="hidden" name="smsType" value="S"> <!-- 발송 타입 -->
              <input type="hidden" name="subject" value=""> <!-- 장문(LMS)인 경우(한글30자이내) -->
              
              <!-- 정상적으로 문자가 전송되고 나서 이동할 주소 -->
              <input type="hidden" name="returnurl" id="returnurl" maxlength="64" value="./proc_next.do" size="80">
              
              <a>전송메세지</a><br>
              <textarea name="msg" cols="30" rows="2" style="width: 455px; margin-top:7px;">[www.goingshare.co.kr] [******]을 인증번호란에 입력해주세요.</textarea><br>
              
              ▶ 단문(SMS) : 최대 90byte까지 전송할 수 있습니다.
              <br>
              <br>받는 번호 <input type="text" name="rphone" id="rphone" value='' style="margin-right:7px;"> 예) 011-0112-1112 , '-' 포함해서 입력. <br>
               <!-- SMS 서비스를 가입한 기업의 관리자 전화번호 -->
              <input type="hidden" name="sphone1" value="010"> <!-- 전화번호 첫째자리 -->
              <input type="hidden" name="sphone2" value=""> <!-- 전화번호 둘째자리 2722-->
              <input type="hidden" name="sphone3" value="9751"> <!-- 전화번호 셋째자리 -->
              <br>
              ▶ 통신사 정책에 따라 발신번호와 수신번호가 같은 경우 발송되지 않습니다. 
              
              <button type="button" id='btn_send' onclick="send()" class="btn btn-secondary" style="margin-left:25px;">전송</button>
              
              
           </form>
      </DIV>  
    </DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

