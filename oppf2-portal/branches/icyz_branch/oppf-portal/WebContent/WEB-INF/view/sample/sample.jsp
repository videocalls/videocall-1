<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sample.jsp
 * @Description : [샘플] 조회
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
	
	  $( "#startDate, #endDate" ).datepicker({
		 closeText          : '닫기'
		,prevText           : '이전달'
		,nextText           : '다음달'
		,currentText        : '오늘'
		,monthNames         : [ '1월(JAN)', '2월(FEB)', '3월(MAR)', '4월(APR)','5월(MAY)', '6월(JUN)','7월(JUL)', '8월(AUG)', '9월(SEP)', '10월(OCT)', '11월(NOV)','12월(DEC)' ]
		,monthNamesShort    : [ '1월', '2월', '3월', '4월', '5월','6월','7월', '8월', '9월', '10월', '11월','12월']
		,dayNames           : [ '일', '월', '화', '수', '목', '금', '토' ]
		,dayNamesShort      : [ '일', '월', '화', '수', '목', '금', '토' ]
		,dayNamesMin        : [ '일', '월', '화', '수', '목', '금', '토' ]
		,weekHeader         : 'Wk'
		,dateFormat         : 'yy-mm-dd'
		,firstDay           : 0
		,isRTL              : false
		,showMonthAfterYear : false
		,yearSuffix         : ''
		,buttonImage : '<c:url value="/images/cmm/icon_calendar.gif"/>'
	  });
	
	/* [조회]버튼 클릭 시 호출 */
	$("#btnSearchList").click(function(){
		fn_searchList();
	});
	
	/* [name] keydown 시 호출 */
	$("#txtSearchName").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [state] keydown 시 호출 */
	$("#txtSearchState").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [state] keydown 시 호출 */
	$("#txtSearchCountry").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [등록]버튼 클릭 시 호출 */
	$("#btnInsert").click(function(){
		var url = "<c:url value='/sample/insertSample.ajax'/>";
		var insertName    = $("#insertName").val();
		var insertState   = $("#insertState").val();
		var insertCountry = $("#insertCountry").val();
	    var objParam = new Object();
		objParam.insertName    = insertName;
		objParam.insertState   = insertState;
		objParam.insertCountry = insertCountry;
		util_ajaxPage(url, objParam, "fn_ajaxCallback_sampleInsert");
	});
	
	/* [등록일순]정렬버튼 클릭 시 호출 */
	$("#btnListOrder1").click(function(){
		$.each($(".boardBtns").children("a"), function(idx,data){
			if( $(this).attr("id") != "btnListOrder1" ){
				$(this).removeClass().addClass("btnTxt");
			}
		});
		
		if( $(this).attr("class").match("Down") ){
			$(this).removeClass().addClass("btnFo_Up");
			$("#listOrder").val("create_date ASC");
			
		}else if( $(this).attr("class").match("Up") ){
			$(this).removeClass().addClass("btnFo_Down");
			$("#listOrder").val("create_date DESC");
			
		}else{
			$(this).removeClass().addClass("btnFo_Down");
			$("#listOrder").val("create_date DESC");
		}
		
		$("#pageIndex").val("1");
		$("#pageStart").val("0");
		fn_ajaxCall_sample();
	});
	
	/* [조회순]정렬버튼 클릭 시 호출 */
	$("#btnListOrder2").click(function(){

		$.each($(".boardBtns").children("a"), function(idx,data){
			if( $(this).attr("id") != "btnListOrder2" ){
				$(this).removeClass().addClass("btnTxt");
			}
		});
		
		if( $(this).attr("class").match("Down") ){
			$(this).removeClass().addClass("btnTh_Up");
			$("#listOrder").val("read_count ASC");
			
		}else if( $(this).attr("class").match("Up") ){
			$(this).removeClass().addClass("btnTh_Down");
			$("#listOrder").val("read_count DESC");
			
		}else{
			$(this).removeClass().addClass("btnTh_Down");
			$("#listOrder").val("read_count DESC");
		}
		
		$("#pageIndex").val("1");
		$("#pageStart").val("0");
		fn_ajaxCall_sample();
	});
	
	fn_onload_searchList();
	
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
			<%-- 좌측메뉴 영역 --%>
<%-- 			<%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %> --%>
			<%-- 좌측메뉴 영역 --%>
			<div id="articleWrap">
				<div class="conHeader">
					<%-- 네비게이션 영역 --%>
<%-- 					<%@ include file="/WEB-INF/view/cmm/common-include-history.jspf" %> --%>
					<%-- 네비게이션 영역 --%>
				</div>
				<div id="content">
					<table border="2">
						<tr>
							<td>
								↓↓↓↓↓ 샘플모음 입니다. ↓↓↓↓↓
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:void(0);" onclick="util_movePage('<c:url value='/sample/retrieveSampleList.do'/>')">
								  샘플게시판
								</a>
								&nbsp;,&nbsp;
								<a href="javascript:void(0);" onclick="util_movePage('<c:url value='/sample/retrieveSampleList.do'/>','','_blank')">
								  샘플게시판
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:void(0);" onclick="util_movePage('<c:url value='/sample/sampleModalPopup.do'/>')">
								  div팝업
								</a>
							</td>
						</tr>
						<tr>
							<td>
							
								<table border="1">
									<tr>
										<td colspan="4">
											<a href="http://zetawiki.com/wiki/JQuery_UI_%EB%82%A0%EC%A7%9C%EC%84%A0%ED%83%9D%EA%B8%B0_datepicker#.EA.B0.99.EC.9D.B4_.EB.B3.B4.EA.B8.B0" target="_blank">
											  달력-datePicker (샘플 링크)
											</a>
										</td>
									</tr>
									<tr>
										<td>
											<input type="text" name="startDate" id="startDate" size="10" maxlength="10" readonly />
										</td>
										<td>
											<a id="aStartDate">
												<img src="<c:url value='/images/cmm/icon_calendar.gif'/>" />
											</a>
										</td>
										<td>
											<input type="text" name="endDate" id="endDate" size="10" maxlength="10" readonly />
										</td>
										<td>
											<a id="aEndDate">
												<img src="<c:url value='/images/cmm/icon_calendar.gif'/>" />
											</a>
										</td>
									</tr>
								</table>
								
							</td>
						</tr>
						<tr>
							<td>
								<a href="http://jqueryte.com/demos" target="_blank">
								  html컴포넌트-te jquery(API)
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="<c:url value='/sample/sampleWidget.do'/>" target="_blank">
								  jqWidget샘플모음
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="http://www.jqwidgets.com/jquery-widgets-demo/" target="_blank">
								  jqWidget
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="https://commons.apache.org/proper/commons-fileupload/using.html" target="_blank">
								  멀티업로드-apache upload(API)
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a id="btnUpload" onclick="javascript:util_movePage('<c:url value='/sample/sampleUpload.do'/>');">파일업로드</a>
							</td>
						</tr>
					</table>
				</div><%-- content --%>
			</div><%-- articleWrap --%>
		</div><%-- contents --%>
	</div><%-- container --%>
	
	<%-- Footer 영역 --%>
<%-- 	<%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>	 --%>
	<%-- Footer 영역 --%>
</div>

</body>
</html>