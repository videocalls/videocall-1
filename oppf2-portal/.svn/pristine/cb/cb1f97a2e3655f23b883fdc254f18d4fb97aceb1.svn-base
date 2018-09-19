<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%--
/**  
 * @Name : mainStatsTraffic.jsp
 * @Description : admin main의 API 트래픽 현황
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.07.14  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.07.14
 * @version 1.0
 *
 */
--%>
<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#searchDateFrom, #searchDateTo").datepicker({
        showOn: "button",
        dateFormat: 'yy-mm-dd',
        buttonImage: "<c:url value='/images/apt/calendar.png'/>",
        buttonImageOnly: true,
        buttonText: "달력보기",
//      minDate: '-3y',
//      maxDate: '+1y',
        currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = ["searchPubCompanyCodeId", "searchSubCompanyCodeId"];
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
    //검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //초기화
    $("#btnSearchClear").click(function(){
        util_moveRequest("AptMainVO", "<c:url value='/apt/cmm/mainView.do'/>");
    });
    
    //구분 선택
    $("input[name=searchType]").change(function(){
        fn_searchTypeChange_new();
    });
    
    //구분 셋팅
    $("input[name=searchType][value="+g_searchType+"]").prop("checked", true);
    fn_searchTypeChange_new();
    
    //현재 일자 셋팅
    $("#searchDate").val(g_searchDate);
    
    //현재 시간 셋팅
    $("#searchTime").val(g_searchTime);

    <%-- 체크박스 선택 onclick이벤트 설정--%>
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }

    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate("date");
    });
    // start time 변경
    $("#searchTimeFrom").change(function() {
        setEndDate("time");
    });
    // end time 변경
    $("#searchTimeTo").change(function() {
        setEndDate("time");
    });

    //조회
    fn_search();    
});

function setEndDate(eventType) {
    var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
    var searchTimeFrom = $.trim($('#searchTimeFrom').val());
    var searchTimeTo = $.trim($('#searchTimeTo').val());

    var searchTypeValue = $("input[name=searchType]:checked").val();
    if(searchTypeValue == "min" || searchTypeValue == "hourly") {
        if(!isFromToDate(searchDateFrom, searchDateTo, pad(searchTimeFrom,2), pad(searchTimeTo,2))) {
            if(eventType === "time") {
                $('#searchTimeTo').val(searchTimeFrom);
            } else {
                $("#searchDateTo").val(util_setFmDate(searchDateFrom));
                if(Number(searchTimeTo) < Number(searchTimeFrom)) {
                    $('#searchTimeTo').val(searchTimeFrom);
                }
            }
        }
    } else if(searchTypeValue == "daily" || searchTypeValue == "monthly") {
        if(!isFromToDate(searchDateFrom, searchDateTo, "00", "00")) {
            $("#searchDateTo").val(util_setFmDate(searchDateFrom));
        }
    }
}
/*******************************************
 * 기능 함수
 *******************************************/ 
<%-- 조회 --%> 
function fn_search(){
    <%-- 체크박스 선택 전체여부 셋팅 --%>
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }

    //validation
    if(!fn_validate()){
        return;
    }

	//API 트래픽 요약 - 전체 건수
    fn_searchTraffic();
    
    //API 트래픽 요약 - 상세 건수
    fn_searchTrafficDtl();
    
} 

/**
 * API 트래픽 요약 - 상세 건수 이후 호출
 */
function fn_second_chart_call() {
    //API 서비스 별 트래픽 현황(요약)
    fn_searchApiTraffic();
    //서비스 제동자별 트래픽 현황(요약)
    fn_searchServiceTraffic();
}
/**
 *
 * 서비스 제동자별 트래픽 현황(요약) 이후 호출
 */
function fn_third_chart_call() {
    //핀테크 앱 별 트래픽 현황(요약)
    fn_searchAppTraffic();
    //사용 Plan 별 트래픽 현황(요약)
    fn_searchPlanTraffic();
}

