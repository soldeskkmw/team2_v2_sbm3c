<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="postno" value="${postVO.postno }" />
<c:set var="adminno" value="${postVO.adminno }" />
<c:set var="adminid" value="${postVO.adminid }" />
<c:set var="adminname" value="${postVO.adminname }" />
<c:set var="ratings" value="${postVO.ratings }" />
<c:set var="cateno" value="${postVO.cateno }" />
<c:set var="posttitle" value="${postVO.posttitle }" />
<c:set var="postcontent" value="${postVO.postcontent }" />
<c:set var="postword" value="${postVO.postword }" />
<c:set var="rdate" value="${postVO.rdate.substring(0, 10) }" />
<c:set var="udate" value="${postVO.udate.substring(0, 10) }" />
<c:set var="poststar" value="${postVO.poststar }" />
<c:set var="postcnt" value="${postVO.postcnt }" />
<c:set var="postfile1" value="${postVO.postfile1 }" />
<c:set var="postfile1saved" value="${postVO.postfile1saved }" />
<c:set var="postthumb1" value="${postVO.postthumb1 }" />
<c:set var="postsize1" value="${postVO.postsize1 }" />
<c:set var="postsize1_label" value="${postVO.postsize1_label }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
/* 등록 버튼 */
$(".enroll_btn").on("click", function(e){
  const postno = '${postno}';
  const memberno = '${memberno}';
  const ratings = $("select").val();
  const data = {
      postno : postno,
      memberno : memberno,
      ratings : ratings,
  }
});
$(document).ready(function() {
  $('.div5_0').show();
  $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_0, .div3_5, .div4_0, .div4_5').hide();
  $('#ratings').change(function() {
    var result = $('#ratings option:selected').val();
    if (result == '1.0') {
      $('.div1_0').show();
      $('.div1_5, .div2_0, .div2_5, .div3_0, .div3_5, .div4_0, .div4_5, .div5_0').hide();
    } else if (result == '1.5') {
      $('.div1_5').show();
      $('.div1_0, .div2_0, .div2_5, .div3_0, .div3_5, .div4_0, .div4_5, .div5_0').hide();
    } else if (result == '2.0') {
      $('.div2_0').show();
      $('.div1_0, .div1_5, .div2_5, .div3_0, .div3_5, .div4_0, .div4_5, .div5_0').hide();
    } else if (result == '2.5') {
       $('.div2_5').show();
       $('.div1_0, .div1_5, .div2_0, .div3_0, .div3_5, .div4_0, .div4_5, .div5_0').hide();
     }else if (result == '3.0') {
        $('.div3_0').show();
        $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_5, .div4_0, .div4_5, .div5_0').hide();
      } else if (result == '3.5') {
        $('.div3_5').show();
        $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_0, .div4_0, .div4_5, .div5_0').hide();
      } else if (result == '4.0') {
        $('.div4_0').show();
        $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_0, .div3_5, .div4_5, .div5_0').hide();
      } else if (result == '4.5') {
        $('.div4_5').show();
        $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_0, .div3_5, .div4_0, .div5_0').hide();
      } else if (result == '5.0') {
        $('.div5_0').show();
        $('.div1_0, .div1_5, .div2_0, .div2_5, .div3_0, .div3_5, .div4_0, .div4_5').hide();
      }
  }); 
});
function type2_recommend_post() {
  var url = 'http://localhost:9093/type2_recommend_post/start.do';
  var win = window.open(url, 'AI 서비스', 'width=1000px, height=750px');

  var x = (screen.width - 1000) / 2;
  var y = (screen.height - 750) / 2;

  win.moveTo(x, y); // 화면 중앙으로 이동
}
</script>
</head>
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'  style="width:80%; margin-top: 60px;">
  <A href="./list_by_cateno_search_paging.do?cateno=${cateno }" class='title_link' style="font-size:1.7rem; font-weight:600;">${cateVO.catename }</A>
  <ASIDE class="aside_right" style="margin-top: 10px;">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./update_text.do?postno=${postno}&now_page=${param.now_page}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?postno=${postno}&now_page=${param.now_page}">대표 이미지 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?postno=${postno}&now_page=${param.now_page}&cateno=${cateno}">삭제</A>  
      <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_search_paging.do?cateno=${cateno }&now_page=${param.now_page}&postword=${param.postword }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateno }">갤러리형</A>
    <span class='menu_divide' >│</span>
    <A href="../post_ratings/list_by_postno.do?postno=${postno }">평점 보러가기</A>
  </ASIDE> 
