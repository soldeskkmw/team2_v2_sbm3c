<%@ page contentType="text/html; charset=UTF-8" %>
 
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
<jsp:include page="../menu/top.jsp" flush='false' />
  <DIV class='title_line'>
    관리자 정보 조회 및 수정
  </DIV>

  <DIV class='content_body'>    
    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do'>회원 가입</A>
      <span class='menu_divide' >│</span> 
      <A href='./list.do'>목록</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
    
    <DIV id='main_panel'></DIV>
   
    <!-- Modal -->
    <div class="modal fade" id="modal_panel" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h4 class="modal-title" id='modal_title'></h4> <!-- 제목 -->
          </div>
          <div class="modal-body">
            <p id='modal_content'></p> <!-- 내용 -->
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
        
      </div>
    </div> <!-- Modal END -->
      
    <FORM name='frm' id='frm' method='POST' action='./update.do' 
                onsubmit="return send();" class="form-horizontal">
      <input type='hidden' name='adminno' id='adminno' value='${adminVO.adminno }'>          
      <input type='hidden' name='adminid' id='adminid' value='${adminVO.adminid }'>          
   
      <div class="form-group">
        <label class="col-md-2 control-label" style='font-size: 0.9em;'>아이디</label>    
        <div class="col-md-10">
          ${adminVO.adminid } (변경 불가능 합니다.)       
        </div>
      </div>   
                  
      <div class="form-group">
        <label class="col-md-2 control-label" style='font-size: 0.9em;'>성명</label>    
        <div class="col-md-10">
          <input type='text' class="form-control" name='adminname' id='adminname' 
                     value='${adminVO.adminname }' required="required" autofocus="autofocus" 
                     style='width: 30%;' placeholder="성명">
        </div>
      </div>   
   
      <div class="form-group">
        <label class="col-md-2 control-label" style='font-size: 0.9em;'>전화번호</label>    
        <div class="col-md-10">
          <input type='text' class="form-control" name='admintel' id='admintel' 
                     value='${adminVO.admintel }' required="required" style='width: 30%;' placeholder="전화번호"> 예) 010-0000-0000
        </div>
      </div>   
   
      <div class="form-group">
        <div class="col-md-12">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-offset-2 col-md-10">
          <button type="submit" class="btn btn-primary btn-md">저장</button>
          <button type="button" onclick="history.go(-1);" class="btn btn-primary btn-md">취소</button>
   
        </div>
      </div>   
    </FORM>
  </DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

