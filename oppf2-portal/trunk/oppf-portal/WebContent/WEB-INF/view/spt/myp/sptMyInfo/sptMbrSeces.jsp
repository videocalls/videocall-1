<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMbrSeces.jsp
 * @Description : [개인회원정보] 회원탈퇴
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.14  유제량        최초  생성
 * </pre>
 *
 * @author 유제량
 * @since 2016.06.14
 * @version 1.0
 * @spt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

/*******************************************
 * 기능 함수
 *******************************************/
 
//탈퇴신청 처리후 성공시 이동함수(로그아웃 시키고 공통로그인 화면으로 보냅니다)
function fn_moveList(){    
    var objParam = new Object();
    objParam.customerId     = $("#userId").val();
    util_movePage("<c:url value='/spt/myp/sptMyInfo/sptMbrSecesConfrm.do'/>",objParam);
}
 
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/spt/myp/sptMyInfo/sptMbrSeces.do'/>";
    var param = new Object();
    param.paramMenuId = "05005";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
    <%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    //[탈퇴신청]버튼 클릭 시 호출
    $("#btnSecesAppl").bind("click", function(){
        fn_moveList();
    });
    
    //핀테크 서비스 조회
    fn_searchMyApplList();
});

<%-- 검색 --%>
function fn_searchMyApplList(){
    //page setting  
    var url = "<c:url value='/spt/myp/svcAppl/selectMypSvcApplList.ajax'/>";
    var param = $("#MypSvcApplVO").serialize();
    var callBackFunc = "fn_searchMyApplListCallBack";
    
    //로딩 호출
    gfn_setLoading(true);
  
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchMyApplListCallBack(data){
    //로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
    
    var resultList = data.data.resultList;    
    
    var html = "";

    //목록 셋팅
    $("#list >").remove();
        
    if(util_chkReturn(resultList, "s") != ""){
        $.each(resultList, function(idx, list){
            var key = list.appId + "_" + list.apiId + "_" + list.customerRealaccountNo;
            
            html += "<tr>";
            
            html += "<th class='rowspan_subcompany'>";
            html += list.subcompanyName;
            html += "</th>";
            html += "<td class='center'>";
            //서버에서 전달한 금투사 리스트
            $.each(list.appList, function(idx2, subAppList){
                html += subAppList;
            });
            html += "</td>";            
            html += "</tr>";
        });
    }else{
        html += "<tr>";
        html += "<td align='center' colspan='4'><div class='nodata'>";
        html += "신청하신 핀테크 서비스 내역이 없습니다";
        html += "</div></td>";
        html += "</tr>";
    }
    
    $("#list").append(html);
    
    //로딩 호출
    gfn_setLoading(false);    
    return;
    

    
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

<input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />
<input type="hidden" id="customerRegNo" name="customerRegNo" value="<c:out value="${LoginVO.customer_reg_no}" />" />

<div class="wrap">

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf"%>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">회원탈퇴</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf"%>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>회원탈퇴</h3>
                </div>

                <!-- con -->
                <div class="con">

                    <div class="title_wrap">
                        <p class="title_01">회원탈퇴 정보확인</p>
                    </div>
                    
                    <!-- tbtype_form -->
                    <table class="tbtype_form type2">
                        <caption>아이디 정보 삭제안내, 서비스 이용 불가안내 정보리스트</caption>
                        <colgroup>
                            <col style="width:25%;">
                            <col style="width:75%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row">아이디 정보 삭제안내</th>
                                <td>
                                    <strong><c:out value="${LoginVO.id}" /></strong><br>
                                    <div class="info_bottom mt15">
                                        <p class="info_msg">
                                            - 탈퇴한 아이디는 복구되지 않습니다.
                                        </p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">서비스 이용 불가안내</th>
                                <td>
                                    <p>금융투자 핀테크 포털의 아이디로 연결된 서비스는 더 이상 이용하실 수 없습니다.<br />신청하신 서비스 정보도 모두 삭제하오니 탈퇴 전 신중하게 확인해주세요.</p>
                                    <table class="tbtype_list2 mt20">
                                        <caption>핀테크 서비스, 금융투자회사, 가상계좌</caption>
                                        <colgroup>
                                            <col style="width:50%;">
                                            <col style="width:50%;">                                            
				                            <col style="">
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th scope="col">핀테크 기업</th>
												<th scope="col">핀테크 서비스</th>
                                            </tr>
                                        </thead>
                                        <tbody id="list">
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- // tbtype_form -->
                    
                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnSecesAppl" class="btn_type1">탈퇴신청</a>
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