<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptPwFind.jsp
 * @Description : 일반사용자 패스워드 찾기
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

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
   
    //확인
    $("#btnFind").bind("click", function(){
        fn_find();
    });
    
    //취소
    $("#btnFind").bind("click", function(){
        $('#searchName').val("");
        $('#searchId').val("");
        $('#searchEmail').val("");
        
        $("#resultEmail").html("");
        $("#resultTb").hide();
        
        $('#searchName').focus();
    });
    
    //로그인
    $("#btnLogin").bind("click", function(){
        util_movePage("<c:url value='/spt/cmm/loginView.do'/>");
    });
    
    //이메일 주소 타입 변경 시
    $('#emailSelect').change(function(){
        if($(this).val()==""){
            $('#searchEmail_02').val("");
            $('#searchEmail_02').attr("disabled",false);
        }else{
            $('#searchEmail_02').val($(this).val());
            $('#searchEmail_02').attr("disabled",true);
        }
    });
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 찾기 --%> 
function fn_find(){
    if(!fn_validate()){
        return;
    }
    
    var moveUrl = "<c:url value='/spt/cmm/selectPwFind.ajax'/>";
    var param = $("#SptLoginVO").serialize();
    var callBackFunc = "fn_findCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_findCallBack(data){
    var result = data.resultData;
    
    if(result != null){
    	$("#resultEmail").html(result.customer_email);
        $("#resultTb").show();
        
        $("#findTb").hide();
    }else{
        alert("<spring:message code='errors.idfind' />");
        $('#searchName').focus();
        return;
    }
}

<%-- validate --%>
function fn_validate(){
    //이름
    if(util_chkReturn($.trim($('#searchName').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이름'/>");
        $('#searchName').val("");
        $('#searchName').focus();
        return false;
    }
        
    //아이디
    if(util_chkReturn($.trim($('#searchId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='아이디'/>");
        $('#searchId').val("");
        $('#searchId').focus();
        return false;
    }
    
    //이메일
    var email = $.trim($('#searchEmail_01').val()) + "@" + $.trim($('#searchEmail_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='이메일 주소'/>");
        $('#searchEmail_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='이메일 주소'/>");
            $('#searchEmail_01').focus();
            return false;
        }
    }
    $("#searchEmail").val(email);
    
    return true;
    
}
 
</script>
</head>
<body>

<center>
<br/><br/><br/>
<form:form commandName="SptLoginVO" name="SptLoginVO" method="post">
<form:hidden path="tabId" id="tabId" /><%-- tabId --%>
<input type="hidden" id="searchEmail" name="searchEmail" />

<table border="1" align="center" style="width: 600px;">
    <colgroup>
        <col style="width:100px;" />
        <col style="width:500px;" />
    </colgroup>
    <tbody id="findTb">
        <tr>
            <td colspan="3" align="center">아이디 찾기</td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type="text" name="searchName" id="searchName" value="" style="width: 90%" tabindex="1" /></td>
        </tr>
        <tr>
            <th>아이디</th>
            <td><input type="text" name="searchId" id="searchId" value="" style="width: 90%" tabindex="1" /></td>
        </tr>
        <tr>
            <th>이메일 주소</th>
            <td>
                <input type="text" name="searchEmail_01" id="searchEmail_01" value="" style="width: 30%"  tabindex="2" />@
                <input type="text" name="searchEmail_02" id="searchEmail_02" value="" style="width: 30%"  tabindex="2" />
                <span>
                <select id="emailSelect" class="select" title="이메일선택" >
                    <option value="">직접입력</option>
                    <c:forEach var="emailList" items="${emailList}" varStatus="status">
                        <option value="${emailList.code_name_kor}">${emailList.code_name_kor }</option>
                    </c:forEach>
                </select>
                </span>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" id="btnCancel" value="취소" />
                <input type="button" id="btnFind" value="확인" />
                
            </td>
        </tr>
    </tbody>
    <tbody id="resultTb" style="display: none;">
        <tr>
            <th scope="row" colspan="2" align="left"><span><label><b>비밀번호 찾기 결과</b></label></span></th>
        </tr>
        <tr>
            <th scope="row" colspan="2" align="left">
                                임시비밀번호가 발송되었습니다<br><br>
                                입력하신 이메일 주소(<span id="resultEmail"></span>)로 임시비밀번호를 발송하였습니다.
                                임시비밀번호로 로그인하신 후 회원정보관리에서 비밀번호를 변경해 주시기 바랍니다.
            </th>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" id="btnLogin" value="로그인" />
                
            </td>
        </tr>
    </tbody>
</table>
</form:form>
</center>
</body>
</html>