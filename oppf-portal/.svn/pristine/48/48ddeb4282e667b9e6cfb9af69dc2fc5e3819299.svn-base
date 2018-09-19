<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : companyTermsContentList.jsp
 * @Description : 기업 약관동의 내용 관리
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.08.01  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.08.01
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
    var url = "<c:url value='/apt/terms/companyTermsContentList.do'/>";
    var param = new Object();
    param.paramMenuId = "12002";
    
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
        util_moveRequest("CompanyTermsContentManageVO", "<c:url value='/apt/terms/companyTermsContentList.do'/>", "_top");
    });
		
	//등록
	$("#btnReg").click(function(){
		fn_reset();
        
		$("#companyCodeId").val($('input[name=searchCompanyCodeId]:checked').val());
		$("#companyTermsType").val($('input[name=searchCompanyTermsType]:checked').val());
        
        $("#edtCompanyCodeId").val($('input[name=searchCompanyCodeId]:checked').val());
        $("#edtCompanyTermsType").val($('input[name=searchCompanyTermsType]:checked').val());
        
        $("#customerTermsContentVer").focus();
	});
	
	//저장
    $("#btnSave").click(function(){
        fn_save();
    });
		
	//그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '기업명', datafield: 'companyCodeName', width: '200px', cellsalign: 'left', align: 'center', pinned: true },
            { text: '동의서명', datafield: 'companyTermsTypeName', width: '200px', cellsalign: 'left', align: 'center', pinned: true },
            { text: '동의서 버전', datafield: 'companyTermsContentVer', width: '100px', cellsalign: 'left', align: 'center', pinned: true },
            { text: '등록일', datafield: 'createDate', width: '150px', cellsalign: 'center', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '150px', cellsalign: 'center', align: 'center' },
            
            { text: 'companyCodeId', datafield: 'companyCodeId', width: '0px', cellsalign: 'center', align: 'center', hidden : true },
            { text: 'companyTermsType', datafield: 'companyTermsType', width: '0px', cellsalign: 'center', align: 'center', hidden : true },
            { text: 'companyTermsContentRegSeq', datafield: 'companyTermsContentRegSeq', width: '0px', cellsalign: 'center', align: 'center', hidden : true }
        ]
    }, "200px");
	
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        //상세이동
        fn_searchDetail(data);
    }).on('bindingcomplete', function (event) {
    	//로딩 호출
        gfn_setLoading(false);
    	
    	fn_reset();
    	
    	$('#jqxgrid').jqxGrid('clearselection');   //clear
    	$('#jqxgrid').jqxGrid('selectrow', 0);     //rowselect
    	
    	var row = $('#jqxgrid').jqxGrid('getrows').length;
    	if(row > 0){
    		var data = $('#jqxgrid').jqxGrid('getrowdata', 0);
            //상세이동
            fn_searchDetail(data);	
    	}
    });
    
});


/*******************************************
 * 기능 함수 
 *******************************************/
<%-- reset --%>
function fn_reset(){
	$("#companyCodeId").val($('input[name=searchCompanyCodeId]:checked').val());
	$("#companyTermsType").val($('input[name=searchCompanyTermsType]:checked').val());
	$("#companyTermsContentRegSeq").val("");
	
	$("#edtCompanyCodeId").val($('input[name=searchCompanyCodeId]:checked').val());
    $("#edtCompanyTermsType").val($('input[name=searchCompanyTermsType]:checked').val());
	
	$("#companyTermsContentVer").val("");
	$("#companyTermsContent").val("");
	
    $("#createDateTr").hide();
    $("#updateDateTr").hide();
}
 
<%-- 검색 --%>
function fn_search(){
	//기업
	if(util_chkReturn($.trim($('input[name=searchCompanyCodeId]:checked').val()), "s") == ""){
		alert("기업을 선택 해 주세요.");
		return;
	}
	//동의서종류
    if(util_chkReturn($.trim($('input[name=searchCompanyTermsType]:checked').val()), "s") == ""){
        alert("동의서 종류를 선택 해 주세요.");
        return;
    }
	
	//page setting  
    var url = "<c:url value='/apt/terms/selectCompanyTermsContentList.ajax'/>";
    var param = $("#CompanyTermsContentManageVO").serialize();
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
    if(util_chkReturn(data, "s") != ""){
		$("#companyCodeId").val(data.companyCodeId);
		$("#companyTermsType").val(data.companyTermsType);
		$("#companyTermsContentRegSeq").val(data.companyTermsContentRegSeq);
		
		$("#edtCompanyCodeId").val(data.companyCodeId);
		$("#edtCompanyTermsType").val(data.companyTermsType);
	}
	
    //page setting  
    var url = "<c:url value='/apt/terms/selectCompanyTermsContentDtl.ajax'/>";
    var param = $("#CompanyTermsContentManageVO").serialize();
    var callBackFunc = "fn_searchDetailCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "상세 조회중 입니다.");
  
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
		$("#companyTermsContentVer").val(resultDetail.companyTermsContentVer);
		$("#companyTermsContent").val(resultDetail.companyTermsContent);
		
		$("#createDate").html(resultDetail.createDate);
		$("#updateDate").html(resultDetail.createDate);
		
		$("#createDateTr").show();
		$("#updateDateTr").show();
		
	}else{
		$("#createDateTr").hide();
        $("#updateDateTr").hide();
	}
	
	//로딩 호출
    gfn_setLoading(false);
}

