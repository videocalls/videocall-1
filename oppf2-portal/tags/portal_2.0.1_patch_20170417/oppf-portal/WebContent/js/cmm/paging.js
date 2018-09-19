/**
 * 메뉴경로 및 화면명 : 페이징 
 * 작성일 : 20130121
 * 작성자 : 이환덕
 * 참  고 : 페이징 공통
 * 변경이력 : 	20130122 - 정헌태 - function pageNavigator 확장형으로 수정
 * 			  	20130123 - 정헌태 - 자동리스트 그리기 및 페이징 function drawListPagin 관련 추가
 * 				20130128 - 박태양 - ajax페이징 추가
 * 				20130313 - 이환덕 - designType 추가
 */


/**
 * 페이지 네비게이터
 * 현재 페이지 번호(iCurrPage), 전체 Data Row 수(iTotalCount)는 필수 값
 * 페이징 클릭시 별도의 값을 입력하지 않으면 fn_paging(nPaginNum); 기본 호출
 * @param iCurrPage	- Interger - (필수)현재 페이지 번호 - 최초 1
 * @param iTotalCount	- Interger - (필수)전체 Data Row 수
 * @param strReturnFn	- String - 페이징 클릭시 호출할 function명 - 미입력 또는 빈스트링시 fn_paging 호출 
 * @param iPageRow	- Interger - 화면에보여주는 Data Row 수 - 미입력 또는 빈스트링 입력시 5기본 세팅
 * @param iPageBlock	- Interger - 페이징 번호 길이 수 - 미입력 또는 빈스트링 입력시 10기본 세팅
 * @param strViewId	- String - 페이징을 표시할 div명 - 미입력 또는 빈스트링시 pagingNavi에 그린다
 * @param designType- String - 페이징디자인타입 - 미입력 또는 빈스트링시 default 페이징디자인타입
 * 
 */
function pageNavigator(iCurrPage, iTotalCount, strReturnFn, iPageRow, iPageBlock, strViewId, designType) {
	// 필수 값 확인
	if (util_chkReturn(iCurrPage, "s") == ""){
		//alert("pageNavigator : 변수 설정을 확인해 주십시오.");
		return;
	}else{
		iCurrPage = parseInt(iCurrPage);
	}
	
	if (util_chkReturn(iTotalCount, "s") == ""){
		//alert("pageNavigator : 변수 설정을 확인해 주십시오.");
		return;
	}else{
		iTotalCount = parseInt(iTotalCount);
	}
	
	// 화면에보여주는 Data Row 수를 미입력 하면 디폴트 10 세팅
	if (util_chkReturn(iPageRow, "s") == ""){
		iPageRow = 10;
	}else{
		iPageRow = parseInt(iPageRow);
	}
	
	// 페이징 번호 길이수를 미입력 하면 디폴트 10 세팅
	if (util_chkReturn(iPageBlock, "s") == ""){
		iPageBlock = 10;
	}else{
		iPageBlock = parseInt(iPageBlock);
	}
	
	//페이지 관리를 위한 변수
	var iTotPage	= 0 ;
	var iTotalBlock = 0 ;
	var iBlock		= 0 ;
	var iFirstPage	= 0 ;
	var iLastPage	= 0 ;
	var iMyPage	    = 1 ;

	iBlock = Math.floor((iCurrPage-1) / iPageBlock + 1);
	iTotPage = Math.floor((iTotalCount -1) / iPageRow + 1);	
	iFirstPage  = (iBlock -1) * iPageBlock + 1;
	iLastPage   = Math.min(iBlock * iPageBlock,iTotPage) ;
	iTotalBlock = Math.floor((iTotPage-1) / iPageBlock + 1);
	
	// 페이징 클릭시 호출할 function 명
	var fncPaging = "fn_search";
	if (util_chkReturn(strReturnFn, "s") != ""){	// 입력값이 있으면 해당 입력값의 function 호출
		fncPaging = strReturnFn;
	}
	
	//디자인 타입
	var dsType = 1;
	if (util_chkReturn(designType, "s") != ""){	// 입력값이 있으면 해당 입력값의 function 호출
		dsType = parseInt(designType);
	}
	
	
	var sCurrPageClass = "on";
	var sReturnValue = "";

	if(iTotalCount > iPageRow){
	
		if(iBlock > 1) {
			iMyPage = iFirstPage;
			
			sReturnValue += "<a class='page first' href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + 1 + ");' alt='"+(iMyPage-1).toString()+"'></a>";
			sReturnValue += "<a class='page prev' href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + (iMyPage-1).toString() + ");' alt='"+(iMyPage-1).toString()+"'></a>";
		}
		
		for(var i = iFirstPage; i <= iLastPage; i++) {
			if(iCurrPage == i) {
				sReturnValue += "<a class='"+sCurrPageClass+"' href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + i.toString() + ");' alt='"+i.toString()+"'>" + i.toString() + "</a>";
			} else {
				sReturnValue += "<a href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + i.toString() + ");' alt='"+i.toString()+"'>" + i.toString() + "</a>";
			}
			
			if(i < iLastPage) {
				sReturnValue += "";
			}
		}
		
		//다음페이지블록에 대한 페이지 링크
		if(iBlock < iTotalBlock) {
			iMyPage = iLastPage+1;
			
			sReturnValue += "<a class='page next' href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + iMyPage.toString() + ");'></a>";
			sReturnValue += "<a class='page last' href='javascript:void(0);' onclick='javascript:" + fncPaging + "(" + iTotPage.toString() + ");'></a>";
		}
	
	}
	
	// 페이징을 그릴 div ID값이 없을 경우 pagingNavi를 기본 세팅
	if (util_chkReturn(strViewId, "s") == ""){
		strViewId = "pagingNavi";
	}
	
	document.getElementById(strViewId).innerHTML = sReturnValue;	// 화면에 그린다
}


