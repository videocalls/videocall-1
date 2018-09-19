<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("테스트데이터조회"+date+".xls","UTF-8");
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
     * @Name : dataSetManagementExcel.jsp
     * @Description : 데이타셋 excel 조회 목록
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
</head>

<body>
<div class="tb_list1">
    <table border="1">
        <colgroup>
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
            <col style="width:200px;">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">이름</th>
            <th scope="col">ID</th>
            <th scope="col">계정상태</th>
            <th scope="col">서비스 제공자</th>
            <th scope="col">계좌유형</th>
            <th scope="col">가상계좌</th>
            <th scope="col">별칭</th>
            <th scope="col">데이터유무</th>
            <th scope="col">등록일</th>
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
                        <td class="txt_l" align="left"><c:out value='${resultList.customerNameKor}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.customerId}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.customerRegStatusName}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.companyNameKorAlias}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.customerRealaccountTypeName}'/></td>
                        <td class="txt_l" align="center" style="mso-number-format:\@"><c:out value='${resultList.customerVtaccountNumber}'/></td>
                        <td class="txt_l" align="left"><c:out value='${resultList.customerVtaccountAlias}'/></td>
                        <td class="txt_l" align="center"><c:out value='${resultList.dataSetYn}'/></td>
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
