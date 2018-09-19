<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptMyInfo.jsp
 * @Description : [기업회원정보] 등록수정
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.30  유제량        최초  생성 
 * </pre>
 *
 * @author 포털 유제량 
 * @since 2016.06.30
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var operatorPhoneNo = '<c:out value="${paramVO.operatorPhoneNo}" />';
var operatorEmail = '<c:out value="${paramVO.operatorEmail}" />';
var companyBizregNo = '<c:out value="${paramVO.companyBizregNo}" />';

/*******************************************
 * 기능 함수
 *******************************************/

/* [개인정보변경]확인 유효성 검증 */
function fn_InfoChangeConfrm(){
	var userId = $("#userId").val(); //사용자의 아이디
	var operatorPassword1 = $("#operatorPassword1").val(); //비밀번호
    var operatorPassword2 = $("#operatorPassword2").val(); //비밀번호확인
    var operatorEmail1 = $("#operatorEmail1").val(); //이메일앞자리
    var operatorEmail2_txt = $("#operatorEmail2_txt").val(); //이메일뒷자리
    var operatorPhoneNo1 = $("#operatorPhoneNo1").val(); //전화번호 국번자리
    var operatorPhoneNo2 = $("#operatorPhoneNo2").val(); //전화번호 가운데 자리
    var operatorPhoneNo3 = $("#operatorPhoneNo3").val(); //전화번호 맨 뒷자리
    
    if(operatorPassword1 != "" || operatorPassword2 != ""){
    	if(!gfn_validationCheckPw("operatorPassword1")){
            return false;
        }
        
        //[비밀번호확인]
        if(util_chkReturn(operatorPassword2, "s") == ""){
            alert("<spring:message code='errors.required' arguments='비밀번호확인'/>");
            $("#operatorPassword2").focus();
            return false;
        }
        
        //[비밀번호]!=[비밀번호확인]
        if(operatorPassword1 != operatorPassword2){
            alert("<spring:message code='errors.notsame' arguments='비밀번호,비밀번호확인'/>");
            return false;
        }
        
        //[비밀번호]가 [아이디]와 동일한지 체크
        if(userId == operatorPassword1) {
            alert("아이디 와 비밀번호가 동일합니다.\r\n비밀번호를 다시 입력하십시오.");
            $("#operatorPassword1").focus();
            return false;
        }
        
        //[비밀번호]에 [아이디]가 포함되어 있는지 체크
        if(userId.substring(0,4) == operatorPassword1.substring(0,4)){
            alert("아이디 와 비밀번호 앞4자리가 동일 합니다.\r\n비밀번호를 다시 입력하십시오.");
            $("#operatorPassword1").focus();
            return false;
        }
    }
    
    //[이메일앞자리]
    if(util_chkReturn(operatorEmail1, "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일앞자리'/>");
        $("#operatorEmail1").focus();
        return false;
    }
    
    //[이메일앞자리]정규식
    if( !util_isEmail(operatorEmail1,'1') ){
        alert("<spring:message code='errors.invalid' arguments='이메일앞자리'/>");
        $("#operatorEmail1").focus();
        return false;
    }
    
    //[이메일뒷자리]
    if(util_chkReturn(operatorEmail2_txt, "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일뒷자리'/>");
        $("#operatorEmail2_txt").focus();
        return false;
    }
    
    //[이메일뒷자리]정규식
    if( !util_isEmail(operatorEmail2_txt,'2') ){
        alert("<spring:message code='errors.invalid' arguments='이메일뒷자리'/>");
        $("#operatorEmail2_txt").focus();
        return false;
    }
    
    //[휴대폰번호1]
    if(util_chkReturn(operatorPhoneNo1, "s") == ""){
        alert("<spring:message code='errors.required.select' arguments='휴대폰번호 첫번째자리'/>");
        $("#operatorPhoneNo1").focus();
        return false;
    }
    
    //[휴대폰번호2]
    if(util_chkReturn(operatorPhoneNo2, "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰번호 두번째자리'/>");
        $("#operatorPhoneNo2").focus();
        return false;
    }
    
    //[휴대폰번호3]
    if(util_chkReturn(operatorPhoneNo3, "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰번호 세번째자리'/>");
        $("#operatorPhoneNo3").focus();
        return false;
    }
    
    //사업자 등록번호
    var bizno = util_replaceAll($('#companyBizregNo').val(), "-", "");
    if(util_chkReturn(bizno, "s") != "") {
    	if(bizno.length != 10){
    		alert("<spring:message code='errors.invalid' arguments='사업자 등록번호'/>");
            $("#companyBizregNo").focus();
            return false;
    	}else{
    		if(!util_isNum(bizno)){
                alert("<spring:message code='errors.integer' arguments='사업자 등록번호'/>");
                $('#companyBizregNo').focus();
                return false;           
            }
    	}
    }
    
    //대표자명
    if(util_chkReturn($.trim($('#ceoName').val()), "s") != "") {
        if(util_calcBytes($('#ceoName').val()) > 64){
        	alert("<spring:message code='errors.maxlength' arguments='대표자명,32'/>");
            $("#ceoName").focus();
            return false;
        }
    }
    
    //우편번호
    if(util_chkReturn($.trim($('#companyPostNo').val()), "s") != "") {
        if(!util_isNum($.trim($('#companyPostNo').val()))){
            alert("<spring:message code='errors.integer' arguments='우편번호'/>");
            $('#companyPostNo').focus();
            return false;           
        }
    }
    
    //주소
    if(util_chkReturn($.trim($('#companyAddress').val()), "s") != "") {
        if(util_calcBytes($('#companyAddress').val()) > 255){
            alert("<spring:message code='errors.maxlength' arguments='주소,120'/>");
            $("#companyAddress").focus();
            return false;
        }
    }
        
    return true;    
}

