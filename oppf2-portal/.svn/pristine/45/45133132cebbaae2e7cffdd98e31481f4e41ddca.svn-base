<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introOppfUse.jsp
 * @Description : [Intro > 오픈플랫폼 이용안내] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.23  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.23
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<c:if test="${isMobile eq 'true'}">
<!-- 반응형 페이지일 경우 추가 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- // 반응형 페이지일 경우 추가 -->
</c:if>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
//화면 로드 처리
$(document).ready(function(){
	
});

/*******************************************
 * 기능 함수
 *******************************************/

</script>
</head>
<body>
<form:form commandName="IntroVO" name="IntroVO" method="post">
<div class="wrap <c:if test="${isMobile eq 'true'}">mobile_wrap</c:if>"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">오픈플랫폼</a></li>
                <li class="on">오픈플랫폼 소개</li>
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
                    <h3>이용안내</h3>
                </div>

                <!-- con -->
                <div class="con">
                    <div class="info_wrap">

                        <p class="info_tit">OpenAPI 이용절차</p>
                        <ul class="info_step1">
                            <li class="step1"><div><strong>1단계</strong><p>핀테크서비스 검증</p></div></li>
                            <li class="step2"><div><strong>2단계</strong><p>이용기관 등록</p></div></li>
                            <li class="step3"><div><strong>핀테크서비스 이용</strong></div></li>
                        </ul>

                        <p class="info_tit">핀테크 서비스 검증</p>
                        <p class="info_tit2">사전검증 절차</p>
                        <ul class="info_step2">
                            <li class="step1"><div><strong>서비스 계획서 작성</strong><p>(핀테크 기업)</p></div></li>
                            <li class="step2"><div><strong>사업성 검토 및 자문</strong><p>(핀테크 지원센터)</p></div></li>
                            <li class="step3"><div><strong>서비스 테스트</strong><p>(테스트베드 센터)</p></div></li>
                            <li class="step4"><div><strong>적합성 확인 및 보안성 진단</strong><p>(테스트베드 센터, 금투사)</p></div></li>
                            <li class="step5"><div><strong>검증완료</strong></div></li>
                        </ul>

                        <p class="info_tit2">사전검증 목록</p>
                        <ul class="list_type01 type2">
                             <li>핀테크 기업이 제공하고자 하는 서비스 적합성 확인</li>
                             <li>연동 테스트를 통해 서비스 안정성 확보</li>
                        </ul>

                        <p class="info_tit2">단계별 내용</p>
                        <ul class="info_step3">
                            <li class="step1"><div><strong>서비스 사업성 검토</strong><p><span>판교 핀테크 지원센터(선택사항)</span></p></div></li>
                            <li class="step2"><div><strong>서비스 테스트 및 기술 검증</strong><p><span>코스콤 핀테크 테스트베드 센터<br>개발을 위한 Sanbox 플랫폼<br>계정 발급</span></p></div></li>
                            <li class="step3"><div><strong>적합성 확인 및 보안 컨설팅 신청</strong><p><span>금융망 정상작동 여부 확인<br>보안 컨설팅 및 결과 확인</span></p></div></li>
                        </ul>

                        <p class="info_tit2">문의처</p>
                        <ul class="list_type01 type2">
                             <li>코스콤 핀테크 테스트베드 센터 : (02) 767-8573</li>
                             <li>핀테크 지원센터 - 판교 : (031) 8016-1169 (코스콤 담당자)</li>
                        </ul>

                        <p class="info_tit">개발자 센터(sandbox 플랫폼) 계정발급</p>
                        <p class="info_tit2">신청 서류</p>
                        <ul class="list_type01 type2">
                             <li><a href="https://developers.koscom.co.kr/resources/documentation/OpenAPI_Platform_Reg.docx"><FONT COLOR=“#0000FF”>OpenAPI 플랫폼 사용 신청서 1 부</FONT></a></li>
                             <li> 법인등기부 등본 1부</li>
                             <li> 사업자 등록증 1부</li>
                             <li> 사업계획서 (자유 양식) 1부. 끝</li>
                        </ul>                             
                        <p class="info_tit2">계정발급 신청</p>
                        <ul class="list_type01 type2">
                             <li> 계정 발급 서류 제출 (이메일 : <FONT COLOR=“#0000FF”>open@koscom.co.kr)</FONT></li>
                             <li> <a href="https://developers.koscom.co.kr/"><FONT COLOR=“#0000FF”>개발자 센터</FONT></a> > 회원 가입</li>                             
                             <li> 서류 검토 후 온라인 가입 승인</li>                             
                        </ul>

                        <p class="info_tit2">오픈API플랫폼 기반 FinTech 서비스 공모전</p>
                        <ul class="list_type01 type2">
                             <li> 공모전 참가자는 공모전 기간 (8/10~9/19) 중 계정발급 신청 서류를 공모서류로 대체 가능</li>
<!--                        <li><a href="https://fintech.koscom.co.kr/portal/bbs/B0000002/view.do?nttId=408&menuNo=200006"><FONT COLOR=“#0000FF”>핀테크 테스트 베드 센터 참조</FONT></a></li>   -->
                             <li><a href="https://fintech.koscom.co.kr/portal/event/master/view.do?eventNo=86&eventSe=01&searchCnd=&searchWrd=&menuNo=200005&sdate=&edate=&prgrStatus=&tab=&section=&siteNm=&pageIndex=1"><FONT COLOR=“#0000FF”>[공모전] Koscom 오픈API플랫폼 기반 FinTech 서비스 공모전</FONT></a></li>
                        </ul>
                        
                        <p class="info_tit">이용기관 등록</p>
                        <p class="info_tit2">이용기관 등록 절차</p>
                        <ul class="info_step2">
                            <li class="step1"><div><strong>API서비스 사전 검증 통과</strong></div></li>
                            <li class="step2"><div><strong>관련서류 제출</strong></div></li>
                            <li class="step3"><div><strong>플랫폼 이용 적격여부 검토</strong></div></li>
                            <li class="step4"><div><strong>최종 승인 및 회원사 공지</strong></div></li>
                            <li class="step5"><div><strong>이용기관 등록</strong></div></li>
                        </ul>

                        <p class="info_tit2">사전검증 목록</p>
                        <ul class="list_type01 type2">
                            <li>신청 대상 : 사전검증 절차 통과 업체</li>
                            <li>방법 : 서류검토 및 필요시 실사</li>
                            <li>심사 내용 : 이용하는 API 유형(금융거래, 비금융거래 등)에 따라 심사내용이 다름</li>
                        </ul>

                        <p class="info_tit2">이용기관 등록</p>
                        <ul class="list_type01 type2">
                            <li>핀테크 포털 사이트 이용 (플랫폼 운영기관이 등록 담당)</li>
                        </ul>
                        
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
</div>
</form:form>
</body>
</html>