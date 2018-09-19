<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introFintechApp.jsp
 * @Description : [Intro > 핀테크 App 소개] 조회
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
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<c:if test="${isMobile eq 'true'}">
<!-- 반응형 페이지일 경우 추가 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- // 반응형 페이지일 경우 추가 -->
</c:if>
<c:choose>
    <%-- 모바일일 경우 --%>
    <c:when test="${isMobile eq 'true'}" >
        <c:set var="pageRowsize" value="5" />
        <c:set var="pageBlock" value="5" />
    </c:when>
    <c:otherwise>
        <c:set var="pageRowsize" value="10" />
        <c:set var="pageBlock" value="10" />
    </c:otherwise>
</c:choose>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
//화면 로드 처리
$(document).ready(function(){
	//조회
    $("#btnSearch").click(function(){
    	fn_search();
    });
	
	//카테고리 변경 시
	$("#searchAppCategory").change(function(){
		fn_search();
	});
	
	//조회
    fn_search($("#pageIndex").val());
});

/*******************************************
 * 기능 함수
 *******************************************/

<%-- 검색 --%>
function fn_search(pageIndex){
    //page
    if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
    $("#pageIndex").val(pageIndex);
    
    //로딩 호출
    gfn_setLoading(true);
                    
    //page setting  
    var url = "<c:url value='/cmm/intro/selectIntroFintechAppList.ajax'/>";
    var param = $("#IntroVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
    var resultAppList = data.resultAppList;                             //핀테크 서비스 목록
    var resultAppListTotalCount = data.resultAppListTotalCount;         //핀테크 서비스 목록 총개수
    
    var resultAppPubcompanyList = data.resultAppPubcompanyList;         //핀테크 서비스 금투사 목록
    
    $("#pagingNavi >").remove();
        
    //핀테크 서비스 선택 리스트 make
    fn_makeSvcList(resultAppList, resultAppPubcompanyList);
        
    //page common
    pageNavigator(
        $("#pageIndex").val(),
        resultAppListTotalCount,
        "fn_search",
        "${ pageRowsize }",
        "${ pageBlock }"
    );
    
    //로딩 호출
    gfn_setLoading(false);
}

<%-- 핀테크 서비스 선택 리스트 make --%>
function fn_makeSvcList(resultAppList, resultAppPubcompanyList){
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    var oldAppId = "";
    var apiFlag = false;
    if(util_chkReturn(resultAppList, "s") != ""){
        $.each(resultAppList, function(idx, list){
            //이미지 경로
            var appImgUrl = "<c:url value=""/>"+"/cmm/appImg/"+list.appId+".do";
           
            html += "<tr>";
            html += "<td class='m_hide'>"+list.appCategoryName+"</td>";
            html += "<td>";
            html += "<div class='app_large'>";
            
            //링크
            if(util_chkReturn(list.appDlUrl, "s") != ""){
            	html += "<a href='javascript:fn_moveAppLink(\""+list.appDlUrl+"\");'>";
            }
            
            html += "    <img id='appIconImg' src='"+appImgUrl+"' alt='"+list.appName+"'";
            html += "         onerror='this.src=\"<c:url value="/images/cmm/icon/icon_app_none.png"/>\"' ";            
            html += "    />";
            html += "    <p>"+list.appName+"</p>";
            
            //링크
            if(util_chkReturn(list.appDlUrl, "s") != ""){
                html += "</a>";
            }
            
            html += "</div>";
            html += "</td>";
            html += "<td class='left tooltip_wrap'>";
           	html += "   <div class='tooltip_area'>";
           	html += "       <textarea class='app_textarea' disabled>"+list.appDescription+"</textarea>";
           	
           	if(util_chkReturn(resultAppPubcompanyList, "s") != ""){
                $.each(resultAppPubcompanyList, function(companyIdx, companyList){
                    if(list.appId == companyList.appId){
                    	if(oldAppId != list.appId){
                    	    html += "       <a href='javascript:fn_svcAppl("+list.appId+");' class='btn_type7'>서비스 신청</a>";
                    	    
                    	    oldAppId = list.appId;
                    	    apiFlag = true;
                    	}
                    }
                });
            }
           	
           	if(apiFlag){
           		apiFlag = false;
           		
	           	html += "       <div class='tooltip'>";
	           	html += "           <dl class='con'>";
	           	html += "               <dt>금융투자회사별 연계 서비스</dt>";
	           	html += "               <dd>";
	           	html += "                   <table class='tbtype_tip'>";
	           	html += "                       <caption>금융투자회사, 연계 서비스 정보</caption>";
	           	html += "                       <colgroup>";
	           	html += "                           <col style='width:45%;'>";
	           	html += "                           <col style=''>";
	           	html += "                       </colgroup>";
	           	html += "                       <thead>";
	           	html += "                           <tr>";
	           	html += "                               <th scope='col'>금융투자회사</th>";
	           	html += "                               <th scope='col'>연계 서비스</th>";
	           	html += "                           </tr>";
	           	html += "                       </thead>";
	           	html += "                       <tbody>";
	           	
	           	var subHtml = "";
	            if(util_chkReturn(resultAppPubcompanyList, "s") != ""){
	                $.each(resultAppPubcompanyList, function(companyIdx, companyList){
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
	                html += "<td colspan='2'><div style='text-align:center;'>조회된 데이터가 없습니다.</div></td>";
	                html += "</tr>";
	            }else{
	                html += subHtml;
	            }
	           	
	           	html += "                       </tbody>";
	           	html += "                   </table>";
	           	html += "               </dd>";
	           	html += "           </dl>";
	           	html += "       </div>";
	           	
           	}
           	
           	html += "   </div>";
           	html += "</td>";
           	html += "</tr>"           	            
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='3'><div class='nodata'>조회된 데이터가 없습니다.</div></td>";
        html += "</tr>";
    }
    $("#list").append(html);
    
    if(util_chkReturn(resultAppList, "s") != ""){
        $.each(resultAppList, function(idx, list){
            //rowspan
            gfn_rowspanMerge("rowspan_pubcompany_"+list.appId);
        });
    }
}

<%-- 서비스 신청 --%>
function fn_svcAppl(appId){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
	    var url = "<c:url value='/usr/svcAppl/asumAcntIsu.do'/>";
	    var param = new Object();
	    param.paramMenuId = "06001";
        param.appId = appId;
	    gfn_loginNeedMove(url, param);
        return;
    </c:if>
    
    var objParam = new Object();
    objParam.appId = appId;
    util_movePage("<c:url value='/usr/svcAppl/asumAcntIsu.do'/>", objParam);
}

<%-- 앱 link 이동 --%>
function fn_moveAppLink(link){
	if(util_chkReturn(link, "s") != ""){
		if(link.indexOf("http") <= -1) link = "http://" + link;
		window.open(link, "_blank");
	}
}

</script>
</head>
<body>
<form:form commandName="IntroVO" name="IntroVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="${ pageRowsize }" /><!-- //목록사이즈 -->

<div class="wrap <c:if test="${isMobile eq 'true'}">mobile_wrap</c:if>"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">핀테크 서비스</a></li>
                <li class="on">App.소개</li>
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
                    <h3>App.소개</h3>
                    <!-- 2016-05-26 수정 -->
					<div class="sub_visual pm_show">                  
					    <img src="<c:url value='/images/spt/common/img_sub_visual01.jpg'/>" alt="">      
					    <div class="txt">
					        <div class="service">
					            <div>
					                <p>코스콤이 제공하는 오픈API를 활용하여 핀테크 기업들의 각종 정보가 더해진<br>
					                                    다양한 핀테크 서비스를 제공합니다.<br>
					                                    코스콤 오픈API플랫폼이 제공하는 최적화된 시스템의 핀테크 서비스를 경험하세요.</p>
					            </div>
					        </div>
					    </div>                     
					</div>  
                    <!-- // 2016-05-26 수정 -->
                </div>

                <!-- con -->
                <div class="con">

                    <!-- search_area -->
                    <div class="search_area">   
                        <span class="sel_box1 left m_hide"><!-- 2016-05-26 수정 -->
                            <select id="searchAppCategory" name="searchAppCategory" style="min-width:;" title="카테고리 선택">
                                <option value="">전체</option>
                                <c:forEach items="${appCategoryList}" var="appCategoryList" varStatus="status">
                                    <option value="${appCategoryList.system_code}">${appCategoryList.code_name_kor}</option>
                                </c:forEach>
                            </select>
                        </span>
                        
                        <div class="search_box right">
                            <span class="sel_box1">
                                <select id="searchCondition" name="searchCondition" style="min-width:;" title="검색조건 선택">
                                    <option value="all">전체</option>
                                    <option value="appName">핀테크 서비스</option>
                                    <option value="appDescription">주요 서비스</option>
                                </select>
                            </span>
                            <input type="text" id="searchKeyword" name="searchKeyword" style="min-width:170px;" title="검색어 입력"
                                   onkeydown="javascript:if(event.keyCode == 13) fn_search();"  
                            />
                            <input type="text" id="temp" style="display: none;">
                            <a href="javascript:void(0);" id="btnSearch" class="btn_search">검색</a>
                        </div>
                    </div>
                    <!-- // search_area -->
                    
                    <!-- tbtype_list -->
                    <table class="tbtype_list type_hover">
                        <caption>구분, 핀테크 서비스, 주요서비스, 서비스신청 정보 리스트</caption>
                        <colgroup>
                            <col style="width:12%;" class="m_hide"><!-- 2016-05-26 수정 -->
                            <col style="width:17%;">
                            <col style="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col" class="m_hide">구분</th><!-- 2016-05-26 수정 -->
                                <th scope="col">핀테크 서비스</th>
                                <th scope="col">주요서비스</th>
                            </tr>
                        </thead>
                        <tbody id="list">
                        </tbody>
                    </table>
                    <!-- // tbtype_list -->

                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination">
                    </div>
                    <!-- // pagination -->

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