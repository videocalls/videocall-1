<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : fintechSvcAppChoic.jsp
 * @Description : [핀테크서비스신청:핀테크서비스선택(특정 서비스 선택시]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.04  이희태        최초  생성
 * </pre>
 *
 * @author 이희태
 * @since 2017.03.04
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">
/*******************************************
* 전역 변수
*******************************************/
$(function(){
    $('.service').on('click', function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on');
        }else{
            $(this).addClass('on');
        }
        //핀테크 서비스 선택 초기화
        fn_search();
    });
});
</script>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_initCheckboxArr = ["searchApiCategory","searchApiContractCode","searchPlanType","searchCompanyCodeId"];

var g_resultSvcAccountList;     //조회된 account 정보

var g_saveAccountList;          //account 정보 저장 정보
var g_savePubCompanyList;       //핀테크 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
var g_saveSubCompanyList;       //금투사 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음

var g_svcTermsAuthList;          //핀테크 서비스 목록 (서비스 제공동의 확인용)
var g_svcTermsAuthFlag;          //서비스 제공동의 확인용
var g_svcTermsAuthCheck = ${fn:length(resultList)}; //nextButton 전달 확인용
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/usr/svcAppl/fintechSvcChoic.do'/>";
    var param = new Object();
    param.paramMenuId = "06001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
		fn_login();
		return;
    </c:if>
	
	//금투사 선택 변경 시
	$("input[name=searchCompanyCodeId]").change(function(){
		//콤보라서 약간 텀을 주고 호출한다.
		setTimeout(fn_search, 100);
	});
	
	//사용여부 선택 변경 시
	$("#searchUseYn").change(function(){
        fn_search();
    });
	
	//핀테크 서비스 선택
	$("#btnFintechSvcSave").click(function(){
		fn_fintechSvcSave();
	});
	
	//다음
	$("#btnAccountSvcSave").click(function(){
		fn_accountSvcSave();
    });
	
	//이전
	$("#btnBack").click(function(){
		util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>");
    });

    <%-- 체크박스 선택 onclick이벤트 설정--%>
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }
    
    //조회
    fn_search();

});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 정보제공 동의 체크 --%>
function fn_svcTermsAuthCheck(){
    g_svcTermsAuthFlag = false;
    var checkFlag = false;
    $('input:checkbox[name="customerRealaccountNo"]').each(function() {
          if(this.checked){//checked 처리된 항목의 값
              var tmpCheck = this.value.split('_')[0];
              g_svcTermsAuthList.forEach( function( i ){
                  if(tmpCheck === i.appId && i.termsAuthYn == 'N'){
                      //정보제공 동의 페이지 필수
                      g_svcTermsAuthFlag = true;
                  }
              });
              checkFlag = true;
           }
     });
    $(".svcTerms").remove();
    if(!g_svcTermsAuthFlag && checkFlag){
        $(".finish").append('<li class="svcTerms" style="background: none;padding-left: 0px;"><input type="checkbox" name="infoTerms" id="infoTerms" checked > <label for="infoTerms" class="chk_box">선택된 핀테크기업에 대한 금융투자회사 정보 제공 동의가 완료되어 해당 단계를 건너뜁니다.</label>  </li>');
    }
}
<%-- 검색 --%>
function fn_search(){
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
                
    //page setting  
    var url = "<c:url value='/usr/svcAppl/selectFintechSvcList.ajax'/>";
    var param = $("#SvcApplVO").serialize();
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
	
    var resultSvcList = data.resultSvcList;                             //핀테크 서비스 목록
    var resultSvcPubcompanyList = data.resultSvcPubcompanyList;         //핀테크 서비스 금투사 목록
    var resultSvcAccountList = data.resultSvcAccountList;               //핀테크 서비스 가상계좌 목록

    //핀테크 서비스 목록 (서비스 제공동의 확인용)
    g_svcTermsAuthList = resultSvcList;
    //가상계좌 정보
    g_resultSvcAccountList = resultSvcAccountList;
        
    //핀테크 서비스 선택 리스트 make
    fn_makeSvcList(resultSvcList, resultSvcPubcompanyList);
    
    //가상계좌 선택 리스트 make
    fn_makeSvcAccountList(resultSvcAccountList);

    //로딩 호출
    gfn_setLoading(false);

    //가상계좌 선택 이벤트
    $('input:checkbox[name="customerRealaccountNo"]').click(function() {
        fn_svcTermsAuthCheck();
    });

    //정보제공동의 체크
    fn_svcTermsAuthCheck();
}