/* [개인정보변경]비밀번호 변경 정책(동일 비밀번호 거부 처리) */
function fn_PwChangeConfrm(){
    var url = "<c:url value='/cpt/myp/cptMyInfo/checkPw.ajax'/>";        
    var operatorPassword1 = $("#operatorPassword1").val();
    var operatorPassword2 = $("#operatorPassword2").val(); //비밀번호확인
    
    var objParam = new Object();    
    objParam.operatorPassword = operatorPassword1;
    
    //마이페이지 회원정보에서 비밀번호와 비밀번호확인을 변경하는 것은 필수입력사항이 아니기 때문에
    if(operatorPassword1 != "" || operatorPassword2 != ""){ //회원이 비밀번호를 변경하기 위해 비밀번호란에 입력한 경우
        //로딩start
        gfn_setLoading(true, "비밀번호를 확인 중입니다.");
        
        $.ajax({
            type    : "post"
           ,url     : url
           ,data    : objParam
           ,success : function(data){
               //로그인 처리
               if(data.error == -1){
                   fn_login();
                   return;
               }
               
               //로딩end
               gfn_setLoading(false);
               
               var checkPw = data.checkPw;  
               if(checkPw == "1"){
                   alert("비밀번호가 기존 비밀번호와 일치합니다.\n다른 비밀번호로 변경하여 주시기 바랍니다.");
                   $("#operatorPassword1").val("");
                   $("#operatorPassword2").val("");
                   $("#operatorPassword1").focus();                   
               }else{
                   //성공시 회원정보를 수정합니다.
                   fn_InfoChange();
               }
           }
           ,error   : function(){
               //로딩end
               gfn_setLoading(false);
               //alert("정보수정 실패");               
           }
       });
    }else{
        //회원정보를 수정합니다.
        fn_InfoChange();
    }    
}

/* [개인정보변경]개인정보변경 처리 */
function fn_InfoChange(){    
	var msgConfirm = "회원정보를 "+"<spring:message code='confirm.modify.msg'/>"; //"수정하시겠습니까?"
    if( confirm(msgConfirm) ){
         var url = "<c:url value='/cpt/myp/cptMyInfo/updateCptMyInfo.ajax'/>";
         var userId = $("#userId").val();
         var companyProfileRegNo = $("#companyProfileRegNo").val();
         var operatorProfileRegNo = $("#operatorProfileRegNo").val();
         var operatorPassword1 = $("#operatorPassword1").val();
         var operatorPassword2 = $("#operatorPassword2").val();
         var operatorEmail1 = $("#operatorEmail1").val();
         var operatorEmail2_txt = $("#operatorEmail2_txt").val();
         var operatorPhoneNo1 = $("#operatorPhoneNo1").val();
         var operatorPhoneNo2 = $("#operatorPhoneNo2").val();
         var operatorPhoneNo3 = $("#operatorPhoneNo3").val();            
         
         var objParam = new Object();
         objParam.operatorId = userId;
         objParam.operatorPassword = operatorPassword1;
         
         objParam.operatorEmail    = operatorEmail1 + "@" + operatorEmail2_txt;
         objParam.operatorPhoneNo   = operatorPhoneNo1+"-"+operatorPhoneNo2+"-"+operatorPhoneNo3;             
         
         //기업정보
         objParam.companyBizregNo   = util_replaceAll($('#companyBizregNo').val(), "-", "");
         objParam.ceoName           = $("#ceoName").val();
         objParam.companyPostNo     = $("#companyPostNo").val();
         objParam.companyAddress    = $("#companyAddress").val();
         
         //로딩start
         gfn_setLoading(true, "회원정보를 변경 중입니다.");
         
         $.ajax({
             type    : "post"
            ,url     : url
            ,data    : objParam
            ,success : function(data){
                //로딩end
                gfn_setLoading(false);
                
                var resultDetail = data.resultDetail;                   
                fn_moveList(resultDetail);
            }
            ,error   : function(){
                //로딩end
                gfn_setLoading(false);
                alert("정보수정 실패");
            }
        });
         
    }
}

