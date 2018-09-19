<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserEmailManageListPopup.jsp
 * @Description : 관리자 메일발송 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.23  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.23
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

/*******************************************
 * 이벤트 함수
 *******************************************/

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
        	gfn_closeModal();
        }
    </c:if>
    
    //이메일 주소 타입 변경 시
    $('#emailSelect').change(function(){
        if($(this).val()==""){
            $('#email_02').val("");
            $('#email_02').attr("disabled",false);
        }else{
            $('#email_02').val($(this).val());
            $('#email_02').attr("disabled",true);
        }
    });
    
    //관리자 메일발송
    $("#btnManagerEmailSend").click(function(){
        fn_managerEmailSend();
    });
    
});

/*******************************************
 * 기능 함수
 *******************************************/
 
<%-- 관리자 메일발송 --%>
function fn_managerEmailSend(){
	if(!fn_validate()){ 
        return; //유효성검증 실패
    }
	
    //로딩 호출
    gfn_setLoading(true, "메일발송중입니다.");
        
    //page setting  
    var url = "<c:url value='/apt/sptUsr/updateAptUserEmailManageBaseEmailSend.ajax'/>";
    var param = $("#AptUserEmailManageVO").serialize();
    var callBackFunc = "fn_managerEmailSendCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_managerEmailSendCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	if(opener){
            window.opener.fn_login();
            window.close();
        }else{
            window.parent.fn_login();
            gfn_closeModal();
        }    
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
        
    
    alert("메일이 발송되었습니다.");
    
    if(opener){
    	window.opener.fn_search();
        window.close();
    }else{
    	window.parent.fn_search();
        gfn_closeModal(this.event);
    }
}

<%-- 유효성 체크 --%>
function fn_validate(){
    //이메일 수신자 성명
    if(util_chkReturn($.trim($('#receiverNmae').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이메일 수신자 성명'/>");
        $('#receiverNmae').val("");
        $('#receiverNmae').focus();
        return false;
    }
    
    //이메일 수신자 이메일주소
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='이메일 수신자 이메일주소'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='이메일 수신자 이메일주소'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#receiverEmail").val(email);
    
    //발송 이메일 제목
    if(util_chkReturn($.trim($('#emailTitle').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='발송 이메일 제목'/>");
        $('#emailTitle').val("");
        $('#emailTitle').focus();
        return false;
    }
    
    //발송 이메일 내용
    if(util_chkReturn($.trim($('#emailContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='발송 이메일 내용'/>");
        $('#emailContent').val("");
        $('#emailContent').focus();
        return false;
    }
    
    return true;
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
} 
</script>
</head>
<body>
<form:form commandName="AptUserEmailManageVO" name="AptUserEmailManageVO" method="post">

<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${AptUserEmailManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="10" /><!-- //목록사이즈 -->

<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    

        <!-- #layer01 -->
        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">관리자 메일발송</div>
            
            <div class="layer_con">
            
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                <div class="tb_write1">
                    <table>
                        <caption>관리자 메일발송 정보입력</caption>
                        <colgroup>
                            <col style="width:29%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        
                        <tr>
                            <th scope="row"><label for="">수신자 성명</label><span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" style="width:100px;" id="receiverNmae" name="receiverNmae" tabindex="1"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#email_01').focus();"
                                       value=""
                                />
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">수신자 이메일주소</label><span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="email_01" tabindex="2"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#email_02').focus();"
                                       value=""
                                /> @
                                <input type="text" class="w100" id="email_02" tabindex="3"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#emailTitle').focus();"
                                       value=""
                                />
                                <select id="emailSelect" title="이메일선택">
                                    <option value="">직접입력</option>
                                    <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                        <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="receiverEmail" name="receiverEmail" value="" />
                                <!-- <input type="text" style="width:250px;" id="receiverEmail" name="receiverEmail"
                                       onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();"  
                                /> -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">발송 이메일 제목</label><span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" style="width:300px;" id="emailTitle" name="emailTitle" tabindex="4"
                                       onkeydown="javascript:if(event.keyCode == 13) $('#emailContent').focus();"
                                       value=""  
                                />
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">발송 이메일 내용</label><span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">                                
                                <textarea style="height:280px;" id="emailContent" name="emailContent" rows="15" cols="100"
                                          value=""
                                ></textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                                
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnManagerEmailSend">관리자 메일발송</a></span>                    
                </div>
                <!-- // btn_type3 -->
                
            </div>
            
            <!-- 닫기 -->
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>        
    <!-- // layer_popup -->   
</div>
</form:form>
</body>
</html>