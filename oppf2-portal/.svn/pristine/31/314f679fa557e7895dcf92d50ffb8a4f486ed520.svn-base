<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : main.jsp
 * @Description : admin main
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.02  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.02
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdraw.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxchart.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.js'/>"></script>


<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_searchType = "daily";
var g_searchDate = util_setFmDate(util_getDate());
var g_searchTime = util_getServerTime().getHours();
if(Number(g_searchTime) < 10) g_searchTime = "0" + g_searchTime;
var chartColorGroup = [
    "#7cb5ec",
    "#90ed7d",
    "#f7a35c",
    "#8085e9",
    "#f15c80",
    "#e4d354",
    "#2b908f",
    "#f45b5b",
    "#91e8e1",
    "#7cb5ec",
    "#434348"
];
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/cmm/mainView.do'/>";
    var param = new Object();
    param.paramMenuId = "";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
	
	// tab_menu 
    if($(".tab_menu").length > 0){
        $(".tab_cont:not(:first)").hide();
        $(".tab_menu li a").click(function() {
            $(".tab_menu li").removeClass("on");
            $(this).parent().addClass("on");
            
            var id = $(this).attr("id");
            id = util_replaceAll(id, "tab_", "");
            
            fn_showTabContent(id);
            
            return false;
        });
    }
	
    //탭정보 세팅
    var tabId = $(".tab_menu li a").attr("id");
    $("#"+tabId).parent().addClass("on");
});

<%-- 구분 선택 변경 시 event --%>
function fn_searchTypeChange(){
    var searchTypeValue = $("input[name=searchType]:checked").val();

    if(g_searchDate === "") {
        g_searchDate = util_setFmDate(util_getDate());
    }

    if(searchTypeValue == "min" || searchTypeValue == "hourly"){
        if(undefined === g_searchTime || g_searchTime === "") {
            var g_searchTime = util_getServerTime().getHours();
            if(Number(g_searchTime) < 10) {
                g_searchTime = "0" + g_searchTime;
            }
        }
        $("#searchTime").val(g_searchTime);
        $(".searchTime").show();
    }else{
        $(".searchTime").hide();
    }
}

function fn_searchTypeChange_new(){
    var searchTypeValue = $("input[name=searchType]:checked").val();

    if(searchTypeValue == "min" || searchTypeValue == "hourly"){
        $(".searchTime").show();
    }else{
        $(".searchTime").hide();
    }

    var searchDateFrom = "";
    var searchDateTo = util_getDate();
    if(util_chkReturn($.trim($('#searchDateTo').val()), "s") != "") {
        searchDateTo = util_replaceAll($("#searchDateTo").val(), "-", "");
    }

    if(searchTypeValue == "min") {
        searchDateFrom = util_addDate(searchDateTo, "d", -1);
        $("#searchTypeNotice").text("최대 48시간");
    } else if(searchTypeValue == "hourly") {
        searchDateFrom = util_addDate(searchDateTo, "d", -7);
        $("#searchTypeNotice").text("최대 14일");
    } else if(searchTypeValue == "daily") {
        searchDateFrom = util_addDate(searchDateTo, "m", -3);
        $("#searchTypeNotice").text("최대 6개월");
    } else {
        searchDateFrom = util_addDate(searchDateTo, "y", -1);
        $("#searchTypeNotice").text("최대 10년");
    }

    if(util_chkReturn(searchDateFrom, "s") != "") searchDateFrom = util_setFmDate(searchDateFrom);
    if(util_chkReturn(searchDateTo, "s") != "") searchDateTo = util_setFmDate(searchDateTo);

    $("#searchDateFrom").val(searchDateFrom);
    $("#searchDateTo").val(searchDateTo);

    var searchTimeTo = util_getServerTime().getHours()-1;
    var searchTimeFrom = searchTimeTo+1;
    if(Number(searchTimeTo) < 10) searchTimeTo = "0" + searchTimeTo;
    if(Number(searchTimeFrom) < 10) searchTimeFrom = "0" + searchTimeFrom;
    $("#searchTimeFrom").val(searchTimeFrom);
    $("#searchTimeTo").val(searchTimeTo);
}

/*******************************************
 * 기능 함수
 *******************************************/
 
function fn_showTabContent(id){	
	var moveUrl = "<c:url value='/apt/cmm/mainStatsTraffic.do'/>";
	if(id == "01"){
		moveUrl = "<c:url value='/apt/cmm/mainStatsTraffic.do'/>";
	}else if(id == "02"){
		moveUrl = "<c:url value='/apt/cmm/mainStatsTrafficDtl.do'/>";
	}else{
		moveUrl = "<c:url value='/apt/cmm/mainStatsService.do'/>";
	}
	
	//조회조건 추출
	g_searchType = $("input[name=searchType]:checked").val();
	g_searchDate = util_setFmDate($("#searchDate").val());
	g_searchTime = $("#searchTime").val();
		
	//ajax 호출
    jQuery.ajax({
        type:"POST",
        url: moveUrl, 
        data: "",
        //dataType:"html",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",//한글 깨짐 처리
        error: function(){
        	//에러가 났을 경우 상태 알려줌
            alert("화면 로드 시 문제가 발생하였습니다.");
        },
        success: function(data){
        	//받아온 데이터를 테이블에 추가 
            $("#tabContent").empty();
            $("#tabContent").append(data);
        }
    });
}

 
</script>
</head>
<body>
    
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
        
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
		    <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
		    <%-- lnb(좌측메뉴) 영역 --%>
		    
		    <%-- content 영역 --%>
            <section class="content">
                <div class="location">
                    <h2>Admin Home</h2>
                </div>
                <!-- // locatioin -->
                <!-- contents -->
                <div class="tab_menu">
                    <ul>
                        <li><a href="javascript:void(0);" id="tab_01">API 트래픽 현황</a></li>
                        <li><a href="javascript:void(0);" id="tab_02">API 트래픽 상세 현황</a></li>
                        <li><a href="javascript:void(0);" id="tab_03">회원 가입 및 서비스 연결 현황</a></li>
                    </ul>
                </div>
                
                <%-- ajax call --%>
                <div id="tabContent">
                    <c:import url="/apt/cmm/mainStatsTraffic.do" />
                </div>
                

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</body>
</html>