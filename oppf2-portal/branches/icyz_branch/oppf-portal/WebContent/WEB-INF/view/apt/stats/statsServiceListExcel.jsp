<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="kr.co.koscom.oppf.apt.stats.service.StatsServiceVO" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    StatsServiceVO paramVO = (StatsServiceVO) request.getAttribute("paramVO");
	String searchType = paramVO.getSearchType();
	String searchDateTime = paramVO.getSearchDateTime();

    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("서비스연결통계["+searchType+"_"+searchDateTime+"기준]_"+date+".xls","UTF-8"); 
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
 * @Name : statsServiceListExcel.jsp
 * @Description : 서비스 연결 통계 excel 조회 목록
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
           <th scope="col">API 서비스 제공자 code</th>
           <th scope="col">API 서비스 제공자</th>
           <th scope="col">API 서비스 구분 code</th>
           <th scope="col">API 서비스 구분 </th>
           <th scope="col">API 서비스 ID</th>
           <th scope="col">API 서비스 이름</th>
           
           <th scope="col">앱 개발자 code</th>
           <th scope="col">앱 개발자</th>
           <th scope="col">앱 ID</th>
           <th scope="col">앱 이름</th>
           
           
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
                   <td class="txt_l" align="left"><%= data.get("pubcompanyCodeId") %></td>
                   <td class="txt_l" align="left"><%= data.get("pubcompanyName") %></td>
                   <td class="txt_l" align="left"><%= data.get("apiCategory") %></td>
                   <td class="txt_l" align="left"><%= data.get("apiCategoryName") %></td>
                   <td class="txt_l" align="left"><%= data.get("apiId") %></td>
                   <td class="txt_l" align="left"><%= data.get("apiName") %></td>
                   
                   <td class="txt_l" align="left"><%= data.get("subcompanyCodeId") %></td>
                   <td class="txt_l" align="left"><%= data.get("subcompanyName") %></td>
                   <td class="txt_l" align="left"><%= data.get("appId") %></td>
                   <td class="txt_l" align="left"><%= data.get("appName") %></td>
                   
                   
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
                  <td colspan="<%= 10 + resultHeader.size() %>" align="center">조회 된 데이터가 없습니다.</td>
               </tr>
<%            	  
              }
%>         
                   
       </tbody>
    </table>
</div>
</body>
</html>
