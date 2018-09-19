<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : notiSave.jsp
 * @Description : [공지사항] 등록수정
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
<%@ include file="/WEB-INF/view/cmm/include/cmmHead.jsp" %>
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
    var noticeKind    = $("#noticeKind").val().replace(/\s/g, "");    //노출
    var noticeTitle   = $("#noticeTitle").val().replace(/\s/g, "");   //제목
    var noticeContent = $("#noticeContent").val().replace(/\s/g, ""); //내용
    var fileId        = $("#fileId").val(); //첨부파일
    
    //[노출]
    if (util_chkReturn(noticeKind, "s") == ""){
        alert("<spring:message code='errors.required' arguments='노출'/>");
        $("#noticeKind").focus();
        return false;
    }
    
    //[제목]
    if (util_chkReturn(noticeTitle, "s") == ""){
        alert("<spring:message code='errors.required' arguments='제목'/>");
        $("#noticeTitle").focus();
        return false;
    }
    
    //[내용]
    if (util_chkReturn(noticeContent, "s") == ""){
        alert("<spring:message code='errors.required' arguments='내용'/>");
        $("#noticeContent").focus();
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
	document.searchfrm.action = "<c:url value='/cmm/noti/notiList.do'/>";
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
				url = "<c:url value='/cmm/noti/insertNoti.ajax'/>";
			}else if(pageMode == 'UPDATE'){
			    url = "<c:url value='/cmm/noti/updateNoti.ajax'/>";
			}
			var userId        = $("#userId").val();
			var noticeKind    = $("#noticeKind").val();
			var noticeTitle   = $("#noticeTitle").val();
			var noticeContent = $("#noticeContent").val();
			var noticeSeq     = $("#noticeSeq").val();
			var fileId        = $("#fileId").val();
		    var objParam = new Object();
			objParam.userId        = userId;
			objParam.noticeKind    = noticeKind;
			objParam.noticeTitle   = noticeTitle;
			objParam.noticeContent = noticeContent;
			objParam.noticeSeq     = noticeSeq;
			objParam.fileId        = fileId;
			
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

<div id="wrapper">
	<div id="container">
		<%-- 탑과 메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/include/cmmTop.jsp" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
			<%-- 좌측메뉴 영역 --%>
			<%@ include file="/WEB-INF/view/cmm/include/cmmLeft.jsp" %>
			<%-- 좌측메뉴 영역 --%>
			<div id="articleWrap">
				<div class="conHeader">
					<%-- 네비게이션 영역 --%>
					<%@ include file="/WEB-INF/view/cmm/include/cmmHistory.jsp" %>
					<%-- 네비게이션 영역 --%>
				</div>
				<div id="content">
					<form method="post" name="searchfrm" id="searchfrm">
						<input type="hidden" name="listOrder"       id="listOrder"       value="<c:out value='${paramVO.listOrder}'/>" /><!-- //목록정렬 -->
						<input type="hidden" name="pageIndex"       id="pageIndex"       value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
						<input type="hidden" name="pageStart"       id="pageStart"       value="<c:out value='${paramVO.pageStart}'/>" /><!-- //mysql limit쿼리 사용을 위한 페이지 시작위치 -->
						<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>" /><!-- //검색조건 -->
						<input type="hidden" name="searchKeyword"   id="searchKeyword"   value="<c:out value='${paramVO.searchKeyword}'/>" /><!-- //검색Keyword -->
						<input type="hidden" name="searchKind"      id="searchKind"      value="<c:out value='${paramVO.searchKind}'/>" /><!-- //검색조건:공지사항 노출 -->
						<input type="hidden" name="noticeSeq"       id="noticeSeq"       value="<c:out value='${paramVO.noticeSeq}'/>" /><!-- //seq -->
						<input type="hidden" name="userId"          id="userId"          value="test1234" /><!-- //userId -->
					</form>
					<table border="1">
						<tr>
							<th>노출</th>
							<td>
								<input type="text" name="noticeKind" id="noticeKind" value="<c:out value='${resultDetail.noticeKind}'/>" />
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="noticeTitle" id="noticeTitle" value="<c:out value='${resultDetail.noticeTitle}'/>" />
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
							    <textarea name="noticeContent" id="noticeContent" class="inMemo"><c:out value='${resultDetail.noticeContent}'/></textarea>
							</td>
						</tr>
                        <tr>
                            <th>첨부파일</th>
                            <td>
                                <input type="text" name="fileId" id="fileId" value="<c:out value='${resultDetail.fileId}'/>" />
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
								<a href="javascript:void(0);" id="btnMoveList" onclick="javascript:fn_moveList();">목록</a>
								<a href="javascript:void(0);" id="btnSave">
								  <c:if test="${pageMode eq 'INSERT'}">등록</c:if>
								  <c:if test="${pageMode eq 'UPDATE'}">수정</c:if>
								</a>
							  <c:if test="${pageMode eq 'UPDATE'}">
								<a href="javascript:void(0);" id="btnDelete">삭제</a>
							  </c:if>
							</td>
						</tr>
					</table>
				</div><%-- content --%>
			</div><%-- articleWrap --%>
		</div><%-- contents --%>
		<%-- Bottom 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/include/cmmBottom.jsp" %>
		<%-- Bottom 영역 --%>
	</div><%-- container --%>
	
	<%-- Footer 영역 --%>
	<%@ include file="/WEB-INF/view/cmm/include/cmmFooter.jsp" %>
	<%-- Footer 영역 --%>
</div>

</body>
</html>