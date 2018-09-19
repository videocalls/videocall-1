<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : faqDtl.jsp
 * @Description : [FAQ:상세] 조회
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
    document.searchfrm.action = "<c:url value='/cmm/faq/faqList.do'/>";
    document.searchfrm.submit();
}

/* [이전글,다음글]이동함수 */
function fn_moveBeforeAfterDetail(paramId){	
    document.searchfrm.faq_seq.value = paramId;
    document.searchfrm.action = "<c:url value='/cmm/faq/faqDtl.do'/>";
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
        document.searchfrm.action = "<c:url value='/cmm/faq/faqMod.do'/>";
        document.searchfrm.submit();
    });
    
    /* [삭제]버튼 클릭 시 호출 */
    $("#btnDelete").click(function(){
        var msgConfirm = "<spring:message code='confirm.delete.msg'/>";
        if( confirm(msgConfirm) ){
            var url = "<c:url value='/cmm/faq/deleteFaq.ajax'/>";
            var faq_seq = $("#faq_seq").val();
            var userId = $("#userId").val();
            var objParam = new Object();
            objParam.faq_seq = faq_seq;
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

        <%-- 탑과 메뉴 영역 --%>
        <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
        <%-- 탑과 메뉴 영역 --%>
        
        <article class="container">
        
               <div>
                    <%-- lnb(좌측메뉴) 영역 --%>
                    <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
                    <%-- lnb(좌측메뉴) 영역 --%>
                   
                    <%-- content 영역 --%>
                    <section class="content">
                    <div class="location">
                        <h2>Admin Home</h2>
                    </div>
                    <!-- // locatioin -->
                    <h3>OpenAPI 플랫폼 개인/기업 회원 등록 현황(요약)</h3>
                    <div>
                    <form method="post" name="searchfrm" id="searchfrm">
                        <input type="hidden" name="listOrder"       id="listOrder"       value="<c:out value='${paramVO.listOrder}'/>" /><!-- //목록정렬 -->
                        <input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
                        <input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
                        <input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
                        <input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색Keyword -->
                        <input type="hidden" name="searchKind"      id="searchKind"      value="<c:out value='${paramVO.searchKind}'/>" /><!-- //검색조건:FAQ 노출 -->
                        <input type="hidden" name="faq_seq"       id="faq_seq"       value="<c:out value='${paramVO.faq_seq}'/>" /><!-- //seq -->
                        <input type="hidden" name="userId"          id="userId"          value="test1234" /><!-- //userId -->
                    </form>
                    <table border="1">
                        <tr>
                            <th>노출</th>
                            <td><c:out value='${resultDetail.faq_kind}'/></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><c:out value='${resultDetail.faq_title}'/></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><c:out value='${resultDetail.faq_content}'/></td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td><c:out value='${resultDetail.file_id}'/></td>
                        </tr>
                        <tr>
                            <th>조회수</th>
                            <td colspan="3">
                                <fmt:formatNumber value="${resultDetail.readCount}" type="number" />
                            </td>
                        </tr>
                        <tr>
                            <th>생성시간</th>
                            <td>
                                <c:out value='${resultDetail.createDate}'/>
                            </td>
                        </tr>
                        <tr>
                            <th>생성자ID</th>
                            <td>
                                <c:out value='${resultDetail.createId}'/>
                            </td>
                        </tr>
                        <tr>
                            <th>수정시간</th>
                            <td>
                                <c:out value='${resultDetail.updateDate}'/>
                            </td>
                        </tr>
                        <tr>
                            <th>수정자ID</th>
                            <td>
                                <c:out value='${resultDetail.updateId}'/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <a href="javascript:;" id="btnMoveList" onclick="javascript:fn_moveList();">목록</a>
                                <a href="javascript:;" id="btnMoveUpdate">수정</a>
                                <a href="javascript:;" id="btnDelete">삭제</a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table>
                                    <caption>FAQ 상세페이지 이전글 다음글</caption>
                                    <colgroup>
                                        <col style="width:10%;">
                                        <col style="width:*;">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td>이전글</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${befAftInfo.beforeId eq null}" >
                                                    <span style="padding-left:18px; color:#595959;" >없음</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="javascript:;" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.beforeId}');">
                                                        <c:out value="${befAftInfo.beforeSj}" />
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>다음글</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${befAftInfo.afterId eq null}" >
                                                    <span style="padding-left:18px; color:#595959;" >없음</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="javascript:;" onclick="javascript:fn_moveBeforeAfterDetail('${befAftInfo.afterId}');">
                                                        <c:out value="${befAftInfo.afterSj}" />
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>                
          </section><%-- content 영역 --%>
     </div><!-- // content -->
</article><!-- // container -->
</body>
</html>