/**
 * 페이징 초기화 함수 - 페이징 div 내용을 삭제한다.
 * @param strViewId	- String - 페이징을 표시한 div명 - 미입력 또는 빈스트링시 pagingNavi를 초기화 한다.
 */
function fn_pagingReset(strViewId){
	if (util_chkReturn(strViewId, "s") != ""){
		strViewId = "pagingNavi";
	}
	
	jQuery("#" + strViewId + " > ").remove();//페이징 초기화
}


/**
 * 화면에 리스트 Data를 그리고 페이징를 표시하며, 자체적으로 페이지 이동 처리도 한다.
 * 리스트 Data가 전체가 나올경우만 사용한다.
 * 화면에 리스트성이 하나일 경우만 사용한다.
 * JSP의 복사할 리스트 ID는 tempListArea, 삽입할 리스트 ID는 listArea로 고정한다.
 * 페이징 부분은 <div id="pagingNavi" class="paging"></div> 으로 고정한다.
 * 
 * @param responseList	- Object - 리스트 Data
 * @param iPageRow	- Interger - 화면에보여주는 Data Row 수 - 미입력 또는 빈스트링 입력시 5기본 세팅
 * @param iPageBlock	- Interger - 페이징 번호 길이 수 - 미입력 또는 빈스트링 입력시 10기본 세팅
 * @param iCurrPage	- Interger - 현 페이지 번호 - 화면에서는 입력하지 않는다.
 */
