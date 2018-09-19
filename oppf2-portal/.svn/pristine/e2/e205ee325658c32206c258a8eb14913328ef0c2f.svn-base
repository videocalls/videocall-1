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
        <%-- 로그인 처리 공통 함수 --%>
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
            $(".accordian-menu .accordian-cont > .btn_bar").on('click', function() {
                if ( $( this ).parent().hasClass( 'active' )) {
                    $( this ).parent().removeClass( 'active' );
                } else {
                    $( this ).parent().removeClass( 'active' );
                    $( this ).parent().addClass( 'active' );
                }
                return false;
            });
        });
        function fn_moveAppList() {
            util_movePage("<c:url value='/usr/svcAppl/appList.do'/>");
        }
        function fn_appDelete(appName) {
            var confirmMsg = appName + "앱 신청 정보를 삭제하시겠습니까? 삭제하시면 서비스를 이용하실 수 없습니다.";
            if( confirm(confirmMsg) ){
                //로딩 호출
                gfn_setLoading(true);
                var url = "<c:url value='/usr/svcAppl/appDelete.ajax'/>";
                var param = $("#AppVO").serialize();
                var callBackFunc = "fn_appDeleteCallBack";
                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            }
        }
        function fn_appDeleteCallBack(data) {
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            fn_moveAppList(); // 목록 화면으로 이동
            //로딩 호출
            gfn_setLoading(false);
        }
        function fn_moveAppAccount() {
            var param = new Object();
            param.appId = $("#appId").val();
            util_movePage("<c:url value='/usr/svcAppl/appAccountSelect.do'/>", param);
        }
        function fn_openTerms(termsRegNo){
            var url = "<c:url value='/usr/svcAppl/svcTermConsntPopup.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '900';
            objOption.height = '700';

            var objParam = new Object();
            objParam.termsRegNo = termsRegNo;
            objParam.termsAuthYn = 'N';
            objParam.callBakFunc = "fn_openTermsCallBack";

            util_modalPage(url, objOption, objParam);
        }
        function fn_openTermsCallBack(data){
            //result : data.result == 0 성공, else 실패
            if(data.result == 0){
                alert("<spring:message code='fail.alert.process' />");
            }else{
                alert("<spring:message code='success.alert.process' />");
            }
        }
        function fn_commonTermsCheck() {
            var url = "<c:url value='/usr/svcAppl/checkedCommonTerms.ajax'/>";
            var param = new Object();
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
                fn_moveAppAccount();
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
                fn_moveAppAccount();
            } else {
                if(util_chkReturn(rsMsg, "s") != ""){
                    alert(rsMsg+'\n['+rsCd+']');
                }
            }
        }
    </script>
</head>
<body>
<form:form commandName="AppVO" name="AppVO" method="post">
<input type="hidden" name="appId" id="appId" value="${appDetail.appId}" /><!-- //목록사이즈 -->
<div class="wrap">

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
                    <h3>앱 사용 신청</h3> <!-- renewal -->
                </div>

                <!-- con -->
                <div class="con">
                    <!-- renewal -->
                    <div class="member_area type2 pb30">
                        <div class="tit_info">
                            <p>
                                <span>신청 완료</span>
                                <strong>${appDetail.appName}</strong>앱 신청이 완료되었습니다.
                            </p>
                        </div>
                    </div>
                    <p class="title_01">서비스 신청 내역</p>
                    <!-- tbtype_form -->
                    <table class="tbtype_list2">
                        <caption>금융투자회사, 가상계좌 정보</caption>
                        <colgroup>
                            <col style="width:300px;">
                            <col style="width:auto;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">금융투자회사</th>
                            <th scope="col">가상계좌</th>
                        </tr>
                        <tbody>
                        <c:forEach var="item" items="${appAccountList}" varStatus="index">
                            <c:forEach var="account" items="${item.accountList}" varStatus="pubIndex">
                                <tr>
                                    <c:if test="${pubIndex.index eq 0}">
                                        <th scope="row" rowspan="${fn:length(item.accountList)}">${item.pubCompanyName}</th>
                                    </c:if>
                                    <td>${account.customerVtaccountNo} / ${account.customerVtaccountAlias}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- // tbtype_form -->
                    <ul class="list_style_01">
                        <li>금융거래정보 제공 동의는 마이페이지>동의서 관리에서 조회 가능합니다.</li>
                    </ul>

                    <!-- 2016-06-20 추가 -->
                    <div class="btn_area">
                        <a href="javascript:fn_moveAppList();" class="btn_type1">확인</a>
                    </div>
                    <!-- // 2016-06-20 추가 -->
                    <!--// renewal -->
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
