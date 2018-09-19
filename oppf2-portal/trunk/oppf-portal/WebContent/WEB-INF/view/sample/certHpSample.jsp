<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sampleModalPopup.jsp
 * @Description : [샘플:모달팝업] 조회
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

<script language="javascript" type="text/javascript">

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
 
//화면 로드 처리
$(document).ready(function(){
	
	/* [인증번호발송] 이벤트발생 시 호출 */
	$("#btnCertHpReq").bind("click", function(e){
		
	    var customerPhone1 = $("#customerPhone1").val(); //휴대폰번호1
	    var customerPhone2 = $("#customerPhone2").val(); //휴대폰번호2
	    var customerPhone3 = $("#customerPhone3").val(); //휴대폰번호3
		
	    //[휴대폰번호1]
	    if(util_chkReturn(customerPhone1, "s") == ""){
	        alert("<spring:message code='errors.required.select' arguments='휴대폰번호 첫번째자리'/>");
	        $("#customerPhone1").focus();
	        return false;
	    }
	    
	    //[휴대폰번호2]
	    if(util_chkReturn(customerPhone2, "s") == ""){
	        alert("<spring:message code='errors.required' arguments='휴대폰번호 두번째자리'/>");
	        $("#customerPhone2").focus();
	        return false;
	    }
	    
	    //[휴대폰번호3]
	    if(util_chkReturn(customerPhone3, "s") == ""){
	        alert("<spring:message code='errors.required' arguments='휴대폰번호 세번째자리'/>");
	        $("#customerPhone3").focus();
	        return false;
	    }
		
		var certResNo1 = Math.floor(Math.random() * 10);
		var certResNo2 = Math.floor(Math.random() * 10);
		var certResNo3 = Math.floor(Math.random() * 10);
		var certResNo4 = Math.floor(Math.random() * 10);
		var certResNo5 = Math.floor(Math.random() * 10);
		var certResNo6 = Math.floor(Math.random() * 10);
		var certResNo = certResNo1 +''+certResNo2+''+certResNo3+''+certResNo4+''+certResNo5+''+certResNo6;
		alert('인증번호:'+certResNo);
		$("#userCertNo").val(certResNo);
		$("#certResNo").val(certResNo);
		$("#certReqYn").val("Y");
	});
	
	/* [취소] 이벤트발생 시 호출 */
	$("#btnCancel").bind("click", function(e){
		self.close();
	});
	
	/* [확인] 이벤트발생 시 호출 */
	$("#btnOk").bind("click", function(e){
		var certReqYn = $("#certReqYn").val(); //휴대폰인증유무 
		if(certReqYn != 'Y'){
			alert('[인증번호] 입력후 [인증번호발송] 버튼을 클릭 하세요.');
			$("#customerPhone2").focus();
			return false;
		}
		
		var userCertNo = $("#userCertNo").val(); //유저입력인증번호 
		if(util_chkReturn(userCertNo, "s") == ""){
	        alert("<spring:message code='errors.required.select' arguments='인증번호'/>");
	        $("#userCertNo").focus();
	        return false;
		}
		
		var certResNo = $("#certResNo").val();
		var userCertNo = $("#userCertNo").val();
		alert('certResNo='+certResNo+',userCertNo='+userCertNo);
		if(certResNo == userCertNo){
			alert('인증이 완료되었습니다.');
			$("#certSucYn").val("Y");
			var customerPhone = $("#customerPhone1").val() +'-'+ $("#customerPhone2").val() +'-'+ $("#customerPhone3").val();
			var customerNameKor = $("#customerNameKor").val();
			var customerVerify = certResNo; //가라검증값
			customerVerify = customerPhone;
// 			customerVerify = 'cn='+$("#customerNameKor").val()+'-2060107,ou=HTS,ou=삼성,ou=증권,o=SignKorea,c=KR';
			window.opener.fn_certHpResult(customerVerify,customerPhone,customerNameKor);
			self.close();
		}else{
			alert('인증이 실패되었습니다.\n다시 입력해 주세요.');
			$("#certSucYn").val("N");
			$("#userCertNo").val("");
		}
	});
	
	
});
</script>
</head>
<body>
<div>
<table>
	<tr>
		<td>휴대폰인증</td>
	</tr>
	<tr>
		<td>
			<span>
			인증번호가 5분 이내 도착하지 않을 경우 인증번호를 재발송 해 주시기 바랍니다.
			통신사의 사정, 메시지함 용량 초과 등으로 인증번호가 도착하지 않을 수 있습니다.
			</span>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td colspan="4">
						<input type="text" name="customerNameKor" id="customerNameKor" value="최배달" maxlength="5" style="ime-mode:active;" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="certReqYn" id="certReqYn" value="N" />
						<input type="hidden" name="certSucYn" id="certSucYn" value="N" />
						<input type="hidden" name="certResNo" id="certResNo" value="123456" />
						<select name="customerPhone1" id="customerPhone1" title="핸드폰번호 앞자리" class="sel" style="width:50px;">
<!-- 							<option value="">선택</option> -->
							<c:forEach var="hpList" items="${hpList}" varStatus="status">
								<option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						&nbsp;-&nbsp;
						<input type="text" name="customerPhone2" id="customerPhone2" class="text num_only" title="핸드폰번호 중간자리" maxlength="4" onkeydown="util_numberonly(event);" style="width:50px;ime-mode:disabled" />
					</td>
					<td>
						&nbsp;-&nbsp;
						<input type="text" name="customerPhone3" id="customerPhone3" class="text num_only" title="핸드폰번호 끝자리" maxlength="4" onkeydown="util_numberonly(event);" style="width:50px;ime-mode:disabled" />
					</td>
					<td>
						<a href="javascript:void(0);" id="btnCertHpReq">인증번호발송</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td>
						<input type="text" name="userCertNo" id="userCertNo" class="text num_only" title="인증번호" maxlength="6" onkeydown="util_numberonly(event);" style="width:70px;ime-mode:disabled" />
					</td>
					<td>
						<a href="javascript:void(0);" id="btnOk">확인</a>
						<a href="javascript:void(0);" id="btnCancel">취소</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>

</body>
</html>