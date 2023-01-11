<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>


<c:import url="/menu/top.do" />

  
  <DIV class='menu_line'></DIV>
  <%--등록 폼  --%> <%--등록 폼 위쪽 코드들 제거 -> post no 및 vo 받아와서 설정 후 다시 코드 작성--%>
  <FORM name='frm' method='POST' action='../review/create.do' enctype="multipart/form-data">
    <input type="hidden" name="postno" value="${postno }">
    <input type="hidden" name="memberno" value="${sessionScope.memberno }"> <%-- 관리자 개발후 변경 필요 --%>
    
    <div>
       <label>리뷰 제목</label>
       <input type='text' name='reviewtitle' value='가을 영화' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>리뷰</label>
       <textarea name='reviewcontent' required="required" class="form-control" rows="12" style='width: 100%;'>가을 단풍보며 멍때리기</textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='reviewword' value='월터,벤 스틸러,크리스튼위그,휴먼,도전' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
   <!-- 파일 부분 삭제함 추가요망  -->   
   <!-- 패스워드 안써서 삭제 -->
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="javascript:history.back();" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

