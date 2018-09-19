<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg1.jsp
 * @Description : [회원가입:3.약관동의]
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
<script type="text/javascript" src="/js/spt/common_pub.js"></script>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/
 
/*유효성검증 함수*/
function fn_SaveValidate(){
    
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
    
    /*
    //[서비스이용약관] 체크박스
    if( !$("input:checkbox[id='customerTerms003_1']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='서비스이용약관'/>");
        $("#customerTerms003_1").focus();
        return false;
    }
    
    //[개인정보취급방침] 체크박스
    if( !$("input:checkbox[id='customerTerms003_2']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='개인정보취급방침'/>");
        $("#customerTerms003_2").focus();
        return false;
    }
    
    //[개인정보수집이용동의] 체크박스
    if( !$("input:checkbox[id='customerTerms003_3']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='개인정보수집이용동의'/>");
        $("#customerTerms003_3").focus();
        return false;
    }
    return true;
    */
    
}
function fn_allCk(){

    if($("#allchkCustomerTerms").is(":checked")){
        $("input[name='chkCustomerTerms']").each(function(){
            $(this).prop("checked",true); 
        });
        
        $("#allchkCustomerTerms").prop("checked",true);
    }else{
        $("input[name='chkCustomerTerms']").each(function(){
            $(this).prop("checked",false);                
        });
        
        $("#allchkCustomerTerms").prop("checked",false);
    }
}

//약관 동의 및 본인인증 체크박스 이벤트
function fn_termsCkBox(chkid){

   	var chNum =0;
       $("input[name='chkCustomerTerms']").each(function(){
           if($(this).is(":checked")){
           	chNum = chNum+1;	
           } 
       });

   	if(chNum > 2){
   		$("#allchkCustomerTerms").prop("checked",true);
   	} else {
   		$("#allchkCustomerTerms").prop("checked",false);
   	}
}

//약관 동의 및 본인인증 체크박스 이벤트
function fn_termsCk(chkid){
	
     if($("#"+chkid+"").is(":checked")){
    	$("#"+chkid+"").prop("checked",false); 
    } else {
    	$("#"+chkid+"").prop("checked",true);
    } 
    

   	var chNum =0;
       $("input[name='chkCustomerTerms']").each(function(){
           if($(this).is(":checked")){
           	chNum = chNum+1;	
           } 
       });

   	if(chNum > 2){
   		$("#allchkCustomerTerms").prop("checked",true);
   	} else {
   		$("#allchkCustomerTerms").prop("checked",false);
   	}
   	
}

/**
*	휴대폰 인증 팝업 호출
**/
function fn_mobileCheckAuth(){
    var url = "<c:url value='/usr/mbrReg/mobileCheckAuth.do'/>";
    var objOption = new Object();
//     objOption.type = 'window';
    objOption.type = 'modal';
    objOption.width = '620';
    objOption.height = '607';

    var objParam = new Object();
    util_modalPage(url, objOption, objParam);
}


function fn_popClose(){
    var uri = '<c:url value="/spt/cmm/mainView.do"/>';
    document.location.href = uri;   
}

function checkCi(sex, birthDay, smobileNo, sname, ci) {
	fn_checkCi(sex, birthDay, smobileNo, sname, ci);
}

