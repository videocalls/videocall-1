<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptIdFind.jsp
 * @Description : 기업운영자 아이디 찾기
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.04  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.04
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript" src="<c:url value='/js/cpt/common_pub.js'/>"></script>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
   
	//아이디 찾기 확인
    $("#btnIdFind").bind("click", function(){
        fn_idFind();
    });
    
    //아이디 찾기 취소
    $("#btnIdCancel").bind("click", function(){
        $('#searchIdName').val("");
        $('#searchIdEmail_01').val("");
        $("#emailSelectIdSch").eq(1);
        
        $('#searchIdName').focus();
    });
    
    //아이디 찾기 이메일 주소 타입 변경 시
    $('#emailSelectIdSch').change(function(){
        if($(this).val()==""){
            $('#searchIdEmail_02').val("");
            $('#searchIdEmail_02').attr("disabled",false);
        }else{
            $('#searchIdEmail_02').val($(this).val());
            $('#searchIdEmail_02').attr("disabled",true);
        }
    });
    
    //아이디 찾기 성공후 로그인
    $("#btnIdLogin").bind("click", function(){
    	fn_moveHttpsUrl("<c:url value='/cpt/cmm/loginView.do'/>");
    });
    
    //비밀번호 찾기 확인
    $("#btnPwFind").bind("click", function(){
        fn_PwFind();
    });
    
    //비밀번호 찾기 취소
    $("#btnPwCancel").bind("click", function(){
        $('#searchPwName').val("");
        $('#searchPwId').val("");
        $('#searchPwEmail_01').val("");
        $("#emailSelectPwSch").eq(1);
        
        $('#searchPwName').focus();
    });
    
    //비밀번호 찾기 이메일 주소 타입 변경 시
    $('#emailSelectPwSch').change(function(){
        if($(this).val()==""){
            $('#searchPwEmail_02').val("");
            $('#searchPwEmail_02').attr("disabled",false);
        }else{
            $('#searchPwEmail_02').val($(this).val());
            $('#searchPwEmail_02').attr("disabled",true);
        }
    });
    
    //비밀번호 찾기 성공후 로그인
    $("#btnPwLogin").bind("click", function(){
    	fn_moveHttpsUrl("<c:url value='/cpt/cmm/loginView.do'/>");
    });
    
    fn_init();
    
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 초기 화면 셋팅은 공통 --%> 
function fn_init(){
    var tabId = $("#tabId").val(); //"id" or "pw"
    $("#findTb").show();
    $("#resultTb").hide();
    $("#findUl").show();
    $("#resultUl").hide();
    
    if(tabId == "id"){
        $("#li01").attr("class", "on"); //탭을 선택한 효과를 보여줍니다.
    }else{
        $("#li02").attr("class", "on");
        $("#tab01").hide();
        $("#tab02").show();
    }
}
 
