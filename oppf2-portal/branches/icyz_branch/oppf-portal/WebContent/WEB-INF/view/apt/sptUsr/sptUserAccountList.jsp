<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserAccountList.jsp
 * @Description : 가상계좌 목록 조회
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

var tmpcompanyNameEngAlias = "";				//기업이름Alias영문
var tmpcompanyCodeId = "";						//기업코드
var tmpcustomerRealaccountTypeEng =  "";		// 실계좌형식
var tmpcustomerVtaccountStatusName = "";	// 상태
var tmpcustomerVtaccountStatus = "";			// 가상계좌상태
var tmpustomerRealaccountNo =  "";				// 가상계좌번호
var tmpcustomerVtaccountNo =  "";				// 가상계좌번호
var tmpcustomerVtaccountAlias =  "";			// 가상계좌별칭
var tmpcustomerId = "";							// 회원 ID

/* [개인]관련 변수 */
var customerRegNo = ''; //사용자번호
var customerId    = 'hjkl1234'; //사용자ID
var customerCi    = '123456';   //사용자CI
var customerDn    = 'asdf';     //사용자DN

/* [기업]정보관련 변수 */
var companyCodeId = '00030'; //
var companyNameEngAlias = 'SAMSUNG';  //기업이름별칭영문

/* [전문결과]변수 */
var resAccList = new Array(); //가상계좌발급결과배열
var resRealAccNo  = ''; //response실계좌번호
var resVtAccNo    = ''; //response가상계좌번호
var resVtAccAlias = ''; //response별칭

/*[OauthToken]변수*/
var reqCnt = 0; //400에러(oauthToken기간만료로 인한 에러)일 경우 요청카운트 : 400에러 무한루프 막기 카운트
var accessToken = '${rsComOauthTokenVO.accessToken}';
var Authorization = '';
var apiKey = '';



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
var g_initCheckboxArr = ["searchPubcompanyCodeId","searchCustomerRegStatus","searchCustomerVtaccountStatus","searchMemberStatusList"];
 
/*******************************************
 * 이벤트 함수
 *******************************************/
 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
	var url = "<c:url value='/apt/sptUsr/sptUserAccountList.do'/>";
    
    var param = new Object();
    param.paramMenuId = "01003";
    
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
    	util_moveRequest("SptUserAccountVO", "<c:url value='/apt/sptUsr/sptUserAccountList.do'/>", "_top");
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
    $("#searchDateFrom").datepicker("option", "disabled", true);
    $("#searchDateTo").datepicker("option", "disabled", true);
    $("input[name=searchDateRdo]").prop("disabled", true);
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
		columns: [
		            { text: '사용자이름(한글)', datafield: 'customerNameKor', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '사용자ID', datafield: 'customerId', width: '8%', cellsalign: 'left', align: 'center' },
            //{ text: '사용자이름(영문)', datafield: 'customerNameEng', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '계정유형', datafield: 'temporaryMemberName', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '계정상태', datafield: 'customerRegStatusName', width: '7%', cellsalign: 'center', align: 'center' },
            { text: '서비스제공자', datafield: 'pubcompanyCodeName', width: '15%', cellsalign: 'left', align: 'center' },
            { text: '계좌유형', datafield: 'customerRealaccountTypeName', width: '7%', cellsalign: 'center', align: 'center' },
            { text: '가상계좌번호', datafield: 'customerVtaccountNo', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '실계좌번호', datafield: 'customerRealaccountNo', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '별칭', datafield: 'customerVtaccountAlias', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '상태', datafield: 'customerVtaccountStatusName', width: '7%', cellsalign: 'center', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '삭제일', datafield: 'deleteDate', width: '10%', cellsalign: 'center', align: 'center' },

            { text: '기업이름Alias영문', datafield: 'companyNameEngAlias', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: '기업코드', datafield: 'pubcompanyCodeId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: '가상계좌상태', datafield: 'customerVtaccountStatus', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: '실계좌형식', datafield: 'customerRealaccountTypeEng', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
		]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);

        customerId = data.customerId;					//기업이름Alias영문
        
        tmpcompanyNameEngAlias = data.companyNameEngAlias;					//기업이름Alias영문
        tmpcustomerRealaccountTypeEng = data.customerRealaccountTypeEng;	// 실계좌형식
        tmpcompanyCodeId = data.pubcompanyCodeId;								//기업코드
        tmpcustomerRealaccountNo = data.customerRealaccountNo;				// 가상계좌번호
        tmpcustomerVtaccountNo = data.customerVtaccountNo;					// 가상계좌번호
        tmpcustomerVtaccountAlias = data.customerVtaccountAlias;			// 가상계좌별칭
        tmpcustomerVtaccountStatusName = data.customerVtaccountStatusName;	 // 상태
        tmpcustomerVtaccountStatus = data.customerVtaccountStatus;
        
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
    

    $("#newAccReg").click(function(){
    	fn_newAccReg();
    });

    $("#delAccReg").click(function(){
    	fn_deleteVtAcc();
    });

	var serverType = '${serverType}';
	
	if(serverType == "N"){
    $("#delAccReg").hide(); //[계좌삭제]
    $("#newAccReg").hide(); //[계좌추가]
	}
	
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