var objTempListAreaClone = null;	// 최초 tempListArea 복사
var objResponseListClone = null;	// 최초 list Data 복사
var niPageRowClone = null;	//  최초 화면에 뿌려줄 줄수 복사
var niPageBlockClone = null;	// 최초 페이징 블럭수 복사
function drawListPagin(responseList, iPageRow, iPageBlock, iCurrPage){
	var bresponseList = true;
	//리스트가 없을경우 종료
	if(undefined==responseList||0==responseList.length){
		bresponseList = false;
		//return;
	} 
	
	// 화면에보여주는 Data Row 수를 미입력 하면 디폴트 5 세팅
	if (util_chkReturn(iPageRow, "s") == ""){
		iPageRow = 5;
	}
	
	// 페이징 번호 길이수를 미입력 하면 디폴트 10 세팅
	if (util_chkReturn(iPageBlock, "s") == ""){
		iPageBlock = 10;
	}
	
	if (util_chkReturn(iCurrPage, "s") == ""){
		iCurrPage = 1;
	}
	
	niPageRowClone = iPageRow;
	niPageBlockClone = iPageBlock;
	var listId = "listArea";	// 그려질 영역이 없을경우 디폴트명으로 설정
	var tempListId = "tempListArea";	// 임시 그려질 영역이 없을경우 디폴트명으로 설정
	var $listArea = $("#"+listId);
	var $tempListArea = $("#"+tempListId);
	var srcArea = null;	// 임시로 그려지는 영역 객체
	
	
	if (objTempListAreaClone != null){	// 처음이 아닐경우
		// listArea의 하위 엘리먼트를 삭제한다.
		jQuery.removeChild(listId);
		
	} else {	// 처음 호출시
		if (bresponseList == true) {
			objResponseListClone = util_copyObj(responseList);
		}
		
		objTempListAreaClone = $tempListArea;	// tempListArea를 복사해 둔다.
	}
	
	var nCount = 0;	// 리스트의 길이
	
	if (util_chkReturn(objResponseListClone) == true){
		nCount = objResponseListClone.length;
	}
	
	// Data가 없을 경우 처리
	if (util_chkReturn(nCount, "s") == "" || nCount == 0){
		var nTdCount = objTempListAreaClone.children("tr").children("td").length;
		$listArea.html("<tr><td colspan=\"" + nTdCount + "\" class=\"noData\">조회된 내역이 없습니다.</td></tr>");
	} else {
		// 리스트의 길이만큼 수행되면서 모든 오브젝트의 키에 오브젝트의 값을 그린다. (화면 오브젝트의 클래스명과 실제 전문의 키값은 동일하게 설정)
		for (var i = iPageRow*iCurrPage -iPageRow; i < iPageRow*iCurrPage; i++) {
			if (util_chkReturn(objResponseListClone[i], "s" != "")){	// Data가 있을 경우만
				var objData = objResponseListClone[i];
				srcArea = objTempListAreaClone.children().clone();
				//데이터가 포함된 row에 현재 rowData세팅
				srcArea.data("data",objData);
				for(var item in objData){
					//현재 row의 column에 rowData세팅
					srcArea.find("[name="+item+"]").data("data",objData);
					srcArea.find("[name="+item+"]").html(objData[item]);
				}
				
				$listArea.append(srcArea);
			}
		}
	}
	
	
	if (objTempListAreaClone != null){
		jQuery.remove(tempListId);
	}
	
	fn_pagingReset();
	pageNavigator(iCurrPage, nCount, iPageRow, iPageBlock, "fn_pagingAuto");
}

/**
 * drawListPagin으로 페이징 생성시 페이징 클릭시 호출되는 함수
 * 다음 페이지를 콜한다.
 * @param nPageNum
 */
function fn_pagingAuto(nPageNum){
	drawListPagin(objResponseListClone, niPageRowClone, niPageBlockClone, nPageNum);
	initEvent();	// 화면 이벤트 초기화 함수 재실행
}










/* *******************************************************************************************************************************
 * 										ajax페이징 처리  시작
 * *******************************************************************************************************************************/

/*
 * 변수선언부
 */
