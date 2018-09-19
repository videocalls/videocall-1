<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : faqManageList.jsp
 * @Description : 관리자 자주 묻는 질문(FAQ) 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.16  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.16
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
    $("#schDateFrom, #schDateTo").datepicker({
        showOn: "button",
        dateFormat: 'yy-mm-dd',
        buttonImage: "<c:url value='/images/apt/calendar.png'/>",
        buttonImageOnly: true,
        buttonText: "달력보기",
//        minDate: '-3y',
//        maxDate: '+1y',
        currentText: util_getDate()
    });
});
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/cmm/faq/faqManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "08002";
    
    gfn_loginNeedMove(url, param);
} 

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
	
    //조회일 radio
    $("input[name=schDateRdo]").change(function(){
        var id = $(this).attr("id");
        
        var schDateFrom = "";
        var schDateTo = util_getDate();
        
        if(util_chkReturn($.trim($('#schDateTo').val()), "s") != "") {
            schDateTo = util_replaceAll($("#schDateTo").val(), "-", "");
        }
        
        //week
        if(id == "schWeek"){
            schDateFrom = util_addDate(schDateTo, "d", -7);
        //month
        }else if(id == "schMonth"){
            schDateFrom = util_addDate(schDateTo, "m", -1);
        //year
        }else if(id == "schYear"){
            schDateFrom = util_addDate(schDateTo, "y", -1);
        //all
        }else{
            schDateFrom = "";
            schDateTo = "";
        }
        
        if(util_chkReturn(schDateFrom, "s") != "") schDateFrom = util_setFmDate(schDateFrom);
        if(util_chkReturn(schDateTo, "s") != "") schDateTo = util_setFmDate(schDateTo);
                
        $("#schDateFrom").val(schDateFrom);
        $("#schDateTo").val(schDateTo);
    });
    
    //검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //초기화
    $("#btnSearchClear").click(function(){
        $("#pageIndex").val("1");
        util_moveRequest("FaqManageVO", "<c:url value='/cmm/faq/faqManageList.do'/>");
    });
        
    //등록
    $("#btnReg").click(function(){
        $("#faqSeq").val("");
        
        util_moveRequest("FaqManageVO", "<c:url value='/cmm/faq/faqManageSave.do'/>");
    });
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '표시순번', datafield: 'faqOrder', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '제목', datafield: 'faqTitle', width: '55%', cellsalign: 'left', align: 'center' },
            { text: '조회수', datafield: 'readCount', width: '5%', cellsalign: 'right', align: 'center' },
            { text: '게시시작일', datafield: 'faqStartDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '게시종료일', datafield: 'faqExpireDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'center', align: 'center' },
            
            { text: 'faqSeq', datafield: 'faqSeq', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    });
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        //상세이동
        fn_moveDetail(data);
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    //조회
    fn_search($("#pageIndex").val());
});