<%-- API 트래픽 요약 - 전체 건수 조회 --%>
function fn_searchTraffic(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
    gfn_setLoading(true, null, "lineTrafficCnt");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTraffic.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchTrafficCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchTrafficCallBack(data){
	//로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false, null, "lineTrafficCnt");
    	
    var resultList = data.resultList;    
    if(util_chkReturn(resultList, "s") != ""){
    	var maxValueData = 0;
    	var unitIntervalData = 5;
        var dateUnitInterval = Math.round(resultList/30);
    	
    	$.each(resultList, function(idx,data){
            if(maxValueData < Number(data.cntApiDurationY)){
            	maxValueData = Number(data.cntApiDurationY);
            } 
            if(maxValueData < Number(data.cntApiDurationN)){
            	maxValueData = Number(data.cntApiDurationN);
            }
        });
    	
    	if(maxValueData > 100){
    		unitIntervalData = Math.round(maxValueData / 100) * 10;
    	}
    	
    	maxValueData = maxValueData + 5;
    	
	    //API 트래픽 요약 - 전체 건수
	    gfn_chart4LineLoding("lineTrafficCnt", resultList,
	        {
	            xAxis : {
	                dataField: "Date",
	                unitInterval: dateUnitInterval
	            },
	            valueAxis : {
	                minValue: 0,
	                maxValue : maxValueData,
	                unitInterval: unitIntervalData
	                //unitInterval: 10
	            },
	            seriesGroups:
	            [
	                {
	                    type: 'line',
	                    series: [
	                            { dataField: 'cntApiDurationY', displayText: '성공', color:chartColorGroup[0]},
	                            { dataField: 'cntApiDurationN', displayText: '실패', color:chartColorGroup[1]}
	                    ]
	                }
	            ]
	        }
	    );
    }else{
    	//로딩 호출
        gfn_setLoading(false, null, "lineTrafficCnt");
    }
}

<%-- API 트래픽 요약 - 상세 건수 조회 --%>
function fn_searchTrafficDtl(){
	if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "lineTrafficCntDtl");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtl.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchTrafficDtlCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchTrafficDtlCallBack(data){
    fn_second_chart_call();
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "lineTrafficCnt");
        
    var resultList = data.resultList;
    if(util_chkReturn(resultList, "s") != ""){
        var maxValueData = 0;
        var unitIntervalData = 5;
        var dateUnitInterval = Math.round(resultList/30);
        
        var seriesData = new Array();
        <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
        seriesData[seriesData.length] = { dataField: 'cntApiDuration${apiCategoryList.system_code}', displayText: '${apiCategoryList.code_name_kor}', color:chartColorGroup[seriesData.length]};
        </c:forEach>
        
        $.each(resultList, function(idx,data){
        	<c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
        	if(maxValueData < Number(eval("data.cntApiDuration${apiCategoryList.system_code}"))){
                maxValueData = Number(eval("data.cntApiDuration${apiCategoryList.system_code}"));
            }
            </c:forEach>
        });
        
        if(maxValueData > 100){
            unitIntervalData = Math.round(maxValueData / 100) * 10;
        }
        
        maxValueData = maxValueData + 5;
        
	    //API 트래픽 요약 - 상세 건수
	    gfn_chart4LineLoding("lineTrafficCntDtl", resultList, 
	        {
	            xAxis : {
	                dataField: "Date",
	                unitInterval: dateUnitInterval
	            },
	            valueAxis : {
	            	minValue: 0,
                    maxValue : maxValueData,
                    unitInterval: unitIntervalData
	            },
	            seriesGroups:
	            [
	                {
	                    type: 'line',
	                    series: seriesData
	                }
	            ]
	        }
	    );
    }else{
        //로딩 호출
        gfn_setLoading(false, null, "lineTrafficCntDtl");
    }
}

<%-- API 서비스 별 트래픽 현황(요약) 조회 --%>
function fn_searchApiTraffic(){
	if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "pieApiTraffic");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficApiTraffic.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "pieApiTraffic");
    
    var resultList = data.resultList;
	gfn_chart4PieLoding("pieApiTraffic", resultList,
		{
			seriesGroups:
			[
		    {
		        type: 'pie',
		        showLegend: true,
		        enableSeriesToggle: true,
		        series: 
		        [
	            { 
	                dataField: 'cntApiDuration', 
	                displayText: 'apiCategoryName',
	                
	                showLabels: true,
	                labelRadius: 160,
	                labelLinesEnabled: true,
	                labelLinesAngles: true,
	                labelsAutoRotate: false,
	                initialAngle: 0,
	                radius: 125,
	                minAngle: 0,
	                maxAngle: 180,
	                centerOffset: 0,
	                offsetY: 170,
	                formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
	                    if (isNaN(value))
	                        return value;
	                    return value + '%';
	                }
	            }
		        ]
		    }
			]
		}
	);
}

