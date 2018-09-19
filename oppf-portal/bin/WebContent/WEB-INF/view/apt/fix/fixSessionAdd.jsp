<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : fixCompanyCodeListPopup.jsp
 * @Description : 기업 코드 리스트 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.07   최판광       최초  생성
 * </pre>
 *
 * @author 최판광
 * @since 2017.03.07
 * @version 2.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = ["searchCmpanyCodeType", "searchCompanyCodeKind", "searchCompanyDivCode"];
var row = "";
var strCallBack = "";
/*******************************************
 * 이벤트 함수
 *******************************************/
/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        $(".wrap >").remove();
        if(opener){
            window.opener.fn_login();
            window.close();
        }else{
        	window.parent.fn_login();
        	gfn_closeModal();
        }
    </c:if>

    //아이디 확인
    $("#btnSave").click(function(){
    	fn_save();
    });
    
    /* [아이디재입력]버튼 클릭 시 호출 */
    $("#btnIdReInput").click(function(){
        $("#customerIdCheck").val("N"); //아이디체크 초기화
        $("#customerId").css("background-color","#ffffff");
        $("#customerId").attr("readonly",false);
        $("#btnIdReInput").hide(); //[아이디재입력]
        $("#btnCheckId").show(); //[중복확인]
		$("#custmerNameKor").text("");
		$("#customerId").val("");
        customerRegNo = "";
        customerCi = "";
        customerDn = "";
        
    });
    

	/* [btnCheckCompId}]중복체크 버튼 클릭 시 호출 */
	$("#btnCheckCompId").click(function(){

		if(util_chkReturn($.trim($('#sessionTargetCompId').val()), "s") == "") {
	    	alert("TargetCompID 은(는) 필수 선택값입니다.");
	        $("#sessionTargetCompId").focus();
	        return;
	    }
		
		if(util_chkReturn($.trim($('#sessionSenderCompId').val()), "s") == "") {
	    	alert("SenderCompID 은(는) 필수 선택값입니다.");
	        $("#sessionSenderCompId").focus();
	        return;
	    }
        $("#compIdCheck").val("Y");
        $("#sessionTargetCompId").css("background-color","silver");
        $("#sessionTargetCompId").attr("readonly",true);
        $("#sessionSenderCompId").css("background-color","silver");
        $("#sessionSenderCompId").attr("readonly",true);
        $("#btnCheckCompId").hide(); //[중복확인]
        $("#btnCompIdReInput").show(); //[아이디재입력]
        
        var url = "<c:url value='/apt/fix/fixCompIdDupChk.ajax'/>";
        var objParam = new Object();
        objParam.sessionTargetCompId = $("#sessionTargetCompId").val();
        objParam.sessionSenderCompId = $("#sessionSenderCompId").val();
        util_ajaxPage(url, objParam, "fn_ajaxCallback_chkCompId");
	});
	
	/* [btnCheckCompId}]재입력 버튼 클릭 시 호출 */
	$("#btnCompIdReInput").click(function(){
        $("#compIdCheck").val("N");
        $("#sessionTargetCompId").css("background-color","#ffffff");
        $("#sessionTargetCompId").attr("readonly",false);
        $("#sessionSenderCompId").css("background-color","#ffffff");
        $("#sessionSenderCompId").attr("readonly",false);
        $("#btnCheckCompId").show(); //[중복확인]
        $("#btnCompIdReInput").hide(); //[아이디재입력]
	    
	});


	
	/* [socket}]중복체크 버튼 클릭 시 호출 */
	$("#btnCheckCon").click(function(){

		if(util_chkReturn($.trim($('#connectionHost').val()), "s") == "") {
	    	alert("Socket 은(는) 필수 선택값입니다.");
	        $("#connectionHost").focus();
	        return;
	    }
		
		if(util_chkReturn($.trim($('#connectionPort').val()), "s") == "") {
	    	alert("Socket Port 은(는) 필수 선택값입니다.");
	        $("#connectionPort").focus();
	        return;
	    }

        $("#connectionCheck").val("Y");
        $("#connectionHost").css("background-color","silver");
        $("#connectionHost").attr("readonly",true);
        $("#connectionPort").css("background-color","silver");
        $("#connectionPort").attr("readonly",true);
        $("#btnCheckCon").hide(); //[중복확인]
        $("#btnConReInput").show(); //[아이디재입력]

        var url = "<c:url value='/apt/fix/fixSocketDupChk.ajax'/>";
        var objParam = new Object();
        objParam.connectionHost = $("#connectionHost").val();
        objParam.connectionPort = $("#connectionPort").val();
        util_ajaxPage(url, objParam, "fn_ajaxCallback_chkSocketPort");
	});

	/* [socket}]재입력 버튼 클릭 시 호출 */
	$("#btnConReInput").click(function(){
        $("#connectionCheck").val("N");
        $("#connectionHost").css("background-color","#ffffff");
        $("#connectionHost").attr("readonly",false);
        $("#connectionPort").css("background-color","#ffffff");
        $("#connectionPort").attr("readonly",false);
        $("#btnCheckCon").show(); //[중복확인]
        $("#btnConReInput").hide(); //[아이디재입력]
	    
	});
	
});