var ajaxOption = {"iTargetPage":1};
/**
 * ajax통신 결과 화면그리기 함수 
 * @param url 					: ajax navigation 명 ex) /mypage/common/HPME11S1
 * @param list					: 그려질 리스트
 * @param totCnt				: 그려질 리스트의 총 수
 * @param option 
 * 			├─iPageRow 			: 한 화면에 표시할 데이터수									예) 5					기본값) 5
 * 			├─iPageBlock 		: 한 화면에 표시할 페이지블럭 수								예) 10					기본값) 10
 * 			├─cutData 			: 리스트데이터를 커스텀하는 함수(false입력시 미수행)			예) cutListData			기본값) cutData
 * 			├─setData 			: 리스트데이터를 화면에 그려주는 함수(false입력시 미수행)		예) setListData			기본값) setData
 * 			├─initEvent 		: 리스트데이터에 대한 이벤트를 설정하는 함수(false입력시 미수행)	예) initListEvent		기본값) initEvent
 * 			├─listId 			: 리스트데이터를 그릴 영역의 Id								예) "listArea"			기본값) "listArea"
 * 			├─tempListId 		: 리스트데이터를 임시로 그릴 영역의 Id						예) "tempListArea"		기본값) "tempListArea"
 * 			└─pageAreaId		: 페이징 화면이 그려질 영역의 Id								예) "pagingNavi"			기본값) "pagingNavi"
 * 
 * 화면 setData() 부분에서 호출. url, list, totCnt 필수
 * 
 * 사용법) 	ajax페이징이 필요한 화면에서 기존 util_drawList부분을 아래와 같이 수정해 주시면 됩니다.
 * 사용예시) 	drawListAjax("/products/pnsa/HPPD11S0", list, listCnt);
 * 
 * delegate 또는 on 이벤트 등 사용하여 event를 다시걸어줄 필요가 없을 경우 initEvent 에  false를 주시면 됩니다.
 */
function drawListAjax(url, list, totCnt, option){
	/*
	 * 입력값에 대한 유효성 체크 및 설정
	 */
	
	//초기옵션
	var defaultOption = {
			"iPageRow" 		: 5,					//한 화면에 표시할 데이터수				 
			"iPageBlock"	: 10,					//한 화면에 표시할 페이지블럭 수				
			"cutData"		: cutData,				//리스트데이터를 커스텀하는 함수명				
			"setData"		: setData,				//리스트데이터를 화면에 그려주는 함수명	     
			"initEvent"		: initEvent,			//리스트데이터에 대한 이벤트를 설정하는 함수	 
			"listId"		: "listArea",			//리스트데이터를 그릴 영역의 Id				
			"tempListId"	: "tempListArea",		//리스트데이터를 임시로 그릴 영역의 Id			
			"pageAreaId"	: "pagingNavi"			//페이징 화면이 그려질 영역의 Id          
	};
	
	//입력이 있을경우 옵션 설정
	if(util_chkReturn(option,"s")!="" && typeof(option) == 'object'){
		if(util_chkReturn(option.iPageRow,"s")!="")		{defaultOption.iPageRow 	= option.iPageRow;}			//한 화면에 표시할 데이터수				
		if(util_chkReturn(option.iPageBlock,"s")!="")	{defaultOption.iPageBlock 	= option.iPageBlock;}		//한 화면에 표시할 페이지블럭 수				
		if(util_chkReturn(option.cutData,"s")!="")		{defaultOption.cutData 		= option.cutData;}			//리스트데이터를 커스텀하는 함수명				
		if(util_chkReturn(option.setData,"s")!="")		{defaultOption.setData 		= option.setData;}			//리스트데이터를 화면에 그려주는 함수명	
		if(util_chkReturn(option.initEvent,"s")!="")	{defaultOption.initEvent 	= option.initEvent;}		//리스트데이터에 대한 이벤트를 설정하는 함수	
		if(util_chkReturn(option.listId,"s")!="")		{defaultOption.listId 		= option.iPageRowlistId;}	//리스트데이터를 그릴 영역의 Id				
		if(util_chkReturn(option.tempListId,"s")!="")	{defaultOption.tempListId 	= option.tempListId;}		//리스트데이터를 임시로 그릴 영역의 Id			
		if(util_chkReturn(option.pageAreaId,"s")!="")	{defaultOption.pageAreaId 	= option.pageAreaId;}		//페이징 화면이 그려질 영역의 Id
	}
	
	//ajax시 수행할 데이터 설정
	ajaxOption["url"]		= url;
	ajaxOption["iPageRow"]	= defaultOption.iPageRow;
	ajaxOption["cutData"]	= defaultOption.cutData;
	ajaxOption["setData"]	= defaultOption.setData;
	ajaxOption["initEvent"]	= defaultOption.initEvent;
	//ajaxOption["inqrTotalCnt"]	= totCnt;
	
	//필수값 유효성 체크
	if(util_chkReturn(url,"s")==""){
		alert("ajaxPaging 호출시 호출 주소 입력값은 필수입니다.");
		return;
	}
	else if(util_chkReturn(totCnt,"s")==""||totCnt == 0||totCnt == "0"){
		if(util_chkReturn(list,"s")==""){
			list = [];
		}
		//util_drawList(list, defaultOption.listId, defaultOption.tempListId);//화면그리기
		//return;
	}

	/*
	 * 화면 그리기 부분
	 */
	util_drawList(list, defaultOption.listId, defaultOption.tempListId);//화면그리기
	fn_pagingReset(defaultOption.pageAreaId);//페이징 그리기
	pageNavigator(ajaxOption["iTargetPage"], totCnt, defaultOption.iPageRow, defaultOption.iPageBlock, "callListAjax");
}

