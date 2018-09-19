<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%--
/**  
 * @Name : mainStatsTrafficDtl.jsp
 * @Description : admin main의 API 트래픽 상세 현황
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
        fn_searchTypeChange_new_second();
    });
    
    //구분 셋팅
    $("input[name=searchType][value="+g_searchType+"]").prop("checked", true);
//    fn_searchTypeChange_new();
    fn_searchTypeChange_new_second();

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
    if($("#searchDateFrom").val().length < 10 || $("#searchDateTo").val().length < 10){
        var searchDate = util_getDate();
        $("#searchDateTo").val(util_setFmDate(searchDate));
        var searchTypeValue = $("input[name=searchTrafficType]:checked").val();
        fn_searchDateSet(searchTypeValue);
    }

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
    g_searchDateFrom = $("#searchDateFrom").val();
    g_searchDateTo = $("#searchDateTo").val();
    g_searchTimeFrom = $('#searchTimeFrom').val();
    g_searchTimeTo = $('#searchTimeTo').val();
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

    //API 트래픽 요약 - 요청 Count
    fn_searchApiTrafficReqCnt();
    
    //API 트래픽 요약 - 평균 Time 조회
    fn_searchApiTrafficAvgTime();
    
    //현황 title 셋팅
    $(".statsDayStd").html("( " + util_setFmDate($("#searchDate").val()) + " 기준 )");
    
    //API 트래픽 현황 - 누적
    fn_searchApiTrafficAccList();
    
    //API 트래픽 현황 - 개별(API 서비스 구분)
    fn_searchApiTrafficSvcCategoryList();
    
    //API 트래픽 현황 - 개별(API 서비스  제공자)
    fn_searchApiTrafficSvcSubcompanyList();
    
    //API 트래픽 현황 - 개별(핀테크 앱)
    fn_searchApiTrafficSvcPubcompanyList();
}

<%-- API 트래픽 요약 - 요청 Count 조회 --%>
function fn_searchApiTrafficReqCnt(){
	if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "apiTrafficReqCnt");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlReqCnt.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficReqCntCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficReqCntCallBack(data){
	//로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "apiTrafficReqCnt");
        
    var resultList = data.resultList;    
    if(util_chkReturn(resultList, "s") != ""){
        var maxValueData = 0;
        var unitIntervalData = 5;
        var dateUnitInterval = Math.round(resultList/30);
                
        $.each(resultList, function(idx,data){
        	$("input[name=searchApiCategory]:checked").each(function() {
        		var cntApiDuration = eval("data.cntApiDuration" + $(this).val());
        		        		
        		if(maxValueData < Number(cntApiDuration)){
                    maxValueData = Number(cntApiDuration);
                }	
        	});
        });
        
        if(maxValueData > 100){
            unitIntervalData = Math.round(maxValueData / 100) * 10;
        }
        
        maxValueData = maxValueData + 5;
        
        //seriesData 셋팅
        var seriesData = new Array();
        var i = 0;
        $("input[name=searchApiCategory]:checked").each(function() {
        	var cntApiDuration = "cntApiDuration" + $(this).val();
        	var cntApiDurationText = $(this).next('label').text();
        	        	
        	seriesData[seriesData.length] = { dataField: cntApiDuration, displayText: cntApiDurationText, color:chartColorGroup[i++]};
        });
        
        //API 트래픽 요약 - 전체 건수
        gfn_chart4LineLoding("apiTrafficReqCnt", resultList,
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
                        series: seriesData
                    }
                ]
            }
        );
    }else{
        //로딩 호출
        gfn_setLoading(false, null, "apiTrafficReqCnt");
    }
    
    //API 트래픽 요약 - 평균 Time 조회
    //fn_searchApiTrafficAvgTime();
}

<%-- API 트래픽 요약 - 평균 Time 조회 --%>
function fn_searchApiTrafficAvgTime(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "apiTrafficAvgTime");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlAvgTime.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficAvgTimeCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficAvgTimeCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "apiTrafficAvgTime");
        
    var resultList = data.resultList;    
    if(util_chkReturn(resultList, "s") != ""){
        var maxValueData = 0;
        var unitIntervalData = 0.05;
        var dateUnitInterval = Math.round(resultList/30);
                
        $.each(resultList, function(idx,data){
            $("input[name=searchApiCategory]:checked").each(function() {
                var avgApiDuration = eval("data.avgApiDuration" + $(this).val());
                                
                if(maxValueData < Number(avgApiDuration)){
                    maxValueData = Number(avgApiDuration);
                }   
            });
        });
        
        if(maxValueData > 100){
            unitIntervalData = Math.round(maxValueData / 100) * 10;
        }
        
        maxValueData = maxValueData + 0.05;
        
        //seriesData 셋팅
        var seriesData = new Array();
        $("input[name=searchApiCategory]:checked").each(function() {
            var avgApiDuration = "avgApiDuration" + $(this).val();
            var avgApiDurationText = $(this).next('label').text();
                        
            seriesData[seriesData.length] = { dataField: avgApiDuration, displayText: avgApiDurationText, color:chartColorGroup[seriesData.length]};
        });
        
        //API 트래픽 요약 - 전체 건수
        gfn_chart4LineLoding("apiTrafficAvgTime", resultList,
            {
                xAxis : {
                    dataField: "Date",
                    unitInterval: dateUnitInterval
                },
                valueAxis : {
                    minValue: 0.00,
                    maxValue : maxValueData,
                    unitInterval: 0.05
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
        gfn_setLoading(false, null, "apiTrafficAvgTime");
    }
}

<%-- API 트래픽 현황 - 누적 조회 --%>
function fn_searchApiTrafficAccList(){
	if(!fn_validate()){
	    return;
	}
	
	//page setting  
	var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlAccList.ajax'/>";
	var param = $("#AptMainVO").serialize();
	var callBackFunc = "fn_searchApiTrafficAccListCallBack";
	<%-- 공통 ajax 호출 --%>
	util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficAccListCallBack(data){
	//로그인 처리
	if(data.error == -1){
	    fn_login();
	    return;
	}
		
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#apiTrafficAccList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
        	
        	//총건
        	html += "<tr>";
        	
        	html += "<td>";
        	html += util_setCommas(list.cntApiDurationTotal_1) + " 건";
        	html += "</td>";
        	
        	html += "<td>";
        	html += util_setCommas(list.cntApiDurationTotal_2) + " 건";
            html += "</td>";
            
        	html += "</tr>";
        	
        	//성공/실패 건
            html += "<tr>";
            
            html += "<td>";
            html += "성공 " + util_setCommas(list.cntApiDurationY_1) + " 건 / ";
            html += "실패 " + util_setCommas(list.cntApiDurationN_1) + " 건";
            html += "</td>";
            
            html += "<td>";
            html += "성공 " + util_setCommas(list.cntApiDurationY_2) + " 건 / ";
            html += "실패 " + util_setCommas(list.cntApiDurationN_2) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
    	//총건
        html += "<tr>";
        html += "<td>- 건</td>";
        html += "<td>- 건</td>";
        html += "</tr>";
        
        //성공/실패 건
        html += "<tr>";
        html += "<td>성공 - 건/ 실패 - 건</td>";
        html += "<td>성공 - 건/ 실패 - 건</td>";
        html += "</tr>";
    }
    
    $("#apiTrafficAccList").append(html);
}

<%-- API 트래픽 현황 - 개별(API 서비스 구분) --%>
function fn_searchApiTrafficSvcCategoryList(){
    if(!fn_validate()){
        return;
    }
                    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlSvcCategoryList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficSvcCategoryListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficSvcCategoryListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
       
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#apiTrafficSvcCategoryList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<th>";
            html += list.apiCategoryName;
            html += "</th>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationAcc) + " 건";
            html += "</td>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td colspan='3'>조회된 데이터가 없습니다.</td>";
        html += "</tr>";
    }
    
    $("#apiTrafficSvcCategoryList").append(html);
}

<%-- API 트래픽 현황 - 개별(API 서비스  제공자) --%>
function fn_searchApiTrafficSvcSubcompanyList(){
    if(!fn_validate()){
        return;
    }
                    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlSvcSubcompanyList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficSvcSubcompanyListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficSvcSubcompanyListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#apiTrafficSvcSubcompanyList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<th>";
            html += list.companyName;
            html += "</th>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationAcc) + " 건";
            html += "</td>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td colspan='3'>조회된 데이터가 없습니다.</td>";
        html += "</tr>";
    }
    
    $("#apiTrafficSvcSubcompanyList").append(html);
    
}

<%-- API 트래픽 현황 - 개별(핀테크 앱) --%>
function fn_searchApiTrafficSvcPubcompanyList(){
    if(!fn_validate()){
        return;
    }
                    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsTrafficDtlSvcPubcompanyList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchApiTrafficSvcPubcompanyListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchApiTrafficSvcPubcompanyListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#apiTrafficSvcPubcompanyList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<th>";
            html += list.companyName;
            html += "</th>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationAcc) + " 건";
            html += "</td>";
            
            html += "<td>";
            html += util_setCommas(list.cntApiDurationYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td colspan='3'>조회된 데이터가 없습니다.</td>";
        html += "</tr>";
    }
    
    $("#apiTrafficSvcPubcompanyList").append(html);
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
            <th scope="row"><label for="">API 구분</label></th>
            <td class="txt_l" colspan="3">
                <!-- chk_list_wrap -->
                <div class="chk_list_wrap type2">
                    <ul>
                        <li>
                            <input type="checkbox" name="searchApiCategory" id="searchApiCategory_all" value="Total">
                            <label for="searchApiCategory_all">전체</label>
                        </li>
                        <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                            <li>
                            <input type="checkbox" name="searchApiCategory" id="searchApiCategory_${apiCategoryList.system_code}" value="${apiCategoryList.system_code}" checked="checked">
                            <label for="searchApiCategory_${apiCategoryList.system_code}">${apiCategoryList.code_name_kor}</label>
                            </li>
                        </c:forEach>
                    </ul>
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
                <input type="text" id="searchDateFrom" name="searchDateFrom" style="width:80px;" />
                <input type="text" id="searchTimeFrom" name="searchTimeFrom" maxlength="2" style="width:20px;" class="searchTime" />
                <span class="dateDot">~</span>
                <input type="text" id="searchDateTo" name="searchDateTo" style="width:80px;" />
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

<div class="division_wrap">
	<ul>
	    <li>
	        <div>
	            <h3>API 트래픽 요약 - 요청 Count</h3>
	            <div id="apiTrafficReqCnt" class="graph_box" style="height: 400px;"></div>
	        </div>
	    </li>   
	</ul>
</div>
<div class="division_wrap">	
	<ul>
	    <li>
	        <div>
	            <h3>API 트래픽 요약 - 평균 Time</h3>
	            <div id="apiTrafficAvgTime" class="graph_box" style="height: 400px;"></div>
	        </div>
	    </li>   
	</ul>
</div>	   

<h3 style="margin-top: 30px;">API 트래픽 현황 - 누적 <span class="statsDayStd"></span></h3>
<div class="tb_list1" id="apiTrafficAccListDiv">
    <table>
        <caption>API 트래픽 현황 - 누적 정보</caption>
        <colgroup>
            <col style="width:50%;">
            <col style="width:50%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">누적 트래픽</th>
            <th scope="col">전일 트래픽</th>
        </tr>
        </thead>
        <tbody id="apiTrafficAccList">
        <tr>
            <td>- 건</td>
            <td>- 건</td>
        </tr>
        <tr>
            <td>성공 - 건/ 실패 - 건</td>
            <td>성공 - 건/ 실패 - 건</td>
        </tr>
        </tbody>
    </table>
</div>

<h3 style="margin-top: 30px;">API 트래픽 현황 - 개별 <span class="statsDayStd"></span></h3>
<div class="tb_list1" id="apiTrafficSvcCategoryListDiv">
    <table>
        <caption>API 트래픽 현황 - 개별(API 서비스 구분)</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:40%;">
            <col style="width:40%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">API 서비스 구분</th>
            <th scope="col">누적 트래픽</th>
            <th scope="col">전일 트래픽</th>
        </tr>
        </thead>
        <tbody id="apiTrafficSvcCategoryList">
        <tr>
            <th>-</th>
            <td>-</td>
            <td>-</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="tb_list1" style="margin-top: 10px;" id="apiTrafficSvcSubcompanyListDiv">
    <table>
        <caption>API 트래픽 현황 - 개별(API 서비스  제공자)</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:40%;">
            <col style="width:40%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">API 서비스 제공자</th>
            <th scope="col">누적 트래픽</th>
            <th scope="col">전일 트래픽</th>
        </tr>
        </thead>
        <tbody id="apiTrafficSvcSubcompanyList">
        <tr>
            <th>-</th>
            <td>-</td>
            <td>-</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="tb_list1" style="margin-top: 10px;" id="apiTrafficSvcPubcompanyListDiv">
    <table>
        <caption>API 트래픽 현황 - 개별(핀테크 앱)</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:40%;">
            <col style="width:40%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">핀테크 앱</th>
            <th scope="col">누적 트래픽</th>
            <th scope="col">전일 트래픽</th>
        </tr>
        </thead>
        <tbody id="apiTrafficSvcPubcompanyList">
        <tr>
            <th>-</th>
            <td>-</td>
            <td>-</td>
        </tr>
        </tbody>
    </table>
</div>