<%-- 약관 팝업 --%>
function fn_newAccReg(termsRegNo, termsAuth, termsAuthYn){
	var url = "<c:url value='/apt/usr/sptUserAccountReg.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '620'; 
    objOption.height = '409';
        
    var objParam = new Object();
    objParam.callBakFunc = "fn_openTermsCallBack";
    
    util_modalPage(url, objOption, objParam);
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
    var url = "<c:url value='/apt/sptUsr/selectSptUserAccountList.ajax'/>";
    var param = $("#SptUserAccountVO").serialize();
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
    
    util_moveRequest("SptUserAccountVO", "<c:url value='/apt/sptUsr/sptUserAccountListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
    
}


/* [가상계좌:삭제]버튼 클릭 시 호출 */
function fn_deleteVtAcc(){

    var companyNameEngAlias = tmpcompanyNameEngAlias;					// 기업이름Alias영문
    var companyCodeId       = tmpcompanyCodeId;							// 기업코드
    var realAccNo           = tmpcustomerRealaccountNo;					// 실계좌번호
    var realAccType         = tmpcustomerRealaccountTypeEng;			// 실계좌형식
    var vtAccNo             = tmpcustomerVtaccountNo;					// 가상계좌번호
    var vtAccAlias          = tmpcustomerVtaccountAlias;				// 가상계좌별칭
    var customerVtaccountStatusName = tmpcustomerVtaccountStatusName;	// 상태 
    var customerVtaccountStatus = tmpcustomerVtaccountStatus;
    if(customerVtaccountStatus == "G009003"){
    	alert("이미 폐기된 계좌입니다.");
    	return false;
    }
    
    //var msgConfirm = "해당 가상계좌를 "+"<spring:message code='confirm.delete.msg'/>";
    var msgConfirm = "해당 가상계좌를 삭제하시면,\n";
    msgConfirm += "핀테크 서비스, 금융거래 정보제공 동의도\n함께 삭제 또는 폐기 될 수 있습니다.\n\n";
    msgConfirm += "삭제 하시겠습니까?";
    if(!confirm(msgConfirm)){
        return false;
    }
    
    //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
    var ajaxCallFlag = fn_ajaxCallvtAccount(
       'DIS' 
      ,companyCodeId
      ,realAccNo
      ,realAccType
      ,vtAccNo
      ,vtAccAlias
      ,companyNameEngAlias
    );
    
    if(!ajaxCallFlag){ //전문결과가 false인 경우
//        alert("<spring:message code='fail.alert.process'/>");
        return false;
    }else{
        var msgAlert = "<spring:message code='success.alert.delete'/>";
        alert(msgAlert);
        var objParam = new Object();
        objParam.appId = '<c:out value='${paramVO.appId}'/>';
        util_movePage("<c:url value='/apt/sptUsr/sptUserAccountList.do'/>", objParam);
    }
}


/* 가상계좌[발급,교체,폐기]전문요청 함수 */
function fn_ajaxCallvtAccount(trCode, companyCode, realAccNo, realAccType, vtAccNo, vtAccAlias, pCompanyNameEngAlias){
    
    var returnFlag = false;
    
    var accInfo = new Object();
    accInfo.realAccNo   = realAccNo;
    accInfo.realAccType = realAccType;
    
    if(trCode != 'REQ'){ //가상계좌발급인 경우
        accInfo.vtAccNo = vtAccNo;
    }
    
    accInfo.vtAccAlias  = vtAccAlias;
    
    var account = new Array();
    account.push(accInfo);
    
    var objParam = new Object();
    objParam.apiKey        = apiKey;
    objParam.Authorization = Authorization;
    objParam.companyNameEngAlias = pCompanyNameEngAlias;
    objParam.trCode          = trCode;
    objParam.companyCode     = companyCode;
    objParam.account         = account;
    objParam.requestUserId   = customerId;
    objParam.requestUniqueId = customerRegNo;
    objParam.userCi          = customerCi;
    objParam.accessToken     = accessToken;
    objParam.userDn          = 'aaa';
    
    gfn_setLoading(true, "처리중 입니다."); //로딩 호출
    
    var url = "<c:url value='/apt/usr/procAccInfo.ajax'/>";
    $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정    
    
    $.ajax({
//         headers : {
//             'x-credential-userId' : customerId
//            ,'x-api-requestId'     : customerRegNo
//            ,'x-credential-ci'     : customerCi
//            ,'x-credential-dn'     : ''
//            ,'x-credential-dn'     : customerDn
//         }
         type    : "POST" //가상계좌생성
        ,contentType: "application/json"
        ,async   : false
        ,url     : url
        ,data    : JSON.stringify(objParam)
        ,success : function(receiveData){
        	gfn_setLoading(false); //로딩 호출
        	
            var data = JSON.parse(receiveData.string);
        	
            if(receiveData.statusCode == '500'){
                var message = "";
                var code = "";
                
                var msgText = "";
                
                try{
                	message = data.message;
                    code = data.code;
                    
                    msgText = "관리자에게 문의하세요.\n["+message+"("+receiveData.statusCode+ " " +code+")]";
                }catch(e){
                	msgText = "관리자에게 문의하세요.\n["+receiveData.statusCode+"]";
                }
                
                alert(msgText);
                
            }else{
                
	            if(receiveData.statusCode == '200'){
	            	if(data.result.status == 'SUCCESS' || data.result.message == '처리성공'){
	            		resAccList = data.account;
	                    if(resAccList == null || resAccList == 'undefined'){
	                        //실패
	                        alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
	                    }else{
	                        if(resAccList.length > 0){
	                        	if(resAccList[0].status == "FAILURE" || resAccList[0].status == "FAILED"){
	                                //실패
	                                alert("관리자에게 문의하세요.\n["+resAccList[0].message+"]");
	                            }else{
	                                resRealAccNo  = resAccList[0].realAccNo;
	                                resVtAccNo    = resAccList[0].vtAccNo;
	                                resVtAccAlias = resAccList[0].vtAccAlias;
	                                /*
	                                if(util_chkReturn(resRealAccNo, "s") != "" && util_chkReturn(resVtAccNo, "s") != "" && util_chkReturn(resVtAccAlias, "s") != ""){
	                                    returnFlag = true;
	                                }
	                                */
	                                returnFlag = true;
	                            }
	                        }
	                    }
	                }else{
	                    if(util_chkReturn(data.account, "s") == ""){
	                        alert(data.message+'\n['+data.code+']');
	                    }
	                }
	            }else if(receiveData.statusCode == '400' || receiveData.statusCode == 'BAD_REQUEST'){
	            	//Access token does not exist -> access token 발급 상황 문제 발생
	                //Access token has expired -> access token 갱신일 문제
	                if(data.message != null){
		                if(data.message.indexOf('Access token does not exist') >= 0 || data.message.indexOf('Access token has expired') >= 0){
			                if(reqCnt >= 3){ //400에러 무한루프 막기 분기문
			                	alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
			                    reqCnt = 0;
			                }else{
			                    fn_ajaxCallGetOAuthToken(); //oauthToken요청 호출
			                    reqCnt++;
			                }
		                }else{
		                	alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
		                }
	                }else {
	                	alert("관리자에게 문의하세요.");
	                }
	            }else{
	            	alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
	            }
	                
            }	            
        }
        ,error   : function(data){
        	gfn_setLoading(false); //로딩 호출
        	
        	alert("관리자에게 문의하세요.\n["+receiveData.statusCode+"]");
        }
    });
    
    return returnFlag;
}



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* OAuthToken요청 함수 */
function fn_ajaxCallGetOAuthToken(){
    //page setting  
    var url = "<c:url value='/cmm/oauth/getToken.ajax'/>";
    var objParam = new Object();
    objParam.customerRegNo = customerRegNo;
    var callBackFunc = "fn_ajaxCallBackGetOAuthToken";
    
    //로딩 호출
    gfn_setLoading(true);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, objParam, callBackFunc);
}

