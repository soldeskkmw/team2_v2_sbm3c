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
     let surveytopic = $('#surveytopic').val().trim(); // 태그의 아이디가 'id'인 태그의 값
     if (surveytopic.length == 0) { // id를 입력받지 않은 경우
       msg = '· 설문 제목을 입력하세요.<br>· 설문 제목 입력은 필수입니다.';
       
       $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
       $('#modal_title').html('설문 제목 입력 누락'); // 제목 
       $('#modal_content').html(msg);        // 내용
       $('#btn_close').attr("data-focus", "surveytopic");  // 닫기 버튼 클릭시 tel 입력으로 focus 이동
       $('#modal_panel').modal();               // 다이얼로그 출력
       return false;
     } 
      $('#frm').submit(); // required="required" 작동 안됨.
    }
 
         $(document).ready(function () {
             let num = 0;
             let msg = "";
                    
          $("button[name='show']").click(function () {
            if(num < 7){
             num = num + 1;
                 
                msg = "질문 " + num +
                   "<input type='text' class='form-control' name='surveyitem" + num + "' id='surveyitem" + num + 
                "' value='' placeholder='설문 질문을 입력하세요' style='width: 700px;'><br>"
                $("le").append(msg);
            }
           });
         });


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
 
<DIV class='title_line'>설문조사 등록</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminid != null }">
        <A href="./survey_create.do">등록</A>
        <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./survey_list.do">기본 목록형</A>    

  </ASIDE> 
  
  </DIV>
  
  
  <DIV class='menu_line' ></DIV>
  <%--등록 폼 --%>
  
  <FORM name='frm' id='frm' method='POST' action='./survey_create.do'  style="maring-left:20px;">

    <input type="hidden" name="surveyno" value="1">  <%--관리자만 등록 가능 --%>
    
    <div>
       <label>설문 제목</label>
       <input type='text' id='surveytopic' name='surveytopic' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 50%;'>
    </div>
    
    <div>
       <label>시작 날짜</label>
       <input type='date' name='startdate' value='' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>
    <div>
       <label>종료 날짜</label>
       <input type='date' name='enddate' value='' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>
   
    <div>
      <p>진행 여부</p>
      <label><input type="radio" name="yn" value='Y'> 진행 중</label> 
      <label><input type="radio" name="yn" value='N'> 종료</label>
      
    </div>   
    
    <div class="surveyitem">
    <br>
        <button type="button" name ="show" id="show" class="btn btn-secondary btn-sm">설문 내용 추가</button>
        <br><br>
        
    </div>
    
      <div class='form-group'>
      
      
      <le></le>
      
      </div>
    
    <div class="content_body_bottom">
      <button type="button" id='btn_send' onclick="send()" class="btn btn-secondary">등록</button>
      <button type="button" onclick="location.href='./survey_list.do'" class="btn btn-secondary">목록</button>
    </div>
  
  </FORM>
 

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>



