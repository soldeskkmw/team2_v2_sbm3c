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
  function setFocus() {  // focus 이동
    // console.log('btn_close click!');
    
    let tag = $('#btn_close').attr('data-focus'); // data-focus 속성에 선언된 값을 읽음 
    // alert('tag: ' + tag);
    
    $('#' + tag).focus(); // data-focus 속성에 선언된 태그를 찾아서 포커스 이동
  }
  
  function send() { // 회원 가입 처리
    // 패스워드를 정상적으로 2번 입력했는지 확인
    if ($('#memberpasswd').val() != $('#memberpasswd2').val()) {
      msg = '입력된 패스워드가 일치하지 않습니다.<br>';
      msg += "패스워드를 다시 입력해주세요.<br>"; 
      
      $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
      $('#modal_title').html('패스워드 일치 여부  확인'); // 제목 
      $('#modal_content').html(msg);  // 내용
      $('#btn_close').attr('data-focus', 'memberpasswd');
      $('#modal_panel').modal();         // 다이얼로그 출력
      
      return false; // submit 중지
    }

    let age = $('#age').val(); // 태그의 아이디가 'age'인 태그의 값
    if ($.trim(age).length == 0) { // age를 입력받지 않은 경우
      msg = '· 나이를 입력하세요.<br>· 나이 입력은 필수입니다.';
      
      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
      $('#modal_title').html('나이 입력 누락'); // 제목 
      $('#modal_content').html(msg);        // 내용
      $('#btn_close').attr("data-focus", "age");  // 닫기 버튼 클릭시 age 입력으로 focus 이동
      $('#modal_panel').modal();               // 다이얼로그 출력
      return false;
    } 
    
    let membername = $('#membername').val(); // 태그의 아이디가 'membername'인 태그의 값
	  if ($.trim(membername).length == 0) { // membername을 입력받지 않은 경우
	    msg = '· 이름을 입력하세요.<br>· 이름 입력은 필수입니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('이름 입력 누락'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "membername");  // 닫기 버튼 클릭시 membername 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
		} 

    let tel = $('#tel').val().trim(); // 태그의 아이디가 'tel'인 태그의 값
	  if (tel.length == 0) { // tel을 입력받지 않은 경우
	    msg = '· 전화번호를 입력하세요.<br>· 전화번호 입력은 필수입니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('전화번호 입력 누락'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "tel");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
		} 

      let receiver = $('#receiver').val().trim(); // 태그의 아이디가 'receiver'인 태그의 값
      if (receiver.length == 0) { // receiver를 입력받지 않은 경우
        msg = '· 이메일을 입력하세요.<br>· 이메일 입력은 필수입니다.';
        
        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
        $('#modal_title').html('이메일 입력 누락'); // 제목 
        $('#modal_content').html(msg);        // 내용
        $('#btn_close').attr("data-focus", "receiver");  // 닫기 버튼 클릭시 receiver 입력으로 focus 이동
        $('#modal_panel').modal();               // 다이얼로그 출력
        return false;
      } 
    $('#frm').submit(); // required="required" 작동 안됨.
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

  <DIV class='title_line' style="font-size:1.5rem;">회원 정보 조회 및 수정</DIV>

  <DIV class='content_body'>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

 <div class='menu_line'></div>
  
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='POST' action='./read.do'>
    <input type="hidden" name="memberno" id="memberno" value="${memberVO.memberno }">
    <input type="hidden" name="memberid" id="memberid" value="${memberVO.memberid }">
      
    <div class="form_input">
      <div style="margin-bottom:2px;">아이디: ${memberVO.memberid } (변경 불가능)</div> <div style="margin-bottom:2px;"> 회원등급 : 
        <c:choose>
          <c:when test="${grade >= 1 and grade <= 10}"><img src='/member/image/man_crown.png' title="회원 관리자" class="icon"> 회원 관리자</c:when>    
          <c:when test="${grade >= 11 and grade <= 20}"><img src='/member/image/girl_student.png' title="회원" class="icon"> 회원</c:when>
          <c:when test="${grade >= 30 and grade <= 39}"><img src='/member/image/suspended.png' title="정지 회원" class="icon"> 정지 회원</c:when>
          <c:when test="${grade >= 40 and grade <= 49}"><img src='/member/image/ghost.png' title="탈퇴 회원" class="icon"> 탈퇴 회원</c:when>
        </c:choose>  
       </div>
        
        <c:choose>
          <c:when test="${memberVO.gender == 'M'}">성별 : 남자</c:when>    
          <c:when test="${memberVO.gender == 'W'}">성별 : 여자</c:when>
        </c:choose>  
    </div>   
    <input type='text' class="form-control" name='age' id='age' value="${memberVO.age }세" required="required" style='width: 10%; margin-top:3px;' placeholder="나이">            
    <div class="form_input">
      <input type='text' class="form-control" name='membername' id='membername' 
                value='${memberVO.membername }' required="required" style='width: 35%;' placeholder="성명">
    </div>   

    <div class="form_input">
      <input type='text' class="form-control" name='tel' id='tel' 
                value='${memberVO.tel }' required="required" style='width: 35%;' placeholder="전화번호"> 예) 010-0000-0000
    </div>
    
    <div class="form_input">
      <input type='text' class="form-control" name='receiver' id='receiver' 
                value='${memberVO.receiver }' required="required" style='width: 35%;' placeholder="이메일">
    </div>    
    
    <div class="form_input">
      <button type="button" id='btn_send' onclick="location.href='./read.do'" class="btn btn-secondary">수정</button>
      <button type="button" onclick="location.href='./unregister.do'" class="btn btn-secondary">회원 탈퇴</button>
    </div>   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

