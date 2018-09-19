<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptMyPwMod.jsp
 * @Description : 비밀번호 변경
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.10  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.29
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<!-- 보안3종 관련 START -->
<meta http-equiv="expires" content="-1" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<!-- 보안3종 관련 END -->
<!-- 보안3종 관련 START -->
<script type="text/javascript" src="<c:url value='/AOS2/include.js'/>"></script>
<script>
<%--window.onload = function(){--%>
    <%--$_astxu.log('[onload] '+navigator.userAgent);--%>
    <%--$_astxu.log('[onload] platform='+navigator.platform+',browser='+$ASTX2_CONST.BROWSER_VER);--%>
    <%----%>
    <%--$ASTX2.setOption({autofocus:true});--%>
    <%----%>
    <%--checkInstallASTX2(--%>
        <%--function onSuccess() {--%>
            <%--doATX2CheckServer();--%>
        <%--}--%>
    <%--);--%>
<%--}--%>

function doATX2CheckServer(){
    $ASTX2.checkServer(
        function onSuccess() {
            $_astxu.log('ASTX.checkServer() success');
            
            $ASTX2.initE2E();
            $_astxj.hideOverlay();
        }, 
        function onFailure() {
            $_astxu.log('ASTX.checkServer() failure: errno='+$ASTX2.getLastError());
        }
    );
}

var astxData = {
    userNowPw : "",
    userNewPw : "",
    userNewPwConfrm : ""
};
function onSubmit2(){
    
    $_astxu.log('[onSubmit2]');
    
    /*
    $ASTX2.getE2EData(
        document.form2,
    */
    $ASTX2.getE2EDataIDs(
//      ['cardno5','cardno6','cardno7','cardno8'],
        ['userNowPw', 'userNewPw', 'userNewPwConfrm'],
        function onSuccess(data) {
            //$_astxu.log('ASTX.getE2EData() success');
            
            jQuery.ajax({
                 url:'/AOS2/do_submit_ajax.jsp',
                 data: data,
                 type: "POST",
                 dataType: 'json',
                 success:function(json){
                     //$_astxu.log('[ajax] '+$_astxu.jsonQstr(json));
                     var data = $_astxu.jsonQstr(json)+"";
                     
                     var rsArr = data.split("&");
                     for(var i=0; i<rsArr.length; i++){
//                       alert('rsArr['+i+']:'+rsArr[i]);
                         var rsArr2 = rsArr[i].split("=");
                         
                         if('userNowPw' == rsArr2[0]){
                             astxData.userNowPw = util_setHtmlParsing(rsArr2[1]);
                         }
                         if('userNewPw' == rsArr2[0]){
                             astxData.userNewPw = util_setHtmlParsing(rsArr2[1]);
                         }
                         if('userNewPwConfrm' == rsArr2[0]){
                             astxData.userNewPwConfrm = util_setHtmlParsing(rsArr2[1]);
                         }
                     }
                                          
                     fn_PwChangeConfrm();
                 },
                 error:function(){
                     alert("허용되지 않는 특수문자가 들어갔습니다.");
                     return;
                 }
            });
            
        }, 
        function onFailure() {
            $_astxu.log('ASTX.getE2EData() failure: errno='+$ASTX2.getLastError());
        }
    );
    
}

var debuger = new myDebuger();
//debuger.write_output(document, true);
</script>
<!-- 보안3종 관련 END -->
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
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    //바탕 제거
    $("body").attr("style", "display:none");
    
    //로딩 호출
    gfn_setLoading(false);
    
    alert("로그인이 필요한 서비스 입니다.");
    
    if(opener){
        window.close();
    }else{
        gfn_closeModal();
    }
} 

