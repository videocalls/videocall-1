<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMberManageList.jsp
 * @Description : 서비스포털(일반사용자) 관리 목록
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.09  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.09
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<link rel="stylesheet" href="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/styles/jqx.base.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxcore.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxdata.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxbuttons.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxscrollbar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxlistbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxdropdownlist.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.filter.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.sort.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.selection.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.columnsresize.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.columnsreorder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxgrid.pager.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxpanel.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxcalendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxdatetimeinput.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/jqxcheckbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jqwidgets-ver4.1.2/jqwidgets/globalization/globalize.js'/>"></script>


<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){
	/*
	// initialize jqxGrid
	var url = "<c:url value='/jqwidgets-ver4.1.2/demos/sampledata/beverages.txt'/>";

    // prepare the data
    var source =
    {
        datatype: "json",
        datafields: [
            { name: 'name', type: 'string' },
            { name: 'type', type: 'string' },
            { name: 'calories', type: 'int' },
            { name: 'totalfat', type: 'string' },
            { name: 'protein', type: 'string' }
        ],
        id: 'id',
        url: url
    };
    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#jqxgrid").jqxGrid(
    {
    	width: '100%',
        height: '100%',
        source: dataAdapter,
        showfilterrow: true,
        filterable: true,
        selectionmode: 'multiplecellsextended',

        columns: [
          { text: 'Name', datafield: 'name', width: 250 },
          { text: 'Beverage Type', datafield: 'type', width: 250 },
          { text: 'Calories', datafield: 'calories', width: 180 },
          { text: 'Total Fat', datafield: 'totalfat', width: 120 },
          { text: 'Protein', datafield: 'protein', minwidth: 120 }
      ]
    });
    */
    
	fnSearchInit();
	
});

/*----------------------------------------------------------------------------------------------
 * Grid 초기값 셋팅
 *----------------------------------------------------------------------------------------------*/
function fnSearchInit(){
     //그리드 기본 Option
    //인자속성 -- (id,obj) or (id,objA,objB) or (id,height,objA,objB) or (id,height,width,objA, objB)
    fnGridOption('jqxgrid',{
        
        columns: [
                    { text: '농가번호', datafield: 'readCount', width: '8%', cellsalign: 'center', align: 'center', hidden : true },
                    { text: '별칭(구분명칭)', datafield: 'createDate', width: '13%', cellsalign: 'center', align: 'center',  cellsformat: 'yyyy-MM-dd'},
                    { text: '성명', datafield: 'test2', width: '10%', cellsalign: 'left', align: 'center' },
                    { text: 'CRM번호', datafield: 'test3', width: '10%', cellsalign: 'center', align: 'center' },
                    { text: '사료공장번호', datafield: 'test4', width: '10%', cellsalign: 'center', align: 'center' },
                    { text: '축종', datafield: 'test5', width: '7%', cellsalign: 'center', align: 'center' },
                    { text: '사업장명', datafield: 'test6', width: '12%', cellsalign: 'center', align: 'center' },
                    { text: '축산업', datafield: 'test7', width: '6%', cellsalign: 'center', align: 'center' },
                    { text: '농업경영', datafield: 'test8', width: '7%', cellsalign: 'center', align: 'center' },
                    { text: '조합원', datafield: 'test9', width: '6%', cellsalign: 'center', align: 'center' },
                    { text: '브랜드', datafield: 'test10', width: '6%', cellsalign: 'center', align: 'center' },
                    { text: '상태', datafield: 'test11', width: '5%', cellsalign: 'center', align: 'center' }
                ]
            });
}

function fnGridOption(){
    //매개변수값 저장
    var a = arguments;
    switch (a.length){
         case 1: //-- 매개변수가 하나일 때
             fnGridOptionLoding(a[0],null,null,null,null);
             break;
         case 2: //-- 매개변수가 두 개 일 때
             fnGridOptionLoding(a[0],null,null,a[1],null);
             break;
         case 3: //-- 매개변수가 세 개 일 때
             fnGridOptionLoding(a[0],a[1],null,null,a[2]);
             break;
         case 4: //-- 매개변수가 네 개 일 때
             fnGridOptionLoding(a[0],a[1],a[2],a[3],null);
             break;
         case 5: //-- 매개변수가 다섯 개 일 때
             fnGridOptionLoding(a[0],a[1],a[2],a[3],a[4]);
             break;
    }
}

