<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : statsTrafficList.jsp
 * @Description : Traffic 통계
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.07.10  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.07.10
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.filter.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.sort.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.selection.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsresize.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsreorder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.pager.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxpanel.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcalendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdatetimeinput.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcheckbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/globalization/globalize.js'/>"></script>
<%-- //jqwidgets --%>

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#searchDate").datepicker({
	    showOn: "button",
	    dateFormat: 'yy-mm-dd',
	    buttonImage: "<c:url value='/images/apt/calendar.png'/>",
	    buttonImageOnly: true,
	    buttonText: "달력보기",
//	    minDate: '-3y',
//	    maxDate: '+1y',
	    currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = [
    "searchPubCompanyCodeId",
    "searchApiCategory",
    "searchApiName",
    "searchApiService",
    "searchSubCompanyCodeId",
    "searchAppName",
    "searchResponseResultYn",
    "searchApiPlanName"
];

var g_gridColumns = null;
var g_gridIdx = 0;

var g_gridColumnsCnt = [
    { text: '서비스 제공자', datafield: 'pubcompanyName', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
    { text: 'API 서비스 구분', datafield: 'apiCategoryName', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
    { text: 'API 서비스 이름', datafield: 'apiName', width: '200px', cellsalign: 'left', align: 'center', pinned: true },
    { text: '단위  API 종류', datafield: 'apiService', width: '150px', cellsalign: 'left', align: 'center' },
    { text: '앱 개발자', datafield: 'subcompanyName', width: '150px', cellsalign: 'left', align: 'center' },
    { text: '앱 이름', datafield: 'appName', width: '150px', cellsalign: 'left', align: 'center' },
    { text: 'Plan 종류', datafield: 'apiPlanName', width: '80px', cellsalign: 'center', align: 'center' },
    { text: '총 트래픽', datafield: 'cntApiDuration', width: '80px', cellsalign: 'right', align: 'center' },
    { text: '성공/실패', datafield: 'responseResultYn', width: '80px', cellsalign: 'center', align: 'center' }
];
var g_gridColumnsDuration = [
    { text: '서비스 제공자', datafield: 'pubcompanyName', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
    { text: 'API 서비스 구분', datafield: 'apiCategoryName', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
    { text: 'API 서비스 이름', datafield: 'apiName', width: '200px', cellsalign: 'left', align: 'center', pinned: true },
    { text: '단위  API 종류', datafield: 'apiService', width: '150px', cellsalign: 'left', align: 'center' },
    { text: '앱 개발자', datafield: 'subcompanyName', width: '150px', cellsalign: 'left', align: 'center' },
    { text: '앱 이름', datafield: 'appName', width: '150px', cellsalign: 'left', align: 'center' },
    { text: 'Plan 종류', datafield: 'apiPlanName', width: '80px', cellsalign: 'center', align: 'center' },
    { text: '총 트래픽', datafield: 'cntApiDuration', width: '80px', cellsalign: 'right', align: 'center' },
    { text: '성공/실패', datafield: 'responseResultYn', width: '80px', cellsalign: 'center', align: 'center' },
    { text: '트래픽 종류', datafield: 'durationName', width: '80px', cellsalign: 'center', align: 'center' }
];

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/stats/statsTrafficList.do'/>";
    var param = new Object();
    param.paramMenuId = "07001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
        
	//검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //초기화
    $("#btnSearchClear").click(function(){
        util_moveRequest("StatsTrafficVO", "<c:url value='/apt/stats/statsTrafficList.do'/>", "_top");
    });
    
    //엑셀
    $("#btnExcel").click(function(){
    	fn_searchExcel('excel');
    });
       
    //API 서비스 제공자 선택
    $("input[name=searchPubCompanyCodeId]").click(function(){
    	setTimeout(function(){
    		fn_changePubCompanyList();
    	}, 100);  //0.1초후
    });
    
    //API 서비스 구분 선택
    $("input[name=searchApiCategory]").click(function(){
    	setTimeout(function(){
    	    fn_changeApiCategoryList();
    	}, 100);  //0.1초후
    });
    
    //API 서비스 이름 선택
    $("input[name=searchApiName]").click(function(){
    	setTimeout(function(){
    	    fn_changeApiNameList();
    	}, 100);  //0.1초후
    });
    
    //앱 개발자 선택
    $("input[name=searchSubCompanyCodeId]").click(function(){
    	setTimeout(function(){
    	    fn_changeSubCompanyList();
    	}, 100);  //0.1초후
    });
    
    //구분 선택
    $("input[name=searchTrafficType]").change(function(){
        if($(this).val() == "min" || $(this).val() == "hourly"){
            $(".searchTime").show();
        }else{
            $(".searchTime").hide();
        }
        //$("#searchTime").val("");
        
        //세부 API 서비스 재 조회
        fn_changeApiNameList();
    });
    
    //톨계기준 선택
    $("input[name=searchStatsType]").change(function(){
        if($(this).val() == "cnt"){
        	g_gridColumns = g_gridColumnsCnt;
        }else{
        	g_gridColumns = g_gridColumnsDuration;
        }
    });
    
        
    //그리드 셋팅
    g_gridColumns = g_gridColumnsCnt;
    
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: g_gridColumns
    });
    
        
    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }
    
    //현재 일자 셋팅
    $("#searchDate").val(util_setFmDate(util_getDate()));
    
    //현재 시간 셋팅
    var dtServerDate = util_getServerTime();
    $("#searchTime").val(dtServerDate.getHours());
});