<%-- 서비스 제동자별 트래픽 현황(요약) --%>
function fn_searchServiceTraffic(){
	if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "pieServiceTraffic");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficServiceTraffic.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchServiceTrafficCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchServiceTrafficCallBack(data){
    fn_third_chart_call();
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "pieServiceTraffic");
    
    var resultList = data.resultList;
    gfn_chart4PieLoding("pieServiceTraffic", resultList,
        {
            seriesGroups:
            [
            {
                type: 'pie',
                showLegend: true,
                enableSeriesToggle: true,
                series: 
                [
                { 
                    dataField: 'cntApiDuration', 
                    displayText: 'companyName',
                    
                    showLabels: true,
                    labelRadius: 160,
                    labelLinesEnabled: true,
                    labelLinesAngles: true,
                    labelsAutoRotate: false,
                    initialAngle: 0,
                    radius: 125,
                    minAngle: 0,
                    maxAngle: 180,
                    centerOffset: 0,
                    offsetY: 170,
                    formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
                        if (isNaN(value))
                            return value;
                        return value + '%';
                    }
                }
                ]
            }
            ]
        }
    );
}

<%-- 핀테크 앱 별 트래픽 현황(요약) --%>
function fn_searchAppTraffic(){
	if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "pieAppTraffic");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficAppTraffic.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchAppTrafficCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchAppTrafficCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "pieAppTraffic");
    
    var resultList = data.resultList;
    gfn_chart4PieLoding("pieAppTraffic", resultList,
        {
            seriesGroups:
            [
            {
                type: 'pie',
                showLegend: true,
                enableSeriesToggle: true,
                series: 
                [
                { 
                    dataField: 'cntApiDuration', 
                    displayText: 'appName',
                    
                    showLabels: true,
                    labelRadius: 160,
                    labelLinesEnabled: true,
                    labelLinesAngles: true,
                    labelsAutoRotate: false,
                    initialAngle: 0,
                    radius: 125,
                    minAngle: 0,
                    maxAngle: 180,
                    centerOffset: 0,
                    offsetY: 170,
                    formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
                        if (isNaN(value))
                            return value;
                        return value + '%';
                    }
                }
                ]
            }
            ]
        }
    );
}

<%-- 사용 Plan 별 트래픽 현황(요약) --%>
function fn_searchPlanTraffic(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "piePlanTraffic");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficPlanTraffic.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchPlanTrafficCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchPlanTrafficCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "piePlanTraffic");
    
    var resultList = data.resultList;
    gfn_chart4PieLoding("piePlanTraffic", resultList,
        {
            seriesGroups:
            [
            {
                type: 'pie',
                showLegend: true,
                enableSeriesToggle: true,
                series: 
                [
                { 
                    dataField: 'cntApiDuration', 
                    displayText: 'planName',
                    
                    showLabels: true,
                    labelRadius: 160,
                    labelLinesEnabled: true,
                    labelLinesAngles: true,
                    labelsAutoRotate: false,
                    initialAngle: 0,
                    radius: 125,
                    minAngle: 0,
                    maxAngle: 180,
                    centerOffset: 0,
                    offsetY: 170,
                    formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
                        if (isNaN(value))
                            return value;
                        return value + '%';
                    }
                }
                ]
            }
            ]
        }
    );
}

