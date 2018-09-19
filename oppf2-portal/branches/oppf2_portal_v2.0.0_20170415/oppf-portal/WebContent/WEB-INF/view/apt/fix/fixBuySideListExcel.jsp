<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<head>
<%--
/**  
 * @Name : fixBuySideListExcel.jsp
 * @Description : buy-side리스트 엑셀
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.07   최판광       최초  생성
 * </pre>
 *
 * @author 최판광
 * @since 2017.03.07
 * @version 2.0
 *
 */
--%>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("buy-side관리목록_"+date+".xls","UTF-8"); 
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
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">기업이름</th>
           <th scope="col">기업코드</th>
           <th scope="col">CompID</th>
           <th scope="col">Queue</th>
           <th scope="col">주문가능여부</th>
           <th scope="col">등록자</th>
           <th scope="col">등록일</th>
           <th scope="col">수정일</th>
       </tr>
       </thead>
       <tbody>
       <c:choose>
           <c:when test="${empty resultList}" >
               <tr>
                  <td colspan="13" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
           </c:when>
           <c:otherwise>
               <c:forEach var="resultList" items="${resultList}" varStatus="status">
                   <tr>  
                       <td class="txt_l" align="left"><c:out value='${resultList.senderCompName}'/></td>
                       <td class="txt_l" align="left" style="mso-number-format:\@"><c:out value='${resultList.companyId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.senderCompId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.fixQueueId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.useYnStatus}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.createNm}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.createTime}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.updateTime}'/></td>
                   </tr>                                                    
               </c:forEach>
           </c:otherwise>
       </c:choose>
       </tbody>
    </table>
</div>
</body>
</html>
