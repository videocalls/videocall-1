<%--
  Created by IntelliJ IDEA.
  User: loyalten
  Date: 2017-06-15
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
    <script type="text/javascript">
        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/usr/svcAppl/appList.do'/>";
            var param = new Object();
            param.paramMenuId = "06002";
            gfn_loginNeedMove(url, param);
        }
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
                fn_login();
                return;
            </c:if>

            //조회
            $("#btnSearch").click(function(){
                fn_search();
            });

            //카테고리 변경 시
            $("#searchAppCategory").change(function(){
                fn_search();
            });

            //조회
            fn_search($("#pageIndex").val());

            $('.btn_toggle').click(function() {
                if($(this).hasClass('active')) {
                    $("#searchAppCreatedYn").val("N");
                    $(this).removeClass('active');
                } else {
                    $("#searchAppCreatedYn").val("Y");
                    $(this).addClass('active');
                }
                fn_search($("#pageIndex").val());
            });
        });

        <%-- 검색 --%>
        function fn_search(pageIndex){
            //page
            if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1";
            $("#pageIndex").val(pageIndex);

            //로딩 호출
            gfn_setLoading(true);

            //page setting
            var url = "<c:url value='/usr/svcAppl/appList.ajax'/>";
            var param = $("#AppVO").serialize();
            var callBackFunc = "fn_searchCallBack";

            <%-- 공통 ajax 호출 --%>
            util_ajaxPage(url, param, callBackFunc);
        }
        function fn_searchCallBack(data){
            //로그인 처리
            if(data.error == -1){
                fn_login();
                return;
            }

            var resultAppList = data.resultAppList;                             //핀테크 서비스 목록
            var resultAppListTotalCount = data.resultAppListTotalCount;         //핀테크 서비스 목록 총개수

            $("#pagingNavi >").remove();

            //핀테크 서비스 선택 리스트 make
            fn_makeSvcList(resultAppList);

            //page common
            pageNavigator(
                    $("#pageIndex").val(),
                    resultAppListTotalCount,
                    "fn_search",
                    "20",
                    "5"
            );

            //로딩 호출
            gfn_setLoading(false);
        }
        <%-- 핀테크 서비스 선택 리스트 make --%>
        function fn_makeSvcList(resultAppList){
            var html = "";

            //목록 셋팅
            $("#appList >").remove();

            if(util_chkReturn(resultAppList, "s") != ""){
                $.each(resultAppList, function(idx, list){
                    //이미지 경로
                    var appImgUrl = "<c:url value=""/>"+"/cmm/appImg/"+list.appId+".do";

                    if(list.sort == 1) {
                        html += "<li class='ing'>";
                    } else {
                        html += "<li class='go'>";
                    }
                    html += "<a href='javascript:fn_moveAppLink(\""+list.appId+"\");'>";
                    html += "<p class='name'>" + list.appName + "</p>";
                    html += "<div class='app_large'>";
                    html += "<img src='" + appImgUrl + "' alt=''>";
                    html += "</div>";
                    html += "<div class='text_area'>";
                    html += "<p class='biz'>" + list.companyName + "</p>";
                    html += "<p class='etc'>" + list.appCategoryName + "</p>";
                    if(list.sort == 1) {
                        html += "<span class='icon_ing'>사용중</span>";
                    } else {
                        html += "<span class='icon_go'>신청</span>";
                    }
                    html += "</div>";
                    html += "<span class='over_bg'></span>";
                    html += "</a>";
                    html += "</li>";
                });
            }else{
                html += "<div class='nodata'>조회된 데이터가 없습니다.</div>";
            }
            $("#appList").append(html);
        }
        <!-- 앱 상세 이동 -->
        function fn_moveAppLink(appId) {
            var objParam = new Object();
            objParam.appId = appId;
            util_movePage("<c:url value='/usr/svcAppl/appDetail.do'/>", objParam);
        }
    </script>
</head>
<body>
<form:form commandName="AppVO" name="AppVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${paramVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="20" /><!-- //목록사이즈 -->
<input type="hidden" name="searchAppCreatedYn" id="searchAppCreatedYn" value="" /><!-- //사용중 체크 -->
<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <section>

        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 관리</a></li>
                <li class="on">앱 사용 신청</li>
            </ul>
        </div>
        <!-- // location -->

        <!-- container -->
        <div class="container">

            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>앱 사용 신청</h3> <!-- modify -->
                    <!-- 2016-06-16 수정 -->
                    <div class="sub_visual pm_show">
                        <img src="<c:url value='/images/spt/common/img_sub_visual01.jpg'/>" alt="">
                        <div class="txt">
                            <div class="service">
                                <div>
                                    <!-- 2016-06-21 수정 -->
                                    <p>코스콤이 제공하는 오픈API를 활용하여 핀테크 기업들의 각종 정보가 더해진<br>
                                        다양한 핀테크 서비스를 제공합니다.<br>
                                        코스콤 오픈API플랫폼이 제공하는 최적화된 시스템의 핀테크 서비스를 경험하세요.</p>
                                    <!-- // 2016-06-21 수정 -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- // 2016-06-16 수정 -->
                </div>

                <!-- con -->
                <div class="con">

                    <!-- search_area -->
                    <div class="search_area">
                    <span class="sel_box1 left m_hide"><!-- 2016-05-26 수정 -->
                        <select id="searchAppCategory" name="searchAppCategory" style="min-width:;" title="카테고리 선택">
                            <option value="">전체</option>
                            <c:forEach items="${appCategoryList}" var="appCategoryList" varStatus="status">
                                <option value="${appCategoryList.system_code}">${appCategoryList.code_name_kor}</option>
                            </c:forEach>
                        </select>
                        <button type="button" class="btn_toggle">사용중</button>
                    </span>

                    <div class="search_box right">
                        <!-- <span class="sel_box1">
                            <select id="searchCondition" name="searchCondition" style="min-width:;" title="검색조건 선택">
                                <option value="all">전체</option>
                                <option value="appName">앱명</option>
                                <option value="companyName">핀테크 기업명</option>
                            </select>
                        </span> -->
                        <input type="text" id="searchKeyword" name="searchKeyword" style="min-width:170px;" title="검색어 입력"
                               onkeydown="javascript:if(event.keyCode == 13) fn_search();"
                        />
                        <input type="text" id="temp" style="display: none;">
                        <a href="javascript:void(0);" id="btnSearch" class="btn_search">검색</a>
                        </div>
                    </div>
                    <!-- // search_area -->

                    <!-- modify -->
                    <div class="app_apply_list">
                        <ul id="appList">
                        </ul>
                    </div>
                    <!--// modify -->

                    <!-- pagination -->
                    <div id="pagingNavi" class="pagination">
                    </div>
                    <!-- // pagination -->

                </div>
                <!-- // con -->

            </article>
            <!-- // content -->

        </div>
        <!-- // container -->

    </section>

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
</div>
</form:form>
</body>
</html>