/**
 * ajax통신 함수
 * @param iTargetPage
 * 화면 호출 필요 없음
 */
function callListAjax(iTargetPage){
	//이동할 페이지
	if (util_chkReturn(iTargetPage, "s") == ""){
		iTargetPage = 1;
	}
	ajaxOption["iTargetPage"] 		= iTargetPage;
	//Ajax통신할 파라메터
	objOrgInSData["PAGE_ROW_SIZE"] 	= ajaxOption["iPageRow"];	//row크기
	//objOrgInSData["INQR_TOTAL_CNT"]	= ajaxOption["inqrTotalCnt"];
	objOrgInSData["PAGE_FIRST_ROW"] = 1 + ((ajaxOption["iTargetPage"]-1)*ajaxOption["iPageRow"]);	//이동할페이지
	//Ajax통신
	util_ajaxPage(ajaxOption["url"], objOrgInSData, makeListDataAjax);
	
}
/**
 * ajax후처리 함수
 * @param response
 * 화면 호출 필요 없음
 */
function makeListDataAjax(response){
	//ajax통신으로 받은 데이터를 기존데이터에 세팅
	objOrgData		= response;
	objOrgOutData	= response.outData;
	objOrgInSData	= response.inSData;

	//수행함수
	//데이터커스텀 함수
	if(ajaxOption["cutData"]!=false){
		if(typeof ajaxOption["cutData"] === 'function'){
			ajaxOption["cutData"]();
		}else if(typeof ajaxOption["cutData"] === 'string'){
			eval(ajaxOption["cutData"] + '()');
		}
	}
	//데이터그리기 함수
	if(ajaxOption["setData"]!=false){
		if(typeof ajaxOption["setData"] === 'function'){
			ajaxOption["setData"]();
		}else if(typeof ajaxOption["setData"] === 'string'){
			eval(ajaxOption["setData"] + '()');
		}
	}
	//이벤트설정 함수
	if(ajaxOption["initEvent"]!=false){
		if(typeof ajaxOption["initEvent"] === 'function'){
			ajaxOption["initEvent"]();
		}else if(typeof ajaxOption["initEvent"] === 'string'){
			eval(ajaxOption["initEvent"] + '()');
		}
	}
}



/* *******************************************************************************************************************************
 * 										ajax페이징 처리  끝
 * *******************************************************************************************************************************/

/* *******************************************************************************************************************************
 * 										전체데이터 페이징 처리 시작
 * *******************************************************************************************************************************/
