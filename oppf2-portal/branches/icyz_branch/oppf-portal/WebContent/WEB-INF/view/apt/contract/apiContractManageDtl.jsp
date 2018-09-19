<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : apiContractManageDtl.jsp
 * @Description : api 계약 상세/수정
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.03  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.03
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#dateFrom, #dateTo").datepicker({
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
    var url = "<c:url value='/apt/contract/apiContractManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "11001";
    
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
	
	//API 서비스 계약 여부 radio
    $("input[name=serviceContractStatus]").change(function(){
    	fn_setServiceTermsDate();
    });
    
	//API 서비스 계약 여부에 따라 tr 보여주기
	fn_setServiceTermsDate();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- API 서비스 계약 여부에 따라 tr 보여주기 --%>
function fn_setServiceTermsDate(){
    var serviceContractStatus = $('input[name=serviceContractStatus]:checked').val();
    
    //필요없음, 미계약
    if(serviceContractStatus == "G023002" || serviceContractStatus == "G023006"){
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
	util_moveRequest("ApiContractManageVO", "<c:url value='/apt/contract/apiContractManageList.do'/>");
}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
    gfn_setLoading(true, "저장중 입니다.");
	
	var moveUrl = "<c:url value='/apt/contract/saveApiContractManage.ajax'/>";
    var param = $("#ApiContractManageVO").serialize();
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
	
	if(data.result <= 0){
        alert("<spring:message code='fail.alert.save' />");
    }else{
        alert("<spring:message code='success.alert.save' />");
    }
	
	//로딩 호출
    gfn_setLoading(false);
	
	return;
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//apiID
    if(util_chkReturn($.trim($('#apiId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='API ID'/>\n<spring:message code='fail.alert.process'/>");
        return false;
    }
    //pubcompanyCodeId
    if(util_chkReturn($.trim($('#pubcompanyCodeId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='서비스 제공자'/>\n<spring:message code='fail.alert.process'/>");
        return false;
    }
    //pubcompanyCodeId
    if(util_chkReturn($.trim($('#pubcompanyCodeId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='앱 개발자'/>\n<spring:message code='fail.alert.process'/>");
        return false;
    }
	
	//API 서비스 계약 상태
    if(util_chkReturn($.trim($('input[name=serviceContractStatus]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='API 계약 상태'/>");
        return false;
    }
	    	
    //API 서비스 계약 기간
    var dateFrom = util_replaceAll($("#dateFrom").val(), "-", "");
    var dateTo = util_replaceAll($("#dateTo").val(), "-", "");
        
    //API 서비스 계약 여부가 미계약 또는 필요없음 일때
    if(
    	util_chkReturn($.trim($('input[name=serviceContractStatus]:checked').val()), "s") == "G023002" ||
    	util_chkReturn($.trim($('input[name=serviceContractStatus]:checked').val()), "s") == "G023006"
    ){
    	dateFrom = "<spring:message code='Globals.format.dateFrom' />";
    	dateTo = "<spring:message code='Globals.format.dateTo' />";
    }else{
	    if(dateFrom == ""){
	        alert("<spring:message code='errors.required' arguments='API 계약 시작일' />");
	        $("#dateFrom").focus();
	        return false;
	    }else{
		    if(!util_isDate(dateFrom)){
		        alert("<spring:message code='errors.invalid' arguments='API 계약 시작일' />");
		        $("#dateFrom").focus();
		        return false;
		    }
	    }
	    
	    if(dateTo == ""){
	        alert("<spring:message code='errors.required' arguments='API 계약 종료일' />");
	        $("#dateTo").focus();
	        return false;
	    }else{
	        if(!util_isDate(dateTo)){
	            alert("<spring:message code='errors.invalid' arguments='API 계약 종료일' />");
	            $("#dateTo").focus();
	            return false;
	        }else{
	            if(dateFrom > dateTo){
	                alert("API 서비스 계약 종료일이 시작일보다 클 수 없습니다.");
	                $("#dateTo").focus();
	                return false;
	            }
	        }   
	    }
	    
	    //api계약기간을 체크 하여 msg를 생성한다.
	    var msg = "";
	    
	    var apiContractCode = $("#apiContractCode").val();
	    var apiTermsStartDate = util_replaceAll($("#apiTermsStartDate").val(), "-", "");
	    var apiTermsExpireDate = util_replaceAll($("#apiTermsExpireDate").val(), "-", "");
	    
	    //계약, 재계약, 필요없을 일때에만 저장 가능
	    if(apiContractCode == "G023001" || apiContractCode == "G023005" || apiContractCode == "G023006"){
	        //필요없을 일때에는 패스
	        if(apiContractCode != "G023006"){
	            //기간 체크
	            //계약 시작 기간 체크
	            if(dateFrom < apiTermsStartDate){
	                msg = "API 계약기간 시작일은  API 계약 기간 시작일 보다 이전일을 선택 할 수 없습니다.";
	            }else{
	                if(apiTermsExpireDate < dateTo){
	                    msg = "API 계약기간 종료일은  API 계약 기간 종료일 보다 이후일을 선택 할 수 없습니다.";
	                }
	            }
	        }
	    }else{
	        msg = "API 계약 상태를 확인 해 주세요.";
	    }
	    
	    if(msg != ""){
	    	alert(msg);
	    	return false;
	    }
	    
    }
        
    $("#contractTermsStartDate").val(dateFrom);
    $("#contractTermsExpireDate").val(dateTo);
    	
	return true;
}

</script>

</head>

<body>
<form:form commandName="ApiContractManageVO" name="ApiContractManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색조건 -->

<input type="hidden" name="searchPubcompanyCodeId" id="searchPubcompanyCodeId" value="<c:out value='${paramVO.searchPubcompanyCodeId}'/>" />
<input type="hidden" name="searchSubcompanyCodeId" id="searchSubcompanyCodeId" value="<c:out value='${paramVO.searchSubcompanyCodeId}'/>" />
<input type="hidden" name="searchApiCategory" id="searchApiCategory" value="<c:out value='${paramVO.searchApiCategory}'/>" />
<input type="hidden" name="searchServiceContractStatus" id="searchServiceContractStatus" value="<c:out value='${paramVO.searchServiceContractStatus}'/>" />
<input type="hidden" name="searchDateFrom" id="searchDateFrom" value="<c:out value='${paramVO.searchDateFrom}'/>" />
<input type="hidden" name="searchDateTo" id="searchDateTo" value="<c:out value='${paramVO.searchDateTo}'/>" />

<input type="hidden" name="apiId" id="apiId" value="<c:out value='${resultDetail.apiId}'/>" />
<input type="hidden" name="pubcompanyCodeId" id="pubcompanyCodeId" value="<c:out value='${resultDetail.pubcompanyCodeId}'/>" />
<input type="hidden" name="subcompanyCodeId" id="subcompanyCodeId" value="<c:out value='${resultDetail.subcompanyCodeId}'/>" />

<input type="hidden" name="apiContractCode" id="apiContractCode" value="<c:out value='${resultDetail.apiContractCode}'/>" />
<input type="hidden" name="apiContractCodeName" id="apiContractCodeName" value="<c:out value='${resultDetail.apiContractCodeName}'/>" />
<input type="hidden" name="apiTermsStartDate" id="apiTermsStartDate" value="<c:out value='${resultDetail.apiTermsStartDate}'/>" />
<input type="hidden" name="apiTermsExpireDate" id="apiTermsExpireDate" value="<c:out value='${resultDetail.apiTermsExpireDate}'/>" />

<input type="hidden" name="contractTermsStartDate" id="contractTermsStartDate" />
<input type="hidden" name="contractTermsExpireDate" id="contractTermsExpireDate" />

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
                    <h2>계약 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                
                <div class="tb_write1">
                    <table>
                        <caption>API 세부 정보</caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">서비스 제공자</th>
                            <td class="txt_l"><c:out value='${resultDetail.pubcompanyName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">서비스 제공자 코드</th>
                            <td class="txt_l"><c:out value='${resultDetail.pubcompanyCodeId}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 구분</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiCategoryName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 이름</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 계약</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiContractCodeName}'/></td>                  
                        </tr>
                        <tr
                        <c:if test="${resultDetail.apiContractCode eq 'G023002' || resultDetail.apiContractCode eq 'G023006'}">
                            style="display: none;"
                        </c:if>
                        >
                            <th scope="row">API 계약 기간</th>
                            <td class="txt_l">
                                <fmt:parseDate value='${resultDetail.apiTermsStartDate}' var='apiTermsStartDate' pattern="yyyyMMdd"/>
                                <fmt:parseDate value='${resultDetail.apiTermsExpireDate}' var='apiTermsExpireDate' pattern="yyyyMMdd"/>
                                <fmt:formatDate value="${apiTermsStartDate}"  pattern="yyyy-MM-dd"/>
                                &nbsp;~&nbsp;
                                <fmt:formatDate value="${apiTermsExpireDate}"  pattern="yyyy-MM-dd"/>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">앱개발자</th>
                            <td class="txt_l"><c:out value='${resultDetail.subcompanyName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 계약 상태<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <c:forEach items="${serviceContractStatusList}" var="serviceContractStatusList" varStatus="status">
                                            <li>
                                            <input type="radio" name="serviceContractStatus" id="serviceContractStatus_${serviceContractStatusList.system_code}" value="${serviceContractStatusList.system_code}"
                                                   <c:if test="${resultDetail.serviceContractStatus eq serviceContractStatusList.system_code}"> checked="checked" </c:if>
                                            >
                                            <label for="serviceContractStatus_${serviceContractStatusList.system_code}">${serviceContractStatusList.code_name_kor}</label>
                                            </li>
                                        </c:forEach>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        <tr id="serviceTermsDateTr">
                            <th scope="row">API 계약 기간<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="dateFrom" value="${resultDetail.contractTermsStartDate}" readonly="readonly" style="width:80px;"/>
                                <span class="dateDot">~</span>
                                <input type="text" id="dateTo" value="${resultDetail.contractTermsExpireDate}" readonly="readonly" style="width:80px;"/>
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