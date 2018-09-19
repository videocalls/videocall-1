/**
 * @Name  : common.js
 * @Description : 공통 script function
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *    수정일    	수정자   	수정내용
 *  ----------  ------  ----------
 *  2016.04.29  신동진  	최초생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.04.29
 * @version 1.0
 *
 */

/**
 * 설명 		: 로그인 필요한 페이지 이동 시 페이지 이동 공통 처리
 * 사용방식 	: gfn_loginMove(loginUrl, url : 처리 url, param: return 후 처리될 param, msg : 페이지 이동시 사용할 msg)
 * 주의 		: 
 * 리턴 		: 없음
 */
function gfn_loginMove(loginUrl, url, param, msg, httpsFlag){
	//바탕 제거
	$("body").attr("style", "display:none");
	
	//바탕 제거
	$(".wrap").attr("style", "display:none");
	//로딩 제거
    gfn_setLoading(false);
	
    var objParam = new Object();
    
	//alert 처리
	if(util_chkReturn(msg, "s") == "") {
		msg = "로그인이 필요한 서비스 입니다.";
	}
	alert(msg);
	
	//param 셋팅
	if(util_chkReturn(param, "s") != "") {
		if(typeof param != "object"){
			var tmpParam = new Object();
			var paramArr = param.split("&");
				
			$.each(paramArr, function(idx,data){
				var paramDataArr = paramArr[idx].split("="); //ex)a
				var obj = eval("tmpParam."+paramDataArr[0]);
				obj = paramDataArr[1];
			});
			
			objParam.searchKeys = JSON.stringify(tmpParam);
		}else{
			objParam.searchKeys = JSON.stringify(param);
		}
	}
	
	//return url setting
	if(util_chkReturn(url, "s") != ""){
		objParam.returnListURL = url;
	}
	
	//httpsFlag setting
	if(util_chkReturn(httpsFlag, "s") != ""){
		objParam.returnHttpsURLFlag = httpsFlag;
	}
	
	var strHtml = "";
	strHtml += "<form id=\"loginForm\" name=\"loginForm\" method=\"POST\" action=\"" + loginUrl + "\">";
	strHtml += util_makeInputTag(objParam, "");// 데이터의 일반, 객체, 배열의 모든 종류의 타입
	strHtml += "</form>";
	
	$("body").append(strHtml);	// 화면에 form 등 생성
	$("#loginForm").submit();	// submit
}

/**
 * 설명 		: 공통 loading 처리
 * 사용방식 	: gfn_setLoading(flag : true[show], false[hide], msg : 처리메세지[null일경우 조회중입니다.], target : null일경우 body) 
 * 주의 		: 
 * 리턴 		: 처리 후 off 호출 해야함.
 */
function gfn_setLoading(flag, msg, target){
	var obj = $("#LOADING_DIV");
    var objFlag = $.trim(obj.html()) == "" ? true:false;
    var html = "";
    
    var divName = "LOADING_DIV";
    var className = "loading_wrap";
    
    if(util_chkReturn(target, "s") != ""){
    	divName = "LOADING_DIV_"+target;
    	className = "loading_wrap type2";
    }
	
	if(util_chkReturn(msg, "s") == "") msg = "조회중입니다.";
	
	html += "<div id='"+divName+"' class='"+className+"' style='display: none;'>";
	html += "<div class='loading_box'>";
	html += "<img src='/images/apt/viewLoading.gif' alt=''>";
	html += "<p id='"+divName+"_TEXT'>"+msg+"</p>";
	html += "</div>";
	html += "</div>";
	
	//obj 생성
    if(objFlag){
    	if(util_chkReturn(target, "s") == ""){
    		$("body").append(html);
    	}else{
    		$("#"+target).append(html);
    	}
        obj = $("#"+divName);
    }
    
    //on
    if(flag){
    	$("#"+divName+"_TEXT").html(msg);
    	
        obj.show();
        
    //off
    }else{
        obj.hide();
    }
}

/**
 * 설명 		: null인지 여부 체크 후, null일 경우 dVal값으로 치환
 * 사용방식 	: gfn_nvl('체크할 값', 'null일경우 반환할 값')
 * 주의 		: 결과값
 * 리턴 		: 없음
 */
