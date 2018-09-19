<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptCompanyManageDtl.jsp
 * @Description : 기업정보관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.21  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.21
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
	var url = "<c:url value='/apt/cptUsr/cptCompanyManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "02002";
    
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
    
    //file size event
    /*
    gfn_initFile("file", 60);
    */
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
    util_moveRequest("CptCompanyManageVO", "<c:url value='/apt/cptUsr/cptCompanyManageList.do'/>", "_self");
}

<%-- 저장 --%>
function fn_save(){
    if(!fn_validate()){
        return;
    }
    
    var moveUrl = "<c:url value='/apt/cptUsr/saveCptCompanyManage.ajax'/>";
    var callBackFunc = "fn_saveCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "저장중 입니다.");
    
    //file submit
    gfn_filePage("CptCompanyManageVO", moveUrl, callBackFunc);    
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    //insert
    if(util_chkReturn($.trim($('#companyProfileRegNo').val()), "s") == ""){
        var companyProfileRegNo = data.companyProfileRegNo;
        
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            return;
        }else{
            alert("<spring:message code='success.alert.regist' />");
            
            $("#companyProfileRegNo").val(companyProfileRegNo);
            $("#pageTitle").html("기업 정보 수정");
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
        }
    }
    
    //이미지 리로드
    /*
    if($("#file").val() != ""){
        var date = new Date();
        $("#companyCiImg").attr("src", "<c:url value='/cmm/companyCi/${resultDetail.companyProfileRegNo}.do?'/>"+date.getTime());
    }
    
    //file 리셋
    $("#file").val("");
    */
    
    return;
}

