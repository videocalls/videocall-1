<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mobileCheckAuth.jsp
 * @Description : [휴대폰 본인인증]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.06.21  최판광        최초  생성
 * </pre>
 *
 * @author  
 * @since 
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

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


// 통신사 약관 조회
function fn_ChangeTermsList(){

	var compGbn = $("#smobileCo option:selected").val();

	if(compGbn == "1" || compGbn == "5"){
        $("#sktTerm").show();
        $("#ktTerm").hide();
        $("#lgTerm").hide();
	} else if(compGbn == "2" || compGbn == "6"){

        $("#sktTerm").hide();
        $("#ktTerm").show();
        $("#lgTerm").hide();
	} else if(compGbn == "3" || compGbn == "7"){

        $("#sktTerm").hide();
        $("#ktTerm").hide();
        $("#lgTerm").show();
	} else {
		
	}
	
}


// 인증번호 발송
function fn_requestSafeAuth() {


	if($("#sname").val()==""){
		alert("이름을 입력해주세요");
		return false;
	}
	if($("#birthDay").val()==""){
		alert("생년월일을 입력해주세요.");
		return false;
	}
	if($("#smobileCo").val()==""){
		alert("통신사를 선택해주세요.");
		return false;
	}   
    if($("#smobileNo").val()==""){
    	alert("휴대폰번호를 선택해주세요.");
		return false;
    }
   

    //인증번호 전송 클릭
	if($("#requestSafeAuth_btn").html() == "인증번호 전송"){
		$("#requestSafeAuth_btn").html("인증번호 재전송");
        $("#sauthNo").attr("readonly",false);

        
	} else {

		/*
		$("#requestSafeAuth_btn").html("인증번호 전송");
		
        $("#sauthNo").attr("readonly",true);

        $("#sname").val("");
        $("#birthDay").val("");
        $("#smobileCo").val("");
        $("#smobileNo").val("");
        */
        

	}
    
	
	//로딩 호출
	gfn_setLoading(true);

	//page setting  
	var url = "<c:url value='/requestSafeAuth.ajax'/>";
	var param = $("#NiceReqVO").serialize();
	var callBackFunc = "fn_requestSafeAuthCallBack";
	
	<%-- 공통 ajax 호출 --%>
	util_ajaxPage(url, param, callBackFunc);
	
}
/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popClose() {
	if (opener) {
		window.close();
	} else {

		alert("만 14세 미만은 가입이 불가능합니다.");
		
   		window.parent.fn_popClose();
		gfn_closeModal(this.event);
	}
}


function fn_requestSafeAuthCallBack(data) {

	//로딩 호출
	gfn_setLoading(false);

	if(data.iReturn != null){
		if(data.iReturn == "9127"){
			alert("만 14세 미만은 가입이 불가능합니다.");
			fn_popClose();
		}
		
	}

	if (data.niceRes != null) {
		if(data.niceRes.returnCode == "0000"){
			alert("인증번호가 전송되었습니다.");


			$('#sresSeq').val(data.niceRes.sresSeq);
			
			
				setTimer();
				var timer = window.setInterval(function(){
		        	limitTime.setSeconds(limitTime.getSeconds() - 1);
		        	if(limitTime.getMinutes() === 59){
		        		//3분 타임아웃
		        		window.clearInterval(timer);

		        		$("#requestSafeAuth_btn").html("인증번호 전송");

		    	        $("#sauthNo").val("");

		    	        /*
		                $("#sauthNo").attr("readonly",true);

		                $("#sname").val("");
		                $("#birthDay").val("");
		                $("#smobileCo").val("");
		                $("#smobileNo").val("");
						*/
		        		$("#timeChk").html("03:00" );
		        	}else{
		        		//시간 감소
		        		var mm = limitTime.getMinutes();
		        		var ss = limitTime.getSeconds();

		        		mm = "0" + mm;
		        		if(ss < 10){
		        			ss = "0" + ss;	
		        		}
		        		$("#timeChk").html(mm + ":" + ss);
		        		
		        	}
		        }, 1000);
			
			
		} else if(data.niceRes.returnCode == "0001"){
			alert("인증정보가 일치하지 않습니다. 다시 확인해주세요.");
	        $("#sauthNo").val("");

			/*
			$("#requestSafeAuth_btn").html("인증번호 전송");

	        			
	        $("#sauthNo").attr("readonly",true);

	        $("#sname").val("");
	        $("#birthDay").val("");
	        $("#smobileCo").val("");
	        $("#smobileNo").val("");
	        */
		} else if(data.niceRes.returnCode == "0012"){
			alert("잘못된 사용자 번호입니다.");
	        $("#sauthNo").val("");

	        /*
			$("#requestSafeAuth_btn").html("인증번호 전송");
			
	        $("#sauthNo").attr("readonly",true);

	        $("#sname").val("");
	        $("#birthDay").val("");
	        $("#smobileCo").val("");
	        $("#smobileNo").val("");
	        */
		} else {
			alert("관리자에게 문의해주세요");
	        $("#sauthNo").val("");

	        /*
			$("#requestSafeAuth_btn").html("인증번호 전송");
			
	        $("#sauthNo").attr("readonly",true);

	        $("#sname").val("");
	        $("#birthDay").val("");
	        $("#smobileCo").val("");
	        $("#smobileNo").val("");
	        */
		}
	} else {
		alert("관리자에게 문의해주세요");
		
	}
	
	
}


