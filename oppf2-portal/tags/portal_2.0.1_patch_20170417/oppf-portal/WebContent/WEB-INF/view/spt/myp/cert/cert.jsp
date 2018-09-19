<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cert.jsp
 * @Description : [마이페이지:공인인증서]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.30  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.30
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
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>

<!-- 공인인증서 관련 -->
<script type="text/javascript">
var i = 0;

function certsign(){
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }

    var plaintext = "testplain";
    var dn = "";
    if(i>4){
      alert("비밀번호 입력횟수 초과");
        return false;
    }
    
    if(CertManX.doubleClickBlock(arguments.callee)) return false;
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                alert("모듈 설치 필요.");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            if(errorCode == 90001 || errorCode == 90002){
                alert("모듈 업데이트 필요");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
                
            }else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
        }
    });
    
    //기간만료 폐기된 공인인증서 화면출력 안됨처리
    CertManX.SetMatchedContextExipreCheck(true);
    
    CertManX.SetDeviceOrder("HRUS"); // 인증서 우선 검색 순위
    CertManX.SetPasswordEncMode(1+16); // 패스워드 ENC 모드
    CertManX.SetExipreCheckSkip(0); // 인증서 만료 안내창
    
    CertManX.SetDeviceViewOrder("RUSH"); //저장매체 이미지 순서
//     CertManX.SetPolicyFilter(0,"");  //인증서 정책 필터[모든]
    CertManX.SetPolicyFilter(1+16+256,"1.2.410.200004.5.1.1.10;");  //인증서 정책 필터
    
    CertManX.SetWebAccMode(1);           //장애인 웹접근성
    
    CertManX.SetScanByDialogChoiceMode(0); //저장매체 선택시 인증서 검색
    CertManX.SetLanguageMode(0); //모듈 언어 설정
    
    //CertManX.SetLogoFile("./test.bmp");  //선택창 로고

    CertManX.SetKeySaferMode(3);         // 키보드보안모듈 연동 -> 최초에 파라미터가 3으로 셋팅되어있었음(키보드 보안업체 프로그램 과의 상호 연동을 위한 설정)
    CertManX.SetWrongPasswordLimit(1);   // 패스워드 입력제한

    
    CertManX.UnSetMatchedContext(function () {
        CertManX.SetMatchedContextExt("","","", 256+0+1, function(dn){
            if(dn == ""){
                if(CertManX.GetLastErrorCode() == 2417){
                    i++;
                    alert("비밀번호 오류 ["+i+"회]");
                    //sign();
                    return false;
                    
                }else{
                    //'인증서선택' 팝업창의 우하단 취소버튼이나 우상단 X버튼을 클릭하면 여기로 옵니다.
                    //alert("SetMatchedContextExt 실패 : ["+CertManX.GetLastErrorCode() +"]"+CertManX.GetLastErrorMsg());
                	if(CertManX.GetLastErrorCode() == "2500"){
                        alert(CertManX.GetLastErrorMsg());
                    }else if(CertManX.GetLastErrorCode() != "2501"){
                        //alert("SetMatchedContextExt 실패 : [" + CertManX.GetLastErrorCode() + "]" + CertManX.GetLastErrorMsg());
                    	alert(CertManX.GetLastErrorMsg());
                    }
                    i = 0;                    
                    return false;
                }
            }
            i=0;

            //BSTR SignDataB64(BSTR pPassword, BSTR pPlainText, long mode)
            CertManX.SignDataB64("", plaintext, 1, function(signdata) {
                if(signdata == ""){
                    //alert("SignDataB64 실패 : ["+CertManX.GetLastErrorCode() +"]"+CertManX.GetLastErrorMsg());
                    alert(CertManX.GetLastErrorMsg());
                    return false;
                }
                
                //1단계 본인인증때 저장한 사용자이름을 가지고 와서 공인인증서 등록시 받아오는 DN값의 사용자이름을 비교 체크합니다. 
                var customerNameKor1 = "<c:out value='${resultCustomerInfoVO.customerNameKor}'/>";
                var customerNameKor2 = CertManX.GetToken(signdata, "dn");
                //alert("customerNameKor1 : "+customerNameKor1+", customerNameKor2 : "+customerNameKor2);
                //if(customerNameKor1.substr(0, 2) == customerNameKor2.substr(3, 2)){
                if(customerNameKor2.indexOf("cn="+customerNameKor1) > -1){
                	form.t_dn.value = CertManX.GetToken(signdata, "dn");
                    form.t_signdata.value = CertManX.GetToken(signdata, "data"); //서명데이터 [서버에서  data와 r 값을 유효성검증함]
                    form.t_rdata.value = CertManX.GetToken(signdata,"r"); //주민번호등 인증비교값 [서버에서  data와 r 값을 유효성검증함]
                    //return true;
                    $("#btnRegist").click();                	
                }else{
                	alert("본인인증 때의 사용자이름과 공인인증서 등록상의 사용자이름이 다릅니다.");
                	return false;
                }
            });
        });
    });
}

