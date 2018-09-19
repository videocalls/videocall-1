<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : asumAcntIsu.jsp
 * @Description : [마이페이지:가상계좌]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.27  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.27
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

var customerRegNo = ''; //회원OP등록번호
var customerId    = 'hjkl1234'; //사용자ID
var customerCi    = '123456';   //사용자CI
var sptCustAccListTotCnt = 0;
var sptCustAccList;

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

var acctType = '';

var tmpCompanyCodeId = '';
var tmpCompanyProfileRegNo = '';

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



/*******************************************
 * 기능 함수
 *******************************************/

/* [페이징]처리 함수 */
function fn_paging(page){
    
    var pageStart = 0;
    var pageIndex = page;
    
    var pageRowsize = $("#pageRowsize").val();
    
    if(pageIndex == 1){
        pageStart = 0;
    }else{
        pageStart = pageRowsize * ( pageIndex -1 );
    }
    
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    fn_ajaxCall_getList();
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
    
    var url = "<c:url value='/cmm/sif/procAccInfo.ajax'/>";
    $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정    
    gfn_setLoading(true); //로딩 호출
    
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

/* [목록]ajax call 함수 */
function fn_ajaxCall_getList(){
    
    var url = "<c:url value='/myp/asumAcnt/selectAsumAcntList.ajax'/>";
    var reqData = {
       "pageRowsize"   : $("#pageRowsize").val()
      ,"pageBlocksize" : $("#pageBlocksize").val()
      ,"pageIndex"     : $("#pageIndex").val()
      ,"pageStart"     : $("#pageStart").val()
      ,"listOrder"     : $("#listOrder").val()
      ,"customerRegNo" : customerRegNo
//       ,"searchCondition" : $("#sltSearchCondition :selected").val()
//       ,"searchKeyword"   : $("#txtSearchKeyword").val()
//       ,"searchKind"      : $("#searchKind").val()
    };
    
    //로딩 호출    
    gfn_setLoading(true);
    
    util_ajaxPage(url, reqData, "fn_ajaxCallback_getList");
}

/* [목록]ajax callback 함수 */
function fn_ajaxCallback_getList(data){
    var rsListTotCnt = data.resultListTotCnt;
    var rsList = data.resultList;    
    var apData="";
    
    $("#rsList >").remove();
    $("#pagingNavi >").remove();
    if (util_chkReturn(rsListTotCnt, "s") == "" || util_chkReturn(rsList, "s") == ""){
        apData = '<tr>'       
            +        '<td colspan="7">발급된 가상계좌가 없습니다.</td>'
            +    '</tr>';
        $("#rsList").append(apData);
        
        //로딩 호출    
        gfn_setLoading(false);
        
        return false;
    }
    
    /* 페이징 설정 변수 */
    var pageIndex = Number(data.paramVO.pageIndex);
    var pageStart = Number(data.paramVO.pageStart);
    var pageRowsize = Number(data.paramVO.pageRowsize);
    
    $.each(rsList, function(idx,data){        
        var iNo = rsListTotCnt - ( (pageIndex - 1) * pageRowsize + (idx+1) )+1;
        apData += '<tr>'               
                +   '<td>'+iNo+'</td>' //번호
                +   '<td>'+data.companyNameKorAlias //금융투자회사
                +     '<input type="hidden" name="companyCodeId" id="companyCodeId_'+idx+'" value="'+data.companyCodeId+'" />'
                +     '<input type="hidden" name="companyNameEngAlias" id="companyNameEngAlias_'+idx+'" value="'+data.companyNameEngAlias+'" />'
                +   '</td>'
                +   '<td>'+data.customerRealaccountTypeNm //유형
                +     '<input type="hidden" name="customerRealaccountType" id="customerRealaccountType_'+idx+'" value="'+data.customerRealaccountType+'" />'
                +     '<input type="hidden" name="customerRealaccountTypeNm" id="customerRealaccountTypeNm_'+idx+'" value="'+data.customerRealaccountTypeNm+'" />'
                +     '<input type="hidden" name="customerRealaccountTypeNmEng" id="customerRealaccountTypeNmEng_'+idx+'" value="'+data.customerRealaccountTypeNmEng+'" />'
                +   '</td>'
                +   '<td>'+data.customerVtaccountNo //가상계좌번호
                +     '<input type="hidden" name="customerVtaccountNo" id="customerVtaccountNo_'+idx+'" value="'+data.customerVtaccountNo+'" />'
                +     '<input type="hidden" name="customerRealaccountNo" id="customerRealaccountNo_'+idx+'" value="'+data.customerRealaccountNoEncryption+'" />'
                +   '</td>'
                +   '<td>' //별칭
                +     '<input type="text" name="customerVtaccountAlias" id="customerVtaccountAlias_'+idx+'" value="'+data.customerVtaccountAlias+'" title="별칭 입력" style="width:120px;" />'
                +     '<a href="javascript:void(0);" class="btn_type6 ml5" onclick="javascript:fn_modifyVtAccAlias(\''+idx+'\')">수정</a>'
                +   '</td>'
                +   '<td>'+data.customerVtaccountRegDate //발급일
                +   '</td>'
                +   '<td>' //삭제
                +     '<a href="javascript:void(0);" class="btn_type4" onclick="javascript:fn_deleteVtAcc(\''+idx+'\')">삭제</a>'
                +   '</td>'
                + '</tr>';
    });
    $("#rsList").append(apData);
    
    /* 페이징 설정 START */
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 START */
    var searchCondition = data.paramVO.searchCondition;
    $("#searchCondition").val(searchCondition);
    $("#sltSearchCondition").val(searchCondition);
    
    var searchKeyword = data.paramVO.searchKeyword;
    $("#searchKeyword").val(searchKeyword);
    $("#txtSearchKeyword").val(searchKeyword);
    
    var searchKind = data.paramVO.searchKind;
    $("#searchKind").val(searchKind);
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 END */
    
    $("#pageTotalcount").val(rsListTotCnt);
    
    pageNavigator(
      $("#pageIndex").val()
     ,$("#pageTotalcount").val()
     ,"fn_paging"
     ,$("#pageRowsize").val()
     ,$("#pageBlocksize").val()     
    );
    /* 페이징 설정 END */
  
    //로딩 호출    
    gfn_setLoading(false);
}


/*******************************************
 * 이벤트 함수
 *******************************************/

/* [가상계좌:수정]버튼 클릭 시 호출되는 함수 */
function fn_modifyVtAccAlias(paramNo){
    var companyNameEngAlias = $("#companyNameEngAlias_"+paramNo).val();                       //기업이름Alias영문
    var companyCodeId       = $("#companyCodeId_"+paramNo).val();                             //기업코드
    var realAccNo           = $("#customerRealaccountNo_"+paramNo).val();                     //실계좌번호
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
        //alert("<spring:message code='fail.alert.process'/>");
        return false;
    }else{
        var msgAlert = "<spring:message code='success.alert.modify'/>";
        alert(msgAlert);
        util_movePage("<c:url value='/myp/asumAcnt/asumAcnt.do'/>");
    }
}

