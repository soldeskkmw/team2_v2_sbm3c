<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<<<<<<< HEAD

<c:set var="cateno" value="${reviewVO.cateno }" />
=======
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.member.MemberVO" %>

>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="membername" value="${reviewVO.membername }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />    
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
<c:set var="reviewcnt" value="${reviewVO.reviewcnt }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
<c:set var="reviewrdate" value="${reviewVO.reviewrdate.substring(0, 10) }" />
<c:set var="reviewudate" value="${reviewVO.reviewudate.substring(0, 10) }" />
<c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
<c:set var="reviewfile1saved" value="${reviewVO.reviewfile1saved }" />
<c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
<c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
<<<<<<< HEAD

=======
<c:set var="reviewstar" value="${reviewVO.reviewstar }" />
<c:set var="goodcnt" value="${reviewVO.goodcnt }" />
<c:set var="cnt" value="${reviewVO.cnt }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
<c:set var="memberno" value="${reviewVO.memberno }" />
<<<<<<< HEAD

=======
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
<!DOCTYPE html> 

<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 

<<<<<<< HEAD
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/JavaScript">
=======
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/JavaScript">

>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</script>
    
<link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" /> 

<<<<<<< HEAD
<DIV class='title_line'  style="width:80%; margin-top: 60px;">
  <A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link' style="font-size:1.7rem; font-weight:600;">${cateVO.catename }</A>
  <ASIDE class="aside_right" style="margin-top: 10px; font-size:1rem;">
    <c:if test="${sessionScope.memberpasswd == memberVO.memberpasswd }">
      <A href="./update_text.do?reviewno=${reviewno}">?????? ??????</A>
      <span class='menu_divide' >???</span>
      <A href="./update_file.do?reviewno=${reviewno}">????????? ??????</A>
      <span class='menu_divide' >???</span>
      <A href="./delete.do?reviewno=${reviewno}&cateno=${cateno}">?????? ??????</A>  
      <span class='menu_divide' >???</span>
    </c:if>
    <A href="javascript:location.reload();">????????????</A>
    <span class='menu_divide' >???</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }&now_page=${param.now_page}&reviewword=${param.reviewword }">?????? ??????</A>    
  </ASIDE> 
</DIV>
=======
 
<DIV class='title_line'><span style="font-size: 1.5em; font-weight: bold;">??????</span></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
     

    
    <A href="javascript:location.reload();">????????????</A>
  
 
  
    
    <%--<c:if test="${sessionScope.admin_id != null }"> --%>
      
    	<%--<A href="./create.do?postno=${postVO.postno  }">??????</A> --%>
    
		
		
		
		<c:choose>
              <c:when test="${sessionScope.adminid == null}">
                            
                            <c:choose>
              									<c:when test="${sessionScope.memberid == null}">
                              
               									</c:when>
                						<c:otherwise>
                									<span class='menu_divide' >???</span>
                    										<A href="./create.do?postno=1">??????</A>
     															<span class='menu_divide' >???</span>     
<<<<<<< HEAD
=======
<<<<<<< HEAD
     																		<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">??? ??????</A>   
=======
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
     																		<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">??? ??????</A>  
     																			<span class='menu_divide' >???</span>
    																<A href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">?????? ??????</A>
     																		
     												
                              <c:choose>
                              	<c:when test="${sessionScope.memberno == memberno}">
                              	<span class='menu_divide' >???</span>  
                              	<A href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}&postno=1<%--${postno } --%>">??????</A>  
               									</c:when>
                              </c:choose>
               								
     																		
     																		
     																		 
<<<<<<< HEAD
=======
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
               							</c:otherwise>
														</c:choose>                                                                                                             
               </c:when>
                <c:otherwise>
                <span class='menu_divide' >???</span>
                    										<A href="./create.do?postno=1">??????</A>
     															<span class='menu_divide' >???</span>     
     																		<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">??? ??????</A>   
                
                    	<span class='menu_divide' >???</span>   
    	<%--<A href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">?????? ??????</A>
    	<span class='menu_divide' >???</span>--%>
    	<A href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}&postno=1<%--${postno } --%>">??????</A>  
  	<%--</c:if> --%>
    	  
               </c:otherwise>
		</c:choose>
<<<<<<< HEAD
=======
<<<<<<< HEAD
		
		
    	 
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
=======
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
  </ASIDE> 
  
  
  <DIV class='menu_line'></DIV>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

