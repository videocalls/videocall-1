<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMbrSecesConfrm.jsp
 * @Description : 회원정보 확인 전 비밀번호 확인
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.14  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.14
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript" src="<c:url value='/AOS2/include.js'/>"></script>
<script language="javascript" type="text/javascript">
window.onload = function(){
    $_astxu.log('[onload] '+navigator.userAgent);
    $_astxu.log('[onload] platform='+navigator.platform+',browser='+$ASTX2_CONST.BROWSER_VER);
    
    $ASTX2.setOption({autofocus:true});
    
    checkInstallASTX2(
        function onSuccess() {
            doATX2CheckServer();
        }
    );
}

function doATX2CheckServer(){
    $ASTX2.checkServer(
        function onSuccess() {
            $_astxu.log('ASTX.checkServer() success');
            
            $ASTX2.initE2E();
            $_astxj.hideOverlay();
        }, 
        function onFailure() {
            $_astxu.log('ASTX.checkServer() failure: errno='+$ASTX2.getLastError());
        }
    );
}

var spw = '';
function onSubmit2(){
    
    $_astxu.log('[onSubmit2]');
    
    /*
    $ASTX2.getE2EData(
        document.form2,
    */
    $ASTX2.getE2EDataIDs(
//      ['cardno5','cardno6','cardno7','cardno8'],
        ['userPw'],
        function onSuccess(data) {
            //$_astxu.log('ASTX.getE2EData() success');
            
            jQuery.ajax({
                 url:'/AOS2/do_submit_ajax.jsp',
                 data: data,
                 type: "POST",
                 dataType: 'json',
                 success:function(json){
                     //$_astxu.log('[ajax] '+$_astxu.jsonQstr(json));
                     var data = $_astxu.jsonQstr(json)+"";
//                   alert(data);
                     var rsArr = data.split("&");
                     for(var i=0; i<rsArr.length; i++){
//                       alert('rsArr['+i+']:'+rsArr[i]);
                         var rsArr2 = rsArr[i].split("=");
                         if('userPw' == rsArr2[0]){
                             spw = util_setHtmlParsing(rsArr2[1]);
                         }
                     }
                     //로그인 처리
                     fn_SchConfrm();                 },
                 error:function(){
                     alert("허용되지 않는 특수문자가 들어갔습니다.");
                     return;
                 }
            });
            
        }, 
        function onFailure() {
            $_astxu.log('ASTX.getE2EData() failure: errno='+$ASTX2.getLastError());
        }
    );
    
}

var debuger = new myDebuger();
//debuger.write_output(document, true);
</script>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

/*******************************************
 * 기능 함수
 *******************************************/
//회원정보 비밀번호 입력전 키보드 보안값 확인
function fn_beforeSchConfrm(){
	var userPw = $("#userPw").val();
    
    //비밀번호 미 입력 시
    if(gfn_nvl(userPw) == ""){
        alert("비밀번호를 입력해 주세요.");
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("userPw");
        return;
    }
    
    onSubmit2();
}

//회원정보 비밀번호 입력 확인
function fn_SchConfrm(){
    var url = "<c:url value='/spt/myp/sptMyInfo/checkPw.ajax'/>";
    var reqData = {
       "customerId"   : $("#userId").val()
      ,"customerRegNo" : $("#customerRegNo").val()
      //,"customerPassword" : $("#userPw").val()
      ,"customerPassword" : spw
    };  
    
    util_ajaxPage(url, reqData, "fn_SchConfrm_callBack");
}
//회원정보 비밀번호 입력 확인 callback
function fn_SchConfrm_callBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    
    var checkPw = data.checkPw; 
    if(checkPw == "0"){
        alert("비밀번호가 일치하지 않습니다.");   
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("userPw");
        return;
    }else{
        fn_SecesAppl(); //탈퇴신청
    }
}

//탈퇴신청
function fn_SecesAppl(){    
    var url = "<c:url value='/spt/myp/sptMyInfo/updateSptMbrSeces.ajax'/>";
    var reqData = {
       "customerId"   : $("#userId").val(),
       "customerRegNo" : $("#customerRegNo").val(),
       "customerRegStatus" : "G005004"
    };  
    util_ajaxPage(url, reqData, "fn_SecesAppl_callBack");
}
//탈퇴신청 처리후 callback
function fn_SecesAppl_callBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    
    var rs = data.rs;   
    if(rs == "0"){
        alert("탈퇴신청에 실패하였습니다.\n관리자에게 문의해 주세요.");        
        return;
    }else{
        //성공
        alert("코스콤 오픈플랫폼 회원탈퇴 되었습니다.");
        fn_emailSend(data); //회원탈퇴이메일 발송 후 로그아웃처리 후 메인화면으로 보냅니다.
    }
}