/* [가상계좌:삭제]버튼 클릭 시 호출 */
function fn_deleteVtAcc(paramNo){
    var companyNameEngAlias = $("#companyNameEngAlias_"+paramNo).val();          //기업이름Alias영문
    var companyCodeId       = $("#companyCodeId_"+paramNo).val();                //기업코드
    var realAccNo           = $("#customerRealaccountNo_"+paramNo).val();        //실계좌번호
    var realAccType         = $("#customerRealaccountTypeNmEng_"+paramNo).val(); //실계좌형식
    var vtAccNo             = $("#customerVtaccountNo_"+paramNo).val();          //가상계좌번호
    //var vtAccAlias          = $("#customerVtaccountAlias_"+paramNo).val();       //가상계좌별칭
    var vtAccAlias          = $("#customerVtaccountAlias_"+paramNo).val().replace(/\s/g, ""); //가상계좌별칭
    
    //var msgConfirm = "해당 가상계좌를 "+"<spring:message code='confirm.delete.msg'/>";
    var msgConfirm = "해당 가상계좌를 삭제하시면,\n";
    msgConfirm += "핀테크 서비스, 금융거래 정보제공 동의도\n함께 삭제 또는 폐기 될 수 있습니다.\n\n";
    msgConfirm += "삭제 하시겠습니까?";
    if(!confirm(msgConfirm)){
        return false;
    }
        
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
        //alert("<spring:message code='fail.alert.process'/>");
        return false;
    }else{
        var msgAlert = "<spring:message code='success.alert.delete'/>";
        alert(msgAlert);
        $("#trCapl_"+paramNo).remove(); //처리후 row 삭제
//         location.reload();
        util_movePage("<c:url value='/myp/asumAcnt/asumAcnt.do'/>");
    }
}


/* 리로드 함수 */
function fn_reloadPage(){

    util_movePage("<c:url value='/myp/asumAcnt/asumAcnt.do'/>");
}

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/myp/asumAcnt/asumAcnt.do'/>";
    var param = new Object();
    param.paramMenuId = "05001";
    
    gfn_loginNeedMove(url, param);
}


function fn_commonTermsCheck(companyProfileRegNo, companyCodeId, type) {
    var url = "<c:url value='/usr/svcAppl/checkedCommonTerms.ajax'/>";
    var param = new Object();
    param.companyCodeId = companyCodeId;
    param.companyProfileRegNo = companyProfileRegNo;
    param.type = type;
    

    tmpCompanyCodeId = companyCodeId;
    tmpCompanyProfileRegNo = companyProfileRegNo;
    
    
    acctType = type;
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

        if(data.result < 1) {
            fn_commonTermsPopup(data.paramVO.type);
        } else {

	        if(acctType === "1") {
	            fn_makeAccount(tmpCompanyProfileRegNo, tmpCompanyCodeId);
	            tmpCompanyCodeId = '';
	            tmpCompanyProfileRegNo = '';
	        } else {
	            fn_makeAccount2(tmpCompanyProfileRegNo, tmpCompanyCodeId);
	            tmpCompanyCodeId = '';
	            tmpCompanyProfileRegNo = '';
	        }
	        
        }
    }
}