//비밀번호 변경확인
function fn_PwChangeConfrm(){
	var userId = $("#userId").val(); //사용자의 아이디    
//	  var userNowPw = astxData.userNowPw;
//    var userNewPw = astxData.userNewPw;
//    var userNewPwConfrm = astxData.userNewPwConfrm;
    var userNowPw = $("#userNowPw").val();
    var userNewPw = $("#userNewPw").val();
    var userNewPwConfrm = $("#userNewPwConfrm").val();
    
    //[현재비밀번호]
    if(util_chkReturn(userNowPw, "s") == ""){
        alert("<spring:message code='errors.required' arguments='현재비밀번호'/>");
        $("#userNowPw").focus();
        return false;
    }
    
    //[새비밀번호]
    if(util_chkReturn(userNewPw, "s") == ""){
        alert("<spring:message code='errors.required' arguments='새비밀번호'/>");
        $("#userNewPw").focus();
        return false;
    }
    
    //[새비밀번호확인]
    if(util_chkReturn(userNewPwConfrm, "s") == ""){
        alert("<spring:message code='errors.required' arguments='새비밀번호확인'/>");
        $("#userNewPwConfrm").focus();
        return false;
    }
    
    //비밀번호 policy 확인(키보드 보안상태)
    if(!gfn_validationCheckPw("userNewPw", "Y", userNewPw)){
    	//키보드 보안 연동 비밀번호 초기화
//    	gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
        return false;
    }
    
    //[비밀번호]!=[비밀번호확인]
    if(userNewPw != userNewPwConfrm){
        alert("<spring:message code='errors.notsame' arguments='새비밀번호,새비밀번호확인'/>");
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
        return false;
    }
    
    //[비밀번호]가 [아이디]와 동일한지 체크
    if(userId == userNewPw) {
        alert("아이디 와 새비밀번호가 동일합니다.\r\n새비밀번호를 다시 입력하십시오.");
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
        return false;
    }
    
    //[비밀번호]에 [아이디]가 포함되어 있는지 체크
    if(userId.substring(0,4) == userNewPw.substring(0,4)){
        alert("아이디 와 새비밀번호 앞4자리가 동일 합니다.\r\n새비밀번호를 다시 입력하십시오.");
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
        return false;
    }
    
    //[현재비밀번호]가 [새비밀번호]와 동일한지 체크
    if(userNowPw == userNewPw) {
        alert("현재비밀번호와 새비밀번호가 동일합니다.\r\n새비밀번호를 다시 입력하십시오.");
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
        return false;
    }
    
    //로딩 호출
    gfn_setLoading(true, "기존 비밀번호 확인 중입니다.");
        
	var url = "<c:url value='/cpt/myp/cptMyInfo/checkPw.ajax'/>";
	var reqData = {
       "companyProfileRegNo"   : $("#companyProfileRegNo").val()
      ,"operatorProfileRegNo" : $("#operatorProfileRegNo").val()
      ,"operatorPassword" : userNowPw
	};	
    util_ajaxPage(url, reqData, "fn_PwChangeConfrm_callBack");
}
//비밀번호 변경확인 callback
function fn_PwChangeConfrm_callBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
	var checkPw = data.checkPw;	
	
	if(checkPw == "0"){
		alert("현재 비밀번호가 맞지 않습니다. 다시 입력해 주세요.");
		//키보드 보안 연동 비밀번호 초기화
//		gfn_clearE2EText("userNowPw");
        return;
	}else{
		//성공
		fn_PwChange();		
	}
}

//비밀번호 변경
function fn_PwChange(){
//	var userNowPw = astxData.userNowPw;
//    var userNewPw = astxData.userNewPw;
//    var userNewPwConfrm = astxData.userNewPwConfrm;
    var userNowPw = $("#userNowPw").val();
    var userNewPw = $("#userNewPw").val();
    var userNewPwConfrm = $("#userNewPwConfrm").val();
    
    var msgConfirm = "<spring:message code='confirm.modify.msg'/>";
    if( confirm(msgConfirm) ){
    	var url = "<c:url value='/cpt/myp/cptMyInfo/updateCptMyPwMod.ajax'/>";
    	var userId = $("#userId").val();
    	
    	var objParam = new Object();
        objParam.operatorId = userId;
        objParam.operatorPassword = userNewPw; 
        
        //로딩 호출
        gfn_setLoading(true, "저장중입니다.");
        
        $.ajax({
            type    : "post"
           ,url     : url
           ,data    : objParam
           ,success : function(data){
        	    //로그인 처리
        	    if(data.error == -1){
        	        fn_login();
        	        return;
        	    }
        	    
        	    //로딩 호출
        	    gfn_setLoading(false);
        	 
        	   //alert("<spring:message code='errors.required' arguments='비밀번호'/>");
               alert("비밀번호가 정상적으로 변경되었습니다.\n변경된 비밀번호로 다시 로그인 해 주세요.");
               fn_moveList();
           }
           ,error   : function(){
               //alert("정보수정 실패");
        	   //로딩 호출
               gfn_setLoading(false);
           }
       });
    }else{
    	//키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPwConfrm", false);
        //키보드 보안 연동 비밀번호 초기화
//        gfn_clearE2EText("userNewPw");
    }
}

//비밀번호 변경 후 성공시 이동함수
function fn_moveList(){	
	//parent.window.location.href = "<c:url value='/cpt/cmm/mainView.do'/>";
	
	if(opener){
        window.opener.fn_pwChgCallBack();
    }else{
        window.parent.fn_pwChgCallBack();
    }  
    
    gfn_closeModal(this.event);
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){     
        window.close();
    }else{      
        gfn_closeModal(this.event);
    }
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty loginVO}">
        fn_login();
        return;
    </c:if>
	
    //[다음에 변경]버튼 클릭 시 호출
    $("#btnNextChange").bind("click", function(){       
        parent.window.location.href = "<c:url value='/cpt/myp/cptMyInfo/cptMyPwModNextChange.do'/>";
        window.close();
    });
    
    /*
    //[비밀번호 변경]버튼 클릭 시 호출
    $("#btnPwChange").bind("click", function(){
    	fn_beforePwChange();
    });
    */
    
    //비밀번호 우클릭 금지
    $("#userNowPw, #userNewPw, #userNewPwConfrm").bind("contextmenu", function(e){
        return false;
    });
    
});

