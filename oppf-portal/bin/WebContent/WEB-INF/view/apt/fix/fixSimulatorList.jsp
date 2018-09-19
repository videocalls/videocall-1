<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserManageList.jsp
 * @Description : 일반회원관리 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.20  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.20
 * @version 1.0
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

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#searchDateFrom, #searchDateTo").datepicker({
	    showOn: "button",
	    dateFormat: 'yy-mm-dd',
	    buttonImage: "<c:url value='/images/apt/calendar.png'/>",
	    buttonImageOnly: true,
	    buttonText: "달력보기",
//	    minDate: '-3y',
//	    maxDate: '+1y',
	    currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

</script>


</head>

<body>
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
					<h2>Simulator 케이스 관리</h2>
				</div>
				<!-- // locatioin -->
				
				<div class="tb_write1">
					<table>
						<caption>Simulator 케이스 관리 정보입력</caption>
						<colgroup>
							<col style="width:20%;">
							<col style="width:*;">
						</colgroup>
						<tbody>
                        <tr>
							<th scope="row"><label for="chk1">구분</label></th>
							<td class="txt_l"> 
								<select title="구분 입력" id="chk1">
                                    <option>전체</option>
                                    <option>API ID</option>
                                    <option>API 이름</option>
                                </select>
								<input type="text" style="width:350px">
							</td>		
						</tr>
						<tr>
							<th scope="row">Buy-side</th>
							<td class="txt_l"> 
								<!-- chk_list_wrap -->
								<div class="chk_list_wrap type2">
									<ul>
										<li><input type="checkbox" name="dd" id="d1"><label for="d1">전체</label></li>
										<li><input type="checkbox" name="dd" id="d2"><label for="d2">atsolutions</label></li>
										<li><input type="checkbox" name="dd" id="d3"><label for="d3">dunamu</label></li>
										<li><input type="checkbox" name="dd" id="d4"><label for="d4">kakao</label></li>
										<li><input type="checkbox" name="dd" id="d5"><label for="d5">naver</label></li>
										<li><input type="checkbox" name="dd" id="d6"><label for="d6">viva</label></li>
										<li><input type="checkbox" name="dd" id="d7"><label for="d7">더블제이스톡</label></li>
										<li><input type="checkbox" name="dd" id="d8"><label for="d8">오름스톡</label></li>
									</ul>
									<a href="#none" class="btn_more">더보기</a>
								</div>
								<!-- // chk_list_wrap -->
							</td>	
						</tr>
						<tr>
							<th scope="row"><label for="chk2">조회기간</label></th>
							<td class="txt_l"> 
								<select title="구분 입력" id="chk2">
                                    <option>전체</option>
                                    <option>API ID</option>
                                    <option>API 이름</option>
                                </select>
								<input type="text" id="datepicker1" readonly="readonly" style="width:80px;"><span class="dateDot">~</span><input type="text" id="datepicker2" readonly="readonly" style="width:80px;">
								<input class="ml10" type="radio" name="bb" id="b1" checked="checked">
								<label for="b1">전체</label>
								<input type="radio" name="bb" id="b2">
								<label for="b2">최근 1주</label>
								<input type="radio" name="bb" id="b3">
								<label for="b3">최근 1달</label>
								<input type="radio" name="bb" id="b4">
								<label for="b4">최근 1년</label>
							</td>	
						</tr>
						</tbody>
					</table>
				</div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="#none;">검색</a></span>
                    <span class="btn_gray2"><a href="#none;">초기화</a></span>
				</div>
				<!-- // btn_type3 -->
				
				<p class="amount">총 <em>99999</em> 건</p>
				<!-- 그리드 영역 -->
				<div class="tb_list1">
					<table>
						<caption>기업이름(한글), 기업코드, CompID, 주문수량대비체결량, 거부율, 등록자, 등록일, 수정일 정보</caption>
						<colgroup>
							<col style="width:*;">
							<col style="width:10%;">
							<col style="width:10%;">
							<col style="width:13%;">
							<col style="width:8%;">
							<col style="width:8%;">
							<col style="width:14%;">
							<col style="width:14%;">
						</colgroup>
						<thead>
						<tr>
							<th scope="col">기업이름(한글)</th>
							<th scope="col">기업코드</th>
							<th scope="col">CompID</th>
							<th scope="col">주문수량대비체결량</th>
							<th scope="col">거부율</th>
							<th scope="col">등록자</th>
							<th scope="col">등록일</th>
							<th scope="col">수정일</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td style="text-align:center;">Kakao</td>
							<td style="text-align:center;">F00002</td>
							<td style="text-align:center;">MGMCTT</td>
							<td style="text-align:center;">100%</td>
							<td style="text-align:center;">10%</td>
							<td style="text-align:center;">홍길동</td>
							<td style="text-align:center;">2016-04-18 17:07</td>
							<td style="text-align:center;">2016-04-18 17:07</td>
						</tr>
						<!-- 데이터가 없을 경우 -->
						<tr>
							<td colspan="8" style="text-align:center;">등록된 정보가 없습니다.</td>
						</tr>
						<!-- // 데이터가 없을 경우 -->
						</tbody>
					</table>
				</div>
				<!--// 그리드 영역 -->

				<!-- pagination -->
				<div class="pagination">
					<a href="#" class="page first"></a>
					<a href="#" class="page prev"></a>
					<a href="#" class="on">1</a>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">4</a>
					<a href="#">5</a>
					<a href="#">6</a>
					<a href="#">7</a>
					<a href="#">8</a>
					<a href="#">9</a>
					<a href="#">10</a>
					<a href="#" class="page next"></a>
					<a href="#" class="page last"></a>

					<div class="btn_type3">
						<div class="left">
							<span class="btn_gray1"><a href="#none;">엑셀</a></span>
							<span class="btn_gray1"><a href="#none;">다운로드</a></span>
						</div>
						<div class="right"><span class="btn_gray1"><a href="#none;">추가</a></span></div>
					</div>
				</div>
				<!-- // pagination -->				

			</section>
			<!-- // content -->
		</div>
	</article>
	<!-- // container -->
</body>
</html>