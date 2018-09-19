<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <%--
    /**
     * @Name : dataSetCopyPopup.jsp
     * @Description : 데이타셋 카피할 아이디 검색 팝업
     * @Modification Information
     *
     * <pre>
     *  Modification Information
     *  수정일        수정자     수정내용
     *  ----------  ------  ----------
     *  2017.02.28  이선하     최초  생성
     * </pre>
     *
     * @author 이선하
     * @since 2017.02.28
     * @version 2.0
     *
     */
    --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>


    <script type="text/javascript">

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/data/dataSetManagementList.do'/>";
            var param = new Object();
            param.paramMenuId = "14001";

            gfn_loginNeedMove(url, param);
        }

        $(document).ready(function(){

            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            $(".wrap >").remove();
            if (opener) {
                window.opener.fn_login();
                window.close();
            } else {
                window.parent.fn_login();
                gfn_closeModal();
            }
            </c:if>

            $("#layerClose").click(function(){
                fn_popupClose();
            });


            //계좌 복사
            $("#btnCopyAccount").click(function(){
                fn_copyAccount();
            });

            //아이디 확인
            $("#btnCheckId").click(function(){
                fn_checkId();
            });

            //enter Key Peress
            $("input[type=text]").keypress(function(e){
               if(e.keyCode == 13){
                   fn_checkId();
               }
            });

            //재입력 클릭시
            $("#btnIdReInput").click(function(){
                fn_IdReInput();
            });



        });

        <%-- 계좌 복사 --%>
        function fn_copyAccount(){
            if(util_chkReturn($('#customerId').val(), "s") == "") {
                alert("<spring:message code='errors.required' arguments='아이디'/>");
                fn_restartPopup();
                return false;
            }else if($("#btnIdReInput").css("display") == "none"){
                alert("아이디를 확인하셔야 합니다.");
                return false;
            }else if(!$("input:radio[name=copyInfo]").is(":checked")){
                alert("선택하신 계좌가 없습니다.");
                return false;
            }else{
                //로딩 호출
                gfn_setLoading(true);

                var copyInfo = $("input:radio[name=copyInfo]:checked").val();
                var copyInfoArray = copyInfo.split(',');
                $("#copyCustomerVtaccountNumber").val(copyInfoArray[0]);
                $("#copyCustomerRealaccountNumber").val(copyInfoArray[1]);
                $("#copyCompanyCodeId").val(copyInfoArray[2]);
//
//                if($("#copyCustomerRegNumber").val()==$("#customerRegNumber").val() &&
//                        $("#copyCustomerVtaccountNumber").val()==$("#customerVtaccountNumber").val() &&
//                        $("#copyCustomerRealaccountNumber").val()==$("#customerRealaccountNumber").val() &&
//                        $("#copyCompanyCodeId").val()==$("#companyCodeId").val()){
//                    alert("동일계좌에는 복사 불가능 합니다.");
//                    $("input:radio[name=copyInfo]").removeAttr("checked");
//
//                    gfn_setLoading(false);
//                    return false;
//                }
                //page setting
                var url = "<c:url value='/apt/data/dataSetCopyAccount.ajax'/>";
                var param = $("#DataSetManageVO").serialize();
                var callBackFunc = "fn_copyAccountCallBack";

                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            }
        }

        function fn_copyAccountCallBack(data){
            //로그인 처리
            if (data.error == -1) {
                fn_login();
                return;
            }
            //로딩 호출
            gfn_setLoading(false);

            alert("복사(덮어쓰기)가 완료되었습니다.");
            /**
             * 완료
             * */
            fn_addCompanyCallBack();
        }


        <%-- ID 확인 버튼 클릭 --%>
        function fn_checkId() {
            if(util_chkReturn($('#customerId').val(), "s") == ""){
                alert("<spring:message code='errors.required' arguments='아이디'/>");
                fn_restartPopup();
                return false;
            }else{
                //로딩 호출
                gfn_setLoading(true);

                //page setting
                var url = "<c:url value='/apt/data/dataSetManagementList.ajax'/>";
                var param = $("#DataSetManageVO").serialize();
                var callBackFunc = "fn_checkIdCallBack";

                <%-- 공통 ajax 호출 --%>
                util_ajaxPage(url, param, callBackFunc);
            }
        }


        <%-- ID 확인 callback --%>
        function fn_checkIdCallBack(data) {
            //로그인 처리
            if (data.error == -1) {
                fn_login();
                return;
            }
            //로딩 호출
            gfn_setLoading(false);

            if (data.resultList != null && data.resultList != '') {

                if(data.resultList[0].customerDatasetLockYn == 'Y'){
                    alert("데이터셋 잠금 ‘사용’으로 설정되어 변경이 불가능합니다. ");
                    fn_restartPopup();
                }else{
                    var resultList = data.resultList;
                    var customerNameKor = resultList[0].customerNameKor;
                    var copyCustomerRegNumber = resultList[0].customerRegNumber;

                    $("#customerNameKor").text(customerNameKor);

                    $("#customerId").css("background-color","silver");
                    $("#customerId").attr("disabled", true);
                    $("#btnCheckId").hide(); //[중복확인]
                    $("#btnIdReInput").show(); //[아이디재입력]
                    $("#customerId").focus();
                    $("#copyCustomerRegNumber").val(copyCustomerRegNumber);
                    $("#accNoTd").append("<ul class='ml10' id='accNoList' name='accNoList' style='height:70px; overflow-y:auto;''>");

                    for(var idx=0; idx<resultList.length; idx++) {	// 새로 가져온 데이터를 데이터 갯수만큼 반복해서 붙여준다.
                        var key = resultList[idx].customerVtaccountNumber + ',' + resultList[idx].customerRealaccountNumber + ',' + resultList[idx].companyCodeId;

                        if($("#customerRegNumber").val() == $("#copyCustomerRegNumber").val() &&
                                $("#customerVtaccountNumber").val() == resultList[idx].customerVtaccountNumber &&
                                $("#customerRealaccountNumber").val() == resultList[idx].customerRealaccountNumber &&
                                $("#companyCodeId").val() == resultList[idx].companyCodeId){
                            $("#accNoList").append("<li><input type='radio' name='copyInfo' id='"+key+"' value='"+key+"' disabled> <label for='"+key+"' class='chk_box'>"+ resultList[idx].companyNameKorAlias + "(" + resultList[idx].customerVtaccountAlias + ") / "+ resultList[idx].customerVtaccountNumber + "</label></li>");
                        }else{
                            $("#accNoList").append("<li><input type='radio' name='copyInfo' id='"+key+"' value='"+key+"'> <label for='"+key+"' class='chk_box'>"+ resultList[idx].companyNameKorAlias + "(" + resultList[idx].customerVtaccountAlias + ") / "+ resultList[idx].customerVtaccountNumber + "</label></li>");
                        }

                    }
                    $("#accNoTd").append("</ul>");
                }
            } else {
                alert("조회된 아이디가 없습니다.");
                fn_restartPopup();

            }

            return;

        }
        function fn_restartPopup(){
            util_moveRequest("DataSetManageVO", "<c:url value='/apt/data/dataSetCopyPopup.do'/>", "_self");
        }

        <%-- 재입력 클릭시 --%>
        function fn_IdReInput() {
            fn_restartPopup();

        }

        /*복사 후 funtion*/
        function fn_addCompanyCallBack(data){
            util_moveRequest("DataSetManageVO", "<c:url value='/apt/data/dataSetManagementDtl.do'/>", "_top");
        }

            /* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
        function fn_popupClose() {
            if (opener) {
                window.close();
            } else {
                gfn_closeModal(this.event);
            }
        }
    </script>
</head>
<body>
<form:form commandName="DataSetManageVO" name="DataSetManageVO" method="post">
<div class="wrap">
    <input type="hidden" id="customerRegNumber" name="customerRegNumber" value="${paramVO.customerRegNumber}"/>
    <input type="hidden" id="companyCodeId" name="companyCodeId" value="${paramVO.companyCodeId}"/>
    <input type="hidden" id="customerRealaccountNumber" name="customerRealaccountNumber" value="${paramVO.customerRealaccountNumber}"/>
    <input type="hidden" id="customerVtaccountNumber" name="customerVtaccountNumber" value="${paramVO.customerVtaccountNumber}"/>

    <input type="hidden" id="copyCustomerRegNumber" name="copyCustomerRegNumber"/>
    <input type="hidden" id="copyCustomerRealaccountNumber" name="copyCustomerRealaccountNumber"/>
    <input type="hidden" id="copyCompanyCodeId" name="copyCompanyCodeId"/>
    <input type="hidden" id="copyCustomerVtaccountNumber" name="copyCustomerVtaccountNumber"/>

    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">

        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">데이터 셋 복사</div>

            <div class="layer_con">

                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>

                <div class="tb_write1">
                    <table>
                        <caption>데이터 셋 복사</caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">아이디</label></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="customerId" name="customerId">&nbsp;
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckId">확인</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display: none;">재입력</a></span></td>
                        </tr>
                        <tr>
                            <th scope="row">이름</th>
                            <td class="txt_l"><em id="customerNameKor"></em></td>
                        </tr>
                        <tr>
                            <th scope="row">복사(덮어쓰기)할계좌<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l scroll" id="accNoTd" name="accNoTd">
                            </td>
                        </tr>
                        <!--// 일치하는 아이디가 없을 경우 -->
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnCopyAccount" name = "btnCopyAccount">저장</a></span>
                </div>
                <!-- // btn_type3 -->
            </div>
            <a href="javascript:void(0);" class="layer_close" id="layerClose">레이어팝업 닫기</a>
        </div>
    </div>
</div>
</form:form>
</body>
</html>





