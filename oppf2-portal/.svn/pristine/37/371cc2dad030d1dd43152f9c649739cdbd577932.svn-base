<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
  /**
   * @Name : senderCompDtlPopup.jsp
   * @Description : Queue관리 기업별 상세 팝업
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
        <script language="javascript" type="text/javascript">
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

                gfn_gridOption('jqxgrid',{
                    height:'200px',
                    columns: [
                        { text: '기업명', datafield: 'senderCompName', width: '40%', cellsalign: 'left', align: 'center' },
                        { text: '기업코드', datafield: 'companyId', width: '30%', cellsalign: 'left', align: 'center' },
                        { text: 'CompId', datafield: 'senderCompId', width: '30%', cellsalign: 'left', align: 'center' },
                    ]
                });

                $('#jqxgrid').on('bindingcomplete', function(event){
                    $("#jqxgrid").jqxGrid('sortby', 'senderCompName', 'desc');
                    //로딩 호출
                    gfn_setLoading(false);
                });

                $("#btnClose").click(function(){
                    popupClose();
                });

                fn_searchList($("#pageIndex").val());

            });

            function fn_searchList(){
                $("#pageIndex").val(1);

                //로딩 호출
                gfn_setLoading(true);

                //page setting
                var url = "<c:url value='/apt/queue/selectQueueCompanyList.ajax'/>";
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
                        "10"
                );
            }

            function popupClose(){
                self.opener = self;
                self.close();
            }
        </script>
</head>

<body>
<form:form commandName="SimulatorManageVO" name="SimulatorManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="10" /><!-- //목록사이즈 -->
    <input type="hidden" name="fixQueueId" id="fixQueueId" value="${paramVO.fixQueueId}"/>
<section class="pop_wrap">
    <div class="tb_write1">
        <table>
            <caption>Initiator ID, Initiator IP 정보</caption>
            <colgroup>
                <col style="width:36%;">
                <col style="width:64%;">
            </colgroup>
            <tbody>
            <tr>
                <th scope="row">QueueId</th>
                <td>${paramVO.fixQueueId}</td>
            </tr>
            </tbody>
        </table>
    </div>

        <%-- rowcount 공통 --%>
    <p class="amount">총 <em id="totCnt">0</em> 건</p>

        <%-- grid --%>
    <div id="jqxgrid" class="tb_list1"></div>

    <!-- // tb_list1 -->
    <div class="pagination">
            <%-- paging 공통 --%>
        <div id="pagingNavi"></div>

        <div class="btn_type3">
            <span class="btn_gray1"><a href="javascript:void(0);" id="btnClose">확인</a></span>
        </div>
    </div>
    <!-- // paging_area -->

</section>
<!-- // content -->
</form:form>
<!-- // container -->
</body>
</html>