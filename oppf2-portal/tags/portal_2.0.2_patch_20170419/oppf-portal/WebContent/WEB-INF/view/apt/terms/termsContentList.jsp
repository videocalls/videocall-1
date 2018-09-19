<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : termsContentList.jsp
 * @Description : 약관동의 내용 관리
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.24  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.24
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
    var url = "<c:url value='/apt/terms/termsContentList.do'/>";
    var param = new Object();
    param.paramMenuId = "12001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
		
	//등록
	$("#btnReg").click(function(){
		fn_reset();
        
		$("#edtCustomerTermsSystemKind").prop("disabled", false);
		
		$("#customerTermsType").val($("#searchTermsType").val());
        $("#customerTermsContentVer").focus();
	});
	
	//저장
    $("#btnSave").click(function(){
        fn_save();
    });
	
    //이메일 발송
    $("#btnEmail").click(function(){
        fn_sendEmail();
    });
    	
	//동의서 종류 변경
	$("#searchTermsType").change(function(){
		$("#edtCustomerTermsType").val($(this).val());
				
		//조회
	    fn_search();
	});
	
	//email 고지여부
	$('input[type=radio][name=customerEmailYn]').change(function(){
		//이메일 발송 여부 Y
        if($(this).val() == "Y"){
        	$("#btnEmailSpan").show();
        }else{
        	$("#btnEmailSpan").hide();
        }
	});
			
	//그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '시스템구분', datafield: 'customerTermsSystemKindName', width: '100px', cellsalign: 'left', align: 'center', pinned: true },
            { text: '동의서명', datafield: 'customerTermsTypeName', width: '500px', cellsalign: 'left', align: 'center', pinned: true },
            //{ text: 'e-mail 고지', datafield: 'customerEmailYnName', width: '100px', cellsalign: 'center', align: 'center', pinned: true },
            //{ text: 'e-mail 고지일', datafield: 'customerEmailDate', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '150px', cellsalign: 'left', align: 'center' },
            
            { text: 'customerTermsType', datafield: 'customerTermsType', width: '0px', cellsalign: 'center', align: 'center', hidden : true },
            { text: 'customerTermsContentRegSeq', datafield: 'customerTermsContentRegSeq', width: '0px', cellsalign: 'center', align: 'center', hidden : true },
            { text: 'customerEmailYn', datafield: 'customerEmailYn', width: '0px', cellsalign: 'center', align: 'center', hidden : true }
        ]
    },  "200px");
	
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        fn_reset();
        
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
    
    
    //조회
    fn_search();
});


/*******************************************
 * 기능 함수 
 *******************************************/
<%-- reset --%>
function fn_reset(){
	$("#customerTermsType").val("");
	$("#customerTermsContentRegSeq").val("");
	$("#edtCustomerTermsType").val($("#searchTermsType").val());
	
	$('#customerTermsSystemKind').val("");
	$('#edtCustomerTermsSystemKind').find('option:first').attr('selected', 'selected');
	$("#edtCustomerTermsSystemKind").attr("disabled", true);
			
	$("#customerTermsContentVer").val("");
	$("#customerTermsContent").val("");
	$('input:radio[name=customerEmailYn]:input[value=N]').attr("checked", true);
	
	$("#customerEmailDateTr").hide();
    $("#btnEmailSpan").hide();
    
    $("#createDateTr").hide();
    $("#updateDateTr").hide();
}
 
