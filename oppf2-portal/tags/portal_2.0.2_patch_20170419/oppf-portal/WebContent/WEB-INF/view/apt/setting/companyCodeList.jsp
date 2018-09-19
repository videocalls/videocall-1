
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : companyCodeList.jsp
 * @Description : 기업코드 관리
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

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/setting/companyCodeList.do'/>";
    var param = new Object();
    param.paramMenuId = "09001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
	
	//엑셀
	$("#btnExcel").click(function(){
		$("#jqxgrid").jqxGrid('exportdata', 'xls', 'companyCode_'+util_getDate());
    });
	
	//등록
	$("#btnReg").click(function(){
		fn_reset();
        
        $("#companyCodeType").focus();
	});
	
	//저장
    $("#btnSave").click(function(){
        fn_save();
    });
	
    //삭제
    $("#btnDelete").click(function(){
    	fn_chkDelete();
    });
        
    // tab_menu 
    if($(".tab_menu").length > 0){
        $(".tab_cont:not(:first)").hide();
        $(".tab_menu li a").click(function() {
        	$(".tab_menu li").removeClass("on");
            $(this).parent().addClass("on");
            
            var id = $(this).attr("id");
            id = util_replaceAll(id, "tab_", "");
            
            $("#searchCompanyCodeKind").val(id);
            
            fn_reset();
            fn_search();
            
            return false;
        });
    }
	
	//그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '코드표 구분', datafield: 'companyCodeType', width: '5%', cellsalign: 'center', align: 'center', pinned: true },
            { text: '기업 분류', datafield: 'companyDivCode', width: '7%', cellsalign: 'center', align: 'center', pinned: true },
            { text: '기업 코드', datafield: 'companyCodeId', width: '10%', cellsalign: 'left', align: 'center', pinned: true },
            { text: '기업이름(한글)', datafield: 'companyNameKor', width: '30%', cellsalign: 'left', align: 'center', pinned: true },
            { text: '기업이름(영문)', datafield: 'companyNameEng', width: '30%', cellsalign: 'left', align: 'center' },
            { text: '기업이름 Alias(한글)', datafield: 'companyNameKorAlias', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '기업이름 Alias(영문)', datafield: 'companyNameEngAlias', width: '10%', cellsalign: 'left', align: 'center' },
            
            { text: '삭제여부', datafield: 'deleteType', width: '50px', cellsalign: 'center', align: 'center', hidden : true },     
            { text: '기업 종류', datafield: 'companyCodeKind', width: '70px', cellsalign: 'center', align: 'center', hidden: true },
            { text: 'companyCodeRegNo', datafield: 'companyCodeRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    });
	
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        //상세이동
        fn_searchDetail(data);
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    //탭정보 세팅
    var tabId = $(".tab_menu li a").attr("id");
    $("#"+tabId).parent().addClass("on");                
    $("#searchCompanyCodeKind").val(util_replaceAll(tabId, "tab_", ""));
    
    //조회
    fn_reset();
    fn_search();
});


/*******************************************
 * 기능 함수 
 *******************************************/
<%-- reset --%>
function fn_reset(){
	$("#companyCodeType").val("");
    $("#companyCodeKind").val("");
    $("#companyDivCode").val("");
    $("#companyCodeId").val("");
    $("#companyNameKor").val("");
    $("#companyNameEng").val("");
    $("#companyNameKorAlias").val("");
    $("#companyNameEngAlias").val("");
    
    $("#companyCodeRegNo").val("");
    
    //기업코드
    $("#companyCodeId").attr("disabled",false);
    
    //삭제버튼 숨김
    $("#btnDeleteSpan").hide();
    
    //기업종류 셋팅
    $("#companyCodeKind").val($("#searchCompanyCodeKind").val());
    
}
 
