<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<<<<<<< HEAD
<title>GoingShare</title>
=======
<title>Resort world</title>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<<<<<<< HEAD
<link rel="icon" href="/images/travel.png">     
=======
    
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</head> 
 
<body>
<c:import url="/menu/top.do" />

<DIV class='content_body'>
  <ASIDE class="aside_left">
    <A href="javascript:location.reload();">${sessionScope.membername }님을 위한 추천</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <DIV id='panel' style='margin: 10px auto; text-align: center; width: 90%;'>
    <DIV style='margin: 0px auto; width: 100%;'>
      <c:forEach var="postVO" items="${list }">
        <c:set var="posttitle" value="${postVO.posttitle }" />
        <c:set var="cateno" value="${postVO.cateno }" />
        <c:set var="postno" value="${postVO.postno }" />
        <c:set var="postthumb1" value="${postVO.postthumb1 }" />
                
        <DIV style='margin: 0px auto; width: 19.5%; float: left; height: 350px;'>
          <a href="/post/read.do?postno=${postno}&cateno=${cateno}"><IMG src="/post/storage/${postthumb1 }" style="width: 95%; height: 200px;"></a>
          <br>
          ${posttitle } 
        </DIV>
      </c:forEach>
    </DIV>
  </DIV>
</DIV>

</body>
 
</html>

