<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptCompanyCodeListPopup.jsp
 * @Description : 미 등록 기업 코드 팝업
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.21  신동진        최초  생성
 * </pre>
 *
 * @author 신동진 
 * @since 2016.06.21
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
var g_initCheckboxArr = ["searchCmpanyCodeType", "searchCompanyCodeKind", "searchCompanyDivCode"];

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
    
    //검색
    $("#btnSearch").click(function(){
        fn_search();
    });
    
    //그리드 셋팅
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    gfn_gridOption('jqxgrid',{    
        columns: [
            { text: '기업코드', datafield: 'companyCodeId', width: '80px', cellsalign: 'left', align: 'center' },
            { text: '기업표구분', datafield: 'companyCodeTypeName', width: '80px', cellsalign: 'center', align: 'center' },
            { text: '기업종류', datafield: 'companyCodeKindName', width: '80px', cellsalign: 'center', align: 'center' },
            { text: '기업분류', datafield: 'companyDivCodeName', width: '80px', cellsalign: 'center', align: 'center' },
            { text: '기업이름 (한글약어)', datafield: 'companyNameKorAlias', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '기업이름 (영문약어)', datafield: 'companyNameEngAlias', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '기업이름 (한글)', datafield: 'companyNameKor', width: '150px', cellsalign: 'left', align: 'center' },
            { text: '기업이름 (영문)', datafield: 'companyNameEng', width: '150px', cellsalign: 'left', align: 'center' },            
                        
            { text: 'companyCodeRegNo', datafield: 'companyCodeRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true },
            { text: 'companyProfileRegNo', datafield: 'companyProfileRegNo', width: '0%', cellsalign: 'left', align: 'center', hidden : true }
        ]
    }, "180");
    
    $('#jqxgrid').on('rowclick', function (event) {
        var row = event.args.rowindex;
        var data = $('#jqxgrid').jqxGrid('getrowdata', row);
        
        var strCallBack = "${callBakFunc}";
        
        //선택
        if(util_chkReturn(strCallBack, "s") != ""){
        	try{
            	if(opener){
            		window.opener.eval(strCallBack)(data);
            	}else{
            		window.parent.eval(strCallBack)(data);
            	}
            	
            	gfn_closeModal();
            }catch(e){}
        }	        
    }).on('bindingcomplete', function(event){
        //로딩 호출
        gfn_setLoading(false);
    });
    
    <%-- 체크박스 선택 onclick이벤트 설정--%>          
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_initCheckbox(g_initCheckboxArr[i]);
    }
    
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
    
    <%-- 체크박스 선택 전체여부 셋팅 --%>           
    for(var i=0; i<g_initCheckboxArr.length; i++){
        gfn_setSelectAllYn(g_initCheckboxArr[i]);
    }
    
    //로딩 호출
    gfn_setLoading(true);
        
    //page setting  
    var url = "<c:url value='/apt/cptUsr/selectCptCompanyCodePopupList.ajax'/>";
    var param = $("#CptCompanyManageVO").serialize();
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
        $("#pageRowsize").val()
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
<form:form commandName="CptCompanyManageVO" name="CptCompanyManageVO" method="post">

<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${CptCompanyManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->
<input type="hidden" name="pageRowsize" id="pageRowsize" value="10" /><!-- //목록사이즈 -->

<input type="hidden" name="searchCompanyRegYn" id="searchCompanyRegYn" value="<c:out value='${CptCompanyManageVO.searchCompanyRegYn}'/>" />

<input type="hidden" name="searchCmpanyCodeTypeAllYn" id="searchCmpanyCodeTypeAllYn" value="N" />
<input type="hidden" name="searchCompanyCodeKindAllYn" id="searchCompanyCodeKindAllYn" value="N" />
<input type="hidden" name="searchCompanyDivCodeAllYn" id="searchCompanyDivCodeAllYn" value="N" />

<div class="wrap">
    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev">    

        <!-- #layer01 -->
        <div class="layer_box" id="layer01"><!-- 가로크기 직접제어, 세로는 최대 500px -->
            <div class="layer_tit">기업코드 선택</div>
            
            <div class="layer_con">
            
                <div class="tb_write1">
                    <table>
                        <caption>기업코드 선택 정보입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="searchCondition">구분</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="searchCondition" name="searchCondition">
                                    <option value="all">전체</option>
                                    <option value="name">기업이름</option>
                                    <option value="id">기업코드</option>
                                </select>
                                <input type="text" style="width:50%;" id="searchKeyword" name="searchKeyword"
                                       onkeydown="javascript:if(event.keyCode == 13) btnSearch.click();"  
                                />
                                <%-- 자동 submit을 막기 위해 처리 --%>
                                <input type="text" style="display: none;" id="dump" name="dump"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="">기업코드표 구분</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchCmpanyCodeType" id="searchCmpanyCodeType_all" value="all" checked="checked">
                                            <label for="searchCmpanyCodeType_all">전체</label>
                                        </li>
                                        <c:forEach items="${companyCodeTypeList}" var="companyCodeTypeList" varStatus="status">
                                            <li><input type="checkbox" name="searchCmpanyCodeType" id="searchCmpanyCodeType_${companyCodeTypeList.system_code}" value="${companyCodeTypeList.system_code}">
                                            <label for="searchCmpanyCodeType_${companyCodeTypeList.system_code}">${companyCodeTypeList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">기업 종류</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchCompanyCodeKind" id="searchCompanyCodeKind_all" value="all" checked="checked">
                                            <label for="searchCompanyCodeKind_all">전체</label>
                                        </li>
                                        <c:forEach items="${companyCodeKindList}" var="companyCodeKindList" varStatus="status">
                                            <li><input type="checkbox" name="searchCompanyCodeKind" id="searchCompanyCodeKind_${companyCodeKindList.system_code}" value="${companyCodeKindList.system_code}">
                                            <label for="searchCompanyCodeKind_${companyCodeKindList.system_code}">${companyCodeKindList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        <tr>
                            <th scope="row"><label for="">기업 분류</label></th>
                            <td class="txt_l">
                                <!-- chk_list_wrap -->
                                <div class="chk_list_wrap type2">
                                    <ul>
                                        <li>
                                            <input type="checkbox" name="searchCompanyDivCode" id="searchCompanyDivCode_all" value="all" checked="checked">
                                            <label for="searchCompanyDivCode_all">전체</label>
                                        </li>
                                        <c:forEach items="${companyDivCodeList}" var="companyDivCodeList" varStatus="status">
                                            <li><input type="checkbox" name="searchCompanyDivCode" id="searchCompanyDivCode_${companyDivCodeList.system_code}" value="${companyDivCodeList.system_code}">
                                            <label for="searchCompanyDivCode_${companyDivCodeList.system_code}">${companyDivCodeList.code_name_kor}</label></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- // chk_list_wrap -->
                            </td>  
                        </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnSearch">검색</a></span>
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