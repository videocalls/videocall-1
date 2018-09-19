<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrNewReg.jsp
 * @Description : 회원 신규 생성
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
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>


    <%-- 디자인 스크립트 --%>
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

    dateSet();
});

/*******************************************
 * 기능 함수
 *******************************************/
function dateSet() {
    $("#searchDateTo").val(util_setFmDate(util_getDate()));
    $("#searchTimeHourTo").val("00");
    $("#searchTimeMinuteTo").val("00");
}
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
    var url = "<c:url value='/apt/pushmng/pushAdd.ajax'/>";
    var param = $("#PushVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
    
}

function fn_saveCallBack(data){
    //로딩 호출
    gfn_setLoading(false);

    if(data.result !== ""){
        alert("정상적으로 등록되었습니다.");
        fn_list();
        return;
    }else{
        alert("등록에 실패하였습니다.");
        return;
    }
    return;
}

<%-- 저장전 체크 --%>
function fn_validate(){

    //플랫폼
    if(util_chkReturn($.trim($('input[name=deviceType]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='플랫폼'/>");
        return false;
    }
    
    //제목
    var pushMessageTitle=$("#pushMessageTitle").val();
    if(util_chkReturn($.trim(pushMessageTitle), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='제목'/>");
        $('#pushMessageTitle').focus();
        return false;
    }
    
    //타입
    var pushMessageType = $.trim($('input[name=pushMessageType]:checked').val());
    if(util_chkReturn(pushMessageType, "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='타입'/>");
        return false;
    }

    var contents = $.trim($('#contents').val());
    if(util_chkReturn(contents, "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='메세지'/>");
        $('#contents').focus();
        return false;
    }

    if(pushMessageType === "G041002") {
        // URL 필수 체크
        var contentsUrl = $.trim($('#contentsUrl').val());
        if(util_chkReturn(contentsUrl, "s") == "") {
            alert("<spring:message code='errors.required.select' arguments='URL'/>");
            $('#contentsUrl').focus();
            return false;
        }
    }
 
    //전송 유형
    var sendType = $.trim($('input[name=sendType]:checked').val());
    if(util_chkReturn(sendType, "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='전송 유형'/>");
        return false;
    }

    if(sendType === "G043002") {
        // 예약인 경우 날짜 시간 필수체크 및 유효성 체크
        var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));
        if(util_chkReturn(searchDateTo, "s") == ""){
            alert("<spring:message code='errors.required' arguments='예약일'/>");
            $('#searchDateTo').focus();
            return false;
        } else {
            if(!util_isDate(searchDateTo)){
                alert("<spring:message code='errors.invalid' arguments='예약일' />");
                $("#searchDateTo").focus();
                return false;
            }
        }

        var searchTimeHourTo = $("#searchTimeHourTo").val();
        if(util_chkReturn(searchTimeHourTo, "s") == "") {
            alert("<spring:message code='errors.required' arguments='예약시간'/>");
            $('#searchTimeHourTo').focus();
            return false;
        }else{
            if(!util_isNum(searchTimeHourTo)){
                alert("<spring:message code='errors.integer' arguments='예약시간'/>");
                $('#searchTimeHourTo').focus();
                return false;
            }else{
                if(Number(searchTimeHourTo) <= -1 || Number(searchTimeHourTo) > 24){
                    alert("<spring:message code='errors.range' arguments='예약시간,00,23'/>");
                    $('#searchTimeHourTo').focus();
                    return false;
                }
            }
        }

        var searchTimeMinuteTo = $("#searchTimeMinuteTo").val();
        if(util_chkReturn(searchTimeMinuteTo, "s") == "") {
            alert("<spring:message code='errors.required' arguments='예약시간'/>");
            $('#searchTimeMinuteTo').focus();
            return false;
        }else{
            if(!util_isNum(searchTimeMinuteTo)){
                alert("<spring:message code='errors.integer' arguments='예약시간'/>");
                $('#searchTimeMinuteTo').focus();
                return false;
            }else{
                if(Number(searchTimeMinuteTo) <= -1 || Number(searchTimeMinuteTo) > 59){
                    alert("<spring:message code='errors.range' arguments='예약시간,00,59'/>");
                    $('#searchTimeMinuteTo').focus();
                    return false;
                }
            }
        }
        
        if(sendType=='G043002') {
    		if(isFromToDate(searchDateTo,searchTimeHourTo,searchTimeMinuteTo)){
    			alert("전송시간이 현재시간 보다 빠를수 없습니다..");
    			return false;
    		}
        }

        var sendDate = searchDateTo + " " + searchTimeHourTo + searchTimeMinuteTo;
        $("#sendDate").val(sendDate);
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
    <input type="hidden" name="sendDate" id="sendDate" value="" />
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
                    <h2>Push 전송 관리</h2>
                </div>
                <!-- // locatioin -->
                
                                              
                <div class="tb_write1">
                    <table>
                        <caption>push 정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:20%;">
                            <col style="width:30%;">
                            <col style="width:20%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th scope="row" colspan="2">플랫폼<span class="icon_basic">*필수입력</span></th>
	                            <td class="txt_l" colspan="3">
	                                        <c:forEach items="${deviceTypeList}" var="deviceType" varStatus="status">
	                                            <input type="radio" name=deviceType id="deviceType_${deviceType.system_code}" value="${deviceType.system_code}" <c:if test="${status.index == 0}">checked</c:if> >
	                                                <label for="deviceType_${deviceType.system_code}">${deviceType.code_name_kor}</label>
	                                        </c:forEach> 
	                            </td>                  
	                        </tr>
	                        <tr>
	                            <th scope="row" colspan="2">제목<span class="icon_basic">*필수입력</span></th>
	                            <td class="txt_l" colspan="3"> 
	                            	<input type="text" name="pushMessageTitle" id="pushMessageTitle" value=""/>                           
	                            </td>                 
	                        </tr>
	                        <tr>
	                            <th scope="row" colspan="2">
	                                <label for="user_pw">타입<span class="icon_basic">*필수입력</span></label>
	                            </th>
	                            <td class="txt_l" colspan="3">
	                                        <c:forEach items="${pushMessageTypeList}" var="pushMessageTypeList" varStatus="status">
	                                            <input type="radio" name="pushMessageType" id="pushMessageType_${pushMessageTypeList.system_code}" value="${pushMessageTypeList.system_code}" <c:if test="${status.index == 0}">checked</c:if> >
	                                                <label for="pushMessageType_${pushMessageTypeList.system_code}">${pushMessageTypeList.code_name_eng}</label>
	                                        </c:forEach>                          
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row" colspan="2">
	                                <label for="user_pw">데이터</label>
	                            </th>
	                            <td class="txt_l" colspan="3">
									<table>
										<tr>
				                            <th scope="row" colspan="2">
				                                <label for="contents">메세지<span class="icon_basic">*필수입력</span></label>
				                            </th>
				                            <td class="txt_l" colspan="3"> 
	                                            <textarea name="contents" id="contents" cols="30" rows="10" ></textarea>
				                            </td>  			                            										
										</tr>
										<tr id="urlArea" style="display:none">
	                                        <th scope="row" colspan="2">
	                                            <label for="contentsUrl">URL<span class="icon_basic">*필수입력</span></label>
	                                        </th>
	                                        <td class="txt_l" colspan="3">
	                                            <input type="text" name="contentsUrl" id="contentsUrl" value=""/>
	                                        </td>
	                                    </tr>
									</table>                        
	                            </td>
	                        </tr>
	                         <tr>
	                            <th scope="row" colspan="2">
	                                <label for="user_pw">전송유형<span class="icon_basic">*필수입력</span></label>
	                            </th>
	                            <td class="txt_l" colspan="3">
	                                <c:forEach items="${sendTypeList}" var="sendType" varStatus="status">
	                                    <input type="radio" name="sendType" id="sendType_${sendType.system_code}" value="${sendType.system_code}"  <c:if test="${status.index == 0}">checked</c:if>>
	                                        <label for="sendType_${sendType.system_code}">${sendType.code_name_kor}</label>
	                                </c:forEach>
	                                <div id="dateArea" style="display:none;width:220px;">
	                                    <input type="text" id="searchDateTo" name="searchDateTo" style="width:80px;"/>
	                                    <select id="searchTimeHourTo" name="searchTimeHourTo">
	   												 <c:forEach var="i" begin="0" end="23" step="1">
				                                 		<option><c:out value="${i<10 ? '0':''}${i}"></c:out></option>
	    											</c:forEach>
	                                    </select> :
	                                    <select id="searchTimeMinuteTo" name="searchTimeMinuteTo">
	   												 <c:forEach var="i" begin="0" end="5" step="1">
				                                 		<option><c:out value="${i==0 ? '0':''}${i*10}"></c:out></option>
	    											</c:forEach>
	                                    </select>
	                                    <%--<input type="text" id="searchTimeHourTo" name="searchTimeHourTo" maxlength="2" style="width:20px;"/> :
	                                    <input type="text" id="searchTimeMinuteTo" name="searchTimeMinuteTo" maxlength="2" style="width:20px;"/>--%>
	                                </div>
	                                &nbsp;
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