/*******************************************
 * 기능 함수
 *******************************************/



 /* [중복확인]ID체크 콜백 함수 */
 function fn_ajaxCallback_chkSocketPort(data){
     var cnt = data.idCnt;
     if(cnt > 0){
         alert("존재하는 Socket Port 입니다.");
         $("#connectionCheck").val("N");
         $("#connectionHost").css("background-color","#ffffff");
         $("#connectionHost").attr("readonly",false);
         $("#connectionPort").css("background-color","#ffffff");
         $("#connectionPort").attr("readonly",false);
         $("#btnCheckCon").show(); //[중복확인]
         $("#btnConReInput").hide(); //[아이디재입력]
     }else{
         alert("사용가능한 Socket Port 입니다.");
     }
 }


 /* [중복확인]ID체크 콜백 함수 */
 function fn_ajaxCallback_chkCompId(data){
     var cnt = data.idCnt;
     if(cnt > 0){
         alert("존재하는 compID 입니다.");
         $("#compIdCheck").val("N");
         $("#sessionTargetCompId").css("background-color","#ffffff");
         $("#sessionTargetCompId").attr("readonly",false);
         $("#sessionSenderCompId").css("background-color","#ffffff");
         $("#sessionSenderCompId").attr("readonly",false);
         $("#btnCheckCompId").show(); //[중복확인]
         $("#btnCompIdReInput").hide(); //[아이디재입력]
     }else{
         alert("사용가능한 compID 입니다.");
     }
 }





 function fn_save(){


	    if(util_chkReturn($.trim($('#compIdCheck').val()), "s") != "Y") {
	    	alert("CompID 중복확인을 해주세요.");
	        return;
	    }

		if(util_chkReturn($.trim($('#sessionTargetCompId').val()), "s") == "") {
	    	alert("TargetCompID 은(는) 필수 선택값입니다.");
	        $("#sessionTargetCompId").focus();
	        return;
	    }
		
		if(util_chkReturn($.trim($('#sessionSenderCompId').val()), "s") == "") {
	    	alert("SenderCompID 은(는) 필수 선택값입니다.");
	        $("#sessionSenderCompId").focus();
	        return;
	    }
		
		
		
		
	    if(util_chkReturn($.trim($('#connectionCheck').val()), "s") != "Y") {
	    	alert("Socket 중복확인을 해주세요.");
	        return;
	    }
	    

		if(util_chkReturn($.trim($('#connectionHost').val()), "s") == "") {
	    	alert("Socket 은(는) 필수 선택값입니다.");
	        $("#connectionHost").focus();
	        return;
	    }
		
		if(util_chkReturn($.trim($('#connectionPort').val()), "s") == "") {
	    	alert("Socket Port 은(는) 필수 선택값입니다.");
	        $("#connectionPort").focus();
	        return;
	    }
		

		if(util_chkReturn($.trim($('#initServerId').val()), "s") == "") {
	    	alert("Initiator 은(는) 필수 선택값입니다.");
	        $("#initServerId").focus();
	        return;
	    }
		
     
     //로딩 호출
     gfn_setLoading(true);
     
   //page setting  
     var url = "<c:url value='/apt/fix/fixSessionInsert.ajax'/>";
     var param = $("#FixManageVO").serialize();
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
     
     if(data.fixUrlError == -1){
         alert("등록된 서버정보가 없습니다.\n관지라에게 문의 해주세요.");
     	fn_list();
     }
     
     //로딩 호출
     gfn_setLoading(false);
     
     if(data.result == "200"){
         alert("정상적으로 등록 되었습니다.");
         fn_popupClose();
     }else{
         alert("등록에 실패하였습니다.");
         fn_popupClose();
     }
     return;
 }

 
/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
    	window.opener.fn_search("1");
        window.close();
    }else{
     	window.parent.fn_search("1");
        gfn_closeModal(this.event);
    }
} 
</script>
</head>
<body>
<form:form commandName="FixManageVO" name="FixManageVO" method="post">
<div class="wrap">
			<!-- layer_popup -->
			<div class="layer_popup_dev">

				<!-- #layer01 -->
				<div class="layer_box" id="layer01" style="width: 620px;">
					<!-- 가로크기 직접제어, 세로는 최대 500px -->
					<div class="layer_tit">Session 추가</div>

					<div class="layer_con">

						<div class="tb_write1">
							<table>
								<caption>Session 추가</caption>
								<colgroup>
									<col style="width: 30%;">
									<col style="width: *;">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="chk1">TargetCompID<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w100" id="sessionTargetCompId" name="sessionTargetCompId">
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk1">SenderCompID<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w100" id="sessionSenderCompId" name="sessionSenderCompId">&nbsp;
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckCompId">중복확인</a></span>
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnCompIdReInput" style="display:none;" >재입력</a></span>
		                                <input type="hidden" name="compIdCheck" id="compIdCheck" value="N" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk1">Socket<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w100" id="connectionHost" name="connectionHost">
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk1">Socket Port<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w100" id="connectionPort" name="connectionPort">&nbsp;
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckCon">확인</a></span>
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnConReInput" style="display:none;" >재입력</a></span>
		                                <input type="hidden" name="connectionCheck" id="connectionCheck" value="N" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk3">Initiator 선택<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><select id="initServerId" name="initServerId" style="min-width: 100px;">
												<option value="">선택</option>
											<c:forEach var="initiatorList" items="${initiatorList}" varStatus="status">
												<option value="${initiatorList.initServerId}">${initiatorList.initServerId} / ${initiatorList.initServerIp}</option>
											</c:forEach>
										</select>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_type3">
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
						</div>
					</div>
					<a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
				</div>
			</div>
			<!-- // layer_popup -->

		</div>
</form:form>
</body>
</html>