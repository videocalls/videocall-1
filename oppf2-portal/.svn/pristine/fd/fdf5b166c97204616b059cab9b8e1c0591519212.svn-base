
/*******************************************
 * 전역 변수
 *******************************************/


/*******************************************
 * 기능 함수
 *******************************************/

/* [페이징]처리 함수 */
function fn_paging(page){
	
	var pageStart = 0;
	var pageIndex = page;
	
	var pageRowsize = $("#pageRowsize").val();
	
    if(pageIndex == 1){
    	pageStart = 0;
    }else{
    	pageStart = pageRowsize * ( pageIndex -1 );
    }
    
    $("#pageIndex").val(pageIndex);
    $("#pageStart").val(pageStart);
    
    fn_ajaxCall_sample2();
} 



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* [샘플목록]ajax call 함수 */
function fn_ajaxCall_sample2(){
	var url = "/oppf-portal/sample/selectSampleList.ajax";
	var reqData = {
       "pageRowsize"   : $("#pageRowsize").val()
      ,"pageBlocksize" : $("#pageBlocksize").val()
      ,"pageIndex"     : $("#pageIndex").val()
      ,"pageStart"     : $("#pageStart").val()
      ,"searchName"    : $("#txtSearchName").val()
      ,"searchState"   : $("#txtSearchState").val()
      ,"searchCountry" : $("#txtSearchCountry").val()
	};
	
	util_ajaxPage(url, reqData, "fn_ajaxCallback_sample2");
}

/* [샘플목록]ajax callback 함수 */
function fn_ajaxCallback_sample2(data){
	var rsListTotCnt = data.resultListTotalCount;
    var rsList = data.resultList;
    var apData="";
    
    $("#rsList >").remove();
    $("#pagingNavi >").remove();
    if (util_chkReturn(rsListTotCnt, "s") == "" || util_chkReturn(rsList, "s") == ""){
    	apData = '<tr>'
    		   +   '<td colspan="9">검색결과를 찾을수 없습니다.</td>'
    		   +'</tr>';
    	$("#rsList").append(apData);
    	return false;
    }
    
    $("#spanRsListTotCnt").text(util_setCommas(rsListTotCnt));
    
    $.each(rsList, function(idx,data){
    	apData += '<tr>'
    	       +    '<td>'+data.id+'</td>'
    	       +    '<td>'
    	       +      '<a href="javascript:;" onclick="javascript:fn_moveDetail(\''+data.id+'\')">'
    	       +        data.name
    	       +      '</a>'
    	       +    '</td>'
    	       +    '<td>'+data.state+'</td>'
    	       +    '<td>'+data.country+'</td>'
    	       +    '<td>'+util_setCommas(data.readCount)+'</td>'
    	       +    '<td>'+data.createDate+'</td>'
    	       +    '<td>'+data.createId+'</td>'
    	       +    '<td>'+data.updateDate+'</td>'
    	       +    '<td>'+data.updateId+'</td>'
    	       +  '</tr>';
    });
    $("#rsList").append(apData);
    
  /* 페이징 설정 START */
    var pageIndex = data.sampleVO.pageIndex;
    $("#pageIndex").val(data.sampleVO.pageIndex);
    
    var pageStart = data.sampleVO.pageStart;
    $("#pageStart").val(data.sampleVO.pageStart);
    
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 START */
    var searchName = data.sampleVO.searchName;
    $("#searchName").val(searchName);
    $("#txtSearchName").val(searchName);
    
    var searchState = data.sampleVO.searchState;
    $("#searchState").val(searchState);
    $("#txtSearchState").val(searchState);
    
    var searchCountry = data.sampleVO.searchCountry;
    $("#searchCountry").val(searchCountry);
    $("#txtSearchCountry").val(searchCountry);
    /* 검색조건에 엉뚱한 값입력후 목록상세로 이동 시 문제점 처리 END */
    
    $("#pageTotalcount").val(rsListTotCnt);
    
    pageNavigator(
      $("#pageIndex").val()
     ,$("#pageTotalcount").val()
     ,$("#pageRowsize").val()
     ,$("#pageBlocksize").val()
     ,"fn_paging"
    );
  /* 페이징 설정 START */
}

/* [샘플목록]ajax callBack 함수 */
function fn_ajaxCallback_sampleInsert(data){
	if(data.rs == null || data.rs == 'undefined'){
		alert("등록실패 하였습니다.");
	}else{
		alert("정상적으로 등록완료 되었습니다.");
	}
	window.location.reload();
}

/*******************************************
 * 이벤트 함수
 *******************************************/

/* [상세]이동함수 */
function fn_moveDetail(paramId){
    var objParam = new Object();
    objParam.pageIndex     = $("#pageIndex").val();
    objParam.pageStart     = $("#pageStart").val();
    objParam.detailId      = paramId;
	objParam.searchName    = $("#searchName").val();
	objParam.searchState   = $("#searchState").val();
	objParam.searchCountry = $("#searchCountry").val();
	
	util_movePage("/oppf-portal/sample/retrieveSampleDetail.do",objParam);
}

/* [샘플조회]onload 호출 */
function fn_onload_searchList(){
	fn_ajaxCall_sample2(); //[샘플목록]ajax call 함수호출
}


/* [샘플조회]호출되는 함수 */
function fn_searchList(){
	$("#pageIndex").val("1");
	$("#pageStart").val("0");
	fn_ajaxCall_sample2(); //[샘플목록]ajax call 함수호출
}

//화면 로드 처리
$(document).ready(function(){
	
	/* [조회]버튼 클릭 시 호출 */
	$("#btnSearchList").click(function(){
		fn_searchList();
	});
	
	/* [name] keydown 시 호출 */
	$("#txtSearchName").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [state] keydown 시 호출 */
	$("#txtSearchState").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [state] keydown 시 호출 */
	$("#txtSearchCountry").bind("keydown", function(key){
		if(key.keyCode == 13){
			fn_searchList();
		}
	});
	
	/* [등록]버튼 클릭 시 호출 */
	$("#btnInsert").click(function(){
		var url = '/oppf-portal/sample/insertSample.ajax';
		var insertName    = $("#insertName").val();
		var insertState   = $("#insertState").val();
		var insertCountry = $("#insertCountry").val();
	    var objParam = new Object();
		objParam.insertName    = insertName;
		objParam.insertState   = insertState;
		objParam.insertCountry = insertCountry;
		util_ajaxPage(url, objParam, "fn_ajaxCallback_sampleInsert");
	});
	
	
	fn_onload_searchList();
});