<%-- 검색 --%>
function fn_search(){
	//page setting  
    var url = "<c:url value='/apt/terms/selectTermsContentList.ajax'/>";
    var param = $("#TermsContentManageVO").serialize();
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
		$("#edtCustomerTermsType").val(data.customerTermsType);
		$("#customerTermsType").val(data.customerTermsType);
		$("#customerTermsContentRegSeq").val(data.customerTermsContentRegSeq);
	}
	
    //page setting  
    var url = "<c:url value='/apt/terms/selectTermsContentDtl.ajax'/>";
    var param = $("#TermsContentManageVO").serialize();
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
		$("#customerTermsSystemKind").val(resultDetail.customerTermsSystemKind);
		$("#edtCustomerTermsSystemKind").val(resultDetail.customerTermsSystemKind);
		
		$("#customerTermsContentVer").val(resultDetail.customerTermsContentVer);
		$("#customerTermsContent").val(resultDetail.customerTermsContent);
		$('input:radio[name=customerEmailYn]:input[value=' + resultDetail.customerEmailYn + ']').attr("checked", true);
		
		//이메일 발송 여부 Y
		if(resultDetail.customerEmailYn == "Y"){
			if(util_chkReturn(resultDetail.customerEmailDate, "s") == "") {
				$("#customerEmailDateTr").hide();
				$("#btnEmailSpan").show();
			}else{
				$("#customerEmailDateTr").show();
                $("#btnEmailSpan").hide();
			}
		}else{
			$("#customerEmailDateTr").hide();
			$("#btnEmailSpan").hide();
		}
		
		$("#customerEmailDate").html(resultDetail.customerEmailDate);
		$("#createDate").html(resultDetail.createDate);
		$("#updateDate").html(resultDetail.createDate);
		
		$("#createDateTr").show();
		$("#updateDateTr").show();
		
	}else{
		$("#customerEmailDateTr").hide();
		$("#btnEmailSpan").hide();
		
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
    
    var moveUrl = "<c:url value='/apt/terms/insertTermsContent.ajax'/>";
    if(util_chkReturn($.trim($('#customerTermsContentRegSeq').val()), "s") != "") moveUrl = "<c:url value='/apt/terms/updateTermsContent.ajax'/>";
    var param = $("#TermsContentManageVO").serialize();
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
    if(util_chkReturn($.trim($('#customerTermsContentRegSeq').val()), "s") == ""){
        var customerTermsContentRegSeq = data.customerTermsContentRegSeq;
        
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            return;
        }else{
            alert("<spring:message code='success.alert.regist' />");
            
            $("#customerTermsContentRegSeq").val(customerTermsContentRegSeq);
            
            //이메일 발송 버튼 view
            $("#btnEmailSpan").show();
            
            fn_search();
            var detailData = {
                customerTermsType : $("#customerTermsType").val(),
                customerTermsContentRegSeq : customerTermsContentRegSeq
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
                customerTermsType : $("#customerTermsType").val(),
                customerTermsContentRegSeq : $("#customerTermsContentRegSeq").val()
            };
            fn_searchDetail(detailData);
        }
    }
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//시스템 구분
    if(util_chkReturn($.trim($('#edtCustomerTermsSystemKind').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='시스템 구분'/>");
        $('#edtCustomerTermsSystemKind').focus();
        return false;
    }else{
    	$("#customerTermsSystemKind").val($('#edtCustomerTermsSystemKind').val());
    }
	
	//동의서 종류
    if(util_chkReturn($.trim($('#edtCustomerTermsType').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='동의서 종류'/>");
        $('#edtCustomerTermsType').focus();
        return false;
    }else{
    	$("#customerTermsType").val($('#edtCustomerTermsType').val());
    }
	
    //버전
    if(util_chkReturn($.trim($('#customerTermsContentVer').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='버전'/>");
        $('#customerTermsContentVer').focus();
        return false;
    }else{
    	if(!util_isNum($.trim($('#customerTermsContentVer').val()))){
            alert("<spring:message code='errors.integer' arguments='버전'/>");
            $('#customerTermsContentVer').focus();
            return false;           
        }
        if(util_calcBytes($.trim($('#customerTermsContentVer').val())) > 10){
            alert("<spring:message code='errors.maxlength' arguments='버전,10'/>");
            $('#customerTermsContentVer').focus();
            return false;
        }
    }
    
    //내용
    if(util_chkReturn($.trim($('#customerTermsContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $('#customerTermsContent').focus();
        return false;
    }
    
    return true;
}

<%-- 이메일 발송 --%>
function fn_sendEmail(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, "이메일 발송중입니다.");
    
    var moveUrl = "<c:url value='/apt/terms/sendTermsContentEmail.ajax'/>";
    var param = $("#TermsContentManageVO").serialize();
    var callBackFunc = "fn_sendEmailCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_sendEmailCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    //로딩 호출
    gfn_setLoading(false);
    
    //insert
    if(util_chkReturn($.trim($('#customerTermsContentRegSeq').val()), "s") == ""){
        var customerTermsContentRegSeq = data.customerTermsContentRegSeq;
        
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.email' />");
            return;
        }else{
            alert("<spring:message code='success.alert.email' />");
            
            $("#customerTermsContentRegSeq").val(customerTermsContentRegSeq);
            
            //이메일 발송 버튼 hide
            $("#btnEmailSpan").hide();
            
            fn_search();
            var detailData = {
                customerTermsType : $("#customerTermsType").val(),
                customerTermsContentRegSeq : customerTermsContentRegSeq
            };
            fn_searchDetail(detailData);
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
            
            //이메일 발송 버튼 hide
            $("#btnEmailSpan").hide();
            
            fn_search();
            var detailData = {
                customerTermsType : $("#customerTermsType").val(),
                customerTermsContentRegSeq : $("#customerTermsContentRegSeq").val()
            };
            fn_searchDetail(detailData);
        }
    }
}

</script>

</head>

<body>
<form:form commandName="TermsContentManageVO" name="TermsContentManageVO" method="post">
<input type="hidden" id="customerTermsType" name="customerTermsType" />
<input type="hidden" id="customerTermsContentRegSeq" name="customerTermsContentRegSeq" />
<input type="hidden" id="customerTermsSystemKind" name="customerTermsSystemKind" />

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
                    <h2>약관동의 내용 관리</h2>
                </div>
                <!-- // locatioin -->                
                <div class="section_wrap">                    
                    <div class="section">
                        <h3>
                                                        동의서 종류 : 
                        </h3>&nbsp;
                        <select id="searchTermsType" name="searchTermsType"  >
                            <c:forEach items="${termsTypeList}" var="termsTypeList" varStatus="status">
                                <option value="${termsTypeList.system_code}">${termsTypeList.code_name_kor}</option>
                            </c:forEach>
                        </select>
                        
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
                                    <th scope="row"><label for="edtCustomerTermsSystemKind">시스템구분</label></th>
                                    <td class="txt_l">
                                        <select id="edtCustomerTermsSystemKind" name="edtCustomerTermsSystemKind" disabled="disabled">
                                            <c:forEach items="${termsSystemKindList}" var="termsSystemKindList" varStatus="status">
                                                <option value="${termsSystemKindList.system_code}">${termsSystemKindList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="edtCustomerTermsType">동의서 종류</label></th>
                                    <td class="txt_l">
                                        <select id="edtCustomerTermsType" disabled="disabled">
			                                <c:forEach items="${termsTypeList}" var="termsTypeList" varStatus="status">
			                                    <option value="${termsTypeList.system_code}">${termsTypeList.code_name_kor}</option>
			                                </c:forEach>
			                            </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="customerTermsContentVer">버전</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <input type="text" class="" id="customerTermsContentVer" name="customerTermsContentVer" style="ime-mode:disabled;" maxlength="10">
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><label for="customerTermsContent">내용</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <textarea id="customerTermsContent" name="customerTermsContent"  style="height:350px"></textarea>
                                    </td>
                                </tr>
                                <%--
                                <tr>
                                    <th scope="row"><label for="companyCodeKind">e-mail 고지여부</label><span class="icon_basic">*필수입력</span></th>
                                    <td class="txt_l">
                                        <input type="radio" id="customerEmailYn_Y" name="customerEmailYn" value="Y"> <label for="customerEmailYn_Y"> 고지 </label>
                                        <input type="radio" id="customerEmailYn_N" name="customerEmailYn" value="N" checked="checked"> <label for="customerEmailYn_N"> 미고지 </label>
                                    </td>
                                </tr>
                                 --%>
                                 <input type="hidden" id="customerEmailYn" name="customerEmailYn" value="N" />
                                
                                <tr id="customerEmailDateTr" style="display: none;">
                                    <th scope="row">고지일시</th>
                                    <td class="txt_l" id="customerEmailDate"></td>                  
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