// 인증번호 확인 및 CI 조회
function fn_requestConfirm() {

	if($("#sname").val()==""){
		alert("이름을 입력해주세요");
		return false;
	}
	if($("#birthDay").val()==""){
		alert("생년월일을 입력해주세요.");
		return false;
	}
	if($("#smobileCo").val()==""){
		alert("통신사를 선택해주세요.");
		return false;
	}   
    if($("#smobileNo").val()==""){
    	alert("휴대폰번호를 선택해주세요.");
		return false;
    }

    if(util_chkReturn($('#sauthNo').val(), "s") == ""){
        alert("인증번호는 필수 입력값입니다.");
        $("#sauthNo").focus();
        return false;
    }

	if( !fn_SaveValidate() ){ //유효성검증호출
		return false;
	}

	
	//로딩 호출
	gfn_setLoading(true);

	//page setting  
	var url = "<c:url value='/requestConfirm.ajax'/>";
	var param = $("#NiceReqVO").serialize();
	var callBackFunc = "fn_requestConfirmCallBack";
	
	<%-- 공통 ajax 호출 --%>
	util_ajaxPage(url, param, callBackFunc);
	
}

function fn_requestConfirmCallBack(data) {
	
	//로딩 호출
	gfn_setLoading(false);

	if(data.iReturn != null){
		
	}

	if (data.niceRes != null) {
		if(data.niceRes.returnCode == "0000"){
			alert("인증되었습니다.");
			var ci = data.niceRes.responseCI;
			fn_authClose(ci);
		} else if(data.niceRes.returnCode == "0001"){
			alert("인증정보가 일치하지 않습니다. 다시 확인해주세요.");
		} else if(data.niceRes.returnCode == "0012"){
			alert("잘못된 사용자 번호입니다.");
		} else {
			alert("관리자에게 문의해주세요");
		} 
        
	} else {
		alert("관리자에게 문의해주세요");
		
	}
	
}

