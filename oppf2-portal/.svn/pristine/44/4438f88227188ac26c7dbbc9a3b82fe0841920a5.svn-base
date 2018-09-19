<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<%--
/**  
 * @Name : fixInitiatorAdd.jsp
 * @Description : Initiator 등록
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
<head>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>


<script language="javascript" type="text/javascript">


/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = ["useStatus"];

var initServerIp =  "<c:out value='${result.initServerIp}'/>";

var initSenderCompId =  "<c:out value='${result.initSenderCompId}'/>";
/*******************************************
 * 이벤트 함수
 *******************************************/
 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserAccountList.do'/>";
    var param = new Object();
    param.paramMenuId = "01003";
    
    gfn_loginNeedMove(url, param);
}


$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>
	

    $("#btnSave").click(function(){
    	fn_save();
    });
    
    $("#btnDel").click(function(){
    	fn_del();
    });
    
    /* [Initiator IP}]중복체크 버튼 클릭 시 호출 */
    $("#btnChkIp").click(function(){

        if(util_chkReturn($.trim($('#initServerIp').val()), "s") == "") {
        	alert("Initiator IP 은(는) 필수 선택값입니다.");
            $("#initServerIp").focus();
            return;
        }

		$("#initServerIpCheck").val("Y");
		$("#initServerIp").css("background-color","silver");
		$("#initServerIp").attr("readonly",true);
		$("#btnChkIp").hide(); //[중복확인]
		$("#btnReChkIp").show(); //[아이디재입력]

    	if("<c:out value='${result.initServerIp}'/>" ==  $("#initServerIp").val()){
    	} else {
			var url = "<c:url value='/apt/fix/fixInitiatoServerIpDupChk.ajax'/>";
			var objParam = new Object();
			objParam.initServerIp = $("#initServerIp").val();
			util_ajaxPage(url, objParam, "fn_ajaxCallback_chkServerIp");
    	}
    });

    /* [Initiator IP}]재입력 버튼 클릭 시 호출 */
    $("#btnReChkIp").click(function(){
        $("#initServerIpCheck").val("N"); //아이디체크 초기화
        $("#initServerIp").css("background-color","#ffffff");
        $("#initServerIp").attr("readonly",false);
        $("#btnReChkIp").hide(); //[아이디재입력]
        $("#btnChkIp").show(); //[중복확인]
        $("#initServerIp").focus();
    });

	

    if(util_chkReturn($.trim($('#initServerIp').val()), "s") != "") {

		$("#initServerIpCheck").val("Y");
		$("#initServerIp").css("background-color","silver");
		$("#initServerIp").attr("readonly",true);
		$("#btnChkIp").hide(); //[중복확인]
		$("#btnReChkIp").show(); //[아이디재입력]
        
    }
    

    if(util_chkReturn($.trim($('#initSenderCompId').val()), "s") != "") {

        $("#initSenderCompIdCheck").val("Y");
        $("#initSenderCompId").css("background-color","silver");
        $("#initSenderCompId").attr("readonly",true);
        $("#btnChkCompId").hide(); //[중복확인]
        $("#btnReChkCompId").show(); //[아이디재입력]
    }
    
    if(util_chkReturn($.trim($('#socketHost').val()), "s") != "" && util_chkReturn($.trim($('#socketPort').val()), "s") != "") {


        $("#socketHost").css("background-color","silver");
        $("#socketHost").attr("readonly",true);
    	
        $("#socketPortCheck").val("Y");
        $("#socketPort").css("background-color","silver");
        $("#socketPort").attr("readonly",true);
        $("#btnChkSocketPort").hide(); //[중복확인]
        $("#btnReChkSocketPort").show(); //[아이디재입력]
        
    }

});



/* [중복확인]ID체크 콜백 함수 */
function fn_ajaxCallback_chkServerIp(data){
    var cnt = data.ipCnt;
    if(cnt > 0){
        alert("존재하는 ServerIp입니다.");
        $("#initServerIpCheck").val("N"); //아이디체크 초기화
        $("#initServerIp").css("background-color","#ffffff");
        $("#initServerIp").attr("readonly",false);
        $("#btnReChkIp").hide(); //[아이디재입력]
        $("#btnChkIp").show(); //[중복확인]
        $("#initServerIp").focus();
    }else{
        alert("사용가능한 ServerIp 입니다.");
        $("#initServerIp").focus();
    }
}




