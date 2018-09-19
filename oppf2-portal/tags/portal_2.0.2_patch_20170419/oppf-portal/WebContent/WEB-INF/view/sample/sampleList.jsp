<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/include/cmmDoctype.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sampleList.jsp
 * @Description : [샘플목록:목록] 조회
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

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/

/* [페이징]처리 함수 */
function fn_paging(page){
	
	var pageStart = 0;
	var pageIndex = page;
	
	var pageRowsize = $("#pageRowsize").val();
	
    if(pageIndex == 1){
    	pageStart = 0;
    }else{
    	pageStart = pageRowsize * ( pageIndex -1 );
    }
    
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    fn_ajaxCall_sample();
} 



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* [샘플목록]ajax call 함수 */
function fn_ajaxCall_sample(){
	var url = "<c:url value='/sample/selectSampleList.ajax'/>";
	var reqData = {
       "pageRowsize"   : $("#pageRowsize").val()
      ,"pageBlocksize" : $("#pageBlocksize").val()
      ,"pageIndex"     : $("#pageIndex").val()
      ,"pageStart"     : $("#pageStart").val()
      ,"searchName"    : $("#txtSearchName").val()
      ,"searchState"   : $("#txtSearchState").val()
      ,"searchCountry" : $("#txtSearchCountry").val()
      ,"listOrder"     : $("#listOrder").val()
	};
	
	util_ajaxPage(url, reqData, "fn_ajaxCallback_sample2");
}

/* [샘플목록]ajax callback 함수 */
function fn_ajaxCallback_sample2(data){
	var rsListTotCnt = data.resultListTotalCount;
    var rsList = data.resultList;
    var apData="";
    
    $("#rsList >").remove();
    $("#pagingNavi >").remove();
    if (util_chkReturn(rsListTotCnt, "s") == "" || util_chkReturn(rsList, "s") == ""){
    	apData = '<tr>'
    		   +   '<td colspan="9">검색결과를 찾을수 없습니다.</td>'
    		   +'</tr>';
    	$("#rsList").append(apData);
    	return false;
    }
    
    $("#spanRsListTotCnt").text(util_setCommas(rsListTotCnt));
    
    $.each(rsList, function(idx,data){
    	apData += '<tr>'
    	       +    '<td>'+data.id+'</td>'
    	       +    '<td>'
    	       +      '<a href="javascript:void(0);" onclick="javascript:fn_moveDetail(\''+data.id+'\')">'
    	       +        data.name
    	       +      '</a>'
    	       +    '</td>'
    	       +    '<td>'+data.state+'</td>'
    	       +    '<td>'+data.country+'</td>'
    	       +    '<td>'+util_setCommas(data.readCount)+'</td>'
    	       +    '<td>'+data.createDate+'</td>'
    	       +    '<td>'+data.createId+'</td>'
    	       +    '<td>'+data.updateDate+'</td>'
    	       +    '<td>'+data.updateId+'</td>'
    	       +  '</tr>';
    });
    $("#rsList").append(apData);
    
  /* 페이징 설정 START */
    var pageIndex = data.sampleVO.pageIndex;
    $("#pageIndex").val(data.sampleVO.pageIndex);
    
    var pageStart = data.sampleVO.pageStart;
    $("#pageStart").val(data.sampleVO.pageStart);
    
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 START */
    var searchName = data.sampleVO.searchName;
    $("#searchName").val(searchName);
    $("#txtSearchName").val(searchName);
    
    var searchState = data.sampleVO.searchState;
    $("#searchState").val(searchState);
    $("#txtSearchState").val(searchState);
    
    var searchCountry = data.sampleVO.searchCountry;
    $("#searchCountry").val(searchCountry);
    $("#txtSearchCountry").val(searchCountry);
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 END */
    
    $("#pageTotalcount").val(rsListTotCnt);
    
    pageNavigator(
      $("#pageIndex").val()
     ,$("#pageTotalcount").val()
     ,$("#pageRowsize").val()
     ,$("#pageBlocksize").val()
     ,"fn_paging"
    );
  /* 페이징 설정 START */
}

/* [샘플목록]ajax callBack 함수 */
function fn_ajaxCallback_sampleInsert(data){
	if(data.rs == null || data.rs == 'undefined'){
		alert("등록실패 하였습니다.");
	}else{
		alert("정상적으로 등록완료 되었습니다.");
	}
	window.location.reload();
}


/*******************************************
 * 이벤트 함수
 *******************************************/

/* 목록정렬함수 */
function fn_searchOrderPaging(colName) {
 	if(colName){
 		util_setListOrderParam(colName);
 	}
 	fn_ajaxCall_sample();
}
 
/* [상세]이동함수 */
function fn_moveDetail(paramId){
    var objParam = new Object();
    objParam.pageIndex     = $("#pageIndex").val();
    objParam.pageStart     = $("#pageStart").val();
    objParam.detailId      = paramId;
	objParam.searchName    = $("#searchName").val();
	objParam.searchState   = $("#searchState").val();
	objParam.searchCountry = $("#searchCountry").val();
	objParam.listOrder = $("#listOrder").val();
	
	util_movePage("<c:url value='/sample/retrieveSampleDetail.do'/>",objParam);
}

/* [샘플조회]onload 호출 */
function fn_onload_searchList(){
	fn_ajaxCall_sample(); //[샘플목록]ajax call 함수호출
}


/* [샘플조회]호출되는 함수 */
function fn_searchList(){
	$("#pageIndex").val("1");
	$("#pageStart").val("0");
	fn_ajaxCall_sample(); //[샘플목록]ajax call 함수호출
}

