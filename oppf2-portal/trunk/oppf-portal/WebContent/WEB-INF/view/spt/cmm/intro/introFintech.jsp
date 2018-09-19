<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introFintech.jsp
 * @Description : [Intro > 핀테크오픈플랫폼 소개] 조회 (개인용)
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
                <li><a href="javascript:void(0);">금융투자 핀테크 포털</a></li>
                <li class="on">금융투자 핀테크 포털 소개</li>
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
                    <h3>금융투자 핀테크 포털 소개</h3><!-- 2016-06-24 전체명칭수정 -->
                </div>

                <!-- con -->
                <div class="con">
                    <!-- 2016-06-29 수정 -->
                    <div class="info_wrap">
                        <p class="info_txt mt20">
                            기존 금융회사가 제공하는 금융서비스 외에 핀테크가  금융서비스를 지금보다 편리하고 혁신적으로 변화시키고 있습니다. 금융투자 핀테크 포털은 금융회사가 보관 중인 개인의 금융거래정보를 안전하고, 편리하게 핀테크 기업으로 전달하여 똑똑한 금융생활을 돕는 다양한 핀테크 서비스가 가능한 환경을 제공하고 있습니다.
                        </p>

                        <div class="info_img">
                            <p><img src="<c:url value="/images/spt/info/img_info01.jpg"/>" alt="금융투자 핀테크 포털의 금융투자회사와 핀테크서비스 양방향 서비스를 통한 제공(이미지)"></p>
                        </div>

                        <p class="info_txt">
                            금융투자 핀테크 포털은 다음과 같은 핀테크 서비스 제공을 위해 핀테크 스타트업과 함께 노력하고 있으며, 새로운 핀테크 서비스 출시와 관련된 소식이 있으면 알려드리겠습니다.
                        </p>

                        <ul class="info_platform_note"> 
                            <li>
                                <div>
                                    <div class="icon">
                                        <img src="<c:url value="/images/spt/info/icon_platform_note01.png"/>" alt="">
                                    </div>  
                                    <dl>
                                        <dt>빠르고 간편하게 금융자산을 통합하여 조회하고 관리할 수 있는 핀테크 서비스</dt>
                                        <dd>가계부앱, 통합자산관리앱 등</dd>
                                    </dl>                                   
                                </div>
                            </li>
                            <li>
                                <div>
                                    <div class="icon"><img src="<c:url value="/images/spt/info/icon_platform_note02.png"/>" alt=""></div>
                                    <dl>
                                        <dt>기존 투자자산과 거래내역을 분석하여 재산증식을 돕는 핀테크 서비스</dt>
                                        <dd>온라인자산자문 및 로보어드바이저 서비스, 투자정보 및 금융뉴스 알림 서비스 등</dd>
                                    </dl>
                                </div>
                            </li>
                            <li>
                                <div>
                                    <div class="icon"><img src="<c:url value="/images/spt/info/icon_platform_note03.png"/>" alt=""></div>
                                    <dl>
                                        <dt>금융투자상품의 비교 및 추천 핀테크 서비스</dt>
                                        <dd>금융회사 별 상품비교 및 추천, 신상품 출시 안내</dd>
                                    </dl>
                                </div>
                            </li>
                        </ul>

                        <p class="info_txt">금융투자 핀테크 포털은 각 단계 별 성장 로드맵에 맞춰 필요한 데이터와 서비스를 제공할 예정입니다.</p><!-- 2016-06-24 전체명칭수정 -->

                        <div class="roadmap_box type1">
                            <p class="tit"><span>1세대</span> 금융 데이터 통합 조회 서비스 기반 구축</p>
                            <ul>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-1.png"/>" alt="">
                                    <p>은행권계좌<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-2.png"/>" alt="">
                                    <p>금투권계좌<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-3.png"/>" alt="">
                                    <p>여신권카드<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-4.png"/>" alt="">
                                    <p>보험권계약<br>통합서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-5.png"/>" alt="">
                                    <p>부동산정보<br>서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_1-6.png"/>" alt="">
                                    <p>개인연금<br>정보서비스</p>
                                </li>
                            </ul>
                        </div>

                        <div class="roadmap_box type2">
                            <p class="tit"><span>2세대</span> 조회 서비스를 기반으로 하는 부가 서비스</p>
                            <ul>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-1.png"/>" alt="">
                                    <p>금융뉴스<br>제공서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-2.png"/>" alt="">
                                    <p>개인금융정보<br>통합조회 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-3.png"/>" alt="">
                                    <p>초보적 투자<br>자문서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-4.png"/>" alt="">
                                    <p>투자자산<br>추천서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-5.png"/>" alt="">
                                    <p>투자도구<br>제공서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-6.png"/>" alt="">
                                    <p>온라인펀드<br>비교서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_2-7.png"/>" alt="">
                                    <p>분석정보<br>제공서비스</p>
                                </li>
                            </ul>
                        </div>

                        <div class="roadmap_box type3">
                            <p class="tit"><span>3세대</span> 금융회사와 핀테크 기업 간 융합 One-Stop 서비스</p>
                            <ul>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-1.png"/>" alt="">
                                    <p>개인종합자산<br>관리서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-2.png"/>" alt="">
                                    <p>독립 투자자문업자<br>솔루션</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-3.png"/>" alt="">
                                    <p>로보 어드<br>바이저 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-4.png"/>" alt="">
                                    <p>온라인펀드<br>비교판매 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-5.png"/>" alt="">
                                    <p>온라인펀드<br>추천판매 서비스</p>
                                </li>
                                <li>
                                    <img src="<c:url value="/images/spt/info/icon_roadmap_3-6.png"/>" alt="">
                                    <p>Retail 알고리즘<br>트레이딩 서비스</p>
                                </li>
                            </ul>
                        </div>

                        <p class="info_tit">금융투자 핀테크 포털 이용 방법</p>
                        <ul class="info_step">
                            <li><div>
                            			<a href="javascript:util_movePage('<c:url value='/usr/mbrReg/mbrNewReg.do'/>');">
                            				<img src="<c:url value="/images/spt/info/icon_info_step1.png"/>" alt=""><p>회원가입</p>
                            			</a>
							</div></li>
                            <li><div>
                            			<a href="javascript:util_movePage('<c:url value="/myp/cert/cert.do"/>');">
                            				<img src="<c:url value="/images/spt/info/icon_info_step2.png"/>" alt=""><p>인증서등록/<br>정보제공동의</p>
                            			</a>	
               				</div></li>
                            <li><div>
                            			<a href="javascript:util_movePage('<c:url value="/myp/asumAcnt/asumAcnt.do"/>');">
                            				<img src="<c:url value="/images/spt/info/icon_info_step3.png"/>" alt=""><p>가상계좌<br>발급/연결</p>
                            				</a>
                            </div></li>
                            <li><div>
                            			<a href="javascript:util_movePage('<c:url value="/usr/svcAppl/appList.do"/>');">
                            				<img src="<c:url value="/images/spt/info/icon_info_step4.png"/>" alt=""><p>핀테크앱 선택</p>
                            			</a>	
                            </div></li>
                            <li><div>
                            			<a href="javascript:util_movePage('<c:url value="/usr/svcAppl/appList.do"/>');">
                            				<img src="<c:url value="/images/spt/info/icon_info_step5.png"/>" alt=""><p>핀테크앱 이용</p>
                            			</a>	
                            </div></li>
                        </ul>

                        <p class="info_tit2">회원가입</p>
                        <ul class="list_type01 type2">
                            <li>휴대폰 인증과 간단한 기본 정보 입력으로 회원가입 절차가 완료됩니다.</li>
                            <li>휴대폰 인증 시 반드시 <span class="point01">본인 명의 휴대폰 번호</span>가 있어야 합니다. </li>
                        </ul>

                        <p class="info_tit2">공인인증서 등록 및 정보제공동의</p>
                        <ul class="list_type01 type2">
                            <li>안전한 핀테크 서비스 이용을 위해 공인인증서 등록 및 보유 계좌 조회를 위한 금융거래정보 제공 동의가 필요합니다.
                                <p class="mt10"><strong class="point01">[필수준비사항] 증권거래용 공인인증서 (범용 또는 증권전용 용도제한용)</strong><br>
                                    회원의 주민등록번호로 발급된 개인용 공인인증서(범용 또는 증권전용 용도제한용) 외에 은행, 카드사용 공인인증서 등은<br>사용 불가합니다.
                                </p>
                            </li>
                        </ul>

                        <p class="info_tit2">가상계좌 발급 및 연결</p>
                        <ul class="list_type01 type2">
                            <li>금융투자회사(증권사)의 계좌번호를 대신하여 핀테크 서비스에 사용할 가상계좌번호를 발급받습니다.</li>
                            <li>등록된 계좌 정보 중 핀테크 앱에 연결할 가상계좌번호를 선택합니다.</li>
                            <li>
                                <p class="mt10"><strong class="point01">[필수준비사항] 증권 계좌</strong><br>
                                    핀테크 앱에 연결할 증권 계좌가 필요합니다. 계좌가 없을 경우 증권사 가입 후 계좌를 발급받으셔야 합니다.</p>
                            </li>
                        </ul>

                        <p class="info_tit2">핀테크 앱 선택</p>
                        <ul class="list_type01 type2">
                            <li>서비스 관리&gt;앱사용신청 메뉴를 클릭하면 신청 가능한 핀테크 앱 목록이 조회됩니다.</li>
                            <li>사용할 핀테크 앱을 선택하여 ‘사용 신청‘ 버튼을 클릭합니다. </li>
                            <li>선택된 핀테크 기업(핀테크 앱 개발사)에 금융거래정보 제공을 위한 동의가 필요합니다. <br>동의는 전자서명(공인인증서 인증), ARS 인증 중 선택 가능합니다.</li>
                            
                        </ul>

                        <p class="info_tit2">핀테크 앱 이용</p>
                        <ul class="list_type01 type2">
                            <li>핀테크 서비스 최초 연결 시 인증을 통해 핀테크 플랫폼 연결을 위한 인증값을 발급받게 됩니다.</li>
                            <li>인증 후 유효기간이 만료되기 전까지 서비스 이용이 가능합니다. </li>
                        </ul>

                        <div class="btn_area m_hide mt50">
                            <a href="javascript:util_movePage('<c:url value='/usr/mbrReg/mbrNewReg.do'/>');" class="btn_type1 type2">회원 가입</a>
                        </div>
                    </div>  
                    <!-- // 2016-06-29 수정 -->
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