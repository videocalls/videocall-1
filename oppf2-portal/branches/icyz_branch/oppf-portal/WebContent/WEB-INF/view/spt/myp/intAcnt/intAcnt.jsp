<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : asumAcntIsu.jsp
 * @Description : [마이페이지:통합계좌조회메인]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.27  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.27
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript" src="/js/spt/common_pub.js"></script>
<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdraw.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxchart.core.js'/>"></script>
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
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

var customerRegNo = ''; //회원OP등록번호
var customerId    = 'hjkl1234'; //사용자ID
var appId = ''; //appId
var accessTokenCheck = ''; //token 검증
var responseType = 'code';
var tabType = 'balance';
var interestGroup = []; //관심종목 그룹별 리스트

/*변수 셋팅 함수*/
function fn_setVariable(){
    var tmpVar = '';
    
    tmpVar = '${customerRegNo}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerRegNo = tmpVar;
    }
    
    tmpVar = '${customerId}'; 
    if(util_chkReturn(tmpVar, "s") != ""){
        customerId = tmpVar;
    }

    tmpVar = '${appId}';
    if(util_chkReturn(tmpVar, "s") != ""){
        appId = tmpVar;
    }

    tmpVar = '${oauthVO}';
    accessTokenCheck = util_chkReturn(tmpVar, "s", "f");

    var searchDateTo = util_getDate();
    var searchDateFrom = util_addDate(searchDateTo, "d", -7);
    $("#searchDateFrom").val(util_setFmDate(searchDateFrom));
    $("#searchDateTo").val(util_setFmDate(searchDateTo));
}

/*******************************************
 * 기능 함수
 *******************************************/

/* 서비스검증 함수 */
function fn_svcApplCheck(){
    //서비스 미신청시 alert 호출 후 액션
    <c:if test="${empty accountProfileList}">
    if(confirm("코스콤 통합 계좌 조회 서비스를\n신청하셔야 합니다. 신청 화면으로\n이동하시겠습니까?")){
        var url = "<c:url value='/usr/svcAppl/appDetail.do'/>?appId=${appId}";
        var objParam = new Object();
        objParam.appId = appId;
        util_movePage(url, objParam);
        return;
    }else{
        var url = "<c:url value='/myp/asumAcnt/asumAcnt.do'/>";
        util_movePage(url);
        return;
    }
    </c:if>
    <c:if test="${!empty accountProfileList}">
        //token검증
        fn_oAuthTokenCheck();
    </c:if>
}

/* token검증 함수 */
function fn_oAuthTokenCheck(){
    //토큰 미 획득시 Oauth 로그인창 인증
    if(accessTokenCheck == 'f'){
        var url = "<c:url value='/spt/myp/intAcnt/intAcntPopup.do'/>";
        var objOption = new Object();
        objOption.type = 'modal';
        objOption.width = '280';
        objOption.height = '180';

        var objParam = new Object();
        var oauthLoginUrl = '${oauthLoginUrl}?';
        oauthLoginUrl += 'response_type=' +responseType ;
        oauthLoginUrl += '&client_id=' + '${clientId}';
        oauthLoginUrl += '&redirect_uri=' + '${callbackUrl}';
        oauthLoginUrl += '&scope=' + '${scope}';
        objParam.url = oauthLoginUrl;
        util_modalPage(url, objOption, objParam);
    }
}

/* OAuthToken 완료 함수 */
function fn_oAuthTokenResult(){
    var url = "<c:url value='/spt/myp/intAcnt/intAcnt.do'/>";
    util_movePage(url);
}

/* 추가 신청하기 */
function fn_linkAccount(){
    var url = "<c:url value='/usr/svcAppl/appDetail.do'/>?appId=${appId}";
    var objParam = new Object();
    objParam.appId = appId;
    util_movePage(url, objParam);
}

