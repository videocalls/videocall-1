<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : svcCompanyTermsConsnt.jsp
 * @Description : [핀테크서비스신청:약관동의]기업약관동의
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.08.01  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.08.01
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

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>";
    var param = new Object();
    param.paramMenuId = "06001";
    
    gfn_loginNeedMove(url, param);
}
 
//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    /* [전체약관동의]체크박스 이벤트발생 시 호출 */
    $(".allCheck").bind("change", function(e){
        if($(this).is(":checked")){
            $("input[name='chkCompanyTerms']").each(function(){
                $(this).prop("checked",true); 
            });
            
            $("#allchkCompanyTerms").prop("checked",true);
            $("#allchkCompanyTermsBottom").prop("checked",true);
        }else{
            $("input[name='chkCompanyTerms']").each(function(){
                $(this).prop("checked",false);                
            });
            
            $("#allchkCompanyTerms").prop("checked",false);
            $("#allchkCompanyTermsBottom").prop("checked",false);
        }
    });
    
    <c:choose>
	    <c:when test="${empty resultList && fn:length(resultList) <= 0}" >
	        //text 셋팅
	        $("#btn1").html("이전");
	        $("#btn2").html("다음");
	    
		    //이전
		    $("#btn1").click(function(){
		    	util_movePage("<c:url value='/usr/svcAppl/fintechSvcChoic.do'/>");
		    });
		    
		    //다음
		    $("#btn2").click(function(){
		    	fn_moveNext();
		    });
	    </c:when>
	    <c:otherwise>
		    //text 셋팅
	        $("#btn1").html("동의하지 않음");
	        $("#btn2").html("동의");
	        
	        //동의하지 않음
            $("#btn1").click(function(){
            	var msgAlert = "<spring:message code='errors.terms.disagree'/>";
                alert(msgAlert);
            });
            
            //동의
            $("#btn2").click(function(){
                fn_save();
            });
	        
	    </c:otherwise>
	</c:choose>
        
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
    var url = "<c:url value='/usr/svcAppl/saveSvcCompanyTermsConsnt.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_saveCallBack";
    
    //param 셋팅
    var dataList = new Array();
    $("input[name='chkCompanyTerms']").each(function(idx){
        var companyCodeId = $("#companyCodeId_"+idx).val();
        var companyTermsContentRegSeq = $("#companyTermsContentRegSeq_"+idx).val();
        
        var data = new Object();
        data.companyCodeId = companyCodeId;
        data.companyTermsContentRegSeq = companyTermsContentRegSeq;
        
        dataList.push(data);
    });
    
    param.sptCustomerCompanyTermsProfileVO = dataList;
    
    //로딩 호출
    gfn_setLoading(true, "약관 저장 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(url, param, callBackFunc);
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
        fn_moveNext();
    }
}

<%-- validate --%>
function fn_validate(){
    var termChkCnt = 0;
    
    $("input[name='chkCompanyTerms']").each(function(idx){
        if($(this).is(":checked")){
        	termChkCnt++;
        }else{
            var alertMsg = $("#txtTermsTitle_"+idx).val()+"의 서비스 이용약관";
            alert("<spring:message code='errors.required.select' arguments='"+alertMsg+"'/>");
            return false;
        }
    });
    
    if(termChkCnt == $("input[name='chkCompanyTerms']").length){
        return true;
    }else{
        return false;
    }
    
}

<%-- 다음 step 이동 --%>
function fn_moveNext(){
	util_movePage("<c:url value='/usr/svcAppl/svcTermConsnt.do'/>");
}

</script>
</head>
<body>

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
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

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>약관동의</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- 2016-07-12 수정 -->
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>가상계좌 발급</div></li><!-- 지나간step -->
                            <li class="pass"><div>핀테크 서비스 선택</div></li><!-- 지나간step -->
                            <li class="on"><div>약관동의</div></li><!-- 현재step -->
                            <li><div>정보 제공 동의</div></li>                            
                            <li class="last"><div>서비스 신청 완료</div></li>
                        </ul>
                    </div>
                    <!-- // 2016-07-12 수정 -->                   
                    
                    <div class="title_wrap">
                        <p class="title_01">약관동의</p>
                    </div>
                    
                    <c:choose>
                        <c:when test="${empty resultList || fn:length(resultList) <= 0}" >
                            <div class="nodata old_bank_list"><b>동의할 약관이 없습니다.</b></div>
                        </c:when>
                        <c:otherwise>
                            <div class="total_chk">
                                <input type="checkbox" class="allCheck" id="allchkCompanyTerms" name="allchkCompanyTerms">
                                <label for="allchkCompanyTerms" class="chk_box">다음 금융투자회사에 대한 금융투자 핀테크 서비스 이용 약관에 모두 동의 합니다.</label>
                            </div>
                            
                            <c:forEach var="resultList" items="${resultList}" varStatus="status">
                            
                                <div class="privercy_box <c:if test='${status.index == 0}'>marT0</c:if>">
			                        <p class="privercy_tit">금융투자 핀테크 서비스 이용약관 &gt; ${resultList.companyName}</p>
			                        <div class="privercy_con">
			                            <div class="privercy_txt">
			                                ${fn:replace( resultList.companyTermsContent, newLineChar, '<br/>')}
			                                <br>
			                            </div>
			                            <div class="privercy_chk">
			                                <input type="checkbox" name="chkCompanyTerms" id="chkCompanyTerms_${resultList.companyCodeId}" value="Y" />
                                            <label for="chkCompanyTerms_${resultList.companyCodeId}" class="chk_box">서비스 이용약관에 동의합니다. (필수)</label>
                                            
                                            <input type="hidden" name="companyCodeId" id="companyCodeId_${status.index}" value="<c:out value='${resultList.companyCodeId}'/>"/>
                                            <input type="hidden" name="companyTermsContentRegSeq" id="companyTermsContentRegSeq_${status.index}" value="<c:out value='${resultList.companyTermsContentRegSeq}'/>"/>
                                            <input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${resultList.companyName}'/>"/>
			                            </div>
			                        </div>
			                    </div>
			                    
                            </c:forEach>
                            
                            <div class="total_chk type2">
                                <input type="checkbox" class="allCheck" name="allchkCompanyTermsBottom" id="allchkCompanyTermsBottom">
		                        <label for="allchkCompanyTermsBottom" class="chk_box">다음 금융투자회사에 대한 금융투자 핀테크 서비스 이용 약관에 모두 동의 합니다.</label>
		                    </div>
                            
                        </c:otherwise>
                    </c:choose>
                    
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btn1" class="btn_type2">동의하지 않음</a>
                        <a href="javascript:void(0);" id="btn2" class="btn_type1">동의</a>
                    </div>
                    
                    <c:if test="${not empty resultAgreeCompanyList && fn:length(resultAgreeCompanyList) > 0}">
                        <p class="info_tit2 mt50">다음은 금융투자 핀테크 서비스 이용 약관에 이미 동의한 금융투자회사 입니다.</p>
                        
                        <div class="old_bank_list">
                            <ul>
                            <c:set var="resultAgreeCompanyListLength" value="${ fn:length(resultAgreeCompanyList)-1 }" />
	                        <c:forEach var="resultAgreeCompanyList" items="${resultAgreeCompanyList}" varStatus="status">
	                            <li>
                                    ${resultAgreeCompanyList.companyName}
                                    <c:if test="${status.index < resultAgreeCompanyListLength}">
                                        &nbsp;/&nbsp;
                                    </c:if>
	                            </li>
	                        </c:forEach>
	                        </ul>
	                    </div>
                    </c:if>    
                    
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>
</body>
</html>