<%-- 검색 --%>
function fn_search(){
	$("#companyCodeRegNo").val("");
	
    //page setting  
    var url = "<c:url value='/apt/setting/selectCompanyCodeList.ajax'/>";
    var param = $("#CompanyCodeManageVO").serialize();
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
    
    //grid common
    gfn_gridData(resultList);
}

<%-- 상세이동 --%>
function fn_searchDetail(data){
    $("#companyCodeRegNo").val(data.companyCodeRegNo);
    
    //page setting  
    var url = "<c:url value='/apt/setting/selectCompanyCodeDtl.ajax'/>";
    var param = $("#CompanyCodeManageVO").serialize();
    var callBackFunc = "fn_searchDetailCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "상세 조회 중입니다.");
    
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
	
	if(resultDetail != null){
		$("#companyCodeType").val(resultDetail.companyCodeType);
		$("#companyCodeKind").val(resultDetail.companyCodeKind);
		$("#companyDivCode").val(resultDetail.companyDivCode);
		$("#companyCodeId").val(resultDetail.companyCodeId);
		$("#companyNameKor").val(resultDetail.companyNameKor);
		$("#companyNameEng").val(resultDetail.companyNameEng);
		$("#companyNameKorAlias").val(resultDetail.companyNameKorAlias);
		$("#companyNameEngAlias").val(resultDetail.companyNameEngAlias);
		
		//기업코드
		$("#companyCodeId").attr("disabled",true);
				
		//삭제버튼 show
        $("#btnDeleteSpan").show();
		
	}else{
		$("#companyCodeId").attr("disabled",false);
		
		//삭제버튼 숨김
	    $("#btnDeleteSpan").hide();
	}
	
	//로딩 호출
    gfn_setLoading(false);
}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
    
    var moveUrl = "<c:url value='/apt/setting/chkCompanyCodeDtl.ajax'/>";
    var param = param = $("#CompanyCodeManageVO").serialize();
    var callBackFunc = "fn_saveChkCallBack";
    
    if($("#companyCodeId").prop("disabled")){
    	$("#companyCodeId").attr("disabled",false);
    	param = $("#CompanyCodeManageVO").serialize();
    	$("#companyCodeId").attr("disabled",true);
    }
    
    //로딩 호출
    gfn_setLoading(true, "기업코드 체크 중입니다.");
        
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
	
	var resultList = data.resultList;
	var flag = true;
	
	if(resultList != null){
		for(var i=0; i<resultList.length; i++){
			if(resultList[i].chkResult == "N"){
				flag = false;
				if(resultList[i].chkType == "companyCodeId"){
					//alert("<spring:message code='errors.invalid' arguments='기업 코드'/>");
					alert("중복된 기업 코드입니다.\n기업 코드를 확인 해 주세요.");
					$("#companyCodeId").focus();
				}else if(resultList[i].chkType == "companyNameEngAlias"){
					alert("<spring:message code='errors.invalid' arguments='기업 이름 Alias(영문)'/>");
					$("#companyNameEngAlias").focus();
				}
				break;
			}
		}
		
		if(flag){
			if(!confirm("<spring:message code='confirm.save.msg' />")){
				return;
			}
			
			var moveUrl = "<c:url value='/apt/setting/insertCompanyCode.ajax'/>";
			if(util_chkReturn($.trim($('#companyCodeRegNo').val()), "s") != "") moveUrl = "<c:url value='/apt/setting/updateCompanyCode.ajax'/>";
		    var param = $("#CompanyCodeManageVO").serialize();
		    var callBackFunc = "fn_saveCallBack";
		    
		    //로딩 호출
		    var msg = "등록 중입니다.";
		    if(util_chkReturn($.trim($('#companyCodeRegNo').val()), "s") != "") msg = "수정 중입니다.";
	        gfn_setLoading(true, msg);
		    
		    <%-- 공통 ajax 호출 --%>
		    util_ajaxPage(moveUrl, param, callBackFunc);
		}else{
			return;
		}
		
	}else{
		alert("<spring:message code='fail.alert.select' />");
        return;
	}
}
function fn_saveCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
	//로딩 호출
    gfn_setLoading(false);
	
	//insert
    if(util_chkReturn($.trim($('#companyCodeRegNo').val()), "s") == ""){
    	var companyCodeRegNo = data.companyCodeRegNo;
    	
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            return;
        }else{
            alert("<spring:message code='success.alert.regist' />");
            
            //목록 조회
            fn_search();
            
            $("#companyCodeRegNo").val(companyCodeRegNo);
            $("#companyCodeId").attr("disabled",true);
            
            //삭제버튼 표시
            $("#btnDeleteSpan").show();
            
            //탭 스왑
            var tabId = "tab_"+$("#companyCodeKind").val();
            $(".tab_menu li").removeClass("on");
            $("#"+tabId).parent().addClass("on");
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
            fn_search();
        }
    }
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//기업 코드표 구분
    if(util_chkReturn($.trim($('#companyCodeType').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='기업 코드표 구분'/>");
        $('#companyCodeType').focus();
        return false;
    }
	
    //기업 종류
    if(util_chkReturn($.trim($('#companyCodeKind').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='기업 종류'/>");
        $('#companyCodeKind').focus();
        return false;
    }
    
    //기업 분류
    if(util_chkReturn($.trim($('#companyDivCode').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='기업 분류'/>");
        $('#companyDivCode').focus();
        return false;
    }
    
    //기업 코드
    if(util_chkReturn($.trim($('#companyCodeId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업코드'/>");
        $('#companyCodeId').focus();
        return false;
    }else{
    	var maxLen = 5;
    	//핀테크기업의 길이는 8자리
    	if($('#companyCodeKind').val() == "G014002"){
    		maxLen = 8;
    	}
    	
        if(util_calcBytes($.trim($('#companyCodeId').val())) > maxLen){
            alert("<spring:message code='errors.maxlength' arguments='기업코드,"+maxLen+"'/>");
            $('#companyCodeId').focus();
            return false;
        }
    }
    
    //기업이름(한글)
    if(util_chkReturn($.trim($('#companyNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업이름(한글)'/>");
        $('#companyNameKor').focus();
        return false;
    }else{
        if(util_calcBytes($.trim($('#companyNameKor').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='기업이름(한글),100'/>");
            $('#companyNameKor').focus();
            return false;
        }
    }
    
    //기업이름(영문)
    if(util_chkReturn($.trim($('#companyNameEng').val()), "s") != "") {
        if(util_calcBytes($.trim($('#companyNameEng').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='기업이름(영문),100'/>");
            $('#companyNameEng').focus();
            return false;
        }
    }
    
    //기업 이름 Alias(한글)
    if(util_chkReturn($.trim($('#companyNameKorAlias').val()), "s") != "") {
        if(util_calcBytes($.trim($('#companyNameKorAlias').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='기업 이름 Alias(한글),100'/>");
            $('#companyNameKorAlias').focus();
            return false;
        }
    }
    
    //기업 이름 Alias(영문)
    if(util_chkReturn($.trim($('#companyNameEngAlias').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업 이름 Alias(영문)'/>");
        $('#companyNameEngAlias').focus();
        return false;
    }else{
        if(util_calcBytes($.trim($('#companyNameEngAlias').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='기업 이름 Alias(영문),100'/>");
            $('#companyNameEngAlias').focus();
            return false;
        }
    }
    
    return true;
}

<%-- 삭제 전 체크 --%>
function fn_chkDelete(){
	var moveUrl = "<c:url value='/apt/setting/chkDeleteCompanyCode.ajax'/>";
    var param = $("#CompanyCodeManageVO").serialize();
    var callBackFunc = "fn_chkDeleteCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "삭제 체크 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_chkDeleteCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
		
	//삭제 처리
	fn_delete(data.result);
}

<%-- 삭제 --%>
function fn_delete(){
	if(data.result > 0){
        if(!confirm(data.result + "건의 기업정보가 등록되어 있습니다.\n<spring:message code='confirm.delete.msg' />")){
            return;
        }   
    }else{
    	if(!confirm("<spring:message code='confirm.delete.msg' />")){
            return;
        }	
    }
    
    var moveUrl = "<c:url value='/apt/setting/deleteCompanyCode.ajax'/>";
    var param = $("#CompanyCodeManageVO").serialize();
    var callBackFunc = "fn_deleteCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "삭제 중입니다.");
    
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
	
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.delete' />");
    }else{
        alert("<spring:message code='success.alert.delete' />");
    }

    fn_reset();
    fn_search();
}

</script>

</head>

<body>
<form:form commandName="CompanyCodeManageVO" name="CompanyCodeManageVO" method="post">
<input type="hidden" name="companyCodeRegNo" id="companyCodeRegNo" value="" />
<input type="hidden" name="searchCompanyCodeKind" id="searchCompanyCodeKind" value="" />

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
                    <h2>기업코드 관리</h2>
                </div>
                <!-- // locatioin -->                
                <div class="section_wrap">
                    <input type="hidden" name="searchDeleteType" value="N" />
                    
                    <div class="section">
                        
                        <!-- contents -->
                        <div class="tab_menu">
                            <ul>
                                <c:forEach items="${companyCodeKindList}" var="companyCodeKindList" varStatus="status">
                                    <li><a href="javascript:void(0);" id="tab_${companyCodeKindList.system_code}">${companyCodeKindList.code_name_kor}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        
                        <!-- contents -->
                        <div style="height:500px; border:1px solid #ddd;">
                            <div id="jqxgrid"></div>
                        </div>
                        
                        <!-- // contents -->
                        <div class="btn_type3">
                            <div class="left">
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                            </div>
                            <div class="right">
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnReg">등록</a></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="section">
                        <!-- contents -->
                        <div class="tb_write1 ml20">                            
                            <table>
                                <caption>정보 입력</caption>
                                <colgroup>
                                    <col style="width:30%;">
                                    <col style="width:*;">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th scope="row"><label for="companyCodeType">기업 코드표 구분</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <select title="기업 코드표 구분" id="companyCodeType" name="companyCodeType">
                                            <option value="">선택</option>
			                                <c:forEach items="${companyCodeTypeList}" var="companyCodeTypeList" varStatus="status">
			                                    <option value="${companyCodeTypeList.system_code}">${companyCodeTypeList.code_name_kor}</option>
			                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyCodeKind">기업 종류</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <select title="기업 종류" id="companyCodeKind" name="companyCodeKind">
                                            <option value="">선택</option>
                                            <c:forEach items="${companyCodeKindList}" var="companyCodeKindList" varStatus="status">
                                                <option value="${companyCodeKindList.system_code}">${companyCodeKindList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyDivCode">기업 분류</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <select title="기업 종류" id="companyDivCode" name="companyDivCode">
                                            <option value="">선택</option>
                                            <c:forEach items="${companyDivCodeList}" var="companyDivCodeList" varStatus="status">
                                                <option value="${companyDivCodeList.system_code}">${companyDivCodeList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyCodeId">기업 코드</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l"><input type="text" class="" id="companyCodeId" name="companyCodeId" style="ime-mode:disabled;" maxlength="8"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyNameKor">기업 이름(한글)</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l"><input type="text" class="" id="companyNameKor" name="companyNameKor"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyNameEng">기업 이름(영문)</label></th>
                                    <td class="txt_l"><input type="text" class="" id="companyNameEng" name="companyNameEng"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyNameKorAlias">기업 이름 Alias(한글)</label></th>
                                    <td class="txt_l"><input type="text" class="" id="companyNameKorAlias" name="companyNameKorAlias"></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyNameEngAlias">기업 이름 Alias(영문)</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l"><input type="text" class="" id="companyNameEngAlias" name="companyNameEngAlias"></td>
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