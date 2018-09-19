<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg6.jsp
 * @Description : [회원가입:6.회원가입완료]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.07  유제량        최초  생성
 * </pre>
 *
 * @author 포털 유제량 
 * @since 2016.06.07
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/
 

/*******************************************
 * 이벤트 함수
 *******************************************/
 
/* 화면 로드 처리 */
$(document).ready(function(){    
    /* [확인] 버튼 클릭 시 호출 */
    $("#btnConfrm").click(function(){
    	//메인페이지로 이동
        fn_moveHttpUrl('/spt/cmm/mainView.do');
    });
    
    /* [핀테크 서비스 신청] 버튼 클릭 시 호출 */
    $("#btnFinSvcAppl, #btnFinSvcAppl2").click(function(){
    	fn_moveHttpsUrl('<c:url value="/usr/svcAppl/asumAcntIsu.do"/>');
    });
    
    if(util_chkReturn($.trim($('#customerRegNo').val()), "s") == ""){
    	//바탕 제거
        $("body").attr("style", "display:none");
        
        //바탕 제거
        $(".wrap").attr("style", "display:none");
        //로딩 제거
        gfn_setLoading(false);
        
        alert("잘못된 접근입니다.");
        
        //메인페이지로 이동
        fn_moveHttpUrl('/spt/cmm/mainView.do');
    }    
});

</script>
</head>
<body>

<input type="hidden" name="customerRegNo" id="customerRegNo" value="<c:out value='${resultVO.customerRegNo}'/>" />
<input type="hidden" name="customerNameKor" id="customerNameKor" value="<c:out value='${resultVO.customerNameKor}'/>" />
<input type="hidden" name="customerId" id="customerId" value="<c:out value='${resultVO.customerId}'/>" />
<input type="hidden" name="customerPhone" id="customerPhone" value="<c:out value='${resultVO.customerPhone}'/>" />
<input type="hidden" name="customerEmail" id="customerEmail" value="<c:out value='${resultVO.customerEmail}'/>" />

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="#none">홈</a></li>
                <li class="on">회원가입</li>
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
                    <h3>회원가입</h3>                                   
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="member_area type2">
                        <div class="tit_info">
                            <p>
                                <span>회원가입 완료</span>
                                <strong><c:out value='${resultVO.customerNameKor}'/></strong>님, 회원가입을 축하합니다.
                            </p>
                        </div>

                        <p class="txt">회원님은 금융투자 핀테크 포털의 개인 회원으로 가입완료 되셨습니다.<br>가입하신 회원님의 회원 정보를 확인해 주세요.</p>
                        
                    </div>

                    <p class="title_01">회원가입 내역</p>

                    <!-- tbtype_form -->
                    <table class="tbtype_form">
                        <caption>아이디, 휴대폰, 이메일 정보</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">아이디</th>
                            <td><strong class="point01"><c:out value='${resultVO.customerId}'/></strong></td>
                        </tr>
                        <tr>
                            <th scope="row">휴대폰</th>
                            <td><c:out value='${resultVO.customerPhone}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">이메일</th>
                            <td><c:out value='${resultVO.customerEmail}'/></td>
                        </tr>
                        </tbody>
                    </table>                    
                    <!-- // tbtype_form -->

                    <ul class="list_style_01">
                        <li>회원정보는 마이 페이지 메뉴에서 조회 및 수정이 가능합니다.</li>
                    </ul>

                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnConfrm" class="btn_type2">확인</a>
                        <a href="javascript:void(0);" id="btnFinSvcAppl2" class="btn_type1">핀테크 서비스 신청</a>
                    </div>

                    <div class="banner_area">
                        <dl>
                            <dt>핀테크 서비스를 시작하세요.</dt>
                            <dd>코스콤과 핀테크 기업이 제공하는 증권용 핀테크 서비스를 이용해 보세요.<br>
                            금융투자 핀테크 포털 회원이라면 오픈API를 활용한 다양한 증권용 핀테크 서비스를<br>
                            신청하실 수 있습니다.</dd>   
                            <dd class="link"><a href="javascript:void(0);" id="btnFinSvcAppl">핀테크 서비스 신청 <span>&gt;&gt;</span></a></dd>
                        </dl>                       
                        <img src="<c:url value='/images/spt/members/bg_banner01.jpg'/>" alt="">
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

</body>
</html>
