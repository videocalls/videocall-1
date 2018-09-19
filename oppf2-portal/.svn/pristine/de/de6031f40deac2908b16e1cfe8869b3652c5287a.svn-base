<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptLogin.jsp
 * @Description : admin login
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.29  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.04.29
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 기능 함수
 *******************************************/
 
//로그인
function fn_login(){
    var loginId = $.trim($("#loginId").val());
    var loginPassword = $.trim($("#loginPassword").val());
    
    //아이디 체크
    if(gfn_nvl(loginId) == ""){
        //alert("<spring:message code='errors.required' arguments='아이디'/>");
        alert("아이디은(는) 필수 입력값입니다.");
        $("#loginId").focus();
        return false;
    }
    
    //패스워드 체크
    if(gfn_nvl(loginPassword) == ""){
        //alert("<spring:message code='errors.required' arguments='비밀번호'/>");
        alert("비밀번호은(는) 필수 입력값입니다.");
        $("#loginPassword").focus();
        return false;
    }
    
    var url = "<c:url value='/apt/cmm/loginCheck.do'/>";
    var reqData = {
       "admin_id"   : $("#loginId").val()
      ,"admin_password" : $("#loginPassword").val()
    };
    
    util_ajaxPage(url, reqData, "fn_loginCheck_callBack");
}

//로그인 체크 callback
function fn_loginCheck_callBack(data){
    var loginVO = data.loginVO;
    
    //로그인 실패 -> 아이디가 없음
    if(loginVO == null){
        alert("아이디를 확인 해 주세요.");
        $("#loginId").focus();
        return;
    }else{
        //1. 패스워드 실패 카운트 : <spring:message code='Globals.user.policy.password.failCnt'/>회 이상일 경우
        if(<spring:message code='Globals.user.policy.password.failCnt'/> <= loginVO.admin_password_fail_cnt){
            //비밀번호 실패시에만 처리, 성공 시 일반 로그인 처리
            if(loginVO.admin_password_yn != "Y"){
                var errMsg = "비밀번호가 틀렸습니다.\n";
                errMsg += "비밀번호 실패 횟수가 "+loginVO.admin_password_fail_cnt+"회 입니다.\n\n";
                errMsg += "다른 관리자에게 임시비밀번호를 발급 받아주세요.";

                alert(errMsg);
                return;
            }
        }
        
        //비밀번호 실패 여부 확인 
        //성공
        if(loginVO.admin_password_yn == "Y"){
            //2. 탈퇴여부
            if(loginVO.delete_yn == "Y"){
                alert("이미 탈퇴하셨습니다.\n다시 회원가입을 해 주세요.");
                return;
            }
            //이메일에서 로그인으로 유도되어 올 경우 emailSendType이 존재합니다.
            <c:choose>
                <c:when test="${empty paramVO.emailSendType}" >
                    /* //3. e-mail인증 여부 확인
                    if(loginVO.customer_email_auth_yn != "Y"){
                        alert("가입 시 입력한 이메일 주소로 전송된 인증 메일을 확인해주세요.");
                        return;
                    }
                    
                    //4. 회원가입 상태 ->활성화(G005002)일 경우에만 session 생성
                    //미활성 상태
                    if(loginVO.customer_reg_status == "G005001"){
                        alert("회원상태가 미활성화 상태입니다.\n관리자에게 문의 해 주세요.");
                        return;
                        
                    //정지 상태
                    }else if(loginVO.customer_reg_status == "G005003"){
                        alert("회원상태가 정지 상태입니다.\n관리자에게 문의 해 주세요.");
                        return;
                        
                    //탈퇴 상태
                    }else if(loginVO.customer_reg_status == "G005004"){
                        alert("회원상태가 탈퇴 상태입니다.\n관리자에게 문의 해 주세요.");
                        return;
                    } */   
                </c:when>
                <c:otherwise>           
                </c:otherwise>
            </c:choose>
            //로그인 한 계정이 3개월 이상 비밀번호 변경 이력이 없는 경우, 임시비번을 부여받은 경우 - 비밀번호 변경 유도 팝업 노출합니다.
            if(loginVO.admin_final_password_yn == "Y" || loginVO.admin_expire_password_yn == "Y" || loginVO.admin_temp_password_yn == "Y"){
                //비밀번호변경 팝업창 오픈                
                var url = "<c:url value='/apt/myp/aptMyInfo/aptMyPwMod.do'/>";
                var objOption = new Object();
                objOption.type = 'modal';
                if(loginVO.admin_final_password_yn == "Y") {
                    objOption.width = '620'; 
                    objOption.height = '560';   
                }else{
                    objOption.width = '620'; 
                    objOption.height = '460';   
                }
                    
                var objParam = new Object();
                objParam.callBakFunc = "fn_pwChgCallBack";
                
                util_modalPage(url, objOption, objParam);
            }else{
                var emailSendType = $("#emailSendType").val();                
                /* if(emailSendType == "G016001"){
                    alert("G016001 - 공통 템플릿, 페이지 이동합니다.");
                }else if(emailSendType == "G016002"){ //메인페이지로 이동("G016002 - 이메일 인증")                   
                    var customerRegNo = "<c:out value='${ paramVO.customerRegNo }'/>";                                     
                    var url = "";
                    url = "/usr/mbrReg/mbrReg6.do";
                    var objParam = new Object();
                    objParam.customerRegNo = customerRegNo;                               
                    util_movePage(url,objParam);
                }else if(emailSendType == "G016003"){ //회원가입 완료("G016003") 이메일 안에 '마이 페이지>회원정보' 링크 클릭시 여기로 옵니다.
                    var url = "";
                    url = "/apt/myp/aptMyInfo/aptMyInfo.do";
                    var objParam = new Object();
                    objParam.customerId = $("#loginId").val();
                    objParam.customerPassword = $("#loginPassword").val();
                    util_movePage(url,objParam);
                }else if(emailSendType == "G016004"){ //임시비밀번호 발급 안내("G016004") */
                if(emailSendType == "G016004"){ //임시비밀번호 발급 안내("G016004")
                    var url = "";
                    url = "/apt/cmm/mainView.do";
                    var objParam = new Object();                                                   
                    util_movePage(url,objParam);
                }else if(emailSendType == "G016005"){
                    alert("G016005 - 금융거래정보제공 동의 및 내용변경, 페이지 이동합니다.");
                }else if(emailSendType == "G016006"){
                    alert("G016006 - 금융거래정보제공 확인 안내, 페이지 이동합니다.");
                }else if(emailSendType == "G016007"){
                    alert("G016007 - 금융거래정보제공 재동의 안내, 페이지 이동합니다.");
                }else{
                    
                    //returnListURL이 있는 경우
                    if(util_chkReturn($("#returnListURL").val(), "s") != "") {
                        var url = $("#returnListURL").val();
                        var param = new Object();
                        
                        if(util_chkReturn($("#searchKeys").val(), "s") != ""){
                            param = JSON.parse($("#searchKeys").val());
                        }else{
                            param = "";
                        }
                        
                        util_movePage(url, param);
                        
                    }else{
                        //메인페이지로 이동
                        var uri = '<c:url value="/apt/cmm/mainView.do"/>';
                        document.location.href = uri;   
                    }   
                }   
            }
            
        //실패
        }else{
            alert("비밀번호를 확인 해 주세요.");
            $("#loginPassword").focus();
            return;
        }
    }
}

