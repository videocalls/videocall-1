<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptApiManageDtl.jsp
 * @Description : 기업사용자의 api 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.30  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.30
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
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
	var url = "<c:url value='/cpt/myp/api/cptApiManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "05002";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
	//목록
    $("#btnList").click(function(){
    	fn_list();    
    });
	   
	//제공자 셋팅
	$('input[name=companyCodeId]:checked').focus();
	
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
	util_moveRequest("CptApiManageVO", "<c:url value='/cpt/myp/api/cptApiManageList.do'/>");
}

</script>

</head>

<body>
<form:form commandName="CptApiManageVO" name="CptApiManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="apiId" id="apiId" value="<c:out value='${paramVO.apiId}'/>" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="#none">홈</a></li>
                <li><a href="#none">마이 페이지</a></li>
                <li class="on">API 관리</li>
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
                    <h3>API 관리</h3>                 
                </div>

                <div>
                    <table class="tbtype_form type3">
                        <caption>API 세부 정보</caption>
                        <colgroup>
                            <col style="width:7%;">
                            <col style="width:5%;">
                            <col style="width:18%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row" rowspan="3">API 등록 정보</th>
                            <th scope="row" colspan="2">API 이름</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">API ID</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiId}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">Routing URI</th>
                            <td class="txt_l"><c:out value='${resultDetail.routingUri}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" rowspan="7">API 추가 정보</th>
                            <th scope="row" rowspan="4">포털<br>표시 정보</th>
                            <th scope="row">API 서비스 제공자</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyNameKor}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 구분</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiCategoryName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 타이틀</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiTitle}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">API 설명</th>
                            <td class="txt_l">
                                <% pageContext.setAttribute("newLineChar", "\n"); %>
                                ${fn:replace( resultDetail.apiDescription, newLineChar, '<br/>')}
                            </td>
                        </tr>
                       <%-- <tr>
                            <th scope="row">활성화 여부</th>
                            <td class="txt_l">
                                <c:if test="${resultDetail.exposureYn eq 'Y'}"> 활성 </c:if>
                                <c:if test="${resultDetail.exposureYn eq 'N' || empty resultDetail.exposureYn}"> 미활성 </c:if>
                            </td>                  
                        </tr>--%>
                        <%--
                        <tr>
                            <th scope="row">활성화 순서</th>
                            <td class="txt_l"><c:out value='${resultDetail.exposureOrder}'/></td>
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="6">계약<br>관련 정보</th>
                            <th scope="row">계좌 사용여부</th>
                            <td class="txt_l">
                                <c:if test="${resultDetail.apiAccountYn eq 'Y' || empty resultDetail.apiAccountYn}"> 사용 </c:if>
                                <c:if test="${resultDetail.apiAccountYn eq 'N'}"> 미사용 </c:if>
                            </td>                  
                        </tr>
                        --%>
                        <%--<tr>
                            <th scope="row" rowspan="3">계약<br>관련 정보</th>
                            <th scope="row">API 서비스 계약 여부</th>
                            <td class="txt_l">
                                <c:forEach items="${apiContractCodeList}" var="apiContractCodeList" varStatus="status">
                                    <c:if test="${resultDetail.apiContractCode eq apiContractCodeList.system_code}">
                                         <c:out value='${apiContractCodeList.code_name_kor}'/>
                                    </c:if>
                                </c:forEach>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">API 서비스 계약 체결일</th>
                            <td class="txt_l"><c:out value='${resultDetail.apiContractDate}'/></td>
                        </tr>
                        <tr id="apiTermsDateTr">
                            <th scope="row">API 서비스 계약 기간</th>
                            <td class="txt_l">
                                <c:out value='${resultDetail.apiTermsStartDate}'/>
                                <span class="dateDot">~</span>
                                <c:out value='${resultDetail.apiTermsExpireDate}'/>
                            </td>                  
                        </tr>--%>
                        <%--
                        <tr>
                            <th scope="row">추가 정보 등록자</th>
                            <td class="txt_l"><c:out value='${resultDetail.createIdName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">추가 정보 등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        --%>
                        </tbody>
                    </table>
                    
                    <div class="btn_area">
                        <div class="right">
                            <a href="javascript:void(0);" id="btnList" class="btn_type2">목록</a>
                        </div>
                    </div>
                    
                </div>

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
    
</form:form>    
</body>
</html>