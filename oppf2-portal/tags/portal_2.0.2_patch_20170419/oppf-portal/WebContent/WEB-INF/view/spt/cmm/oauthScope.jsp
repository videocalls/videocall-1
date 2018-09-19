<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : oauthScope.jsp
 * @Description : 일반사용자 oauth Scope 확인
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.02.28  이희태        최초  생성
 * </pre>
 *
 * @author 이희태
 * @since 2017.02.28
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="expires" content="-1" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){

});

/*******************************************
 * 기능 함수
 *******************************************/
//허용
function fn_grant(){

	var url = "<c:url value='/spt/cmm/authorization.do'/>";
	var reqData = {
		"customer_id"       : '<c:out value='${ paramVO.customer_id }'/>'
	   ,"sessionID" : '<c:out value='${ paramVO.sessionID }'/>'
	   ,"scope" : '<c:out value='${ paramVO.scope }'/>'
	   ,"clientId" : '<c:out value='${ paramVO.clientId }'/>'
	};

	util_ajaxPage(url, reqData, "fn_close_callBack");
}

function fn_close_callBack(data){
	if(data.result == '00'){
		$(location).attr('href', data.location);
	}else{
		alert('Oauth 로그인 서비스 ERROR.');
	}
}

function fn_close(){
	window.open('','_self').close();
	//크롬
	var closeWindow= window.open("about:blank", "_self");
	closeWindow.close();
	return false;
}
</script>
</head>
<body>
<div class="wrap window_popup">
	<h2 class="logo"><a href="#none"><img src="/images/spt/common/logo.png" alt="Koscom OpenAPI Platform"></a></h2>
		<!-- 아이디 입력 -->
		<div class="account">
			<div class="tit_info font16">
				<c:set var="appImg" value="/cmm/appImg/${paramVO.appId}.do" />
				<img src="<c:url value=""/><c:out value='${ appImg }'/>" onerror="this.src='<c:url value="/images/cmm/icon/icon_app_none.png"/>'" alt="<c:out value='${ paramVO.app_name }'/>" style="width: 28px;height: 28px;">
				<span><c:out value='${ paramVO.app_name }'/></span>에 제공하게될 정보
			</div>
			<ul class="txt">
				<c:set var="scopeCheck" value="0" />
				<c:forEach items="${scopeList}" var="scopeList" varStatus="status">
					<c:if test="${ fn:indexOf( paramVO.scope, scopeList.code_name_kor) > -1 }">
						<c:set var="scopeCheck" value="1" />
						<li>- <c:out value='${ scopeList.codeExtend1 }'/></li>
					</c:if>
				</c:forEach>
				<c:if test="${ scopeCheck eq '0' }">
						<li>- 기본 scope 권한</li>
				</c:if>
			</ul>
			<p class="txt_b mt20">허용을 클릭하시면 <c:out value='${ paramVO.app_name }'/>에서 회원님의 정보를 사용하도록 허용하게 됩니다.</p>
			<div class="btn_area mt20">
				<a href="javascript:fn_close();" class="btn_type5 type2">거부</a>
				<a href="javascript:fn_grant();" class="btn_type5">허용</a>
			</div>
		</div>
</div>
</body>
</html>