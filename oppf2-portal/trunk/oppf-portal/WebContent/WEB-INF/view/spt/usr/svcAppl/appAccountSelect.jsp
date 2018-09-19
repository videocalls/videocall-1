<%--
  Created by IntelliJ IDEA.
  User: loyalten
  Date: 2017-06-15
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
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
    
        var customerRegNo = ''; //회원OP등록번호
        var customerId    = 'hjkl1234'; //사용자ID
        var customerCi    = '123456';   //사용자CI
        /* [전문결과]변수 */
        var resAccList = new Array(); //가상계좌발급결과배열
        var resRealAccNo  = ''; //response실계좌번호
        var resVtAccNo    = ''; //response가상계좌번호
        var resVtAccAlias = ''; //response별칭

        /*[OauthToken]변수*/
        var reqCnt = 0; //400에러(oauthToken기간만료로 인한 에러)일 경우 요청카운트 : 400에러 무한루프 막기카운트
        var accessToken = '${rsComOauthTokenVO.accessToken}';
        var Authorization = '';
        var apiKey = '';
        var clickId = ""; //OauthToken 취득후 이벤트 클릭될 버튼 ID

        var createAppAccountList = new Array();

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
        }

        /* 리로드 함수 */
        function fn_reloadPage(){
            var objParam = new Object();
            objParam.appId = $("#appId").val();
            $("#AppVO").attr({
                data : objParam,
                action : '/usr/svcAppl/appAccountSelect.do',
                method : 'post'
            }).submit();
        }
        <%-- 로그인 처리 공통 함수 --%>

        function fn_commonTermsCheck(companyProfileRegNo, companyCodeId, type) {
            var url = "<c:url value='/usr/svcAppl/checkedCommonTerms.ajax'/>";
            var param = new Object();
            param.companyCodeId = companyCodeId;
            param.companyProfileRegNo = companyProfileRegNo;
            param.type = type;
            var callBackFunc = "fn_commonTermsCheckCallBack";
            //로딩 호출
            gfn_setLoading(true);
            <%-- 공통 ajax 호출 --%>
            util_ajaxPage(url, param, callBackFunc);
        }

        function fn_commonTermsCheckCallBack(data) {
            //로딩 호출
            gfn_setLoading(false);
            if(data.result < 1) {
                fn_commonTermsPopup();
            } else {
                if(data.paramVO.type === "1") {
                    fn_makeAccount(data.paramVO.companyProfileRegNo, data.paramVO.companyCodeId);
                } else {
                    fn_makeAccount2(data.paramVO.companyProfileRegNo, data.paramVO.companyCodeId);
                }
            }
        }

        function fn_commonTermsPopup() {
            var url = "<c:url value='/usr/svcAppl/commonTermsPopup.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '900';
            objOption.height = '700';

            var objParam = new Object();
            objParam.callBakFunc = "fn_commonTermsCallBack";

            util_modalPage(url, objOption, objParam);
        }

        function fn_commonTermsCallBack(rsCd,rsMsg) {
            if(rsCd == '00'){
                alert("<spring:message code='success.alert.process' />");
            } else {
                if(util_chkReturn(rsMsg, "s") != ""){
                    alert(rsMsg+'\n['+rsCd+']');
                }
            }
        }

        /* 금융주자사 이름 클릭 시 호출 */
        function fn_makeAccount(companyProfileRegNo,companyCodeId){

            var url = "<c:url value='/usr/svcAppl/registAsumAcntIsuPopup.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '601';
            objOption.height = '600';

            var objParam = new Object();
            objParam.companyProfileRegNo = companyProfileRegNo;
            objParam.companyCodeId = companyCodeId;

            util_modalPage(url, objOption, objParam);
        }

        /* 금융주자사 이름 클릭 시 호출 */
        function fn_makeAccount2(companyProfileRegNo,companyCodeId){
            var url = "<c:url value='/usr/svcAppl/registAsumAcntIsuPopup2.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '601';
            objOption.height = '520';

            var objParam = new Object();
            objParam.companyProfileRegNo = companyProfileRegNo;
            objParam.companyCodeId = companyCodeId;

            util_modalPage(url, objOption, objParam);
        }
        function fn_login(){
            var url = "<c:url value='/usr/svcAppl/appList.do'/>";
            var param = new Object();
            param.paramMenuId = "06002";
            gfn_loginNeedMove(url, param);
        }
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
                fn_login();
                return;
            </c:if>

            $("#allChecked").on("click", function() {
                if($(this).is(":checked")) {
                    $.each($("input[name=accountChecked]"), function(index, item) {
                        if(!$(item).is(":disabled")) {
                            $(item).prop("checked", "checked");
                        }
                    });
                } else {
                    $("input[name=accountChecked]").removeProp("checked", "checked");
                }
            });

            //변수셋팅함수 호출
            fn_setVariable();
        });

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
                    if(util_chkReturn(clickId, "s") != ""){
                        $("#"+clickId).click();
                    }

                }else{
                    if(util_chkReturn(data.rsMsg, "s") != ""){
                        alert(data.rsMsg);
                    }
                }

            }else{
                alert('처리과정중에 오류가 발생하였습니다.');
            }
        }

        /* [가상계좌:수정]버튼 클릭 시 호출되는 함수 */
        function fn_modifyVtAccAlias(paramNo){

            if(util_chkReturn(accessToken, "s") == ""){
                //oauthToken요청 호출
                fn_ajaxCallGetOAuthToken();
                return false;
            }

            var companyNameEngAlias = $("#companyNameEngAlias_"+paramNo).val();                       //기업이름Alias영문
            var companyCodeId       = $("#companyCodeId_"+paramNo).val();                             //기업코드
            var realAccNo           = $("#customerRealAccountNoEncryption_"+paramNo).val();                     //실계좌번호
            var realAccType         = $("#customerRealaccountTypeNmEng_"+paramNo).val();              //실계좌형식
            var vtAccNo             = $("#customerVtaccountNo_"+paramNo).val();                       //가상계좌번호
            var vtAccAlias          = $("#customerVtaccountAlias_"+paramNo).val().replace(/\s/g, ""); //가상계좌별칭

            //유효성검증
            //[별칭]
            if(util_chkReturn(vtAccAlias, "s") == ""){
                alert("<spring:message code='errors.required' arguments='별칭'/>");
                $("#customerVtaccountAlias_"+paramNo).focus();
                return false;
            }else{
                if(!chkSpecialCharaters(vtAccAlias, true)){
                    alert("별칭에 특수문자는 입력 할 수 없습니다.");
                    $("#customerVtaccountAlias_"+paramNo).focus();
                    return false;
                }
            }

            var msgConfirm = "해당 가상계좌의 별칭을 "+"<spring:message code='confirm.modify.msg'/>";
            if(!confirm(msgConfirm)){
                return false;
            }

            //oauthToken취득후 이벤트 클릭 될 버튼ID
            clickId = "btnModify_"+paramNo;

            //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
            var ajaxCallFlag = fn_ajaxCallvtAccount(
                    'REP'
                    ,companyCodeId
                    ,realAccNo
                    ,realAccType
                    ,vtAccNo
                    ,vtAccAlias
                    ,companyNameEngAlias
            );

            if(!ajaxCallFlag){ //전문결과가 false인 경우
                return false;
            }else{
                var msgAlert = "<spring:message code='success.alert.modify'/>";
                alert(msgAlert);
                var objParam = new Object();
                objParam.appId = '<c:out value='${paramVO.appId}'/>';
                util_movePage("<c:url value='/usr/svcAppl/appAccountSelect.do'/>", objParam);
            }
        }

        /* [가상계좌:삭제]버튼 클릭 시 호출 */
        function fn_deleteVtAcc(paramNo){
            var companyNameEngAlias = $("#companyNameEngAlias_"+paramNo).val();          //기업이름Alias영문
            var companyCodeId       = $("#companyCodeId_"+paramNo).val();                //기업코드
            var realAccNo           = $("#customerRealAccountNoEncryption_"+paramNo).val();        //실계좌번호
            var realAccType         = $("#customerRealaccountTypeNmEng_"+paramNo).val(); //실계좌형식
            var vtAccNo             = $("#customerVtaccountNo_"+paramNo).val();          //가상계좌번호
            var vtAccAlias          = $("#customerVtaccountAlias_"+paramNo).val().replace(/\s/g, ""); //가상계좌별칭

            //var msgConfirm = "해당 가상계좌를 "+"<spring:message code='confirm.delete.msg'/>";
            var msgConfirm = "해당 가상계좌를 삭제하시면,\n";
            msgConfirm += "핀테크 서비스, 금융거래 정보제공 동의도\n함께 삭제 또는 폐기 될 수 있습니다.\n\n";
            msgConfirm += "삭제 하시겠습니까?";
            if(!confirm(msgConfirm)){
                return false;
            }

            //oauthToken취득후 이벤트 클릭 될 버튼ID
            clickId = "btnDelete_"+paramNo;

            //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
            var ajaxCallFlag = fn_ajaxCallvtAccount(
                    'DIS'
                    ,companyCodeId
                    ,realAccNo
                    ,realAccType
                    ,vtAccNo
                    ,vtAccAlias
                    ,companyNameEngAlias
            );

            if(!ajaxCallFlag){ //전문결과가 false인 경우
                return false;
            }else{
                var msgAlert = "<spring:message code='success.alert.delete'/>";
                alert(msgAlert);
                $("#trCapl_"+paramNo).remove(); //처리후 row 삭제
                var objParam = new Object();
                objParam.appId = '<c:out value='${paramVO.appId}'/>';
                util_movePage("<c:url value='/usr/svcAppl/appAccountSelect.do'/>", objParam);
            }
        }
        /* 가상계좌[발급,교체,폐기]전문요청 함수 */
        function fn_ajaxCallvtAccount(trCode, companyCode, realAccNo, realAccType, vtAccNo, vtAccAlias, pCompanyNameEngAlias){

            var returnFlag = false;

            var accInfo = new Object();
            accInfo.realAccNo   = realAccNo;
            accInfo.realAccType = realAccType;

            if(trCode != 'REQ'){ //가상계좌발급인 경우
                accInfo.vtAccNo = vtAccNo;
            }

            accInfo.vtAccAlias  = vtAccAlias;

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
            objParam.userDn          = 'aaa';

            gfn_setLoading(true, "처리중 입니다."); //로딩 호출

            var url = "<c:url value='/cmm/sif/procAccInfo.ajax'/>";
            $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정

            $.ajax({
//         headers : {
//             'x-credential-userId' : customerId
//            ,'x-api-requestId'     : customerRegNo
//            ,'x-credential-ci'     : customerCi
//            ,'x-credential-dn'     : ''
//            ,'x-credential-dn'     : customerDn
//         }
                type    : "POST" //가상계좌생성
                ,contentType: "application/json"
                ,async   : false
                ,url     : url
                ,data    : JSON.stringify(objParam)
                ,success : function(receiveData){
                    gfn_setLoading(false); //로딩 호출

                    var data = JSON.parse(receiveData.string);

                    if(receiveData.statusCode == '500'){
                        var message = "";
                        var code = "";

                        var msgText = "";

                        try{
                            message = data.message;
                            code = data.code;

                            msgText = "관리자에게 문의하세요.\n["+message+"("+receiveData.statusCode+ " " +code+")]";
                        }catch(e){
                            msgText = "관리자에게 문의하세요.\n["+receiveData.statusCode+"]";
                        }

                        alert(msgText);

                    }else{
                        if(receiveData.statusCode == '200'){
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
                                             if(util_chkReturn(resRealAccNo, "s") != "" && util_chkReturn(resVtAccNo, "s") != "" && util_chkReturn(resVtAccAlias, "s") != ""){
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
                            //Access token does not exist -> access token 발급 상황 문제 발생
                            //Access token has expired -> access token 갱신일 문제
                            if(data.message.indexOf('Access token does not exist') >= 0 || data.message.indexOf('Access token has expired') >= 0){
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
                }
                ,error   : function(data){
                    gfn_setLoading(false); //로딩 호출

                    alert("관리자에게 문의하세요.\n["+receiveData.statusCode+"]");

                }
            });

            return returnFlag;
        }
        function fn_createApp() {
            var checkedFlag = false;
            var checkedPubCompanyCodeId = new Array();
            $.each($("input[name=accountChecked]"), function(index, item){
                if($(item).is(":checked")){
                    checkedFlag = true;
                    checkedPubCompanyCodeId.push($("#companyCodeId_"+index).val());
                }
            });
            if(!checkedFlag) {
                alert("앱에 연결할 계좌를 선택해주세요.");
                return false;
            } else {
                fn_checkedAppTerms(checkedPubCompanyCodeId);
            }
        }
        function fn_checkedAppTerms(checkedPubCompanyCodeId) {
            var url = "<c:url value='/usr/svcAppl/checkedAppTerms.ajax'/>";
            var param = new Object();
            param.checkedPubCompanyList = checkedPubCompanyCodeId;
            param.appId = $("#appId").val();
            param.subCompanyCodeId = $("#companyCodeId").val();
            var callBackFunc = "fn_appTermsCheckedCallBack";
            //로딩 호출
            gfn_setLoading(true);
            <%-- 공통 ajax 호출 --%>
            util_ajaxPageJson(url, param, callBackFunc);
        }
        function fn_appTermsCheckedCallBack(data) {
            //로딩 호출
            gfn_setLoading(false);
            if(data.termsRegNo !== "") {
                // 등록처리
                var objParam = new Object();
                objParam.termsCreatedYn = "Y";
                objParam.termsRegNo = data.termsRegNo;
                fn_appCreate(objParam);
            } else {
                fn_appTermsPopup(data.paramVO.checkedPubCompanyList);
                // 나 동의서 팝업
            }
        }
        function fn_appCreate(objParam) {
            fn_appAccountListSet();
            var url = "<c:url value='/usr/svcAppl/createApp.ajax'/>";
            var param = new Object();
            param.appId = $("#appId").val();
            param.subCompanyCodeId = $("#companyCodeId").val();
            param.appAccountList = createAppAccountList;
            param.serviceRegNo = $("#serviceRegNo").val();
            param.termsCreatedYn = objParam.termsCreatedYn;
            param.termsRegNo = objParam.termsRegNo;
            param.termsAuthType = objParam.termsAuthType;

            if(objParam.termsAuthType === "G032001") {
                param.customerSignDn = objParam.customerSignDn;
                param.customerSignData = objParam.customerSignData;
                param.reqHtml = objParam.reqHtml;
            } else {
                param.arsResultCd = objParam.arsResultCd;
                param.arsResultMessage = objParam.arsResultMessage;
                param.arsTrCd = objParam.arsTrCd;
                param.arsTxtNo = objParam.arsTxtNo;
                param.arsRecordData = objParam.arsRecordData;
            }

            var callBackFunc = "fn_appCreateCallBack";
            //로딩 호출
            gfn_setLoading(true, '처리 중입니다.');
            <%-- 공통 ajax 호출 --%>
            util_ajaxPageJson(url, param, callBackFunc);
        }
        function fn_appCreateCallBack(data) {
            //로딩 호출
            gfn_setLoading(false);
            <%--alert("<spring:message code='success.alert.process' />");--%>
            if(data.rsCd == '00'){
                fn_moveAppCreateResult();
             } else {
                 if(util_chkReturn(data.rsCdMsg, "s") != ""){
                    alert(data.rsCdMsg+'\n['+data.rsCd+']');
                 }
             }
        }
        function fn_appTermsPopup(checkedPubCompanyCodeId) {
            var checkedPubCompany = "";
            for(var i=0; i<checkedPubCompanyCodeId.length; i++) {
                if(checkedPubCompany === "") {
                    checkedPubCompany = checkedPubCompanyCodeId[i];
                } else {
                    checkedPubCompany += "," + checkedPubCompanyCodeId[i];
                }
            }
            var url = "<c:url value='/usr/svcAppl/appTermsPopup.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '900';
            objOption.height = '700';

            var objParam = new Object();
            objParam.callBakFunc = "fn_appTermsPopupCallBack";
            objParam.checkedPubCompany = gfnBase64.encode(checkedPubCompany);
            objParam.appId = gfnBase64.encode($("#appId").val());
            objParam.subCompanyCodeId = gfnBase64.encode($("#companyCodeId").val());
            objParam.appAccountList = createAppAccountList;

            util_modalPage(url, objOption, objParam);
        }
        function fn_appTermsPopupCallBack(objParam) {
            fn_appCreate(objParam);
            /*if(rsCd == '00'){
                alert("<spring:message code='success.alert.process' />");
                fn_moveAppCreateResult();
            } else {
                if(util_chkReturn(rsMsg, "s") != ""){
                    alert(rsMsg+'\n['+rsCd+']');
                }
            }*/
        }
        function fn_appAccountListSet() {
            createAppAccountList = new Array();
            $.each($("input[name=accountChecked]"), function(index, item){
                var createAppAccount = {
                    appId : "",
                    apiId : "",
                    customerRealAccountNo : "",
                    accountRegNo : "",
                    checked : null,
                    pubCompanyCodeId : "",
                    pubCompanyName : ""
                };
                if(!$(item).is(":disabled")){
                    createAppAccount.appId = $("#appId_"+index).val();
                    createAppAccount.apiId = $("#apiId_"+index).val();
                    createAppAccount.customerRealAccountNo = $("#customerRealaccountNo_"+index).val();
                    createAppAccount.accountRegNo = $("#accountRegNo_"+index).val();
                    createAppAccount.checked = $(item).is(":checked");
                    createAppAccount.pubCompanyCodeId = $("#companyCodeId_"+index).val();
                    createAppAccount.pubCompanyName = $("#companyName_"+index).val();
                    createAppAccountList.push(createAppAccount);
                }
            });
        }
        function fn_moveAppCreateResult() {
            var param = new Object();
            param.appId = $("#appId").val();
            util_movePage("<c:url value='/usr/svcAppl/appCreateResult.do'/>", param);
        }
        function fn_AppDetail() {
            var param = new Object();
            param.appId = $("#appId").val();
            util_movePage("<c:url value='/usr/svcAppl/appDetail.do'/>", param);
        }
    </script>
</head>
<body>
<form:form commandName="AppVO" name="AppVO" id="AppVO" method="post">
<input type="hidden" name="appId" id="appId" value="${appDetail.appId}" />
<input type="hidden" name="companyCodeId" id="companyCodeId" value="${appDetail.companyCodeId}" />
<input type="hidden" name="serviceRegNo" id="serviceRegNo" value="${appDetail.serviceRegNo}" />
<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <section>

        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 관리</a></li>
                <li class="on">앱 사용 신청</li>
            </ul>
        </div>
        <!-- // location -->

        <!-- container -->
        <div class="container">

            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>앱 사용 신청</h3> <!-- renewal -->
                    <!-- 2016-06-16 수정 -->
                    <div class="sub_visual app_top_area <c:if test='${appDetail.serviceRegNo ne ""}'>apping</c:if>"> <!-- apping 일떄 로고에 아이콘 노출 -->
                        <div class="logo">
                            <img src="/cmm/appImg/<c:out value='${appDetail.appId}'/>.do" alt="">
                        </div>
                        <div class="text">
                            <p><c:out value='${appDetail.appName}'/>  <c:if test='${appDetail.serviceRegNo ne ""}'><span class="icon_ing">사용중</span></c:if><c:if test='${appDetail.serviceRegNo eq ""}'><span class="icon_go">신청</span></c:if></p>
                            <p><c:out value='${appDetail.companyName}'/></p>
                            <p class="ft_gray"><c:out value='${appDetail.appCategoryName}'/></p>
                        </div>
                    </div>
                    <!-- // 2016-06-16 수정 -->
                </div>

                <!-- con -->
                <div class="con">
                    <!-- renewal -->
                    <div class="title_step step1">
                        <p>가상계좌추가</p>
                    </div>
                    <div class="copy_bank_list">
                        <ul>
                            <c:choose>
                                <c:when test="${empty companyProfileList}">
                                    <li><spring:message code="common.nodata.msg"/></li>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="cpList" items="${companyProfileList}" varStatus="status">
                                        <li>
                                            <a href="javascript:void(0);"
                                                    <c:if test="${cpList.issueAccountlist eq 'Y' || cpList.issueAccountlist eq 'X' || empty cpList.issueAccountlist}">
                                                        onclick="javascript:fn_commonTermsCheck('${cpList.companyProfileRegNo}','${cpList.companyCodeId}', '1');"
                                                    </c:if>
                                                    <c:if test="${cpList.issueAccountlist eq 'N'}">
                                                        onclick="javascript:fn_commonTermsCheck('${cpList.companyProfileRegNo}','${cpList.companyCodeId}', '2');"
                                                    </c:if>
                                            >
                                                <c:out value='${cpList.companyNameKorAlias}' />
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                    <div class="title_step step2">
                        <p>연결계좌선택</p>
                    </div>
                    <div class="copy_account_list">
                        <!-- tbtype_list2 -->
                        <table class="tbtype_list2">
                            <caption>금융투자회사, 유형, 가상계좌번호, 별칭, 관리</caption>
                            <colgroup>
                                <col style="width:5%;">
                                <col style="width:20%;">
                                <col style="width:11%;">
                                <col style="width:23%;">
                                <col style="">
                                <col style="width:17%;">
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col"><input type="checkbox" name="allChecked" id="allChecked"></th>
                                <th scope="col">금융투자회사</th>
                                <th scope="col">유형</th>
                                <th scope="col">가상계좌번호</th>
                                <th scope="col">별칭*</th>
                                <th scope="col">관리</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${appAccountList ne null and fn:length(appAccountList) > 0}">
                                <c:forEach var="account" items="${appAccountList}" varStatus="status">
                                    <tr id="trCapl_${status.index}">
                                        <input type="hidden" name="accountRegNo" id="accountRegNo_${status.index}" value="<c:out value='${account.accountRegNo}'/>" />
                                        <input type="hidden" name="appId" id="appId_${status.index}" value="<c:out value='${account.appId}'/>" />
                                        <input type="hidden" name="apiId" id="apiId_${status.index}" value="<c:out value='${account.apiId}'/>" />
                                        <td>
                                            <input type="checkbox" name="accountChecked" id="accountChecked_${status.index}"
                                                   <c:if test="${account.checked eq true}">checked</c:if>
                                                   <c:if test="${account.isAvailable eq false}">disabled</c:if>
                                            >
                                        </td>
                                        <td class="<c:if test="${account.isAvailable eq false}">disabled</c:if>">
                                            ${account.pubCompanyName}
                                            <input type="hidden" name="companyName" id="companyName_${status.index}" value="<c:out value='${account.pubCompanyName}'/>" />
                                            <input type="hidden" name="companyCodeId" id="companyCodeId_${status.index}" value="<c:out value='${account.pubCompanyCodeId}'/>" />
                                            <input type="hidden" name="companyNameEngAlias" id="companyNameEngAlias_${status.index}" value="<c:out value='${account.pubCompanyNameEng}'/>" />
                                        </td>
                                        <td class="<c:if test="${account.isAvailable eq false}">disabled</c:if>">
                                            ${account.customerRealAccountTypeName}
                                            <input type="hidden" name="customerRealaccountTypeNmEng" id="customerRealaccountTypeNmEng_${status.index}" value="<c:out value='${account.customerRealAccountTypeNameEng}'/>" />
                                        </td>
                                        <td class="<c:if test="${account.isAvailable eq false}">disabled</c:if>">
                                            ${account.customerVtaccountNo}
                                            <input type="hidden" name="customerRealAccountNoEncryption" id="customerRealAccountNoEncryption_${status.index}" value="<c:out value='${account.customerRealAccountNoEncryption}'/>" />
                                            <input type="hidden" name="customerRealaccountNo" id="customerRealaccountNo_${status.index}" value="<c:out value='${account.customerRealAccountNo}'/>" />
                                            <input type="hidden" name="customerVtaccountNo" id="customerVtaccountNo_${status.index}" value="<c:out value='${account.customerVtaccountNo}'/>" />
                                        </td>
                                        <td><input type="text" name="customerVtaccountAlias" id="customerVtaccountAlias_${status.index}" title="별칭 입력" value="${account.customerVtaccountAlias}" style="width:140px;"></td>
                                        <td>
                                            <a href="javascript:void(0);" id="btnModify_${status.index}" class="btn_type6" onclick="javascript:fn_modifyVtAccAlias('${status.index}');">수정</a>
                                            <a href="javascript:void(0);" id="btnDelete_${status.index}" class="btn_type4 ml5" onclick="javascript:fn_deleteVtAcc('${status.index}');">삭제</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <!-- // tbtype_list2 -->
                    </div>
                    <div class="btn_area">
                        <a href="javascript:void(0);" onclick="javascript:fn_AppDetail();" class="btn_type2">이전</a>
                        <a href="javascript:void(0);" onclick="javascript:fn_createApp();" class="btn_type1">다음</a>
                    </div>
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->

        </div>
        <!-- // container -->

    </section>

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
</div>
</form:form>
</body>
</html>
