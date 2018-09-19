<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : asumAcntIsu.jsp
 * @Description : [핀테크서비스신청:정보제공 동의]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.04  이희태        최초  생성
 * </pre>
 *
 * @author 포털 이희태
 * @since 2017.03.04
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="<c:url value='/js/cmm/jquery.printArea.js'/>"></script>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

var customerRegNo = '${customerRegNo}';
var strCallBack='${paramVO.callBakFunc}';
var arsStep='${paramVO.arsStep}';
var rsCd;
var rsCdMsg;

var arsRsltCd = '0001';
var arsRsltMsg;
var arsTrCd;
var arsTxtNo;
var arsRecordData;

/*******************************************
 * 기능 함수
 *******************************************/
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
/* ARS응답결과 openr전송 함수 */
function fn_openerCallBackSend(){
    window.parent.eval(strCallBack)(rsCd, rsCdMsg);
    gfn_closeModal(this.event);
}

/* ARS 인증전화요청 */
function arsCall(){
    var objParam = new Object();
    objParam.customerRegNo  = customerRegNo;
    objParam.customerPhone    = "${paramVO.customerPhone}";
    objParam.customerName    = "${paramVO.customerName}";
    objParam.subcompanyName    = "${paramVO.subcompanyName}";
    objParam.rsPubcompanyList    = "${paramVO.rsPubcompanyList}";

    var url = "<c:url value='/cmm/ars/arsRequest.ajax'/>";
    $.ajax({
         type    : "POST"
        ,url     : url
        ,data    : objParam
        ,async   : true
        ,beforeSend : function(){
            //로딩 호출
            gfn_setLoading(true, "ARS 인증진행 요청 중 입니다.");
            fn_initStep(2);
            gfn_setLoading(false);
        }
        ,success : function(data){
            arsRsltCd = data.arsRsltCd;
            arsRsltMsg = data.arsRsltMsg;
            arsTrCd = data.arsTrCd;
            arsTxtNo = data.arsTxtNo;
            arsRecordData = data.arsRecordData;
            fn_initStep(3);
        }
        ,error   : function(data){
            fn_initStep(3);
        }
    });
}

/* ARS 인증 */
function arssign(){
    //로딩 호출
    gfn_setLoading(true, "ARS 인증 결과 처리 중 입니다.");

    var objParam = new Object();
    objParam.customerRegNo  = customerRegNo;
    objParam.customerId  = '${customerId}';
    objParam.termsRegNo     = "${paramVO.termsRegNo}";
    objParam.termsStartDate = "${paramVO.termsStartDate}";
    objParam.termsPolicy    = "${paramVO.termsPolicy}";
    objParam.customerPhone    = "${paramVO.customerPhone}";

    objParam.arsRsltCd = arsRsltCd;
    objParam.arsRsltMsg = arsRsltMsg;
    objParam.arsTrCd = arsTrCd;
    objParam.arsTxtNo = arsTxtNo;
    objParam.arsRecordData = arsRecordData;

    //메일발송용 정보
    objParam.customerName    = "${paramVO.customerName}";
    objParam.customerEmail   = "${paramVO.customerEmail}";

    var url = "<c:url value='/cmm/ars/reqTerms.ajax'/>";
    $.ajax({
        type    : "POST"
        ,url     : url
        ,data    : objParam
        ,async   : false
        ,success : function(data){
            //로딩 호출
            gfn_setLoading(false);

            if(util_chkReturn(data.rsCd, "s") != ""){
                rsCd = data.rsCd;
            }else{
                rsCd  = '01';
            }

            if(util_chkReturn(data.rsCdMsg, "s") != ""){
                rsCdMsg = data.rsCdMsg;
            }else{
                rsCdMsg = 'ARS서버단에서 응답결과메세지 또는 응답결과가 없습니다.';
            }
        }
        ,error   : function(data){
            //로딩 호출
            gfn_setLoading(false);

            if(util_chkReturn(data.rsCd, "s") != ""){
                rsCd = data.rsCd;
             }else{
                 rsCd  = '01';
             }

            if(util_chkReturn(data.rsCdMsg, "s") != ""){
                rsCdMsg = data.rsCdMsg;
            }else{
                rsCdMsg = 'ARS작업중 문제가 생겼습니다.';
            }
        }
    });

    //부모창콜백처리
    window.parent.eval(strCallBack)(rsCd, rsCdMsg);
    $(window.parent).focus();
    window.close();
}
/*******************************************
 * 이벤트 함수
 *******************************************/

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
}

/* ARS 인증 진행 절차  */
function fn_initStep(idx){
    arsStep = idx;
    //인증 전화 요청
    if(arsStep == '1'){
        $('.stpe1').removeClass('pass');
        $('.stpe1').addClass('on');
        $('.stpe2').removeClass('pass');
        $('.stpe3').removeClass('on');
        $('.stpe1_div').css( "visibility", "visible" );
        $('.stpe3_div_s').css( "visibility", "hidden" );
        $('.stpe3_div_f').css( "visibility", "hidden" );
    }else if(arsStep == '2'){
        //인증 진행
        $('.stpe1').removeClass('on');
        $('.stpe1').addClass('pass');
        $('.stpe2').addClass('on');
        $('.stpe1_div').css( "visibility", "hidden" );
        $('.stpe2_div').css( "visibility", "visible" );
    }else{
        //인증 결과
        $('.stpe1').removeClass('on');
        $('.stpe2').removeClass('on');
        $('.stpe2').addClass('pass');
        $('.stpe3').addClass('on');
        $('.stpe1_div').css( "visibility", "hidden" );
        $('.stpe2_div').css( "visibility", "hidden" );
        if(arsRsltCd == '0000' ){
            //인증성공
            $('.stpe3_div_s').css( "visibility", "visible" );
        }else{
            //인증실패
            $('.stpe3_div_f').css( "visibility", "visible" );
        }
    }
}

