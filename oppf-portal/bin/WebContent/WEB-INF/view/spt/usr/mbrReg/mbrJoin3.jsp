<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrJoin2.jsp
 * @Description : [회원가입:개인정보입력]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 * </pre>
 *
 * @author 포털 
 * @since 
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script type="text/javascript">

/* 화면 로드 처리 */
$(document).ready(function(){
    
    
    
    var objParam = new Object();   

    /* [다음]버튼 클릭 시 호출 */
    $("#btnOk").click(function(){
        util_movePage("<c:url value='/spt/cmm/mainView.do'/>",objParam);
    });			
    /* [다음]버튼 클릭 시 호출 */
    $("#btnFin").click(function(){

        util_movePage("<c:url value='/usr/svcAppl/appList.do'/>",objParam);
    });
    
    
    
});

</script>
</head>
<body>

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">회원가입</li>
            </ul>
        </div>
        <!-- // location -->
        
        <!-- container -->
        <div class="container">
        
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <article id="content">
                <div class="sub_title">
                    <h3>회원가입</h3>                                    
                </div>
                <!-- con -->
                <div class="con">
					
					<div class="member_area type2">
						<div class="tit_info">
							<p>
								<span>회원가입 완료</span>
								<strong><c:out value='${paramVO.customerNameKor}'/></strong>님, 회원가입을 축하합니다.
							</p>
						</div>

						<p class="txt">회원님은 오픈플랫폼의 개인 회원으로 가입완료되셨습니다.<br>가입하신 회원님의 정보는 아래와 같습니다.</p> <!-- renewal -->
						
					</div>

					<p class="title_01">회원가입 내역</p>

					<!-- tbtype_form -->
					<table class="tbtype_form">
						<caption>아이디, 휴대폰, 이메일 정보</caption>
						<colgroup>
							<col style="width:20%;">
							<col style="width:*;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td><strong class="point01"><c:out value='${paramVO.customerId}'/></strong></td>
						</tr>
						<tr>
							<th scope="row">휴대폰</th>
							<td><c:out value='${paramVO.customerPhone}'/></td>
						</tr>
						<tr>
							<th scope="row">이메일</th>
							<td><c:out value='${paramVO.customerEmail}'/></td>
						</tr>
						</tbody>
					</table>					
					<!-- // tbtype_form -->

					<ul class="list_style_01">
						<li>회원정보는 마이 페이지 메뉴에서 조회 및 수정이 가능합니다.</li>
					</ul>
					
					<!-- 2016-06-20 추가 -->
					<div class="btn_area">						
						<a href="javascript:void(0);" id="btnOk" class="btn_type2">확인</a>
                        <a href="javascript:void(0);" id="btnFin"  class="btn_type1">핀테크 서비스신청</a>
					</div>
					<!-- // 2016-06-20 추가 -->
	
                </div>
                <!-- // con -->
                
            </article>
            <!-- // content -->
        
        </div>
        <!-- // container -->
        
    </section>
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>

</body>
</html>