<%-- 비밀번호 변경 전 처리 --%>
function fn_beforePwChange(){
    fn_PwChangeConfrm();
//	onSubmit2();
}
</script>
</head>
<body>

<div class="wrap">
    <table Style="display:none">
        <tr> 
            <th>로그인 세션값 저장</th>
            <td>
                <input type="hidden" id="userId" name="userId" value="${ loginVO.id }" />
                <input type="hidden" id="companyProfileRegNo" name="companyProfileRegNo" value="${ loginVO.company_profile_reg_no }" />
                <input type="hidden" id="operatorProfileRegNo" name="operatorProfileRegNo" value="${ loginVO.operator_profile_reg_no }" />
            </td>
        </tr>
    </table>

    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">   

        <!-- #layer01 -->
        <div class="layer_box" id="layer01" style="width:620px;">
            <div class="layer_tit">비밀번호 변경</div>

            <div class="layer_con">

                <div class="pass_change_area">
                    <%-- 비밀번호 기간 지났을 경우 --%>
                    <c:if test="${loginVO.temp_password_yn eq 'N' && loginVO.final_password_date_yn eq 'Y'}">
                    <div class="tit_info">
                                        고객님께서는 <span class="point01">3개월</span> 동안 비밀번호를 변경하지 않으셨습니다.
                    </div>
                    <p class="txt_info">고객님의 소중한 개인정보 보호를 위해서 주기적으로 비밀번호를 변경하는 것을 권장합니다.<br>새로운 비밀번호를 입력하시고 [<strong>비밀번호 변경</strong>] 버튼을 클릭해 주십시요.</p>
                    </c:if>
                    
                    <%-- 임시비밀번호 발급 시 --%>
                    <c:if test="${loginVO.temp_password_yn eq 'Y'}">
                    <div class="tit_info">
                                        임시비밀번호 입니다.<span class="point01">비밀번호</span>를 변경해 주십시요.
                    </div>
                    <p class="txt_info">고객님의 소중한 개인정보 보호를 위해서 주기적으로 비밀번호를 변경하는 것을 권장합니다.<br>새로운 비밀번호를 입력하시고 [<strong>비밀번호 변경</strong>] 버튼을 클릭해 주십시요.</p>
                    </c:if>
                    
                    <div class="password_box">
                        <c:if test="${loginVO.final_password_date_yn eq 'N'}"><br/></c:if>
                        <ul>
                            <li>
                                <label for="old_pw">현재 비밀번호</label>
                                <input type="password" id="userNowPw" name="userNowPw" class="input_txt01" maxlength="15" tabindex="1"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#userNewPw').focus();" 
                                       value=""
                                       autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'userNowPw',15);"
                                >
                            </li>
                            <li>
                                <label for="new_pw01">새 비밀번호</label>
                                <input type="password" id="userNewPw" name="userNewPw" class="input_txt01 type2" maxlength="15" tabindex="2"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#userNewPwConfrm').focus();" 
                                       value=""
                                       autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'userNewPw',15);"
                                >
                                <div class="info_msg">
                                    <ul class="list_style_01">
                                        <li>8~15자의 영문 대/소문자, 숫자,<br>특수문자 중 3개 이상의 조합</li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <label for="new_pw02">새 비밀번호 확인</label>
                                <input type="password" id="userNewPwConfrm" name="userNewPwConfrm" class="input_txt01 type2" maxlength="15" tabindex="3"
                                       onkeydown="javascript:if(event.keyCode == 13) fn_beforePwChange();" 
                                       value=""
                                       autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'userNewPwConfrm',15);"
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="btn_area">
                        <c:if test="${loginVO.final_password_date_yn eq 'N' && loginVO.temp_password_yn eq 'N'}">
                        <a href="javascript:void(0);" id="btnNextChange" class="btn_type5 type2">다음에 변경</a>
                        </c:if>
                        <a href="javascript:fn_beforePwChange();" id="btnPwChange" class="btn_type5">비밀번호 변경</a>
                    </div>  
                    
                    <div class="info_box type2">
                        <div class="tit">
                            <p class="icon_tip">안전한 비밀번호 변경</p>
                        </div>
                        <div class="txt">
                            <ul class="list_type01">
                                <li>영문/숫자/기호 등의 조합으로 비밀번호를 구성해 주세요.</li>
                                <li>연속숫자, 연속문자, 동일숫자 또는 이름은 비밀번호로 사용할 수 없습니다.</li>
                                <li>쉽게 유추해 낼 수 있는 아이디/생일/핸드폰번호 등 개인정보와 관련된 문자, 숫자는 사용하지 않는 것이 좋습니다.</li>
                            </ul>
                        </div>
                    </div>
                </div>
                
            </div>
            <c:if test="${loginVO.final_password_date_yn eq 'N' && loginVO.temp_password_yn eq 'N'}">
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
            </c:if>
        </div>

    </div>
    <!-- // layer_popup -->

    <%-- footer --%>
    <%-- <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %> --%>
    <%-- footer --%>

</div>
	
</body>
</html>