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
	
    //서비스 추가 신청
    $("#btnServiceAdd").click(function(){
    	util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>?paramMenuId=06001");
    });
    
    //titleMsg
    var termsFinalYear = Math.ceil(<spring:message code='Globals.user.policy.terms.final' /> / 12);
    var termsYear = Math.ceil(<spring:message code='Globals.user.policy.terms.expire' /> / 12);
    
    var titleHtml = "<li>- 금융정보제공동의는 최대 유효기간 "+termsFinalYear+"년이며, 유효기간이 만료되기 전 재동의하셔야 <br>정상적인 서비스 이용이 가능합니다.</li>";
    titleHtml += "<li>- 서비스 해지 버튼을 클릭하시면 해당 핀테크 서비스 이용신청 해지 및 서비스 목록이 삭제가 됩니다.</li>";
    titleHtml += "<li>- 「금융실명거래 및 비밀보장에 관한 법률」 제4조의2 제1항 규정의 거래정보 등의 제공사실 통보를 <br>1년 단위로 포괄하여 통보합니다. </li>";
    
    $("#titleMsg").html(titleHtml);
    
    
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
	
    var resultList = data.resultList;    
    
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            var key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
            
            html += "<tr>";
            
            html += "<th class='rowspan_subcompany'>";
            if(list.termsAuthYn == "N"){
                //html += "<a href='javascript:fn_openTerms(\""+list.termsRegNoEncryption+"\", \"N\");' class='point04'>";
                html += list.subcompanyName;
                //html += "</a>";
            }else{
            	html += list.subcompanyName;	
            }
            
            html += "</th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+"'><strong>"+list.appName+"</strong></th>";
            html += "<th class='rowspan_subcompany_cancle' id='cancel_"+list.subcompanyCodeId+"'>"+list.subcompanyName+"</th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId+"'>"+list.pubcompanyName+"</th>";
            
            html += "<td class='left'>";
            html += "    <label for='"+key+"' class='chk_box'>"+ list.customerVtaccountNo +" / "+ list.customerVtaccountAlias +"</label>";
            html += "</td>";
            html += "<td class='rowspan_subcompany_terms agree_td' id='"+list.subcompanyCodeId+"'>"+list.subcompanyName+"</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='6'><div class='nodata'>";
        html += "신청하신 핀테크 서비스 내역이 없습니다";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#list").append(html);
    
    if(util_chkReturn(resultList, "s") != ""){
    	//핀테크 기업 companyCodeId array
    	var subcompanyCodeIdArr = new Array();
    	
	    //첫 컬럼 rowspan
	    gfn_rowspanMerge("rowspan_subcompany");
	    
	    //서비스 해지 컬럼 rowspan
        gfn_rowspanMerge("rowspan_subcompany_cancle");
        $(".rowspan_subcompany_cancle").html("");
	        
	    //두번째, 세번째 rowspan
	    var key2 = "";
	    var key3 = "";
	    if(util_chkReturn(resultList, "s") != ""){
	        $.each(resultList, function(idx, list){
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
	            
	            //companyCodeId + _ + termsAuthYn + _ + termsRegNo + _ + serviceRegNo + _ + termsAuthDateYn + _ + termsStartDate + _ + termsExpireDate
	            var key = list.subcompanyCodeId + "_";
	            key += list.termsAuthYn + "_";
	            //key += list.termsRegNo + "_";
	            key += list.termsRegNoEncryption + "_";
	            key += list.serviceRegNo + "_";
	            key += list.termsAuthDateYn + "_";
	            key += list.termsStartDate + "_";
	            key += list.termsExpireDate;
	            	            
	            subcompanyCodeIdArr.push(key);
	        });
	        
	        subcompanyCodeIdArr = gfn_arrayUnique(subcompanyCodeIdArr);
	    }
	    
	    //마지막 컬럼 rowspan
	    gfn_rowspanMerge("rowspan_subcompany_terms");
	    $(".rowspan_subcompany_terms").html("");
	    
	    //버튼 셋팅
	    if(util_chkReturn(subcompanyCodeIdArr, "s") != ""){
            $.each(subcompanyCodeIdArr, function(idx, val){
            	var cancelHtml = "";
            	var html = "";
            	
            	var companyCodeId = "";
            	var termsAuthYn = "";
            	var termsRegNo = "";
            	var serviceRegNo = "";
            	var termsAuthDateYn = "";
            	var termsStartDate = "";
            	var termsExpireDate = "";
            	            	
            	//key : companyCodeId + _ + termsAuthYn + _ + termsRegNo + _ + serviceRegNo + _ + termsAuthDateYn + _ + termsStartDate + _ + termsExpireDate
            	var keyArr = val.split("_"); 
                if(keyArr != null){
                	companyCodeId = keyArr[0];
                	termsAuthYn = keyArr[1];
                	termsRegNo = keyArr[2];
                	serviceRegNo = keyArr[3];
                	termsAuthDateYn = keyArr[4];
                	termsStartDate = keyArr[5];
                	termsExpireDate = keyArr[6];
                }
            	
            	//서비스 해지
            	cancelHtml += "<a href='javascript:fn_cancelService(\""+serviceRegNo+"\");' class='btn_type8 on'>서비스 해지</a>";
            	$("#cancel_" + companyCodeId).html(cancelHtml);
            	
            	//정보제공동의
            	html += "<div class='agree_td'>";
            	if(termsAuthYn == "Y"){
            		html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"Y\");' class='btn_type6'>정보제공동의</a>";
            		
            	//동의서 보기
            	}else{
            		//동의서 기간 이내
                    // 20170406 동의서 유지기간 변경 건으로 인한 수정
                    html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"N\");' class='point04'>";
                    html += termsStartDate + "~<br>" + termsExpireDate;
                    html += "</a>";

                    if(termsAuthDateYn == "Y"){

            			html += "<a href='javascript:fn_cancelTerms(\""+termsRegNo+"\");' class='btn_type4'>동의서 폐기</a>";
            		}else{
            			// [s] termsAuthYn 값 원복 2017-04-07 한유진
            			html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"R\");' class='btn_type6'>동의서 재동의</a>";
            			// [e] termsAuthYn 값 원복 2017-04-07 한유진
            			
            			html += "<a href='javascript:fn_cancelTerms(\""+termsRegNo+"\");' class='btn_type4'>동의서 폐기</a>";
            		}
            	}
            	html += "</div>";
            	
                $("#" + companyCodeId).html(html);
            });
	    }
    }	    
    
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
    
    var resultList = data.resultList;    
    
    var html = "";

    //목록 셋팅
    $("#discardList >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            var key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
            
            html += "<tr>";
            
            html += "<th class='discard_rowspan_subcompany' id='discard_rowspan_subcompany_"+list.serviceRegNo+"'>";
            
            /*
            html += "<a href='javascript:fn_openTerms(\""+list.termsRegNoEncryption+"\", \"N\");' class='point04'>";
            html += list.subcompanyName;
            html += "</a>";
            */
            
            html += list.serviceRegNo;
            
            html += "</th>";
            html += "<th class='discard_rowspan_"+list.serviceRegNo+list.subcompanyCodeId+list.appId+"'><strong>"+list.appName+"</strong></th>";
            html += "<th class='discard_rowspan_"+list.serviceRegNo+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId+"'>"+list.pubcompanyName+"</th>";
            
            html += "<td class='left'>";
            html += "    <label for='"+key+"' class='chk_box'>"+ list.customerVtaccountNo +" / "+ list.customerVtaccountAlias +"</label>";
            html += "</td>";
            html += "<td class='discard_rowspan_subcompany_terms_date agree_td' id='discard_date_"+list.serviceRegNo+list.subcompanyCodeId+"'>"+list.serviceRegNo+"</td>";
            html += "<td class='discard_rowspan_subcompany_terms agree_td' id='discard_"+list.serviceRegNo+list.subcompanyCodeId+"'>"+list.serviceRegNo+"</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='6'><div class='nodata'>";
        html += "폐기한 정보제공동의 내역이 없습니다";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#discardList").append(html);
    
    
    if(util_chkReturn(resultList, "s") != ""){
        //핀테크 기업 companyCodeId array
        var subcompanyCodeIdArr = new Array();
        
        //첫 컬럼 rowspan
        gfn_rowspanMerge("discard_rowspan_subcompany");
        $(".discard_rowspan_subcompany").html("");
        
        //두번째, 세번째 rowspan
        var key2 = "";
        var key3 = "";
        if(util_chkReturn(resultList, "s") != ""){
            $.each(resultList, function(idx, list){
                var key2Data = list.serviceRegNo+list.subcompanyCodeId+list.appId;
                var key3Data = list.serviceRegNo+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId;
                
                //rowspan
                if(key2 == ""){
                    key2 = key2Data;
                    gfn_rowspanMerge("discard_rowspan_"+key2);
                }else{
                    if(key2 != key2Data){
                        key2 = key2Data;
                        gfn_rowspanMerge("discard_rowspan_"+key2);
                    }
                }
                
                if(key3 == ""){
                    key3 = key3Data;
                    gfn_rowspanMerge("discard_rowspan_"+key3);
                }else{
                    if(key3 != key3Data){
                        key3 = key3Data;
                        gfn_rowspanMerge("discard_rowspan_"+key3);
                    }
                }
                
                //serviceRegNo + _ + subcompanyCodeId + _ + subcompanyCodeName + _ + termsAuthYn + _ + termsRegNoEncryption + _ + serviceRegNo + _ + termsAuthDateYn + _ + termsStartDate + _ + termsExpireDate + _ + termsDiscardDate
                var key = list.serviceRegNo + "_";
                key += list.subcompanyCodeId + "_";
                key += list.subcompanyName + "_";
                key += list.termsAuthYn + "_";
                //key += list.termsRegNo + "_";
                key += list.termsRegNoEncryption + "_";
                key += list.serviceRegNo + "_";
                key += list.termsAuthDateYn + "_";
                key += list.termsStartDate + "_";
                key += list.termsExpireDate + "_";
                key += list.termsDiscardDate;
                                
                subcompanyCodeIdArr.push(key);
            });
            
            subcompanyCodeIdArr = gfn_arrayUnique(subcompanyCodeIdArr);
        }
        
        //폐기일시 rowspan
        gfn_rowspanMerge("discard_rowspan_subcompany_terms_date");
        $(".discard_rowspan_subcompany_terms_date").html("");
        
        //마지막 컬럼 rowspan
        gfn_rowspanMerge("discard_rowspan_subcompany_terms");
        $(".discard_rowspan_subcompany_terms").html("");
        
        //버튼 셋팅
        if(util_chkReturn(subcompanyCodeIdArr, "s") != ""){
            $.each(subcompanyCodeIdArr, function(idx, val){
                var cancelHtml = "";
                var html = "";
                
                var serviceRegNo = "";
                var companyCodeId = "";
                var companyCodeName = "";
                var termsAuthYn = "";
                var termsRegNo = "";
                var serviceRegNo = "";
                var termsAuthDateYn = "";
                var termsStartDate = "";
                var termsExpireDate = "";
                var termsDiscardDate = "";
                                
              //serviceRegNo + _ + subcompanyCodeId + _ + subcompanyCodeName + _ + termsAuthYn + _ + termsRegNoEncryption + _ + serviceRegNo + _ + termsAuthDateYn + _ + termsStartDate + _ + termsExpireDate + _ + termsDiscardDate
                var keyArr = val.split("_"); 
                if(keyArr != null){
                	serviceRegNo = keyArr[0];
                    companyCodeId = keyArr[1];
                    companyCodeName = keyArr[2];
                    termsAuthYn = keyArr[3];
                    termsRegNo = keyArr[4];
                    serviceRegNo = keyArr[5];
                    termsAuthDateYn = keyArr[6];
                    termsStartDate = keyArr[7];
                    termsExpireDate = keyArr[8];
                    termsDiscardDate = keyArr[9]; 
                }
                
                //핀테크 기업
                html = "";
                //html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"N\");' class='point04'>";
                html += companyCodeName;
                //html += "</a>";
                
                $("#discard_rowspan_subcompany_" + serviceRegNo).html(html);
                
                //폐기일시
                html = termsDiscardDate;
                
                $("#discard_date_" + serviceRegNo + companyCodeId).html(html);
                                
                //정보제공동의
                html = "";
                html += "<div class='agree_td'>";
                
                html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"N\");' class='point04'>";
                html += termsStartDate + "~<br>" + termsExpireDate;
                html += "</a>";
                
                html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"N\");' class='btn_type6 on'>동의서 보기</a>";
                
                html += "</div>";
                
                $("#discard_" + serviceRegNo + companyCodeId).html(html);
            });
        }
    }       
    
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
function fn_cancelTerms(termsRegNo){
	var msg = "해당 동의서를 폐기하시면,\n";
	msg += "연결된 핀테크 서비스도 함께 삭제 될 수 있습니다.\n\n";
	msg += "동의서를 폐기 하시겠습니까?";
	if(!confirm(msg)){
        return;
    }
	
    $("#termsRegNo").val(termsRegNo);
    
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
    
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.process' />");
    }else{
        alert("<spring:message code='success.alert.process' />");
    }
    
    fn_search();
}

