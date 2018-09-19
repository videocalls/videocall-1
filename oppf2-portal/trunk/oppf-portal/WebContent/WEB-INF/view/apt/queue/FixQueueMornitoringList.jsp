<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
    <%--
        /**
        * @Name : FixQueueManageList.jsp
        * @Description : Queue 모니터링
        * @Modification Information
        *
        * <pre>
        *  Modification Information
        *  수정일                 수정자          수정내용
        *  ----------  ------  ----------
        *  2017.04.03   최판광         최초  생성
        * </pre>
        *
        * @author 최판광
        * @since 2017.04.03
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

    <%-- 디자인 스크립트 --%>
    <script language="javascript" type="text/javascript">
        $(function() {
            // 달력
            $("#searchDateFrom, #searchDateTo").datepicker({
                showOn: "button",
                dateFormat: 'yy-mm-dd',
                buttonImage: "<c:url value='/images/apt/calendar.png'/>",
                buttonImageOnly: true,
                buttonText: "달력보기",
                currentText: util_getDate()
            });
        });
    </script>
    <script language="javascript" type="text/javascript">

        /*******************************************
         * 전역 변수
         *******************************************/
        
        var playAler;
        var intervalTime = "30000";

        /*******************************************
         * 이벤트 함수
         *******************************************/

        <%-- 로그인 처리 공통 함수 --%>
        function fn_login(){
            var url = "<c:url value='/apt/queue/fixQueueManageList.do'/>";
            var param = new Object();
            param.paramMenuId = "15004";

            gfn_loginNeedMove(url, param);
        }

        //화면 로드 처리
        $(document).ready(function(){
            <%-- 로그인 처리 --%>
            <c:if test="${empty LoginVO}">
            fn_login();
            return;
            </c:if>

            //그리드 셋팅
            //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
            gfn_gridOption('jqxgrid',{
                columns: [
                    { text: 'queueId', datafield: 'queueId', width: '20%', cellsalign: 'left', align: 'center' },
                    { text: 'dequeueCount', datafield: 'dequeueCount', width: '20%', cellsalign: 'left', align: 'center' },
                    { text: 'consumerCount', datafield: 'consumerCount', width: '20%', cellsalign: 'left', align: 'center' },
                    { text: 'pendingCount', datafield: 'pendingCount', width: '20%', cellsalign: 'left', align: 'center' },
                    { text: 'enqueueCount', datafield: 'enqueueCount', width: '20%', cellsalign: 'left', align: 'center' }
                ]
            });

            $('#jqxgrid').on('bindingcomplete', function(event){
                $("#jqxgrid").jqxGrid('sortby', 'queueId', 'asc');
                //로딩 호출
                gfn_setLoading(false);
            });

            //이메일 주소 타입 변경 시
            $('#intervalTimeCombo').change(function(){
                if($(this).val()==""){
                }else{
                	$('#intervalTime').val($(this).val());
                	fn_stopInterval();
                }
            });
            

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
             var url = "<c:url value='/apt/queue/selectFixQueueMornitoringList.ajax'/>";
             var param = $("#FixQueueManageVO").serialize();
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
         	var resultListTotalCount = data.resultListCnt;
         	
         	$("#totCnt").text(util_setCommas(resultListTotalCount));
         	
         	//grid common
         	gfn_gridData(resultList);
         	
         	intervalTime = data.intervalTime;
         	
            playAlert = setInterval(function(intervalTime) {
            	fn_stopInterval();
             }, intervalTime); // 30초 30000 , 60초 60000 , 90초 90000

         }
         
		function fn_stopInterval(){
         	clearInterval(playAlert);
         	fn_startInterval();
        }
		
		function fn_startInterval(){
        	fn_search();
		}
        <%-- 검색 --%>
        //function fn_search(){
        	//util_moveRequest("FixQueueManageVO", "<c:url value='/apt/sptUsr/sptUserManageDtl.do'/>", "_top");
        //}
        
        
    </script>

</head>

<body>
<form:form commandName="FixQueueManageVO" name="FixQueueManageVO" method="post">

<input type="hidden" name="intervalTime" id="intervalTime" value="${paramVO.getIntervalTime()}" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <article class="container">
        <div>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>

            <section class="content">
                <div class="location">
                    <h2>Queue 모니터링</h2>
                </div>

                <%-- rowcount 공통 --%>
                
                
					<div class="btn_type3">
                        <div class="left">
                            <p class="amount">총 <em id="totCnt">0</em> 건</p>
                        </div>
						
                        <div class="right">
							<select title="구분 입력" id="intervalTimeCombo" name="intervalTimeCombo" style="width:50px;">
								<option value="30000">30초</option>
								<option value="60000">60초</option>
								<option value="90000">90초</option>
							</select>
                        </div>
				</div>

                <%-- grid --%>
                <div id="jqxgrid" class="tb_list1"></div>

            </section>
        </div>
    </article>
</form:form>
</body>
</html>