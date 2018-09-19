<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : fixInitiatorList.jsp
 * @Description : Initiator  관리
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.07   최판광       최초  생성
 *  2017.04.04   최판광       fail-over 관련 수정
 * </pre>
 *
 * @author 최판광
 * @since 2017.03.07
 * @version 2.0
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
var g_initCheckboxArr = ["useStatus"];

var style = "style='min-width: 42px; height: 30px; padding: 0 6px; " +
			"background: #fff; color: #413a3a !important; " +
			"font-size: 13px; line-height: 20px; white-space: " +
			"nowrap;  margin: 0 1px; border: 1px solid #666666; border-radius: 3px;'";


var serverManage = function(row, columnfield, value, defaulthtml, columnproperties) {
    var gridData = $("#jqxgrid").jqxGrid('getrowdata', row);
    var stringJson = JSON.stringify(gridData);
    
    if(gridData.fixStateCd == "0"){	
        return "<div style='text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;'>" +
		"<span class='btn_type'><a href='javascript:fn_stop(" + stringJson + ", " + row + ");' " +
		style + "id='stopBtn'>중지</a></span>" +
		"</div>";
    } else if(gridData.fixStateCd == "1"){     
    	return "<div style='text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;'>" +
        "<span class='btn_type'><a href='javascript:fn_start(" + stringJson + ", " + row + ");'" +
        style + "id='startBtn'>시작</a></span>" +
        "</div>";
    } else {     
    	return "<div style='text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;'></div>";
    }
    
    //●  style='color: #ff0000;'
    
};

var companySearchManage = function(row, columnfield, value, defaulthtml, columnproperties) {
    var gridData = $("#jqxgrid").jqxGrid('getrowdata', row);
    var stringJson = JSON.stringify(gridData);


    var tmpInitServerId = $("#jqxgrid").jqxGrid('getrowdata', row).initServerId;
    var tmpInitServerIp = $("#jqxgrid").jqxGrid('getrowdata', row).initServerIp;
    
    if(util_chkReturn($.trim(tmpInitServerId), "s") == ""
    		|| util_chkReturn($.trim(tmpInitServerIp), "s") == "" ){
    	return "<div style='text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;'></div>";
    } else {
    
	    return "<div style='text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;'>" +
	            "<span class='btn_type'><a href='javascript:fn_moveCompDtl(" + stringJson + ", " + row + ");'" +
	            style + "id='moveCompDtlBtn'>조회</a></span> </div>";
    }
};

