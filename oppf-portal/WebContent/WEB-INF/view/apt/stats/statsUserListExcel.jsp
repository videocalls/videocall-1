<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="kr.co.koscom.oppf.apt.stats.service.StatsUserVO" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    StatsUserVO paramVO = (StatsUserVO) request.getAttribute("paramVO");
    String searchType = paramVO.getSearchType();
    String searchDateTime = paramVO.getSearchDateTime();

    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("회원통계["+searchType+"_"+searchDateTime+"기준]_"+date+".xls","UTF-8"); 
    //다운로드하고자 한다면 
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
    response.setHeader("Content-Description", "JSP Generated Data");   
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    
    List<HashMap> resultHeader =  (List<HashMap>) request.getAttribute("resultHeader");
    List<HashMap> resultList =  (List<HashMap>) request.getAttribute("resultList");
%>
<!doctype html>
<html lang="ko">
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<head>
<%--
/**  
 * @Name : statsUserListExcel.jsp
 * @Description : 회원 통계 excel 조회 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.07.10  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.07.10
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
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:300px;">
           <col style="width:300px;">
           
           <c:choose>
               <c:when test="${empty resultHeader}" >
               </c:when>
               <c:otherwise>
                   <c:forEach var="resultHeader" items="${resultHeader}" varStatus="status">
                       <col style="width:200px;">                                                 
                   </c:forEach>
               </c:otherwise>
           </c:choose>
       </colgroup>
       <thead>
       <tr>
           <th scope="col">회원구분</th>
           <th scope="col">회원구분 code</th>
           <th scope="col">총 회원수</th>
           <th scope="col">가입상태</th>
           <th scope="col">가입상태 code</th>
           <th scope="col">가입상태 건수</th>
           
           <c:choose>
	           <c:when test="${empty resultHeader}" >
	           </c:when>
	           <c:otherwise>
	               <c:forEach var="resultHeader" items="${resultHeader}" varStatus="status">
	                   <th scope="col" style="mso-number-format:\@"><c:out value='${resultHeader.headerName}'/></th>                                                    
	               </c:forEach>
	           </c:otherwise>
	       </c:choose>
	       
       </tr>
       </thead>
       <tbody>
       
<%
              if(resultList != null && resultList.size() > 0){
              for(int i=0; i<resultList.size(); i++){
            	  HashMap data = (HashMap) resultList.get(i);
%>
               <tr>
                   <td class="txt_l" align="center"><%= data.get("userKindName") %></td>
                   <td class="txt_l" align="left"><%= data.get("userKind") %></td>
                   <td class="txt_l" align="right" style="mso-number-format:\@"><%= data.get("totCnt") %></td>
                   <td class="txt_l" align="center"><%= data.get("regStatusName") %></td>
                   <td class="txt_l" align="left"><%= data.get("regStatus") %></td>
                   <td class="txt_l" align="right" style="mso-number-format:\@"><%= data.get("statusCnt") %></td>
                   
<%
                   if(resultList != null && resultList.size() > 0){
                   for(int j=0; j<resultHeader.size(); j++){
                	   HashMap headerData = (HashMap) resultHeader.get(j);
                	   
                	   String dataName = (String) headerData.get("headerId");
%>
                       <td class="txt_l" align="right" style="mso-number-format:\@"><%= data.get(dataName) %></td>
<%                	   
                   }
                   }
%>                   
                   
               </tr>
<%            	  
            			  
              }
              }else{
%>
                <tr>
                  <td colspan="<%= 6 + resultHeader.size() %>" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
<%            	  
              }
%>         
                   
       </tbody>
    </table>
</div>
</body>
</html>
