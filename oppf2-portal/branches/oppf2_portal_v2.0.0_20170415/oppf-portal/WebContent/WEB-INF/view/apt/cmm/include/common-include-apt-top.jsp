<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%-- wrap_top --%>
<header class="wrap_top">
    <div>
        <h1>
            <a href="<c:url value='/'/>"><img src="<c:url value='/images/apt/logo.png'/>" alt="koscom OpenAPI Platform" /></a>
            <span>서비스관리시스템</span>
        </h1>
        <div style="top:15px">
            <div class="login_st1">
                <span>'${ LoginVO.name_kor }' 님 안녕하세요</span>
                <a href="<c:url value='/cmm/commonLogout.do'/>" class="btn_logout">
                                    로그아웃
                </a>                   
            </div>
        </div>
        <!-- <div class="timer">최종접속시간 : 2016-06-09 17:20 / <span>30:00</span><a href="#none" class="btn_logout">연장</a></div> -->
    </div>
</header>
<%-- // wrap_top --%>