<DIV class='content_body'>
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; margin-bottom: 30px;">
          <span style="font-size: 2em; font-weight: bold;">${reviewtitle }</span><br>
        </DIV>
        <DIV style="width: 100%; margin-bottom: 20px;">
          <span>??????? BY ${membername } </span>
          <c:choose>
            <c:when test="${reviewudate != null}"><span>${reviewudate }</span></c:when>
            <c:otherwise><span>${reviewrdate }</span></c:otherwise>
          </c:choose>
        </DIV>
        <DIV style="width: 50%; float:left; margin-bottom:10px;">
            <c:choose>
              <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
                <A href="/review/storage/${reviewfile1saved }"><IMG src="/review/storage/${reviewfile1saved }" style="width: 70%;"></A> 
              </c:when>
              <c:otherwise> <!-- ?????? ????????? ?????? -->
                <IMG src="/review/images/none1.png" style="width: 70%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 50%; margin-bottom:40px; font-size: 1.1em;">
          <c:if test="${reviewfile1.trim().length() > 0 }">
            ???? ?????? ????????? ????????????: <A href='/download?dir=/review/storage&reviewfilename=${reviewfile1saved}&downname=${reviewfile1}'>${reviewfile1}</A>???? (${reviewsize1_label}) 
            <A href='/download?dir=/review/storage&reviewfilename=${reviewfile1saved}&downname=${reviewfile1}'></A>
          </c:if>
        </DIV>
        <DIV class='menu_line'></DIV>
        <DIV style="width:80%; margin: 0 auto; margin-bottom:80px;">${reviewcontent }</DIV>
      </li>
      <li class="li_none">
        <DIV class='menu_line'></DIV>
        <DIV style='margin:30px 0;'>
          <h4><span class="badge badge-pill badge-dark">Keyword</span></h4>
          <DIV style='margin:10px;'>${reviewword }</DIV>
        </DIV>
      </li>
      <li class="li_none">
        <DIV class='menu_line'></DIV>
        <DIV style='margin:30px 0;'>
          <h4><span class="badge badge-pill badge-dark">?????? ${replycnt }</span></h4>
          <DIV style='margin:10px;'>${reviewword }</DIV>
        </DIV>
      </li>
    </ul>
  </fieldset>

</DIV>
 
<<<<<<< HEAD
=======
 <DIV class='title_line'><A  class='title_link' style="font-size:30px">??????</A></DIV>
 
 
 <c:forEach var="ReplyVO" items="${replylist }">
        <c:set var="reviewno1" value="${ReplyVO.reviewno }" />      
  			<c:set var="replycontent1" value="${ReplyVO.replycontent }" />
        <c:set var="replyno1" value="${ReplyVO.replyno }" />
        <c:set var="memberno1" value="${ReplyVO.memberno }" />     
        <c:set var="memberid1" value="${ReplyVO.memberid }" />
<<<<<<< HEAD
        [ ${memberid1} ]      
=======
<<<<<<< HEAD
        [ ${memberid1} ]
        
=======
        [ ${memberid1} ]      
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
        
<c:choose>
					<c:when test="${sessionScope.memberid ==memberid1 }">
                    <A href="./replydelete.do?now_page=${param.now_page}&replyno=${replyno1}"><strong class="aside_right">????????????</strong></A>     
           </c:when>
           <c:otherwise>
               	  <c:choose>
              						<c:when test="${sessionScope.adminid == null}">
                              
               						</c:when>
                					<c:otherwise>
               									<A href="./replydelete.do?now_page=${param.now_page}&replyno=${replyno1}"><strong class="aside_right">????????????</strong></A>   	  
               						</c:otherwise>
									</c:choose> 
           </c:otherwise>
</c:choose>
        
           
           
        <input type='text' name='replycontent' readonly value='${replycontent1}' required="required" 
                  class="form-control" style='width: 100%;'>               
        <br>
</c:forEach>         
 
 
  <%--?????? ???  --%> <%--?????? ??? ?????? ????????? ?????? -> post no ??? vo ???????????? ?????? ??? ?????? ?????? ??????--%>
  <FORM name='frm' method='POST' action='./replycreate.do' enctype="multipart/form-data">
<<<<<<< HEAD
    <input type="hidden" name="postno" value=${postno }>
    <input type="hidden" name="cateno" value=${cateno }>
=======
<<<<<<< HEAD
    <input type="hidden" name="postno" value="1">
=======
    <input type="hidden" name="postno" value=${postno }>
    <input type="hidden" name="cateno" value=${cateno }>
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    <input type="hidden" name="memberno" value="${sessionScope.memberno }"> <%-- ????????? ????????? ?????? ?????? --%>
  	<input type="hidden" name="reviewno" value="${reviewno }">
  	<input type="hidden" name="memberid" value="${sessionScope.memberid }">
    
      
<c:choose>
		<c:when test="${sessionScope.memberid == null}">
           <c:choose>
              		<c:when test="${sessionScope.adminid == null}">
                              
               		</c:when>
                	<c:otherwise>
                				<div>
       											<label>?????? ??????</label>
       											<textarea name='replycontent' required="required" class="form-control" rows="1" style='width: 100%;'></textarea>
   													</div>                					
               							<div class="content_body_bottom">
      											<button type="submit" class="btn btn-primary">?????? ??????</button>
    										</div>  
               		</c:otherwise>
						</c:choose>     
    </c:when>
    <c:otherwise>
            <div>
       			<label>?????? ??????</label>
       			<textarea name='replycontent' required="required" class="form-control" rows="1" style='width: 100%;'></textarea>
    				</div>
            <div class="content_body_bottom">
      			<button type="submit" class="btn btn-primary">?????? ??????</button>
    				</div>  
    </c:otherwise>
</c:choose>
   
  
		</FORM>
 

  	
 
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