<%-- validate --%>
function fn_validate(){

    // 조회일 체크
    var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
    var searchTimeFrom = $.trim($('#searchTimeFrom').val());
    var searchTimeTo = $.trim($('#searchTimeTo').val());
    var searchDateTimeFrom = "";
    var searchDateTimeTo = "";

    // 조회일
    if(util_chkReturn(searchDateFrom, "s") == ""){
        alert("<spring:message code='errors.required' arguments='조회일'/>");
        $('#searchDateFrom').focus();
        return false;
    } else {
        if(!util_isDate(searchDateFrom)){
            alert("<spring:message code='errors.invalid' arguments='조회일' />");
            $("#searchDateFrom").focus();
            return false;
        }
    }

    // 조회시간
    if(util_chkReturn(searchDateTo, "s") == ""){
        alert("<spring:message code='errors.required' arguments='조회일'/>");
        $('#searchDateTo').focus();
        return false;
    } else {
        if(!util_isDate(searchDateTo)){
            alert("<spring:message code='errors.invalid' arguments='조회일' />");
            $("#searchDateTo").focus();
            return false;
        }
    }

    if(util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "min"
       || util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "hourly") {

        if(util_chkReturn(searchTimeFrom, "s") == "") {
            alert("<spring:message code='errors.required' arguments='조회시간'/>");
            $('#searchTimeFrom').focus();
            return false;
        }else{
            if(!util_isNum(searchTimeFrom)){
                alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                $('#searchTimeFrom').focus();
                return false;
            }else{
                if(Number(searchTimeFrom) <= -1 || Number(searchTimeFrom) > 24){
                    alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                    $('#searchTimeFrom').focus();
                    return false;
                }
            }
        }

        if(util_chkReturn(searchTimeTo, "s") == "") {
            alert("<spring:message code='errors.required' arguments='조회시간'/>");
            $('#searchTimeTo').focus();
            return false;
        }else{
            if(!util_isNum(searchTimeTo)){
                alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                $('#searchTimeTo').focus();
                return false;
            }else{
                if(Number(searchTimeTo) <= -1 || Number(searchTimeTo) > 24){
                    alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                    $('#searchTimeTo').focus();
                    return false;
                }
            }
        }

        searchDateTimeFrom = searchDateFrom;
        if(Number(searchTimeFrom) < 10){
            searchTimeFrom = "0" + Number(searchTimeFrom);
        }
        searchDateTimeFrom += searchTimeFrom;

        searchDateTimeTo = searchDateTo;
        if(Number(searchTimeTo) < 10){
            searchTimeTo = "0" + Number(searchTimeTo);
        }
        searchDateTimeTo += searchTimeTo;

        if(util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "min") {
            // 최대 48시간 체크
            if(2 < getDateTime(searchDateTimeFrom, searchDateTimeTo)) {
                alert("<spring:message code='errors.period' arguments="5분,48시간"/>");
                return false;
            }
        } else if(util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "hourly") {
            // 최대 14일 체크
            if(14 < getDateTime(searchDateTimeFrom, searchDateTimeTo)) {
                alert("<spring:message code='errors.period' arguments="시간,14일"/>");
                return false;
            }
        }
    } else {
        if(util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "daily") {
            // 최대 6개월 체크
            var checkFromDate = util_addDate(searchDateTo, "m", -6);
            if(checkFromDate > searchDateFrom) {
                alert("<spring:message code='errors.period' arguments="일,6개월"/>");
                return false;
            }
        } else {
            // 최대 10년 체크
            var checkFromDate = util_addDate(searchDateTo, "y", -10);
            if(checkFromDate > searchDateFrom) {
                alert("<spring:message code='errors.period' arguments="월,10년"/>");
                return false;
            }
            searchDateFrom = util_subStrL(searchDateFrom, 6, "");
            searchDateTo = util_subStrL(searchDateTo, 6, "");
        }

        //조회일시 셋팅
        searchDateTimeFrom = searchDateFrom;
        searchDateTimeTo = searchDateTo;
    }
    $("#searchDateTimeFrom").val(searchDateTimeFrom);
    $("#searchDateTimeTo").val(searchDateTimeTo);

    return true;
    
}

function getDateTime(startDateTime, endDateTime) {
    var startDate = new Date(startDateTime.substring(0, 4),startDateTime.substring(4, 6) - 1,startDateTime.substring(6, 8),startDateTime.substring(8, 10),00);
    var endDate = new Date(endDateTime.substring(0, 4),endDateTime.substring(4, 6) - 1,endDateTime.substring(6, 8),endDateTime.substring(8, 10),00);
    return (endDate.getTime() - startDate.getTime()) / (1000*60*60*24);
}

