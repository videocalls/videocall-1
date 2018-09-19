<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMyPwConfrm.jsp
 * @Description : 회원정보 확인 전 비밀번호 확인
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.09  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.05.09
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript" src="<c:url value='/AOS2/include.js'/>"></script>
<script language="javascript" type="text/javascript">
window.onload = function(){
    $_astxu.log('[onload] '+navigator.userAgent);
    $_astxu.log('[onload] platform='+navigator.platform+',browser='+$ASTX2_CONST.BROWSER_VER);
    
    $ASTX2.setOption({autofocus:true});
    
    checkInstallASTX2(
        function onSuccess() {
            doATX2CheckServer();
        }
    );
}

function doATX2CheckServer(){
    $ASTX2.checkServer(
        function onSuccess() {
            $_astxu.log('ASTX.checkServer() success');
            
            $ASTX2.initE2E();
            $_astxj.hideOverlay();
        }, 
        function onFailure() {
            $_astxu.log('ASTX.checkServer() failure: errno='+$ASTX2.getLastError());
        }
    );
}

var spw = '';
function onSubmit2(){
    
    $_astxu.log('[onSubmit2]');
    
    /*
    $ASTX2.getE2EData(
        document.form2,
    */
    $ASTX2.getE2EDataIDs(
//      ['cardno5','cardno6','cardno7','cardno8'],
        ['userPw'],
        function onSuccess(data) {
            //$_astxu.log('ASTX.getE2EData() success');
            
            jQuery.ajax({
                 url:'/AOS2/do_submit_ajax.jsp',
                 data: data,
                 type: "POST",
                 dataType: 'json',
                 success:function(json){
                     $_astxu.log('[ajax] '+$_astxu.jsonQstr(json));
                     var data = $_astxu.jsonQstr(json)+"";
//                   alert(data);
                     var rsArr = data.split("&");
                     for(var i=0; i<rsArr.length; i++){
//                       alert('rsArr['+i+']:'+rsArr[i]);
                         var rsArr2 = rsArr[i].split("=");
                         if('userPw' == rsArr2[0]){
                             spw = util_setHtmlParsing(rsArr2[1]);
                         }
                     }
                     //로그인 처리
                     fn_SchConfrm();
                 },
                 error:function(){
                     alert("허용되지 않는 특수문자가 들어갔습니다.");
                     return;
                 }
            });
            
        }, 
        function onFailure() {
            $_astxu.log('ASTX.getE2EData() failure: errno='+$ASTX2.getLastError());
        }
    );
    
}

var debuger = new myDebuger();
//debuger.write_output(document, true);
</script>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

/*******************************************
 * 기능 함수
 *******************************************/
 
//회원정보 비밀번호 입력전 키보드 보안값 확인
function fn_beforeSchConfrm(){
	var userPw = $("#userPw").val();
	
	//비밀번호 미 입력 시
    if(gfn_nvl(userPw) == ""){
        //alert("<spring:message code='errors.required' arguments='비밀번호'/>");
        alert("비밀번호를 입력해 주세요.");
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("userPw");
        return;
    }
	
    onSubmit2();
}

//회원정보 비밀번호 입력 확인
function fn_SchConfrm(){
	
    var url = "<c:url value='/spt/myp/sptMyInfo/checkPwProcs.ajax'/>";
    
    //로딩start
    gfn_setLoading(true, "비밀번호 확인 중입니다.");
    
    var objParam = new Object();
    objParam.customerId = $("#userId").val();
    objParam.customerRegNo = $("#customerRegNo").val();
    //objParam.customerPassword = $("#userPw").val();
    objParam.customerPassword = spw;
        
    //로딩end
    gfn_setLoading(false);
  
    util_movePage(url,objParam);
}


<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/spt/myp/sptMyInfo/sptMyPwConfrm.do'/>";
    var param = new Object();
    param.paramMenuId = "05004";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
	    return;
    </c:if>
	
	//비밀번호 우클릭 금지
    $("#userPw").bind("contextmenu", function(e){
        return false;
    });
    
	/*
    //[확인]버튼 클릭 시 호출 - 회원정보 비밀번호 입력 확인
    $("#btnSchConfrm").bind("click", function(){
    	fn_beforeSchConfrm();    	
    });
	*/
    
    <c:if test="${not empty rtCheckPw}">
	    alert("비밀번호가 일치하지 않습니다.");
	    //키보드 보안 연동 비밀번호 초기화
	    gfn_clearE2EText("userPw");
	</c:if>
    
});
</script>
</head>
<body>

<div class="wrap">

    <table Style="display:none">
        <tr> 
            <th>로그인 세션값 저장</th>
            <td>
                <input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />                
                <input type="hidden" id="customerRegNo" name="customerRegNo" value="${ LoginVO.customer_reg_no }" />
            </td>
        </tr>
    </table>

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf"%>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="#none">홈</a></li>
                <li><a href="#none">마이 페이지</a></li>
                <li class="on">회원정보</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
	        <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf"%>
	        <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>회원정보</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="pw_confirm">
                        <p class="tit">개인정보를 안전하게 보호하기 위해<br>비밀번호를 한번 더 입력해주세요.</p>
                        <input type="password" name="userPw" id="userPw" title="비밀번호 입력" placeholder="비밀번호 입력"
                               onkeydown="javascript:if(event.keyCode == 13) fn_beforeSchConfrm();"
                               autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'userPw',15);"  
                        />
                        <div class="btn_area">
                            <a href="javascript:fn_beforeSchConfrm();" id="btnSchConfrm" class="btn_type1 type2">확인</a>
                        </div>
                    </div>
                    
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