/**
*	본인 인증 완료 후 CI 조회
**/
//function checkCi() {
function fn_checkCi(sex, birthDay, smobileNo, sname, ci) {
	/*
	chkCustomerTerms_${status.index};
	chkCustomerTerms_${status.index};
	chkCustomerTerms_${status.index};
	*/
	
	$("#sex").val(sex);
	$("#birthDay").val(birthDay);
	$("#smobileNo").val(smobileNo);
	$("#sname").val(sname);
	$("#customerVerify").val(ci);
    
	//로딩 호출
	gfn_setLoading(true);
		
	//page setting  
    var url = "<c:url value='/usr/mbrReg/chkMbrCiChk.ajax'/>";
    var param = $("#MbrRegVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}

function fn_searchCallBack(data){
	//로그인 처리
	if(data.error == -1){
		fn_login();
	    return;
	}
	
	var resultList = data.resultList;

	if(data.withDrawCnt > 0){
		alert("탈퇴회원의 재가입은 3일\n이후부터 가능합니다.");
        //메인페이지로 이동
        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
        document.location.href = uri;   
	} else if(data.returnVO != null){
		if(data.returnVO.temporaryMemberYn == 'Y' && data.returnVO.customerRegStatus == 'G005002'){

			alert("비회원 인증 내역이 존재합니다. 회원 가입 시, 비회원 인증을 통해 이용하신 내역이 이전됩니다.");
			fn_updateMember(data.returnVO.customerEmail,data.returnVO.customerRegNo);
			
		} else if(data.returnVO.customerRegStatus == 'G005002'){
			alert("이미 회원가입된 정보입니다.\n확인을 클릭하시면\n로그인 페이지로 이동합니다.");
	        //로그인 페이지로 이동
	        var uri = '<c:url value="/spt/cmm/loginView.do"/>';
	        document.location.href = uri;   
		}else if(data.returnVO.customerRegStatus == 'G005003'){
			alert("이미 회원가입된 정보입니다.\n관리자에게 문의해주세요.");
	        //메인페이지로 이동
	        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
	        document.location.href = uri;   
		} else if(data.returnVO.customerRegStatus == 'G005004'){
			alert("탈퇴환 회원입니다.\n관리자에게 문의해주세요.");
	        //메인페이지로 이동
	        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
	        document.location.href = uri;   
		} else {		
			fn_createMember();
		}
	} else {
		fn_createMember();
	}
	
}

/**
*	회원 등록
**/
function fn_createMember() {
    //약관배열설정
    var objParamList2 = new Array();
    $("input[name='chkCustomerTerms']").each(function(){
        if($(this).is(":checked")){
            var objParam2 = new Object();
            var tmpIdArr = $(this).attr("id").split("_");
            var customerTermsType = '';
            if(tmpIdArr.length > 1){
                customerTermsType = tmpIdArr[1];
            }
            var customerTermsContentRegSeq = $(this).val();
            objParam2.customerTermsType = customerTermsType; //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
            objParam2.customerTermsContentRegSeq = customerTermsContentRegSeq;
            objParam2.customerTermsAuthYn = 'Y';
            objParamList2.push(objParam2);        	
        }
    });

    //파라미터셋팅
    var objParam = new Object();
    objParam.customerTermsList = objParamList2;
    
    objParam.customerSex = $("#sex").val();
    objParam.customerBirthDay = $("#birthDay").val();
    objParam.customerPhone = $("#smobileNo").val();
    objParam.customerNameKor = $("#sname").val();
    objParam.customerVerify = $("#customerVerify").val();

    util_movePage("<c:url value='/usr/mbrReg/mbrNewReg2.do'/>", objParam);

}


function fn_updateMember(custEmail,custRegNo) {
    //약관배열설정
    var objParamList2 = new Array();
    $("input[name='chkCustomerTerms']").each(function(){
        if($(this).is(":checked")){
            var objParam2 = new Object();
            var tmpIdArr = $(this).attr("id").split("_");
            var customerTermsType = '';
            if(tmpIdArr.length > 1){
                customerTermsType = tmpIdArr[1];
            }
            var customerTermsContentRegSeq = $(this).val();
            objParam2.customerTermsType = customerTermsType; //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
            objParam2.customerTermsContentRegSeq = customerTermsContentRegSeq;
            objParam2.customerTermsAuthYn = 'Y';
            objParamList2.push(objParam2);        	
        }
    });

    //파라미터셋팅
    var objParam = new Object();
    objParam.customerTermsList = objParamList2;
    
    objParam.customerSex = $("#sex").val();
    objParam.customerBirthDay = $("#birthDay").val();
    objParam.customerPhone = $("#smobileNo").val();
    objParam.customerNameKor = $("#sname").val();
    objParam.customerVerify = $("#customerVerify").val();
    objParam.customerEmail = custEmail;
    objParam.customerRegNo = custRegNo;

    util_movePage("<c:url value='/usr/mbrReg/mbrNewReg2.do'/>", objParam);

}

/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
 

/*******************************************
 * 이벤트 함수
 *******************************************/
 
/* 화면 로드 처리 */
$(document).ready(function(){
    
    /* [전체약관동의]체크박스 이벤트발생 시 호출 */
    //$("#allchkCustomerTerms").bind("change", function(e){
    $(".allCheck").bind("change", function(e){
        if($(this).is(":checked")){
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",true); 
            });
            
            $("#allchkCustomerTerms").prop("checked",true);
            $("#allchkCustomerTermsBottom").prop("checked",true);
        }else{
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",false);                
            });
            
            $("#allchkCustomerTerms").prop("checked",false);
            $("#allchkCustomerTermsBottom").prop("checked",false);
        }
    });
        
    
    /* [동의] 이벤트발생 시 호출 */
    $("#btnTermsAgree").bind("click", function(e){
        
        if( !fn_SaveValidate() ){ //유효성검증호출
            return false;
        }
                
        fn_mobileCheckAuth();
        
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
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">회원가입</li>
            </ul>
        </div>
        <!-- // location -->
        
        <!-- container -->
        <div class="container">
        <form:form commandName="MbrRegVO" name="MbrRegVO" method="post">
        	<input type="hidden" name="sex" id="sex" value="" />
        	<input type="hidden" name="birthDay" id="birthDay" value="" />
        	<input type="hidden" name="smobileNo" id="smobileNo" value="" />
        	<input type="hidden" name="sname" id="sname" value="" />
        	<input type="hidden" name="customerVerify" id="customerVerify" value="" />
        
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
							<li class="on"><div>약관동의 및 본인인증</div></li><!-- 현재step -->
							<li><div>개인정보 입력</div></li>
							<li class="last"><div>가입완료</div></li>
						</ul>
                    </div>
					<!-- // 2016-06-01 수정 -->
					
					<div class="title_wrap">
                        <p class="title_01">약관 동의 및 본인인증</p>
                    </div>

					<div class="total_chk">
						<input type="checkbox" class="allCheck" name="allchkCustomerTerms" id="allchkCustomerTerms">
						<label for="allchkCustomerTerms" class="chk_box" onclick="fn_allCk();">서비스 이용약관, 개인정보 수집∙이용 동의, 개인정보 제3자 동의에 모두 동의합니다.</label>						
					</div>					
					<div class="privercy_box marT0">
					
					
                	<c:forEach var="tcList" items="${termsContentList}" varStatus="status">
	                	<c:if test='${status.index == 0}'>
							<div class="privercy_wide">
								<div class="privercy_con">
									<div class="privercy_chk">
										<input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${tcList.customerTermsType}" value="${tcList.customerTermsContentRegSeq}" onclick="fn_termsCkBox('chkCustomerTerms_${tcList.customerTermsType}');"><label for="chk1" class="chk_box" onclick="fn_termsCk('chkCustomerTerms_${tcList.customerTermsType}');"><c:out value='${tcList.customerTermsTypeName}'/>에 동의합니다. (필수)</label>
										<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${tcList.customerTermsTypeName}'/>"/>                                               
									</div>
									<div class="textboxwrap_term1">
										<div class="intextboxwrap" tabindex="0">
											<span id="privercy_txt" style="display: block;">
												<c:out value="${tcList.customerTermsContent}" escapeXml="false" />
											</span>
										</div>
									</div>									
								</div>
							</div>
						</c:if>
						
						<c:if test='${status.index == 1}'>
							<div class="privercy_box_both">
						</c:if>
						<c:if test='${status.index == 1}'>
							<div class="privercy_con">
								<div class="privercy_chk">
									<input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${tcList.customerTermsType}" value="${tcList.customerTermsContentRegSeq}" onclick="fn_termsCkBox('chkCustomerTerms_${tcList.customerTermsType}');"><label for="chk2" class="chk_box" onclick="fn_termsCk('chkCustomerTerms_${tcList.customerTermsType}');"><c:out value='${tcList.customerTermsTypeName}'/>에 동의합니다. (필수)</label>
									<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${tcList.customerTermsTypeName}'/>"/>                                               
								</div>
								<div class="textboxwrap_term2">
									<div class="intextboxwrap" tabindex="0">
										<span id="privercy_txt" style="display: block;">
											<c:out value="${tcList.customerTermsContent}" escapeXml="false" />
										</span>
									</div>
								</div>								
							</div>
						
						</c:if>
						<c:if test='${status.index == 2}'>
							<div class="privercy_con">
								<div class="privercy_chk">
									<input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${tcList.customerTermsType}" value="${tcList.customerTermsContentRegSeq}" onclick="fn_termsCkBox('chkCustomerTerms_${tcList.customerTermsType}');"><label for="chk3" class="chk_box" onclick="fn_termsCk('chkCustomerTerms_${tcList.customerTermsType}');"><c:out value='${tcList.customerTermsTypeName}'/>에 동의합니다. (필수)</label>
									<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${tcList.customerTermsTypeName}'/>"/>                                     
								</div>
								<div class="textboxwrap_term2">
									<div class="intextboxwrap" tabindex="0">
										<span id="privercy_txt" style="display: block;">
											<c:out value="${tcList.customerTermsContent}" escapeXml="false" />
										</span>
									</div>
								</div>								
							</div>
						</c:if>
						<c:if test='${status.index == 2}'>
							</div>
						</c:if>
								
                	</c:forEach>
                
					</div>

					<div class="button_area">
						<a href="javascript:void(0);" id="btnTermsAgree" title="휴대전화 인증 새창 열기"  id="layer_open">휴대전화 인증</a>
					</div>
					<div class="info_box type2 mt25">
					<div class="txt">
							<ul class="list_type01">
								<li class="ft_blue">반드시 본인 명의의 휴대폰 번호로 인증하셔야 서비스 이용이 가능합니다. </li>
								<li class="ft_blue">만 14세 미만은 가입이 불가능합니다.</li>
								<li>허위기재나 타인의 정보를 도용하여 가입된 경우 사전통보 없이 즉시 탈퇴처리됩니다.</li>
								<li>정보통신망법(2012.08.18 시행)제 23조 2(주민번호 사용제한) 규정에 따라 온라인 상 주민번호의 수집/이용을 제한합니다.</li>
								<li>㈜코스콤은 주민등록번호 등 개인정보를 사용자 동의 없이 수집하지 않습니다.</li>
							</ul>
						</div>
					</div>
				</article>
				</form:form>	
                </div>
            </article>
        </div>
    </section>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
</div>

</body>
</html>