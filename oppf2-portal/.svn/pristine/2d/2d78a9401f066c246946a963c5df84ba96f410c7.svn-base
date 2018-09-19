<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptWithdrawUserManageDtl.jsp
 * @Description : 탈퇴회원관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.06.16  최판광        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2017.06.16
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
    var url = "<c:url value='/apt/sptUsr/sptUserManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "01001";
    
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
    
    
    //저장
    $("#btnSave").click(function(){
    	fn_save();
    });
    
    
    //회원 가입 상태
    $("#customerRegStatus").val("<c:out value='${resultDetail.customerRegStatus}'/>");
    
    //이메일
    var emailArr = "<c:out value='${resultDetail.customerEmail}'/>".split("@");
    if(emailArr != null){
        $("#customerEmail_01").val(emailArr[0]); 
        $("#customerEmail_02").val(emailArr[1]);
    }
    
    //휴대전화
    var phone = "<c:out value='${resultDetail.customerPhone}'/>".split("-");
    if(phone != null){
        $("#customerPhone_01").val(phone[0]);
        $("#customerPhone_02").val(phone[1]);
        $("#customerPhone_03").val(phone[2]);
    }
    
    //rowspan
    <c:choose>
        <c:when test="${empty resultDetailSvcTermsList}" >
        </c:when>
        <c:otherwise>
            //첫 컬럼 rowspan
            gfn_rowspanMerge("rowspan_subcompany");
            
            var key2 = "";
            var key3 = "";
            var key4 = "";
            
            var key2Data = "";
            var key3Data = "";
            var key4Data = "";
            
            //roop
            <c:forEach var="resultDetailSvcTermsList" items="${resultDetailSvcTermsList}" varStatus="status">
            key2Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}";
            key3Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}${resultDetailSvcTermsList.pubcompanyCodeId}";
            key4Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}";
            
            //rowspan
            if(key2 == ""){
                key2 = key2Data;
                gfn_rowspanMerge("rowspan_"+key2);
            }else{
                if(key2 != key2Data){
                    key2 = key2Data;
                    gfn_rowspanMerge("rowspan_"+key2);
                }
            }
            
            if(key3 == ""){
                key3 = key3Data;
                gfn_rowspanMerge("rowspan_"+key3);
            }else{
                if(key3 != key3Data){
                    key3 = key3Data;
                    gfn_rowspanMerge("rowspan_"+key3);
                }
            } 
            
            if(key4 == ""){
                key4 = key4Data;
                gfn_rowspanMerge("rowspan_"+key4);
            }else{
                if(key4 != key4Data){
                    key4 = key4Data;
                    gfn_rowspanMerge("rowspan_"+key4);
                }
            } 
			
            
            </c:forEach>
        </c:otherwise>
    </c:choose>
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
    util_moveRequest("SptUserManageVO", "<c:url value='/apt/sptUsr/sptWithdrawUserManageList.do'/>", "_self");
}