//화면 로드 처리
$(document).ready(function(){
	
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
			$("#listOrder").val("A.create_date ASC");
			
		}else if( $(this).attr("class").match("Up") ){
			$(this).removeClass().addClass("btnFo_Down");
			$("#listOrder").val("A.create_date DESC");
			
		}else{
			$(this).removeClass().addClass("btnFo_Down");
			$("#listOrder").val("A.create_date DESC");
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
			$("#listOrder").val("A.read_count ASC");
			
		}else if( $(this).attr("class").match("Up") ){
			$(this).removeClass().addClass("btnTh_Down");
			$("#listOrder").val("A.read_count DESC");
			
		}else{
			$(this).removeClass().addClass("btnTh_Down");
			$("#listOrder").val("A.read_count DESC");
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
		<%@ include file="/WEB-INF/view/cmm/include/cmmTop.jsp" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
			<spring:message code="uss.ion.noi.ntfcSj"/>
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
						<tr>
							<td>
								<input type="hidden" name="listOrder"      id="listOrder"      value="<c:out value='${sampleVO.listOrder}'/>" /><!-- //목록정렬 -->
								<input type="hidden" name="pageTotalcount" id="pageTotalcount" value="0" /><!-- //총갯수 -->
								<input type="hidden" name="pageRowsize"    id="pageRowsize"    value="10" /><!-- //목록사이즈 -->
								<input type="hidden" name="pageBlocksize"  id="pageBlocksize"  value="10" /><!-- //페이징블록사이즈 -->
								<input type="hidden" name="pageIndex"      id="pageIndex"      value="<c:out value='${sampleVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
								<input type="hidden" name="pageStart"      id="pageStart"      value="<c:out value='${sampleVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
								<input type="hidden" name="searchName"     id="searchName"     value="<c:out value='${sampleVO.searchName}'/>" />
								<input type="hidden" name="searchState"    id="searchState"    value="<c:out value='${sampleVO.searchState}'/>" />
								<input type="hidden" name="searchCountry"  id="searchCountry"  value="<c:out value='${sampleVO.searchCountry}'/>" />
								
								<table border="1">
									<tr>
										<th>name</th>
										<td><input type="text" name="txtSearchName" id="txtSearchName" value="${sampleVO.searchName}" /></td>
										<th>state</th>
										<td><input type="text" name="txtSearchState" id="txtSearchState" value="${sampleVO.searchState}" /></td>
										<th>country</th>
										<td><input type="text" name="txtSearchCountry" id="txtSearchCountry" value="${sampleVO.searchCountry}" /></td>
										<td>
											<a href="javascript:void(0);" id="btnSearchList">
												조회
											</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								총<span id="spanRsListTotCnt">0</span>건
							</td>
						</tr>
						<tr>
							<td>
								<div class="boardBtns">
									<a href="javascript:void(0);" id="btnListOrder1"
									  <c:choose>
									    <c:when test="${sampleVO.listOrder eq 'A.create_date DESC'}">class="btnFo_Down"</c:when> 
									    <c:when test="${sampleVO.listOrder eq 'A.create_date ASC'}">class="btnFo_Up"</c:when>
										<c:otherwise>class="btnTxt Inquiry"</c:otherwise>
									  </c:choose>
									>등록일순
									</a>
									<a href="javascript:void(0);" id="btnListOrder2"
									  <c:choose>
									    <c:when test="${sampleVO.listOrder eq 'read_count DESC'}">class="btnTh_Down"</c:when> 
									    <c:when test="${sampleVO.listOrder eq 'read_count ASC'}">class="btnTh_Up"</c:when>
									    <c:otherwise>class="btnTxt Inquiry"</c:otherwise>
									  </c:choose>
									>
									조회순</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
							
								<table border="1">
									<caption>샘플</caption>
									<colgroup>
										<col style="width:10%;" />
										<col style="width:13%;" />
										<col style="width:13%;" />
										<col style="width:13%;" />
										<col style="width:11%;" />
										<col style="width:10%;" />
										<col style="width:10%;" />
										<col style="width:10%;" />
										<col style="width:10%;" />
									</colgroup>
									<thead>
										<tr>
											<th>id</th>
											<th>name</th>
											<th>state</th>
											<th>country</th>
											<th>
												조회수
<!-- 												<a href="javascript:void(0);" id="btnListOrderDesc_5"> -->
<%-- 													<img src="<c:url value='/images/cmm/icon_select_down.gif' />" alt="조회수 내림차순 정렬" /> --%>
<!-- 												</a> -->
<!-- 												<a href="javascript:void(0);" id="btnListOrderAsc_5" style="display:none;"> -->
<%-- 													<img src="<c:url value='/images/cmm/icon_select_up.gif' />" alt="조회수 오름차순 정렬" /> --%>
<!-- 												</a> -->
											</th>
											<th>등록일</th>
											<th>등록자ID</th>
											<th>수정일</th>
											<th>수정자ID</th>
										</tr>
									</thead>
									<tbody id="rsList">
										<tr>
											<td colspan="9">검색결과를 찾을수 없습니다.</td>
										</tr>
									</tbody>
								</table>
								
								<div id="pagingNavi" class="paging"></div>
								
							</td>
						</tr>
						<tr>
							<td>
						
								<table border="1">
									<tr>
										<th>name</th>
										<td>
											<input type="text" name="insertName" id="insertName" value="" />
										</td>
										<th>state</th>
										<td>
											<input type="text" name="insertState" id="insertState" value="" />
										</td>
										<th>country</th>
										<td>
											<input type="text" name="insertCountry" id="insertCountry" value="" />
										</td>
										<td>
											<a href="javascript:void(0);" id="btnInsert">
												등록
											</a>
										</td>
									</tr>
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