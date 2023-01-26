<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>GoingShare</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
  function send(f) {
	  if ($("input[type=radio][name=surveyitemno]:checked").is(':checked')) {
		  alert('참여해주셔서 감사합니다.');
		  f.submit();
    } else {
    	 alert('선택이 되지 않았습니다.');
    }	  
	}
</script>
<link rel="icon" href="/images/travel.png">     
</head> 
 
<body>
<c:import url="/menu/top.do" />
 <form name='surveytopic' action='./cnt_update.do' method='POST'>
   <input type='hidden' name='surveyno' value='${param.surveyno }' >
 
<DIV class='title_line'>
  <A href="./list_by_surveyno.do?surveyno=${surveyVO.surveyno }" class='title_link'>${surveyVO.surveytopic }</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?surveyno=${surveyVO.surveyno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_surveyno_grid1.do?surveyno=${surveyVO.surveyno }">갤러리형</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 50%;"></col>
      <col style="width: 30%;"></col>
      <c:if test="${sessionScope.adminpasswd != null }">
      <col style="width: 10%;"></col>
      </c:if>
    </colgroup>
    <tbody>
      <c:forEach var="surveyitemVO" items="${list }" varStatus="prop" >
        <c:set var="surveyno" value="${surveyitemVO.surveyno }" />
        <c:set var="cnt" value="${surveyitemVO.cnt }" />
        <c:set var="width" value="${(surveyitemVO.cnt / sum) * 300 }" />
        
        <tr style="height: 30px;"> 
          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- /static/surveyitem/storage/ --%>
                <a href="./read.do?surveyitemno=${surveyitemno}&now_page=${param.now_page }"><IMG src="/surveyitem/storage/${thumb1 }" style="width: 120px; height: 80px;"></a> 
              </c:when>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'> 
            <label style="cursor: pointer;">
            <input type="radio" name="surveyitemno" value="${surveyitemVO.surveyitemno}" > ${surveyitemVO.surveyitem}
            </label>
          </td> 
          <td style='vertical-align: middle;'>
            <IMG src="/surveyitem/images/chart01.png" width="${width}" height="20px"> ${surveyitemVO.cnt} 명
          </td>          
          <c:if test="${sessionScope.adminpasswd != null }">
          <td style='vertical-align: middle; text-align: center;'>          
            <A href="./read_update.do?surveyitemno=${surveyno }&surveyitemno=${surveyitemno}" title="수정"><IMG src="/survey/images/file_rename.png" class="icon"></A>
            <A href="./read_delete.do?surveyitemno=${surveyno }&surveyitemno=${surveyitemno}" title="삭제"><IMG src="/survey/images/trash.png" class="icon"></A>
          </c:if>
          </td> 
       </tr>
      </c:forEach>
     
            
    </tbody>
  </table>
</DIV>
      <c:if test="${sessionScope.memberpasswd != null }">
       <div class="content_body_bottom">
       <button type="button" class="btn btn-secondary" onclick="send(this.form)">참여</button>
       </div>
       </c:if>
 </form>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

