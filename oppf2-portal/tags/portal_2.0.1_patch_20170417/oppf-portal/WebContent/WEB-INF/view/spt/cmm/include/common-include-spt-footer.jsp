<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>

<%-- footer --%>
<footer class="footer">
    <div class="footer_area">
        <ul class="link">
            <li><a href="javascript:void(0);" onclick="javascript:fn_moveHttpUrl('<c:url value='/cmm/intro/introServiceTerms.do'/>');">서비스이용약관</a></li>
            <li><a href="javascript:void(0);" onclick="javascript:fn_moveHttpUrl('<c:url value='/cmm/intro/introPrivacyPolicy.do'/>');">개인정보취급방침</a></li>
        </ul>
        <address>
            서울시 영등포구 여의나루로 76 ㈜코스콤 | <span>이메일 : <a href="mailto:<spring:message code='Globals.manager.email.${ SYSTEM_KIND }' />"><spring:message code='Globals.manager.email.${ SYSTEM_KIND }' /></a></span>
                <span class="copy">COPYRIGHT 2016. KOSCOM CORP. ALL RIGHTS RESERVED.</span>
        </address>

        <div class="family_site">
            <!-- selBox2 -->
            <span class="sel_box2">
                <select onchange="if(this.value){window.open(this.value);}" style="min-width:150px">
                    <option value="">코스콤 사이트</option>
                    <option value="https://www.koscom.co.kr ">KOSCOM</option>
                    <option value="http://fintech.koscom.co.kr">FINTECH</option>
                    <option value="http://www.check.co.kr">CHECKExpert</option>
                    <option value="http://data.koscom.co.kr">Market Data</option>
                    <option value="http://datamall.koscom.co.kr/index.jsp">DataMall</option>
                    <option value="http://www.signkorea.com">SignKorea</option>
                    <option value="http://www.ansimmail.co.kr">안심메일</option>
                </select>
            </span>
            <!-- selBox2 -->
            <span class="sel_box2">
                <select onchange="if(this.value){window.open(this.value);}" style="min-width:170px">
                    <option value="">관련 사이트</option>
                    <option value="http://www.fsc.go.kr/">금융위원회</option>
                    <option value="http://www.fss.or.kr/fss/kr/main.html">금융감독원</option>
                    <option value="http://www.kftc.or.kr/">금융결제원</option>
                    <option value="http://www.fsec.or.kr/Main.do">금융보안원</option>
                    <option value="http://www.kofia.or.kr/">금융투자협회</option>
                    <option value="http://www.fintechcenter.or.kr/">핀테크지원센터</option>
                    <option value="http://www.kcredit.or.kr/index.jsp">한국신용정보원</option>
                    <option value="http://www.krx.co.kr/">KRX(한국거래소)</option>                       
                </select>
            </span>             
        </div>

    </div>
</footer>
<%-- // footer --%>