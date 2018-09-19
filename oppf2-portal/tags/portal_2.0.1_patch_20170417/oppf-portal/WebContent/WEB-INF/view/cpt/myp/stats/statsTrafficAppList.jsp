<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : statsTrafficAppList.jsp
 * @Description : App Traffic 통계
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.08.19  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.08.19
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdraw.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxchart.core.js'/>"></script>
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
    $("#searchDateFrom, #searchDateTo").datepicker({
        showOn: "button",
        dateFormat: 'yy-mm-dd',
        buttonImage: "<c:url value='/images/cpt/common/calendar.png'/>",
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
var g_initCheckboxArr = [
    "searchAppName","searchApiName"
];

var g_gridColumns = null;
var g_gridColumnGroupAll = null;
var g_gridIdx = 0;
var chartColorGroup = [
    "#7cb5ec",
    "#90ed7d",
    "#f7a35c",
    "#8085e9",
    "#f15c80",
    "#e4d354",
    "#2b908f",
    "#f45b5b",
    "#91e8e1",
    "#7cb5ec",
    "#434348"
];
/*******************************************
 * 이벤트 함수
 *******************************************/
function gfn_initCheckbox(codeNm, limit){
    var allCheckDiv = gfn_isSelectAll(codeNm);

    //전체 제외한 체크박스 이벤트 추가 & 체크박스 선택 표시
    $("input[name="+codeNm+"]").each(function() {
        var id = $(this).attr("id");

        //선택 event 셋팅
        if(id != codeNm+"_all"){
            $(this).attr('onclick', "javascript:gfn_selectCode('"+$(this).attr("id")+"',"+limit+");");
        }else{
            $(this).attr('onclick', "javascript:gfn_selectAllCode('"+codeNm+"');");
        }
    });
}

function gfn_selectCode(codeId, limit){
    var codeNm = $("input[id="+codeId+"]").attr("name");
    if($("input[name="+codeNm+"]:checked").length > limit){
        $("#"+codeId).prop('checked', false);
        alert("<spring:message code='errors.select.limit' arguments='5' />");
        return false;
    }

    //현재 선택한 코드값이 true일때 전체를 해제한다.
    if($("#"+codeId).is(":checked")){
        $("#"+codeNm+"_all").prop('checked', false);
    }

    //모두 선택 해지 상태 일땐 전체값으로 셋팅
    if($("input[name="+codeNm+"]:checked").length == 0){
        $("#"+codeNm+"_all").prop('checked', true);
    }

    //모두 선택 일때 전체로 치환
    var allCheckDiv = $("input[name="+codeNm+"]:checked").length == ($("input[name="+codeNm+"]").length - 1) ? true : false;
    //선택할 수 있는게 한개일 경우에는 한개만 선택 할 수 있게 처리
    if($("input[name="+codeNm+"]:checked").length > 2){
        if(allCheckDiv){
            //전체 제외한 체크박스 false
            $("input[name="+codeNm+"]").each(function() {
                var id = $(this).attr("id");

                //선택 event 셋팅
                if(id != codeNm+"_all"){
                    $(this).prop('checked', false);
                }else{
                    $(this).prop('checked', true);
                }
            });
        }
    }
}

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cpt/myp/stats/statsTrafficAppList.do'/>";
    var param = new Object();
    param.paramMenuId = "05004";
    
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
        util_moveRequest("CptStatsTrafficVO", "<c:url value='/cpt/myp/stats/statsTrafficAppList.do'/>", "_top");
    });
    
    //엑셀
    /*$("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });*/

    //구분 선택
    $("input[name=searchTrafficType]").change(function(){
        fn_searchTrafficTypeChange();
    });
            
    //그리드 셋팅
    /*g_gridColumns =
    [
        { text: '일', datafield: 'Date', width: '100px', cellsalign: 'center', align: 'center', pinned: true },
        { text: '호출성공', datafield: 'cntApiDurationY', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
        { text: '내부응답', datafield: 'apiDurationY', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
        { text: '외부응답', datafield: 'sifDurationY', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
        { text: '호출실패', datafield: 'cntApiDurationN', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
        { text: '내부응답', datafield: 'apiDurationN', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
        { text: '외부응답', datafield: 'sifDurationN', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' }
    ];

    g_gridColumnGroupAll = [
        {text:'전체', align: 'center', name: 'dataForAll'}
    ];

    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: g_gridColumns,
        columngroups: g_gridColumnGroupAll
    });*/

    $("input[name=searchAppName]").click(function(){
        if(($(this).attr("id") == "searchAppName_all") || ($("input[name=searchAppName]:checked").length <= 5)) {
            setTimeout(function(){
                fn_changeAppNameList();
            }, 100);  //0.1초후
        }
    });
        
    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i], 5);
    }

    // 구분셋팅(구분에 따른 현재일자및 시간 셋팅)
    $("input[name=searchTrafficType][value=daily]").prop("checked", true);
    fn_searchTrafficTypeChange();

    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate("date");
    });

});

