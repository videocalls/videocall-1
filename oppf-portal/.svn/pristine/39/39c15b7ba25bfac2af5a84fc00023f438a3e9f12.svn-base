<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserServiceList.jsp
 * @Description : 서비스 연결 현황
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.30  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.30
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
var g_initCheckboxArr = ["searchMemberTemporary","searchCustomerRegStatus","searchSubcompanyCodeId","searchAppId","searchPubcompanyCodeId","searchSvcAvailableYn"];
var tmpserviceRegNo = "";
var tmpcustomerRegNo = "";
var tmpaccountRegNo = "";
var tmptermsRegNo = "";
var tmpappId = "";

/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserServiceList.do'/>";
    var param = new Object();
    param.paramMenuId = "01005";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>
		
	//조회일 searchDateType
    $("#searchDateType").change(function(){
        if($(this).val() == "all"){
            $("#searchDateFrom").val("");
            $("#searchDateTo").val("");
            
            $("#searchDateFrom").datepicker("option", "disabled", true);
            $("#searchDateTo").datepicker("option", "disabled", true);
            $("#searchDateFrom").attr('readonly','readonly');
            $("#searchDateTo").attr('readonly','readonly');
            $("input[name=searchDateRdo]").prop("disabled", true);
            $("input[name=searchDateRdo]").eq(0).prop('checked', true);
            
        }else{
            $("#searchDateFrom").datepicker("option", "disabled", false);
            $("#searchDateTo").datepicker("option", "disabled", false);
            $("#searchDateFrom").removeAttr('readonly');
            $("#searchDateTo").removeAttr('readonly');
            $("input[name=searchDateRdo]").prop("disabled", false);
        }
    });
    
    //조회일 radio
    $("input[name=searchDateRdo]").change(function(){
        var id = $(this).attr("id");
        
        var searchDateFrom = "";
        var searchDateTo = util_getDate();
        
/*         if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
            searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
        } */
        
        //week
        if(id == "searchWeek"){
            searchDateFrom = util_addDate(searchDateTo, "d", -7);
        //month
        }else if(id == "searchMonth"){
            searchDateFrom = util_addDate(searchDateTo, "m", -1);
        //year
        }else if(id == "searchYear"){
            searchDateFrom = util_addDate(searchDateTo, "y", -1);
        //all
        }else{
            searchDateFrom = "";
            searchDateTo = "";
        }
        
        if(util_chkReturn(searchDateFrom, "s") != "") searchDateFrom = util_setFmDate(searchDateFrom);
        if(util_chkReturn(searchDateTo, "s") != "") searchDateTo = util_setFmDate(searchDateTo);
                
        $("#searchDateFrom").val(searchDateFrom);
        $("#searchDateTo").val(searchDateTo);
    });
	
	//검색
	$("#btnSearch").click(function(){
		fn_search();
	});
	
	//초기화
    $("#btnSearchClear").click(function(){
    	$("#pageIndex").val("1");
    	util_moveRequest("SptUserServiceVO", "<c:url value='/apt/sptUsr/sptUserServiceList.do'/>", "_top");
    });
	
    //엑셀
    $("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });
    
    //다운로드
    $("#btnDown").click(function(){
        fn_searchExcel('down');
    });

    //앱 개발자 선택
    $("input[name=searchSubcompanyCodeId]").click(function(){
            setTimeout(function(){
                fn_changeSubCompanyList();
            }, 100);  //0.1초후
    });

    //datepicker disable
    $("#searchDateFrom").datepicker("option", "disabled", true);
    $("#searchDateTo").datepicker("option", "disabled", true);
    $("input[name=searchDateRdo]").prop("disabled", true);
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
		columns: [
            { text: '서비스등록번호', datafield: 'serviceRegNo', width: '8%', cellsalign: 'left', align: 'center' },
            { text: '유효여부', datafield: 'svcAvailableYnName', width: '5%', cellsalign: 'center', align: 'center' },
            { text: '사용자ID', datafield: 'customerId', width: '8%', cellsalign: 'left', align: 'center' },
            { text: '사용자이름(한글)', datafield: 'customerNameKor', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '사용자이름(영문)', datafield: 'customerNameEng', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '계정유형', datafield: 'temporaryMember', width: '7%', cellsalign: 'center', align: 'center' },
            { text: '계정상태', datafield: 'customerRegStatusName', width: '7%', cellsalign: 'center', align: 'center' },
            { text: '앱 개발자', datafield: 'subcompanyCodeName', width: '15%', cellsalign: 'left', align: 'center' },
            { text: '앱 이름', datafield: 'appName', width: '15%', cellsalign: 'left', align: 'center' },
            { text: '서비스제공자', datafield: 'pubcompanyCodeName', width: '15%', cellsalign: 'left', align: 'center' },
            { text: '가상계좌번호', datafield: 'customerVtaccountNo', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '실계좌번호', datafield: 'customerRealaccountNo', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '삭제일', datafield: 'deleteDate', width: '10%', cellsalign: 'center', align: 'center' },

            { text: 'customerRegNo', datafield: 'customerRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'termsRegNo', datafield: 'termsRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'appId', datafield: 'appId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'accountRegNo', datafield: 'accountRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
            
		]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        tmpserviceRegNo = data.serviceRegNo;
        tmpcustomerRegNo = data.customerRegNo;
        tmpaccountRegNo = data.accountRegNo;
        tmptermsRegNo = data.termsRegNo;
        tmpappId = data.appId
        
    }).on('bindingcomplete', function(event){
    	//로딩 호출
    	gfn_setLoading(false);
    });
    
    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i], 5);
    }

    $("#accDel").click(function(){

    	if(tmpserviceRegNo == "" || tmpcustomerRegNo == ""){
	        alert("기등록된 1개의 서비스 등록번호 선택후 다시 시도해주시기 바랍니다.");
	        return;
    	}
    	fn_accDel();
    });
    

    $("#newAccAdd").click(function(){
    	if(tmpserviceRegNo == ""){
	        alert("기등록된 1개의 서비스 등록번호 선택후 다시 시도해주시기 바랍니다.");
	        return;
    	} else {
    		$("#searchServiceRegNo").val(tmpserviceRegNo);
    		$("#searchCustomerRegNo").val(tmpcustomerRegNo);
    	}
    	fn_newAccAdd();
    });
    

    $("#newSvcAdd").click(function(){
    	fn_newSvcAdd();
    });
    

	var serverType = '${serverType}';
	if(serverType == "N"){
	    $("#accDel").hide(); //[계좌연결삭제]
	    $("#newAccAdd").hide(); //[계좌연결추가]
    	$("#newSvcAdd").hide(); //[서비스연결추가]
	}
    
    //조회
    fn_search($("#pageIndex").val());
    
    //[s] 조회일 date format 세팅1 2017.04.18 한유진
    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate("date");
    });
    //[e] 조회일 date format 세팅1 2017.04.18 한유진
});



