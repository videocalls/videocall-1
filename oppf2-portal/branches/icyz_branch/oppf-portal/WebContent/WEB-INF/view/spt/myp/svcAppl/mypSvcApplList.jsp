<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mypSvcApplList.jsp
 * @Description : [마이페이지>신청서비스]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.10  신동진        최초  생성
 *  2016.08.04  신동진        tab추가, 폐기된 정보제공동의 추가 
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.10
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
	var url = "<c:url value='/spt/myp/svcAppl/mypSvcApplList.do'/>";
	var param = new Object();
	param.paramMenuId = "05002";
	
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
            
            $(".tab_cont").hide();
            $("#tab"+id).show();
            
            if(id == "01"){
            	//서비스 신청 조회
                fn_search();       	
            }else{
            	//폐기 된 정보 조회
                fn_searchDiscard();
            }
                        
            return false;
        });
    }
    
    //서비스 신청 조회
    fn_search();   
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 서비스 신청 조회 --%>
function fn_search(){
    //page setting  
    var url = "<c:url value='/spt/myp/svcAppl/selectMypSvcApplList.ajax'/>";
    var param = $("#MypSvcApplVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    //로딩 호출
    gfn_setLoading(true);
  
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    var resultList = data.data.resultList;
    
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        //핀테크 기업 리스트
        $.each(resultList, function(idx, list){
            html += "<tr>";
            html += "<th scope='row'>";
            // 핀테크 기업이름
            html += list.subcompanyName;
            html += "</th>";
            html += "<td class='left'>";
            //서버에서 전달한 금투사 리스트
            $.each(data.data, function(idx2, subList){
                // 금투사 약관번호 비교
                if(idx2 == list.termsRegNo){
                    $.each(subList, function(idx3, comData){
                        html += comData.pubcompanyName;
                        if(idx3 < subList.length -1){
                            html += ", ";
                        }
                    });
                }
            });
            html += "</td>";
            html += "<td>";
            //동의서 유효 기간
            html += list.termsStartDate + " ~ " + list.termsExpireDate;
            html += "</td>";
            html += "<td>";
            if(list.termsAuthDateYn != "Y") {
                html += "<a href='javascript:fn_openTerms(\""+list.termsRegNoEncryption+"\", \"R\", \""+list.appId+"\", \""+list.subcompanyCodeId+"\");' class='btn_type9'>재동의</a>";
            }
            html += "<a href='javascript:fn_cancelTerms_popupOpen(\""+list.termsRegNoEncryption+"\", \"" +list.appList+ "\");' class='btn_type4 ml5'>폐기</a>";
            html += "<a href='javascript:fn_openTerms(\""+list.termsRegNoEncryption+"\", \"N\", \""+list.appId+"\", \""+list.subcompanyCodeId+"\");' class='btn_type6 ml5'>조회</a>";
            html += "</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='4'><div class='nodata'>";
        html += "신청하신 동의서 내역이 없습니다";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#list").append(html);

    //로딩 호출
    gfn_setLoading(false);
}

<%-- 폐기 된 정보 조회 --%>
function fn_searchDiscard(){
    //page setting  
    var url = "<c:url value='/spt/myp/svcAppl/selectMypSvcApplDiscardList.ajax'/>";
    var param = $("#MypSvcApplVO").serialize();
    var callBackFunc = "fn_searchDiscardCallBack";
    
    //로딩 호출
    gfn_setLoading(true);
  
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchDiscardCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    var resultList = data.data.resultList;
    
    var html = "";

    //목록 셋팅
    $("#discardList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            html += "<tr>";
            html += "<td scope='row'>";
            // 핀테크 기업이름
            html += list.subcompanyName;
            html += "</td>";
            html += "<td class='left'>";
            //서버에서 전달한 금투사 리스트
            $.each(data.data, function(idx2, subList){
                // 금투사 약관번호 비교
                if(idx2 == list.termsRegNo){
                    $.each(subList, function(idx3, comData){
                        html += comData.pubcompanyName;
                        if(idx3 < subList.length -1){
                            html += ", ";
                        }
                    });
                }
            });
            html += "</td>";
            html += "<td>";
            //동의서 유효 기간
            html += list.termsStartDate + " ~ " + list.termsExpireDate;
            html += "</td>";
            html += "<td>";
            //폐기일시
            html += list.termsDiscardDate;
            html += "</td>";
            html += "<td>";
            html += "<a href='javascript:fn_openTerms(\""+list.termsRegNoEncryption+"\", \"N\", \""+list.appId+"\", \""+list.subcompanyCodeId+"\");' class='btn_type6 ml5'>조회</a>";
            html += "</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='4'><div class='nodata'>";
        html += "폐기한 정보제공동의 내역이 없습니다";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#discardList").append(html);

    //로딩 호출
    gfn_setLoading(false);
}

<%-- 서비스 해지 --%>
function fn_cancelService(serviceRegNo){
	var msg = "해당 서비스를 해지 하시면,\n";
	msg += "동의서도 함께 폐기 될 수 있습니다.\n\n";
	msg += "서비스를 해지 하시겠습니까?";
	if(!confirm(msg)){
		return;
	}
	
	$("#serviceRegNo").val(serviceRegNo);
	
	//page setting  
    var url = "<c:url value='/spt/myp/svcAppl/cancelMypSvcAppl.ajax'/>";
    var param = $("#MypSvcApplVO").serialize();
    var callBackFunc = "fn_cancelServiceCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "서비스 해지 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_cancelServiceCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.process' />");
    }else{
        alert("<spring:message code='success.alert.process' />");
    }
    
    fn_search();
}

<%-- 동의서 폐기 --%>
function fn_cancelTerms_popupOpen(termsRegNo, appList){
    $("#appList").html(appList);
    $("#termsRegNo").val(termsRegNo);
    $('#cancelTerms_popup').css("display", "block");
}

function fn_cancelTerms_popupClose(){
    $('#cancelTerms_popup').css("display", "none");
}

function fn_cancelTerms(){
    //page setting  
    var url = "<c:url value='/spt/myp/svcAppl/cancelMypSvcApplTerms.ajax'/>";
    var param = $("#MypSvcApplVO").serialize();
    var callBackFunc = "fn_cancelTermsCallBack";

    //로딩 호출
    gfn_setLoading(true, "동의서 폐기중입니다.");
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}

function fn_cancelTermsCallBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    $('#cancelTerms_popup').css("display", "none");
    fn_search();
}


<%-- 약관 팝업 --%>
var appId = "";
var subCompanyCodeId = "";
function fn_openTerms(termsRegNo, termsAuthYn, appId, subCompanyCodeId){
	var url = "<c:url value='/usr/svcAppl/appTermsPopup.do'/>";
	var objOption = new Object();
	objOption.type = 'modal';
	objOption.width = '900';
    objOption.height = '700';
		
	var objParam = new Object();
    objParam.appId = appId;
    objParam.subCompanyCodeId = subCompanyCodeId;
	objParam.termsRegNo = termsRegNo;
    if(termsAuthYn === "R") {
        objParam.type = "reSync";
    } else {
        objParam.type = "myPage";
    }
	objParam.callBakFunc = "fn_openTermsCallBack";
	
	util_modalPage(url, objOption, objParam);
}
function fn_openTermsCallBack(data){
    // 제공동의 재동의 처리
    fn_appTermsReSync(data);
}
function fn_appTermsReSync(objParam) {
    var url = "<c:url value='/usr/svcAppl/createAppTerms.ajax'/>";
    var param = new Object();
    param.appId = objParam.appId;
    param.subCompanyCodeId = objParam.subCompanyCodeId;
    param.termsCreatedYn = objParam.termsCreatedYn;
    param.termsRegNo = objParam.termsRegNo;
    param.termsAuthType = objParam.termsAuthType;

    if(objParam.termsAuthType === "G032001") {
        param.customerSignDn = objParam.customerSignDn;
        param.customerSignData = objParam.customerSignData;
        param.reqHtml = objParam.reqHtml;
    } else {
        param.arsResultCd = objParam.arsResultCd;
        param.arsResultMessage = objParam.arsResultMessage;
        param.arsTrCd = objParam.arsTrCd;
        param.arsTxtNo = objParam.arsTxtNo;
        param.arsRecordData = objParam.arsRecordData;
    }

    var callBackFunc = "fn_appTermsReSyncCallBack";
    //로딩 호출
    gfn_setLoading(true, '처리 중입니다.');
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(url, param, callBackFunc);
}
function fn_appTermsReSyncCallBack(data) {
    //로딩 호출
    gfn_setLoading(false);
    alert("<spring:message code='success.alert.process' />");
    fn_search();
}

</script>
</head>
<body>
<form:form commandName="MypSvcApplVO" name="MypSvcApplVO" method="post">
<input type="hidden" name="serviceRegNo" id="serviceRegNo" />
<input type="hidden" name="termsRegNo" id="termsRegNo" />
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
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">동의서 관리</li>
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
                    <h3>동의서 관리</h3>
                </div>

                <!-- con -->
                <div class="con">
                    <!-- tab_menu -->
                    <div class="tab_menu">
                        <ul>
                            <li class="on"><a href="javascript:void(0);" id="tab_01">유효</a></li>
                            <li><a href="javascript:void(0);" id="tab_02">폐기</a></li>
                        </ul>
                    </div>
                    <!-- // tab_menu -->
                    
                    <div class="tab_cont" id="tab01">
	                    <!-- tbtype_list2 type4 -->
	                    <table class="tbtype_list2 type3 type3_2 mt25">
                            <caption>핀테크 기업. 금융투자회사, 금융거래정보 제공 동의, 관리 정보리스트</caption>
                            <colgroup>
                                <col style="width:15%;">
                                <col style="width:auto;">
                                <col style="width:25%;">
                                <col style="width:23%;">
                            </colgroup>
                            <thead>
	                            <tr>
                                    <th scope="col">핀테크 기업</th>
                                    <th scope="col">금융투자회사</th>
                                    <th scope="col">금융거래정보 제공 동의</th>
                                    <th scope="col">관리</th>
                                </tr>
	                        </thead>
	                        <tbody id="list">
	                        </tbody>
	                    </table>
	                    <!-- // tbtype_list2 type4 -->
                    </div>	                    
                    <div class="tab_cont" id="tab02">
                        <!-- tbtype_list2 type4 -->
                        <table class="tbtype_list2 type3 type3_2 mt25">
                            <caption>핀테크 기업. 금융투자회사, 금융거래정보 제공 동의, 폐기일시 관리 정보리스트</caption>
                            <colgroup>
                                <col style="width:15%;">
                                <col style="width:auto;">
                                <col style="width:27%;">
                                <col style="width:18%;">
                                <col style="width:5%;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">핀테크 기업</th>
                                    <th scope="col">금융투자회사</th>
                                    <th scope="col">금융거래정보 제공 동의</th>
                                    <th scope="col">폐기일시</th>
                                    <th scope="col">관리</th>
                                </tr>
                            </thead>
                            <tbody id="discardList">
                            </tbody>
                        </table>
                        <!-- // tbtype_list2 type4 -->
                    </div>
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>

</form:form>
<div class="layer_popup" id="cancelTerms_popup" style="display: none;">
    <div class="dimm"></div>
    <!-- #layer01 -->
    <div class="layer_box" id="layer01" style="width: 340px; top: 30%; margin-left: -170px;">
        <div class="layer_tit">동의서 폐기</div>

        <div class="layer_con">
            <div class="cont_pop">
                <p class="">해당 동의서를 폐기하시면 연결된 핀테크앱도 함께 해지됩니다. 동의서를 폐기하시겠습니까?</p>

                <p class="title">연결된 앱</p>
                <div class="scl_gray" id="appList">
                </div>
                <div class="btn_area">
                    <a href="javascript:fn_cancelTerms_popupClose();" class="btn_type2">취소</a>
                    <a href="javascript:fn_cancelTerms();" class="btn_type1">확인</a>
                </div>
            </div>
        </div>

        <a href="javascript:fn_cancelTerms_popupClose();" class="layer_close">레이어팝업 닫기</a>
    </div>
</div>
</body>
</html>