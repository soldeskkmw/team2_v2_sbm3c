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

	// jQuery ajax 요청
	function checkID() {
	  // $('#btn_close').attr("data-focus", "이동할 태그 지정");
	  
	  let frm = $('#frm'); // id가 frm인 태그 검색
	  let memberid = $('#memberid', frm).val(); // frm 폼에서 id가 'id'인 태그 검색
	  let params = '';
	  let msg = '';
	
	  if ($.trim (memberid).length == 0) { // id를 입력받지 않은 경우
	    msg = '· ID를 입력하세요. <br>· ID 입력은 필수 입니다.<br>· ID는 3자이상 권장합니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('ID 중복 확인'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "memberid");  // 닫기 버튼 클릭시 id 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
	  } else {  // when ID is entered
	    params = 'memberid=' + memberid;
	    // var params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
	    // alert('params: ' + params);
	
	    $.ajax({
	      url: './checkID.do', // spring execute
	      type: 'get',  // post
	      cache: false, // 응답 결과 임시 저장 취소
	      async: true,  // true: 비동기 통신
	      dataType: 'json', // 응답 형식: json, html, xml...
	      data: params,      // 데이터
	      success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우: {"cnt":1}
	        // alert(rdata);
	        let msg = "";
	        
	        if (rdata.cnt > 0) {
	          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	          msg = "이미 사용중인 ID 입니다.";
	          $('#btn_close').attr("data-focus", "memberid");  // id 입력으로 focus 이동
	        } else {
	          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap CSS 변경
	          msg = "사용 가능한 ID 입니다.";
	          $('#btn_close').attr("data-focus", "memberpasswd");  // passwd 입력으로 focus 이동
	          // $.cookie('checkId', 'TRUE'); // Cookie 기록
	        }
	        
	        $('#modal_title').html('ID 중복 확인'); // 제목 
	        $('#modal_content').html(msg);        // 내용
	        $('#modal_panel').modal();              // 다이얼로그 출력
	      },
	      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
	      error: function(request, status, error) { // callback 함수
	        console.log(error);
	      }
	    });
	    
	    // 처리중 출력
	/*     var gif = '';
	    gif +="<div style='margin: 0px auto; text-align: center;'>";
	    gif +="  <img src='/member/images/ani04.gif' style='width: 10%;'>";
	    gif +="</div>";
	    
	    $('#panel2').html(gif);
	    $('#panel2').show(); // 출력 */
	    
	  }
	
	}

  function setFocus() {  // focus 이동
    // console.log('btn_close click!');
    
    let tag = $('#btn_close').attr('data-focus'); // data-focus 속성에 선언된 값을 읽음 
    // alert('tag: ' + tag);
    
    $('#' + tag).focus(); // data-focus 속성에 선언된 태그를 찾아서 포커스 이동
  }
  
  function send() { // 회원 가입 처리
	  
	   let memberid = $('#memberid').val(); // 태그의 아이디가 'id'인 태그의 값
	    if ($.trim(memberid).length == 0) { // id를 입력받지 않은 경우
	      msg = '· ID를 입력하세요.<br>· ID 입력은 필수 입니다.<br>· ID는 3자이상 권장합니다.';
	      
	      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	      $('#modal_title').html('ID 중복 확인'); // 제목 
	      $('#modal_content').html(msg);        // 내용
	      $('#btn_close').attr("data-focus", "memberid");  // 닫기 버튼 클릭시 id 입력으로 focus 이동
	      $('#modal_panel').modal();               // 다이얼로그 출력
	      return false;
	    } 
	   
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

	    let membername = $('#membername').val(); // 태그의 아이디가 'id'인 태그의 값
	    if ($.trim(membername).length == 0) { // id를 입력받지 않은 경우
	      msg = '· 이름을 입력하세요.<br>· 이름 입력은 필수입니다.';
	      
	      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	      $('#modal_title').html('이름 입력 누락'); // 제목 
	      $('#modal_content').html(msg);        // 내용
	      $('#btn_close').attr("data-focus", "membername");  // 닫기 버튼 클릭시 mname 입력으로 focus 이동
	      $('#modal_panel').modal();               // 다이얼로그 출력
	      return false;
	    } 
	    

	  let age = $('#age').val(); // 태그의 아이디가 'id'인 태그의 값
	    if ($.trim(age).length == 0) { // id를 입력받지 않은 경우
	      msg = '· 나이를 입력하세요.<br>· 나이 입력은 필수입니다.';
	      
	      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	      $('#modal_title').html('나이 입력 누락'); // 제목 
	      $('#modal_content').html(msg);        // 내용
	      $('#btn_close').attr("data-focus", "age");  // 닫기 버튼 클릭시 mname 입력으로 focus 이동
	      $('#modal_panel').modal();               // 다이얼로그 출력
	      return false;
	    } 


    let tel = $('#tel').val().trim(); // 태그의 아이디가 'id'인 태그의 값
	  if (tel.length == 0) { // id를 입력받지 않은 경우
	    msg = '· 전화번호를 입력하세요.<br>· 전화번호 입력은 필수입니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('전화번호 입력 누락'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "tel");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
		} 
		
	    let receiver = $('#receiver').val().trim(); // 태그의 아이디가 'id'인 태그의 값
	    if (receiver.length == 0) { // id를 입력받지 않은 경우
	      msg = '· 이메일을 입력하세요.<br>· 이메일 입력은 필수입니다.';
	      
	      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	      $('#modal_title').html('이메일 입력 누락'); // 제목 
	      $('#modal_content').html(msg);        // 내용
	      $('#btn_close').attr("data-focus", "receiver");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
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

  <DIV class='title_line' style="font-size:1.5rem;">회원 가입</DIV>

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
  <FORM name='frm' id='frm' method='POST' action='./create.do'>
  
   
    
    <div class="form_input" style="text-align:left;">
      <input type='text' class="form-control" name='memberid' id='memberid' value='' required="required" style="width:35%; display:inline-block; margin-right:7px;"placeholder="아이디" autofocus="autofocus">
      <button type='button' id="btn_checkID" onclick="checkID()" style="display:inline-block;" class="btn btn-secondary">중복확인</button>
    </div>   
         
    <div class="form_input">
      <input type='password' class="form-control" name='memberpasswd' id='memberpasswd' value='' required="required" style='width: 35%;' placeholder="패스워드">
    </div>   

    <div class="form_input">
      <input type='password' class="form-control" name='memberpasswd2' id='memberpasswd2' value='' required="required" style='width: 35%;' placeholder="패스워드 확인">
    </div>   
    
    <div class="form_input">
      <input type='text' class="form-control" name='membername' id='membername' 
                value='' required="required" style='width: 35%;' placeholder="성명">
    </div> 
    
    <div class="form_input">
      성별 
      <input type="radio" id = "M" name="gender" value="M" required="required" style="margin-left:15px;"> 남
      <input type="radio" id = "W" name="gender" value="W" required="required" style="margin-left:5px;"> 여<br>
      
      <input type='text' class="form-control" name='age' id='age' value='' required="required" style='width:15%; margin-top:7px;' placeholder="나이">
    </div>  

    <div class="form_input">
      <input type='text' class="form-control" name='tel' id='tel' 
                value='' required="required" style='width: 35%;' placeholder="전화번호"> 예) 010-0000-0000
    </div>
    
    <div class="form_input">
      <input type='text' class="form-control" name='receiver' id='receiver' 
                value='' required="required" style='width: 35%; margin-bottom:20px;' placeholder="이메일">
    </div> 

    <div class="form_input">
      <button type="button" id='btn_send' onclick="send()" class="btn btn-secondary">가입</button>
      <button type="button" onclick="history.back()" class="btn btn-secondary">취소</button>
    </div>   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

