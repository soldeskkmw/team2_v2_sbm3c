<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.member.MemberVO" %>

<c:set var="reviewno" value="${reviewVO.reviewno }" />
<c:set var="postno" value="${reviewVO.postno }" />
<c:set var="reviewtitle" value="${reviewVO.reviewtitle }" />        
<c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
<c:set var="reviewfile1saved" value="${reviewVO.reviewfile1saved }" />
<c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
<c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
<c:set var="reviewcontent" value="${reviewVO.reviewcontent }" />
<c:set var="reviewword" value="${reviewVO.reviewword }" />
<c:set var="reviewsize1_label" value="${reviewVO.reviewsize1_label }" />
<c:set var="reviewstar" value="${reviewVO.reviewstar }" />
<c:set var="goodcnt" value="${reviewVO.goodcnt }" />
<c:set var="cnt" value="${reviewVO.cnt }" />
<c:set var="replycnt" value="${reviewVO.replycnt }" />
<c:set var="memberno" value="${reviewVO.memberno }" />

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

<script type="text/JavaScript">

</script>
    
<link rel="icon" href="/images/travel.png">   
</head> 
 
<body>
<c:import url="/menu/top.do" /> 

 
<DIV class='title_line'><span style="font-size: 1.5em; font-weight: bold;">리뷰</span></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
     

    
    <A href="javascript:location.reload();">새로고침</A>
  
 
  
    
    <%--<c:if test="${sessionScope.admin_id != null }"> --%>
      
    	<%--<A href="./create.do?postno=${postVO.postno  }">등록</A> --%>
    
		
		
		
		<c:choose>
              <c:when test="${sessionScope.adminid == null}">
                            
                            <c:choose>
              									<c:when test="${sessionScope.memberid == null}">
                              
               									</c:when>
                						<c:otherwise>
                									<span class='menu_divide' >│</span>
                    										<A href="./create.do?postno=1">등록</A>
     															<span class='menu_divide' >│</span>     
     																		<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">글 수정</A>  
     																			<span class='menu_divide' >│</span>
    																<A href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">파일 수정</A>
     																		
     												
                              <c:choose>
                              	<c:when test="${sessionScope.memberno == memberno}">
                              	<span class='menu_divide' >│</span>  
                              	<A href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}&postno=1<%--${postno } --%>">삭제</A>  
               									</c:when>
                              </c:choose>
               								
     																		
     																		
     																		 
               							</c:otherwise>
														</c:choose>                                                                                                             
               </c:when>
                <c:otherwise>
                <span class='menu_divide' >│</span>
                    										<A href="./create.do?postno=1">등록</A>
     															<span class='menu_divide' >│</span>     
     																		<A href="./update_text.do?reviewno=${reviewno}&now_page=${param.now_page}">글 수정</A>   
                
                    	<span class='menu_divide' >│</span>   
    	<%--<A href="./update_file.do?reviewno=${reviewno}&now_page=${param.now_page}">파일 수정</A>
    	<span class='menu_divide' >│</span>--%>
    	<A href="./delete.do?reviewno=${reviewno}&now_page=${param.now_page}&postno=1<%--${postno } --%>">삭제</A>  
  	<%--</c:if> --%>
    	  
               </c:otherwise>
		</c:choose>
  </ASIDE> 
  
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 50%; float: left; margin-right: 10px;">
            <c:choose>
              <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}">
                <%-- /static/review/storage/ --%>
                <A href="/review/storage/${reviewfile1saved }"><IMG src="/review/storage/${reviewfile1saved }" style="width: 100%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/review/images/none1.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 47.5%; height: 260px; float: left; margin-right: 10px; margin-bottom: 30px;">
          <span style="font-size: 1.5em; font-weight: bold;">${reviewtitle}</span><br>
          <span style="color: #FF0000; font-size: 2.0em;">별점 = ${reviewstar}</span>          
          <span style="font-size: 1.0em;">좋아요 수 = ${goodcnt}</span><br>
          <span style="font-size: 1.0em;">조회수 = ${cnt}</span><br>
          <span style="font-size: 1.0em;">댓글수 = ${replycnt}</span><br>          
        </DIV> 

        <DIV>${reviewcontent }</DIV>
      </li>

           
     <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${reviewfile1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/review/storage&filename=${reviewfile1saved}&downname=${reviewfile1}'>${reviewfile1}</A> (${reviewsize1_label}) 
            <A href='/download?dir=/review/storage&filename=${reviewfile1saved}&downname=${reviewfile1}'><IMG SRC="/review/images/download.png"></A>
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
 <DIV class='title_line'><A  class='title_link' style="font-size:30px">댓글</A></DIV>
 
 
 <c:forEach var="ReplyVO" items="${replylist }">
        <c:set var="reviewno1" value="${ReplyVO.reviewno }" />      
  			<c:set var="replycontent1" value="${ReplyVO.replycontent }" />
        <c:set var="replyno1" value="${ReplyVO.replyno }" />
        <c:set var="memberno1" value="${ReplyVO.memberno }" />     
        <c:set var="memberid1" value="${ReplyVO.memberid }" />
        [ ${memberid1} ]      
        
<c:choose>
					<c:when test="${sessionScope.memberid ==memberid1 }">
                    <A href="./replydelete.do?now_page=${param.now_page}&replyno=${replyno1}"><strong class="aside_right">댓글삭제</strong></A>     
           </c:when>
           <c:otherwise>
               	  <c:choose>
              						<c:when test="${sessionScope.adminid == null}">
                              
               						</c:when>
                					<c:otherwise>
               									<A href="./replydelete.do?now_page=${param.now_page}&replyno=${replyno1}"><strong class="aside_right">댓글삭제</strong></A>   	  
               						</c:otherwise>
									</c:choose> 
           </c:otherwise>
</c:choose>
        
           
           
        <input type='text' name='replycontent' readonly value='${replycontent1}' required="required" 
                  class="form-control" style='width: 100%;'>               
        <br>
</c:forEach>         
 
 
  <%--등록 폼  --%> <%--등록 폼 위쪽 코드들 제거 -> post no 및 vo 받아와서 설정 후 다시 코드 작성--%>
  <FORM name='frm' method='POST' action='./replycreate.do' enctype="multipart/form-data">
    <input type="hidden" name="postno" value=${postno }>
    <input type="hidden" name="cateno" value=${cateno }>
    <input type="hidden" name="memberno" value="${sessionScope.memberno }"> <%-- 관리자 개발후 변경 필요 --%>
  	<input type="hidden" name="reviewno" value="${reviewno }">
  	<input type="hidden" name="memberid" value="${sessionScope.memberid }">
    
      
<c:choose>
		<c:when test="${sessionScope.memberid == null}">
           <c:choose>
              		<c:when test="${sessionScope.adminid == null}">
                              
               		</c:when>
                	<c:otherwise>
                				<div>
       											<label>댓글 작성</label>
       											<textarea name='replycontent' required="required" class="form-control" rows="1" style='width: 100%;'></textarea>
   													</div>                					
               							<div class="content_body_bottom">
      											<button type="submit" class="btn btn-primary">댓글 등록</button>
    										</div>  
               		</c:otherwise>
						</c:choose>     
    </c:when>
    <c:otherwise>
            <div>
       			<label>댓글 작성</label>
       			<textarea name='replycontent' required="required" class="form-control" rows="1" style='width: 100%;'></textarea>
    				</div>
            <div class="content_body_bottom">
      			<button type="submit" class="btn btn-primary">댓글 등록</button>
    				</div>  
    </c:otherwise>
</c:choose>
   
  
		</FORM>
 

  	
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

