<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
     /**
      * @Name : pushList.jsp
      * @Description : Push 목록
      * @Modification Information
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
                currentText: util_getDate()
            });
        });
    </script>
    <script language="javascript" type="text/javascript">

        /*******************************************
         * 전역 변수
         *******************************************/
        var g_initCheckboxArr = ["searchDeviceType","searchPushMessageType","searchSendType","searchSendYn"];

        /*******************************************
         * 이벤트 함수
         *******************************************/

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/pushmng/pushSendList.do'/>";
            var param = new Object();
            param.paramMenuId = "14002";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>

            /* 검색조건 */
            if(${!empty paramVO.searchCondition && paramVO.searchCondition != 'all'}){
                $("#searchCondition").val('${paramVO.searchCondition}')
            }else{
                $("#searchCondition").val('all');
            }

            /* 검색 키워드 */
            if(${!empty paramVO.searchKeyword && paramVO.searchKeyword != ''}){
                $("#searchKeyword").val('${paramVO.searchKeyword}');
            }else{
                $("#searchKeyword").val('');
            }	
            
            /*searchDeviceType checkbox*/
            <c:choose>
            <c:when test="${!empty paramVO.searchDeviceType && paramVO.searchDeviceType[0] != '[all]' && paramVO.searchDeviceType[0] != ''}">
            $("#searchDeviceType_all").attr("checked", false);
                <c:forEach items="${deviceTypeList}" var="deviceType" varStatus="status">
                    <c:forEach items="${paramVO.searchDeviceType}" var="searchDeviceType" varStatus="status">
                        var searchDeviceType = "${searchDeviceType}";
                        searchDeviceType = searchDeviceType.replace(/\[/g,'').replace(/\]/g,'');

                        if('${deviceType.system_code}' == searchDeviceType){
                            $("#searchDeviceType_${deviceType.system_code}").attr("checked", true);
                        }else if(searchDeviceType =='all'){
                            $("input[name='searchDeviceType']").attr("checked", false);
                            $("#searchDeviceType_all").attr("checked", true);
                        }
                    </c:forEach>
                </c:forEach>
            </c:when>
            <c:otherwise>
                $("input[name='searchDeviceType']").attr("checked", false);
                $("#searchDeviceType_all").attr("checked", true);
            </c:otherwise>
        </c:choose>
            
            /*searchPushMessageType checkbox*/
            <c:choose>
                <c:when test="${!empty paramVO.searchPushMessageType && paramVO.searchPushMessageType[0] != '[all]' && paramVO.searchPushMessageType[0] != ''}">
                $("#searchPushMessageType_all").attr("checked", false);
                    <c:forEach items="${pushMessageTypeList}" var="pushMessageTypeList" varStatus="status">
                        <c:forEach items="${paramVO.searchPushMessageType}" var="searchPushMessageType" varStatus="status">
                            var searchPushMessageType = "${searchPushMessageType}";
                            searchPushMessageType = searchPushMessageType.replace(/\[/g,'').replace(/\]/g,'');

                            if('${pushMessageTypeList.system_code}' == searchPushMessageType){
                                $("#searchPushMessageType_${pushMessageTypeList.system_code}").attr("checked", true);
                            }else if(searchPushMessageType =='all'){
                                $("input[name='searchPushMessageType']").attr("checked", false);
                                $("#searchPushMessageType_all").attr("checked", true);
                            }
                        </c:forEach>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    $("input[name='searchPushMessageType']").attr("checked", false);
                    $("#searchPushMessageType_all").attr("checked", true);
                </c:otherwise>
            </c:choose>
            
            /*searchSendType checkbox*/
            <c:choose>
                <c:when test="${!empty paramVO.searchSendType && paramVO.searchSendType[0] != '[all]' && paramVO.searchSendType[0] != ''}">
                $("#searchSendType_all").attr("checked", false);
                    <c:forEach items="${sendTypeList}" var="sendTypeList" varStatus="status">
                        <c:forEach items="${paramVO.searchSendType}" var="searchSendType" varStatus="status">
                            var searchSendType = "${searchSendType}";
                            searchSendType = searchSendType.replace(/\[/g,'').replace(/\]/g,'');

                            if('${sendTypeList.system_code}' == searchSendType){
                                $("#searchSendType_${sendTypeList.system_code}").attr("checked", true);
                            }else if(searchSendType =='all'){
                                $("input[name='searchSendType']").attr("checked", false);
                                $("#searchSendType_all").attr("checked", true);
                            }
                        </c:forEach>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    $("input[name='searchSendType']").attr("checked", false);
                    $("#searchSendType_all").attr("checked", true);

                </c:otherwise>
            </c:choose>

            /*searchSendYn checkbox*/
            <c:choose>
                <c:when test="${!empty paramVO.searchSendYn && paramVO.searchSendYn[0] != '[all]' && paramVO.searchSendYn[0]!= ''}">
                $("#searchSendYn_all").attr("checked", false);
                    <c:forEach items="${paramVO.searchSendYn}" var="searchSendYn" varStatus="status">
                        var searchSendYn = "${searchSendYn}";
                        searchSendYn = searchSendYn.replace(/\[/g,'').replace(/\]/g,'');

                        if(searchSendYn == 'Y'){
                            $("#searchSendYn_Y").attr("checked", true);
                        }else if(searchSendYn == 'N'){
                            $("#searchSendYn_N").attr("checked", true);
                        }else if(searchSendYn == 'all'){
                            $("input[name='searchSendYn']").attr("checked", false);
                            $("#searchSendYn_all").attr("checked", true);
                        }
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    $("input[name='searchSendYn']").attr("checked", false);
                    $("#searchSendYn_all").attr("checked", true);

                </c:otherwise>
            </c:choose>

            /*조회기간*/
            if(${!empty paramVO.searchDateType && paramVO.searchDateType != 'all'}){
                if("${paramVO.searchDateType}" == "create"){
                    //등록일 일 경우
                    $("#searchDateType").val("create");
                }else{
                    //수정일 일 경우
                    $("#searchDateType").val("update");
                }
                $("#searchDateFrom").datepicker("option", "disabled", false);
                $("#searchDateTo").datepicker("option", "disabled", false);
                $("input[name=searchDateRdo]").prop("disabled", false);

                $("#searchDateFrom").val('${paramVO.searchDateFrom}');
                $("#searchDateTo").val('${paramVO.searchDateTo}');
            }else{
                $("#searchDateType").val("all")
                $("#searchDateFrom").val('');
                $("#searchDateTo").val('');
                $("#searchDateFrom").datepicker("option", "disabled", true);
                $("#searchDateTo").datepicker("option", "disabled", true);
                $("input[name=searchDateRdo]").prop("disabled", true);
            }

            //조회일 searchDateType changeAction
            $("#searchDateType").change(function(){
                if($(this).val() == "all"){
                    $("#searchDateFrom").val("");
                    $("#searchDateTo").val("");

                    $("#searchDateFrom").datepicker("option", "disabled", true);
                    $("#searchDateTo").datepicker("option", "disabled", true);
                    $("#searchDateFrom").attr('readonly','readonly');
                    $("#searchDateTo").attr('readonly','readonly');
                    
                    $("input[name=searchDateRdo]").prop("disabled", true);
                    $("input[name=searchDateRdo]").eq(0).prop('checked', true);
                    
                }else{
                    $("#searchDateFrom").datepicker("option", "disabled", false);
                    $("#searchDateTo").datepicker("option", "disabled", false);
                    $("#searchDateFrom").removeAttr('readonly');
                    $("#searchDateTo").removeAttr('readonly');
                    
                    $("input[name=searchDateRdo]").prop("disabled", false);
                }
            });

            //조회일 radio
            $("input[name=searchDateRdo]").change(function(){
                var id = $(this).attr("id");

                var searchDateFrom = "";
                var searchDateTo = util_getDate();

/*                 if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
                    searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
                } */

                //week
                if(id == "searchWeek"){
                    searchDateFrom = util_addDate(searchDateTo, "d", -7);
                    //month
                }else if(id == "searchMonth"){
                    searchDateFrom = util_addDate(searchDateTo, "m", -1);
                    //year
                }else if(id == "searchYear"){
                    searchDateFrom = util_addDate(searchDateTo, "y", -1);
                    //all
                }else{
                    searchDateFrom = "";
                    searchDateTo = "";
                }

                if(util_chkReturn(searchDateFrom, "s") != "") searchDateFrom = util_setFmDate(searchDateFrom);
                if(util_chkReturn(searchDateTo, "s") != "") searchDateTo = util_setFmDate(searchDateTo);

                $("#searchDateFrom").val(searchDateFrom);
                $("#searchDateTo").val(searchDateTo);
            });

            //검색
            $("#btnSearch").click(function(){
                fn_search();
            });

            //초기화
            $("#btnSearchClear").click(function(){
                $("#pageIndex").val("1");
				
                $('input[name=searchDeviceType]:checked').val("");
                $('input[name=searchSendType]:checked').val("");
                $('input[name=searchPushMessageType]:checked').val("");
                $('input[name=searchSendYn]:checked').val("all");

                //검색 text
                $("#searchCondition").val("");
                $("#searchKeyword").val("");
                
                $("#searchCondition").val("");
                $("#searchCondition").val("");
                $("#searchCondition").val("");
                $("#searchCondition").val("");
                
                //체크박스 초기화
                $("input[name='searchSendYnAllYn']").val('');
                $("input[name='searchSendTypeAllYn']").val('');
                $("input[name='searchPushMessageTypeAllYn']").val('');
                $("input[name='searchDeviceTypeAllYn']").val('');

                //날짜 초기화
                $("#searchDateType").val('')
                $("#searchDateFrom").val('');
                $("#searchDateTo").val('');


                util_moveRequest("PushVO", "<c:url value='/apt/pushmng/pushSendList.do'/>", "_top");
            });

            //엑셀
            $("#btnExcel").click(function(){
                fn_searchExcel('excel');
            });

            //다운로드
            $("#btnDown").click(function(){
                fn_searchExcel('down');
            });

            //추가
            $("#btnAdd").click(function(){
                fn_addPushMessage();
            });

            //그리드 셋팅
            //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
            gfn_gridOption('jqxgrid',{
                columns: [
                    { text: '플랫폼', datafield: 'codeNameKor', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: '제목', datafield: 'pushMessageTitle', width: '20%', cellsalign: 'left', align: 'center' },
                    { text: '전송상태', datafield: 'sendYn', width: '7%', cellsalign: 'left', align: 'center' },
                  	{ text: '전송유형', datafield: 'sendType', width: '7%', cellsalign: 'left', align: 'center' },
                    { text: '전송/예약일시', datafield: 'sendDate', width: '15%', cellsalign: 'left', align: 'center' },
                    { text: '등록자', datafield: 'createId', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: '등록일', datafield: 'createDate', width: '16%', cellsalign: 'left', align: 'center' },
                    { text: '수정일', datafield: 'updateDate', width: '15%', cellsalign: 'left', align: 'center' },

                    { text: 'pushMessageRegno', datafield: 'pushMessageRegno', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
                ]
            });

            $('#jqxgrid').on('rowclick', function (event) {
                var row = event.args.rowindex;
                var data = $('#jqxgrid').jqxGrid('getrowdata', row);
                //상세이동
                fn_moveDetail(data);

            }).on('bindingcomplete', function(event){
                $("#jqxgrid").jqxGrid('sortby', 'pushMessageRegno', 'desc');
                //로딩 호출
                gfn_setLoading(false);
            });


            <%-- 체크박스 선택 onclick이벤트 설정--%>
            for(var i=0; i<g_initCheckboxArr.length; i++){
                gfn_initCheckbox(g_initCheckboxArr[i]);
            }

            //조회
            fn_search($("#pageIndex").val());
            
            //[s] 조회일 date format 세팅1 2017.04.18 한유진
            // start date 변경
            $("#searchDateFrom").change(function() {
                setEndDate();
            });
            // end date 변경
            $("#searchDateTo").change(function() {
                setEndDate();
            });
            //[e] 조회일 date format 세팅1 2017.04.18 한유진
        });

        /*******************************************
         * 기능 함수
         *******************************************/

        <%-- 검색 --%>
        function fn_search(pageIndex){
            if(!fn_validate()){
                return;
            }

            //page
            if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1";
            $("#pageIndex").val(pageIndex);

            <%-- 체크박스 선택 전체여부 셋팅 --%>
            for(var i=0; i<g_initCheckboxArr.length; i++){
                gfn_setSelectAllYn(g_initCheckboxArr[i]);
            }

            //로딩 호출
            gfn_setLoading(true);

            //page setting
            var url = "<c:url value='/apt/pushmng/SelectPushSendList.ajax'/>";
            var param = $("#PushVO").serialize();
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
                    "50"
            );
        }

        <%-- validate --%>
        function fn_validate(){
            //조회일 체크
            if($("#searchDateType").val() != "all"){
                var searchFrom = util_replaceAll($("#searchDateFrom").val(), "-", "");
                var searchTo = util_replaceAll($("#searchDateTo").val(), "-", "");

                if(searchFrom != ""){
                    if(!util_isDate(searchFrom)){
                        alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
                        $("#searchDateFrom").focus();
                        return false;
                    }

                    if(searchTo == ""){
                        alert("<spring:message code='errors.date' arguments='조회종료일' />");
                        $("#searchDateTo").focus();
                        return false;
                    }else{
                        if(!util_isDate(searchTo)){
                            alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
                            $("#searchDateTo").focus();
                            return false;
                        }else{
                            if(searchFrom > searchTo){
                                alert("종료일이 시작일보다 클 수 없습니다.");
                                $("#searchDateTo").focus();
                                return false;
                            }
                        }
                    }
                }else{
                    if(searchTo != "" && searchFrom == ""){
                        alert("<spring:message code='errors.date' arguments='조회시작일' />");
                        $("#searchDateFrom").focus();
                        return false;
                    }
                    if(searchTo == "" && searchFrom == ""){
                        alert($("#searchDateType option:selected").text() + "을 입력해주세요");
                        $("#searchDateFrom").focus();
                        return false;
                    }
                }


            }
            return true;
        }
        
        <%-- 상세이동 --%>
        function fn_moveDetail(data){
            $("#pushMessageRegno").val(data.pushMessageRegno);

            util_moveRequest("PushVO", "<c:url value='/apt/pushmng/pushDtl.do'/>", "_top");
        }

        <%-- 푸시추가 --%>
        function fn_addPushMessage(){
            util_moveRequest("PushVO", "<c:url value='/apt/pushmng/pushAdd.do'/>", "_top");
        }

        //[s] 조회일 date format 세팅2 2017.04.18 한유진
        function isFromToDate(startDate, endDate, startTime, endTime) {
        	var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
        	var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
        	return endDate.getTime() >= startDate.getTime();
        }

        function setEndDate() {

        	var searchDate = util_getDate();

        	if($("#searchDateFrom").val().length < 10){
            	$("#searchDateFrom").val(util_setFmDate(searchDate));
        	}

        	if($("#searchDateTo").val().length < 10){
            	$("#searchDateTo").val(util_setFmDate(searchDate));
        	}

        	var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
        	var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));

        	if(!isFromToDate(searchDateFrom, searchDateTo, "00", "00")) {
            	$("#searchDateTo").val(util_setFmDate(searchDateFrom));
        	}
        }
        //[e] 조회일 date format 세팅2 2017.04.18 한유진
