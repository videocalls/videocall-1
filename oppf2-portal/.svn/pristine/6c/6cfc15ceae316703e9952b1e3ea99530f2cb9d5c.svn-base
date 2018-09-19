<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%  
    StatsTrafficVO paramVO = (StatsTrafficVO) request.getAttribute("paramVO");
    String searchTrafficType = paramVO.getSearchTrafficType();
    String searchStatsType = paramVO.getSearchStatsType();
    String searchDateTime = paramVO.getSearchDateTime();

    String date = OppfStringUtil.getTimeStamp().substring(0, 8);
    String filename = java.net.URLEncoder.encode("서비스별트래픽통계["+searchTrafficType+"기준]_"+date+".xls","UTF-8");
    //다운로드하고자 한다면 
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
    response.setHeader("Content-Description", "JSP Generated Data");   
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    
    List<HashMap> resultList =  (List<HashMap>) request.getAttribute("resultList");
    List<HashMap<String,Object>> chartDisplayNames = (List<HashMap<String,Object>>) request.getAttribute("chartDisplayNames");
    Boolean isAllData = (Boolean) request.getAttribute("isAllData");
%>
<!doctype html>
<html lang="ko">
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<head>
<%--
/**  
 * @Name : statsServiceTrafficListExcel.jsp
 * @Description : 서비스별 트래픽 통계 excel 조회 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.08  김충열        최초  생성
 * </pre>
 *
 * @author 김충열
 * @since 2017.03.08
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
           <c:choose>
               <c:when test="${empty chartDisplayNames}" >
               </c:when>
               <c:otherwise>
                   <c:forEach var="chartDisplayNames" items="${chartDisplayNames}" varStatus="status">
                       <col style="width:100px;">
                   </c:forEach>
               </c:otherwise>
           </c:choose>
       </colgroup>
       <thead>
       <tr>
           <th scope="col" rowspan="2">
               <%
                   if(searchTrafficType.equals("min")) {
               %>
                       분(5분)
               <%
                   } else if(searchTrafficType.equals("hourly")) {
               %>
                       시간
               <%
                   } else if(searchTrafficType.equals("daily")) {
               %>
                       일
               <%
                   } else {
               %>
                       월
               <%
                   }
               %>
           </th>
           <c:choose>
               <c:when test="${empty chartDisplayNames}" >
               </c:when>
               <c:otherwise>
                   <c:forEach var="chartDisplayNames" items="${chartDisplayNames}" varStatus="status">
                       <th scope="col" colspan="6"><c:out value="${chartDisplayNames.displayText}" /></th>
                   </c:forEach>
               </c:otherwise>
           </c:choose>
       </tr>
       <tr>
           <c:choose>
               <c:when test="${empty chartDisplayNames}" >
               </c:when>
               <c:otherwise>
                   <c:forEach var="chartDisplayNames" items="${chartDisplayNames}" varStatus="status">
                       <th scope="col">호출성공</th>
                       <th scope="col">내부응답</th>
                       <th scope="col">외부응답</th>
                       <th scope="col">호출실패</th>
                       <th scope="col">내부응답</th>
                       <th scope="col">외부응답</th>
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
                   <td class="txt_l" align="center"><%= data.get("Date") %></td>
                       <%
                           for(int k=0; k<chartDisplayNames.size(); k++) {
                               HashMap<String,Object> headerMap = (HashMap<String,Object>) chartDisplayNames.get(k);
                               String dataFieldName = "";
                               if(!isAllData) {
                                   dataFieldName = "_" + headerMap.get("dataField");
                               }
                       %>
                           <td class="txt_l" align="right"><%= data.get("cntApiDurationY"+dataFieldName) %></td>
                           <td class="txt_l" align="right"><%= data.get("apiDurationY"+dataFieldName) %></td>
                           <td class="txt_l" align="right"><%= data.get("sifDurationY"+dataFieldName) %></td>
                           <td class="txt_l" align="right"><%= data.get("cntApiDurationN"+dataFieldName) %></td>
                           <td class="txt_l" align="right"><%= data.get("apiDurationN"+dataFieldName) %></td>
                           <td class="txt_l" align="right"><%= data.get("sifDurationN"+dataFieldName) %></td>
                        <%
                            }
                        %>
                </tr>
<%            	  
                    }

                }else{
              }
%>         
                   
       </tbody>
    </table>
</div>
</body>
</html>
