<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : introSvcApi.jsp
 * @Description : [Intro > API 소개] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.23  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.23
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
//화면 로드 처리
$(document).ready(function(){
	
	//콤보 변경 시 조회
	$("#searchApiCategory, #searchCompanyCodeId").change(function(){
		fn_search();
	});
	
	//조회
    $("#btnSearch").click(function(){
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
    var url = "<c:url value='/cmm/intro/selectIntroSvcApiList.ajax'/>";
    var param = $("#IntroVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
    var resultList = data.resultList;
    var resultListTotalCount = data.resultListTotalCount;
    
    $("#pagingNavi >").remove();
        
    //리스트 make
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
        	
        	html += "<li>";
       		html += "<dl>";
       		html += "<dt>";
       		html += "    <strong>["+list.apiCategoryName+"] "+list.apiTitle+"</strong>";
       		html += "    <span>"+list.createDate+" 최종 업데이트</span>";
       		html += "</dt>";
       		html += "<dd>";
       		html += "    <textarea class='txt' disabled='disabled'>";
       		html += list.apiDescription;
       		html += "    </textarea>";
       		html += "    <div class='ver'>";
       		html += "        <p>- 정보제공자 : <span class='point03'>"+list.companyNameKorAlias+"</span></p>";
       		//html += "        <p>- 버전 : <span class='point03'>v.1.0</span></p>";
       		html += "    </div>";
       		html += "</dd>";
       		html += "</dl>";
       		html += "</li>";        
        });
    }else{
        html += "<li>";
        html += "<div class='nodata'>조회된 데이터가 없습니다.</div>";
        html += "</li>";
    }
    
    $("#list").append(html);
            
    //page common
    pageNavigator(
        $("#pageIndex").val(),
        resultListTotalCount
    );
    
    //로딩 호출
    gfn_setLoading(false);
}

</script>
</head>
<body>
<form:form commandName="IntroVO" name="IntroVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

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
                <li><a href="javascript:void(0);">서비스 소개</a></li>
                <li class="on">API 소개</li>
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
                    <h3>API 소개</h3>
                    <div class="sub_visual">
                        <img src="<c:url value='/images/cpt/common/img_sub_visual02.jpg'/>" alt="">
                        <div class="txt">
                            <div class="service">
                                <div>
                                    <p>
                                        코스콤과 제휴사가 보유한 다양한 자원과 기능을 활용하여 서비스를 개발할 수 있도록<br>
                                        오픈 API를 제공하고 있습니다. 
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>              
                </div>

                <!-- con -->
                <div class="con">

                    <!-- search_area -->
                    <div class="search_area">   
                        <span class="sel_box1 left">
                            <select id="searchApiCategory" name="searchApiCategory" style="min-width:;" title="정렬기준 선택">
                                <option value="">전체</option>
                                <c:forEach items="${apiCategoryList}" var="apiCategoryList" varStatus="status">
                                    <option value="${apiCategoryList.system_code}">${apiCategoryList.code_name_kor}</option>
                                </c:forEach>
                            </select>
                        </span>
                        <span class="sel_box1 left">
                            <select id="searchCompanyCodeId" name="searchCompanyCodeId" style="min-width:;" title="정보제공자 선택">
                                <option value="">전체</option>
                                <c:forEach items="${companyCodeList}" var="companyCodeList" varStatus="status">
                                    <option value="${companyCodeList.companyCodeId}">${companyCodeList.companyNameKorAlias}</option>
                                </c:forEach>
                            </select>
                        </span>
                        
                        <div class="search_box right">
                            <span class="sel_box1">
                                <select id="searchCondition" name="searchCondition" style="min-width:;" title="검색조건 선택">
                                    <option value="all">전체</option>
                                    <option value="apiName">API명</option>
                                    <option value="apiDescription">내용</option>
                                </select>
                            </span>
                            <input type="text" id="searchKeyword" name="searchKeyword" style="min-width:170px;" title="검색어 입력"
                                   onkeydown="javascript:if(event.keyCode == 13) fn_search();"   
                            >
                            <a href="javascript:void(0);" id="btnSearch" class="btn_search">검색</a>
                            
                            <%-- 자동 submit을 막기 위한 input --%>
                            <input type="text" id="dump" name="dump" style="width: 0px;display: none;">
                        </div>
                    </div>
                    <!-- // search_area -->
                    
                    <!-- api_list -->
                    <div class="api_list">
                        <ul id="list">
                        </ul>
                    </div>
                    <!-- // api_list -->

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