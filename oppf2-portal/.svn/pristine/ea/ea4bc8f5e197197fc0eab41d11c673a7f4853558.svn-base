<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
       /**
        * @Name : simulatorManageList.jsp
        * @Description : simulator List 조회 목록
        * @Modification Information
        *
        * <pre>
        *  Modification Information
        *  수정일        수정자    수정내용
        *  ----------  ------  ----------
        *  2017.03.27  이선하    최초  생성
        * </pre>
        *
        * @author 이선하
        * @since 2017.03.27
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
        var g_initCheckboxArr = ["fixBuySide"];

        /*******************************************
         * 이벤트 함수
         *******************************************/

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/simul/simulatorManageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15005";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>



            /* 검색 키워드 */
            if(${!empty paramVO.searchKeyword && paramVO.searchKeyword != 'all'}){
                $("#searchKeyword").val('${paramVO.searchKeyword}');
            }else{
                $("#searchKeyword").val('');
            }

            /*Buy-side checkbox*/
            <c:choose>
                <c:when test="${!empty paramVO.fixBuySide && paramVO.fixBuySide[0] != '[all]' && paramVO.fixBuySide[0] != ''}">
                $("#fixBuySide_all").attr("checked", false);
                    <c:forEach items="${fixBuySideList}" var="fixBuySideList" varStatus="status">
                        <c:forEach items="${paramVO.fixBuySide}" var="fixBuySide" varStatus="status">
                            var fixBuySide = "${fixBuySide}";
                            fixBuySide = fixBuySide.replace(/\[/g,'').replace(/\]/g,'');

                            if('${fixBuySideList.senderCompId}' == fixBuySide){
                                $("#fixBuySide_${fixBuySideList.senderCompId}").attr("checked", true);
                            }else if(fixBuySide == 'all'){
                                $("input[name='fixBuySide']").attr("checked", false);
                                $("#fixBuySide_all").attr("checked", true);
                            }
                        </c:forEach>
                    </c:forEach>
                    $("#fixBuySide_all").attr("checked", false);
                </c:when>
                <c:otherwise>
                $("input[name='fixBuySide']").attr("checked", false);
                $("#fixBuySide_all").attr("checked", true);

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


            //조회일 searchDateType
            $("#searchDateType").change(function(){
                if($(this).val() == "all"){
                    $("#searchDateFrom").val("");
                    $("#searchDateTo").val("");

                    $("#searchDateFrom").datepicker("option", "disabled", true);
                    $("#searchDateTo").datepicker("option", "disabled", true);
                    $("input[name=searchDateRdo]").prop("disabled", true);
                    $("input[name=searchDateRdo]").eq(0).prop('checked', true);

                }else{
                    $("#searchDateFrom").datepicker("option", "disabled", false);
                    $("#searchDateTo").datepicker("option", "disabled", false);
                    $("input[name=searchDateRdo]").prop("disabled", false);
                }
            });

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
                //검색 text
                $("#searchCondition").val("");
                $("#searchKeyword").val("");
                //체크박스 초기화
                $("input[name='fixBuySide']").val('');
                //날짜 초기화
                $("#searchDateType").val('')
                $("#searchDateFrom").val('');
                $("#searchDateTo").val('');

                util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatorManageList.do'/>", "_top");
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
                fn_addSimulator();
            });


            //그리드 셋팅
            //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
            gfn_gridOption('jqxgrid',{
                columns: [
                    { text: '기업이름', datafield: 'senderCompName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '기업코드', datafield: 'companyId', width: '13%', cellsalign: 'left', align: 'center' },
                    { text: 'CompID', datafield: 'senderCompId', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: '주문수량대비체결량', datafield: 'excutionRate', width: '15%', cellsalign: 'left', align: 'center' },
                    { text: '거부율', datafield: 'rejectRate', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: '둥록자', datafield: 'createName', width: '18%', cellsalign: 'left', align: 'center' },
                    { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'left', align: 'center' },
                ]
            });

            $('#jqxgrid').on('rowclick', function (event) {
                var row = event.args.rowindex;
                var data = $('#jqxgrid').jqxGrid('getrowdata', row);

                //상세이동
                fn_moveDetail(data);
            }).on('bindingcomplete', function(event){
                $("#jqxgrid").jqxGrid('sortby', 'createDate', 'desc');
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
            var url = "<c:url value='/apt/simul/selectSimulatorManageList.ajax'/>";
            var param = $("#SimulatorManageVO").serialize();
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

        <%-- 엑셀다운 --%>
        function fn_searchExcel(excelType){
            $("#excelType").val(excelType);

            //로딩 호출
            gfn_setLoading(true, "엑셀 조회중 입니다.");
            $("#EXCEL_FRAME").ready(function(){
                gfn_setLoading(false);
            });

            util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatorManageListExcel.do'/>", "EXCEL_FRAME");

            //excelType rest
            $("#excelType").val("");

        }
        <%-- 상세이동 --%>
        function fn_moveDetail(data){
            $("#senderCompId").val(data.senderCompId);
            $("#senderCompName").val(data.senderCompName);
            $("#companyId").val(data.companyId);

            util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatorManageDtl.do'/>", "_top");
        }

        <%-- 기업추가 --%>
        function fn_addSimulator(){
            var url = "<c:url value='/apt/simul/simulatorCompPopup.do'/>";
            var objOption = new Object();
            //fn_addCompanyCallBack
            objOption.type = 'modal';
            objOption.width = '602';
            objOption.height = '440';

            var objParam = new Object();
            // objParam.callBakFunc = "fn_addCompanyCallBack";
            objParam.searchCompanyRegYn = "Y";  //등록된 기업만 조회

            util_modalPage(url, objOption, objParam);
        }

        /*추가할때 티는 funtion*/
        function fn_addCompanyCallBack(data){
            util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatormanageDtl.do'/>", "_top");
        }


    </script>

</head>

<body>
<form:form commandName="SimulatorManageVO" name="SimulatorManageVO" method="post">
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
    <input type="hidden" name="pageRowsize" id="pageRowsize" value="50" /><!-- //목록사이즈 -->

    <input type="hidden" name="senderCompId" id="senderCompId"/><!-- //rs_comp_tb data -->
    <input type="hidden" name="senderCompName" id="senderCompName"/><!-- //rs_comp_tb data-->
    <input type="hidden" name="companyId" id="companyId"/><!-- //rs_comp_tb data -->

    <input type="hidden" name="fixBuySideAllYn" id="fixBuySideAllYn" value="N"/>

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
                    <h2>Simulator 케이스 등록</h2>
                </div>
                <!-- // locatioin -->
                <div class="tb_write1">
                    <table>
                        <caption>Simulator 검색정보 입력</caption>
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
                                    <option value="senderCompName">기업명</option>
                                    <option value="senderCompId">CompID</option>
                                    <option value="companyId">기업코드</option>
                                </select>
                                <input type="text" style="width:350px" id="searchKeyword" name="searchKeyword">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Buy-side</th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="fixBuySide" id="fixBuySide_all" value="all" checked="checked">
                                            <label for="fixBuySide_all">전체</label>
                                        </li>
                                        <c:forEach items="${fixBuySideList}" var="fixBuySideList" varStatus="status">
                                            <li><input type="checkbox" name="fixBuySide" id="fixBuySide_${fixBuySideList.senderCompId}" value="${fixBuySideList.senderCompId}">
                                                <label for="fixBuySide_${fixBuySideList.senderCompId}">${fixBuySideList.senderCompName}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <a href="javascript:void(0);" class="btn_more">더보기</a>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">조회기간</label></th>
                            <td class="txt_l">
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