function gfn_nvl(tVal, dVal){
	var rtnStr = "";
	rtnStr = tVal;
	
	if(tVal == null || tVal == "null" || tVal == "undefined" || $.trim(tVal) == ""){
		if(dVal == null || dVal == "null" || dVal == "undefined" || $.trim(dVal) == ""){
			rtnStr = "";
		}else{
			rtnStr = dVal;
		}
	}
	
	return rtnStr;
}

/**
 * 설명 		: spt에서 null인지 여부 체크 후, null일 경우 dVal값으로 치환
 * 사용방식 	: sptFn_nvl('체크할 값', 'null일경우 반환할 값')
 * 주의 		: 결과값
 * 리턴 		: true
 */
function sptFn_nvl(tVal, dVal){
	var rtnStr = "";
	
	if(tVal == null || tVal == "null" || tVal == "undefined" || $.trim(tVal) == ""){		
		rtnStr = true;	
	}else{		
		rtnStr = false;
	}
	
	return rtnStr;
}

/**
 * 설명 		: date format으로 return
 * 사용방식 	: gfn_formatDate(date)
 * 주의 		: yyyymmdd로 넘겨야하지만, yyyy-mm-dd로 넘겨지면 무방
 * 리턴 		: yyyy.mm.dd
 */
function gfn_formatDate(d){
	return d.replace(/-/g, '').replace(/(\d{4})(\d{2})(\d{2})/g, '$1-$2-$3');
}

/**
 * 설명 		: 전체선택여부
 * 사용방식 	: gfn_isSelectAll(코드명:String) 
 * 주의 		: 체크박스의 name 입력. 
 * 리턴 		: true : 전체선택, false : 단일선택
 */
function gfn_isSelectAll(codeNm){
	return allCheckDiv = $("#"+codeNm+"_all").is(":checked");	
}

/**
 * 설명 		: checkbox onclick 이벤트 추가 및 초기화 
 * 		  		1.일반 항목 onclick 이벤트 추가
 * 		  		2.전체 버튼 onclick 이벤트
 * 사용방식 	: gfn_initCheckbox(코드명:String) 
 * 주의 		: 코드명 		:체크박스 그룹의 name을 입력. 
 *            전체코드의 id는 name + _all로 동일해야한다.
 * 리턴 		: 없음
 */  
function gfn_initCheckbox(codeNm, allSelectAt){
	var allCheckDiv = gfn_isSelectAll(codeNm);
	
	//전체 제외한 체크박스 이벤트 추가 & 체크박스 선택 표시
	$("input[name="+codeNm+"]").each(function() {
		var id = $(this).attr("id");
		
		//선택 event 셋팅
		if(id != codeNm+"_all"){
			$(this).attr('onclick', "javascript:gfn_selectCode('"+$(this).attr("id")+"',"+allSelectAt+");");
		}else{
			$(this).attr('onclick', "javascript:gfn_selectAllCode('"+codeNm+"',"+allSelectAt+");");
		}
	});
}

/**
 * 설명 		: 체크박스 전체선택 
 * 사용방식 	: gfn_selectAllCode(코드명:String) 
 * 주의 		: 체크박스 그룹의 name을 입력.
 * 리턴 		: 없음
 */  
function gfn_selectAllCode(codeNm){
	//전체 제외한 체크박스 check false
	$("input[name="+codeNm+"]").each(function() {
		var id = $(this).attr("id");
		
		//선택 event 셋팅
		if(id != codeNm+"_all"){
			$(this).prop('checked', false);
			$(this).attr('checked', false);
		}else{
			$(this).prop('checked', true);
			$(this).attr('checked', true);
		}
	});
}

/**
 * 설명 		: 단일 체크박스 제어 
 * 사용방식 	: gfn_selectCode(코드ID:String) 
 * 주의 		: 체크박스의 ID 입력.
 * 		      전체체크여부가 true일 경우에만 선택 체크박스가 없을 경우 전체버튼 체크 됨. 
 * 리턴 		: 없음
 */
