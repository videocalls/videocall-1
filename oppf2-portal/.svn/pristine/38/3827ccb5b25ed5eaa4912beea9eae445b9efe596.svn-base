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
	    /**
	     * 설명       : base64 encode, decode
	     * 사용방식     : gfnBase64 
	     * 주의       : 
	     * 리턴       : 
	     */
	    var gfnBase64 = {
	        // private property
	        _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
	
	        // public method for encoding
	        ,encode : function(input){
	            var output = "";
	            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
	            var i = 0;
	            input = gfnBase64._utf8_encode(input);
	            while(i < input.length){
	                chr1 = input.charCodeAt(i++);
	                chr2 = input.charCodeAt(i++);
	                chr3 = input.charCodeAt(i++);
	
	                enc1 = chr1 >> 2;
	                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	                enc4 = chr3 & 63;
	
	                if(isNaN(chr2)){
	                    enc3 = enc4 = 64;
	                }else if(isNaN(chr3)){
	                    enc4 = 64;
	                }
	                output = output +
	                    this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
	                    this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
	            }
	            return output;
	        }
	
	        // public method for decoding
	        ,decode : function(input){
	            var output = "";
	            var chr1, chr2, chr3;
	            var enc1, enc2, enc3, enc4;
	            var i = 0;
	            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	            while(i < input.length){
	                enc1 = this._keyStr.indexOf(input.charAt(i++));
	                enc2 = this._keyStr.indexOf(input.charAt(i++));
	                enc3 = this._keyStr.indexOf(input.charAt(i++));
	                enc4 = this._keyStr.indexOf(input.charAt(i++));
	                chr1 = (enc1 << 2) | (enc2 >> 4);
	                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	                chr3 = ((enc3 & 3) << 6) | enc4;
	                output = output + String.fromCharCode(chr1);
	                if(enc3 != 64){
	                    output = output + String.fromCharCode(chr2);
	                }
	                if(enc4 != 64){
	                    output = output + String.fromCharCode(chr3);
	                }
	            }
	            output = gfnBase64._utf8_decode(output);
	            return output;
	        }
	
	        // private method for UTF-8 encoding
	        ,_utf8_encode : function (string) {
	            string = string.replace(/\r\n/g,"\n");
	            var utftext = "";
	            for(var n = 0; n < string.length; n++){
	                var c = string.charCodeAt(n);
	                if(c < 128){
	                    utftext += String.fromCharCode(c);
	                }else if((c > 127) && (c < 2048)) {
	                    utftext += String.fromCharCode((c >> 6) | 192);
	                    utftext += String.fromCharCode((c & 63) | 128);
	                }else{
	                    utftext += String.fromCharCode((c >> 12) | 224);
	                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
	                    utftext += String.fromCharCode((c & 63) | 128);
	                }
	            }
	            return utftext;
	        }
	
	        // private method for UTF-8 decoding
	        ,_utf8_decode : function (utftext) {
	            var string = "";
	            var i = 0;
	            var c = c1 = c2 = 0;
	            while( i < utftext.length ){
	                c = utftext.charCodeAt(i);
	                if(c < 128){
	                    string += String.fromCharCode(c);
	                    i++;
	                }else if((c > 191) && (c < 224)){
	                    c2 = utftext.charCodeAt(i+1);
	                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
	                    i += 2;
	                }else{
	                    c2 = utftext.charCodeAt(i+1);
	                    c3 = utftext.charCodeAt(i+2);
	                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
	                    i += 3;
	                }
	            }
	           return string;
	        }
	
	        ,URLEncode : function (string){
	            return escape(this._utf8_encode(string));
	        }
	
	        // public method for url decoding
	        ,URLDecode : function (string){
	            return this._utf8_decode(unescape(string));
	        }
	    }
    
        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){

            <c:if test="${empty paramVO.appId}">
                var url = "<c:url value='/usr/svcAppl/appList.do'/>";
            </c:if>
            <c:if test="${!empty paramVO.appId}">
                var url = "<c:url value='/usr/svcAppl/appDetail.do'/>?appId=<c:out value='${paramVO.appId}'/>";
            </c:if>

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
            /*$(".accordian-menu .accordian-cont > .btn_bar").on('click', function() {
                if ( $( this ).parent().hasClass( 'active' )) {
                    $( this ).parent().removeClass( 'active' );
                } else {
                    $( this ).parent().removeClass( 'active' );
                    $( this ).parent().addClass( 'active' );
                }
                return false;
            });*/

            $('.more button').click(function() {
                if($(this).closest( '.info_text_wrap' ).hasClass('active')) {
                    $(this).closest( '.info_text_wrap' ).removeClass('active');
                } else {
                    $(this).closest( '.info_text_wrap' ).addClass('active');
                }
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

        function fn_openTerms(termsRegNo, type){
            var url = "<c:url value='/usr/svcAppl/appTermsPopup.do'/>";
            var objOption = new Object();
            objOption.type = 'modal';
            objOption.width = '900';
            objOption.height = '700';

            var objParam = new Object();
            objParam.termsRegNo = gfnBase64.encode(termsRegNo);
            objParam.appId = gfnBase64.encode($("#appId").val());
            objParam.type = type;
            objParam.callBakFunc = "fn_openTermsCallBack";

            util_modalPage(url, objOption, objParam);
        }
        function fn_openTermsCallBack(data){
            // 제공동의 재동의 처리
            fn_appTermsReSync(data);
        }
        function fn_appTermsReSync(objParam) {
            var url = "<c:url value='/usr/svcAppl/createAppTerms.ajax'/>";
            var param = new Object();
            param.appId = $("#appId").val();
            param.subCompanyCodeId = $("#companyCodeId").val();
            param.termsCreatedYn = objParam.termsCreatedYn;
            param.termsRegNo = objParam.termsRegNo;
            param.termsAuthType = objParam.termsAuthType;

            if(objParam.termsAuthType === "G032001") {
                param.customerSignDn = objParam.customerSignDn;
                param.customerSignData = objParam.customerSignData;
                param.reqHtml = objParam.reqHtml;
            } else {
                param.arsResultCd = objParam.arsResultCd;
                param.arsResultMessage = objParam.arsResultMessage;
                param.arsTrCd = objParam.arsTrCd;
                param.arsTxtNo = objParam.arsTxtNo;
                param.arsRecordData = objParam.arsRecordData;
            }

            var callBackFunc = "fn_appTermsReSyncCallBack";
            //로딩 호출
            gfn_setLoading(true, '처리 중입니다.');
            <%-- 공통 ajax 호출 --%>
            util_ajaxPageJson(url, param, callBackFunc);
        }
        function fn_appTermsReSyncCallBack(data) {
            //로딩 호출
            gfn_setLoading(false);
            alert("<spring:message code='success.alert.process' />");
            fn_moveAppList();
        }
    </script>
</head>
<body>
<form:form commandName="AppVO" name="AppVO" method="post">
<input type="hidden" name="appId" id="appId" value="${appDetail.appId}" />
<input type="hidden" name="companyCodeId" id="companyCodeId" value="${appDetail.companyCodeId}" />
<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <section>

        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 관리</a></li>
                <li class="on">앱 사용 신청</li>
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
                    <!-- 2016-06-16 수정 -->
                    <div class="sub_visual app_top_area <c:if test='${appDetail.serviceRegNo ne ""}'>apping</c:if>"> <!-- apping 일떄 로고에 아이콘 노출 -->
                        <div class="logo">
                            <img src="/cmm/appImg/<c:out value='${appDetail.appId}'/>.do" alt="">
                        </div>
                        <div class="text">
                            <p><c:out value='${appDetail.appName}'/>  <c:if test='${appDetail.serviceRegNo ne ""}'><span class="icon_ing">사용중</span></c:if><c:if test='${appDetail.serviceRegNo eq ""}'><span class="icon_go">신청</span></c:if></p>
                            <p><c:out value='${appDetail.companyName}'/></p>
                            <p class="ft_gray"><c:out value='${appDetail.appCategoryName}'/></p>
                        </div>
                        <div class="btn_area">
                            <c:if test="${appDetail.serviceRegNo ne ''}">
                                <a href="javascript:fn_appDelete('${appDetail.appName}');" class="btn_type2">앱 삭제</a>
                                <a href="javascript:fn_moveAppAccount();" class="btn_type1">신청 정보 수정</a>
                            </c:if>
                            <c:if test="${appDetail.serviceRegNo eq ''}">
                                <a href="javascript:fn_commonTermsCheck();" class="btn_type1">사용신청</a>
                            </c:if>
                        </div>
                    </div>
                    <!-- // 2016-06-16 수정 -->
                </div>

                <!-- con -->
                <div class="con">
                    <!-- renewal -->
                    <div class="app_info"> <!-- 앱소개 -->
                        <!-- 아코디언 메뉴 -->
                        <div class="accordian-menu"><!-- accordion -->
                            <div id="appDescBar" class="accordian-cont cont_wrap"><!-- add active :열림 -->
                                <span class="btn_bar">소개</span>
                                <div class="detail_cont">
                                    <ul>
                                        <li>
                                            <div class="info_text_wrap"> <!-- active -->
                                                <pre><c:out value='${appDetail.appDescription}'/></pre>
                                                <div class="more"><button type="button" title="더 보기">더 보기</button></div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div id="pubCompanyBar" class="accordian-cont cont_wrap"><!-- add active :열림 -->
                                <span class="btn_bar">금융투자회사별 연계서비스</span>
                                <div class="detail_cont">
                                    <div class="table_wrap">
                                        <table>
                                            <colgroup><col style="width:50%;" ><col style="width:auto;" ></colgroup>
                                            <thead>
                                                <tr>
                                                    <th>금융투자회사</th>
                                                    <th>연계서비스</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="pubCompany" items="${appPubCompanyList}">
                                                    <tr>
                                                        <td>${pubCompany.companyNameKorAlias}</td>
                                                        <td>${pubCompany.apiTitle}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${appDetail.serviceRegNo ne ''}">
                                <div id="accountBar" class="accordian-cont cont_wrap"><!-- add active :열림 -->
                                    <span class="btn_bar">연결계좌</span>
                                    <div class="detail_cont">
                                        <div class="table_wrap">
                                            <table>
                                                <colgroup><col style="width:33%;" ><col style="width:33%;" ><col style="width:auto;" ></colgroup>
                                                <thead>
                                                    <tr>
                                                        <th>금융투자회사</th>
                                                        <th>별칭*</th>
                                                        <th>가상계좌번호</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="appAccount" items="${appAccountList}">
                                                        <tr>
                                                            <td>${appAccount.pubCompanyName}</td>
                                                            <td>${appAccount.customerVtaccountAlias}</td>
                                                            <td>${appAccount.customerVtaccountNo}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div id="termsBar" class="accordian-cont cont_wrap"><!-- add active :열림 -->
                                    <span class="btn_bar">
                                        정보제공동의
                                        <button type="button" onclick="fn_openTerms('${appDetail.termsRegNo}', 'detail')" class="btn_info_pop" title="정보제공팝업">정보제공팝업</button>
                                        <c:if test="${appDetail.reSyncYn eq 'Y'}">
                                            <button type="button" onclick="fn_openTerms('${appDetail.termsRegNo}', 'reSync')" class="btn_time_pop" title="정보제공동의팝업">정보제공동의팝업</button>
                                        </c:if>
                                    </span>
                                    <div class="detail_cont">
                                        유효기간
                                        <fmt:parseDate value='${appDetail.termsStartDate}' var='termsStartDate' pattern="yyyyMMdd"/>
                                        <fmt:formatDate value="${termsStartDate}"  pattern="yyyy년 MM월 dd일"/> ~
                                        <fmt:parseDate value='${appDetail.termsEndDate}' var='termsEndDate' pattern="yyyyMMdd"/>
                                        <fmt:formatDate value="${termsEndDate}"  pattern="yyyy년 MM월 dd일"/>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <!--// 아코디언 메뉴 -->
                    </div>
                    <!--// renewal -->
                    <div class="btn_area">
                        <a href="javascript:fn_moveAppList();" class="btn_type2">목록</a>
                    </div>
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