/* 마이페이지 가상계좌메뉴 이동 */
function fn_linkMypage(){
    var url = "<c:url value='/myp/asumAcnt/asumAcnt.do'/>";
    util_movePage(url);
}
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
/* 조회 */
function fn_search(){

    var url = "";
    var callBackFunc = "";
    var param = new Object();
    //잔고조회
    if(tabType == 'balance'){
        url = "<c:url value='/spt/myp/intAcnt/balance.ajax'/>";
        callBackFunc = "fn_balanceSearchCallBack";
    }else if(tabType == 'transaction'){
    //거래내역
        url = "<c:url value='/spt/myp/intAcnt/transaction.ajax'/>";
        callBackFunc = "fn_transactionSearchCallBack";
        //종목구분
        param.searchIsinType = $("#isinList option:selected").val();
        //종목
        param.searchIsin = $("#searchIsin").val();
        //검색시작일자
        param.searchDateFrom = $("#searchDateFrom").val();
        //검색종료일자
        param.searchDateTo = $("#searchDateTo").val();

    }else if(tabType == 'portfolio'){
    //포토폴리오
        url = "<c:url value='/spt/myp/intAcnt/portfolio.ajax'/>";
        callBackFunc = "fn_portfolioSearchCallBack";
    }else{
    //관심종목
        url = "<c:url value='/spt/myp/intAcnt/interest.ajax'/>";
        callBackFunc = "fn_interestSearchCallBack";
    }

    param.companyId = $("#companyList option:selected").val();
    param.accountNo = $("#accountList option:selected").val();
    //로딩 호출
    gfn_setLoading(true);
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
/* 자산현황 조회 결과 */
function fn_balanceSearchCallBack(data){
    //로딩 호출
    gfn_setLoading(false);
    //조회성공
    if('00' == data.result) {
        //주식리스트
        $('#tab05').css("display", "block");
        //펀드리스트
        $('#tab06').css("display", "none");
        //기타리스트
        $('#tab07').css("display", "none");
        //통합 Summary
        if(null == data.data.totalResult){
            $("#bal_totalAccVal").html("0 원");
            $("#bal_cashAvWithdraw").html("0 원");
            $("#bal_cashBalance").html("0 원");
            $("#bal_valueAtCur").html("0 원");
            $("#bal_valAtTrade").html("0 원");
            $("#bal_proLoss").html("0 원");
        }else{
            var intSummary =  data.data.totalResult.summary;
            $("#bal_totalAccVal").html(util_setNum(intSummary.totalAccVal,true)+ " 원");
            $("#bal_cashAvWithdraw").html(util_setNum(intSummary.cashAvWithdraw,true)+ " 원");
            $("#bal_cashBalance").html(util_setNum(intSummary.cashBalance,true)+ " 원");
            $("#bal_valueAtCur").html(util_setNum(intSummary.valueAtCur,true)+ " 원");
            $("#bal_valAtTrade").html(util_setNum(intSummary.valAtTrade,true)+ " 원");
            $("#bal_proLoss").html(util_setNum(intSummary.proLoss,true)+ " 원");
        }

        //주식탭 클릭
        $('a[href="#tab05"]').trigger('click');
        //주식리스트 그리드
        fn_balanceEquityList(data.data.resultList);
        //펀드리스트 그리드
        fn_balanceFundList(data.data.resultList);
        //기타리스트 그리드
        fn_balanceEtcList(data.data.resultList);

        //실패내역 처리
        fn_failList(data.data, 'bal');

    }else if('01' == data.result){
        //로그인
        fn_login();
    }else{
        alert("조회 오류가 발생 했습니다.");
    }
}
//잔고조회 주식 리스트
function fn_balanceEquityList(data){
    //주식 그리드 셋팅
    var bal_equityListGridColumns;
    if('' == $("#companyList option:selected").val()){
        bal_equityListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '평가금액(원)', datafield: 'valAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '평가손익(원)', datafield: 'proLoss', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }else{
        bal_equityListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '평가금액(원)', datafield: 'valAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '평가손익(원)', datafield: 'proLoss', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }

    $("#bal_equityList_jqxgrid").remove();
    $("#bal_equityList_jqxgridData").html("<div id='bal_equityList_jqxgrid'></div>");
    gfn_gridOption('bal_equityList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: bal_equityListGridColumns
    });

    var resultList = data;
    var equity = new Array();
    $.each(resultList, function (index, value) {
        var list = value.balanceList.balance.equityList;
        if(list.length > 0){
            //주식 리스트 merge
            equity = equity.concat(list);
        }
    });
    gfn_gridData(equity, 'bal_equityList_jqxgrid');
}

//잔고조회 펀드 리스트
function fn_balanceFundList(data){
    //펀드 그리드 셋팅
    var bal_fundListGridColumns;
    if('' == $("#companyList option:selected").val()){
        bal_fundListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '펀드이름', datafield: 'fundName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가금액(원)', datafield: 'valAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가손익(원)', datafield: 'proLoss', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }else{
        bal_fundListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '펀드이름', datafield: 'fundName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가금액(원)', datafield: 'valAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가손익(원)', datafield: 'proLoss', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }

    $("#bal_fundList_jqxgrid").remove();
    $("#bal_fundList_jqxgridData").html("<div id='bal_fundList_jqxgrid'></div>");
    gfn_gridOption('bal_fundList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: bal_fundListGridColumns
    });

    var resultList = data;
    var fund = new Array();
    $.each(resultList, function (index, value) {
        var list = value.balanceList.balance.fundList;
        if(list.length > 0){
            //리스트 merge
            fund = fund.concat(list);
        }
    });
    gfn_gridData(fund, 'bal_fundList_jqxgrid');
}

//잔고조회 기타 리스트
function fn_balanceEtcList(data){
    //기타 그리드 셋팅
    var bal_etcListGridColumns;
    if('' == $("#companyList option:selected").val()){
        bal_etcListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '상품명', datafield: 'assetName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가금액(원)', datafield: 'valueAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2'  }
                ];
    }else{
        bal_etcListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '상품명', datafield: 'assetName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '매수금액(원)', datafield: 'valAtTrade', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '평가금액(원)', datafield: 'valueAtCur', width: '120px', cellsalign: 'right', align: 'center', cellsformat: 'n'  },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2'  }
                ];
    }

    $("#bal_etcList_jqxgrid").remove();
    $("#bal_etcList_jqxgridData").html("<div id='bal_etcList_jqxgrid'></div>");
    gfn_gridOption('bal_etcList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: bal_etcListGridColumns
    });

    var resultList = data;
    var etc = new Array();
    $.each(resultList, function (index, value) {
        var list = value.balanceList.balance.etcList;
        if(list.length > 0){
            //리스트 merge
            etc = etc.concat(list);
        }
    });
    gfn_gridData(etc, 'bal_etcList_jqxgrid');
}

