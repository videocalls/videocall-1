<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf"%>
<link rel="stylesheet" href="/css/apt/import.css">

<script type="text/javascript">
/*******************************************
 * 전역 변수
 *******************************************/
var tmpAppList = "";

var g_resultSvcAccountList;     //조회된 account 정보

var g_saveAccountList;          //account 정보 저장 정보
var g_savePubCompanyList;       //핀테크 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
var g_saveSubCompanyList;       //금투사 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
var customerRegNo = "";
var serviceRegNo = "";
/*******************************************
 * 전역 변수 End
 *******************************************/

/* 화면 로드 처리 */
$(document).ready(function() {
<%-- 로그인 처리 --%>
<c:if test="${empty LoginVO}">
	$(".wrap >").remove();
	if (opener) {
		window.opener.fn_login();
		window.close();
	} else {
		window.parent.fn_login();
		gfn_closeModal();
	}
	</c:if>
	
    
    //아이디 확인
    $("#btnCheckId").click(function(){
    	fn_checkId();
    });
	

    /* [아이디재입력]버튼 클릭 시 호출 */
    $("#btnIdReInput").click(function(){
    	fn_IdReInput();
    });
    
    /* [저장]버튼 클릭 시 호출 */
    $("#btnSave").click(function(){
    	fn_save();
    });
    

    //아이디 찾기 이메일 주소 타입 변경 시
    $('#appList').change(function(){
    	if($(this).val()==""){
    		alert("<spring:message code='errors.required' arguments='앱 이름'/>");
        	$("#accNoList").remove();
            $("#appList").focus();
        } else {
        	
        	$("#accNoList").remove();

        	$("#searchAppId").val($(this).val());
        	
        	tmpAppList = $(this).val();;
        	
        	fn_checkId();
        }
    	
    });
	
});


<%-- ID 확인 버튼 클릭 --%>
function fn_checkId() {
	

    if(util_chkReturn($('#customerId').val(), "s") == ""){
        alert("<spring:message code='errors.required' arguments='아이디'/>");
        $("#customerId").focus();
        return false;
    }
	
	//로딩 호출
	gfn_setLoading(true);

	//page setting  
	var url = "<c:url value='/apt/sptUsr/selectMemberInfo.ajax'/>";
	var param = $("#NewMbrRegVO").serialize();
	var callBackFunc = "fn_checkIdCallBack";
	
	<%-- 공통 ajax 호출 --%>
	util_ajaxPage(url, param, callBackFunc);
	
}


<%-- ID 확인 callback --%>
function fn_checkIdCallBack(data) {
	//로그인 처리
	if (data.error == -1) {
		fn_login();
		return;
	}

	//로딩 호출
	gfn_setLoading(false);


	if (data.resultVO != null) {
		
		var resultVO = data.resultVO;
		var customerNameKor = resultVO.customerNameKor;
		customerRegNo = resultVO.customerRegNo;

        customerId = $("#customerId").val();
        
        customerCi = data.customerCiVO;
        customerDn = data.customerDnVO;
        
		$("#custmerNameKor").text(resultVO.customerNameKor);
        $("#customerIdCheck").val("Y");
        $("#customerId").css("background-color","silver");
        $("#customerId").attr("readonly",true);
        $("#btnCheckId").hide(); //[중복확인]
        $("#btnIdReInput").show(); //[아이디재입력]
        $("#customerId").focus();
        
        var resultSvcList = data.resultSvcList;
        $("#appList option").each(function() {	//id가 city인 option요소에 적용할 반복문
			$("#appList option:eq(0)").remove();	// city option의 0번째 항목이 없을때까지 0번쨰 항목을 지운다. (기존에 있는거 모두 지운다.)
		});
        
		for(var idx=0; idx<resultSvcList.length; idx++) {	// 새로 가져온 데이터를 데이터 갯수만큼 반복해서 붙여준다.
			if(idx==0){
				$("#appList").append("<option value=''>선택</option>");
			}

			if(tmpAppList == resultSvcList[idx].appId){
				$("#appList").append("<option value='"+resultSvcList[idx].appId+"' selected>"+resultSvcList[idx].appName+"</option>");
			} else {
				$("#appList").append("<option value='"+resultSvcList[idx].appId+"'>"+resultSvcList[idx].appName+"</option>");
			}
		}
		
		if(resultSvcList != null && resultSvcList.length > 0 ){
			var resultVtAccnoList = data.resultSvcAccountList;
			
			$("#accNoTd").append("<ul class='ml10' id='accNoList' name='accNoList' style='height:70px; overflow-y:auto;' >");
			if(resultVtAccnoList != null && resultVtAccnoList.length > 0 ){

				if(resultVtAccnoList != null || resultVtAccnoList.length > 0 ){
					for(var idx=0; idx<resultVtAccnoList.length; idx++) {	// 새로 가져온 데이터를 데이터 갯수만큼 반복해서 붙여준다.
			
			            var key = resultVtAccnoList[idx].appId + "_" + resultVtAccnoList[idx].apiId + "_" + resultVtAccnoList[idx].customerRealaccountNo + "_" + resultVtAccnoList[idx].serviceRegNo + "_" + resultVtAccnoList[idx].accountRegNo + "_" + resultVtAccnoList[idx].subcompanyCodeId + "_" + resultVtAccnoList[idx].pubcompanyCodeId;
						
						if(resultVtAccnoList[idx].useYn == "Y"){
							$("#accNoList").append("<li><input type='checkbox' name='customerVtAccountNo' id='"+key+"' value='"+key+"_U' Enalbe='true' checked disabled> <label for='"+key+"' class='chk_box'>"+ resultVtAccnoList[idx].pubcompanyName +" / "+ resultVtAccnoList[idx].customerVtaccountNo +" / "+ resultVtAccnoList[idx].customerVtaccountAlias +"</label></li>");	
						}
			
						if(resultVtAccnoList[idx].useYn == "N"){
							$("#accNoList").append("<li><input type='checkbox' name='customerRealaccountNo' id='"+key+"' value='"+key+"_I'> <label for='"+key+"' class='chk_box'>"+ resultVtAccnoList[idx].pubcompanyName +" / "+ resultVtAccnoList[idx].customerVtaccountNo +" / "+ resultVtAccnoList[idx].customerVtaccountAlias +"</label></li>");	
						}
					}
				} else {

					$("#accNoList").append("<li>연결 가능한 계좌가 없습니다.</li>");
				}
			}
			$("#accNoTd").append("</ul>");
			
			
		}

		
	} else {
		
		alert("검색된 아이디가 없습니다.");
		
	}
	
	return;
}