var pagingOption = {"iTargetPage":1};
function drawListPaging(list, option){
	/*
	 * 입력값에 대한 유효성 체크 및 설정
	 */
	//초기옵션
	var defaultOption = {
			"iPageRow" 		: 5,					//한 화면에 표시할 데이터수				 
			"iPageBlock"	: 10,					//한 화면에 표시할 페이지블럭 수				
			"initEvent"		: initEvent,			//리스트데이터에 대한 이벤트를 설정하는 함수	 
			"listId"		: "listArea",			//리스트데이터를 그릴 영역의 Id				
			"tempListId"	: "tempListArea",		//리스트데이터를 임시로 그릴 영역의 Id			
			"pageAreaId"	: "pagingNavi",			//페이징 화면이 그려질 영역의 Id   
			"iTargetPage"	: 1
	};
	
	//입력이 있을경우 옵션 설정
	if(util_chkReturn(option,"s")!="" && typeof(option) == 'object'){
		if(util_chkReturn(option.iPageRow,"s")!="")		{defaultOption.iPageRow 	= option.iPageRow;}			//한 화면에 표시할 데이터수				
		if(util_chkReturn(option.iPageBlock,"s")!="")	{defaultOption.iPageBlock 	= option.iPageBlock;}		//한 화면에 표시할 페이지블럭 수				
		if(util_chkReturn(option.initEvent,"s")!="")	{defaultOption.initEvent 	= option.initEvent;}		//리스트데이터에 대한 이벤트를 설정하는 함수	
		if(util_chkReturn(option.listId,"s")!="")		{defaultOption.listId 		= option.iPageRowlistId;}	//리스트데이터를 그릴 영역의 Id				
		if(util_chkReturn(option.tempListId,"s")!="")	{defaultOption.tempListId 	= option.tempListId;}		//리스트데이터를 임시로 그릴 영역의 Id			
		if(util_chkReturn(option.pageAreaId,"s")!="")	{defaultOption.pageAreaId 	= option.pageAreaId;}		//페이징 화면이 그려질 영역의 Id
		if(util_chkReturn(option.iTargetPage,"s")!="")	{defaultOption.iTargetPage 	= option.iTargetPage;}		//첫 페이징 번호
	}
	//리스트 유효성 체크하여 리스트가 없을경우 길이0 리스트로 치환
	if(typeof(list) != "object" || list.constructor != Array){
		list = [];
	}
	//데이터 설정
	pagingOption["iPageRow"]	= defaultOption.iPageRow;
	pagingOption["iPageBlock"]	= defaultOption.iPageBlock;
	pagingOption["listId"]		= defaultOption.listId;
	pagingOption["tempListId"]	= defaultOption.tempListId;
	pagingOption["cutData"]		= defaultOption.cutData;
	pagingOption["setData"]		= defaultOption.setData;
	pagingOption["initEvent"]	= defaultOption.initEvent;
	pagingOption["pageAreaId"]	= defaultOption.pageAreaId;
	pagingOption["iTargetPage"]	= defaultOption.iTargetPage;
	pagingOption["list"]		= list;
	
	/*
	 * 화면 그리기 부분
	 */
	callListPaging(pagingOption["iTargetPage"]);//화면그리기
}

function callListPaging(iTargetPage){
	//이동할 페이지
	if (util_chkReturn(iTargetPage, "s") == ""){
		iTargetPage = 1;
	}
	pagingOption["iTargetPage"] 		= iTargetPage;
	
	//페이징 처리
	var list = pagingOption["list"];
	fn_pagingReset(pagingOption.pageAreaId);//페이징 그리기
	pageNavigator(pagingOption["iTargetPage"], list.length, pagingOption.iPageRow, pagingOption.iPageBlock, "callListPaging");
	
	//리스트 처리
	var startIndex 	= (Number(iTargetPage) - 1) * (Number(pagingOption["iPageRow"]));
	var endIndex	= startIndex + Number(pagingOption["iPageRow"]);
	if(endIndex > list.length){
		endIndex = list.length;
	}
	list = list.slice(startIndex, endIndex);

	//리스트그리기
	makeListDataList(list);
	
}

function makeListDataList(list){
	//수행함수
	util_drawList(list, pagingOption.listId, pagingOption.tempListId);
	//이벤트설정 함수
	if(pagingOption["initEvent"]!=false){
		if(typeof pagingOption["initEvent"] === 'function'){
			pagingOption["initEvent"]();
		}else if(typeof pagingOption["initEvent"] === 'string'){
			eval(pagingOption["initEvent"] + '()');
		}
	}
}
/* *******************************************************************************************************************************
 * 										전체데이터 페이징 처리 끝
 * *******************************************************************************************************************************/