<%-- 탈퇴 처리 --%>
function fn_save(){
    
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/sptUsr/updateSptWithdrawUser.ajax'/>";
    var param = $("#SptUserManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    //사용자 customer_reg_no가 없는경우
    if(data.user == -1){
        alert("회원의 customer_reg_no가 없습니다.");
        return;
    }
    
    if(data.result > 0){
        alert("저장에 성공하였습니다.");
    }else{
        alert("저장에 실패하였습니다.");
    }
    return;
}


</script>

</head>

<body>
<form:form commandName="SptUserManageVO" name="SptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${SptUserManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="customerRegNo" id="customerRegNo" value="<c:out value='${resultDetail.customerRegNo}'/>" />

<!-- //리스트 검색조건 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${SptUserManageVO.searchCondition}'/>" />
<input type="hidden" name="searchCustomerRegStatus" id="searchCustomerRegStatus" value="<c:out value='${SptUserManageVO.searchCustomerRegStatus}'/>" />
<input type="hidden" name="searchDateFrom" id="searchDateFrom" value="<c:out value='${SptUserManageVO.searchDateFrom}'/>" />
<input type="hidden" name="searchDateTo" id="searchDateTo" value="<c:out value='${SptUserManageVO.searchDateTo}'/>" />
<input type="hidden" name="searchDateType" id="searchDateType" value="<c:out value='${SptUserManageVO.searchDateType}'/>" />
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${SptUserManageVO.searchKeyword}'/>" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <section class="content">
                <div class="location">
                    <h2>탈퇴 회원 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <h3>기본 정보</h3>                                
                <div class="tb_write1">
                    <table>
                        <caption>회원정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:20%;">
                            <col style="width:30%;">
                            <col style="width:20%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row" colspan="2">회원 이름(한글)</th>
                            <td class="txt_l" colspan="3">
                                <c:out value='${resultDetail.customerNameKor}'/>
                            </td>                  
                        </tr>
                        <%--
                        <tr>
                            <th scope="row" colspan="2">회원 이름(영문)</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerNameEng}'/></td>                  
                        </tr>
                        --%>
                        <tr>
                            <th scope="row" colspan="2">회원 ID</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerId}'/></td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">회원 가입 상태</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerRegStatusName}'/></td>                 
                        </tr>
						<tr>
                            <th scope="row" colspan="2">계정 유형</th>
                            <td class="txt_l" ><c:out value='${resultDetail.temporaryMemberName}'/></td>
                            <th scope="row">가입 구분</th>
                            <td class="txt_l"><c:out value='${resultDetail.customerJoinTypeName}'/></td>
                            </td>                 
                        </tr>
						<tr>
							<th scope="row" colspan="2">계정 상태</th>
							<td class="txt_l"><c:out value='${resultDetail.customerRegStatusName}'/></td>
                            <th scope="row">단말기 정보</th>
                            <td class="txt_l"><input type="text" class="w250" id="" value="${deviceDetail.deviceUniqueId}"  readonly="readonly" /></td>
						</tr>
                        <tr>
                            <th scope="row" colspan="2">생년월일</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerBirthDay}'/></td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">e-Mail</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerEmail}'/></td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">휴대폰</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerPhone}'/></td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">이메일 인증 상태</th>
                            <td class="txt_l"><c:out value='${resultDetail.customerEmailAuthYn}'/></td>                 
                            <th scope="row">인증 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.customerEmailRegDate}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">이메일 수신동의 여부</th>
                            <td class="txt_l"><c:out value='${resultDetail.customerEmailReceiveYn}'/></td>
                            <th scope="row">등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.customerEmailReceiveDate}'/></td>                                             
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">본인 인증 상태</th>
                            <td class="txt_l">
                                <input type="text" style="width: 90%;" maxlength="200"
                                       value="${resultDetail.ciCustomerVerify}" readonly="readonly"/>
                            </td>
                            <th scope="row">인증 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.ciCustomerVerifyDate}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">공인 인증서 등록 상태</th>
                            <td class="txt_l"><c:out value='${resultDetail.dnCustomerVerify}'/></td>                 
                            <th scope="row">등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.dnCustomerVerifyDate}'/></td>
                        </tr>
                        
                        <%-- 이용약관 --%>
                        <c:set value="${ fn:length(resultDetailTermsList) }" var="resultDetailTermsListLength" />
                        <c:choose>
                            <c:when test="${empty resultDetailTermsList}" >
                                <tr>
                                    <th scope="row" colspan="2">이용 약관</th>
                                    <td colspan="3" align="center">약관동의를 안하였습니다.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="resultDetailTermsList" items="${resultDetailTermsList}" varStatus="status">
                                    <tr>
                                        <c:if test="${status.index eq 0}">
                                            <th scope="row" rowspan="${ resultDetailTermsListLength }">이용 약관</th>
                                        </c:if>
                                        <th scope="row"><c:out value='${resultDetailTermsList.customerTermsTypeName}'/></th>
                                        <td class="txt_l">
                                            <c:if test="${resultDetailTermsList.customerTermsAuthVerYn eq 'N'}">
                                                <span style='color: red;'>
                                            </c:if>
                                            <c:out value='${resultDetailTermsList.customerTermsAuthYn}'/>
                                            <c:if test="${resultDetailTermsList.customerTermsAuthVerYn eq 'N'}">
                                                </span>
                                            </c:if>
                                        </td>                 
			                            <th scope="row">동의 일시</th>
			                            <td class="txt_l"><c:out value='${resultDetailTermsList.customerTermsAuthDate}'/></td>
                                    </tr>                                                    
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                                                
                        <tr>
                            <th scope="row" colspan="2">스마트 OTP 사용</th>
                            <td class="txt_l">
                                <c:choose>
		                            <c:when test="${resultDetail.customerOtpYn eq 'Y'}" >
		                                                            사용 (<c:out value='${resultDetail.customerOtpId}'/> / <c:out value='${resultDetail.customerOtpStatusName}'/>)
		                            </c:when>
		                            <c:otherwise>
		                                                            미사용
		                            </c:otherwise>
		                        </c:choose>
                            </td>                 
                            <th scope="row">등록 일시</th>
                            <td class="txt_l"><c:out value='${resultDetail.otpChgDate}'/></td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">통합계좌조회 사용</th>
                            <td class="txt_l" colspan="3">
                                    <c:if test="${resultDetail.integrationAccountYn eq 'Y' }"> 사용 </c:if> 
                                   <c:if test="${resultDetail.integrationAccountYn eq 'N' }"> 미사용 </c:if>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">등록 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">수정 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.updateDate}'/></td>                  
                        </tr>
                        <c:choose>
                            <c:when test="${empty resultDetail.deleteDate}">
                                <tr>
                                    <th scope="row" colspan="2">탈퇴 일시</th>
                                    <td class="txt_l" colspan="3"><c:out
                                            value='${resultDetail.deleteDate}' /></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <th scope="row" colspan="2">탈퇴 일시</th>
                                    <td class="txt_l"><c:out
                                            value='${resultDetail.deleteDate}' /></td>
                                    <th scope="row">탈퇴 처리</th>
                                    <td class="txt_l">
			                                <select id="customerWithdrawalProcYn" name="customerWithdrawalProcYn" style="min-width: 80px;">
			                                    <option value="Y" <c:if test="${resultDetail.customerWithdrawalProcYn eq 'Y' }"> selected </c:if> >처리</option>
			                                    <option value="N" <c:if test="${resultDetail.customerWithdrawalProcYn eq 'N' }"> selected </c:if> >미처리</option>
			                                </select>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        
                        </tbody>
                    </table>
                </div>
                
                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
                    <div class="right">
                               <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                    </div>
                </div>
                <!-- // btn_type3 -->           

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>