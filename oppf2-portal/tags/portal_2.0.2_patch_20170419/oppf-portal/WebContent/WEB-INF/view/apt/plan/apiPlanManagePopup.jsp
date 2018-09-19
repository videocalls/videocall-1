<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : apiPlanManagePopup.jsp
 * @Description : plan 코드 관리 popup
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.28  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.28
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<%-- jqwidgets --%>
<link rel="stylesheet" href="<c:url value='/js/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/cmm/jqwidgets.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.filter.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.sort.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.selection.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsresize.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.columnsreorder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdata.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.export.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxgrid.pager.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxpanel.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcalendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxdatetimeinput.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/jqxcheckbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqwidgets/globalization/globalize.js'/>"></script>
<%-- //jqwidgets --%>

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

/*******************************************
 * 이벤트 함수
 *******************************************/
/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        $(".wrap >").remove();
        if(opener){
            window.opener.fn_login();
            window.close();
        }else{
        	window.parent.fn_login();
        	gfn_closeModal();
        }
    </c:if>
    
    //저장
    $("#btnSave").click(function(){
        fn_save();
    });
        
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: 'Plan ID', datafield: 'apiPlanId', width: '80%', cellsalign: 'left', align: 'center' },
            { text: 'Plan Type', datafield: 'planTypeName', width: '20%', cellsalign: 'left', align: 'center' },
            
            { text: 'systemGrpCode', datafield: 'systemGrpCode', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'systemCode', datafield: 'systemCode', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'planType', datafield: 'planType', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    }, "130");
    
    $('#jqxgrid').on('rowclick', function (event) {
        
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
        
    //조회
    fn_search();
});

/*******************************************
 * 기능 함수
 *******************************************/
 
<%-- 검색 --%>
function fn_search(){
    //로딩 호출
    gfn_setLoading(true);
        
    //page setting  
    var url = "<c:url value='/apt/plan/selectApiPlanManagePopupList.ajax'/>";
    var param = $("#ApiPlanManageVO").serialize();
    var callBackFunc = "fn_searchCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_searchCallBack(data){
    //로그인 처리
    if(data.error == -1){
    	if(opener){
            window.opener.fn_login();
            window.close();
        }else{
            window.parent.fn_login();
            gfn_closeModal();
        }
    	
        return;
    }
    
    var resultList = data.resultList;
    
    //grid common
    gfn_gridData(resultList);
    
} 

<%-- 저장 --%>
function fn_save(){
	//plan Type
    if(util_chkReturn($.trim($('#planType').val()), "s") == "") {
        alert("<spring:message code='errors.required.select' arguments='Plan Type'/>");
        $('#planType').focus();
        return false;
    }
	
	if(!confirm("동일 Plan ID를 갖는 API에 대해서도 모두 변경하겠습니까?")){
		return;
	}		
    
    //로딩 호출
    gfn_setLoading(true, "저장중 입니다.");
    
    var moveUrl = "<c:url value='/apt/plan/saveApiPlanManagePopupList.ajax'/>";
    var param = $("#ApiPlanManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
   
    if(data.result <= 0){
        alert("<spring:message code='fail.alert.save' />");
    }else{
        alert("<spring:message code='success.alert.save' />");
    }	    
    
    fn_search();
}

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
    	window.opener.fn_search();
        window.close();
    }else{
    	window.parent.fn_search();
        gfn_closeModal(this.event);
    }
    
} 
</script>
</head>
<body>
<form:form commandName="ApiPlanManageVO" name="ApiPlanManageVO" method="post">
<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    

        <!-- #layer01 -->
        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">Plan Type 선택</div>
            
            <div class="layer_con">
                <h3>Plan 정보</h3>
                <div class="tb_write1">
                    <table>
                        <caption>기업코드 선택 정보입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><label for="planType">Plan Type</label></th>
                                <td class="txt_l">
                                    <select title="구분 입력" id="planType" name="planType">
                                        <option value="">선택하세요.</option>
                                        <c:forEach items="${planTypeList}" var="planTypeList" varStatus="status">
                                            <option value="${planTypeList.system_code}" 
                                                <c:if test="${paramVO.planType eq planTypeList.system_code}"> selected="selected" </c:if>
                                            >${planTypeList.code_name_kor}</option>
                                        </c:forEach>                                 
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><label for="apiPlanId">Plan ID</label></th>
                                <td class="txt_l">
                                    <input type="text" style="width:80%;" id="apiPlanId" name="apiPlanId"
                                           value="<c:out value='${paramVO.apiPlanId}'/>"
                                           onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();"  
                                    />
                                </td>
                            </tr>
                        </tbody>
                    </table>                        
                </div>
                
                <div class="btn_type3">
                    <div class="right">
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                    </div>
                </div>
                
                <h3>Plan 매핑 정보</h3>
                <%-- grid --%>
                <div id="jqxgrid" class="tb_list1"></div>
            </div>
            
            <!-- 닫기 -->
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
        </div>
    </div>        
    <!-- // layer_popup -->   
</div>
</form:form>
</body>
</html>