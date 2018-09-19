<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<head>
<%--
/**  
 * @Name : sptUserManageListExcel.jsp
 * @Description : 일반회원관리 excel 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.20  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.20
 * @version 1.0
 *
 */
--%>
<%  
    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("일반회원_"+date+".xls","UTF-8"); 
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
           <%--<col style="width:300px;">--%>
           <col style="width:150px;">
           <col style="width:300px;">
           <col style="width:100px;">
           <col style="width:150px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:100px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">이름(한글)</th>
           <%--<th scope="col">이름(영문)</th>--%>
           <th scope="col">ID</th>
           <th scope="col">이메일</th>
           <th scope="col">계정유형</th>
           <th scope="col">계정상태</th>
           <th scope="col">가입구분</th>
           <th scope="col">등록일</th>
           <th scope="col">수정일</th>
           <th scope="col">삭제일</th>
           
            { text: '이름(한글)', datafield: 'customerNameKor', width: '12%', cellsalign: 'left', align: 'center' },
            //{ text: '이름(영문)', datafield: 'customerNameEng', width: '13%', cellsalign: 'left', align: 'center' },
            { text: 'ID', datafield: 'customerId', width: '10%', cellsalign: 'left', align: 'center' },
            { text: '이메일', datafield: 'customerEmail', width: '20%', cellsalign: 'left', align: 'center' },
            { text: '계정유형', datafield: 'temporaryMemberName', width: '7%', cellsalign: 'left', align: 'center' },
            { text: '계정상태', datafield: 'customerRegStatusName', width: '7%', cellsalign: 'left', align: 'center' },
            { text: '가입구분', datafield: 'customerJoinTypeName', width: '11%', cellsalign: 'left', align: 'center' },
            { text: '등록일', datafield: 'createDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '수정일', datafield: 'updateDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: '삭제일', datafield: 'deleteDate', width: '10%', cellsalign: 'center', align: 'center' },
            { text: 'customerRegNo', datafield: 'customerRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
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
                       <td class="txt_l" align="left"><c:out value='${resultList.customerNameKor}'/></td>
                       <%--<td class="txt_l" align="left"><c:out value='${resultList.customerNameEng}'/></td>--%>
                       <td class="txt_l" align="left"><c:out value='${resultList.customerId}'/></td>
                       <td class="txt_l" align="left"><c:out value='${resultList.customerEmail}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.temporaryMemberName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.customerRegStatusName}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.customerJoinTypeName}'/></td>
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
