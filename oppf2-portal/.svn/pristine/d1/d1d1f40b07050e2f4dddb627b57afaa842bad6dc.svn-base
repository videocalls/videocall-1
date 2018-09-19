<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
       /**
        * @Name : simulatorManageDtl.jsp
        * @Description : simulator 상세 조회 목록
        * @Modification Information
        *
        * <pre>
        *  Modification Information
        *  수정일        수정자    수정내용
        *  ----------  ------  ----------
        *  2017.03.27  이선하    최초  생성
        * </pre>
        *
        * @author 이선하
        * @since 2017.03.27
        * @version 2.0
        *
        */
       --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

    <script language="javascript" type="text/javascript">
        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/simul/simulatorManageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15005";

            gfn_loginNeedMove(url, param);
        }


        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>


            <c:choose>
            <c:when test="${empty resultData.createDate || resultData.createDate eq null}">
            $("#createDateTr").hide();
            $("#updateDateTr").hide();
            $("#btnDelete").hide();
            $("#hInsert").show();
            $("#hUpdate").hide();
            $("#excutionRate").val(100);
            $("#rejectRate").val(10);
            </c:when>
            <c:otherwise>
            $("#createDateTr").show();
            $("#updateDateTr").show();
            $("#createDateTd").html("${resultData.createDate}");
            $("#updateDateTd").html("${resultData.updateDate}");
            $("#excutionRate").val("${resultData.excutionRate}");
            $("#rejectRate").val("${resultData.rejectRate}");
            $("#btnDelete").show();
            $("#hInsert").hide();
            $("#hUpdate").show();

            </c:otherwise>
            </c:choose>

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

        <%-- 목록 --%>
        function fn_list(){
            $("#excutionRate").val(0);
            $("#rejectRate").val(0);

            //로딩 호출
            gfn_setLoading(false);

            util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatorManageList.do'/>", "_self");
        }

        <%-- 저장 --%>
        function fn_save(){
            if(!fn_validate()){
                return;
            }
            //로딩 호출
            gfn_setLoading(true, "저장중 입니다.");
            var moveUrl = "<c:url value='/apt/simul/saveSimulatorManage.ajax'/>";
            var param = $("#SimulatorManageVO").serialize();
            var callBackFunc = "fn_saveCallBack";

            util_ajaxPage(moveUrl, param, callBackFunc);
        }

        <%-- 삭제 --%>
        function fn_delete(){
            if(confirm("삭제하시겠습니까?")){
                gfn_setLoading(true, "삭제중 입니다.");
                var moveUrl = "<c:url value='/apt/simul/deleteSimulatorManage.ajax'/>";
                var param = $("#SimulatorManageVO").serialize();
                var callBackFunc = "fn_deleteCallBack";

                util_ajaxPage(moveUrl, param, callBackFunc);
            }
        }

        <%-- 상세이동 --%>
        function fn_moveDetail(data){
            util_moveRequest("SimulatorManageVO", "<c:url value='/apt/simul/simulatorManageDtl.do'/>", "_top");
        }

        <%-- validation --%>
        function fn_validate(){
            var excutionRate = $("#excutionRate").val();
            var rejectRate = $("#rejectRate").val();

            if(excutionRate == "" || excutionRate == null){
                alert("주문수량 대비 체결량 은(는) 필수 입력값입니다.");
                return false;
            }
            if(rejectRate == "" || rejectRate == null){
                alert("거부율 은(는) 필수 입력값입니다.");
                return false;
            }
            return true;
        }


        function fn_saveCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            //로딩 호출
            gfn_setLoading(false);

            if(data.result != "200"){
                alert("<spring:message code='fail.alert.save' />");
            }else{
                alert("<spring:message code='success.alert.save' />");
                if(data.afterState == 'insert'){
                    fn_list();
                }else{
                    fn_moveDetail();
                }
            }
            return;
        }

        function fn_deleteCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            //로딩 호출
            gfn_setLoading(false);

            if(data.result <= 0){
                //console.log("delete fail");
            }else{
                alert("<spring:message code='success.alert.delete' />");
                fn_list();
            }
            return;
        }

    </script>
</head>

<body>
<form:form commandName="SimulatorManageVO" name="SimulatorManageVO" method="post" enctype='multipart/form-data'>
    <input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>"/>

    <input type="hidden" name="senderCompId" id="senderCompId" value="<c:out value='${paramVO.senderCompId}'/>"/>
    <input type="hidden" name="senderCompName" id="senderCompName" value="<c:out value='${paramVO.senderCompName}'/>"/>
    <input type="hidden" name="companyId" id="companyId" value="<c:out value='${paramVO.companyId}'/>"/>

    <input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${paramVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${paramVO.searchKeyword}'/>"/>
    <input type="hidden" name="fixBuySide" id="fixBuySide" value="<c:out value='${paramVO.fixBuySide}'/>"/>
    <input type="hidden" name="searchDateType" id="searchDateType" value="${paramVO.searchDateType}"/>
    <input type="hidden" name="searchDateFrom" id="searchDateFrom" value="${paramVO.searchDateFrom}"/>
    <input type="hidden" name="searchDateTo" id="searchDateTo" value="${paramVO.searchDateTo}"/>


    <!-- // wrap_top -->
    <article class="container">
        <div>
                <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
                <%-- lnb(좌측메뉴) 영역 --%>
            <!-- // lnb -->


            <section class="content">
                <div class="location">
                    <h2 id="hInsert">Simulator 케이스 등록</h2>
                    <h2 id="hUpdate">Simulator 케이스 수정</h2>
                </div>
                <!-- // locatioin -->

                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>

                <div class="tb_write1">
                    <table>
                        <caption>Simulator 케이스 등록</caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">기업 이름(한글)</th>
                            <td class="txt_l"><c:out value='${paramVO.senderCompId}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">기업코드</th>
                            <td class="txt_l"><c:out value='${paramVO.senderCompName}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">CompID</th>
                            <td class="txt_l"><c:out value='${paramVO.companyId}'/></td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">주문수량 대비 체결량<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">
                                <input type="number" class="w50" id="excutionRate" name="excutionRate">&nbsp;<span class="vertical_middle">%</span>
                                <span class="ml10 vertical_middle">예) 100%로 설정하면 전량체결, 50%로 설정하면 50%수량 부분체결</span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk3">거부율<span class="icon_basic">*필수입력</span></label></th>
                            <td class="txt_l">
                                <input type="number" class="w50" id="rejectRate" name="rejectRate">&nbsp;<span class="vertical_middle">%</span>
                                <span class="ml10 vertical_middle">예) 10%로 설정하면, 전체 주문 대비 10%는 거부 발생</span>
                            </td>
                        </tr>
                        <tr id="createDateTr">
                            <th scope="row">등록일시</th>
                            <td class="txt_l" id="createDateTd"></td>
                        </tr>
                        <tr id="updateDateTr">
                            <th scope="row">수정일시</th>
                            <td class="txt_l" id="updateDateTd"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
                    <div class="right">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnDelete">삭제</a></span>
                    </div>
                </div>
                <!-- // btn_type3 -->

            </section>
        </div>
    </article>
</form:form>
</body>
</html>