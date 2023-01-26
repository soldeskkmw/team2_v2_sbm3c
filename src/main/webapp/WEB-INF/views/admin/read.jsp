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
    if ($('#adminpasswd').val() != $('#adminpasswd2').val()) {
      msg = '입력된 패스워드가 일치하지 않습니다.<br>';
      msg += "패스워드를 다시 입력해주세요.<br>"; 
      
      $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
      $('#modal_title').html('패스워드 일치 여부  확인'); // 제목 
      $('#modal_content').html(msg);  // 내용
      $('#btn_close').attr('data-focus', 'memberpasswd');
      $('#modal_panel').modal();         // 다이얼로그 출력
      
      return false; // submit 중지
    }

    let adminage = $('#adminage').val(); // 태그의 아이디가 'adminage'인 태그의 값
    if ($.trim(adminage).length == 0) { // adminage를 입력받지 않은 경우
      msg = '· 나이를 입력하세요.<br>· 나이 입력은 필수입니다.';
      
      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
      $('#modal_title').html('나이 입력 누락'); // 제목 
      $('#modal_content').html(msg);        // 내용
      $('#btn_close').attr("data-focus", "adminage");  // 닫기 버튼 클릭시 adminage 입력으로 focus 이동
      $('#modal_panel').modal();               // 다이얼로그 출력
      return false;
    } 
    
    let adminname = $('#adminname').val(); // 태그의 아이디가 'adminname'인 태그의 값
	  if ($.trim(adminname).length == 0) { // adminname를 입력받지 않은 경우
	    msg = '· 이름을 입력하세요.<br>· 이름 입력은 필수입니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('이름 입력 누락'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "adminname");  // 닫기 버튼 클릭시 adminname 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
		} 

    let admintel = $('#admintel').val().trim(); // 태그의 아이디가 'admintel'인 태그의 값
	  if (admintel.length == 0) { // id를 입력받지 않은 경우
	    msg = '· 전화번호를 입력하세요.<br>· 전화번호 입력은 필수입니다.';
	    
	    $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
	    $('#modal_title').html('전화번호 입력 누락'); // 제목 
	    $('#modal_content').html(msg);        // 내용
	    $('#btn_close').attr("data-focus", "admintel");  // 닫기 버튼 클릭시 admintel 입력으로 focus 이동
	    $('#modal_panel').modal();               // 다이얼로그 출력
	    return false;
		} 

      let adminreceiver = $('#adminreceiver').val().trim(); // 태그의 아이디가 'adminreceiver'인 태그의 값
      if (adminreceiver.length == 0) { // id를 입력받지 않은 경우
        msg = '· 이메일을 입력하세요.<br>· 이메일 입력은 필수입니다.';
        
        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
        $('#modal_title').html('이메일 입력 누락'); // 제목 
        $('#modal_content').html(msg);        // 내용
        $('#btn_close').attr("data-focus", "adminreceiver");  // 닫기 버튼 클릭시 adminreceiver 입력으로 focus 이동
        $('#modal_panel').modal();               // 다이얼로그 출력
        return false;
      } 

      let zipcode = $('#zipcode').val().trim(); // 태그의 아이디가 'zipcode'인 태그의 값
      if (zipcode.length == 0) { // id를 입력받지 않은 경우
        msg = '· 우편번호를 입력하세요.<br>· 우편번호 입력은 필수입니다.';
        
        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
        $('#modal_title').html('우편번호 입력 누락'); // 제목 
        $('#modal_content').html(msg);        // 내용
        $('#btn_close').attr("data-focus", "zipcode");  // 닫기 버튼 클릭시 zipcode 입력으로 focus 이동
        $('#modal_panel').modal();               // 다이얼로그 출력
        return false;
      } 

      let address1 = $('#address1').val().trim(); // 태그의 아이디가 'address1'인 태그의 값
      if (address1.length == 0) { // id를 입력받지 않은 경우
        msg = '· 주소를 입력하세요.<br>· 주소 입력은 필수입니다.';
        
        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
        $('#modal_title').html('주소 입력 누락'); // 제목 
        $('#modal_content').html(msg);        // 내용
        $('#btn_close').attr("data-focus", "address1");  // 닫기 버튼 클릭시 address1 입력으로 focus 이동
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

  <DIV class='title_line' style="font-size:1.5rem;">관리자 정보 조회 및 수정</DIV>

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
  <FORM name='frm' id='frm' method='POST' action='./update.do'>
    <input type="hidden" name="adminno" id="adminno" value="${adminVO.adminno }">
    <input type="hidden" name="adminid" id="adminid" value="${adminVO.adminid }">
      
    <div class="form_input">
      아이디: ${adminVO.adminid } (변경 불가능) <br> 관리자 등급 : 
        <c:choose>
          <c:when test="${admingrade >= 1 and admingrade <= 10}"><img src='/member/image/man_crown.png' title="메인 관리자" class="icon"> 메인 관리자</c:when>    
          <c:when test="${admingrade >= 11 and admingrade <= 20}"><img src='/member/image/girl_student.png' title="관리자" class="icon"> 관리자</c:when>
          <c:when test="${admingrade >= 30 and admingrade <= 39}"><img src='/member/image/suspended.png' title="정지 관리자" class="icon"> 정지 관리자</c:when>
          <c:when test="${admingrade >= 40 and admingrade <= 49}"><img src='/member/image/ghost.png' title="탈퇴 관리자" class="icon"> 탈퇴 관리자</c:when>
        </c:choose>  
       
        <br>
        <c:choose>
          <c:when test="${adminVO.admingender == 'M'}">성별 : 남자</c:when>    
          <c:when test="${adminVO.admingender == 'W'}">성별 : 여자</c:when>
        </c:choose>  
    </div>   
    
     <input type='text' class="form-control" name='adminage' id='adminage' value="${adminVO.adminage }" required="required" style='width: 10%;' placeholder="나이">
    <div class="form_input">
      <input type='text' class="form-control" name='adminname' id='adminname' 
                value='${adminVO.adminname }' required="required" style='width: 30%;' placeholder="성명">
    </div>   

    <div class="form_input">
      <input type='text' class="form-control" name='admintel' id='admintel' 
                value='${adminVO.admintel }' required="required" style='width: 30%;' placeholder="전화번호"> 예) 010-0000-0000
    </div>
    
    <div class="form_input">
      <input type='text' class="form-control" name='adminreceiver' id='adminreceiver' 
                value='${adminVO.adminreceiver }' required="required" style='width: 30%;' placeholder="이메일">
    </div>
    
        <div class="form_input">
      <input type='text' class="form-control" name='zipcode' id='zipcode' 
                value='${adminVO.zipcode }' style='width: 30%;' placeholder="우편번호">
      <button type="button" id="btn_DaumPostcode" onclick="DaumPostcode()" class="btn btn-secondary">우편번호 찾기</button>
    </div>  

    <div class="form_input">
      <input type='text' class="form-control" name='address1' id='address1' 
                 value=' ${adminVO.address1 }' style='width: 100%;' placeholder="주소">
    </div>   

    <div class="form_input">
      <input type='text' class="form-control" name='address2' id='address2' 
                value=' ${adminVO.address2 }'  style='width: 100%;' placeholder="상세 주소">
    </div>      
  
  
  <!-- ------------------------------ DAUM 우편번호 API 시작 ------------------------------ -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
  // 우편번호 찾기 화면을 넣을 element
  var element_wrap = document.getElementById('wrap');

  function foldDaumPostcode() {
      // iframe을 넣은 element를 안보이게 한다.
      element_wrap.style.display = 'none';
  }

  function DaumPostcode() {
      // 현재 scroll 위치를 저장해놓는다.
      var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
      new daum.Postcode({
          oncomplete: function(data) {
              // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

              // 각 주소의 노출 규칙에 따라 주소를 조합한다.
              // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
              var fullAddr = data.address; // 최종 주소 변수
              var extraAddr = ''; // 조합형 주소 변수

              // 기본 주소가 도로명 타입일때 조합한다.
              if(data.addressType === 'R'){
                  //법정동명이 있을 경우 추가한다.
                  if(data.bname !== ''){
                      extraAddr += data.bname;
                  }
                  // 건물명이 있을 경우 추가한다.
                  if(data.buildingName !== ''){
                      extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                  }
                  // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                  fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
              }

              // 우편번호와 주소 정보를 해당 필드에 넣는다.
              $('#zipcode').val(data.zonecode); // 5자리 새우편번호 사용 ★
              $('#address1').val(fullAddr);  // 주소 ★

              // iframe을 넣은 element를 안보이게 한다.
              // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
              element_wrap.style.display = 'none';

              // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
              document.body.scrollTop = currentScroll;
              
              $('#address2').focus(); //  ★
          },
          // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
          onresize : function(size) {
              element_wrap.style.height = size.height+'px';
          },
          width : '100%',
          height : '100%'
      }).embed(element_wrap);

      // iframe을 넣은 element를 보이게 한다.
      element_wrap.style.display = 'block';
  }
 
</script>
<!-- ------------------------------ DAUM 우편번호 API 종료 ------------------------------ -->
  <div class="form_input">
      <button type="button" id='btn_send' onclick="send()" class="btn btn-secondary">저장</button>
      <button type="button" onclick="history.back()" class="btn btn-secondary">취소</button>
    </div>   
  </FORM>
  </DIV>
  
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

