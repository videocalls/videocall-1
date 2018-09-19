<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : apiManageDtl.jsp
 * @Description : api 상세/수정
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
	var url = "<c:url value='/apt/api/apiManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "05001";
    
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
    $("input[name=apiContractCode]").change(function(){
    	fn_setApiTermsDate();
    });
    
	//제공자 셋팅
	$('input[name=companyCodeId]:checked').focus();
	
	//API 서비스 계약 여부에 따라 tr 보여주기
	fn_setApiTermsDate();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- API 서비스 계약 여부에 따라 tr 보여주기 --%>
function fn_setApiTermsDate(){
    var apiContractCode = $('input[name=apiContractCode]:checked').val();
    
    //필요없음, 미계약
    if(apiContractCode == "G023002" || apiContractCode == "G023006"){
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
	util_moveRequest("ApiManageVO", "<c:url value='/apt/api/apiManageList.do'/>");
}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
    gfn_setLoading(true, "저장중 입니다.");
	
	var moveUrl = "<c:url value='/apt/api/saveApiManage.ajax'/>";
    var param = $("#ApiManageVO").serialize();
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
	
	if(data.result <= 0){
        alert("<spring:message code='fail.alert.save' />");
    }else{
        alert("<spring:message code='success.alert.save' />");
    }
	return;
}