var cellsrenderer = function (row, columnfield, value, defaulthtml, columnproperties, rowdata) {
	var gridData = $("#jqxgrid").jqxGrid('getrowdata', row);
    var stringJson = JSON.stringify(gridData);
    
    if(gridData.fixStateCd == "0"){
    	return '<div style="text-align:center;diplay:inline-block;vertical-align:middle;line-height:30px;"><span style="margin:4px;color:#008000;">● ' + gridData.fixStateCdNm + '</span></div>';
    }
    else if(gridData.fixStateCd == "1"){
        return '<div style="text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;"><span style="margin:4px;color:#ff0000;">● ' + gridData.fixStateCdNm + '</span></div>';
    }else {
        return '<div style="text-align: center;diplay:inline-block;vertical-align:middle;line-height:30px;"></div>';
    }
}
/*******************************************
 * 이벤트 함수
 *******************************************/

 function fn_start(jsonString, row){

	 if(!confirm("시작 하시겠습니까?")){
			return false;
		} 
	 
	 $("#sessionSenderCompId").val($("#jqxgrid").jqxGrid('getrowdata', row).sessionSenderCompId);
	 $("#sessionTargetCompId").val($("#jqxgrid").jqxGrid('getrowdata', row).sessionTargetCompId);
	 $("#initServerIp").val($("#jqxgrid").jqxGrid('getrowdata', row).initServerIp);

	 //로딩 호출
     gfn_setLoading(true);
     
   //page setting  
     var url = "<c:url value='/apt/fix/fixInitiatorStart.ajax'/>";
     var param = $("#FixManageVO").serialize();
     var callBackFunc = "fn_startCallBack";
     <%-- 공통 ajax 호출 --%>
     util_ajaxPage(url, param, callBackFunc);
 }

 function fn_startCallBack(data){
     //로그인 처리
     if(data.error == -1){
         fn_login();
         return;
     }
     
     if(data.fixUrlError == -1){
         alert("등록된 서버정보가 없습니다.\n관지라에게 문의 해주세요.");
         fn_search();
     }
     
     //로딩 호출
     gfn_setLoading(false);
     
     if(data.result == "200"){
         alert("정상적으로 시작 되었습니다.");
         fn_search();
     }else{
         alert("시작에 실패하였습니다.");
         fn_search();
     }
     return;
 }

 <%-- equity 수정 --%>
 function fn_moveCompDtl(jsonString, row){

 	var url = "<c:url value='/apt/fix/fixInitiatorCompPopup.do'/>";
     var objOption = new Object();
     objOption.type = 'modal';
     objOption.width = '490'; 
     objOption.height = '350';
     
     var param =  new Object();
     param.initServerId = $("#jqxgrid").jqxGrid('getrowdata', row).initServerId;
     param.initServerIp = $("#jqxgrid").jqxGrid('getrowdata', row).initServerIp;
     //objParam.searchCompanyRegYn = "N";  //미등록된 기업만 추가
     
     util_modalPage(url, objOption, param);
 }

 function fn_stop(jsonString, row){

	if(!confirm("중지 하시겠습니까?")){
		return false;
	}
	 
	 $("#sessionSenderCompId").val($("#jqxgrid").jqxGrid('getrowdata', row).sessionSenderCompId);
	 $("#sessionTargetCompId").val($("#jqxgrid").jqxGrid('getrowdata', row).sessionTargetCompId);
	 $("#initServerIp").val($("#jqxgrid").jqxGrid('getrowdata', row).initServerIp);
     
     //로딩 호출
     gfn_setLoading(true);
     
   //page setting  
     var url = "<c:url value='/apt/fix/fixInitiatorStop.ajax'/>";
     var param = $("#FixManageVO").serialize();
     var callBackFunc = "fn_stopCallBack";
     <%-- 공통 ajax 호출 --%>
     util_ajaxPage(url, param, callBackFunc);
 }

 function fn_stopCallBack(data){
     //로그인 처리
     if(data.error == -1){
         fn_login();
         return;
     }
     
     if(data.fixUrlError == -1){
         alert("등록된 서버정보가 없습니다.\n관지라에게 문의 해주세요.");
     	fn_list();
     }
     
     //로딩 호출
     gfn_setLoading(false);
     
     if(data.result == "200"){
         alert("정상적으로 중지 되었습니다.");
         fn_search();
     }else{
         alert("중지에 실패하였습니다.");
         fn_search();
     }
     return;
 }

 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserAccountList.do'/>";
    var param = new Object();
    param.paramMenuId = "01003";
    
    gfn_loginNeedMove(url, param);
}


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
    	util_moveRequest("FixManageVO", "<c:url value='/apt/fix/fixInitiator.do'/>", "_top");
    });
	
    //엑셀
    $("#btnExcel").click(function(){
        fn_searchExcel('excel');
    });
    
    //다운로드
    $("#btnDown").click(function(){
        fn_searchExcel('down');
    });

    $("#btnAdd").click(function(){
    	fn_btnAdd();
    });


    $("#btnAddIni").click(function(){
    	fn_btnAddIni();
    });

    $("#btnAddSes").click(function(){
    	fn_btnAddSes();
    });
    
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    
    gfn_gridOption('jqxgrid',{    
		columns: [
            { text: 'ServerID', datafield: 'initServerId', width: '10%', cellsalign: 'left', align: 'center' },
            { text: 'ServerIP', datafield: 'initServerIp', width: '15%', cellsalign: 'left', align: 'center' },
            { text: 'TargetCompId', datafield: 'sessionTargetCompId', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'SenderCompID', datafield: 'sessionSenderCompId', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'Socket Host', datafield: 'connectionHost', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'Socket Port', datafield: 'connectionPort', width: '13%', cellsalign: 'left', align: 'center' },
            { text: '연결상태', datafield: 'fixStateCdNm', width: '11%', cellsalign: 'center', cellsrenderer: cellsrenderer, align: 'center' },
            { text:'관리', datafield: 'fixStateCd', columngroup:'management', width: '12%', cellsalign: 'center', align: 'center'
                , cellsrenderer: serverManage, editable: false, textAlign: 'center'
            },
            { text:'기업정보', datafield: 'comp',  width: '12%', cellsalign: 'center', align: 'center'
                , cellsrenderer: companySearchManage, editable: false, textAlign: 'center'
            }
		]
    });
    
    
    
    $('#jqxgrid').on('cellclick', function (event) {
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
    	var row = event.args.rowindex;
    	var cellField = event.args.datafield;
    	if(cellField == "initServerId" ){
    		if(util_chkReturn($.trim($('#jqxgrid').jqxGrid('getrowdata', row).initServerId), "s") != "") {
    			fn_fixInitiatorDetail($('#jqxgrid').jqxGrid('getrowdata', row).initServerId);
    		}
    	} else if(cellField == "sessionSenderCompId" || cellField == "sessionTargetCompId"  ){
    		if(util_chkReturn($.trim($('#jqxgrid').jqxGrid('getrowdata', row).sessionSenderCompId), "s") != "" 
    				&& util_chkReturn($.trim($('#jqxgrid').jqxGrid('getrowdata', row).sessionTargetCompId), "s") != "") {
    			
    		fn_fixSessionDetail($('#jqxgrid').jqxGrid('getrowdata', row).sessionSenderCompId, $('#jqxgrid').jqxGrid('getrowdata', row).sessionTargetCompId);
    		}
    	} else {
    		return;
    	}
    	
        //fn_moveDetail($('#jqxgrid').jqxGrid('getrowdata', row).initServerId);
        
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
    

});

