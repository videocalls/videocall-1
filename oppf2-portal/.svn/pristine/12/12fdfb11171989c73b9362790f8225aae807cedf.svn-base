<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : faqList.jsp
 * @Description : [FAQ목록:목록] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.04  유제량        최초  생성
 * </pre>
 *
 * @author 포털 유제량 
 * @since 2016.05.04
 * @version 1.0
 * @see
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<c:if test="${isMobile eq 'true'}">
<!-- 반응형 페이지일 경우 추가 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- // 반응형 페이지일 경우 추가 -->
</c:if>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/

/* [페이징]처리 함수 */
function fn_paging(page){
    
    var pageStart = 0;
    var pageIndex = page;
    
    var pageRowsize = $("#pageRowsize").val();
    
    if(pageIndex == 1){
        pageStart = 0;
    }else{
        pageStart = pageRowsize * ( pageIndex -1 );
    }
    
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    fn_ajaxCall_getList();
} 



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* [FAQ목록]ajax call 함수 */
function fn_ajaxCall_getList(){
    
    if( /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/.test($("#txtSearchKeyword").val())){
    }else{
    	if(util_chkReturn($("#txtSearchKeyword").val(), "s") == ""){
    	}else{
		    alert('검색어안에 사용할 수 없는 문자가 있습니다.');
		    $("#txtSearchKeyword").focus();
	        return false;
    	}
    }
    
    var url = "<c:url value='/cmm/faq/selectFaqList.ajax'/>";
    var reqData = {
       "pageRowsize"   : $("#pageRowsize").val()
      ,"pageBlocksize" : $("#pageBlocksize").val()
      ,"pageIndex"     : $("#pageIndex").val()
      ,"pageStart"     : $("#pageStart").val()
      ,"listOrder"     : $("#listOrder").val()
      ,"searchCondition" : $("#sltSearchCondition :selected").val()
      ,"searchKeyword"   : $("#txtSearchKeyword").val()
      ,"searchKind"      : $("#searchKind").val()
    };
    
    //로딩 호출
    gfn_setLoading(true);
    
    util_ajaxPage(url, reqData, "fn_ajaxCallback_getList");
}

/* [FAQ목록]ajax callback 함수 */
function fn_ajaxCallback_getList(data){
    var rsListTotCnt = data.resultListTotalCount;
    var rsList = data.resultList;
    var apData="";
    
    $("#rsList").children().remove();    
    $("#pagingNavi >").remove();
    if (util_chkReturn(rsListTotCnt, "s") == "" || util_chkReturn(rsList, "s") == ""){
    	apData = '<dt>'       
            //+        '<td colspan="5"><div class="nodata">등록된 내용이 없습니다.</div></td>'
            +        '<div class="nodata">등록된 내용이 없습니다.</div>'
            +    '</dt>';
        $("#rsList").append(apData);
        
        //로딩 호출
        gfn_setLoading(false);
        
        return false;
    }
    
    $("#spanRsListTotCnt").text(util_setCommas(rsListTotCnt));
    
    /* 페이징 설정 변수 */
    var pageIndex = Number(data.paramVO.pageIndex);
    var pageStart = Number(data.paramVO.pageStart);
    //var pageRowsize = Number(data.paramVO.pageRowsize); //공통VO에서는 pageRowsize가 10으로 셋팅 되어있음. 
    var pageRowsize = Number(15);
    
    
    $.each(rsList, function(idx,data){
        var iNo = rsListTotCnt - ( (pageIndex - 1) * pageRowsize + (idx+1) )+1;
        
        apData += '<dt><a href="javascript:void(0);"><span>Q. </span>'
            +        data.faqTitle
        	+	  '</a></dt>'
            +     '<dd>'
            +     '<span>A. </span>'
            +        util_setHtmlParsing(data.faqContent)
            +     '</dd>';
    });
    
    $("#rsList").append(apData);
    
    /* 페이징 설정 START */
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 START */
    var searchCondition = data.paramVO.searchCondition;
    $("#searchCondition").val(searchCondition);
    $("#sltSearchCondition").val(searchCondition);
    
    var searchKeyword = data.paramVO.searchKeyword;
    $("#searchKeyword").val(searchKeyword);
    $("#txtSearchKeyword").val(searchKeyword);
    
    var searchKind = data.paramVO.searchKind;
    $("#searchKind").val(searchKind);
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 END */
    
    $("#pageTotalcount").val(rsListTotCnt);
    
    pageNavigator(
      $("#pageIndex").val()
     ,$("#pageTotalcount").val()
     ,"fn_paging"
     ,$("#pageRowsize").val()
     ,$("#pageBlocksize").val()
    );
    
    // accordion_list
    if($(".accordion_list").length > 0){
        $(".accordion_list dd").hide();
        $(".accordion_list dt").click(function(){
            
            if( $(this).next().is(':hidden') ) {
                $(".accordion_list dt").removeClass('active').next().slideUp("fast");
                $(this).addClass("active");
                $(this).next().slideDown("fast");
            }else{
                $(this).removeClass("active");
                $(".accordion_list dd").slideUp("fast");
            }
            return false;
        });
    }
    
    /* 페이징 설정 START */
    //로딩 호출
    gfn_setLoading(false);  
}

