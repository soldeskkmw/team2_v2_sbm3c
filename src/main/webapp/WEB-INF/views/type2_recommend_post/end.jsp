<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관심분야 등록</title>
    <link href="/css/style.css" rel="Stylesheet" type="text/css">
    <script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
        $(function() {
            send();  // Django ajax 호출
            $('#btn_previous').on('click', function() { history.back(); });   // 이전
            $('#btn_close').on('click', function() { window.close(); });      // 윈도우 닫기
        });

        function send() {
            var params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
            // alert('params: ' + params);  // 수신 데이터 확인
            $.ajax({
              url: 'http://localhost:8000/type2_recommend_post/end_ajax',  // Spring Boot -> Django 호출
              type: 'get',  // get or post
              cache: false, // 응답 결과 임시 저장 취소
              async: true,  // true: 비동기 통신
              dataType: 'json', // 응답 형식: json, html, xml...
              data: params,      // 데이터
              success: function(rdata) { // 응답이 온경우
                // alert(rdata.index);
                if (rdata.index == 0) {        // 음식 추천 필요
                    $('#food').css('display',''); // show
                } else if(rdata.index == 1) { // 뷰 추천 필요
                    $('#view').css('display','');
                } else if(rdata.index == 2) { // 지식 추천 필요
                    $('#know').css('display','');
                } else if(rdata.index == 3) { // 힐링 추천 필요
                    $('#heel').css('display','');
                } else if(rdata.index == 4) { // 축제 추천 필요
                    $('#festival').css('display','');
                } else {                            // 사진 추천 필요
                    $('#photo').css('display','');
                } 
                    
                $('#panel').html("");  // animation gif 삭제
                $('#panel').css('display', 'none'); // 숨겨진 태그의 출력

                // --------------------------------------------------
                // 분류 정보에 따른 상품 이미지 SELECT
                //  --------------------------------------------------
              },
              // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우
              error: function(request, status, error) { // callback 함수
                console.log(error);
              }
            });

            // $('#panel').html('처리중입니다....');  // 텍스트를 출력하는 경우
            $('#panel').html("<img src='/type2_recommend_post/images/ani04.gif' style='width: 10%;'>");
            $('#panel').show(); // 숨겨진 태그의 출력
          }
    </script>

    <style>
        *{
            text-align: center;
        }

        .td_image{
            vertical-align: middle;
            padding: 5px;
            cursor: pointer;
        }

    </style>
    
</head>
<body>

<DIV style='display: none;'>
    <form name='frm' id='frm'>
        <input type='hidden' name='step1' value='${param.step1 }'>
        <input type='hidden' name='step2' value='${param.step2 }''>
        <input type='hidden' name='step3' value='${param.step3 }''>
        <input type='hidden' name='step4' value='${param.step4 }'>
        <input type='hidden' name='step5' value='${param.step5 }''>
        <input type='hidden' name='step6' value='${param.step6 }''>
    </form>
</DIV>

<DIV class="container">
    <H3 style=' margin-top: 10px;'>참여해주셔서 감사합니다.</H3>
    <H3>추천 포스트</H3>

    <DIV id='panel' style='margin: 30px auto; width: 90%;'></DIV>
    
    <DIV id='panel_img' style='margin: 0px auto; width: 90%;'>
        <DIV id='food' style='display: none;'> <!-- 음식 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v11.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v21.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v31.jpg" style='float:left; height: 200px'>
                    </TD>
	                </TR>
	                <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v41.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v51.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v61.png" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
        <DIV id='view' style='display: none;'>  <!-- 뷰 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v12.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v22.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v32.jpg" style='float:left; height: 200px'>
                    </TD>
                  </TR>
                  <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v42.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v52.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v62.jpg" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
        <DIV id='know' style='display: none;'> <!-- 지식 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v13.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v23.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v33.jpg" style='float:left; height: 200px'>
                    </TD>
                  </TR>
                  <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v43.png" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v53.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v63.jpg" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
        <DIV id='heel' style='display: none;'> <!-- 힐링 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v14.jpeg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v24.png" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v34.jpeg" style='float:left; height: 200px'>
                    </TD>
                  </TR>
                  <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v44.png" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v54.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v64.jpg" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
        <DIV id='festival' style='display: none;'> <!-- 축제 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v15.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v25.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v35.jpg" style='float:left; height: 200px'>
                    </TD>
                  </TR>
                  <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v45.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v55.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v65.jpg" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
        <DIV id='photo' style='display: none;'> <!-- 사진 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <img id='img1' src="/type2_recommend_post/images/v16.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img2' src="/type2_recommend_post/images/v26.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img3' src="/type2_recommend_post/images/v36.jpg" style='float:left; height: 200px'>
                    </TD>
                  </TR>
                  <TR>
                    <TD class='td_image'>
                        <img id='img4' src="/type2_recommend_post/images/v46.png" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img5' src="/type2_recommend_post/images/v56.jpg" style='float:left; height: 200px'>
                    </TD>
                    <TD class='td_image'>
                        <img id='img6' src="/type2_recommend_post/images/v66.png" style='float:left; height: 200px'>
                    </TD>
                </TR>          
            </TABLE>
        </DIV>
    </DIV>
    
    <form id='frm' name='frm' action='' method='GET'>
        <br>
        <DIV style="text-align:center;">
            <button type='button' id='btn_previous' class="btn btn-info">이전</button>
            <button type='button' id='btn_close' class="btn btn-info">종료</button>
        </DIV>
    </form>
</DIV>
</body>
</html>


