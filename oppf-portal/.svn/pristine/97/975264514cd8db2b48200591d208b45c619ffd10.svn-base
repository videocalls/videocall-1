<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("메일발송이력조회_"+date+".xls","UTF-8"); 
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
 * @Name : aptUserEmailManageListExcel.jsp
 * @Description : 메일 발송 이력 excel 조회 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.22  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.22
 * @version 1.0
 *
 */
--%>
</head>

<body>
<div class="tb_list1">
   <table border="1">
       <colgroup>
           <col style="width:100px;">
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:400px;">
           <col style="width:400px;">
           <col style="width:400px;">           
       </colgroup>
       <thead>
       <tr>
           <th scope="col">No.</th>
           <th scope="col">발신자</th>
           <th scope="col">ID</th>
           <th scope="col">수신자이메일</th>
           <th scope="col">이메일유형</th>
           <th scope="col">발송일시</th>           
       </tr>
       </thead>
       <tbody>
       <c:choose>
           <c:when test="${empty resultList}" >
               <tr>
                  <td colspan="6" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
           </c:when>
           <c:otherwise>
               <c:forEach var="resultList" items="${resultList}" varStatus="status">
                   <tr>
                       <td class="txt_l" align="center"><c:out value='${resultList.rownum}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.senderKind}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.senderId}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.receiverEmail}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.emailSendType}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.sendResultDate}'/></td>                       
                   </tr>                                                    
               </c:forEach>
           </c:otherwise>
       </c:choose>
       </tbody>
    </table>
</div>
</body>
</html>