/* [휴대폰번호]셋팅 함수 */
function fn_setValphoneNumber(pNumber){
 	if(util_chkReturn(pNumber, "s") == ""){
 		alert('휴대폰번호가 없습니다.');
 		return false;
 	}
	var operatorPhoneNo1 = '';
	var operatorPhoneNo2 = '';
	var operatorPhoneNo3 = '';
 	var pNumber_arr = pNumber.split("-");
 	if(pNumber_arr.length == 3){
 		operatorPhoneNo1 = pNumber_arr[0];
 		operatorPhoneNo2 = pNumber_arr[1];
 		operatorPhoneNo3 = pNumber_arr[2];
 	}else{
 		if(pNumber.length == 10){ //ex)0104568901
 			operatorPhoneNo1 = pNumber.substring(0,3);
 			operatorPhoneNo2 = pNumber.substring(3,6);
 			operatorPhoneNo3 = pNumber.substring(6);
 		}else if(pNumber.length == 11){//ex)01045678901
 			operatorPhoneNo1 = pNumber.substring(0,3);
 			operatorPhoneNo2 = pNumber.substring(3,7);
 			operatorPhoneNo3 = pNumber.substring(7);
 		}
 	}
 	$("#operatorPhoneNo1").val(operatorPhoneNo1);
 	$("#operatorPhoneNo2").val(operatorPhoneNo2);
 	$("#operatorPhoneNo3").val(operatorPhoneNo3);
}
 
/* [이메일]셋팅 함수 */
function fn_setValemail(pEmail){
	var pEmail_arr = pEmail.split("@");	 
	$("#operatorEmail1").val(pEmail_arr[0]);
	$("#operatorEmail2_txt").val(pEmail_arr[1]);
}

/* [사업자등록번호]셋팅 함수 */
function fn_setValcompanyBizregNo(pCompanyBizregNo){
	var companyBizregNo = '';
	var companyBizregNo1 = '';
	var companyBizregNo2 = '';
	var companyBizregNo3 = '';	
	companyBizregNo1 = pCompanyBizregNo.substring(0,3);
	companyBizregNo2 = pCompanyBizregNo.substring(3,5);
	companyBizregNo3 = pCompanyBizregNo.substring(5);
	companyBizregNo   = companyBizregNo1+"-"+companyBizregNo2+"-"+companyBizregNo3;
    $("#companyBizregNo").val(companyBizregNo);
}

/*******************************************
 * 이벤트 함수
 *******************************************/