<%-- 저장전 체크 --%>
function fn_validate(){
	
	//서비스 제공자
	if(util_chkReturn($.trim($('input[name=companyCodeId]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='서비스 제공자'/>");
        $('input[name=companyCodeId]').focus();
        return false;
    }
	
	//api 구분
	if(util_chkReturn($.trim($('input[name=apiCategory]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='API 구분'/>");
        $('input[name=apiCategory]').first().focus();
        return false;
    }
	
	//API 타이틀
    if(util_chkReturn($.trim($('#apiTitle').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='API 타이틀'/>");
        $('#apiTitle').focus();
        return false;
    }else{
        if(util_calcBytes($.trim($('#apiTitle').val())) > 64){
            alert("<spring:message code='errors.maxlength' arguments='API 타이틀,64'/>");
            $('#apiTitle').focus();
            return false;
        }
    }
	
	//API 설명
	if(util_chkReturn($.trim($('#apiDescription').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='API 설명'/>");
        $('#apiDescription').focus();
        return false;
    }
	
	//활성화 여부
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
    
    //계좌 사용 여부
    if(util_chkReturn($.trim($('input[name=apiAccountYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='계좌 사용 여부'/>");
        $('#apiAccountYn_N').focus();
        return false;
    }
    
    //사용자 확인여부(CI)
    if(util_chkReturn($.trim($('input[name=apiUserConfirmYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='사용자 확인여부(CI)'/>");
        $('#apiUserConfirmYn_N').focus();
        return false;
    }
    	
	//API 서비스 계약 여부
    if(util_chkReturn($.trim($('input[name=apiContractCode]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='API 서비스 계약'/>");
        $('input[name=apiContractCode]').first().focus();
        return false;
    }
	
	//API 서비스 계약 체결일
    if(util_chkReturn($.trim($('#contractDate').val()), "s") != "") {
    	var apiContractDate = util_replaceAll($("#contractDate").val(), "-", "");
    	if(!util_isDate(apiContractDate)){
            alert("<spring:message code='errors.invalid' arguments='API 서비스 계약 체결일' />");
            $("#contractDate").focus();
            return false;
        }
    	
    	$("#apiContractDate").val(apiContractDate);
    }
	
    	
    //API 서비스 계약 기간
    var dateFrom = util_replaceAll($("#dateFrom").val(), "-", "");
    var dateTo = util_replaceAll($("#dateTo").val(), "-", "");
    
    //API 서비스 계약 여부가 미계약 또는 필요없음 일때
    if(
    	util_chkReturn($.trim($('input[name=apiContractCode]:checked').val()), "s") == "G023002" ||
    	util_chkReturn($.trim($('input[name=apiContractCode]:checked').val()), "s") == "G023006"
    ){
    	dateFrom = "<spring:message code='Globals.format.dateFrom' />";
    	dateTo = "<spring:message code='Globals.format.dateTo' />";
    }else{
	    if(dateFrom == ""){
	        alert("<spring:message code='errors.required' arguments='API 서비스 계약 시작일' />");
	        $("#dateFrom").focus();
	        return false;
	    }else{
		    if(!util_isDate(dateFrom)){
		        alert("<spring:message code='errors.invalid' arguments='API 서비스 계약 시작일' />");
		        $("#dateFrom").focus();
		        return false;
		    }
	    }
	    
	    if(dateTo == ""){
	        alert("<spring:message code='errors.required' arguments='API 서비스 계약 종료일' />");
	        $("#dateTo").focus();
	        return false;
	    }else{
	        if(!util_isDate(dateTo)){
	            alert("<spring:message code='errors.invalid' arguments='API 서비스 계약 종료일' />");
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
    }	    
    
    $("#apiTermsStartDate").val(dateFrom);
    $("#apiTermsExpireDate").val(dateTo);
    	
	return true;
}

</script>

</head>

<body>
<form:form commandName="ApiManageVO" name="ApiManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="apiId" id="apiId" value="<c:out value='${paramVO.apiId}'/>" />
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchApiCategory" id="searchApiCategory" value="<c:out value='${paramVO.searchApiCategory}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchApiContractCode" id="searchApiContractCode" value="<c:out value='${paramVO.searchApiContractCode}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchCompanyCodeId" id="searchCompanyCodeId" value="<c:out value='${paramVO.searchCompanyCodeId}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchDateFrom" id="searchDateFrom" value="<c:out value='${paramVO.searchDateFrom}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchDateTo" id="searchDateTo" value="<c:out value='${paramVO.searchDateTo}'/>" /><!-- //검색조건 -->


<input type="hidden" name="apiName" id="apiName" value="<c:out value='${resultDetail.apiName}'/>" />
<input type="hidden" name="apiContractDate" id="apiContractDate" />
<input type="hidden" name="apiTermsStartDate" id="apiTermsStartDate" />
<input type="hidden" name="apiTermsExpireDate" id="apiTermsExpireDate" />

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
                    <h2>API 세부 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                
                <div class="tb_write1">
                    <table>
                        <caption>API 세부 정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:5%;">
                            <col style="width:15%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row" rowspan="3">API 등록 정보</th>
                            <th scope="row" colspan="2">API 이름</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">API ID</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiId}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">Routing URI</th>
                            <td class="txt_l"><c:out value='${resultDetail.routingUri}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" rowspan="13">API 추가 정보</th>
                            <th scope="row" rowspan="6">포털<br>표시 정보</th>
                            <th scope="row">서비스 제공자<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul class="on">
                                        <c:forEach items="${companyCodeList}" var="companyCodeList" varStatus="status">
                                            <li>
                                            <input type="radio" name="companyCodeId" id="companyCodeId_${companyCodeList.companyProfileRegNo}" value="${companyCodeList.companyCodeId}"
                                                   <c:if test="${resultDetail.companyCodeId eq companyCodeList.companyCodeId}"> checked="checked" </c:if>
                                            >
                                            <label for="companyCodeId_${companyCodeList.companyProfileRegNo}">${companyCodeList.companyNameKorAlias}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more on">닫기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 구분<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                                            <li>
                                            <input type="radio" name="apiCategory" id="apiCategory_${apiCategoryList.system_code}" value="${apiCategoryList.system_code}"
                                                   <c:if test="${resultDetail.apiCategory eq apiCategoryList.system_code}"> checked="checked" </c:if>
                                            >
                                            <label for="apiCategory_${apiCategoryList.system_code}">${apiCategoryList.code_name_kor}</label>
                                            </li>
                                        </c:forEach>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 타이틀<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="apiTitle" name="apiTitle" value="${resultDetail.apiTitle}" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 설명<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <textarea id="apiDescription" name="apiDescription" >${resultDetail.apiDescription}</textarea>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">활성화 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="exposureYn_Y" name="exposureYn" value="Y"
                                    <c:if test="${resultDetail.exposureYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="exposureYn_Y"> 활성 </label>
                                <input type="radio" id="exposureYn_N" name="exposureYn" value="N"
                                    <c:if test="${resultDetail.exposureYn eq 'N' || empty resultDetail.exposureYn}"> checked="checked" </c:if>
                                > <label for="exposureYn_N"> 미활성 </label>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">활성화 순서<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100 txt_r" id="exposureOrder" name="exposureOrder" value="${resultDetail.exposureOrder}" maxlength="3" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row" rowspan="7">계약<br>관련 정보</th>
                            <th scope="row">계좌 사용여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <input type="radio" id="apiAccountYn_Y" name="apiAccountYn" value="Y"
		                                    <c:if test="${resultDetail.apiAccountYn eq 'Y' || empty resultDetail.apiAccountYn}"> checked="checked" </c:if>
		                                > <label for="apiAccountYn_Y"> 사용 </label>
		                                <input type="radio" id="apiAccountYn_N" name="apiAccountYn" value="N"
		                                    <c:if test="${resultDetail.apiAccountYn eq 'N'}"> checked="checked" </c:if>
		                                > <label for="apiAccountYn_N"> 미사용 </label>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">사용자 확인여부(CI)<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <input type="radio" id="apiUserConfirmYn_Y" name="apiUserConfirmYn" value="Y"
                                            <c:if test="${resultDetail.apiUserConfirmYn eq 'Y'}"> checked="checked" </c:if>
                                        > <label for="apiUserConfirmYn_Y"> 사용 </label>
                                        <input type="radio" id="apiUserConfirmYn_N" name="apiUserConfirmYn" value="N"
                                            <c:if test="${resultDetail.apiUserConfirmYn eq 'N' || empty resultDetail.apiUserConfirmYn}"> checked="checked" </c:if>
                                        > <label for="apiUserConfirmYn_N"> 미사용 </label>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 서비스 계약 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <ul class="wrap_input type_2">
                                        <c:forEach items="${apiContractCodeList}" var="apiContractCodeList" varStatus="status">
                                            <li>
                                            <input type="radio" name="apiContractCode" id="apiContractCode_${apiContractCodeList.system_code}" value="${apiContractCodeList.system_code}"
                                                   <c:if test="${resultDetail.apiContractCode eq apiContractCodeList.system_code}"> checked="checked" </c:if>
                                                   <c:if test="${empty resultDetail.apiContractCode && apiContractCodeList.system_code eq 'G023006' }">
                                                       checked="checked"
                                                   </c:if>
                                            >
                                            <label for="apiContractCode_${apiContractCodeList.system_code}">${apiContractCodeList.code_name_kor}</label>
                                            </li>
                                        </c:forEach>
                                    </ul> 
                                </div>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 서비스 계약 체결일</th>
                            <td class="txt_l">
                                <input type="text" id="contractDate" value="${resultDetail.apiContractDate}" readonly="readonly" style="width:80px;"/>
                            </td>                  
                        </tr>
                        <tr id="apiTermsDateTr">
                            <th scope="row">API 서비스 계약 기간<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="dateFrom" value="${resultDetail.apiTermsStartDate}" readonly="readonly" style="width:80px;"/>
                                <span class="dateDot">~</span>
                                <input type="text" id="dateTo" value="${resultDetail.apiTermsExpireDate}" readonly="readonly" style="width:80px;"/>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">추가 정보 등록자</th>
                            <td class="txt_l"><c:out value='${resultDetail.createIdName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">추가 정보 등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>                  
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