<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : customerTermsListPopup.jsp
 * @Description : 개인포털 변경약관 (재)동의 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.08.03  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.08.03
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

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
    
    /* [전체약관동의]체크박스 이벤트발생 시 호출 */
    $(".allCheck").bind("change", function(e){
        if($(this).is(":checked")){
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",true); 
            });
            
            $("#allchkCustomerTerms").prop("checked",true);
            $("#allchkCustomerTermsBottom").prop("checked",true);
        }else{
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",false);                
            });
            
            $("#allchkCustomerTerms").prop("checked",false);
            $("#allchkCustomerTermsBottom").prop("checked",false);
        }
    });
        
    /* [동의하지않음] 이벤트발생 시 호출 */
    $("#btnTermsDisagree").bind("click", function(e){
        var msgAlert = "<spring:message code='errors.terms.disagree'/>";
        alert(msgAlert);
    });
    
    
    /* [동의] 이벤트발생 시 호출 */
    $("#btnTermsAgree").click(function(){
        fn_save();
    });
});
    
/*******************************************
 * 기능 함수
 *******************************************/
<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/usr/mbrReg/saveCustomerTermsListPopup.ajax'/>";
    var callBackFunc = "fn_saveCallBack";
    
    //약관정보 셋팅
    var dataList = new Array();
    $("input[name='chkCustomerTerms']").each(function(){
        if($(this).is(":checked")){
            var data = new Object();
            var tmpIdArr = $(this).attr("id").split("_");
            var customerTermsType = '';
            if(tmpIdArr.length > 1){
                customerTermsType = tmpIdArr[1];
            }
            var customerTermsContentRegSeq = $(this).val();
            
            data.customerTermsType = customerTermsType; //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
            data.customerTermsContentRegSeq = customerTermsContentRegSeq;
            data.customerTermsAuthYn = 'Y';
            
            dataList.push(data);
        }
    });
    
    //parameter 셋팅
    var objParam = new Object();
    objParam.customerTermsList = dataList;
        
    //로딩 호출
    gfn_setLoading(true, "약관 저장 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(url, objParam, callBackFunc);
}
function fn_saveCallBack(data){    
    //로그인 필요
    if(data.error == -1){
        fn_login();
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.regist' />");
    }else{
    	alert("약관동의가 저장되었습니다.\n서비스를 이용하실 수 있습니다.");
    	fn_popupClose();
    }
}

/*유효성검증 함수*/
function fn_validate(){
    
    var TermChkCnt = 0;
    
    $("input[name='chkCustomerTerms']").each(function(idx){
        if($(this).is(":checked")){
            TermChkCnt++;
        }else{
            var alertMsg = $("#txtTermsTitle_"+idx).val();
            alert("<spring:message code='errors.required.select' arguments='"+alertMsg+"'/>");
            return false;
        }
    });
    
    if(TermChkCnt == $("input[name='chkCustomerTerms']").length){
        return true;
    }else{
        return false;
    }    
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
} 
</script>
</head>
<body>

<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    
                
        <!-- #layer01 -->
        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">변경 약관 (재)동의</div>
                        
            <div class="layer_con">
                <p class="info_txt">※ 다음의 약관이 개정/갱신되어 (재)동의를 받고 있습니다.</p>
                
                <div class="title_wrap">
                    <p class="title_01">약관동의</p>
                </div>
                
                <%-- 동의 명칭 셋팅 --%>
                <c:set var="termsNamesListLength" value="${ fn:length(resultTermsList)-1 }" />
                
                <div class="total_chk">
                    <input type="checkbox" class="allCheck" name="allchkCustomerTerms" id="allchkCustomerTerms">
                    <label for="allchkCustomerTerms" class="chk_box">
                    <c:forEach var="resultTermsList" items="${resultTermsList}" varStatus="status">
	                    ${resultTermsList.customerTermsTypeName}
	                    <c:if test="${status.index < termsNamesListLength}">
	                    ,&nbsp;    
	                    </c:if>
	                </c:forEach>에 모두 (재)동의 합니다.
                    </label>                        
                </div>
                
                <c:forEach var="resultTermsList" items="${resultTermsList}" varStatus="status">
                    <div class="privercy_box <c:if test='${status.index == 0}'>marT0</c:if>">
                        <p class="privercy_tit">
                            <c:out value='${resultTermsList.customerTermsTypeName}'/>
                            <input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${resultTermsList.customerTermsTypeName}'/>"/>
                        </p>
                        <div class="privercy_con">
                            <div class="privercy_txt">
                                <p class="txt_tit">
                                    <c:out value='${resultTermsList.customerTermsTypeName}'/>
                                </p>
                                ${fn:replace( resultTermsList.customerTermsContent, newLineChar, '<br/>')}
                                <br/>
                            </div>
                            <div class="privercy_chk">
                                <input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${resultTermsList.customerTermsType}" value="${resultTermsList.customerTermsContentRegSeq}" />
                                <label for="chkCustomerTerms_${resultTermsList.customerTermsType}" class="chk_box">
                                    <c:out value='${resultTermsList.customerTermsTypeName}'/>에 (재)동의합니다. (필수)
                                </label>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                <div class="total_chk type2">
                    <input type="checkbox" class="allCheck" name="allchkCustomerTermsBottom" id="allchkCustomerTermsBottom">
                    <label for="allchkCustomerTermsBottom" class="chk_box">
                    <c:forEach var="resultTermsList" items="${resultTermsList}" varStatus="status">
                        ${resultTermsList.customerTermsTypeName}
                        <c:if test="${status.index < termsNamesListLength}">
                        ,&nbsp;    
                        </c:if>
                    </c:forEach>에 모두 (재)동의 합니다.
                    </label>                     
                </div>
            
                <div class="btn_area">
                    <a href="javascript:void(0);" id="btnTermsDisagree" class="btn_type2">동의하지 않음</a>
                    <a href="javascript:void(0);" id="btnTermsAgree" class="btn_type1">동의</a>
                </div>
            
            </div>
            
            <!-- 닫기 -->
            <%-- <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a> --%>
        </div>
    </div>        
    <!-- // layer_popup -->   
</div>
</body>
</html>