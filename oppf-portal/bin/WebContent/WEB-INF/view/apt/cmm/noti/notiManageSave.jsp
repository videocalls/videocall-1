<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : notiManageSave.jsp
 * @Description : 관리자 공지사항 등록/수정
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
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.energyblue.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownbutton.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcolorpicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxwindow.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxtooltip.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcheckbox.js'/>"></script>
<%-- //jqwidgets --%>

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#dateFrom, #dateTo").datepicker({
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
var g_fileIdx = 1;          //첨부파일 idx 
var g_fileMaxSize = 5000;   //첨부파일 최대 size(KB)
var g_maxFileCnt = 5;       //첨부파일 최대 개수
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cmm/noti/notiManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "08001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    //목록
    $("#btnList").click(function(){
        fn_list();    
    });
    
    //저장
    $("#btnSave").click(function(){
        fn_save();
    });
    
    //삭제
    $("#btnDel").click(function(){
        fn_delete();
    });
    
    //팝업여부
    $("input[name=noticePopYn]").click(function(){
        if($(this).val() == "Y"){
            $("#popWidthTr").show();
            $("#popHeightTr").show();
        }else{
            $("#popWidthTr").hide();
            $("#popHeightTr").hide();
            
            $("#noticePopWidth").val("300");
            $("#noticePopHeight").val("300");
        }
    });
    
    //게시일 셋팅
    if("${resultDetail.noticeStartDate}" == ""){
        $("#dateFrom").val(util_getDate("-"));
    }
    if("${resultDetail.noticeExpireDate}" == ""){
        $("#dateTo").val(util_setFmDate("<spring:message code='Globals.format.dateTo' />", "-"));
    }
    
    $('#noticeContent').jqxEditor({
        height: "400px",
        width: "100%",
        tools : "bold italic underline | format font size | color background | left center right | outdent indent | ul ol | html"
    });
    
    //파일 index 셋팅
    var fileCnt = 0;
    <c:forEach var="fileList" items="${fileList}" varStatus="status">
        if(g_fileIdx < Number("${ fileList.fileSeq }")){
        	g_fileIdx = Number("${ fileList.fileSeq }");
        }     
        fileCnt++;
	</c:forEach>
    g_fileIdx = g_fileIdx * 10;
        
    //파일 추가
    if(fileCnt < g_maxFileCnt){
        fn_addNewFile();
    }
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
    util_moveRequest("NotiManageVO", "<c:url value='/cmm/noti/notiManageList.do'/>", "_self");
}

<%-- 첨부파일 event --%>
function fn_initNotiFile(fileId){
    var maxSize = g_fileMaxSize * 1000;
    
    $('#'+fileId).bind("change", function(){
        var idx = util_replaceAll($(this).attr("id"), "file_", "");
        if($(this).val() != ""){
            if(this.files[0].size > maxSize){
                alert("파일의 최대 크기는 "+g_fileMaxSize+" KB 입니다.");
                $(this).val("");
            }else{
                var fileSize = this.files[0].size; 
                var fileName = this.files[0].name;
                var fileExt = fileName.split(".").pop().toLowerCase();

                $("#fileName_"+idx).val(fileName);
                $("#fileSize_"+idx).val(fileSize);
                $("#fileExt_"+idx).val(fileExt);
            }
        }
    });
}

<%-- 신규 파일 추가 --%>
function fn_addNewFile(){
    //파일 개수 체크
    var fileCnt = 0;
    $("#oldFileList>li").each(function(index){
        fileCnt++;
    });
    $("#newFileList>li").each(function(index){
        fileCnt++;
    });
    
    if(fileCnt >= g_maxFileCnt){
        alert("파일은 최대 "+g_maxFileCnt+"개 까지만 추가 하실 수 있습니다.");
        return;
    }
    
    var html = "";
    html += "<li id='fileLi_"+g_fileIdx+"'>";
    html += "<div class='file'>";
    html += "<input type='file' name='file' id='file_"+g_fileIdx+"' style='width: 300px' />";
    html += "<div class='btn_plus_minus'>";
    html += "<a href='javascript:fn_addNewFile();'><img src='<c:url value='/images/apt/blt_plus.png'/>' alt='파일추가' /></a>";
    html += "<a href='javascript:fn_deleteFile(\""+g_fileIdx+"\", \"new\");'><img src='<c:url value='/images/apt/blt_minus.png'/>' alt='파일삭제' /></a>";
    html += "</div>";
    
    html += "<input type='hidden' name='fileName' id='fileName_"+g_fileIdx+"' value='' />";
    html += "<input type='hidden' name='fileSize' id='fileSize_"+g_fileIdx+"' value='' />";
    html += "<input type='hidden' name='fileExt' id='fileExt_"+g_fileIdx+"' value='' />";
    
    html += "</div>";
    html += "</li>";
    
    $("#newFileList").append(html);
    
    fn_initNotiFile("file_"+g_fileIdx);
    
    g_fileIdx++;
}

