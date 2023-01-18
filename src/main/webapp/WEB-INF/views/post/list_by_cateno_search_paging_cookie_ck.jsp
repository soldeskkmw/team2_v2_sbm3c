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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
  $(function() {
    // var contentsno = 0;
    // $('#btn_cart').on('click', function() { cart_ajax(contentsno)});
    // $('#btn_login').on('click', login_ajax);
    // $('#btn_loadDefault').on('click', loadDefault);
  });

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
        type: 'post',  // get, post
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

  <%-- 쇼핑 카트에 상품 추가 --%>
  function cart_ajax(contentsno) {
    var f = $('#frm_login');
    $('#contentsno', f).val(contentsno);  // 쇼핑카트 등록시 사용할 상품 번호를 저장.
    // console.log('-> contentsno: ' + $('#contentsno', f).val()); 
    if ($('#login_yn') != 'Y') {  // 로그인이 안되어 있다면
      $('#div_login').show();   // 로그인 폼 
    } else {  // 로그인 한 경우
      alert('쇼핑카트에 insert 처리 Ajax 호출');
    }

  }
  
</script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
<DIV style='height:60px;'></DIV>
<DIV style="width:100%; height:350px;">
  <c:choose>
    <c:when test="${param.cateno == 1 }">
        <IMG src="/post/images/main_festival.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 2 }">
        <IMG src="/post/images/main_hotels.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 3 }">
        <IMG src="/post/images/main_trip.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 4 }">
        <IMG src="/post/images/main_life.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 5 }">
        <IMG src="/post/images/main_food.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
    <c:when test="${param.cateno == 6 }">
        <IMG src="/post/images/main_cafe.jpg" style="width: 100%; height: 100%; object-fit: cover;">
    </c:when>
  </c:choose>
</DIV>
<DIV class='title_line'>
  <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }" class='title_link' style="font-size:2.5rem;">NEWS</A>
  <ASIDE class="aside_right" style="margin-top: 25px;">
    <c:if test="${sessionScope.adminid != null }">
      <A href="./create.do?cateno=${cateVO.cateno }">등록</A>    
      <span class='menu_divide' >│</span>
    </c:if>
    <!-- <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> -->
    <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid.do?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 
</DIV>

<DIV class='content_body'>

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
  
  <DIV class='menu_line'></DIV>
  
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
      <c:choose>
        <c:when test="${sessionScope.adminid != null }">
          <col style="width: 35%;"></col>
          <col style="width: 55%;"></col>
          <col style="width: 10%;"></col>
        </c:when>
        <c:otherwise>
          <col style="width: 40%;"></col>
          <col style="width: 60%;"></col>
        </c:otherwise>
      </c:choose>
    </colgroup>
    <%-- table 컬럼 --%>
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="postVO" items="${list }">
        <c:set var="cateno" value="${postVO.cateno }" />
        <c:set var="postno" value="${postVO.postno }" />
        <c:set var="postfile1" value="${postVO.postfile1 }" />
        <c:set var="postsize1" value="${postVO.postsize1 }" />
        <c:set var="postthumb1" value="${postVO.postthumb1 }" />
        <c:set var="rdate" value="${postVO.rdate.substring(0, 16) }" />
				<c:set var="udate" value="${postVO.udate.substring(0, 16) }" />
				<c:set var="poststar" value="${postVO.poststar }" />
				<c:set var="postcnt" value="${postVO.postcnt }" />

        <tr style="height: 200px;"> 
          <td style='vertical-align: middle; text-align: center;'>
            <div>
              <c:choose>
                <c:when test="${postthumb1.endsWith('jpg') || postthumb1.endsWith('png') || postthumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <%-- /static/post/storage/ --%>
                  <a href="./read.do?postno=${postno}&cateno=${cateno}&postcnt=${postcnt}&postword=${param.postword}&now_page=${param.now_page}"><IMG src="/post/storage/${postthumb1 }" style="width: 90%;"></a> 
                </c:when>
                <c:otherwise> <!-- 파일이 없거나 이미지가 아닌 경우 출력 -->
                  <c:choose>
                    <c:when test="${postsize1 > 0 }"> <!-- 파일명 출력 -->
                      <a href="./read.do?postno=${postno}&cateno=${cateno}&postcnt=${postcnt}&postword=${param.postword}&now_page=${param.now_page}">${postfile1 }</a>
                    </c:when>
                    <c:when test="${postsize1 == 0 }"> <!-- 기본 이미지 출력 -->
                      <a href="./read.do?postno=${postno}&cateno=${cateno}&postcnt=${postcnt}&postword=${param.postword}&now_page=${param.now_page}"><IMG src="/post/images/none1.png" style="width: 90%;"></a>
                    </c:when>
                  </c:choose>
                </c:otherwise>
              </c:choose>
            </div>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?postno=${postno}&cateno=${cateno}&postcnt=${postcnt}&postword=${param.postword}&now_page=${param.now_page}" style="text-decoration:none;">
              <div style="font-size: 25px; font-weight: bold; ">${postVO.posttitle}</div>
            </a>
            <div>
              <c:choose>
                <c:when test="${postVO.postcontent.length() > 120 }">
                    ${postVO.postcontent.substring(0, 120)}.....
                </c:when>
                <c:when test="${postVO.postcontent.length() <= 120 }">
                    ${postVO.postcontent}
                </c:when>
              </c:choose>
            </div>
            <DIV  style="margin-top: 10%;">
              <div style="font-size: 0.9em;">등록일 : ${rdate }</div>
              <c:if test="${udate != null }">
               <div style="font-size: 0.9em;">수정일 : ${udate }</div>
              </c:if>
              <a>별점: <fmt:formatNumber value="${poststar}" pattern="#,###"/></a>
              <a>조회수: ${postcnt }</a>
            </DIV>
          </td> 
          <c:if test="${sessionScope.adminid != null }">
            <td style='vertical-align: middle; text-align: center;'>
              <A href="/post/update_text.do?cateno=${cateno }&postno=${postno}&postword=${param.postword }" title="글 수정"><IMG src="/post/images/update.png" class="icon"></A>
              <A href="/post/update_file.do?cateno=${cateno }&postno=${postno}&postword=${param.postword }" title="대표 이미지 수정"><IMG src="/post/images/update_file.png" class="icon"></A>
              <A href="/post/delete.do?cateno=${cateno }&postno=${postno}&postword=${param.postword }" title="삭제"><IMG src="/post/images/delete.png" class="icon"></A>
            </td>
            </c:if>
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

