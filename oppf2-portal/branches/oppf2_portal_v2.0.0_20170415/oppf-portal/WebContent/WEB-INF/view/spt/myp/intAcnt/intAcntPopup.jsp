<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : intAcntPopup.jsp
 * @Description : [핀테크서비스신청:로그인인증선택]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.16  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.16
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">
var g_verifyWin = null;     //인증 팝업
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/


/*******************************************
 * 이벤트 함수
 *******************************************/

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        window.parent.fn_linkMypage();
        gfn_closeModal(this.event);
    }
}

//로그인 팝업창
function fn_login(){
     fn_closeVerifyWin();
     var url = '${url}';
     var winName = "popupChk" + util_getDate() + util_getTime("", "s");
     g_verifyWin = window.open('', winName, 'width=600, height=400, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
     document.form_chk.action = url;
     document.form_chk.target = winName;
     document.form_chk.submit();
}

//인증 팝업 닫기
function fn_closeVerifyWin(){
    if(g_verifyWin != null){
        g_verifyWin.close();
    }
}

/* 화면 로드 처리 */
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        $(".wrap >").remove();
        if(opener){
            window.opener.fn_login();
            window.close();
        }else{
            window.parent.fn_login();
            gfn_closeModal(this.event);
        }
    </c:if>
});
</script>
</head>
<body>
<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    
        <div class="dimm" style="display:none;"></div>

        <!-- #layer01 -->
        <div class="layer_box" id="layer01" style="width:280px;">
            <div class="layer_tit">로그인 안내</div>
            <div class="layer_con">
                <ul class="list_style_01">
                    <li style="background: none;text-align: center">통합 계좌 조회 서비스 이용을 위해<br>
                    OAuth 로그인이 필요합니다</li>
                </ul>
                <div class="btn_area">
                    <a href="javascript:void(0);" class="btn_type3 type2" onclick="javascript:fn_popupClose();">취소</a>
                    <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_login();">로그인</a>
                </div> 
            </div>
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>
    <!-- // layer_popup -->
</div>
<form name="form_chk" method="post"></form>
</body>
</html>