<%-- 저장전 체크 --%>
function fn_validate(){
	//사업자 등록번호
	var bizno = util_replaceAll($('#companyBizregNo').val(), "-", "");
	if(util_chkReturn(bizno, "s") != "") {
        if(bizno.length != 10){
            alert("<spring:message code='errors.invalid' arguments='사업자 등록번호'/>");
            $("#companyBizregNo").focus();
            return false;
        }else{
        	if(!util_isNum(bizno)){
                alert("<spring:message code='errors.integer' arguments='사업자 등록번호'/>");
                $('#companyBizregNo').focus();
                return false;           
            }
        }
    }
	
	//우편번호
    if(util_chkReturn($.trim($('#companyPostNo').val()), "s") != "") {
        if(!util_isNum($.trim($('#companyPostNo').val()))){
            alert("<spring:message code='errors.integer' arguments='우편번호'/>");
            $('#companyPostNo').focus();
            return false;           
        }
    }
	
    //기업 역할
    if(util_chkReturn($.trim($('input[name=companyServiceType]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='기업 역할'/>");
        return false;
    }
    
    //가상계좌 발급 주체
    if(util_chkReturn($.trim($('input[name=issueVtaccount]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='가상계좌 발급 주체'/>");
        $('#issueVtaccount_N').focus();
        return false;
    }
    
    //실계좌목록조회 요청 여부
    if(util_chkReturn($.trim($('input[name=issueAccountlist]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='실계좌목록조회 요청 여부'/>");
        $('#issueAccountlist_Y').focus();
        return false;
    }

    // 토큰 라이프 타임
    var tokenLifeTime = $("#tokenLifeTime").val();
    if(util_chkReturn(tokenLifeTime, "s") != "") {
        if(!util_isNum(tokenLifeTime)){
            alert("<spring:message code='errors.integer' arguments='토큰 라이프 타임'/>");
            $('#tokenLifeTime').focus();
            return false;
        }
    } else {
        alert("<spring:message code='errors.required' arguments='토큰 라이프 타임'/>");
        $('#tokenLifeTime').focus();
        return false;
    }

    // 토큰 리프레쉬 라이프 타임
    var tokenRefreshLifeTime = $("#tokenRefreshLifeTime").val();
    if(util_chkReturn(tokenRefreshLifeTime, "s") != "") {
        if(!util_isNum(tokenRefreshLifeTime)){
            alert("<spring:message code='errors.integer' arguments='토큰 리프레쉬 라이프 타임'/>");
            $('#tokenRefreshLifeTime').focus();
            return false;
        }
    } else {
        alert("<spring:message code='errors.required' arguments='토큰 리프레쉬 라이프 타임'/>");
        $('#tokenRefreshLifeTime').focus();
        return false;
    }

    //첨부파일 체크
    /*
    if(util_chkReturn($.trim($('#file').val()), "s") != ""){
        var extension = gfn_getFileExt("file");
        
        //이미지 파일은 JPG, PNG 확장자만 가능
        if( (extension != 'jpg') && (extension != 'png') ) {
            alert("이미지 파일은 JPG, PNG 확장자만 가능합니다.");
            return false;
        }
    }
    */
    
    //활성화 여부
    if(util_chkReturn($.trim($('input[name=exposureYn]:checked').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='활성화 여부'/>");
        $('#exposureYn_N').focus();
        return false;
    }
    
    //노출순서
    /*
    if(util_chkReturn($.trim($('#exposureOrder').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='노출순서'/>");
        $('#exposureOrder').focus();
        return false;
    }else{
        if(!util_isNum($.trim($('#exposureOrder').val()))){
            alert("<spring:message code='errors.integer' arguments='노출순서'/>");
            $('#exposureOrder').focus();
            return false;           
        }
    }
    */
	
	return true;
}

</script>

</head>

<body>
<form:form commandName="CptCompanyManageVO" name="CptCompanyManageVO" method="post" enctype='multipart/form-data'>
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${CptCompanyManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="searchCompanyServiceTypeAllYn" id="searchCompanyServiceTypeAllYn" value="${CptCompanyManageVO.searchCompanyServiceTypeAllYn}" />

<input type="hidden" name="companyProfileRegNo" id="companyProfileRegNo" value="${resultDetail.companyProfileRegNo}" />

<input type="hidden" name="companyNameKor" id="companyNameKor" value="${resultDetail.companyNameKor}" />
<input type="hidden" name="companyNameEng" id="companyNameEng" value="${resultDetail.companyNameEng}" />
<input type="hidden" name="companyNameKorAlias" id="companyNameKorAlias" value="${resultDetail.companyNameKorAlias}" />
<input type="hidden" name="companyNameEngAlias" id="companyNameEngAlias" value="${resultDetail.companyNameEngAlias}" />
<input type="hidden" name="companyCodeId" id="companyCodeId" value="${resultDetail.companyCodeId}" />

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
                    <h2 id="pageTitle">
                    <c:choose>
                        <c:when test="${empty resultDetail.companyProfileRegNo}">기업 정보 등록</c:when>
                        <c:otherwise>기업 정보 수정</c:otherwise>
                    </c:choose> 
                    </h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
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
                            <th scope="row" rowspan="5">기업<br>코드 정보</th>
                            <th scope="row">기업 이름(한글)</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyNameKor}'/></td>
                            <th scope="row">기업 이름(한글 약어)</th>                  
                            <td class="txt_l"><c:out value='${resultDetail.companyNameKorAlias}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">기업 이름(영어)</th>
                            <td class="txt_l"><c:out value='${resultDetail.companyNameEng}'/></td>
                            <th scope="row">기업 이름(영어 약어)</th>                  
                            <td class="txt_l"><c:out value='${resultDetail.companyNameEngAlias}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">기업 코드</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.companyCodeId}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">기업 종류</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.companyCodeKindName}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">기업 분류</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.companyDivCodeName}'/></td>
                        </tr>
                        
                        <tr>
                            <th scope="row" rowspan="15">기업<br>추가 정보</th>
                            <th scope="row">사업자 등록번호</th>
                            <td class="txt_l" colspan="3">
                                <input type="text" class="w100" id="companyBizregNo" name="companyBizregNo" value="${resultDetail.companyBizregNo}" maxlength="10" placeholder="- 없이 입력" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">우편번호</th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="companyPostNo" name="companyPostNo" value="${resultDetail.companyPostNo}" maxlength="5" style="width: 50px" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">주소</th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="companyAddress" name="companyAddress" value="${resultDetail.companyAddress}" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">대표자 명</th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="ceoName" name="ceoName" value="${resultDetail.ceoName}" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">기업 역할<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <div class="scroll_box">
                                    <c:forEach items="${companyServiceTypeList}" var="companyServiceTypeList" varStatus="status">
                                        <input type="radio" name="companyServiceType" id="companyServiceType_${companyServiceTypeList.system_code}" value="${companyServiceTypeList.system_code}"
                                               <c:if test="${resultDetail.companyServiceType eq companyServiceTypeList.system_code}"> checked="checked" </c:if>
                                               <c:if test="${empty resultDetail.companyServiceType && companyServiceTypeList.system_code eq 'G002003'}"> checked="checked" </c:if>
                                        >
                                        <label for="companyServiceType_${companyServiceTypeList.system_code}">${companyServiceTypeList.code_name_kor}</label>
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">가상계좌 발급 주체<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="radio" id="issueVtaccount_Y" name="issueVtaccount" value="Y"
                                    <c:if test="${resultDetail.issueVtaccount eq 'Y'}"> checked="checked" </c:if>
                                > <label for="issueVtaccount_Y"> 자체발급 </label>
                                <input type="radio" id="issueVtaccount_N" name="issueVtaccount" value="N"
                                    <c:if test="${resultDetail.issueVtaccount eq 'N'}"> checked="checked" </c:if>
                                > <label for="issueVtaccount_N"> 코스콤 발급 </label>
                                <input type="radio" id="issueVtaccount_X" name="issueVtaccount" value="X"
                                    <c:if test="${resultDetail.issueVtaccount eq 'X' || empty resultDetail.issueVtaccount}"> checked="checked" </c:if>
                                > <label for="issueVtaccount_X"> 해당없음 </label>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">실계좌목록조회 요청 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="radio" id="issueAccountlist_Y" name="issueAccountlist" value="Y"
                                <c:if test="${resultDetail.issueAccountlist eq 'Y' || empty resultDetail.issueAccountlist}"> checked="checked" </c:if>
                                > <label for="issueAccountlist_Y"> 사용 </label>
                                <input type="radio" id="issueAccountlist_N" name="issueAccountlist" value="N"
                                <c:if test="${resultDetail.issueAccountlist eq 'N'}"> checked="checked" </c:if>
                                > <label for="issueAccountlist_N"> 미사용 </label>
                                    <%--
                                    <input type="radio" id="issueAccountlist_X" name="issueAccountlist" value="X"
                                        <c:if test="${resultDetail.issueAccountlist eq 'X' || empty resultDetail.issueAccountlist}"> checked="checked" </c:if>
                                    > <label for="issueAccountlist_X"> 해당없음 </label>
                                    --%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">금융정보 제공 동의서 자동 수신 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="radio" id="termsTransmit_Y" name="termsTransmitYn" value="Y"
                                <c:if test="${resultDetail.termsTransmitYn eq 'Y' || empty resultDetail.termsTransmitYn}"> checked="checked" </c:if>
                                > <label for="termsTransmit_Y"> 수신함 </label>
                                <input type="radio" id="termsTransmit_N" name="termsTransmitYn" value="N"
                                <c:if test="${resultDetail.termsTransmitYn eq 'N'}"> checked="checked" </c:if>
                                > <label for="termsTransmit_N"> 수신안함 </label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">토큰 라이프 타임<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="tokenLifeTime" name="tokenLifeTime" value="${resultDetail.tokenLifeTime}" style="width: 90px; text-align:right;"/>초
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">토큰 리프레쉬 사용 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="radio" id="tokenRefresh_Y" name="tokenRefreshYn" value="Y"
                                <c:if test="${resultDetail.tokenRefreshYn eq 'Y' || empty resultDetail.tokenRefreshYn}"> checked="checked" </c:if>
                                > <label for="tokenRefresh_Y"> 사용 </label>
                                <input type="radio" id="tokenRefresh_N" name="tokenRefreshYn" value="N"
                                <c:if test="${resultDetail.tokenRefreshYn eq 'N'}"> checked="checked" </c:if>
                                > <label for="tokenRefresh_N"> 미사용 </label>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">토큰 리프레쉬 라이프 타임<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="tokenRefreshLifeTime" name="tokenRefreshLifeTime" value="${resultDetail.tokenRefreshLifeTime}" style="width: 90px; text-align:right;"/>초
                            </td>
                        </tr>
                        <%-- 20160628 ci 이미지 기능 뺌
                        <tr>
                            <th scope="row">기업 CI.</th>
                            <td class="txt_l" colspan="3">
                                <div class="company_ci_icon">
                                    <div class="icon">                                        
                                        <img id="companyCiImg" src="/cmm/companyCi/${resultDetail.companyProfileRegNo}.do" alt="기업 CI"
                                        onerror="this.src='<c:url value='/images/apt/img_banner_none.jpg'/>'" 
                                        />
                                    </div>
                                    <div class="file">
                                        <input type="file" id="file" name="file" style="width: 300px" />
                                        <p>60KB 이하의 이미지 (jpg, png 파일 가능)</p>
                                    </div>
                                </div>    
                            </td>
                        </tr>
                        --%>
                        <input type="file" id="file" name="file" style="width: 0px" />
                         
                        <tr>
                            <th scope="row">활성화 여부<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="radio" id="exposureYn_Y" name="exposureYn" value="Y"
                                    <c:if test="${resultDetail.exposureYn eq 'Y'}"> checked="checked" </c:if>
                                > <label for="exposureYn_Y"> 활성 </label>
                                <input type="radio" id="exposureYn_N" name="exposureYn" value="N"
                                    <c:if test="${resultDetail.exposureYn eq 'N' || empty resultDetail.exposureYn}"> checked="checked" </c:if>
                                > <label for="exposureYn_N"> 미활성</label>
                            </td>                  
                        </tr>
                        
                        <%-- 노출순서 뺌
                        <tr>
                            <th scope="row">노출순서<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" class="w100 txt_r" id="exposureOrder" name="exposureOrder" value="${resultDetail.exposureOrder}" maxlength="3" />
                            </td>                  
                        </tr>
                         --%>
                         <input type="hidden" id="exposureOrder" name="exposureOrder" value="0" />
                        
                        <tr>
                            <th scope="row">등록 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.createDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">수정 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.updateDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">삭제 일시</th>
                            <td class="txt_l"colspan="3"><c:out value='${resultDetail.deleteDate}'/></td>                  
                        </tr>
                        
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