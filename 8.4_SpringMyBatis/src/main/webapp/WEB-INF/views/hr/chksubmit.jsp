<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
$(function () {

});

var uf_init = function (data) {

};

function uf_checkForm(){
    var questCnt    = 7;
    var answerCnt   = 0;
    for( var i = 1; i < questCnt+1; i++ ){
        var answerObj   = $('input[name="ANSWER'+i+'"]');
        for( var j = 0; j < answerObj.length; j++ ){
            if( answerObj.eq(j).is(":checked") ){
                answerCnt++;
            }
        }

        if( answerCnt == 0 ){
            cf_alert(i+"번째 질문에 답해주세요.");
            return false;
        } else {
            answerCnt = 0;
        }
    }

    return true;
}


function uf_submit(){
    if( !uf_checkForm() ){
        return;
    }

    $("#form1").attr("target","_self");
	$("#form1").attr("action","FndAsvcAnly0200.act");
	cf_submit($("#form1"));
}
</script>
</body>
</html>