/* 이동함수 */
function fn_moveList(resultDetail){
	alert("개인 정보 변경에 따라 로그아웃 하겠습니다. 다시 로그인 하여 주십시오");
	var operatorPassword1 = $("#operatorPassword1").val(); //비밀번호
    var operatorPassword2 = $("#operatorPassword2").val(); //비밀번호확인
	var objParam = new Object();
	//(6/19 이민우 상무님)정보 수정 경우 '개인 정보 변경에 따라 로그아웃 하겠습니다. 다시 로근인 하여 주십시오' 등으로 alert 띄우고, 비밀 번호 변경은 로그인 페이지 기타 변경은 마이페이지로 이동이 맞음
	if(operatorPassword1 != "" || operatorPassword2 != ""){
		if(resultDetail.operatorPasswordYn == "Y"){ //기타변경
	        //메인페이지로 이동(기타변경의 경우 마이페이지로 이동할 것을 말씀하셨지만 마이페이지는 로그인이 필요한 페이지라서 메인페이지로 보냈습니다.)
	        var uri = '<c:url value="/cpt/cmm/mainView.do"/>';
	        document.location.href = uri;
	    }else{ //비밀번호변경
	        //로그인페이지로 이동
	        var uri = '<c:url value="/cpt/cmm/loginView.do"/>';
	        document.location.href = uri;           
	    }	
	}else{
		//메인페이지로 이동 - operatorPassword1가 공백값이라면 operatorPasswordYn 값이 있을 수 없고 기타정보를 변경한 상황입니다.
        var uri = '<c:url value="/cpt/cmm/mainView.do"/>';
        document.location.href = uri;
	}
} 

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cpt/myp/cptMyInfo/cptMyPwConfrm.do'/>";
    var param = new Object();
    param.paramMenuId = "05006";
    
    gfn_loginNeedMove(url, param);
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
		return;
    </c:if>
    
	/* [이메일]셀렉트박스 변경 시 호출 */
	$("#operatorEmail2_slt").change(function(){
		if($(this).val()==""){ //[직접입력]인 경우
			$("#operatorEmail2_txt").val("");
			$("#operatorEmail2_txt").attr("disabled",false);
		}else{
			$("#operatorEmail2_txt").val($(this).val());
			$("#operatorEmail2_txt").attr("disabled",true);
		}
	});
	
    /* [취소]버튼 클릭 시 호출 */
    $("#btnNextChange").click(function(){
    	var uri = '<c:url value="/cpt/cmm/mainView.do"/>';
        document.location.href = uri;
    });
	
	/* [정보수정]버튼 클릭 시 호출 */
    $("#btnInfoChange").click(function(){
    	if( !fn_InfoChangeConfrm() ){ //유효성검증호출
            return false;
        }    	
    	fn_PwChangeConfrm(); //2016.07.07 이준형 과장님의 비밀번호 변경 정책(동일 비밀번호 거부 처리) 적용    	
    });
	
	fn_setValphoneNumber(operatorPhoneNo); //휴대폰번호 셋팅
	fn_setValemail(operatorEmail); //이메일주소 셋팅
	fn_setValcompanyBizregNo(companyBizregNo); //사업자등록번호 셋팅
	
});


</script>
</head>
<body>

<input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />
<input type="hidden" id="companyProfileRegNo" name="companyProfileRegNo" value="${ LoginVO.company_profile_reg_no }" />
<input type="hidden" id="operatorProfileRegNo" name="operatorProfileRegNo" value="${ LoginVO.operator_profile_reg_no }" />

