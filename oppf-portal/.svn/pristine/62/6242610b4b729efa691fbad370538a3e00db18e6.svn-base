<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : svcTermConsnt.jsp
 * @Description : [핀테크서비스신청:정보제공동의]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.10  신동진        최초  생성
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
var g_termsAuthYn = "N";
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/usr/svcAppl/svcTermConsnt.do'/>";
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
	
	
	//다음
	$("#btnSave").click(function(){
		if(g_termsAuthYn == "Y"){
			alert("정보제공동의를 모두 동의 하셔야 합니다.");
			return;
		}else{
			util_movePage("<c:url value='/usr/svcAppl/svcApplComplt.do'/>");	
		}
    });
	
	//이전
	$("#btnBack").click(function(){
		util_movePage("<c:url value='/usr/svcAppl/svcCompanyTermsConsnt.do'/>");
    });
    
    //조회
    fn_search();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 검색 --%>
function fn_search(){
    //page setting  
    var url = "<c:url value='/usr/svcAppl/selectSvcTermConsntList.ajax'/>";
    var param = $("#SvcApplVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    //global 값 셋팅
    g_termsAuthYn = "N";
    
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
            
            html += "<th class='rowspan_subcompany'>"+list.subcompanyName+"</th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+"'><strong>"+list.appName+"</strong></th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId+"'>"+list.pubcompanyName+"</th>";
            
            html += "<td class='left'>";
            html += "    <label for='"+key+"' class='chk_box'>"+ list.customerVtaccountNo +" / "+ list.customerVtaccountAlias +"</label>";
            html += "</td>";
            html += "<td class='rowspan_subcompany_terms' id='"+list.subcompanyCodeId+"'>"+list.subcompanyName+"</td>";
            html += "</tr>";
            
            //한건이라도 정보제공동의 필요 일경우
            if(list.termsAuthYn == "Y") g_termsAuthYn = "Y";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='5'><div class='nodata'>";
        html += "조회된 데이터가 없습니다.<br><br><strong class='point04'>핀테크 서비스 선택</strong>으로 이동하여  <strong class='point04'>핀테크 서비스 선택 후 가상계좌를 선택</strong> 해 주세요.";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#list").append(html);
    
    if(util_chkReturn(resultList, "s") != ""){
    	//핀테크 기업 companyCodeId array
    	var subcompanyCodeIdArr = new Array();
    	
	    //첫 컬럼 rowspan
	    gfn_rowspanMerge("rowspan_subcompany");
	        
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
	            
	            //companyCodeId + _ + termsAuthYn + _ + termsRegNo
// 	            var key = list.subcompanyCodeId + "_" + list.termsAuthYn + "_" + list.termsRegNo;
	            var key = list.subcompanyCodeId + "_" + list.termsAuthYn + "_" + list.termsRegNoEncryption;
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
            	var html = "";
            	
            	var companyCodeId = "";
            	var termsAuthYn = "";
            	var termsRegNo = "";
            	            	
            	var keyArr = val.split("_");       //val : companyCodeId + _ + termsAuthYn + _ + termsRegNo
                if(keyArr != null){
                	companyCodeId = keyArr[0];
                	termsAuthYn = keyArr[1];
                	termsRegNo = keyArr[2];
                }
            	           	
            	//정보제공동의
            	if(termsAuthYn == "Y"){
            		html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"Y\");' class='btn_type6 off'>정보제공동의</a>";
            	//동의서 보기
            	}else{
            		html += "<a href='javascript:fn_openTerms(\""+termsRegNo+"\", \"N\");' class='btn_type6 on'>동의서 보기</a>";
            	}
            	
                $("#" + companyCodeId).html(html);
            });
	    }
    }
    
    //로딩 호출
    gfn_setLoading(false);
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
function fn_openTermsCallBack(rsCd,rsMsg){
    //result : data.result == 00 성공, else 실패
    if(rsCd == '00'){
        alert("<spring:message code='success.alert.process' />");
    }else{
    	
    	if(util_chkReturn(rsMsg, "s") != ""){
    		alert(rsMsg+'\n['+rsCd+']');
    	}
//         alert("<spring:message code='success.alert.process' />");
    }
    
    fn_search();
}

</script>
</head>
<body>
<form:form commandName="SvcApplVO" name="SvcApplVO" method="post">
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
                    
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>가상계좌 발급</div></li><!-- 지나간step -->
                            <li class="pass"><div>핀테크 서비스 선택</div></li><!-- 지나간step -->
                            <li class="pass"><div>약관동의</div></li><!-- 지나간step -->
                            <li class="on"><div>정보제공동의</div></li><!-- 현재step -->
                            <li class="last"><div>서비스 신청 완료</div></li>
                        </ul>
                    </div>
                    
                    <p class="title_01">핀테크 서비스 정보제공동의</p>

                    <!-- tbtype_list -->
                    <table class="tbtype_list2">
                        <caption>번호, 핀테크 기업, 핀테크 서비스, 금융투자회사, 가상계좌, 정보제공동의 리스트</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:15%;">
                            <col style="width:20%;">
                            <col style="">
                            <col style="width:15%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">핀테크 기업</th>
                                <th scope="col">핀테크 서비스</th>
                                <th scope="col">금융투자회사</th>
                                <th scope="col">가상계좌</th>
                                <th scope="col">정보제공동의</th>
                            </tr>
                        </thead>
                        <tbody id="list">
                        </tbody>
                    </table>
                    <!-- // tbtype_list -->
                    
                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" class="btn_type2" id="btnBack">이전</a>
                        <a href="javascript:void(0);" class="btn_type1" id="btnSave">다음</a>
                    </div>
                    <!-- // btn_area -->
                    
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