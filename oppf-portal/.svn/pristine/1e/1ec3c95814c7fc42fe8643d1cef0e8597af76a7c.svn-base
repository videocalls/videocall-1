<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptApiManageList.jsp
 * @Description : 기업사용자의 api 조회 목록
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
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.cpt.css'/>" type="text/css" />
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
	    buttonImage: "<c:url value='/images/cpt/common/calendar.png'/>",
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
var g_initCheckboxArr = ["searchApiCategory"];

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cpt/myp/api/cptApiManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "05002";
    
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
        $("#pageIndex").val("1");
        util_moveRequest("CptApiManageVO", "<c:url value='/cpt/myp/api/cptApiManageList.do'/>", "_top");
    });
    
    //엑셀
    $("#btnExcel").click(function(){
    	fn_searchExcel('excel');
    });
    
    //조회일 radio
    $("input[name=schDateRdo]").change(function(){
        var id = $(this).attr("id");
        
        var searchDateFrom = "";
        var searchDateTo = util_getDate();
        
        /* if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
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
            /*
            { text: '서비스 제공자', datafield: 'companyNameKor', width: '150px', cellsalign: 'left', align: 'center', pinned: true },
            { text: 'API ID', datafield: 'apiId', width: '300px', cellsalign: 'left', align: 'center', pinned: true },
            { text: 'API 이름', datafield: 'apiName', width: '250px', cellsalign: 'left', align: 'center' },
            { text: 'API 구분', datafield: 'apiCategoryName', width: '100px', cellsalign: 'center', align: 'center' },
            { text: 'API 타이틀', datafield: 'apiTitle', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '활성화여부', datafield: 'exposureYn', width: '80px', cellsalign: 'center', align: 'center' },
            { text: '활성화순서', datafield: 'exposureOrder', width: '80px', cellsalign: 'right', align: 'center' },
            { text: '계좌사용여부', datafield: 'apiAccountYnName', width: '100px', cellsalign: 'center', align: 'center' },
            { text: 'API 계약여부', datafield: 'apiContractCodeName', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '계약 체결일', datafield: 'apiContractDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '계약 시작일', datafield: 'apiTermsStartDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '계약 종료일', datafield: 'apiTermsExpireDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '작성자', datafield: 'createIdName', width: '100px', cellsalign: 'left', align: 'center' },
            { text: '작성일시', datafield: 'createDate', width: '150px', cellsalign: 'center', align: 'center' },
            */
            { text: 'API 이름', datafield: 'apiName', width: '200px', cellsalign: 'left', align: 'center' },
            { text: 'API 구분', datafield: 'apiCategoryName', width: '100px', cellsalign: 'center', align: 'center' },
            { text: 'API 설명', datafield: 'apiDescription', width: '298px', cellsalign: 'left', align: 'center' },
            { text: '계약 시작일', datafield: 'apiTermsStartDate', width: '100px', cellsalign: 'center', align: 'center' },
            { text: '계약 종료일', datafield: 'apiTermsExpireDate', width: '100px', cellsalign: 'center', align: 'center' },            
                        
            { text: 'companyCodeId', datafield: 'companyCodeId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'apiCategory', datafield: 'apiCategory', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'apiContractCode', datafield: 'apiContractCode', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'apiAccountYn', datafield: 'apiAccountYn', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    });
    
    //row 선택
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
    
    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate("date");
    });
    
    //조회
    fn_search($("#pageIndex").val());
});

/*******************************************
 * 기능 함수 
 *******************************************/
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
	
function isFromToDate(startDate, endDate, startTime, endTime) {
	    var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
	    var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
	    return endDate.getTime() >= startDate.getTime();
	}
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
    var url = "<c:url value='/cpt/myp/api/selectCptApiManageList.ajax'/>";
    var param = $("#CptApiManageVO").serialize();
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
        $("#pageRowsize").val()
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
	
	util_moveRequest("CptApiManageVO", "<c:url value='/cpt/myp/api/selectCptApiManageListExcel.do'/>", "EXCEL_FRAME");
	
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
                    alert("시작일이 종료일보다 클 수 없습니다.");
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
    
    util_moveRequest("CptApiManageVO", "<c:url value='/cpt/myp/api/cptApiManageDtl.do'/>", "_top");
}
//date input 한글 제한
$('.hasDatepicker').live("blur keyup", function() {
    $(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
});
</script>

</head>

<body>
<form:form commandName="CptApiManageVO" name="CptApiManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="20" /><!-- //목록사이즈 -->

<input type="hidden" name="apiId" id="apiId" />

<input type="hidden" name="searchApiCategoryAllYn" id="searchApiCategoryAllYn" value="N" />
<%--<input type="hidden" name="searchApiContractCodeAllYn" id="searchApiContractCodeAllYn" value="N" />--%>

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
                <li class="on">API 관리</li>
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
                    <h3>API 관리</h3>                 
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- tbtype_serch -->
                    <table class="tbtype_serch">
                        <caption>구분, API종류, App. 상태, 등록일 입력</caption>
                        <colgroup>
                            <col style="width:18%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="searchCondition">구분</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="searchCondition" name="searchCondition">
                                    <option value="all">전체</option>
                                    <option value="apiId">API ID</option>
                                    <option value="apiName">API 이름</option>                                    
                                </select>
                                <input type="text" style="width:150px;" id="searchKeyword" name="searchKeyword" />
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
                        
                        <%--<tr>
                            <th scope="row"><label for="">API 계약</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchApiContractCode" id="searchApiContractCode_all" value="all" checked="checked">
                                            <label for="searchApiContractCode_all">전체</label>
                                        </li>
                                        <c:forEach items="${apiContractCodeList}" var="apiContractCodeList" varStatus="status">
                                            <c:if test="${ apiContractCodeList.system_code ne 'G023002' }">
                                            <li><input type="checkbox" name="searchApiContractCode" id="searchApiContractCode_${apiContractCodeList.system_code}" value="${apiContractCodeList.system_code}">
                                            <label for="searchApiContractCode_${apiContractCodeList.system_code}">${apiContractCodeList.code_name_kor}</label></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>--%>
                        <tr>
                            <th scope="row"><label for="searchDateFrom">조회일</label></th>
                            <td class="txt_l">
                                <div class="radio_img">
                                    <input type="radio" id="schAll" name="schDateRdo" checked="checked" /><label for="schAll"> 전체 </label>
                                    <input type="radio" id="schWeek" name="schDateRdo" /><label for="schWeek"> 최근 1주 </label>
                                    <input type="radio" id="schMonth" name="schDateRdo" /><label for="schMonth"> 최근 1달 </label>
                                    <input type="radio" id="schYear" name="schDateRdo" /><label for="schYear"> 최근 1년 </label>
                                </div>
                                <div class="mt10">
	                                <input type="text" id="searchDateFrom" name="searchDateFrom" maxlength="10" style="width:120px;" />
	                                <span class="dateDot">~</span>
	                                <input type="text" id="searchDateTo" name="searchDateTo"  maxlength="10" style="width:120px;" />
                                </div>
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
                    
                    <div class="total_num">총 <span id="totCnt" class="point01">0</span>건</div>
                    
                    <%-- grid --%>
                    <div class="tb_list1">                    
                        <div id="jqxgrid"></div>
                    </div>
                    
                    <div class="btn_area type2">
                        <div class="left">
                            <a href="javascript:void(0);" id="btnExcel" class="btn_type9 excel">엑셀</a>
                        </div>
                    </div>
                    
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