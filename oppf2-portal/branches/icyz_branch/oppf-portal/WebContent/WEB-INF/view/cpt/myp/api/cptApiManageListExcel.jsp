<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("Api조회_"+date+".xls","UTF-8"); 
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
 * @Name : cptApiManageListExcel.jsp
 * @Description : 기업사용자의 api 조회 Excel 목록
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
           <%--
           <col style="width:250px;">
           <col style="width:400px;">
           <col style="width:300px;">
           <col style="width:100px;">
           <col style="width:200px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:250px;">
           --%>
           <col style="width:400px;">
           <col style="width:200px;">
           <col style="width:400px;">
           <col style="width:200px;">
           <col style="width:200px;">
           
       </colgroup>
       <thead>
       <tr>
           <%--
           <th scope="col">서비스 제공자</th>
           <th scope="col">API ID</th>
           <th scope="col">API 이름</th>
           <th scope="col">API 구분</th>
           <th scope="col">API 타이틀</th>
           <th scope="col">활성화여부</th>
           <th scope="col">활성화순서</th>
           <th scope="col">계좌 사용여부</th>
           <th scope="col">API 계약여부</th>
           <th scope="col">계약 체결일</th>
           <th scope="col">계약 시작일</th>
           <th scope="col">계약 종료일</th>
           <th scope="col">작성자</th>
           <th scope="col">작성일시</th>
           --%>
           <th scope="col">API 이름</th>
           <th scope="col">API 구분</th>
           <th scope="col">API 설명</th>
           <th scope="col">계약 시작일</th>
           <th scope="col">계약 종료일</th>
       </tr>
       </thead>
       <tbody>
       <c:choose>
           <c:when test="${empty resultList}" >
               <tr>
                  <td colspan="5" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
           </c:when>
           <c:otherwise>
               <c:forEach var="resultList" items="${resultList}" varStatus="status">
                   <tr>
                       <%--
                       <td class="txt_l" align="left"><c:out value='${resultList.companyNameKor}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiCategoryName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiTitle}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.exposureYn}'/></td>
                       <td class="txt_l" align="right"><c:out value='${resultList.exposureOrder}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiAccountYnName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiContractCodeName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiContractDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiTermsStartDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiTermsExpireDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.createIdName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.createDate}'/></td>
                       --%>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiCategoryName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.apiDescription}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiTermsStartDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.apiTermsExpireDate}'/></td>
                   </tr>                                                    
               </c:forEach>
           </c:otherwise>
       </c:choose>
       </tbody>
    </table>
</div>
</body>
</html>
