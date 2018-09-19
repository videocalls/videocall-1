<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("FixQueue조회"+date+".xls","UTF-8");
    //다운로드하고자 한다면
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
    response.setHeader("Content-Description", "JSP Generated Data");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
%>
<!doctype html>
<html lang="ko">
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<head>
    <%--
    /**
     * @Name : queueManagementExcel.jsp
     * @Description : fix Queue excel 조회 목록
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
</head>

<body>
<div class="tb_list1">
    <table border="1">
        <colgroup>
            <col style="width:250px;">
            <col style="width:400px;">
            <col style="width:300px;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">FIX QUEUE ID</th>
            <th scope="col">Initiator Server</th>
            <th scope="col">수정일</th>

        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty resultList}" >
                <tr>
                    <td colspan="14" align="center">조회 된 데이터가 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="resultList" items="${resultList}" varStatus="status">
                    <tr>
                        <td class="txt_l" align="left"><c:out value='${resultList.fixQueueId}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.initServerIpName}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.lastTimestamp}'/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
