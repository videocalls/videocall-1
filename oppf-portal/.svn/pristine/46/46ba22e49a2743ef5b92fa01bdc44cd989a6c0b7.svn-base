<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : appManageDtl.jsp
 * @Description : app 상세/수정
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

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#contractDate, #dateFrom, #dateTo").datepicker({
        showOn: "button",
        dateFormat: 'yy-mm-dd',
        buttonImage: "<c:url value='/images/apt/calendar.png'/>",
        buttonImageOnly: true,
        buttonText: "달력보기",
//        minDate: '-3y',
//        maxDate: '+10y',
        currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
	var url = "<c:url value='/apt/app/appManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "04001";
    
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
    	fn_list();    
    });
	
	//저장
	$("#btnSave").click(function(){
		fn_save();
	});
	
	//App 서비스 계약 여부 radio
    $("input[name=appContractCode]").change(function(){
    	fn_setAppTermsDate();
    });
	
	//file size event
	gfn_initFile("file", 60);
	    
	//제공자 셋팅
	$('input[name=companyCodeId]:checked').focus();
	
	//App 서비스 계약 여부에 따라 tr 보여주기
	fn_setAppTermsDate();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- App 서비스 계약 여부에 따라 tr 보여주기 --%>
function fn_setAppTermsDate(){
    var appContractCode = $('input[name=appContractCode]:checked').val();
    
    //필요없음, 미계약
    if(appContractCode == "G023002" || appContractCode == "G023006"){
    	$("#dateFrom").val("");
        $("#dateTo").val("");
        
        $("#dateFrom").css("background-color","silver");
        $("#dateFrom").attr("readonly",true);
        $("#dateTo").css("background-color","silver");
        $("#dateTo").attr("readonly",true);
        
        $("#dateFrom").datepicker("option", "disabled", true);
        $("#dateTo").datepicker("option", "disabled", true);
    }else{
    	$("#dateFrom").datepicker("option", "disabled", false);
        $("#dateTo").datepicker("option", "disabled", false);
        
        $("#dateFrom").css("background-color","#ffffff");
        $("#dateFrom").attr("readonly",false);
        $("#dateTo").css("background-color","#ffffff");
        $("#dateTo").attr("readonly",false);
    }
} 
 
