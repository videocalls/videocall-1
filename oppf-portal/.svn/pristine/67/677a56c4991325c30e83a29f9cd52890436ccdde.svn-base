<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrJoin2.jsp
 * @Description : [회원가입:개인정보입력]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 * </pre>
 *
 * @author 포털 
 * @since 
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var customerPhone = '<c:out value="${paramVO.customerPhone}" />';

var customerTermsList = new Array();
	customerTermsList = '<c:out value="${termsContentList}" />';

	
/*******************************************
 * 기능 함수
 *******************************************/
 
/* [휴대폰번호]셋팅 함수 */
function fn_setValphoneNumber(pNumber){
    if(util_chkReturn(pNumber, "s") == ""){
        alert('휴대폰번호가 없습니다.');
        return false;
    }
    var customerPhone1 = '';
    var customerPhone2 = '';
    var customerPhone3 = '';
     var pNumber_arr = pNumber.split("-");
     if(pNumber_arr.length == 3){
         customerPhone1 = pNumber_arr[0];
         customerPhone2 = pNumber_arr[1];
         customerPhone3 = pNumber_arr[2];
     }else{
         if(pNumber.length == 10){ //ex)0104568901
             customerPhone1 = pNumber.substring(0,3);
             customerPhone2 = pNumber.substring(3,6);
             customerPhone3 = pNumber.substring(6);
         }else if(pNumber.length == 11){//ex)01045678901
             customerPhone1 = pNumber.substring(0,3);
             customerPhone2 = pNumber.substring(3,7);
             customerPhone3 = pNumber.substring(7);
         }
     }
     $("#customerPhone1").val(customerPhone1);
     $("#customerPhone2").val(customerPhone2);
     $("#customerPhone3").val(customerPhone3);
}