function gfn_selectCode(codeId){
	var codeNm = $("input[id="+codeId+"]").attr("name");
	//현재 선택한 코드값이 true일때 전체를 해제한다.
	if($("#"+codeId).is(":checked")){
		$("#"+codeNm+"_all").prop('checked', false);
	}
	
	//모두 선택 해지 상태 일땐 전체값으로 셋팅
	if($("input[name="+codeNm+"]:checked").length == 0){
		$("#"+codeNm+"_all").prop('checked', true);
	}
	
	//모두 선택 일때 전체로 치환
	var allCheckDiv = $("input[name="+codeNm+"]:checked").length == ($("input[name="+codeNm+"]").length - 1) ? true : false;
	//선택할 수 있는게 한개일 경우에는 한개만 선택 할 수 있게 처리
	if($("input[name="+codeNm+"]:checked").length > 2){
		if(allCheckDiv){
			//전체 제외한 체크박스 false
			$("input[name="+codeNm+"]").each(function() {
				var id = $(this).attr("id");
				
				//선택 event 셋팅
				if(id != codeNm+"_all"){
					$(this).prop('checked', false);
				}else{
					$(this).prop('checked', true);
				}
			});
		}
	}
}

/**
 * 설명 		: 전체선택여부 값 셋팅
 * 사용방식 	: gfn_setSelectAllYn(코드명:String) -> 조회전 parameter 셋팅전에 호출한다. 
 * 주의 		: 체크박스의 allYn 값을 셋팅한다.
 * 			  전체 선택여부의 값은 name + AllYn으로 동일하게 셋팅한다.
 * 리턴 		: true : 전체선택, false : 단일선택
 */
function gfn_setSelectAllYn(codeNm){
	var flag = gfn_isSelectAll(codeNm);
	var result = "N";
    if(flag){
    	result = "Y";
    }
    
    //전체 선택여부의 값은 name + AllYn으로 동일하게 셋팅한다.
	$("#"+codeNm+"AllYn").val(result);
}

/**
 * 설명 		: 첨부파일 저장 ajax처럼 처리
 * 사용방식 	: gfn_filePage(target:file이 있는 formID, url : url, callBackFunc : 처리결과 function) 
 * 주의 		: form의 enctype='multipart/form-data'로 셋팅
 * 			  처리결과는 cmm/fileView.jsp로 가며, json형태로 결과를 셋팅하며 root명칭은 result로 한다.
 * 리턴 		: 
 */
function gfn_filePage(target, url, callBackFunc){
	var formObj = $("#"+target);
 
    //generate a random id
    var iframeId = 'unique' + (new Date().getTime());
 
    //create an empty iframe
    var iframe = $('<iframe src="javascript:false;" id="'+iframeId+'" name="'+iframeId+'" width="1000px" height="100px" />');
 
    //hide it
    iframe.hide();
 
    //set form target to iframe
    formObj.attr('target',iframeId);
 
    //Add iframe to body
    iframe.appendTo('body');
    
    //callbackfunc 셋팅
    if($("input[name=callBackFunc]", "form[name="+target+"]").length <= 0){
    	$("#"+target+":last").append("<input type='hidden' name='callBackFunc' value='"+callBackFunc+"' />");	
    }else{
    	$("input[name=callBackFunc]", "form[name="+target+"]").val(callBackFunc);
    }
    
    util_moveRequest(target, url, iframeId);
}

/**
 * 설명 		: 첨부파일 init
 * 사용방식 	: gfn_initFile(file Id, file size(kb)) 
 * 주의 		: file의 길이체크
 * 리턴 		: 
 */
function gfn_initFile(fileId, maxKBSize){
	var maxSize = maxKBSize * 1000;
	
	$('#'+fileId).bind("change", function(){
		if($(this).val() != ""){
			if(this.files[0].size > maxSize){
				alert("파일의 최대 크기는 "+maxKBSize+" KB 입니다.");
				$(this).val("");
			}
		}
    });
}

/**
 * 설명 		: 첨부파일 저장 시 확장자 return
 * 사용방식 	: gfn_getFileExt(file Id) 
 * 주의 		: file의 경로체크하여 확장자 리턴
 * 리턴 		: 
 */
function gfn_getFileExt(fileId){
	var extension = "";
	
	//첨부파일 체크
    if(util_chkReturn($.trim($('#file').val()), "s") != ""){
    	//확장자 체크
	    var filename = $('#file').val();
	    extension = filename.replace(/^.*\./, '');
	    if (extension == filename) {
	        extension = '';
	    } else {
	        extension = extension.toLowerCase();
	    }
    }
    
    return extension;
}

