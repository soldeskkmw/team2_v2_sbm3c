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
            $('#img1').on('click', function() { $('#step1').prop('checked', true) });  // 첫번째 이미지를 클릭하면 첫번째 라디오 체크됨.
            $('#img2').on('click', function() { $('#step2').prop('checked', true) });
            $('#img3').on('click', function() { $('#step3').prop('checked', true) });
            $('#img4').on('click', function() { $('#step4').prop('checked', true) });
            $('#img5').on('click', function() { $('#step5').prop('checked', true) });
            $('#img6').on('click', function() { $('#step6').prop('checked', true) });
            
            $('#btn_previous').on('click', function() { history.back(); });   // 이전
            $('#btn_next').on('click', function() {   // 다음
                // alert($('#step1').prop('checked'));
                if ($('#step1').prop('checked') == true | $('#step2').prop('checked') == true | $('#step3').prop('checked') == true | $('#step4').prop('checked') == true | $('#step5').prop('checked') == true | $('#step6').prop('checked') == true) {
                    // alert('submit 진행');
                    frm.submit();
                } else {
                    alert('관심 분야의 이미지를 선택해주세요.');
                }
            });       
            $('#btn_close').on('click', function() { window.close(); });      // 윈도우 닫기
        });
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
        
        .td_radio{
            vertical-align: middle;
            padding: 5px;
        }
        .td_radio_select {
            cursor: pointer;
        }
    </style>
<<<<<<< HEAD
<link rel="icon" href="/images/travel.png">     
=======
    
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
</head>
<body>
<DIV class="container">
    <H3 style=' margin-top: 10px;'>1/6 단계입니다.</H3>
    <DIV id='panel' style='display: none; margin: 10px auto; width: 90%;'></DIV>
    <form id='frm' name='frm' action='/type2_recommend_post/form2.do' method='GET'>
        <br>
        <TABLE style='margin: 0px auto;'>
            <TR>
                <TD class='td_image'>
                    <img id='img1' src="/type2_recommend_post/images/v11.jpg" style='float:left; height: 200px'>
                </TD>
                <TD class='td_image'>
                    <img id='img2' src="/type2_recommend_post/images/v12.jpg" style='float:left; height: 200px'>
                </TD>
                <TD class='td_image'>
                    <img id='img3' src="/type2_recommend_post/images/v13.jpg" style='float:left; height: 200px'>
                </TD>
            </TR>
            <TR>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step1' value='1' class='td_radio_select'>
                </TD>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step2' value='2' class='td_radio_select'>
                </TD>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step3' value='3' class='td_radio_select'>
                </TD>
            </TR>
            <TR>
                <TD class='td_image'>
                    <img id='img4' src="/type2_recommend_post/images/v14.jpeg" style='float:left; height: 200px'>
                </TD>
                <TD class='td_image'>
                    <img id='img5' src="/type2_recommend_post/images/v15.jpg" style='float:left; height: 200px'>
                </TD>
                <TD class='td_image'>
                    <img id='img6' src="/type2_recommend_post/images/v16.jpg" style='float:left; height: 200px'>
                </TD>
            </TR>
            <TR>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step4' value='4' class='td_radio_select'>
                </TD>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step5' value='5' class='td_radio_select'>
                </TD>
                <TD class='td_radio'>
                    <input type='radio' name='step1' id='step6' value='6' class='td_radio_select'>
                </TD>
            </TR>            
        </TABLE>
        <br>
        <DIV style="text-align:center;">
            <button type='button' id='btn_previous' class="btn btn-info">이전</button>
            <button type='button' id='btn_next' class="btn btn-info">다음</button>
            <button type='button' id='btn_close' class="btn btn-info">취소</button>
        </DIV>
    </form>
</DIV>
</body>
</html>

