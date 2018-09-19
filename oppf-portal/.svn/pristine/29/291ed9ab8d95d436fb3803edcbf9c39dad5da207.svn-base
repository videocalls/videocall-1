<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("App조회_"+date+".xls","UTF-8"); 
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
 * @Name : appManageListExcel.jsp
 * @Description : api excel 조회 목록
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
           <col style="width:250px;">
           <col style="width:300px;">
           <col style="width:100px;">
           <col style="width:400px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:100px;">
           <col style="width:150px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">앱 개발자</th>
           <th scope="col">App. 이름</th>
           <th scope="col">App. ID</th>
           <th scope="col">App. Key</th>
           <th scope="col">App. 구분</th>
           <th scope="col">App. 상태</th>
           <th scope="col">활성화여부</th>
           <th scope="col">활성화순서</th>
           <th scope="col">App. 계약</th>
           <th scope="col">계약 체결일</th>
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
                  <td colspan="13" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
           </c:when>
           <c:otherwise>
               <c:forEach var="resultList" items="${resultList}" varStatus="status">
                   <tr>
                       <td class="txt_l" align="left"><c:out value='${resultList.companyNameKor}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.appName}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.appId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.appKey}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appCategoryName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appStatusName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.exposureYn}'/></td>
                       <td class="txt_l" align="right"><c:out value='${resultList.exposureOrder}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appContractCodeName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appContractDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appTermsStartDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.appTermsExpireDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.createIdName}'/></td>
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