/**
 * 설명 		: 첨부파일 저장 시 확장자 return
 * 사용방식 	: gfn_rowspanMerge(className) 
 * 주의 		: file의 경로체크하여 확장자 리턴
 * 리턴 		: 
 */
function gfn_rowspanMerge(className){
	//className : td에 추가한 Class  
	$('.'+className).each(function(index){
		var that=$(this).text();
		var row=0;
		$('.'+className).each(function(i){
			if (that==$(this).text()){
				if (row!=0){
				    $(this).addClass('remove')
				}    
				row++;
			}
		});
		
		$(this).attr('rowspan',row);  
		$('.remove').remove();
	});
}

/**
 * 설명 		: array 중보 제거
 * 사용방식 	: gfn_arrayUnique(arr : 대상 array) 
 * 주의 		: arr의 값이 null이 아니어야함.
 * 리턴 		: 
 */
function gfn_arrayUnique(arr){
	var resultArr = new Array();
		
	$.each(arr, function(i, el){
	    if($.inArray(el, resultArr) === -1) resultArr.push(el);
	})
	
	return resultArr;
}

/*
 * ID 유효성 검증 함수(도큐먼트id값)
 */
function gfn_validationCheckId(paramId){
	var paramVal = $("#"+paramId).val();
	var paramFocus = $("#"+paramId).focus();
	
	if(util_chkReturn(paramVal, "s") == ""){
		alert('아이디를 입력하세요.');
		paramFocus;
		return false;
	}
	if(paramVal.length <= 3 || paramVal.length >= 16){
		alert('아이디는 4자이상 15자이하 입니다.');
		paramFocus;
		return false;
	}
	if(Number(paramVal)){
		alert('아이디는 숫자만 사용할 수 없습니다.');
		paramFocus;
		return false;
	}
    if(!/^[a-zA-Z]/.test(paramVal)){
        alert('아이디는 영문으로 시작해야 합니다.');
        paramFocus;
        return false;
    }
    if(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/.test(paramVal)){
        alert('아이디안에 한글은 포함될 수 없습니다.');
        paramFocus;
        return false;
    }
    return true;
}

/*
 * 비밀번호 유효성 검증 함수(도큐먼트id값)
 */
