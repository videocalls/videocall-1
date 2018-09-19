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
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

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
/* 리로드 함수 */
function fn_reloadPage(){
    util_moveRequest("frm", "<c:url value='/usr/svcAppl/asumAcntIsu.do'/>");
}
 
/* 금융주자사 이름 클릭 시 호출 */
function fn_makeAccount(companyProfileRegNo,companyCodeId){
    var url = "<c:url value='/usr/svcAppl/registAsumAcntIsuPopup.do'/>";
    var objOption = new Object();
//     objOption.type = 'window';
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
//     objOption.type = 'window';
    objOption.type = 'modal';
    objOption.width = '601';
    objOption.height = '350';
    
    var objParam = new Object();
    objParam.companyProfileRegNo = companyProfileRegNo;
    objParam.companyCodeId = companyCodeId;
    
    util_modalPage(url, objOption, objParam);
}


/* 실계좌번호 마스크 처리 함수 */
function fn_setMaskRealAccount(){
    
    $("input[name='customerRealaccountNo']").each(function(idx,entry) {
        var crNo = util_acctMask($(this).val());
        $("#spanRealAccount_"+idx).text(crNo);
    });
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



/*******************************************
 * 이벤트 함수
 *******************************************/

/* [가상계좌:수정]버튼 클릭 시 호출되는 함수 */
function fn_modifyVtAccAlias(paramNo){
    
    if(util_chkReturn(accessToken, "s") == ""){
        //oauthToken요청 호출
        fn_ajaxCallGetOAuthToken();
        return false;
    }
    
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
//         var msgAlert = "<spring:message code='fail.alert.modify'/>";
//         alert(msgAlert);
        return false;
    }else{
        var msgAlert = "<spring:message code='success.alert.modify'/>";
        alert(msgAlert);
//         window.location.reload();
        var objParam = new Object();
        objParam.appId = '<c:out value='${paramVO.appId}'/>';
        util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>", objParam);
        //util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>");
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
//        alert("<spring:message code='fail.alert.process'/>");
        return false;
    }else{
        var msgAlert = "<spring:message code='success.alert.delete'/>";
        alert(msgAlert);
        $("#trCapl_"+paramNo).remove(); //처리후 row 삭제
//         location.reload();
        var objParam = new Object();
        objParam.appId = '<c:out value='${paramVO.appId}'/>';
        util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>", objParam);
        //util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>");
    }
}

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/usr/svcAppl/asumAcntIsu.do'/>";
    var param = new Object();
    param.paramMenuId = "06001";
    
    gfn_loginNeedMove(url, param);
}

/* 화면 로드 처리 */
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>    
    
    /* [다음]버튼 이벤트발생 시 호출 */
    $("#btnNext").bind("click", function(e){
    	//가상계좌 발급 확인
    	if(${fn:length(sptCustAccList)} <= 0){
    		alert("STEP.1에서 금융투자회사를 선택하여 가상계좌를 발급 받아주세요.");
    		return;
    	}
        var objParam = new Object();
        objParam.appId = '<c:out value='${paramVO.appId}'/>';
        util_movePage("<c:url value='/usr/svcAppl/fintechSvcChoic.do'/>", objParam);
    });

    //변수셋팅함수 호출
    fn_setVariable();
    
    //실계좌번호마스크처리함수 호출
//     fn_setMaskRealAccount();
    
});

</script>
</head>
<body>

