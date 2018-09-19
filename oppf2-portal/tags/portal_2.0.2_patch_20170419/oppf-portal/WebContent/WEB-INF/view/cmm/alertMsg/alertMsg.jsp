<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptMain.jsp
 * @Description : 기업포털 main
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

//화면 로드 처리
$(document).ready(function(){
//     $(".wrap >").remove();
    var alertMsg = '${alertMsg}';
    var moveUrl  = '${moveUrl}';
    var popupYn  = '${popupYn}';
    alert(alertMsg);
    if(popupYn == 'Y'){
        if(opener){
            window.close();
        }else{
        	gfn_closeModal();
        }
    }else{
        util_movePage(moveUrl);
    }
});
</script>
</head>
<body>
</body>
</html>