<%-- 저장 버튼 클릭 --%>
function fn_save(){
		

    if(util_chkReturn($('#customerId').val(), "s") == ""){
        alert("아이디를 확인하셔야 합니다.");
        $("#customerId").focus();
        return false;
    }
	
		if(util_chkReturn($.trim($('#appList').val()), "s") == "") {
			alert("앱 이름을  선택 해 주세요.");
			return;
		}
			
		
		//가상계좌 선택 여부 체크
		var customerRealaccountNoFlag = false;
		$.each($("input[name=customerRealaccountNo]"), function(){
			if($(this).is(":checked")){
				customerRealaccountNoFlag = true;
			}
		});
		if(!customerRealaccountNoFlag){
			return;
		}
		
		//로딩 호출
	    gfn_setLoading(true, "정보제공동의 확인 중 입니다.");
	    gfn_setLoading(false);
		
		//1. 현재 정보가 갱신된 상태인지 확인한다. -> account정보, 약관정보 셋팅
		//현재 선택한 정보를 기준으로 저장 json객체를 생성한다.
		g_saveAccountList = new Array();         //account 정보
		g_savePubCompanyList = new Array();      //핀테크 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
		g_saveSubCompanyList = new Array();      //금투사 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
		

	    var customerRealaccountNoList = new Array();
	    var termsProfileList = new Array();
	    var termsPubcompanyProfileList = new Array();
	    
		$.each($("input[name=customerRealaccountNo]"), function(){
		    var key = $(this).val();  //key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
		    
		    if($(this).is(":checked")){
	    		//체크되어 있고, appId값이 있을 경우에만 처리한다.
	    		if(util_chkReturn($.trim($(this).val()), "s") != ""){
	    		    var appId = "";
	    		    var apiId = "";
	    		    var customerRealaccountNo = "";
	    		    var data = new Object();

	    			var tmpData = new Object();
	    			var tmpProData = new Object();
	    			var tmpSubData = new Object();
	    			
	    		    var keyArr = key.split("_");

	    			
	    		    if(keyArr != null){
	    		    	if("I" == keyArr[7]){
			    			tmpData.appId = keyArr[0];
			    			tmpData.apiId = keyArr[1];
			    			tmpData.customerRealaccountNo = keyArr[2];
			    			tmpData.serviceRegNo = keyArr[3];
	
			    		    if(util_chkReturn(keyArr[3], "s") != ""){
			    		    	serviceRegNo = keyArr[3];
			    		    }
			    		    
			    			tmpData.accountRegNo = keyArr[4];
			    			tmpProData.subcompanyCodeId = keyArr[5];
			    			tmpSubData.subcompanyCodeId = keyArr[5];
			    			tmpProData.pubcompanyCodeId = keyArr[6];
			    			tmpSubData.pubcompanyCodeId = keyArr[6];
			    			tmpData.actionType = keyArr[7];
	
			    			tmpData.useYn = "Y";
			    			tmpData.actionType = "I";
			    			tmpProData.useYn = "Y";
			    			tmpProData.actionType = "I";
	
			    			tmpSubData.actionType = "I";
			    			
			    			customerRealaccountNoList.push(tmpData);
			    	        termsProfileList.push(tmpProData);
			    		    termsPubcompanyProfileList.push(tmpSubData);
			    			
	    		    	}
	    		    }else{
	    		    	//로딩 호출
	    		        gfn_setLoading(false);
	    		    	
	    		    	alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
	    		    	fn_search();
	    		    	return false;
	    		    }
	    		    
	    		}
	    	}
	    });
		
		var param = new Object();
	    param.sptCustomerServiceAccountProfileVO = customerRealaccountNoList;				//일반회원 서비스 계좌 프로파일
	    param.sptCustomerServiceTermsProfileVO = termsProfileList;                          //일반회원서비스 약관 프로파일 저장용
	    param.sptCustomerServiceTermsPubcompanyProfileVO = termsPubcompanyProfileList;      //일반회원서비스 약관 금투사 프로파일 저장용
	    param.customerRegNo = customerRegNo;
	    //param.serviceRegNo = serviceRegNo;
	    
		
	    //저장 시작
	    var moveUrl = "<c:url value='/usr/svcAppl/saveFintechSvcTermsDev.ajax'/>";
		
	    var callBackFunc = "fn_accountSvcSaveCallBack";
	    //param 셋팅
	    //로딩 호출
	    gfn_setLoading(true, "저장 중 입니다.");
	    
	    <%-- 공통 ajax 호출 --%>
	    util_ajaxPageJson(moveUrl, param, callBackFunc);
	}
	
	function fn_accountSvcSaveCallBack(data){
		//로딩 호출
	    gfn_setLoading(false);
	    
		
	    if(data.rsCd == "00"){
	    	alert("저장되었습니다.");
	    	fn_moveNext();
	    }else{
	        alert("<spring:message code='fail.alert.regist' />");
	    }
	}

	<%-- 다음단계 --%>
	function fn_moveNext(){
		fn_popupClose();
	}