/* [회원탈퇴]를 위한 이메일발송 함수 */
function fn_emailSend(data){
	var rsParamVO = data.rsParamVO;
	
    var customerRegNo        = $("#customerRegNo").val(); //회원OP등록번호
    var customerNameKor      = rsParamVO.customerNameKor; //이름
    var customerId           = $("#userId").val(); //아이디
    var customerEmail        = rsParamVO.customerEmail; //이메일
    
    var objParam = new Object();
    objParam.customerRegNo    = customerRegNo;
    objParam.emailSendType    = "G016005"; //이메일 유형 - com_email_temp_info 회원탈퇴 확인 메일 
    objParam.emailVer         = "1"; //이메일 버전 - com_email_temp_info 이메일 템플릿 조회    
    objParam.receiverName     = customerNameKor; //받는 사람 이름(실제 이메일에 셋팅됩니다.)    
    objParam.receiverName2    = util_nameMask(customerNameKor); //받는 사람 이름(실제 이메일에 셋팅됩니다.)    
    objParam.receiverEmail    = customerEmail; //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)    
    objParam.receiverEmail2   = util_emailMask(customerEmail); //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
    objParam.senderKind       = "G017003"; //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
    objParam.receiverKind     = "G018001"; //수신자 종류 - G018001:일반사용자, G018002:기업사용자
    objParam.receiverId       = customerRegNo; //수신자 ID(DB저장용)
    objParam.receiverId2      = util_idMask(customerId); //수신자 ID(실제 메일발송시 고객에게 보여질 ID)
    objParam.uriContextPath   = httpsContextpath;
    
    var url = "<c:url value='/spt/myp/sptMyInfo/updateSptMbrSecesEmail.ajax'/>";
    $.ajax({
         type    : "post"
        ,url     : url
        ,data    : objParam
        ,success : function(data){
           //alert("회원탈퇴가 되었습니다.");
           fn_moveList(); //로그아웃처리 후 메인화면으로 보냅니다.
        }
        ,error   : function(){
            var msgAlert = "<spring:message code='fail.alert.regist'/>";
            alert(msgAlert);
        }
    });
}

//비밀번호 확인 후 성공시 회원탈퇴신청 절차를 처리하고 메인화면으로 이동
function fn_moveList(){    
    var objParam = new Object();
    util_movePage("<c:url value='/cmm/commonLogout.do'/>",objParam); //공통 로그아웃 처리
}

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/spt/myp/sptMyInfo/sptMbrSeces.do'/>";
    var param = new Object();
    param.paramMenuId = "05005";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    //비밀번호 우클릭 금지
    $("#userPw").bind("contextmenu", function(e){
        return false;
    });
    
    /*
    //[확인]버튼 클릭 시 호출 - 회원정보 비밀번호 입력 확인
    $("#btnSchConfrm").bind("click", function(){
    	fn_beforeSchConfrm();     
    });
    */
    
});
</script>
</head>
<body>

<input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />
<input type="hidden" id="customerRegNo" name="customerRegNo" value="<c:out value="${LoginVO.customer_reg_no}" />" />

<div class="wrap">

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf"%>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">공인인증서</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
	        <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf"%>
	        <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>회원탈퇴</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="pw_confirm">
                        <p class="tit">본인확인을 위해 탈퇴하려는 아이디의 비밀번호를<br>한번 더 입력해주세요.</p>
                        <input type="password" name="userPw" id="userPw" title="비밀번호 입력" placeholder="비밀번호 입력"
                               onkeydown="javascript:if(event.keyCode == 13) fn_beforeSchConfrm();" 
                               autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'userPw',15);" 
                        />
                        <div class="btn_area">
                            <a href="javascript:fn_beforeSchConfrm();" id="btnSchConfrm" class="btn_type1 type2">확인</a>
                        </div>
                        <!-- <div id="errMessage" style="display: none;"></div> -->
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