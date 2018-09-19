<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : systemCodeList.jsp
 * @Description : 공통코드 관리
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.16  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.16
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
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdatatable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxtreegrid.js'/>"></script>
<%-- //jqwidgets --%>

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#grpCode_dateFrom, #grpCode_dateTo, #code_dateFrom, #code_dateTo").datepicker({
        showOn: "button",
        dateFormat: 'yy-mm-dd',
        buttonImage: "<c:url value='/images/apt/calendar.png'/>",
        buttonImageOnly: true,
        buttonText: "달력보기",
//        minDate: '-3y',
//        maxDate: '+1y',
        currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_saveFlag = "INS";    <%-- 저장종류 : INS[등록], UPD[수정] --%>
var g_saveLvl = "0";        <%-- treeLvl과 동일값 --%>

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/setting/systemCodeList.do'/>";
    var param = new Object();
    param.paramMenuId = "09002";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
	//tree expandAll
	$("#btnExpandAll").click(function(){
		$("#treeGrid").jqxTreeGrid('expandAll');
	});
	
	//tree collapseAll
	$("#btnCollapseAll").click(function(){
        $("#treeGrid").jqxTreeGrid('collapseAll');
    });
	
	//그룹코드 등록
	$("#btnGrpCodeReg").click(function(){
		//삭제버튼 비활성화
        $("#btnDeleteSpan").hide();
		
		//상세 보기 리셋
        var data = {treeLvl : '0'};
        fn_setDetailDiv(data);
        
        $("#grpCode_systemGrpCode").focus();
        
        g_saveFlag = "INS";
        g_saveLvl = "0";
    });
		
	//코드 등록
    $("#btnCodeReg").click(function(){
    	var treeData = $("#treeGrid").jqxTreeGrid('getSelection')[0];
        var treeLvl = "";
        var systemGrpCode = "";
        if(util_chkReturn(treeData, "s") == ""){
        	alert("코드그룹을 먼저 선택 해 주세요.");
        	return;
        }else{
        	if(treeData.treeLvl == "1" || treeData.treeLvl == ""){
        		alert("코드그룹을 먼저 선택 해 주세요.");
                return;	
        	}
        }
        treeLvl = treeData.treeLvl;
        systemGrpCode = treeData.systemGrpCode;
            	
    	//삭제버튼 비활성화
        $("#btnDeleteSpan").hide();
    	
        //상세 보기 리셋
        var data = {treeLvl : '1'};
        fn_setDetailDiv(data);
        
        $("#code_systemGrpCode").val(systemGrpCode);
        $("#code_systemGrpCode").attr("disabled",true);
        
        $("#code_systemCode").focus();
        
        g_saveFlag = "INS";
        g_saveLvl = "1";
    });
	
    //저장
    $("#btnSave").click(function(){
        fn_save();
    });
    
    //삭제
    $("#btnDelete").click(function(){
        fn_delete();
    });
	
	// create Tree Grid
    $("#treeGrid").jqxTreeGrid({
        width: '100%',
        height: '100%',
        columnsResize: true,
        columns: [
                  { text: '코드명', datafield: 'codeName', width: '200px', cellsalign: 'left', align: 'center'},
                  { text: '코드', datafield: 'systemCode', width: '100px', cellsalign: 'left', align: 'center'},
                  
                  { text: '표시순번', datafield: 'codeSeq', width: '70px', cellsalign: 'center', align: 'center'},
                  { text: '유효시작일자', datafield: 'vldStartDate', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '유효종료일자', datafield: 'vldExpireDate', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '사용여부', datafield: 'useYn', width: '70px', cellsalign: 'center', align: 'center' },
                  { text: '확장코드1', datafield: 'codeExtend1', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '확장코드2', datafield: 'codeExtend2', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '확장코드3', datafield: 'codeExtend3', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '확장코드4', datafield: 'codeExtend4', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '확장코드5', datafield: 'codeExtend5', width: '100px', cellsalign: 'center', align: 'center' },
                                           
                  { text: 'systemGrpCode', datafield: 'systemGrpCode', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'treeCode', datafield: 'treeCode', width: '100px', cellsalign: 'center', align: 'center', hidden : true },
                  { text: 'treeParentCode', datafield: 'treeParentCode', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'treeLvl', datafield: 'treeLvl', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
        
    });
	
	//event
    $('#treeGrid').on('rowSelect', function (event) {
    	var data = event.args.row;
        
        //상세이동
        fn_searchDetail(data);
    }).on('bindingComplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    //조회
    fn_search();
});


/*******************************************
 * 기능 함수 
 *******************************************/

<%-- 검색 --%>
function fn_search(){
    
    //page setting  
    var url = "<c:url value='/apt/setting/selectSystemCodeList.ajax'/>";
    var param = $("#SystemCodeManageVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    //로딩 호출
    gfn_setLoading(true);
    
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
    
    var source =
    {
        datatype: "json",
        hierarchy:  {
            keyDataField: { name: 'treeCode' },
            parentDataField: { name: 'treeParentCode' }
        },
        id : 'treeCode',
        localdata: resultList
    };

    var dataAdapter = new $.jqx.dataAdapter(source);
    
    $("#treeGrid").jqxTreeGrid({source: dataAdapter});
}

<%-- 상세이동 --%>
function fn_searchDetail(data){
	//상세 화면 셋팅
    fn_setDetailDiv(data);
    
    var systemGrpCode = data.systemGrpCode;
    var systemCode = data.systemCode;
    
    if(data.treeLvl == "0"){
    	systemCode = "";
    }
    
    $("#systemGrpCode").val(systemGrpCode);
    $("#systemCode").val(systemCode);
    
    //page setting  
    var url = "<c:url value='/apt/setting/selectSystemCodeDtl.ajax'/>";
    if(data.treeLvl == "0") url = "<c:url value='/apt/setting/selectSystemGrpCodeDtl.ajax'/>";
    var param = $("#SystemCodeManageVO").serialize();
    var callBackFunc = "fn_searchDetailCallBack";
    
    //로딩 호출
    var msg = "그룹코드 상세 조회 중입니다.";
    if(data.treeLvl == "0") msg = "코드 상세 조회 중입니다.";
    gfn_setLoading(true, msg);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchDetailCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    var resultDetail = data.resultDetail;
    
    var treeData = $("#treeGrid").jqxTreeGrid('getSelection')[0];
    var treeLvl = treeData.treeLvl;
    
    if(resultDetail != null){    	
    	//그룹코드
    	if(treeLvl == "0"){
    		$("#grpCode_systemGrpCode").val(resultDetail.systemGrpCode);
    		$("#grpCode_systemGrpCodeDesc").val(resultDetail.systemGrpCodeDesc);
    		$("#grpCode_dateFrom").val(resultDetail.vldStartDate);
    		$("#grpCode_dateTo").val(resultDetail.vldExpireDate);
    		
    		$('input:radio[name=grpCode_useYn]:input[value=' + resultDetail.useYn + ']').attr("checked", true);
    		
    		$("#grpCode_createDate").html(resultDetail.createDate);
    		$("#grpCode_updateDate").html(resultDetail.updateDate);
    		
    		$("#grpCode_createDateTr").show();
    	    $("#grpCode_updateDateTr").show();
    	    
    	    $("#grpCode_systemGrpCode").attr("disabled",true);
    	//일반
    	}else{
    		$("#code_systemGrpCode").val(resultDetail.systemGrpCode);
    		$("#code_systemCode").val(resultDetail.systemCode);
    		$("#code_codeNameKor").val(resultDetail.codeNameKor);
    		$("#code_codeNameEng").val(resultDetail.codeNameEng);
    		$("#code_codeSeq").val(resultDetail.codeSeq);
    		$("#code_dateFrom").val(resultDetail.vldStartDate);
            $("#code_dateTo").val(resultDetail.vldExpireDate);
            
            $('input:radio[name=code_useYn]:input[value=' + resultDetail.useYn + ']').attr("checked", true);
                        
            $("#code_codeExtend1").val(resultDetail.codeExtend1);
            $("#code_codeExtend2").val(resultDetail.codeExtend2);
            $("#code_codeExtend3").val(resultDetail.codeExtend3);
            $("#code_codeExtend4").val(resultDetail.codeExtend4);
            $("#code_codeExtend5").val(resultDetail.codeExtend5);
            
            $("#code_createDate").html(resultDetail.createDate);
            $("#code_updateDate").html(resultDetail.updateDate);
            
            $("#code_createDateTr").show();
            $("#code_updateDateTr").show();  
            
            $("#code_systemGrpCode").attr("disabled",true);
            $("#code_systemCode").attr("disabled",true);
    	}
    	
    	//삭제버튼 활성화
    	$("#btnDeleteSpan").show();	
    	
    	g_saveFlag = "UPD";
    	g_saveLvl = treeLvl;
    }else{
    	//삭제버튼 비활성화
        $("#btnDeleteSpan").hide();
    	
        g_saveFlag = "INS";
        g_saveLvl = treeLvl;
    }
    
    //로딩 호출
    gfn_setLoading(false);
}

<%-- 상세보기 코드 div 셋팅 --%>
function fn_setDetailDiv(data){
    var lvl = data.treeLvl;
    
    $("#grpCode_createDateTr").hide();
    $("#grpCode_updateDateTr").hide();
    
    $("#code_createDateTr").hide();
    $("#code_updateDateTr").hide();
    
    //grpCode
    if(lvl == "0"){
        $("#grpCodeDiv").show();
        $("#codeDiv").hide();
        
        $("#detailTitle").html("그룹코드 상세");
    //code
    }else{
    	$("#grpCodeDiv").hide();        
        $("#codeDiv").show();
        
        $("#detailTitle").html("코드 상세");
    }
    
    //data 리셋
    $("#systemGrpCode").val("");
    $("#systemGrpCodeDesc").val("");
    $("#vldStartDate").val("");
    $("#vldExpireDate").val("");
    $("#useYn").val("");
    
    $("#systemCode").val("");
    $("#codeNameKor").val("");
    $("#codeNameEng").val("");
    $("#codeSeq").val("");
    $("#codeExtend1").val("");
    $("#codeExtend2").val("");
    $("#codeExtend3").val("");
    $("#codeExtend4").val("");
    $("#codeExtend5").val("");
    
    //화면 리셋
    $("#grpCode_systemGrpCode").val("");
    $("#grpCode_systemGrpCodeDesc").val("");
    $("#grpCode_dateFrom").val("");
    $("#grpCode_dateTo").val("");
    $('input:radio[name=grpCode_useYn]:input[value=N]').attr("checked", true);
    $("#grpCode_createDate").html("");
    $("#grpCode_updateDate").html("");
    
    $("#grpCode_systemGrpCode").attr("disabled",false);
    
    $("#code_systemGrpCode").val("");
    $("#code_systemCode").val("");
    $("#code_codeNameKor").val("");
    $("#code_codeNameEng").val("");
    $("#code_codeSeq").val("1");
    $("#code_dateFrom").val("");
    $("#code_dateTo").val("");
    $('input:radio[name=code_useYn]:input[value=N]').attr("checked", true);
    $("#code_codeExtend1").val("");
    $("#code_codeExtend2").val("");
    $("#code_codeExtend3").val("");
    $("#code_codeExtend4").val("");
    $("#code_codeExtend5").val("");
    
    $("#code_createDate").html("");
    $("#code_updateDate").html("");    
    
    $("#code_systemGrpCode").attr("disabled",false);
    $("#code_systemCode").attr("disabled",false);
}   

<%-- 저장 --%>
function fn_save(){
    if(!fn_validate()){
        return;
    }
    
    var moveUrl = "";
    var param = $("#SystemCodeManageVO").serialize();
    var callBackFunc = "";
    var msg = "";
    
    //등록전 체크
    if(g_saveFlag == "INS"){
    	moveUrl = "<c:url value='/apt/setting/chkSystemCodeDtl.ajax'/>";
        if(g_saveLvl == "0") moveUrl = "<c:url value='/apt/setting/chkSystemGrpCodeDtl.ajax'/>";
        
        callBackFunc = "fn_saveChkCallBack";
        
        msg = "코드의 등록 가능 여부를 체크 중입니다.";
        if(g_saveLvl == "0") msg = "그룹코드의 등록 가능 여부를 체크 중입니다.";
        
    //수정
    }else{
    	moveUrl = "<c:url value='/apt/setting/updateSystemCode.ajax'/>";
        if(g_saveLvl == "0") moveUrl = "<c:url value='/apt/setting/updateSystemGrpCode.ajax'/>";
        
        callBackFunc = "fn_saveCallBack";
        
        msg = "코드 수정 중입니다.";
        if(g_saveLvl == "0") msg = "그룹코드 수정 중입니다.";
    }
    
    //로딩 호출
    gfn_setLoading(true, msg);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveChkCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
	var result = data.result;
	
	//코드그룹
	if(g_saveLvl == "0"){
		//실패
		if(result > 0){
			alert("<spring:message code='errors.invalid' arguments='코드그룹 ID'/>");
            $("#grpCode_systemGrpCode").focus();
            return;
		}
	//코드
	}else{
		//실패
        if(result > 0){
            alert("<spring:message code='errors.invalid' arguments='코드 ID'/>");
            $("#code_systemCode").focus();
            return;
        }
	}
	
	if(!confirm("<spring:message code='confirm.regist.msg' />")){
        return;
    }
    
    var moveUrl = "<c:url value='/apt/setting/insertSystemCode.ajax'/>";
    if(g_saveLvl == "0") moveUrl = "<c:url value='/apt/setting/insertSystemGrpCode.ajax'/>";
    var param = $("#SystemCodeManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    
    //로딩 호출
    var msg = "코드 등록 중입니다.";
    if(g_saveLvl == "0") msg = "그룹코드 등록 중입니다.";
    gfn_setLoading(true, msg);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
	var result = data.result;
	
	if(result <= 0){
        if(g_saveFlag == "INS"){
            alert("<spring:message code='fail.alert.regist' />");
        }else{
            alert("<spring:message code='fail.alert.modify' />");
        }
        return;
    }else{
        if(g_saveFlag == "INS"){
            alert("<spring:message code='success.alert.regist' />");
        }else{
            alert("<spring:message code='success.alert.modify' />");
        }
    }
	
	//코드그룹
    if(g_saveLvl == "0"){
    	if(g_saveFlag == "INS"){
    		$("#grpCode_systemGrpCode").attr("disabled",true);
    		g_saveFlag = "UPD";
    		
    		//삭제버튼 활성화
            $("#btnDeleteSpan").show();
    	}
    }else{
    	if(g_saveFlag == "INS"){
            $("#code_systemGrpCode").attr("disabled",true);
            $("#code_systemCode").attr("disabled",true);
            g_saveFlag = "UPD";
            
            //삭제버튼 활성화
            $("#btnDeleteSpan").show();
        }
    }
	
    fn_search();
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//그룹코드
	if(g_saveLvl == "0"){
		//코드그룹 ID
        if(util_chkReturn($.trim($('#grpCode_systemGrpCode').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='코드그룹 ID'/>");
            $('#grpCode_systemGrpCode').focus();
            return false;
        }
		
		//코드명
        if(util_chkReturn($.trim($('#grpCode_systemGrpCodeDesc').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='코드명'/>");
            $('#grpCode_systemGrpCodeDesc').focus();
            return false;
        }else{
        	if(util_calcBytes($.trim($('#grpCode_systemGrpCodeDesc').val())) > 100){
                alert("<spring:message code='errors.maxlength' arguments='코드명,100'/>");
                $('#grpCode_systemGrpCodeDesc').focus();
                return false;
            }
        }
		
		//유효일자
        var dateFrom = util_replaceAll($("#grpCode_dateFrom").val(), "-", "");
        var dateTo = util_replaceAll($("#grpCode_dateTo").val(), "-", "");
                
        if(dateFrom == ""){
            alert("<spring:message code='errors.required' arguments='유효 시작일' />");
            $("#grpCode_dateFrom").focus();
            return false;
        }else{
        	if(dateFrom.length != 8){
        		alert("<spring:message code='errors.invalid' arguments='유효 시작일' />");
                $("#grpCode_dateFrom").focus();
                return false;
        	}
            if(!util_isDate(dateFrom)){
                alert("<spring:message code='errors.invalid' arguments='유효 시작일' />");
                $("#grpCode_dateFrom").focus();
                return false;
            }
        }
        
        if(dateTo == ""){
            alert("<spring:message code='errors.required' arguments='유효 종료일' />");
            $("#grpCode_dateTo").focus();
            return false;
        }else{
        	if(dateTo.length != 8){
                alert("<spring:message code='errors.invalid' arguments='유효 종료일' />");
                $("#grpCode_dateTo").focus();
                return false;
            }
            if(!util_isDate(dateTo)){
                alert("<spring:message code='errors.invalid' arguments='유효 종료일' />");
                $("#grpCode_dateTo").focus();
                return false;
            }else{
                if(dateFrom > dateTo){
                    alert("종료일이 시작일보다 클 수 없습니다.");
                    $("#grpCode_dateTo").focus();
                    return false;
                }
            }   
        }
		
		//사용여부
		if(util_chkReturn($.trim($('input[name=grpCode_useYn]:checked').val()), "s") == "") {
	        alert("<spring:message code='errors.required.select' arguments='사용여부'/>");
	        $('#grpCode_useYn_Y').focus();
	        return false;
	    }
		
		//데이터 셋팅
		$("#systemGrpCode").val($('#grpCode_systemGrpCode').val());
	    $("#systemGrpCodeDesc").val($('#grpCode_systemGrpCodeDesc').val());
	    $("#vldStartDate").val(dateFrom);
	    $("#vldExpireDate").val(dateTo);
	    $("#useYn").val($('input[name=grpCode_useYn]:checked').val());
		
	}else{
		//코드그룹 ID
        if(util_chkReturn($.trim($('#code_systemGrpCode').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='코드그룹 ID'/>");
            $('#code_systemGrpCode').focus();
            return false;
        }
		
		//코드 ID
		if(util_chkReturn($.trim($('#code_systemCode').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='코드 ID'/>");
            $('#code_systemCode').focus();
            return false;
        }
		
		//코드명(한글)
        if(util_chkReturn($.trim($('#code_codeNameKor').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='코드명(한글)'/>");
            $('#code_codeNameKor').focus();
            return false;
        }else{
            if(util_calcBytes($.trim($('#code_codeNameKor').val())) > 100){
                alert("<spring:message code='errors.maxlength' arguments='코드명(한글),100'/>");
                $('#code_codeNameKor').focus();
                return false;
            }
        }
		
        //코드명(영문)
        if(util_chkReturn($.trim($('#code_codeNameEng').val()), "s") != "") {
            if(util_calcBytes($.trim($('#code_codeNameEng').val())) > 100){
                alert("<spring:message code='errors.maxlength' arguments='코드명(영문),100'/>");
                $('#code_codeNameEng').focus();
                return false;
            }
        }
        
        //표시순번
        if(util_chkReturn($.trim($('#code_codeSeq').val()), "s") == "") {
	        alert("<spring:message code='errors.required' arguments='표시순번'/>");
	        $('#code_codeSeq').focus();
	        return false;
	    }else{
	        if(!util_isNum($.trim($('#code_codeSeq').val()))){
	            alert("<spring:message code='errors.integer' arguments='표시순번'/>");
	            $('#code_codeSeq').focus();
	            return false;           
	        }
	    }
        
        //유효일자
        var dateFrom = util_replaceAll($("#code_dateFrom").val(), "-", "");
        var dateTo = util_replaceAll($("#code_dateTo").val(), "-", "");
                
        if(dateFrom == ""){
            alert("<spring:message code='errors.required' arguments='유효 시작일' />");
            $("#code_dateFrom").focus();
            return false;
        }else{
        	if(dateFrom.length != 8){
                alert("<spring:message code='errors.invalid' arguments='유효 시작일' />");
                $("#code_dateFrom").focus();
                return false;
            }
            if(!util_isDate(dateFrom)){
                alert("<spring:message code='errors.invalid' arguments='유효 시작일' />");
                $("#code_dateFrom").focus();
                return false;
            }
        }
        
        if(dateTo == ""){
            alert("<spring:message code='errors.required' arguments='유효 종료일' />");
            $("#code_dateTo").focus();
            return false;
        }else{
        	if(dateFrom.length != 8){
                alert("<spring:message code='errors.invalid' arguments='유효 종료일' />");
                $("#code_dateTo").focus();
                return false;
            }
            if(!util_isDate(dateTo)){
                alert("<spring:message code='errors.invalid' arguments='유효 종료일' />");
                $("#code_dateTo").focus();
                return false;
            }else{
                if(dateFrom > dateTo){
                    alert("종료일이 시작일보다 클 수 없습니다.");
                    $("#code_dateTo").focus();
                    return false;
                }
            }   
        }
        
        //사용여부
        if(util_chkReturn($.trim($('input[name=code_useYn]:checked').val()), "s") == "") {
            alert("<spring:message code='errors.required.select' arguments='사용여부'/>");
            $('#code_useYn_Y').focus();
            return false;
        }
        
        //확장코드 
        for(var i=1; i<=5; i++){
	        if(util_chkReturn($.trim($('#code_codeExtend'+i).val()), "s") != "") {
	        	if(util_calcBytes($.trim($('#code_codeExtend'+i).val())) > 100){
	                alert("<spring:message code='errors.maxlength' arguments='확장코드"+i+",100'/>");
	                $('#code_codeExtend'+i).focus();
	                return false;
	            }
	        }
        }
      
        //데이터 셋팅
        $("#systemGrpCode").val($('#code_systemGrpCode').val());
        $("#systemCode").val($('#code_systemCode').val());
        $("#codeNameKor").val($('#code_codeNameKor').val());
        $("#codeNameEng").val($('#code_codeNameEng').val());
        $("#codeSeq").val($('#code_codeSeq').val());
        $("#vldStartDate").val(dateFrom);
        $("#vldExpireDate").val(dateTo);
        $("#useYn").val($('input[name=code_useYn]:checked').val());
        $("#codeExtend1").val($('#code_codeExtend1').val());
        $("#codeExtend2").val($('#code_codeExtend2').val());
        $("#codeExtend3").val($('#code_codeExtend3').val());
        $("#codeExtend4").val($('#code_codeExtend4').val());
        $("#codeExtend5").val($('#code_codeExtend5').val());
        
	}
	
	return true;
}

<%-- 삭제 --%>
function fn_delete(){
    if(!confirm("<spring:message code='confirm.delete.msg' />")){
        return;
    }
    
    if(g_saveLvl == "0"){
    	$("#systemGrpCode").val($('#grpCode_systemGrpCode').val());
    }else{
    	$("#systemGrpCode").val($('#code_systemGrpCode').val());
        $("#systemCode").val($('#code_systemCode').val());
    }
    
    var moveUrl = "<c:url value='/apt/setting/deleteSystemCode.ajax'/>";
    if(g_saveLvl == "0") moveUrl = "<c:url value='/apt/setting/deleteSystemGrpCode.ajax'/>";
    var param = $("#SystemCodeManageVO").serialize();
    var callBackFunc = "fn_deleteCallBack";
    
    //로딩 호출
    var msg = "코드 삭제 중입니다.";
    if(g_saveLvl == "0") msg = "그룹코드 삭제 중입니다.";
    gfn_setLoading(true);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_deleteCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
    if(data.result <= -1){
        alert("<spring:message code='fail.alert.delete' />");
    }else{
        alert("<spring:message code='success.alert.delete' />");
    }

    fn_setDetailDiv({treeLvl : '0'});
    fn_search();
}
</script>

</head>

<body>
<form:form commandName="SystemCodeManageVO" name="SystemCodeManageVO" method="post">
<%-- 그룹 코드 --%>
<input type="hidden" name="systemGrpCode" id="systemGrpCode" />
<input type="hidden" name="systemGrpCodeDesc" id="systemGrpCodeDesc" />
<input type="hidden" name="vldStartDate" id="vldStartDate" />
<input type="hidden" name="vldExpireDate" id="vldExpireDate" />
<input type="hidden" name="useYn" id="useYn" />

<%-- 상세코드 --%>
<input type="hidden" name="systemCode" id="systemCode" />
<input type="hidden" name="codeNameKor" id="codeNameKor" />
<input type="hidden" name="codeNameEng" id="codeNameEng" />
<input type="hidden" name="codeSeq" id="codeSeq" />
<input type="hidden" name="codeExtend1" id="codeExtend1" />
<input type="hidden" name="codeExtend2" id="codeExtend2" />
<input type="hidden" name="codeExtend3" id="codeExtend3" />
<input type="hidden" name="codeExtend4" id="codeExtend4" />
<input type="hidden" name="codeExtend5" id="codeExtend5" />


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
                    <h2>공통 관리</h2>
                </div>
                <!-- // locatioin -->                
                <div class="section_wrap type2">
                    <div class="section">
                        <h3>코드</h3>
                    
                        <!-- 2016-05-20 추가 -->
                        <div class="btn_type_onoff">
                            <span class="btn_small2"><a href="javascript:void(0);" id="btnExpandAll">+ 열기</a></span>
                            <span class="btn_small2"><a href="javascript:void(0);" id="btnCollapseAll">- 닫기</a></span>
                        </div>
                                            
                        <!-- contents -->
                        <div style="height:450px; border:1px solid #ddd;">
                            <div id="treeGrid"></div>
                        </div>
                        
                        <!-- // contents -->
                        <div class="btn_type3">
                            <div class="left">
                                <!-- <span class="btn_gray1"><a href="javascript:void(0);" id="btnExpandAll2">엑셀</a></span> -->
                            </div>
                            <div class="right">
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnGrpCodeReg">그룹코드 등록</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnCodeReg">코드 등록</a></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="section">
                        <!-- contents -->
                        <div class="tb_write1 ml20">
                            <%-- title --%>
                            <h3 id="detailTitle">그룹코드 상세</h3>
                        
                            <table>
                                <caption>정보 입력</caption>
                                <colgroup>
                                    <col style="width:30%;">
                                    <col style="width:*;">
                                </colgroup>
                                
                                <%-- 그룹코드 --%>
                                <tbody id="grpCodeDiv">
                                <tr>
                                    <th scope="row"><label for="grpCode_systemGrpCode">코드그룹 ID</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l"><input type="text" id="grpCode_systemGrpCode" maxlength="4"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="grpCode_systemGrpCodeDesc">코드명</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l"><input type="text" id="grpCode_systemGrpCodeDesc"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="grpCode_dateFrom">유효일자</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <input type="text" id="grpCode_dateFrom" style="width:80px;" maxlength="10"/>
		                                <span class="dateDot">~</span>
		                                <input type="text" id="grpCode_dateTo" style="width:80px;" maxlength="10"/>
                                    </td>
                                </tr>
                                <tr>
		                            <th scope="row">사용여부<span class="icon_basic">*필수입력</span></th>
		                            <td class="txt_l">
		                                <input type="radio" id="grpCode_useYn_Y" name="grpCode_useYn" value="Y"> <label for="grpCode_useYn_Y"> 사용 </label>
		                                <input type="radio" id="grpCode_useYn_N" name="grpCode_useYn" value="N" checked="checked"> <label for="grpCode_useYn_N"> 미사용 </label>
		                            </td>                  
		                        </tr>
                                <tr id="grpCode_createDateTr" style="display: none;">
                                    <th scope="row">등록일시</th>
                                    <td class="txt_l" id="grpCode_createDate"></td>                  
                                </tr>
                                <tr id="grpCode_updateDateTr" style="display: none;">
                                    <th scope="row">수정일시</th>
                                    <td class="txt_l" id="grpCode_updateDate"></td>                  
                                </tr>
                                </tbody>
                                
                                <%-- 상세코드 --%>
                                <tbody id="codeDiv" style="display: none;">
                                    <tr>
	                                    <th scope="row">코드그룹 ID<span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l"><input type="text" id="code_systemGrpCode" disabled="disabled"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_systemCode">코드 ID</label><span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l"><input type="text" id="code_systemCode" maxlength="3"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeNameKor">코드명(한글)</label><span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l"><input type="text" id="code_codeNameKor"></td>
	                                </tr>
	                                <tr>
                                        <th scope="row"><label for="code_codeNameEng">코드명(영문)</label></th>
                                        <td class="txt_l"><input type="text" id="code_codeNameEng"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="code_codeSeq">표시순번</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l"><input type="text" id="code_codeSeq" maxlength="3"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="code_dateFrom">유효일자</label><span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l">
	                                        <input type="text" id="code_dateFrom" style="width:80px;" maxlength="10"/>
	                                        <span class="dateDot">~</span>
	                                        <input type="text" id="code_dateTo" style="width:80px;" maxlength="10"/>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <th scope="row">사용여부<span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l">
	                                        <input type="radio" id="code_useYn_Y" name="code_useYn" value="Y"> <label for="code_useYn_Y"> 사용 </label>
	                                        <input type="radio" id="code_useYn_N" name="code_useYn" value="N" checked="checked"> <label for="code_useYn_N"> 미사용 </label>
	                                    </td>                  
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeExtend1">확장코드1</label></th>
	                                    <td class="txt_l"><input type="text" id="code_codeExtend1" maxlength="100"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeExtend2">확장코드2</label></th>
	                                    <td class="txt_l"><input type="text" id="code_codeExtend2" maxlength="100"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeExtend3">확장코드3</label></th>
	                                    <td class="txt_l"><input type="text" id="code_codeExtend3" maxlength="100"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeExtend4">확장코드4</label></th>
	                                    <td class="txt_l"><input type="text" id="code_codeExtend4" maxlength="100"></td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><label for="code_codeExtend5">확장코드5</label></th>
	                                    <td class="txt_l"><input type="text" id="code_codeExtend5" maxlength="100"></td>
	                                </tr>
	                                <tr id="code_createDateTr" style="display: none;">
                                        <th scope="row">등록일시</th>
                                        <td class="txt_l" id="code_createDate"></td>                  
                                    </tr>
                                    <tr id="code_updateDateTr" style="display: none;">
                                        <th scope="row">수정일시</th>
                                        <td class="txt_l" id="code_updateDate"></td>                  
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- // contents -->
                        <div class="btn_type3">
                            <div class="right">
                                <span class="btn_gray2" id="btnDeleteSpan" style="display: none;"><a href="javascript:void(0);" id="btnDelete">삭제</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                            </div>
                        </div>
                    </div>
            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>
</body>
</html>