<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%--
/**  
 * @Name : mainStatsService.jsp
 * @Description : admin main의 회원 가입 및 서비스 연결 현황
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.07.14  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.07.14
 * @version 1.0
 *
 */
--%>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
	//검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //초기화
    $("#btnSearchClear").click(function(){
        util_moveRequest("AptMainVO", "<c:url value='/apt/cmm/mainView.do'/>");
    });
    
    //구분 선택
    $("input[name=searchType]").change(function(){
    	fn_searchTypeChange();
    });
    
    //구분 셋팅
    var tmpSearchType = g_searchType;
    if(tmpSearchType == "min") tmpSearchType = "hourly";
    $("input[name=searchType][value="+tmpSearchType+"]").prop("checked", true);
    fn_searchTypeChange();
    
    //현재 일자 셋팅
    $("#searchDate").val(g_searchDate);
    
    //현재 시간 셋팅
//    $("#searchTime").val(g_searchTime);
        
    //조회
    fn_search();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 조회 --%> 
function fn_search(){

    //validation
    if(!fn_validate()){
        return;
    }

    //회원 가입 및 서비스 연결 현황 요약
    fn_searchUserService();
    
    //가상계좌 발급 회원 현황(요약)
    fn_searchUserAccount();
    
    //핀테크 앱 연결 회원 현황(요약)
    fn_searchUserApp();
    
    //현황 title 셋팅
    $(".statsDayStd").html("( " + util_setFmDate($("#searchDate").val()) + " 기준 )");
    
    //포털 회원 가입 현황 요약
    fn_searchSptUserList();
    
    //기업/금투사 포털 회원 가입 현황 요약 -> 기업/금투사 등록정보
    fn_searchCptUserList();
    
    //금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌
    fn_searchUserAccountList();
    
    //금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자
    fn_searchUserAccountCompanyList();
    
    //금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자
    fn_searchUserServiceAppList();
    
} 

<%-- 회원 가입 및 서비스 연결 현황 요약 조회 --%>
function fn_searchUserService(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "userService");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserService.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserServiceCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserServiceCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "userService");
        
    var resultList = data.resultList;
    if(util_chkReturn(resultList, "s") != ""){
        var maxValueData = 0;
        var unitIntervalData = 5;
        
        $.each(resultList, function(idx,data){
        	var val = Number(data.userCnt) + Number(data.serviceCnt);
        	
            if(maxValueData < Number(val)){
                maxValueData = Number(val);
            }
        });
        
        if(maxValueData > 100){
        	unitIntervalData = Math.round(maxValueData / 100) * 10;
        }
        
        maxValueData = maxValueData + 5;
        
        //회원 가입 및 서비스 연결 현황 요약
        gfn_chart4StackedcolumnLoding("userService", resultList,
            {
                xAxis : {
                    dataField: "Date",
                    unitInterval: 3
                },
                valueAxis : {
                    minValue: 0,
                    maxValue : maxValueData,
                    unitInterval: unitIntervalData
                    //unitInterval: 10
                },
                seriesGroups:
                [
                    {
                        type: 'stackedcolumn',
                        series: [
                                { dataField: 'userCnt', displayText: '회원 가입'},
                                { dataField: 'serviceCnt', displayText: '서비스 연결'}
                        ]
                    }
                ]
            }
        );
    }else{
        //로딩 호출
        gfn_setLoading(false, null, "userService");
    }
}

<%-- 가상계좌 발급 회원 현황(요약) 조회 --%>
function fn_searchUserAccount(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "userAccount");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserAccount.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserAccountCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserAccountCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "userAccount");
    
    var resultList = data.resultList;
    gfn_chart4PieLoding("userAccount", resultList,
        {
            seriesGroups:
            [
            {
                type: 'pie',
                showLegend: true,
                enableSeriesToggle: true,
                series: 
                [
                { 
                    dataField: 'companyAvg', 
                    displayText: 'companyName',
                    
                    showLabels: true,
                    labelRadius: 160,
                    labelLinesEnabled: true,
                    labelLinesAngles: true,
                    labelsAutoRotate: false,
                    initialAngle: 0,
                    radius: 125,
                    minAngle: 0,
                    maxAngle: 180,
                    centerOffset: 0,
                    offsetY: 170,
                    formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
                        if (isNaN(value))
                            return value;
                        return value + '%';
                    }
                }
                ]
            }
            ]
        }
    );
}

