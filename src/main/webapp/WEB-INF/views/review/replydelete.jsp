<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="reviewno" value="${replyVO.reviewno }" />
<c:set var="replyno" value="${replyVO.replyno }" />
   <c:set var="replycontent" value="${replyVO.replycontent }" />     

           
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
<%-- <c:import url="/menu/top.do" /> 신형 top --%>
 <jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>댓글 삭제</DIV>

<DIV class='content_body'>
  
  
  

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>

   
            댓글 번호 = ${replyno }
        <br>  
         댓글 내용 : ${replycontent} 
             
           
      
        </DIV>

        <DIV style='text-align: left; width: 47%; float: left;'>
        
       
       
          <FORM name='frm' method='POST' action='./replydelete.do'>
              <input type='hidden' name='replyno' value='${replyno}'>
                            <input type='hidden' name='now_page' value='${param.now_page}'>
              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="btn btn-primary">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="btn btn-primary">취소</button>
              </div>   
          </FORM>
        </DIV>
      </li>
     </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

