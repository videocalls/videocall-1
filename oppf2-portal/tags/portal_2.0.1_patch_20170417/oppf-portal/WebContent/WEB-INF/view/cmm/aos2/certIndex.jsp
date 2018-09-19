<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : certIndex.jsp
 * @Description : [공통 보안3종(PC방화벽,키보드보안,백신) 보안 프로그램 설치 안내]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.21  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.06.21
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<OBJECT classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codeBase="http://www.signkorea.com/SKCommAX.cab#version=9,9,1,9" id="CertManX" width="0" height="0"></OBJECT>
<!-- <object classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codebase="http://www.signkorea.com/SKCommAX.cab#version=9,9,0,5" id="CertManX" width="1" height="1"></object> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/json3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/iecompatibility.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/vestsign.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/activexAdp.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>

<script type="text/javascript">
/*******************************************
 * 전역 변수
 *******************************************/
var setCheckSuccessText = '<strong class="point02">설치완료</strong>';
var setCheckFailText = '<strong>미설치</strong>';
var setCheckFailText1 = '<a id="aAhnDown" class="download" href="http://safetx.ahnlab.com/master/win/default/all/astx_setup.exe" title="암호화 프로그램 설치 링크">다운로드</a>';
var setCheckFailText2 = '<a id="aSignDown" class="download" href="<c:url value='/js/cmm/SKCertService/SKCertServiceSetup_v2.1.8_r2694.exe'/>" title="공인인증 프로그램 설치 링크">다운로드</a>';



/*******************************************
 * 기능 함수
 *******************************************/
/* 자동 다운로드 함수 */
function fn_autoDownload(){
    $ASTX2.init(
        function onSuccess(){
        	$("#tdStatus1").html(setCheckSuccessText);
        }, 
        function onFailure(){
        	location.href="http://safetx.ahnlab.com/master/win/default/all/astx_setup.exe";
        }
    );
    
    //공인인증서
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }
//     if(CertManX.doubleClickBlock(arguments.callee)){
//         return;
//     }
    CertManX.SetInfoPage(1);    // 1로 세팅시 CertManX.CertServiceSetup실행 후 errorCode 로직 실행됨.

    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
            	location.href="<c:url value='/js/cmm/SKCertService/SKCertServiceSetup_v2.1.8_r2694.exe'/>";
            }
            if(errorCode == 90001 || errorCode == 90002){
                location.href="<c:url value='/js/cmm/SKCertService/SKCertServiceSetup_v2.1.8_r2694.exe'/>";
            }else{
//                 alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                location.href="<c:url value='/js/cmm/SKCertService/SKCertServiceSetup_v2.1.8_r2694.exe'/>";
            }
        }else{
        	$("#tdStatus2").html(setCheckSuccessText);
        }
    });
    
}

/* [안랩]설치여부 체크함수 */
function fn_setCheck1(){
    $ASTX2.init(
        function onSuccess(){
            $("#tdStatus1").html(setCheckSuccessText);
        }, 
        function onFailure(){
        	$("#tdStatus1").html(setCheckFailText+setCheckFailText1);
        }
    );
}

/* [공인인증서] 설치여부 체크함수 */
function fn_setCheck2(){
    //공인인증서
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                $("#tdStatus2").html(setCheckFailText+setCheckFailText2);
            }
            if(errorCode == 90001 || errorCode == 90002){
                $("#tdStatus2").html(setCheckFailText+setCheckFailText2);
            }
            else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                $("#tdStatus2").html(setCheckFailText+setCheckFailText2);
            }
        }else{
        	$("#tdStatus2").html(setCheckSuccessText);
        }
    });
}


/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/



/*******************************************
 * 이벤트 함수
 *******************************************/

/* 화면 로드 처리 */
$(document).ready(function(){

    /* [새로고침]버튼 이벤트발생 시 호출 */
    $("#btnRefresh").bind("click", function(e){
        window.document.location.reload();
    });
    
    /* [메인화면으로이동]버튼 이벤트발생 시 호출 */
    $("#btnMoveMainPage").bind("click", function(e){
        util_movePage("<c:url value='/spt/cmm/mainView.do'/>");
    });
    
    fn_autoDownload();

});


