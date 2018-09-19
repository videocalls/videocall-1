<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf"%>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : push 상세/수정
 * @Description : push 상세 / 수정
 * @Modification Information
 *
 * <pre>
 *  
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  
 * </pre>
 *
 * @author 
 * @since 
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf"%>
<script language="javascript" type="text/javascript">
        $(function () {
            // 달력
            $("#searchDateTo").datepicker({
                minDate: '0d',
                maxDate: '+1y',
                <%--currentText: util_getDate()--%>
                showOn: "button",
                dateFormat: 'yy-mm-dd',
                buttonImage: "/images/apt/calendar.png",
                buttonImageOnly: true,
                buttonText: "달력보기",
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
    var url = "<c:url value='/apt/pushmng/pushSendList.do'/>";
    var param = new Object();
    param.paramMenuId = "01001";
    
    gfn_loginNeedMove(url, param);
}



function changeRadioType(str){
	
	if(str.value=='G041001'){
		$('#contentsUrl').val("");
	}/* else{
		$('#contentsUrl').val("");
	} */	
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
    
    
    $("input[name=sendType]").on("change", function() {
        if($(this).val() === "G043002") {
            $("#dateArea").css("display", "inline-block");
        } else {
            $("#dateArea").css("display", "none");
        }
    });

    
    $("input[name=pushMessageType]").on("change", function() {
        if($(this).val() === "G041002") {
            $("#urlArea").css("display", "table-row");
        } else {
            $("#urlArea").css("display", "none");
        }
    });

   /*  dateSet(); */
    
        
});

/*******************************************
 * 기능 함수
 *******************************************/

/*  function dateSet() {
	    $("#searchDateTo").val(util_setFmDate(util_getDate()));
	    $("#searchTimeHourTo").val("00");
	    $("#searchTimeMinuteTo").val("00");
	} */
	
	
 <%-- 목록 --%>
 function fn_list(){
     util_moveRequest("PushVO", "<c:url value='/apt/pushmng/pushSendList.do'/>", "_self");
 }
 
<%-- 저장 --%>
function fn_save(){
	
    if(!fn_validate()){
        return;
    }
	       
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/pushmng/pushUpdate.ajax'/>";
    var param = $("#PushVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
    
}

function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);

    if(data.result !== ""){
        alert("정상적으로 수정되었습니다.");
        fn_list();
        return;
    }else{
        alert("수정에 실패하였습니다.");
        return;
    }
    return;
}

<%-- 저장전 체크 --%>
function fn_validate(){
	
    var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
    var searchTimeHourTo = $("#searchTimeHourTo").val();
    var searchTimeMinuteTo = $("#searchTimeMinuteTo").val();
    var sendType = $.trim($('input[name=sendType]:checked').val());

    if(sendType=='G043002') {
		if(isFromToDate(searchDateTo,searchTimeHourTo,searchTimeMinuteTo)){
			alert("이미 전송이 완료되어 수정이 불가능합니다.");
			return false;
		}
    }

    var sendDate = searchDateTo + " " + searchTimeHourTo + searchTimeMinuteTo;
    $("#sendDate").val(sendDate);
	
    
    var pushMessageType = $.trim($('input[name=pushMessageType]:checked').val());
    if(pushMessageType=='G041001 '){
    	$('#contentsUrl').val("");
    }
       
    //제목
    var pushMessageTitle=$("#pushMessageTitle").val();
    if(util_chkReturn($.trim(pushMessageTitle), "s") == "") {
        alert("<spring:message code='errors.required' arguments='제목'/>");
        $('#pushMessageTitle').focus();
        return false;
    }
	
    return true;
}


function isFromToDate(searchDateTo, searchTimeHourTo, searchTimeMinuteTo) {
    var startDate = new Date(searchDateTo.substring(0, 4),searchDateTo.substring(4, 6) - 1,searchDateTo.substring(6, 8),searchTimeHourTo,searchTimeMinuteTo);
    var nowDate = new Date();
    return nowDate.getTime() >= startDate.getTime();
}

</script>

</head>

<body>
	<form:form commandName="PushVO" name="PushVO" method="post">
		<input type="hidden" name="pushMessageRegno" id="pushMessageRegno" value="${pushDtl.pushMessageRegno}" />
		<input type="hidden" name="sendDate" id="sendDate" value="" />
		<%-- 탑과 대메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/common-include-top.jspf"%>
		<%-- 탑과 대메뉴 영역 --%>

		<!-- // wrap_top -->
		<article class="container">
			<div>
				<%-- lnb(좌측메뉴) 영역 --%>
				<%@ include file="/WEB-INF/view/cmm/common-include-left.jspf"%>
				<%-- lnb(좌측메뉴) 영역 --%>

				<!-- content -->
				<section class="content">
					<div class="location">
						<h2>Push 전송 관리</h2>
					</div>
					<!-- // locatioin -->
					<div class="tb_write1">
						<table>
							<caption>push 정보</caption>
							<colgroup>
								<col style="width: 20%;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" colspan="2" width="100px">플랫폼<span class="icon_basic">*필수입력</span>
									</th>
									<td class="txt_l" colspan="3">
									<c:if	test="${pushDtl.sendYn=='N'}">
											<c:forEach items="${deviceTypeList}" var="deviceTypeList" varStatus="status">
												<input type="radio" name=deviceType	id="deviceType_${deviceTypeList.system_code}" value="${deviceTypeList.system_code}"
													<c:if test="${pushDtl.deviceType eq deviceTypeList.system_code}"> checked="checked" </c:if>
													>
												<label for="deviceType_${deviceTypeList.system_code}">${deviceTypeList.code_name_kor}</label>
											</c:forEach>
										</c:if> 
										<c:if test="${pushDtl.sendYn=='Y'}">
											<c:forEach items="${deviceTypeList}" var="deviceTypeList" varStatus="status">
												<c:if test="${pushDtl.deviceType eq deviceTypeList.system_code}">${deviceTypeList.code_name_kor}</c:if>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<th scope="row" colspan="2">제목<span class="icon_basic">*필수입력</span></th>
									<td class="txt_l" colspan="3">
									<c:if test="${pushDtl.sendYn=='N'}">
											<input type="text" name="pushMessageTitle" id="pushMessageTitle" value="${pushDtl.pushMessageTitle}" />
									</c:if> 
									<c:if test="${pushDtl.sendYn=='Y'}">
                            			${pushDtl.pushMessageTitle}
									</c:if>
									</td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="sendType">타입<span class="icon_basic">*필수입력</span></label>
									</th>
									<td class="txt_l" colspan="3">
									<c:if test="${pushDtl.sendYn=='N'}">
										<c:forEach items="${pushMessageTypeList}" var="pushMessageTypeList" varStatus="status">
											<input type="radio" name="pushMessageType" id="pushMessageType_${pushMessageTypeList.system_code}" value="${pushMessageTypeList.system_code}"
												<c:if test="${pushDtl.pushMessageType eq pushMessageTypeList.system_code}"> checked="checked" </c:if>
												onchange="changeRadioType(this)">
											<label for="pushMessageType_${pushMessageTypeList.system_code}">${pushMessageTypeList.code_name_eng}</label>
										</c:forEach>
									</c:if> 
									<c:if test="${pushDtl.sendYn=='Y'}">
										<c:forEach items="${pushMessageTypeList}" var="pushMessageTypeList" varStatus="status">
											<c:if	test="${pushDtl.pushMessageType eq pushMessageTypeList.system_code}">${pushMessageTypeList.code_name_eng}</c:if>
										</c:forEach>
									</c:if>
								</td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="data">데이터</label></th>
									<td class="txt_l" colspan="3">
										<table>
											<c:if test="${pushDtl.sendYn=='Y'}">
												<tr>
													<th scope="row" colspan="2" width="100px"><label	for="contents">메세지<span class="icon_basic">*필수입력</span></label></th>
														<td class="txt_l" colspan="3">
															<textarea name="contents" id="contents" cols="30" rows="10" readonly="readonly">${pushDtl.contents}</textarea>
														</td>
												</tr>
												<c:if test="${pushDtl.pushMessageType=='G041002'}">
													<tr id="urlArea">
												</c:if>
												<c:if test="${pushDtl.pushMessageType=='G041001'}">
													<tr id="urlArea" style="display: none">
												</c:if>
												<th scope="row" colspan="2"><label for="contentsUrl">URL<span class="icon_basic">*필수입력</span></label></th>
													<td class="txt_l" colspan="3">${pushDtl.contents}</td>
												</tr>
											</c:if>
											<c:if test="${pushDtl.sendYn=='N'}">
												<tr>
													<th scope="row" colspan="2" width="100px"><label for="contents">메세지</label></th>
													<td class="txt_l" colspan="3">
													<textarea name="contents" id="contents" cols="30" rows="10">${pushDtl.contents}</textarea>
													</td>
												</tr>
												<c:if test="${pushDtl.pushMessageType=='G041002'}">
													<tr id="urlArea">
												</c:if>
												<c:if test="${pushDtl.pushMessageType=='G041001'}">
													<tr id="urlArea" style="display: none">
												</c:if>
												<th scope="row" colspan="2"><label for="contentsUrl">URL<span class="icon_basic">*필수입력</span></label></th>
													<td class="txt_l" colspan="3">
														<input type="text" name="contentsUrl" id="contentsUrl" value="${pushDtl.contentsUrl}" maxlength="200" />
													</td>
												</tr>
											</c:if>
										</table>
									</td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="sendDate">전송 일시</label></th>
									<td class="txt_l" colspan="3">
									<c:if test="${pushDtl.sendYn=='N'}">
											<c:forEach items="${sendTypeList}" var="sendTypeList" varStatus="status">
												<input type="radio" name="sendType"	id="sendType_${sendTypeList.system_code}" value="${sendTypeList.system_code}"
													<c:if test="${pushDtl.sendType eq sendTypeList.system_code}"> checked="checked" </c:if>
													>
												<label for="sendType_${sendTypeList.system_code}">${sendTypeList.code_name_kor}</label>
											</c:forEach>
											<c:if test="${pushDtl.sendType=='G043001'}">
												<div id="dateArea" style="display: none; width: 300px;">
											</c:if>
											<c:if test="${pushDtl.sendType=='G043002'}">
												<div id="dateArea" style="display: inline-block; width: 300px;">
											</c:if>
											<input type="text" id="searchDateTo" name="searchDateTo"style="width: 80px;" value="${fn:substring(pushDtl.sendDate, 0, 11)}" />
											<select name="searchTimeHourTo" id="searchTimeHourTo">
												<c:forEach var="i" begin="0" end="23" step="1">
													<c:if test="${i<10}">
														<option 
															<c:if test="${fn:substring(pushDtl.sendDate, 12, 13) eq i}">selected="selected"</c:if>><c:out value="${i<10 ? '0':''}${i}"></c:out>
														</option>
													</c:if>
													<option
														<c:if test="${fn:substring(pushDtl.sendDate, 11, 13) eq i}">selected="selected"</c:if>><c:out value="${i<10 ? '0':''}${i}"></c:out>
													</option>
												</c:forEach>
											</select> :
			                                 	<select name="searchTimeMinuteTo" 	id="searchTimeMinuteTo">
												<c:forEach var="i" begin="0" end="5" step="1">
													<option
														<c:if test="${fn:substring(pushDtl.sendDate, 14, 15) eq i}">selected="selected"</c:if>><c:out
															value="${i==0 ? '0':''}${i*10}"></c:out>
													</option>
												</c:forEach>
											</select>
											</div>
										</c:if> 
										<c:if test="${pushDtl.sendYn=='Y'}">
											<c:forEach items="${sendTypeList}" var="sendTypeList" varStatus="status">
												<c:if test="${pushDtl.sendType eq sendTypeList.system_code}">
                                                 ${sendTypeList.code_name_kor}
                                                </c:if>
											</c:forEach>
 										 / <c:out value='${pushDtl.sendDate}' />
										</c:if></td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="send_Yn">전송 상태</label></th>
									<td class="txt_l" colspan="3">${pushDtl.sendYn=='Y'? "완료":"대기"}
									</td>
								</tr>
								<c:if test="${pushDtl.sendYn=='Y'}">
									<tr>
										<th scope="row" colspan="2"><label for="">전송 대상</label></th>
										<td class="txt_l" colspan="3">${pushDtl.totalCount}건 (성공 ${pushDtl.successCount}/ 오류 ${pushDtl.failCount})</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<div class="btn_type3">
						<div class="left">
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
						</div>
						<c:if test="${pushDtl.sendYn=='N'}">
							<div class="right">
								<span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
							</div>
						</c:if>
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