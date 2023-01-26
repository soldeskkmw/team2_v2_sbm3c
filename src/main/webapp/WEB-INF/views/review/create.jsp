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
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script> <!-- /static 기준 -->

<script type="text/javascript">
  $(function() {
    CKEDITOR.replace('reviewcontent');  // <TEXTAREA>태그 id 값
  }); 
 
</script>

<link rel="icon" href="/images/travel.png">
</head> 
 
<body>
<<<<<<< HEAD
<c:import url="/menu/top.do" />

<DIV style='margin-top: 60px; '>
<A style="font-size: 1.5rem; font-weight:600;">${cateVO.catename } > 리뷰 등록</A>
<ASIDE class="aside_right" style="margin-top: 10px;">
  <A href="javascript:location.reload();">새로고침</A>
  <span class='menu_divide' >│</span>
  <A href="./list_by_cateno_search_paging.do?cateno=${cateVO.cateno }">리뷰 목록</A>    
</ASIDE>
</DIV>
<DIV class='title_line'></DIV>

<DIV class='content_body'>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="cateno" value="${param.cateno }">
    <input type="hidden" name="memberno" value="${sessionScope.memberno }">
    <div style="margin: 40px 0;">
       <input type='text' name='reviewtitle' required="required" placeholder="제목을 입력하세요." autofocus="autofocus" class="form-control" style='width: 100%;'>
=======

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

<c:import url="/menu/top.do" />
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0

>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
<c:import url="/menu/top.do" />
 
  
  <DIV class='menu_line'></DIV>
  <%--등록 폼  --%> <%--등록 폼 위쪽 코드들 제거 -> post no 및 vo 받아와서 설정 후 다시 코드 작성--%>
  <FORM name='frm' method='POST' action='../review/create.do' enctype="multipart/form-data">
    <input type="hidden" name="postno" value="${postno }">
    <input type="hidden" name="memberno" value="${sessionScope.memberno }"> <%-- 관리자 개발후 변경 필요 --%>
    
    <div>
       <label>리뷰 제목</label>
       <input type='text' name='reviewtitle' value='가을 영화' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    </div>
    <div>
       <label>Review 작성</label>
       <textarea class="form-control" name='reviewcontent' id='reviewcontent' rows='10' placeholder="내용을 입력하세요." style='width: 100%;'>${reviewcontent }</textarea>
    </div>
    <div style="margin: 40px 0;">
       <input type='text' name='reviewword' value=''  placeholder="검색어를 입력하세요." required="required" maxlength="400" class="form-control" style='width: 100%;'>
    </div>  
    <div>
<<<<<<< HEAD
       <label>이미지 선택</label>
       <input type='file' class="form-control" name='reviewfile1MF' id='reviewfile1MF'  value='' placeholder="파일 선택">
    </div>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn btn-primary">목록</button>
=======
       <label>검색어</label>
       <input type='text' name='reviewword' value='월터,벤 스틸러,크리스튼위그,휴먼,도전' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>  
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='reviewfile1MF' id='reviewfile1MF' 
                 value='' placeholder="파일 선택">
    </div> 
   <!-- 파일 부분 삭제함 추가요망  -->   
   <!-- 패스워드 안써서 삭제 -->
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
<<<<<<< HEAD
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-primary">목록</button>
<<<<<<< HEAD
      <button type="button" onclick="javascript:history.back();" class="btn btn-primary">목록</button>
=======
=======
      <button type="button" onclick="javascript:history.back();" class="btn btn-primary">목록</button>
>>>>>>> 8fc72d6731d86e0f8db2e008743e4e885d4270e0
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    </div>
  
  </FORM>
 </DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