/* OAuthToken요청 콜백함수 */
function fn_ajaxCallBackGetOAuthToken(data){
	gfn_setLoading(false);
    if(util_chkReturn(data.rsCd, "s") != ""){
        if(data.rsCd == '00'){
            accessToken = data.rsData.accessToken;
            if(util_chkReturn(clickId, "s") != ""){
                $("#"+clickId).click();
            }
            
        }else{
            if(util_chkReturn(data.rsMsg, "s") != ""){
                alert(data.rsMsg);
            }
        }
        
    }else{
        alert('처리과정중에 오류가 발생하였습니다.');
    }
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
<form:form commandName="SptUserAccountVO" name="SptUserAccountVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${SptUserAccountVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

<input type="hidden" name="searchPubcompanyCodeIdAllYn" id="searchPubcompanyCodeIdAllYn" value="N" />
<input type="hidden" name="searchCustomerRegStatusAllYn" id="searchCustomerRegStatusAllYn" value="N" />
<input type="hidden" name="searchMemberStatusListAllYn" id="searchMemberStatusListAllYn" value="N" />


<input type="hidden" name="searchCustomerVtaccountStatusAllYn" id="searchCustomerVtaccountStatusAllYn" value="N" />

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
                    <h2>가상계좌 목록 조회</h2>
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
                            <th scope="row"><label for="">계정유형</label></th>
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
                            <th scope="row"><label for="">서비스 제공자</label></th>
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
                            <th scope="row"><label for="">가상계좌 상태</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchCustomerVtaccountStatus" id="searchCustomerVtaccountStatus_all" value="all" checked="checked">
                                            <label for="searchCustomerVtaccountStatus_all">전체</label>
                                        </li>
                                        <c:forEach items="${customerVtaccountStatusList}" var="customerVtaccountStatusList" varStatus="status">
                                            <li><input type="checkbox" name="searchCustomerVtaccountStatus" id="searchCustomerVtaccountStatus_${customerVtaccountStatusList.system_code}" value="${customerVtaccountStatusList.system_code}">
                                            <label for="searchCustomerVtaccountStatus_${customerVtaccountStatusList.system_code}">${customerVtaccountStatusList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                                                                        
                        <tr>
                            <th scope="row"><label for="searchDateType">조회일</label></th>
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
                        
                        <div class="right" id="btn_newMbrReg">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="delAccReg">계좌 삭제 </a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="newAccReg">계좌 추가</a></span>
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