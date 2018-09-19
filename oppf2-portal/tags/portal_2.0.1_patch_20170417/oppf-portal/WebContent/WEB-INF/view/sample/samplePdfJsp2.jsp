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
	location.href = "<c:url value='/sample/retrieveSampleList.do'/>";
}
 
<%--화면 로드 처리--%>
$(document).ready(function(){
	
	/* [수정]버튼 클릭 시 호출 */
	$("#btnUpdate").click(function(){
// 		$("#txtId").attr("readonly",false);
		$("#txtName").attr("readonly",false);
		$("#txtState").attr("readonly",false);
		$("#txtCountry").attr("readonly",false);
		$("#btnUpdate").hide();
		$("#btnDelete").hide();
		$("#btnSave").show();
		$("#btnCancel").show();
	});
	
	/* [취소]버튼 클릭 시 호출 */
	$("#btnCancel").click(function(){
// 		$("#txtId").attr("readonly",true);
		$("#txtName").attr("readonly",true);
		$("#txtState").attr("readonly",true);
		$("#txtCountry").attr("readonly",true);
		$("#btnUpdate").show();
		$("#btnDelete").show();
		$("#btnSave").hide();
		$("#btnCancel").hide();
	});
	
	/* [저장]버튼 클릭 시 호출 */
	$("#btnSave").click(function(){
		var url = "<c:url value='/sample/updateSample.ajax'/>";
		var id = $("#txtId").val();
		var name = $("#txtName").val();
		var state = $("#txtState").val();
		var country = $("#txtCountry").val();
	    var objParam = new Object();
		objParam.id = id;
		objParam.name = name;
		objParam.state = state;
		objParam.country = country;
		
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
		}
	});
	
	/* [약관동의]버튼 클릭 시 호출 */
	$("#btnAgreement").click(function(){
		var confirmMsg = "약관에 동의하시겠습니까?";
		if( confirm(confirmMsg) ){
			var id = $("#txtId").val();
			var Name = $("#txtName").val();
			var State = $("#txtState").val();
			var Country = $("#txtCountry").val();
			
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
							<input type="text" name="txtId" id="txtId" value="<c:out value='${resultDetail.id}'/>"  />
						</td>
						<th>성별</th>
						<td>
							<input type="text" name="txtName" id="txtName" value="<c:out value='${resultDetail.name}'/>"  />
						</td>
					</tr>
					<tr>
						<th>영문이름</th>
						<td>
							<input type="text" name="txtState" id="txtState" value="<c:out value='${resultDetail.state}'/>"  />
						</td>
						<th>동의날짜</th>
						<td>
							<input type="text" name="txtCountry" id="txtCountry" value="<c:out value='${resultDetail.country}'/>"  />
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
								약관동의 완료스텝 페이지입니다
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