<%-- 파일 삭제 --%>
function fn_deleteFile(idx, type){
    if(type == "new"){
        var fileCnt = 0;
        $("#newFileList>li").each(function(index){
            fileCnt++;
        });
        
        //1개만 남아있을 경우에는 삭제 하지 않고 값을 리셋한다.
        if(fileCnt <= 1){
            $("#file_"+idx).val("");
            
            $("#fileName_"+idx).val("");
            $("#fileSize_"+idx).val("");
            $("#fileExt_"+idx).val("");
        }else{
            $("#fileLi_"+idx).remove();     
        }
    }else{
        //삭제 파일 목록 추가
        var html = "<input type='hidden' name='delFileSeq' value='"+idx+"' />";
        $("#NotiManageVO").append(html);
        
        //파일 삭제
        $("#fileLi_"+idx).remove();
        
        //파일 개수 체크
        var fileCnt = 0;
        $("#newFileList>li").each(function(index){
            fileCnt++;
        });
        
        //파일 추가
        if(fileCnt <= 0){
            fn_addNewFile();
        }
    }
    
}

<%-- 저장 --%>
function fn_save(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    var msg = "등록 중입니다.";
    if(util_chkReturn($.trim($('#noticeSeq').val()), "s") != "") msg = "수정 중입니다."; 
    gfn_setLoading(true, msg);
        
    var moveUrl = "<c:url value='/cmm/noti/insertNoti.ajax'/>";
    if(util_chkReturn($.trim($('#noticeSeq').val()), "s") != "") moveUrl = "<c:url value='/cmm/noti/updateNoti.ajax'/>";
    var callBackFunc = "fn_saveCallBack";
        
    gfn_filePage("NotiManageVO", moveUrl, callBackFunc);
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
      
    //insert
    if(util_chkReturn($.trim($('#noticeSeq').val()), "s") == ""){
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            
            //로딩 호출
            gfn_setLoading(false);
            return;
        }else{
            alert("<spring:message code='success.alert.regist' />");
            fn_list();
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            
            //로딩 호출
            gfn_setLoading(false);
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
            fn_list();
        }
    }
}