/**
 * 본인 확인 서비스 이용약관 밸리데이션
**/	
function fn_SaveValidate(){
    
    var TermChkCnt = 0;
    var compGbn = $("#smobileCo option:selected").val();

    var mobileComp = "";
	
	if(compGbn == "1" || compGbn == "5"){
		mobileComp = "chkSktCustomerTerms";
	} else if(compGbn == "2" || compGbn == "6"){
		mobileComp = "chkKtCustomerTerms";
	} else if(compGbn == "3" || compGbn == "7"){
		mobileComp = "chkLgCustomerTerms";
	} else {
		
	}
	
    $("input[name='"+mobileComp+"']").each(function(idx){
        if($(this).is(":checked")){
            TermChkCnt++;
        }else{
            var alertMsg = $("#txtTermsTitle_"+idx).val();
            alert(alertMsg+"를 선택하세요.");
            return false;
        }
    });
    
    if(TermChkCnt == $("input[name='"+mobileComp+"']").length){
        return true;
    }else{
        return false;
    }
    
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_authClose(ci) {
	if (opener) {
		window.close();
	} else {
		var sex = $("input[name='sex']:checked").val();
		var birthDay = $("#birthDay").val();
		var smobileNo = $("#smobileNo").val();
		var sname = $("#sname").val();
		
   		window.parent.checkCi(sex, birthDay, smobileNo, sname, ci);
		gfn_closeModal(this.event);
	}
}

function fn_allCk(){


    var compGbn = $("#smobileCo option:selected").val();

    var mobileComp = "";
	
	if(compGbn == "1" || compGbn == "5"){
		mobileComp = "chkSktCustomerTerms";
	} else if(compGbn == "2" || compGbn == "6"){
		mobileComp = "chkKtCustomerTerms";
	} else if(compGbn == "3" || compGbn == "7"){
		mobileComp = "chkLgCustomerTerms";
	} else {
		
	}
	
	
    if($(this).is(":checked")){
        $("input[name='"+mobileComp+"']").each(function(){
            $(this).prop("checked",true); 
        });
        
        $("#allchkCustomerTerms").prop("checked",true);
    }else{
        $("input[name='"+mobileComp+"']").each(function(){
            $(this).prop("checked",false);                
        });
        
        $("#allchkCustomerTerms").prop("checked",false);
    }
}


function fn_termsCk(chkid){
    if($("#"+chkid+"").is(":checked")){
    	$("#"+chkid+"").prop("checked",false); 
    } else {
    	$("#"+chkid+"").prop("checked",true);
    }
    

    var compGbn = $("#smobileCo option:selected").val();

    var mobileComp = "";
	
	if(compGbn == "1" || compGbn == "5"){
		mobileComp = "chkSktCustomerTerms";
	} else if(compGbn == "2" || compGbn == "6"){
		mobileComp = "chkKtCustomerTerms";
	} else if(compGbn == "3" || compGbn == "7"){
		mobileComp = "chkLgCustomerTerms";
	} else {
		
	}

   	var chNum =0;
       $("input[name='"+mobileComp+"']").each(function(){
           if($(this).is(":checked")){
           	chNum = chNum+1;	
           } 
       });

   	if(chNum > 3){
   		$("#all_ck").prop("checked",true);
   	} else {
   		$("#all_ck").prop("checked",false);
   	}
   	
}


function fn_termsCkBox(chkid){

    var compGbn = $("#smobileCo option:selected").val();

    var mobileComp = "";
	
	if(compGbn == "1" || compGbn == "5"){
		mobileComp = "chkSktCustomerTerms";
	} else if(compGbn == "2" || compGbn == "6"){
		mobileComp = "chkKtCustomerTerms";
	} else if(compGbn == "3" || compGbn == "7"){
		mobileComp = "chkLgCustomerTerms";
	} else {
		
	}

   	var chNum =0;
       $("input[name='"+mobileComp+"']").each(function(){
           if($(this).is(":checked")){
           	chNum = chNum+1;	
           } 
       });

   	if(chNum > 3){
   		$("#all_ck").prop("checked",true);
   	} else {
   		$("#all_ck").prop("checked",false);
   	}
}

/* 화면 로드 처리 */
$(document).ready(function(){
    	
    $("#smobileCo").change(function(){
               fn_ChangeTermsList();
    });

    $("#sauthNo").attr("readonly",true);
    
    
    
    $(".allCheck").bind("change", function(e){
    	

        var compGbn = $("#smobileCo option:selected").val();

        var mobileComp = "";
    	
    	if(compGbn == "1" || compGbn == "5"){
    		mobileComp = "chkSktCustomerTerms";
    	} else if(compGbn == "2" || compGbn == "6"){
    		mobileComp = "chkKtCustomerTerms";
    	} else if(compGbn == "3" || compGbn == "7"){
    		mobileComp = "chkLgCustomerTerms";
    	} else {
    		
    	}
    	
    	
        if($(this).is(":checked")){
            $("input[name='"+mobileComp+"']").each(function(){
                $(this).prop("checked",true); 
            });
            
            $("#allchkCustomerTerms").prop("checked",true);
        }else{
            $("input[name='"+mobileComp+"']").each(function(){
                $(this).prop("checked",false);                
            });
            
            $("#allchkCustomerTerms").prop("checked",false);
        }
    });
    

    $("#requestConfirm").bind("click", function(e){
    	fn_requestConfirm();
    });
    
    

    
});


function setTimer(){
	limitTime = new Date();
    limitTime.setHours(0);
    limitTime.setMinutes(3);
    limitTime.setSeconds(0);
}

</script>
</head>
<body>
<div class="wrap">
    <div class="layer_popup_dev">
        <div class="dimm" style="display:none;"></div>
        <!-- #layer01 -->
		<div class="layer_box" id="layer01" style="width:620px;">
			<div class="layer_tit">휴대폰 인증</div>
			<form:form commandName="NiceReqVO" name="NiceReqVO" method="post">
				<input type="hidden" id="sresSeq" name="sresSeq" value="" />
	            <div class="layer_con" style="min-height:560px;">
					<div class="cont_pop">
						<div class="personal_info">
							<ul class="input_list">
								<li>
									<div class="cell txt">이름</div>
									<div class="cell"><input type="text" id="sname" name="sname" /></div>
									<div class="cell last">
										<ul class="list_radio">
											<li>
												<input type="radio" id="native" name="nationality" value="Y" checked="">
												<label for="native"><span>내국인</span></label>
											</li>
											<li>
												<input type="radio" id="foreigner" name="nationality" value="N">
												<label for="foreigner"><span>외국인</span></label>
											</li>
										</ul>
									</div>
								</li>
								<li>
									<div class="cell txt">생년월일</div>
									<div class="cell"><input type="text" id="birthDay" name="birthDay"  placeholder="예) 19990216"  onkeydown="util_numberonly(event);" /></div>
									<div class="cell last">
										<ul class="list_radio">
											<li>
												<input type="radio" id="M" name="sex" value="M" checked="">
												<label for="M"><span>남자</span></label>
											</li>
											<li>
												<input type="radio" id="F" name="sex" value="F">
												<label for="F"><span>여자</span></label>
											</li>
										</ul>
									</div>
								</li>
								<li>
									<div class="cell txt">통신사</div>
									<div class="cell">
										<select id="smobileCo" name="smobileCo" style="min-width:80px;">
										        <option value="">선택</option>
										    <c:forEach var="mobileCompList" items="${mobileCompList}" varStatus="status">
										        <option value="${mobileCompList.code_name_eng}">${mobileCompList.code_name_kor}</option>
										    </c:forEach>
										</select>
									</div>
									<div class="cell last"></div>
								</li>
								<li class="phone">
									<div class="cell txt">휴대폰번호</div>
									<div class="cell">
										<input type="text" name="smobileNo" id="smobileNo" placeholder="‘-’없이 입력"  onkeydown="util_numberonly(event);" />
									</div>
									<div class="cell last"><button type="button" class="btn_gray" id="requestSafeAuth_btn" onclick="fn_requestSafeAuth();">인증번호 전송</button></div>
								</li>
								<li>
									<ul class="list_style_01">
										<li>휴대폰번호를 입력하신 후, 인증번호 전송 버튼을 클릭해주세요.</li>
									</ul>
								</li>
								<li>
									<div class="cell txt">인증번호 입력</div>
									<div class="cell"><input type="text" maxlength="6" name="sauthNo" id="sauthNo" placeholder="인증번호를 입력하세요." value="" onkeydown="util_numberonly(event);" /></div>
									<div class="cell last time" id="timeChk">03:00</div>
								</li>
							</ul>
						</div>
	
						<p class="title_pop mt30">본인 확인 서비스 이용약관</p>
						<div class="agreement_area"> <!-- 약관동의 -->
							<!-- 전체동의 -->
							<div class="all_check">
								<div class="form_input"><input type="checkbox" class="allCheck"  id="all_ck" name="all_ck"><label for="all_ck" onclick="fn_allCk();">전체동의</label></div>
							</div>
							<div class="accordian-menu"  id="sktTerm" name="sktTerm"  style="display:none;">
							
								<c:forEach var="skTermsList" items="${skTermsList}" varStatus="status">
									<div class="accordian-cont cont_wrap">
										<a href="#none" class="btn_bar"></a>
										<div class="detail_head"><input type="checkbox" name="chkSktCustomerTerms" id="chkSktCustomerTerms_${status.index}" onclick="fn_termsCkBox('chkSktCustomerTerms_${status.index}');"><label for="agree1" onclick="fn_termsCk('chkSktCustomerTerms_${status.index}');"><c:out value='${skTermsList.mobileTermsSubject}'/></label></div>
										<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${skTermsList.mobileTermsSubject}'/>"/>
										<div class="detail_cont">
											<div class="textboxwrap">
												<div class="intextboxwrap" tabindex="0">
													<span id="agree_skt" style="display: block;">
															<c:out value="${skTermsList.mobileTermsContent}"  escapeXml="false"/>
													</span>
												</div>
											</div>	
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="accordian-menu"  id="ktTerm" name="ktTerm"  style="display:none;">
							
								<c:forEach var="ktTermsList" items="${ktTermsList}" varStatus="status">
									<div class="accordian-cont cont_wrap">
										<a href="#none" class="btn_bar"></a>
										<div class="detail_head"><input type="checkbox" name="chkKtCustomerTerms" id="chkKtCustomerTerms_${status.index}" onclick="fn_termsCkBox('chkKtCustomerTerms_${status.index}');"><label for="agree1" onclick="fn_termsCk('chkKtCustomerTerms_${status.index}');"><c:out value='${ktTermsList.mobileTermsSubject}'/></label></div>
										<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${ktTermsList.mobileTermsSubject}'/>"/>
										<div class="detail_cont">
											<div class="textboxwrap">
												<div class="intextboxwrap" tabindex="0">
													<span id="agree_kt" style="display: block;">
															<c:out value="${ktTermsList.mobileTermsContent}" escapeXml="false"/>
													</span>
												</div>
											</div>											
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="accordian-menu"  id="lgTerm" name="lgTerm"  style="display:none;">
							
								<c:forEach var="lgTermsList" items="${lgTermsList}" varStatus="status">
									<div class="accordian-cont cont_wrap">
										<a href="#none" class="btn_bar"></a>
										<div class="detail_head"><input type="checkbox" name="chkLgCustomerTerms" id="chkLgCustomerTerms_${status.index}" onclick="fn_termsCkBox('chkLgCustomerTerms_${status.index}');"><label for="agree1" onclick="fn_termsCk('chkLgCustomerTerms_${status.index}');"><c:out value='${lgTermsList.mobileTermsSubject}'/></label></div>
										<input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${lgTermsList.mobileTermsSubject}'/>"/>
										<div class="detail_cont">
											<div class="textboxwrap">
												<div class="intextboxwrap" tabindex="0">
													<span id="agree_lgt" style="display: block;">
															<c:out value="${lgTermsList.mobileTermsContent}" escapeXml="false"/>
													</span>
												</div>
											</div>											
										</div>
									</div>
								</c:forEach>
							</div>
							
<script type="text/javascript">
	// 자기자신만 열고 닫기
	var $accordianMenu = $( '.accordian-menu .accordian-cont > .btn_bar' );
	   $accordianMenu.on( 'click', function () {
		  //var index = $( this ).index();
	   if ( $( this ).parent().hasClass( 'active' )) {
		  $( this ).parent().removeClass( 'active' );
	   } else {
		  $( this ).parent().removeClass( 'active' );
		  $( this ).parent().addClass( 'active' );
	   }
	   return false;
	});
</script>
						</div>
						<div class="txt_error hide_set">약관에 모두 동의하셔야 됩니다.</div> <!-- add class : hide_set : display:none; 처리  -->
						<div class="btn_area">
							<a href="javascript:void(0);" id="requestConfirm" class="btn_type5">인증하기</a>
						</div>
					</div>
	            </div>
			</form:form>
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>
</div>

</body>
</html>