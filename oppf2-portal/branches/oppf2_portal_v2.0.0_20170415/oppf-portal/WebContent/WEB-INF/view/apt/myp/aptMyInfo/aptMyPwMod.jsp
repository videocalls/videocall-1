<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptMyPwMod.jsp
 * @Description : 회원정보 확인 전 비밀번호 확인
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.28  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.28
 * @version 1.0
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
	var userNowPw = $.trim($("#userNowPw").val());
    var userNewPw = $.trim($("#userNewPw").val());
    var userNewPwConfrm = $.trim($("#userNewPwConfrm").val());
        
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
    
    if(!gfn_validationCheckPw("userNewPw")){
        return false;
    }
    
    //[비밀번호]!=[비밀번호확인]
    if(userNewPw != userNewPwConfrm){
        alert("<spring:message code='errors.notsame' arguments='새비밀번호,새비밀번호확인'/>");
        return false;
    }
    
    //[비밀번호]가 [아이디]와 동일한지 체크
    if(userId == userNewPw) {
        alert("아이디 와 새비밀번호가 동일합니다.\r\n새비밀번호를 다시 입력하십시오.");
        $("#userNewPw").focus();
        return false;
    }
    
    //[비밀번호]에 [아이디]가 포함되어 있는지 체크
    if(userId.substring(0,4) == userNewPw.substring(0,4)){
        alert("아이디 와 새비밀번호 앞4자리가 동일 합니다.\r\n새비밀번호를 다시 입력하십시오.");
        $("#userNewPw").focus();
        return false;
    }
    
    //[현재비밀번호]가 [새비밀번호]와 동일한지 체크
    if(userNowPw == userNewPw) {
        alert("현재비밀번호와 새비밀번호가 동일합니다.\r\n새비밀번호를 다시 입력하십시오.");
        $("#userNewPw").focus();
        return false;
    }
    
    //로딩 호출
    gfn_setLoading(true, "기존 비밀번호 확인 중입니다.");
    
	var url = "<c:url value='/apt/myp/aptMyInfo/checkPw.ajax'/>";
	var reqData = {
       "adminId"   : $("#userId").val()
      ,"adminProfileRegNo"  : $("#adminProfileRegNo").val()
      ,"adminPassword" : $("#userNowPw").val()
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
  
	var userNowPw = $.trim($("#userNowPw").val());
    var userNewPw = $.trim($("#userNewPw").val());
    var userNewPwConfrm = $.trim($("#userNewPwConfrm").val());
	var checkPw = data.checkPw;	
	
	if(checkPw == "0"){
		alert("현재 비밀번호가 맞지 않습니다. 다시 입력해 주세요.");
        $("#userNowPw").focus();
        return;
	}else{
		//성공
		fn_PwChange();		
	}
}

//비밀번호 변경
function fn_PwChange(){
	var userNowPw = $.trim($("#userNowPw").val());
    var userNewPw = $.trim($("#userNewPw").val());
    var userNewPwConfrm = $.trim($("#userNewPwConfrm").val());
    
    var msgConfirm = "<spring:message code='confirm.modify.msg'/>";
    if( confirm(msgConfirm) ){
    	var url = "<c:url value='/apt/myp/aptMyInfo/updateAptMyPwMod.ajax'/>";
    	var userId = $("#userId").val();
    	var adminProfileRegNo = $("#adminProfileRegNo").val();
    	
    	var objParam = new Object();
        objParam.adminId = userId;
        objParam.adminPassword = userNewPw; 
        objParam.adminProfileRegNo = adminProfileRegNo;
        
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
               alert("비밀번호가 정상적으로 변경되었습니다.");
               fn_moveList();                
           }
           ,error   : function(){
               //alert("정보수정 실패");
        	   //로딩 호출
        	   gfn_setLoading(false);
           }
       });
    }
}

//비밀번호 변경 후 성공시 이동함수
function fn_moveList(){
	/* window.opener.top.location.href = "/apt/cmm/mainView.do";
    window.open("about:blank","_self").close(); */
    parent.window.location.href = "<c:url value='/apt/cmm/mainView.do'/>";
    window.close();
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
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
	
    //[다음에 변경]버튼 클릭 시 호출
    $("#btnNextChange").bind("click", function(){       
        parent.window.location.href = "<c:url value='/apt/cmm/mainView.do'/>";
        window.close();
    });
    
    //[비밀번호 변경]버튼 클릭 시 호출
    $("#btnPwChange").bind("click", function(){
        fn_PwChangeConfrm();        
    });
    
});
</script>
</head>
<body>
<div class="wrap">
    <table Style="display:none">
        <tr> 
            <th>로그인 세션값 저장</th>
            <td>
                <input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />
                <input type="hidden" id="customerRegNo" name="customerRegNo" value="${ LoginVO.admin_profile_reg_no }" />
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
                    <c:if test="${LoginVO.final_password_date_yn eq 'Y'}">
                    <div class="tit_info">
                                  고객님께서는 <span class="point01">3개월</span> 동안 비밀번호를 변경하지 않으셨습니다.
                    </div>
                    <p class="txt_info">고객님의 소중한 개인정보 보호를 위해서 주기적으로 비밀번호를 변경하는 것을 권장합니다.<br>새로운 비밀번호를 입력하시고 [<strong>비밀번호 변경</strong>] 버튼을 클릭해 주세요.</p>
                    </c:if>
                    
                    <div class="password_box">
                        <c:if test="${LoginVO.final_password_date_yn eq 'N'}"><br/></c:if>
                        <ul>
                            <li>
                                <label for="old_pw">현재 비밀번호</label>
                                <input type="password" id="userNowPw" class="input_txt01" maxlength="12" tabindex="1"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#userNewPw').focus();" 
                                       value=""
                                >
                            </li>
                            <li>
                                <label for="new_pw01">새 비밀번호</label>
                                <input type="password" id="userNewPw" class="input_txt01 type2" maxlength="12" tabindex="2"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#userNewPwConfrm').focus();" 
                                       value=""
                                >
                                <div class="info_msg">
                                    <ul class="list_style_01">
                                        <li>8~15자의 영문 대/소문자, 숫자,<br>특수문자 중 3개 이상의 조합</li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <label for="new_pw02">새 비밀번호 확인</label>
                                <input type="password" id="userNewPwConfrm" class="input_txt01 type2" maxlength="12" tabindex="3"
                                       onkeydown="javascript:if(event.keyCode == 13) btnPwChange.click();" 
                                       value=""
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="btn_type3">
                        <c:if test="${LoginVO.final_password_date_yn eq 'N'}">
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnNextChange">다음에 변경</a></span>
                        </c:if>
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnPwChange">비밀번호 변경</a></span>
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

            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>

    </div>
    <!-- // layer_popup -->

    <%-- footer --%>
    <%-- <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %> --%>
    <%-- footer --%>

</div>    
	
</body>
</html>