<%-- 핀테크 서비스 선택 리스트 make --%>
function fn_makeSvcList(resultSvcList, resultSvcPubcompanyList){
	var html = "";

    //목록 셋팅
    $("#fintechSvcList >").remove();
    var disabled = false;

    if(util_chkReturn(resultSvcList, "s") != ""){
        $.each(resultSvcList, function(idx, list){
           //서비스 선택시 체크로직
           if(list.appId != '<c:out value='${paramVO.appId}'/>' && $('.service').hasClass('on'))
           return true;

           //이미지 경로
            var appImgUrl = "<c:url value=""/>"+"/cmm/appImg/"+list.appId+".do";
            //사용여부
            var useYn = "미사용";
            var useYnCalss = "";


            html += "<tr>";
            html += "<td>";

            //서비스 해지 버튼 셋팅
            if(list.useYn == "Y"){
            	var serviceCancelMsg = "선택 해제";
            	var className = "btn_type4 on";
            	if(list.termsAuthYn == "Y"){
            		serviceCancelMsg = "서비스 해지";
            		className = "btn_type6 off";
            	}
                if(list.appId == '<c:out value='${paramVO.appId}'/>' && $('.service').hasClass('on')){
                    disabled = true;
                }
            	html += "<a href='javascript:fn_cancelService(\""+list.serviceRegNo+"\", \""+list.termsAuthYn+"\");' class='"+className+"' "+disabled+">"+serviceCancelMsg+"</a>";
            }


            html += "<input type='checkbox' id='appId_"+list.appId+"' name='appId' ";

            if(list.useYn == "Y"){
                useYn = "사용중";
                useYnClass = "on";

                html += " value=''  checked='checked' style='display:none;' ";
            }else{
            	useYn = "미사용";
            	useYnClass = "";

            	html += " value='"+list.appId+"' ";
            }

            html += "/>";
            html += "</td>";
            html += "<td>"+list.appCategoryName+"</td>";
            html += "<td>";
            html += "    <div class='app_small'>";
            html += "       <img id='appIconImg' src='"+appImgUrl+"' alt='"+list.appName+"'";
            html += "        onerror='this.src=\"<c:url value="/images/cmm/icon/icon_app_none.png"/>\"' ";
            html += "        />";
            html += "        <p>"+list.appName+"</p>";
            html += "    </div>";
            html += "</td>";
            html += "<td class='left tooltip_wrap'>";
            html += "    <div class='tooltip_area'>";
            html += "        <textarea class='app_textarea' disabled>"+list.appDescription+"</textarea>";

            if(list.termsAuthYn == "Y"){
                //html += "        <a href='javascript:void(0);' class='btn_type8 "+useYnClass+"'>"+useYn+"</a>";
            	html += "        <a href='javascript:void(0);' class='btn_type8 on'>사용중</a>";
            }

            html += "        <div class='tooltip'>";
            html += "            <dl class='con'>";
            html += "                <dt>금융투자회별 연계 서비스</dt>";
            html += "                <dd>";
            html += "                    <table class='tbtype_tip'>";
            html += "                        <caption>금융투자회사, 연계 서비스 정보</caption>";
            html += "                        <colgroup>";
            html += "                            <col style='width:45%;'>";
            html += "                            <col style=''>";
            html += "                        </colgroup>";
            html += "                        <thead>";
            html += "                            <tr>";
            html += "                                <th scope='col'>금융투자회사</th>";
            html += "                                <th scope='col'>연계 서비스</th>";
            html += "                            </tr>";
            html += "                        </thead>";
            html += "                        <tbody>";

            var subHtml = "";
            if(util_chkReturn(resultSvcPubcompanyList, "s") != ""){
                $.each(resultSvcPubcompanyList, function(companyIdx, companyList){
                    if(list.appId == companyList.appId){
                        subHtml += "<tr>";
                        subHtml += "<th scope='row' class='rowspan_pubcompany_"+list.appId+"'>"+companyList.companyNameKorAlias+"</th>";
                        subHtml += "<td>"+companyList.apiTitle+"</td>";
                        subHtml += "</tr>";
                    }
                });
            }

            if(util_chkReturn(subHtml, "s") == ""){
                html += "<tr>";
                html += "<td align='center' colspan='2'>현재 연결 앱이 없습니다.</td>";
                html += "</tr>";
            }else{
                html += subHtml;
            }

            html += "                        </tbody>";
            html += "                    </table>";
            html += "                </dd>";
            html += "            </dl>";
            html += "        </div>";
            html += "    </div>";
            html += "</td>";
            html += "</tr>";
        });
    }else{
         html += "<tr>";
         html += "<td align='center' colspan='4'><div class='nodata'>조회된 데이터가 없습니다.</div></td>";
         html += "</tr>";
    }
    $("#fintechSvcList").append(html);

    if(disabled){
        $('.btn_type4').removeAttr("href");
        //핀테크 서비스 선택 버튼 비활성
        $('#btnFintechSvcSave').css("display", "none");
    }else{
        $('#btnFintechSvcSave').css("display", "block");
    }

    if(util_chkReturn(resultSvcList, "s") != ""){
        $.each(resultSvcList, function(idx, list){
            //rowspan
            gfn_rowspanMerge("rowspan_pubcompany_"+list.appId);
        });
    }
}

