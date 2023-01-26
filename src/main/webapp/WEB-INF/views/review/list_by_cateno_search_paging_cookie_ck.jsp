<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Going Share</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">

<%-- 로그인 기본값 --%>
  function loadDefault() {
	    $('#id').val('user1');
	    $('#passwd').val('1234');
  } 
  
  <%-- 로그인 --%>
  function login_ajax() {
    var params = "";
    params = $('#frm_login').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    
    $.ajax(
      {
        url: '/member/login_ajax.do',
        type: 'review',  // get, review
        cache: false, // 응답 결과 임시 저장 취소
        async: true,  // true: 비동기 통신
        dataType: 'json', // 응답 형식: json, html, xml...
        data: params,      // 데이터
        success: function(rdata) { // 응답이 온경우
          var str = '';
          alert('-> login cnt: ' + rdata.cnt);  // 1: 로그인 성공
          
          if (rdata.cnt == 1) {
            // 쇼핑카트에 insert 처리 Ajax 호출
            $('#div_login').hide(); // 로그인폼 감추기
            alert('로그인 성공');
            $('#login_yn').val('Y');
            
          } else {
            alert('로그인에 실패했습니다.\n잠시후 다시 시도해주세요.');
            
          }
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END

  }
</script>
<link rel="icon" href="/images/travel.png"> 
</head> 
 
<body>
<c:import url="/menu/top.do" />
<DIV style='height:60px;'></DIV>
<DIV style="width:100%; height:250px; position: relative;">
  <c:choose>
    <c:when test="${param.cateno == 1 }">
        <IMG src="/post/images/main_festival.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 2 }">
        <IMG src="/post/images/main_hotels.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 3 }">
        <IMG src="/post/images/main_trip.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 4 }">
        <IMG src="/post/images/main_life.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 5 }">
        <IMG src="/post/images/main_food.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 6 }">
        <IMG src="/post/images/main_cafe.jpg" style="width: 100%; height: 100%; opacity:0.6; object-fit: cover;">
    </c:when>
  </c:choose>
  <DIV style="position: absolute; float:left; transform: translate( 10%, -250% ); padding:0 25px; ">
    <A style="font-size:4em; font-weight:600; color:white;">${cateVO.catename }</A>
  </DIV>
</DIV>

<DIV class='title_line'>
<ul class="nav nav-tabs" >
  <li class="nav-item">
    <a class="nav-link" href="../post/list_by_cateno_search_paging.do?cateno=${cateVO.cateno }" >NEWS</a>
  </li>
  <li class="nav-item dropdown" style="font-weight:600;" >
    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Review</a>
    <div class="dropdown-menu">
      <c:if test="${sessionScope.memberpasswd != null }">
        <A class="dropdown-item" href="./create.do?cateno=${cateVO.cateno }">리뷰 작성</A>    
      </c:if>
      <a class="dropdown-item" href="javascript:location.reload();">새로고침</A>
    </div>
  </li>
</ul>
</DIV>

<DIV class='content_body'>

  <%-- ******************** Ajax 기반 로그인 폼 시작 ******************** --%>
  <DIV id='div_login' style='display: none;'>
    <div style='width: 30%; margin: 0px auto;'>
      <FORM name='frm_login' id='frm_login' method='POST'>
        <input type='hidden' name='contentsno' id='contentsno' value=''>
        <input type='hidden' name='login_yn' id='login_yn' value=''>
        
        <div class="form_input">
          <input type='text' class="form-control" name='id' id='id' 
                    value="${id }" required="required" 
                    style='width: 100%;' placeholder="아이디" autofocus="autofocus">
          <Label>   
            <input type='checkbox' name='id_save' value='Y' ${id_save == 'Y' ? "checked='checked'" : "" }> 저장
          </Label>    
        </div>   
     
        <div class="form_input">
          <input type='password' class="form-control" name='passwd' id='passwd' 
                    value='${passwd }' required="required" style='width: 100%;' placeholder="패스워드">
          <Label>
            <input type='checkbox' name='passwd_save' value='Y' ${passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
          </Label>                    
        </div>   
      </FORM>
    </div>
   
    <div style='text-align: center; margin: 10px auto;'>
      <button type="button" id='btn_login' class="btn btn-info" onclick="login_ajax()">로그인</button>
      <button type='button' onclick="location.href='./create.do'" class="btn btn-info">회원가입</button>
      <button type='button' id='btn_loadDefault' class="btn btn-info" onclick="loadDefault()">테스트 계정</button>
      <button type='button' id='btn_cancel' class="btn btn-info" onclick="$('#div_login').hide();">취소</button>
    </div>
  </DIV>
  <%-- ******************** Ajax 기반 로그인 폼 종료 ******************** --%>
  
  <table class="table" style='width: 100%;'>
    <colgroup>
      <col style="width: 40%;"></col>
      <col style="width: 60%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="reviewVO" items="${list }">
        <c:set var="cateno" value="${reviewVO.cateno }" />
        <c:set var="membername" value="${reviewVO.membername }" />
        <c:set var="reviewno" value="${reviewVO.reviewno }" />
        <c:set var="reviewfile1" value="${reviewVO.reviewfile1 }" />
        <c:set var="reviewsize1" value="${reviewVO.reviewsize1 }" />
        <c:set var="reviewthumb1" value="${reviewVO.reviewthumb1 }" />
        <c:set var="reviewrdate" value="${reviewVO.reviewrdate.substring(0, 10) }" />
        <c:set var="reviewudate" value="${reviewVO.reviewudate.substring(0, 10) }" />
        <c:set var="reviewcnt" value="${reviewVO.reviewcnt }" />
        <c:set var="replycnt" value="${reviewVO.replycnt }" />

        <tr style="height: 200px;"> 
          <td style='vertical-align: middle; text-align: right;'>
            <div>
	            <c:choose>
	              <c:when test="${reviewthumb1.endsWith('jpg') || reviewthumb1.endsWith('png') || reviewthumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
	                <%-- /static/review/storage/ --%>
	                <a href="../reply/read_all_list.do?reviewno=${reviewno}&cateno=${cateno}&reviewcnt=${reviewcnt}&reviewword=${param.reviewword}&now_page=${param.now_page}"><IMG src="/review/storage/${reviewthumb1 }" style="width:70%;"></a> 
	              </c:when>
	              <c:otherwise> <!-- 파일이 없거나 이미지가 아닌 경우 출력 -->
	                <c:choose>
	                  <c:when test="${reviewsize1 > 0 }"> <!-- 파일명 출력 -->
	                    <a href=../reply/read_all_list.do?reviewno=${reviewno}&cateno=${cateno}&reviewcnt=${reviewcnt}&reviewword=${param.reviewword}&now_page=${param.now_page}>${reviewfile1 }</a>
	                  </c:when>
	                  <c:when test="${reviewsize1 == 0 }"> <!-- 기본 이미지 출력 -->
	                    <a href="../reply/read_all_list.do?reviewno=${reviewno}&cateno=${cateno}&reviewcnt=${reviewcnt}&reviewword=${param.reviewword}&now_page=${param.now_page}"><IMG src="/review/images/none1.png" style="width:70%;"></a>
	                  </c:when>
	                </c:choose>
	              </c:otherwise>
	            </c:choose>
            </div>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="../reply/read_all_list.do?reviewno=${reviewno}&cateno=${cateno}&reviewcnt=${reviewcnt}&reviewword=${param.reviewword}&now_page=${param.now_page}" style="text-decoration:none;">
              <div style="font-size: 25px; font-weight: bold; ">${reviewVO.reviewtitle}</div>
            </a>
	          <div>
              <c:choose>
                <c:when test="${reviewVO.reviewcontent.length() > 120 }">
                    ${reviewVO.reviewcontent.substring(0, 120)}.....
                </c:when>
                <c:when test="${reviewVO.reviewcontent.length() <= 120 }">
                    ${reviewVO.reviewcontent}
                </c:when>
              </c:choose>
            </div>
            <DIV  style="margin-top: 10%; ">
	            <div style="font-size: 0.9em;"><a>✍🏻 BY ${membername } </a>작성일 : ${reviewrdate }
		            <c:if test="${reviewudate != null }">
		             <a style="font-size: 0.9em;">수정일 : ${reviewudate }</a>
		            </c:if>
	            </div>
	            <a>👀 ${reviewcnt }</a>
              <a>💭 ${replycnt }</a>
            </DIV>
          </td> 
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
  
  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
  
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

