<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserManageList.jsp
 * @Description : 일반회원관리 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.20  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.20
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
 var g_initCheckboxArr = ["searchJoinSectionList","searchMemberStatusList"];
 
  
/*******************************************
 * 이벤트 함수
 *******************************************/
 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "01001";
    
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
            $("input[name=searchDateRdo]").prop("disabled", true);
            $("input[name=searchDateRdo]").eq(0).prop('checked', true);
            
        }else{
            $("#searchDateFrom").datepicker("option", "disabled", false);
            $("#searchDateTo").datepicker("option", "disabled", false);
            $("input[name=searchDateRdo]").prop("disabled", false);
        }
    });

    
	//조회일 radio
	$("input[name=searchDateRdo]").change(function(){
		var id = $(this).attr("id");
		
		var searchDateFrom = "";
		var searchDateTo = util_getDate();
		
/* 		if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
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

    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }
	//검색
	$("#btnSearch").click(function(){
		fn_search();
	});
	
	//초기화
    $("#btnSearchClear").click(function(){
    	$("#pageIndex").val("1");
        
        $("#searchCondition").val("<c:out value='${SptUserManageVO.searchCondition}'/>");
       	$("#searchKeyword").val("");
       	$("#searchCustomerRegStatus").val("");
        $("#searchDateType").val("all")
        $("#searchDateFrom").val("");
        $("#searchDateTo").val("");
        $("#searchDateFrom").datepicker("option", "disabled", true);
        $("#searchDateTo").datepicker("option", "disabled", true);
        $("input[name=searchDateRdo]").prop("disabled", true);

        util_moveRequest("SptUserManageVO", "<c:url value='/apt/sptUsr/sptUserManageList.do'/>", "_top");
        
    });
	
    //엑셀
    $("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });
    
    //다운로드
    $("#btnDown").click(function(){
        fn_searchExcel('down');
    });
    

    $("#newMbrReg").click(function(){
    	fn_moveNewMember();
    });
    
    
		
	//datepicker disable
    $("#searchDateFrom").datepicker("option", "disabled", true);
    $("#searchDateTo").datepicker("option", "disabled", true);
    $("input[name=searchDateRdo]").prop("disabled", true);
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
		columns: [
            { text: '이름(한글)', datafield: 'customerNameKor', width: '12%', cellsalign: 'left', align: 'center' },
            //{ text: '이름(영문)', datafield: 'customerNameEng', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'ID', datafield: 'customerId', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '이메일', datafield: 'customerEmail', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '계정유형', datafield: 'temporaryMemberName', width: '7%', cellsalign: 'left', align: 'center' },
            { text: '계정상태', datafield: 'customerRegStatusName', width: '7%', cellsalign: 'left', align: 'center' },
            { text: '가입구분', datafield: 'customerJoinTypeName', width: '11%', cellsalign: 'left', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '삭제일', datafield: 'deleteDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: 'customerRegStatus', datafield: 'customerRegStatus', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'customerRegNo', datafield: 'customerRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
		]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        if(data.customerRegStatus != 'G005004'){
	        //상세이동
	        fn_moveDetail(data);
    	}
    }).on('bindingcomplete', function(event){
    	//로딩 호출
    	gfn_setLoading(false);
    });
       	   

    var serValue = $("#serverType").val();

    if(serValue == "Y"){
        $("#btn_newMbrReg").show();
    }else{
        $("#btn_newMbrReg").hide();
    }

    
    if("<c:out value='${SptUserManageVO.searchCondition}'/>" != ""){
    	$("#searchCondition").val("<c:out value='${SptUserManageVO.searchCondition}'/>");
    }

    if("<c:out value='${SptUserManageVO.searchKeyword}'/>" != ""){
    	$("#searchKeyword").val("<c:out value='${SptUserManageVO.searchKeyword}'/>");
    }

    
    
    if("<c:out value='${SptUserManageVO.searchCustomerRegStatus}'/>" != ""){
    	$("#searchCustomerRegStatus").val("<c:out value='${SptUserManageVO.searchCustomerRegStatus}'/>");
    }

    if("<c:out value='${SptUserManageVO.searchDateType}'/>" != ""){
    	$("#searchDateType").val("<c:out value='${SptUserManageVO.searchDateType}'/>");
    }

 
    
    if($("#searchDateType").val() != "all"){
        $("#searchDateFrom").datepicker("option", "disabled", false);
        $("#searchDateTo").datepicker("option", "disabled", false);
        $("#searchDateFrom").attr('readonly','readonly');
        $("#searchDateTo").attr('readonly','readonly');
        $("input[name=searchDateRdo]").prop("disabled", false);

        $("#searchDateFrom").val('${SptUserManageVO.searchDateFrom}');
        $("#searchDateTo").val('${SptUserManageVO.searchDateTo}');
    }else{
        $("#searchDateType").val("all")
        $("#searchDateFrom").val("");
        $("#searchDateTo").val("");
        $("#searchDateFrom").datepicker("option", "disabled", true);
        $("#searchDateTo").datepicker("option", "disabled", true);
        $("#searchDateFrom").removeAttr('readonly');
        $("#searchDateTo").removeAttr('readonly');
        $("input[name=searchDateRdo]").prop("disabled", true);
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

<%-- 검색 --%>
function fn_search(pageIndex){
	if(!fn_validate()){
        return;
    }

	<%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
	  
	//page
	if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
	$("#pageIndex").val(pageIndex);
	    

	//로딩 호출
	gfn_setLoading(true);
		
	//page setting  
    var url = "<c:url value='/apt/sptUsr/selectSptUserManageList.ajax'/>";
    var param = $("#SptUserManageVO").serialize();
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
    
    util_moveRequest("SptUserManageVO", "<c:url value='/apt/sptUsr/sptUserManageListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

<%-- 상세이동 --%>
function fn_moveDetail(data){
	$("#customerRegNo").val(data.customerRegNo);

	util_moveRequest("SptUserManageVO", "<c:url value='/apt/sptUsr/sptUserManageDtl.do'/>", "_top");
		
	
}

function fn_moveNewMember(){

	util_moveRequest("SptUserManageVO", "<c:url value='/apt/usr/mbrNewReg.do'/>", "_top");
	
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
/*******************************************
 * 기능 함수
 *******************************************/

 
</script>

</head>

<body>
<form:form commandName="SptUserManageVO" name="SptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${SptUserManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="customerRegNo" id="customerRegNo" value="" />
<input type="hidden" name="excelType" id="excelType" value="" />
<input type="hidden" name="serverType" id="serverType" value="${serverType}" />

<input type="hidden" name="searchMemberStatusListAllYn" id="searchMemberStatusListAllYn" value="N" />
<input type="hidden" name="searchJoinSectionListAllYn" id="searchJoinSectionListAllYn" value="N" />

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
                    <h2>일반 회원 조회</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>구분, 등록일, 계정상태 입력</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:40%;">
                            <col style="width:10%;">
                            <col style="width:40%;">
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
                            <th scope="row"><label for="searchCustomerRegStatus">계정 유형</label></th>
                            <td class="txt_l"> 
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchMemberStatusList" id="searchMemberStatusList_all" value="all" checked="checked">
                                            <label for="searchMemberStatusList_all">전체</label>
                                        </li>
                                        <c:forEach items="${customerMemberStatusList}" var="customerMemberStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchMemberStatusList" id="searchMemberStatusList_${customerMemberStatusList.code_name_eng}" value="${customerMemberStatusList.code_name_eng}">
                                            <label for="searchMemberStatusList_${customerMemberStatusList.code_name_eng}">${customerMemberStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>                           
                        </tr>
                        <tr>
                            <th scope="row"><label for="searchCustomerRegStatus">계정 상태</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="searchCustomerRegStatus" name="searchCustomerRegStatus">
                                    <option value="all">전체</option>
                                    <c:forEach items="${customerRegStatusList}" var="customerRegStatusList" varStatus="status">
                                        <option value="${customerRegStatusList.system_code}">${customerRegStatusList.code_name_kor}</option>
                                    </c:forEach>
                                </select>
                            </td>  
                            <th scope="row"><label for="customerJoinSection">가입 구분</label></th>
                            <td class="txt_l"> 
                                <!-- chk_list_wrap -->
                                
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchJoinSectionList" id="searchJoinSectionList_all" value="all" checked="checked">
                                            <label for="searchJoinSectionList_all">전체</label>
                                        </li>
                                        <c:forEach items="${customerJoinSection}" var="customerJoinSection" varStatus="status">
                                            <li><input type="checkbox" name="searchJoinSectionList" id="searchJoinSectionList_${customerJoinSection.system_code}" value="${customerJoinSection.system_code}">
                                            <label for="searchJoinSectionList_${customerJoinSection.system_code}">${customerJoinSection.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                
                                <!-- // chk_list_wrap -->
                            </td>                           
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">조회일</label></th>
                            <td colspan="3" class="txt_l">
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
                        <div class="right" id="btn_newMbrReg">
                            <span class="btn_gray1"><a href="javascript:void(0);" id=newMbrReg>회원추가 </a></span>
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

<script>

</script>
</html>