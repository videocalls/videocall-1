<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/include/cmmDoctype.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sampleDetail.jsp
 * @Description : [샘플목록:상세] 조회
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
<%@ include file="/WEB-INF/view/cmm/include/cmmHead.jsp" %>
<script type="text/javascript">

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

/* [목록]이동함수 */
function fn_moveList(){
	
	document.searchfrm.action = "<c:url value='/sample/retrieveSampleList.do'/>";
	document.searchfrm.submit();
	
// 	var url = "/oppf-portal/sample/retrieveSampleList.do";
// 	var reqData = {
//        "pageIndex"     : $("#pageIndex").val()
//       ,"pageStart"     : $("#pageStart").val()
//       ,"searchName"    : $("#searchName").val()
//       ,"searchState"   : $("#searchState").val()
//       ,"searchCountry" : $("#searchCountry").val()
// 	};
	
// 	util_movePage(url, reqData);
}

/* [이전글,다음글]이동함수 */
function fn_moveBeforeAfterDetail(paramId){
	document.searchfrm.detailId.value = paramId;
	document.searchfrm.action = "<c:url value='/sample/retrieveSampleDetail.do'/>";
	document.searchfrm.submit();
}
 
<%--화면 로드 처리--%>
$(document).ready(function(){
	
	/* [목록]버튼 클릭 시 호출 */
	$("#btnMoveList").click(function(){
		fn_moveList();
	});
	
    /* [수정]버튼 클릭 시 호출 */
    $("#btnUpdate").click(function(){
//      $("#updateId").attr("readonly",false);
        $("#updateName").attr("readonly",false);
        $("#updateState").attr("readonly",false);
        $("#updateCountry").attr("readonly",false);
        $("#updateName").css("background-color","#ffffff");
        $("#updateState").css("background-color","#ffffff");
        $("#updateCountry").css("background-color","#ffffff");
        $("#btnUpdate").hide();
        $("#btnDelete").hide();
        $("#btnSave").show();
        $("#btnCancel").show();
    });
    
    /* [취소]버튼 클릭 시 호출 */
    $("#btnCancel").click(function(){
//      $("#updateId").attr("readonly",true);
        $("#updateName").attr("readonly",true);
        $("#updateState").attr("readonly",true);
        $("#updateCountry").attr("readonly",true);
        $("#updateName").css("background-color","silver");
        $("#updateState").css("background-color","silver");
        $("#updateCountry").css("background-color","silver");
        $("#btnUpdate").show();
        $("#btnDelete").show();
        $("#btnSave").hide();
        $("#btnCancel").hide();
    });
	
	/* [저장]버튼 클릭 시 호출 */
	$("#btnSave").click(function(){
		var url = "<c:url value='/sample/updateSample.ajax'/>";
		var updateId = $("#updateId").val();
		var updateName = $("#updateName").val();
		var updateState = $("#updateState").val();
		var updateCountry = $("#updateCountry").val();
	    var objParam = new Object();
		objParam.updateId      = updateId;
		objParam.updateName    = updateName;
		objParam.updateState   = updateState;
		objParam.updateCountry = updateCountry;
		
		$.ajax({
			 type    : "post"
			,url     : url
			,data    : objParam
			,success : function(){
				alert("정상적으로 수정완료 되었습니다.");
				fn_moveList();
			}
			,error   : function(){
				alert("수정실패 하였습니다.");
			}
		});
	});
	
	/* [삭제]버튼 클릭 시 호출 */
	$("#btnDelete").click(function(){
		var confirmMsg = "삭제처리를 하시겠습니까?";
		if( confirm(confirmMsg) ){
			var url = "<c:url value='/sample/deleteSample.ajax'/>";
			var deleteId = $("#updateId").val();
		    var objParam = new Object();
			objParam.deleteId = deleteId;
			
			$.ajax({
				 type    : "post"
				,url     : url
				,data    : objParam
				,success : function(){
					alert("정상적으로 삭제완료 되었습니다.");
					fn_moveList();
				}
				,error   : function(){
					alert("삭제실패 하였습니다.");
				}
			});
		}
	});
	
	
});
</script>
</head>
<body>

