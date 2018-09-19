<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>

<script type="text/javascript" src="<c:url value='/js/apt/common_pub.js'/>"></script>

<script type="text/javascript">
/* TopMenu 뎁스1 클릭 시 호출되는 함수 */
function gfn_topMenu1_click(paramObj){
	$(paramObj).parent().children("ul").children("li:eq(0)").children("a").click();
// 	$( $(paramObj).parent() ).find(" ul li:eq(0) a").click();
}

/* TopMenu 뎁스2 클릭 시 호출되는 함수 */
function gfn_topMenu2_click(paramMenuId,paramUrl,paramParentMenuId){
	var paramObj = new Object();
	paramObj.paramMenuId = paramMenuId;
	paramObj.paramParentMenuId = paramParentMenuId;
	util_movePage(paramUrl,paramObj);
}

function btnMonitoring(){
	var paramObj = new Object();
	paramObj.paramMenuId = paramMenuId;
	paramObj.paramParentMenuId = paramParentMenuId;
	util_movePage(paramUrl,paramObj);
}

//alert('currentMenuId='+'${currentMenuId}'+'\n,currentParentMenuId='+'${currentParentMenuId}');
</script>
<%-- lnb --%>
<section class="lnbArea">
    <nav>
        <ul class="lnb">
            <c:forEach var="menuLeftList" items="${menuLeftList}" varStatus="status">
                <c:if test="${menuLeftList.treeLvl eq '1'}">
                    <li>
                        <a href="javascript:void(0);"
                            <c:if test="${currentParentMenuId eq menuLeftList.menuId}">
                            class="active"
                            </c:if>
                        >
                            ${fn:replace( menuLeftList.menuName, 'br', '<br>')}
                        </a>
                        <ul>
                </c:if>
                        <c:if test="${menuLeftList.treeLvl eq '2'}">
                            <li>
                                <a href="javascript:void(0);"
                                    <c:if test="${not empty menuLeftList.menuUrl}">
                                        onclick= "javascript:gfn_topMenu2_click('<c:out value='${menuLeftList.menuId}'/>','<c:url value='${menuLeftList.menuUrl}'/>','<c:out value='${menuLeftList.parentMenuId}'/>');"
                                    </c:if>
                                    
                                    <c:if test="${currentMenuId eq menuLeftList.menuId}">
                                    class="active"
                                    </c:if>
                                >
                                    <c:if test="${currentMenuId eq menuLeftList.menuId}">
                                        <b>${fn:replace( menuLeftList.menuName, 'br', '<br>')}</b>
                                    </c:if>
                                    <c:if test="${currentMenuId ne menuLeftList.menuId}">
                                        ${fn:replace( menuLeftList.menuName, 'br', '<br>')}
                                    </c:if>
                                </a>
                            </li>
                        </c:if>
                <c:if test="${menuLeftList.treeLvlNext eq '1'}">
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
			<li><a href="#" onclick="window.open('${monitoring}', '_blank')">모니터링 정보</a></li>			 
        </ul>
    </nav>
</section>
<%-- // lnb --%>