<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="icon" href="/images/travel.png">     
</head>  
 
<body>   
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>설문 조사</DIV>

<DIV class='content_body'>

  <FORM name='frm' method='POST' action='./create.do'><!-- /survey 폴더 자동 인식, 권장 -->
    <div>
       <label>설문 제목</label>
       <input type='text' name='surveytopic' value='' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 50%;'>
    </div>
    <div>
       <label>출력</label>
       <input type='text' name='yn' value='Y' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 50%;'>
    </div>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info btn-secondary">진행</button>      
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-info btn-secondary">전체 목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 