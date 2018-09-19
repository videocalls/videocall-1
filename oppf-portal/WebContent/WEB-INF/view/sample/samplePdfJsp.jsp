<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
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

function fn_moveList(url){
	location.href = "<c:url value='/sample/samplePdfJsp2.do'/>";
}

function fn_ajaxCallback_sample7(data){
	alert('data.rsMsg='+data.rsMsg);
	fn_moveList();
}
 
<%--화면 로드 처리--%>
$(document).ready(function(){
	
	/* [약관동의]버튼 클릭 시 호출 */
	$("#btnAgreement").click(function(){
		var confirmMsg = "약관에 동의하시겠습니까?";
		if( confirm(confirmMsg) ){
			var id = $("#txtId").val();
// 			var url = "/oppf-portal/sample/samplePdfJsp.ajax";
			var url = "/oppf-portal/sample/test2.ajax";
			var name = $("#txtName").val();
			var state = $("#txtState").val();
			var country = $("#txtCountry").val();
		    var objParam = new Object();
			objParam.id = id;
			objParam.name = name;
			objParam.state = state;
			objParam.country = country; 
			var reqData = {
				"searchId" : id
				,"searchName" : name
				,"searchState" : state
				,"searchCountry" : country
			};
			
// 			alert("한글이름 : " + id + ", 성별 : " + Name + ", 영문이름 : " + State + ", 동의날짜 : " + Country);
			
// 			util_ajaxPage(url, objParam, "fn_ajaxCallback_sample7");
			util_ajaxPage(url, reqData, "fn_ajaxCallback_sample7");
			/* $.ajax({
				 type    : "post"
				,url     : url
				,data    : objParam
				,success : function(){
					alert("정상적으로 약관동의 절차가 이루어졌습니다.");
					fn_moveList();
				}
				,error   : function(){
					alert("약관동의 절차가 이루어지지 않았습니다.");
				}
			}); */
			
		}
		
		
		/* var confirmMsg = "삭제처리를 하시겠습니까?";
		if( confirm(confirmMsg) ){
			var url = "<c:url value='/sample/deleteSample.ajax'/>";
			var id = $("#txtId").val();
		    var objParam = new Object();
			objParam.id = id;
			
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
		} */
	});
	
	
});
</script>
</head>
<body>

<div id="wrapper">
	<div id="container">
		<%-- 탑과 메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
			
			<div id="content">
				<table border="1">
					<tr>
						<th>한글이름</th>
						<td>
							<input type="text" name="txtId" id="txtId" value="<c:out value='${sampleVO.id}'/>"  />
						</td>
						<th>성별</th>
						<td>
							<input type="text" name="txtName" id="txtName" value="<c:out value='${sampleVO.name}'/>"  />
						</td>
					</tr>
					<tr>
						<th>영문이름</th>
						<td>
							<input type="text" name="txtState" id="txtState" value="<c:out value='${sampleVO.state}'/>"  />
						</td>
						<th>동의날짜</th>
						<td>
							<input type="text" name="txtCountry" id="txtCountry" value="<c:out value='${sampleVO.country}'/>"  />
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="javascript:;" onclick="javascript:util_movePage('<c:url value='/sample/retrieveSampleList.do'/>');" style="display:none;">
								목록
							</a>
							<a href="javascript:;" id="btnUpdate" style="display:none;">
								수정
							</a>
							<a href="javascript:;" id="btnDelete" style="display:none;">
								삭제
							</a>
							<a href="javascript:;" id="btnSave" style="display:none;">
								저장
							</a>
							<a href="javascript:;" id="btnCancel" style="display:none;">
								취소
							</a>
							<a href="javascript:;" id="btnAgreement">
								약관동의
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<%-- Footer 영역 --%>
	<%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
	<%-- Footer 영역 --%>
</div>

</body>
</html>