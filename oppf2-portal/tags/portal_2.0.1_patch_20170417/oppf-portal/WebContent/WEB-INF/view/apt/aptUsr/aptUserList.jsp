<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserList.jsp
 * @Description : admin 포털 회원관리 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.09  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.09
 * @version 1.0
 * @apt
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
    $("#schDateFrom, #schDateTo").datepicker({
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
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/aptUsr/aptUserList.do'/>";
    var param = new Object();
    param.paramMenuId = "03001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>
	
	//조회일 schDateType
    $("#schDateType").change(function(){
        if($(this).val() == "all"){
        	$("#schDateFrom").val("");
            $("#schDateTo").val("");
            
            $("#schDateFrom").datepicker("option", "disabled", true);
            $("#schDateTo").datepicker("option", "disabled", true);
            $("input[name=schDateRdo]").prop("disabled", true);
            $("input[name=schDateRdo]").eq(0).prop('checked', true);
            
        }else{
            $("#schDateFrom").datepicker("option", "disabled", false);
            $("#schDateTo").datepicker("option", "disabled", false);
            $("input[name=schDateRdo]").prop("disabled", false);
        }
    });
	
	//조회일 radio
	$("input[name=schDateRdo]").change(function(){
		var id = $(this).attr("id");
		
		var schDateFrom = "";
		var schDateTo = util_getDate();
		
		if(util_chkReturn($.trim($('#schDateTo').val()), "s") != "") {
			schDateTo = util_replaceAll($("#schDateTo").val(), "-", "");
		}
		
		//week
		if(id == "schWeek"){
			schDateFrom = util_addDate(schDateTo, "d", -7);
		//month
		}else if(id == "schMonth"){
			schDateFrom = util_addDate(schDateTo, "m", -1);
		//year
		}else if(id == "schYear"){
			schDateFrom = util_addDate(schDateTo, "y", -1);
		//all
		}else{
			schDateFrom = "";
	        schDateTo = "";
		}
		
		if(util_chkReturn(schDateFrom, "s") != "") schDateFrom = util_setFmDate(schDateFrom);
	    if(util_chkReturn(schDateTo, "s") != "") schDateTo = util_setFmDate(schDateTo);
	    		
		$("#schDateFrom").val(schDateFrom);
        $("#schDateTo").val(schDateTo);
	});
	
	//검색
	$("#btnSearch").click(function(){
		fn_search();
	});
	
	//초기화
    $("#btnSearchClear").click(function(){
    	$("#pageIndex").val("1");
    	util_moveRequest("AptUserManageVO", "<c:url value='/apt/aptUsr/aptUserList.do'/>", "_top");
    });
	
    //엑셀
    $("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });
    
    //다운로드
    $("#btnDown").click(function(){
        fn_searchExcel('down');
    });
		
	//datepicker disable
    $("#schDateFrom").datepicker("option", "disabled", true);
    $("#schDateTo").datepicker("option", "disabled", true);
    $("input[name=schDateRdo]").prop("disabled", true);
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
		columns: [
            { text: '이름(한글)', datafield: 'adminNameKor', width: '12%', cellsalign: 'left', align: 'center' },
            { text: '이름(영문)', datafield: 'adminNameEng', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'ID', datafield: 'adminId', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '휴대폰', datafield: 'adminPhoneNo', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '유선전화', datafield: 'adminTelNo', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '이메일', datafield: 'adminEmail', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '계정상태', datafield: 'deleteType', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '삭제일', datafield: 'deleteDate', width: '10%', cellsalign: 'left', align: 'center' },
            
            { text: 'adminProfileRegNo', datafield: 'adminProfileRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
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
    
    //조회
    fn_search($("#pageIndex").val());
});

<%-- 검색 --%>
function fn_search(pageIndex){
	if(!fn_validate()){
        return;
    }
	
	//page
	if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
	$("#pageIndex").val(pageIndex);
	
	//로딩 호출
	gfn_setLoading(true);
		
	//page setting  
    var url = "<c:url value='/apt/aptUsr/selectAptUserList.ajax'/>";
    var param = $("#AptUserManageVO").serialize();
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
    if($("#schDateType").val() != "all"){
    	var schFrom = util_replaceAll($("#schDateFrom").val(), "-", "");
    	var schTo = util_replaceAll($("#schDateTo").val(), "-", "");
    	    	
    	if(schFrom != ""){
	    	if(!util_isDate(schFrom)){
	    		alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
	    		$("#schDateFrom").focus();
	    		return false;
	    	}
	    	
	    	if(schTo == ""){
	    		alert("<spring:message code='errors.date' arguments='조회종료일' />");
	    		$("#schDateTo").focus();
                return false;
	    	}else{
	    		if(!util_isDate(schTo)){
	                alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
	                $("#schDateTo").focus();
	                return false;
	            }else{
	            	if(schFrom > schTo){
	            		alert("종료일이 시작일보다 클 수 없습니다.");
	                    $("#schDateTo").focus();
	                    return false;
	            	}
	            }	
	    	}
    	}else{
    		if(schTo != "" && schFrom == ""){
                alert("<spring:message code='errors.date' arguments='조회시작일' />");
                $("#schDateFrom").focus();
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
    
    util_moveRequest("AptUserManageVO", "<c:url value='/apt/aptUsr/aptUserListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}

<%-- 상세이동 --%>
function fn_moveDetail(data){
	$("#adminProfileRegNo").val(data.adminProfileRegNo);
	
	util_moveRequest("AptUserManageVO", "<c:url value='/apt/aptUsr/aptUserDtl.do'/>", "_top");
}

/*******************************************
 * 기능 함수
 *******************************************/

 
</script>

</head>

<body>
<form:form commandName="AptUserManageVO" name="AptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${AptUserManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="adminProfileRegNo" id="adminProfileRegNo" value="" />
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
                    <h2>admin 회원 조회</h2>
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
                            <th scope="row"><label for="schDeleteType">계정상태</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="schDeleteType" name="schDeleteType">
                                    <option value="all">전체</option>
                                    <option value="N">활성</option>
                                    <option value="Y">비활성</option>
                                </select>
                                
                            </td>                           
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">조회일</label></th>
                            <td colspan="3" class="txt_l">
                                <select title="구분 입력" id="schDateType" name="schDateType">
                                    <option value="all">전체</option>
                                    <option value="create">등록일</option>
                                    <option value="update">수정일</option>
                                    <option value="delete">삭제일</option>
                                </select>
                                <input type="text" id="schDateFrom" name="schDateFrom" readonly="readonly" style="width:80px;" disabled="disabled" />
                                <span class="dateDot">~</span>
                                <input type="text" id="schDateTo" name="schDateTo" readonly="readonly" style="width:80px;" disabled="disabled" />
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
<iframe id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>