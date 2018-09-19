<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : faqSave.jsp
 * @Description : [FAQ] 등록수정
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
var pageMode = '${pageMode}'; //INSERT:등록,UPDATE:수정
 
/*******************************************
 * 기능 함수
 *******************************************/
 
/*유효성검증 함수*/
function fn_SaveValidate(){
    var faq_kind    = $("#faq_kind").val().replace(/\s/g, "");    //노출
    var faq_title   = $("#faq_title").val().replace(/\s/g, "");   //제목
    var faq_content = $("#faq_content").val().replace(/\s/g, ""); //내용
    var file_id        = $("#file_id").val(); //첨부파일
    
    //[노출]
    if (util_chkReturn(faq_kind, "s") == ""){
        alert("<spring:message code='errors.required' arguments='노출'/>");
        $("#faq_kind").focus();
        return false;
    }
    
    //[제목]
    if (util_chkReturn(faq_title, "s") == ""){
        alert("<spring:message code='errors.required' arguments='제목'/>");
        $("#faq_title").focus();
        return false;
    }
    
    //[내용]
    if (util_chkReturn(faq_content, "s") == ""){
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $("#faq_content").focus();
        return false;
    }
    
    return true;
    
}
 
 
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
 
/* 화면 로드 처리 */
$(document).ready(function(){
	
	/* [목록]버튼 클릭 시 호출 */
	$("#btnMoveList").click(function(){
		fn_moveList();
	});
	
	/* [저장]버튼 클릭 시 호출 */
	$("#btnSave").click(function(){
		if( !fn_SaveValidate() ){ //유효성검증호출
			return false;
		}
		var msgConfirm = "<spring:message code='confirm.modify.msg'/>";
		if(pageMode == 'INSERT'){
			msgConfirm = "<spring:message code='confirm.regist.msg'/>";
		}
		if( confirm(msgConfirm) ){
			var url = "";
			if(pageMode == 'INSERT'){
				url = "<c:url value='/cmm/faq/insertFaq.ajax'/>";
			}else if(pageMode == 'UPDATE'){
			    url = "<c:url value='/cmm/faq/updateFaq.ajax'/>";
			}
			var userId      = $("#userId").val();
			var faq_kind    = $("#faq_kind").val();
			var faq_title   = $("#faq_title").val();
			var faq_content = $("#faq_content").val();
			var faq_seq     = $("#faq_seq").val();
			var file_id        = $("#file_id").val();
		    var objParam = new Object();
			objParam.userId        = userId;
			objParam.faq_kind    = faq_kind;
			objParam.faq_title   = faq_title;
			objParam.faq_content = faq_content;
			objParam.faq_seq     = faq_seq;
			objParam.file_id        = file_id;
			
			$.ajax({
				 type    : "post"
				,url     : url
				,data    : objParam
				,success : function(){
					var msgAlert = "<spring:message code='success.alert.modify'/>";
					if(pageMode == 'INSERT'){
						  msgAlert = "<spring:message code='success.alert.regist'/>";
					}
					alert(msgAlert);
					fn_moveList();
				}
				,error   : function(){
					var msgAlert = "<spring:message code='fail.alert.modify'/>";
                       if(pageMode == 'INSERT'){
                           msgAlert = "<spring:message code='fail.alert.regist'/>";
                       }
					alert(msgAlert);
					fn_moveList();
				}
			});
		}
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
						<input type="hidden" name="searchKind"      id="searchKind"      value="<c:out value='${paramVO.searchKind}'/>" /><!-- //검색조건:공지사항 노출 -->
						<input type="hidden" name="faq_seq"         id="faq_seq"       value="<c:out value='${paramVO.faq_seq}'/>" /><!-- //seq -->
						<input type="hidden" name="userId"          id="userId"          value="test1234" /><!-- //userId -->
					</form>
					<table border="1">
						<tr>
							<th>노출</th>
							<td>
								<input type="text" name="faq_kind" id="faq_kind" value="<c:out value='${resultDetail.faq_kind}'/>" />
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="faq_title" id="faq_title" value="<c:out value='${resultDetail.faq_title}'/>" />
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
							    <textarea name="faq_content" id="faq_content" class="inMemo"><c:out value='${resultDetail.faq_content}'/></textarea>
							</td>
						</tr>
                        <tr>
                            <th>첨부파일</th>
                            <td>
                                <input type="text" name="file_id" id="file_id" value="<c:out value='${resultDetail.file_id}'/>" />
                            </td>
                        </tr>
				      <c:if test="${pageMode eq 'UPDATE'}">
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
				      </c:if>
						<tr>
							<td colspan="2">
								<a href="javascript:;" id="btnMoveList" onclick="javascript:fn_moveList();">목록</a>
								<!-- <a href="javascript:;" id="btnMoveList" onclick="javascript:fn_moveList();">등록</a> -->
								<a href="javascript:;" id="btnSave">
								  <c:if test="${pageMode eq 'INSERT'}">등록</c:if>
								  <c:if test="${pageMode eq 'UPDATE'}">수정</c:if>
								</a>
							  <c:if test="${pageMode eq 'UPDATE'}">
								<a href="javascript:;" id="btnDelete">삭제</a>
							  </c:if>
							</td>
						</tr>
					</table>
				</div>                
          </section><%-- content 영역 --%>
     </div><!-- // content -->
</article><!-- // container -->
</body>
</html>