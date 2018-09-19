<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : notiList.jsp
 * @Description : [공지사항목록:목록] 조회
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.28  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.04.28
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
/* [공지사항목록]ajax call 함수 */
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
    
    var url = "<c:url value='/cmm/noti/selectNotiList.ajax'/>";
    var reqData = {
       "pageRowsize"   : $("#pageRowsize").val()
      ,"pageBlocksize" : $("#pageBlocksize").val()
      ,"pageIndex"     : $("#pageIndex").val()
      ,"pageStart"     : $("#pageStart").val()
      ,"listOrder"     : $("#listOrder").val()
      ,"searchCondition" : $("#sltSearchCondition :selected").val()
      ,"searchKeyword"   : $("#txtSearchKeyword").val()
      ,"searchKind"      : $("#searchKind").val()
      ,"noticeFixYn"      : "N"
    };
    
    //로딩 호출
    gfn_setLoading(true);
    
    util_ajaxPage(url, reqData, "fn_ajaxCallback_getList");
}

/* [공지사항목록]ajax callback 함수 */
function fn_ajaxCallback_getList(data){
    var rsListTotCnt = data.resultListTotalCount;
    var rsList = data.resultList;
    
    var rsListFix = data.resultListFix;
    
    var apData="";
    
    /* 페이징 설정 변수 */
    var pageIndex = Number(data.paramVO.pageIndex);
    var pageStart = Number(data.paramVO.pageStart);
    var pageRowsize = Number(data.paramVO.pageRowsize);
    
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
    
    $("#fixApData >").remove();
    
    $("#rsList >").remove();
    $("#pagingNavi >").remove();
    if (
        ( util_chkReturn(rsListTotCnt, "s") == "" || util_chkReturn(rsList, "s") == "" ) &&
        util_chkReturn(rsListFix, "s") == ""
    ){
        apData = '<tr>'       
            +        '<td colspan="3"><div class="nodata">등록된 내용이 없습니다.</div></td>'
            +    '</tr>';
        $("#rsList").append(apData);
        
        //로딩 호출
        gfn_setLoading(false);
        
        return false;
    }
    
    //fix notice
    if(util_chkReturn(rsListFix, "s") != ""){
    	var fixApData="";
    	
	    $.each(rsListFix, function(idx,data){      
	        fixApData += '<tr class="top_notice">'
	            +  '<td class="m_hide"><img src="<c:url value="/images/spt/customer/icon_speaker.png"/>" alt=""></td>'
	            +  '<td class="left">'
	            +  '<a href="javascript:void(0);" onclick="javascript:fn_moveDetail(\''+data.noticeSeq+'\')">'
	            +    data.noticeTitle
	            +  '</a>'
	            +  '<div class="m_date">'+data.createDate+'</div>'
	            +  '</td>'
	            +  '<td class="m_hide">'+data.createDate+'</td>'
	            +  '</tr>';
	    });    
	    $("#fixApData").append(fixApData);
    }	    
    
    /* 페이징 설정 변수 */
//     var pageIndex = Number(data.paramVO.pageIndex);
//     var pageStart = Number(data.paramVO.pageStart);
//     var pageRowsize = Number(data.paramVO.pageRowsize);
    
    $.each(rsList, function(idx,data){    	
        var iNo = rsListTotCnt - ( (pageIndex - 1) * pageRowsize + (idx+1) )+1;
        var fileId = '';
        
        if (util_chkReturn(data.fileId, "s") != ""){
        	fileId = '<img src="<c:url value="/images/spt/customer/icon_file.png"/>" alt="파일첨부 아이콘"class="img_file">';
        }
               
        apData += '<tr>'               
            +  '<td class="m_hide">'+iNo+'</td>'
            +  '<td class="left">'
            +  '<a href="javascript:void(0);" onclick="javascript:fn_moveDetail(\''+data.noticeSeq+'\')">'
            +    data.noticeTitle
            +  '</a>'
            +    fileId
            +  '<div class="m_date">'+data.createDate+'</div>'
            +  '</td>'
            +  '<td class="m_hide">'+data.createDate+'</td>'
            +  '</tr>';
    });
    $("#rsList").append(apData);
    
    /* 페이징 설정 START */
//     $("#pageIndex").val(pageIndex);
//     $("#pageStart").val(pageStart);
    
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 START */
//     var searchCondition = data.paramVO.searchCondition;
//     $("#searchCondition").val(searchCondition);
//     $("#sltSearchCondition").val(searchCondition);
    
//     var searchKeyword = data.paramVO.searchKeyword;
//     $("#searchKeyword").val(searchKeyword);
//     $("#txtSearchKeyword").val(searchKeyword);
    
//     var searchKind = data.paramVO.searchKind;
//     $("#searchKind").val(searchKind);
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 END */
    
    $("#pageTotalcount").val(rsListTotCnt);
    
    pageNavigator(
      $("#pageIndex").val()
     ,$("#pageTotalcount").val()
     ,"fn_paging"
     ,$("#pageRowsize").val()
     ,$("#pageBlocksize").val()     
    );
    /* 페이징 설정 END */
    
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
 
/* [상세]이동함수 */
function fn_moveDetail(seq){
    var objParam = new Object();
    objParam.pageIndex = $("#pageIndex").val();
    objParam.pageStart = $("#pageStart").val();
    objParam.listOrder = $("#listOrder").val();
    objParam.noticeSeq       = seq;
    objParam.searchCondition = $("#searchCondition").val();
    objParam.searchKeyword   = $("#searchKeyword").val();
    objParam.searchKind      = $("#searchKind").val();
    objParam.noticePopYn     = "N";
    
    util_movePage("<c:url value='/cmm/noti/notiDtl.do'/>",objParam);
}

/* [공지사항조회]호출되는 함수 */
function fn_searchList(){
    $("#pageIndex").val("1");
    $("#pageStart").val("0");
    
    fn_ajaxCall_getList(); //[공지사항목록]ajax call 함수호출
}

//화면 로드 처리
$(document).ready(function(){
    
    /* [조회]버튼 클릭 시 호출 */
    $("#btnSearch").click(function(){
        fn_searchList();
    });
    
    /* [검색키워드] keydown 시 호출 */
    $("#txtSearchKeyword").bind("keydown", function(key){
        if(key.keyCode == 13){
            fn_searchList();
        }
    });
    
    fn_ajaxCall_getList(); //[공지사항목록]ajax call 함수호출    
    
});
</script>
</head>
<body>

<input type="hidden" name="pageTotalcount"  id="pageTotalcount"  value="0" /><!-- //총갯수 -->
<input type="hidden" name="pageRowsize"     id="pageRowsize"     value="10" /><!-- //목록사이즈 -->
<input type="hidden" name="pageBlocksize"   id="pageBlocksize"   value="10" /><!-- //페이징블록사이즈 -->
<input type="hidden" name="listOrder"       id="listOrder"       value="a.notice_kind asc, a.notice_fix_yn desc, a.update_date desc" /><!-- //목록정렬 -->
<input type="hidden" name="pageRowsizeFix"  id="pageRowsizeFix"  value="20" /><!-- //목록사이즈 -->
<input type="hidden" name="pageIndexFix"    id="pageIndexFix"    value="1" />
<input type="hidden" name="pageStartFix"    id="pageStartFix"    value="0" />
<input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /> <!-- //검색조건 -->
<input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /> <!-- //검색Keyword -->

<%-- 2016.09.09 이준형과장 요청 - 미 로그인 시에도 노출 --%>
<%--
<input type="hidden" name="searchKind"      id="searchKind"      value="<c:if test="${not empty LoginVO  }">${SYSTEM_KIND}</c:if>" />
--%>
<input type="hidden" name="searchKind"      id="searchKind"      value="${SYSTEM_KIND}" />

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
                <li class="on">공지사항</li>
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
                    <h3>공지사항</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- search_area -->
                    <div class="search_area m_hide"><!-- 2016-05-26 수정 -->
                        <div class="search_box right">
                            <span class="sel_box1">
                                <select name="sltSearchCondition" id="sltSearchCondition" style="min-width:;" title="검색조건 선택">
                                    <option value="sjcn">전체</option>
                                    <option value="sj">제목</option>
                                    <option value="cn">내용</option>
                                </select>
                            </span>                            
                            <input type="text" style="width:196px;" name="txtSearchKeyword" id="txtSearchKeyword" class="ccboxTopSearch" placeholder="검색어를 입력하세요" title="공지사항 검색어 입력" value="<c:out value="${paramVO.searchKeyword}"/>"
                                   onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();" 
                            />
                            <a href="javascript:void(0);" id="btnSearch" class="btn_search" title="검색하기">검색</a>
                            <%-- <h3>키워드</h3>
                            <select name="sltSearchCondition" id="sltSearchCondition" class="ccboxTopSelec" title="공지사항 키워드를 선택해주세요">
                                <option value="sj"   <c:if test="${paramVO.searchCondition eq 'sj' || empty paramVO.searchCondition}"> selected="selected" </c:if> >제목</option>
                                <option value="cn"   <c:if test="${paramVO.searchCondition eq 'cn'}"> selected="selected" </c:if> >내용</option>
                                <option value="sjcn" <c:if test="${paramVO.searchCondition eq 'sjcn'}"> selected="selected" </c:if> >제목+내용</option>
                            </select>
                            <input type="text" name="txtSearchKeyword" id="txtSearchKeyword" class="ccboxTopSearch" title="검색하실 내용을 입력해주세요." value="<c:out value="${paramVO.searchKeyword}"/>" />페이지 Forward 시 검색조건 유지를 위한 파라미터 셋팅
                            <span>
                               <a href="javascript:void(0);" id="btnSearch" class="btnDtS2" title="검색하기">검색</a>
                            </span> --%>
                        </div>
                    </div>
                    <!-- // search_area -->
                    
                    <!-- tbtype_list2 type3 -->
                    <table class="tbtype_list2 type3">
                        <caption>번호, 제목, 등록일</caption>
                        <colgroup>
                            <col style="width:10%;" class="m_hide"><!-- 2016-05-26 수정 -->
                            <col style="">
                            <col style="width:20%;" class="m_hide"><!-- 2016-05-26 수정 -->
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col" class="m_hide">번호</th><!-- 2016-05-26 수정 -->
                                <th scope="col">제목</th>
                                <th scope="col" class="m_hide">등록일</th> <!-- 2016-05-26 수정 --> 
                            </tr>
                        </thead>
                        <tbody id="fixApData">
                        
                        </tbody>
                        <tbody id="rsList">
                            
                        </tbody>
                    </table>
                    <!-- // tbtype_list2 type3 -->

                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination"></div>
                    <!-- <div class="pagination">
                        <a href="#" class="page first"></a>
                        <a href="#" class="page prev"></a>
                        <a href="#" class="on">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#">6</a>
                        <a href="#">7</a>
                        <a href="#">8</a>
                        <a href="#">9</a>
                        <a href="#">10</a>
                        <a href="#" class="page next"></a>
                        <a href="#" class="page last"></a>
                    </div> -->
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