<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptModifyPw.jsp
 * @Description : 비밀번호 변경 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.23  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.23
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">
<!--
//-->
</script>
<script language="javascript" type="text/javascript">

/*******************************************
* 전역 변수
*******************************************/

/*******************************************
* 이벤트 함수
*******************************************/

/* 패스워드 변경 함수 */
function fn_openPopupModifyPw(){
	var popupWidth  = '${popupWidth}';
	var popupHeight = '${popupHeight}';
    //비밀번호변경 팝업창 오픈
    var url = "<c:url value='/spt/myp/sptMyInfo/sptMyPwMod.do'/>";
    var objOption = new Object();
    objOption.type   = 'modal';
    objOption.width  = popupWidth;
    objOption.height = popupHeight;
    
    var objParam = new Object();
    objParam.callBakFunc = "fn_pwChgCallBack";
    util_modalPage(url, objOption, objParam);
}

/* 콜백함수 */
function fn_pwChgCallBack(data){
    //alert(1);
}

//화면 로드 처리
$(document).ready(function(){
	
    $(".wrap >").remove();
    
	fn_openPopupModifyPw();
	

});
</script>
</head>
<body>
</body>
</html>