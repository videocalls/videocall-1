<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introPrivacyPolicy.jsp
 * @Description : [개인정보 취급방침] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.13  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.13
 * @version 1.0
 *
 */
--%>
<%--@ include file="/WEB-INF/view/cmm/common-include-head.jspf" --%>
<%--IE문서모드 표준 설정--%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%--뒤로가기 시 만료된 페이지 대신 URL을 다시 호출하는 설정(권장하지는 않습니다.)--%>
<meta http-equiv="expires" content="-1" >
<title>코스콤 오픈플렛폼</title>

<%--JQUERY 기본 스크립트--%>
<script type="text/javascript" src="<c:url value='/js/cmm/jquery-1.11.3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/jquery-migrate-1.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/html5shiv.min.js'/>"></script>
<script type="text/javascript">
/**
 * 입력된 data가 null, undefined 인지 체크 판단하여 key 값에 따른 값을 리턴한다.
 * 
 * @param data
 *            체크할 data
 * @param strReKey
 *            입력안할 경우 : 정상일경우 true, 비정상일 경우 false b : 정상일 경우 true, 비정상일 경우 false
 *            s : 정상일 경우 입력된 data 반환, 비정상일 경우 빈스트링 반환 n : 정상일 경우 입력된 data 반환,
 *            비정상일 경우 0 반환
 * @param returnData
 *            비정상일경우 리턴할 data
 * @param rePlusEnd -
 *            String - 접미어 설정 strReKey 값이 "s"일경우 입력된 값이 정상일 경우 접미어를 붙여서 리턴
 *            비정상이거나 빈스트링일 경우 returnData 값을 리턴
 */
function util_chkReturn(data, strReKey, returnData, rePlusEnd) {

    var strType = jQuery.type(data);
    var bCheck = true;
    var bReturnData = true;
    var bRePlusEnd = false;
    var strRePlusEnd = "";

    if (strType == "null" || strType == "undefined") {
        bCheck = false;
    }

    if (jQuery.type(returnData) == "null"
            || jQuery.type(returnData) == "undefined") {
        bReturnData = false;
    }

    strType = jQuery.type(strReKey);

    if (strType == "null" || strType == "undefined" || strReKey == "b"
            || strReKey == "") {
        return bCheck;
    }

    if (rePlusEnd != null && rePlusEnd != undefined) {
        bRePlusEnd = true;
        strRePlusEnd = rePlusEnd;
    }

    if (bCheck == true) {
        if (strReKey == "s") {
            if (bRePlusEnd == true && data == "") {
                return returnData;
            } else if (bRePlusEnd == true) {
                return data + strRePlusEnd;
            } else {
                if (data == "" && bReturnData == true) {
                    return returnData;
                } else {
                    return data + "";
                }

            }
        } else {
            return data;
        }
    } else {
        if (strReKey == "s") {
            if (bReturnData) {
                return returnData;
            } else {
                return "";
            }
        } else if (strReKey == "n") {
            if (bReturnData) {
                return returnData;
            } else {
                return 0;
            }
        }
    }

    return bCheck;
}
</script>
<script type="text/javascript">
    var serverName = "${pageContext.request.serverName}";
    var serverPort = "${pageContext.request.serverPort}";
    var serverURI = "http://" + serverName;
    
    if(util_chkReturn(serverPort, "s") != ""){
        serverURI += ":" + serverPort;
    }
    
    var g_SYSTEM_KIND = "${SYSTEM_KIND}";
    
    var ipYn;//IP여부(Y:IP, N:Domain)
    var serverNameTmpArr = serverName.split(".");
    if(Number(serverNameTmpArr[0]) || serverNameTmpArr[0] == 'localhost'){
        ipYn = "Y";
    }else{
        ipYn = "N";
    }

    //SSL Port
    var sslPort = "";

    //포트취득[https포트 바뀌면 serverPort도 되는 경우]처리
    if("spt" == g_SYSTEM_KIND){
        serverPort = "<spring:message code='Globals.port.spt'/>";
        sslPort    = "<spring:message code='Globals.sslPort.spt'/>";
        
    }else if("cpt" == g_SYSTEM_KIND){
        serverPort = "<spring:message code='Globals.port.cpt'/>";
        sslPort    = "<spring:message code='Globals.sslPort.cpt'/>";
        
    }else if("apt" == g_SYSTEM_KIND){
        serverPort = "<spring:message code='Globals.port.apt'/>";
        sslPort    = "<spring:message code='Globals.sslPort.apt'/>";
    }

    //http 또는 https context path 설정
    var httpContextpath  = 'http://';
    var httpsContextpath = 'https://';
    if(ipYn == 'Y'){
        httpContextpath  += serverName+':'+serverPort;
        httpsContextpath += serverName+':'+sslPort;
    }else{
        httpContextpath  += serverName;
        httpsContextpath += serverName;
    }
    
</script>

<%-- 각 시스템별 head include --%>
<c:import url="/cmm/includeHead.do" />

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
//화면 로드 처리
$(document).ready(function(){

});

/*******************************************
 * 기능 함수
 *******************************************/

/** util.js
 * @param strUrl
 * @param objParam
 * @param strTarget
 */