<%-- 검색 --%>
function fn_search(pageIndex){
    if(!fn_validate()){
        return;
    }
    
    //page
    if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
    $("#pageIndex").val(pageIndex);
            
    //로딩 호출
    gfn_setLoading(true);
  
    //page setting  
    var url = "<c:url value='/cmm/faq/selectFaqManageList.ajax'/>";
    var param = $("#FaqManageVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    var resultList = data.resultList;
    var resultListTotalCount = data.resultListTotalCount;
    
    $("#totCnt").text(util_setCommas(resultListTotalCount));
    
    $("#pagingNavi >").remove();
    
    //grid common
    gfn_gridData(resultList);
    
    //page common
    pageNavigator(
        $("#pageIndex").val(),
        resultListTotalCount,
        "fn_search",
        "30"
    );
}

<%-- validate --%>
function fn_validate(){
    //조회일 체크
    var schFrom = util_replaceAll($("#schDateFrom").val(), "-", "");
    var schTo = util_replaceAll($("#schDateTo").val(), "-", "");
            
    if(schFrom != ""){
        if(!util_isDate(schFrom)){
            alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
            $("#schDateFrom").focus();
            return false;
        }
        
        if(schTo == ""){
            alert("<spring:message code='errors.date' arguments='조회종료일' />");
            $("#schDateTo").focus();
            return false;
        }else{
            if(!util_isDate(schTo)){
                alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
                $("#schDateTo").focus();
                return false;
            }else{
                if(schFrom > schTo){
                    alert("종료일이 시작일보다 클 수 없습니다.");
                    $("#schDateTo").focus();
                    return false;
                }
            }   
        }
    }else{
        if(schTo != "" && schFrom == ""){
            alert("<spring:message code='errors.date' arguments='조회시작일' />");
            $("#schDateFrom").focus();
            return false;
        }
    }
    
    return true;
    
}

<%-- 상세이동 --%>
function fn_moveDetail(data){
    $("#faqSeq").val(data.faqSeq);
    
    util_moveRequest("FaqManageVO", "<c:url value='/cmm/faq/faqManageSave.do'/>");
}

/*******************************************
 * 기능 함수 
 *******************************************/
</script>

</head>

<body>
<form:form commandName="FaqManageVO" name="FaqManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="30" /><!-- //목록사이즈 -->

<input type="hidden" name="faqSeq" id="faqSeq" value="" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <section class="content">
                <div class="location">
                    <h2>자주 묻는 질문 조회</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>키워드, 노출종류, 조회일</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:40%;">
                            <col style="width:10%;">
                            <col style="width:40%;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="schKind">키워드</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="searchCondition" name="searchCondition">
                                    <option value="sj">제목</option>
                                    <option value="cn">내용</option>
                                    <option value="sjcn">제목+내용</option>                                    
                                </select>
                                <input type="text" style="width:150px;" id="searchKeyword" name="searchKeyword"
                                       onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();" 
                                />
                            </td>
                            <th scope="row"><label for="">노출종류</label></th>
                            <td class="txt_l"> 
                                <c:forEach items="${faqKindList}" var="faqKindList" varStatus="status">
                                    <input type="checkbox" name="searchFaqKind" id="searchFaqKind_${faqKindList.system_code}" value="${faqKindList.system_code}" checked="checked">
                                    <label for="searchFaqKind_${faqKindList.system_code}">${faqKindList.code_name_kor}</label>
                                </c:forEach>
                            </td>                           
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">조회일</label></th>
                            <td colspan="3" class="txt_l">
                                <input type="text" id="schDateFrom" name="schDateFrom" readonly="readonly" style="width:80px;" />
                                <span class="dateDot">~</span>
                                <input type="text" id="schDateTo" name="schDateTo" readonly="readonly" style="width:80px;" />
                                &nbsp;
                                <input type="radio" id="schAll" name="schDateRdo" checked="checked" /><label for="schAll"> 전체 </label>
                                <input type="radio" id="schWeek" name="schDateRdo" /><label for="schWeek"> 최근 1주 </label>
                                <input type="radio" id="schMonth" name="schDateRdo" /><label for="schMonth"> 최근 1달 </label>
                                <input type="radio" id="schYear" name="schDateRdo" /><label for="schYear"> 최근 1년 </label>
                                
                            </td>                           
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnSearch">검색</a></span>
                    <span class="btn_gray2"><a href="javascript:void(0);" id="btnSearchClear">초기화</a></span>
                </div>
                <!-- // btn_type3 -->
                
                <%-- rowcount 공통 --%>
                <p class="amount">총 <em id="totCnt">0</em> 건</p>
                
                <%-- grid --%>
                <div id="jqxgrid" class="tb_list1"></div>
                
                <!-- // tb_list1 -->
                <div class="pagination">
                    <%-- paging 공통 --%>
                    <div id="pagingNavi"></div>
                    
                    <div class="btn_type3">
                        <!-- <div class="left"><span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span></div> -->
                        <div class="right"><span class="btn_gray1"><a href="javascript:void(0);" id="btnReg">등록</a></span></div>
                    </div>
                </div>
                <!-- // paging_area -->

                

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>
<%-- grid excel --%>
<div style="display: none;">
<div id="jqxgridExcel"></div>
</div>
</body>
</html>