/*******************************************
 * 이벤트 함수
 *******************************************/

/* 목록정렬함수 */
function fn_searchOrderPaging(colName) {
     if(colName){
         util_setListOrderParam(colName);
     }
     fn_ajaxCall_getList();
}

/* [FAQ조회]onload 호출 */
function fn_onload_searchList(){
    fn_ajaxCall_getList(); //[FAQ목록]ajax call 함수호출
}

/* [FAQ조회]호출되는 함수 */
function fn_searchList(){
    $("#pageIndex").val("1");
    $("#pageStart").val("0");
    fn_ajaxCall_getList(); //[FAQ목록]ajax call 함수호출
}

/* 이동함수 */
function fn_moveList(){   
    var objParam = new Object();
    objParam.customer_id     = $("#userId").val();        
    util_movePage("<c:url value='/spt/cmm/mainView.do'/>",objParam);
} 

//화면 로드 처리
$(document).ready(function(){
	
    /* [검색] 버튼 클릭 시 호출 */
    $("#btnSearch").click(function(){
        fn_searchList();        
    });
    
    /* [검색키워드] keydown 시 호출 */
    $("#txtSearchKeyword").bind("keydown", function(key){
        if(key.keyCode == 13){
            //fn_searchList();
        }
    });
    
    /* [e-mail : openapimaster@koscom.com] 클릭 시 호출 */
    $("#btnEmailTo").click(function(){
    	fn_moveList();
    });
    
    fn_onload_searchList();
    
});
</script>
</head>
<body>

<input type="hidden" name="pageTotalcount"  id="pageTotalcount"  value="0" /><!-- //총갯수 -->
<input type="hidden" name="pageRowsize"     id="pageRowsize"     value="15" /><!-- //목록사이즈 -->
<input type="hidden" name="pageBlocksize"   id="pageBlocksize"   value="10" /><!-- //페이징블록사이즈 -->
<input type="hidden" name="listOrder"       id="listOrder"       value="a.faq_order, a.create_date desc, a.faq_title" /><!-- //목록정렬 -->
<input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /> <!-- //검색조건 -->
<input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /> <!-- //검색Keyword -->

<%-- 2016.09.09 이준형과장 요청 - 미 로그인 시에도 노출 --%>
<%--
<input type="hidden" name="searchKind"      id="searchKind"      value="<c:if test="${not empty LoginVO  }">${SYSTEM_KIND}</c:if>" /> <!-- //검색조건:FAQ 노출 -->
--%>
<input type="hidden" name="searchKind"      id="searchKind"      value="${SYSTEM_KIND}"> <!-- //검색조건:FAQ 노출 -->
<input type="hidden" name="userId"          id="userId"          value="test1234" /><!-- //userId -->
                                
<div class="wrap <c:if test="${isMobile eq 'true'}">mobile_wrap</c:if>"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->
    
    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>
    
    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 지원</a></li>
                <li class="on">자주 묻는 질문</li>
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
                    <h3>자주 묻는 질문</h3>
                    <div class="sub_visual">
                        <img src="<c:url value="/images/spt/customer/img_sub_visual01.jpg"/>" alt="">
                        <div class="txt">
                            <div class="faq">
                                <div>
                                    <p class="txt1">궁금한 내용을 빠르고 쉽게 찾을 수 있도록 자주 하는 질문을 모았습니다.<br>
                                                                        원하는 답변을 찾을 수 없는 경우 이메일을 이용해 주시기 바랍니다.</p>
                                    <p class="txt2"><span><spring:message code='Globals.domain.${ SYSTEM_KIND }.name' /> 관련 문의</span>&nbsp;&nbsp;<img src="<c:url value="/images/spt/customer/icon_mail.png"/>" alt=""> e-mail : <a href="mailto:<spring:message code='Globals.manager.email.${ SYSTEM_KIND }' />"><spring:message code='Globals.manager.email.${ SYSTEM_KIND }' /></a></p>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- con -->
                <div class="con">

                    <!-- search_area -->
                    <div class="search_area">
                        <div class="search_box right">
                            <span class="sel_box1">
                                <select name="sltSearchCondition" id="sltSearchCondition" style="min-width:;" title="검색조건 선택">
                                    <option value="sjcn">전체</option>
                                    <option value="sj">제목</option>
                                    <option value="cn">내용</option>
                                </select>                                
                            </span>
                            <input type="text" name="txtSearchKeyword" id="txtSearchKeyword" style="width:175px;" placeholder="검색어를 입력하세요" title="자주 묻는 질문 검색어 입력" value="<c:out value="${paramVO.searchKeyword}"/>"
                                   onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();" 
                            ><%-- 페이지 Forward 시 검색조건 유지를 위한 파라미터 셋팅 --%>
                            <a href="javascript:void(0);" id="btnSearch" class="btn_search">검색</a>
                        </div>
                    </div>
                    <!-- // search_area -->
                    
                    <!-- accordion_list -->
                    <div class="accordion_list">
                        <dl id="rsList">
                            
                        </dl>
                    </div>
                    <!-- // accordion_list -->

                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination"></div>                    
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
	    
</body>
</html>