</script>
</head>
<body>

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
        <div class="container">
            <div class="certi_wrap">
                <p class="certi_tit">보안 프로그램 설치 안내</p>
                <p class="certi_txt">안전한 <spring:message code='Globals.domain.${ SYSTEM_KIND }.name' /> 서비스 이용을 위하여 최신 보안 프로그램을 설치 중입니다.<br><!-- 2016-06-24 전체명칭수정 -->
                <strong class="point02">설치가 정상적으로 완료 된 후 “메인화면으로 이동” 버튼</strong>을 클릭하면 <spring:message code='Globals.domain.${ SYSTEM_KIND }.name' /><br>웹사이트로 이동됩니다.</p><!-- 2016-06-24 전체명칭수정 -->

                <ol class="certi_list">
                    <li><strong class="point02">1.</strong>설치여부를 묻는 <span class="point02">보안경고창이 나타나면 반드시 [실행]</span>을 선택하여 주시기 바랍니다.</li>
                    <li><strong class="point02">2.</strong>암호화 프로그램 및 공인인증 프로그램이 ‘설치완료’되어야 정상적으로 서비스 이용이 가능합니다.</li>
                    <li><strong class="point02">3.</strong>보안 프로그램 설치는 PC환경에 따라 <span class="point02">약 2~3분이 소요</span> 됩니다.</li>
                </ol>

                <table class="tbtype_list2 type3">
                    <caption>프로그램 명, 기능안내, 설치상태</caption>
                    <colgroup>
                        <col style="width:30%;">
                        <col style="">
                        <col style="width:13%;">                        
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">프로그램 명</th>
                            <th scope="col">기능안내</th>
                            <th scope="col">설치상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="<c:url value='/images/spt/common/icon_certi_program.png'/>" alt="">
                                <p class="p_name">암호화 프로그램<br>(AhnLab Safe Transaction)</p>
                            </td>
                            <td class="left">온라인 백신, 개인 PC 방화벽 그리고 입력 정보 유출을 원천 방어하기 위한 키보드 보안 통합 프로그램 입니다.</td>
                            <td id="tdStatus1">
                                <strong>미설치</strong>
<!--                                         <a class="download" href="http://safetx.ahnlab.com/master/win/default_nonstop/all/astx_setup_nonstop.exe" title="암호화 프로그램 설치 링크"> -->
                                <a id="aAhnDown" class="download" href="http://safetx.ahnlab.com/master/win/default/all/astx_setup.exe" title="암호화 프로그램 설치 링크">
                                                                            다운로드
                                </a>
<!--                                         <strong class="point02">설치완료</strong> -->
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<c:url value='/images/spt/common/icon_certi_program.png'/>" alt="">
                                <p class="p_name">공인인증 프로그램<br>(SignKorea Certification<br>Toolkit)</p>
                            </td>
                            <td class="left">공인인증서 로그인과 거래내역에 대한 전자서명을 위한 프로그램입니다.</td>
                            <td id="tdStatus2">
                                <strong>미설치</strong>
<!--                                         <a class="download" href="http://kiwoom-dn-cdn.daouidc.com/SKCertServiceSetup_v2.1.4_r2634.exe" title="공인인증 프로그램 설치 링크"> -->
                                <a id="aSignDown" class="download" href="<c:url value='/js/cmm/SKCertService/SKCertServiceSetup_v2.1.8_r2694.exe'/>" title="공인인증 프로그램 설치 링크">
                                                                            다운로드
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="btn_area">
                    <a href="javascript:void(0);" class="btn_type2" id="btnRefresh">새로고침</a>
                    <a href="javascript:void(0);" class="btn_type1" id="btnMoveMainPage">메인화면으로 이동</a>
                </div>

                <div class="info_box">
                    <div class="tit"><p class="icon_tip">유의사항</p></div>
                    <div class="txt">
                        <ul class="list_type01">
                            <li>지원 가능한 브라우저는 익스플로러 8 이상입니다. 그 이하 브라우저는 익스플로러 8 이상으로 업그레이드해 주시기 바랍니다.</li>
                            <li>사용하는 PC의 OS가 윈도우 XP인 경우 서비스팩3 이상, 비스타인 경우 서비스팩1 이상이 설치되어 있어야 하며, 그 이하인 경우 윈도우 업그레이드가 필요합니다.</li>
                            <li>설치 장애 시 고객센터(02-767-7114)로 문의 주시기 바랍니다.</li>
                        </ul>
                    </div>
                </div>      
            </div>

        </div>
    
    </section>
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
    
</div>

</body>
</html>