<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("목업메시지조회"+date+".xls","UTF-8");
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
 * @Name : mockUpServiceListExcel.jsp
 * @Description : 목업서비스 excel 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일        수정자    수정내용
 *  ----------  ------  ----------
 *  2017.02.21  이선하    최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2017.02.21
 * @version 1.0
 *
 */
--%>
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
            <th scope="col">서비스제공자</th>
            <th scope="col">제목(API 이름)</th>
            <th scope="col">Msg.ID</th>
            <th scope="col">Method</th>
            <th scope="col">URI</th>
            <th scope="col">http status</th>
            <th scope="col">등록일</th>
            <th scope="col">수정일</th>
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
                        <td class="txt_l" align="left"><c:out value='${resultList.companyNameKorAlias}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.title}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.mockupServiceNumber}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.methodType}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.mockupUri}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.httpStatusName}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.createDate}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.updateDate}'/></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