<%-- 아이디 찾기 --%> 
function fn_idFind(){
    if(!fn_validate()){
        return;
    }   
    var searchIdName = $("#searchIdName").val();
    var searchIdEmail = $("#searchIdEmail_01").val()+"@"+$("#searchIdEmail_02").val();
    
    var moveUrl = "<c:url value='/cpt/cmm/selectIdFind.ajax'/>";
    //var param = $("#CptLoginVO").serialize();
    var param = new Object();
    param.searchName = searchIdName;
    param.searchEmail = searchIdEmail;    
    var callBackFunc = "fn_idFindCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
<%-- 비밀번호 찾기 --%> 
function fn_PwFind(){
    if(!fn_validate()){
        return;
    }
    var searchPwName = $("#searchPwName").val();
    var searchPwEmail = $("#searchPwEmail_01").val()+"@"+$("#searchPwEmail_02").val();
    var searchPwId = $("#searchPwId").val();
    
    var moveUrl = "<c:url value='/cpt/cmm/selectPwFind.ajax'/>";
    //var param = $("#CptLoginVO").serialize();
    var param = new Object();
    param.searchName = searchPwName;
    param.searchEmail = searchPwEmail;
    param.searchId = searchPwId;
    param.uriContextPath = httpsContextpath;
    var callBackFunc = "fn_idFindCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_idFindCallBack(data){
    var result = data.resultData;
    var tabId = $("#tabId").val(); //"id" or "pw"
    
    if(result != null){     
        $("#findTb").hide(); //조회탭 화면은 숨기고
        $("#resultTb").show(); //결과탭 화면은 보입니다
        $("#findUl").hide(); //탭의 li 부분이 조회2개(id,pw), 결과2개(id,pw) 전부 다릅니다 - 조회부분의 탭을 숨깁니다
        $("#resultUl").show();
        if(tabId == "id"){
            $("#li03").attr("class", "on");
            $("#resultId").html(result.operator_id);            
            $("#tab03").show(); 
        }else{
            $("#li04").attr("class", "on");
            $("#resultEmail").html(result.operator_email);
            $("#tab04").show(); 
        }
    }else{
        if(tabId == "id"){
            alert("<spring:message code='errors.idfind' />");
            $('#searchIdName').focus();
        }else{
            alert("<spring:message code='errors.idfind' />");
            $('#searchName').focus();
        }
        return;
    }
}

function fn_valSet(param){
    if(param == "id"){
        $('#tabId').val(param);
    }else{
        $('#tabId').val(param);
    }
}

<%-- validate --%>
function fn_validate(){
    var tabId = $("#tabId").val(); //"id" or "pw"
    var email = "";
    
    if(tabId == "id"){
        //이름
        if(util_chkReturn($.trim($('#searchIdName').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='이름'/>");
            $('#searchIdName').val("");
            $('#searchIdName').focus();
            return false;
        }
        
        //이메일
        email = $.trim($('#searchIdEmail_01').val()) + "@" + $.trim($('#searchIdEmail_02').val());
        if(util_chkReturn(email, "s") == "") {
            alert("<spring:message code='errors.required' arguments='이메일 주소'/>");
            $('#searchIdEmail_01').focus();
            return false;
        }else{
            if(!util_isEmail(email)){
                alert("<spring:message code='errors.email' arguments='이메일 주소'/>");
                $('#searchIdEmail_01').focus();
                return false;
            }
        }   
    }else{
        //이름
        if(util_chkReturn($.trim($('#searchPwName').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='이름'/>");
            $('#searchPwName').val("");
            $('#searchPwName').focus();
            return false;
        }
        
        //아이디
        if(util_chkReturn($.trim($('#searchPwId').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='아이디'/>");
            $('#searchPwId').val("");
            $('#searchPwId').focus();
            return false;
        }
        
        //이메일
        email = $.trim($('#searchPwEmail_01').val()) + "@" + $.trim($('#searchPwEmail_02').val());
        if(util_chkReturn(email, "s") == "") {
            alert("<spring:message code='errors.required' arguments='이메일 주소'/>");
            $('#searchPwEmail_01').focus();
            return false;
        }else{
            if(!util_isEmail(email)){
                alert("<spring:message code='errors.email' arguments='이메일 주소'/>");
                $('#searchPwEmail_01').focus();
                return false;
            }
        }
    }
    
    $("#searchEmail").val(email);
    
    return true;
    
}
 
</script>
</head>
<body>

<div class="wrap">
    <form:form commandName="CptLoginVO" name="CptLoginVO" method="post">
    <%-- <form:hidden path="tabId" id="tabId" /> --%><%-- tabId --%>    
    <input type="hidden" id="searchEmail" name="searchEmail" />
    <input type="hidden" id="tabId" name="tabId" value="${ tabId }"/>
    
    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">로그인</a></li>
                <li class="on">아이디/비밀번호 찾기</li>
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
                    <h3>아이디/비밀번호 찾기</h3>                                    
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- tab_menu -->
                    <div class="tab_menu">
                        <ul id="findUl">
                            <li id="li01"><a href="#tab01" onclick="javascript:fn_valSet('id')">아이디 찾기</a></li>
                            <li id="li02"><a href="#tab02" onclick="javascript:fn_valSet('pw')">비밀번호 찾기</a></li>
                        </ul>
                        <ul id="resultUl">   
                            <li id="li03"><a href="#tab03">아이디 찾기</a></li>
                            <li id="li04"><a href="#tab04">비밀번호 찾기</a></li>
                        </ul>
                    </div>
                    <!-- // tab_menu -->
                    
                    <div id="findTb">
                        <!-- tab_cont -->
                        <div class="tab_cont" id="tab01">                       
    
                            <div class="idpw_search_box">
                                <div class="info_txt">
                                    <ul class="list_type01 type2">
                                        <li>회원님의 이름과 가입 시 입력한 이메일 주소를 입력해 주십시오.</li>
                                        <li>입력하신 정보가 등록된 정보와 일치 시 아이디를 확인 하실 수 있습니다.</li>
                                    </ul>                           
                                </div>
                                <ul class="idpw_box">                                    
                                    <li>
                                        <label for="user_name">이름</label>
                                        <input type="text" name="searchIdName" id="searchIdName" class="input_txt01"  style="min-width:238px;" tabindex="1"
                                               onkeydown="javascript:if(event.keyCode == 13) $('#searchIdEmail_01').focus();" 
                                               value=""
                                        >
                                    </li>
                                    <li>
                                        <label for="user_email">이메일 주소</label>
                                        <input type="text" name="searchIdEmail_01" id="searchIdEmail_01" class="input_txt01" style="min-width:148px;" tabindex="2"
                                               onkeydown="javascript:if(event.keyCode == 13) $('#searchIdEmail_02').focus();" 
                                               value=""
                                        >
                                        <span class="dot">@</span>
                                        <input type="text" name="searchIdEmail_02" id="searchIdEmail_02" class="input_txt01" style="min-width:188px;" tabindex="3"
                                              onkeydown="javascript:if(event.keyCode == 13) btnIdFind.click();" 
                                              value=""
                                        >
                                        <span class="sel_box1 type2">
                                            <select id="emailSelectIdSch" style="min-width:150px;" title="이메일주소 선택">
                                                <option value="">직접입력</option>
                                                <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                                    <option value="${emailList.code_name_kor}">${emailList.code_name_kor }</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </li>
                                </ul>
                            </div>
    
                            <div class="btn_area">
                                <a href="javascript:void(0);" id="btnIdCancel" class="btn_type2 type2">취소</a>
                                <a href="javascript:void(0);" id="btnIdFind" class="btn_type1 type2">확인</a>
                            </div>
    
                        </div>
    
                        <div class="tab_cont" id="tab02">                        
                            <div class="idpw_search_box">
                                <div class="info_txt">
                                    <ul class="list_type01 type2">
                                        <li>회원님의 이름, 아이디, 가입 시 입력하신 이메일 주소를 입력해 주십시오.</li>
                                        <li>입력하신 정보가 등록된 정보와 일치 시 임시 비밀번호가 이메일로 발송 됩니다.</li>
                                        <li>임시 비밀번호로 로그인 시 마이페이지 > 회원정보에서 비밀번호를 변경하시기 바랍니다.</li>
                                    </ul>
                                </div>
                                <ul class="idpw_box">                                    
                                    <li>
                                        <label for="user_name2">이름</label>
                                        <input type="text" name="searchPwName" id="searchPwName" class="input_txt01"  style="min-width:238px;" tabindex="1"
                                               onkeydown="javascript:if(event.keyCode == 13) $('#searchPwId').focus();" 
                                               value=""
                                        >
                                    </li>                                    
                                    <li>
                                        <label for="user_id">아이디</label>
                                        <input type="text" name="searchPwId" id="searchPwId" class="input_txt01"  style="min-width:238px;" tabindex="2"
                                               onkeydown="javascript:if(event.keyCode == 13) $('#searchPwEmail_01').focus();" 
                                               value=""
                                        >
                                    </li>
                                    <li>
                                        <label for="user_email2">이메일 주소</label>
                                        <input type="text" name="searchPwEmail_01" id="searchPwEmail_01" class="input_txt01" style="min-width:148px;" tabindex="3"
                                               onkeydown="javascript:if(event.keyCode == 13) $('#searchPwEmail_02').focus();" 
                                               value=""
                                        >
                                        <span class="dot">@</span>
                                        <input type="text" name="searchPwEmail_02" id="searchPwEmail_02" class="input_txt01" style="min-width:188px;" tabindex="4"
                                               onkeydown="javascript:if(event.keyCode == 13) btnPwFind.click();" 
                                               value=""
                                        >
                                        <span class="sel_box1 type2">
                                            <select id="emailSelectPwSch" style="min-width:150px;" title="이메일주소 선택">
                                                <option value="">직접입력</option>
                                                <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                                    <option value="${emailList.code_name_kor}">${emailList.code_name_kor }</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </li>
                                </ul>
                            </div>
    
                            <div class="btn_area">
                                <a href="javascript:void(0);" id="btnPwCancel" class="btn_type2 type2">취소</a>
                                <a href="javascript:void(0);" id="btnPwFind" class="btn_type1 type2">확인</a>
                            </div>
    
                        </div>
                        <!-- // tab_cont -->
                    </div>
                    
                    <div id="resultTb">
                        <!-- tab_cont -->
                        <div class="tab_cont" id="tab03">
                            <div class="id_search_area">
                                <div class="tit_info">
                                    <%-- <img src="<c:url value='/images/cpt/members/icon_tit_info03.png'/>" alt=""> --%>
                                    <p>
                                        <span>아이디 찾기 결과</span>
                                                           입력하신 정보와 일치하는 아이디입니다.
                                    </p>
                                </div>
    
                                <div class="txt_id">
                                    <strong id="resultId"></strong>
                                    <p class="txt">개인정보보호를 위하여 아이디 일부를 숨김 처리합니다.</p>
                                </div>
                            </div>  
                            
                            <div class="btn_area">
                                <a href="javascript:void(0);" id="btnIdLogin" class="btn_type1 type2">로그인</a>
                            </div>
    
                        </div>
    
                        <div class="tab_cont" id="tab04">
                            
                            <div class="pw_search_area">
                                <div class="tit_info">
                                    <%-- <img src="<c:url value='/images/cpt/members/icon_tit_info04.png'/>" alt=""> --%>
                                    <p>
                                        <span>비밀번호 찾기 결과</span>
                                                           임시비밀번호가 발송되었습니다.
                                    </p>
                                </div>
    
                                <div class="txt_pw">
                                            입력하신 <strong>이메일 주소</strong>(<span id="resultEmail" class="point01"></span>)로 임시비밀번호를 발송하였습니다.<br>
                                            임시비밀번호로 로그인하신 후 회원정보관리에서 비밀번호를 변경해 주시기 바랍니다.
                                </div>                                              
                            </div>  
                            
                            <div class="btn_area">
                                <a href="javascript:void(0);" id="btnPwLogin" class="btn_type1 type2">로그인</a>
                            </div>      
    
                        </div>
                        <!-- // tab_cont -->
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
    </form:form>
</div>

</body>
</html>