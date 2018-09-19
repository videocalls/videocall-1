<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introServiceTerms.jsp
 * @Description : [서비스 이용약관] 조회(개인)
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.13  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.13
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
//화면 로드 처리
$(document).ready(function(){
	//서비스이용약관 변경
    $("#searchTermsCode").change(function(){
    	fn_search($(this).val());        
    });
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 조회 --%>
function fn_search(key){
	var keys = key.split("|");	
    if(keys != null){        
    	var param = new Object();
    	
        //회원가입시 사용하는 서비스 이용약관
        if(keys[0] == "cust"){
            param.searchTerms = keys[0];                   
        //기업용 서비스 이용약관
        }else{
            //concat('comp|',a.company_code_id, '|', a.company_terms_content_reg_seq) as termsKey
            param.searchTerms = keys[0];
            param.companyCodeId = keys[1];
            param.companyTermsContentRegSeq = keys[2]; 
        }
        
        //page setting  
        var url = "<c:url value='/cmm/intro/selectSptIntroServiceTerms.ajax'/>";
        var callBackFunc = "fn_searchCallBack";
        
        //로딩 호출
        gfn_setLoading(true);
        
        <%-- 공통 ajax 호출 --%>
        util_ajaxPage(url, param, callBackFunc);
    }
}
function fn_searchCallBack(data){
	//로딩 호출
    gfn_setLoading(false);
	
	var customerTermsContent = "";
	
	if(util_chkReturn(data, "s") == "") {
	    alert("조회된 데이터가 없습니다.");
	}else{
		var termsData = data.termsData;
		customerTermsContent = termsData.customerTermsContent;
	}
	
    $("#termsContent").val(customerTermsContent);
}

</script>
</head>
<body>
<form:form commandName="IntroVO" name="IntroVO" method="post">
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
                <li class="on">서비스이용약관</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">

            <div class="certi_wrap">
                <p class="certi_tit">서비스이용약관</p>
                <p class="certi_txt">
                    <span class="fl">금융투자 핀테크 포털 서비스 이용약관</span>
                    
                    <span class="sel_box1 fr">
                        <select id="searchTermsCode" >
                            <c:forEach items="${termsCodeList}" var="termsCodeList" varStatus="status">
                                <option value="${ termsCodeList.termsKey }">${ termsCodeList.termsName }</option>
                            </c:forEach>
                        </select>
                    </span>                     
                </p>
                
                <textarea id="termsContent" class="service_privercy_txt" readonly="readonly" onclick="javascript:void(0);"><c:out value='${introVO.customerTermsContent}'/></textarea>
    
            </div>

        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>
</form:form>
</body>
</html>