<%-- ID 확인 버튼 클릭 --%>
function fn_IdReInput() {

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

    // 앱리스트 초기화
    $("#appList option").each(function() {
		$("#appList option:eq(0)").remove();
	});
	$("#appList").append("<option value=''>선택</option>");
	
	// 가상계좌 리스트 초기화
	$("#accNoList").remove();
	
    return;
	
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose() {
	if (opener) {
		window.close();
	} else {
		parent.fn_search(1);
		gfn_closeModal(this.event);
	}
}
</script>

</head>

<body>
	<form:form commandName="NewMbrRegVO" name="NewMbrRegVO" method="post">
		<input type="hidden" name="customerIdCheck" id="customerIdCheck" value="N" /> 
		<div class="wrap">
			<!-- layer_popup -->
			<div class="layer_popup_dev">
				<!-- #layer01 -->
				<div class="layer_box" id="layer01" style="width: 620px;">
					<div class="layer_tit">서비스 연결 추가</div>
	
					<div class="layer_con">
	
						<p class="info_right">
							<span class="icon_basic">*</span> 필수 입력사항
						</p>
	
						<div class="tb_write1">
							<table>
								<caption>서비스 연결 추가 정보 입력</caption>
								<colgroup>
									<col style="width: 30%;">
									<col style="width: *;">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="chk1">아이디</label></th>
										<td class="txt_l"><input type="text" class="w100" id="customerId" name="customerId" value="">&nbsp;											
											<span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckId">확인</a></span>
											<span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display:none;" >재입력</a></span>
										</td>
									</tr>
									<tr>
										<th scope="row">이름</th>
										<td class="txt_l"> <em id="custmerNameKor"></em></td>
									</tr>
									<tr>
										<th scope="row"><label for="chk3">앱 이름</label></th>
										<td class="txt_l">
											<select id="appList" name="appList" style="min-width: 100px;">
													<option>선택</option>
											</select>
											<input type="hidden" id="searchAppId" name="searchAppId" />
										</td>
									</tr>
									<tr>
										<th scope="row">연결 가능한<br>가상계좌<span
											class="icon_basic">*필수입력</span></th>
										<td class="txt_l scroll" id="accNoTd" name="accNoTd" style="height:80px;">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_type3">
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
						</div>
						<!-- // btn_type3 -->
					</div>
					<a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>