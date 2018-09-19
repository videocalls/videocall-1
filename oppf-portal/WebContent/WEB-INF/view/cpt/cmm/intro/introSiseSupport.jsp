<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introFintechCpt.jsp
 * @Description : [Intro > 핀테크오픈플랫폼 소개] 조회 (기업용)
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
<c:if test="${isMobile eq true}">
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

<div class="wrap <c:if test="${isMobile eq true}">mobile_wrap</c:if>"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->
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
                <li class="on">시세정보 지원</li>
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
                    <h3>시세정보 지원</h3>
                </div>

                <!-- con -->
                <div class="con">
                    <div class="info_wrap">

                        <p class="info_tit">개요</p>
                        <p class="info_tit2">정보이용료 부과 유예</p>
                        <ul class="list_type01 type2">
                             <li>FinTech 산업내 스타트업기업의 성장 지원 및 정보이용 비즈니스 모델 활성화를 통한 미래 수익기반 확대를 위해 초기 1년간 일부 정보이용료 부과를 유예</li>
                        </ul>

                        <p class="info_tit">추진 목적</p>
                        <p class="info_tit2">금융권 FinTech 산업 육성 및 스타트업 활성화</p>
                        <ul class="list_type01 type2">
                             <li>금융위원회의 공동 오픈플랫폼 구축 계획(‘15.7.15)에 의거 자본시장 Open API 플랫폼 서비스 제공</li>
                             <li>Open API 플랫폼 이용(예정)사 다수가 시세 이용을 필요로 하나, 스타트업의 특성상 비용부담 여력이 부족하여 시세 이용에 난항</li>
                        </ul>
                        <p class="info_tit2"> 미래 정보수익 기반 확대</p>
                        <ul class="list_type01 type2">
                             <li>금융권 스타트업 비즈니스 활성화 → 정보 이용수요 확대(가입자 수 증가) → 정보사업 수익 증가의 선순환 구조 유도</li>
                        </ul>
                         <p class="info_tit2"> 음성적 시세 이용 억제 및 적법한 계약후 이용 관행 정착</p>
                        <ul class="list_type01 type2">
                             <li>상당수 스타트업 기업들은 초기 자본 부족 및 수익모델의 불확실성이 높아 음성적으로 시세정보를 조달하여 이용하려는 경향</li>
                        </ul>                       

                        <p class="info_tit">지원 방안</p>
                        <p class="info_tit2">지원대상 업체 : 아래 2요건을 충족하는 기업</p>
                        <ul class="list_type01 type2">
                             <p class="info_txt">(1) 설립 7년* 이내 ‘중소기업’<br>
                             - (중소기업 인정 기준) 중소기업기본법 제2조 제1항 및 시행령 제3조 제1항에 따른 요건을 모두 갖춘 기업<br>
                             * 7년 기준 : 중소기업창업 지원법상 ‘창업자’ 요건(제2조 제2호)을 적용</p>
                             <p class="info_txt">(2) 금융위원회 기본계획(‘15.7.15)에 의거 구축․운영되는 자본시장 공동 오픈플랫폼을 통해 시세정보를 수신․이용하는 업체</p>
                        </ul>
                        <p class="info_tit2">지원내용</p>
                        <ul class="list_type01 type2">
                             <p class="info_txt">(1) 지원 대상<br>
                             - 실시간정보 : 외부제공용 라이선스 중 “소매사업 전용”<br>
                             * 일반용/방송사용 라이선스 및 웹사이트체결가 정액제는 지원대상 아님<br>
                             - 종가정보 : 외부제공용 라이선스</p>
                             <p class="info_txt">(2) 지원내용 : 정보상품별로 최대 12개월간 정보이용료 중 기본료 면제<br>
                             - 변동료는 면제 대상 아님</p>
                             <p class="info_txt">(3) 기본료 면제 종료조건 : 가입자(접속 ID) 수 1만명 초과시 (익월부터 기본료 청구)</p>                             
                        </ul>

                        <p class="info_tit2">행정절차</p>
                        <ul class="info_step2">
                            <li class="step1"><div><strong>이용사 신청<br>접수</strong><p>(핀테크 기업)</p></div></li>
                            <li class="step2"><div><strong>지원대상 요건 충족여부 확인</strong><p>(KOSCOM)</p></div></li>
                            <li class="step3"><div><strong>KRX 계약 사전 승인</strong><p>(KRX)</p></div></li>
                            <li class="step4"><div><strong>이용사와 정보<br>이용계약 체결</strong><p>(핀테크 기업, KOSCOM)</p></div></li>
                        </ul>
                        
                        <p class="info_tit2">신청 접수</p>
                        <ul class="list_type01 type2">
                            <li><a href="https://developers.koscom.co.kr/resources/documentation/OpenAPI_Platform_Reg.docx"><FONT COLOR=“#0000FF”>정보이용사전승인신청서 1 부</FONT></a></li>
                            <li> 서류 접수 (이메일 : <FONT COLOR=“#0000FF”>open@koscom.co.kr)</FONT></li>
                        </ul>
                        <br>
                        <ul class="info_step2">                        
                            <li><a href="https://developers.koscom.co.kr/resources/documentation/OpenAPI_Platform_Reg.docx"><FONT COLOR=“#0000FF”>정보이용사전승인신청서 <br>작성예 1페이지</FONT></a></li>                            
                            <li><a href="https://developers.koscom.co.kr/resources/documentation/OpenAPI_Platform_Reg.docx"><FONT COLOR=“#0000FF”>정보이용사전승인신청서 <br>2페이지</FONT></a></li>
                            <li><a href="https://developers.koscom.co.kr/resources/documentation/OpenAPI_Platform_Reg.docx"><FONT COLOR=“#0000FF”>정보이용사전승인신청서 <br>3페이지</FONT></a></li>
                        </ul>                            
                                                    
                        <p class="info_tit2">문의처</p>
                        <ul class="list_type01 type2">
                             <li>코스콤 핀테크 테스트베드 센터 : (02) 767-8573</li>
                             <li>핀테크 지원센터 - 판교 : (031) 8016-1169 (코스콤 담당자)</li>
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