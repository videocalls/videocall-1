<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : apiContractManageList.jsp
 * @Description : api 계약 조회 목록
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
	    //minDate: '-3y',
	    //maxDate: '+1y',
	    currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = ["searchPubcompanyCodeId","searchSubcompanyCodeId","searchApiCategory","searchServiceContractStatus"];

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


    /*조회기간*/
    if(${!empty paramVO.searchDateFrom}){
        $("#searchDateFrom").val('${paramVO.searchDateFrom}');
        $("#searchDateTo").val('${paramVO.searchDateTo}');
    }else{
        $("#searchDateFrom").val('');
        $("#searchDateTo").val('');
    }

    /* 서비스 제공자 */
    <c:choose>
        <c:when test="${!empty paramVO.searchPubcompanyCodeId && paramVO.searchPubcompanyCodeId[0] != '[all]' && paramVO.searchPubcompanyCodeId[0] != ''}">
            $("#searchPubcompanyCodeId_all").attr("checked", false);
            <c:forEach items="${pubCompanyCodeList}" var="pubCompanyCodeList" varStatus="status">
                <c:forEach items="${paramVO.searchPubcompanyCodeId}" var="searchPubcompanyCodeId" varStatus="status">
                    var searchPubcompanyCodeId = "${searchPubcompanyCodeId}";
                    searchPubcompanyCodeId = searchPubcompanyCodeId.replace(/\[/g,'').replace(/\]/g,'');

                    if('${pubCompanyCodeList.companyCodeId}' == searchPubcompanyCodeId){
                        $("#searchPubcompanyCodeId_${pubCompanyCodeList.companyProfileRegNo}").attr("checked", true);
                    }else if(searchPubcompanyCodeId =='all'){
                        $("input[name='searchPubcompanyCodeId']").attr("checked", false);
                        $("#searchPubcompanyCodeId_all").attr("checked", true);
                    }
                </c:forEach>
            </c:forEach>
        </c:when>
        <c:otherwise>
            $("input[name='searchPubcompanyCodeId']").attr("checked", false);
            $("#searchPubcompanyCodeId_all").attr("checked", true);
        </c:otherwise>
    </c:choose>

    /* 앱 개발자 */
    <c:choose>
        <c:when test="${!empty paramVO.searchSubcompanyCodeId && paramVO.searchSubcompanyCodeId[0] != '[all]' && paramVO.searchSubcompanyCodeId[0] != ''}">
            $("#searchSubcompanyCodeId_all").attr("checked", false);
            <c:forEach items="${subCompanyCodeList}" var="subCompanyCodeList" varStatus="status">
                <c:forEach items="${paramVO.searchSubcompanyCodeId}" var="searchSubcompanyCodeId" varStatus="status">
                    var searchSubcompanyCodeId = "${searchSubcompanyCodeId}";
                    searchSubcompanyCodeId = searchSubcompanyCodeId.replace(/\[/g,'').replace(/\]/g,'');

                    if('${subCompanyCodeList.companyCodeId}' == searchSubcompanyCodeId){
                        $("#searchSubcompanyCodeId_${subCompanyCodeList.companyProfileRegNo}").attr("checked", true);
                    }else if(searchSubcompanyCodeId =='all'){
                        $("input[name='searchSubcompanyCodeId']").attr("checked", false);
                        $("#searchSubcompanyCodeId_all").attr("checked", true);
                    }
                </c:forEach>
            </c:forEach>
        </c:when>
        <c:otherwise>
            $("input[name='searchSubcompanyCodeId']").attr("checked", false);
            $("#searchSubcompanyCodeId_all").attr("checked", true);
        </c:otherwise>
    </c:choose>

    /* API 구분 */
    <c:choose>
        <c:when test="${!empty paramVO.searchApiCategory && paramVO.searchApiCategory[0] != '[all]' && paramVO.searchApiCategory[0] != ''}">
            $("#searchApiCategory_all").attr("checked", false);
            <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                <c:forEach items="${paramVO.searchApiCategory}" var="searchApiCategory" varStatus="status">
                    var searchApiCategory = "${searchApiCategory}";
                    searchApiCategory = searchApiCategory.replace(/\[/g,'').replace(/\]/g,'');

                    if('${apiCategoryList.system_code}' == searchApiCategory){
                        $("#searchApiCategory_${apiCategoryList.system_code}").attr("checked", true);
                    }else if(searchApiCategory =='all'){
                        $("input[name='searchApiCategory']").attr("checked", false);
                        $("#searchApiCategory_all").attr("checked", true);
                    }
                </c:forEach>
            </c:forEach>
        </c:when>
        <c:otherwise>
            $("input[name='searchApiCategory']").attr("checked", false);
            $("#searchApiCategory_all").attr("checked", true);
        </c:otherwise>
    </c:choose>

    /* API 계약상태 */
    <c:choose>
        <c:when test="${!empty paramVO.searchServiceContractStatus && paramVO.searchServiceContractStatus[0] != '[all]' && paramVO.searchServiceContractStatus[0] != ''}">
            $("#searchServiceContractStatus_all").attr("checked", false);
            <c:forEach items="${serviceContractStatusList}" var="serviceContractStatusList" varStatus="status">
                <c:forEach items="${paramVO.searchServiceContractStatus}" var="searchServiceContractStatus" varStatus="status">
                    var searchServiceContractStatus = "${searchServiceContractStatus}";
                    searchServiceContractStatus = searchServiceContractStatus.replace(/\[/g,'').replace(/\]/g,'');

                    if('${serviceContractStatusList.system_code}' == searchServiceContractStatus){
                        $("#searchServiceContractStatus_${serviceContractStatusList.system_code}").attr("checked", true);
                    }else if(searchServiceContractStatus =='all'){
                        $("input[name='searchServiceContractStatus']").attr("checked", false);
                        $("#searchServiceContractStatus_all").attr("checked", true);
                    }
                </c:forEach>
            </c:forEach>
        </c:when>
        <c:otherwise>
            $("input[name='searchServiceContractStatus']").attr("checked", false);
            $("#searchServiceContractStatus_all").attr("checked", true);
        </c:otherwise>
    </c:choose>

    //검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //초기화
    $("#btnSearchClear").click(function(){
        $("#pageIndex").val("1");

        //체크박스 초기화
        $("input[name='searchPubcompanyCodeId']").val('');
        $("input[name='searchSubcompanyCodeId']").val('');
        $("input[name='searchApiCategory']").val('');
        $("input[name='searchServiceContractStatus']").val('');

        //datepicker 초기화
        $("#searchDateFrom").val('');
        $("#searchDateTo").val('');

        util_moveRequest("ApiContractManageVO", "<c:url value='/apt/contract/apiContractManageList.do'/>", "_top");
    });
    
    //엑셀
    $("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });
    
    //다운로드
    $("#btnDown").click(function(){
        fn_searchExcel('down');
    });
    
    //조회일 radio
    $("input[name=schDateRdo]").change(function(){
        var id = $(this).attr("id");
        
        var searchDateFrom = "";
        var searchDateTo = util_getDate();
        
/*         if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
        	searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
        } */
        
        //week
        if(id == "schWeek"){
        	searchDateFrom = util_addDate(searchDateTo, "d", -7);
        //month
        }else if(id == "schMonth"){
        	searchDateFrom = util_addDate(searchDateTo, "m", -1);
        //year
        }else if(id == "schYear"){
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
        
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '서비스 제공자', datafield: 'pubcompanyName', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
            { text: 'API 구분', datafield: 'apiCategoryName', width: '120px', cellsalign: 'center', align: 'center', pinned: true },
            { text: 'API 이름', datafield: 'apiName', width: '300px', cellsalign: 'left', align: 'center', pinned: true },
            
            { text: '앱 개발자', datafield: 'subcompanyName', width: '150px', cellsalign: 'left', align: 'center' },
            { text: 'API 계약상태', datafield: 'serviceContractStatusName', width: '150px', cellsalign: 'center', align: 'center' },
            { text: '계약 시작일', datafield: 'contractTermsStartDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '계약 종료일', datafield: 'contractTermsExpireDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '작성자', datafield: 'createIdName', width: '100px', cellsalign: 'left', align: 'center' },
            { text: '작성일시', datafield: 'createDate', width: '150px', cellsalign: 'center', align: 'center' },
            
            { text: 'subcompanyCodeId', datafield: 'subcompanyCodeId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'pubcompanyCodeId', datafield: 'pubcompanyCodeId', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        //상세이동
        fn_moveDetail(data);
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }
    
    //조회
    fn_search($("#pageIndex").val());
    
    //[s] 조회일 date format 세팅1 2017.04.18 한유진
    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate();
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate();
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
    var url = "<c:url value='/apt/contract/selectApiContractManageList.ajax'/>";
    var param = $("#ApiContractManageVO").serialize();
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
        "50"
    );
}

<%-- 엑셀다운 --%>
function fn_searchExcel(excelType){
    $("#excelType").val(excelType);
    
    //로딩 호출
    gfn_setLoading(true, "엑셀 조회중 입니다.");
    $("#EXCEL_FRAME").ready(function(){
        gfn_setLoading(false);
    });
    
    util_moveRequest("ApiContractManageVO", "<c:url value='/apt/contract/selectApiContractManageListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");    
}

<%-- validate --%>
function fn_validate(){
    //조회일 체크
    var searchDateFrom = util_replaceAll($("#searchDateFrom").val(), "-", "");
    var searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
            
    if(searchDateFrom != ""){
        if(!util_isDate(searchDateFrom)){
            alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
            $("#searchDateFrom").focus();
            return false;
        }
        
        if(searchDateTo == ""){
            alert("<spring:message code='errors.date' arguments='조회종료일' />");
            $("#searchDateTo").focus();
            return false;
        }else{
            if(!util_isDate(searchDateTo)){
                alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
                $("#searchDateTo").focus();
                return false;
            }else{
                if(searchDateFrom > searchDateTo){
                    alert("종료일이 시작일보다 클 수 없습니다.");
                    $("#searchDateTo").focus();
                    return false;
                }
            }   
        }
    }else{
        if(searchDateTo != "" && searchDateFrom == ""){
            alert("<spring:message code='errors.date' arguments='조회시작일' />");
            $("#searchDateFrom").focus();
            return false;
        }
    }
    
    return true;
    
}

<%-- 상세이동 --%>
function fn_moveDetail(data){
    $("#apiId").val(data.apiId);
    $("#pubcompanyCodeId").val(data.pubcompanyCodeId);
    $("#subcompanyCodeId").val(data.subcompanyCodeId);
    
    util_moveRequest("ApiContractManageVO", "<c:url value='/apt/contract/apiContractManageDtl.do'/>", "_top");
}

//[s] 조회일 date format 세팅2 2017.04.18 한유진
function isFromToDate(startDate, endDate, startTime, endTime) {
	var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
	var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
	return endDate.getTime() >= startDate.getTime();
}

function setEndDate() {

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
<form:form commandName="ApiContractManageVO" name="ApiContractManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="searchPubcompanyCodeIdAllYn" id="searchPubcompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchSubcompanyCodeIdAllYn" id="searchSubcompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchApiCategoryAllYn" id="searchApiCategoryAllYn" value="N" />
<input type="hidden" name="searchServiceContractStatusAllYn" id="searchServiceContractStatusAllYn" value="N" />

<input type="hidden" name="apiId" id="apiId" />
<input type="hidden" name="pubcompanyCodeId" id="pubcompanyCodeId" />
<input type="hidden" name="subcompanyCodeId" id="subcompanyCodeId" />

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
                    <h2>계약 정보 조회</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>키워드, 노출종류, 조회일</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="">서비스 제공자</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchPubcompanyCodeId" id="searchPubcompanyCodeId_all" value="all" checked="checked">
                                            <label for="searchPubcompanyCodeId_all">전체</label>
                                        </li>
                                        <c:forEach items="${pubCompanyCodeList}" var="pubCompanyCodeList" varStatus="status">
                                            <li><input type="checkbox" name="searchPubcompanyCodeId" id="searchPubcompanyCodeId_${pubCompanyCodeList.companyProfileRegNo}" value="${pubCompanyCodeList.companyCodeId}">
                                            <label for="searchPubcompanyCodeId_${pubCompanyCodeList.companyProfileRegNo}">${pubCompanyCodeList.companyNameKorAlias}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
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
                                        <c:forEach items="${subCompanyCodeList}" var="subCompanyCodeList" varStatus="status">
                                            <li><input type="checkbox" name="searchSubcompanyCodeId" id="searchSubcompanyCodeId_${subCompanyCodeList.companyProfileRegNo}" value="${subCompanyCodeList.companyCodeId}">
                                            <label for="searchSubcompanyCodeId_${subCompanyCodeList.companyProfileRegNo}">${subCompanyCodeList.companyNameKorAlias}</label></li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->                                
                            </td>                           
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">API 구분</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
	                                        <input type="checkbox" name="searchApiCategory" id="searchApiCategory_all" value="all" checked="checked">
	                                        <label for="searchApiCategory_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                                            <li><input type="checkbox" name="searchApiCategory" id="searchApiCategory_${apiCategoryList.system_code}" value="${apiCategoryList.system_code}">
                                            <label for="searchApiCategory_${apiCategoryList.system_code}">${apiCategoryList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">API 계약상태</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchServiceContractStatus" id="searchServiceContractStatus_all" value="all" checked="checked">
                                            <label for="searchServiceContractStatus_all">전체</label>
                                        </li>
                                        <c:forEach items="${serviceContractStatusList}" var="serviceContractStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchServiceContractStatus" id="searchServiceContractStatus_${serviceContractStatusList.system_code}" value="${serviceContractStatusList.system_code}">
                                            <label for="searchServiceContractStatus_${serviceContractStatusList.system_code}">${serviceContractStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="searchDateFrom">API 계약 기간</label></th>
                            <td class="txt_l">
                                <input type="text" id="searchDateFrom" name="searchDateFrom" style="width:80px;" />
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo" style="width:80px;" />
                                &nbsp;
                                <input type="radio" id="schAll" name="schDateRdo" checked="checked" /><label for="schAll"> 전체 </label>
                                <input type="radio" id="schWeek" name="schDateRdo" /><label for="schWeek"> 최근 1주 </label>
                                <input type="radio" id="schMonth" name="schDateRdo" /><label for="schMonth"> 최근 1달 </label>
                                <input type="radio" id="schYear" name="schDateRdo" /><label for="schYear"> 최근 1년 </label>
                                
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