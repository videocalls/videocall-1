<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserEmailDtl.jsp
 * @Description : admin 포털 메일 발송 이력 관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.13  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.13
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.energyblue.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownbutton.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcolorpicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxwindow.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxtooltip.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcheckbox.js'/>"></script>
<%-- //jqwidgets --%>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/userEmailManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "01002";
    
    gfn_loginNeedMove(url, param);
} 
 
//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
	//목록
    $("#btnList").click(function(){
    	util_moveRequest("AptUserEmailManageVO", "<c:url value='/apt/sptUsr/userEmailManageList.do'/>");    
    });
	
    //저장
    $("#btnSave").click(function(){
    	fn_save();
    });
    
    //관리자 메일발송
    $("#btnEmailSend").click(function(){
        fn_emailSend();
    });
    
    //발신자 타입 변경 시
    $('#senderKindSelect').change(function(){
        if($(this).val()==""){
            $('#senderKind2').val("");
            $('#senderKind2').attr("disabled",false);
        }else{        	
            $('#senderKind2').val($("#senderKindSelect option:selected").text());
            $('#senderKind').val($(this).val());
            $('#senderKind2').attr("disabled",true);
        }
    });
    
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
    
    //이메일유형 타입 변경 시
    $('#emailSendTypeSelect').change(function(){
        if($(this).val()==""){
            $('#emailSendType2').val("");
            $('#emailSendType2').attr("disabled",false);
        }else{          
            $('#emailSendType2').val($("#emailSendTypeSelect option:selected").text());
            $('#emailSendType').val($(this).val());
            $('#emailSendType2').attr("disabled",true);
        }
    });
    
    $('#emailContent').jqxEditor({
        height: "400px",
        width: "100%",
        tools : "bold italic underline | format font size | color background | left center right | outdent indent | ul ol | html"
    });
    
    //기본정보 셋팅
    fn_init();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 기본정보 셋팅 --%> 
function fn_init(){
	//이메일
    var emailArr = "<c:out value='${resultDetail.receiverEmail}'/>".split("@");
    if(emailArr != null){
    	$("#email_01").val(emailArr[0]); 
    	$("#email_02").val(emailArr[1]);
    }
} 

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){	
        return; //유효성검증 실패
	}
	
	//로딩 호출
    gfn_setLoading(true);
			
	var moveUrl = "<c:url value='/apt/sptUsr/updateAptUserEmailManage.ajax'/>";
    var param = $("#AptUserEmailManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCallBack(data){
	//로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
	var msg = "성공";
	if(data.result <= 0){
		msg = "실패";
	}
	
	alert("저장에 "+msg+"하였습니다.");
    return;
}

<%-- 관리자 메일발송 --%>
function fn_emailSend(){
    if(!fn_validate()){ 
        return; //유효성검증 실패
    }
    
    //로딩 호출
    gfn_setLoading(true, "메일발송중입니다.");
            
    var moveUrl = "<c:url value='/apt/sptUsr/updateAptUserEmailManageDtlEmailSend.ajax'/>";
    var param = $("#AptUserEmailManageVO").serialize();
    var callBackFunc = "fn_emailSendCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_emailSendCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    var msg = "성공";
    if(data.result != "0"){
        msg = "실패";
        alert("관리자 메일발송에 "+msg+"하였습니다.");
    }else{
    	alert("메일이 발송되었습니다.");
        util_moveRequest("AptUserEmailManageVO", "<c:url value='/apt/sptUsr/userEmailManageList.do'/>");
    }
    
    return;
}

