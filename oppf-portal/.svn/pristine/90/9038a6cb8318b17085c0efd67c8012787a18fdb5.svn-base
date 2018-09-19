<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : fixBuySideUpdate.jsp
 * @Description : buy-side 수정
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

<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.filter.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.sort.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.selection.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsresize.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsreorder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.pager.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxpanel.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcalendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdatetimeinput.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcheckbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/globalization/globalize.js'/>"></script>
<%-- //jqwidgets --%>


<script language="javascript" type="text/javascript">

/*******************************************
 * 전역변수
 *******************************************/

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

	//삭제
	$("#btnDel").click(function(){
		fn_del();
	});

	//목록
	$("#btnList").click(function(){
		fn_list();
	});
	

	
    
});


function fn_save(){
	

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
	
	
	var senderCompName =  "<c:out value='${result.senderCompName}'/>";
	var companyId =  "<c:out value='${result.companyId}'/>";
	
	$("#senderCompName").val(senderCompName);
	$("#companyId").val(companyId);
	
		
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/fix/fixBuySideDtlUpdate.ajax'/>";
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
    if(data.result == "OK" || data.result == "200"){
        alert("정상적으로 수정되었습니다.");
    	fn_list();
    }else{
        alert("저장에 실패하였습니다.");
    	fn_list();
    }
    return;
}

function fn_del(){

    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/fix/fixBuySideDtlDelete.ajax'/>";
    var param = $("#FixManageVO").serialize();
    var callBackFunc = "fn_delCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_delCallBack(data){
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

    if(data.result == "OK" || data.result == "200"){
        alert("삭제 되었습니다.");
    	fn_list();
    }else{
        alert("삭제에 실패하였습니다.");
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
					<h2>Buy-side 정보 수정</h2>
				</div>
				<!-- // locatioin -->
               
			    <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>
                
				<div class="tb_write1">
					<table>
						<caption>기업 정보 입력</caption>
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
							<td class="txt_l">${result.senderCompId}
								<input type="hidden" class="w100" id="senderCompId" name="senderCompId" value="${result.senderCompId}">
							</td>
						</tr>
                        <tr>
							<th scope="row"><label for="chk2">SessionSenderCompId<span class="icon_basic">*필수입력</span></label></th>
							<td class="txt_l">
								<input type="text" class="w100" id="sessionSenderCompId" name="sessionSenderCompId" value="${result.sessionSenderCompId}">&nbsp;   
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="chk5">FIX Queue<span class="icon_basic">*필수입력</span></label></th>
							<td class="txt_l">
								<select title="구분 입력" id="fixQueueList" style="width:440px;">
									<option value="">전체</option>
									<c:forEach var="queueList"	items="${queueList}" varStatus="status">
										<option value="${queueList.fixQueueId}"
										<c:if test="${queueList.fixQueueId eq result.fixQueueId}"> selected </c:if>
										>${queueList.fixQueueId}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="fixQueueId" id="fixQueueId" value="${result.fixQueueId}"  />
                            </td>
						</tr>
    	
        
						<tr>
							<th scope="row">주문가능여부<span class="icon_basic">*필수입력</span></th>
							<td class="txt_l">
								<input type="radio" name="useYnComb" id="Y" <c:if test="${result.useYn eq 'Y'}"> checked="checked" </c:if> >
								<label for="k1">가능</label>
								<input type="radio" name="useYnComb" id="N" <c:if test="${result.useYn eq 'N'}"> checked="checked" </c:if> >
								<label for="k2">불가능</label>
								<input type="hidden" name="useYnStatus" id="useYnStatus"  value="${result.useYn}" />
							
							</td>
						</tr>
						<tr>
							<th scope="row">등록 일시</th>
							<td class="txt_l">${result.createTime}</td>
						</tr>
						<tr>
							<th scope="row">수정 일시</th>
							<td class="txt_l">${result.updateTime}</td>
						</tr>
						</tbody>
					</table>
				</div>
                <div class="btn_type3">
                    <div class="left"><span class="btn_gray2"><a href="javascript:void(0);" id="btnList">목록</a></span></div>
					<div class="right">
						<span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
						<span class="btn_gray1"><a href="javascript:void(0);" id="btnDel">삭제</a></span>
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