function util_movePage(strUrl, objParam, strTarget){
    if (util_chkReturn(strUrl, "s") == ""){
        alert("이동할 URL이 설정되지 않았습니다.");
        return;
    }
    
    if (strUrl == "imsi"){
        alert("개발준비중 입니다.");
        return;
    }
    
    //strUrl 에 parameter를 넘긴경우 objParam으로 셋팅 작업
    var urlParamSettingYn = 'N';
    var obj = new Object;
    var strUrlArr = strUrl.split("?"); //ex)aaa.jsp?a=1&b=2&c=3
    if(strUrlArr.length > 1){
        urlParamSettingYn = 'Y';
        var tmpstrUrlArr = strUrlArr[1].split("&"); //ex)a=1&b=2&c=3
        strUrl = strUrlArr[0];
        if(tmpstrUrlArr.length > 1){
            $.each(tmpstrUrlArr, function(idx,data){
                var tmp2strUrlArr = tmpstrUrlArr[idx].split("="); //ex)a
                if(tmp2strUrlArr.length > 1){
                    obj[tmp2strUrlArr[0]] = tmp2strUrlArr[1];
                }else{
                    alert('잘못된 형식의 URL입니다.');
                    return false;
                }
            });
        }else{
            var tmp2strUrlArr = tmpstrUrlArr[0].split("=");
            if(tmp2strUrlArr.length > 1){
                obj[tmp2strUrlArr[0]] = tmp2strUrlArr[1];
            }else{
                alert('잘못된 형식의 URL입니다.');
                return false;
            }
        }
    }
    
    
    if (util_chkReturn(objParam, "s") == ""){
        objParam = new Object;
    }else if(typeof objParam != "object"){ //objParam에 menuId 값을 넘긴경우 셋팅
        var tmp = objParam;
        objParam = new Object;
        objParam.menuId = tmp;
    }
    if (util_chkReturn(strTarget, "s") != ""){
        strTarget = "target=\"" + strTarget + "\"";
    } else {
        strTarget = "";
    }
    
    if (urlParamSettingYn == 'Y'){
        objParam = obj;
    }
    
//  showLoading();  // 모달 로딩 시작
    
    // 화면에 추가할 html text를 만든다.
    var strHtml = "";
    strHtml += "<form id=\"nextForm\" name=\"nextForm\" method=\"POST\" " + strTarget + " action=\"" + strUrl + "\">";
    strHtml += util_makeInputTag(objParam, "");// 데이터의 일반, 객체, 배열의 모든 종류의 타입
    strHtml += "</form>";
    
    $("body").append(strHtml);  // 화면에 form 등 생성
    $("#nextForm").submit();    // submit
}

/**
 * 동적으로 inputTag를 만든다
 */
function util_makeInputTag(obj, parentKey) {
    var strHtml = "";
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        return String("");        
    } else {
        var n, v;
        for (n in obj) {
            v = obj[n];                
            t = typeof(v);
            if (obj.hasOwnProperty(n)) {
                var inId, inName;
                if("" == parentKey){
                    inId = n, inName = n;
                }else{
                    inId = parentKey + n, inName = parentKey;
                }
                if (t == 'string'){
                    strHtml += "<input name=\"" + inName + "\" id=\"" + inId + "\" type=\"hidden\" value=\'" + v + "\' />";
                }else if (t == "object" && v !== null){// 객체나 배열일 경우 같은 name으로
                                                        // 묶어서 하위에서 재귀적 처리
                    strHtml += util_makeInputTag(v, parentKey + "[" + n + "]"); 
                }else{
                    strHtml += "<input name=\"" + inName + "\" id=\"" + inId + "\" type=\"hidden\" value=\'" + v + "\' />";
                }
            }            
        }            
        return strHtml;
    }    
}

/**
 * 입력된 data가 null, undefined 인지 체크 판단하여 key 값에 따른 값을 리턴한다.
 * 
 * @param data
 *            체크할 data
 * @param strReKey
 *            입력안할 경우 : 정상일경우 true, 비정상일 경우 false b : 정상일 경우 true, 비정상일 경우 false
 *            s : 정상일 경우 입력된 data 반환, 비정상일 경우 빈스트링 반환 n : 정상일 경우 입력된 data 반환,
 *            비정상일 경우 0 반환
 * @param returnData
 *            비정상일경우 리턴할 data
 * @param rePlusEnd -
 *            String - 접미어 설정 strReKey 값이 "s"일경우 입력된 값이 정상일 경우 접미어를 붙여서 리턴
 *            비정상이거나 빈스트링일 경우 returnData 값을 리턴
 */
