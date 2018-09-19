<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%@taglib prefix="cutil" uri="tld/CommonUtil.tld" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
    /**
     * @Name : viewFixMessageDtlPopup.jsp
     * @Description : [FIX Message]Initiator & BuySide Excel 목록
     * @Modification Information
     *
     * <pre>
     *  Modification Information
     *  수정일        수정자    수정내용
     *  ----------  ------  ----------
     *  2017.03.03  이선하    최초  생성
     * </pre>
     *
     * @author 이선하
     * @since 2017.03.03
     * @version 2.0
     *
     */
    --%>
    <title>koscom OpenAPI Platform 통합관리시스템</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../../css/apt/import.css">
    <!-- IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="../../js/cmm/html5shiv.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="<c:url value='/js/apt/json2.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/apt/jsonlint.js'/>"></script>
    <script type="text/javascript" src="../../js/cmm/jquery-1.11.3.min.js"></script>

    <script type="text/javascript" src="../../js/apt/common_pub.js"></script>

    <script language="javascript" type="text/javascript">
        function fn_login(){
            var url = "<c:url value='/apt/fixMsg/viewFixMessageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15001";
        }



        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>

            $("#btnClose").click(function(){
                popupClose();
            });

            <c:if test="${fixMessage != null}">
            <c:if test="${!empty fixMessage.dataRecvJson}">
            try{
                var result = jsonlint.parse('${cutil:escapeJS(fixMessage.dataRecvJson)}');
                if(result){
                    var dataRecvJson = JSON.stringify(result, null, "    ");
                    $("#jsonData").text(dataRecvJson);
                    //recv
                }
            }catch(e){
                //console.log("error : " + e);
                return ;
            }


            </c:if>

            <c:if test="${!empty fixMessage.dataSendJson}">
            $("orderTd").show();
            try{
                var result = jsonlint.parse('${cutil:escapeJS(fixMessage.dataSendJson)}');
                if(result){
                    var dataSendJson = JSON.stringify(result, null, "    ");
                    $("#jsonData").text(dataSendJson);
                    //send
                }
            }catch(e){
                //console.log("error : " + e);
                return ;
            }



            </c:if>



            </c:if>



        });

        function popupClose(){
            self.opener = self;
            self.close();
        }

    </script>

</head>

<body>
<section class="pop_wrap">
    <div class="location">
        <c:if test="${!empty fixMessage.dataSendJson}">
            <h2>Buy-side 메시지 조회</h2>
        </c:if>
        <c:if test="${!empty fixMessage.dataRecvJson}">
            <h2>Initiator 메시지 조회</h2>
        </c:if>
    </div>

    <div class="tb_write1">
        <table>
            <caption>기업 정보 입력</caption>
            <colgroup>
                <col style="width:20%;">
                <col style="width:30%;">
            </colgroup>
            <tbody>
            <tr>
                <th scope="row">Server</th>
                <td class="txt_l">${fixMessage.hubTypeName}</td>
                <th scope="row">SeqNum</th>
                <td class="txt_l">${fixMessage.seq}</td>
            </tr>
            <tr>
                <th scope="row">기업 이름(한글)</th>
                <td class="txt_l" id="compName">
                    ${fixMessage.companyName}
                </td>
                <th scope="row">기업코드</th>
                <td class="txt_l" id="compCode">
                    ${fixMessage.companyId}
                </td>
            </tr>
            <tr>
                <th scope="row">SendCompID</th>
                <td class="txt_l">${fixMessage.senderCompId}</td>
                <th scope="row">DeliverToCompID</th>
                <td class="txt_l">${fixMessage.deliverCompId}</td>
            </tr>
            <c:if test="${!empty fixMessage.dataSendJson}">
                <tr>
                    <th scope="row">MsgType</th>
                    <td class="txt_l">${fixMessage.msgTypeName}</td>
                    <th scope="row">Order</th>
                    <td class="txt_l">${fixMessage.orderId}</td>
                </tr>
            </c:if>
            <tr>
                <th scope="row">CliOrdID</th>
                <td class="txt_l">${fixMessage.clientOrderId}</td>
                <th scope="row">ListID</th>
                <td class="txt_l">${fixMessage.listOrderId}</td>
            </tr>
            <c:if test="${!empty fixMessage.dataRecvJson}">
                <tr>
                    <th scope="row">MsgType</th>
                    <td class="txt_l" colspan="3">${fixMessage.msgTypeName}</td>
                </tr>
            </c:if>
            <tr>
                <th scope="row"><label for="txt">RequestMessage</label></th>
                <td class="txt_l" colspan="3">
                    <textarea name="jsonData" id="jsonData" cols="30" rows="10"  readonly="readonly">

                    </textarea>
                </td>
            </tr>
            <c:if test="${paramVO.tabDivision == 'acceptor'}">
                <tr>
                    <th scope="row">Acceptor</th>
                    <td class="txt_l rejected" colspan="3">${fixMessage.rejectYnNameKor}</td>
                </tr>
            </c:if>
            <tr>
                <th scope="row">수신 일시</th>
                <td class="txt_l" colspan="3">${fixMessage.createDate}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="btn_type3">
        <span class="btn_gray1"><a href="javascript:void(0);" id="btnClose">확인</a></span>
    </div>
    <!-- // btn_type3 -->

</section>
<!-- // content -->
</div>
</article>
<!-- // container -->
</body>
</html>
