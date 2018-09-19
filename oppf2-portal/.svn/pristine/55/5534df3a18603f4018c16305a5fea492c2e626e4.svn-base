<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptServiceTermsList.jsp
 * @Description : 기업 사용자 금융정보제공 동의서목록
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
var g_initCheckboxArr = ["searchTermsStatus"];
 
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cpt/myp/svcTerms/cptServiceTermsList.do'/>";
    var param = new Object();
    param.paramMenuId = "05005";
    
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
    	util_moveRequest("CptSreviceTermsVO", "<c:url value='/cpt/myp/svcTerms/cptServiceTermsList.do'/>", "_top");
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

       /*  if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
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
            { text: '앱개발자', datafield: 'subcompanyCodeName', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '서비스제공자', datafield: 'pubcompanyCodeName', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '동의서ID', datafield: 'termsRegNo', width: '12%', cellsalign: 'center', align: 'center' },
            { text: '상태', datafield: 'termsStatusName', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '회원명', datafield: 'customerNameKor', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '회원ID', datafield: 'customerId', width: '11%', cellsalign: 'left', align: 'center' },
            { text: '정보제공동의 시작일', datafield: 'termsStartDate', width: '15%', cellsalign: 'center', align: 'center' },
            { text: '정보제공동의 종료일', datafield: 'termsExpireDate', width: '15%', cellsalign: 'center', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '18%', cellsalign: 'left', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '18%', cellsalign: 'left', align: 'center' },
            
            { text: 'customerRegNo', datafield: 'customerRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'serviceRegNo', datafield: 'serviceRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'pubcompanyCodeId', datafield: 'pubcompanyCodeId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'termsRegNoEncryption', datafield: 'termsRegNoEncryption', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
		]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        //약관 팝업
        fn_openTerms(data);
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
    var url = "<c:url value='/cpt/myp/svcTerms/selectCptServiceTermsList.ajax'/>";
    var param = $("#CptSreviceTermsVO").serialize();
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
	            		alert("시작일이 종료일보다 클 수 없습니다.");
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
    
    util_moveRequest("CptSreviceTermsVO", "<c:url value='/cpt/myp/svcTerms/cptServiceTermsListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
    
}

<%-- 약관 팝업 --%>
function fn_openTerms(data){
	var companyServiceType = "${LoginVO.company_service_type}";
	
//  var termsRegNo = data.termsRegNo;
    var termsRegNo = data.termsRegNoEncryption;
	var customerRegNo = data.customerRegNo;
	var pubcompanyCodeId = data.pubcompanyCodeId;
	var termsAuthYn = "N";
	
	var url = "<c:url value='/usr/svcAppl/svcTermConsntPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '900'; 
    objOption.height = '700';
        
    var objParam = new Object();
    objParam.termsRegNo = termsRegNo;
    objParam.termsAuthYn = termsAuthYn;
    objParam.customerRegNo = customerRegNo;
    
    //서비스 제공자일 경우에만 금투사 정보를 셋팅한다.
    if(companyServiceType == "G002002") objParam.pubcompanyCodeId = pubcompanyCodeId;
    objParam.callBakFunc = "fn_openTermsCallBack";
        
    util_modalPage(url, objOption, objParam);
}
function fn_openTermsCallBack(data){
}

//date input 한글 제한
$('.hasDatepicker').live("blur keyup", function() {
    $(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
});

/*******************************************
 * 기능 함수
 *******************************************/

 
</script>

</head>

<body>
<form:form commandName="CptSreviceTermsVO" name="CptSreviceTermsVO" method="post">

<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${CptSreviceTermsVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="20" /><!-- //목록사이즈 -->

<input type="hidden" name="searchTermsStatusAllYn" id="searchTermsStatusAllYn" value="N" />

<input type="hidden" name="excelType" id="excelType" value="" />

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
                <li class="on">동의서 조회</li>
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
                    <h3>동의서 조회</h3>                    
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- tbtype_serch -->
                    <table class="tbtype_serch">
                        <caption>구분, App. 구분, App. 상태, App. 서비스 계약, 서비스 제공자, 조회일 입력</caption>
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
                                    <option value="name">회원명</option>
                                    <option value="id">ID</option>
                                    <option value="email">이메일</option>
                                </select>
                                <input type="text" style="width:150px;" id="searchKeyword" name="searchKeyword" />
                            </td>
                        </tr>
                        <tr>                         
                            <th scope="row"><label for="">금융정보제공<br>동의서 상태</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchTermsStatus" id="searchTermsStatus_all" value="all" checked="checked">
                                            <label for="searchTermsStatus_all">전체</label>
                                        </li>
                                        <c:forEach items="${termsStatusList}" var="termsStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchTermsStatus" id="searchTermsStatus_${termsStatusList.system_code}" value="${termsStatusList.system_code}">
                                            <label for="searchTermsStatus_${termsStatusList.system_code}">${termsStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
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
                                    <input type="text" id="searchDateTo" name="searchDateTo" maxlength="10" style="width:120px;" />
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

                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination"></div>
                    <!-- // pagination -->

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
<iframe id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>