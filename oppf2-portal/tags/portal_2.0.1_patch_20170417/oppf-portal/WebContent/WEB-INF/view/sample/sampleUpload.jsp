<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sampleWidget.jsp
 * @Description : [샘플:jqWidgets] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.28  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.04.28
 * @version 1.0
 * @see
 *
 */
--%>

<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<link rel="stylesheet" href="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<%-- <script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/scripts/demos.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxfileupload.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxdatetimeinput.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxtooltip.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/globalization/globalize.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/globalization/globalize.culture.ko.js'/>"></script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/


/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/


/*******************************************
 * 이벤트 함수
 *******************************************/
 
function fn_upload(){
    alert('a');
}
 
//화면 로드 처리
$(document).ready(function(){
	
});
</script>
</head>
<body>

<div id="wrapper">
	<div id="container">
		<%-- 탑과 메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
			<%-- 좌측메뉴 영역 --%>
			<%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
			<%-- 좌측메뉴 영역 --%>
			<div id="articleWrap">
				<div class="conHeader">
					<%-- 네비게이션 영역 --%>
					<%@ include file="/WEB-INF/view/cmm/common-include-history.jspf" %>
					<%-- 네비게이션 영역 --%>
				</div>
				<div id="content">
					<table>
						<tr>
							<td>
								↓↓↓↓↓ 샘플업로드 입니다. ↓↓↓↓↓
							</td>
						</tr>
						<tr>
							<td>
								jqxFileUpload
								<div id="divJqxFileUpload"></div>
								<a id="btnUpload">파일업로드</a>
							</td>
						</tr>
					</table>
				</div><%-- content --%>
			</div><%-- articleWrap --%>
		</div><%-- contents --%>
	</div><%-- container --%>
	
	<%-- Footer 영역 --%>
	<%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>	
	<%-- Footer 영역 --%>
</div>

</body>
</html>