<%-- 유효성 체크 --%>
function fn_validate(){
	//발신자
    /* if(util_chkReturn($.trim($('#senderKind2').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='발신자'/>");
        $('#senderKind2').val("");
        $('#senderKind2').focus();
        return false;
    } */
	
    //ID
    /* if(util_chkReturn($.trim($('#senderId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='ID'/>");
        $('#senderId').val("");
        $('#senderId').focus();
        return false;
    } */
	
	//이메일
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='수신자이메일'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='수신자이메일'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#receiverEmail").val(email);
	
    //이메일유형
    /* if(util_chkReturn($.trim($('#emailSendType2').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이메일유형'/>");
        $('#emailSendType2').val("");
        $('#emailSendType2').focus();
        return false;
    } */
  
    //이메일컨텐츠
    if(util_chkReturn($.trim($('#emailContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이메일컨텐츠'/>");
        $('#emailContent').val("");
        $('#emailContent').focus();
        return false;
    }
    
	return true;
}
 
</script>

</head>

<body>
<form:form commandName="AptUserEmailManageVO" name="AptUserEmailManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${resultDetail.pageIndex}'/>" /><%-- 현재페이지번호 --%>
<input type="hidden" name="emailRegNo" id="emailRegNo" value="<c:out value='${resultDetail.emailRegNo}'/>" />
<input type="hidden" name="receiverId" id="receiverId" value="<c:out value='${resultDetail.receiverId}'/>" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <section class="content">
                <div class="location">
                    <h2>메일 발송 이력 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>                
                <div class="tb_write1">
                    <table>
                        <caption>메일 발송 이력 정보</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">발신자</th>
                            <td class="txt_l">
                                <c:out value='${resultDetail.senderKind}'/>
                                <input type="hidden" class="w100" id="senderKind2" name="senderKind2" value="<c:out value='${resultDetail.senderKind}'/>" disabled="true"/>
                                <%-- <select id="senderKindSelect" title="발신자종류선택">
                                    <c:forEach var="senderKindList" items="${senderKindList}" varStatus="status">
                                        <option value="${senderKindList.system_code}">${senderKindList.code_name_kor}</option>
                                    </c:forEach>
                                </select> --%>
                                <input type="hidden" id="senderKind" name="senderKind" value="<c:out value='${resultDetail.senderKind2}'/>" />                                
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">ID</th>
                            <td class="txt_l">
                                <c:out value='${resultDetail.senderId}'/>
                                <input type="hidden" class="w100" id="senderId" name="senderId" value="<c:out value='${resultDetail.senderId}'/>" disabled="true"/>
                            </td>                  
                        </tr>                        
                        <tr>
                            <th scope="row">수신자이메일</th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="email_01"/> @
                                <input type="text" class="w150" id="email_02"/>
                                <select id="emailSelect" title="이메일선택">
	                                <option value="">직접입력</option>
	                                <c:forEach var="emailList" items="${emailList}" varStatus="status">
	                                    <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
	                                </c:forEach>
                                </select>
                                <input type="hidden" id="receiverEmail" name="receiverEmail" value="<c:out value='${resultDetail.receiverEmail}'/>" />
                            </td>                   
                        </tr>
                        <tr>
                            <th scope="row">이메일유형</th>
                            <td class="txt_l">
                                <c:out value='${resultDetail.emailSendType}'/>
                                <input type="hidden" class="w100" id="emailSendType2" name="emailSendType2" value="<c:out value='${resultDetail.emailSendType}'/>" disabled="true"/>
                                <%-- <select id="emailSendTypeSelect" title="이메일유형종류선택">
                                    <c:forEach var="emailSendTypeList" items="${emailSendTypeList}" varStatus="status">
                                        <option value="${emailSendTypeList.system_code}">${emailSendTypeList.code_name_kor}</option>
                                    </c:forEach>
                                </select> --%>
                                <input type="hidden" id="emailSendType" name="emailSendType" value="<c:out value='${resultDetail.emailSendType2}'/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">이메일제목</th>
                            <td class="txt_l">
                                <input type="text" style="width:800px;" id="emailTitle" name="emailTitle" value="<c:out value='${resultDetail.emailTitle}'/>" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">이메일컨텐츠<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <%-- <input type="text" class="w825" id="emailContent" name="emailContent" value="<c:out value='${resultDetail.emailContent}'/>" /> --%>
                                <textarea style="height:120px;" id="emailContent" name="emailContent" rows="15" cols="100">${resultDetail.emailContent}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">발송일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.sendResultDate}'/></td>
                        </tr>                        
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                        <!-- <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span> -->
                    </div>
                    <div class="right">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnEmailSend">관리자 수정 메일발송</a></span>
                    </div>
                </div>
                
            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>