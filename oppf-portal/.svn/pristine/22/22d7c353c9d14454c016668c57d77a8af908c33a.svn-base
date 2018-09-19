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

 /**
  * 설명       : base64 encode, decode
  * 사용방식     : gfnBase64 
  * 주의       : 
  * 리턴       : 
  */
 var gfnBase64 = {
     // private property
     _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="

     // public method for encoding
     ,encode : function(input){
         var output = "";
         var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
         var i = 0;
         input = gfnBase64._utf8_encode(input);
         while(i < input.length){
             chr1 = input.charCodeAt(i++);
             chr2 = input.charCodeAt(i++);
             chr3 = input.charCodeAt(i++);

             enc1 = chr1 >> 2;
             enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
             enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
             enc4 = chr3 & 63;

             if(isNaN(chr2)){
                 enc3 = enc4 = 64;
             }else if(isNaN(chr3)){
                 enc4 = 64;
             }
             output = output +
                 this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                 this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
         }
         return output;
     }

     // public method for decoding
     ,decode : function(input){
         var output = "";
         var chr1, chr2, chr3;
         var enc1, enc2, enc3, enc4;
         var i = 0;
         input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
         while(i < input.length){
             enc1 = this._keyStr.indexOf(input.charAt(i++));
             enc2 = this._keyStr.indexOf(input.charAt(i++));
             enc3 = this._keyStr.indexOf(input.charAt(i++));
             enc4 = this._keyStr.indexOf(input.charAt(i++));
             chr1 = (enc1 << 2) | (enc2 >> 4);
             chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
             chr3 = ((enc3 & 3) << 6) | enc4;
             output = output + String.fromCharCode(chr1);
             if(enc3 != 64){
                 output = output + String.fromCharCode(chr2);
             }
             if(enc4 != 64){
                 output = output + String.fromCharCode(chr3);
             }
         }
         output = gfnBase64._utf8_decode(output);
         return output;
     }

     // private method for UTF-8 encoding
     ,_utf8_encode : function (string) {
         string = string.replace(/\r\n/g,"\n");
         var utftext = "";
         for(var n = 0; n < string.length; n++){
             var c = string.charCodeAt(n);
             if(c < 128){
                 utftext += String.fromCharCode(c);
             }else if((c > 127) && (c < 2048)) {
                 utftext += String.fromCharCode((c >> 6) | 192);
                 utftext += String.fromCharCode((c & 63) | 128);
             }else{
                 utftext += String.fromCharCode((c >> 12) | 224);
                 utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                 utftext += String.fromCharCode((c & 63) | 128);
             }
         }
         return utftext;
     }

     // private method for UTF-8 decoding
     ,_utf8_decode : function (utftext) {
         var string = "";
         var i = 0;
         var c = c1 = c2 = 0;
         while( i < utftext.length ){
             c = utftext.charCodeAt(i);
             if(c < 128){
                 string += String.fromCharCode(c);
                 i++;
             }else if((c > 191) && (c < 224)){
                 c2 = utftext.charCodeAt(i+1);
                 string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                 i += 2;
             }else{
                 c2 = utftext.charCodeAt(i+1);
                 c3 = utftext.charCodeAt(i+2);
                 string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                 i += 3;
             }
         }
        return string;
     }

     ,URLEncode : function (string){
         return escape(this._utf8_encode(string));
     }

     // public method for url decoding
     ,URLDecode : function (string){
         return this._utf8_decode(unescape(string));
     }
 }

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
    objParam.appId = gfnBase64.encode(appId);
    objParam.subCompanyCodeId = gfnBase64.encode(subCompanyCodeId);
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