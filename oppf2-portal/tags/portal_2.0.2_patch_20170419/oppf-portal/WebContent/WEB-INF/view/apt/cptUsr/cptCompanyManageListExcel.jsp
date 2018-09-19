<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptCompanyManageListExcel.jsp
 * @Description : 기업정보 관리 excel 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.21  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.21
 * @version 1.0
 *
 */
--%>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("기업정보관리_"+date+".xls","UTF-8"); 
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
           <th scope="col">기업이름(한글)</th>
           <th scope="col">기업이름(영문)</th>
           <th scope="col">기업코드</th>
           <th scope="col">기업역할</th>
           <%--<th scope="col">사업자등록번호</th>--%>
           <th scope="col">가상계좌 발급여부</th>
           <th scope="col">실계좌목록조회 여부</th>
           <th scope="col">등록일</th>
           <th scope="col">수정일</th>
           <th scope="col">삭제일</th>
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
                       <td class="txt_l" align="left"><c:out value='${resultList.companyNameEngAlias}'/></td>
                       <td class="txt_l" align="left" style="mso-number-format:\@"><c:out value='${resultList.companyCodeId}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.companyServiceTypeName}'/></td>
                       <%--<td class="txt_l" align="left" style="mso-number-format:\@"><c:out value='${resultList.companyBizregNo}'/></td>--%>
                       <td class="txt_l" align="center"><c:out value='${resultList.issueVtaccountName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.issueAccountlistName}'/></td>
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
