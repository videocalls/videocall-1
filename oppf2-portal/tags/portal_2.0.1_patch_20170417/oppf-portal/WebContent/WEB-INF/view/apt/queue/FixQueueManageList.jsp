<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
        /**
        * @Name : FixQueueManageList.jsp
        * @Description : Queue관리 목록
        * @Modification Information
        *
        * <pre>
        *  Modification Information
        *  수정일        수정자    수정내용
        *  ----------  ------  ----------
        *  2017.03.24  이선하    최초  생성
        * </pre>
        *
        * @author 이선하
        * @since 2017.03.24
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
        var g_initCheckboxArr = [];
        var serverInfo = new Array();

        var btnStyle = "style='min-width: 42px; height: 30px; padding: 0 6px; " +
                "background: #fff; color: #413a3a !important; " +
                "font-size: 13px; line-height: 20px; white-space: " +
                "nowrap;  margin: 0 1px; border: 1px solid #666666; border-radius: 3px;'";
        var selectorStyle = "style='min-width: 200px; height: 22px; padding: 0 6px; " +
                "background: #fff; color: #413a3a !important; " +
                "font-size: 13px; line-height: 20px; white-space: " +
                "nowrap;  margin: 0 1px; border: 1px solid #666666; border-radius: 3px;'";

        //Select Box Setting
        var serverIdList = function(row, columnfield, value, defaulthtml, columnproperties) {
            var data = $("#jqxgrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(data);
            var htmlSelectBoxBegin = "<div style='line-height:26px; text-align: center;'>" +
                    "<select id='hub_" + data.fixQueueId + "'" + selectorStyle + ">";
            var htmlSelectBoxEnd = "</select>";

            var htmlSelectBoxOption = "";

            var updateBtn =
                    "<span class='btn_type'><a href='javascript:fn_beforServerStop("+ stringJson +")'" + btnStyle + ">수정</a></span></div>"

            if(serverInfo != null && serverInfo !=''){
                for(var i in serverInfo){
                    if(value == serverInfo[i].initServerId){
                        htmlSelectBoxOption +=
                                "<option name='initServerId' value = '" + serverInfo[i].initServerId + "' selected>" +
                                serverInfo[i].initServerIp + "</option>";
                    }else{
                        htmlSelectBoxOption +=
                                "<option name='initServerId' value = '" + serverInfo[i].initServerId + "'>" +
                                serverInfo[i].initServerIp + "</option>";
                    }
                }
                return htmlSelectBoxBegin + htmlSelectBoxOption + htmlSelectBoxEnd + updateBtn;
            }

        }

        var viewCompanyList = function(row, columnfield, value, defaulthtml, columnproperties){
            var data = $("#jqxgrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(data);
            var selectBtn =
                    "<div style='line-height:26px; text-align: center;'>" +
                    "<span class='btn_type'>" +
                    "<a href='javascript:fn_senderCompDtlPopup("+ stringJson +")'" + btnStyle + ">조회</a>" +
                    "</span></div>";

            return selectBtn;
        }

        /*******************************************
         * 이벤트 함수
         *******************************************/

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/queue/fixQueueManageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15004";

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
            $("input[name=searchDateRdo]").change(function(){
                var id = $(this).attr("id");

                var searchDateFrom = "";
                var searchDateTo = util_getDate();

                if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
                    searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
                }

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
                util_moveRequest("FixQueueManageVO", "<c:url value='/apt/queue/fixQueueManageList.do'/>", "_top");
            });

            //엑셀
            $("#btnExcel").click(function(){
                fn_searchExcel('excel');
            });

            //다운로드
            $("#btnDown").click(function(){
                fn_searchExcel('down');
            });

            //그리드 셋팅
            //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
            gfn_gridOption('jqxgrid',{
                columns: [
                    { text: 'FIX QUEUE ID', datafield: 'fixQueueId', width: '18%', cellsalign: 'left', align: 'center' },
                    { text: 'Initiator Server', dataField: 'initServerId',  cellsrenderer: serverIdList,  width: '35%', align: 'center' },
                    {
                        text: '기업정보',
                        datafield: 'senderCompDtl',
                        width: '13%',
                        cellsalign: 'left',
                        align: 'center',
                        cellsrenderer: viewCompanyList
                    },
                    { text: '수정일', datafield: 'lastTimestamp', width: '22%', cellsalign: 'left', align: 'center' },
                ]
            });

            $('#jqxgrid').on('bindingcomplete', function(event){
                $("#jqxgrid").jqxGrid('sortby', 'fixQueueId', 'asc');
                //로딩 호출
                gfn_setLoading(false);
            });

            <%-- 체크박스 선택 onclick이벤트 설정--%>
            for(var i=0; i<g_initCheckboxArr.length; i++){
                gfn_initCheckbox(g_initCheckboxArr[i]);
            }

            //조회
            fn_search($("#pageIndex").val());
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
            var url = "<c:url value='/apt/queue/selectFixQueueManageList.ajax'/>";
            var param = $("#FixQueueManageVO").serialize();
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
            serverInfo = data.serverInfo;

            $("#totCnt").text(util_setCommas(resultListTotalCount));

            $("#pagingNavi >").remove();

            //grid common
            gfn_gridData(resultList);

            gfn_setLoading(false);

            //page common
            pageNavigator(
                    $("#pageIndex").val(),
                    resultListTotalCount,
                    "fn_search",
                    "50"
            );
        }

        <%-- stop 후 start --%>
        function fn_beforServerStop(data){
            if(confirm("수정하시겠습니까?")){
                //로딩 호출
                gfn_setLoading(true, "수정중입니다.");

                var url = "<c:url value='/apt/queue/updateFixQueueManageList.ajax'/>";
                var param = new Object();
                param.fixQueueId = data.fixQueueId;
                param.initServerId = data.initServerId;
                param.afterInitServerIp = $("#hub_" + data.fixQueueId + " option:selected").val();
                param.activeQueueYn = 'N';

                var callBackFunc = "fn_updateCallBack";
                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            }else{
                return;
            }
        }

        function fn_updateCallBack(data){
            gfn_setLoading(false);
            if(data.result != "200"){
                alert("정상적으로 수정되었습니다.");
            }else{
                //console.log("update fail");
            }
            return;
        }

        <%-- validate --%>
        function fn_validate(){
            var searchFrom = util_replaceAll($("#searchDateFrom").val(), "-", "");
            var searchTo = util_replaceAll($("#searchDateTo").val(), "-", "");

            if(searchFrom != "") {
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

            }
            return true;
        }

        <%-- 엑셀다운 --%>
        function fn_searchExcel(excelType){
            $("#excelType").val(excelType);

            //로딩 호출
            gfn_setLoading(true, "엑셀 조회중 입니다.");
            $("#EXCEL_FRAME").ready(function(){
                gfn_setLoading(false);
            });

            util_moveRequest("FixQueueManageVO", "<c:url value='/apt/queue/selectFixQueueManageListExcel.do'/>", "EXCEL_FRAME");

            //excelType rest
            $("#excelType").val("");

        }
        <%-- 상세이동 --%>
        function fn_senderCompDtlPopup(data){
            var paramVO = "fixQueueId=" + data.fixQueueId;
            var url = "/apt/queue/senderCompDtlQueuePopup.do?" + paramVO;
            window.open("<c:url value='/apt/queue/senderCompDtlQueuePopup.do?'/>" + paramVO, "_blank", "width=500, height=500, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
        }
    </script>

</head>

<body>
<form:form commandName="FixQueueManageVO" name="FixQueueManageVO" method="post">
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
    <input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

    <input type="hidden" name="excelType" id="excelType" value="" />

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
                    <h2>FIX Queue 관리</h2>
                </div>
                <!-- // locatioin -->
                <div class="tb_write1">
                    <table>
                        <caption>Queue 검색정보 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">구분</label></th>
                            <td class="txt_l">
                                <select id="searchCondition" name="searchCondition">
                                    <option value="all">전체</option>
                                    <option value="fixQueueId">Fix Queue ID</option>
                                    <option value="initiatorServer">Initiator Server</option>
                                </select>
                                <input type="text" style="width:350px" id="searchKeyword" name="searchKeyword">
                            </td>
                        </tr>

                        <tr>
                            <th scope="row"><label for="chk2">조회기간</label></th>
                            <td class="txt_l">
                                <input type="text" id="searchDateFrom" name="searchDateFrom" readonly="readonly" style="width:80px;" />
                                <span class="dateDot">~</span>
                                <input type="text" id="searchDateTo" name="searchDateTo" readonly="readonly" style="width:80px;" />
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