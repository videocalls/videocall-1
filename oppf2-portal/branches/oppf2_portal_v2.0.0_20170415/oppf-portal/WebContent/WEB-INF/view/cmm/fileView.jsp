<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : fileView.jsp
 * @Description : 첨부파일 결과처리용
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.30  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.30
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script language="javascript" type="text/javascript">
//화면 로드 처리
$(document).ready(function(){
	//무조건 result값만 셋팅한다.
    eval('parent.${callBackFunc}(${result})');
});
</script>
</head>
<body></body>
</html>
