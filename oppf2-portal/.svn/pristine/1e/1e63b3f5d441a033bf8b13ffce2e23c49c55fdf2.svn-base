<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sampleModalPopup.jsp
 * @Description : [샘플:모달팝업] 조회
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

<script language="javascript" type="text/javascript">

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

/* div팝업 오픈 함수 */
function fn_openPopupDiv(param){
 	var openPopDivFilm = "<div id='divBackFirm' style='display:none;position:absolute;z-index:9998;background:#000000;opacity:0.7;top:0;left:0;'></div>"
 	$("body").append(openPopDivFilm);
 	$("#divBackFirm").css( "display" , "block" );
 	$("#divBackFirm").css( "width"   , $(document).width() );
 	$("#divBackFirm").css( "height"  , $(document).height() );
 	$("#divPopupImage-"+param).css("display","block");
 	var topPos = Number(($(document).scrollTop()+document.documentElement.clientHeight/2)-280);
 	$("#divPopupImage-"+param).css("top",topPos);
 	$("#divPopupImage-"+param).css("left","30%");
 	$("#divPopupImage-"+param).draggable();
}
 
/* div팝업 닫기 함수 */
function fn_closePopupDiv(param){
 	$("#divBackFirm").remove();
 	$("#divPopupImage-"+param).css("display","none");
}
 
//화면 로드 처리
$(document).ready(function(){
	
	/* [모달팝업1]버튼 클릭 시 호출 */
	$("#modalPopup1").click(function(){
		fn_openPopupDiv(1);
	});
	
	
});
</script>
</head>
<body>

<div id="wrapper">
	<div id="container">
		<%-- 탑과 메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>	
		<%-- 탑과 메뉴 영역 --%>
		
		<div id="contents">
		<spring:message code="uss.ion.noi.ntfcSj"/>
			<%-- 좌측메뉴 영역 --%>
			<%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
			<%-- 좌측메뉴 영역 --%>
			<div id="articleWrap">
				<div class="conHeader">
					<%-- 네비게이션 영역 --%>
					<%@ include file="/WEB-INF/view/cmm/common-include-history.jspf" %>
					<%-- 네비게이션 영역 --%>
				</div>
				<div id="content">
					<table>
						<tr>
							<td>
								<a href="javascript:;" onclick="util_movePage('<c:url value='/sample/sample.do'/>')">
									샘플모음으로 이동
								</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="javascript:;" id="modalPopup1">
									모달팝업1
								</a>
							</td>
						</tr>
					</table>
				</div><%-- content --%>
			</div><%-- articleWrap --%>
		</div><%-- contents --%>
	</div><%-- container --%>

     <div id="divPopupImage-1" style="display: none; position: absolute; z-index: 9999; top: 481px; left: 30%; padding: 0px;" class="ui-draggable ui-draggable-handle">
     	<div style="position:absolute;top:1px;right:-29px;">
     		<a href="javascript:;" onclick="javascript:fn_closePopupDiv('1');">
     			<img src="<c:url value='/images/cmm/btn_pop_close.gif'/>" alt="div팝업닫기이미지" title="div팝업닫기이미지" />
     		</a>
     	</div>
     	<div style="border:1px solid #999;background-color:#ffffff;">
			<table>
				<tr>
					<td>
					<img src="<c:url value='/images/cmm/bg_login.gif'/>"
					onerror="this.src='<c:url value='/images/cmm/no_images.gif'/>'"
					/>
					</td>
				</tr>
				<tr>
					<td>
						테스트 모달 팝업 입니다.
					</td>
				</tr>
			</table>
     	</div>
     </div>

	<%-- Footer 영역 --%>
	<%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>	
	<%-- Footer 영역 --%>
</div>

</body>
</html>