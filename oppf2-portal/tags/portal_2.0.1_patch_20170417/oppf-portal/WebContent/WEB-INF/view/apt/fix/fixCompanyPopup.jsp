<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : fixCompanyCodeListPopup.jsp
 * @Description : 기업 코드 리스트 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.03.07   최판광       최초  생성
 * </pre>
 *
 * @author 최판광
 * @since 2017.03.07
 * @version 2.0
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
var g_initCheckboxArr = ["searchCmpanyCodeType", "searchCompanyCodeKind", "searchCompanyDivCode"];
var row = "";
var strCallBack = "";
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
    
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '기업명', datafield: 'senderCompName', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '기업코드', datafield: 'companyId', width: '150px', cellsalign: 'center', align: 'center' },
            { text: 'CompID', datafield: 'senderCompId', width: '150px', cellsalign: 'center', align: 'center' }
        ]
    }, "180");
    
    $('#jqxgrid').on('rowclick', function (event) {
        row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        	        
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    

    //조회
    fn_search($("#pageIndex").val());
});

/*******************************************
 * 기능 함수
 *******************************************/

 <%-- 검색 --%>
 function fn_search(pageIndex){

 	//page
 	if(util_chkReturn(pageIndex, "s") == "") pageIndex = "1"; 
 	$("#pageIndex").val(pageIndex);
 	    
 	//로딩 호출
 	gfn_setLoading(true);
 		
 	//page setting  
     var url = "<c:url value='/apt/fix/fixInitiatorCompPopupSearch.ajax'/>";
     var param = $("#FixManageVO").serialize();
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
 	
 	var resultList = data.resultList;
 	var resultListTotalCount = data.resultListTotalCount;
 	
 	$("#totCnt").text(util_setCommas(resultListTotalCount));
 	
 	$("#pagingNavi >").remove();
   	
 	//grid common
 	gfn_gridData(resultList);
     
 	//page common
 	pageNavigator(
 		$("#pageIndex").val(),
 		resultListTotalCount,
 		"fn_search",
         "50"
     );
 }
 
 
/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
} 
</script>
</head>
<body>
<form:form commandName="FixManageVO" name="FixManageVO" method="post">

<input type="hidden" name="pageIndex" id="pageIndex" value="1" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="10" /><!-- //목록사이즈 -->

<input type="hidden" name="initServerId" id="initServerId" value="<c:out value='${result.initServerId}'/>" />
<input type="hidden" name="initServerIp" id="initServerIp" value="<c:out value='${result.initServerIp}'/>" />

<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    

        <!-- #layer01 -->
        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">기업 정보 조회</div>
            
            <div class="layer_con">
				<div class="tb_write1">
					<table>
						<caption>Initiator ID, Initiator IP 정보</caption>
						<colgroup>
							<col style="width:36%;">
							<col style="width:64%;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row">Initiator ID</th>
							<td>${result.initServerId}</td>
						</tr>
						<tr>
							<th scope="row">Initiator IP</th>
							<td>${result.initServerIp}</td>
						</tr>
						</tbody>
					</table>
				</div>
            
                
                <!-- // btn_type3 -->
                
                <%-- rowcount 공통 --%>
                <p class="amount">총 <em id="totCnt">0</em> 건</p>
                
                <%-- grid --%>
                <div id="jqxgrid" class="tb_list1"></div>
                
                <!-- // tb_list1 -->
                <div class="pagination">
                    <%-- paging 공통 --%>
                    <div id="pagingNavi"></div>
                </div>
                <!-- // paging_area -->
            
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