function setEndDate(eventType) {
    var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
    if(!isFromToDate(searchDateFrom, searchDateTo, "00", "00")) {
        $("#searchDateTo").val(util_setFmDate(searchDateFrom));
    }
}

function fn_searchTrafficTypeChange(){
    var searchTypeValue = $("input[name=searchTrafficType]:checked").val();

    var searchDateFrom = "";
    var searchDateTo = util_getDate();
    if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
        searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
    }

    if(searchTypeValue == "daily") {
        searchDateFrom = util_addDate(searchDateTo, "m", -3);
        $("#searchTypeNotice").text("최대 6개월");
        g_gridHeaderName = "일";
    } else {
        searchDateFrom = util_addDate(searchDateTo, "y", -1);
        $("#searchTypeNotice").text("최대 10년");
        g_gridHeaderName = "월";
    }

    if(util_chkReturn(searchDateFrom, "s") != "") searchDateFrom = util_setFmDate(searchDateFrom);
    if(util_chkReturn(searchDateTo, "s") != "") searchDateTo = util_setFmDate(searchDateTo);

    $("#searchDateFrom").val(searchDateFrom);
    $("#searchDateTo").val(searchDateTo);

}

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

    var appNameList = new Array();
    $("input[name=searchAppName]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        appNameList[appNameList.length] = code + "/" + value;
    });
    $("#appNameList").val(appNameList);

    var apiNameList = new Array();
    $("input[name=searchApiName]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        apiNameList[apiNameList.length] = code + "/" + value;
    });
    $("#apiNameList").val(apiNameList);

    /*var apiPlanNameList = new Array();
    $("input[name=searchApiPlanName]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        apiPlanNameList[apiPlanNameList.length] = code + "/" + value;
    });
    $("#apiPlanNameList").val(apiPlanNameList);*/

    //로딩 호출
    gfn_setLoading(true);
                
    //page setting  
    var url = "<c:url value='/cpt/myp/stats/selectStatsTrafficAppList.ajax'/>";
    var param = $("#CptStatsTrafficVO").serialize();
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
    
    var resultList = data.resultList;
    var resultListTotalCount = resultList.length;
    $("#totCnt").text(util_setCommas(resultListTotalCount));
    var dateUnitInterval = Math.round(resultListTotalCount/15);

    var isAllData = data.isAllData;

    var maxValueData = data.maxValueData;

    if(isAllData) {
        maxValueData = 0;
        $.each(resultList, function(idx,data){
            if(maxValueData < Number(data.cntApiDurationTotal)){
                maxValueData = Number(data.cntApiDurationTotal);
            }
        });
    }

    var unitIntervalData = 5;
    if(maxValueData > 100){
        unitIntervalData = Math.round(maxValueData / 100) * 10;
    }

    maxValueData = maxValueData + 5;

    var seriesData = new Array();
    var columns = new Array();

    columns.push({ text: g_gridHeaderName, datafield: 'Date', width: '150px', cellsalign: 'center', align: 'center', pinned: true });
    var columnGroups = new Array();
    var headers = data.chartDisplayNames;
    for(var i=0; i<headers.length; i++) {
        if(isAllData) {
//            seriesData[seriesData.length] = {dataField: headers[i].dataField, displayText:headers[i].displayText, color:chartColorGroup[i]};
            seriesData[0] = {dataField: "cntApiDurationY", displayText:"성공", color:chartColorGroup[0]};
            seriesData[1] = {dataField: "cntApiDurationN", displayText:"실패", color:chartColorGroup[1]};

            columns.push({ text: '호출성공', datafield: "cntApiDurationY", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '내부응답', datafield: "apiDurationY", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '외부응답', datafield: "sifDurationY", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '호출실패', datafield: "cntApiDurationN", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '내부응답', datafield: "apiDurationN", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '외부응답', datafield: "sifDurationN", columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
        } else {
            if(headers.length == 1) {
                seriesData[0] = {dataField: "cntApiDurationY_" + headers[i].dataField, displayText:"성공", color:chartColorGroup[0]};
                seriesData[1] = {dataField: "cntApiDurationN_" + headers[i].dataField, displayText:"실패", color:chartColorGroup[1]};
            } else {
                seriesData[seriesData.length] = {dataField: "cntApiDurationTotal_" + headers[i].dataField, displayText:headers[i].displayText, color:chartColorGroup[i]};
            }

            columns.push({ text: '호출성공', datafield: 'cntApiDurationY_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '내부응답', datafield: 'apiDurationY_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '외부응답', datafield: 'sifDurationY_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '호출실패', datafield: 'cntApiDurationN_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '내부응답', datafield: 'apiDurationN_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '외부응답', datafield: 'sifDurationN_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
        }

        columnGroups.push({text:headers[i].displayText, align: 'center', name: 'group'+i});
    }

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
                seriesGroups: [
                    {
                        type: 'line',
                        series: seriesData
                    }
                ]
            }
    );

    gfn_setLoading(false);
    //기존 grid 삭제/ redrow
//    $("#jqxgrid").remove();
//    $("#jqxgridData").html("<div id='jqxgrid' class='tb_list1'></div>");

    //그리드 redrow
//    gfn_gridOption("jqxgrid",{
//        columns: columns,
//        columngroups:columnGroups
//    });

    //row 선택
//    $('#jqxgrid').on('bindingcomplete', function(event){
//        //로딩 호출
//        gfn_setLoading(false);
//    });

    //grid common
//    gfn_gridData(resultList);
    
}

<%-- validate --%>
function fn_validate(){
    // 조회일 체크
    var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));

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

    // 조회일
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

    // 최대 검색 일자 체크
    if(util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "daily") {
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

    $("#searchDateTimeFrom").val(searchDateFrom);
    $("#searchDateTimeTo").val(searchDateTo);

    return true;
    
}

