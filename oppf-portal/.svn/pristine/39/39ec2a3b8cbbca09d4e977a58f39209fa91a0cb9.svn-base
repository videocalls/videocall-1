<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : svcApplComplt.jsp
 * @Description : [핀테크서비스신청:서비스신청완료]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.11  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.11
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/usr/svcAppl/svcApplComplt.do'/>";
    var param = new Object();
    param.paramMenuId = "06001";
    
    gfn_loginNeedMove(url, param);
}
 
//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    
    //확인
    $("#btnComplt").click(function(){
    	util_movePage("<c:url value='/'/>");
    });
    
    //이전
    $("#btnBack").click(function(){
        util_movePage("<c:url value='/usr/svcAppl/svcTermConsnt.do'/>");
    });
    
    //조회
    fn_search();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 검색 --%>
function fn_search(){
    //page setting  
    var url = "<c:url value='/usr/svcAppl/selectSvcApplCompltList.ajax'/>";
    var param = $("#SvcApplVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    
    //로딩 호출
    gfn_setLoading(true);
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    
    var resultList = data.resultList;    
    
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            var key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
            
            html += "<tr>";
            
            html += "<th class='rowspan_subcompany'>"+list.subcompanyName+"</th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+"'><strong>"+list.appName+"</strong></th>";
            html += "<th class='rowspan_"+list.subcompanyCodeId+list.appId+list.pubcompanyCodeId+"'>"+list.pubcompanyName+"</th>";
            
            html += "<td class='left'>";
            html += "    <label for='"+key+"' class='chk_box'>"+ list.customerVtaccountNo +" / "+ list.customerVtaccountAlias +"</label>";
            html += "</td>";
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='4'><div class='nodata'>";
        html += "조회된 데이터가 없습니다.";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#list").append(html);
    
    if(util_chkReturn(resultList, "s") != ""){
        //첫 컬럼 rowspan
        gfn_rowspanMerge("rowspan_subcompany");
            
        //두번째, 세번째 rowspan
        var key2 = "";
        var key3 = "";
        if(util_chkReturn(resultList, "s") != ""){
            $.each(resultList, function(idx, list){
                var key2Data = list.subcompanyCodeId+list.appId;
                var key3Data = list.subcompanyCodeId+list.appId+list.pubcompanyCodeId;
                
                //rowspan
                if(key2 == ""){
                    key2 = key2Data;
                    gfn_rowspanMerge("rowspan_"+key2);
                }else{
                    if(key2 != key2Data){
                        key2 = key2Data;
                        gfn_rowspanMerge("rowspan_"+key2);
                    }
                }
                
                if(key3 == ""){
                    key3 = key3Data;
                    gfn_rowspanMerge("rowspan_"+key3);
                }else{
                    if(key3 != key3Data){
                        key3 = key3Data;
                        gfn_rowspanMerge("rowspan_"+key3);
                    }
                }
            });
        }
        
    }   
    
    //로딩 호출
    gfn_setLoading(false);
}

</script>
</head>
<body>

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">서비스 신청</a></li>
                <li class="on">핀테크 서비스 신청</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>핀테크 서비스 신청</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <!-- 2016-06-02 수정 -->
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>가상계좌 발급</div></li><!-- 지나간step -->
                            <li class="pass"><div>핀테크 서비스 선택</div></li><!-- 지나간step -->
                            <li class="pass"><div>약관동의</div></li><!-- 지나간step -->
                            <li class="pass"><div>정보제공동의</div></li><!-- 지나간step -->
                            <li class="last on"><div>서비스 신청 완료</div></li><!-- 현재step -->
                        </ul>
                    </div>
                    <!-- // 2016-06-02 수정 -->
                    
                    <div class="member_area type2">
                        <div class="tit_info">
                            <p>
                                <span>서비스 신청 완료</span>
                                <strong><c:out value='${LoginVO.name_kor}'/></strong> 회원님의 핀테크 서비스 신청이 완료 되셨습니다.
                            </p>
                        </div>

                        <p class="txt">금융투자 핀테크 포털과 금융투자회사, 핀테크 기업이 함께 제공하는 핀테크 서비스를 이용하실 수 있습니다.<br>회원님의 서비스 신청정보를 다시 한번 확인하여 주십시오.</p>
                        
                    </div>
                    
                    <p class="title_01">서비스 신청 내역</p>
                    
                    <!-- 2016-06-02 수정 -->
                    <!-- tbtype_list -->
                    <table class="tbtype_list2">
                        <caption>핀테크 기업, 핀테크 서비스, 금융투자회사, 가상계좌, 리스트</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:20%;">
                            <col style="width:25%;">
                            <col style="">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">핀테크 기업</th>
                                <th scope="col">핀테크 서비스</th>
                                <th scope="col">금융투자회사</th>
                                <th scope="col">가상계좌</th>
                            </tr>
                        </thead>
                        <tbody id="list">
                        </tbody>
                    </table>
                    <!-- // tbtype_list -->
                    <!-- // 2016-06-02 수정 -->
                    

                    <ul class="list_style_01">
                        <li>서비스 신청 정보는 마이 페이지 메뉴에서 조회 및 수정이 가능합니다.</li>
                    </ul>

                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" class="btn_type2" id="btnBack">이전</a>
                        <a href="javascript:void(0);" class="btn_type1" id="btnComplt">확인</a>
                    </div>
                    <!-- // btn_area -->
                    
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>
</body>
</html>