<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : statsFixEngineRequestList.jsp
 * @Description : Fix Engine 통계
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.09  최판광        최초  생성
 * </pre>
 *
 * @author 
 * @since 2017.03.09
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
var g_searchType = "daily";
var g_searchDate = util_setFmDate(util_getDate());
var g_searchTime = util_getServerTime().getHours();
if(Number(g_searchTime) < 10) g_searchTime = "0" + g_searchTime;
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/cmm/mainView.do'/>";
    var param = new Object();
    param.paramMenuId = "";
    
    gfn_loginNeedMove(url, param);
}

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = [
                         "searchFixBuySideList",
                         "searchMsgTypeCodeId",
                         "searchReject"
                     ];
/*******************************************
 * 이벤트 함수
 *******************************************/

var g_gridColumns = null;
var g_gridIdx = 0;
var g_gridHeaderName = "";

var g_gridColumnAll = [
	{ text: '일', datafield: 'resultDt', width: '200px', cellsalign: 'center', align: 'center', pinned: true },
	{ text: '총호출수', datafield: 'totCnt', columngroup:'dataForAll', width: '200px', cellsalign: 'right', align: 'center' },
	{ text: '성공', datafield: 'sucCnt', columngroup:'dataForAll', width: '200px', cellsalign: 'right', align: 'center' },
	{ text: '실패', datafield: 'failCnt', columngroup:'dataForAll', width: '200px', cellsalign: 'right', align: 'center' }
];
var g_gridColumnGroupAll = [
	{text:'전체', align: 'center', name: 'dataForAll'}
];

//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
    fn_login();
    return;
    </c:if>
    //검색
    $("#btnSearch").click(function(){

        <%-- 체크박스 선택 전체여부 셋팅 --%>           
        for(var i=0; i < g_initCheckboxArr.length; i++){
        	gfn_setSelectAllYn(g_initCheckboxArr[i]);
        }
        fn_search();
    });

    
	// tab_menu 
    if($(".tab_menu").length > 0){
        $(".tab_cont:not(:first)").hide();
        $(".tab_menu li a").click(function() {
            $(".tab_menu li").removeClass("on");
            $(this).parent().addClass("on");
            
            var id = $(this).attr("id");
            id = util_replaceAll(id, "tab_", "");
            
            fn_showTabContent(id);
            
            return false;
        });
    }
	
    //탭정보 세팅
    var tabId = $(".tab_menu li a").attr("id");
    $("#"+tabId).parent().addClass("on");

    g_gridColumnAll = [
    	{ text: '일', datafield: 'resultDt', width: '200px', cellsalign: 'center', align: 'center', pinned: true },
    	{ text: '총호출수', datafield: 'totCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
    	{ text: '성공', datafield: 'sucCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' },
    	{ text: '실패', datafield: 'failCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' }
    ];
    g_gridColumnGroupAll = [
    	{text:'전체', align: 'center', name: 'dataForAll'}
    ];
    
    //초기화
    $("#btnSearchClear").click(function(){
        util_moveRequest("StatsTrafficVO", "<c:url value='/apt/stats/statsFixEngineListRequest.do'/>");
    });
    

    //그리드 셋팅
    g_gridColumns = g_gridColumnAll;
    
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: g_gridColumns,
        columngroups: g_gridColumnGroupAll
    });
    
    

    //구분 선택
    $("input[name=searchTrafficType]").change(function(){
        fn_searchTrafficTypeChange();
    });


    // 구분셋팅(구분에 따른 현재일자및 시간 셋팅)
    $("input[name=searchTrafficType][value=daily]").prop("checked", true);
    fn_searchTrafficTypeChange();
    


    if("<c:out value='${paramVO.searchTrafficType}'/>" != ""){

        $('input[name=searchTrafficType]:input[value='+"<c:out value='${paramVO.searchTrafficType}'/>"+']').prop("checked", true);

        fn_searchTrafficTypeChange();
        
	    $("#searchDateFrom").val("<c:out value='${paramVO.searchDateFrom}'/>");
	    $("#searchTimeFrom").val("<c:out value='${paramVO.searchTimeFrom}'/>");
	    $("#searchDateTo").val("<c:out value='${paramVO.searchDateTo}'/>");
	    $("#searchTimeTo").val("<c:out value='${paramVO.searchTimeTo}'/>");	
    	
    	
    }


    

    <%-- 체크박스 선택 onclick이벤트 설정--%>
    for(var i=0; i< g_initCheckboxArr.length; i++){
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

	// start date 변경
    $("#searchDateFrom").focusout(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").focusout(function() {
        setEndDate("date");
    });
    // start time 변경
    $("#searchTimeFrom").focusout(function() {
        setEndDate("time");
    });
    // end time 변경
    $("#searchTimeTo").focusout(function() {
        setEndDate("time");
    });

    
    
    
    //엑셀
    $("#btnExcel").click(function(){
    	fn_searchExcel('excel');
    });
    
    // 조회
	//fn_search();
});


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

    if(util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "min"
            || util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "hourly") {

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

        if(util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "min") {
            // 최대 48시간 체크
            if(2 < getDateTime(searchDateTimeFrom, searchDateTimeTo)) {
                alert("<spring:message code='errors.period' arguments="5분,48시간"/>");
                return false;
            }
        } else if(util_chkReturn($.trim($('input[name=searchTrafficType]:checked').val()), "s") == "hourly") {
            // 최대 14일 체크
            if(14 < getDateTime(searchDateTimeFrom, searchDateTimeTo)) {
                alert("<spring:message code='errors.period' arguments="시간,14일"/>");
                return false;
            }
        }
    } else {
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

        //조회일시 셋팅
        searchDateTimeFrom = searchDateFrom;
        searchDateTimeTo = searchDateTo;
    }
    $("#searchDateTimeFrom").val(searchDateTimeFrom);
    $("#searchDateTimeTo").val(searchDateTimeTo);
    
    
    return true;
}



