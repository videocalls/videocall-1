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
/* [개인]관련 변수 */
var customerRegNo = ''; //사용자번호
var customerId    = 'hjkl1234'; //사용자ID
var customerCi    = '123456';   //사용자CI
var customerDn    = 'asdf';     //사용자DN

/* [기업]정보관련 변수 */
var companyCodeId = '00030'; //
var companyNameEngAlias = 'SAMSUNG';  //기업이름별칭영문

/* [전문결과]변수 */
var resAccList = new Array(); //가상계좌발급결과배열
var resRealAccNo  = ''; //response실계좌번호
var resVtAccNo    = ''; //response가상계좌번호
var resVtAccAlias = ''; //response별칭

/*[OauthToken]변수*/
var reqCnt = 0; //400에러(oauthToken기간만료로 인한 에러)일 경우 요청카운트 : 400에러 무한루프 막기 카운트
var accessToken = '${rsComOauthTokenVO.accessToken}';
var Authorization = '';
var apiKey = '';

/*변수 셋팅 함수*/
function fn_setVariable(){
    var tmpVar = '';
}




/**
 * 설명       : base64 encode, decode
 * 사용방식     : gfnBase64 
 * 주의       : 
 * 리턴       : 
 */
var gfnBase64 = {
    // private property
    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="

    // public method for encoding
    ,encode : function(input){
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = gfnBase64._utf8_encode(input);
        while(i < input.length){
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if(isNaN(chr2)){
                enc3 = enc4 = 64;
            }else if(isNaN(chr3)){
                enc4 = 64;
            }
            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    ,decode : function(input){
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while(i < input.length){
            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if(enc3 != 64){
                output = output + String.fromCharCode(chr2);
            }
            if(enc4 != 64){
                output = output + String.fromCharCode(chr3);
            }
        }
        output = gfnBase64._utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    ,_utf8_encode : function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for(var n = 0; n < string.length; n++){
            var c = string.charCodeAt(n);
            if(c < 128){
                utftext += String.fromCharCode(c);
            }else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }else{
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    }

    // private method for UTF-8 decoding
    ,_utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while( i < utftext.length ){
            c = utftext.charCodeAt(i);
            if(c < 128){
                string += String.fromCharCode(c);
                i++;
            }else if((c > 191) && (c < 224)){
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }else{
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
       return string;
    }

    ,URLEncode : function (string){
        return escape(this._utf8_encode(string));
    }

    // public method for url decoding
    ,URLDecode : function (string){
        return this._utf8_decode(unescape(string));
    }
}
</script>

<script type="text/javascript">
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
		

	    //목록
	    $("#btnList").click(function(){
	    	fn_list();
	    });
	    
	    //아이디 확인
	    $("#btnCheckId").click(function(){
	    	fn_checkId();
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
	    
		$("input[name=trCodeList]").change(function(){

			var id = $(this).attr("id");
			
	        if(id == "REQ"){
	    		$("#trCode").val(id);
	    		$("#vtAccNo").val("");
	    		$("#vtAccNo").attr("readonly",true);
				$("#vtAccNo").css("background-color","silver");
	        }else if(id == "KOS"){
	    		$("#trCode").val(id);
	    		$("#vtAccNo").val("");
	    		$("#vtAccNo").attr("readonly",true);
				$("#vtAccNo").css("background-color","silver");
	        }else if(id == "ISS"){
	    		$("#trCode").val(id);
	    		$("#vtAccNo").val("");
	    		$("#vtAccNo").attr("readonly",false);
	    		$("#vtAccNo").css("background-color","#ffffff");
	        }else{
	    		$("#trCode").val(id);
	    		$("#vtAccNo").val("");
	    		$("#vtAccNo").attr("readonly",false);
				$("#vtAccNo").css("background-color","silver");
	        }
	        
	        
	        
	    });

		 //서비스제공자 변경 시
	    $('#companyCodeSel').change(function(){
	        if($(this).val()==""){
	            $('#companyCodeId').val("");
	        } else {

	            $('#companyCodeId').val($(this).val());
	        }
	        
	        <c:choose>
	        <c:when test="${empty companyProfileList}" >
	            msg = "서비스 제공자가 없습니다.";
	        </c:when>
	        <c:otherwise>
	            <c:forEach var="companyProfileList" items="${companyProfileList}" varStatus="status">
	                param = {
	                		codeId : "<c:out value='${companyProfileList.companyCodeId}'/>",
	                		cmpNameEng : "<c:out value='${companyProfileList.companyNameEngAlias}'/>",
	                };

			        if($(this).val() == param.codeId){
			        	companyNameEngAlias = param.cmpNameEng;
			        	return;
			        }
	                
	            </c:forEach>
	        </c:otherwise>
	    </c:choose>
	    });
		 
		 //유형 변경 시
	    $('#realAccTypeSel').change(function(){
	        if($(this).val()==""){
	            $('#realAccType').val("");
	        }else {

	            $('#realAccType').val($(this).val());
	        }
	    });

		
	

	    /* [가상계좌발급]버튼 이벤트발생 시 호출 */
	    $("#btnRegAccNo").bind("click", function(e){
	    	
	        var companyCodeId = $("#companyCodeSel option:selected").val();
	        var realAccType = $("#realAccTypeSel option:selected").val();
	        var realAccNo   = $("#realAccNo").val();
	        var trCode = $("#trCode").val();
	        var vtAccNo = $("#vtAccNo").val();
	        var vtAccAlias = $("#vtAccAlias").val();

	        var customerIdCheck = $("#customerIdCheck").val();
	        if(util_chkReturn(customerIdCheck, "s") == "N"){
	            alert("<spring:message code='errors.required.select' arguments='아이디'/>");
	            $("#customerId").focus();
	            return false;
	        }
	        
	        //유효성검증:[서비스제공자]셀렉트버튼
	        if(util_chkReturn(companyCodeId, "s") == ""){
	            alert("<spring:message code='errors.required.select' arguments='서비스제공자'/>");
	            $("#companyCodeSel").focus();
	            return false;
	        }

	        //유효성검증:[유형]셀렉트버튼
	        if(util_chkReturn(realAccType, "s") == ""){
	            alert("<spring:message code='errors.required.select' arguments='유형'/>");
	            $("#realAccTypeSel").focus();
	            return false;
	        }
	        

			 var chk = document.NewMbrRegVO.realAccNoCkb.checked;
			 if(chk){
		        //유효성검증:[실계좌번호]셀렉트버튼
		        if(util_chkReturn(realAccNo, "s") == ""){
		            alert("<spring:message code='errors.required' arguments='실계좌번호'/>");
		            $("#realAccNo").focus();
		            return false;
		        }	
			 }
	    	

			if(trCode == ""){
		        //유효성검증:[가상계좌번호]
		        if(util_chkReturn(vtAccNo, "s") == ""){
		            alert("<spring:message code='errors.required' arguments='가상계좌번호'/>");
		            $("#vtAccNo").focus();
		            return false;
		        }
			}
			
	        //유효성검증:[가상계좌별칭]셀렉트버튼
	        if(util_chkReturn(vtAccAlias, "s") == ""){
	            alert("<spring:message code='errors.required' arguments='가상계좌별칭'/>");
	            $("#vtAccAlias").focus();
	            return false;
	        }
	        
	        //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
	        var ajaxCallFlag = fn_ajaxCallvtAccount(
	        	trCode
	           ,companyCodeId
	           ,realAccNo
	           ,realAccType
	           ,vtAccNo //가상계좌번호
	           ,vtAccAlias
	           ,companyNameEngAlias
	        );
	        
	        if(!ajaxCallFlag){ //전문결과가 false인 경우
//	             alert("<spring:message code='fail.alert.process'/>");
	            return false;
	        }else{
	            alert("<spring:message code='success.alert.process'/>");

	            if(opener){
	            	window.opener.fn_search();
	                window.close();
	            }else{
	            	window.parent.fn_search();
	                gfn_closeModal(this.event);
	            }
	            
	            return false;
	        }

	    });
		
		// 변수설정함수 호출
	    fn_setVariable();
		

		$("#realAccNo").css("background-color","silver");
		$("#vtAccNo").css("background-color","silver");
		
		
	});
	
<%-- 저장 --%>
	function fn_checkId() {
		

	    if(util_chkReturn($('#customerId').val(), "s") == ""){
	        alert("<spring:message code='errors.required' arguments='아이디'/>");
	        $("#customerId").focus();
	        return false;
	    }
		
		//로딩 호출
		gfn_setLoading(true);

		//page setting  
		var url = "<c:url value='/apt/usr/selectMemberInfo.ajax'/>";
		var param = $("#NewMbrRegVO").serialize();
		var callBackFunc = "fn_checkIdCallBack";
		
		<%-- 공통 ajax 호출 --%>
		util_ajaxPage(url, param, callBackFunc);
		
	}
	
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
	        
	        //var companyProfileList = resultVO.companyProfileList;
	        
	        
		} else {
			alert("조회된 아이디가 없습니다.");
			
		}
		return;
	}

	/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
	function fn_popupClose() {
		if (opener) {
			window.close();
		} else {
			gfn_closeModal(this.event);
		}
	}
	
	/* 실계좌번호 직접입력 버튼 클릭시 호출 */
	function fn_accNoToggle() {
		 var chk = document.NewMbrRegVO.realAccNoCkb.checked;
		 if(chk){
			$("#realAccNo").val("");
    		$("#realAccNo").attr("readonly",false);
    		$("#realAccNo").css("background-color","#ffffff");
		 } else {
			$("#realAccNo").val("");
			$("#realAccNo").attr("readonly",true);
			$("#realAccNo").css("background-color","silver");
		 }

		//realAccNo
        
	}
	


	/*******************************************
	 * ajax,ajax Callback 함수
	 *******************************************/

	/* OAuthToken요청 함수 */
	function fn_ajaxCallGetOAuthToken(){
	    //page setting  
	    var url = "<c:url value='/cmm/oauth/getToken.ajax'/>";
	    var objParam = new Object();
	    objParam.customerRegNo = customerRegNo;
	    var callBackFunc = "fn_ajaxCallBackGetOAuthToken";
	    
	    //로딩 호출
	    gfn_setLoading(true);
	    
	    <%-- 공통 ajax 호출 --%>
	    util_ajaxPage(url, objParam, callBackFunc);
	}

	/* OAuthToken요청 콜백함수 */
	function fn_ajaxCallBackGetOAuthToken(data){
		gfn_setLoading(false);
	    if(util_chkReturn(data.rsCd, "s") != ""){
	        if(data.rsCd == '00'){
	            accessToken = data.rsData.accessToken;
	            $("#btnRegistVtAcc").click();
	            
	        }else{
	            if(util_chkReturn(data.rsMsg, "s") != ""){
	                 alert(data.rsMsg);
	                return false;
	            }
	        }
	    }else{
	         alert('처리과정중에 오류가 발생하였습니다.');
	        return false;
	    }
	        
	}
	 
	/* 가상계좌[발급,교체,폐기]전문요청 함수 */
	function fn_ajaxCallvtAccount(trCode, companyCode, realAccNo, realAccType, vtAccNo, vtAccAlias, pCompanyNameEngAlias){
	    
	    var returnFlag = false;
	    
	    var accInfo = new Object();
	    accInfo.realAccNo   = gfnBase64.encode(realAccNo);
	    accInfo.realAccType = realAccType;

	    if(trCode != 'REQ'){ //가상계좌발급인 경우
	        accInfo.vtAccNo = vtAccNo;
	    }
	    
	    if(trCode == 'KOS'){ //가상계좌발급인 경우
	    	trCode = "ISS"
	    }
	    
	    accInfo.vtAccAlias = vtAccAlias;
	    
	    var account = new Array();
	    account.push(accInfo);
	    
	    var objParam = new Object();
	    objParam.apiKey        = apiKey;
	    objParam.Authorization = Authorization;
	    objParam.companyNameEngAlias = pCompanyNameEngAlias;
	    objParam.trCode          = trCode;
	    objParam.companyCode     = companyCode;
	    objParam.account         = account;
	    objParam.requestUserId   = customerId;
	    objParam.requestUniqueId = customerRegNo;
	    objParam.userCi          = customerCi;
	    objParam.accessToken     = accessToken;
	    objParam.userDn          = '';
	    
	    gfn_setLoading(true); //로딩 호출
	    
	    var url = "<c:url value='/apt/usr/procAccInfo.ajax'/>";
	    $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정 
	    $.ajax({
	         type    : "POST" //가상계좌생성
	        ,contentType: "application/json"
	        ,async   : false
	        ,url     : url
	        ,data    : JSON.stringify(objParam)
	        ,success : function(receiveData){
	        	gfn_setLoading(false); //로딩 호출
	        	
	            var data = JSON.parse(receiveData.string);
	            if(receiveData.statusCode == '200' || receiveData.statusCode == 'OK'){
	            	if(data.result.status == 'SUCCESS' || data.result.message == '처리성공'){
	            		resAccList = data.account;
	                    if(resAccList == null || resAccList == 'undefined'){
	                    	//실패
	                        alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
	                    }else{
	                        if(resAccList.length > 0){
	                        	if(resAccList[0].status == "FAILURE" || resAccList[0].status == "FAILED"){
	                                //실패
	                                alert("관리자에게 문의하세요.\n["+resAccList[0].message+"]");
	                            }else{
		                            resRealAccNo  = resAccList[0].realAccNo;
		                            resVtAccNo    = resAccList[0].vtAccNo;
		                            resVtAccAlias = resAccList[0].vtAccAlias;
		                            /*
		                            if(util_chkReturn(resVtAccNo, "s") != "" && util_chkReturn(resVtAccNo, "s") != "" && util_chkReturn(resVtAccNo, "s") != ""){
		                                returnFlag = true;
		                            }
		                            */
		                            returnFlag = true;
	                            }
	                        }
	                   }
	                }else{
	                	if(util_chkReturn(data.account, "s") == ""){
	                        alert(data.message+'\n['+data.code+']');
	                    }
	                }
	            }else if(receiveData.statusCode == '400' || receiveData.statusCode == 'BAD_REQUEST'){
	            	//Access Token does not exist -> access token 발급 상황 문제 발생
	                //Access token has expired -> access token 갱신일 문제
	                if(data.error_description.indexOf('Access Token does not exist') >= 0 || data.error_description.indexOf('Access token has expired') >= 0){
		                if(reqCnt >= 3){ //400에러 무한루프 막기 분기문
		                	alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
		                    reqCnt = 0;
		                }else{
		                    fn_ajaxCallGetOAuthToken(); //oauthToken요청 호출
		                    reqCnt++;
		                }
	            	}else{
	            		alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
	            	}
	            }else{
	            	alert("관리자에게 문의하세요.\n["+data.message+"("+data.code+")]");
	            }
	        }
	        ,error   : function(data){
	        	gfn_setLoading(false); //로딩 호출
	        	
	        	alert("관리자에게 문의하세요.\n["+receiveData.statusCode+"]");
	        }
	    });
	    return returnFlag;
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
					<!-- 가로크기 직접제어, 세로는 최대 500px -->
					<div class="layer_tit">계좌 추가</div>

					<div class="layer_con">

						<p class="info_right">
							<span class="icon_basic">*</span> 필수 입력사항
						</p>

						<div class="tb_write1">
							<table>
								<caption>계좌 정보 입력</caption>
								<colgroup>
									<col style="width: 30%;">
									<col style="width: *;">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="chk1">아이디<span
												class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w100" id="customerId" name="customerId">&nbsp;
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckId">확인</a></span>
										<span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display:none;" >재입력</a></span>
										</td>
									</tr>
									<tr>
										<th scope="row">이름</th>
										<td class="txt_l"> <em id="custmerNameKor"></em></td>
									</tr>
									<tr>
										<th scope="row"><label for="chk3">서비스 제공자<span
												class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><select id="companyCodeSel" style="min-width: 100px;">
												<option value="">선택</option>
											<c:forEach var="companyProfileList" items="${companyProfileList}" varStatus="status">
												<option value="${companyProfileList.companyCodeId}">${companyProfileList.companyNameKor}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="companyCodeId" name="companyCodeId" value="" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk4">유형<span
												class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l">
											<select id=realAccTypeSel style="min-width: 100px;">
												<option value="">선택</option>
												<c:forEach var="cmmAccTypeList" items="${cmmAccTypeList}" varStatus="status">
												    <option value="<c:out value='${cmmAccTypeList.code_name_eng}'/>">
												        <c:out value='${cmmAccTypeList.code_name_kor}'/>
												    </option>
												</c:forEach>
											</select>
										<input type="hidden" id="realAccType" name="realAccType" value="" />
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="chk5">실계좌번호<span
												class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w200" id="realAccNo" name="realAccNo" readonly="readonly">
											<ul class="wrap_input type_3 ml10">
												<li><input type="checkbox" name="realAccNoCkb" id="realAccNoCkb" onclick="javascript:fn_accNoToggle();"><label for="c1">직접입력</label></li>
											</ul></td>
									</tr>
									<tr>
										<th scope="row"><label for="info">가상계좌번호<span
												class="icon_basic">*필수입력</span></label></label></th>
										<td class="txt_l">
											<ul class="wrap_input">
												<li><input type="radio" name="trCodeList" id="REQ" checked="checked"><label for="k1">금투사 자체발급</label></li>
												<li><input type="radio" name="trCodeList" id="KOS"><label for="k2">코스콤발급 </label></li>
												<li><input type="radio" name="trCodeList" id="ISS"><label for="k3">직접입력</label></li>
											</ul><input type="text" class="w200" id="vtAccNo" name ="vtAccNo" readonly="readonly">
											<input type="hidden" id="trCode" name ="trCode" value="REQ">
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="info2">별칭<span class="icon_basic">*필수입력</span></label></th>
										<td class="txt_l"><input type="text" class="w200" id="vtAccAlias" name="vtAccAlias"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_type3">
							<span class="btn_gray1"><a href="javascript:void(0);" id="btnRegAccNo">저장</a></span>
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