<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cateno" value="${reviewVO.cateno }" />
<c:set var="reviewno" value="${reviewVO.reviewno }" />
=======
<<<<<<< HEAD
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
=======
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3

<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="postno" value="${reviewVO.postno }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />        
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
<c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
<c:set var="reviewfile1saved" value="${reviewVO.reviewfile1saved }" />
<c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
<c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
<<<<<<< HEAD
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
=======
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
<c:set var="reviewstar" value="${reviewVO.reviewstar }" />
<c:set var="goodcnt" value="${reviewVO.goodcnt }" />
<c:set var="cnt" value="${reviewVO.cnt }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
 

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<<<<<<< HEAD
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
 <link rel="icon" href="/images/travel.png">    
=======
<<<<<<< HEAD
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

</script>
    
<link rel="icon" href="/images/travel.png"> 
=======
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<<<<<<< HEAD
<DIV style='margin-top: 60px; '>
<A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link'>${cateVO.catename } > ????????? ??????</A>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.admin_id != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">??????</A>
      <span class='menu_divide' >???</span>
    </c:if>
    <A href="javascript:location.reload();">????????????</A>
    <span class='menu_divide' >???</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">???????????????</A>    
    <span class='menu_divide' >???</span>
    <A href="./update_text.do?reviewno=${reviewno}">?????? ??????</A>
  </ASIDE> 
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
=======
<DIV class='title_line'>?????? ??????</DIV>


<DIV class='content_body'>
  <ASIDE class="aside_right">

  
  <DIV class='menu_line'></DIV>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>
          <c:choose>
            <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
              <IMG src="/review/storage/${reviewfile1saved }" style='width: 90%;'> 
            </c:when>
            <c:otherwise> <!-- ???????????? ?????? -->
               <IMG src="/review/images/none1.png" style="width: 90%;"> 
            </c:otherwise>
          </c:choose>
          
        </DIV>

        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${reviewtitle}</span>
          <br>
<<<<<<< HEAD
          <FORM name='frm' method='POST' action='./update_file.do' enctype="multipart/form-data">
=======
          <FORM name='frm' method='POST' action='../review/update_file.do' enctype="multipart/form-data">
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
            <input type="hidden" name="reviewno" value="${reviewno }">
            <input type="hidden" name="now_page" value="${param.now_page }">
                
            <br><br> 
            ?????? ????????? ??????<br>  
            <input type='file' name='reviewfile1MF' id='reviewfile1MF' value='' placeholder="?????? ??????"><br>
            <br>
            <div style='margin-top: 20px; clear: both;'>  
<<<<<<< HEAD
              <button type="submit" class="btn btn-primary">????????? ??????</button>
              <button type="submit" class="btn btn-primary">????????? ??????</button>
=======
              <button type="submit" class="btn btn-primary">?????? ?????? ??????</button>
              
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
              <button type="button" onclick="history.back();" class="btn btn-primary">??????</button>
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