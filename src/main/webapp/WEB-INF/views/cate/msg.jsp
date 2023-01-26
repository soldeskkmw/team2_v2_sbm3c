<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
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

<DIV class='title_line' style="font-size:1.5rem;">카테고리 > 알림</DIV>
code: ${code} / cnt: ${cnt}
<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${test == 'create_fail' }">
	        <LI class='li_none'>
	          <span class="span_fail">새로운 카테고리 [${catename }] 등록에 실패 했습니다.</span>
	        </LI>
        </c:when>

        <c:when test="${test == 'update_fail' }">
	        <LI class='li_none'>
	          <span class="span_fail">카테고리 [${catename }] 수정을 실패 했습니다.</span>
	        </LI>  
        </c:when>
        
        <c:when test="${test == 'delete_fail' }">
	        <LI class='li_none'>
	          <span class="span_fail">카테고리 [${catename }] 삭제를 실패 했습니다.</span>
	        </LI>  
        </c:when>        
                
      </c:choose>
    
      <LI class='li_none'>
        <br>
        <c:choose>
          <c:when test="${cnt == 0 }">
          	<button type='button' onclick="history.back()" class="btn btn-danger">다시 시도</button>
          </c:when>
          
          <c:when test="${cnt > 0 }">
            <button type='button' onclick="location.href='./create.do'" class="btn btn-info">새로운 카테고리 등록</button>
          </c:when>
        </c:choose>
  
        <button type='button' onclick="location.href='./list_all.do'" class="btn btn-info">전체 목록</button>
      </LI>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