</DIV>
  
  <%-- 검색 --%>
  <DIV style="text-align: right; clear: both;">
  <nav class="navbar navbar-expand-sm" style='padding:0px;'>
    <form  class="form-inline justify-content-end" name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do' style='width: 100%;'>
      <input class="form-control mr-sm-2" placeholder="Search" type='hidden' name='cateno' value='${param.cateno }'>
      <c:choose>
        <c:when test="${param.postword != '' }"> <%-- 검색하는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='${param.postword }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='postword' id='postword' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button class='btn btn-secondary' type='submit'>검색</button>
      <c:if test="${param.postword.length() > 0 }">
        <button class='btn btn-danger ml-sm-2' type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&postword='">취소</button>  
      </c:if>    
    </form>
   </nav>
  </DIV>
  
<DIV class='content_body'>
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; margin-bottom: 30px;">
          <span style="font-size: 2em; font-weight: bold;">${posttitle }</span><br>
        </DIV>
        <DIV style="width: 100%; margin-bottom: 20px;">
          <span>🙇🏻 BY ${adminname } </span>
          <c:choose>
            <c:when test="${udate != null}"><span>${udate }</span></c:when>
            <c:otherwise><span>${rdate }</span></c:otherwise>
          </c:choose>
        </DIV>
        <DIV style="width: 50%; float:left; margin-bottom:10px;">
            <c:choose>
              <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}">
                <A href="/post/storage/${postfile1saved }"><IMG src="/post/storage/${postfile1saved }" style="width: 100%;"></A> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/post/images/none1.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
        </DIV>
        <DIV style="width: 40%; float:left; margin-left:20px;">
          <c:if test="${sessionScope.memberid != null}">
            <%--평점 등록 폼 start --%>
            <FORM name='frm' method='POST' action='../post_ratings/create.do' enctype="multipart/form-data">
              <input type="hidden" name="postno" value="${param.postno }">
              <input type="hidden" name="cateno" value="${param.cateno }">
              <input type="hidden" name="postword" value="${param.postword }">
              <input type="hidden" name="now_page" value="${param.now_page }">
              <input type="hidden" name="memberno" value="${sessionScope.memberno }">
              
              <div class="card">
                <h5 class="card-header">평점 등록하기</h5>
                <div class="card-body">
                  <select id="ratings" name="ratings">
                    <option value="" selected="selected">평점을 선택해주세요.</option>
                    <option value="1.0">1.0</option>
                    <option value="1.5">1.5</option>
                    <option value="2.0">2.0</option>
                    <option value="2.5">2.5</option>
                    <option value="3.0">3.0</option>
                    <option value="3.5">3.5</option>
                    <option value="4.0">4.0</option>
                    <option value="4.5">4.5</option>
                    <option value="5.0">5.0</option>
                  </select>
                  <div class="div1_0"><IMG src="/post_ratings/images/1.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div1_5"><IMG src="/post_ratings/images/1.5.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div2_0"><IMG src="/post_ratings/images/2.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div2_5"><IMG src="/post_ratings/images/2.5.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div3_0"><IMG src="/post_ratings/images/3.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div3_5"><IMG src="/post_ratings/images/3.5.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div4_0"><IMG src="/post_ratings/images/4.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div4_5"><IMG src="/post_ratings/images/4.5.png" style="height: 30px; margin:15px 0;"></div>
                  <div class="div5_0"><IMG src="/post_ratings/images/5.png" style="height: 30px; margin:15px 0;"></div>
                  <DIV style="text-align:right;"><button type="submit" class="enroll_btn btn btn-primary">등록</button></DIV>
                </div>
              </div>
            </FORM>
            <%--평점 등록 폼 end --%>
           </c:if>
        </DIV>
        <DIV style="width: 50%; margin-bottom:40px; font-size: 1.1em;">
          <c:if test="${postfile1.trim().length() > 0 }">
            📁 대표 이미지 다운받기: <A href='/download?dir=/post/storage&postfilename=${postfile1saved}&downname=${postfile1}'>${postfile1}</A>📥 (${postsize1_label}) 
            <A href='/download?dir=/post/storage&postfilename=${postfile1saved}&downname=${postfile1}'></A>
          </c:if>
        </DIV>
        <DIV class='menu_line'></DIV>
        <DIV style="width:80%; margin: 0 auto; margin-bottom:80px;">${postcontent }</DIV>
      </li>
      <li class="li_none">
         <DIV style="width:80%; height:180px; margin:0 auto; border: 5px solid black; text-align:center; padding:20px; margin-bottom:50px;">
            <DIV style="font-size:1.7em; font-weight:600;">오로지 나만을 위한 추천 포스트가 궁금하다면?</DIV>
            <DIV style="font-size:4em;">
               👉🏻 <a class="btn btn-warning btn-lg" href="javascript:type2_recommend_post();" style="text-decoration:none; font-weight:600;">관심분야 추천 받기</a> 👈🏻
            </DIV>
         </DIV>
       </li>
      <li class="li_none">
        <DIV class='menu_line'></DIV>
        <DIV style='margin:30px 0;'>
          <h4><span class="badge badge-pill badge-dark">Keyword</span></h4>
          <DIV style='margin:10px;'>${postword }</DIV>
        </DIV>
      </li>
      
    </ul>
  </fieldset>

