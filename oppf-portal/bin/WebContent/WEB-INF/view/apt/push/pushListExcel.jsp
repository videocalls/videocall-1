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
    String filename = java.net.URLEncoder.encode("PUSH_"+date+".xls","UTF-8"); 
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
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
           <col style="width:200px;">
       </colgroup>
       <thead>
       <tr>
           <th scope="col">플랫폼</th>
           <th scope="col">제목</th>
           <th scope="col">전송상태</th>
           <th scope="col">전송유형</th>
            <th scope="col">전송/예약일시</th>          
           <th scope="col">등록자</th>
           <th scope="col">등록일</th>
           <th scope="col">수정일</th>
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
                       <td class="txt_l" align="center"><c:out value='${resultList.codeNameKor}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.pushMessageTitle}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.sendYn}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.sendType}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.sendDate}'/></td>
                       <td class="txt_l" align="center"><c:out value='${resultList.createId}'/></td>
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