<div id="wrapper">
	<div id="container">
		<%-- 탑과 메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/include/cmmTop.jsp" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
			<%-- 좌측메뉴 영역 --%>
			<%@ include file="/WEB-INF/view/cmm/include/cmmLeft.jsp" %>
			<%-- 좌측메뉴 영역 --%>
			<div id="articleWrap">
				<div class="conHeader">
					<%-- 네비게이션 영역 --%>
					<%@ include file="/WEB-INF/view/cmm/include/cmmHistory.jsp" %>
					<%-- 네비게이션 영역 --%>
				</div>
				<div id="content">
					<table>
						<tr>
							<td>
								<a href="javascript:void(0);" onclick="util_movePage('<c:url value='/sample/sample.do'/>')">
									샘플모음으로 이동
								</a>
							</td>
						</tr>
					</table>
					<form method="post" name="searchfrm" id="searchfrm">
						<input type="hidden" name="listOrder"      id="listOrder"      value="<c:out value='${sampleVO.listOrder}'/>" /><!-- //목록정렬 -->
						<input type="hidden" name="pageIndex"      id="pageIndex"      value="<c:out value='${sampleVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
						<input type="hidden" name="pageStart"      id="pageStart"      value="<c:out value='${sampleVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
						<input type="hidden" name="detailId"       id="detailId"       value="<c:out value='${sampleVO.detailId}'/>" />
						<input type="hidden" name="searchName"     id="searchName"     value="<c:out value='${sampleVO.searchName}'/>" />
						<input type="hidden" name="searchState"    id="searchState"    value="<c:out value='${sampleVO.searchState}'/>" />
						<input type="hidden" name="searchCountry"  id="searchCountry"  value="<c:out value='${sampleVO.searchCountry}'/>" />
					</form>
					<table border="1">
						<tr>
							<th>id</th>
							<td>
								<input type="text" name="updateId" id="updateId" value="<c:out value='${resultDetail.id}'/>" style="background-color:silver" readonly />
							</td>
							<th>name</th>
							<td>
								<input type="text" name="updateName" id="updateName" value="<c:out value='${resultDetail.name}'/>" style="background-color:silver" readonly />
							</td>
						</tr>
						<tr>
							<th>state</th>
							<td>
								<input type="text" name="updateState" id="updateState" value="<c:out value='${resultDetail.state}'/>" style="background-color:silver" readonly />
							</td>
							<th>country</th>
							<td>
								<input type="text" name="updateCountry" id="updateCountry" value="<c:out value='${resultDetail.country}'/>" style="background-color:silver" readonly />
							</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td colspan="3">
								<fmt:formatNumber value="${resultDetail.readCount}" type="number" />
							</td>
						</tr>
						<tr>
							<th>생성시간</th>
							<td>
								<c:out value='${resultDetail.createDate}'/>
							</td>
							<th>생성자ID</th>
							<td>
								<c:out value='${resultDetail.createId}'/>
							</td>
						</tr>
						<tr>
							<th>수정시간</th>
							<td>
								<c:out value='${resultDetail.updateDate}'/>
							</td>
							<th>수정자ID</th>
							<td>
								<c:out value='${resultDetail.updateId}'/>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<a href="javascript:void(0);" id="btnMoveList" onclick="javascript:fn_moveList();">
									목록
								</a>
								<a href="javascript:void(0);" id="btnUpdate">
									수정
								</a>
								<a href="javascript:void(0);" id="btnDelete">
									삭제
								</a>
								<a href="javascript:void(0);" id="btnSave" style="display:none;">
									저장
								</a>
								<a href="javascript:void(0);" id="btnCancel" style="display:none;">
									취소
								</a>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table>
									<caption>샘플 상세페이지 이전글 다음글</caption>
									<colgroup>
										<col style="width:10%;">
										<col style="width:*;">
									</colgroup>
									<tbody>
									<tr>
										<td>이전글</td>
										<td>
											<c:choose>
												<c:when test="${befAftInfo.beforeId ne '0'}" >
													<a href="javascript:void(0);" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.beforeId}');">
														<c:out value="${befAftInfo.beforeSj}" />
													</a>
												</c:when>
												<c:otherwise>
													<span style="padding-left:18px; color:#595959;" >없음</span>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>다음글</td>
										<td>
											<c:choose>
												<c:when test="${befAftInfo.afterId ne '0'}" >
													<a href="javascript:void(0);" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.afterId}');">
														<c:out value="${befAftInfo.afterSj}" />
													</a>
												</c:when>
												<c:otherwise>
													<span style="padding-left:18px; color:#595959;" >없음</span>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div><%-- content --%>
			</div><%-- articleWrap --%>
		</div><%-- contents --%>
		<%-- Bottom 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/include/cmmBottom.jsp" %>
		<%-- Bottom 영역 --%>
	</div><%-- container --%>
	
	<%-- Footer 영역 --%>
	<%@ include file="/WEB-INF/view/cmm/include/cmmFooter.jsp" %>
	<%-- Footer 영역 --%>
</div>

</body>
</html>