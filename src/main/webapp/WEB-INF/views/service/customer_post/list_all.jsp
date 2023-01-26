<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  <A href="./list_all.do" class='title_link'>문의 글 목록</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.adminno != null }">
      <A href="/service/servicecate/list_all.do">카테고리 편집</A>
      <span class='menu_divide' >│</span>
    </c:if>  
  
    <A href="javascript:location.reload();">새로고침</A>    
    <span class='menu_divide' >│</span> 
    
    <select onchange = "location.href='/service/customer_post/list_all.do?word=${param.word }&servicecateno=' + (this.value)">
      <option value="-1">전체</option>
      <c:forEach var="ServiceCateVO" items="${serviceCateList }">
        <c:set var="servicecateno" value="${ServiceCateVO.servicecateno }" />
        <c:set var="servicetype_content" value="${ServiceCateVO.servicetype_content }" />
        <option 
           value="${servicecateno}" 
           <c:if test="${servicecateno == param.servicecateno }">
              selected="selected"
           </c:if>
         >${servicetype_content }
         </option>
       </c:forEach>
    </select>
    </ASIDE> 
    
<<<<<<< HEAD
    <DIV style="text-align: right; clear: both; padding-top:10px;">  
=======
    <DIV style="text-align: right; clear: both;">  
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
    <nav class="navbar navbar-expand-sm" style='padding:0px;'>
	    <form class="form-inline justify-content-end" style='width: 100%;' name='frm' id='frm' method='get' action='/service/customer_post/list_all.do'>
	      <input class="form-control mr-sm-2" type='hidden' name='servicecateno' value='${param.servicecateno }'>
	      
	      <c:choose>
	        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
	          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
	        </c:when>
	        <c:otherwise> <%-- 검색하지 않는 경우 --%>
	          <input class="form-control mr-sm-2 justify-content-end" placeholder="Search" type='text' name='word' id='word' value='' style='width: 20%;'>
	        </c:otherwise>
	      </c:choose>
	      <button class='btn btn-secondary' type='submit'>검색</button>
	      <c:if test="${param.word.length() > 0 }">
	        <button class='btn btn-danger ml-sm-2' type='button' 
	                     onclick="location.href='/service/customer_post/list_all.do?servicecateno=${param.servicecateno}&word='">검색 취소</button>  
	      </c:if>    
	    </form>
    </nav>
  </DIV>
  

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 40%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 10%;"></col>
      
      <c:if test="${sessionScope.adminno != null }">      
      <col style="width: 10%;"></col>
       </c:if>
       
    </colgroup>
    <%-- table 컬럼 --%>
<!--     <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>상품명</th>
        <th style='text-align: center;'>정가, 할인률, 판매가, 포인트</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead> -->
    
        <thead>  
    <TR>
      <TH class="th_bs"></TH>
      <TH class="th_bs">제목</TH>
      <TH class="th_bs">내용</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">공개 여부</TH>
      <c:if test="${sessionScope.adminid != null }">
      <TH class="th_bs">편집</TH>
      </c:if>

    </TR>
    </thead>
    
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="customer_postVO" items="${list }">
        <c:set var="serviceno" value="${customer_postVO.serviceno }" />
        <c:set var="memberno" value="${customer_postVO.memberno }" />
        <c:set var="servicecateno" value="${customer_postVO.servicecateno }" />
        <c:set var="servicetitle" value="${customer_postVO.servicetitle }" />
        <c:set var="servicecontents" value="${customer_postVO.servicecontents }" />        
        <c:set var="servicevisible" value="${customer_postVO.servicevisible }" />
        <c:set var="thumb1" value="${customer_postVO.thumb1 }" />
        <c:set var="rdate" value="${customer_postVO.rdate }" />
        
        <tr style="height: 60px;">
          <td style='vertical-align: middle; text-align: center;'><div>${serviceno }</div></td>

          <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${fn:contains(servicevisible, 'T') || sessionScope.adminno != null || sessionScope.memberno == memberno}">
                <c:choose>
                    <c:when test="${servicetitle.length() > 20 }">
                        ${servicetitle.substring(0, 20)}.....
                    </c:when>
                    <c:when test="${servicetitle.length() <= 20 }">
                        ${servicetitle}
                    </c:when>
                  </c:choose>
                </td>
                
                <td style='vertical-align: middle;'>
                <a href="./read.do?serviceno=${serviceno}&servicecateno=${servicecateno}"><strong>${title}</strong> 
                  <c:choose>
                    <c:when test="${servicecontents.length() > 160 }">
                        ${servicecontents.substring(0, 160)}.....
                    </c:when>
                    <c:when test="${servicecontents.length() <= 160 }">
                        ${servicecontents}
                    </c:when>
                  </c:choose>
              </c:when>
              <c:otherwise>
                비공개글
                </td>
                <td style='vertical-align: middle;'>
                해당 글에 대한 권한이 없습니다.
              </c:otherwise>
            </c:choose>            
            
            </a> 
          </td> 
          
          <td style='vertical-align: middle;'><div>${rdate.substring(0, 10) }</div></td>
          
          <td style='vertical-align: middle; text-align: center;'>
<%--             <A href="/contents/map.do?servicecateno=${servicecateno }&serviceno=${serviceno}" title="지도"><IMG src="/contents/images/map.png" class="icon"></A> --%>
<%--             <A href="/contents/youtube.do?servicecateno=${servicecateno }&serviceno=${serviceno}" title="Youtube"><IMG src="/contents/images/youtube.png" class="icon"></A> --%>
            <!-- 공개상태, 답변 여부 -->
             <c:choose>
              <c:when test="${servicevisible eq 'T' }">
                  <a>공개</a>
              </c:when>
              <c:when test="${servicevisible eq 'F' }">
                  <a>비공개</a>
              </c:when>
        </c:choose>
            
          </td>
          
        <c:if test="${sessionScope.adminid != null }">
        <TD class="td_bs">
          <A href="./survey_read_update.do?surveyno=${surveyno}" title="수정"><IMG src="/survey/images/edit.png" class="icon"></A>
          <A href="./survey_read_delete.do?surveyno=${surveyno}" title="삭제"><IMG src="/survey/images/delete.png" class="icon"></A>
        </TD>   
        </c:if>
          
          
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
  
<%--   <c:if test="${sessionScope.memberno != null }"> --%>
<!--     <button onclick="location.href='/service/customer_post/create.do'">글쓰기</button> -->
<%--   </c:if> --%>
   <div class="content_body_bottom">

  <button onclick="location.href='/service/customer_post/create.do'"  class="btn btn-secondary">글쓰기</button>
  
  </div>

  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
</DIV>
 
<c:import url="/menu/bottom.do" />
</body>
 
</html>