<%-- 핀테크 앱 연결 회원 현황(요약) 조회 --%>
function fn_searchUserApp(){
    if(!fn_validate()){
        return;
    }
    
    //로딩 호출
    gfn_setLoading(true, null, "userApp");
                
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserApp.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserAppCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserAppCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false, null, "userApp");
    
    var resultList = data.resultList;
    gfn_chart4PieLoding("userApp", resultList,
        {
            seriesGroups:
            [
            {
                type: 'pie',
                showLegend: true,
                enableSeriesToggle: true,
                series: 
                [
                { 
                    dataField: 'appAvg', 
                    displayText: 'appName',
                    
                    showLabels: true,
                    labelRadius: 160,
                    labelLinesEnabled: true,
                    labelLinesAngles: true,
                    labelsAutoRotate: false,
                    initialAngle: 0,
                    radius: 125,
                    minAngle: 0,
                    maxAngle: 180,
                    centerOffset: 0,
                    offsetY: 170,
                    formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
                        if (isNaN(value))
                            return value;
                        return value + '%';
                    }
                }
                ]
            }
            ]
        }
    );
}

<%-- 포털 회원 가입 현황 요약 --%>
function fn_searchSptUserList(){
    if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceSptUserList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchSptUserListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchSptUserListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#sptUserList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<td>";
            html += util_setCommas(list.customerCntAcc) + " 명";
            html += "</td>";
            
            html += "<td>";
            html += util_setCommas(list.customerCntYday) + " 명";
            html += "</td>";

            html += "<td>";
            html += util_setCommas(list.operatorCntAcc) + " 명";
            html += "</td>";

            html += "<td>";
            html += util_setCommas(list.operatorCntYday) + " 명";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        //총건
        html += "<tr>";
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        html += "</tr>";
    }
    
    $("#sptUserList").append(html);
}

<%-- 기업/금투사 등록정보 --%>
function fn_searchCptUserList(){
    if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceCptUserList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchCptUserListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchCptUserListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#cptUserList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            //둘다
            html += "<td>";
            html += util_setCommas(list.operatorCntG002001Acc) + " 명";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.operatorCntG002001Yday) + " 명";
            html += "</td>";
            
            //API 서비스 제공자
            html += "<td>";
            html += util_setCommas(list.operatorCntG002002Acc) + " 명";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.operatorCntG002002Yday) + " 명";
            
            //핀테크 앱 개발자
            html += "<td>";
            html += util_setCommas(list.operatorCntG002003Acc) + " 명";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.operatorCntG002003Yday) + " 명";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        //총건
        html += "<tr>";
        
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        
        html += "<td>- 명</td>";
        html += "<td>- 명</td>";
        
        html += "</tr>";
    }
    
    $("#cptUserList").append(html);
}

<%-- 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌 조회 --%>
function fn_searchUserAccountList(){
    if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserAccountList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserAccountListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserAccountListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#userAccountList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<td>";
            html += util_setCommas(list.accountCntAcc) + " 건";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.accountCntYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        
        html += "<td>- 건</td>";
        html += "<td>- 건</td>";
        
        html += "</tr>";
    }
    
    $("#userAccountList").append(html);
}

<%-- 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회 --%>
function fn_searchUserAccountCompanyList(){
    if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserAccountCompanyList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserAccountCompanyListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserAccountCompanyListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#userAccountCompanyList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<th>";
            html += list.companyName;
            html += "</th>";
            html += "<td>";
            html += util_setCommas(list.accountCntAcc) + " 건";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.accountCntYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        
        html += "<th>-</th>";
        html += "<td>- 건</td>";
        html += "<td>- 건</td>";
        
        html += "</tr>";
    }
    
    $("#userAccountCompanyList").append(html);
}