function isFromToDate(startDate, endDate, startTime, endTime) {
    var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
    var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
    return endDate.getTime() >= startDate.getTime();
}
</script>
<form:form commandName="AptMainVO" name="AptMainVO" method="post">
    <input type="hidden" name="searchDateTime" id="searchDateTime" value="" />
    <input type="hidden" name="searchDateTimeFrom" id="searchDateTimeFrom" value="" />
    <input type="hidden" name="searchDateTimeTo" id="searchDateTimeTo" value="" />
    <input type="hidden" name="searchPubCompanyCodeIdAllYn" id="searchPubCompanyCodeIdAllYn" value="N" />
    <input type="hidden" name="searchSubCompanyCodeIdAllYn" id="searchSubCompanyCodeIdAllYn" value="N" />

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
                            <li><input type="checkbox" name="searchPubCompanyCodeId" id="searchPubCompanyCodeId_${pubCompanyList.companyProfileRegNo}" value="${pubCompanyList.companyCodeId}">
                                <label for="searchPubCompanyCodeId_${pubCompanyList.companyProfileRegNo}">${pubCompanyList.companyNameKorAlias}</label></li>
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
                            <li><input type="checkbox" name="searchSubCompanyCodeId" id="searchSubCompanyCodeId_${subCompanyList.companyProfileRegNo}" value="${subCompanyList.companyCodeId}">
                                <label for="searchSubCompanyCodeId_${subCompanyList.companyProfileRegNo}">${subCompanyList.companyNameKorAlias}</label></li>
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
                <input type="radio" id="searchTypeMin" name="searchType" value="min" /><label for="searchTypeMin"> 분(5분) </label>
                <input type="radio" id="searchTypeHourly" name="searchType" value="hourly" /><label for="searchTypeHourly"> 시간 </label>
                <input type="radio" id="searchTypeDaily" name="searchType" value="daily" /><label for="searchTypeDaily"> 일 </label>
                <input type="radio" id="searchTypeMonthly" name="searchType" value="monthly" /><label for="searchTypeMonthly"> 월 </label>
            </td>
            <th scope="row"><label for="">조회가능기간</label></th>
            <td class="txt_l" id="searchTypeNotice">
                최대 6개월
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="">조회일/시</label></th>
            <td class="txt_l" colspan="3">
                <input type="text" id="searchDateFrom" name="searchDateFrom" readonly="readonly" style="width:80px;" />
                <input type="text" id="searchTimeFrom" name="searchTimeFrom" maxlength="2" style="width:20px;" class="searchTime" />
                <span class="dateDot">~</span>
                <input type="text" id="searchDateTo" name="searchDateTo" readonly="readonly" style="width:80px;" />
                <input type="text" id="searchTimeTo" name="searchTimeTo" maxlength="2" style="width:20px;" class="searchTime" />
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
</form:form>

<%--
<div class="info_box mt10">
    <div class="tit"><p class="icon_tip">Notice</p></div>
    <div class="txt">
        <ul class="list_type01">
            <li><strong>분</strong> : 최대 2 시간 (조회일/시 기준 2시간)</li>
            <li><strong>시간</strong> : 최대 2 일 (조회일/시 기준 2일)</li>
            <li><strong>일</strong> : 최대 2개월 (조회일 기준 60일)</li>
            <li><strong>개월</strong> : 최대 2년 (조회월 기준 24개월)</li>
        </ul>
    </div>
</div>
--%>
<div class="division_wrap">
	<ul>
	    <li>
	        <div>
				<h3>API 트래픽 요약 - 전체 건수</h3>
				<div id="lineTrafficCnt" class="graph_box" style="height: 400px;"></div>
			</div>
	    </li>   
	</ul>  
</div>  
<div class="division_wrap">
	<ul>
	    <li>
	        <div>
				<h3>API 트래픽 요약 - 상세 건수</h3>
				<div id="lineTrafficCntDtl" class="graph_box" style="height: 400px;"></div>
			</div>
	    </li>   
	</ul>
</div>   
<div class="division_wrap">
	<ul>
	    <li>
	       <div>
	           <h3>API 서비스 별 트래픽 현황(요약)</h3>
	           <div id="pieApiTraffic" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
	    <li>
	       <div>
	           <h3>서비스 제공자 별 트래픽 현황</h3>
	           <div id="pieServiceTraffic" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
	</ul>
</div>
<div class="division_wrap">
	<ul>
	    <li>
	       <div>
	           <h3>핀테크 앱 별 트래픽 현황(요약)</h3>
	           <div id="pieAppTraffic" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
	    <li>
	       <div>
	           <h3>사용 Plan 별 트래픽 현황(요약)</h3>
	           <div id="piePlanTraffic" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
	</ul>
</div>


