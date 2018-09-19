<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
    /**
     * @Name : dataSetManagementDtl.jsp
     * @Description : 데이타셋 상세 조회
     * @Modification Information
     *
     * <pre>
     *  Modification Information
     *  수정일        수정자     수정내용
     *  ----------  ------  ----------
     *  2017.02.28  이선하     최초  생성
     * </pre>
     *
     * @author 이선하
     * @since 2017.02.28
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
    <script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.edit.js'/>"></script>
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

        /*******************************************
         * 전역 변수
         *******************************************/

        /*Field Division*/
        var balSummaryField;
        var balEquityField;
        var balFundField;
        var balEtcField;
        var portEquityField;
        var portFundField;
        var portEtcField;
        var transactionField;
        var groupField;
        /*Row Division*/
        var balSummaryRow;
        var balEquityRow;
        var balFundRow;
        var balEtcRow;
        var portEquityRow;
        var portFundRow;
        var portEtcRow;
        var transactionRow;
        var groupRow;


        /*******************************************
         * Balance
         *******************************************/

        var summary;    //summary 전역변수( portfolio, AMT사용 )
        var afterBtnStyle = "style='min-width: 42px; height: 30px; padding: 0 6px; " +
                "background: #fff; color: #413a3a !important; " +
                "font-size: 13px; line-height: 20px; white-space: " +
                "nowrap;  margin: 0 1px; border: 1px solid #666666; border-radius: 3px;'";
        var beforeBtnStyle = "style='min-width: 42px;height: 30px;padding: 0 6px;background: #ccc;color: #868686 !important; cursor: default; pointer-events: none;" +
                "font-size: 13px;line-height: 20px;vertical-align: middle;text-align: center;white-space: nowrap;" +
                " ;margin: 0 1px;border: 1px solid #868686;border-radius: 3px;'";


        //summart btn설정
        var summaryCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#bal_summaryGrid").jqxGrid('getrowdata', row);

            var returnBtn = "";
            if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifySummary(" + row + ");' " +
                        afterBtnStyle + "id='modify_bal_summaryGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifySummary(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_bal_summaryGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };


        //equity btn설정
        var equityCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#bal_equityGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEquity(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' >삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEquity(" + row + ");' " +
                        afterBtnStyle + "id='modify_bal_equityGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEquity(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEquity(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_bal_equityGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        //fund btn설정
        var fundCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#bal_fundGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteFund(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyFund(" + row + ");' " +
                        afterBtnStyle + "id='modify_bal_fundGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteFund(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyFund(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_bal_fundGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        //etc btn설정
        var etcCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#bal_etcGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEtc(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEtc(" + row + ");' " +
                        afterBtnStyle + "id='modify_bal_etcGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEtc(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEtc(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_bal_etcGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };


        /*******************************************
         * Portfolio
         *******************************************/

        var portEquityCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#port_equityGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEquity(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEquityPort(" + row + ");' " +
                        afterBtnStyle + "id='modify_port_equityGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEquity(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEquityPort(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_port_equityGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        //fund btn설정
        var portFundCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#port_fundGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteFund(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyFundPort(" + row + ");' " +
                        afterBtnStyle + "id='modify_port_fundGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteFund(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyFundPort(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_port_fundGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        //etc btn설정
        var portEtcCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#port_etcGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEtc(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEtcPort(" + row + ");' " +
                        afterBtnStyle + "id='modify_port_etcGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteEtc(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyEtcPort(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_port_etcGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };


        /*******************************************
         * Transaction
         *******************************************/

        //transaction btn설정
        var transactionCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#tran_transactionGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteTransaction(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyTransaction(" + row + ");' " +
                        afterBtnStyle + "id='modify_tran_transactionGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteTransaction(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyTransaction(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_tran_transactionGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        /*******************************************
         * Group
         *******************************************/
        //group btn설정
        var groupCellsrenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
            var gridData = $("#grp_groupGrid").jqxGrid('getrowdata', row);
            var stringJson = JSON.stringify(gridData);

            var returnBtn = "";
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:void(0)'" + beforeBtnStyle + ">삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'>" +
                        "<a href='javascript:void(0)' " + beforeBtnStyle + "'>수정</a></span>" +
                        "</div>";
            }
            else if(gridData.btnDivision == 'modify'){
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteGroup(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyGroup(" + row + ");' " +
                        afterBtnStyle + "id='modify_grp_groupGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }else{
                returnBtn = "<div style='line-height:26px; text-align: center;'>" +
                        "<span class='btn_type'><a href='javascript:fn_deleteGroup(" + stringJson + ", " + row + ");'" +
                        afterBtnStyle + "id='equityDeleteBtn' name='delBtn'>삭제</a></span>&nbsp;&nbsp;" +
                        "<span style='align-content: center;' class='btn_type'><a href='javascript:fn_modifyGroup(" + row + ");' " +
                        beforeBtnStyle + "id='before_modify_grp_groupGrid_" + row + "'>수정</a></span>" +
                        "</div>";
            }

            return returnBtn;
        };

        /*******************************************
         * 이벤트 함수
         *******************************************/


        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/data/dataSetManagementList.do'/>";
            var param = new Object();
            param.paramMenuId = "14001";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>


            /**
             * test tab function
             * */
            $(".tabEvent").click(function(){
                var tabName = $(this).attr('id');
                $("#apiAccountDivision").val(tabName);
            })


            /*******************************************
             * tab menuBar
             *******************************************/

            //목록
            $("#btnList").click(function(){
                fn_list();
            });

            //데이터셋 비활성화
            $("#btnDelete").click(function(){
                fn_deleteDataset();
            });

            //복사
            $("#btnCopy").click(function(){
                fn_copyDataset();
            });

            //포트폴리오 현금잔고ATM수정
            $("#btnModifyPortfolio").click(function(){
                fn_modifyPortfolioAmt();
            });



            /*******************************************
             * grid
             *
             * balance
             *******************************************/

            gfn_gridOption('bal_summaryGrid',{
                editable: true,
                height:'80px',
                columns: [
                    {text:'관리', datafield: 'modify', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: summaryCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '현금잔고', datafield: 'cashBalance', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '대용금', datafield: 'substitute', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '미수/미납금', datafield: 'receivable', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '대용증거금', datafield: 'subsMargin', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '대출/신용금', datafield: 'loanCredit', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '유가증권매수금액', datafield: 'valAtTrade', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '유가증권평가금액', datafield: 'valueAtCur', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '유가증권평가손익', datafield: 'proLoss', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '총평가금액', datafield: 'totalAccVal', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '출금가능액', datafield: 'cashAvWithdraw', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#bal_summaryGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                balSummaryField = args.datafield;
                balSummaryRow = args.rowindex;
            });
            $("#bal_summaryGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'bal_equityGrid';
                }

            });


            gfn_gridOption('bal_equityGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: equityCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '상품구분자', datafield: 'assetType', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: 'ISINCODE', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '잔고수량', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '매수금액', datafield: 'valAtTrade', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '평가금액', datafield: 'valAtCur', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '평가손익', datafield: 'proLoss', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'n'},
                    { text: '수익률(소수점 2째자리)', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center' ,cellsformat: 'f2'},

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#bal_equityGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                balEquityField = args.datafield;
                balEquityRow = args.rowindex;

            });
            $("#bal_equityGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'bal_equityGrid';
                }

            });

            gfn_gridOption('bal_fundGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: fundCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '펀드표준코드', datafield: 'fundCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '펀드이름', datafield: 'fundName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '매수금액', datafield: 'valAtTrade', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n'},
                    { text: '평가금액', datafield: 'valAtCur', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n'},
                    { text: '평가손익', datafield: 'proLoss', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n'},
                    { text: '최초매수일', datafield: 'firstDateBuy', width: '12%', cellsalign: 'left', align: 'center'},
                    { text: '최종매수일', datafield: 'lastDateBuy', width: '12%', cellsalign: 'left', align: 'center'},
                    { text: '만기일', datafield: 'maturity', width: '12%', cellsalign: 'left', align: 'center'},
                    { text: '수익률', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: '수량 또는 비중', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#bal_fundGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                balFundField = args.datafield;
                balFundRow = args.rowindex;
                /*여기서 수정버튼 활성화 해야함*/
            });
            $("#bal_fundGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'bal_fundGrid';
                }
            });



            gfn_gridOption('bal_etcGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: etcCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '상품구분자', datafield: 'assetType', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '상품명', datafield: 'assetName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '수량 또는 비중', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: '매수금액', datafield: 'valAtTrade', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '평가금액', datafield: 'valueAtCur', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: 'ISINCODE', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#bal_etcGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                balEtcField = args.datafield;
                balEtcRow = args.rowindex;
                /*여기서 수정버튼 활성화 해야함*/
            });
            $("#bal_etcGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'bal_etcGrid';
                }
            });





            /*******************************************
             * grid
             *
             * portfolio
             *******************************************/

            gfn_gridOption('port_equityGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: portEquityCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '상품구분자', datafield: 'assetType', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: 'ISINCODE', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '비중', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: '수익률', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#port_equityGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                portEquityField = args.datafield;
                portEquityRow = args.rowindex;
                /*여기서 수정버튼 활성화 해야함*/
            });
            $("#port_equityGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'port_equityGrid';
                }
            });


            gfn_gridOption('port_fundGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: portFundCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '펀드표준코드', datafield: 'fundCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '펀드이름', datafield: 'fundName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '비중', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: '만기일', datafield: 'maturity', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '수익률', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#port_fundGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                portFundField = args.datafield;
                portFundRow = args.rowindex;

            });
            $("#port_fundGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'port_fundGrid';
                }
            });


            gfn_gridOption('port_etcGrid',{
                editable: true,
                height:'185px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: portEtcCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '상품구분자', datafield: 'assetType', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '상품명', datafield: 'assetName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '비중', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: '수익률', datafield: 'earningRate', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'f2' },
                    { text: 'ISINCODE', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#port_etcGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                portEtcField = args.datafield;
                portEtcRow = args.rowindex;
            });
            $("#port_etcGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue){
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'port_etcGrid';
                }
            });



            /*******************************************
             * grid
             *
             * Transaction
             *******************************************/

            gfn_gridOption('tran_transactionGrid',{
                editable: true,
                height:'313px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: transactionCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '종목코드', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '거래일자', datafield: 'transDate', width: '12%', cellsalign: 'left', align: 'center'},
                    { text: '잔고수량', datafield: 'changeAmt', width: '12%', cellsalign: 'right', align: 'center' },
                    { text: '거래구분', datafield: 'transType', width: '12%', cellsalign: 'left', align: 'center', cellsformat: 'n' },
                    { text: '금액증감', datafield: 'changeQuantity', width: '12%', cellsalign: 'right', align: 'center' },
                    { text: '수량증감', datafield: 'quantity', width: '12%', cellsalign: 'right', align: 'center', cellsformat: 'n' },

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#tran_transactionGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                transactionField = args.datafield;
                transactionRow = args.rowindex;

            });
            $("#tran_transactionGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue) {
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'tran_transactionGrid';
                }
            });



            /*******************************************
             * grid
             *
             * Group
             *******************************************/

            gfn_gridOption('grp_groupGrid',{
                editable: true,
                height:'313px',
                columns: [
                    {text:'관리', datafield: 'delete', columngroup:'managementTest', width: '12%', cellsalign: 'center', align: 'center'
                        , cellsrenderer: groupCellsrenderer, pinned: true,  editable: false, textAlign: 'center'
                    },
                    { text: '관심종목 그룹이름', datafield: 'groupName', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '종목코드', datafield: 'isinCode', width: '12%', cellsalign: 'left', align: 'center' },
                    { text: '최종 수정일', datafield: 'modifyDate', width: '12%', cellsalign: 'left', align: 'center'},

                    { text: 'modifyBtnDivision', datafield: 'btnDivision', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'rowIndex', datafield: 'rowindex', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                    { text: 'tableName', datafield: 'tableName', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
                ]
            });
            $("#grp_groupGrid").on('cellbeginedit', function (event) {
                var args = event.args;
                groupField = args.datafield;
                groupRow = args.rowindex;
            });
            $("#grp_groupGrid").on('cellendedit', function (event) {
                var args = event.args;
                var columnDataField = args.datafield;
                var rowIndex = args.rowindex;
                var cellValue = args.value;
                var oldValue = args.oldvalue;

                if(oldValue != cellValue) {
                    args.row.btnDivision = 'modify';
                    args.row.rowindex = rowIndex;
                    args.row.tableName = 'grp_groupGrid';
                }
            });

            <%-- DataSet List 추출 --%>
            fn_dataResultList();


            /*******************************************
             * grid dataset lock yn
             *******************************************/
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                $("#bal_summaryGrid").jqxGrid('editable', false);
                $("#bal_equityGrid").jqxGrid('editable', false);
                $("#bal_fundGrid").jqxGrid('editable', false);
                $("#bal_etcGrid").jqxGrid('editable', false);
                $("#port_equityGrid").jqxGrid('editable', false);
                $("#port_fundGrid").jqxGrid('editable', false);
                $("#port_etcGrid").jqxGrid('editable', false);
                $("#tran_transactionGrid").jqxGrid('editable', false);
                $("#grp_groupGrid").jqxGrid('editable', false);

            }

        });




        /*******************************************
         * 기능 함수
         *******************************************/

        <%-- portfolio_amt 수정 --%>
        function fn_modifyPortfolioAmt(){
            //로그인 처리
            var t_amt = $("#portfolio_amt").val();
            summary.amt = t_amt;

            var url = "<c:url value='/apt/data/updateSummary.ajax'/>";
            var param = summary;
            var callBakFunc = "successModifyAmtData";
            util_ajaxPage(url, param, callBakFunc);

        }


        <%-- summary 수정 --%>
        function fn_modifySummary(row){
            if(row == balSummaryRow){
                $("#bal_summaryGrid").jqxGrid("endcelledit", row, balSummaryField, false);        //현재입력각 등록
            }
            var data = $("#bal_summaryGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            var url = "<c:url value='/apt/data/updateSummary.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);

        }

        <%-- equity 수정 --%>
        function fn_modifyEquity(row){
            if(row == balEquityRow){
                $("#bal_equityGrid").jqxGrid("endcelledit", row, balEquityField, false);        //현재입력각 등록
            }
            var data = $("#bal_equityGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            var url = "<c:url value='/apt/data/updateEquity.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- port equity 수정 --%>
        function fn_modifyEquityPort(row){
            if(row == portEquityRow){
                $("#port_equityGrid").jqxGrid("endcelledit", row, portEquityField, false);        //현재입력각 등록
            }
            var data = $("#port_equityGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            var url = "<c:url value='/apt/data/updateEquity.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- equity 삭제 --%>
        function fn_deleteEquity(data, row){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            gfn_setLoading(true);
            if(confirm("삭제하시겠습니까?")){
                var url = "<c:url value='/apt/data/deleteEquity.ajax'/>";
                var param = data;
                var callBakFunc = "successDeleteData";
                util_ajaxPage(url, param, callBakFunc);
            }else{
                gfn_setLoading(false);
                return;
            }
        }

        <%-- fund 수정 --%>
        function fn_modifyFund(row){
            if(row == balFundRow){
                $("#bal_fundGrid").jqxGrid("endcelledit", row, balFundField, false);        //현재입력각 등록
            }
            var data = $("#bal_fundGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateFund.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- port fund 수정 --%>
        function fn_modifyFundPort(row){
            if(row == portFundRow){
                $("#port_fundGrid").jqxGrid("endcelledit", row, portFundField, false);        //현재입력각 등록
            }
            var data = $("#port_fundGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateFund.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }


        <%-- fund 삭제 --%>
        function fn_deleteFund(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            if(confirm("삭제하시겠습니까?")){
                var url = "<c:url value='/apt/data/deleteFund.ajax'/>";
                var param = data;
                var callBakFunc = "successDeleteData";
                util_ajaxPage(url, param, callBakFunc);
            }else{
                gfn_setLoading(false);
                return;
            }
        }

        <%-- etc 수정 --%>
        function fn_modifyEtc(row){
            if(row == balEtcRow){
                $("#bal_etcGrid").jqxGrid("endcelledit", row, balEtcField, false);        //현재입력각 등록
            }
            var data = $("#bal_etcGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateEtc.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- etc 수정 --%>
        function fn_modifyEtcPort(row){
            if(row == portEtcRow){
                $("#port_etcGrid").jqxGrid("endcelledit", row, portEtcField, false);        //현재입력각 등록
            }
            var data = $("#port_etcGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateEtc.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }
//shl
        <%-- etc 삭제 --%>
        function fn_deleteEtc(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            if(confirm("삭제하시겠습니까?")){
                var url = "<c:url value='/apt/data/deleteEtc.ajax'/>";
                var param = data;
                var callBakFunc = "successDeleteData";
                util_ajaxPage(url, param, callBakFunc);
            }else{
                gfn_setLoading(false);
                return;
            }
        }

        <%-- transaction 수정 --%>
        function fn_modifyTransaction(row){
            if(row == transactionRow){
                $("#tran_transactionGrid").jqxGrid("endcelledit", row, transactionField, false);        //현재입력각 등록
            }
            var data = $("#tran_transactionGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateTransaction.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- transaction 삭제 --%>
        function fn_deleteTransaction(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            if(confirm("삭제하시겠습니까?")){
                var url = "<c:url value='/apt/data/deleteTransaction.ajax'/>";
                var param = data;
                var callBakFunc = "successDeleteData";
                util_ajaxPage(url, param, callBakFunc);
            }else{
                gfn_setLoading(false);
                return;
            }
        }

        <%-- group 수정 --%>
        function fn_modifyGroup(row){
            if(row == groupRow){
                $("#grp_groupGrid").jqxGrid("endcelledit", row, groupField, false);        //현재입력각 등록
            }
            var data = $("#grp_groupGrid").jqxGrid("getrowdata", row);

            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            var url = "<c:url value='/apt/data/updateGroup.ajax'/>";
            var param = data;
            var callBakFunc = "successModifyData";
            util_ajaxPage(url, param, callBakFunc);
        }

        <%-- group 삭제 --%>
        function fn_deleteGroup(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            if(confirm("삭제하시겠습니까?")){
                var url = "<c:url value='/apt/data/deleteGroup.ajax'/>";
                var param = data;
                var callBakFunc = "successDeleteData";
                util_ajaxPage(url, param, callBakFunc);
            }else{
                gfn_setLoading(false);
                return;
            }
        }

        <%--AMT 수정 결과--%>
        function successModifyAmtData(data){
            alert("<spring:message code='success.alert.modify' />");
            return ;
        }
        <%-- 나머지 데이터(grid) 수정 결과 --%>
        function successModifyData(data){
            alert("<spring:message code='success.alert.modify' />");

            var row;
            var fieldName = 'btnDivision';
            var tableName = '';

            if(typeof data.DataSetSummaryVO != "undefined"){
                row = data.DataSetEquityVO.rowindex;
                tableName = data.DataSetEquityVO.tableName;
            }
            if(typeof data.DataSetEquityVO != "undefined"){
                row = data.DataSetEquityVO.rowindex;
                tableName = data.DataSetEquityVO.tableName;
            }else if(typeof data.DataSetFundVO != "undefined"){
                row = data.DataSetFundVO.rowindex;
                tableName = data.DataSetFundVO.tableName
            }else if(typeof data.DataSetEtcVO != "undefined"){
                row = data.DataSetEtcVO.rowindex;
                tableName = data.DataSetEtcVO.tableName
            }else if(typeof data.DataSetTransactionVO != "undefined"){
                row = data.DataSetTransactionVO.rowindex;
                tableName = data.DataSetTransactionVO.tableName;
            }else if(typeof data.DataSetGroupVO != "undefined"){
                row = data.DataSetGroupVO.rowindex;
                tableName = data.DataSetGroupVO.tableName;
            }else{
                return false;
            }
            $("#" + tableName).jqxGrid("setcellvalue", row, fieldName, "");
            $("#modify_" + tableName + "_" + row).css("background", "#ccc");
            $("#modify_" + tableName + "_" + row).css("color", "#868686 !important");
            $("#modify_" + tableName + "_" + row).css("cursor", "default");
            $("#modify_" + tableName + "_" + row).css("pointer-events", "none");
            $("#modify_" + tableName + "_" + row).css("text-align", "center");
            $("#modify_" + tableName + "_" + row).css("vertical-align", "middle");

            return;
        }

        <%-- 데이터 삭제 결과 --%>
        function successDeleteData(data){
            fn_moveRealDetail()
            return;
        }

        /***************************************************************************************************** */
        <%-- DataSet List 추출 --%>
        function fn_dataResultList(){
            //page setting
            var url = "<c:url value='/apt/data/dataSetResultList.ajax'/>";
            var param = $("#DataSetManageVO").serialize();
            var callBackFunc = "fn_detailCallBack";

            //로딩 호출
            gfn_setLoading(true);

            <%-- 공통 ajax 호출 --%>
            util_ajaxPage(url, param, callBackFunc);
        }

        function fn_detailCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }
            gfn_setLoading(false);


            summary = data.selectSummary;

            var BALANCE_EQYITY = data.balSelectEquityList;
            var BALANCE_FUND = data.balSelectFundList;
            var BALANCE_ETC = data.balSelectEtcList;

            var PORTFOLIO_EQYITY = data.portSelectEquityList;
            var PORTFOLIO_FUND = data.portSelectFundList;
            var PORTFOLIO_ETC = data.portSelectEtcList;

            var TRANSACTION = data.selectTransactionList;
            var GROUPLIST = data.selectInterestList;

            $("#portfolio_amt").val(summary.amt);

            var apiAccountDivision = data.apiAccountDivision;

            //tab구분
            $("#" + apiAccountDivision).click();

            //grid common
            gfn_gridData(summary,'bal_summaryGrid');

            //balance
            gfn_gridData(BALANCE_EQYITY,'bal_equityGrid');
            gfn_gridData(BALANCE_FUND,'bal_fundGrid');
            gfn_gridData(BALANCE_ETC,'bal_etcGrid');

            //portfolio
            gfn_gridData(PORTFOLIO_EQYITY,'port_equityGrid');
            gfn_gridData(PORTFOLIO_FUND,'port_fundGrid');
            gfn_gridData(PORTFOLIO_ETC,'port_etcGrid');

            //transaction
            gfn_gridData(TRANSACTION,'tran_transactionGrid');

            //group
            gfn_gridData(GROUPLIST,'grp_groupGrid');
        }

        <%-- 목록 --%>
        function fn_list(){
            //로딩 호출
            gfn_setLoading(false);
            util_moveRequest("DataSetManageVO", "<c:url value='/apt/data/dataSetManagementList.do'/>", "_self");
        }

        <%-- 데이터셋 비활성화 --%>
        function fn_deleteDataset(){
            if(${dataSetManagementDtl.customerDatasetLockYn == 'Y'}){
                alert("데이터셋 잠금 ‘사용’으로 설정되어 변경이 불가능합니다.");
            }else{
                if(confirm("데이터셋을 비활성화하시면 등록된 모든 데이터가 삭제됩니다.\n비활성화 처리하시겠습니까?")) {
                    gfn_setLoading(true, "비활성화중 입니다.");

                    var moveUrl = "<c:url value='/apt/data/dataSetDisable.ajax'/>";
                    var param = $("#DataSetManageVO").serialize();
                    var callBackFunc = "fn_list";

                    util_ajaxPage(moveUrl, param, callBackFunc);
                }else{
                    return;
                }
            }
        }

        <%-- 복사 --%>
        function fn_copyDataset(){
            /**
             * popup작업
             * */
            var url = "<c:url value='/apt/data/dataSetCopyPopup.do'/>";
            var objOption = new Object();
            //fn_addCompanyCallBack
            objOption.type = 'modal';
            objOption.width = '602';
            objOption.height = '350';

            var objParam = new Object();
            objParam.customerRegNumber = $("#customerRegNumber").val();
            objParam.companyCodeId = $("#companyCodeId").val();
            objParam.customerRealaccountNumber = $("#customerRealaccountNumber").val();
            objParam.customerVtaccountNumber = $("#customerVtaccountNumber").val();

            util_modalPage(url, objOption, objParam);
        }

        <%-- 상세 --%>
        function fn_moveRealDetail(){
            util_moveRequest("DataSetManageVO", "<c:url value='/apt/data/dataSetManagementDtl.do'/>", "_top");
        }

    </script>

</head>

<body>
<form:form commandName="DataSetManageVO" name="DataSetManageVO" method="post">
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>"/>

    <input type="hidden" name="customerRegNumber" id="customerRegNumber" value="${dataSetManagementDtl.customerRegNumber}" />
    <input type="hidden" name="companyCodeId" id="companyCodeId" value="${dataSetManagementDtl.companyCodeId}" />
    <input type="hidden" name="customerRealaccountNumber" id="customerRealaccountNumber" value="${dataSetManagementDtl.customerRealaccountNumber}" />
    <input type="hidden" name="customerVtaccountNumber" id="customerVtaccountNumber" value="${dataSetManagementDtl.customerVtaccountNumber}" />
    <input type="hidden" name="companyNameKorAlias" id="companyNameKorAlias" value="${dataSetManagementDtl.companyNameKorAlias}" />

    <input type="hidden" name="apiAccountDivision" id="apiAccountDivision" value="${apiAccountDivision}"/>

    <%--검색조건--%>
    <input type="hidden" id="searchCondition" name="searchCondition" value="${paramVO.searchCondition}"/>
    <input type="hidden" id="searchKeyword" name="searchKeyword" value="${paramVO.searchKeyword}"/>
    <input type="hidden" id="searchPubcompanyCodeId" name="searchPubcompanyCodeId" value="${paramVO.searchPubcompanyCodeId}"/>
    <input type="hidden" id="searchDataSetYn" name="searchDataSetYn" value="${paramVO.searchDataSetYn}"/>
    <input type="hidden" id="searchDateType" name="searchDateType" value="${paramVO.searchDateType}"/>
    <input type="hidden" id="searchDateFrom" name="searchDateFrom" value="${paramVO.searchDateFrom}"/>
    <input type="hidden" id="searchDateTo" name="searchDateTo" value="${paramVO.searchDateTo}"/>

    <input type="hidden" name="searchPubcompanyCodeIdAllYn" id="searchPubcompanyCodeIdAllYn" value="${paramVO.searchPubcompanyCodeIdAllYn}" />
    <input type="hidden" name="searchDataSetYnAllYn" id="searchDataSetYnAllYn" value="${paramVO.searchDataSetYnAllYn}" />



    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>

    <!-- // wrap_top -->
    <article class="container">
        <div>
                <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
                <%-- lnb(좌측메뉴) 영역 --%>
            <!-- // lnb -->
            <section class="content">
                <div class="location">
                    <h2>테스트 데이터 관리</h2>
                </div>
                <!-- // locatioin -->
                <h3>기본정보</h3>
                <div class="tb_write1">
                    <table>
                        <caption>이름, 아이디, 서비스 제공자, 계좌 유형, 가상계좌 정보</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:30%;">
                            <col style="width:20%;">
                            <col style="width:30%;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">이름</th>
                            <td class="txt_l">${dataSetManagementDtl.customerNameKor}</td>
                            <th scope="row">아이디</th>
                            <td class="txt_l">${dataSetManagementDtl.customerId}</td>
                        </tr>
                        <tr>
                            <th scope="row">서비스 제공자</th>
                            <td class="txt_l">${dataSetManagementDtl.companyNameKorAlias}</td>
                            <th scope="row">계좌 유형</th>
                            <td class="txt_l">${dataSetManagementDtl.customerRealaccountTypeName}</td>
                        </tr>
                        <tr>
                            <th scope="row">가상계좌</th>
                            <td class="txt_l">${dataSetManagementDtl.customerVtaccountNumber}</td>
                            <th scope="row">별칭</th>
                            <td class="txt_l">${dataSetManagementDtl.customerVtaccountAlias}</td>
                        </tr>
                        <c:if test="${dataSetManagementDtl.customerDatasetLockYn == 'N'}">
                        <tr>
                            <th scope="row">데이터셋 잠금 여부</th>
                            <td class="txt_l" colspan="3">미사용</td>
                        </tr>
                        </c:if>
                        <c:if test="${dataSetManagementDtl.customerDatasetLockYn == 'Y'}">
                            <tr>
                                <th scope="row">데이터셋 잠금 여부</th>
                                <td class="txt_l" colspan="3">사용</td>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                </div>
                <!-- // tb_list1 -->
                <div class="tab_menu mt30">
                    <ul>
                        <li class="on"><a class="tabEvent" href="#tab01" id="balanceTab" name="tabDivision">계좌잔고</a></li>
                        <li><a class = "tabEvent" href="#tab02" id="transactionTab" name="tabDivision">거래내역</a></li>
                        <li><a class = "tabEvent" href="#tab03" id="portfolioTab" name="tabDivision">포트폴리오</a></li>
                        <li><a class = "tabEvent" href="#tab04" id="interestTab" name="tabDivision">관심종목</a></li>
                    </ul>
                </div>

                <!-- 계좌잔고 -->
                <div class="tab_cont" id="tab01" style="display: block;">
                    <h4>- summary</h4>
                    <div id="bal_summaryGrid" class="tb_list1"></div>
                    <br>
                    <h4>- equityList</h4>
                    <div id="bal_equityGrid" class="tb_list1"></div>
                    <br>
                    <h4>- fundList</h4>
                    <div id="bal_fundGrid" class="tb_list1"></div>
                    <br>
                    <h4>- etcList</h4>
                    <div id="bal_etcGrid" class="tb_list1"></div>
                </div>
                <!--// 계좌잔고 -->
                <!-- 거래내역 -->
                <div class="tab_cont" id="tab02" style="display: none;">
                    <h4>- transaction</h4>
                    <div id="tran_transactionGrid" class="tb_list1"></div>
                </div>
                <!-- 거래내역 -->


                <div class="tab_cont" id="tab03" style="display: none;">

                    <h4>- cash</h4>
                    <div id="port_summaryGrid" class="tb_write1">
                        <table>
                            <tbody>
                                <tr>
                                    <th scope="row">현금잔고</th>
                                    <td colspan="3" class="txt_l">
                                        <input type="number" value="${selectSummary.cash}" id="portfolio_amt" name="portfolio_amt" style="width: 30%; height: 25px;"
                                                <c:if test="${dataSetManagementDtl.customerDatasetLockYn == 'Y'}">readonly</c:if>/>
                                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnModifyPortfolio"
                                                <c:if test="${dataSetManagementDtl.customerDatasetLockYn == 'Y'}">
                                                    style="border: 1px solid #b9b7b7;background: #c3c3c3;color: #a5a5a5 !important; cursor:default; pointer-events: none;"
                                                </c:if>>수정</a></span>
                                        <%--<button onclick="javascript:fn_modifyPortfolioAmt();return false;">수정</button>--%>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <h4>- equityList</h4>
                    <div id="port_equityGrid" class="tb_list1"></div>
                    <br>
                    <h4>- fundList</h4>
                    <div id="port_fundGrid" class="tb_list1"></div>
                    <br>
                    <h4>- etcList</h4>
                    <div id="port_etcGrid" class="tb_list1"></div>
                </div>

                <!-- 관심종목 -->
                <div class="tab_cont" id="tab04" style="display: none;">
                    <h4>- group</h4>
                    <div id="grp_groupGrid" class="tb_list1"></div>
                </div>

                <div class="btn_type3 mt20">
                    <div class="left"><span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span></div>
                    <div class="right">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnCopy">복사</a></span>
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnDelete">데이터셋 비활성화</a></span>
                    </div>
                </div>
            </section>
            <!-- // content -->
        </div>
    </article>
</form:form>
</body>
</html>