<%-- 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회 --%>
function fn_searchUserServiceAppList(){
    if(!fn_validate()){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/apt/cmm/selectMainStatsServiceUserServiceAppList.ajax'/>";
    var param = $("#AptMainVO").serialize();
    var callBackFunc = "fn_searchUserServiceAppListCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageNoErrormsg(url, param, callBackFunc);
}
function fn_searchUserServiceAppListCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
        
    var resultList = data.resultList;
    var html = "";

    //목록 셋팅
    $("#userServiceAppList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            
            html += "<tr>";
            
            html += "<th>";
            html += list.companyName + " / " + list.appName;
            html += "</th>";
            html += "<td>";
            html += util_setCommas(list.appCntAcc) + " 건";
            html += "</td>";
            html += "<td>";
            html += util_setCommas(list.appCntYday) + " 건";
            html += "</td>";
            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        
        html += "<th>-</th>";
        html += "<td>- 건</td>";
        html += "<td>- 건</td>";
        
        html += "</tr>";
    }
    
    $("#userServiceAppList").append(html);
}

<%-- validate --%>
function fn_validate(){
    //조회일 체크
    var searchDate = $.trim(util_replaceAll($("#searchDate").val(), "-", ""));
    var searchTime = $.trim($('#searchTime').val());
    var searchDateTime = "";
    
    //조회일
    if(util_chkReturn(searchDate, "s") == ""){
        alert("<spring:message code='errors.required' arguments='조회일'/>");
        $('#searchDate').focus();
        return false;  
    }else{
        if(!util_isDate(searchDate)){
            alert("<spring:message code='errors.invalid' arguments='조회일' />");
            $("#searchDate").focus();
            return false;
        }
    }
    
    //조회 시간
    if(
        util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "min" ||
        util_chkReturn($.trim($('input[name=searchType]:checked').val()), "s") == "hourly"
    ){
        if(util_chkReturn(searchTime, "s") == "") {
            alert("<spring:message code='errors.required' arguments='조회시간'/>");
            $('#searchTime').focus();
            return false;
        }else{
            if(!util_isNum(searchTime)){
                alert("<spring:message code='errors.integer' arguments='조회시간'/>");
                $('#searchTime').focus();
                return false;           
            }else{
                if(Number(searchTime) <= -1 || Number(searchTime) > 24){
                    alert("<spring:message code='errors.range' arguments='조회시간,00,23'/>");
                    $('#searchTime').focus();
                    return false;
                }
            }
        }
        
        //조회일시 셋팅
        searchDateTime = searchDate;
        if(Number(searchTime) < 10){
            searchTime = "0" + Number(searchTime); 
        }
        searchDateTime += searchTime;
    }else{
        //조회일시 셋팅
        searchDateTime = searchDate;
        
    }
    $("#searchDateTime").val(searchDateTime);
        
    return true;
    
}
</script>

<form:form commandName="AptMainVO" name="AptMainVO" method="post">
<input type="hidden" name="searchDateTime" id="searchDateTime" value="" />

<div class="tb_write1">
    <table>
        <caption>키워드, 노출종류, 조회일</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:*;">
            <col style="width:20%;">
            <col style="width:*;">
        </colgroup>
        <tbody>
        <tr>
            <th scope="row"><label for="">구분</label></th>
            <td class="txt_l">
                <input type="radio" id="searchTypeHourly" name="searchType" value="hourly" /><label for="searchTypeHourly"> 1시간 </label>
                <input type="radio" id="searchTypeDaily" name="searchType" value="daily" /><label for="searchTypeDaily"> 1일 </label>
                <input type="radio" id="searchTypeMonthly" name="searchType" value="monthly" /><label for="searchTypeMonthly"> 1개월 </label>
            </td>
            <th scope="row"><label for="searchDateFrom">조회일/시</label></th>
            <td class="txt_l">
                <input type="text" id="searchDate" name="searchDate" style="width:80px;" />
                &nbsp;
                <input type="text" id="searchTime" name="searchTime" maxlength="2" style="width:20px;" class="searchTime" />
                <span class="info_msg searchTime">※ 조회시간 ex) 00~23</span>
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
</form:form>

