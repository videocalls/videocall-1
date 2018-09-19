<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : asumAcntIsu.jsp
 * @Description : [핀테크서비스신청:가상계좌발급]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.16  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.16
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript" src="/js/spt/common_pub.js"></script>
<script type="text/javascript">
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
    
    tmpVar = '${customerRegNo}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerRegNo = tmpVar;
    }
    
    tmpVar = '${customerId}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerId = tmpVar;
    }
    
    tmpVar = '${customerCi}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerCi = tmpVar;
    }
    
    tmpVar = '${customerDn}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerDn = tmpVar;
    }
    
    tmpVar = '${rsComCompanyProfileVO.companyNameEngAlias}';
    if(util_chkReturn(tmpVar, "s") != ""){
        companyNameEngAlias = tmpVar;
    }
    
    tmpVar = '${rsComCompanyProfileVO.companyCodeId}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        companyCodeId = tmpVar;
    }
    
}



/*******************************************
 * 기능 함수
 *******************************************/



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
    
    var url = "<c:url value='/cmm/sif/procAccInfo.ajax'/>";
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



/*******************************************
 * 이벤트 함수
 *******************************************/

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
}

//금투사 기업약관 저장
function fn_saveMemberCompanyTerm() {
	
	var companyCodeId       = $("#companyCodeId").val();
	var companyTermsContentRegSeq       = $("#companyTermsContentRegSeq").val(); 
	
	
	var objParam = new Object();   
	objParam.companyTermsContentRegSeq     = companyTermsContentRegSeq;
	objParam.companyCodeId          = companyCodeId;

	//로딩 호출
	gfn_setLoading(true);

	//page setting  
	var url = "<c:url value='/usr/svcAppl/saveMemberCompanyTermsConsnt.ajax'/>";
	
	var callBackFunc = "fn_saveMemberCompanyTermCallBack";
	
	<%-- 공통 ajax 호출 --%>
	util_ajaxPageJson(url, objParam, callBackFunc);
	
}