<%-- 목록 --%>
function fn_list(){

	util_moveRequest("AppManageVO", "<c:url value='/apt/app/appManageList.do'/>", "_self");
}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	var moveUrl = "<c:url value='/apt/app/saveAppManage.ajax'/>";
	var callBackFunc = "fn_saveCallBack";
	
	//로딩 호출
    gfn_setLoading(true, "저장중 입니다.");
	
	//file submit
	gfn_filePage("AppManageVO", moveUrl, callBackFunc);    
}
function fn_saveCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
	if(data.result <= 0){
        alert("<spring:message code='fail.alert.save' />");
    }else{
        alert("<spring:message code='success.alert.save' />");
    }
	
	//이미지 리로드
	if($("#file").val() != ""){
		var date = new Date();
	    $("#appIconImg").attr("src", "<c:url value='/cmm/appImg/${resultDetail.appId}.do?'/>"+date.getTime());
	}
	
	//file 리셋
    $("#file").val("");
    $("#isAppIcon").val("Y");
	
	return;
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//App. 구분
	if(util_chkReturn($.trim($('input[name=appCategory]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='App. 구분'/>");
        return false;
    }
		
	//App. 설명
	if(util_chkReturn($.trim($('#appDescription').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='App. 설명'/>");
        $('#appDescription').focus();
        return false;
    }
	
	//활성화여부
    if(util_chkReturn($.trim($('input[name=exposureYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='활성화 여부'/>");
        $('#exposureYn_N').focus();
        return false;
    }
    
    //활성화 순서
    if(util_chkReturn($.trim($('#exposureOrder').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='활성화 순서'/>");
        $('#exposureOrder').focus();
        return false;
    }else{
        if(!util_isNum($.trim($('#exposureOrder').val()))){
            alert("<spring:message code='errors.integer' arguments='활성화 순서'/>");
            $('#exposureOrder').focus();
            return false;           
        }
    }
    
    //첨부파일 체크
    if(util_chkReturn($.trim($('#file').val()), "s") == ""){
        //첨부파일을 등록 안했을 경우에만
        if($("#isAppIcon").val() == "N"){
            alert("<spring:message code='errors.required' arguments='App. 아이콘의 이미지' />");
            $("#file").focus();
            return false;
        }
    }else{
        var extension = gfn_getFileExt("file");
        
        //이미지 파일은 JPG, PNG 확장자만 가능
        if( (extension != 'jpg') && (extension != 'png') ) {
            alert("이미지 파일은 JPG, PNG 확장자만 가능합니다.");
            return false;
        }
    }
	
	//App. 서비스 계약 여부
    if(util_chkReturn($.trim($('input[name=appContractCode]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='App. 계약'/>");
        return false;
    }
	
	//App. 서비스 계약 체결일
    if(util_chkReturn($.trim($('#contractDate').val()), "s") != "") {
    	var appContractDate = util_replaceAll($("#contractDate").val(), "-", "");
    	if(!util_isDate(appContractDate)){
            alert("<spring:message code='errors.invalid' arguments='App. 계약 체결일' />");
            $("#contractDate").focus();
            return false;
        }
    	
    	$("#appContractDate").val(appContractDate);
    }
	
    	
    //App. 서비스 계약 기간
    var dateFrom = util_replaceAll($("#dateFrom").val(), "-", "");
    var dateTo = util_replaceAll($("#dateTo").val(), "-", "");
    
    //App. 서비스 계약 여부가 미계약 또는 필요없음 일때
    if(
    	util_chkReturn($.trim($('input[name=appContractCode]:checked').val()), "s") == "G023002" ||
    	util_chkReturn($.trim($('input[name=appContractCode]:checked').val()), "s") == "G023006"
    ){
    	dateFrom = "<spring:message code='Globals.format.dateFrom' />";
    	dateTo = "<spring:message code='Globals.format.dateTo' />";
    }else{
	    if(dateFrom == ""){
	        alert("<spring:message code='errors.required' arguments='App. 계약 시작일' />");
	        $("#dateFrom").focus();
	        return false;
	    }else{
		    if(!util_isDate(dateFrom)){
		        alert("<spring:message code='errors.invalid' arguments='App. 계약 시작일' />");
		        $("#dateFrom").focus();
		        return false;
		    }
	    }
	    
	    if(dateTo == ""){
	        alert("<spring:message code='errors.required' arguments='App. 계약 종료일' />");
	        $("#dateTo").focus();
	        return false;
	    }else{
	        if(!util_isDate(dateTo)){
	            alert("<spring:message code='errors.invalid' arguments='App. 계약 종료일' />");
	            $("#dateTo").focus();
	            return false;
	        }else{
	            if(dateFrom > dateTo){
	                alert("App. 서비스 계약 종료일이 시작일보다 클 수 없습니다.");
	                $("#dateTo").focus();
	                return false;
	            }
	        }   
	    }
	    
	    var msg = "";
	    var termsMsg = "";
	    var param = {};
	    <c:choose>
	        <c:when test="${empty resultApiList}" >
	            msg = "API 서비스 계약 내용이 없습니다.";
	        </c:when>
	        <c:otherwise>
	            <c:forEach var="resultApiList" items="${resultApiList}" varStatus="status">
	                param = {
	                    dateFrom : dateFrom,
	                    dateTo : dateTo,
	                    companyName : "<c:out value='${resultApiList.companyNameKor}'/>",
	                    apiName : "<c:out value='${resultApiList.apiName}'/>",
	                    apiContractCode : "<c:out value='${resultApiList.apiContractCode}'/>",
	                    apiContractCodeName : "<c:out value='${resultApiList.apiContractCodeName}'/>",
	                    apiTermsStartDate : util_replaceAll("<c:out value='${resultApiList.apiTermsStartDate}'/>", "-", ""),
	                    apiTermsExpireDate : util_replaceAll("<c:out value='${resultApiList.apiTermsExpireDate}'/>", "-", "")
	                };
	                
	                termsMsg = fn_getTermsMsg(param);
	                if(termsMsg != ""){
	                	if(msg != "") msg += "\n\n";
	                    msg += termsMsg;
	                }
	            </c:forEach>
	        </c:otherwise>
	    </c:choose>
	    
	    if(msg != ""){
	    	if(!confirm(msg + "\n\n저장 하시겠습니까?")){
	    		return false;
	    	}
	    }
    }	    
    
    $("#appTermsStartDate").val(dateFrom);
    $("#appTermsExpireDate").val(dateTo);
    
	return true;
}
<%-- api 계약 데이터 체크 --%>
function fn_getTermsMsg(param){
	var msg = "";
	var msgApiName = param.companyName + " " + param.apiName + " API";
	
	if(util_chkReturn($.trim(param.companyName), "s") == ""){
		msg += param.apiName + " API의 추가정보가 입력되지 않았습니다.";
	}else{
		//계약, 재계약, 필요없을 일때에만 체크
	    if(param.apiContractCode == "G023001" || param.apiContractCode == "G023005" || param.apiContractCode == "G023006"){
	        //필요없을 일때에는 패스
	        if(param.apiContractCode != "G023006"){
	            //기간 체크
	            //계약 시작 기간 체크
	            if(param.dateFrom < param.apiTermsStartDate){
	                msg += msgApiName + "의 App.서비스 계약기간의 시작일을  API 서비스 계약 체결일의 시작일 보다 이전일을 선택 하셨습니다.";
	            }
	            
	            //계약 종료 기간 체크
	            if(msg != "") msg += "\n";
                if(param.apiTermsExpireDate < param.dateTo){
                	msg += msgApiName + "의 App.서비스 계약기간의 종료일을  API 서비스 계약 채결일의 종료일 보다 이후일을 선택 하셨습니다.";
                }
	        }
	    }else{
	        msg += msgApiName + "의 계약 상태가 " + param.apiContractCodeName + "입니다.";
	    }
	}		
	
    return msg;	
}

</script>

</head>

<body>
<form:form commandName="AppManageVO" name="AppManageVO" method="post" enctype='multipart/form-data'>
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchAppCategory" id="searchAppCategory" value="<c:out value='${paramVO.searchAppCategory}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchAppStatus" id="searchAppStatus" value="<c:out value='${paramVO.searchAppStatus}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchAppContractCode" id="searchAppContractCode" value="<c:out value='${paramVO.searchAppContractCode}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchCompanyCodeId" id="searchCompanyCodeId" value="<c:out value='${paramVO.searchCompanyCodeId}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchExposureYn" id="searchExposureYn" value="<c:out value='${paramVO.searchExposureYn}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchDateFrom" id="searchDateFrom" value="<c:out value='${paramVO.searchDateFrom}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchDateTo" id="searchDateTo" value="<c:out value='${paramVO.searchDateTo}'/>" /><!-- //검색조건 -->

<input type="hidden" name="appId" id="appId" value="<c:out value='${paramVO.appId}'/>" />
<input type="hidden" name="appName" id="appName" value="<c:out value='${resultDetail.appName}'/>" />
<input type="hidden" name="appKey" id="appKey" value="<c:out value='${resultDetail.appKey}'/>" />

<input type="hidden" name="appContractDate" id="appContractDate" />
<input type="hidden" name="appTermsStartDate" id="appTermsStartDate" />
<input type="hidden" name="appTermsExpireDate" id="appTermsExpireDate" />

<input type="hidden" name="isAppIcon" id="isAppIcon" value="<c:out value='${resultDetail.isAppIcon}'/>" />

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
                    <h2>App. 세부 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                
                <div class="tb_write1">
                    <table>
                        <caption>App. 세부 정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:5%;">
                            <col style="width:5%;">
                            <col style="width:10%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row" rowspan="10">App. 등록 정보</th>
                            <th scope="row" colspan="3">App. 이름</th>
                            <td class="txt_l"><c:out value='${resultDetail.appName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. ID</th>
                            <td class="txt_l"><c:out value='${resultDetail.appId}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. Key</th>
                            <td class="txt_l"><c:out value='${resultDetail.appKey}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. 상태</th>
                            <td class="txt_l"><c:out value='${resultDetail.appStatusName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">앱개발자 명</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyNameKor}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">앱개발자 코드</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyCodeId}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">Secret</th>
                            <td class="txt_l"><c:out value='${resultDetail.keySecret}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuthCallbackURL</th>
                            <td class="txt_l" style="word-break: break-all;"><c:out value='${resultDetail.oauthCallbackUrl}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuth Scope</th>
                            <td class="txt_l"><c:out value='${resultDetail.oauthScope}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuth Type</th>
                            <td class="txt_l"><c:out value='${resultDetail.oauthType}'/></td>
                        </tr>

                        <tr>
                            <th scope="row" rowspan="11">App. 추가 정보</th>
                            <th scope="row" rowspan="6">포털<br>표시 정보</th>
                            <th scope="row" colspan="2">App. 구분<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <c:forEach items="${appCategoryList}" var="appCategoryList" varStatus="status">
                                            <li>
                                            <input type="radio" name="appCategory" id="appCategory_${appCategoryList.system_code}" value="${appCategoryList.system_code}"
                                                   <c:if test="${resultDetail.appCategory eq appCategoryList.system_code}"> checked="checked" </c:if>
                                            >
                                            <label for="appCategory_${appCategoryList.system_code}">${appCategoryList.code_name_kor}</label>
                                            </li>
                                        </c:forEach>
                                    </ul> 
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">App. 설명<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <textarea id="appDescription" name="appDescription" >${resultDetail.appDescription}</textarea>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">활성화 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="exposureYn_Y" name="exposureYn" value="Y"
                                    <c:if test="${resultDetail.exposureYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="exposureYn_Y"> 활성 </label>
                                <input type="radio" id="exposureYn_N" name="exposureYn" value="N"
                                    <c:if test="${resultDetail.exposureYn eq 'N'}"> checked="checked" </c:if>
                                > <label for="exposureYn_N"> 미활성 </label>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">활성화 순서<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100 txt_r" id="exposureOrder" name="exposureOrder" value="${resultDetail.exposureOrder}" maxlength="3" />
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="2">App.<br>아이콘</th>
                            <th scope="row">아이콘<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="app_icon">
                                    <div class="icon">
                                        <c:set value="/cmm/appImg/${resultDetail.appId}.do" var="appIconImgSrc" />
                                        <c:if test="${resultDetail.isAppIcon eq 'N'}">
                                            <c:set value="/images/cmm/icon/icon_app_none.png" var="appIconImgSrc" />
                                        </c:if>
                                        
                                        <img id="appIconImg" src="<c:url value='${ appIconImgSrc }'/>" alt="App. 아이콘"
                                        onerror="this.src='<c:url value='/images/cmm/icon/icon_app_none.png'/>'" 
                                        />
                                    </div>
                                    <div class="file">
                                        <input type="file" id="file" name="file" style="width: 300px" />
                                        <p>가로 70px, 세로 70px 이상으로 권장합니다. (jpg, png 파일 가능)</p>
                                    </div>
                                </div>    
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">URL</th>
                            <td class="txt_l">
                                <input type="text" id="appDlUrl" name="appDlUrl" value="${resultDetail.appDlUrl}" />
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="3">계약<br>관련 정보</th>
                            <th scope="row" colspan="2">App. 계약<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <c:forEach items="${appContractCodeList}" var="appContractCodeList" varStatus="status">
                                            <li>
                                            <input type="radio" name="appContractCode" id="appContractCode_${appContractCodeList.system_code}" value="${appContractCodeList.system_code}"
                                                   <c:if test="${resultDetail.appContractCode eq appContractCodeList.system_code}"> checked="checked" </c:if>
                                            >
                                            <label for="appContractCode_${appContractCodeList.system_code}">${appContractCodeList.code_name_kor}</label>
                                            </li>
                                        </c:forEach>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">App. 계약 체결일</th>
                            <td class="txt_l">
                                <input type="text" id="contractDate" value="${resultDetail.appContractDate}" readonly="readonly" style="width:80px;"/>
                            </td>                  
                        </tr>
                        <tr id="appTermsDateTr">
                            <th scope="row" colspan="2">App. 계약 기간<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="dateFrom" value="${resultDetail.appTermsStartDate}" readonly="readonly" style="width:80px;"/>
                                <span class="dateDot">~</span>
                                <input type="text" id="dateTo" value="${resultDetail.appTermsExpireDate}" readonly="readonly" style="width:80px;"/>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="2">추가정보</th>
                            <th scope="row" colspan="2">등록자</th>
                            <td class="txt_l"><c:out value='${resultDetail.createIdName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="4">API 관련정보</th>
                            <td class="txt_l">
                                <table>
                                    <caption>서비스 제공자</caption>
                                    <colgroup>
                                        <col style="width:*">
                                        <col style="width:40%;">
                                        <col style="width:15%;">
                                        <col style="width:25%;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">서비스 제공자</th>
                                            <th scope="row">API 이름</th>
                                            <th scope="row">API 서비스 계약<br>여부</th>
                                            <th scope="row">API 서비스 계약<br>체결일</th>
                                        </tr>
                                        <c:choose>
                                            <c:when test="${empty resultApiList}" >
                                                <tr>
                                                   <td colspan="5" align="center">조회 된 서비스 제공자가 없습니다.</td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="resultApiList" items="${resultApiList}" varStatus="status">
                                                    <tr>
                                                        <td class="txt_l"><c:out value='${resultApiList.companyNameKor}'/></td>                  
                                                        <td class="txt_l"><c:out value='${resultApiList.apiName}'/></td>
                                                        <td class="txt_l" style="text-align: center"><c:out value='${resultApiList.apiContractCodeName}'/></td>
                                                        <td class="txt_l" style="text-align: center">
                                                            <c:out value='${resultApiList.apiTermsStartDate}'/>
                                                            <c:if test="${not empty resultApiList.apiTermsStartDate}"> ~ </c:if>
                                                            <c:out value='${resultApiList.apiTermsExpireDate}'/>
                                                        </td>
                                                    </tr>                                                    
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                                
                            </td>                  
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
                    <div class="right">
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                    </div>
                </div>
                <!-- // btn_type3 -->           

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>