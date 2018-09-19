<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf"%>
<!doctype html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf"%>
<%-- jqwidgets --%>
<link rel="stylesheet"
	href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>"
	type="text/css" />
<link rel="stylesheet" href="/css/apt/import.css">

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

var g_resultSvcAccountList;     //조회된 account 정보

var g_saveAccountList;          //account 정보 저장 정보
var g_savePubCompanyList;       //핀테크 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
var g_saveSubCompanyList;       //금투사 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
var customerRegNo = "";
var serviceRegNo = "";


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
	

	//다음
	$("#btnave").click(function(){
		fn_save();
    });
	
	customerRegNo = "<c:out value='${resultVO.customerRegNo}'/>";
	
});

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose() {
	if (opener) {
		window.close();
	} else {
		parent.fn_search(1);
		gfn_closeModal(this.event);
	}
}


<%-- 다음 --%>
function fn_save(){
	//가상계좌 선택 여부 체크
	var customerRealaccountNoFlag = false;
	$.each($("input[name=customerRealaccountNo]"), function(){
		if($(this).is(":checked")){
			customerRealaccountNoFlag = true;
		}
	});
	if(!customerRealaccountNoFlag){
		alert("STEP.3의 가상계좌를 선택 해 주세요.");
		return;
	}
	
	//로딩 호출
    //gfn_setLoading(true, "정보제공동의 확인 중 입니다.");
	
	//1. 현재 정보가 갱신된 상태인지 확인한다. -> account정보, 약관정보 셋팅
	//2. 갱신여부를 체크하여 로직처리
	//2-1. 정보갱신이 안되어있으면 다음처리
	//2-2. 갱신상태 일 경우
	//2-2-1. 기존 약관정보를 가져온다.
	//2-2-2. 기존 약관정보와 비교하여, 재약관이 필요한지 여부를 셋팅하여 저장 데이터를 생성한다.
	//2-2-3. 저장정보를 통해 저장 후 2-1.과 동일한 처리를 한다.
		
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
	    			tmpData.appId = keyArr[0];
	    			tmpData.apiId = keyArr[1];
	    			tmpData.customerRealaccountNo = keyArr[2];
	    			tmpData.serviceRegNo = keyArr[3];
	    			tmpData.accountRegNo = keyArr[4];
	    			tmpData.actionType = keyArr[7];
	    			//alert(tmpData.actionType);
	    			//alert(keyArr[7]);
	    			
	    		    if(util_chkReturn(keyArr[3], "s") != ""){
	    		    	serviceRegNo = keyArr[3];
	    		    }
	    		    
	    			tmpProData.subcompanyCodeId = keyArr[5];
	    			tmpSubData.subcompanyCodeId = keyArr[5];
	    			tmpProData.pubcompanyCodeId = keyArr[6];
	    			tmpSubData.pubcompanyCodeId = keyArr[6];

	    			tmpData.useYn = "Y";
	    			//tmpData.actionType = "I";
	    			tmpProData.useYn = "Y";
	    			tmpProData.actionType = "I";

	    			tmpSubData.actionType = "I";
	    			
	    			customerRealaccountNoList.push(tmpData);
	    	        termsProfileList.push(tmpProData);
	    		    termsPubcompanyProfileList.push(tmpSubData);
	    			
	    			
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
    
    
	
    //저장 시작
    var moveUrl = "<c:url value='/usr/svcAppl/saveFintechSvcTermsDev.ajax'/>";
	
    var callBackFunc = "fn_accountSvcSaveCallBack";
    //param 셋팅
    //로딩 호출
    gfn_setLoading(true, "저장 중 입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
	
}
function fn_accountSvcSaveCallBack(data){//로딩 호출
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


</script>


</head>

<body>
	<div class="wrap">
		<!-- layer_popup -->
		<div class="layer_popup_dev">
			<!-- #layer01 -->
			<div class="layer_box" id="layer01" style="width: 620px;">
				<!-- 가로크기 직접제어, 세로는 최대 500px -->
				<div class="layer_tit">계좌 연결 추가</div>

				<div class="layer_con">

					<p class="info_right">
						<span class="icon_basic">*</span> 필수 입력사항
					</p>

					<div class="tb_write1">
						<table>
							<caption>계좌 연결 추가 정보 입력</caption>
							<colgroup>
								<col style="width: 30%;">
								<col style="width: *;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">서비스등록 번호</th>
									<td class="txt_l"> <em id="custmerId">${serviceRegNo}</em></td>
								</tr>
								<tr>
									<th scope="row">아이디</th>
									<td class="txt_l"> <em id="custmerId">${resultVO.customerId}</td>
								</tr>
								<tr>
									<th scope="row">이름</th>
									<td class="txt_l"> <em id="custmerNameKor">${resultVO.customerNameKor}</em></td>
								</tr>
								<tr>
									<th scope="row">앱 이름</th>
									<td class="txt_l"> <em id="custmerNameKor">${resultSvcAccountList[0].appName}</em></td>
								</tr>
								<tr>
									<th scope="row">연결 가능한<br>가상계좌<span
										class="icon_basic">*필수입력</span></th>
										<td class="txt_l scroll" id="accNoTd" name="accNoTd" style="height:80px;">
										<ul class="ml10">
		                                    <c:forEach var="resultSvcAccountList" items="${resultSvcAccountList}" varStatus="status">
		                                        <li>
		                                        	<c:choose>
														<c:when test="${resultSvcAccountList.useYn eq 'Y'}">
		                                        			<input type="checkbox" name="customerRealaccountNo" id="${resultSvcAccountList.appId}_${resultSvcAccountList.apiId}_${resultSvcAccountList.customerRealaccountNo}_${resultSvcAccountList.serviceRegNo}_${resultSvcAccountList.accountRegNo}_${resultSvcAccountList.subcompanyCodeId}_${resultSvcAccountList.pubcompanyCodeId}" value="${resultSvcAccountList.appId}_${resultSvcAccountList.apiId}_${resultSvcAccountList.customerRealaccountNo}_${resultSvcAccountList.serviceRegNo}_${resultSvcAccountList.accountRegNo}_${resultSvcAccountList.subcompanyCodeId}_${resultSvcAccountList.pubcompanyCodeId}_U" Enalbe="true" checked disabled>
															<label class="chk_box">${resultSvcAccountList.pubcompanyName} / ${resultSvcAccountList.customerVtaccountNo}</label></li>
														</c:when>
														<c:when test="${resultSvcAccountList.useYn eq 'N'}">
		                                        			<input type="checkbox" name="customerRealaccountNo" id="${resultSvcAccountList.appId}_${resultSvcAccountList.apiId}_${resultSvcAccountList.customerRealaccountNo}_${resultSvcAccountList.serviceRegNo}_${resultSvcAccountList.accountRegNo}_${resultSvcAccountList.subcompanyCodeId}_${resultSvcAccountList.pubcompanyCodeId}" value="${resultSvcAccountList.appId}_${resultSvcAccountList.apiId}_${resultSvcAccountList.customerRealaccountNo}_${resultSvcAccountList.serviceRegNo}_${resultSvcAccountList.accountRegNo}_${resultSvcAccountList.subcompanyCodeId}_${resultSvcAccountList.pubcompanyCodeId}_I" Enalbe="false">
															<label class="chk_box">${resultSvcAccountList.pubcompanyName} / ${resultSvcAccountList.customerVtaccountNo}</label></li>
															

														</c:when>
													</c:choose> 
		                                    </c:forEach>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn_type3">
						<span class="btn_gray1"><a href="javascript:void(0);"  id="btnave">저장</a></span>
					</div>
				</div>
					<a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
			</div>
		</div>
	</div>
</body>
</html>