function fn_searchTrafficTypeChange(){
    var searchTypeValue = $("input[name=searchTrafficType]:checked").val();

    if(searchTypeValue == "min" || searchTypeValue == "hourly"){
        $(".searchTime").show();
    }else{
        $(".searchTime").hide();
    }

    var searchDateFrom = "";
    var searchDateTo = util_getDate();
    if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
        searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
    }

    if(searchTypeValue == "min") {
        searchDateFrom = util_addDate(searchDateTo, "d", -1);
        $("#searchTypeNotice").text("최대 48시간");
        g_gridHeaderName = "분";
    } else if(searchTypeValue == "hourly") {
        searchDateFrom = util_addDate(searchDateTo, "d", -7);
        $("#searchTypeNotice").text("최대 14일");
        g_gridHeaderName = "시간";
    } else if(searchTypeValue == "daily") {
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

    var searchTimeTo = util_getServerTime().getHours()-1;
    var searchTimeFrom = searchTimeTo+1;
    if(Number(searchTimeTo) < 10) searchTimeTo = "0" + searchTimeTo;
    if(Number(searchTimeFrom) < 10) searchTimeFrom = "0" + searchTimeFrom;
    $("#searchTimeFrom").val(searchTimeFrom);
    $("#searchTimeTo").val(searchTimeTo);
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

function setEndDate(eventType) {

    var searchDate = util_getDate();

    if($("#searchDateFrom").val().length < 10){
        $("#searchDateFrom").val(util_setFmDate(searchDate));
    }

    if($("#searchDateTo").val().length < 10){
        $("#searchDateTo").val(util_setFmDate(searchDate));
    }
    
    var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
    var searchTimeFrom = $.trim($('#searchTimeFrom').val());
    var searchTimeTo = $.trim($('#searchTimeTo').val());

    var searchTypeValue = $("input[name=searchTrafficType]:checked").val();
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

function fn_buySideAll(){

	fix_selectCodeChkAll(g_initCheckboxArr[0]);

}
function fn_msgTypeAll(){

	fix_selectCodeChkAll(g_initCheckboxArr[1]);
    
}
function fix_selectCodeChkAll(codeNm){
	$("#"+codeNm+"_all").prop('checked', false);
	$("."+codeNm+"_Chk").attr("checked",true);
}


<%-- 검색 --%>
function fn_search(){
	if(!fn_validate()){
        return;
    }
	
    var fixBuySideList = new Array();
    $("input[name=searchFixBuySideList]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        fixBuySideList[fixBuySideList.length] = code + "/" + value;
    });
    $("#fixBuySideList").val(fixBuySideList);

    var msgTypeCodeIdList = new Array();
    $("input[name=searchMsgTypeCodeId]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        msgTypeCodeIdList[msgTypeCodeIdList.length] = code + "/" + value;
    });
    $("#msgTypeCodeIdList").val(msgTypeCodeIdList);

    var rejectYnList = new Array();
    $("input[name=searchRejectYn]:checked").each(function() {
        var code = $(this).val();
        var value = $(this).next('label').text();
        rejectYnList[rejectYnList.length] = code + "/" + value;
    });
    $("#searchRejectYnList").val(rejectYnList);
    
    //로딩 호출
    gfn_setLoading(true);
    
    //page setting  
    var url = "<c:url value='/apt/stats/statsFixEngineRequestajax.do'/>";
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
	
    var resultList = data.resultList;
    var resultListTotalCount = resultList.length;
    $("#totCnt").text(util_setCommas(resultListTotalCount));

    /*
    String stdDate = resultMap.get("resultDt").toString();
    String resultCode = resultMap.get("codeId").toString();
    if(stdData.getStdDate().equals(stdDate) && codeId.equals(resultCode)) {
        isDataCheck = true;
        if(maxValueData < Integer.parseInt(resultMap.get("totCnt").toString())) {
            maxValueData = Integer.parseInt(resultMap.get("totCnt").toString());
        }
        chartDataForDate.put("totCnt"+codeId, resultMap.get("totCnt").toString());
        chartDataForDate.put("sucCnt"+codeId, resultMap.get("sucCnt").toString());
        chartDataForDate.put("failCnt"+codeId, resultMap.get("failCnt").toString());
    
*/
    var isAllData = data.isAllData;

    var seriesData = new Array();
    var columns = new Array();

    columns.push({ text: g_gridHeaderName, datafield: 'resultDt', width: '200px', cellsalign: 'center', align: 'center', pinned: true });
    var columnGroups = new Array();
    var headers = data.chartDisplayNames;
    for(var i=0; i<headers.length; i++) {
        if(isAllData) {
            
            columns.push({ text: '총호출수', datafield: 'totCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '성공', datafield: 'sucCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '실패', datafield: 'failCnt', columngroup:'dataForAll', width: '100px', cellsalign: 'right', align: 'center' });

            columnGroups.push({text:'전체', align: 'center', name: 'dataForAll'});
        } else {
            columns.push({ text: '총호출수', datafield: 'totCnt_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '성공', datafield: 'sucCnt_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columns.push({ text: '실패', datafield: 'failCnt_'+ headers[i].dataField, columngroup:'group'+i, width: '100px', cellsalign: 'right', align: 'center' });
            columnGroups.push({text:headers[i].displayText, align: 'center', name: 'group'+i});
        }

    }

    gfn_setLoading(false);
    //기존 grid 삭제/ redrow
    $("#jqxgrid").remove();
    $("#jqxgridData").html("<div id='jqxgrid' class='tb_list1'></div>");

    //그리드 redrow
    gfn_gridOption("jqxgrid",{    
        columns: columns,
        columngroups:columnGroups
    });

    //row 선택
    $('#jqxgrid').on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    //grid common
    
        gfn_setLoading(false)

    	$("#pagingNavi >").remove();
      	
    	//grid common
    	gfn_gridData(resultList);
        
    	//page common
    	pageNavigator(
    		$("#pageIndex").val(),
    		resultListTotalCount,
    		"fn_search",
            "50"
        );
    
}

/*******************************************
 * 기능 함수
 *******************************************/
 
function fn_showTabContent(id){	
	var moveUrl = "<c:url value='/apt/stats/statsFixEngineListRequest.do'/>";
	if(id == "01"){
		moveUrl = "<c:url value='/apt/stats/statsFixEngineListRequest.do'/>";
	}else{
		moveUrl = "<c:url value='/apt/stats/statsFixEngineReponseList.do'/>";
	}

	util_moveRequest("StatsTrafficVO", moveUrl, "_top");
	

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
    
        
    util_moveRequest("StatsTrafficVO", "<c:url value='/apt/stats/statsFixEngineRequestajaxExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}
</script>


</head>

<body>
<form:form commandName="StatsTrafficVO" name="StatsTrafficVO" method="post">

<input type="hidden" name="searchFixBuySideListAllYn" id="searchFixBuySideListAllYn" value="N" />
<input type="hidden" name="searchMsgTypeCodeIdAllYn" id="searchMsgTypeCodeIdAllYn" value="N" />
<input type="hidden" name="searchRejectAllYn" id="searchRejectAllYn" value="N" />


<input type="hidden" name="fixBuySideList" id="fixBuySideList" value="" />
<input type="hidden" name="msgTypeCodeIdList" id="msgTypeCodeIdList" value="" />
<input type="hidden" name="searchRejectYnList" id="searchRejectYnList" value="" />

<input type="hidden" name="searchDateTime" id="searchDateTime" value="" />
<input type="hidden" name="searchDateTimeFrom" id="searchDateTimeFrom" value="" />
<input type="hidden" name="searchDateTimeTo" id="searchDateTimeTo" value="" />

<%-- 엑셀 --%>
<input type="hidden" name="excelType" id="excelType" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
	<article class="container">
		<div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
			<section class="content">
				<div class="location">
					<h2>FIX Engine 통계</h2>
				</div>
				<!-- // locatioin -->

				<div class="tab_menu">
					<ul>
						<li><a href="javascript:void(0)" id="tab_01">Request</a></li>
						<li><a href="javascript:void(0)" id="tab_02">Response</a></li>
					</ul>
				</div>
				
                
					<div class="tb_write1">
						<table>
							<caption>FIX Engine 통계 정보입력</caption>
							<colgroup>
								<col style="width:20%;">
								<col style="width:30%;">
								<col style="width:20%;">
								<col style="width:30%;">
							</colgroup>
							<tbody>
							<tr>
								<th scope="row">Buy-side</th>
								<td class="txt_l" colspan="3"> 
									<!-- chk_list_wrap -->
									<div class="chk_list_wrap type2">
						                    <ul>
						                        <li>
						                            <input type="checkbox" name="searchFixBuySideList" id="searchFixBuySideList_all" value="all" checked="checked">
						                            <label for="searchFixBuySideList_all">전체</label>
						                            <li class="chk_click" onclick="javascript:fn_buySideAll()" >전체 체크</li>
						                        </li>
						                        <c:forEach items="${fixBuySideList}" var="fixBuySideList" varStatus="status">
						                            <li><input type="checkbox" name="searchFixBuySideList" id="searchFixBuySideList_${fixBuySideList.senderCompId}" value="${fixBuySideList.senderCompId}" class="searchFixBuySideList_Chk">
						                                <label for="searchFixBuySideList_${fixBuySideList.senderCompId}">${fixBuySideList.senderCompName}</label></li>
						                        </c:forEach>
						                    </ul>
						                    <a href="javascript:void(0)" class="btn_more">더보기</a>
						                </div>
									</div>
									<!-- // chk_list_wrap -->
								</td>	
							</tr>
							<tr>
								<th scope="row">Msgtype</th>
								<td class="txt_l" colspan="3"> 
									<!-- chk_list_wrap -->
									<div class="chk_list_wrap type2">
						                    <ul>
						                        <li>
						                            <input type="checkbox" name="searchMsgTypeCodeId" id="searchMsgTypeCodeId_all" value="all" checked="checked">
						                            <label for="searchMsgTypeCodeId_all">전체</label>
						                            <li class="chk_click" onclick="javascript:fn_msgTypeAll()">전체 체크</li>
						                        </li>
						                        <c:forEach items="${msgTypeList}" var="msgTypeList" varStatus="status">
						                            <li><input type="checkbox" name="searchMsgTypeCodeId" id="searchMsgTypeCodeId_${msgTypeList.codeValue}" value="${msgTypeList.codeValue}" class="searchMsgTypeCodeId_Chk">
						                                <label for="searchMsgTypeCodeId_${msgTypeList.codeValue}">${msgTypeList.codeNm}</label></li>
						                        </c:forEach>
						                    </ul>
						                    <a href="javascript:void(0)" class="btn_more">더보기</a>
									</div>
									<!-- // chk_list_wrap -->
								</td>	
							</tr>
							<tr>
								<th scope="row">성공여부</th>
								<td class="txt_l" colspan="3"> 
									<!-- chk_list_wrap -->
									<div class="chk_list_wrap type2">
										<ul>
											<li><input type="checkbox" name="searchReject" id="searchReject_all" value="all" checked="checked"><label for="searchReject_all">전체</label></li>
											<li><input type="checkbox" name="searchReject" id="searchReject_n" value="N"><label for="searchReject_n">성공</label></li>
											<li><input type="checkbox" name="searchReject" id="searchReject_y" value="Y"><label for="searchReject_y">실패</label></li>
										</ul>
									</div>
									<!-- // chk_list_wrap -->
								</td>	
							</tr>
							<tr>
								<th scope="row">통계 기준</th>
								<td class="txt_l" colspan="3"> 
									<input type="radio" name="anaType" id="buySide" value="buySide" checked="checked">
									<label for="b2">Buy-side</label>
									<input type="radio" name="anaType" id="msgType" value="msgType" >
									<label for="b3">Msgtype</label>
								</td>	
							</tr>
                            <tr>
                                <th scope="row"><label for="">구분</label></th>
                                <td class="txt_l">
                                    <input type="radio" id="searchTrafficTypeMin" name="searchTrafficType" value="min" checked="checked" /><label for="searchTrafficTypeMin"> 분(5분) </label>
                                    <input type="radio" id="searchTrafficTypeHourly" name="searchTrafficType" value="hourly" /><label for="searchTrafficTypeHourly"> 시간 </label>
                                    <input type="radio" id="searchTrafficTypeDaily" name="searchTrafficType" value="daily" /><label for="searchTrafficTypeDaily"> 일 </label>
                                    <input type="radio" id="searchTrafficTypeMonthly" name="searchTrafficType" value="monthly" /><label for="searchTrafficTypeMonthly"> 월 </label>
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
					
                	<p class="amount">총 <em id="totCnt">0</em> 건</p>
					<!-- 그리드 영역 -->
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