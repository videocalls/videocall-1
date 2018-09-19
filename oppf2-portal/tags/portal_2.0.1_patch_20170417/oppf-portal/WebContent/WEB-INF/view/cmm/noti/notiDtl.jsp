<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : notiDtl.jsp
 * @Description : [공지사항:상세] 조회
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
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

 
/*******************************************
 * 기능 함수
 *******************************************/
 
 
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/


/*******************************************
 * 이벤트 함수
 *******************************************/

/* [목록]이동함수 */
function fn_moveList(){	
    document.searchfrm.action = "<c:url value='/cmm/noti/notiList.do'/>";
    document.searchfrm.submit();
}

/* [이전글,다음글]이동함수 */
function fn_moveBeforeAfterDetail(paramId){
    document.searchfrm.noticeSeq.value = paramId;
    document.searchfrm.action = "<c:url value='/cmm/noti/notiDtl.do'/>";
    document.searchfrm.submit();
}
 
/* 화면 로드 처리 */
$(document).ready(function(){
	
    /* [목록]버튼 클릭 시 호출 */
    $("#btnMoveList").click(function(){
        fn_moveList();
    });
    
    /* [수정]페이지로 이동 */
    $("#btnMoveUpdate").click(function(){
        document.searchfrm.action = "<c:url value='/cmm/noti/notiMod.do'/>";
        document.searchfrm.submit();
    });
    
    /* [삭제]버튼 클릭 시 호출 */
    $("#btnDelete").click(function(){
        var msgConfirm = "<spring:message code='confirm.delete.msg'/>";
        if( confirm(msgConfirm) ){
            var url = "<c:url value='/cmm/noti/deleteNoti.ajax'/>";
            var noticeSeq = $("#noticeSeq").val();
            var userId = $("#userId").val();
            var objParam = new Object();
            objParam.noticeSeq = noticeSeq;
            objParam.userId  = userId;
            
            $.ajax({
                 type    : "post"
                ,url     : url
                ,data    : objParam
                ,success : function(){
                    var msgAlert = "<spring:message code='success.alert.delete'/>";
                    alert(msgAlert);
                    fn_moveList();
                }
                ,error   : function(){
                    var msgAlert = "<spring:message code='fail.alert.delete'/>";
                    alert(msgAlert);
                    fn_moveList();
                }
            });
        }
    });
    
    
});
</script>
</head>
<body>

<form method="post" name="searchfrm" id="searchfrm">
    <input type="hidden" name="listOrder"       id="listOrder"       value="a.notice_fix_yn desc, a.update_date desc" /><!-- //목록정렬 -->    
    <input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
    <input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
    <input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
    <input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색Keyword -->
    <input type="hidden" name="searchKind"      id="searchKind"      value="<c:out value='${paramVO.searchKind}'/>" /><!-- //검색조건:공지사항 노출 -->
    <input type="hidden" name="noticeSeq"       id="noticeSeq"       value="<c:out value='${paramVO.noticeSeq}'/>" /><!-- //seq -->
    <input type="hidden" name="userId"          id="userId"          value="test1234" /><!-- //userId -->
</form>

<div class="wrap <c:if test="${isMobile eq 'true'}">mobile_wrap</c:if>"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="#none">홈</a></li>
                <li><a href="#none">서비스 지원</a></li>
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
                    <h3>공지사항</h3>
                </div>

                <!-- con -->
                <div class="con">       
                    
                    <dl class="notice_view">                        
                        <dt>
                            <div class="tit"><p><c:out value='${resultDetail.noticeTitle}'/></p><span><c:out value='${resultDetail.createDate}'/></span></div>
                            <c:if test="${not empty resultDetail.fileId}">
                                <c:forEach var="fileList" items="${fileList}" varStatus="status">
	                                <div class="file">
	                                    <img src="<c:url value="/images/spt/customer/icon_file.png"/>" alt="파일첨부 아이콘">
	                                    <a href="javascript:gfn_noticeFileDown('${ fileList.fileId }', '${ fileList.fileSeq }');">
	                                       <c:out value='${ fileList.fileName }'/>
	                                    </a>
	                                </div>
                                </c:forEach>
                            </c:if>
                        </dt>
                        <dd>
                            <div class="view_con">
                                <% //pageContext.setAttribute("newLineChar", "\n"); %>
                                ${ resultDetail.noticeContent }
                            </div>
                        </dd>
                    </dl>

                    <div class="btn_area">                        
                        <a href="javascript:void(0);" id="btnMoveList" class="btn_type1" onclick="javascript:fn_moveList();">목록</a>
                    </div>

                    <ul class="view_list_btn">
                        <li><div class="tit"><span class="prev">이전글</span></div><div class="txt">
                        <c:choose>
                            <c:when test="${befAftInfo.beforeId eq null}" >
                                                이전글이 없습니다.
                                <!-- <span style="padding-left:18px; color:#595959;" >이전글이 없습니다.</span> -->
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.beforeId}');">
                                    <c:out value="${befAftInfo.beforeSj}" />
                                </a>
                            </c:otherwise>
                        </c:choose>
                        </div></li>
                        
                        <li><div class="tit"><span class="next">다음글</span></div><div class="txt">
                        <c:choose>
                            <c:when test="${befAftInfo.afterId eq null}" >
                                                다음글이 없습니다.
                                <!-- <span style="padding-left:18px; color:#595959;" >다음글이 없습니다.</span> -->
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.afterId}');">
                                    <c:out value="${befAftInfo.afterSj}" />
                                </a>
                            </c:otherwise>
                        </c:choose>
                        </div></li>
                    </ul>

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