/*******************************************
 * 기능 함수
 *******************************************/

<%-- 검색 --%>
function fn_search(pageIndex){
	if(!fn_validate()){
        return;
    }
	
	//page
	if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
	$("#pageIndex").val(pageIndex);
	
	<%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
	    
	//로딩 호출
	gfn_setLoading(true);
		
	//page setting  
    var url = "<c:url value='/apt/sptUsr/selectSptUserServiceList.ajax'/>";
    var param = $("#SptUserServiceVO").serialize();
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
	$("#jqxgrid").jqxGrid('clearselection');
	 
	var resultList = data.resultList;
	var resultListTotalCount = data.resultListTotalCount;
	
	$("#totCnt").text(util_setCommas(resultListTotalCount));
	
	$("#pagingNavi >").remove();
  	
	//grid common
	gfn_gridData(resultList);
    
	//page common
	pageNavigator(
		$("#pageIndex").val(),
		resultListTotalCount,
		"fn_search",
        $("#pageRowsize").val()
    );
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
    var url = "<c:url value='/apt/sptUsr/changeSubCompanyList.ajax'/>";
    var param = $("#SptUserServiceVO").serialize();
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

    var appList = data.appList;
    fn_makeSearchAppName(appList);
}


<%-- validate --%>
function fn_validate(){
    //조회일 체크
    if($("#searchDateType").val() != "all"){
    	var searchFrom = util_replaceAll($("#searchDateFrom").val(), "-", "");
    	var searchTo = util_replaceAll($("#searchDateTo").val(), "-", "");
    	    	
    	if(searchFrom != ""){
	    	if(!util_isDate(searchFrom)){
	    		alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
	    		$("#searchDateFrom").focus();
	    		return false;
	    	}
	    	
	    	if(searchTo == ""){
	    		alert("<spring:message code='errors.date' arguments='조회종료일' />");
	    		$("#searchDateTo").focus();
                return false;
	    	}else{
	    		if(!util_isDate(searchTo)){
	                alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
	                $("#searchDateTo").focus();
	                return false;
	            }else{
	            	if(searchFrom > searchTo){
	            		alert("종료일이 시작일보다 클 수 없습니다.");
	                    $("#searchDateTo").focus();
	                    return false;
	            	}
	            }	
	    	}
    	}else{
    		if(searchTo != "" && searchFrom == ""){
                alert("<spring:message code='errors.date' arguments='조회시작일' />");
                $("#searchDateFrom").focus();
                return false;
            }
    	}
    	
    	
    }
    
    return true;
    
}