function isFromToDate(startDate, endDate, startTime, endTime) {
    var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
    var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
    return endDate.getTime() >= startDate.getTime();
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
        
    util_moveRequest("CptStatsTrafficVO", "<c:url value='/cpt/myp/stats/statsTrafficAppListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

<%-- 앱 이름 선택 --%>
function fn_changeAppNameList(){
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
    var url = "<c:url value='/cpt/myp/stats/changeAppNameList.ajax'/>";
    var param = $("#CptStatsTrafficVO").serialize();
    var callBackFunc = "fn_changeAppNameListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_changeAppNameListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);

    var apiNameList = data.apiNameList;
    fn_makeSearchApiName(apiNameList);
}

<%-- API 이름 make --%>
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
    gfn_initCheckbox("searchApiName", 5);
}
</script>

</head>

<body>
<form:form commandName="CptStatsTrafficVO" name="CptStatsTrafficVO" method="post">
<input type="hidden" id="companyCodeId" name="companyCodeId" value="${ paramVO.companyCodeId }" />

<input type="hidden" name="searchAppNameAllYn" id="searchAppNameAllYn" value="N" />
<input type="hidden" name="searchApiNameAllYn" id="searchApiNameAllYn" value="N" />
<%--<input type="hidden" name="searchApiPlanNameAllYn" id="searchApiPlanNameAllYn" value="N" />--%>

<input type="hidden" name="searchDateTime" id="searchDateTime" value="" />
<input type="hidden" name="searchDateTimeFrom" id="searchDateTimeFrom" value="" />
<input type="hidden" name="searchDateTimeTo" id="searchDateTimeTo" value="" />

<input type="hidden" name="appNameList" id="appNameList" value="" />
<input type="hidden" name="apiNameList" id="apiNameList" value="" />
<input type="hidden" name="apiPlanNameList" id="apiPlanNameList" value="" />

<%--<input type="hidden" name="searchStatsType" id="searchStatsType" value="appName" />--%>

<%-- 엑셀 --%>
<input type="hidden" name="excelType" id="excelType" />
<%-- 엑셀 --%>
    
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">App. 트래픽 통계</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>App. 트래픽 통계</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- tbtype_serch -->
                    <table class="tbtype_serch">
                        <caption>구분, API종류, App. 상태, 등록일 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        
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
                        <%--<tr>
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
                            <th scope="row"><label for="">통계 기준</label></th>
                            <td class="txt_l" colspan="3">
                                <div class="radio_img">
                                    <input type="radio" id="searchStatsTypeAppName" name="searchStatsType" value="appName" checked="checked" /><label for="searchStatsTypeAppName"> 앱 이름 </label>
                                    <input type="radio" id="searchStatsTypePlan" name="searchStatsType" value="plan" /><label for="searchStatsTypePlan"> Plan 종류 </label>
                                </div>
                            </td>
                        </tr>--%>
                        <tr>
                            <th scope="row"><label for="">통계 기준</label></th>
                            <td class="txt_l" colspan="3">
                                <div class="radio_img">
                                    <input type="radio" id="searchStatsTypeServiceName" name="searchStatsType" value="appName" checked="checked"/><label for="searchStatsTypeServiceName"> 앱 이름 </label>
                                    <input type="radio" id="searchStatsTypeCompany" name="searchStatsType" value="apiName" /><label for="searchStatsTypeCompany"> API 서비스 이름 </label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">구분</label></th>
                            <td class="txt_l">
                                <div class="radio_img">
                                    <input type="radio" id="searchTrafficTypeDaily" name="searchTrafficType" value="daily" /><label for="searchTrafficTypeDaily"> 일 </label>
                                    <input type="radio" id="searchTrafficTypeMonthly" name="searchTrafficType" value="monthly" /><label for="searchTrafficTypeMonthly"> 월 </label>
                                </div>
                            </td>
                            <th scope="row"><label for="">조회가능기간</label></th>
                            <td class="txt_l" id="searchTypeNotice">
                                    최대 6개월
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="searchDateFrom">조회일</label></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="searchDateFrom" name="searchDateFrom" readonly="readonly" style="width:120px;" />
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo" readonly="readonly" style="width:120px;" />
                            </td>
                        </tr>
                        
                        </tbody>
                    </table>
                    <!-- // tbtype_serch -->

                    <div class="btn_area type2">
                        <div class="right">
                            <a href="javascript:void(0);" id="btnSearchClear" class="btn_type2 type2">초기화</a>
                            <a href="javascript:void(0);" id="btnSearch" class="btn_type1 type2">검색</a>
                        </div>
                    </div>

                    <!-- 차트 -->
                    <div class="division_wrap">
                        <ul>
                            <li>
                                <div>
                                    <div id="lineTrafficCnt" class="graph_box" style="height: 400px;background: #f2f2f2;"></div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- / 차트 -->
                    
                    <%--<div class="total_num">총 <span id="totCnt" class="point01">0</span>건</div>
                    
                    &lt;%&ndash; grid &ndash;%&gt;
                    <div id="jqxgridData" class="tb_list1">                    
                        <div id="jqxgrid"></div>
                    </div>--%>
                    
                    <%--<div class="btn_area type2">
                        <div class="left">
                            <a href="javascript:void(0);" id="btnExcel" class="btn_type9 excel">엑셀</a>
                        </div>
                    </div>--%>
                    
                    <%-- paging 공통 --%>
                    <div class="pagination" id="pagingNavi"></div>

                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</form:form>
<%-- 엑셀용 frame --%>
<iframe src="" id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>