<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
    /**
     * @Name : mockupServiceList.jsp
     * @Description : 목업서비스 상세
     * @Modification Information
     *
     * <pre>
     *  Modification Information
     *  수정일        수정자    수정내용
     *  ----------  ------  ----------
     *  2017.02.20  이선하    최초  생성
     * </pre>
     *
     * @author 이선하
     * @since 2017.02.20
     * @version 2.0
     *
     */
    --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
    <script type="text/javascript" src="<c:url value='/js/apt/json2.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/apt/jsonlint.js'/>"></script>
    <script language="javascript" type="text/javascript">

        /*******************************************
         * 전역 변수
         *******************************************/

        /*******************************************
         * 이벤트 함수
         *******************************************/

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/mock/mockUpServiceList.do'/>";
            var param = new Object();
            param.paramMenuId = "14002";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>

            //수정 입력 hide tr
            fn_hideTr()

            //목록
            $("#btnList").click(function(){
                fn_list();
            });

            //저장
            $("#btnSave").click(function(){
                fn_save();
            });

            //삭제
            $("#btnDelete").click(function(){
                fn_delete();
            });

        });

        /*******************************************
         * 기능 함수
         *******************************************/

        <%-- 수정, 입력 구분 --%>
        function fn_hideTr(){
            if(${resultDetail eq null || resultDetail eq ''}){          //insert
                $("#mockupServiceNumberTr").hide();
                $("#createDateTr").hide();
                $("#updateDateTr").hide();
                $("#btnDelete").hide();
                /*서비스 제공자 자리에 paramVO.companyNameKorAlias*/
                $("#companyNameKorAlias").val("${paramVO.companyNameKorAlias}");
                $("#companyCodeId").val("${paramVO.companyCodeId}");
                $("#companyNameHtml").html("${paramVO.companyNameKorAlias}");
                $("#httpStatus").val("").attr("selected", "selected")
                /*method get을 기본으로 체크해줌*/
                $("input:radio[name='methodType']:radio[id='get']").prop("checked",true);
                $("#mockupUriAfterTr").hide();
            }else{                                                      //update
                $("#companyNameHtml").html("${resultDetail.companyNameKorAlias}");
                $("#companyCodeId").val("${resultDetail.companyCodeId}");
                $("#mockupUriTr").hide();



            }


        }

        <%-- 목록 --%>
        function fn_list(){
            //로딩 호출
            gfn_setLoading(false);
            util_moveRequest("MockUpVO", "<c:url value='/apt/mock/mockUpServiceList.do'/>", "_self");
        }

        <%-- 저장 --%>
        function fn_save(){
            if(!fn_validate()){
                return;
            }

            //로딩 호출
            gfn_setLoading(true, "저장중 입니다.");
            var moveUrl = "<c:url value='/apt/mock/saveMockUpService.ajax'/>";
            var param = $("#MockUpVO").serialize();
            var callBackFunc = "fn_saveCallBack";

            util_ajaxPage(moveUrl, param, callBackFunc);
        }

        <%--삭제--%>
        function fn_delete(){
            if(window.confirm("삭제하시겠습니까?")){
                //로딩 호출
                gfn_setLoading(true, "삭제중 입니다.");

                var moveUrl = "<c:url value='/apt/mock/deleteMockUpService.ajax'/>";
                var param = $("#MockUpVO").serialize();
                var callBackFunc = "fn_deleteCallBack";

                util_ajaxPage(moveUrl, param, callBackFunc);
            }
        }

        <%--삭제 후 메세지--%>
        function fn_deleteCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            //로딩 호출
            gfn_setLoading(false);

            if(data.result <= 0){
                alert("<spring:message code='fail.alert.delete' />");
            }else{
                alert("<spring:message code='success.alert.delete' />");
                fn_list();
            }
            return;
        }

        <%--입력 또는 수정 후 메세지--%>
        function fn_saveCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            //로딩 호출
            gfn_setLoading(false);

            //insert
            if((data.insertAfter != "" || data.insertAfter != null) && typeof data.result == 'undefined' ){
                $("#mockupServiceNumber").val(data.insertAfter);
                alert("<spring:message code='success.alert.save' />");
                fn_list();
            }
            //update
            else if((data.updateAfter != "" || data.updateAfter != null) && data.result >=0 && typeof data.insertAfter == 'undefined'){
                $("#mockupServiceNumber").val(data.updateAfter);
                fn_moveDetail();
                alert("<spring:message code='success.alert.modify' />");
            }
            else{
                alert("<spring:message code='fail.alert.save' />");
            }
            return;
        }

        <%-- 추가, 수정 후 상세이동 --%>
        function fn_moveDetail(){
            util_moveRequest("MockUpVO", "<c:url value='/apt/mock/mockUpServiceDtl.do'/>", "_top");
        }

        <%-- 저장전 체크 --%>
        function fn_validate(){

                //==>필수선택값
            //서비스제공자
            if($.trim($("#companyCodeId").val()) == "" || $.trim($("#companyCodeId").val()) == null){
                alert("서비스제공자는 필수 입력값입니다");
                return false;
            }

            //title
            if($.trim($("#title").val()) == "" || $.trim($("#title").val()) == null){
                alert("제목(API이름)은 필수 선택입니다");
                return false;
            }

            //URI
            if($.trim($("#mockupUri").val()) == "" || $.trim($("#mockupUri").val()) == null){
                alert("URI는 필수 입력값입니다");
                return false;
            }


            //Method
            if($(":radio[name='methodType']:checked").val() == "" || $(":radio[name='methodType']:checked").val() == null){
                alert("Method는 필수 선택입니다");
                return false;
            }

            //Http Status  "" 이거나 선택되지 않았을경우
            if($("#httpStatus").val() == "" || $("#httpStatus").val() == null){
                alert("http status는 필수 선택입니다");
                return false;
            }

            //Response Message
            if($.trim($("#responseMessage").val()) == "" || $.trim($("#responseMessage").val()) == null){
                alert("Response Message는 필수 입력값입니다");
                return false;
            }else{
                if(jsonValidation($.trim($("#responseMessage").val()), "responseMessage")){
                    alert("ResponseMessage가 JSON형식에 맞지 않습니다.");
                    return false;
                }
            }


            //==>json 형식 validation
            if($.trim($("#headerInfo").val()) != ""){
                if(jsonValidation($.trim($("#headerInfo").val()), "headerInfo")){
                    alert("Header이(가) JSON형식에 맞지 않습니다.");
                    return false;
                }

                if(jsonObjectValid($.trim($("#headerInfo").val())) > 1){
                    alert("Header은(는) key : value 형식으로 입력 가능합니다.");
                    return false;
                }
            }
            if($.trim($("#queryStringInfo").val()) != ""){
                if(jsonValidation($.trim($("#queryStringInfo").val()), "queryStringInfo")){
                    alert("queryString이(가) JSON형식에 맞지 않습니다.");
                    return false;
                }

                if(jsonObjectValid($.trim($("#queryStringInfo").val())) > 1){
                    alert("QueryString은(는) key : value 형식으로 입력 가능합니다.");
                    return false;
                }
            }
            if($.trim($("#bodyInfo").val()) != ""){
                if(jsonValidation($.trim($("#bodyInfo").val()), "bodyInfo")){
                    alert("Body이(가) JSON형식에 맞지 않습니다.");
                    return false;
                }

                if(jsonObjectValid($.trim($("#bodyInfo").val())) > 1){
                    alert("Body은(는) key : value 형식으로 입력 가능합니다.");
                    return false;
                }
            }
            return true;
        }

        <!--JSON 형식 validation-->
        function jsonValidation(contents, paramId){

            try {
                var result = jsonlint.parse(contents);
                if(result){
                    //textarea value값 json형식 줄바꿈 후 입력 하기
                    var jsonAfter = JSON.stringify(result, null, "    ");
                    var stringPraimId = "#" + paramId;
                    $(stringPraimId).val(jsonAfter);
                    return false;
                }
            }catch(e){
                //console.log("error : " + e);
                return true;
            }

        }

        <!--JSON Object Validation-->
        function jsonObjectValid(contents){
            var result = contents.match(/{/g);
            if(result != null){
                return result.length;
            }
        }

    </script>

</head>

<body>
<form:form commandName="MockUpVO" name="MockUpVO" method="post" enctype='multipart/form-data'>
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>"/>

    <input type="hidden" name="mockupServiceNumber" id="mockupServiceNumber" value="<c:out value='${resultDetail.mockupServiceNumber}'/>" />

    <%--검색조건--%>
    <input type="hidden" id="searchCondition" name="searchCondition" value="${paramVO.searchCondition}"/>
    <input type="hidden" id="searchKeyword" name="searchKeyword" value="${paramVO.searchKeyword}"/>
    <input type="hidden" id="searchDateType" name="searchDateType" value="${paramVO.searchDateType}"/>
    <input type="hidden" id="searchDateFrom" name="searchDateFrom" value="${paramVO.searchDateFrom}"/>
    <input type="hidden" id="searchDateTo" name="searchDateTo" value="${paramVO.searchDateTo}"/>
    <input type="hidden" id="searchPubcompanyCodeId" name="searchPubcompanyCodeId" value="${paramVO.searchPubcompanyCodeId}"/>
    <input type="hidden" id="searchMethod" name="searchMethod" value="${paramVO.searchMethod}"/>
    <input type="hidden" id="searchHttpStatus" name="searchHttpStatus" value="${paramVO.searchHttpStatus}"/>

    <%--<input type="hidden" name="searchPubcompanyCodeIdAllYn" id="searchPubcompanyCodeIdAllYn" value="${paramVO.searchPubcompanyCodeIdAllYn}" />--%>
    <%--<input type="hidden" name="searchMethodAllYn" id="searchMethodAllYn" value="${paramVO.searchMethodAllYn}"/>--%>
    <%--<input type="hidden" name="searchHttpStatusAllYn" id="searchHttpStatusAllYn" value="${paramVO.searchHttpStatusAllYn}"/>--%>

    <input type="hidden" name="companyCodeId" id="companyCodeId" value="" />
    <input type="hidden" name="companyNameKorAlias" id="companyNameKorAlias" value="" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    <%--<input type="hidden" name="companyCodeId" value="<c:out value='${paramVO.companyCodeId}'/>"/>--%>

    <!-- // wrap_top -->
    <article class="container">
        <div>
                <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
                <%-- lnb(좌측메뉴) 영역 --%>
            <!-- // lnb -->
            <section class="content">
                <div class="location">
                    <h2>목업 메시지 관리</h2>
                </div>
                <!-- // locatioin -->

                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>

                <div class="tb_write1">
                    <table>
                        <caption>정보 입력</caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">서비스 제공자<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l" id="companyNameHtml"></td>
                        </tr>
                        <tr id="mockupServiceNumberTr">
                            <th scope="row">Msg.ID</th>
                            <td class="txt_l">${resultDetail.mockupServiceNumber}</td>
                        </tr>
                        <tr id="mockupUriAfterTr">
                            <th scope="row"><label for="chk2">URI<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">${resultDetail.mockupUri}</td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="chk1">제목(API 이름)<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td class="txt_l">
                                <input type="text" class="w300" id="title" name="title" value="${resultDetail.title}">
                            </td>
                        </tr>
                        <tr id="mockupUriTr">
                            <th scope="row"><label for="chk2">URI<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">&nbsp;/mockupservice/&nbsp;&nbsp;
                                <input type="text" class="w300" id="mockupUri" name="mockupUri" style="width: 40%;" value="${resultDetail.mockupUri}">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Method<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <ul class="wrap_input">
                                    <li>
                                        <input type="radio" name="methodType" id="get" value="GET"
                                               <c:if test="${resultDetail.methodType eq 'GET'}">checked</c:if>>
                                        <label for="k1">GET</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="methodType" id="post" value="POST"
                                               <c:if test="${resultDetail.methodType eq 'POST'}">checked</c:if>>
                                        <label for="k2">POST</label>
                                    </li>
                                    <li>
                                        <input type="radio" name="methodType" id="put" value="PUT"
                                               <c:if test="${resultDetail.methodType eq 'PUT'}">checked</c:if>>
                                        <label for="k3">PUT</label>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="t1">Header</label></th>
                            <td class="txt_l">
                                <textarea name="headerInfo" id="headerInfo" cols="30" rows="10">${resultDetail.headerInfo}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="t2">QueryString</label></th>
                            <td class="txt_l">
                                <textarea name="queryStringInfo" id="queryStringInfo" cols="30" rows="10">${resultDetail.queryStringInfo}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="t3">Body</label></th>
                            <td class="txt_l">
                                <textarea name="bodyInfo" id="bodyInfo" cols="30" rows="10">${resultDetail.bodyInfo}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk5">http status<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">
                                <select id="httpStatus" name="httpStatus" style="min-width:100px;">
                                    <option value="">선택</option>
                                    <c:forEach items="${httpStatusList}" var="httpStatusList" varStatus="status">
                                        <option value="${httpStatusList.system_code}" <c:if test="${resultDetail.httpStatus eq httpStatusList.system_code}">selected</c:if>>${httpStatusList.code_name_kor}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="t4">Response message<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">
                                <textarea name="responseMessage" id="responseMessage" cols="30" rows="10">${resultDetail.responseMessage}</textarea>
                            </td>
                        </tr>
                        <tr id="createDateTr">
                            <th scope="row">등록일</th>
                            <td class="txt_l">${resultDetail.createDate}</td>
                        </tr>
                        <tr id="updateDateTr">
                            <th scope="row">수정일</th>
                            <td class="txt_l">${resultDetail.updateDate}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <div class="left"><span class="btn_gray2"><a href="javascript:void(0);" id="btnList">목록</a></span></div>
                    <div class="right">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnDelete">삭제</a></span>
                    </div>
                </div>
                <!-- // btn_type3 -->

            </section>
            <!-- // content -->
        </div>
    </article>
</form:form>
</body>
</html>