<%-- 저장 --%>
function fn_save(){
    if(!fn_validate()){
        return;
    }
    
    var moveUrl = "<c:url value='/apt/terms/insertCompanyTermsContent.ajax'/>";
    if(util_chkReturn($.trim($('#companyTermsContentRegSeq').val()), "s") != "") moveUrl = "<c:url value='/apt/terms/updateCompanyTermsContent.ajax'/>";
    var param = $("#CompanyTermsContentManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "저장 중입니다.");
    
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
	
    //insert
    if(util_chkReturn($.trim($('#companyTermsContentRegSeq').val()), "s") == ""){
        var companyTermsContentRegSeq = data.companyTermsContentRegSeq;
        
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            return;
        }else{
            alert("<spring:message code='success.alert.regist' />");
            
            $("#companyTermsContentRegSeq").val(companyTermsContentRegSeq);
            
            fn_search();
            var detailData = {
           		companyCodeId : $("#companyCodeId").val(),
           		companyTermsType : $("#companyTermsType").val(),
           		companyTermsContentRegSeq : companyTermsContentRegSeq
            };
            fn_searchDetail(detailData);
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
            fn_search();
            var detailData = {
                companyCodeId : $("#companyCodeId").val(),
                companyTermsType : $("#companyTermsType").val(),
                companyTermsContentRegSeq : $("#companyTermsContentRegSeq").val()
            };
            fn_searchDetail(detailData);
        }
    }
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//기업
    if(util_chkReturn($.trim($('#edtCompanyCodeId').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='기업'/>");
        $('#edtCompanyCodeId').focus();
        return false;
    }else{
    	$("#companyCodeId").val($('#edtCompanyCodeId').val());
    }
	
	//동의서 종류
    if(util_chkReturn($.trim($('#edtCompanyTermsType').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='동의서 종류'/>");
        $('#edtCompanyTermsType').focus();
        return false;
    }else{
    	$("#companyTermsType").val($('#edtCompanyTermsType').val());
    }
	
    //버전
    if(util_chkReturn($.trim($('#companyTermsContentVer').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='버전'/>");
        $('#companyTermsContentVer').focus();
        return false;
    }else{
    	if(!util_isNum($.trim($('#companyTermsContentVer').val()))){
            alert("<spring:message code='errors.integer' arguments='버전'/>");
            $('#companyTermsContentVer').focus();
            return false;           
        }
        if(util_calcBytes($.trim($('#companyTermsContentVer').val())) > 10){
            alert("<spring:message code='errors.maxlength' arguments='버전,10'/>");
            $('#companyTermsContentVer').focus();
            return false;
        }
    }
    
    //내용
    if(util_chkReturn($.trim($('#companyTermsContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $('#companyTermsContent').focus();
        return false;
    }
    
    return true;
}


</script>

</head>

<body>
<form:form commandName="CompanyTermsContentManageVO" name="CompanyTermsContentManageVO" method="post">
<input type="hidden" id="companyCodeId" name="companyCodeId" />
<input type="hidden" id="companyTermsType" name="companyTermsType" />
<input type="hidden" id="companyTermsContentRegSeq" name="companyTermsContentRegSeq" />

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
                    <h2>기업 약관동의 내용 관리</h2>
                </div>
                <!-- // locatioin -->                
                <div class="section_wrap">                    
                    <div class="section">
                        <div class="tb_write1">
		                    <table>
		                        <caption>키워드, 노출종류, 조회일</caption>
		                        <colgroup>
		                            <col style="width:20%;">
		                            <col style="width:*;">
		                        </colgroup>
		                        <tbody>
		                        <tr>
		                            <th scope="row"><label for="">기업</label></th>
		                            <td class="txt_l">
		                                <!-- chk_list_wrap -->
		                                <div class="chk_list_wrap type2">
		                                    <ul>
		                                        <c:forEach items="${companyCodeList}" var="companyCodeList" varStatus="status">
		                                            <li><input type="radio" name="searchCompanyCodeId" id="searchCompanyCodeId_${companyCodeList.companyCodeId}" value="${companyCodeList.companyCodeId}">
		                                            <label for="searchCompanyCodeId_${companyCodeList.companyCodeId}">${companyCodeList.companyCodeName}</label></li>
		                                        </c:forEach>
		                                    </ul>
		                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
		                                </div>
		                                <!-- // chk_list_wrap -->                                
		                            </td>                           
		                        </tr>
		                        <tr>
                                    <th scope="row"><label for="">동의서 종류</label></th>
                                    <td class="txt_l">
                                        <!-- chk_list_wrap -->
                                        <div class="chk_list_wrap type2">
                                            <ul>
                                                <c:forEach items="${companyTermsTypeList}" var="companyTermsTypeList" varStatus="status">
                                                    <li>
                                                    <input type="radio" name="searchCompanyTermsType" id="searchCompanyTermsType_${companyTermsTypeList.system_code}" value="${companyTermsTypeList.system_code}"
                                                           <c:if test="${companyTermsTypeList.system_code eq 'G031001'}"> checked="checked" </c:if>
                                                    >
                                                    <label for="searchCompanyTermsType_${companyTermsTypeList.system_code}">${companyTermsTypeList.code_name_kor}</label>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <!-- // chk_list_wrap -->                                
                                    </td>                           
                                </tr>
		                        </tbody>
		                    </table>
		                </div>
		                <div class="btn_type3">
		                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnSearch">검색</a></span>
		                    <span class="btn_gray2"><a href="javascript:void(0);" id="btnSearchClear">초기화</a></span>
		                </div>
                                                
                        
                        <!-- contents -->
                        <div style="height:200px; border:1px solid #ddd;">
                            <div id="jqxgrid"></div>
                        </div>
                        
                        <!-- // contents -->
                        <div class="btn_type3">
                            <div class="left">
                                <!-- 
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                                -->
                            </div>
                            <div class="right">
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnReg">등록</a></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="section">
                        <!-- contents -->
                        <div class="tb_write1 ml20">         
                            <h3 id="detailTitle">동의서 상세</h3>
                                               
                            <table>
                                <caption>정보 입력</caption>
                                <colgroup>
                                    <col style="width:30%;">
                                    <col style="width:*;">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th scope="row"><label for="edtCompanyCodeId">기업</label></th>
                                    <td class="txt_l">
                                        <select id="edtCompanyCodeId" disabled="disabled">
	                                        <c:forEach items="${companyCodeList}" var="companyCodeList" varStatus="status">
	                                           <option value="${companyCodeList.companyCodeId}">${companyCodeList.companyCodeName}</option>
	                                        </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="edtCompanyTermsType">동의서 종류</label></th>
                                    <td class="txt_l">
                                        <select id="edtCompanyTermsType" disabled="disabled">
                                            <c:forEach items="${companyTermsTypeList}" var="companyTermsTypeList" varStatus="status">
                                                <option value="${companyTermsTypeList.system_code}">${companyTermsTypeList.code_name_kor}</option>
                                            </c:forEach>
			                                <c:forEach items="${termsTypeList}" var="termsTypeList" varStatus="status">
			                                    <option value="${termsTypeList.system_code}">${termsTypeList.code_name_kor}</option>
			                                </c:forEach>
			                            </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyTermsContentVer">버전</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <input type="text" class="" id="companyTermsContentVer" name="companyTermsContentVer" style="ime-mode:disabled;" maxlength="10">
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="companyTermsContent">내용</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <textarea id="companyTermsContent" name="companyTermsContent"  style="height:350px"></textarea>
                                    </td>
                                </tr>                                
                                <tr id="createDateTr" style="display: none;">
                                    <th scope="row">등록일시</th>
                                    <td class="txt_l" id="createDate"></td>                  
                                </tr>
                                <tr id="updateDateTr" style="display: none;">
                                    <th scope="row">수정일시</th>
                                    <td class="txt_l" id="updateDate"></td>                  
                                </tr>
                                
                                </tbody>
                            </table>
                        </div>
                        <!-- // contents -->
                        <div class="btn_type3">
                            <div class="right">
                                <span class="btn_gray2" id="btnEmailSpan" style="display: none;"><a href="javascript:void(0);" id="btnEmail">발송</a></span>
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