<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
    /**
     * @Name : fixMeessageAcceptorExcel.jsp
     * @Description : [FIX Message]Buy-Side Excel 목록
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
    <%
        String date = OppfStringUtil.getTimeStamp().substring(0, 8);
        String filename = java.net.URLEncoder.encode("Buy-Side_Fix_Message"+date+".xls","UTF-8");
        //다운로드하고자 한다면
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
        response.setHeader("Content-Description", "JSP Generated Data");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
    %>
</head>

<body>
<div class="tb_list1">
    <table border="1">
        <colgroup>
            <col style="width:300px;">
            <col style="width:300px;">
            <col style="width:150px;">
            <col style="width:300px;">
            <%--<col style="width:100px;">--%>
            <col style="width:150px;">
            <col style="width:170px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">기업이름</th>
            <th scope="col">기업코드</th>
            <th scope="col">Server</th>
            <th scope="col">SeqNum</th>
            <th scope="col">SenderCompId</th>
            <th scope="col">DeliverToCompId</th>
            <th scope="col">MsgType</th>
            <th scope="col">ClientOrderId</th>
            <th scope="col">ListOrderId</th>
            <th scope="col">Order</th>
            <th scope="col">Acceptor</th>
            <th scope="col">수신일</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty resultList}" >
                <tr>
                    <td colspan="9" align="center">조회 된 데이터가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="resultList" items="${resultList}" varStatus="status">
                    <tr>
                        <td class="txt_l" align="center"><c:out value='${resultList.companyName}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.companyId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.hubTypeName}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.seq}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.senderCompId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.deliverCompId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.msgTypeName}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.clientOrderId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.listOrderId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.orderId}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.rejectYnNameKor}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.createDate}'/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
