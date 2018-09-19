<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : fixBuySideAdd.jsp
 * @Description : buy-side 기업 추가
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.07   최판광       최초  생성
 * </pre>
 *
 * @author 최판광
 * @since 2017.03.07
 * @version 2.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역변수
 *******************************************/

var senderCompId =  "<c:out value='${result.senderCompId}'/>";

/*******************************************
 * 이벤트 함수
 *******************************************/
 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserAccountList.do'/>";
    var param = new Object();
    param.paramMenuId = "01003";
    
    gfn_loginNeedMove(url, param);
}


$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>
	
		
    // 사용여부 radio
    $("input[name=useYnComb]").change(function(){
        var id = $(this).attr("id");
        
        // 가능
        if(id == "Y"){
        	$("#useYnStatus").val("Y");
        }else{
        	$("#useYnStatus").val("N");
        }
    });

    // QueueId
    $("#fixQueueList").change(function(){
		$('#fixQueueId').val($(this).val());
    });
    
	//검색
	$("#btnSave").click(function(){
		fn_save();
	});


	//목록
	$("#btnList").click(function(){
		fn_list();
	});
	

    /* [중복확인]버튼 클릭 시 호출 */
    $("#btnChkCompId").click(function(){

    	if(util_chkReturn($.trim($('#senderCompId').val()), "s") == "") {
        	alert("CompID 은(는) 필수 입력값입니다.");
            $("#senderCompId").focus();
            return;
        }
    	
    	if(senderCompId ==  $("#senderCompId").val()){
            $("#senderCompIdCheck").val("Y");
            $("#senderCompId").css("background-color","silver");
            $("#senderCompId").attr("readonly",true);
            $("#btnChkCompId").hide(); //[중복확인]
            $("#btnReChkCompId").show(); //[아이디재입력]
            $("#senderCompId").focus();
    	}else {
	        var url = "<c:url value='/apt/fix/countSenderCompId.ajax'/>";
	        var reqData = {"senderCompId":$("#senderCompId").val()};
	        util_ajaxPage(url, reqData, "fn_ajaxCallback_chkCompId");
    	}
    });
	

    /* [아이디재입력]버튼 클릭 시 호출 */
    $("#btnReChkCompId").click(function(){
        $("#senderCompIdCheck").val("N"); //아이디체크 초기화
        $("#senderCompId").css("background-color","#ffffff");
        $("#senderCompId").attr("readonly",false);
        $("#btnReChkCompId").hide(); //[아이디재입력]
        $("#btnChkCompId").show(); //[중복확인]
    });
	

});


/* [중복확인]ID체크 콜백 함수 */
function fn_ajaxCallback_chkCompId(data){
    var dup = data.resultCount;
    if(dup == "N"){
        alert("사용가능한 CompID입니다.");
        $("#senderCompIdCheck").val("Y");
        $("#senderCompId").css("background-color","silver");
        $("#senderCompId").attr("readonly",true);
        $("#btnChkCompId").hide(); //[중복확인]
        $("#btnReChkCompId").show(); //[아이디재입력]
        $("#senderCompId").focus();
    }else{
        alert("존재하는 CompID입니다.");
        $("#senderCompId").focus();
    }
    $("#senderCompIdCheck").val(se);
}