/* ARS 전화인증 완료 버튼  */
function fn_arsConfirm(){
    if(arsStep != '3'){
        alert('ARS 인증 진행 중입니다.\n인증을 완료하신 후 ‘전화 인증 완료’\n버튼을 클릭하시기 바랍니다');
    }else{
        fn_initStep(3);
    }
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        $(".wrap >").remove();
        if(opener){
            window.opener.fn_login();
            window.close();
        }else{
        	window.parent.fn_login();
        	gfn_closeModal();
        }
    </c:if>
    
    //인증 진행 절차
    fn_initStep(arsStep);
});

</script>
</head>
<body>
<div class="wrap">

    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev type2">
        <div class="dimm" style="display:none;"></div>

        <!-- #layer01 -->
        <div class="layer_box" id="layer01" style="width:500px;";>
            <div class="layer_tit">ARS 인증</div>

            <div class="layer_con">

                <!-- banking_agree_area -->
                <div class="banking_agree_area">

                    <h1 class="logo"><a href="#none"><img src="/images/spt/common/logo.png" alt="Koscom OpenAPI Platform"></a></h1>

                    <!-- 인증 전화 요청 -->
                    <div class="step_area mt20">
                        <ul>
                            <li class="stpe1"><div>인증 전화 요청</div></li><!-- 현재step -->
                            <li class="stpe2"><div>인증진행</div></li>
                            <li class="stpe3"><div>인증결과</div></li>
                        </ul>
                    </div>
                    <div class="con_wrap stpe1_div" style="position: absolute">
                        <div class="request" style="width: 415px">
                            <p class="phone">휴대폰 번호 : <c:out value='${paramVO.incodingCustomerPhone}'/></p>
                            <div class="btn_area">
                                <a href="javascript:void(0);" class="btn_type5" onclick="javascript:arsCall();">인증 전화 요청</a>
                            </div>
                        </div>
                        <ul class="list_type01 mt10">
                            <li>회원정보에 등록된 휴대폰 번호로 ARS 인증을 진행합니다.</li>
                            <li>발신번호(02-767-7114)가 휴대폰에서 "수신차단등록"된 경우 인증전화를 <br>받을 수 없습니다. 인증 요청을 받지 못하는 경우 "수신차단등록"을 해제하여 <br>주시기 바랍니다. </li>
                        </ul>
                    </div>
                    <!--// 인증 전화 요청 -->

                    <div class="con_wrap stpe2_div" style="visibility: hidden; position: absolute">
                        <div class="request" style="width: 415px">
                            <p class="phone">ARS 인증 진행 중입니다.</p>
                            <div class="btn_area">
                                <a href="javascript:void(0);" class="btn_type5 type2" onclick="javascript:fn_popupClose();">취소</a>
                                <%--<a href="javascript:void(0);" class="btn_type5" onclick="javascript:fn_arsConfirm();">전화 인증 완료</a>--%>
                            </div>
                        </div>
                        <ul class="list_type01 mt10">
                            <li>인증 진행 중 전화가 끊어졌을 경우 인증 창을 닫고 다시 시도해주시기 바랍니다.</li>
                            <li>전화가 오지 않는 경우 휴대폰 번호가 다르거나 착신이 정지되어 있는 것은 아닌지 <br>확인하시기 바랍니다.</li>
                            <li>착신 전환된 전화번호로는 ARS 인증이 불가하오니 유의하시기 바랍니다.</li>
                        </ul>
                    </div>
                    <!--// 인증진행 -->

                    <div class="con_wrap stpe3_div_s" style="visibility: hidden; position: absolute">
                        <div class="confirm" style="width: 450px">
                            <p class="complete"><strong>ARS 인증 완료</strong>되었습니다.</p>
                            <div class="btn_area">
                                <a href="javascript:void(0);" class="btn_type5" onclick="javascript:arssign();">확인</a>
                            </div>
                        </div>
                    </div>
                    <!--// 인증 완료시 인증결과 -->

                    <div class="con_wrap stpe3_div_f" style="visibility: hidden; position: absolute">
                        <div class="confirm" style="width: 450px">
                            <p class="fail"><strong>ARS 인증 실패</strong>하였습니다.</p>
                            <div class="btn_area">
                                <a href="javascript:void(0);" class="btn_type5" onclick="javascript:fn_initStep(1);">재시도</a>
                                <a href="javascript:void(0);" class="btn_type5 type2" onclick="javascript:fn_popupClose();">닫기</a>
                            </div>
                        </div>
                        <ul class="list_type01 mt10">
                            <li>확인하신 후, ARS 인증을 다시 시도하여 주시기 바랍니다. </li>
                        </ul>
                    </div>
                    <!--// 인증 실패시 인증결과 -->
                </div>
            </div>

            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>
    <!-- // layer_popup_dev -->
    
</div>

</body>
</html>