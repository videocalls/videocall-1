<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : faqManageSave.jsp
 * @Description : 관리자 자주 묻는 질문(FAQ) 등록/수정
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
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cmm/faq/faqManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "08002";
    
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
	
	<c:if test="${empty resultDetail.faqSeq}">
	   //게시일 기본 셋팅
	   $("#dateFrom").val(util_getDate("-"));
	   $("#dateTo").val(util_setFmDate("<spring:message code='Globals.format.dateTo' />", "-"));
	</c:if>
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
	util_moveRequest("FaqManageVO", "<c:url value='/cmm/faq/faqManageList.do'/>");
}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
	var msg = "등록 중입니다.";
	if(util_chkReturn($.trim($('#faqSeq').val()), "s") != "") msg = "수정 중입니다.";
    gfn_setLoading(true, msg);
	
	var moveUrl = "<c:url value='/cmm/faq/insertFaq.ajax'/>";
	if(util_chkReturn($.trim($('#faqSeq').val()), "s") != "") moveUrl = "<c:url value='/cmm/faq/updateFaq.ajax'/>";
    var param = $("#FaqManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
	//insert
	if(util_chkReturn($.trim($('#faqSeq').val()), "s") == ""){
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
    if(util_chkReturn($.trim($('#faqKind').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='노출종류'/>");
        $('#faqKind').focus();
        return false;
    }
	
	//표시순서
	if(util_chkReturn($.trim($('#faqOrder').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='표시순서'/>");
        $('#faqOrder').focus();
        return false;
    }else{
    	if(!util_isNum($.trim($('#faqOrder').val()))){
    		alert("<spring:message code='errors.integer' arguments='표시순서'/>");
            $('#faqOrder').focus();
            return false;    		
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
    
    $("#faqStartDate").val(dateFrom);
    $("#faqExpireDate").val(dateTo);
    
    //제목
    if(util_chkReturn($.trim($('#faqTitle').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='제목'/>");
        $('#faqTitle').focus();
        return false;
    }else{
    	if(util_calcBytes($.trim($('#faqTitle').val())) > 100){
    		alert("<spring:message code='errors.maxlength' arguments='제목,50'/>");
    		$('#faqTitle').focus();
            return false;
    	}
    }
    
    //내용
    if(util_chkReturn($.trim($('#faqContent').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $('#faqContent').focus();
        return false;
    }
    /*
    else{
    	if(util_calcBytes($.trim($('#faqContent').val())) > 100){
            alert("<spring:message code='errors.maxlength' arguments='내용,150'/>");
            $('#faqContent').focus();
            return false;
        }
    }
    */
	
	return true;
}

<%-- 삭제 --%>
function fn_delete(){
	if(!confirm("<spring:message code='confirm.delete.msg' />")){
		return;
	}
	
	//로딩 호출
    gfn_setLoading(true, "삭제 중입니다.");
	
	var moveUrl = "<c:url value='/cmm/faq/deleteFaq.ajax'/>";
    var param = $("#FaqManageVO").serialize();
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
<form:form commandName="FaqManageVO" name="FaqManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색조건 -->
<input type="hidden" name="searchFaqKind" id="searchFaqKind" value="<c:out value='${paramVO.searchFaqKind}'/>" /><!-- //검색조건 -->
<input type="hidden" name="schDateFrom" id="schDateFrom" value="<c:out value='${paramVO.schDateFrom}'/>" /><!-- //검색조건 -->
<input type="hidden" name="schDateTo" id="schDateTo" value="<c:out value='${paramVO.schDateTo}'/>" /><!-- //검색조건 -->

<input type="hidden" name="faqSeq" id="faqSeq" value="<c:out value='${resultDetail.faqSeq}'/>" />
<input type="hidden" name="faqStartDate" id="faqStartDate" value="" />
<input type="hidden" name="faqExpireDate" id="faqExpireDate" value="" />



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
                    <h2>자주 묻는 질문&nbsp;
                        <c:choose>
                            <c:when test="${empty resultDetail.faqSeq}">등록</c:when>
                            <c:otherwise>수정</c:otherwise>
                        </c:choose>						                         
                    </h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>FAQ&nbsp;
                        <c:choose>
                            <c:when test="${empty resultDetail.faqSeq}">등록</c:when>
                            <c:otherwise>수정</c:otherwise>
                        </c:choose> 
                        </caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">노출종류<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <c:forEach items="${faqKindList}" var="faqKindList" varStatus="status">
                            		<c:if test="${faqKindList.system_code != 'G003004'}">
												<input type="radio" name="faqKind"	id="faqKind" value="${faqKindList.system_code}"
													<c:if test="${resultDetail.faqKind eq faqKindList.system_code}">  checked="checked" </c:if>
													>
												<label for="faqKind">${faqKindList.code_name_eng}</label>
									</c:if>
									</c:forEach>
                            </td>                                        
                        </tr>
                        
                        <tr>
                            <th scope="row">노출구분<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
									<input type="radio" name="displayServerType"	id="displayServerType" value=""
									<c:if test="${resultDetail.displayServerType =='' || resultDetail.displayServerType == null}">  checked="checked" </c:if>
									>
									<label for="displayServerType">전체</label>
                            		<c:forEach items="${displayServerTypeList}" var="displayServerTypeList" varStatus="status">
												<input type="radio" name="displayServerType"	id="displayServerType" value="${displayServerTypeList.system_code}"
													<c:if test="${resultDetail.displayServerType eq displayServerTypeList.system_code}">  checked="checked" </c:if>
													>
												<label for="displayServerType">${displayServerTypeList.code_name_eng}</label>
									</c:forEach>
                            </td>                  
                        </tr>                        
                        
                        <tr>
                            <th scope="row">표시순서<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="faqOrder" name="faqOrder" value="${resultDetail.faqOrder}" />
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">사용여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="radio" id="useYn_Y" name="useYn" value="Y"
                                    <c:if test="${resultDetail.useYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="useYn_Y"> 사용 </label>
                                <input type="radio" id="useYn_N" name="useYn" value="N"
                                    <c:if test="${resultDetail.useYn eq 'N'}"> checked="checked" </c:if>
                                > <label for="useYn_N"> 미사용 </label>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row">게시일<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" id="dateFrom" name="dateFrom" value="${resultDetail.faqStartDate}" style="width:80px;"/>
                                <span class="dateDot">~</span>
                                <input type="text" id="dateTo" name="dateTo" value="${resultDetail.faqExpireDate}" style="width:80px;"/>
                            </td>                  
                        </tr>
                        
                        <c:if test="${not empty resultDetail.faqSeq}">
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
                                <input type="text" id="faqTitle" name="faqTitle" value="${resultDetail.faqTitle}" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">내용<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <textarea id="faqContent" name="faqContent" >${resultDetail.faqContent}</textarea>
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
                        <c:if test="${not empty resultDetail.faqSeq}">
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