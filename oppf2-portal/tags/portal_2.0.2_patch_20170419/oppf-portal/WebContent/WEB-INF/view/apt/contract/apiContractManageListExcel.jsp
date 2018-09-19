<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("계약정보조회_"+date+".xls","UTF-8"); 
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
 * @Name : apiContractManageListExcel.jsp
 * @Description : api 계약 excel 조회 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.06  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.06
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
           <col style="width:150px;">
           <col style="width:400px;">
           <col style="width:300px;">
           <col style="width:170px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:150px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">서비스 제공자</th>
           <th scope="col">API 구분</th>
           <th scope="col">API 이름</th>
           
           <th scope="col">앱 개발자</th>
           <th scope="col">API 계약상태</th>
           <th scope="col">계약 시작일</th>
           <th scope="col">계약 종료일</th>
           <th scope="col">작성자</th>
           <th scope="col">작성일시</th>
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
                       <td class="txt_l" align="left"><c:out value='${resultList.pubcompanyName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiCategoryName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.subcompanyName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.serviceContractStatusName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.contractTermsStartDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.contractTermsExpireDate}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.createIdName}'/></td>
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