<%-- 저장전 체크 --%>
function fn_validate(){
    //노출종류
    if(util_chkReturn($.trim($('#noticeKind').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='노출종류'/>");
        $('#noticeKind').focus();
        return false;
    }
    
    //고정여부
    if(util_chkReturn($.trim($('input[name=noticeFixYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='고정여부'/>");
        $('#noticeFixYn').focus();
        return false;
    }
    
    //팝업여부
    if(util_chkReturn($.trim($('input[name=noticePopYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='팝업여부'/>");
        $('#noticePopYn').focus();
        return false;
    }else{
        //팝업일때
        if(util_chkReturn($.trim($('input[name=noticePopYn]:checked').val()), "s") == "Y"){
            //넓이
            if(util_chkReturn($.trim($('#noticePopWidth').val()), "s") != ""){
                if(!util_isNum($('#noticePopWidth').val())){
                    alert("<spring:message code='errors.integer' arguments='팝업 넓이'/>");
                    $('#noticePopWidth').focus();
                    return false;
                }
            }
            
            //높이
            if(util_chkReturn($.trim($('#noticePopHeight').val()), "s") != ""){
                if(!util_isNum($('#noticePopHeight').val())){
                    alert("<spring:message code='errors.integer' arguments='팝업 높이'/>");
                    $('#noticePopHeight').focus();
                    return false;
                }
            }
        }else{
            $('#noticePopWidth').val("0");
            $('#noticePopHeight').val("0");
        }
    }
  
    //사용여부
    if(util_chkReturn($.trim($('input[name=useYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='사용여부'/>");
        $('#useYn').focus();
        return false;
    }
    
    //게시일
    var dateFrom = util_replaceAll($("#dateFrom").val(), "-", "");
    var dateTo = util_replaceAll($("#dateTo").val(), "-", "");
            
    if(dateFrom == ""){
        alert("<spring:message code='errors.required' arguments='게시시작일' />");
        $("#dateFrom").focus();
        return false;
    }else{
        if(!util_isDate(dateFrom)){
            alert("<spring:message code='errors.invalid' arguments='게시시작일' />");
            $("#dateFrom").focus();
            return false;
        }
    }
    
    if(dateTo == ""){
        alert("<spring:message code='errors.required' arguments='게시종료일' />");
        $("#dateTo").focus();
        return false;
    }else{
        if(!util_isDate(dateTo)){
            alert("<spring:message code='errors.invalid' arguments='게시종료일' />");
            $("#dateTo").focus();
            return false;
        }else{
            if(dateFrom > dateTo){
                alert("종료일이 시작일보다 클 수 없습니다.");
                $("#dateTo").focus();
                return false;
            }
        }   
    }
    
    $("#noticeStartDate").val(dateFrom);
    $("#noticeExpireDate").val(dateTo);
    
    //제목
    if(util_chkReturn($.trim($('#noticeTitle').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='제목'/>");
        $('#noticeTitle').focus();
        return false;
    }else{
        if(util_calcBytes($.trim($('#noticeTitle').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='제목,100'/>");
            $('#noticeTitle').focus();
            return false;
        }
    }
    
    //내용
    if(util_chkReturn($.trim($('#noticeContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $('#noticeContent').focus();
        return false;
    }
    
    return true;
}

<%-- 삭제 --%>
function fn_delete(){
    if(!confirm("<spring:message code='confirm.delete.msg' />")){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, "삭제 중입니다.");
    
    var moveUrl = "<c:url value='/cmm/noti/deleteNoti.ajax'/>";
    var param = $("#NotiManageVO").serialize();
    var callBackFunc = "fn_deleteCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_deleteCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.delete' />");
    }else{
        alert("<spring:message code='success.alert.delete' />");
    }

    fn_list();
}

</script>

</head>

<body>
<form:form commandName="NotiManageVO" name="NotiManageVO" method="post" enctype="multipart/form-data">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchNoticeKind" id="searchNoticeKind" value="<c:out value='${paramVO.searchNoticeKind}'/>" /><!-- //검색조건 -->
<input type="hidden" name="schDateFrom" id="schDateFrom" value="<c:out value='${paramVO.schDateFrom}'/>" /><!-- //검색조건 -->
<input type="hidden" name="schDateTo" id="schDateTo" value="<c:out value='${paramVO.schDateTo}'/>" /><!-- //검색조건 -->

<input type="hidden" name="noticeSeq" id="noticeSeq" value="<c:out value='${resultDetail.noticeSeq}'/>" />
<input type="hidden" name="fileId" id="fileId" value="<c:out value='${resultDetail.fileId}'/>" />
<input type="hidden" name="noticeStartDate" id="noticeStartDate" value="" />
<input type="hidden" name="noticeExpireDate" id="noticeExpireDate" value="" />



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
                    <h2>공지사항&nbsp;
                        <c:choose>
                            <c:when test="${empty resultDetail.noticeSeq}">등록</c:when>
                            <c:otherwise>수정</c:otherwise>
                        </c:choose>                                              
                    </h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>공지사항&nbsp;
                        <c:choose>
                            <c:when test="${empty resultDetail.noticeSeq}">등록</c:when>
                            <c:otherwise>수정</c:otherwise>
                        </c:choose> 
                        </caption>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">노출종류<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <c:forEach items="${noticeKindList}" var="noticeKindList" varStatus="status">
                            		<c:if test="${noticeKindList.system_code != 'G003004'}">
                                        <input type="radio" name="noticeKind"	id="noticeKind" value="${noticeKindList.system_code}"
                                            <c:if test="${(resultDetail.displayServerType =='' || resultDetail.displayServerType == null) && noticeKindList.system_code == 'G003001'}">checked="checked"</c:if>
                                            <c:if test="${resultDetail.noticeKind eq noticeKindList.system_code}">  checked="checked" </c:if>
                                        >
												<label for="noticeKind">${noticeKindList.code_name_eng}</label>
									</c:if>
									</c:forEach>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">노출구분<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
									<input type="radio" name=displayServerType	id="displayServerType" value=""
									<c:if test="${resultDetail.displayServerType =='' || resultDetail.displayServerType == null}">  checked="checked" </c:if>
									>
									<label for="displayServerType">전체</label>
                            		<c:forEach items="${displayServerTypeList}" var="displayServerTypeList" varStatus="status">
												<input type="radio" name=displayServerType	id="displayServerType" value="${displayServerTypeList.system_code}"
													<c:if test="${resultDetail.displayServerType eq displayServerTypeList.system_code}">  checked="checked" </c:if>
													>
												<label for="displayServerType">${displayServerTypeList.code_name_eng}</label>
									</c:forEach>
                            </td>                  
                        </tr>                        
                        
                        <tr>
                            <th scope="row">고정여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="noticeFixYn_Y" name="noticeFixYn" value="Y"
                                    <c:if test="${resultDetail.noticeFixYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="noticeFixYn_Y"> 고정 </label>
                                <input type="radio" id="noticeFixYn_N" name="noticeFixYn" value="N"
                                    <c:if test="${resultDetail.noticeFixYn eq 'N' || empty resultDetail.noticeFixYn}"> checked="checked" </c:if>
                                > <label for="noticeFixYn_N"> 미고정 </label>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">팝업여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="noticePopYn_Y" name="noticePopYn" value="Y"
                                    <c:if test="${resultDetail.noticePopYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="noticePopYn_Y"> 팝업 </label>
                                <input type="radio" id="noticePopYn_N" name="noticePopYn" value="N"
                                    <c:if test="${resultDetail.noticePopYn eq 'N' || empty resultDetail.noticePopYn}"> checked="checked" </c:if>
                                > <label for="noticePopYn_N"> 일반 </label>
                            </td>                  
                        </tr>
                        <tr id="popWidthTr" <c:if test="${empty resultDetail.noticePopYn || resultDetail.noticePopYn eq 'N'}"> style="display: none;" </c:if>>
                            <th scope="row">팝업 넓이</th>
                            <td class="txt_l">
                                <input type="text" id="noticePopWidth" name="noticePopWidth" value="${resultDetail.noticePopWidth}" class="w100 txt_r" maxlength="4">
                                <label for="noticePopWidth"> px (default : 300 px)</label>
                            </td>                  
                        </tr>
                        <tr id="popHeightTr" <c:if test="${empty resultDetail.noticePopYn || resultDetail.noticePopYn eq 'N'}"> style="display: none;" </c:if>>
                            <th scope="row">팝업 높이</th>
                            <td class="txt_l">
                                <input type="text" id="noticePopHeight" name="noticePopHeight" value="${resultDetail.noticePopHeight}"  class="w100 txt_r" maxlength="4">
                                <label for="noticePopHeight"> px (default : 300 px)</label>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">사용여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="useYn_Y" name="useYn" value="Y"
                                    <c:if test="${resultDetail.useYn eq 'Y' || empty resultDetail.useYn}"> checked="checked" </c:if>
                                > <label for="useYn_Y"> 사용 </label>
                                <input type="radio" id="useYn_N" name="useYn" value="N"
                                    <c:if test="${resultDetail.useYn eq 'N'}"> checked="checked" </c:if>
                                > <label for="useYn_N"> 미사용 </label>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">게시일<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="dateFrom" name="dateFrom" value="${resultDetail.noticeStartDate}" style="width:80px;"/>
                                <span class="dateDot">~</span>
                                <input type="text" id="dateTo" name="dateTo" value="${resultDetail.noticeExpireDate}" style="width:80px;"/>
                            </td>                  
                        </tr>
                        
                        <c:if test="${not empty resultDetail.noticeSeq}">
                        <tr>
                            <th scope="row">조회수</th>
                            <td class="txt_l"><fmt:formatNumber value="${resultDetail.readCount}" type="number" /></td>                  
                        </tr>
                        <tr>
                            <th scope="row">등록일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">수정일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.updateDate}'/></td>                  
                        </tr>
                        </c:if>
                        
                        <tr>
                            <th scope="row">제목<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="noticeTitle" name="noticeTitle" value="${resultDetail.noticeTitle}" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">내용<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <textarea id="noticeContent" name="noticeContent" >${resultDetail.noticeContent}</textarea>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">첨부파일</th>
                            <td class="txt_l">
                                <ul class="file_added_list" id="oldFileList">
                                    <c:forEach var="fileList" items="${fileList}" varStatus="status">
                                        <li id="fileLi_${ fileList.fileSeq }">
                                         <a href="javascript:gfn_noticeFileDown('${ fileList.fileId }', '${ fileList.fileSeq }');">${ fileList.fileName }(${ fileList.fileSize } KB)</a>
                                         <a href="javascript:fn_deleteFile('${ fileList.fileSeq }', 'old');"><img src="<c:url value='/images/apt/blt_minus.png'/>" alt="파일삭제" /></a>
                                         
                                         <input type="hidden" id="fileSeq_${ fileList.fileSeq }" value="${ fileList.fileSeq }" /> 
                                     </li>                                              
                                    </c:forEach>                                        
                                </ul>
                                <ul id="newFileList" class="file_add_list mt10">
                                </ul>
                                <div>  
                                    <span class="info_msg">※ File size : size 멘트 대기</span>
                                </div>
                            </td>                  
                        </tr>
                        
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
                    <div class="right">
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                        <c:if test="${not empty resultDetail.noticeSeq}">
                            <span class="btn_gray2"><a href="javascript:void(0);" id="btnDel">삭제</a></span>
                        </c:if>
                    </div>
                </div>
                <!-- // btn_type3 -->           

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>