/* 거래내역 조회 결과 */
function fn_transactionSearchCallBack(data){
    //로딩 호출
    gfn_setLoading(false);
    //조회성공
    if('00' == data.result) {
        var trans_GridColumns;
        if('' == $("#companyList option:selected").val()){
            trans_GridColumns =
                    [
                        /*
                        { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                        { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                        { text: '거래일자', datafield: 'transDate', width: '100px', cellsalign: 'center', align: 'center' },
                        /*{ text: '종목코드', datafield: 'isinCode', width: '120px', cellsalign: 'center', align: 'center' },*/
                        { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                        { text: '거래구분', datafield: 'transType', width: '70px', cellsalign: 'center', align: 'center' },
                        { text: '거래금액(원)', datafield: 'changeAmt', width: '130px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                        { text: '거래수량(주)', datafield: 'changeQty', width: '100px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                        { text: '잔고수량(주)', datafield: 'qty', width: '100px', cellsalign: 'right', align: 'center', cellsformat: 'n' }
                    ];
        }else{
            trans_GridColumns =
                    [
                        /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                        { text: '거래일자', datafield: 'transDate', width: '100px', cellsalign: 'center', align: 'center' },
                        /*{ text: '종목코드', datafield: 'isinCode', width: '120px', cellsalign: 'center', align: 'center' },*/
                        { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                        { text: '거래구분', datafield: 'transType', width: '70px', cellsalign: 'center', align: 'center' },
                        { text: '거래금액(원)', datafield: 'changeAmt', width: '130px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                        { text: '거래수량(주)', datafield: 'changeQty', width: '100px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                        { text: '잔고수량(주)', datafield: 'qty', width: '100px', cellsalign: 'right', align: 'center', cellsformat: 'n' }
                    ];
        }

        $("#trans_jqxgrid").remove();
        $("#trans_jqxgridData").html("<div id='trans_jqxgrid'></div>");
        gfn_gridOption('trans_jqxgrid',{
            height:'310px',
            width:'99%',
            columns: trans_GridColumns
        });

        var resultList = data.data.resultList;
        var trans = new Array();
        $.each(resultList, function (index, value) {
            var list = value.transList.transaction;
            if(list.length > 0){
                //주식 리스트 merge
                trans = trans.concat(list);
            }
        });
        $(".transactionCnt").html(trans.length);
        gfn_gridData(trans, 'trans_jqxgrid');

        //실패내역 처리
        fn_failList(data.data, 'trans');

    }else if('01' == data.result){
        //로그인
        fn_login();
    }else{
        alert("조회 오류가 발생 했습니다.");
    }
}

/* 포토폴리오 조회 결과 */
function fn_portfolioSearchCallBack(data){
    //로딩 호출
    gfn_setLoading(false);
    //조회성공
    if('00' == data.result) {
        //주식리스트
        $('#tab08').css("display", "block");
        //펀드리스트
        $('#tab09').css("display", "none");
        //기타리스트
        $('#tab10').css("display", "none");

        //주식탭 클릭
        $('a[href="#tab08"]').trigger('click');
        //주식리스트 그리드
        fn_portfolioEquityList(data.data.resultList);
        //펀드리스트 그리드
        fn_portfolioFundList(data.data.resultList);
        //기타리스트 그리드
        fn_portfolioEtcList(data.data.resultList);

        //실패내역 처리
        fn_failList(data.data, 'portfolio');

    }else if('01' == data.result){
        //로그인
        fn_login();
    }else{
        alert("조회 오류가 발생 했습니다.");
    }
}
//포토폴리오 주식 리스트
function fn_portfolioEquityList(data){
    //주식 그리드 셋팅
    var port_equityListGridColumns;
    if('' == $("#companyList option:selected").val()){
        port_equityListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }else{
        port_equityListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '종목명', datafield: 'isinName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }

    $("#port_equityList_jqxgrid").remove();
    $("#port_equityList_jqxgridData").html("<div id='port_equityList_jqxgrid'></div>");
    gfn_gridOption('port_equityList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: port_equityListGridColumns
    });

    var resultList = data;
    var equity = new Array();
    $.each(resultList, function (index, value) {
        var list = value.portfolioList.portfolio.equityList;
        if(list.length > 0){
            //주식 리스트 merge
            equity = equity.concat(list);
        }
    });
    gfn_gridData(equity, 'port_equityList_jqxgrid');
}

//포토폴리오 펀드 리스트
function fn_portfolioFundList(data){
    //펀드 그리드 셋팅
    var port_fundListGridColumns;
    if('' == $("#companyList option:selected").val()){
        port_fundListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '펀드이름', datafield: 'fundName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '만기일', datafield: 'maturity', width: '100px', cellsalign: 'center', align: 'center' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }else{
        port_fundListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '펀드이름', datafield: 'fundName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '만기일', datafield: 'maturity', width: '100px', cellsalign: 'center', align: 'center' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }

    $("#port_fundList_jqxgrid").remove();
    $("#port_fundList_jqxgridData").html("<div id='port_fundList_jqxgrid'></div>");
    gfn_gridOption('port_fundList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: port_fundListGridColumns
    });

    var resultList = data;
    var fund = new Array();
    $.each(resultList, function (index, value) {
        var list = value.portfolioList.portfolio.fundList;
        if(list.length > 0){
            //리스트 merge
            fund = fund.concat(list);
        }
    });
    gfn_gridData(fund, 'port_fundList_jqxgrid');
}

//포토폴리오 기타 리스트
function fn_portfolioEtcList(data){
    //기타 그리드 셋팅
    var port_etcListGridColumns;
    if('' == $("#companyList option:selected").val()){
        port_etcListGridColumns =
                [
                    /*
                    { text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '금융회사', datafield: 'comName', width: '120px', cellsalign: 'left', align: 'center', pinned: true},
                    { text: '상품명', datafield: 'assetName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }else{
        port_etcListGridColumns =
                [
                    /*{ text: '가상계좌', datafield: 'vtAccNo', width: '20%', cellsalign: 'center', align: 'center', pinned: true},*/
                    { text: '상품명', datafield: 'assetName', width: '150px', cellsalign: 'left', align: 'center' },
                    { text: '상품 구분', datafield: 'assetType', width: '70px', cellsalign: 'center', align: 'center' },
                    { text: '수량(주)', datafield: 'qty', width: '80px', cellsalign: 'right', align: 'center', cellsformat: 'n' },
                    { text: '수익률(%)', datafield: 'earningRate', width: '75px', cellsalign: 'right', align: 'center', cellsformat: 'f2' }
                ];
    }

    $("#port_etcList_jqxgrid").remove();
    $("#port_etcList_jqxgridData").html("<div id='port_etcList_jqxgrid'></div>");
    gfn_gridOption('port_etcList_jqxgrid',{
        height:'310px',
        width:'99%',
        columns: port_etcListGridColumns
    });

    var resultList = data;
    var etc = new Array();
    $.each(resultList, function (index, value) {
        var list = value.portfolioList.portfolio.etcList;
        if(list.length > 0){
            //리스트 merge
            etc = etc.concat(list);
        }
    });
    gfn_gridData(etc, 'port_etcList_jqxgrid');
}

/* 관심종목 조회 결과 */
function fn_interestSearchCallBack(data){
    //로딩 호출
    gfn_setLoading(false);
    //조회성공
    if('00' == data.result) {

        $("#interest_data").empty();
        var resultList = data.data.resultList;
        var group = new Array();
        $.each(resultList, function (index, value) {
            var list = value.interestSymbolListResponseBody.groupList.group;
            if(list.length > 0){
                //주식 리스트 merge
                group = group.concat(list);
            }
        });

        //관심종목 리스트 초기화
        interestGroup = [];
        if(group.length > 0){
            $('#interest_table').css("display", "block");
            var groupList;
            //관심그룹리스트
            $.each(group, function (index, value) {
                groupList += "<option value='" +index+ "'>" +value.groupName+ "</option>";
                interestGroup[index] = value.isinName;
                //관심그룹에 포함된 첫번째 종목코드리스트
                if(index == 0){
                    var groupLi = '';
                    $.each(value.isinName, function (index1, value1) {
                        groupLi += "<li style='font-size: 13px; color:#1b1b1b; text-align: center;'>" +value1+ "</li>";
                    });
                    //첫번째 그룹 리스트 생성
                    $("#interest_data").html(groupLi);
                    //리스트 총 카운트
                    $(".interestCnt").html(value.isinName.length);
                }
            });
            $('#interestGroupList').css("display", "inline");
            $("#interestGroupList").html(groupList);
            $('#interest_no_data').css("display", "none");
        }else{
            $(".interestCnt").html(0);
            $('#interest_table').css("display", "none");
            $('#interestGroupList').css("display", "none");
            $('#interest_no_data').css("display", "block");
        }

        //실패내역 처리
        fn_failList(data.data, 'interest');


    }else if('01' == data.result){
        //로그인
        fn_login();
    }else{
        alert("조회 오류가 발생 했습니다.");
    }
}

/* 실패내역 결과 */
function fn_failList(data, id){
    var failCnt =  data.failCount;
    //실패 정보 처리
    if(failCnt > 0){
        $('#'+id+'_chk_fail').css("display", "block");
        $("#"+id+"_failCnt").html(failCnt);
        $("#"+id+"_failList").html('');
        var failList =  data.failList;
        $.each(failList, function (index, value) {
            var failData = value;
            var li;
            try{
                var jsonMessage = $.parseJSON(failData.detailMessage);
                li = '<li style="width: 100%;">- ' + failData.comName + ' / '+ failData.vtAccNo + ' / ' + jsonMessage.category + ' / ' + jsonMessage.code + ' / ' + jsonMessage.message + ' </li>';
            }catch(exception){
                li = '<li style="width: 100%;">- ' + failData.comName + ' / '+ failData.vtAccNo + ' / ' + failData.detailMessage + ' </li>';
            }
            $("#"+id+"_failList").append(li);
        });
    }else{
        $('#'+id+'_chk_fail').css("display", "none");
    }
}

/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/spt/myp/intAcnt/intAcnt.do'/>";
    var param = new Object();
    param.paramMenuId = "05006";
    
    gfn_loginNeedMove(url, param);
}

/* 가상계좌 조회 */
function fn_accountListChange(val) {

    $("#accountList").html("<option value=''>전체</option>");
    //전체
    if(val == ''){
        <c:forEach var="data" items="${companyMap}" varStatus="status">
            <c:forEach var="account" items="${data.value}" >
                $("#accountList").append("<option value='${account.customerVtaccountNo}'>${account.customerVtaccountAlias}</option>");
            </c:forEach>
        </c:forEach>
    }else{
        <c:forEach var="data" items="${companyMap}" varStatus="status">
                if(val == '${data.key}'){
                    <c:forEach var="account" items="${data.value}" >
                    $("#accountList").append("<option value='${account.customerVtaccountNo}'>${account.customerVtaccountAlias}</option>");
                    </c:forEach>
                }
        </c:forEach>
    }
}

//잔고조회 주식, 펀드, 기타 리스트
function fn_balanceTabList(id){
    $('#tab05').css("display", "none");
    $('#tab06').css("display", "none");
    $('#tab07').css("display", "none");
    if(id = 1){
        //주식리스트
        $('#tab05').css("display", "block");
    }else if(id == 2){
        //펀드리스트
        $('#tab06').css("display", "block");
    }else{
        //기타리스트
        $('#tab07').css("display", "block");
    }
}

//포토폴리오 주식, 펀드, 기타 리스트
function fn_portfolioTabList(id){
    $('#tab08').css("display", "none");
    $('#tab09').css("display", "none");
    $('#tab10').css("display", "none");
    if(id = 1){
        //주식리스트
        $('#tab08').css("display", "block");
    }else if(id == 2){
        //펀드리스트
        $('#tab09').css("display", "block");
    }else{
        //기타리스트
        $('#tab10').css("display", "block");
    }
}

//자산현황, 거래내역, 포트폴리오, 관심종목
function fn_tabType(id){
    tabType = id;
    //잔고조회
    if(tabType == 'balance'){
        $('.transactionSearch').css("display", "none");
    }else if(tabType == 'transaction'){
        //거래내역검색 조건
        $('.transactionSearch').css("display", "table-row");
    }else if(tabType == 'portfolio'){
        //포토폴리오
        $('.transactionSearch').css("display", "none");
    }else{
        //관심종목
        $('.transactionSearch').css("display", "none");
    }
}

/* 검색 초기화 */
function fn_searchInit() {
    //금융회사 초기화
    $("#companyList option:eq(0)").attr("selected", "selected");
    //종목구분 초기화
    $("#isinList option:eq(0)").attr("selected", "selected");
    //종목초기화
    $("#searchIsin").val('')
    //조회일시 초기화
    var searchDateTo = util_getDate();
    var searchDateFrom = util_addDate(searchDateTo, "d", -7);
    $("#searchDateFrom").val(util_setFmDate(searchDateFrom));
    $("#searchDateTo").val(util_setFmDate(searchDateTo));
    fn_accountListChange('');
}

/* 관심종목 그룹 리스트 변경 */
function fn_GroupListChange(idx) {
    if(interestGroup[idx].length > 0){
        var groupLi = '';
        $.each(interestGroup[idx], function (index, value) {
            groupLi += "<li style='font-size: 13px; color:#1b1b1b; text-align: center;'>" +value+ "</li>";
        });
        $('#interest_table').css("display", "block");
        $('#interest_no_data').css("display", "none");
        //관심종목 테이블 초기화
        $("#interest_data").html(groupLi);
        //리스트 총 카운트
        $(".interestCnt").html(interestGroup[idx].length);
    }else{
        $(".interestCnt").html(0);
        $('#interest_table').css("display", "none");
        $('#interest_no_data').css("display", "block");
    }
}

function setEndDate(eventType) {

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

function isFromToDate(startDate, endDate, startTime, endTime) {
    var startDate = new Date(startDate.substring(0, 4),startDate.substring(4, 6) - 1,startDate.substring(6, 8),startTime,"00");
    var endDate = new Date(endDate.substring(0, 4),endDate.substring(4, 6) - 1,endDate.substring(6, 8),endTime,"00");
    return endDate.getTime() >= startDate.getTime();
}

function fn_setDate(day) {

    var nowDate = new Date();
    switch(day) {
        case "yesterday":
            nowDate.setDate(nowDate.getDate() - 1);
            break;
        case "today":
            break;
        case "week":
            nowDate.setDate(nowDate.getDate() - 7);
            break;
        case "1month":
            nowDate.setMonth(nowDate.getMonth() - 1);
            break;
        case "3month":
            nowDate.setMonth(nowDate.getMonth() - 3);
            break;
    }

    $("#searchDateFrom").val(nowDate.toISOString().substr(0,10));
    $("#searchDateTo").val(util_setFmDate(util_getDate()));
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    //변수셋팅함수 호출
    fn_setVariable();
    //서비스검증
    fn_svcApplCheck();

    // start date 변경
    $("#searchDateFrom").change(function() {
        setEndDate("date");
    });
    // end date 변경
    $("#searchDateTo").change(function() {
        setEndDate("date");
    });

    //엑셀
    $("#btnExcel").click(function(){
        //로딩 호출
        gfn_setLoading(true, "엑셀 조회중 입니다.");
        $("#EXCEL_FRAME").ready(function(){
            gfn_setLoading(false);
        });
        $("#companyId").val($("#companyList option:selected").val());
        $("#accountNo").val($("#accountList option:selected").val());
        util_moveRequest("IntAcntSearchVO", "<c:url value='/spt/myp/intAcnt/transactionExcel.do'/>", "EXCEL_FRAME");
    });

});
//퍼블리셔js
    $(function() {
        // 달력
        $("#searchDateFrom, #searchDateTo").datepicker({
            showOn: "button",
            dateFormat: 'yy-mm-dd',
            buttonImage: "<c:url value='/images/cpt/common/calendar.png'/>",
            buttonImageOnly: true,
            buttonText: "달력보기",
    //	    minDate: '-3y',
    //	    maxDate: '+1y',
            currentText: util_getDate(),
            constrainInput: true
        });
    });

    $(function(){
        $('.bal_fail').on('click', function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                $(this).children().text('열림');
                $('#bal_failList').hide();
            }else{
                $(this).addClass('on');
                $(this).children().text('닫힘');
                $('#bal_failList').show();
            }
            return false;
        });

        $('.trans_fail').on('click', function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                $(this).children().text('열림');
                $('#trans_failList').hide();
            }else{
                $(this).addClass('on');
                $(this).children().text('닫힘');
                $('#trans_failList').show();
            }
            return false;
        });

        $('.portfolio_fail').on('click', function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                $(this).children().text('열림');
                $('#portfolio_failList').hide();
            }else{
                $(this).addClass('on');
                $(this).children().text('닫힘');
                $('#portfolio_failList').show();
            }
            return false;
        });

        $('.interest_fail').on('click', function(){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                $(this).children().text('열림');
                $('#interest_failList').hide();
            }else{
                $(this).addClass('on');
                $(this).children().text('닫힘');
                $('#interest_failList').show();
            }
            return false;
        });
        //date input 한글 제한
        $('.hasDatepicker').live("blur keyup", function() {
            $(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ) );
        });

        var $accMenu = $( '.chk_fail .service' );
        $accMenu.on( 'click', function () {
            //var index = $( this ).index();
            if ( $( this ).closest( '.chk_fail' ).hasClass( 'active' )) {
                $( this ).closest( '.chk_fail' ).removeClass( 'active' );
            } else {
                $( this ).closest( '.chk_fail' ).removeClass( 'active' );
                $( this ).closest( '.chk_fail' ).addClass( 'active' );
            }
            return false;
        });
});