<%-- 약관 팝업 --%>
function fn_openTerms(termsRegNo, termsAuthYn){
	var url = "<c:url value='/usr/svcAppl/svcTermConsntPopup.do'/>";
	var objOption = new Object();
	objOption.type = 'modal';
	objOption.width = '900';
    objOption.height = '700';
		
	var objParam = new Object();
	objParam.termsRegNo = termsRegNo;
	objParam.termsAuthYn = termsAuthYn;
	objParam.callBakFunc = "fn_openTermsCallBack";
	
	util_modalPage(url, objOption, objParam);
}
function fn_openTermsCallBack(data){
	//result : data.result == 0 성공, else 실패
	if(data.result == 0){
        alert("<spring:message code='fail.alert.process' />");
    }else{
        alert("<spring:message code='success.alert.process' />");
    }
    
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
                <li class="on">신청 서비스</li>
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
                    <h3>신청 서비스</h3>
                    <div class="sub_visual">
                        <img src="<c:url value='/images/spt/mypage/img_sub_visual01.jpg'/>" alt="">
                        <div class="txt">
                            <div class="mypage">
                                <div>
                                    <ul id="titleMsg">
                                    </ul>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- con -->
                <div class="con">
                    <!-- tab_menu -->
                    <div class="tab_menu">
                        <ul>
                            <li class="on"><a href="javascript:void(0);" id="tab_01">신청 서비스</a></li>
                            <li><a href="javascript:void(0);" id="tab_02">폐기된 정보제공동의</a></li>
                        </ul>
                    </div>
                    <!-- // tab_menu -->
                    
                    <div class="tab_cont" id="tab01">
	                    <!-- tbtype_list2 type4 -->
	                    <table class="tbtype_list2 type4">
	                        <caption>핀테크 기업, 핀테크 서비스, 금융투자회사, 가상계좌, 정보제공동의</caption>
	                        <colgroup>
	                            <col style="width:10%;">
	                            <col style="width:14%;">
	                            <col style="width:10%;">
	                            <col style="width:15%;">
	                            <col style="">
	                            <col style="width:14%;">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th scope="col">핀테크 기업</th>
	                                <th scope="col">핀테크 서비스</th>
	                                <th scope="col">서비스 해지</th>
	                                <th scope="col">금융투자회사</th>
	                                <th scope="col">가상계좌</th>
	                                <th scope="col">정보제공동의</th>
	                            </tr>
	                        </thead>
	                        <tbody id="list">
	                        </tbody>
	                    </table>
	                    <!-- // tbtype_list2 type4 -->
	
	                    <div class="btn_area type2">
	                       <div class="right">
	                           <a href="javascript:void(0);" class="btn_type7" id="btnServiceAdd">서비스 추가 신청</a>
	                       </div>
	                    </div>
                    </div>	                    
                    <div class="tab_cont" id="tab02">
                        <!-- tbtype_list2 type4 -->
                        <table class="tbtype_list2 type4">
                            <caption>핀테크 기업, 핀테크 서비스, 금융투자회사, 가상계좌, 정보제공동의</caption>
                            <colgroup>
                                <col style="width:10%;">
                                <col style="width:14%;">
                                <col style="width:15%;">
                                <col style="">
                                <col style="width:12%;">
                                <col style="width:14%;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">핀테크 기업</th>
                                    <th scope="col">핀테크 서비스</th>
                                    <th scope="col">금융투자회사</th>
                                    <th scope="col">가상계좌</th>
                                    <th scope="col">폐기 일시</th>
                                    <th scope="col">정보제공동의</th>
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
</body>
</html>