function fn_commonTermsPopup(type) {
    var url = "<c:url value='/usr/svcAppl/commonTermsPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '900';
    objOption.height = '700';

    var objParam = new Object();
    objParam.callBakFunc = "fn_commonTermsCallBack";

    util_modalPage2(url, objOption, objParam);
}

function fn_commonTermsCallBack(rsCd,rsMsg) {
    if(rsCd == '00'){
        alert("<spring:message code='success.alert.process' />");
       if(acctType === "1") {
            acctType = "";
            fn_makeAccountAfterTerms(tmpCompanyProfileRegNo, tmpCompanyCodeId);
            tmpCompanyCodeId = '';
            tmpCompanyProfileRegNo = '';
        } else {
            acctType = "";
            fn_makeAccount2AfterTerms(tmpCompanyProfileRegNo, tmpCompanyCodeId);
            tmpCompanyCodeId = '';
            tmpCompanyProfileRegNo = '';
        }
        
    } else {
        if(util_chkReturn(rsMsg, "s") != ""){
            alert(rsMsg+'\n['+rsCd+']');
        }
    }
}


/* 금융주자사 이름 클릭 시 호출 */
function fn_makeAccountAfterTerms(companyProfileRegNo,companyCodeId){

    var url = "<c:url value='/usr/svcAppl/registAsumAcntIsuPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '601';
    objOption.height = '600';

    var objParam = new Object();
    objParam.companyProfileRegNo = companyProfileRegNo;
    objParam.companyCodeId = companyCodeId;
    
    setTimeout(function(){
    	util_modalPage(url, objOption, objParam);
    }, 1000);  //1초후

}

/* 금융주자사 이름 클릭 시 호출 */
function fn_makeAccount2AfterTerms(companyProfileRegNo,companyCodeId){
    var url = "<c:url value='/usr/svcAppl/registAsumAcntIsuPopup2.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '601';
    objOption.height = '520';

    var objParam = new Object();
    objParam.companyProfileRegNo = companyProfileRegNo;
    objParam.companyCodeId = companyCodeId;

    setTimeout(function(){
    	util_modalPage(url, objOption, objParam);
    }, 1000);  //1초후
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

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    //변수셋팅함수 호출
    fn_setVariable();
    
    //목록호출
    fn_ajaxCall_getList();
    
});

</script>
</head>
<body>

<div class="wrap">

<input type="hidden" name="pageTotalcount"  id="pageTotalcount"  value="0" /><!-- //총갯수 -->
<input type="hidden" name="pageRowsize"     id="pageRowsize"     value="5" /><!-- //목록사이즈 -->
<input type="hidden" name="pageBlocksize"   id="pageBlocksize"   value="10" /><!-- //페이징블록사이즈 -->
<input type="hidden" name="listOrder"       id="listOrder"       value="ifnull(c.exposure_order, 'Z') asc, c.company_name_kor_alias asc, a.customer_vtaccount_reg_date desc" /><!-- //목록정렬 -->
<input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /> <!-- //검색조건 -->
<input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /> <!-- //검색Keyword -->
<input type="hidden" name="searchKind"      id="searchKind"      value="<c:if test="${not empty LoginVO}">${SYSTEM_KIND}</c:if>" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">가상계좌</li>
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
                    <h3>가상계좌</h3>
					<div class="sub_visual">
					    <img src="<c:url value='/images/spt/mypage/img_sub_visual02.jpg'/>" alt="">
					    <div class="txt">
					        <div class="mypage">
					            <div>
					                <ul>
					                    <li>- 발급 받으신 금융투자회사별 가상계좌 목록 입니다.</li>
					                    <li>- 계좌삭제를 클릭 하시면 해당 가상계좌가 삭제 됩니다.</li>
					                </ul>   
					            </div>
					        </div>
					    </div>
					</div>
                </div>
            
            
            
                <!-- con -->
                <div class="con">
                    
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
                    
                    
                    <div class="title_step step1">
	                    <!-- tbtype_list -->
	                    <!-- <table class="tbtype_list2 type4"> -->
	                    <table class="tbtype_list2">
	                        <caption>번호, 금융투자회사, 유형, 가상계좌번호, 별칭, 발급일, 삭제 리스트</caption>
	                        <colgroup>
	                            <col style="width:6%;">
	                            <col style="width:16%;">
	                            <col style="width:7%;">
	                            <col style="width:21%;">
	                            <col style="">                            
	                            <col style="width:13%;">
	                            <col style="width:10%;">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th scope="col">번호</th>
	                                <th scope="col">금융투자회사</th>
	                                <th scope="col">유형</th>
	                                <th scope="col">가상계좌번호</th>
	                                <th scope="col">별칭</th>
	                                <th scope="col">발급일</th>
	                                <th scope="col">삭제</th>
	                            </tr>
	                        </thead>
	                        <tbody id="rsList"></tbody>
	                    </table>
	                    <!-- // tbtype_list -->
	                   </div>
                    </div>
                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination"></div>
                    <!-- // pagination -->
                    
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

</body>
</html>