<%-- 가상계좌 선택 리스트 make --%>
function fn_makeSvcAccountList(resultSvcAccountList){
	//핀테크 서비스 가상계좌 목록
    var html = "";
    
    //목록 셋팅
    $("#accountSvcList >").remove();
  
    if(util_chkReturn(resultSvcAccountList, "s") != ""){
        $.each(resultSvcAccountList, function(idx, list){
            //서비스 선택시 체크로직
            if(list.appId != '<c:out value='${paramVO.appId}'/>' && $('.service').hasClass('on'))
                return true;

            var key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
            
            html += "<tr>";
            
            html += "<th class='rowspan_subcompany'>"+list.subcompanyName+"</th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+"'><strong>"+list.appName+"</strong></th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId+"'>"+list.pubcompanyName+"</th>";
            
            html += "<td class='left'>";
            html += "    <input type='checkbox' name='customerRealaccountNo' id='"+key+"' value='"+key+"' ";
            
            if(list.useYn == "Y"){
                html += " checked='checked' ";
            }
            
            html += "    />";
            html += "    <label for='"+key+"' class='chk_box'>"+ list.customerVtaccountNo +" / "+ list.customerVtaccountAlias +"</label>";
            html += "</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='4'><div class='nodata'>";
        html += "조회된 데이터가 없습니다.<br><br><strong class='point04'>STEP.2 핀테크 서비스 선택</strong>에서 핀테크 서비스를 선택 후 <strong class='point04'>핀테크 서비스 선택 버튼을 클릭</strong> 해 주세요.";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#accountSvcList").append(html);
    
    //첫 컬럼 rowspan
    gfn_rowspanMerge("rowspan_subcompany");
    
    //두번째, 세번째 rowspan
    var key2 = "";
    var key3 = "";
    if(util_chkReturn(resultSvcAccountList, "s") != ""){
        $.each(resultSvcAccountList, function(idx, list){
            var key2Data = list.subcompanyCodeId+list.appId;
            var key3Data = list.subcompanyCodeId+list.appId+list.pubcompanyCodeId;
            
            //rowspan
            if(key2 == ""){
                key2 = key2Data;
                gfn_rowspanMerge("rowspan_"+key2);
            }else{
                if(key2 != key2Data){
                    key2 = key2Data;
                    gfn_rowspanMerge("rowspan_"+key2);
                }
            }
            
            if(key3 == ""){
                key3 = key3Data;
                gfn_rowspanMerge("rowspan_"+key3);
            }else{
                if(key3 != key3Data){
                    key3 = key3Data;
                    gfn_rowspanMerge("rowspan_"+key3);
                }
            }
        });
    }
}

<%-- 서비스 해지 --%>
function fn_cancelService(serviceRegNo, termsAuthYn){
	var msg = "해당 서비스를 선택 해제 하시겠습니까?";
	var loadingTitle = "선택 해제";
    if(termsAuthYn == "Y"){
    	msg = "해당 서비스를 해지 하시면,\n";
        msg += "동의서도 함께 폐기 될 수 있습니다.\n\n";
        msg += "서비스를 해지 하시겠습니까?";
        
        loadingTitle = "서비스 해지";
    }
    
    if(!confirm(msg)){
        return;
    }
    
    //page setting  
    var url = "<c:url value='/usr/svcAppl/cancelSvcAppl.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_cancelServiceCallBack";
    
    //serviceRegNo 셋팅
    param.serviceRegNo = serviceRegNo;
    
    //로딩 호출
    gfn_setLoading(true, loadingTitle+" 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(url, param, callBackFunc);
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
        //alert("<spring:message code='success.alert.process' />");
    }
    
    fn_search();
}

<%-- 핀테크 서비스 저장 --%>
function fn_fintechSvcSave(){
	var moveUrl = "<c:url value='/usr/svcAppl/insertFintechSvc.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_fintechSvcSaveCallBack";
    
    var cnt = 0;
    var sptCustomerServiceProfileList = new Array();
    $.each($("input[name=appId]"), function(){
    	if($(this).is(":checked")){
    		//체크되어 있고, appId값이 있을 경우에만 처리한다.
    		if(util_chkReturn($.trim($(this).val()), "s") != ""){
    			cnt++;
    			var tmpData = new Object();
    			tmpData.appId = $(this).val();
    			sptCustomerServiceProfileList.push(tmpData);
    		}
    	}
    });
        
    if(cnt <= 0){
    	alert("<spring:message code='errors.required.select' arguments='핀테크 서비스'/>\n최소 1개 이상 선택 하셔야 합니다.");
    	return;
    }else{
    	//데이터 셋팅
    	if(sptCustomerServiceProfileList != null && sptCustomerServiceProfileList.length > 0){
    		param.sptCustomerServiceProfileList = sptCustomerServiceProfileList;
        }else{
        	alert("<spring:message code='fail.alert.process' />");
            return;
        }
    }
    
    //로딩 호출
    gfn_setLoading(true, "저장중입니다.");
            
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
}
function fn_fintechSvcSaveCallBack(data){
	//로그인 필요
	if(data.error == -1){
		fn_login();
	}
	
	if(data.result <= 0){
        alert("<spring:message code='fail.alert.regist' />");
    }else{
        //alert("<spring:message code='success.alert.regist' />");
    }
		
	fn_search();
}

<%-- 다음 --%>
function fn_accountSvcSave(){
	//가상계좌 선택 여부 체크
	var customerRealaccountNoFlag = false;
	$.each($("input[name=customerRealaccountNo]"), function(){
		if($(this).is(":checked")){
			customerRealaccountNoFlag = true;
		}
	});
	if(!customerRealaccountNoFlag){
		alert("STEP.2의 가상계좌를 선택 해 주세요.");
		return;
	}

	//로딩 호출
    gfn_setLoading(true, "정보제공동의 확인 중 입니다.");

	//1. 현재 정보가 갱신된 상태인지 확인한다. -> account정보, 약관정보 셋팅
	//2. 갱신여부를 체크하여 로직처리
	//2-1. 정보갱신이 안되어있으면 다음처리
	//2-2. 갱신상태 일 경우
	//2-2-1. 기존 약관정보를 가져온다.
	//2-2-2. 기존 약관정보와 비교하여, 재약관이 필요한지 여부를 셋팅하여 저장 데이터를 생성한다.
	//2-2-3. 저장정보를 통해 저장 후 2-1.과 동일한 처리를 한다.
		
	//1. 현재 정보가 갱신된 상태인지 확인한다. -> account정보, 약관정보 셋팅
	//현재 선택한 정보를 기준으로 저장 json객체를 생성한다.
	g_saveAccountList = new Array();         //account 정보
	g_savePubCompanyList = new Array();      //핀테크 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
	g_saveSubCompanyList = new Array();      //금투사 기업 약관 대상 정보 -> account 정보에 상세 정보가 있음
	$.each($("input[name=customerRealaccountNo]"), function(){
	    var key = $(this).val();  //key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
	    var appId = "";
	    var apiId = "";
	    var customerRealaccountNo = "";
	    
	    var data = new Object();
	    
	    var keyArr = key.split("_");
	    if(keyArr != null){
	    	appId = keyArr[0];
	        apiId = keyArr[1];
	        customerRealaccountNo = keyArr[2];
	    }else{
	    	//로딩 호출
	        gfn_setLoading(false);
	    	
	    	alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
	    	fn_search();
	    	return false;
	    }
	    
	    //조회된 현재 가상계좌 정보를 통해서 정보를 취득한다.
	    $.grep(g_resultSvcAccountList, function(n){
	    	if(
	            n.appId == appId &&
	            n.apiId == apiId &&
	            n.customerRealaccountNo == customerRealaccountNo
	    	){
	    		data = n;
	    	}
	    });
	    
	    if(util_chkReturn(data, "s") == "" || util_chkReturn(data.appId, "s") == "" ){
	    	//로딩 호출
            gfn_setLoading(false);
	    	
	    	alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
	    	fn_search();
            return false;
	    }
	    
	    //현재 값이 원본값과 다를경우 저장 대상
	    var useYn = $(this).is(":checked") ? "Y" : "N";
	    if(useYn != data.useYn){
	    	data.newUseYn = useYn;
	    	data.actionType = "";
	    	
	    	//원본 N -> Y : 등록대상(I) -> 등록대상은 계약도 신규로 따야함.
	    	if(useYn == "Y"){
	    		if(util_chkReturn(data.account_reg_no, "s") == ""){
	    			data.actionType = "I";	
	    		}else{
	    			data.actionType = "U";	
	    		}
	    		
	    	//원본 Y -> N : 변경대상(U) -> 삭제대상은 삭제만 처리하면 됨.
	    	}else if(data.useYn == "Y" && useYn == "N"){
	    		data.actionType = "U";
	    	}
	    		    	
	    	//저장 대상 list 셋팅
            g_saveAccountList.push(data);
	    }
	    
	    //계약정보 셋팅
	    if(useYn == "Y"){
            //계약정보 셋팅
            var subKey = data.subcompanyCodeId;
            var pubKey = subKey + "_" + data.pubcompanyCodeId;
            
            g_saveSubCompanyList.push(subKey);
            g_savePubCompanyList.push(pubKey);
        }
    });
	
	//unique
	g_saveSubCompanyList = gfn_arrayUnique(g_saveSubCompanyList);
	g_savePubCompanyList = gfn_arrayUnique(g_savePubCompanyList);

	//2. 갱신여부를 체크하여 로직처리
    //2-1. 정보갱신이 안되어있으면 다음처리
    if(util_chkReturn(g_saveAccountList, "s") == ""){
    	fn_moveNext();

   	//2-2. 갱신상태 일 경우
    //2-2-1. 기존 약관정보를 가져온다.
    }else{
    	var moveUrl = "<c:url value='/usr/svcAppl/selectFintechSvcTermsList.ajax'/>";
    	var param = new Object();
        var callBackFunc = "fn_searchTermsCallBack";

        //핀테크 서비스 선택값의 appId를 추출해서 가져온다.
        var apiIds = new Array();
	    $.each($("input[name=appId]"), function(){
	        if($(this).is(":checked")){
	            //체크되어 있고, appId값이 없을 경우에만 처리한다. -> 저장된 데이터는 appId를 셋팅안했음.
	            if(util_chkReturn($.trim($(this).val()), "s") == ""){
	                var appId = util_replaceAll($(this).attr("id"), "appId_", "");

	            	apiIds.push(appId);
	            }
	        }
	    });
	    param.searchAppId = apiIds;

        <%-- 공통 ajax 호출 --%>
        util_ajaxPageJson(moveUrl, param, callBackFunc);
    }
}
function fn_searchTermsCallBack(data){
	//로그인 필요
    if(data.error == -1){
    	fn_login();
    }
	
    var resultSvcTermsList = data.resultSvcTermsList;
    
    var accountProfile = new Array();
    var termsProfileList = new Array();
    var termsPubcompanyProfileList = new Array();
  
    //param 셋팅
    //일반회원 서비스 계좌 프로파일 정보 셋팅
    accountProfile = g_saveAccountList;
    
    //비교로직
    //1. resultSvcTermsList의 정보가 없을 경우에는 현재 정보로 insert처리
    //2. resultSvcTermsList의 정보가 있을 경우
    //3. 저장되어 있는 약관의 핀테크 기업 정보를 추출한다.
    //   - 경우의 수 
    //   1) 현재 선택된 핀테크 기업정보가 저장되어있는 정보에 없을 때 -> 신규  : 약관전체 등록 & spt_customer_service_profile에 terms_reg_no update 
    //   2) 현재 선택된 핀테크 기업정보가 있고, 금투사가 있을 때 -> none : 
    //   3) 현재 선택된 핀테크 기업정보가 있고, 금투사가 없을 때 -> 수정 : 가상계좌만 수정처리하고, 약관은 처리하지 않음.
    //   4) 저장되어 있는 핀테크 기업 정보가 있고, 현재 선택된 핀테크 기업정보가 없을 때 -> 삭제 : 약관 delete_date update & spt_customer_service_profile에 terms_reg_no update
    //
    
    if(util_chkReturn(resultSvcTermsList, "s") != ""){
    	var resultSubCompanyList = new Array();        //저장된 약관 핀테크 정보
    	var resultPubCompanyList = new Array();        //저장된 약관 금투사 정보
    	
    	//저장된 계약정보로 핀테크, 금투사 정보를 추출한다.
    	$.each(resultSvcTermsList, function(idx, data){
    		//약관정보 셋팅
            var subKey = data.subcompanyCodeId;
            var pubKey = subKey + "_" + data.pubcompanyCodeId;
            
            resultSubCompanyList.push(subKey);
            resultPubCompanyList.push(pubKey);
    	});
    	
    	//unique
        resultSubCompanyList = gfn_arrayUnique(resultSubCompanyList);
        resultPubCompanyList = gfn_arrayUnique(resultPubCompanyList);
        
        //현재 정보 기준으로 비교 시작
        $.each(g_saveSubCompanyList, function(subIdx, subcompanyCodeId){
        	var subData = new Object();
        	var subActionType = "U";  //I : 약관 변경 있음, U : 약관 변경 없음(금투사만 변경), D: 약관 삭제
        	
        	//현재 선택된 핀테크 기업정보가 있고, 금투사가 있을 때
        	if($.inArray(subcompanyCodeId, resultSubCompanyList) > -1){
                //금투사 정보 확인
                $.each(g_savePubCompanyList, function(pubIdx, pubcompanyCode){
                	var pubData = new Object();
                    var pubActionType = "U";  //I : 약관 변경 있음, U : 약관 변경 없음(금투사만 변경), D: 약관 삭제
                    
                    var keyArr = pubcompanyCode.split("_");       //val : subcompanyCodeId + "_" + pubcompanyCodeId
                    if(keyArr != null){
                    	pubData.subcompanyCodeId = keyArr[0];
                    	pubData.pubcompanyCodeId = keyArr[1];
                    }else{
                    	//로딩 호출
                        gfn_setLoading(false);
                    	
                        alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
                        return false;
                    }
                	
                    //핀테크 기업이 같을 때 비교
                    if(subcompanyCodeId == pubData.subcompanyCodeId){
	                	//정보가 있을 때 -> 핀테크 기업이 있고, 금투사 정보가 같은경우
	                	if($.inArray(pubcompanyCode, resultPubCompanyList) > -1){
	                		pubData.actionType = "U";
	                	//정보가 없을 때
	                	}else{
	                		pubData.actionType = "I";
	                		subActionType = "I";
	                	}
	                		                	
	                	termsPubcompanyProfileList.push(pubData);
                    }
                });
                
            //현재 선택된 핀테크 기업정보가 없을 때
        	}else{
        		subActionType = "I";
        		
        		//g_savePubCompanyList 기준으로 약관정보를 셋팅한다.
                $.each(g_savePubCompanyList, function(idx, pubcompanyCode){
                    var data = new Object();
                    var keyArr = pubcompanyCode.split("_");       //val : subcompanyCodeId + "_" + pubcompanyCodeId
                    if(keyArr != null){
                        data.subcompanyCodeId = keyArr[0];
                        data.pubcompanyCodeId = keyArr[1];
                        
                        if(subcompanyCodeId == data.subcompanyCodeId){
	                        data.actionType = "I";
	                        
	                        termsPubcompanyProfileList.push(data);
                        }
                    }else{
                    	//로딩 호출
                        gfn_setLoading(false);
                    	
                        alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
                        return false;
                    }
                });
        	}
        	
        	subData.subcompanyCodeId = subcompanyCodeId;
            subData.actionType = subActionType;
        	
        	termsProfileList.push(subData);
        });
        
        //저장된 정보를 기준으로 핀테크 기업이 있을경우
        $.each(resultSubCompanyList, function(idx, subcompanyCodeId){
        	var subData = new Object();
            var subActionType = "D";  //I : 약관 변경 있음, U : 약관 변경 없음(금투사만 변경), D: 약관 삭제
            
        	//금투사 정보가 빠진경우
        	if($.inArray(subcompanyCodeId, g_saveSubCompanyList) <= -1){
        		subData.subcompanyCodeId = subcompanyCodeId;
                subData.actionType = subActionType;
                
                termsProfileList.push(subData);
                termsPubcompanyProfileList.push(subData);  //의미없는값
        	}
        });
        
        $("#testView").html(resultSubCompanyList + "<br>" + resultPubCompanyList);

    }else{
    	//약관 신규 등록
    	//g_saveSubCompanyList 기준으로 약관정보를 셋팅한다.
        $.each(g_saveSubCompanyList, function(idx, val){
            var data = new Object();
            data.subcompanyCodeId = val;
            data.actionType = "I";
            
            termsProfileList.push(data);
        });
    	
        //g_savePubCompanyList 기준으로 약관정보를 셋팅한다.
        $.each(g_savePubCompanyList, function(idx, val){
            var data = new Object();
            var keyArr = val.split("_");       //val : subcompanyCodeId + "_" + pubcompanyCodeId
            if(keyArr != null){
                data.subcompanyCodeId = keyArr[0];
                data.pubcompanyCodeId = keyArr[1];
                data.actionType = "I";
                
                termsPubcompanyProfileList.push(data);
            }else{
            	//로딩 호출
                gfn_setLoading(false);
            	
                alert("가상계좌 정보 취득에 실패 했습니다.\n관리자에게 문의 해 주세요.");
                return false;
            }
        });
    }
    
    //저장 시작
    var moveUrl = "<c:url value='/usr/svcAppl/saveFintechSvcTerms.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_accountSvcSaveCallBack";
    
    //param 셋팅
    param.sptCustomerServiceAccountProfileVO = accountProfile;                          //일반회원 서비스 계좌 프로파일
    param.sptCustomerServiceTermsProfileVO = termsProfileList;                          //일반회원서비스 약관 프로파일 저장용
    param.sptCustomerServiceTermsPubcompanyProfileVO = termsPubcompanyProfileList;      //일반회원서비스 약관 금투사 프로파일 저장용

    //로딩 호출
    gfn_setLoading(true, "저장 중 입니다.");

    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
}
function fn_accountSvcSaveCallBack(data){
	//로딩 호출
    gfn_setLoading(false);

	//로그인 필요
    if(data.error == -1){
    	fn_login();
    }

    if(data.result <= 0){
        alert("<spring:message code='fail.alert.regist' />");
    }else{
        fn_moveNext();
    }
}

<%-- 다음단계 --%>
function fn_moveNext(){
    //약관동의 필요 없음
    if(g_svcTermsAuthCheck == 0){// 2017-03-02 추가
        //서비스약관동의
        if(g_svcTermsAuthFlag){
            if($("input:checkbox[id='useTerms']").is(":checked")) {
                //정보제공동의 페이지로 이동
                util_movePage("<c:url value='/usr/svcAppl/svcTermConsnt.do'/>");
            }else {
                //사용자가 체크버튼 해제시 약관 동의 페이지로 이동
                util_movePage("<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>");
            }
        }else{//서비스약관동의 + 정보제공동의서
            //정보제공동의서 체크시
            if($("input:checkbox[id='infoTerms']").is(":checked")) {
                if(!$("input:checkbox[id='useTerms']").is(":checked")) {
                    //사용자가 체크버튼 해제시 약관 동의 페이지로 이동
                    util_movePage("<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>");
                }else{
                    //서비스신청완료 페이지로 이동
                    util_movePage("<c:url value='/usr/svcAppl/svcApplComplt.do'/>");
                }
            }else{//정보제공동의서 미체크시
                if(!$("input:checkbox[id='useTerms']").is(":checked")) {
                    //사용자가 체크버튼 해제시 약관 동의 페이지로 이동
                    util_movePage("<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>");
                }else{
                    //사용자가 체크버튼 해제시 정보제공 동의 페이지로 이동
                    util_movePage("<c:url value='/usr/svcAppl/svcTermConsnt.do'/>");
                }
            }
        }
    }else{
        util_movePage("<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>");
    }
}

</script>
</head>
<body>
<form:form commandName="SvcApplVO" name="SvcApplVO" method="post">
<input type="hidden" name="searchCompanyCodeIdAllYn" id="searchCompanyCodeIdAllYn" value="N" />

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 신청</a></li>
                <li class="on">핀테크 서비스 신청</li>
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
                    <h3>핀테크 서비스 신청</h3>
                </div>
                                            
                <!-- con -->
                <div class="con">
                    
                    <!-- 2016-06-02 수정 -->
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>가상계좌 발급</div></li><!-- 지나간step -->
                            <li class="on"><div>핀테크 서비스 선택</div></li><!-- 현재step -->
                            <li><div>약관동의</div></li>
                            <li><div>정보제공동의</div></li>
                            <li class="last"><div>서비스 신청 완료</div></li>
                        </ul>
                    </div>
                    <!-- // 2016-06-02 수정 -->
                    
                    <p class="title_01">핀테크 서비스 선택</p>

                    <div class="title_step step1">
                        <p class="step">STEP.1</p>
                        <p>핀테크 서비스 선택</p>
                        <div class="sel_service">
                            <span class="service on">
								<a href="javascript:void(0);">서비스 전체</a>
							</span>
                        </div>
                    </div>
                    
                    <!-- tbtype_list -->
                    <table class="tbtype_list2 type_hover">
                        <caption>선택, 구분, 핀테크 서비스, 주요 서비스, 사용여부 정보 리스트</caption>
                        <colgroup>
                            <col style="width:8%;">
                            <col style="width:10%;">
                            <col style="width:20%;">
                            <col style="width:62%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">선택</th>
                                <th scope="col">구분</th>
                                <th scope="col">핀테크 서비스</th>
                                <th scope="col">주요 서비스</th>
                            </tr>
                        </thead>
                        <tbody id="fintechSvcList">
                        </tbody>
                    </table>
                    <!-- // tbtype_list -->
                    
                    <!-- btn_area -->
                    <div class="btn_area type2">
                       <div class="left">
                        <ul class="list_type01">
                            <li>[주요 서비스] 항목에 마우스를 오버하시면 금융투자회사별 연계 서비스를 확인하실 수 있습니다.</li>
                            <li>서비스 전체 : 발급된 가상계좌 정보를 기반으로 신청 가능한 핀테크 서비스 전체가 조회됩니다.</li>
                        </ul>
                       </div>
                       <div class="right">
                           <a href="javascript:void(0);" id="btnFintechSvcSave" class="btn_type7">핀테크 서비스 선택</a>
                       </div>
                    </div>
                    <!-- // btn_area -->

                    <div class="title_step step3">
                        <p class="step">STEP.2</p>
                        <p>가상계좌 선택<span>핀테크 서비스와 연결할 가상계좌를 체크해 주세요.</span></p>
                    </div>
                    
                    <!-- 2016-06-02 수정 -->
                    <!-- tbtype_list -->
                    <table class="tbtype_list2">
                        <caption>핀테크 기업, 핀테크 서비스, 금융투자회사, 가상계좌 리스트</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:20%;">
                            <col style="width:25%;">
                            <col style="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">핀테크 기업</th>
                                <th scope="col">핀테크 서비스</th>
                                <th scope="col">금융투자회사</th>
                                <th scope="col">가상계좌</th>
                            </tr>
                        </thead>
                        <tbody id="accountSvcList">
                        </tbody>
                    </table>
                    <!-- // tbtype_list -->
                    <!-- // 2016-06-02 수정 -->

                    <!-- // 2017-03-02 추가 -->
                    <c:if test='${fn:length(resultList) == 0}'>
                        <ul class="finish">
                            <li style="background: none;padding-left: 0px;"><input type="checkbox" name="useTerms" id="useTerms" checked> <label for='useTerms' class='chk_box'>선택된 금융투자회사에 대한 서비스 이용 약관 동의가 완료되어 해당 단계를 건너뜁니다.</label> </li>
                        </ul>
                    </c:if>

                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" class="btn_type2" id="btnBack">이전</a>
                        <a href="javascript:void(0);" class="btn_type1" id="btnAccountSvcSave">다음</a>
                    </div>
                    <!-- // btn_area -->
                    
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>        
    </section>
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
</div>
</form:form>
</body>
</html>