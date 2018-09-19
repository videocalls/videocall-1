<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMyPwMod.jsp
 * @Description : 회원정보 확인 전 비밀번호 확인
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.19  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.05.19
 * @version 1.0
 * @spt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

/*******************************************
 * 기능 함수
 *******************************************/

/* function times(){
	var today=new Date();
	today.setDate(today.getDate()+1);   
	var ccc=today.toGMTString();
	//document.write(ccc);
} */	  
 
function setCookie( name, value, expiredays ){ 
    var todayDate = new Date(); 
    todayDate.setDate( todayDate.getDate() + expiredays );
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" 
} 
 
function closeWin(){ 
	var popTilte = $("#popTilte").val();
	//alert(popTilte);
    //if ( document.cnjform.notice.checked )  // 폼네임 cnjform 은 동일해야 합니다.
//    setCookie(popTilte, "" , 7);   // 부모창에서 지정한 쿠키네임과 일치 해야 합니다.(일주일간 보지 않기)
    setCookie(popTilte, "" , 1);   // 부모창에서 지정한 쿠키네임과 일치 해야 합니다.(하루동안 보지 않기)
    top.close();
}

function fn_popupClose(){
	closeWin();    
}

//화면 로드 처리
$(document).ready(function(){
	$("#popClose").click(function(){
		var oneWeekHidden = document.popForm.notice.checked;
        if(oneWeekHidden){
        	closeWin();
        	//alert("체크 되어있음");
        }else{
        	top.close();
        	//alert("체크 안되어있음");
        }
    });
	
});
</script>
</head>
<!-- <body onload=times()> -->
<body>
<form:form commandName="popForm" name="popForm" method="post">
<input type="hidden" name="noticeSeq" id="noticeSeq" value="<c:out value='${ paramVO.noticeSeq }'/>"/>
<input type="hidden" name="popTilte" id="popTilte" value="<c:out value='${ paramVO.popTilte }'/>"/>
<div class="wrap window_popup">
    <!-- <h1>공지사항(타이틀 필요시 사용)</h1> -->
    <dl class="notice_view">
        <dt>
            <div class="tit"><p><c:out value='${ resultDetail.noticeTitle }'/></p><span><c:out value='${resultDetail.createDate}'/></span></div>
            <c:if test="${not empty resultDetail.fileId}">
                <c:forEach var="fileList" items="${fileList}" varStatus="status">
                    <div class="file">
                        <img src="<c:url value="/images/spt/customer/icon_file.png"/>" alt="파일첨부 아이콘">
                        <a href="javascript:gfn_noticeFileDown('${ fileList.fileId }', '${ fileList.fileSeq }');">
                           <c:out value='${ fileList.fileName }'/>
                        </a>
                    </div>
                </c:forEach>
            </c:if>
        </dt>
        <dd>
            <div class="view_con">${ resultDetail.noticeContent }</div>
        </dd>
    </dl>   

    <div class="bottom_close">
        <p><input type="checkbox" name="notice" id="notice" onclick="javascript:fn_popupClose();"><label for="notice" onclick="javascript:fn_popupClose();">오늘 하루동안 열지않음</label></p>
        <a href="javascript:void(0);" class="btn_close" name="popClose" id="popClose">[닫기]</a>
    </div>

</div>

</form:form>	
</body>
</html>