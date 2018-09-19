<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : statsUserist.jsp
 * @Description : 회원 통계
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
    "searchUserKind",
    "searchRegStatus"
];

var g_gridColumns = null;

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/stats/statsUserList.do'/>";
    var param = new Object();
    param.paramMenuId = "07002";
    
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
        util_moveRequest("StatsUserVO", "<c:url value='/apt/stats/statsUserList.do'/>", "_top");
    });
    
    //엑셀
    $("#btnExcel").click(function(){
    	fn_searchExcel('excel');
    });
    
    //구분 선택
    $("input[name=searchType]").change(function(){
        if($(this).val() == "min" || $(this).val() == "hourly"){
            $("#searchTime").show();
        }else{
            $("#searchTime").hide();
        }
        //$("#searchTime").val("");
    });
    
    //그리드 셋팅
    g_gridColumns = [
        { text: '회원구분', datafield: 'userKindName', width: '80px', cellsalign: 'center', align: 'center', pinned: true },
        { text: '총 회원수', datafield: 'totCnt', width: '100px', cellsalign: 'right', align: 'center', pinned: true },
        { text: '가입상태', datafield: 'regStatusName', width: '80px', cellsalign: 'center', align: 'center', pinned: true },
        { text: '가입상태 건수', datafield: 'statusCnt', width: '100px', cellsalign: 'right', align: 'center', pinned: true }
    ]; 
    
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
    var url = "<c:url value='/apt/stats/selectStatsUserList.ajax'/>";
    var param = $("#StatsUserVO").serialize();
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
    	util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "min" ||
    	util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "hourly"
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
    $("#EXCEL_FRAME").ready(function(){
        gfn_setLoading(false);
    });
    
    util_moveRequest("StatsUserVO", "<c:url value='/apt/stats/statsUserListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

</script>

</head>

<body>
<form:form commandName="StatsUserVO" name="StatsUserVO" method="post">
<input type="hidden" name="searchUserKindAllYn" id="searchUserKindAllYn" value="N" />
<input type="hidden" name="searchRegStatusAllYn" id="searchRegStatusAllYn" value="N" />

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
                    <h2>회원 통계</h2>
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
                            <th scope="row"><label for="">회원구분</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchUserKind" id="searchUserKind_all" value="all" checked="checked">
                                            <label for="searchUserKind_all">전체</label>
                                        </li>
                                        
                                        <li>
                                            <input type="checkbox" name="searchUserKind" id="searchUserKind_spt" value="spt">
                                            <label for="searchUserKind_spt">일반회원</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="searchUserKind" id="searchUserKind_cpt" value="cpt">
                                            <label for="searchUserKind_cpt">기업회원</label>
                                        </li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                                                
                        <tr>
                            <th scope="row"><label for="">가입 상태</label></th>
                            <td class="txt_l" colspan="3">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
	                                        <input type="checkbox" name="searchRegStatus" id="searchRegStatus_all" value="all" checked="checked">
	                                        <label for="searchRegStatus_all">전체</label>
                                        </li>
                                        <c:forEach items="${regStatusList}" var="regStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchRegStatus" id="searchRegStatus_${regStatusList.system_code}" value="${regStatusList.system_code}">
                                            <label for="searchRegStatus_${regStatusList.system_code}">${regStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">구분</label></th>
                            <td class="txt_l">
                                <input type="radio" id="searchTypeHourly" name="searchType" value="hourly" checked="checked" /><label for="searchTypeHourly"> 1시간 </label>
                                <input type="radio" id="searchTypeDaily" name="searchType" value="daily" /><label for="searchTypeDaily"> 1일 </label>
                                <input type="radio" id="searchTypeMonthly" name="searchType" value="monthly" /><label for="searchTypeMonthly"> 1개월 </label>
                            </td>
                            
                            <th scope="row"><label for="searchDateFrom">조회일/시</label></th>
                            <td class="txt_l">
                                <input type="text" id="searchDate" name="searchDate" style="width:80px;" />
                                &nbsp;
                                <input type="text" id="searchTime" name="searchTime" maxlength="2" style="width:20px;" />
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