<%-- 검색 --%>
function fn_search(pageIndex){
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
    var url = "<c:url value='/apt/fix/fixInitiatorList.do'/>";
    var param = $("#FixManageVO").serialize();
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



<%-- Initiator  --%>
function fn_btnAddIni(){
	
	var url = "<c:url value='/apt/fix/fixInitiatorAdd.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '420'; 
    objOption.height = '200';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_fixInitiatorAddCallBack";
    
    util_modalPage(url, objOption, objParam);
}

<%-- Initiator  --%>
function fn_fixInitiatorDetail(initServerId){
	
	var url = "<c:url value='/apt/fix/fixInitiatorUpdate.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '420'; 
    objOption.height = '240';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_fixInitiatorUpdateCallBack";
    objParam.initServerId = initServerId;
    
    util_modalPage(url, objOption, objParam);
}

<%-- session --%>
function fn_btnAddSes(){
	var url = "<c:url value='/apt/fix/fixSessionAdd.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '620'; 
    objOption.height = '309';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_fixSessionAddCallBack";
    
    util_modalPage(url, objOption, objParam);
}

<%-- session  --%>
function fn_fixSessionDetail(sessionSenderCompId, sessionTargetCompId){
	
	var url = "<c:url value='/apt/fix/fixSessionDtl.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '620'; 
    objOption.height = '350';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_fixSessionDtlCallBack";
    
    objParam.sessionSenderCompId = sessionSenderCompId;
    objParam.sessionTargetCompId = sessionTargetCompId;
    
    util_modalPage(url, objOption, objParam);
}


function fn_addFixCompanyCallBack(data){
    //$("#companyCodeRegNo").val(data.companyCodeRegNo);
    
    $("#companyCodeId").val(data.companyCodeId);
    $("#companyNameKor").val(data.companyNameKor);
    gfn_setLoading(true);

    util_moveRequest("FixManageVO", "<c:url value='/apt/fix/fixBuySideAdd.do'/>", "_top");
}

<%-- 엑셀다운 --%>
function fn_searchExcel(excelType){
    $("#excelType").val(excelType);
    
    //로딩 호출
    gfn_setLoading(true, "엑셀 조회중 입니다.");
    $("#EXCEL_FRAME").ready(function(){
    	gfn_setLoading(false);
    });

    util_moveRequest("FixManageVO", "<c:url value='/apt/fix/fixInitiatorListhExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    //$("#excelType").val("");
    
}

</script>

</head>

<body>

<form:form commandName="FixManageVO" name="FixManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="1" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="useStatusAllYn" id="useStatusAllYn" value="N" />

<input type="hidden" name="customerRegNo" id="customerRegNo" value="" />
<input type="hidden" name="excelType" id="excelType" value="" />

<input type="hidden" name="senderCompId" id="senderCompId" value="" />

<input type="hidden" name="companyCodeId" id="companyCodeId" value="" />
<input type="hidden" name="companyNameKor" id="companyNameKor" value="" />

<input type="hidden" name="initServerId" id="initServerId" value="" />


<input type="hidden" name="sessionSenderCompId" id="sessionSenderCompId" value="" />
<input type="hidden" name="sessionTargetCompId" id="sessionTargetCompId" value="" />
<input type="hidden" name="initServerIp" id="initServerIp" value="" />



    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
			<section class="content">
				<div class="location">
					<h2>Initiator 관리</h2>
				</div>
				<!-- // locatioin -->
				
				<div class="tb_write1">
					<table>
						<caption>Initiator 관리</caption>
						<colgroup>
							<col style="width:20%;">
							<col style="width:*;">
						</colgroup>
						<tbody>
                        <tr>
							<th scope="row"><label for="chk1">구분</label></th>
							<td class="txt_l"> 
                                <select title="구분 입력" id="searchCondition" name="searchCondition">>
			                                 <option value = "all">전체</option>
			                                 <option value = "initiatorId">Initiator ID</option>
			                                 <option value = "initiatorIp">Initiator IP</option>
			                                 <option value = "senderCompId">SenderCompID</option>
			                                 <option value = "targetCompId">TargetCompID</option>
			                                 <option value = "socketHost">Socket Host</option>
			                                 <option value = "socketPort">Socket Port</option>
			                             </select>
                                </select>
                                <input type="text" style="width:150px;" id="searchKeyword" name="searchKeyword"
                                       onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();"  
                                />
							</td>	
						</tr>
						<tr>
							<th scope="row">연결상태</th>
							<td class="txt_l"> 
								<div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="useStatus" id="useStatus_all" value="all" checked="checked">
                                            <label for="useStatus_all">전체</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="useStatus" id="useStatus_Y" value="0" class="useStatus_Chk">
                                            <label for="useStatus_Y">정상</label>
                                        </li>
                                        <li>
                                            <input type="checkbox" name="useStatus" id="useStatus_N" value="1" class="useStatus_Chk">
                                            <label for="useStatus_N">중지</label>
                                        </li>
                                    </ul>
							</td>	
						</tr>
						</tbody>
					</table>
				</div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnSearch">검색</a></span>
                    <span class="btn_gray2"><a href="javascript:void(0);" id="btnSearchClear">초기화</a></span>
                </div>
				
                <p class="amount">총 <em id="totCnt">0</em> 건</p>
                
                <div id="jqxgrid" class="tb_list1"></div>
				
				<div class="pagination">
                    <%-- paging 공통 --%>
                    <div id="pagingNavi"></div>

					<div class="btn_type3">
                        <div class="left">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnDown">다운로드</a></span>
                        </div>
						
                        <div class="right" id="btn_newMbrReg">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnAddIni">Initiator 추가 </a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnAddSes">Session 추가 </a></span>
                        </div>
				</div>
			</section>
		</div>
	</article>
	</form:form>
<%-- 엑셀용 frame --%>
<iframe id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>