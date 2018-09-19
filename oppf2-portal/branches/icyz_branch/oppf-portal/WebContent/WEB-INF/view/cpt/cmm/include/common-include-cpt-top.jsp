<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<script type="text/javascript">
$(document).ready(function(){
    /******************* mobile **********************/
    
    /* 전체메뉴 열고닫기 */
    $(".btn_gnb_menu").click(function() {
        $(".gnb_menu").show();
        $(".gnb_menu").animate({"right": "0"}, "fast");     
        $(".wrap.mobile_wrap").css("position","fixed");
        $(".wrap.mobile_wrap").animate({"right": "100%"}, "fast");      
        
        var gnbHeight = $(window).innerHeight();
        $(".gnb_menu > ul").css("height",gnbHeight-67);
        return false;
    });
    
    $(".gnb_menu .btn_close").click(function() {
        $(".gnb_menu").animate({"right": "-100%"}, "fast"); 
        $(".gnb_menu").hide();          
        $(".wrap.mobile_wrap").animate({"right": "0"}, "fast"); 
        $(".wrap.mobile_wrap").css("position","relative");
    });

    // GNB서브메뉴 열고닫기 */
    if($(".mobile_wrap").length > 0){
        $(".mobile_wrap .gnb_menu > ul > li > a").click(function(){         
            if( $(this).next(".sub_menu").is(':hidden') ) {
                $(".mobile_wrap .gnb_menu > ul > li a").removeClass('on');
                $(this).addClass("on");
            }else{
                $(this).removeClass("on");
            }
            return false;
        });
    }


});

</script>

<script type="text/javascript">

/* TopMenu 뎁스1 클릭 시 호출되는 함수 */
function gfn_topMenu1_click(paramObj){
	$(paramObj).parent().children("ul").children("li:eq(0)").children("a").click();
//  $( $(paramObj).parent() ).find(" ul li:eq(0) a").click();
}

/* https url flag 
 * paramUrl 분석하여 httpsUri에 있는 값이면 https처리 true를 리턴한다.
 */
function gfn_getHttpsFlag(paramUrl){
    var httsFlag = false;
    var httpsUri = [
        "loginView",               //로그인
        "/myp/"                    //마이페이지
    ];
    $.each(httpsUri, function(idx){
        if(paramUrl.indexOf(httpsUri[idx]) > -1){
            httsFlag = true;
        }
    });
    
    return httsFlag;
}

/* TopMenu 뎁스2 클릭 시 호출되는 함수 */
function gfn_topMenu2_click(paramMenuId,paramUrl){
	//로그인 페이지 일 경우 다른메뉴 클릭 시 https > http 처리
    var tmp_paramUrl = paramUrl;
	
    //https 변환할  url인지 판단한다.
    var httsFlag = gfn_getHttpsFlag(paramUrl);
        
    if(httsFlag){
        paramUrl = httpsContextpath + tmp_paramUrl;
    }else{
        paramUrl = httpContextpath + tmp_paramUrl;
    }
	
    var paramObj = new Object();
    paramObj.paramMenuId = paramMenuId;
    util_movePage(paramUrl,paramObj);
}

/* Http 페이지로 이동 */
function fn_moveHttpUrl(paramUri){
	util_movePage(httpContextpath + paramUri);
}

/* Https 페이지로 이동 */
function fn_moveHttpsUrl(paramUri){
	util_movePage(httpsContextpath + paramUri);
}
</script>
<!-- header -->
<header class="header">
    <div class="header_area">
        <div class="skip_navi">
            <a href="#gnb">메뉴 바로가기</a>
            <a href="#main_content">본문 바로가기</a><!-- ID값이 서브페이지와 상이함 --> 
        </div>

        <div class="util_wrap">
            <ul class="site">
                <li><a href="http://<spring:message code='Globals.domain.spt' />">개인</a></li>
                <%--<li class="on"><a href="javascript:void(0);" onclick="javascript:util_movePage('http://<spring:message code='Globals.domain.cpt' />');">기업/금투사</a></li>--%>
                <li class="on"><a href="http://<spring:message code='Globals.domain.cpt' />">기업/금투사</a></li>
                <li class="fin"><a href="http://<spring:message code='Globals.domain.dev' />" target="_new">개발자센터</a></li>
            </ul>
            <div class="menu">
                <c:if test="${not empty LoginVO}">
                    <p><c:out value='${LoginVO.company_name}'/>&nbsp;<span><c:out value='${LoginVO.name_kor}'/></span>님 안녕하세요!</p>
                    <ul>
                        <li class="log">
                            <a href="javascript:void(0);" onclick="javascript:fn_moveHttpUrl('/cmm/commonLogout.do')">
                                                        로그아웃
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="javascript:util_movePage('<c:url value='/cpt/myp/cptMyInfo/cptMyPwConfrm.do'/>?paramMenuId=05006');">
                                                        마이페이지
                            </a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${empty LoginVO}">
                    <ul>
                        <li class="log">
                            <a href="javascript:void(0);" onclick="javascript:fn_moveHttpsUrl('/cpt/cmm/loginView.do')">
                                                        로그인
                            </a>
                        </li>
                    </ul>
                </c:if>
            </div>
        </div>

        <nav id="gnb">
            <h1 class="logo"><a href="http://<spring:message code='Globals.domain.cpt' />"><img src="<c:url value='/images/cpt/common/logo.png'/>" alt="Koscom OpenAPI Platform"></a></h1>

            <!-- 2016-06-01 수정 -->
            <div class="gnb_menu">
                <ul>
                    <c:set var="menuNo1" value="0"/>
                    <c:set var="menuNo2" value="0"/>
                    <c:forEach var="menuTopList" items="${menuTopList}" varStatus="status">
                        <c:if test="${menuTopList.treeLvl eq '1'}">
                            <c:set var="menuNo1" value="${menuNo1 + 1}"/>
                            <c:set var="menuNo2" value="0"/>
                            <li id="liMenu_${menuNo1}@${menuTopList.menuId}">
                                <a href="javascript:void(0);" onclick= "javascript:gfn_topMenu1_click(this);">
                                    ${fn:replace( menuTopList.menuName, 'br', '')}
                                </a>
                        </c:if>
                        <c:if test="${menuTopList.treeLvl eq '2'}">
                            <c:set var="menuNo2" value="${menuNo2 + 1}"/>
                            <c:if test="${menuTopList.treeLvlBefore eq '1'}">
                                <ul id="ulMenu_${menuNo1}" class="sub_menu">
                            </c:if>
                                    <li id="liulliMenu_${menuNo1}-${menuNo2}@${menuTopList.menuId}">
                                        <a href="javascript:void(0);"
                                            <c:if test="${not empty menuTopList.menuUrl}">
                                                onclick= "javascript:gfn_topMenu2_click('<c:out value='${menuTopList.menuId}'/>','<c:url value='${menuTopList.menuUrl}'/>');"
                                            </c:if>
                                        >
                                            ${fn:replace( menuTopList.menuName, 'br', '')}
                                        </a>
                                    </li>
                        </c:if>
                        <c:if test="${menuTopList.treeLvlNext eq '1'}">
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </nav>
    </div>
</header>
<!-- // header -->