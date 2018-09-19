<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
    /**
     * @Name : viewFixMessageList.jsp
     * @Description : [FIX Message] 목록
     * @Modification Information
     *
     * <pre>
     *  Modification Information
     *  수정일        수정자    수정내용
     *  ----------  ------  ----------
     *  2017.03.03  이선하    최초  생성
     * </pre>
     *
     * @author 이선하
     * @since 2017.03.03
     * @version 2.0
     *
     */
    --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

    <%-- jqwidgets --%>
    <link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css"/>
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
        $(function () {
            // 달력
            $("#searchDateFrom, #searchDateTo").datepicker({
                minDate: '-7d',
                maxDate: '+0d',
                <%--currentText: util_getDate()--%>
                showOn: "button",
                dateFormat: 'yy-mm-dd',
                buttonImage: "/images/apt/calendar.png",
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
        var g_initCheckboxArr = ["searchMessageType", "rejectYn", "hubType"];

        /*******************************************
         * 이벤트 함수
         *******************************************/
        function fn_login(){
            var url = "<c:url value='/apt/fixMsg/viewFixMessageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15001";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function () {
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>

            //날짜 : 오늘부터 일주일 전 주기에 맞춘 조회기간 (paramVO가 없을경우)
            var searchDateFrom = "";
            var searchDateTo = util_getDate();

            searchDateFrom = util_addDate(searchDateTo, "d", -7);
            <c:if test="${empty paramVO.searchDateFrom}">
            if(util_chkReturn(searchDateFrom, "s") != ""){
                searchDateFrom = util_setFmDate(searchDateFrom);
                //시간 : 하루
                $("#searchTimeHourFrom").val("00");
                $("#searchTimeMinuteFrom").val("00");
            }
            if(util_chkReturn(searchDateTo, "s") != ""){
                searchDateTo = util_setFmDate(searchDateTo);
                //시간 : 하루
                $("#searchTimeHourTo").val("23");
                $("#searchTimeMinuteTo").val("59");
            }

            $("#searchDateFrom").val(searchDateFrom);
            $("#searchDateTo").val(searchDateTo);
            </c:if>

            /*----------------------------------- Acceptor, Initiator 구분 -------------------------------------*/

            if ($("#tabDivision").val() == 'initiator') {
                $("#initiatorArea").show();
                $("#acceptorArea").hide();
                $("#rejectTypeTr").hide();
                $("#tabAcc").removeClass("on");
                $("#tabInit").addClass("on");
            } else {
                $("#tabDivision").val("acceptor");
                $("#initiatorArea").hide();
                $("#acceptorArea").show();
                $("#rejectTypeTr").show();
                $("#tabAcc").addClass("on");
                $("#tabInit").removeClass("on");
            }

            //검색
            $("#btnSearch").click(function () {
                fn_search();
            });

            //초기화
            $("#btnSearchClear").click(function () {
                $("#searchCondition").val("all");
                $("#searchKeyword").val("");
                searchDateFrom = util_setFmDate(searchDateFrom);
                searchDateTo = util_setFmDate(searchDateTo);
                $("#searchDateFrom").val(searchDateFrom);
                $("#searchDateTo").val(searchDateTo);
                $("#searchTimeHourFrom").val("00");
                $("#searchTimeMinuteFrom").val("00");
                $("#searchTimeHourTo").val("23");
                $("#searchTimeMinuteTo").val("59");
                fn_listReturn();
            });

            //엑셀
            $("#btnExcel").click(function () {
                fn_searchExcel('excel');
            });

            //다운로드
            $("#btnDown").click(function(){
                fn_searchExcel('down');
            });

            //tab division
            $("#tabAcceptor").click(function () {
                $("#tabDivision").val("acceptor");
                fn_listReturn();
            });
            $("#tabInitiator").click(function () {
                $("#tabDivision").val("initiator");
                fn_listReturn();
            });

            //datepicker disable
            $("input[name=searchDateRdo]").prop("disabled", true);

            //그리드 셋팅
            //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
            gfn_gridOption('acceptorGrid', {
                columns: [
                    {text: '기업이름', datafield: 'companyName', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: '기업코드', datafield: 'companyId', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'Server', datafield: 'hubTypeName', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'seqNum', datafield: 'seq', width: '10%', cellsalign: 'left', align: 'center'},
                    {
                        text: 'senderCompId',
                        datafield: 'senderCompId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {
                        text: 'DeliverToCompId',
                        datafield: 'deliverCompId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {text: 'MsgType', datafield: 'msgTypeName', width: '10%', cellsalign: 'left', align: 'center'},
                    {
                        text: 'CliOrdId',
                        datafield: 'clientOrderId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {text: 'ListId', datafield: 'listOrderId', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'Order' +
                    '', datafield: 'orderId', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'Buy-side', datafield: 'rejectYnNameKor', width: '5%', cellsalign: 'left', align: 'center'},
                    {text: '수신일', datafield: 'createDate', width: '10%', cellsalign: 'left', align: 'center'},

                    {text: 'dt', datafield: 'dt', width: '0%', cellsalign: 'left', align: 'center', hidden: true},
                ]
            });
            $('#acceptorGrid').on('rowclick', function (event) {
                var row = event.args.rowindex;
                var data = $('#acceptorGrid').jqxGrid('getrowdata', row);

                //상세이동
                fn_moveDetailAcceptor(data);
            }).on('bindingcomplete', function (event) {
                $("#acceptorGrid").jqxGrid('sortby', 'createDate', 'desc');
                //로딩 호출
                gfn_setLoading(false);
            });

            gfn_gridOption('initiatorGrid', {
                columns: [
                    {text: '기업이름', datafield: 'companyName', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: '기업코드', datafield: 'companyId', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'Server', datafield: 'hubTypeName', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: 'seqNum', datafield: 'seq', width: '10%', cellsalign: 'left', align: 'center'},
                    {
                        text: 'senderCompId',
                        datafield: 'senderCompId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {
                        text: 'DeliverToCompId',
                        datafield: 'deliverCompId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {text: 'MsgType', datafield: 'msgTypeName', width: '10%', cellsalign: 'left', align: 'center'},
                    {
                        text: 'CliOrdId',
                        datafield: 'clientOrderId',
                        width: '10%',
                        cellsalign: 'left',
                        align: 'center'
                    },
                    {text: 'ListId', datafield: 'listOrderId', width: '10%', cellsalign: 'left', align: 'center'},
                    {text: '수신일', datafield: 'createDate', width: '10%', cellsalign: 'left', align: 'center'},

                    {text: 'dt', datafield: 'dt', width: '0%', cellsalign: 'left', align: 'center', hidden: true},
                ]
            });
            $('#initiatorGrid').on('rowclick', function (event) {
                var row = event.args.rowindex;
                var data = $('#initiatorGrid').jqxGrid('getrowdata', row);

                //상세이동
                fn_moveDetailInitiator(data);
            }).on('bindingcomplete', function (event) {
                $("#initiatorGrid").jqxGrid('sortby', 'createDate', 'desc');
                //로딩 호출
                gfn_setLoading(false);
            });

            <%-- 체크박스 선택 onclick이벤트 설정--%>
            for (var i = 0; i < g_initCheckboxArr.length; i++) {
                gfn_initCheckbox(g_initCheckboxArr[i]);
            }

            //조회
            fn_search($("#pageIndex").val());
            
            //[s] 조회일 date format 세팅1 2017.04.18 한유진
            // start date 변경
            $("#searchDateFrom").change(function() {
            	var schDate =  $("#searchDateFrom").val();
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
        function fn_search(pageIndex) {
            if (!fn_validate()) {
                return;
            }

            //page
            if (util_chkReturn(pageIndex, "s") == "") pageIndex = "1";
            $("#pageIndex").val(pageIndex);

            <%-- 체크박스 선택 전체여부 셋팅 --%>
            for (var i = 0; i < g_initCheckboxArr.length; i++) {
                gfn_setSelectAllYn(g_initCheckboxArr[i]);
            }

            //로딩 호출
            gfn_setLoading(true);

            if ($("#tabDivision").val() == 'initiator') {
                //page setting
                var url = "<c:url value='/apt/fixMsg/viewInitFixMessageList.ajax'/>";
                var param = $("#SearchFixMessageVO").serialize();
                var callBackFunc = "fn_searchCallBack";
                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            } else {
                //page setting
                var url = "<c:url value='/apt/fixMsg/viewFixMessageList.ajax'/>";
                var param = $("#SearchFixMessageVO").serialize();
                var callBackFunc = "fn_searchCallBack";
                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            }

        }
        function fn_searchCallBack(data) {
            //로그인 처리
            if (data.error == -1) {
                fn_login();
                return;
            }

            var resultList = data.resultList;
            var resultListTotalCount = data.resultListTotalCount;

            $("#totCnt").text(util_setCommas(resultListTotalCount));

            $("#pagingNavi >").remove();

            if ($("#tabDivision").val() == 'initiator') {
                //grid common
                gfn_gridData(resultList, "initiatorGrid");
            } else {
                //grid common
                gfn_gridData(resultList, "acceptorGrid");
            }

            //page common
            pageNavigator(
                    $("#pageIndex").val(),
                    resultListTotalCount,
                    "fn_search",
                    "50"
            );
        }


        <%-- tab 이동 (restart) --%>
        function fn_listReturn() {
            $("#pageIndex").val("1");

            util_moveRequest("SearchFixMessageVO", "<c:url value='/apt/fixMsg/viewFixMessageList.do'/>", "_top");
        }


        <%-- validate --%>
        function fn_validate() {
            //조회일 체크
            var searchFrom = util_replaceAll($("#searchDateFrom").val(), "-", "");
            var searchTo = util_replaceAll($("#searchDateTo").val(), "-", "");
            var searchTimeHourFrom = $("#searchTimeHourFrom").val();
            var searchTimeHourTo = $("#searchTimeHourTo").val();
            var searchTimeMinuteFrom = $("#searchTimeMinuteFrom").val();
            var searchTimeMinuteTo = $("#searchTimeMinuteTo").val();

            /*날짜*/
            if (searchFrom != "") {
                if (!util_isDate(searchFrom)) {
                    alert("<spring:message code='errors.invalid' arguments='조회시작일' />");
                    $("#searchDateFrom").focus();
                    return false;
                }

                if (searchTo == "") {
                    alert("<spring:message code='errors.date' arguments='조회종료일' />");
                    $("#searchDateTo").focus();
                    return false;
                } else {
                    if (!util_isDate(searchTo)) {
                        alert("<spring:message code='errors.invalid' arguments='조회종료일' />");
                        $("#searchDateTo").focus();
                        return false;
                    } else {
                        if (searchFrom > searchTo) {
                            alert("종료일이 시작일보다 클 수 없습니다.");
                            $("#searchDateTo").focus();
                            return false;
                        }
                    }
                }
            } else {
                if (searchTo != "" && searchFrom == "") {
                    alert("<spring:message code='errors.date' arguments='조회시작일' />");
                    $("#searchDateFrom").focus();
                    return false;
                }
            }

            /*시간*/
            if(util_chkReturn(searchTimeHourFrom, "s") == "") {
                alert("<spring:message code='errors.required' arguments='조회시간'/>");
                $('#searchTimeHourFrom').focus();
                return false;
            }else{
                if(!util_isNum(searchTimeHourFrom)){
                    alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                    $('#searchTimeHourFrom').focus();
                    return false;
                }else{
                    if(Number(searchTimeHourFrom) <= -1 || Number(searchTimeHourFrom) > 24){
                        alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                        $('#searchTimeHourFrom').focus();
                        return false;
                    }
                }
            }

            if(util_chkReturn(searchTimeHourTo, "s") == "") {
                alert("<spring:message code='errors.required' arguments='조회시간'/>");
                $('#searchTimeHourTo').focus();
                return false;
            }else{
                if(!util_isNum(searchTimeHourTo)){
                    alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                    $('#searchTimeHourTo').focus();
                    return false;
                }else{
                    if(Number(searchTimeHourTo) <= -1 || Number(searchTimeHourTo) > 23){
                        alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                        $('#searchTimeHourTo').focus();
                        return false;
                    }
                }
            }

            /*분*/
            if(util_chkReturn(searchTimeMinuteFrom, "s") == "") {
                alert("<spring:message code='errors.required' arguments='조회시간(분)'/>");
                $('#searchTimeMinuteFrom').focus();
                return false;
            }else{
                if(!util_isNum(searchTimeMinuteFrom)){
                    alert("<spring:message code='errors.integer' arguments='조회시간(분)'/>");
                    $('#searchTimeMinuteFrom').focus();
                    return false;
                }else{
                    if(Number(searchTimeMinuteFrom) <= -1 || Number(searchTimeMinuteFrom) > 59){
                        alert("<spring:message code='errors.range' arguments='조회시간,00,59'/>");
                        $('#searchTimeMinuteFrom').focus();
                        return false;
                    }
                }
            }

            if(util_chkReturn(searchTimeMinuteTo, "s") == "") {
                alert("<spring:message code='errors.required' arguments='조회시간(분)'/>");
                $('#searchTimeMinuteTo').focus();
                return false;
            }else{
                if(!util_isNum(searchTimeMinuteTo)){
                    alert("<spring:message code='errors.integer' arguments='조회시간(분)'/>");
                    $('#searchTimeMinuteTo').focus();
                    return false;
                }else{
                    if(Number(searchTimeMinuteTo) <= -1 || Number(searchTimeMinuteTo) > 59){
                        alert("<spring:message code='errors.range' arguments='조회시간,00,59'/>");
                        $('#searchTimeMinuteTo').focus();
                        return false;
                    }
                }
            }

            var searchDateTimeFrom = $.trim(searchFrom + searchTimeHourFrom + searchTimeMinuteFrom);
            var searchDateTimeTo = $.trim(searchTo + searchTimeHourTo + searchTimeMinuteTo);
//            $("#searchDateTimeFrom").val(util_setFmDateTime(searchDateTimeFrom,12));
            $("#searchDateTimeFrom").val(searchDateTimeFrom);
            $("#searchDateTimeTo").val(searchDateTimeTo);

            return true;
        }

        <%-- 엑셀다운 --%>
        function fn_searchExcel(excelType) {
            $("#excelType").val(excelType);

            //로딩 호출
            gfn_setLoading(true, "엑셀 조회중 입니다.");
            $("#EXCEL_FRAME").ready(function () {
                gfn_setLoading(false);
            });

            if ($("#tabDivision").val() == 'initiator') {
                util_moveRequest("SearchFixMessageVO", "<c:url value='/apt/fixMsg/viewFixMessageInitiatorExcel.do'/>", "EXCEL_FRAME");
            }else{
                util_moveRequest("SearchFixMessageVO", "<c:url value='/apt/fixMsg/viewFixMessageAccountExcel.do'/>", "EXCEL_FRAME");
            }

            //excelType rest
            $("#excelType").val("");

        }
        <%-- Acceptor 상세이동 --%>
        function fn_moveDetailAcceptor(data) {
//            param : dt, sender_comp_id, seq, division
            var paramVO = "dt=" + data.dt + "&senderCompId=" + data.senderCompId + "&seq=" + data.seq + "&tabDivision=" + $("#tabDivision").val();
            window.open("<c:url value='/apt/fixMsg/viewFixMessageDtlAcceptorPopup.do?'/>" + paramVO, "_blank", "width=800, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
        }

        <%-- Initiator 상세이동 --%>
        function fn_moveDetailInitiator(data) {
//            param : dt, deliver_comp_id, seq, division
            var paramVO = "dt=" + data.dt + "&deliverCompId=" + data.deliverCompId + "&seq=" + data.seq + "&tabDivision=" + $("#tabDivision").val();
            window.open("<c:url value='/apt/fixMsg/viewFixMessageDtlInitiatorPopup.do?'/>" + paramVO, "_blank", "width=800, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
        }

      //[s] 조회일 date format 세팅2 2017.04.18 한유진
        function isFromToDate(startDate, endDate, startTime, endTime) {
        	var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
        	var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
        	return endDate.getTime() >= startDate.getTime();
        }

        function setEndDate() {
			
        	var currentDate = util_getDate();
        	var tempDate = util_addDate(currentDate, "d", -7);

        	// 10자리 미만 자동 오늘날짜 세팅
        	if($("#searchDateFrom").val().length < 10){
            	$("#searchDateFrom").val(util_setFmDate(currentDate));
            	$("#searchTimeHourFrom").val("00");
        		$("#searchTimeMinuteFrom").val("00");
        	}
        	if($("#searchDateTo").val().length < 10){
            	$("#searchDateTo").val(util_setFmDate(currentDate));
            	$("#searchTimeHourTo").val("23");
        		$("#searchTimeMinuteTo").val("59");
        	}

        	var searchDateFrom = $.trim(util_replaceAll($("#searchDateFrom").val(), "-", ""));
        	var searchDateTo = $.trim(util_replaceAll($("#searchDateTo").val(), "-", ""));

        	// 조회시작일 < 7일전  일 경우
         	if(!isFromToDate(tempDate,searchDateFrom,"00", "00")){
  				// 조회시작일이 범위 내 일 경우       		
         		if(searchDateFrom > tempDate){
         			$("#searchDateFrom").val(util_setFmDate(searchDateFrom));
         		} else { 
         			$("#searchDateFrom").val(util_setFmDate(tempDate));
         		}
  				
         		$("#searchTimeHourFrom").val("00");
        		$("#searchTimeMinuteFrom").val("00");
        		
         	// 조회시작일 > 현재날짜 일 경우 
         	}else if(!isFromToDate(searchDateFrom,currentDate,"00", "00")){
         		$("#searchDateFrom").val(util_setFmDate(currentDate));
         		$("#searchDateTo").val(util_setFmDate(currentDate));
        		$("#searchTimeHourFrom").val("00");
        		$("#searchTimeMinuteFrom").val("00");
        		$("#searchTimeHourTo").val("23");
        		$("#searchTimeMinuteTo").val("59");
         	}
         	
         	// 조회종료일 > 현재날짜 일 경우 
         	if(!isFromToDate(searchDateTo,currentDate,"00", "00")){
        		$("#searchDateTo").val(util_setFmDate(currentDate));
        		$("#searchTimeHourTo").val("23");
        		$("#searchTimeMinuteTo").val("59");
        	// 조회종료일이 범위 보다 작을 경우 
         	}else if(!isFromToDate(tempDate, searchDateTo,"00", "00")){
         		$("#searchDateTo").val(util_setFmDate(currentDate));
         		$("#searchTimeHourTo").val("23");
        		$("#searchTimeMinuteTo").val("59");
         	}
        	
         	// 조회 시작일 > 조회 종료일 일 경우 
        	if(!isFromToDate(searchDateFrom, searchDateTo, "00", "00")) {
        		
        		if(searchDateFrom > currentDate ){
        			$("#searchDateTo").val(util_setFmDate(currentDate));
        		}else {
        			$("#searchDateTo").val(util_setFmDate(searchDateFrom));
        		}
        		$("#searchTimeHourTo").val("23");
        		$("#searchTimeMinuteTo").val("59");
        	}
         	
        }
        //[e] 조회일 date format 세팅2 2017.04.18 한유진
    </script>


</head>

<body>
<form:form commandName="SearchFixMessageVO" name="SearchFixMessageVO" method="post">
    <input type="hidden" name="pageIndex" id="pageIndex"
           value="<c:out value='${paramVO.pageIndex}'/>"/><!-- //현재페이지번호 -->
    <input type="hidden" name="pageRowsize" id="pageRowsize" value="50"/><!-- //목록사이즈 -->

    <input type="hidden" name="searchMessageTypeAllYn" id="searchMessageTypeAllYn" value="N"/>
    <input type="hidden" name="rejectYnAllYn" id="rejectYnAllYn" value="N"/>
    <input type="hidden" name="hubTypeAllYn" id="hubTypeAllYn" value="N"/>

    <input type="hidden" name="dt" id="dt" value=""/>
    <input type="hidden" name="seq" id="seq" value=""/>
    <input type="hidden" name="senderCompId" id="senderCompId" value=""/>

    <input type="hidden" name="tabDivision" id="tabDivision" value="<c:out value='${paramVO.tabDivision}'/>"/>
    <input type="hidden" name="excelType" id="excelType"/>

    <%--날짜 form--%>
    <input type="hidden" name="searchDateTimeFrom" id="searchDateTimeFrom"/>
    <input type="hidden" name="searchDateTimeTo" id="searchDateTimeTo"/>

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
                    <h2>메시지 조회</h2>
                </div>
                <!-- // locatioin -->
                <div class="tab_menu">
                    <ul>
                        <li id="tabAcc" class="on"><a href="javascript:void(0)" id="tabAcceptor">Buy-side</a></li>
                        <li id="tabInit"><a href="javascript:void(0)" id="tabInitiator">Initiator</a></li>
                    </ul>
                </div>
                <div class="tb_write1">
                    <table>
                        <caption>Fix message 정보입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">구분</label></th>
                            <td class="txt_l">
                                <select id="searchCondition" name="searchCondition" style="width:150px">
                                    <option value="all"<c:if test="${paramVO.searchCondition == 'all'}">selected</c:if>>전체</option>
                                    <option value="seq"<c:if test="${paramVO.searchCondition == 'seq'}">selected</c:if>>SeqNum</option>
                                    <option value="sendCompId"<c:if test="${paramVO.searchCondition == 'sendCompId'}">selected</c:if>>SenderCompId</option>
                                    <option value="deliverToCompId"<c:if test="${paramVO.searchCondition == 'deliverToCompId'}">selected</c:if>>DeliverToCompId</option>
                                    <option value="cliOrdId"<c:if test="${paramVO.searchCondition == 'cliOrdId'}">selected</c:if>>clientId</option>
                                    <option value="listId"<c:if test="${paramVO.searchCondition == 'listId'}">selected</c:if>>listId</option>
                                </select>
                                <input type="text" style="width:350px" id="searchKeyword" name="searchKeyword"<c:if test="${!empty paramVO.searchKeyword}">value="${paramVO.searchKeyword}"</c:if>>
                            </td>
                        </tr>
                        <tr id="serverTypeTr">
                            <th scope="row">Server</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li><input type="checkbox" name="hubType" id="hubType_all" value="all"
                                                   checked="checked"><label for="hubType_all">전체</label></li>
                                        <c:forEach items="${hubTypeList}" var="hubTypeList" varStatus="status">
                                            <li><input type="checkbox" name="hubType"
                                                       id="hubType_${hubTypeList.commonCode}"
                                                       value="${hubTypeList.commonCode}">
                                                <label for="hubType_${hubTypeList.commonCode}">${hubTypeList.commonCodeName}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">MsgType</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li><input type="checkbox" name="searchMessageType" id="searchMessageType_all"
                                                   value="all" checked="checked">
                                            <label for="searchMessageType_all">전체</label>
                                        </li>
                                        <c:forEach items="${msgTypeList}" var="msgTypeList" varStatus="status">
                                            <li><input type="checkbox" name="searchMessageType"
                                                       id="searchMessageType_${msgTypeList.codeValue}"
                                                       value="${msgTypeList.codeValue}">
                                                <label for="searchMessageType_${msgTypeList.codeValue}">${msgTypeList.codeNm}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <a href="#none" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        <tr id="rejectTypeTr">
                            <th scope="row">Buy-side</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li><input type="checkbox" name="rejectYn" id="rejectYn_all" value="all"
                                                   checked="checked"><label for="rejectYn_all">전체</label></li>
                                        <li><input type="checkbox" name="rejectYn" id="rejectYn_N" value="N"><label
                                                for="rejectYn_N">승인</label></li>
                                        <li><input type="checkbox" name="rejectYn" id="rejectYn_Y" value="Y"><label
                                                for="rejectYn_Y">거부</label></li>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">조회기간</label></th>
                            <td class="txt_l">
                                <input type="text" id="searchDateFrom" name="searchDateFrom" 
                                       style="width:80px;"
                                       <c:if test="${!empty paramVO.searchDateFrom}">value="${paramVO.searchDateFrom}"</c:if>/>
                                <input type="text" id="searchTimeHourFrom" name="searchTimeHourFrom" maxlength="2"
                                       style="width:20px;"
                                       <c:if test="${!empty paramVO.searchTimeHourFrom}">value="${paramVO.searchTimeHourFrom}"</c:if>/> :
                                <input type="text" id="searchTimeMinuteFrom" name="searchTimeMinuteFrom" maxlength="2"
                                       style="width:20px;"
                                       <c:if test="${!empty paramVO.searchTimeMinuteFrom}">value="${paramVO.searchTimeMinuteFrom}"</c:if>/>
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo"
                                       style="width:80px;"
                                       <c:if test="${!empty paramVO.searchDateTo}">value="${paramVO.searchDateTo}"</c:if>/>
                                <input type="text" id="searchTimeHourTo" name="searchTimeHourTo" maxlength="2"
                                       style="width:20px;"
                                       <c:if test="${!empty paramVO.searchTimeHourTo}">value="${paramVO.searchTimeHourTo}"</c:if>/>:
                                <input type="text" id="searchTimeMinuteTo" name="searchTimeMinuteTo" maxlength="2"
                                       style="width:20px;"
                                       <c:if test="${!empty paramVO.searchTimeMinuteTo}">value="${paramVO.searchTimeMinuteTo}"</c:if>/>
                                &nbsp;
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
                <p class="amount">총 <em id="totCnt">0</em> 건 </p>
                <div id="acceptorArea">
                        <%-- grid --%>
                    <div id="acceptorGrid" class="tb_list1"></div>
                </div>
                <div id="initiatorArea">
                        <%-- grid --%>
                    <div id="initiatorGrid" class="tb_list1"></div>
                </div>


                <!-- // tb_list1 -->
                <div class="pagination">
                        <%-- paging 공통 --%>
                    <div id="pagingNavi"></div>
                    <div class="btn_type3">
                        <div class="left">
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnExcel">엑셀</a></span>
                            <span class="btn_gray1"><a href="javascript:void(0);" id="btnDown">다운로드</a></span>
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