function fn_pwChgCallBack(data){
    //alert(1);
}



/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
    
    //로그인 처리
    $("#btnLogin").bind("click", function(){
        fn_login();
    });    
});


 
</script>
</head>
<input type="hidden" name="returnListURL" id="returnListURL" value="<c:out value='${ paramVO.returnListURL }'/>"/>
<input type="hidden" name="searchKeys" id="searchKeys" value="<c:out value='${ paramVO.searchKeys }'/>"/>

<body class="bc_fff">

    <div class="wrap">
        <!-- Container -->
        <div class="wrap_login" >
        <div>
            <!-- login_con -->
            <div class="content_login">
                <h1 class="login_title">
                    <p>
                        <a href="<c:url value='/' />"><img src="<c:url value='/images/apt/logo.png' />" alt="koscom OpenAPI Platform" /></a> 
                    </p>
                    <p class="detail"><strong>서비스관리시스템</strong></p>
                </h1>
                <div class="box">
                    
                    <div class="login_click">
                        <ul class="input">
                            <li>
                                <label for="login">아 이 디</label>
                                <span>
                                    <input type="text" id="loginId" name="loginId" class="i_text"
                                            onkeydown="javascript:if(event.keyCode == 13) $('#loginPassword').focus();" 
                                    />
                                </span>
                            </li>
                            <li>
                                <label for="login_pass">비밀번호</label>
                                <span>
                                    <input type="password" id="loginPassword" name="loginPassword" class="i_text"
                                             onkeydown="javascript:if(event.keyCode == 13) fn_login();" 
                                    />
                                </span>
                            </li>
                            <li class="btn" id="btnLogin"><a href="javascript:void(0);">로그인</a></li>
                        </ul>
                        <p class="link">
                            <!-- <span><a href="javascript:void(0);" id="btnSchId">아이디</a> / <a href="javascript:void(0);" id="btnSchPw">비밀번호 찾기</a></span> -->
                            <!-- <span><a href="javascript:void(0);" id="btnJoin">회원가입</a></span> -->
                        </p>
                        <!-- <p class="check"><input type="checkbox" id="idSave"> <label for="idSave">아이디 저장</label></p> -->
                    </div>
                </div>
                <ul class="info">
                    <li>관리자로 등록된 아이디로 로그인 할 수 있습니다.</li>
                    <li>본 관리자시스템은 koscom OpenAPI Platform에 직/간접적으로 반영됩니다. 보안 및 사용에 안전을 기해 주세요.</li>
                </ul>
                <p class="copyright">COPYRIGHT 2016. KOSCOM CORP. ALL RIGHTS RESERVED.</p>
            </div>
            <!-- login_con // -->
        </div>
        </div>
        <!-- Container // -->       
    </div>

</body>
</html>