function fnGridOptionLoding(id,heightInfo,widthInfo,objA, objB){
    //페이지 처리 custerm start
  var pagerrenderer = function () {
	  fnCellendeditEvent(id);
	  
      var element = $("<div style='margin-right: 10px; margin-top: 5px; width: 100%; height: 100%;'></div>");
      var datainfo = $("#"+id).jqxGrid('getdatainformation');
      var paginginfo = datainfo.paginginformation;
      label = $("<div style='font-size: 11px; margin: 2px 3px; font-weight: bold; float: left;'></div>");
      label.text(datainfo.rowscount+' 건이 조회 되었습니다.');
      label.appendTo(element);
      self.label = label;
    
      return element;
  }
  
  //페이지 처리 custerm end
  var defaultOption = new Object(); 
    defaultOption.theme = "custom"; 
    
  defaultOption.width = "100%";
  defaultOption.height = "430px"; 
 // defaultOption.autosavestate = true;
  //defaultOption.autorowheight = true;
  
  defaultOption.sortable = true;
  defaultOption.enabletooltips = true;
  defaultOption.altrows = true;
  defaultOption.columnsresize = true;       //column resize
  defaultOption.columnsreorder= true;       //drop & down
  //defaultOption.pageable = true;
  //defaultOption.pagesize = 100000;
  defaultOption.pagerrenderer = pagerrenderer;
  
//  defaultOption.showfilterrow = true;
//  defaultOption.filterable = true;
//  defaultOption.selectionmode =  'multiplecellsextended';
  
 // defaultOption.scrollmode = 'deferred';
//   defaultOption.pagesizeoptions = ['15','30', '50', '100','200','1000','5000','9000']; 
  
  if(heightInfo != undefined || heightInfo != null){
      defaultOption.height=heightInfo;
  }

  if(widthInfo != undefined || widthInfo != null){
      defaultOption.width=widthInfo;
  }

  if(objA != undefined || objA != null){
      $.extend(defaultOption, objA);
  }
  if(objB != undefined || objB != null){
      $.extend(defaultOption, objB);
  }
  
  $("#"+id).jqxGrid(defaultOption);
}
var cellbegineditHandler = null;
var cellendeditHandler = null;

//grid - update/insert 발생시 저장 처리 
function fnCellendeditEvent(id){
    window.this_grid_id = id;
    org_rows = {};
    editedRows = {};
    
    alert(1);
    
    if(!cellbegineditHandler){
        cellbegineditHandler = function (event) {
            var _args = event.args;
            var _id = $("#" + id).jqxGrid('getrowid', _args.rowindex);
            var _datas = _args.row;
            var _colname = _args.datafield;
            if(org_rows[_id] !== undefined){
                var _org_col = org_rows[_id][_colname];
                if(_org_col === undefined){
                    org_rows[_id][_colname] = getCellValue(_args.value);
                }
            }else{
                var _newObj = {};
                _newObj[_colname] = getCellValue(_args.value);
                org_rows[_id] = _newObj;
            }
        };
        $("#" + id).on('cellbeginedit', cellbegineditHandler);
    }
    if(!cellendeditHandler){
        cellendeditHandler = function (event) {
            var _args = event.args;
            var _id = $("#" + id).jqxGrid('getrowid', _args.rowindex);
            var _colname = _args.datafield;
            if(org_rows[_id] != undefined){
                var _keyarr = Object.keys(org_rows[_id]);
                if($.inArray(_colname, _keyarr) > -1){
                    if(editedRows[_id] == undefined){
                        if(org_rows[_id][_colname] != getCellValue(_args.value)){
                            var _newObj = {};
                            _newObj[_colname] = getCellValue(_args.value);
                            editedRows[_id] = _newObj;
                        }
                    }else{
                        if(org_rows[_id][_colname] == getCellValue(_args.value)){
                            if(editedRows[_id][_colname] != undefined){
                                delete editedRows[_id][_colname];
                            }
                        }else if(_args.value != undefined){
                            editedRows[_id][_colname] = getCellValue(_args.value);
                        }
                    }
                }
            }else{
                return false;
            }
            if(editedRows[_id] != undefined){
                var _isedit = false;
                for(var _name in editedRows[_id]){
                    var _value = editedRows[_id][_name] || "";
                    var _oldValue = org_rows[_id][_name] || "";
                    if(_value != _oldValue){
                        _isedit = true;
                        break;
                    }
                }
                if(!_isedit){
                    delete editedRows[_id];
                }
            }
        };
        $("#" + id).on('cellendedit', cellendeditHandler);
    }
}