/* [생년월일]설정 함수 */
function fn_setSelectBoxBirthDay(){
    var date = new Date();
    var currentYYYY = date.getFullYear();
    var currentMM = date.getMonth()+1;
    var currentDD = date.getDate();
    if(currentMM < 10){
        currentMM = '0'+currentMM;
    }
    if(currentDD < 10){
        currentDD = '0'+currentDD;
    }
    
    //[년] select option 설정
    var objParam = new Object();
    objParam.term = 100;
    objParam.term2 = 10;
    var yyyy_arr = gfn_getDataListYear(objParam);
    $.each(yyyy_arr, function(idx,data){
        $("#customerBirthDayYYYY").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[월] select option 설정
    var mm_arr = gfn_getDataListMonth();
    $.each(mm_arr, function(idx,data){
        $("#customerBirthDayMM").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[일] select option 설정
    var dd_arr = gfn_getDataListDay();
    $.each(dd_arr, function(idx,data){
        $("#customerBirthDayDD").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
}

 
/*유효성검증 함수*/
function fn_SaveValidate(){
    var customerId      = $("#customerId").val().replace(/\s/g, ""); //아이디
    var customerIdCheck = $("#customerIdCheck").val(); //아이디 중복체크
    var customerPassword1 = $("#customerPassword1").val().replace(/\s/g, ""); //비밀번호
    var customerPassword2 = $("#customerPassword2").val().replace(/\s/g, ""); //비밀번호확인
    var customerEmail1     = $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
    var customerEmail2_txt = $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
    
    
    
    if(!gfn_validationCheckId("customerId")){
    	return false;
    }
    
    //[아이디]중복확인
    if(customerIdCheck != "Y"){
        alert("아이디 중복확인을 해주세요");
        $("#customerId").focus();
        return false;
    }
    
    if(!gfn_validationCheckPw("customerPassword1")){
    	return false;
    }
    
    //[비밀번호확인]
    if(util_chkReturn(customerPassword2, "s") == ""){
        alert("<spring:message code='errors.required' arguments='비밀번호확인'/>");
        $("#customerPassword2").focus();
        return false;
    }
    
    //[비밀번호]!=[비밀번호확인]
    if(customerPassword1 != customerPassword2){
        alert("입력하신 비밀번호와 비밀번호확인이 일치하지 않습니다.");
        return false;
    }
    
    //[비밀번호]가 [아이디]와 동일한지 체크
    if(customerId == customerPassword1) {
        alert("아이디 와 비밀번호가 동일합니다.\r\n비밀번호를 다시 입력하십시오.");
        $("#customerPassword1").focus();
        return false;
    }
    
    //[비밀번호]에 [아이디]가 포함되어 있는지 체크
    if(customerId.substring(0,4) == customerPassword1.substring(0,4)){
        alert("아이디 와 비밀번호 앞4자리가 동일 합니다.\r\n비밀번호를 다시 입력하십시오.");
        $("#customerPassword1").focus();
        return false;
    }

    //[비밀번호]에 [아이디]의 연속된 3자리가 같은지 체크
    if( idValuePw(customerId, customerPassword1)){
   	   alert("비밀번호가 아이디와 동일하거나 3자리 이상 일치할 경우 사용할 수 없습니다.");
       $("#customerPassword1").focus();
       return false;
    }

//[성별]
//     if($("input[name='customerSex']:checked").length > 1){
//         alert("<spring:message code='errors.required.select' arguments='성별'/>");
//         $("#customerSex_001").focus();
//         return false;
//     }
    
    //[이메일앞자리]
    if(util_chkReturn(customerEmail1, "s") == ""){
        alert("이메일 주소를 입력해주세요.");
        $("#customerEmail1").focus();
        return false;
    }
    
    //[이메일앞자리]정규식
    if( !util_isEmail(customerEmail1,'1') ){
        alert("이메일 형식이 일치하지 않습니다.");
        $("#customerEmail1").focus();
        return false;
    }
    
    //[이메일뒷자리]
    if(util_chkReturn(customerEmail2_txt, "s") == ""){
        alert("이메일 주소를 입력해주세요.");
        $("#customerEmail2_txt").focus();
        return false;
    }
    
    //[이메일뒷자리]정규식
    if( !util_isEmail(customerEmail2_txt,'2') ){
        alert("이메일 형식이 일치하지 않습니다.");
        $("#customerEmail2_txt").focus();
        return false;
    }
    
    return true;
    
}

//비밀번호가 아이디와 동일하거나 3자리 이상 일치할 경우 사용할 수 없습니다.
function idValuePw(validid, validpw) {
 	var tmp = "";
 	var cnt = 0;
 	for(i=0; i<validid.length-2; i++){
  		tmp = validid.charAt(i) + validid.charAt(i+1) + validid.charAt(i+2);
  		if(validpw.indexOf(tmp) > -1){
   			cnt = cnt + 1;
  		}
 	}
 	 if(cnt > 0){
 		  return true;
	 } else {
 		  return false;
	 }
}
 
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
 
/* [중복확인]ID체크 콜백 함수 */
function fn_ajaxCallback_checkId(data){
    var se = data.chkSe;
    if(se == "Y"){
        alert("사용가능한 아이디입니다.");
        $("#customerIdCheck").val("Y");
        $("#customerId").css("background-color","silver");
        $("#customerId").attr("readonly",true);
        $("#btnChkId").hide(); //[중복확인]
        $("#btnIdReInput").show(); //[아이디재입력]
        $("#customerId").focus();
    }else{
        alert("존재하는 아이디입니다.");
//         $("#customerId").val("");
        $("#customerId").focus();
    }
    $("#customerIdCheck").val(se);
}

<%-- 처리 실패시 reset --%>
function fn_resetOtp(){
    var customerOtpYn = $("#customerOtpYn").is(":checked")? false:true;
    
    $("#btnOtp").html("확인");
    //체크 시
    if(customerOtpYn){
        $("#customerSendOtpId").removeAttr("disabled");
        
        $("#btnOtp").removeClass("type2");
        
        //이전값 셋팅
        $("#customerOtpYn").prop("checked", true);
        
        $("#customerSendOtpId").val("");
        $("#customerOtpId").val("temp");
    }else{
        $("#customerSendOtpId").attr("disabled", "disabled");
        
        $("#btnOtp").addClass("type2");
        
        //이전값 셋팅
        $("#customerOtpYn").prop("checked", false);
        
        $("#customerSendOtpId").val("");
        $("#customerOtpId").val("");
    }    
}


/*******************************************
 * 이벤트 함수
 *******************************************/
 
/* 화면 로드 처리 */
$(document).ready(function(){
    
//     $("#customerEmail1").bind("keydown", function(e){
        
//     });
    
	/* [이메일]셀렉트박스 변경 시 호출 */
    $("#customerEmail2_slt").change(function(){
        if($(this).val()==""){ //[직접입력]인 경우
            $("#customerEmail2_txt").val("");
            $("#customerEmail2_txt").attr("disabled",false);
        }else{
            $("#customerEmail2_txt").val($(this).val());
            $("#customerEmail2_txt").attr("disabled",true);
        }
    });
    
    /* [중복확인]버튼 클릭 시 호출 */
    $("#btnChkId").click(function(){
        
        if(!gfn_validationCheckId("customerId")){
        	return false;
        }
        
        var url = "<c:url value='/usr/mbrReg/checkId.ajax'/>";
        var reqData = {"customerId":$("#customerId").val()};
        util_ajaxPage(url, reqData, "fn_ajaxCallback_checkId");
        
    });
    
    /* [아이디재입력]버튼 클릭 시 호출 */
    $("#btnIdReInput").click(function(){
        $("#customerIdCheck").val("N"); //아이디체크 초기화
        $("#customerId").css("background-color","#ffffff");
        $("#customerId").attr("readonly",false);
        $("#btnIdReInput").hide(); //[아이디재입력]
        $("#btnChkId").show(); //[중복확인]
    });
    
    
    
    /* [다음]버튼 클릭 시 호출 */
    $("#btnNext").click(function(){
    	fn_save();
    });
    

	<c:if test="${not empty paramVO.email1}">

	var email1 = "<c:out value="${paramVO.email1}" />";
	var email2 = "<c:out value="${paramVO.email2}" />";
		
    $("#customerEmail1").val(email1);     //이메일앞자리
    $("#customerEmail2_txt").val(email2); //이메일뒷자리
	</c:if>

	<c:if test="${not empty paramVO.customerRegNo}">

    $("#customerRegNo").val("<c:out value="${paramVO.customerRegNo}" />"); 
	</c:if>
    
    
});


//인증번호 확인 및 CI 조회
function fn_save() {

    var customerPhone       = $("#customerPhone").val(); //휴대폰번호1
    
	 if( !fn_SaveValidate() ){ //유효성검증호출
         return;
     }
	 if( fn_setValphoneNumber(customerPhone)){
         return;
	 }
     //var msgConfirm = "<spring:message code='confirm.regist.msg'/>";
     //if( confirm(msgConfirm) ){
 	
     var customerBirthDay     = $("#customerBirthDay").val();
     
     var customerNameKor      = $("#customerNameKor").val(); //이름
     var customerId           = $("#customerId").val().replace(/\s/g, ""); //아이디
     var customerPassword1    = $("#customerPassword1").val().replace(/\s/g, ""); //비밀번호

     
     var customerSex          = $("#customerSex").val();  //성별
     var customerEmail1       = $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
     var customerEmail2_txt   = $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
     var customerPhone       = $("#customerPhone").val(); //휴대폰번호1
     
     
     var customerPhone1       = $("#customerPhone1").val(); //휴대폰번호1
     var customerPhone2       = $("#customerPhone2").val(); //휴대폰번호2
     var customerPhone3       = $("#customerPhone3").val(); //휴대폰번호3
     var customerVerify       = $("#customerVerify").val(); 
     
     
     var customerEmailReceiveYn = 'N'; //이메일수신동의여부
     
     //이메일수신동의여부가 체크한 경우
     if( $("input:checkbox[id='email_chk']").is(":checked") ){
         customerEmailReceiveYn = 'Y';
     }
     
     var objParam = new Object();

     
     
     objParam.customerNameKor     = customerNameKor;
     objParam.customerId          = customerId;
     objParam.customerPassword    = customerPassword1;
     if(util_chkReturn(customerBirthDay, "s") != ""){
         objParam.customerBirthDay = customerBirthDay;
     }
     if(util_chkReturn(customerSex, "s") != ""){
         objParam.customerSex = customerSex;
     }
     objParam.customerEmail       = customerEmail1 + '@' + customerEmail2_txt;
     objParam.customerPhone       = customerPhone1 + '-' + customerPhone2 + '-' + customerPhone3;
     objParam.customerEmailReceiveYn = customerEmailReceiveYn;
     objParam.customerStep        = 'G006005'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
     objParam.customerRegStatus   = 'G005002'; //메일발송하기때문에 상태를 활성으로 바꿔준다.
     objParam.customerVerify   	  = customerVerify; //메일발송하기때문에 상태를 활성으로 바꿔준다.
     objParam.customerEmailAuthYn = 'Y';
     objParam.customerTermsAuthYn = 'Y';
     //비밀번호 관련
     objParam.customerChgPasswordDate = "sysdate";
     objParam.customerExpirePasswordDate = "<spring:message code='Globals.user.policy.password.expire' />";
     objParam.customerFinalPasswordDate = "<spring:message code='Globals.user.policy.password.final' />";
     

 	<c:if test="${not empty paramVO.customerRegNo}">
 		objParam.customerRegNo = $("#customerRegNo").val(); 
 	</c:if>
     
	
	//로딩 호출
	gfn_setLoading(true);

	//page setting  
	var url = "<c:url value='/usr/mbrReg/mbrNewReg2.ajax'/>";
	
	var callBackFunc = "fn_saveCallBack";
	
	<%-- 공통 ajax 호출 --%>
	util_ajaxPageJson(url, objParam, callBackFunc);
	
}

function fn_saveCallBack(data) {
	
	//로딩 호출
	gfn_setLoading(false);
    var objParam = new Object();

	if(data.customerRegNo == null || data.customerRegNo == "") {

        var msgAlert = "회원가입 중 문제가 발생하였습니다.\n관리자에게 문의 해 주세요.";
        alert(msgAlert);
		util_movePage("<c:url value='/spt/cmm/mainView.do'/>",objParam);        
	} else {
		$("#customerRegNo").val(data.customerRegNo);
	    //메일발송
	    fn_emailSend();		
	}
}


/* [이메일인증]을 위한 이메일발송 함수 */
function fn_emailSend(){
    var customerRegNo        	= $("#customerRegNo").val(); //회원OP등록번호
    var customerNameKor      	= $("#customerNameKor").val(); //이름
    var customerEmail1       	= $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
    var customerEmail2_txt   	= $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
    var customerEmail        	= customerEmail1 + '@' + customerEmail2_txt;
    var customerId				= $("#customerId").val();
    var customerPhone1       = $("#customerPhone1").val(); //휴대폰번호1
    var customerPhone2       = $("#customerPhone2").val(); //휴대폰번호2
    var customerPhone3       = $("#customerPhone3").val(); //휴대폰번호3
    
    var objParam = new Object();    
    objParam.customerRegNo    = customerRegNo;
    objParam.emailSendType    = "G016003"; //이메일 유형 - com_email_temp_info 이메일 템플릿 조회 
    objParam.emailVer         = "1"; //이메일 버전 - com_email_temp_info 이메일 템플릿 조회    
    objParam.receiverName     = customerNameKor; //받는 사람 이름(실제 이메일에 셋팅됩니다.)    
    objParam.receiverEmail    = customerEmail; //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
    objParam.senderKind       = "G017003"; //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
    objParam.receiverKind     = "G018001"; //수신자 종류 - G018001:일반사용자, G018002:기업사용자    
    objParam.receiverId       = customerRegNo; //수신자 ID
    objParam.uriContextPath   = httpsContextpath;

    objParam.receiverName2    = customerNameKor;
    objParam.receiverId2	  = customerId;
    objParam.receiverEmail2	  = customerEmail;
    
    var url = "<c:url value='/usr/mbrReg/sendMbrReg4Email.ajax'/>";
    $.ajax({
         type    : "post"
        ,url     : url
        ,data    : objParam
        ,success : function(data){
        	objParam.customerId			= customerId;
            objParam.customerEmail		= customerEmail;
            objParam.customerPhone		= customerPhone1 + '-' + customerPhone2 + '-' + customerPhone3;
            objParam.customerNameKor		= customerNameKor;
            
            util_movePage("<c:url value='/usr/mbrReg/mbrNewReg3.do'/>",objParam);
        }
        ,error   : function(){
            var msgAlert = "메일발송 중 문제가 발생하였습니다.\n관리자에게 문의 해 주세요.";
            alert(msgAlert);
            return;
        }
    });
} 
</script>
</head>
<body>

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
					<!-- 2016-06-01 수정 --> <!-- renewal -->
					<div class="step_area"> 
						<ul>
							<li class="pass"><div>약관동의 및 본인인증</div></li><!-- 지나간step -->
							<li class="on"><div>개인정보 입력</div></li><!-- 현재step -->
							<li class="last"><div>가입완료</div></li>
						</ul>
                    </div>
					<!-- // 2016-06-01 수정 -->
					
					<div class="title_wrap">
						<p class="title_01">개인정보 입력</p>
						<p class="right"><span class="icon_basic">*</span><span class="point03">필수입력사항 입니다</span></p>
					</div>

					<!-- tbtype_form --> <!-- renewal -->
					<table class="tbtype_form">
						<caption>아이디, 비밀번호, 비밀번호 확인, 이메일 입력</caption>
						<colgroup>
							<col style="width:20%;">
							<col style="width:*;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row"><label for="user_id">아이디<span class="icon_basic">*필수입력</span></label></th>
							<td>
                                <input type="text" name="customerId" id="customerId" style="width:238px;ime-mode:disabled;" maxlength="15" onkeydown="util_engonly(event);" />
                                &nbsp;
                                <a href="javascript:void(0);" id="btnChkId" class="btn_type3">중복확인</a>
                                <a href="javascript:void(0);" id="btnIdReInput" style="display:none;" class="btn_type3">아이디재입력</a>
                                <span class="info_msg">영문, 숫자 조합, 소문자 입력4~15자 이내 <br>한글입력 불가</span>
                                <input type="hidden" name="customerIdCheck" id="customerIdCheck" value="N" />
                                <input type="hidden" name="customerRegNo" id="customerRegNo" value="" />
                                
                                <input type="hidden" name="customerVerify" id="customerVerify" value="<c:out value="${paramVO.customerVerify}" />" />
                                <input type="hidden" name="customerNameKor" id="customerNameKor" value="<c:out value="${paramVO.customerNameKor}" />" />
                                <input type="hidden" name="customerSex" id="customerSex" value="<c:out value="${paramVO.customerSex}" />" />
                                <input type="hidden" name="customerPhone" id="customerPhone" value="<c:out value="${paramVO.customerPhone}" />" />
                                <input type="hidden" name="customerBirthDay" id="customerBirthDay" value="<c:out value="${paramVO.customerBirthDay}" />" />
                                <input type="hidden" name="customerEmail" id="customerEmail" />
                                <input type="hidden" name="customerEmail2" id="customerEmail2" />
                                <input type="hidden" name="customerPhone1" id="customerPhone1" />
                                <input type="hidden" name="customerPhone2" id="customerPhone2" />
                                <input type="hidden" name="customerPhone3" id="customerPhone3" />
                                
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="user_pw">비밀번호<span class="icon_basic">*필수입력</span></label></th>
							<td>
                                <input type="password" name="customerPassword1" id="customerPassword1" style="width:238px;" maxlength="15" placeHolder="ex)Qetu101!@" />
                                <span class="info_msg">8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
                            </td>
						</tr>
						<tr>
							<th scope="row"><label for="user_pw2">비밀번호 확인<span class="icon_basic">*필수입력</span></label></th>
							<td>
                                <input type="password" name="customerPassword2" id="customerPassword2" style="width:238px;"  maxlength="15" />
							</td>
						</tr>
						<tr>
                            <th scope="row">
                                <label for="user_email">이메일<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <input type="text" name="customerEmail1" id="customerEmail1" maxlength="20" style="width:154px;ime-mode:disabled;" />
                                <span class="dot">@</span>
                                <input type="text" name="customerEmail2_txt" id="customerEmail2_txt" title="이메일주소뒷부분" maxlength="20" style="width:174px;ime-mode:disabled;" />
                                <span class="sel_box1">
                                    <select name="customerEmail2_slt" id="customerEmail2_slt" title="이메일주소 선택">
                                        <option value="">직접입력</option>
                                        <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                            <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                        </c:forEach>
                                    </select>
                                </span>    	
								
								<div class="info_bottom">
									<p class="info_msg">* 서비스 및 정책 관련 중요 정보는 수신 여부와 관계없이 발송됩니다. (예: 금융정보제공 재동의 안내)</p>
									<input type="checkbox" id="email_chk"><label for="email_chk" class="chk_box">수신 동의 (뉴스/이벤트 등)</label>
								</div>
							</td>
						</tr>
						<!-- // 2016-06-02 추가 -->
						</tbody>
					</table>					
					<!-- // tbtype_form --> <!--// renewal -->

					<div class="btn_area">
						<!-- <a href="#none" class="btn_type2">취소</a> -->
                        <a href="javascript:void(0);" id="btnNext" class="btn_type1">다음</a>
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