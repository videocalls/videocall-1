<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>

<script type="text/javascript" src="<c:url value='/js/cpt/common_pub.js'/>"></script>

<%-- lnb --%>
<article class="lnb">
    <c:forEach var="menuLeftList" items="${menuLeftList}" varStatus="status">
        <c:if test="${menuLeftList.treeLvl eq 1}">
            <h2>${fn:replace( menuLeftList.menuName, 'br', '<br>')}</h2>
            <ul>
        </c:if>
        <c:if test="${menuLeftList.treeLvl ne 1}">
                 <li>
                     <a href="javascript:void(0);"
                         <c:if test="${not empty menuLeftList.menuUrl}">
                             onclick= "javascript:gfn_topMenu2_click('<c:out value='${menuLeftList.menuId}'/>','<c:url value='${menuLeftList.menuUrl}'/>');"
                         </c:if>
                         <c:if test="${menuLeftList.menuId eq currentMenuId}">
                             class="on"
                         </c:if>
                     >
                         ${fn:replace( menuLeftList.menuName, 'br', '<br>')}
                     </a>
                 </li>
        </c:if>
        <c:if test="${status.last}">
            </ul>
        </c:if>
    </c:forEach>
</article>
<%-- // lnb --%>