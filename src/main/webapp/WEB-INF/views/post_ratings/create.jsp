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
<title>Going Share</title>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->

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
 
</script>
<<<<<<< HEAD
<link rel="icon" href="/images/travel.png"> 
=======
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</head> 
 
<body>
<c:import url="/menu/top.do" />
<DIV style='width: 80%; margin-top: 60px;'>
	<DIV style="font-size: 1.5rem; font-weight:600;">⭐평점 등록하기⭐</DIV>
	<DIV style="font-size: 1.5rem; margin-top:10px;">${postVO.posttitle }</DIV>
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body' style="width: 80%;">
  <%--평점 등록 폼 start --%>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="postno" value="${param.postno }">
    <input type="hidden" name="cateno" value="${param.cateno }">
    <input type="hidden" name="postcnt" value="${param.postcnt }">
    <input type="hidden" name="postword" value="${param.postword }">
    <input type="hidden" name="now_page" value="${param.now_page }">
    <input type="hidden" name="memberno" value="1">
    <div>
      <a style="font-size: 1.3rem; ">평점 선택하기</a>
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
      <div class="div1_0"><IMG src="/post_ratings/images/1.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div1_5"><IMG src="/post_ratings/images/1.5.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div2_0"><IMG src="/post_ratings/images/2.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div2_5"><IMG src="/post_ratings/images/2.5.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div3_0"><IMG src="/post_ratings/images/3.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div3_5"><IMG src="/post_ratings/images/3.5.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div4_0"><IMG src="/post_ratings/images/4.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div4_5"><IMG src="/post_ratings/images/4.5.png" style="height: 30px; margin-top:10px;"></div>
      <div class="div5_0"><IMG src="/post_ratings/images/5.png" style="height: 30px; margin-top:10px;"></div>
    </div>
    
    <div class="content_body_bottom">
      <button type="submit" class="enroll_btn btn btn-primary">등록</button>
      <button type="button" onclick="history.back()" class="btn btn-primary">취소</button>
    </div>
  
  </FORM>
  <%--평점 등록 폼 end --%>
</DIV>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