<div class="wrap">
<form id="frm" name="frm"><input type="hidden" id="appId" name="appId" value="<c:out value='${paramVO.appId}'/>"></form>

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 신청</a></li>
                <li class="on">핀테크 서비스 신청</li>
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
                    <h3>핀테크 서비스 신청</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- 2016-06-02 수정 -->
                    <div class="step_area">
                        <ul>
                            <li class="on"><div>가상계좌 발급</div></li><!-- 현재step -->
                            <li><div>핀테크 서비스 선택</div></li>
                            <li><div>약관동의</div></li>
                            <li><div>정보 제공 동의</div></li>                            
                            <li class="last"><div>서비스 신청 완료</div></li>
                        </ul>
                    </div>
                    <!-- // 2016-06-02 수정 -->
                    
                    <p class="title_01">가상계좌 발급</p>
                    
                    <div class="title_step step1">
                        <p class="step">STEP.1</p>
                        <p>금융투자회사 선택<span>거래중인 금융투자회사를 선택하시면 가상계좌 발급신청 화면이 열립니다.</span></p>
                    </div>
                    
                    <!-- bank_list -->
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
                                             onclick="javascript:fn_makeAccount('${cpList.companyProfileRegNo}','${cpList.companyCodeId}');"
                                             </c:if>
                                             <c:if test="${cpList.issueAccountlist eq 'N'}">
                                             onclick="javascript:fn_makeAccount2('${cpList.companyProfileRegNo}','${cpList.companyCodeId}');"
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
                    <!-- // bank_list -->
                    
                    <div class="title_step step2">
                        <p class="step">STEP.2</p>
                        <p>가상계좌 목록</p>
                    </div>
                    
                    <!-- scroll_box -->
                    <div class="copy_account_list">
                        <!-- tbtype_list2 -->
                        <table class="tbtype_list2">
                            <caption>금융투자회사, 유형, 별칭, 실계좌번호, 가상계좌번호, 삭제</caption>
                            <colgroup>
                                <col style="width:18%;">
                                <col style="width:8%;">
                                <col style="width:13%;">
                                <col style="width:22%;">    
                                <col style="">
                                <col style="width:5%;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">금융투자회사</th>
                                    <th scope="col">유형</th>
                                    <th scope="col">실계좌번호</th>
                                    <th scope="col">가상계좌번호</th>
                                    <th scope="col">별칭</th>
                                    <th scope="col">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${empty sptCustAccList}">
                                        <td colspan="6"><spring:message code="common.nodata.msg"/></td>
                                    </c:when>
                                    <c:otherwise>
                                         <c:forEach var="scaList" items="${sptCustAccList}" varStatus="status">
                                              <tr id="trCapl_${status.index}">
                                                 <td>
                                                     <!-- 금융투자회사 -->
                                                     <c:out value='${scaList.companyNameKorAlias}'/>
                                                     <input type="hidden" name="companyCodeId" id="companyCodeId_${status.index}" value="<c:out value='${scaList.companyCodeId}'/>" />
                                                     <input type="hidden" name="companyNameEngAlias" id="companyNameEngAlias_${status.index}" value="<c:out value='${scaList.companyNameEngAlias}'/>" />
                                                 </td>
                                                 <td>
                                                     <!-- 유형 -->
                                                     <c:out value='${scaList.customerRealaccountTypeNm}'/>
                                                     <input type="hidden" name="customerRealaccountType" id="customerRealaccountType_${status.index}" value="<c:out value='${scaList.customerRealaccountType}'/>" />
                                                     <input type="hidden" name="customerRealaccountTypeNm" id="customerRealaccountTypeNm_${status.index}" value="<c:out value='${scaList.customerRealaccountTypeNm}'/>" />
                                                     <input type="hidden" name="customerRealaccountTypeNmEng" id="customerRealaccountTypeNmEng_${status.index}" value="<c:out value='${scaList.customerRealaccountTypeNmEng}'/>" />
                                                 </td>
                                                 <td>
                                                     <!-- 실계좌번호 -->
                                                     <span id="spanRealAccount_${status.index}"><c:out value='${scaList.customerRealaccountNoMask}'/></span>
                                                     <input type="hidden" name="customerRealaccountNo" id="customerRealaccountNo_${status.index}" value="<c:out value='${scaList.customerRealaccountNoEncryption}'/>" />
                                                 </td>
                                                 <td>
                                                     <!-- 가상계좌번호 -->
                                                     <c:out value='${scaList.customerVtaccountNo}'/>
                                                     <input type="hidden" name="customerVtaccountNo" id="customerVtaccountNo_${status.index}" value="<c:out value='${scaList.customerVtaccountNo}'/>" />
                                                 </td>
                                                 <td>
                                                     <!-- 별칭 -->
                                                     <input type="text" name="customerVtaccountAlias" id="customerVtaccountAlias_${status.index}" title="별칭 입력" style="width:140px;" value="<c:out value='${scaList.customerVtaccountAlias}'/>" />
                                                     <a href="javascript:void(0);" class="btn_type6 ml5" id="btnModify_${status.index}" onclick="javascript:fn_modifyVtAccAlias('${status.index}');">수정</a>
                                                 </td>
                                                 <td>
                                                     <a href="javascript:void(0);" class="btn_type4" id="btnDelete_${status.index}" onclick="javascript:fn_deleteVtAcc('${status.index}');">삭제</a>
                                                 </td>
                                             </tr>
                                           </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                        <!-- // tbtype_list2 -->
                    </div>
                    <!-- // scroll_box -->
                    
                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" class="btn_type1" id="btnNext">다음</a>
                    </div>
                    <!-- // btn_area -->
                    
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

</body>
</html>