function fn_saveMemberCompanyTermCallBack(data) {
	
	//로딩 호출
	gfn_setLoading(false);

	if(data.result > 0){
	     alert("<spring:message code='success.alert.process'/>");
	     if(opener){
	         window.opener.fn_reloadPage();
	     }else{
	         window.parent.fn_reloadPage();
	     }
	     return false;
	} else {
		var msgAlert = "금투사 기업 약관 동의중 문제가 발생하였습니다. \n관리자에게 문의 해 주세요.";
	      alert(msgAlert);
	}

}

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
            gfn_closeModal(this.event);
        }
    </c:if>
    
    /* [가상계좌발급]버튼 이벤트발생 시 호출 */
    $("#btnRegistVtAcc").bind("click", function(e){
    	
        var realAccType = $("#realAccType option:selected").val();
        var realAccNo   = $("#realAccNo").val();
        var vtAccAlias = $("#vtAccAlias").val();
    	
        //유효성검증:[실계좌유형]셀렉트버튼
        if(util_chkReturn(realAccType, "s") == ""){
            alert("<spring:message code='errors.required.select' arguments='실계좌유형'/>");
            $("#realAccType").focus();
            return false;
        }
    	
        //유효성검증:[실계좌번호]셀렉트버튼
        if(util_chkReturn(realAccNo, "s") == ""){
            alert("<spring:message code='errors.required' arguments='실계좌번호'/>");
            $("#realAccNo").focus();
            return false;
        }
        
        //유효성검증:[가상계좌별칭]셀렉트버튼
        if(util_chkReturn(vtAccAlias, "s") == ""){
            alert("<spring:message code='errors.required' arguments='가상계좌별칭'/>");
            $("#vtAccAlias").focus();
            return false;
        }else{
            if(!chkSpecialCharaters(vtAccAlias, true)){
            	 alert("<spring:message code='errors.required' arguments='가상계좌별칭'/>");
                 $("#vtAccAlias").focus();
                return false;
            }
        }

    	<c:if test="${returnCode eq 'Y'}">
        if($("#agree1").is(":checked")){
        	
        }else {
        	//alert("<spring:message code='errors.required.select' arguments='서비스 이용약관 동의'/>");
        	alert("서비스 이용약관에 동의해주세요.");
                $("#agree1").focus();
                return false;
    	}
        </c:if>
        
        //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
        var ajaxCallFlag = fn_ajaxCallvtAccount(
            'REQ'
           ,companyCodeId
           ,realAccNo
           ,realAccType
           ,''//,vtAccNo //가상계좌번호
           ,vtAccAlias
           ,companyNameEngAlias
        );
        
        if(!ajaxCallFlag){ //전문결과가 false인 경우
//             alert("<spring:message code='fail.alert.process'/>");
            return false;
        }else{

        	var returnCode = 'N';
        	<c:if test="${returnCode eq 'Y'}">
        	returnCode = 'Y';
			</c:if>
			if(returnCode == 'Y'){
        	fn_saveMemberCompanyTerm();
			} else {
		        alert("<spring:message code='success.alert.process'/>");
		        if(opener){
		            window.opener.fn_reloadPage();
		        }else{
		            window.parent.fn_reloadPage();
		        }
		        return false;
			}
        }

    });

    //변수설정함수 호출
    fn_setVariable();
    
});
</script>
</head>
<body>
<div class="wrap">

    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    

        <div class="dimm" style="display:none;"></div>

        <!-- #layer01 -->
        <div class="layer_box" id="layer01" style="width:600px;">
            <div class="layer_tit">가상계좌 발급</div>

            <div class="layer_con" style="max-height: 453px;">

				<p class="title_pop">가상계좌 발급</p>

				<p class="title_01">금융투자회사 : <span class="point02"><c:out value='${rsComCompanyProfileVO.companyNameKorAlias}'/></span></p>

				<table class="tbtype_list2 type3">
					<caption>유형, 실계좌번호, 별칭 입력</caption>
					<colgroup>
						<col style="width:25%;">
						<col style="width:45%;">
						<col style="width:30%;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">유형</th>
							<th scope="col">실계좌번호</th>
							<th scope="col">별칭</th>
						</tr>
					</thead>
					<tbody>						
						<tr>
							<td>
								<span class="sel_box1" style="width:100%;">
									<select id="realAccType" title="유형 선택" style="width:100%;">
										<option value="">선택</option>
										<c:forEach var="cmmAccTypeList" items="${cmmAccTypeList}" varStatus="status">
										    <option value="<c:out value='${cmmAccTypeList.code_name_eng}'/>">
										        <c:out value='${cmmAccTypeList.code_name_kor}'/>
										    </option>
										</c:forEach>
									</select>
								</span>							
							</td>
							<td><input type="text" id="realAccNo" onkeydown="util_numberonly(event);" style="width:100%;ime-mode:disabled;" title="실계좌번호 입력하기" maxlength="15"></td>
							<td><input type="text" id="vtAccAlias" style="width:100%;" title="별칭 입력하기"></td>
						</tr>
					</tbody>
				</table>
				
				<ul class="list_style_01">
					<li>가상계좌를 발급 받을 실 계좌번호를 '-' 없이 숫자만 입력하시고 [가상계좌 발급] 버튼을 클릭 하시면 선택된 계좌의 가상계좌가 발급됩니다.</li>
					<li>별칭 입력은 필수 입니다.</li>
				</ul>
				
               	<c:if test="${returnCode eq 'Y'}">
                	<!-- renewal -->
					<p class="title_01 mt20"><span class="point02"><c:out value='${rsComCompanyProfileVO.companyNameKorAlias}'/></span> - 약관동의</p>
					<!-- 약관동의 -->
					<div class="agreement_area virtual_acc"> 
						<!-- 아코디언 메뉴 -->
						<div class="accordian-menu">
							<!-- loop -->
							<div class="accordian-cont cont_wrap">
								<a href="#none" class="btn_bar"></a>
								<div class="detail_head"><input type="checkbox" id="agree1" name="agree1"><label for="agree1">서비스 이용약관에 동의합니다.</label></div>
								<input type="hidden" name ="companyCodeId" id="companyCodeId" value="<c:out value='${sptCustomerCompanyTermsProfileVO.companyCodeId}'/>" />
								<input type="hidden" name ="companyTermsContentRegSeq" id="companyTermsContentRegSeq" value="<c:out value='${sptCustomerCompanyTermsProfileVO.companyTermsContentRegSeq}'/>" />
								<div class="detail_cont">
									<textarea class="scroll_box"><c:out value='${sptCustomerCompanyTermsProfileVO.companyTermsContent}'/></textarea>
								</div>
							</div>
							<!--// loop -->
						</div>
<script type="text/javascript">
// 자기자신만 열고 닫기
var $accordianMenu = $( '.accordian-menu .accordian-cont > .btn_bar' );
   $accordianMenu.on( 'click', function () {
	  //var index = $( this ).index();
   if ( $( this ).parent().hasClass( 'active' )) {
	  $( this ).parent().removeClass( 'active' );
   } else {
	  $( this ).parent().removeClass( 'active' );
	  $( this ).parent().addClass( 'active' );
   }
   return false;
});
</script>
					</div>
				</c:if>
                <div class="btn_area">
                    <a href="javascript:void(0);" id="btnRegistVtAcc" class="btn_type5">가상계좌 발급</a>
                </div> 

            </div>

            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>
    <!-- // layer_popup -->

</div>

</body>
</html>