<%-- 엑셀다운 --%>
function fn_searchExcel(excelType){
    $("#excelType").val(excelType);
    
    //로딩 호출
    gfn_setLoading(true, "엑셀 조회중 입니다.");
    $("#EXCEL_FRAME").ready(function(){
    	gfn_setLoading(false);
    });
    
    util_moveRequest("PushVO", "<c:url value='/apt/pushmng/pushSendListExcel.do'/>", "EXCEL_FRAME");
    
    //excelType rest
    $("#excelType").val("");
}
    </script>

</head>

<body>
<form:form commandName="PushVO" name="PushVO" method="post">
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
    <input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

    <input type="hidden" name="pushMessageRegno" id="pushMessageRegno" value=""/>
	<input type="hidden" name="excelType" id="excelType" value="" />

    <input type="hidden" name="searchSendYnAllYn" id="searchSendYnAllYn" value="N" />
    <input type="hidden" name="searchSendTypeAllYn" id="searchSendTypeAllYn" value="N" />
    <input type="hidden" name="searchPushMessageTypeAllYn" id="searchPushMessageTypeAllYn" value="N" />
	<input type="hidden" name="searchDeviceTypeAllYn" id="searchDeviceTypeAllYn" value="N" />
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
                    <h2>Push 전송 관리</h2>
                </div>
                <!-- // locatioin -->
                <div class="tb_write1">
                    <table>
                        <caption>푸시 메시지 검색정보 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">구분</label></th>
                            <td class="txt_l" colspan="3">
                                <select id="searchCondition" name="searchCondition">
                                    <option value="all">전체</option>
                                    <option value="title">제목</option>
                                    <option value="createId">등록자</option>
                                </select>
                                <input type="text" style="width:350px" id="searchKeyword" name="searchKeyword">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">플랫폼</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                         <li>
                                            <input type="checkbox" name="searchDeviceType" id="searchDeviceType_all" value="all" checked="checked">
                                            <label for="searchDeviceType_all">전체</label>
                                        </li>                                   
                                        <c:forEach items="${deviceTypeList}" var="deviceType" varStatus="status">
                                            <li><input type="checkbox" name="searchDeviceType" id="searchDeviceType_${deviceType.system_code}" value="${deviceType.system_code}">
                                                <label for="searchDeviceType_${deviceType.system_code}">${deviceType.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                            <th scope="row">전송유형</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchSendType" id="searchSendType_all" value="all" checked="checked">
                                            <label for="searchSendType_all">전체</label>
                                        </li>
                                        <c:forEach items="${sendTypeList}" var="sendTypeList" varStatus="status">
                                            <li><input type="checkbox" name="searchSendType" id="searchSendType_${sendTypeList.system_code}" value="${sendTypeList.system_code}">
                                                <label for="searchSendType_${sendTypeList.system_code}">${sendTypeList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>                            
                        </tr>
                        <tr>
                            <th scope="row">타입</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchPushMessageType" id="searchPushMessageType_all" value="all" checked="checked">
                                            <label for="searchPushMessageType_all">전체</label>
                                        </li>
                                        <c:forEach items="${pushMessageTypeList}" var="pushMessageTypeList" varStatus="status">
                                            <li><input type="checkbox" name="searchPushMessageType" id="searchPushMessageType_${pushMessageTypeList.system_code}" value="${pushMessageTypeList.system_code}">
                                                <label for="searchPushMessageType_${pushMessageTypeList.system_code}">${pushMessageTypeList.code_name_eng}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                           </td>
                            <th scope="row">전송상태</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li><input type="checkbox" name="searchSendYn" id="searchSendYn_all" value="all" checked="checked"><label for="searchSendYn_all">전체</label></li>
                                        <li><input type="checkbox" name="searchSendYn" id="searchSendYn_Y" value="Y"><label for="searchSendYn_Y">완료</label></li>
                                        <li><input type="checkbox" name="searchSendYn" id="searchSendYn_N" value="N"><label for="searchSendYn_N">대기</label></li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>                      
                        <tr>
                            <th scope="row"><label for="chk2">조회기간</label></th>
                            <td class="txt_l" colspan="3">
                                <select title="구분 입력" id="searchDateType" name="searchDateType">
                                    <option value="all">전체</option>
                                    <option value="create">등록일</option>
                                    <option value="update">수정일</option>
                                </select>
                                <input type="text" id="searchDateFrom" name="searchDateFrom" readonly="readonly" style="width:80px;" disabled="disabled" />
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo" readonly="readonly" style="width:80px;" disabled="disabled" />
                                &nbsp;
                                <input type="radio" id="searchAll" name="searchDateRdo" checked="checked" /><label for="searchAll"> 전체 </label>
                                <input type="radio" id="searchWeek" name="searchDateRdo" /><label for="searchWeek"> 최근 1주 </label>
                                <input type="radio" id="searchMonth" name="searchDateRdo" /><label for="searchMonth"> 최근 1달 </label>
                                <input type="radio" id="searchYear" name="searchDateRdo" /><label for="searchYear"> 최근 1년 </label>
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
                        <div class="left">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnDown">다운로드</a></span>
                        </div>
                        <div class="right">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnAdd">추가</a></span>
                        </div>
                    </div>
                </div>
                <!-- // paging_area -->
            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>
<%-- 엑셀용 frame --%>
<iframe id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>