<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserAccountListExcel.jsp
 * @Description : 가상계좌 목록 조회 excel 목록
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
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("가상계좌목록_"+date+".xls","UTF-8"); 
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
           <col style="width:100px;">
           <col style="width:250px;">
           <col style="width:100px;">
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:100px;">
           <col style="width:150px;">
           <col style="width:150px;">
           <col style="width:150px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">사용자ID</th>
           <th scope="col">사용자이름(한글)</th>
           <th scope="col">사용자이름(영문)</th>
           <th scope="col">계정상태</th>
           <th scope="col">서비스제공자</th>
           <th scope="col">유형</th>
           <th scope="col">가상계좌번호</th>
           <th scope="col">실계좌번호</th>
           <th scope="col">별칭</th>
           <th scope="col">상태</th>
           <th scope="col">등록일</th>
           <th scope="col">수정일</th>
           <th scope="col">삭제일</th>
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
                       <td class="txt_l" align="left"><c:out value='${resultList.customerId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.customerNameKor}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.customerNameEng}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.customerRegStatusName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.pubcompanyCodeName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.customerRealaccountTypeName}'/></td>
                       <td class="txt_l" align="left" style="mso-number-format:\@"><c:out value='${resultList.customerVtaccountNo}'/></td>
                       <td class="txt_l" align="left" style="mso-number-format:\@"><c:out value='${resultList.customerRealaccountNo}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.customerVtaccountAlias}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.customerVtaccountStatusName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.createDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.updateDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.deleteDate}'/></td>
                   </tr>                                                    
               </c:forEach>
           </c:otherwise>
       </c:choose>
       </tbody>
    </table>
</div>
</body>
</html>