<%-- 엑셀다운 --%>
function fn_searchExcel(excelType){
    $("#excelType").val(excelType);
    
    //로딩 호출
    gfn_setLoading(true, "엑셀 조회중 입니다.");
    $("#EXCEL_FRAME").ready(function(){
    	gfn_setLoading(false);
    });
    
    util_moveRequest("SptUserServiceVO", "<c:url value='/apt/sptUsr/sptUserServiceListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

/* 계좌 삭제 버튼 클릭 */
function fn_accDel(termsRegNo, termsAuth, termsAuthYn){
	
	if(!confirm("해당 계좌를 삭제하시면 서비스 해지 및\n 정보제공동의서가 폐기됩니다.\n선택한 계좌를 삭제하시겠습니까?")){
		return false;
	}
	
    var objParam = new Object();
    objParam.customerRegNo = tmpcustomerRegNo;
    objParam.serviceRegNo = tmpserviceRegNo;
    objParam.accountRegNo = tmpaccountRegNo;
    objParam.termsRegNo = tmptermsRegNo;
    objParam.appId = tmpappId;


	var url = "<c:url value='/usr/svcAppl/deleteAccountSvcInfo.ajax'/>";
	
    var callBackFunc = "fn_accDelCallBack";

    //gfn_setLoading(true, "저장 중 입니다.");
    
    util_ajaxPageJson(url, objParam, callBackFunc);
	
}

function fn_accDelCallBack(data){
	
	//로딩 호출
    gfn_setLoading(false);
	if(data.selCnt == "1"){
		alert("삭제할수 없는 계좌입니다.");
        fn_search($("#pageIndex").val());
	} else if(data.rtn > 0){
    	alert("삭제되었습니다.");

    	$("#jqxgrid").jqxGrid('clearselection');
        fn_search($("#pageIndex").val());
		
        // 계좌 삭제후 화면 초기화
    	//$("#pageIndex").val("1");
    	//util_moveRequest("SptUserServiceVO", "<c:url value='/apt/sptUsr/sptUserServiceList.do'/>", "_top");
        
    }else{
        alert("<spring:message code='fail.alert.regist' />");
    }
}

/* 계좌 연결 추가 버튼 클릭 */
function fn_newAccAdd(termsRegNo, termsAuth, termsAuthYn){

	var url = "<c:url value='/apt/usr/sptUserNewAccountAdd.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '620'; 
    objOption.height = '409';

    var objParam = new Object();
    objParam.searchServiceRegNo = tmpserviceRegNo;
    objParam.searchCustomerRegNo = tmpcustomerRegNo;

    
    util_modalPage(url, objOption, objParam);
}

/* 서비스 연결 추가 버튼 클릭 */
function fn_newSvcAdd(termsRegNo, termsAuth, termsAuthYn){
	var url = "<c:url value='/apt/usr/sptUserNewServiceAdd.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '620'; 
    objOption.height = '313';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_openTermsCallBack";
    
    util_modalPage(url, objOption, objParam);
}

<%-- 앱 이름 make --%>
function fn_makeSearchAppName(list){
    var html = "";
    $("#searchAppNameData >").remove();

    html += "<li>";
    html += "<input type='checkbox' name='searchAppId' id='searchAppId_all' value='all' checked='checked'>";
    html += "<label for='searchAppId_all'>전체</label>";
    html += "</li>";

    if(util_chkReturn(list, "s") != ""){
        $.each(list, function(idx, data){
            html += "<li>";
            html += "<input type='checkbox' name='searchAppId' id='searchAppId_"+data.appId+"' value='"+data.appId+"'>";
            html += "<label for='searchAppId_"+data.appId+"'>"+data.appName+"</label>";
            html += "</li>";
        });
    }
    $("#searchAppNameData").append(html);

    //event 셋팅
    gfn_initCheckbox("searchAppId");
}

//[s] 조회일 date format 세팅2 2017.04.18 한유진
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

	if(!isFromToDate(searchDateFrom, searchDateTo, "00", "00")) {
  	$("#searchDateTo").val(util_setFmDate(searchDateFrom));
	}
}
//[e] 조회일 date format 세팅2 2017.04.18 한유진
 
</script>

</head>

<body>
<form:form commandName="SptUserServiceVO" name="SptUserServiceVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${SptUserServiceVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="searchMemberTemporaryAllYn" id="searchMemberTemporaryAllYn" value="N" />
<input type="hidden" name="searchCustomerRegStatusAllYn" id="searchCustomerRegStatusAllYn" value="N" />
<input type="hidden" name="searchSubcompanyCodeIdAllYn" id="searchSubcompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchAppIdAllYn" id="searchAppIdAllYn" value="N" />
<input type="hidden" name="searchPubcompanyCodeIdAllYn" id="searchPubcompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchSvcAvailableYnAllYn" id="searchSvcAvailableYnAllYn" value="N" />

<input type="hidden" name="searchServiceRegNo" id="searchServiceRegNo" value="" /> <!-- 서비스 등록 번호 -->
<input type="hidden" name="searchCustomerRegNo" id="searchCustomerRegNo" value="" /> <!-- 서비스 등록 번호 -->


<input type="hidden" name="excelType" id="excelType" value="" />

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
                    <h2>서비스 연결 현황</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>구분, 등록일, 계정상태 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        
                        <tr>
                            <th scope="row"><label for="searchCondition">구분</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="searchCondition" name="searchCondition">
                                    <option value="all">전체</option>
                                    <option value="name">이름</option>
                                    <option value="id">ID</option>
                                    <option value="email">이메일</option>
                                </select>
                                <input type="text" style="width:150px;" id="searchKeyword" name="searchKeyword"
                                       onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();"  
                                />
                            </td>                   
                        </tr>

                        <tr>
                            <th scope="row"><label for="">계정 유형</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchMemberTemporary" id="searchMemberTemporary_all" value="all" checked="checked">
                                            <label for="searchMemberTemporary_all">전체</label>
                                        </li>
                                        <li><input type="checkbox" name="searchMemberTemporary" id="searchMemberTemporary_N" value="N">
                                            <label for="searchMemberTemporary_N">회원</label></li>
                                        <li><input type="checkbox" name="searchMemberTemporary" id="searchMemberTemporary_Y" value="Y">
                                            <label for="searchMemberTemporary_Y">비회원</label></li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        
                        <tr>                         
                            <th scope="row"><label for="">계정상태</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchCustomerRegStatus" id="searchCustomerRegStatus_all" value="all" checked="checked">
                                            <label for="searchCustomerRegStatus_all">전체</label>
                                        </li>
                                        <c:forEach items="${customerRegStatusList}" var="customerRegStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchCustomerRegStatus" id="searchCustomerRegStatus_${customerRegStatusList.system_code}" value="${customerRegStatusList.system_code}">
                                            <label for="searchCustomerRegStatus_${customerRegStatusList.system_code}">${customerRegStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">앱 개발자</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchSubcompanyCodeId" id="searchSubcompanyCodeId_all" value="all" checked="checked">
                                            <label for="searchSubcompanyCodeId_all">전체</label>
                                        </li>
                                        <c:forEach items="${subcompanyCodeIdList}" var="subcompanyCodeIdList" varStatus="status">
                                            <li><input type="checkbox" name="searchSubcompanyCodeId" id="searchSubcompanyCodeId_${subcompanyCodeIdList.companyProfileRegNo}" value="${subcompanyCodeIdList.companyCodeId}">
                                            <label for="searchSubcompanyCodeId_${subcompanyCodeIdList.companyProfileRegNo}">${subcompanyCodeIdList.companyNameKorAlias}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->                                
                            </td>                           
                        </tr>
                        
                        <tr>                         
                            <th scope="row"><label for="">앱 이름</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul id="searchAppNameData">
                                        <li>
                                            <input type="checkbox" name="searchAppId" id="searchAppId_all" value="all" checked="checked">
                                            <label for="searchAppId_all">전체</label>
                                        </li>
                                        <c:forEach items="${appList}" var="appList" varStatus="status">
                                            <li><input type="checkbox" name="searchAppId" id="searchAppId_${appList.appId}" value="${appList.appId}">
                                            <label for="searchAppId_${appList.appId}">${appList.appName}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="searchDateFrom">서비스 제공자</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchPubcompanyCodeId" id="searchPubcompanyCodeId_all" value="all" checked="checked">
                                            <label for="searchPubcompanyCodeId_all">전체</label>
                                        </li>
                                        <c:forEach items="${pubcompanyCodeIdList}" var="pubcompanyCodeIdList" varStatus="status">
                                            <li><input type="checkbox" name="searchPubcompanyCodeId" id="searchPubcompanyCodeId_${pubcompanyCodeIdList.companyProfileRegNo}" value="${pubcompanyCodeIdList.companyCodeId}">
                                            <label for="searchPubcompanyCodeId_${pubcompanyCodeIdList.companyProfileRegNo}">${pubcompanyCodeIdList.companyNameKorAlias}</label></li>
										</c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->                                
                            </td>                           
                        </tr>
                        
                        <tr>                         
                            <th scope="row"><label for="">유효여부</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchSvcAvailableYn" id="searchSvcAvailableYn_all" value="all" checked="checked">
                                            <label for="searchSvcAvailableYn_all">전체</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="searchSvcAvailableYn" id="searchSvcAvailableYn_Y" value="Y">
                                            <label for="searchSvcAvailableYn_Y">유효</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="searchSvcAvailableYn" id="searchSvcAvailableYn_N" value="N">
                                            <label for="searchSvcAvailableYn_N">삭제</label>
                                        </li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                                                
                        <tr>
                            <th scope="row"><label for="chk2">조회일</label></th>
                            <td class="txt_l">
                                <select title="구분 입력" id="searchDateType" name="searchDateType">
                                    <option value="all">전체</option>
                                    <option value="create">등록일</option>
                                    <option value="update">수정일</option>
                                    <option value="delete">삭제일</option>
                                </select>
                                <input type="text" id="searchDateFrom" name="searchDateFrom" readonly="readonly" style="width:80px;" disabled="disabled" />
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo" readonly="readonly" style="width:80px;" disabled="disabled" />
                                &nbsp;
                                <input type="radio" id="searchAll" name="searchDateRdo" checked="checked" /><label for="searchAll"> 전체 </label>
                                <input type="radio" id="searchWeek" name="searchDateRdo" /><label for="searchWeek"> 최근 1주 </label>
                                <input type="radio" id="searchMonth" name="searchDateRdo" /><label for="searchMonth"> 최근 1달 </label>
                                <input type="radio" id="searchYear" name="searchDateRdo" /><label for="searchYear"> 최근 1년 </label>
                                
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
                
                <%-- rowcount 공통 --%>
                <p class="amount">총 <em id="totCnt">0</em> 건</p>
                
                <%-- grid --%>
                <div id="jqxgrid" class="tb_list1"></div>
                
                <!-- // tb_list1 -->
                <div class="pagination">
                    <%-- paging 공통 --%>
                    <div id="pagingNavi"></div>
                    
                    <div class="btn_type3">
                        <div class="left">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnDown">다운로드</a></span>
                        </div>
                        
                        <div class="right">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="accDel">계좌 연결 삭제</a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="newAccAdd">계좌 연결 추가 </a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="newSvcAdd">서비스 연결 추가 </a></span>
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
<iframe id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>