</DIV>

<DIV class='title_line'><A  class='title_link' style="font-size:30px">리뷰</A><A href="../review/create.do?postno=${postno}&cateno=${cateno}">등록</A></DIV>

 
<%-- 리뷰 목록  --%>
<c:forEach var="ReviewVO" items="${reviewlist}">
        <c:set var="postno1" value="${ReviewVO.postno }" />      
  			<c:set var="reviewcontent1" value="${ReviewVO.reviewcontent }" />
        <c:set var="reviewno1" value="${ReviewVO.reviewno }" />
        <c:set var="memberno1" value="${ReviewVO.memberno }" />     
       <c:set var="reviewtitle1" value="${ReviewVO.reviewtitle }" /> 
          
    <A  class='title_link' style="font-size:20px"> 제목 : [${reviewtitle1 } ]</A>
    
    
    <c:choose>
					<c:when test="${sessionScope.memberno ==memberno1 }">
                    <A href="../review/delete.do?now_page=${param.now_page}&reviewno=${reviewno1}&postno=${postno}&cateno=${cateno}"><strong class="aside_right">리뷰삭제</strong></A>     
           </c:when>
           <c:otherwise>
               	  <c:choose>
              						<c:when test="${sessionScope.adminid == null}">
                              
               						</c:when>
                					<c:otherwise>
               									<A href="../review/delete.do?now_page=${param.now_page}&reviewno=${reviewno1}&postno=${postno}&cateno=${cateno}"><strong class="aside_right">리뷰삭제</strong></A>   	  
               						</c:otherwise>
									</c:choose> 
           </c:otherwise>
</c:choose>
    
    
     <br>
     <a href="../review/read.do?reviewno=${reviewno1}&now_page=${param.now_page }&postno=${postno}&cateno=${cateno}"><input type='text' name='replycontent' readonly value='${reviewcontent1}' required="required" 
                  class="form-control" style='width: 100%;'></a>                  	
		<br>
 </c:forEach>   
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