/*******************************************
 * 기능 함수 
 *******************************************/

<%-- 검색 --%>
function fn_search(){
	if(!fn_validate()){
        return;
    }
	
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
    	gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
    
    //로딩 호출
    gfn_setLoading(true);
                
    //page setting  
    var url = "<c:url value='/apt/stats/selectStatsTrafficList.ajax'/>";
    var param = $("#StatsTrafficVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
	var resultHeader = data.resultHeader;
    var resultList = data.resultList;
    var resultListTotalCount = resultList.length;
    $("#totCnt").text(util_setCommas(resultListTotalCount));
    
    //기존 grid 삭제/ redrow
    $("#jqxgrid").remove();
    $("#jqxgridData").html("<div id='jqxgrid' class='tb_list1'></div>");
        
    var gridColumns = g_gridColumns.slice();
    if(util_chkReturn(resultHeader, "s") != ""){
        $.each(resultHeader, function(idx,data){
        	var colIdx = gridColumns.length;
        	gridColumns[colIdx] = { 
        	    text: data.headerName, datafield: data.headerId, width: '100px', cellsalign: 'right', align: 'center'
        	};
        });
    }
       
    //그리드 redrow
    gfn_gridOption("jqxgrid",{    
        columns: gridColumns
    });
    
    //row 선택
    $('#jqxgrid').on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    //grid common
    gfn_gridData(resultList);
    
}

<%-- validate --%>
function fn_validate(){
    //조회일 체크
    var searchDate = $.trim(util_replaceAll($("#searchDate").val(), "-", ""));
    var searchTime = $.trim($('#searchTime').val());
    var searchDateTime = "";
    
    //조회일
    if(util_chkReturn(searchDate, "s") == ""){
    	alert("<spring:message code='errors.required' arguments='조회일'/>");
        $('#searchDate').focus();
        return false;  
    }else{
    	if(!util_isDate(searchDate)){
            alert("<spring:message code='errors.invalid' arguments='조회일' />");
            $("#searchDate").focus();
            return false;
        }
    }
    
    //조회 시간
    if(
    	util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "min" ||
    	util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "hourly"
    ){
    	if(util_chkReturn(searchTime, "s") == "") {
    		alert("<spring:message code='errors.required' arguments='조회시간'/>");
            $('#searchTime').focus();
            return false;
    	}else{
    		if(!util_isNum(searchTime)){
                alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                $('#searchTime').focus();
                return false;           
            }else{
            	if(Number(searchTime) <= -1 || Number(searchTime) > 24){
            		alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                    $('#searchTime').focus();
                    return false;
            	}
            }
    	}
    	
    	//조회일시 셋팅
    	searchDateTime = searchDate;
    	if(Number(searchTime) < 10){
    		searchTime = "0" + Number(searchTime); 
        }
    	searchDateTime += searchTime;
    }else{
    	//조회일시 셋팅
    	searchDateTime = searchDate;
    	
    }
    $("#searchDateTime").val(searchDateTime);
        
    return true;
    
}

<%-- 엑셀다운 --%>
function fn_searchExcel(excelType){
	if(!fn_validate()){
        return;
    }
    
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
    
    //로딩 호출
    gfn_setLoading(true, "엑셀 조회중 입니다.");
    setTimeout(function(){
    	gfn_setLoading(false);
    }, 1000);  //0.1초후
    
        
    util_moveRequest("StatsTrafficVO", "<c:url value='/apt/stats/statsTrafficListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

<%-- API 서비스 제공자 선택 --%>
function fn_changePubCompanyList(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
    gfn_setLoading(true, "검색조건 처리 중입니다.");
	
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
    
    //page setting  
    var url = "<c:url value='/apt/stats/changePubCompanyList.ajax'/>";
    var param = $("#StatsTrafficVO").serialize();
    var callBackFunc = "fn_changePubCompanyListCallBack";
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_changePubCompanyListCallBack(data){
	//로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);
    
    var apiCategoryList = data.apiCategoryList;
    var apiNameList = data.apiNameList;
    var apiServiceList = data.apiServiceList;
    
    fn_makeSearchApiName(apiNameList);
    fn_makeSearchApiService(apiServiceList);
}

<%-- API 서비스 구분 선택 --%>
function fn_changeApiCategoryList(){
	if(!fn_validate()){
        return;
    }
	
    //로딩 호출
    gfn_setLoading(true, "검색조건 처리 중입니다.");
    
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
                
    //page setting  
    var url = "<c:url value='/apt/stats/changeApiCategoryList.ajax'/>";
    var param = $("#StatsTrafficVO").serialize();
    var callBackFunc = "fn_changeApiCategoryListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_changeApiCategoryListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);
    
    var apiNameList = data.apiNameList;
    var apiServiceList = data.apiServiceList;
    
    fn_makeSearchApiName(apiNameList);
    fn_makeSearchApiService(apiServiceList);
}

<%-- API 서비스 이름 선택 --%>
function fn_changeApiNameList(){
	if(!fn_validate()){
        return;
    }
	
    //로딩 호출
    gfn_setLoading(true, "검색조건 처리 중입니다.");
    
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
                
    //page setting  
    var url = "<c:url value='/apt/stats/changeApiNameList.ajax'/>";
    var param = $("#StatsTrafficVO").serialize();
    var callBackFunc = "fn_changeApiNameListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_changeApiNameListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);
    
    var apiServiceList = data.apiServiceList;
    fn_makeSearchApiService(apiServiceList);
}

<%-- API 서비스 이름 make --%>
function fn_makeSearchApiName(list){
    var html = "";
    $("#searchApiNameData >").remove();
    
    html += "<li>";
    html += "<input type='checkbox' name='searchApiName' id='searchApiName_all' value='all' checked='checked'>";
    html += "<label for='searchApiName_all'>전체</label>";
    html += "</li>";
    
    if(util_chkReturn(list, "s") != ""){
        $.each(list, function(idx, data){
            html += "<li>";
            html += "<input type='checkbox' name='searchApiName' id='searchApiName_"+data.apiId+"' value='"+data.apiId+"'>";
            html += "<label for='searchApiName_"+data.apiId+"'>"+data.apiName+"</label>";
            html += "</li>";
        });
    }        
    $("#searchApiNameData").append(html);
    
    //event 셋팅
    gfn_initCheckbox("searchApiName");
    
    //API 서비스 이름 선택
    $("input[name=searchApiName]").click(function(){
        fn_changeApiNameList();
    });
}

<%-- 세부 API 서비스 make --%>
function fn_makeSearchApiService(list){
    var html = "";
    $("#searchApiServiceData >").remove();
    
    html += "<li>";
    html += "<input type='checkbox' name='searchApiService' id='searchApiService_all' value='all' checked='checked'>";
    html += "<label for='searchApiService_all'>전체</label>";
    html += "</li>";
    
    if(util_chkReturn(list, "s") != ""){
        $.each(list, function(idx, data){
            html += "<li>";
            html += "<input type='checkbox' name='searchApiService' id='searchApiService_"+data.apiResource+"' value='"+data.apiService+"'>";
            html += "<label for='searchApiService_"+data.apiResource+"'>"+data.apiService+"</label>";
            html += "</li>";
        });
    }        
    $("#searchApiServiceData").append(html);
    
    //event 셋팅
    gfn_initCheckbox("searchApiService");
}

<%-- 앱 개발자 선택 --%>
function fn_changeSubCompanyList(){
	if(!fn_validate()){
        return;
    }
	
    //로딩 호출
    gfn_setLoading(true, "검색조건 처리 중입니다.");
    
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
                
    //page setting  
    var url = "<c:url value='/apt/stats/changeSubCompanyList.ajax'/>";
    var param = $("#StatsTrafficVO").serialize();
    var callBackFunc = "fn_changeSubCompanyListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_changeSubCompanyListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);
    
    var appNameList = data.appNameList;
    fn_makeSearchAppName(appNameList);
}

<%-- 앱 이름 make --%>
function fn_makeSearchAppName(list){
    var html = "";
    $("#searchAppNameData >").remove();
    
    html += "<li>";
    html += "<input type='checkbox' name='searchAppName' id='searchAppName_all' value='all' checked='checked'>";
    html += "<label for='searchAppName_all'>전체</label>";
    html += "</li>";
    
    if(util_chkReturn(list, "s") != ""){
        $.each(list, function(idx, data){
            html += "<li>";
            html += "<input type='checkbox' name='searchAppName' id='searchAppName_"+data.appId+"' value='"+data.appId+"'>";
            html += "<label for='searchAppName_"+data.appId+"'>"+data.appName+"</label>";
            html += "</li>";
        });
    }        
    $("#searchAppNameData").append(html);
    
    //event 셋팅
    gfn_initCheckbox("searchAppName");
}


</script>

</head>

<body>
<form:form commandName="StatsTrafficVO" name="StatsTrafficVO" method="post">
<input type="hidden" name="searchPubCompanyCodeIdAllYn" id="searchPubCompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchApiCategoryAllYn" id="searchApiCategoryAllYn" value="N" />
<input type="hidden" name="searchApiNameAllYn" id="searchApiNameAllYn" value="N" />
<input type="hidden" name="searchApiServiceAllYn" id="searchApiServiceAllYn" value="N" />
<input type="hidden" name="searchSubCompanyCodeIdAllYn" id="searchSubCompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchAppNameAllYn" id="searchAppNameAllYn" value="N" />
<input type="hidden" name="searchResponseResultYnAllYn" id="searchResponseResultYnAllYn" value="N" />
<input type="hidden" name="searchApiPlanNameAllYn" id="searchApiPlanNameAllYn" value="N" />

<input type="hidden" name="searchDateTime" id="searchDateTime" value="" />

<%-- 엑셀 --%>
<input type="hidden" name="excelType" id="excelType" />
<%-- 엑셀 --%>
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
                    <h2>트레픽 통계</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>키워드, 노출종류, 조회일</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                                                
                        <tr>
                            <th scope="row"><label for="">서비스 제공자</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
	                                        <input type="checkbox" name="searchPubCompanyCodeId" id="searchPubCompanyCodeId_all" value="all" checked="checked">
	                                        <label for="searchPubCompanyCodeId_all">전체</label>
                                        </li>
                                        <c:forEach items="${pubCompanyList}" var="pubCompanyList" varStatus="status">
                                            <li><input type="radio" name="searchPubCompanyCodeId" id="searchPubCompanyCodeId_${pubCompanyList.companyProfileRegNo}" value="${pubCompanyList.companyCodeId}">
                                            <label for="searchPubCompanyCodeId_${pubCompanyList.companyProfileRegNo}">${pubCompanyList.companyNameKorAlias}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">API 서비스 구분</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul id="searchApiCategoryData">
                                        <li>
                                            <input type="checkbox" name="searchApiCategory" id="searchApiCategory_all" value="all" checked="checked">
                                            <label for="searchApiCategory_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                                            <li><input type="checkbox" name="searchApiCategory" id="searchApiCategory_${apiCategoryList.system_code}" value="${apiCategoryList.system_code}">
                                            <label for="searchApiCategory_${apiCategoryList.system_code}">${apiCategoryList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">API 서비스 이름</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul id="searchApiNameData">
                                        <li>
                                            <input type="checkbox" name="searchApiName" id="searchApiName_all" value="all" checked="checked">
                                            <label for="searchApiName_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiNameList}" var="apiNameList" varStatus="status">
                                            <li><input type="checkbox" name="searchApiName" id="searchApiName_${apiNameList.apiId}" value="${apiNameList.apiId}">
                                            <label for="searchApiName_${apiNameList.apiId}">${apiNameList.apiName}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">세부 API 서비스</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul id="searchApiServiceData">
                                        <li>
                                            <input type="checkbox" name="searchApiService" id="searchApiService_all" value="all" checked="checked">
                                            <label for="searchApiService_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiServiceList}" var="apiServiceList" varStatus="status">
                                            <li><input type="checkbox" name="searchApiService" id="searchApiService_${apiServiceList.apiResource}" value="${apiServiceList.apiService}">
                                            <label for="searchApiService_${apiServiceList.apiResource}">${apiServiceList.apiService}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">앱 개발자</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchSubCompanyCodeId" id="searchSubCompanyCodeId_all" value="all" checked="checked">
                                            <label for="searchSubCompanyCodeId_all">전체</label>
                                        </li>
                                        <c:forEach items="${subCompanyList}" var="subCompanyList" varStatus="status">
                                            <li><input type="radio" name="searchSubCompanyCodeId" id="searchSubCompanyCodeId_${subCompanyList.companyProfileRegNo}" value="${subCompanyList.companyCodeId}">
                                            <label for="searchSubCompanyCodeId_${subCompanyList.companyProfileRegNo}">${subCompanyList.companyNameKorAlias}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">앱 이름</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul id="searchAppNameData">
                                        <li>
                                            <input type="checkbox" name="searchAppName" id="searchAppName_all" value="all" checked="checked">
                                            <label for="searchAppName_all">전체</label>
                                        </li>
                                        <c:forEach items="${appNameList}" var="appNameList" varStatus="status">
                                            <li><input type="checkbox" name="searchAppName" id="searchAppName_${appNameList.appId}" value="${appNameList.appId}">
                                            <label for="searchAppName_${appNameList.appId}">${appNameList.appName}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">성공여부</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchResponseResultYn" id="searchResponseResultYn_all" value="all" checked="checked">
                                            <label for="searchResponseResultYn_all">전체</label>
                                        </li>
                                        
                                        <li>
                                            <input type="checkbox" name="searchResponseResultYn" id="searchResponseResultYn_Y" value="Y">
                                            <label for="searchResponseResultYn_Y">성공</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="searchResponseResultYn" id="searchResponseResultYn_N" value="N">
                                            <label for="searchResponseResultYn_N">실패</label>
                                        </li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                                               
                        <tr>
                            <th scope="row"><label for="">Plan 종류</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchApiPlanName" id="searchApiPlanName_all" value="all" checked="checked">
                                            <label for="searchApiPlanName_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiPlanNameList}" var="apiPlanNameList" varStatus="status">
                                            <li><input type="checkbox" name="searchApiPlanName" id="searchApiPlanName_${apiPlanNameList.apiPlanId}" value="${apiPlanNameList.apiPlanId}">
                                            <label for="searchApiPlanName_${apiPlanNameList.apiPlanId}">${apiPlanNameList.apiPlanName}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">구분</label></th>
                            <td class="txt_l">
                                <input type="radio" id="searchTrafficTypeMin" name="searchTrafficType" value="min" checked="checked" /><label for="searchTrafficTypeMin"> 5분 </label>
                                <input type="radio" id="searchTrafficTypeHourly" name="searchTrafficType" value="hourly" /><label for="searchTrafficTypeHourly"> 1시간 </label>
                                <input type="radio" id="searchTrafficTypeDaily" name="searchTrafficType" value="daily" /><label for="searchTrafficTypeDaily"> 1일 </label>
                                <input type="radio" id="searchTrafficTypeMonthly" name="searchTrafficType" value="monthly" /><label for="searchTrafficTypeMonthly"> 1개월 </label>
                            </td>
                            <th scope="row"><label for="">통계기준</label></th>
                            <td class="txt_l">
                                <input type="radio" id="searchStatsTypeCnt" name="searchStatsType" value="cnt" checked="checked" /><label for="searchStatsTypeCnt"> 트레픽 수 </label>
                                <input type="radio" id="searchStatsTypeDuration" name="searchStatsType" value="duration" /><label for="searchStatsTypeDuration"> 응답시간 </label>
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="searchDateFrom">조회일/시</label></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="searchDate" name="searchDate" style="width:80px;" />
                                &nbsp;
                                <input type="text" id="searchTime" name="searchTime" maxlength="2" style="width:20px;" class="searchTime" />
                                <a href="javascript:fn_changeApiNameList();"><img src="<c:url value='/images/apt/btn_reload.png'/>" alt="세부 API서비스 reload" /></a>
                                <span class="info_msg searchTime">※ 조회시간 ex) 00~23</span>                              
                            </td>                           
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnSearch">검색</a></span>
                    <span class="btn_gray2"><a href="javascript:void(0);" id="btnSearchClear">초기화</a></span>
                </div>
                <!-- // btn_type3 -->
                
                <div class="info_box mt10">
                    <div class="tit"><p class="icon_tip">Notice</p></div>
                    <div class="txt">
                        <ul class="list_type01">
                            <li><strong>5 분</strong> : 최대 2 시간 (조회일/시 기준 - 2시간 ~ 조회일/시 기준)</li>
                            <li><strong>1 시간</strong> : 최대 2 일 (조회일/시 기준 - 47시간 ~ 조회일/시 기준)</li>
                            <li><strong>1 일</strong> : 최대 2개월 (조회일 기준 - 60일 ~ 조회일 기준)</li>
                            <li><strong>1 개월</strong> : 최대 2년 (조회월 기준 - 23개월 ~ 조회월 기준)</li>
                        </ul>
                    </div>
                </div>
                
                <%-- rowcount 공통 --%>
                <p class="amount">총 <em id="totCnt">0</em> 건</p>
                
                <%-- grid --%>
                <div id="jqxgridData">
                    <div id="jqxgrid" class="tb_list1"></div>
                </div>
                <!-- // tb_list1 -->
                <div class="pagination">
                    
                    <div class="btn_type3">
                        <div class="left">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                        </div>
                    </div>
                </div>
                <!-- // paging_area -->

                

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>
<%-- 엑셀용 frame --%>
<iframe src="" id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>