</script>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/
 
 /*유효성검증 함수:주민등록번호*/
 function fn_SaveValidate1(){
     var customerSsn = $("#customerSsn").val().replace(/\s/g, ""); //주민등록번호
     
     //[주민등록번호]
     if(util_chkReturn(customerSsn, "s") == ""){
         alert("<spring:message code='errors.required' arguments='주민등록번호'/>");
         $("#customerSsn").focus();
         return false;
     }
     
     //[주민등록번호]정규식
     if( !util_isJumin(customerSsn) ){
         alert("<spring:message code='errors.invalid' arguments='주민등록번호'/>");
         $("#customerSsn").focus();
         return false;
     }
     
     return true;
     
 }

 /*유효성검증:동의체크*/
 function fn_SaveValidate2(){
     
     var TermChkCnt = 0;
     
     $("input[name='chkCustomerTerms']").each(function(idx){
         if($(this).is(":checked")){
             TermChkCnt++;
         }else{
             var alertMsg = $("#txtTermsTitle_"+idx).val();
             alert("<spring:message code='errors.required.select' arguments='"+alertMsg+"'/>");
             return false;
         }
     });
     
     if(TermChkCnt == $("input[name='chkCustomerTerms']").length){
         return true;
     }else{
         return false;
     }
     
}



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/*[공인인증서서버]공인인증서 서버 유효성검증을 조회요청 함수 */
function fn_ajaxCallSKVeirify(){
    
    var rsFlag = false;
    
//     var userSsn  = $("#customerSsn").val().replace(/\s/g, ""); //주민등록번호
    var signData = form.t_signdata.value;
    var rValue   = form.t_rdata.value;
    
    //파라미터셋팅
    var objParam = new Object();
    objParam.signData = signData;
    objParam.rValue   = rValue;

    //로딩 호출
    gfn_setLoading(true, "공인인증서 유효성 검증 중 입니다.");
    
    var url = "<c:url value='/cmm/selectSKVerify.ajax'/>";
    $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정
    $.ajax({
        type    : "post"
       ,contentType: "application/json"
       ,async   : false 
       ,url     : url
       ,data    : JSON.stringify(objParam)
       ,success : function(data){
    	   //로딩 호출
    	   gfn_setLoading(false);
    	   
           if(data.rs == '0'){
               var msgAlert = "<spring:message code='success.alert.regist'/>";
//                alert(msgAlert);
               rsFlag = true;
           }else{
               alert("<spring:message code='errors.alert.signkorea'/>\n["+data.rs+"]");
//                alert(data.rsMsg);
           }
       }
       ,error : function(){
    	   //로딩 호출
           gfn_setLoading(false);
    	   
    	   var msgAlert = "<spring:message code='fail.alert.regist'/>";
           alert(msgAlert);
       }
   });
    return rsFlag; 
}