/*******************************************
 * 기능 함수
 *******************************************/
 /*----------------------------------------------------------------------------------------------
  * grid search
  *----------------------------------------------------------------------------------------------*/
 function fnSearch(){
	 var url = "<c:url value='/cmm/noti/selectNotiList.ajax'/>";
	 var params = "?pageRowsize=5";
	 params += "&pageBlocksize=5";
	 params += "&pageIndex=1";
	 params += "&pageStart=1";
    
	 url = url + params;
     // prepare the data
     var source = {
                          datatype: "json", 
                          /*
                          datafields: [
                                             { name: 'readCount',   type: 'number' },
                                             { name: 'createDate',      type: 'string' },
                                             { name: 'test2',  type: 'string' },
                                             { name: 'test3', type: 'string' },
                                             { name: 'test4',  type: 'string' },
                                             { name: 'test5',      type: 'string' },
                                             { name: 'test6',     type: 'string' },
                                             { name: 'test7',     type: 'string' },
                                             { name: 'test8',     type: 'string' },
                                             { name: 'test9',     type: 'string' },
                                             { name: 'test10',     type: 'string' },
                                             { name: 'test11',     type: 'string' }
                                         ],
                           */                                         
                          url: url
                  };
     
     var dataAdapter = new $.jqx.dataAdapter(source);
 
     // create jqxgrid.
     $("#jqxgrid").jqxGrid(
     {
         source: dataAdapter
     });
 }
 
</script>

<%-- 디자인 스크립트 --%>
<script language="javascript" type="text/javascript">
$(function() {
    // 달력
    $("#datepicker1, #datepicker2").datepicker({
      showOn: "button",
      buttonImage: "<c:url value='/images/apt/calendar.png'/>",
      buttonImageOnly: true,
      buttonText: "달력보기"
    });
});
</script>

</head>

<body>
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <section class="content">
                <div class="location">
                    <h2>회원 조회</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="tb_write1">
                    <table>
                        <caption>구분, 등록일, 계정상태 입력</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:40%;">
                            <col style="width:10%;">
                            <col style="width:40%;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row"><label for="chk1">구분</label></th>
                            <td class="txt_l"> 
                                <select title="구분 입력" id="chk1">
                                    <option>전체</option>
                                    <option>이름</option>
                                    <option>ID</option>
                                    <option>이메일</option>
                                </select>
                                <input type="text" style="width:150px;">
                            </td>
                            <th scope="row">등록일</th>
                            <td class="txt_l"> 
                                <input type="text" id="datepicker1" readonly="readonly" style="width:80px;"><span class="dateDot">~</span><input type="text" id="datepicker2" readonly="readonly" style="width:80px;">
                            </td>                           
                        </tr>
                        <tr>
                            <th scope="row"><label for="chk2">계정 상태</label></th>
                            <td colspan="3" class="txt_l"> 
                                <select title="계정상태 입력" id="chk2">
                                    <option>전체</option>
                                    <option>활성</option>
                                    <option>비활성</option>
                                    <option>정지</option>
                                    <option>탈퇴</option>
                                </select>
                            </td>                           
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn_type3">
                    <span class="btn_gray1"><a href="javascript:fnSearch();">검색</a></span>
                    <span class="btn_gray2"><a href="#none;">초기화</a></span>
                </div>
                <!-- // btn_type3 -->

                <p class="amount">총 <em>99999</em> 건</p>
                
                <div id="jqxgrid" class="tb_list1">
                </div>
                
                
                <!-- // tb_list1 -->
                <div class="paging_area">                   
                    <div class="paging">
                        <a class="go_pp" href="#none">처음</a>
                        <a class="go_p" href="#none">이전</a>
                        <a href="#none" class="num on">1</a>
                        <a href="#none" class="num">2</a>
                        <a href="#none" class="num">3</a>
                        <a href="#none" class="num">4</a>
                        <a href="#none" class="num">5</a>
                        <a href="#none" class="num">6</a>
                        <a href="#none" class="num">7</a>
                        <a href="#none" class="num">8</a>
                        <a href="#none" class="num">9</a>
                        <a href="#none" class="num">10</a>
                        <a class="go_f" href="#none">다음</a>
                        <a class="go_ff" href="#none">마지막</a>
                    </div>
                    <div class="btn_type3">
                        <div class="left"><span class="btn_gray1"><a href="javascript:fn_search();">엑셀</a></span></div>
                    </div>
                </div>
                <!-- // paging_area -->

                

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</body>
</html>