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
                    <h3>오픈플랫폼 소개</h3>
                </div>

                <!-- con -->
                <div class="con">
                    <div class="info_wrap">
                        <p class="info_tit">자본시장 공동 핀테크 오픈플랫폼</p>
                        <p class="info_txt">
                            자본시장 공동 핀테크 오픈플랫폼(오픈플랫폼)은 금융회사, 유관기관, 핀테크 기업 및 기타 기업의 데이터와 서비스를 API로 게시하고, 상호 융합을 통해 경제적인 방식으로 혁신적 비즈니스를 만들 수 있는 기술기반입니다.
                        </p>
                        <p class="info_txt">
                        오픈플랫폼은 다수의 참여자 간 상호 교류를 위해 상호 양방향 서비스를 지향합니다.<br>
                        API서비스의 공급자로서, 또한 API 서비스의 사용자로서 자유롭게 오픈플랫폼에 참여하여 새로운 금융서비스를 창출할 수 
                        있는 기회를 경험하시기 바랍니다.
                        </p>

                        <div class="info_img">
                            <p class="m_hide"><img src="<c:url value='/images/cpt/info/img_info01.jpg'/>" alt="핀테크기업, 자본시장오픈플랫폼, 금투사, 공공/금융기관 등의 양방향 서비스"></p>
                            <p class="m_show"><img src="<c:url value='/images/cpt/info/img_info_m01.jpg'/>" alt="핀테크기업, 자본시장오픈플랫폼, 금투사, 공공/금융기관 등의 양방향 서비스"></p>
                        </div>

                        <p class="info_tit">플랫폼 특징</p>

                        <ul class="info_platform_note"> 
                            <li>
                                <div>
                                    <div class="icon">
                                        <p>Open API</p><img src="<c:url value='/images/cpt/info/icon_platform_note01.png'/>" alt=""><p>JSON</p>
                                    </div>  
                                    <dl>
                                        <dt>플랫폼 참여 플레이어 간의 효율적 연결을 위해 연동 방식 및 데이터의 표준을 수립하여 구축</dt>
                                        <dd>연동방식: 웹/모바일 업계 표준인 OpenAPI</dd>
                                        <dd>데이터 표현방식은 JSON(JavaScript Object Notation)</dd>
                                    </dl>                                   
                                </div>
                            </li>
                            <li>
                                <div>
                                    <div class="icon"><img src="<c:url value='/images/cpt/info/icon_platform_note02.png'/>" alt=""></div>
                                    <dl>
                                        <dt>금융회사와 핀테크 스타트업이 자발적 참여를 통해 데이터와 서비스를 표준화함</dt>
                                        <dd>금융회사,핀테크기업,오픈플랫폼 운영사가 상호 협력하여 데이터,서비스 개방 범위 및 표준화 진행</dd>
                                        <dd>가상계좌관리, 인증, 핀테크기업과 금융회사간 서비스 연계 처리를 플랫폼이 제공</dd>
                                    </dl>
                                </div>
                            </li>
                            <li>
                                <div>
                                    <div class="icon"><p>DATA</p><img src="<c:url value='/images/cpt/info/icon_platform_note03.png'/>" alt=""></div>
                                    <dl>
                                        <dt>금융권 공통 플랫폼 위에 표준화된 데이터 서비스를 공유하고 개방</dt>
                                        <dd>탐색 및 구축비용의 최소화와 네트워크 효과 극대화 지향</dd>
                                        <dd>핀테크기업에 한정하지 않고, 자본시장 성장에 기여 가능한 모든 파트너의 참여 가능</dd>
                                    </dl>
                                </div>
                            </li>
                        </ul>

                        <p class="info_tit">자본시장 공동 핀테크 오픈플랫폼 확장 RoadMap</p>
                        <p class="info_txt">오픈플랫폼은 각 단계별 성장 로드맵에 맞추어 필요한 데이터와 서비스를 제공할 예정입니다.</p>

                        <div class="roadmap_box type1">
                            <p class="tit"><span>1세대</span> 금융 데이터 통합 조회 서비스 기반 구축</p>
                            <ul>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-1.png'/>" alt="">
                                    <p>은행권계좌<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-2.png'/>" alt="">
                                    <p>금투권계좌<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-3.png'/>" alt="">
                                    <p>여신권카드<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-4.png'/>" alt="">
                                    <p>보험권계약<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-5.png'/>" alt="">
                                    <p>부동산정보<br>서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_1-6.png'/>" alt="">
                                    <p>개인연금<br>정보서비스</p>
                                </li>
                            </ul>
                        </div>

                        <div class="roadmap_box type2">
                            <p class="tit"><span>2세대</span> 조회 서비스를 기반으로 하는 부가 서비스</p>
                            <ul>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-1.png'/>" alt="">
                                    <p>금융뉴스<br>제공서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-2.png'/>" alt="">
                                    <p>개인금융정보<br>통합조회 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-3.png'/>" alt="">
                                    <p>초보적 투자<br>자문서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-4.png'/>" alt="">
                                    <p>투자자산<br>추천서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-5.png'/>" alt="">
                                    <p>투자도구<br>제공서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-6.png'/>" alt="">
                                    <p>온라인펀드<br>비교서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_2-7.png'/>" alt="">
                                    <p>분석정보<br>제공서비스</p>
                                </li>
                            </ul>
                        </div>

                        <div class="roadmap_box type3">
                            <p class="tit"><span>3세대</span> 금융회사와 핀테크 기업 간 융합 One-Stop 서비스</p>
                            <ul>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-1.png'/>" alt="">
                                    <p>개인종합자산<br>관리서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-2.png'/>" alt="">
                                    <p>독립 투자자문업자<br>솔루션</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-3.png'/>" alt="">
                                    <p>로보 어드<br>바이저 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-4.png'/>" alt="">
                                    <p>온라인펀드<br>비교판매 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-5.png'/>" alt="">
                                    <p>온라인펀드<br>추천판매 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value='/images/cpt/info/icon_roadmap_3-6.png'/>" alt="">
                                    <p>Retail 알고리즘<br>트레이딩 서비스</p>
                                </li>
                            </ul>
                        </div>

                        <p class="info_txt mt50">또한 오픈플랫폼은 온라인자산관리, 투자자문서비스 등의 자산관리의 핵심 분야를 지원하는<br> 자본시장 인프라로써의 역할을 수행 할 수 있도록 성장시켜 나갈 예정입니다.</p>

                        <div class="info_img">
                            <p class="m_hide"><img src="<c:url value='/images/cpt/info/img_info02.jpg'/>" alt="종합자산관리(투자자)에 대한 하위 조직도"></p>
                            <p class="m_show"><img src="<c:url value='/images/cpt/info/img_info_m02.jpg'/>" alt="종합자산관리(투자자)에 대한 하위 조직도"></p>
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

</div>
</form:form>
</body>
</html>