/* [중복확인]ID체크 콜백 함수 */
function fn_ajaxCallback_chkCompId(data){
    var cnt = data.idCnt;
    if(cnt > 0){
        alert("존재하는 SenderCompId 입니다.");

	    $("#initSenderCompIdCheck").val("N"); //아이디체크 초기화
	    $("#initSenderCompId").css("background-color","#ffffff");
	    $("#initSenderCompId").attr("readonly",false);
	    $("#btnReChkCompId").hide(); //[아이디재입력]
	    $("#btnChkCompId").show(); //[중복확인]
	    $("#initSenderCompId").focus();
    }else{
        alert("사용가능한 SenderCompId 입니다.");
        $("#initSenderCompId").focus();
    }
}



/* [Socket Port] 콜백 함수 */
function fn_ajaxCallback_chkSocketPort(data){
    var cnt = data.idCnt;
    if(cnt > 0){
        alert("존재하는 Socket Port 입니다.");

	    $("#socketPortCheck").val("N"); //아이디체크 초기화
	    $("#socketHost").css("background-color","#ffffff");
	    $("#socketHost").attr("readonly",false);
	    $("#socketPort").css("background-color","#ffffff");
	    $("#socketPort").attr("readonly",false);
	    $("#btnReChkSocketPort").hide(); //[아이디재입력]
	    $("#btnChkSocketPort").show(); //[중복확인]
	    $("#socketPort").focus();
	    
    }else{
        alert("사용가능한 Socket Port 입니다.");
        $("#socketPort").focus();
    }
}



function fn_save(){

	var senderCompName =  "<c:out value='${result.senderCompName}'/>";
	var companyId =  "<c:out value='${result.companyId}'/>";
	if(util_chkReturn($.trim($('#initServerIpCheck').val()), "s") != "Y") {
    	alert("Server IP 중복확인을 해주세요.");
        $("#initServerIp").focus();
        return;
    }

    if(util_chkReturn($.trim($('#initServerIp').val()), "s") == "") {
    	alert("Server IP 은(는) 필수 선택값입니다.");
        $("#initServerIp").focus();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/fix/fixInitiatorUpdate.ajax'/>";
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
        alert("정상적으로 수정 되었습니다.");
        fn_popupClose();
    }else{
        alert("수정에 실패하였습니다.");
        fn_popupClose();
    }
    return;
}

function fn_del(){


	 if(!confirm("삭제 하시겠습니까?")){
			return false;
	} 
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/fix/fixInitiatorDel.ajax'/>";
    var param = $("#FixManageVO").serialize();
    var callBackFunc = "fn_delCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}

function fn_delCallBack(data){
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
        alert("정상적으로 삭제 되었습니다.");
        fn_popupClose();
    }else{
        alert("삭제에 실패하였습니다.");
        fn_popupClose();
    }
    return;
}



//function fn_list(){
//    util_moveRequest("FixManageVO", "<c:url value='/apt/fix/fixInitiator.do'/>", "_self");
//}


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
				<div class="layer_box" id="layer01" style="width:420">
					<!-- 가로크기 직접제어, 세로는 최대 500px -->
					<div class="layer_tit">Initiator 수정</div>

					<div class="layer_con">
		
						<div class="tb_write1">
							<table>
								<caption>Initiator 수정</caption>
								<colgroup>
									<col style="width:30%;">
									<col style="width:*;">
								</colgroup>
								<tbody>
								<tr>
									<th scope="row">Initiator ID</th>
									<td class="txt_l"> ${result.initServerId}
									<input type="hidden" name="initServerId" id="initServerId" value="${result.initServerId}" />
									</td>
								</tr>
		                        <tr>
									<th scope="row"><label for="chk2">Initiator IP<span class="icon_basic">*필수입력</span></label></th>
									<td class="txt_l">
										<input type="text" class="w100" id="initServerIp" name="initServerIp" value="${result.initServerIp}">&nbsp;&nbsp;
		                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnChkIp">중복확인</a></span>
		                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnReChkIp" style="display:none;" >재입력</a></span>
		                                <input type="hidden" name="initServerIpCheck" id="initServerIpCheck" value="N" />
									</td>
								</tr>
								<tr>
									<th scope="row">등록일시</th>
									<td class="txt_l">${result.createTime}</td>
								</tr>
								<tr>
									<th scope="row">수정일시</th>
									<td class="txt_l">${result.updateTime}</td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_type3">
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnDel">삭제</a></span>
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
						</div>
					</div>
					<a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
                </div>
			</div>

		</div>
</form:form>
</body>
</html>