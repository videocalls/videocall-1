<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("통합계좌거래내역조회_"+date+".xls","UTF-8");
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
 * @Name : intAcntTransactionExcel.jsp
 * @Description : 통합계좌 API  Excel 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.30  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.30
 * @version 1.0
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
           <th scope="col">금융투자회사</th>
           <th scope="col">가상계좌</th>
           <th scope="col">거래일자</th>
           <%--<th scope="col">종목코드</th>--%>
           <th scope="col">종목명</th>
           <th scope="col">거래구분</th>
           <th scope="col">거래금액(원)</th>
           <th scope="col">거래수량(주)</th>
           <th scope="col">수량(주)</th>
       </tr>
       </thead>
       <tbody>
       <c:choose>
           <c:when test="${empty resultList}" >
               <tr>
                  <td colspan="8" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
           </c:when>
           <c:otherwise>
               <c:forEach var="resultList" items="${resultList}" varStatus="status">
                    <c:forEach var="data" items="${resultList.transList.transaction}">
                   <tr>
                       <td class="txt_l" align="left"><c:out value='${data.comName}'/></td>
                       <td class="txt_l" align="center" style='mso-number-format: "\@";'><c:out value='${data.vtAccNo}'/></td>
                       <td class="txt_l" align="center"><c:out value='${data.transDate}'/></td>
                       <%--<td class="txt_l" align="center"><c:out value='${data.isinCode}'/></td>--%>
                       <td class="txt_l" align="center"><c:out value='${data.isinName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${data.transType}'/></td>
                       <td class="txt_l" align="center" style='mso-number-format: "\@";'><c:out value='${data.changeAmt}'/></td>
                       <td class="txt_l" align="center"><c:out value='${data.changeQty}'/></td>
                       <td class="txt_l" align="center"><c:out value='${data.qty}'/></td>
                   </tr>
                    </c:forEach>
               </c:forEach>
           </c:otherwise>
       </c:choose>
       </tbody>
    </table>
</div>
</body>
</html>