function gfn_validationCheckPw(paramId, flag, paramData){
	var paramVal = $("#"+paramId).val();
	var paramFocus = $("#"+paramId).focus();
	
	//flag가 Y일 경우 키보드 보안을 거친 값이다.
	if(util_chkReturn(flag, "s") == "Y"){
		paramVal = paramData;
	}
	
	if(util_chkReturn(paramVal, "s") == ""){
		alert('비밀번호를 입력하세요.');
		paramFocus;
		return false;
	}
	if(paramVal.length <= 7 || paramVal.length >= 16){
		alert('비밀번호는 8자 이상 15자 이하입니다.');
		paramFocus;
		return false;
	}
    if(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/.test(paramVal)){
        alert('비밀번호안에 한글은 사용할 수 없습니다.');
        paramFocus;
        return false;
    }
    if(/(\w)\1\1\1/.test(paramVal)){
    	alert('비밀번호안에 같은문자는 4번 이상 사용할 수 없습니다.');
        paramFocus;
        return false;
    }
    var pwChkCnt1 = 0;
    if(/[0-9]/.test(paramVal)){ //숫자
    	pwChkCnt1++;
    }
    var pwChkCnt2 = 0;
    if(/[A-Z]/.test(paramVal)){ //대문자
    	pwChkCnt2++;
    }
    var pwChkCnt3 = 0;
    if(/[~!@\#$%<>^&*\()\-=+_\’]/.test(paramVal)){ //특수문자
    	pwChkCnt3++;
    }
    if(/[a-z]/.test(paramVal)){ //대문자
    	pwChkCnt2++;
    }
    
    //막아야되는 비밀번호 확인
    if(/[\"\\]/.test(paramVal)){ //특수문자
    	alert("입력불가능한 비밀번호를 입력하셨습니다.");
    	return false;
    }
    
    var pwChkCnt = pwChkCnt1 + pwChkCnt2 + pwChkCnt3;
    if(pwChkCnt <= 2){
        alert('비밀번호는 영문 대/소문자, 숫자, 특수문자 중 3개이상의 조합이여야만 합니다.');
        paramFocus;
        return false;
    }
    
    //연속된숫자
    if(/(0123)|(1234)|(2345)|(3456)|(4567)|(5678)|(6789)|(7890)/.test(paramVal)){// 연속된 숫자 정규식
        alert('비밀번호는 4회이상의 연속된 숫자를 사용할 수 없습니다.');
        paramFocus;
        return false;
    }

    //연속된숫자
    if(/(0987)|(9876)|(8765)|(7654)|(6543)|(5432)|(4321)|(3210)/.test(paramVal)){// 연속된 숫자 정규식
    	alert('비밀번호는 4회이상의 연속된 숫자를 사용할 수 없습니다.');
    	paramFocus;
    	return false;
    }
    
	//연속된 문자 확인
	var ckAlpa = "abcdefghijklmnopqrstuvwxyz";
	for(var i=0; i<ckAlpa.length; i++){
		if(i+4 > ckAlpa.length){
			break;
		}else{
			if(paramVal.toLowerCase().indexOf(ckAlpa.substring(i,i+4)) != -1){
				if(ckAlpa.substring(i,i+paramVal.length).length > 3){
			    	alert('비밀번호는 4회이상의 연속된 문자를 사용할 수 없습니다.');
			    	paramFocus;
					return false;
				}
			}
		}
	}
	
	//연속된 문자 확인
	var ckAlpa2 = "zyxwvutsrqponmlkjihgfedcba";
	for(var i=0; i <ckAlpa2.length; i++){
		if(i+4 > ckAlpa2.length){
			break;
		}else{
			if(paramVal.toLowerCase().indexOf(ckAlpa2.substring(i,i+4)) != -1){
				if(ckAlpa2.substring(i,i+paramVal.length).length > 3){
			    	alert('비밀번호는 4회이상의 연속된 문자를 사용할 수 없습니다.');
			    	paramFocus;
					return false;
				}
			}
		}
	}
	return true;
}

/**
 * 설명 		: 공지사항 첨부파일 다운로드
 * 사용방식 	: gfn_noticeFileDown(fileId : 첨부파일 ID, fileSeq: 첨부파일 seq) 
 * 주의 		: 
 * 리턴 		: 
 */
function gfn_noticeFileDown(fileId, fileSeq){
	var random_id = String(new Date().getTime());
	
	//target form
	var formId = "fileForm_"+random_id;
	var formHtml = "<form name='"+formId+"' id='"+formId+"'>";
	formHtml += "<input type='hidden' name='fileId' value='"+fileId+"' />";
	formHtml += "<input type='hidden' name='fileSeq' value='"+fileSeq+"' />";
	formHtml += "</form>";
	
	var form = $(formHtml);
	
	form.appendTo('body');
	
	//generate a random id
    var iframeId = 'unique' + random_id;
 
    //create an empty iframe
    var iframe = $('<iframe src="javascript:false;" id="'+iframeId+'" name="'+iframeId+'" width="1000px" height="100px" />');
 
    //hide it
    iframe.hide();
 
    //set form target to iframe
    form.attr('target', "fileForm_" + iframeId);
 
    //Add iframe to body
    iframe.appendTo('body');
    
    util_moveRequest(formId, "/cmm/noti/notiFileDown.do", iframeId);
}

/**
 * 설명 		: 키보드 보안 대상 input text 클리어
 * 사용방식 	: gfn_clearE2EText(target : obj id, reLoadFlag : 키보드 focus event 리로드여부[옵션])
 * 주의 		: 
 * 리턴 		: 없음
 */
function gfn_clearE2EText(target, reLoadFlag){
	//키보드 보안 연동 비밀번호 초기화
    $ASTX2.clearE2EText( document.getElementById(target) );
	
    if(util_chkReturn(reLoadFlag, "s") != ""){
    	if(reLoadFlag){
			//focus를 input에 줘서 키보드 보안 걸린 input의 focus를 리로드한다.
		    $("#"+target).blur();
		    $("#"+target).focus();
    	}
    }else{
    	//focus를 input에 줘서 키보드 보안 걸린 input의 focus를 리로드한다.
	    $("#"+target).blur();
	    $("#"+target).focus();
    }
}