/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/myp/cert/cert.do'/>";
    var param = new Object();
    param.paramMenuId = "05003";
    
    gfn_loginNeedMove(url, param);
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">    
        fn_login();
        return;
    </c:if>
	
    /* [취소] 이벤트발생 시 호출 */
    $("#btnCancel").bind("click", function(e){
        /* var customerNameKor = $("#customerNameKor").val();
        var customerPhone   = $("#customerPhone").val();
        var objParam = new Object();
        objParam.customerStep     = '001'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
        objParam.customerNameKor  = customerNameKor;
        objParam.customerPhone    = customerPhone;
        util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>",objParam); */
    	//메인페이지로 이동
        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
        document.location.href = uri;
    });
    
    /* [등록] 이벤트발생 시 호출 */
    $("#btnRegistCert").bind("click", function(e){
        /* if( !fn_SaveValidate1() ){ //유효성검증호출:주민등록번호
            return false;
        }
        if( !fn_SaveValidate2() ){ //유효성검증호출:동의체크
            return false;
        } */
        certsign();
    });
    
    /* [등록] 이벤트발생 시 호출 */
    $("#btnRegist").bind("click", function(e){
    	//유효성검증:공인인증호출
    	var rsFlag = fn_ajaxCallSKVeirify();
    	if(!rsFlag){
        	return false;
        }
//         var customerSsn   = $("#customerSsn").val();   //주민번호
        var customerRegNo = $("#customerRegNo").val(); //회원OP등록번호
        var customerVerify = $("#t_dn").val();         //회원검증값
        
        //약관배열설정
        var objParam2List = new Array();
        $("input[name='chkCustomerTerms']").each(function(){
            if($(this).is(":checked")){
                var objParam2 = new Object();
                var tmpIdArr = $(this).attr("id").split("_");
                var customerTermsType = '';
                if(tmpIdArr.length > 1){
                    customerTermsType = tmpIdArr[1];
                }
                var customerTermsContentRegSeq = $(this).val();
                objParam2.customerRegNo = customerRegNo;
                objParam2.customerTermsType = customerTermsType; //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
                objParam2.customerTermsContentRegSeq = customerTermsContentRegSeq;
                objParam2.customerTermsAuthYn = 'Y';
                objParam2List.push(objParam2);
            }
        });
        
        //파라미터셋팅
        var objParam = new Object();
        objParam.customerStep       = 'G006002'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
//         objParam.customerSsn        = customerSsn;
        objParam.customerRegNo      = customerRegNo;
        objParam.customerVerifyType = 'G007002'; //G007:회원검증종류(001:본인인증, 002:공인인증서)
        objParam.customerVerify     = customerVerify;
        objParam.customerTermsList  = objParam2List;
        
        //로딩 호출
        gfn_setLoading(true, "저장중입니다.");
        
        var url = "<c:url value='/myp/cert/updateSptCustomerVerifyAndTermsInfo.ajax'/>";
        $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정 
        $.ajax({
            type    : "post"
           ,contentType: "application/json"
           ,async   : false
           ,url     : url
           ,data    : JSON.stringify(objParam)
           ,success : function(data){
        	   //로딩 호출
               gfn_setLoading(false);
        	 
        	   var msgAlert = "<spring:message code='success.alert.regist'/>";
               alert(msgAlert);
               //location.reload();
               
               var param = new Object();
               param.paramMenuId = "05003";
               util_movePage("<c:url value='/myp/cert/cert.do'/>", param);
           }
           ,error   : function(){
        	   //로딩 호출
               gfn_setLoading(false);
        	 
        	   var msgAlert = "<spring:message code='fail.alert.regist'/>";
               alert(msgAlert);
           }
       });
    });
    
});
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
                <!-- <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">회원가입</li> -->
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">공인인증서</li>
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
                    <h3>공인인증서</h3>                                    
                </div>
            
                <!-- con -->
                <div class="con">
                    <%-- <div class="step_area">
                        <img src="<c:url value='/images/spt/members/step_join02.jpg'/>" 
                            alt="1단계 본인인증, 2단계 공인인증서 등록, 3단계 약관동의, 4단계 개인정보 입력, 5단계 이메일 인증 중 2단계 공인인증서 등록" 
                        />
                    </div> --%>
                    <div class="title_wrap">
                        <p class="title_01">공인인증서 등록</p>
                    </div>
                    
                    <input type="hidden" name="customerRegNo"  id="customerRegNo"  value="<c:out value="${customerRegNo}" />" />
                    <input type="hidden" name="customerDn" id="customerDn" value="<c:out value="${customerDn}" />" />
                    
                    <!-- 2016-06-03 삭제
                    <div class="form_list">
                       <ul>
                           <li>
                                <dl>
                                    <dt>
                                       <div><label for="user_num">주민등록번호</label></div>
                                    </dt>
                                    <dd>
                                        <div>
                                               <input type="text" name="customerSsn" id="customerSsn" style="width:315px;ime-mode:disabled;" placeholder="'-'를 제외하고 입력하세요." maxlength="13" onkeydown="util_numberonly(event);">
                                        </div>
                                    </dd>
                                </dl>
                            </li>
                        </ul>
                    </div>
                    -->
                    
                    <ul class="list_style_01">
                        <!-- <li>최초 1회 주민등록번호 입력 후 공인인증서를 등록하세요.</li> --> <!-- 2016-06-03 삭제 -->
                        <li>금융투자 핀테크 포털에서는 공인인증서를 직접 발급 및 재발급 받을 수 없습니다.</li>
                        <li>공인인증서 발급은 금융투자회사(증권사 등)를 이용하시기 바랍니다.</li>
                        <li>공인인증서를 이용하여 금융거래정보제공동의서에 전자서명을 하고 법적 효력과 함께 안전한 전자거래를 보장하기 위함입니다.</li>
                        <li>등록 가능한 공인인증서 : 회원의 주민등록번호로 발급된 개인용 공인인증서(범용 또는 증권전용 용도제한용)</li>
                    </ul>
                    
                <%-- 2016-06-03 삭제     
                <c:forEach var="tcList" items="${termsContentList}" varStatus="status">
                    <div class="privercy_box <c:if test='${status.index == 0}'>marT0</c:if>">
                        <p class="privercy_tit">
                            <c:out value='${tcList.customerTermsTypeName}'/>
                            <input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${tcList.customerTermsTypeName}'/>"/>
                        </p>
                        <div class="privercy_con">
                            <div class="privercy_txt">
                                <p class="txt_tit">
                                    <c:out value='${tcList.customerTermsTypeName}'/>
                                </p>
                                <c:out value='${tcList.customerTermsContent}'/>
                            </div>
                            <div class="privercy_chk">
                                <input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${tcList.customerTermsType}" value="${tcList.customerTermsContentRegSeq}" />
                                <label for="chkCustomerTerms_${tcList.customerTermsType}" class="chk_box">
                                    <c:out value='${tcList.customerTermsTypeName}'/>에 동의합니다. (필수)
                                </label>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                --%>
                
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnRegistCert" class="btn_type1">(재)등록</a>
                        <a href="javascript:void(0);" id="btnRegist" class="btn_type1" style="display:none;">등록</a>
                    </div>
                    
                    <form name="form" method="post">
                        <table>
                            <tr>
                                <td>
                                    <input type="text" id="t_dn" name="t_dn" style="width:200; display:none;" />
                                    <input type="text" id="t_rdata" name="t_rdata" style="width:200; display:none;" />
                                    <textarea id="t_signdata" name="t_signdata" style="height:100; width:300; display:none;"></textarea>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <iframe id="yettie_library_iframe" name="yettie_library_iframe" src="<c:url value='/js/cmm/SKCertService/app/views/library.html'/>" title="library" width="0px" height="0px" scrolling="no" style="top: 50%; left: 50%; margin-top: -200px; margin-left: -195px; position: fixed; z-index: 0; border: none;"></iframe>
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