</script>
</head>
<body>
<form:form commandName="IntAcntSearchVO" name="IntAcntSearchVO" method="post">
<input type="hidden" name="companyId" id="companyId" />
<input type="hidden" name="accountNo" id="accountNo" />
<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
            <!-- location -->
            <div class="location">
                <ul>
                    <li class="home"><a href="javascript:void(0);">홈</a></li>
                    <li><a href="javascript:void(0);">마이페이지</a></li>
                    <li class="on">통합 계좌 조회</li>
                </ul>
            </div>
            <!-- // location -->

            <div class="container">

                <%-- lnb(좌측메뉴) 영역 --%>
                <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
                <%-- lnb(좌측메뉴) 영역 --%>

                <!-- content -->
                <article id="content">

                    <div class="sub_title">
                        <h3>통합 계좌 조회</h3>
                    </div>

                    <!-- con -->
                    <div class="con">

                        <!-- tab_menu -->
                        <div class="tab_menu">
                            <ul>
                                <li class="on"><a href="#tab01" onclick="javascript:fn_tabType('balance');">자산현황</a></li>
                                <li class=""><a href="#tab02" onclick="javascript:fn_tabType('transaction');">거래내역</a></li>
                                <li class=""><a href="#tab03" onclick="javascript:fn_tabType('portfolio');">포트폴리오</a></li>
                                <li class=""><a href="#tab04" onclick="javascript:fn_tabType('interest');">관심종목</a></li>
                            </ul>
                        </div>
                        <!-- // tab_menu -->

                        <div class="asset">
                            <c:choose>
                                <c:when test="${empty accountProfileList}" >
                                    <p class="count">금융투자회사 : 0 / 등록계좌 : 0</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="count">금융투자회사 : <c:out value="${fn:length(companyMap)}"/> / 등록계좌 : <c:out value="${accountCnt}"/></p>
                                    <c:if test="${ noLinkAccountCnt > 0 }">
                                    <div class="right">
                                        <p class="being">※ 미등록 계좌가 <c:out value="${noLinkAccountCnt}"/>건 존재합니다.</p>
                                        <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_linkAccount();">추가 신청하기</a>
                                    </div>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- tab_cont -->
                        <c:if test="${!empty accountProfileList }">
                            <%-- 공통검색 --%>
                            <table class="tbtype_form mt20">
                                <caption>금융투자회사, 가상계좌, 구분, 기간 입력</caption>
                                <colgroup>
                                    <col style="width:20%;">
                                    <col style="width:30%;">
                                    <col style="width:20%;">
                                    <col style="width:30%;">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th scope="row"><label for="sel">금융투자회사</label></th>
                                    <td>
                                        <span class="sel_box1">
                                            <select id="companyList" name="companyList" style="width:150px;" onchange="fn_accountListChange(this.value);">
                                                <option value="">전체</option>
                                                <c:forEach var="data" items="${companyMap}" varStatus="status">
                                                    <option value="${data.key}">${data.value[0].pubcompanyName}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </td>
                                    <th scope="row"><label for="sel2">가상계좌</label></th>
                                    <td>
                                        <span class="sel_box1">
                                            <select id="accountList" name="accountList" style="width:200px;">
                                                <option value="">전체</option>
                                                <c:forEach var="data" items="${companyMap}" varStatus="status">
                                                        <c:forEach var="account" items="${data.value}" >
                                                    <option value="${account.customerVtaccountNo}">${account.customerVtaccountAlias}</option>
                                                        </c:forEach>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </td>
                                </tr>
                                <tr class="transactionSearch" style="display: none">
                                    <th scope="row"><label for="chk1">구분</label></th>
                                    <td colspan="3">
									<span class="sel_box1">
										<select title="구분 입력" id="isinList">
											<option value="code">종목코드</option>
											<option value="name">종목명</option>
										</select>
									</span>
                                        <input type="text" style="width:350px" id="searchIsin" name="searchIsin">
                                    </td>
                                </tr>
                                <tr class="transactionSearch" style="display: none">
                                    <th scope="row">등록일</th>
                                    <td class="txt_l" colspan="3">
                                        <input type="text" id="searchDateFrom" name="searchDateFrom"  maxlength="10" style="width:120px;" />
                                        &nbsp;
                                        <input type="text" id="searchDateTo" name="searchDateTo" maxlength="10" style="width:120px;" />
                                        <div class="btn_area2 left">
                                            <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_setDate('yesterday');">어제</a>
                                            <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_setDate('today');">오늘</a>
                                            <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_setDate('week');">7일</a>
                                            <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_setDate('1month');">1개월</a>
                                            <a href="javascript:void(0);" class="btn_type3" onclick="javascript:fn_setDate('3month');">3개월</a>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                            <div class="btn_area search">
                                <a href="javascript:void(0);" class="btn_type1" onclick="javascript:fn_search();">검색</a>
                                <a href="javascript:void(0);" class="btn_type2" onclick="javascript:fn_searchInit();">초기화</a>
                            </div>

                        <!-- 자산현황 -->
                        <div class="tab_cont" id="tab01" style="display: block;">

                            <div class="property">
                                <!-- <p class="title_01">홍길동님의 자산현황</p> -->
                                <table class="tbtype_form align">
                                    <caption>자산현황</caption>
                                    <colgroup>
                                        <col style="width:20%;">
                                        <col style="width:30%;">
                                        <col style="width:20%;">
                                        <col style="width:30%;">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <th scope="row">총평가금액</th>
                                        <td id="bal_totalAccVal"></td>
                                        <th scope="row">출금가능액</th>
                                        <td id="bal_cashAvWithdraw"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">현금잔고</th>
                                        <td id="bal_cashBalance"></td>
                                        <th scope="row">평가금액</th>
                                        <td id="bal_valueAtCur"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">매수금액</th>
                                        <td id="bal_valAtTrade"></td>
                                        <th scope="row">평가손익</th>
                                        <td id="bal_proLoss"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>

                            <!-- tab_menu -->
                            <div class="tab_menu2">
                                <ul>
                                    <li class="on"><a href="#tab05" onclick="javascript:fn_balanceTabList(1);">주식</a></li>
                                    <li class=""><a href="#tab06" onclick="javascript:fn_balanceTabList(2);">펀드</a></li>
                                    <li class=""><a href="#tab07" onclick="javascript:fn_balanceTabList(3);">기타</a></li>
                                </ul>
                            </div>
                            <!-- // tab_menu -->

                            <!-- 주식 -->
                            <div class="tab_cont2" id="tab05" style="display: block;">
                                <%-- grid --%>
                                <div id="bal_equityList_jqxgridData" class="tb_list1">
                                    <div id="bal_equityList_jqxgrid"></div>
                                </div>
                            </div>

                            <!--// 주식 -->

                            <!-- 펀드 -->
                            <div class="tab_cont2" id="tab06" style="display: none;">
                                <%-- grid --%>
                                <div id="bal_fundList_jqxgridData" class="tb_list1">
                                    <div id="bal_fundList_jqxgrid"></div>
                                </div>
                            </div>
                            <!--// 펀드 -->

                            <!-- 기타 -->
                            <div class="tab_cont2" id="tab07" style="display: none;">
                                <%-- grid --%>
                                <div id="bal_etcList_jqxgridData" class="tb_list1">
                                    <div id="bal_etcList_jqxgrid"></div>
                                </div>
                            </div>
                            <!--// 기타 -->

                            <!--// 실패내역 -->
                            <div class="chk_fail" id="bal_chk_fail" style="display: none;">
                                <div class="title_step">
                                    <p class="fail"><span>!</span> <label id="bal_failCnt" style="vertical-align: bottom;"></label>건의 계좌 조회에 실패하였습니다.</p>
                                    <div class="toggle">
									   <span class="bal_fail service">
                                           <a href="javascript:void(0);">펼침</a>
										</span>
                                    </div>
                                    <ul id="bal_failList">
                                    </ul>
                                </div>
                            </div>

                            <!-- 계좌 조회 개수 제한 -->
                            <div class="chk_fail mt10">
                                <div class="title_step">
                                    <p class="fail type_black"><span>!</span> 계좌 조회 개수 제한 안내</p>
                                    <div class="toggle">
										<span class="service">
											<a href="javascript:void(0);">펼침</a>
										</span>
                                    </div>
                                </div>
                                <ul>
                                    <li>- 통합계좌조회 시, 출력 가능한 개수는 <span class="ft_red">계좌별 최대 100개</span>입니다. </li>
                                </ul>
                            </div>
                            <!--// 계좌 조회 개수 제한 -->

                        </div>
                        <!--// 자산현황 -->

                        <!-- 거래내역 -->
                        <div class="tab_cont" id="tab02" style="display: none;">
                            <div class="total_num">총 <span class="transactionCnt">0</span>건</div>
                                <%-- grid --%>
                                <div id="trans_jqxgridData" class="tb_list1" style="margin-top: 10px">
                                    <div id="trans_jqxgrid"></div>
                                </div>

                            <div class="btn_area type2">
                                <div class="left">
                                    <a href="javascript:void(0);" id="btnExcel" class="btn_type9 excel">엑셀</a>
                                </div>
                            </div>

                            <!--// 실패내역 -->
                            <div class="chk_fail" id="trans_chk_fail" style="display: none;">
                                <div class="title_step">
                                    <p class="fail"><span>!</span> <label id="trans_failCnt" style="vertical-align: bottom;"></label>건의 계좌 조회에 실패하였습니다.</p>
                                    <div class="toggle">
									   <span class="trans_fail service">
                                           <a href="javascript:void(0);">펼침</a>
										</span>
                                    </div>
                                    <ul id="trans_failList">
                                    </ul>
                                </div>
                            </div>

                        </div>
                        <!--// 거래내역 -->

                        <!-- 포트폴리오 -->
                        <div class="tab_cont" id="tab03" style="display: none;">

                            <!-- tab_menu -->
                            <div class="tab_menu3">
                                <ul>
                                    <li class="on"><a href="#tab08" onclick="javascript:fn_portfolioTabList(1);">주식</a></li>
                                    <li><a href="#tab09" onclick="javascript:fn_portfolioTabList(2);">펀드</a></li>
                                    <li><a href="#tab10" onclick="javascript:fn_portfolioTabList(3);">기타</a></li>
                                </ul>
                            </div>
                            <!-- // tab_menu -->

                            <!-- 주식 -->
                            <div class="tab_cont3" id="tab08">
                                <%-- grid --%>
                                <div id="port_equityList_jqxgridData" class="tb_list1">
                                    <div id="port_equityList_jqxgrid"></div>
                                </div>
                            </div>

                            <!--// 주식 -->

                            <!-- 펀드 -->
                            <div class="tab_cont3" id="tab09" style="display: none;">
                                <%-- grid --%>
                                <div id="port_fundList_jqxgridData" class="tb_list1">
                                    <div id="port_fundList_jqxgrid"></div>
                                </div>
                            </div>
                            <!--// 펀드 -->

                            <!-- 기타 -->
                            <div class="tab_cont3" id="tab10" style="display: none;">
                                <%-- grid --%>
                                <div id="port_etcList_jqxgridData" class="tb_list1">
                                    <div id="port_etcList_jqxgrid"></div>
                                </div>
                            </div>
                            <!--// 기타 -->

                            <!--// 실패내역 -->
                            <div class="chk_fail" id="portfolio_chk_fail" style="display: none;">
                                <div class="title_step">
                                    <p class="fail"><span>!</span> <label id="portfolio_failCnt" style="vertical-align: bottom;"></label>건의 계좌 조회에 실패하였습니다.</p>
                                       <div class="toggle">
									   <span class="portfolio_fail service">
                                           <a href="javascript:void(0);">펼침</a>
										</span>
                                       </div>
                                       <ul id="portfolio_failList">
                                       </ul>
                                </div>
                            </div>

                        </div>
                        <!--// 포트폴리오 -->

                        <!-- 관심종목 -->
                        <div class="tab_cont" id="tab04" style="display: none;">
                            <div class="total_num">총 <span class="interestCnt">0</span>건
                                <select id="interestGroupList" name="interestGroupList" style="width:150px; display: none; float: right;" onchange="fn_GroupListChange(this.value);">
                                </select>
                            </div>

                            <div class="copy_bank_list" id="interest_table" style="height: auto;margin-top: 20px;display: none;">
                                <ul id="interest_data">
                                </ul>
                            </div>

                            <div class="nodata old_bank_list" id="interest_no_data" style="margin-top: 20px; display: none;"><b>검색 결과가 없습니다.</b></div>

                            <!--// 실패내역 -->
                            <div class="chk_fail" id="interest_chk_fail" style="display: none;">
                                <div class="title_step">
                                    <p class="fail"><span>!</span> <label id="interest_failCnt" style="vertical-align: bottom;"></label>건의 계좌 조회에 실패하였습니다.</p>
                                    <div class="toggle">
										<span class="interest_fail service">
											<a href="javascript:void(0);">펼침</a>
										</span>
                                    </div>
                                    <ul id="interest_failList">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--// 관심종목 -->

                        </c:if>
                    </div>
                    <!-- // con -->

                </article>
                <!-- // content -->
            </div>
        </section>
        <!-- // container -->
    
    </section>
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>
</form:form>
<%-- 엑셀용 frame --%>
<iframe src="" id="EXCEL_FRAME" name="EXCEL_FRAME" style="width:0px; height:0px"></iframe>
<%-- 엑셀용 frame --%>
</body>
</html>