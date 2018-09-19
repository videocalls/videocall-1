<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : intAcntCallBack.jsp
 * @Description : 일반사용자 통합계좌조회 로그인 CallBack 확인
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

	jQuery(document).ready(function(){

		<%-- 로그인 처리 --%>
		<c:choose>
			<c:when test="${empty LoginVO}" >
				opener.parent.fn_login();
			</c:when>
			<c:otherwise>
				var result = '${result}';
				if(result != '00'){
					var msg = "인증 오류가 발생하였습니다.";
					alert(msg);
				}
				opener.parent.fn_oAuthTokenResult();
			</c:otherwise>
		</c:choose>

		window.open('','_self').close();
		//크롬
		var closeWindow= window.open("about:blank", "_self");
		closeWindow.close();
		return false;
	});

</script>
</head>
<body>
</body>
</html>