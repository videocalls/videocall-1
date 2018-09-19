<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg1.jsp
 * @Description : [회원가입:3.약관동의]
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
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/
 
/*유효성검증 함수*/
function fn_SaveValidate(){
    
    var TermChkCnt = 0;
    
    $("input[name='chkCustomerTerms']").each(function(idx){
        if($(this).is(":checked")){
            TermChkCnt++;
        }else{
            var alertMsg = $("#txtTermsTitle_"+idx).val();
            alert("<spring:message code='errors.required.select' arguments='"+alertMsg+"'/>");
            return false;
        }
    });
    
    if(TermChkCnt == $("input[name='chkCustomerTerms']").length){
        return true;
    }else{
        return false;
    }
    
    /*
    //[서비스이용약관] 체크박스
    if( !$("input:checkbox[id='customerTerms003_1']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='서비스이용약관'/>");
        $("#customerTerms003_1").focus();
        return false;
    }
    
    //[개인정보취급방침] 체크박스
    if( !$("input:checkbox[id='customerTerms003_2']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='개인정보취급방침'/>");
        $("#customerTerms003_2").focus();
        return false;
    }
    
    //[개인정보수집이용동의] 체크박스
    if( !$("input:checkbox[id='customerTerms003_3']").is(":checked") ){
        alert("<spring:message code='errors.required.select' arguments='개인정보수집이용동의'/>");
        $("#customerTerms003_3").focus();
        return false;
    }
    return true;
    */
    
}
 
 
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
 

/*******************************************
 * 이벤트 함수
 *******************************************/
 
/* 화면 로드 처리 */
$(document).ready(function(){
    
    /* [전체약관동의]체크박스 이벤트발생 시 호출 */
    //$("#allchkCustomerTerms").bind("change", function(e){
    $(".allCheck").bind("change", function(e){
        if($(this).is(":checked")){
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",true); 
            });
            
            $("#allchkCustomerTerms").prop("checked",true);
            $("#allchkCustomerTermsBottom").prop("checked",true);
        }else{
            $("input[name='chkCustomerTerms']").each(function(){
                $(this).prop("checked",false);                
            });
            
            $("#allchkCustomerTerms").prop("checked",false);
            $("#allchkCustomerTermsBottom").prop("checked",false);
        }
    });
        
    /* [동의하지않음] 이벤트발생 시 호출 */
    $("#btnTermsDisagree").bind("click", function(e){
        var msgAlert = "<spring:message code='errors.terms.disagree'/>";
        alert(msgAlert);
    });
    
    /* [동의] 이벤트발생 시 호출 */
    $("#btnTermsAgree").bind("click", function(e){
        
        if( !fn_SaveValidate() ){ //유효성검증호출
            return false;
        }
        
        var customerRegNo  = $("#customerRegNo").val();  //회원OP등록번호
        
        //약관배열설정
        var objParam2List = new Array();
        $("input[name='chkCustomerTerms']").each(function(){
            if($(this).is(":checked")){
                var objParam2 = new Object();
                var tmpIdArr = $(this).attr("id").split("_");
                var customerTermsType = '';
                if(tmpIdArr.length > 1){
                    customerTermsType = tmpIdArr[1];
                }
                var customerTermsContentRegSeq = $(this).val();
                objParam2.customerRegNo = customerRegNo;
                objParam2.customerTermsType = customerTermsType; //회원동의종류[G008(001:서비스이용약관, 002:개인정보취급방침, 003:개인정보수집이용동의, 010:공인인증서등록약관동의, 020:금융제공동의서)]
                objParam2.customerTermsContentRegSeq = customerTermsContentRegSeq;
                objParam2.customerTermsAuthYn = 'Y';
                objParam2List.push(objParam2);
            }
        });
        
        //파라미터셋팅
        var objParam = new Object();
        objParam.customerRegNo     = customerRegNo;
        objParam.customerTermsList = objParam2List;
        
        var url = "<c:url value='/usr/mbrReg/saveMbrReg3.ajax'/>";
        $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정 
        $.ajax({
            type    : "post"
            ,contentType: "application/json"
           ,async   : false
            ,url     : url
            ,data    : JSON.stringify(objParam)
           ,success : function(data){
               //var msgAlert = "<spring:message code='success.alert.regist'/>";
               //alert(msgAlert);
               objParam.customerStep = 'G006004'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
               objParam.customerVerify = $("#customerVerify").val(); //회원검증값
               util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>",objParam);
           }
           ,error   : function(){
               var msgAlert = "<spring:message code='fail.alert.regist'/>";
               alert(msgAlert);
           }
       });

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
                
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>본인인증</div></li><!-- 지나간step -->
                            <li class="pass"><div>공인인증서 등록</div></li><!-- 지나간step -->
                            <li class="on"><div>약관동의</div></li><!-- 현재step -->
                            <li><div>개인정보 입력</div></li>
                            <li class="last"><div>이메일 인증</div></li>
                        </ul>
                    </div>
                    <div class="title_wrap">
                        <p class="title_01">약관동의</p>
                    </div>
                    
                    <input type="hidden" name="customerRegNo"  id="customerRegNo"  value="<c:out value="${paramVO.customerRegNo}" />" />
                    <input type="hidden" name="customerVerify" id="customerVerify" value="<c:out value="${paramVO.customerVerify}" />" />
            
                    <div class="total_chk">
                        <input type="checkbox" class="allCheck" name="allchkCustomerTerms" id="allchkCustomerTerms">
                        <label for="allchkCustomerTerms" class="chk_box">서비스 이용약관, 개인정보 수집 및 이용 동의, 개인정보 제3자 제공동의에 모두 동의 합니다. (필수)</label>                        
                    </div>
                    
                <c:forEach var="tcList" items="${termsContentList}" varStatus="status">
                    <div class="privercy_box <c:if test='${status.index == 0}'>marT0</c:if>">
                        <p class="privercy_tit">
                            <c:out value='${tcList.customerTermsTypeName}'/>
                            <input type="hidden" name="txtTermsTitle" id="txtTermsTitle_${status.index}" value="<c:out value='${tcList.customerTermsTypeName}'/>"/>
                        </p>
                        <div class="privercy_con">
                            <div class="privercy_txt">
                                <p class="txt_tit">
                                    <c:out value='${tcList.customerTermsTypeName}'/>
                                </p>
                                ${fn:replace( tcList.customerTermsContent, newLineChar, '<br/>')}
                                <br/>
                            </div>
                            <div class="privercy_chk">
                                <input type="checkbox" name="chkCustomerTerms" id="chkCustomerTerms_${tcList.customerTermsType}" value="${tcList.customerTermsContentRegSeq}" />
                                <label for="chkCustomerTerms_${tcList.customerTermsType}" class="chk_box">
                                    <c:out value='${tcList.customerTermsTypeName}'/>에 동의합니다. (필수)
                                </label>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                    <div class="total_chk type2">
                        <input type="checkbox" class="allCheck" name="allchkCustomerTermsBottom" id="allchkCustomerTermsBottom">
                        <label for="allchkCustomerTermsBottom" class="chk_box">서비스 이용약관, 개인정보 수집 및 이용 동의, 개인정보 제3자 제공동의에 모두 동의 합니다. (필수)</label>                     
                    </div>
                
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnTermsDisagree" class="btn_type2">동의하지 않음</a>
                        <a href="javascript:void(0);" id="btnTermsAgree" class="btn_type1">동의</a>
                    </div>
                
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