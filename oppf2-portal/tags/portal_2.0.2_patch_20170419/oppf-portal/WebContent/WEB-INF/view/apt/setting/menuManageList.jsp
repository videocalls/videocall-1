<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : menuManageList.jsp
 * @Description : 메뉴 관리
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.23  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.23
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

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_saveFlag = "INS";     <%-- 저장종류 : INS[등록], UPD[수정] --%>
var g_saveLvl = "1";        <%-- treeLvl과 동일값 --%>

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/setting/menuManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "09003";
    
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
    
    //대메뉴 등록
    $("#btnTopMenuReg").click(function(){
        //삭제버튼 비활성화
        $("#btnDeleteSpan").hide();
                
        g_saveFlag = "INS";
        g_saveLvl = "1";
        
        $("#treeLvl").val(g_saveLvl);
        
        //상세보기 리셋
        fn_initDetail();
    });
        
    //메뉴 등록
    $("#btnMenuReg").click(function(){
        var treeData = $("#treeGrid").jqxTreeGrid('getSelection')[0];
        var treeLvl = "";
        var parentMenuId = "";
        
        if(util_chkReturn(treeData, "s") == ""){
            alert("상위메뉴를 먼저 선택 해 주세요.");
            return;
        }else{
        	if(treeData.treeLvl == "3"){
                alert("3레벨 이하로 메뉴를 생성 할 수 없습니다.");
                return;
            }	
        }
        
        treeLvl = treeData.treeLvl;
        parentMenuId = treeData.menuId;
        
        g_saveFlag = "INS";
        g_saveLvl = Number(treeLvl)+1;
        $("#treeLvl").val(g_saveLvl);
        
        //상세보기 리셋
        fn_initDetail();
                
        //상위 메뉴 ID
        $("#parentMenuId").val(parentMenuId);
        //상위 메뉴 ID show
        $("#parentMenuIdTr").show();
    });
    
    //저장
    $("#btnSave").click(function(){
        fn_save();
    });
    
    //삭제
    $("#btnDelete").click(function(){
        fn_delete();
    });
	
	// tab_menu 
    if($(".tab_menu").length > 0){
        $(".tab_cont:not(:first)").hide();
        $(".tab_menu li a").click(function() {
            $(".tab_menu li").removeClass("on");
            $(this).parent().addClass("on");
            
            var id = $(this).attr("id");
            id = util_replaceAll(id, "tab_", "");
            
            $("#searchSystemKindId").val(id);
            fn_initDetail();
            
            fn_search();
            
            return false;
        });
    }
	
    // create Tree Grid
    $("#treeGrid").jqxTreeGrid({
        width: '100%',
        height: '100%',
        columnsResize: true,
        columns: [
                  { text: '메뉴명', datafield: 'menuName', width: '250px', cellsalign: 'left', align: 'center'},
                  { text: 'menuId', datafield: 'treeMenuId', width: '100px', cellsalign: 'left', align: 'center' },
                  
                  { text: '표시순번', datafield: 'menuOrder', width: '70px', cellsalign: 'right', align: 'center'},
                  { text: '메뉴구분', datafield: 'menuTypeName', width: '130px', cellsalign: 'center', align: 'center'},
                  { text: '메뉴경로', datafield: 'menuUrl', width: '200px', cellsalign: 'left', align: 'center'},
                  { text: '로그인여부', datafield: 'loginYn', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '모바일사용여부', datafield: 'mobileYn', width: '100px', cellsalign: 'center', align: 'center' },
                  { text: '사용여부', datafield: 'useYn', width: '70px', cellsalign: 'center', align: 'center' },
                                           
                  { text: 'systemKindId', datafield: 'systemKindId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'parentMenuId', datafield: 'parentMenuId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'menuId', datafield: 'menuId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'menuType', datafield: 'menuType', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  
                  { text: 'treeParentMenuId', datafield: 'treeParentMenuId', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'treeLvl', datafield: 'treeLvl', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                  { text: 'treeOrder', datafield: 'treeOrder', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
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
    
    //탭정보 세팅
    var tabId = $(".tab_menu li a").attr("id");
    $("#"+tabId).parent().addClass("on");                
    $("#searchSystemKindId").val(util_replaceAll(tabId, "tab_", ""));
    $("#edtSystemKindId").val(util_replaceAll(tabId, "tab_", ""));
    $("#systemKindId").val(util_replaceAll(tabId, "tab_", ""));
    
    //조회
    fn_search();
});


/*******************************************
 * 기능 함수 
 *******************************************/
<%-- 검색 --%>
function fn_search(){
    //page setting  
    var url = "<c:url value='/apt/setting/selectMenuManageList.ajax'/>";
    var param = $("#MenuManageVO").serialize();
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
            keyDataField: { name: 'treeMenuId' },
            parentDataField: { name: 'treeParentMenuId' }
        },
        id : 'treeMenuId',
        localdata: resultList
    };

    var dataAdapter = new $.jqx.dataAdapter(source);
    
    $("#treeGrid").jqxTreeGrid({source: dataAdapter});
}

<%-- 상세보기 리셋 --%>
function fn_initDetail(){
	//상세 보기 리셋
    $("#edtSystemKindId").val($("#searchSystemKindId").val());
    $("#systemKindId").val($("#searchSystemKindId").val());
    $("#parentMenuId").val("");
    $("#menuId").val("");
    $("#menuName").val("");
    $("#menuType option:eq(0)").attr("selected", "selected");
    
    $("#menuOrder").val("1");
    $("#menuUrl").val("");
    
    $('input:radio[name=loginYn]:input[value=Y]').attr("checked", true);
    $('input:radio[name=mobileYn]:input[value=Y]').attr("checked", true);
    $('input:radio[name=useYn]:input[value=N]').attr("checked", true);
    
    $("#parentMenuIdTr").hide();
    
    if(g_saveFlag == "INS"){
	    //삭제버튼 비활성화
	    $("#btnDeleteSpan").hide();
    }
}

<%-- 상세이동 --%>
function fn_searchDetail(data){
	//상세보기 리셋
    fn_initDetail();
	    
	$("#searchSystemKindId").val(data.systemKindId);
    $("#searchMenuId").val(data.menuId);
    
    var menuId = $("#searchMenuId").val();
    //첫번째 값이 int로 치환이 되는 버그있음.
    if(data.treeLvl == "1"){
        if(menuId.length != 2){
        	$("#searchMenuId").val("0" + menuId);
        }
    }else if(data.treeLvl == "2"){
        if(menuId.length != 5){
        	$("#searchMenuId").val("0" + menuId);
        }
    }else{
        if(menuId.length != 8){
        	$("#searchMenuId").val("0" + menuId);
        }
    }
        
    //page setting  
    var url = "<c:url value='/apt/setting/selectMenuManageDtl.ajax'/>";
    var param = $("#MenuManageVO").serialize();
    var callBackFunc = "fn_searchDetailCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "상세정보 조회 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchDetailCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
    var resultDetail = data.resultDetail;
    
    var treeData = $("#treeGrid").jqxTreeGrid('getSelection')[0];
    var treeLvl = treeData.treeLvl;
    
    if(resultDetail != null){
    	$("#edtSystemKindId").val(resultDetail.systemKindId);
    	$("#systemKindId").val(resultDetail.systemKindId);
    	$("#parentMenuId").val(resultDetail.parentMenuId);
    	$("#menuId").val(resultDetail.menuId);
    	$("#menuName").val(resultDetail.menuName);
    	$("#menuType").val(resultDetail.menuType);
    	
    	$("#menuOrder").val(resultDetail.menuOrder);
    	$("#menuUrl").val(resultDetail.menuUrl);
    	
    	$('input:radio[name=loginYn]:input[value=' + resultDetail.loginYn + ']').attr("checked", true);
    	$('input:radio[name=mobileYn]:input[value=' + resultDetail.mobileYn + ']').attr("checked", true);
    	$('input:radio[name=useYn]:input[value=' + resultDetail.useYn + ']').attr("checked", true);
    	
    	if(treeLvl != 1) $("#parentMenuIdTr").show();
    	
    	//삭제버튼 활성화
        $("#btnDeleteSpan").show(); 
        
        g_saveFlag = "UPD";
        g_saveLvl = treeLvl;
        
        $("#treeLvl").val(g_saveLvl);
    }else{
    	//삭제버튼 비활성화
        $("#btnDeleteSpan").hide();
        
        g_saveFlag = "INS";
        g_saveLvl = treeLvl;
    }
    
}

<%-- 저장 --%>
function fn_save(){
    if(!fn_validate()){
        return;
    }
    
    var moveUrl = "";
    var param = $("#MenuManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    var msg = "";
    
    //등록
    if(g_saveFlag == "INS"){
        moveUrl = "<c:url value='/apt/setting/insertMenu.ajax'/>";
        msg = "등록 중입니다.";
    //수정
    }else{
        moveUrl = "<c:url value='/apt/setting/updateMenu.ajax'/>";
        msg = "수정 중입니다.";
    }
    
    //로딩 호출
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
    
    if(g_saveFlag == "INS"){
    	var menuId = data.menuId;
    	$("#menuId").val(menuId);
    	
    	g_saveFlag = "UPD";
        
        //삭제버튼 활성화
        $("#btnDeleteSpan").show();
    }
        
    fn_search();
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//시스템
    if(util_chkReturn($.trim($('#edtSystemKindId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='시스템'/>");
        $('#edtSystemKindId').focus();
        return false;
    }
		
	//대메뉴가 아닐경우에만
	if(g_saveLvl != "1"){
		if(util_chkReturn($.trim($('#parentMenuId').val()), "s") == "") {
	        alert("<spring:message code='errors.required' arguments='상위 메뉴 ID'/>");
	        $('#parentMenuId').focus();
	        return false;
	    }
	}
	
	//수정일때에만 체크
	if(g_saveFlag == "UPD"){
		if(util_chkReturn($.trim($('#menuId').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='메뉴 ID'/>");
            $('#menuId').focus();
            return false;
        }		
	}
	
	//메뉴명
	if(util_chkReturn($.trim($('#menuName').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='메뉴명'/>");
        $('#menuName').focus();
        return false;
    }else{
    	if(util_calcBytes($.trim($('#menuName').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='메뉴명,100'/>");
            $('#menuName').focus();
            return false;
        }
    }
	
	//메뉴 구분
	if(util_chkReturn($.trim($('#menuType').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='메뉴 구분'/>");
        $('#menuType').focus();
        return false;
	}
	
	//로그인 필요 여부
    if(util_chkReturn($.trim($('input[name=loginYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='로그인 필요 여부'/>");
        $('#loginYn_Y').focus();
        return false;
    }
	
    //메뉴 순서
    if(util_chkReturn($.trim($('#menuOrder').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='메뉴 순서'/>");
        $('#menuOrder').focus();
        return false;
    }else{
        if(!util_isNum($.trim($('#menuOrder').val()))){
            alert("<spring:message code='errors.integer' arguments='메뉴 순서'/>");
            $('#menuOrder').focus();
            return false;           
        }
    }
    
    //메뉴경로
    if(util_chkReturn($.trim($('#menuUrl').val()), "s") != "") {
        if(util_calcBytes($.trim($('#menuUrl').val())) > 2000){
	        alert("<spring:message code='errors.maxlength' arguments='메뉴경로,2000'/>");
	        $('#menuUrl').focus();
	        return false;
	    }
    }
    
	//사용여부
    if(util_chkReturn($.trim($('input[name=useYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='사용여부'/>");
        $('#useYn_Y').focus();
        return false;
    }
	
	$("#treeLvl").val(g_saveLvl);
		    
    return true;
}

<%-- 삭제 --%>
function fn_delete(){
	if(util_chkReturn($.trim($('#menuId').val()), "s") == "") {
        alert("메뉴를 선택 해 주세요.");
        return;
    }
	
    if(!confirm("하위메뉴도 삭제됩니다.\n<spring:message code='confirm.delete.msg' />")){
        return;
    }
        
    var moveUrl = "<c:url value='/apt/setting/deleteMenu.ajax'/>";
    var param = $("#MenuManageVO").serialize();
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
	
    if(data.result <= -1){
        alert("<spring:message code='fail.alert.delete' />");
    }else{
        alert("<spring:message code='success.alert.delete' />");
    }
    
    g_saveFlag = "INS";
    g_saveLvl = "1"; 

    fn_initDetail();
    fn_search();
}

</script>

</head>

<body>
<form:form commandName="MenuManageVO" name="MenuManageVO" method="post">
<input type="hidden" id="searchSystemKindId" name="searchSystemKindId" />
<input type="hidden" id="searchMenuId" name="searchMenuId" />
<input type="hidden" id="treeLvl" name="treeLvl" value="1" />

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
                    <h2>메뉴 관리</h2>
                </div>
                <!-- // locatioin -->                
                <div class="section_wrap type2">
                    <div class="section">
                        <!-- contents -->
                        <div class="tab_menu">
                            <ul>
                                <c:forEach items="${systemKindIdList}" var="systemKindIdList" varStatus="status">
                                    <li><a href="javascript:void(0);" id="tab_${systemKindIdList.code_name_kor}">${systemKindIdList.codeExtend1}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        
                        <h3>메뉴</h3>
                    
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
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnTopMenuReg">대 메뉴 등록</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnMenuReg">메뉴 등록</a></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="section">
                        <!-- contents -->
                        <div class="tb_write1 ml20">
                            <%-- title --%>
                            <h3 id="detailTitle">메뉴 상세</h3>
                        
                            <table>
                                <caption>정보 입력</caption>
                                <colgroup>
                                    <col style="width:30%;">
                                    <col style="width:*;">
                                </colgroup>
                                
                                <tbody>
                                    <tr>
                                        <th scope="row"><label for="systemKindId">시스템</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l">
                                            <select id="edtSystemKindId" name="edtSystemKindId" disabled="disabled">
                                                <c:forEach items="${systemKindIdList}" var="systemKindIdList" varStatus="status">
                                                    <option value="${systemKindIdList.code_name_kor}">${systemKindIdList.codeExtend1}</option>
				                                </c:forEach>
                                            </select>
                                            <input type="hidden" id="systemKindId" name="systemKindId" />
                                        </td>
                                    </tr>
                                    
                                    <tr id="parentMenuIdTr" style="display: none;">
                                        <th scope="row"><label for="parentMenuId">상위 메뉴 ID</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l"><input type="text" id="parentMenuId" name="parentMenuId" readonly="readonly"></td>
                                    </tr>
                                    
                                    <tr>
	                                    <th scope="row"><label for="menuId">메뉴 ID</label><span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l"><input type="text" id="menuId" name="menuId" readonly="readonly"></td>
	                                </tr>
	                                
	                                <tr>
                                        <th scope="row"><label for="menuName">메뉴명</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l">
                                            <input type="text" id="menuName" name="menuName">
                                            <span class="info_msg">※ 메뉴명에 'br'입력 시 엔터 처리됩니다. 단, Gnb 제외</span>
                                        </td>
                                    </tr>
	                                
	                                <tr>
                                        <th scope="row"><label for="menuType">메뉴 구분</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l">
                                            <select id="menuType" name="menuType">
                                                <c:forEach items="${menuTypeList}" var="menuTypeList" varStatus="status">
                                                    <option value="${menuTypeList.system_code}">${menuTypeList.code_name_kor}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <th scope="row">로그인 필요 여부<span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l">
                                            <input type="radio" id="loginYn_Y" name="loginYn" value="Y"> <label for="loginYn_Y"> 필요 </label>
                                            <input type="radio" id="loginYn_N" name="loginYn" value="N" checked="checked"> <label for="loginYn_N"> 미필요 </label>
                                        </td>                  
                                    </tr>
                                    
                                    <tr>
                                        <th scope="row">모바일 사용 여부<span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l">
                                            <input type="radio" id="mobileYn_Y" name="mobileYn" value="Y"> <label for="mobileYn_Y"> 사용 </label>
                                            <input type="radio" id="mobileYn_N" name="mobileYn" value="N" checked="checked"> <label for="mobileYn_N"> 미사용 </label>
                                        </td>                  
                                    </tr>
                                    
                                    <tr>
                                        <th scope="row"><label for="menuOrder">메뉴 순서</label><span class="icon_basic">*필수입력</span></th>
                                        <td class="txt_l"><input type="text" id="menuOrder" name="menuOrder" maxlength="3"></td>
                                    </tr>
                                    
                                    <tr>
                                        <th scope="row"><label for="menuOrder">메뉴 경로</label></th>
                                        <td class="txt_l"><input type="text" id="menuUrl" name="menuUrl"></td>
                                    </tr>
                                    
                                    <tr>
	                                    <th scope="row">사용여부<span class="icon_basic">*필수입력</span></th>
	                                    <td class="txt_l">
	                                        <input type="radio" id="useYn_Y" name="useYn" value="Y"> <label for="useYn_Y"> 사용 </label>
	                                        <input type="radio" id="useYn_N" name="useYn" value="N" checked="checked"> <label for="useYn_N"> 미사용 </label>
	                                    </td>                  
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