function fn_save(){

	var senderCompName =  "<c:out value='${result.senderCompName}'/>";
	var companyId =  "<c:out value='${result.companyId}'/>";
	
	$("#senderCompName").val(senderCompName);
	$("#companyId").val(companyId);
	

	if(util_chkReturn($.trim($('#senderCompId').val()), "s") == "") {
    	alert("CompID 은(는) 필수 입력값입니다.");
        $("#senderCompId").focus();
        return;
    }
	
    if(util_chkReturn($.trim($('#senderCompIdCheck').val()), "s") != "Y") {
    	alert("CompID 중복확인을 해주세요.");
        $("#senderCompId").focus();
        return;
    }


	if(util_chkReturn($.trim($("#fixQueueList").val()), "s") == "") {
    	alert("Fix Queue 은(는) 필수 선택값입니다.");
        $("#fixQueueList").focus();
        return;
    }
	
	if(util_chkReturn($.trim($("#sessionSenderCompId").val()), "s") == "") {
    	alert("Session Sender CompID 은(는) 필수 선택값입니다.");
        $("#sessionSenderCompId").focus();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/fix/fixBuySideInsert.ajax'/>";
    var param = $("#FixManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    if(data.fixUrlError == -1){
        alert("등록된 서버정보가 없습니다.\n관지라에게 문의 해주세요.");
    	fn_list();
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    if(data.result == "200"){
        alert("정상적으로 등록 되었습니다.");
    	fn_list();
    }else{
        alert("등록에 실패하였습니다.");
    	fn_list();
    }
    return;
}



function fn_list(){
    util_moveRequest("FixManageVO", "<c:url value='/apt/fix/fixBuySideList.do'/>", "_self");
}



</script>
</head>

<body>

<form:form commandName="FixManageVO" name="FixManageVO" method="post">
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
			<section class="content">
				<div class="location">
					<h2>Buy-side 정보 등록</h2>
				</div>
				<!-- // locatioin -->
               
			    <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                
				<div class="tb_write1">
					<table>
						<caption>Buy-side 정보 입력</caption>
						<colgroup>
							<col style="width:30%;">
							<col style="width:*;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row">기업 이름(한글)</th>
							<td class="txt_l">${result.senderCompName}
								<input type="hidden" name="senderCompName" id="senderCompName" />
							</td>
						</tr>
						<tr>
							<th scope="row">기업코드</th>
							<td class="txt_l">${result.companyId}
								<input type="hidden" name="companyId" id="companyId" />
							</td>
						</tr>
                        <tr>
							<th scope="row"><label for="chk2">CompID<span class="icon_basic">*필수입력</span></label></th>
							<td class="txt_l">
								<input type="text" class="w100" id="senderCompId" name="senderCompId" value="${result.senderCompId}"
								onkeydown="javascript:if(event.keyCode == 13) btnChkCompId.click();"
								>&nbsp;
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnChkCompId">중복확인</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnReChkCompId" style="display:none;" >재입력</a></span>
                                <input type="hidden" name="senderCompIdCheck" id="senderCompIdCheck" value="N" />   
							</td>
						</tr>
                        <tr>
							<th scope="row"><label for="chk2">SessionSenderCompId<span class="icon_basic">*필수입력</span></label></th>
							<td class="txt_l">
								<input type="text" class="w100" id="sessionSenderCompId" name="sessionSenderCompId" value="${result.sessionSenderCompId}"
								onkeydown="javascript:if(event.keyCode == 13)"
								>&nbsp;   
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="chk5">FIX Queue<span class="icon_basic">*필수입력</span></label></th>
							<td class="txt_l">
								<select title="구분 입력" id="fixQueueList" style="width:440px;">
									<option value="">전체</option>
									<c:forEach var="queueList"	items="${queueList}" varStatus="status">
										<option value="${queueList.fixQueueId}">${queueList.fixQueueId}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="fixQueueId" id="fixQueueId" />
                            </td>
						</tr>
						<tr>
							<th scope="row">주문가능여부<span class="icon_basic">*필수입력</span></th>
							<td class="txt_l">
								<input type="radio" name="useYnComb" id="Y" checked="checked">
								<label for="k1">가능</label>
								<input type="radio" name="useYnComb" id="N">
								<label for="k2">불가능</label>
								<input type="hidden" name="useYnStatus" id="useYnStatus" value= "Y" />
							
							</td>
						</tr>	
						</tbody>
					</table>
				</div>
                <div class="btn_type3">
                    <div class="left"><span class="btn_gray2"><a href="javascript:void(0);" id="btnList">목록</a></span></div>
					<div class="right">
						<span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
					</div>
				</div>
				<!-- // btn_type3 -->
				
			</section>
			<!-- // content -->
		</div>
	</article>
	<!-- // container -->
	</form:form>
</body>
</html>