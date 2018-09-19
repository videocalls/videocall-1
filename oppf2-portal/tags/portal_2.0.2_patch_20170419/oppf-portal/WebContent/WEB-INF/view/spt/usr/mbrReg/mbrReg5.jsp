<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg1.jsp
 * @Description : [회원가입:5.인증메일발송]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.28  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.04.28
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
 * ajax,ajax Callback 함수
 *******************************************/
 

/*******************************************
 * 이벤트 함수
 *******************************************/

/* 화면 로드 처리 */
$(document).ready(function(){
    
    /* [확인] 이벤트발생 시 호출 */
    $("#btnOk").bind("click", function(e){
        var objParam = new Object();
        util_movePage("<c:url value='/spt/cmm/loginView.do'/>",objParam);
    });
});
</script>
</head>
<body>

<input type="hidden" id="customerNameKor" name="customerNameKor" value="${resultVO.customerNameKor}" />
<input type="hidden" id="customerEmail" name="customerEmail" value="${resultVO.customerEmail}" />

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">회원가입</li>
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
                    <h3>회원가입</h3>                                    
                </div>
            
                <!-- con -->
                <div class="con">
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>본인인증</div></li><!-- 지나간step -->
                            <li class="pass"><div>공인인증서 등록</div></li><!-- 지나간step -->
                            <li class="pass"><div>약관동의</div></li><!-- 지나간step -->
                            <li class="pass"><div>개인정보 입력</div></li><!-- 지나간step -->
                            <li class="last on"><div>이메일 인증</div></li><!-- 현재step -->
                        </ul>
                    </div>
                    <p class="title_01">인증메일 발송</p>
                    
                    <input type="hidden" name="customerRegNo"  id="customerRegNo"  value="<c:out value="${resultVO.customerRegNo}" />" />
                    <%-- <input type="hidden" name="customerVerify" id="customerVerify" value="<c:out value="${paramVO.customerVerify}" />" /> --%>
            
                    <div class="member_area type2">
                        <div class="tit_info">
                            <img src="<c:url value='/images/spt/members/icon_tit_info04.png'/>" alt=""/>
                            <p><strong>인증메일이 발송되었습니다.</strong></p>
                        </div>
            
                        <p class="txt">
                            <strong>
                                <span class="point01">
                                    <c:out value='${resultVO.customerEmail}'/>
                                </span> 로 인증메일이 발송되었습니다.
                            </strong>
                            <br>이메일 인증 완료 시 회원가입이 완료됩니다.
                        </p>
                        
                    </div>
            
                    <div class="info_box">
                        <div class="tit"><p class="icon_tip">회원가입 인증 안내</p></div>
                        <div class="txt">
                            <ul class="list_type01">
                                <li>인증메일은 가입신청일로부터 <strong>7일 이내 확인</strong>해주세요.</li>
                                <li>7일 이후에도 인증이 완료되지 않은 경우 회원가입 요청 내용이 모두 삭제되어 재가입하셔야 합니다.</li>
                                <li>메일이 오지 않았을 경우, 스팸 메일함도 확인해주세요.</li>
                            </ul>
                        </div>
                    </div>
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnOk" class="btn_type1">확인</a>
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

</body>
</html>