<div class="division_wrap">
	<ul>
	    <li>
	        <div>
	            <h3>회원 가입 및 서비스 연결 현황 요약</h3>
	            <div id="userService" class="graph_box" style="height: 400px;"></div>
	        </div>
	    </li>   
	</ul>    
</div>

<div class="division_wrap">
	<ul>
	    <li>
	       <div>
	           <h3>가상계좌 발급 회원 현황(요약)</h3>
	           <div id="userAccount" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
	    <li>
	       <div>
	           <h3>핀테크 앱 연결 회원 현황(요약)</h3>
	           <div id="userApp" class="graph_box" style="height: 300px;"></div>
	       </div>
	    </li>
    </ul>
</div>    

<h3 style="margin-top: 30px;">포털 회원 가입 현황 요약 <span class="statsDayStd"></span></h3>
<div class="tb_list1">
    <table>
        <caption>누적회원, 전월 신규가입회원 정보</caption>
        <colgroup>
            <col style="width:25%;">
            <col style="width:25%;">
            <col style="width:25%;">
            <col style="width:*;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">누적회원(개인)</th>
            <th scope="col">전일 신규가입(개인)</th>
            <th scope="col">누적회원(기업)</th>
            <th scope="col">전일 신규가입(기업)</th>
        </tr>
        </thead>
        <tbody id="sptUserList">
        <tr>
            <td>- 명</td>
            <td>- 명</td>
            <td>- 명</td>
            <td>- 명</td>
        </tr>
        </tbody>
    </table>
</div>

<h3 style="margin-top: 30px;">기업/금투사 등록정보 <span class="statsDayStd"></span></h3>
<div class="tb_list1">
    <table>
        <caption>누적회원, 전월 신규가입회원 정보</caption>
        <colgroup>
            <col style="width:18%;">
            <col style="width:16%;">
            <col style="width:18%;">
            <col style="width:16%;">
            <col style="width:18%;">
            <col style="width:16%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col" colspan="2">둘다</th>
            <th scope="col" colspan="2">API 서비스 제공자</th>
            <th scope="col" colspan="2">핀테크 앱 개발자</th>
        </tr>
        <tr>
            <th scope="col">누적회원</th>
            <th scope="col">전일 신규등록</th>
            
            <th scope="col">누적회원</th>
            <th scope="col">전일 신규등록</th>
            
            <th scope="col">누적회원</th>
            <th scope="col">전일 신규등록</th>
        </tr>
        </thead>
        <tbody id="cptUserList">
        <tr>
            <td>- 명</td>
            <td>- 명</td>
            
            <td>- 명</td>
            <td>- 명</td>
            
            <td>- 명</td>
            <td>- 명</td>
        </tr>
        </tbody>
    </table>
</div>

<h3 style="margin-top: 30px;">금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 <span class="statsDayStd"></span></h3>
<div class="tb_list1">
    <table>
        <caption>금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌</caption>
        <colgroup>
            <col style="width:50%;">
            <col style="width:*;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">가상계좌 발급 누적 건수</th>
            <th scope="col">전일 신규/추가 발급 건수</th>
        </tr>
        </thead>
        <tbody id="userAccountList">
        <tr>
            <td>- 건</td>
            <td>- 건</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="tb_list1" style="margin-top: 10px;">
    <table>
        <caption>금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:40%;">
            <col style="width:40%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">서비스 제공자</th>
            <th scope="col">가상계좌 발급 누적 건수</th>
            <th scope="col">전일 신규/추가 발급 건수</th>
        </tr>
        </thead>
        <tbody id="userAccountCompanyList">
        <tr>
            <th>-</th>
            <td>- 건</td>
            <td>- 건</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="tb_list1" style="margin-top: 10px;">
    <table>
        <caption>금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자</caption>
        <colgroup>
            <col style="width:20%;">
            <col style="width:40%;">
            <col style="width:40%;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">앱 개발자</th>
            <th scope="col">서비스 연결 누적 건수</th>
            <th scope="col">전일 신규/추가 연결 건수</th>
        </tr>
        </thead>
        <tbody id="userServiceAppList">
        <tr>
            <th>-</th>
            <td>- 건</td>
            <td>- 건</td>
        </tr>
        </tbody>
    </table>
</div>