<div class="wrap">

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">회원정보</li>
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
                    <h3>회원정보</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="title_wrap">
                        <p class="title_01">기업 회원정보</p>
                        <p class="right"><span class="icon_basic">*</span><span class="point02">필수사항 입니다</span></p>
                    </div>
                    
                    <!-- tbtype_form -->
                    <table class="tbtype_form">
                        <caption>회사명, 회사코드, 아이디, 비밀번호, 비밀번호 확인, 담당자명, 부서명, 담당자 이메일, 담당자 휴대폰, 사업자 등록번호, 분류, 대표자 명, 회사 주소, 등록일 정보 및 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:30%;">
                            <col style="width:20%;">
                            <col style="width:30%;">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th scope="row">회사명</th>
	                            <td><c:out value="${paramVO.companyNameKor}" /></td>
	                            <th scope="row">회사코드</th>
	                            <td><c:out value="${paramVO.companyCodeId}" /></td>
	                        </tr>
	                        <tr>
	                            <th scope="row">아이디</th>
	                            <td colspan="3"><c:out value="${paramVO.operatorId}" /></td>
	                        </tr>
	                        <tr>
	                            <th scope="row"><label for="operatorPassword1">비밀번호</label></th>
	                            <td colspan="3">
	                                <input type="password" id="operatorPassword1" style="width:238px;" tabindex="1"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#operatorPassword2').focus();" 
                                           value=""
	                                />
	                                <span class="info_msg">8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row"><label for="operatorPassword2">비밀번호 확인</label></th>
	                            <td colspan="3">
	                                <input type="password" id="operatorPassword2" style="width:238px;" tabindex="2"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#operatorEmail1').focus();" 
                                           value=""
	                                />
	                                <span class="info_msg">사용자의 아이디와 동일한 비밀번호는 사용불가</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">담당자명</th>
	                            <td colspan="3"><c:out value="${paramVO.operatorNameKor}" /></td>	                            
	                        </tr>
	                        <tr>
	                            <th scope="row"><label for="user_email">담당자 이메일<span class="icon_basic">*필수입력</span></label></th>
	                            <td colspan="3">	                                
	                                <input type="hidden" name="operatorEmail" id="operatorEmail" />
                                    <input type="text" name="operatorEmail1" id="operatorEmail1" class="text" maxlength="20" style="width:154px;" tabindex="3"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#operatorEmail2_txt').focus();"
                                    />
                                    <span class="dot">@</span>
                                    <input type="text" name="operatorEmail2_txt" id="operatorEmail2_txt" class="text" title="이메일주소뒷부분" maxlength="20" style="width:174px;" tabindex="4"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#operatorPhoneNo1').focus();" 
                                    />
                                    <span class="sel_box1">                                        
                                        <select name="operatorEmail2_slt" id="operatorEmail2_slt" title="이메일주소 선택">
                                            <option value="">직접입력</option>
                                            <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                                <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                        <!-- <input type="hidden" name="operatorEmail2" id="operatorEmail2" /> -->
                                    </span>                                 
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row"><label for="user_phone">담당자 휴대폰<span class="icon_basic">*필수입력</span></label></th>
	                            <td colspan="3">	                                
	                                <span class="sel_box1">                                        
                                        <input type="hidden" name="operatorPhoneNo" id="operatorPhoneNo" value="<c:out value="${paramVO.operatorPhoneNo}" />" title="휴대폰번호 첫자리 선택"/>
                                        <select name="operatorPhoneNo1" id="operatorPhoneNo1" title="핸드폰번호 앞자리" tabindex="5">
                                            <option value="">선택</option>
                                            <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                                <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                    </span> 
                                    <span class="dot">-</span>
                                    <input type="text" name="operatorPhoneNo2" id="operatorPhoneNo2" class="text num_only" title="핸드폰번호 중간자리" maxlength="4" style="width:97px;" tabindex="6" 
                                           onkeydown="util_numberonly(event); javascript:if(event.keyCode == 13) $('#operatorPhoneNo3').focus();"
                                    />
                                    <span class="dot">-</span>                                    
                                    <input type="text" name="operatorPhoneNo3" id="operatorPhoneNo3" class="text num_only" title="핸드폰번호 끝자리" maxlength="4" style="width:97px;" tabindex="7"
                                           onkeydown="util_numberonly(event); javascript:if(event.keyCode == 13) $('#companyBizregNo').focus();" 
                                    />
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">사업자 등록번호</th>
	                            <td>
	                                <input type="text" name="companyBizregNo" id="companyBizregNo" class="text" maxlength="12" style="width:154px;" tabindex="8"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#ceoName').focus();"  
	                                       value="${paramVO.companyBizregNo}" 
	                                />	                                
	                            </td>
	                            <th scope="row">분류</th>
	                            <td><c:out value="${paramVO.companyDivCode}" /></td>
	                        </tr>
	                        <tr>
	                            <th scope="row">대표자 명</th>
	                            <td colspan="3">
	                                <input type="text" name="ceoName" id="ceoName" class="text" style="width:154px;" tabindex="9"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#companyPostNo').focus();"  
	                                       value="${paramVO.ceoName}" 
	                                />
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">회사 주소</th>
	                            <td colspan="3">
	                                <input type="text" id="companyPostNo" name="companyPostNo" maxlength="5" style="width: 60px;" tabindex="10"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#companyAddress').focus();"  
	                                       value="${paramVO.companyPostNo}" 
	                                />&nbsp;
	                                <input type="text" id="companyAddress" name="companyAddress" style="width: 450px" tabindex="11"
                                           onkeydown="javascript:if(event.keyCode == 13) btnInfoChange.click();"   
	                                       value="${paramVO.companyAddress}"  
	                                />
	                            </td>
	                        </tr>
	                        <tr>
	                            <th scope="row">등록일</th>
	                            <td colspan="3"><c:out value="${paramVO.companyCreateDate}" /></td>
	                        </tr>
	                        <tr>
                                <th scope="row">수정일</th>
                                <td colspan="3"><c:out value="${paramVO.companyUpdateDate}" /></td>
                            </tr>    
                        </tbody>
                    </table>
                    <!-- // tbtype_form -->
                    
                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnNextChange" class="btn_type2">취소</a>
                        <a href="javascript:void(0);" id="btnInfoChange" class="btn_type1">정보수정</a>
                    </div>
                    <!-- // btn_area -->
                    
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