<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptAppManageDtl.jsp
 * @Description : 기업사용자의 app 상세
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
	var url = "<c:url value='/cpt/myp/app/cptAppManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "05001";
    
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
});

/*******************************************
 * 기능 함수
 *******************************************/

<%-- 목록 --%>
function fn_list(){
	util_moveRequest("CptAppManageVO", "<c:url value='/cpt/myp/app/cptAppManageList.do'/>", "_self");
}

</script>

</head>

<body>
<form:form commandName="CptAppManageVO" name="CptAppManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="appId" id="appId" value="<c:out value='${paramVO.appId}'/>" />

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
                    <h3>APP 관리</h3>                 
                </div>

                <!-- con -->
                <div class="con">

                    <table class="tbtype_form type3">
                        <caption>App. 세부 정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:5%;">
                            <col style="width:7%;">
                            <col style="width:8%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row" rowspan="10">App.<br>등록 정보</th>
                            <th scope="row" colspan="3">앱개발자 명</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyNameKor}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">앱개발자 코드</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyCodeId}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. 이름</th>
                            <td class="txt_l"><c:out value='${resultDetail.appName}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. ID</th>
                            <td class="txt_l"><c:out value='${resultDetail.appId}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. 상태</th>
                            <td class="txt_l"><c:out value='${resultDetail.appStatusName}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">App. Key</th>
                            <td class="txt_l"><c:out value='${resultDetail.appKey}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">Secret</th>
                            <td class="txt_l"><c:out value='${resultDetail.keySecret}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuthCallbackURL</th>
                            <td class="txt_l" style="word-break: break-all;"><c:out value='${resultDetail.oauthCallbackUrl}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuth Scope</th>
                            <td class="txt_l"><c:out value='${resultDetail.oauthScope}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="3">OAuth Type</th>
                            <td class="txt_l"><c:out value='${resultDetail.oauthType}'/></td>
                        </tr>


                        
                        <tr>
                            <th scope="row" rowspan="8">App.<br>추가 정보</th>
                            <th scope="row" rowspan="5">포털<br>표시 정보</th>
                            <th scope="row" colspan="2">App. 구분</th>
                            <td class="txt_l">
                                <c:forEach items="${appCategoryList}" var="appCategoryList" varStatus="status">
                                    <c:if test="${resultDetail.appCategory eq appCategoryList.system_code}">
                                         <c:out value='${appCategoryList.code_name_kor}'/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">App. 설명</th>
                            <td class="txt_l">
                                <% pageContext.setAttribute("newLineChar", "\n"); %>
                                ${fn:replace( resultDetail.appDescription, newLineChar, '<br/>')}
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">활성화 여부</th>
                            <td class="txt_l">
                                <c:if test="${resultDetail.exposureYn eq 'Y'}"> 활성 </c:if>
                                <c:if test="${resultDetail.exposureYn eq 'N'}"> 미활성 </c:if>
                            </td>                  
                        </tr>
                        <%--
                        <tr>
                            <th scope="row" colspan="2">활성화 순서</th>
                            <td class="txt_l"><c:out value='${resultDetail.exposureOrder}'/></td>
                        </tr>
                        --%>
                        <tr>
                            <th scope="row" rowspan="2">App.<br>아이콘</th>
                            <th scope="row">아이콘</th>
                            <td class="txt_l">
                                <div class="app_icon">
                                    <div class="icon">
                                        <c:set value="/cmm/appImg/${resultDetail.appId}.do" var="appIconImgSrc" />
                                        <c:if test="${resultDetail.isAppIcon eq 'N'}">
                                            <c:set value="/images/cmm/icon/icon_app_none.png" var="appIconImgSrc" />
                                        </c:if>
                                        
                                        <img id="appIconImg" src="<c:url value='${ appIconImgSrc }'/>" alt="App. 아이콘" style="height:50px;width: 50px"
                                        onerror="this.src='<c:url value='/images/cmm/icon/icon_app_none.png'/>'" 
                                        />
                                    </div>
                                </div>    
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">URL</th>
                            <td class="txt_l"><c:out value='${resultDetail.appDlUrl}'/></td>
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="3">계약<br>관련 정보</th>
                            <th scope="row" colspan="2">App. 계약</th>
                            <td class="txt_l">
                                <div class="scroll_box">
                                    <c:forEach items="${appContractCodeList}" var="appContractCodeList" varStatus="status">
                                        <c:if test="${resultDetail.appContractCode eq appContractCodeList.system_code}">
                                            <c:out value='${appContractCodeList.code_name_kor}'/>
                                        </c:if>
                                    </c:forEach> 
                                </div>
                            </td>                  
                        </tr>
                        
                        <tr>
                            <th scope="row" colspan="2">App. 계약 체결일</th>
                            <td class="txt_l"><c:out value='${resultDetail.appContractDate}'/></td>
                        </tr>
                        <tr id="appTermsDateTr">
                            <th scope="row" colspan="2">App. 계약 기간</th>
                            <td class="txt_l">
                                <c:out value='${resultDetail.appTermsStartDate}'/>
                                <span class="dateDot">~</span>
                                <c:out value='${resultDetail.appTermsExpireDate}'/>
                            </td>                  
                        </tr>
                        <%--
                        <tr>
                            <th scope="row" rowspan="2">추가정보</th>
                            <th scope="row" colspan="2">등록자</th>
                            <td class="txt_l"><c:out value='${resultDetail.createIdName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        --%>
                        <tr>
                            <th scope="row" colspan="4">API 관련정보</th>
                            <td class="txt_l">
                                <table>
                                    <caption>서비스 제공자</caption>
                                    <colgroup>
                                        <col style="width:*">
                                        <col style="width:60%;">
                                        <%--
                                        <col style="width:15%;">
                                        <col style="width:25%;">
                                        --%>
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">서비스 제공자</th>
                                            <th scope="row">API 이름</th>
                                            <%--
                                            <th scope="row">API 계약<br>여부</th>
                                            <th scope="row">API 계약<br>체결일</th>
                                            --%>
                                        </tr>
                                        <c:choose>
                                            <c:when test="${empty resultApiList}" >
                                                <tr>
                                                   <td colspan="5" align="center">조회 된 서비스 제공자가 없습니다.</td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="resultApiList" items="${resultApiList}" varStatus="status">
                                                    <tr>
                                                        <td class="txt_l"><c:out value='${resultApiList.companyNameKor}'/></td>                  
                                                        <td class="txt_l"><c:out value='${resultApiList.apiName}'/></td>
                                                        <%--
                                                        <td class="txt_l" style="text-align: center"><c:out value='${resultApiList.apiContractCodeName}'/></td>
                                                        <td class="txt_l" style="text-align: center">
                                                            <c:out value='${resultApiList.apiTermsStartDate}'/>
                                                            <c:if test="${not empty resultApiList.apiTermsStartDate}"> ~ </c:if>
                                                            <c:out value='${resultApiList.apiTermsExpireDate}'/>
                                                        </td>
                                                        --%>
                                                    </tr>                                                    
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                                
                            </td>                  
                        </tr>
                        </tbody>
                    </table>
                    
                    <div class="btn_area">
                        <div>
                            <a href="javascript:void(0);" id="btnList" class="btn_type2">목록</a>
                        </div>
                    </div>
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
</form:form>    
</body>
</html>