function util_chkReturn(data, strReKey, returnData, rePlusEnd) {
    
    var strType = jQuery.type(data);
    var bCheck = true;
    var bReturnData = true;
    var bRePlusEnd = false;
    var strRePlusEnd = "";
    
    if (strType == "null" || strType == "undefined") {
            bCheck = false;
    }
    
    if (jQuery.type(returnData) == "null" || jQuery.type(returnData) == "undefined"){
        bReturnData = false;
    }
    
    strType = jQuery.type(strReKey);
    
    if (strType == "null" || strType == "undefined" || strReKey == "b" || strReKey == "") {
        return bCheck;
    }
    
    if (rePlusEnd != null && rePlusEnd != undefined) {
        bRePlusEnd = true;
        strRePlusEnd = rePlusEnd;
    }
    
    if (bCheck == true) {
        if (strReKey == "s"){
            if (bRePlusEnd == true && data == ""){
                return returnData;
            } else if (bRePlusEnd == true){
                return data + strRePlusEnd;
            } else {
                if (data == "" && bReturnData == true){
                    return returnData;
                } else {
                    return data + "";
                }
                
            }
        } else {
            return data;
        }
    } else {
        if (strReKey == "s") {
            if (bReturnData){
                return returnData;
            } else {
                return "";
            }
        } else if (strReKey == "n") {
            if (bReturnData){
                return returnData;
            } else {
                return 0;
            }
        }
    }
    
    return bCheck;
}
</script>
</head>
<body>
<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>

        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">개인정보취급방침</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
        
            <div class="certi_wrap">
                <p class="certi_tit">개인정보취급방침</p>
                <p class="txt">㈜코스콤은 개인정보 보호법 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리지침을 수립·공개합니다</p>

                <ul class="privacy_list">
                    <li><a href="#policy1"><span>1조</span> 개인정보의 처리목적 및 수집항목</a></li>
                    <li><a href="#policy2"><span>2조</span> 개인정보의 처리 및 보유기간</a></li>
                    <li><a href="#policy3"><span>3조</span> 개인정보의 제3자 제공</a></li>
                    <li><a href="#policy4"><span>4조</span> 개인정보처리의 위탁</a></li>
                    <li><a href="#policy5"><span>5조</span> 정보주체의 권리ㆍ의무 및 행사방법</a></li>
                    <li><a href="#policy6"><span>6조</span> 개인정보의 파기</a></li>
                    <li><a href="#policy7"><span>7조</span> 개인정보의 안전성 확보조치</a></li>
                    <li><a href="#policy8"><span>8조</span> 개인정보 보호책임자</a></li>
                    <li><a href="#policy9"><span>9조</span> 개인정보 열람청구</a></li>
                    <li><a href="#policy10"><span>10조</span> 인권침해 구제방법</a></li>
                    <li><a href="#policy11"><span>11조</span> 개인정보 처리방침 변경</a></li>
                </ul>
                
                <!-- privacy_box -->
                <div class="privacy_box">
                    <!-- 제1조 -->
                    <h2 class="tit1" id="policy1">제1조(개인정보의 처리목적)</h2>
                    <p class="txt">㈜코스콤은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다</p>
                    <table class="tbtype_list2 type2">
                        <caption>순번(필수항목), 개인정보파일명(선택항목), 처리목적, 처리항목 정보를 포함하는 표</caption>
                        <colgroup>
                        <col style="width:7%;">
                        <col style="width:17%;">
                        <col style="width:25%;">
                        <col style="">
                        <col style="width:22%;">
                        </colgroup>
                        <thead> 
                            <tr> 
                            <th rowspan="2" scope="col">순번</th> 
                            <th rowspan="2" scope="col">개인정보파일명</th> 
                            <th rowspan="2" scope="col">처리목적</th> 
                            <th colspan="2" scope="col">처리항목</th> 
                            </tr> 
                            <tr> 
                            <th scope="col">필수항목</th> 
                            <th scope="col">선택항목</th> 
                            </tr>
                        </thead>
                        <tbody> 
                            <tr> 
                            <td>1</td> 
                            <td>KOSCOM<br> 고객 정보</td> 
                            <td>고객요구처리 정보 관리</td> 
                            <td>성명, 고객사, 부지점, 전화번호(회사)</td> 
                            <td>이메일, 휴대폰 번호</td> 
                            </tr> 
                            <tr> 
                            <td rowspan="2">2</td> 
                            <td rowspan="2">콜센터 상담<br> 정보</td> 
                            <td>고객상담 정보관리(파워베이스)</td> 
                            <td>성명, 고객사, 부지점, 전화번호(회사)</td> 
                            <td>이메일, 휴대폰 번호</td> 
                            </tr> 
                            <tr> 
                            <td>고객상담 정보관리(공인인증)</td> 
                            <td>성명, 사업자번호, 주민등록번호, 전화번호</td> 
                            <td>이메일</td> 
                            </tr> 
                            <tr> 
                            <td>3</td> 
                            <td>CHECK 사용<br> 고객 정보</td> 
                            <td>CHECK 사용 고객 관리</td> 
                            <td>고객사명,부서,고객번호,선택상품,사용자명,회사전화번호,핸드폰번호,근무지주소</td> 
                            <td>　</td> 
                            </tr> 
                            <tr> 
                            <td>4</td> 
                            <td>FB메신저 사용<br> 고객정보</td> 
                            <td>FB메신저 사용 고객정보 관리</td> 
                            <td>아이디, 비밀번호, 성명, 성명(영문), 전화번호</td> 
                            <td>야후아이디, 전화번호(회사), 휴대전화번호, 팩스번호, 홈페이지주소</td> 
                            </tr> 
                            <tr> 
                            <td>5</td> 
                            <td>MDCS회원정보</td> 
                            <td>상품이용과 관련한 서비스 제공 목적</td> 
                            <td>아이디(E-Mail),    비밀번호, 성명, 회사명, 부서명, 전화번호, 휴대폰 번호, 담당업무</td> 
                            <td>이메일</td> 
                            </tr> 
                            <tr> 
                            <td>6</td> 
                            <td>IT아카데미<br> 수강생 정보</td> 
                            <td>IT아카데미 수강생 정보 관리</td> 
                            <td>성명,주소,실거주지,전화번호,학력,자격,경력,어학능력,가족현황,보유기술,교육이수,알게된 경로, 자기소개</td> 
                            <td>　</td> 
                            </tr> 
                            <tr> 
                            <td>7</td> 
                            <td>꿈나무 장학생 개인정보</td> 
                            <td>꿈나무 장학사업 장학금 지급 등 관리</td> 
                            <td>성명, 주소, 연락처, 주민등록번호, 은행계좌번호, 학교, 학년</td> 
                            <td>　</td> 
                            </tr> 
                            <tr> 
                            <td>8</td> 
                            <td>공인전자주소<br> 회원정보</td> 
                            <td>공인전자주소 서비스를 위한 #메일 주소관리</td> 
                            <td>성명, 전화번호, 이메일, 주소, 휴대폰번호</td> 
                            <td>공인인증서정보, 14세미만 동의서, 대면확인서, 신원확인증표</td> 
                            </tr> 
                            <tr> 
                            <td>9</td> 
                            <td>비윤리 행위 신고 개인정보</td> 
                            <td>비윤리행위 신고자 정보관리</td> 
                            <td>연락처, 이메일, 주소</td> 
                            <td></td> 
                            </tr> 
                            <tr> 
                            <td>10</td> 
                            <td>사보신청 개인정보</td> 
                            <td>사보신청 시 배송에 필요한 최소정보 수집</td> 
                            <td>이메일, 주소</td> 
                            <td>회사명, 부서명</td> 
                            </tr> 
                            <tr> 
                            <td>11</td> 
                            <td>고객만족센터 개인정보</td> 
                            <td>고객만족 등록 시 개인정보</td> 
                            <td>연락처, 이메일</td> 
                            <td></td> 
                            </tr> 
                            <tr> 
                            <td>12</td> 
                            <td>이벤트 개인정보</td> 
                            <td>이벤트 참여 시 당첨자추첨 필요에 필요한 최소정보</td> 
                            <td>연락처 , 이메일</td> 
                            <td></td>
                            </tr> 
                            <tr> 
                            <td>13</td> 
                            <td>금융투자 핀테크 포털</td> 
                            <td>금융투자 핀테크 서비스 연결 및 이용</td> 
                            <td>이메일 주소, 휴대폰 전화번호, 비밀번호, 성명, 암호화된 동일인 식별정보(CI), 범용/증권용 공인인증서(DN)</td> 
                            <td>성별, 생년월일</td> 
                            </tr>
                        </tbody>
                    </table>
                    <p class="point02">※ 기타 ㈜코스콤의 개인정보파일 등록사항 공개는 안전행정부 개인정보보호 종합지원 포털(<a title="새 창 열림" href="www.privacy.go.kr" target="_blank">www.privacy.go.kr</a>) → 개인정보민원 → 개인정보열람등 요구 → 개인정보파일 목록검색 메뉴를 활용해주시기 바랍니다.</p>

                    <!-- 제2조 -->
                    <h2 class="tit1" id="policy2">제2조(개인정보의 처리 및 보유기간)</h2>
                    <ol>
                        <li>① ㈜코스콤은 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의 받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.</li>
                        <li>② 각각의 개인정보 처리 및 보유기간은 다음과 같습니다</li>
                    </ol>

                    <table class="tbtype_list2 type2">
                        <caption>순번, 개인정보파일명, 수집/이용근거, 보유기간 산정근거, 보유기간 정보를 포함하는 표</caption>
                        <colgroup>
                            <col style="width:7%;">
                            <col style="width:20%">
                            <col style="width:25%">
                            <col>
                            <col style="width:15%">
                        </colgroup>
                        <thead>
                        <tr>
                        <th scope="col">순번</th>
                        <th scope="col">개인정보파일명</th>
                        <th scope="col">수집/이용근거</th>
                        <th scope="col">보유기간 산정근거</th>
                        <th scope="col">보유기간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <td>1</td>
                        <td>KOSCOM 고객 정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>5년</td>
                        </tr>
                        <tr>
                        <td rowspan="2">2</td>
                        <td rowspan="2">콜센터 상담 정보</td>
                        <td>개인정보 수집 및 이용 동의<br>(파워베이스)</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>10년</td>
                        </tr>
                        <tr>
                        <td>개인정보 수집 및 이용 동의<br>(전자인증)</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>3년</td>
                        </tr>
                        <tr>
                        <td>3</td>
                        <td>CHECK 사용 고객 정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>5년</td>
                        </tr>
                        <tr>
                        <td>4</td>
                        <td>FB메신저 사용 고객정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년(서비스 미이용 후)</td>
                        </tr>
                        <tr>
                        <td>5</td>
                        <td>MDCS회원정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년(서비스 미이용 후)</td>
                        </tr>
                        <tr>
                        <td>6</td>
                        <td>IT아카데미 수강생 정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년</td>
                        </tr>
                        <tr>
                        <td>7</td>
                        <td>꿈나무 장학생 개인정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>10년</td>
                        </tr>
                        <tr>
                        <td>8</td>
                        <td>공인전자주소 회원정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년(공인전자주소 해지 후)</td>
                        </tr>
                        <tr>
                        <td>9</td>
                        <td>비윤리행위신고개인정보 </td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년</td>
                        </tr>
                        <tr>
                        <td>10</td>
                        <td>사보신청 개인정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년</td>
                        </tr>
                        <tr>
                        <td>11</td>
                        <td>고객만족센터 개인정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년</td>
                        </tr>
                        <tr>
                        <td>12</td>
                        <td>이벤트 개인정보</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td>1년</td>
                        </tr>
                        <tr>
                        <td>13</td>
                        <td>금융투자 핀테크 포털</td>
                        <td>개인정보 수집 및 이용 동의</td>
                        <td>표준 개인정보보호지침 제64조</td>
                        <td> 1년(서비스 미이용 후)</td>
                        </tr>
                        </tbody>
                    </table>

                    <!-- 제3조 -->
                    <h2 class="tit1" id="policy3">제3조(개인정보의 제3자 제공)</h2>
                    <ol>
                        <li>① ㈜코스콤은 정보주체의 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조 에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.</li>
                        <li>② ㈜코스콤은 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.</li>
                    </ol>

                    <table class="tbtype_list2 type2">
                        <caption>순번, 개인정보 파일명, 제공 받는자, 제공근거, 제공목적, 제공항목, 제공받는자 보유 및 이용기간 정보를 포함하는 표</caption>
                        <colgroup>
                            <col style="width:7%;">
                            <col style="width:10%;">
                            <col style="width:13%;">
                            <col style="width:15%;">
                            <col style="width:15%;">
                            <col style="">
                            <col style="width:20%;">
                        </colgroup>
                        <thead> 
                            <tr> 
                            <th scope="col">순번</th> 
                            <th scope="col">개인정보 파일명</th> 
                            <th scope="col">제공 받는자</th> 
                            <th scope="col">제공근거</th> 
                            <th scope="col">제공목적</th> 
                            <th scope="col">제공항목</th> 
                            <th scope="col">제공받는자 보유 및 이용기간</th> 
                            </tr>
                        </thead>
                        <tbody> 
                            <tr>
                            <td>1</td> 
                            <td>MDCS회원정보</td> 
                            <td>한국거래소</td> 
                            <td>KRX-코스콤간 분배위임계약</td> 
                            <td>MDCS 이용자 현황 공유</td> 
                            <td>성명, 회사명, 부서명, 담당업무, 주소, 연락처, 이메일</td> 
                            <td>회원탈퇴시까지(서비스 미사용 1년 후 자동 탈퇴)</td> 
                            </tr>
                            <tr>
                            <td>2</td> 
                            <td>금융투자 핀테크 포털</td> 
                            <td>금융투자회사 (대신증권,대우증권,미래에셋증권,삼성증권,신한금융투자,유안타증권,키움증권,하나투자금융,하이투자증권,현대증권(KB증권),NH투자증권,SK증권,DB금융투자,이베스트증권,코리아에셋증권,한국투자증권)</td> 
                            <td>금융투자 핀테크 서비스 이용 약관 및 금융정보제공 동의</td> 
                            <td>금융투자 핀테크 서비스 연결 및 이용</td> 
                            <td>암호화된 동일인 식별정보(CI), 범용/증권용 공인인증서(DN)</td> 
                            <td>회원탈퇴 및 금융정보제공 동의 폐기</td> 
                            </tr>
                        </tbody>
                    </table>
                    
                    <!-- 제4조 -->
                    <h2 class="tit1" id="policy4">제4조(개인정보처리의 위탁) </h2>
                    <ol>
                        <li>① ㈜코스콤은 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.
                            <table class="tbtype_list2 type2">
                                <caption>순번(업체명), 개인정보 파일명(주소), 위탁하는 업무의 내용(이메일), 수탁업체 현황(전화) 정보를 포함하는 표</caption>
                                <colgroup>
                                    <col style="width:7%;">
                                    <col style="width:12%;">
                                    <col style="width:13%;">
                                    <col style="width:15%;">
                                    <col style="width:15%;">
                                    <col style="">
                                    <col style="width:20%;">
                                </colgroup>
                                <thead> 
                                    <tr> 
                                    <th rowspan="2" scope="col">순번</th> 
                                    <th rowspan="2" scope="col">개인정보 파일명</th> 
                                    <th rowspan="2" scope="col">위탁하는 업무의 내용</th> 
                                    <th colspan="4" scope="col">수탁업체 현황</th> 
                                    </tr> 
                                    <tr> 
                                    <th scope="col">업체명</th> 
                                    <th scope="col">주소</th> 
                                    <th scope="col">이메일</th> 
                                    <th scope="col">전화</th> 
                                </tr>
                                </thead>
                                <tbody> 
                                    <tr>
                                    <td>1</td> 
                                    <td>KOSTOM 고객 정보</td> 
                                    <td>KOSTOM 운용</td> 
                                    <td>ASPN</td> 
                                    <td>서울시 영등포구 국제금융로 6길 33</td> 
                                    <td><a href="mailto:kjs@aspnc.com">kjs@aspnc.com</a></td> 
                                    <td>02-6331-6335</td> 
                                    </tr> 
                                    <tr> 
                                    <td rowspan="2">2</td> 
                                    <td rowspan="2">콜센터 상담 정보</td> 
                                    <td>파워베이스, 114안내 업무</td> 
                                    <td>효성ITX</td> 
                                    <td>서울시 영등포구 의사당대로 1번길 25</td> 
                                    <td><a href="mailto:sook6110@hyosung.com">sook6110@hyosung.com</a></td> 
                                    <td>1577-3600</td> 
                                    </tr> 
                                    <tr> 
                                    <td>공인인증 관련 안내 업무</td> 
                                    <td>아이티네이드</td> 
                                    <td>경기도 안양시 동안구 엘에스로 115번길 26(호계동)</td> 
                                    <td><a href="mailto:whaba@itnade.co.kr">whaba@itnade.co.kr</a></td> 
                                    <td>1577-7337</td> 
                                    </tr> 
                                    <tr> 
                                    <td rowspan="2">3</td> 
                                    <td rowspan="2">CHECK 사용 고객 정보</td> 
                                    <td>CHECK이용료 빌링</td> 
                                    <td>비아이씨엔에스</td> 
                                    <td>성남시 분당구 판교로253 판교이노밸리 C동 603호</td> 
                                    <td><a href="mailto:bi_insa@bicns.com">bi_insa@bicns.com</a></td> 
                                    <td>02-422-4242</td> 
                                    </tr> 
                                    <tr> 
                                    <td>고객 방문 및 문의사항 처리</td> 
                                    <td>이비아이앤씨</td> 
                                    <td>서울시 여의도동 44-26 중앙빌딩 705호</td> 
                                    <td><a href="mailto:yslee@ebest-it.co.kr">yslee@ebest-it.co.kr</a></td> 
                                    <td>02-417-5681</td> 
                                    </tr> 
                                    <tr> 
                                    <td>4</td> 
                                    <td>FB메신저 사용 고객정보</td> 
                                    <td>FB 메신저 운용</td> 
                                    <td>이비아이앤씨</td> 
                                    <td>서울시 여의도동 44-26 중앙빌딩 705호</td> 
                                    <td><a href="mailto:sam2@ebinc.co.kr">sam2@ebinc.co.kr</a></td> 
                                    <td>02-417-5681</td> 
                                    </tr> 
                                    <tr> 
                                    <td>5</td> 
                                    <td>고객만족도 조사를 위한 모집단 고객정보</td> 
                                    <td>고객만족도 조사</td> 
                                    <td>포커스 컴퍼니</td> 
                                    <td>서울시 강남구 테헤란로 77길(삼성동 144-4) ES타워 2층</td> 
                                    <td><a href="mailto:yhlee@focuscompany.co.kr">yhlee@focuscompany.co.kr</a></td> 
                                    <td>02-556-6440</td> 
                                    </tr> 
                                </tbody>
                            </table>
                        </li>
                        <li>② ㈜코스콤은 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적·관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리·감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.</li>
                        <li>③  위탁업무의 내용이나 수탁자가 변경될 경우에는 지체 없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.</li>
                    </ol>
                    
                    <!-- 제5조 -->
                    <h2 class="tit1" id="policy5">제5조(정보주체의 권리·의무 및 행사방법)</h2>
                    <ol>
                        <li>① 정보주체는 개인정보보호법 제35조에 따라 ㈜코스콤에 대해 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.
                            <ol>
                                <li>1. 개인정보 열람요구</li>
                                <li>2. 오류 등이 있을 경우 정정 요구</li>
                                <li>3. 삭제요구</li>
                                <li>4. 처리정지 요구</li>
                            </ol>
                        </li>
                        <li>② 제1항에 따른 권리 행사는 다음에 해당하는 경우 제한될 수 있습니다.
                            <table class="tbtype_list2 type2">
                                <caption>구분, 제한 대상, 근거 조항 정보를 포함하는 표</caption>
                                <colgroup>
                                <col width="20%">
                                <col width="">
                                <col width="15%">
                                </colgroup>
                                <thead> 
                                    <tr> 
                                    <th scope="col">구분</th> 
                                    <th scope="col">제한 대상</th> 
                                    <th scope="col">근거 조항</th> 
                                    </tr>
                                </thead>
                                <tbody> 
                                    <tr> 
                                    <td>1. 개인정보 열람요구</td> 
                                    <td class="left"> 
                                    <ul class="list_text"> 
                                    <li>가. 법률에 따라 열람이 금지되거나 제한되는 경우</li>
                                    <li>나. 다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
                                    <li>다. ㈜코스콤이 다음 어느 하나에 해당하는 업무를 수행할 때 중대한 지장을 초래하는 경우
                                    <ul>
                                    <li>1) 학력·기능 및 채용에 관한 시험, 자격 심사에 관한 업무</li>
                                    <li>2) 보상금·급부금 산정 등에 대하여 진행 중인 평가 또는 판단에 관한 업무</li>
                                    <li>3) 다른 법률에 따라 진행 중인 감사 및 조사에 관한 업무</li>
                                    </ul></li> 
                                    </ul></td> 
                                    <td>개인정보보호법 35조 5항</td> 
                                    </tr> 
                                    <tr> 
                                    <td>2. 개인정보 삭제 요구</td> 
                                    <td class="left">다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우</td> 
                                    <td>개인정보보호법 36조 1항</td> 
                                    </tr> 
                                    <tr> 
                                    <td>3. 처리 정지 요구</td> 
                                    <td class="left"> 
                                        <ul> 
                                            <li>가. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
                                            <li>나. 다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
                                            <li>다. ㈜코스콤이 개인정보를 처리하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우</li>
                                            <li>라. 개인정보를 처리하지 아니하면 정보주체와 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 정보주체가 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li> 
                                        </ul>
                                    </td>
                                    <td>개인정보보호법 37조 2항</td> 
                                    </tr>
                                </tbody>
                            </table>
                        </li>
                        <li>③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.</li>
                        <li>④ 정보주체는 개인정보 보호법 등 관계법령을 위반하여 ㈜코스콤이 처리하고 있는 정보주체 본인이나 타인의 개인정보 및 사생활을 침해하여서는 아니됩니다.</li>
                    </ol>
                    
                    <!-- 제6조 -->
                    <h2 class="tit1" id="policy6">제6조(개인정보의 파기)</h2>
                    <ol>
                        <li>① ㈜코스콤은 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.</li>
                        <li>② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보(또는 개인정보파일)을 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.</li>
                        <li>③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.
                            <ol>
                                <li>1. 파기절차 <br>
                                ㈜코스콤은 파기하여야 하는 개인정보(또는 개인정보파일)에 대해 개인정보 파기계획을 수립하여 파기합니다.<br>㈜코스콤은 파기 사유가 발생한 개인정보(또는 개인정보파일)을 선정하고, 개인정보 분야별책임자의 승인을 받아  개인정보(또는 개인정보파일)을 파기합니다.</li>
                                <li>2. 파기방법<br>㈜코스콤은 전자적 파일 형태로 기록·저장된 개인정보는 기록을 재생할 수 없도록 로우레밸포멧(Low Level Format)등의 방법을 이용하여 파기하며, 종이 문서에 기록·저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다.</li>
                            </ol>
                        </li>
                    </ol>

                    <!-- 제7조 -->
                    <h2 class="tit1" id="policy7">제7조(개인정보의 안전성 확보조치)</h2>
                    <ol>
                        <li>① ㈜코스콤은 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.
                            <ol>
                                <li>1. 관리적 조치 : 내부관리계획 수립·시행, 정기적 직원 교육 등</li>
                                <li>2. 기술적 조치 : 개인정보처리시스템 등의 접근권한 관리, 접근통제시스템 설치, 고유식별정보 등의 암호화, 보안프로그램 설치</li>
                                <li>3. 물리적 조치 : 전산실, 자료보관실 등의 접근통제</li>
                            </ol>
                        </li>
                    </ol>

                    <!-- 제8조 -->
                    <h2 class="tit1" id="policy8">제8조(개인정보 보호책임자)</h2>
                    <ol>
                        <li>① ㈜코스콤은 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다
                            <ol>
                                <li>▶ 개인정보 보호책임자
                                    <ol>
                                        <li>- 성 명 : 강신</li>
                                        <li>- 직 책 : 인프라본부 본부장</li>
                                        <li>- 연락처 : 02-767-7114</li>
                                        <li class="point02">※ ㈜코스콤 대표번호로 연결됩니다.</li>
                                    </ol>
                                </li>
                            </ol>
                        </li>
                    </ol>

                    <!-- 제9조 -->
                    <h2 class="tit1" id="policy9">제9조(개인정보 열람청구)</h2>
                    <ol>
                        <li>① 정보주체는 개인정보보호법 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.
                            <table class="tbtype_list2 type2">
                                <caption>순번, 개인정보파일명, 부서명, 개인정보분야별보호책임자, 개인정보분야별보호담당자 정보를 포함하는 표</caption>
                                    <colgroup>
                                    <col width="7%">
                                    <col width="17%">
                                    <col width="17">
                                    <col width="30%">
                                    <col width="30%">
                                    <col>
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                    <th scope="col">순번</th>
                                    <th scope="col">개인정보파일명</th>
                                    <th scope="col">부서명</th>
                                    <th scope="col">개인정보분야별보호책임자</th>
                                    <th scope="col">개인정보분야별보호담당자</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>1</td>
                                    <td>KOSTOM 고객 정보</td>
                                    <td>경영지원부</td>
                                    <td>- 성명 : 이순모<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 :&nbsp; <a href="mailto:lsm@koscom.co.kr">lsm@koscom.co.kr</a></td>
                                    <td>- 성명 : 김상무<br>
                                    - 소속 : 경영정보팀<br>
                                    - 연락처 :    02-767-84556<br>
                                    - 이메일 :<a href="mailto:"> smkim@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>2</td>
                                    <td>콜센터 상담 정보</td>
                                    <td>경영지원부</td>
                                    <td>- 성명 : 이순모<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 :&nbsp;<a href="mailto: lsm@koscom.co.kr"> lsm@koscom.co.kr</a></td>
                                    <td>- 성명 : 김상무<br>
                                    - 소속 : 경영정보팀<br>
                                    - 연락처 :    02-767-84556<br>
                                    - 이메일 :<a href="mailto:smkim@koscom.co.kr"> smkim@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>3</td>
                                    <td>CHECK 사용 고객 정보</td>
                                    <td>정보업무부</td>
                                    <td>- 성명 : 김성현<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:datamall@koscom.co.kr">datamall@koscom.co.kr</a></td>
                                    <td>- 성명 : 허자은<br>
                                    - 소속 : 정보컨텐츠팀<br>
                                    - 연락처 :    02-767-8649<br>
                                    - 이메일 :<a href="mailto:hje27@koscom.co.kr"> hje27@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>4</td>
                                    <td>FB메신저 사용 고객정보</td>
                                    <td>정보데이터사업부</td>
                                    <td>- 성명 : 김성현<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:shkim@koscom.co.kr"> shkim@koscom.co.kr</a></td>
                                    <td>- 성명 : 이윤학<br>
                                    - 소속 : 금융투자협회팀<br>
                                    - 연락처 :    02-767-8729<br>
                                    - 이메일 : <a href="mailto:lee@koscom.co.kr">lee@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>5</td>
                                    <td>MDCS회원정보</td>
                                    <td>영업2부</td>
                                    <td>- 성명 : 유영권<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:youngkos1@koscom.co.kr"> youngkos1@koscom.co.kr</a></td>
                                    <td>- 성명 : 임운주<br>
                                    - 소속 : 자본시장마케팅팀<br>
                                    - 연락처 :    02-767-7561<br>
                                    - 이메일 :<a href="mailto:woonju@koscom.co.kr"> woonju@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>6</td>
                                    <td>IT아카데미 수강생 정보</td>
                                    <td>자본시장 IT아카데미</td>
                                    <td>- 성명 : 임지영<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:jylim@gmail.com"> jylim@gmail.com</a></td>
                                    <td>- 성명 : 이형석<br>
                                    - 소속 : 자본시장 IT아카데미<br>
                                    - 연락처 :    02-767-7885<br>
                                    - 이메일 : <a href="mailto:hslee@koscom.co.kr">hslee@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>7</td>
                                    <td>꿈나무 장학생 개인정보</td>
                                    <td>대외협력부</td>
                                    <td>- 성명 : 배오열<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 :&nbsp; <a href="mailto:boy@koscom.co.kr">boy@koscom.co.kr</a></td>
                                    <td>- 성명 : 반현주<br>
                                    - 소속 : 사회공헌팀 <br>
                                    - 연락처 :    02-767-8063<br>
                                    - 이메일 : <a href="mailto:ks5246@hanmail.net">ks5246@hanmail.net</a></td>
                                    </tr>
                                    <tr>
                                    <td>8</td>
                                    <td>공인전자주소 회원정보</td>
                                    <td>인프라사업부</td>
                                    <td>- 성명 : 윤성배<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 :&nbsp; <a href="mailto:yunsb082@koscom.co.kr"> yunsb082@koscom.co.kr</a></td>
                                    <td>- 성명 : 이종희 <br>
                                    - 소속 : 전자금융팀<br>
                                    - 연락처 : 02-767-7249<br>
                                    - 이메일 : <a href="mailto: jongdal@koscom.co.kr"> jongdal@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>9</td>
                                    <td>비윤리 행위 신고 개인정보</td>
                                    <td>감사부</td>
                                    <td>- 성명 :    공호관<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:21gongja@gmail.com">21gongja@gmail.com</a></td>
                                    <td>- 성명 :    심명섭<br>
                                    - 소속 : 감사부<br>
                                    - 연락처 : 02-767-8855 <br>
                                    - 이메일 : <a href="mailto:msshim@koscom.co.kr">msshim@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>10</td>
                                    <td>사보신청 개인 정보</td>
                                    <td>대외협력부</td>
                                    <td>-    성명 : 배오열<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:boy@koscom.co.kr">boy@koscom.co.kr</a></td>
                                    <td>-    성명 : 박진용<br>
                                    - 소속 : 대외협력부<br>
                                    - 연락처 : 02-767-8533 <br>
                                    - 이메일 : <a href="mailto:ohhoney2@naver.com">ohhoney2@naver.com</a></td>
                                    </tr>
                                    <tr>
                                    <td>11</td>
                                    <td>고객만족센터 개인정보</td>
                                    <td>경영지원부</td>
                                    <td>-    성명 : 이순모<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 :&nbsp; <a href="mailto:lsm@koscom.co.kr">lsm@koscom.co.kr</a></td>
                                    <td>-    성명 : 김상무<br>
                                    - 소속 : 경영정보팀<br>
                                    - 연락처 : 02-767-84556<br>
                                    - 이메일 : <a href="mailto:smkim@koscom.co.kr">smkim@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>12</td>
                                    <td>이벤트 개인정보</td>
                                    <td>대외협력부</td>
                                    <td>-    성명 : 배오열<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:boy@koscom.co.kr">boy@koscom.co.kr</a></td>
                                    <td>-    성명 : 이형진<br>
                                    - 소속 : 대외협력부<br>
                                    - 연락처 : 02-767-8591 <br>
                                    - 이메일 : <a href="mailto:kscie@koscom.co.kr">kscie@koscom.co.kr</a></td>
                                    </tr>
                                    <tr>
                                    <td>13</td>
                                    <td>금융투자 핀테크 포털</td>
                                    <td>핀테크연구부</td>
                                    <td>-    성명 : 정동욱<br>
                                    - 직위 : 부서장<br>
                                    - 이메일 : <a href="mailto:kgysign@koscom.co.kr">cdukos@koscom.co.kr</a></td>
                                    <td>-    성명 : 이준형<br>
                                    - 소속 : 핀테크연구부<br>
                                    - 연락처 : 02-767-7914 <br>
                                    - 이메일 : <a href="mailto:hellolee@koscom.co.kr">hellolee@koscom.co.kr</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </li>
                        <li>
                            ②  정보주체께서는 제1항의 열람청구 접수·처리부서 이외에, 안전행정부의 ‘개인정보보호 종합지원 포털’ 웹사이트(<a title="새창열림" href="www.privacy.go.kr" target="_blank">www.privacy.go.kr</a>)를 통하여서도 개인정보 열람청구를 하실 수 있습니다. 
                            <p>▶ 안전행정부 개인정보보호 종합지원 포털 → 개인정보 민원 → 개인정보 열람 등 요구<br> (공공아이핀을 통한 실명인증 필요)</p>
                        </li>
                    </ol>
                    
                    <!-- 제10조 -->
                    <h2 class="tit1" id="policy10">제10조(인권침해 구제방법)</h2>
                    <p class="txt">정보주체는 아래의 기관에 대해 개인정보 침해에 대한 피해구제, 상담 등을 문의하실 수 있습니다. <br>&lt; 아래의 기관은 ㈜코스콤과는 별개의 기관으로서, ㈜코스콤의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이 필요하시면 문의하여 주시기 바랍니다 &gt;</p>
                    <ol class="mt10">
                        <li>▶ 개인정보 침해신고센터 (한국인터넷진흥원 운영)
                            <ol>
                                <li>- 소관업무 : 개인정보 침해사실 신고, 상담 신청</li>
                                <li>- 홈페이지 :<a title="새창열림" href="http://privacy.kisa.or.kr" target="_blank"> privacy.kisa.or.kr</a></li>
                                <li>- 전화 : (국번없이) 118</li>
                                <li>- 주소 : (138-950) 서울시 송파구 중대로 135 한국인터넷진흥원 개인정보침해신고센터</li>
                            </ol>
                        </li>
                        <li>▶ 개인정보 분쟁조정위원회 (한국인터넷진흥원 운영)
                            <ol>
                                <li>- 소관업무 : 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)</li>
                                <li>- 홈페이지 :<a title="새창열림" href="http://privacy.kisa.or.kr" target="_blank"> privacy.kisa.or.kr</a></li>
                                <li>- 전화 : (국번없이) 118</li>
                                <li>- 주소 : (138-950) 서울시 송파구 중대로 135 한국인터넷진흥원 개인정보침해신고센터</li>
                            </ol>
                        </li>
                        <li>▶ 대검찰청 사이버범죄수사단 : 02-3480-3573 (cybercid@spo.go.kr)</li>
                        <li>▶ 경찰청 사이버테러대응센터 : 1566-0112 (<a title="새창열림" href="http://www.netan.go.kr" target="_blank">www.netan.go.kr</a> )</li>
                    </ol>

                    <!-- 제11조 -->
                    <h2 class="tit1" id="policy11">제11조(개인정보 처리방침 변경)</h2>
                    <ol>
                        <li>① 분야별로 관리되는 개인정보 파일의 수량 변동으로 인한 변경사유 발생 시에는 고지를 생략하며, 제1조부터 제4조까지 그리고 제9조에서 별도로 공개되는 개인정보파일별 개인정보 처리목적, 처리항목 등의 변경 사항은 해당 공개 자료에서 확인하실 수 있습니다. 이 개인정보 처리방침은 2015. 6. 11부터 적용됩니다.</li>
                        <li>② 이전의 개인정보 처리방침은 코스콤 홈페이지에서 확인하실 수 있습니다.</li>
                    </ol>               

                </div>
                <!-- // privacy_box -->

            </div>

        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>
</body>
</html>