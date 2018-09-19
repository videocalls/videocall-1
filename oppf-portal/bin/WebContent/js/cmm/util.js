/**
 * 
 * *** 페이지 오픈/이동 관련 함수 **************************************************
 * util_movePage	: form 방식으로 화면을 이동한다.
 * util_ajaxPage	: ajax 통신을한다.
 * util_ajaxPageJson: ajax 통신을한다. parameter가 json형태
 * util_modalPage	: modal 팝업을 띄운다.
 * closeModal		: modal 팝업을 닫는다. 
 * 
 * *** 마스크 처리 관련 함수 **************************************************
 * util_telMask		: 전화 번호 Mask 처리(구분자 "-" 삽입되어 리턴)(구분자 "-" 삽입된 것도 처리가능)
 * util_regMask		: 주민등록번호 Mask 처리(구분자 "-" 삽입되어 리턴)(구분자 "-" 삽입된 것도 처리가능)
 * util_nameMask	: 성명 Mask 처리(앞뒤 마지막 글자를 재외하고 모두 '*'처리, 외자의 경우 뒷자리만 처리)
 * util_idMask      : id Mask 처리(4번째 글자까지를 제외하고 모두 '*'처리)
 * util_contNoMask	: 증권번호 Mask 처리
 * util_emailMask	: 이메일 Mask 처리
 * util_acctMask	: 계좌번호 Mask 처리 - '-'가 업을 경우 뒤 4자리 마스크처리, 있을경우 마지막 '-'이후 마스크 처리
 * util_addrMask	: 주소 Mask 처리 - 전체 주소
 * util_addrMaskTwo : 주소 Mask 처리 - 기본 주소와 상세 주소가 나누어진 경우
 * 
 * 
 * *** 데이터 포메터 관련 함수 **************************************************
 * util_setCommas	: 숫자의 3자리 자릿수 컴마 표시를 한다.(소수형도 같이 사용 가능) - 입력된 값이 null, undefined, 빈스트링일 경우 대체 텍스트를 표시할 수 있다.
 * util_getCommas	: 숫자의 3자리 자릿수 컴마 삭제를 한다.(소수형도 같이 사용 가능) - 입력된 값이 null, undefined, 빈스트링일 경우 대체 텍스트를 표시할 수 있다.
 * util_setCommaInput : 텍스트 필드에 입력한 값에 3자리마다 콤마(,)를 붙인다.
 * util_ceil	: 소수점 이하 올림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * util_round	: 소수점 이하 반올림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * util_floor	: 소수점 이하 버림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * util_setRate	: 비율 기본형으로 리턴한다. 모든 데이터는 소수점 2자리까지 버림하여 출력됨 - 표준출력
 * util_setEmpty: 문자열 기본형으로 리턴.
 * util_setNum	: 숫자 및 금액 기본입력형 리턴, bType을 true로 주면 자리수 표시된 정수로 리턴한다.
 * util_setZero	: 숫자형 문자열의 자릿수를 입력받아 왼쪽을 0으로 채운다.
 * 
 * 
 * *** 공백제거 및 문자열처리 **************************************************
 * util_lTrim		: 문자열 좌측의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * util_mTrim		: 문자열 중간의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * util_rTrim		: 문자열 우측의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * util_trim		: 공백 제거(좌우) 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * util_replaceAll	: 문자열 치환
 * util_subStrL		: 입력된 str을 입력받은 길이만큼 왼쪽에서부터 잘라서 return 한다.
 * util_subStrR		: 입력된 str을 입력받은 길이만큼 오른쪽에서부터 잘라서 return 한다.
 * util_setJuminBar	: 주민등록 번호 '-'를 추가한다.
 * util_setHtmlParsing	: html 특사 문자코드등을 html로 변환 한다.
 * util_setHtmlParsing2	: html 특사 문자코드,엔터 등을 text 변환 한다.
 * util_setStrCutDot : 일정길이 만큼 텍스트를 잘라내고 접미어를 붙인다.
 * 
 * *** 데이터 체크 함수 **************************************************
 * util_chkReturn	: 입력된 data가 null, undefined 인지 체크 판단하여 key 값에 따른 값을 리턴한다.
 * util_isEmail		: 이메일 입력 유효성체크
 * util_isTelno		: 휴대폰번호 입력 유효성 체크
 * util_isJuminno	: 주민번호 입력 유효성 체크 (데이터 타입 및 형식만 체크) 
 * util_isJumin		: 주민번호 입력 유효성 체크 (데이터 타입 및 형식, 실제 주민번호의 유효성까지 체크)
 * util_isCrdCard	: 신용카드 입력 유효성 체크
 * util_isSecCard	: 보안카드 입력 유효성 체크
 * util_isValidDate	: 입력날짜가 기준일보다 이전일인지 체크한다. (기본값은 현재일 이전일일 경우를 체크)
 * util_calcBytes	: 문자열길이 체크.영문인지 한글인지 체크해서 길이를 반환
 * util_isKor		: 입력값이 한글만인지 체크(문자열 기준)
 * util_isEng		: 입력값이 영문만인지 체크(문자열 기준)
 * util_isNum		: 입력값이 숫자만인지 체크(문자열 기준)
 * util_isEngAddr	: 영문주소(영문,특수문,숫자만) 입력되었는지 체크
 * util_inputNumChk : 숫자 입력시 입력체크(입력필드 기준)
 * util_inputEngChk : 영문 입력시 입력체크(입력필드 기준)
 * util_inputKorChk : 한글 입력시 입력체크(입력필드 기준)
 * chkSpecialCharaters : 특수 문자 입력 체크 (문자열 기준)
 * 
 * *** 이벤트 처리 함수	- 공통 유효성 체크 (아래 입력 필드에 대해서 공통 함수를 사용해주세요) **************************************************
 * util_intDateValid	(조회화면 기간 조회 입력 필드 이벤트바인드)		: 날짜 조회시 시작일과 종료일 사이의 유효성을 체크한다
 * util_inputName 		(입력화면 이름입력 필드 이벤트바인드)				: 이름입력필드 초기화
 * util_inputNum 		(입력화면 숫자입력 필드 이벤트바인드)				: 숫자입력필드 초기화
 * util_inputAmt 		(입력화면 금액입력 필드 이벤트바인드)				: 금액입력필드 초기화
 * util_inputJumin1 	(입력화면 주민번호앞자리입력 필드 이벤트바인드)		: 주민번호앞자리입력필드 초기화
 * util_inputJumin2 	(입력화면 주민번호뒷자리입력 필드 이벤트바인드)		: 주민번호뒷자리입력필드 초기화 
 * util_inputEmail1 	(입력화면 이메일아이디입력 필드 이벤트바인드)		: 이메일앞자리입력필드 초기화
 * util_inputEmail2 	(입력화면 이메일도메인입력 필드 이벤트바인드)		: 이메일뒷자리입력필드 초기화
 * util_inputTel2 		(입력화면 전화번호중간자리입력 필드 이벤트바인드)	: 전화번호중간자리입력필드 초기화
 * util_inputTel3 		(입력화면 전화번호뒷자리입력 필드 이벤트바인드)		: 전화번호뒷자리입력필드 초기
 * util_inputCardNum	(입력화면 신용카드번호입력 필드 이벤트바인드)		: 신용카드번호입력필드 초기
 * util_inputSecCard	(입력화면 보안카드번호입력 필드 이벤트바인드)		: 보안카드번호입력필드 초기
 * 
 * 
 * *** 날짜 처리 관련 함수 **************************************************
 * util_isDate		: 입력된 문자열이 YYYYMMDD의 날짜형식인지 확인한다.
 * util_isDate6		: 입력된 문자열이 YYYYMM의 날짜형식인지 확인한다.
 * util_setFmDate	: YYYYMMDD 형식 문자열을 YYYY-MM-DD 형식으로 변환 - strToken 미입력시는 "-"으로 기본 파싱하며 값이 있을경우 해당값으로 파싱
 * util_setFmDateTime : 년월일시분초(yyyyMMddHHmmss) 를 yyyy-MM-dd HH:mm:ss 포메팅한다. 혹은 yyyyMMddHHmm를 yyyy-MM-dd HH:mm로 변경
 * 						변수 nType을 6을 세팅할경우 yyyy-MM-dd만 리턴
 * 						변수 nType을 12을 세팅할경우 yyyy-MM-dd HH:mm만 리턴
 * util_getDate		: 서버 날짜를 yyyyMMdd 형식으로 추출한다. - strToken 값을 줄경우 해당 구분자를 삽입한다.
 * util_getTime		: 서버 시간을 HHmm 형식으로 추출한다. - strToken 값을 줄경우 해당 구분자를 삽입하고, strSec 값을 줄경우 초까지 리턴
 * util_addDate		: yyyyMMdd 타입의 날짜를 받아 년,월,일에 날자를 더하거나 뺀다. - 변수 strReType에 'n'을 세팅하면 int형으로 리턴한다.
 *  
 *  
 * *** 화면 그리기 함수 **************************************************
 * util_drawList			: 입력받은 데이터에 매핑된 key에 맞춰 자동으로 리스트를 그려준다.
 * util_drawMap				: 입력받은 데이터에 매핑된 key에 맞춰 자동으로 맵을 그려준다.
 * util_drawSelectBoxOption	: 셀렉트박스의 옵션을 자동으로 그려준다.
 * util_drawRadioBoxOption	: 라디오박스의 옵션을 자동으로 그려준다.
 * util_drawInputYMD		: 년월일 입력 셀렉트박스 자동생성함수
 * util_drawSelectBoxOptionNumber : 셀렉트박스 옵션에 숫자 자동생성함수
 * util_drawListNon			: 테이블 리스트의 tbody에 "조회된 내역이 없습니다."를 넣어준다.
 * 
 * 
 * *** 기타 함수 **************************************************
 * util_copyObj				: JSON 타입 Object를 deep copy한다.
 * util_delKeyObj			: JSON 타입 Object의 원소(키)를 삭제한다.
 * util_concatObj			: JSON 타입 Object를 서로 연결한다.
 * util_makeFileUploadForm	: 파일 업로드 폼을 동적으로 생성한다. (파일 업로드가 필요한 페이지 초기화함수에서 호출)
 * util_getTrData			: util_drawList로 그린 영역의 row데이터 전체를 가져온다.
 * util_makeInputTag		: 동적으로 inputTag를 만든다.
 * 
 * jQuery 확장, script 확장 구현부
 * jQuery.extend
 * 		- jQuery.stringify	: JSON.stringify 브라우저 호환용 확장부	
 * 		- jQuery.parse		: JSON.parse 브라우저 호환용 확장부
 * 		- jQuery.keys		: Object.keys 브라우저 호환용 확장부
 * 		- remove			: jQuery의 remove가 IE구버전에서 하위 엘리멘탈만 삭제하는 경우가 있음.
 * 		- removeChild		: 특정 엘리먼트의 ID를 넘겨주면 그 하위 엘리먼트를 삭제한다.
 * Array
 * Array.prototype.unique	: 배열의 중복요소 제거
 * 
 * Map			: 	javascript Map 을 HashMap으로 확장
 * Map.prototype
 * showLoading	:	// 모달 로딩 시작
 * closeLoading	:	// 모달 로딩 종료
 * util_objstrPasing	: array object를 받아 key값 기준으로 value값에 구분자를 삽입하여 Object리턴한다. 구분자는 /@
 * util_strobjPasing	: 구분자(/@)로 되어있는 스트링 문자열 Object를 Array 형태로 만들어 리턴한다.
 * 
 * 
 * *** 화면 업무처리 함수 **************************************************
 * util_getBanInfoList      : 최근 이체실적 계좌 ,모든계좌,계좌 이체일 정보등을 가져옵니다 
 * 
 * 
 * *** 달력관련 함수 **************************************************
 * util_setCalendar1j	: Input Calendar 조회 시작일 1주전 날자, 조회 종료일 금일 날자 세팅
 * util_setCalendar15d	: Input Calendar 조회 시작일 15일전 날자, 조회 종료일 금일 날자 세팅
 * util_setCalendar1m	: Input Calendar 조회 시작일 1개월전 날자, 조회 종료일 금일 날자 세팅
 * util_setCalendar3m	: Input Calendar 조회 시작일 3개월전 날자, 조회 종료일 금일 날자 세팅
 * util_setCalendarDate	: 조회시작일, 조회종료일 inputbox에 날자를 세팅한다.(util_setCalendar1j 등에서 사용 한다.)
 * util_chkDateStartUp	: 조회시작일과 조회종료일을 비교하여 조회 시작일이 조회종료일 이후일경우 false 리턴
 * util_chkDateToDay	: 조회일이 금일 이후 날짜인지 판단하여 이후일경우 false 리턴
 * util_chkDateGap		: 조회시작일과 조회종료일이 특정 일수 차이인지 판단하여 범위 밖일경우 false 리턴
 * util_chkCalendar01	: 조회시작일과 조회종료일이 있는 두개의 Calendard의 벨리데이션 체크를 한다.(특정 일수 차이 1년으로 고정되어 있음.)
 * 
 * 
 * *** dataList 함수 **************************************************
 * 
 * 
 */

/**************************************************************************************************************************************************
 * 													페이지 오픈/이동 관련 함수
 **************************************************************************************************************************************************/

/**
 * form 방식으로 화면을 이동한다.
 * 화면에 자동으로 form 및 input hidden을 생성하여 submit까지 실행한다.
 * @param strUrl	- String	- url을 입력한다. .do등의 확장자는 입력하지 않는다.
 * @param objParam	- Object	- 파라메터로 넘길 Object, 함 수 호출시 입력하지 않으면 페이지 이동만 한다. 
 *                                파라메터 Object 예 
 *                                var obj = new Object();
 *                                obj.a = 1;
 *                                obj.b = 2;
 * @param strTarget	- String	- form의 타겟을 지정한다.
 *                                _blank : 새로운 창에 표시
 */

/** IE에서 console.log 에러나는 현상을 위해 추가 */

var debugging = true; // or false
if (typeof console == "undefined" || typeof console.log == "undefined")
	var console = {
		log : function() {
		}
	};
else if (!debugging && typeof console != "undefined")
	console.log = function() {
	};

/**
 * lpad 함수
 * @param value 입력된 값
 * @param count 자리수
 * @returns {*}
 */
function pad(value, count) {
	value = value + '';
	return value.length >= count ? value : new Array(count - value.length + 1).join('0') + value;
}

/**
 * @param strUrl
 * @param objParam
 * @param strTarget
 */
function util_movePage(strUrl, objParam, strTarget) {
	if (util_chkReturn(strUrl, "s") == "") {
		alert("이동할 URL이 설정되지 않았습니다.");
		return;
	}

	if (strUrl == "imsi") {
		alert("개발준비중 입니다.");
		return;
	}

	// strUrl 에 parameter를 넘긴경우 objParam으로 셋팅 작업
	var urlParamSettingYn = 'N';
	var obj = new Object;
	var strUrlArr = strUrl.split("?"); // ex)aaa.jsp?a=1&b=2&c=3
	if (strUrlArr.length > 1) {
		urlParamSettingYn = 'Y';
		var tmpstrUrlArr = strUrlArr[1].split("&"); // ex)a=1&b=2&c=3
		strUrl = strUrlArr[0];
		if (tmpstrUrlArr.length > 1) {
			$.each(tmpstrUrlArr, function(idx, data) {
				var tmp2strUrlArr = tmpstrUrlArr[idx].split("="); // ex)a
				if (tmp2strUrlArr.length > 1) {
					obj[tmp2strUrlArr[0]] = tmp2strUrlArr[1];
				} else {
					alert('잘못된 형식의 URL입니다.');
					return false;
				}
			});
		} else {
			var tmp2strUrlArr = tmpstrUrlArr[0].split("=");
			if (tmp2strUrlArr.length > 1) {
				obj[tmp2strUrlArr[0]] = tmp2strUrlArr[1];
			} else {
				alert('잘못된 형식의 URL입니다.');
				return false;
			}
		}
	}

	if (util_chkReturn(objParam, "s") == "") {
		objParam = new Object;
	} else if (typeof objParam != "object") { // objParam에 menuId 값을 넘긴경우 셋팅
		var tmp = objParam;
		objParam = new Object;
		objParam.menuId = tmp;
	}
	if (util_chkReturn(strTarget, "s") != "") {
		strTarget = "target=\"" + strTarget + "\"";
	} else {
		strTarget = "";
	}

	if (urlParamSettingYn == 'Y') {
		objParam = obj;
	}

	// showLoading(); // 모달 로딩 시작

	// 화면에 추가할 html text를 만든다.
	var strHtml = "";
	strHtml += "<form id=\"nextForm\" name=\"nextForm\" method=\"POST\" "
			+ strTarget + " action=\"" + strUrl + "\">";
	strHtml += util_makeInputTag(objParam, "");// 데이터의 일반, 객체, 배열의 모든 종류의 타입
	strHtml += "</form>";
	
	$("#nextForm").remove();
	$("body").append(strHtml); // 화면에 form 등 생성
	$("#nextForm").submit(); // submit
}

/**
 * 설명 : view(.jsp)내부의 폼을 전송합니다. 사용방식 : bizinfoRequest(폼 ID, 타겟 URL) 주의 : 폼ID와
 * 타겟URL은 반드시 존재해야 합니다 리턴 : 타겟URL에서 전송
 */
function util_moveRequest(frmId, Url, mTarget) {

	var retFrmId = '#' + frmId;
	// console.log(retFrmId);
	if (mTarget != 'undefined') {
		$(retFrmId).attr({
			action : Url,
			method : 'post',
			target : mTarget
		}).submit();
	} else {
		$(retFrmId).attr({
			action : Url,
			method : 'post'
		}).submit();
	}
	/*
	if ($('#loading'))
		$('#loading').show();
	*/
}

/**
 * ajax통신을 한다. Object data를 받아 처리하고 통신 성공시 특정 펑션을 호출하여 Object를 리턴한다.
 * 
 * @param strUrl -
 *            String - 이동할 URL 주소, .ajax는 생략한다
 * @param objParam -
 *            Object - 파라메타 오브젝트, 파라메타가 없을경우 빈스트링 처리 objParam.noLoading 값을 true로
 *            주면 로딩 이미지 안나타남
 * @param strCallBack -
 *            String - 통신성공시 호출할 펑션명, 미입력시 callAjaxData 펑션을 무조건 호출한다.
 * @bNoLoadingChk - Boolean - ture 일경우 로딩 안나타남
 */
function util_ajaxPage(strUrl, objParam, strCallBack) {

	if (util_chkReturn(strUrl, "s") == "") {
		alert("이동할 URL이 설정되지 않았습니다.");
		return;
	}

	if (util_chkReturn(objParam) == false) {
		objParam = "";
	}

	if (util_chkReturn(objParam.noLoading) == false) {
		// showLoading(); // 모달 로딩 시작
	}

	$.ajax({
		"type" : "POST",
		// "url" : strUrl + ".ajax",
		"url" : strUrl,
		"data" : objParam,
		"success" : function(data) {
			// data = jQuery.parse(data);
			var error_code = ((typeof data.result) == "undefined" ? false
					: data.result.ERROR_CODE);
			// 임시 콘솔 로그
			if (navigator.appName.indexOf("Microsoft") == -1) {
				console.log("ajax success result : ", data);
			}
			// 정상처리후 strCallBack 값이 있을경우 해당 펑션호출 없을경우 callAjaxData펑션 호출
			if (util_chkReturn(strCallBack, "s") != "") {
				//closeLoading(); // 모달 로딩 종료

				if (typeof strCallBack === 'function') {
					strCallBack(data.result);
				} else if (typeof strCallBack === 'string') {
					eval(strCallBack + '(data)');
				}
			} else {
				// closeLoading(); // 모달 로딩 종료
				callAjaxData(data);
			}
		},
		"error" : function(data) {
			// 로딩 호출
			gfn_setLoading(false);

			alert("처리에 실패하였습니다.\n관리자에게 문의해 주세요.");

			// 임시 콘솔 로그
			if (navigator.appName.indexOf("Microsoft") == -1) {
				console.log("ajax error result : ", data);
			}
			// closeLoading(); // 모달 로딩 종료
		}
	});
};

/**
 * ajax통신을 한다. Object data를 받아 처리하고 통신 성공시 특정 펑션을 호출하여 Object를 리턴한다.
 * 
 * @param strUrl -
 *            String - 이동할 URL 주소, .ajax는 생략한다
 * @param objParam -
 *            Object - 파라메타 오브젝트, 파라메타가 없을경우 빈스트링 처리 objParam.noLoading 값을 true로
 *            주면 로딩 이미지 안나타남
 * @param strCallBack -
 *            String - 통신성공시 호출할 펑션명, 미입력시 callAjaxData 펑션을 무조건 호출한다.
 * @bNoLoadingChk - Boolean - ture 일경우 로딩 안나타남
 */
function util_ajaxPageNoErrormsg(strUrl, objParam, strCallBack) {
    
    if (util_chkReturn(strUrl, "s") == "") {
        alert("이동할 URL이 설정되지 않았습니다.");
        return;
    }
    
    if (util_chkReturn(objParam) == false) {
        objParam = "";
    }
    
    if (util_chkReturn(objParam.noLoading) == false) {
        // showLoading(); // 모달 로딩 시작
    }
    
    $.ajax({
        "type" : "POST",
        // "url" : strUrl + ".ajax",
        "url" : strUrl,
        "data" : objParam,
        "success" : function(data) {
            // data = jQuery.parse(data);
            var error_code = ((typeof data.result) == "undefined" ? false
                    : data.result.ERROR_CODE);
            // 임시 콘솔 로그
            if (navigator.appName.indexOf("Microsoft") == -1) {
                console.log("ajax success result : ", data);
            }
            // 정상처리후 strCallBack 값이 있을경우 해당 펑션호출 없을경우 callAjaxData펑션 호출
            if (util_chkReturn(strCallBack, "s") != "") {
                //closeLoading(); // 모달 로딩 종료
                
                if (typeof strCallBack === 'function') {
                    strCallBack(data.result);
                } else if (typeof strCallBack === 'string') {
                    eval(strCallBack + '(data)');
                }
            } else {
                // closeLoading(); // 모달 로딩 종료
                callAjaxData(data);
            }
        },
        "error" : function(data) {
            // 로딩 호출
            gfn_setLoading(false);
            
//            alert("처리에 실패하였습니다.\n관리자에게 문의해 주세요.");
            
            // 임시 콘솔 로그
            if (navigator.appName.indexOf("Microsoft") == -1) {
                console.log("ajax error result : ", data);
            }
            // closeLoading(); // 모달 로딩 종료
        }
    });
};

/**
 * ajax통신을 한다. Object data를 받아 처리하고 통신 성공시 특정 펑션을 호출하여 Object를 리턴한다. 이때
 * parameter는 json 형태이다.
 * 
 * @param strUrl -
 *            String - 이동할 URL 주소, .ajax는 생략한다
 * @param objParam -
 *            Object - 파라메타 오브젝트, 파라메타가 없을경우 빈스트링 처리 objParam.noLoading 값을 true로
 *            주면 로딩 이미지 안나타남
 * @param strCallBack -
 *            String - 통신성공시 호출할 펑션명, 미입력시 callAjaxData 펑션을 무조건 호출한다.
 * @bNoLoadingChk - Boolean - ture 일경우 로딩 안나타남
 */
function util_ajaxPageJson(strUrl, objParam, strCallBack) {

	if (util_chkReturn(strUrl, "s") == "") {
		alert("이동할 URL이 설정되지 않았습니다.");
		return;
	}

	if (util_chkReturn(objParam) == false) {
		objParam = "";
	}

	if (util_chkReturn(objParam.noLoading) == false) {
		// showLoading(); // 모달 로딩 시작
	}

	$.ajaxSettings.traditional = true; // ajax 배열 parameter 처리 설정
	$.ajax({
		type : "post",
		contentType : "application/json",
		async : true,
		url : strUrl,
		data : JSON.stringify(objParam),
		success : function(data) {
			var error_code = ((typeof data.result) == "undefined" ? false
					: data.result.ERROR_CODE);
			// 임시 콘솔 로그
			if (navigator.appName.indexOf("Microsoft") == -1) {
				// console.log("ajax success result : ",data);
			}
			// 정상처리후 strCallBack 값이 있을경우 해당 펑션호출 없을경우 callAjaxData펑션 호출
			if (util_chkReturn(strCallBack, "s") != "") {
				//closeLoading(); // 모달 로딩 종료

				if (typeof strCallBack === 'function') {
					strCallBack(data.result);
				} else if (typeof strCallBack === 'string') {
					eval(strCallBack + '(data)');
				}
			} else {
				callAjaxData(data);
			}
		},
		error : function(data) {
			// 로딩 호출
			gfn_setLoading(false);

			alert("처리에 실패하였습니다.\n관리자에게 문의해 주세요.");
			// 임시 콘솔 로그
			if (navigator.appName.indexOf("Microsoft") == -1) {
				console.log("ajax error result : ", data);
			}
		}
	});
};

/**
 * 팝업을 띄워 새로운 페이지를 연다. objOption의 width(default:800px)와 height(default:700px) 값은
 * 선택 이다. objOption의 값을 세팅해주고자 하면 함수에 해당 처리부분 추가 필요 objOption 예) var objOption =
 * new Object(); objOption.type = "window";//(default:modal) objOption.width =
 * "850"; objOption.height = "630";
 * 
 * @param strUrl -
 *            String - 팝업에서 띄울 URL + parameter
 * @param objOption -
 *            Object - width, height 등의 팝업 옵션값
 * @param objData -
 *            Object - objData 값이 있을 경우 parameter를 자동 세팅한다.
 */
function util_modalPage(strUrl, objOption, objData) {
	if (util_chkReturn(strUrl, "s") == "") {
		alert("util_modalPage : 오픈할 URL을 확인해 주십시오.");
		return;
	}
	/*
	 * if (strUrl.substring(strUrl.length -4, strUrl.length) != ".jsp" &&
	 * strUrl.substring(strUrl.length -4, strUrl.length) != ".do"){ strUrl =
	 * strUrl + ".do"; }
	 */

	if (strUrl.indexOf("?") >= 0) {

	} else if (strUrl.indexOf(".do") >= 0) {

	} else {
		if ((strUrl.indexOf(".do") >= 0) || ((strUrl.indexOf(".jsp") >= 0))) {
		} else {
			strUrl = strUrl + ".do";
		}
	}
	
	/*
	// objData로 동적 parameter 생성
	if (util_chkReturn(objData, "s") != "") {
		var keys = [];

		for ( var k in objData) { // object의 키값을 구한다.
			keys.push(k);
		}

		var nCountI = keys.length;
		var chkCount = 0;

		for (var i = 0; i < nCountI; i++) {
			var strGetValue = eval("objData." + keys[i]);

			if (chkCount == 0) {
				strUrl = strUrl + "?" + keys[i] + "=" + strGetValue;
				chkCount = 1;
			} else {
				strUrl = strUrl + "&" + keys[i] + "=" + strGetValue;
			}
		}
	}
	*/

	if (util_chkReturn(objOption, "s") == "") {
		objOption = {};
	}

	if (util_chkReturn(objOption.type, "s") == "") {
		objOption.type = "modal";
	}
	// 가로크기
	if (util_chkReturn(objOption.width, "s") == "") {
		objOption.width = "";
	}

	// 세로크기
	if (util_chkReturn(objOption.height, "s") == "") {
		objOption.height = "";
	}

	// 로딩시 이미지 URL
	if (util_chkReturn(objOption.loadingImgUrl, "s") == "") {
		objOption.loadingImgUrl = "";
	}

	// 배경 투명도
	if (util_chkReturn(objOption.opacityBg, "s") == "") {
		objOption.opacityBg = 0.3;
	}

	// 청약 로딩 유무
	if (util_chkReturn(objOption.loadType, "s") == "") {
		objOption.loadType = false;
	}

	jQuery(this).JQmodal({
		popUrl : strUrl,
		objParam : objData,
		type : objOption.type,
		width : objOption.width,
		height : objOption.height,
		loadingImgUrl : objOption.loadingImgUrl,
		opacityBg : objOption.opacityBg,
		loadType : objOption.loadType,
		closeBtnClass : objOption.closeBtnClass,
		bgColor : objOption.bgColor

	});
}

/**
 * 팝업을 띄워 새로운 페이지를 연다. objOption의 width(default:800px)와 height(default:700px) 값은
 * 선택 이다. objOption의 값을 세팅해주고자 하면 함수에 해당 처리부분 추가 필요 objOption 예) var objOption =
 * new Object(); objOption.type = "window";//(default:modal) objOption.width =
 * "850"; objOption.height = "630";
 *
 * @param strUrl -
 *            String - 팝업에서 띄울 URL + parameter
 * @param objOption -
 *            Object - width, height 등의 팝업 옵션값
 * @param objData -
 *            Object - objData 값이 있을 경우 parameter를 자동 세팅한다.
 */
function util_modalPage2(strUrl, objOption, objData) {
	if (util_chkReturn(strUrl, "s") == "") {
		alert("util_modalPage : 오픈할 URL을 확인해 주십시오.");
		return;
	}
	/*
	 * if (strUrl.substring(strUrl.length -4, strUrl.length) != ".jsp" &&
	 * strUrl.substring(strUrl.length -4, strUrl.length) != ".do"){ strUrl =
	 * strUrl + ".do"; }
	 */

	if (strUrl.indexOf("?") >= 0) {

	} else if (strUrl.indexOf(".do") >= 0) {

	} else {
		if ((strUrl.indexOf(".do") >= 0) || ((strUrl.indexOf(".jsp") >= 0))) {
		} else {
			strUrl = strUrl + ".do";
		}
	}

	if (util_chkReturn(objOption, "s") == "") {
		objOption = {};
	}

	if (util_chkReturn(objOption.type, "s") == "") {
		objOption.type = "modal";
	}
	// 가로크기
	if (util_chkReturn(objOption.width, "s") == "") {
		objOption.width = "";
	}

	// 세로크기
	if (util_chkReturn(objOption.height, "s") == "") {
		objOption.height = "";
	}

	// 로딩시 이미지 URL
	if (util_chkReturn(objOption.loadingImgUrl, "s") == "") {
		objOption.loadingImgUrl = "";
	}

	// 배경 투명도
	if (util_chkReturn(objOption.opacityBg, "s") == "") {
		objOption.opacityBg = 0.3;
	}

	// 청약 로딩 유무
	if (util_chkReturn(objOption.loadType, "s") == "") {
		objOption.loadType = false;
	}

	jQuery(this).JQmodal2({
		popUrl : strUrl,
		objParam : objData,
		type : objOption.type,
		width : objOption.width,
		height : objOption.height,
		loadingImgUrl : objOption.loadingImgUrl,
		opacityBg : objOption.opacityBg,
		loadType : objOption.loadType,
		closeBtnClass : objOption.closeBtnClass,
		bgColor : objOption.bgColor

	});
}

/* 모달팝업 auto resize 함수 */
function gfn_modalPopupAutoResize(i) {
	try {
		var iframeHeight = (i).contentWindow.document.body.scrollHeight;
		if (Number(iframeHeight) >= 500) {
			(i).height = 547;
		} else {
			(i).height = iframeHeight;
		}
		var iframeWidth = (i).contentWindow.document.body.scrollWidth;
		(i).width = iframeWidth;
	} catch (e) {
	}
}

function gfn_modalPopupAutoWidthResize(i) {
	try {
		var iframeWidth = (i).contentWindow.document.body.scrollWidth;
		(i).width = iframeWidth;
	} catch (e) {
	}
}

function gfn_modalPopupAutoHeightResize(i) {
	try {
		var iframeHeight = (i).contentWindow.document.body.scrollHeight;
		if (Number(iframeHeight) >= 500) {
			(i).height = 547;
		} else {
			(i).height = iframeHeight;
		}
	} catch (e) {
	}
}

/*******************************************************************************
 * 마스크 처리 관련 함수
 ******************************************************************************/

/**
 * 전화 번호 Mask 처리(구분자 "-" 삽입되어 리턴)(구분자 "-" 삽입된 것도 처리가능)
 * 
 * @param tel_no -
 *            String - 전화번호 문자열
 * @returns '-'가 붙어 있는 마스킹된 전화번호
 */
function util_telMask(tel_no) {
	if (util_chkReturn(tel_no, "s") == "") {
		alert("util_telMask : 파라메타를 확인해 주십시오.");
		return "";
	}

	var strTemp = "****";

	if (tel_no.indexOf("-") != -1) { // 전화번호에 - 가있다면
		var arrTemp = tel_no.split("-");

		if (arrTemp.length >= 2) {
			strTemp = (arrTemp[1].length == 3) ? '***' : '****';
		}

		if (arrTemp.length == 1) {
			return (util_subStrL(tel_no, tel_no.length - 4) + strTemp);
		} else if (arrTemp.length == 2) {
			return (arrTemp[0] + "-" + strTemp + "-" + util_subStrR(tel_no, 4));
		} else {
			return (arrTemp[0] + "-" + strTemp + "-" + arrTemp[2]);
		}
	} else {
		if (tel_no.substr(0, 2) == "01") {
			if (tel_no.length == 10) {
				return util_subStrL(tel_no, 3) + "-***-"
						+ util_subStrR(tel_no, 4);
			} else {
				return util_subStrL(tel_no, 3) + "-****-"
						+ util_subStrL(tel_no, 4);
			}
		} else if (tel_no.substr(0, 2) == "02") {
			if (tel_no.length == 9) {
				return util_subStrL(tel_no, 2) + "-***-"
						+ util_subStrR(tel_no, 4);
			} else {
				return util_subStrL(tel_no, 2) + "-****-"
						+ util_subStrR(tel_no, 4);
			}
		} else if (tel_no.substr(0, 2) == "03" || tel_no.substr(0, 2) == "04"
				|| tel_no.substr(0, 2) == "05" || tel_no.substr(0, 2) == "06") {
			return util_subStrL(tel_no, 3) + "-***-" + util_subStrR(tel_no, 4);
		} else {
			alert("util_telMask : 정상적인 파라메타인지 확인해 주십시오.");
			return tel_no;
		}
	}
}

/**
 * 주민등록번호 Mask 처리(구분자 "-" 삽입되어 리턴)(구분자 "-" 삽입된 것도 처리가능)
 * 
 * @param reg_no -
 *            String - 주민등록번호 문자열
 * @returns '-'가 붙어 있는 마스킹된 주민등록번호
 */
function util_regMask(reg_no) {
	if (util_chkReturn(reg_no, "s") == "") {
		// alert("util_regMask : 파라메타를 확인해 주십시오.");
		return "-";
	}

	var strTemp = "******";

	if (reg_no.indexOf("-") == -1) { // 주민번호에 - 가없다면
		return (util_subStrL(reg_no, 6) + "-" + reg_no.substr(6, 1) + strTemp);
	} else {
		reg_no = util_replaceAll(reg_no, "-", "");
		return (util_subStrL(reg_no, 6) + "-" + reg_no.substr(6, 1) + strTemp);
	}
}

/**
 * 성명 Mask 처리(앞뒤 마지막 글자를 재외하고 모두 '*'처리, 외자의 경우 뒷자리만 처리)
 * 
 * @param custname -
 *            String - 성명 문자열
 * @returns 마스크 처리된 성명
 */
function util_nameMask(custname) {
	custname = new String(custname);
	var length = custname.length;
	var first = custname.substring(0, 1);
	var last = custname.substring(length - 1, length);
	var asterisk = '';

	if (custname.length > 2) { // 성명이 두자 초과인 경우
		for (var i = 0; i < length - 2; i++) {
			asterisk = asterisk + '*';
		}
		custname = first + asterisk + last;
	} else {
		custname = first + "*";
	}

	return custname;
}

/**
 * ID Mask 처리(4번째 글자까지를 제외하고 모두 '*'처리)
 * 
 * @param custid -
 *            String - ID 문자열
 * @returns 마스크 처리된 ID
 */
function util_idMask(custid) {
	custid = new String(custid);
	var length = custid.length;
	var first = custid.substring(0, 4);
	var first2 = custid.substring(0, 2);
	var asterisk = '';

	if (custid.length > 4) { // ID가 4자 초과인 경우(ID는 8글자 이상임)
		for (var i = 0; i < length - 4; i++) {
			asterisk = asterisk + '*';
		}
		custid = first + asterisk;
	} else {
		for (var i = 0; i < length - 2; i++) {
			asterisk = asterisk + '*';
		}
		custid = first + asterisk;
	}

	return custid;
}

/**
 * 증권번호 Mask 처리
 * 
 * @param obj -
 *            String - 증권번호 문자열
 * @returns 마스크처리된 증권번호 문자열
 */
function util_contNoMask(obj) {
	if (util_chkReturn(obj, "s") == "") {
		return "";
	}

	obj = util_replaceAll(obj, "-", "");
	var length = obj.length;

	if (length > 9) {
		return obj.substring(0, length - 7) + "****"
				+ obj.substring(length - 3, length);
	} else if (length <= 9 && length >= 7) {
		return obj.substring(0, length - 6) + "****"
				+ obj.substring(length - 2, length);
	} else {
		return obj;
	}
}

/**
 * 이메일 Mask 처리
 * 
 * @param addr -
 *            String - 이메일 문자열
 * @returns 마스크처리된 이메일 문자열
 */
function util_emailMask(addr) {
	if (util_chkReturn(addr, "s") == "") {
		return "";
	}

	if (addr.indexOf("@") == -1) {
		alert("이메일을 확인해 주십시오.");
		return "";
	}

	var splitValue = addr.split("@");
	var nLength = splitValue[0].length;
	var first = splitValue[0];

	if (nLength > 2) {
		return splitValue[0].substring(0, nLength - 2) + "**@" + splitValue[1];
	} else if (nLength <= 2) {
		return splitValue[0].substring(0, nLength - 1) + "*@" + splitValue[1];
	}

	return "";
}

/**
 * 계좌번호 Mask 처리 - '-'가 업을 경우 뒤 4자리 마스크처리, 있을경우 마지막 '-'이후 마스크 처리
 * 
 * @param value -
 *            String - 계좌번호 문자열
 * @returns
 */
function util_acctMask(value) {
	if (util_chkReturn(value, "s") == "") {
		return "";
	}

	if (value.length <= 5) {
		return value;
	}

	if (value.indexOf("-") != -1) { // '-'가 있을 경우
		var arrTemp = value.split("-");
		var strReText = "";
		for (var i = 0; i < arrTemp.length; i++) {
			if (i != arrTemp.length - 1) {
				strReText = strReText + arrTemp[i] + "-";
			} else {
				var strLast = "";
				for (var j = 0; j < arrTemp[i].length; j++) {
					strLast = strLast + "*";
				}

				strReText = strReText + strLast;
			}
		}
		return strReText;
	} else {
		return value.substring(0, value.length - 4) + "****";
	}

	return "";
}

/**
 * 주소 Mask 처리 - 전체 주소 "동 ", "읍 ", "면 ", "가 "로 찾아 뒤에는 모두 마스크 처리 해당 케이스가 없을경우 "
 * "으로 찾아 세번째 이후는 모두 마스크 처리
 * 
 * @param addr -
 *            String - 주소 문자열
 * @returns
 */
function util_addrMask(addr) {
	if (util_chkReturn(addr, "s") == "") {
		return "";
	}

	var strStAddr = "";
	var strEndMask = "";
	var indexDong = addr.indexOf("동 ");
	var indexUb = addr.indexOf("읍 ");
	var indexMyun = addr.indexOf("면 ");
	var indexGa = addr.indexOf("가 ");

	if (indexDong == -1 && indexUb == -1 && indexMyun == -1 && indexGa == -1) {
		// 동, 읍, 면, 가 로 검색 불가시
		var arrAddr = addr.split(" "); // 스페이스로 배열

		if (arrAddr.length > 3) { // 3번째 이상 마스크가 있을 경우
			var strEndAddr = "";
			var nCount = 0;

			// 마스크 처리를 위해 3번째 이상 텍스트 합침
			for (var i = 3; i < arrAddr.length; i++) {
				if (strEndAddr == "") {
					strEndAddr = arrAddr[i];
				} else {
					strEndAddr = strEndAddr + " " + arrAddr[i];
				}
			}

			// 텍스트 길이만큼 마스크 표시
			for (var i = 0; i < strEndAddr.length; i++) {
				strEndMask += "*";
			}

			// 주소 앞부분 합치기 위한 자릿수 체크
			if (arrAddr.length > 3) {
				nCount = 3;
			} else {
				nCount = arrAddr.length;
			}

			// 주소 앞부분 합침
			for (var i = 0; i < nCount; i++) {
				strStAddr = strStAddr + " " + arrAddr[i];
			}

		} else {
			return addr;
		}
	} else {
		var nIndexKey = -1;
		var nTotalLength = 0;

		if (indexDong != -1) {
			nIndexKey = indexDong; // 동
		} else if (indexUb != -1) {
			nIndexKey = indexUb; // 읍
		} else if (indexMyun != -1) {
			nIndexKey = indexMyun; // 면
		} else if (indexGa != -1) {
			nIndexKey = indexGa; // 가
		}

		nTotalLength = addr.length; // 전체길이

		if (nIndexKey == -1) {
			return addr;
		}

		strStAddr = addr.substring(0, nIndexKey + 1);
		strEndMask = "";

		// 텍스트 길이만큼 마스크 표시
		for (var i = 0; i < addr.substring(nIndexKey + 2, nTotalLength).length; i++) {
			strEndMask += "*";
		}

	}

	// 마스크 처리 부분이 없을 경우 바로 리턴
	if (strEndMask.length == 0) {
		return strStAddr;
	}

	// 마스크 처리부와 합쳐 리턴
	return strStAddr + " " + strEndMask;

	/*
	 * var strTemp = " ********"; var indexDong = addr.indexOf("동 "); var
	 * indexUb = addr.indexOf("읍 "); var indexMyun = addr.indexOf("면 "); var
	 * indexGa = addr.indexOf("가 ");
	 * 
	 * if (addr.length > 0) { if (indexDong != -1) { if (indexDong >=
	 * addr.length - 6) { var addrLength = addr.length; var strTempSub =
	 * addr.substr(0, addrLength - 9); return strTempSub + strTemp; } else {
	 * return addr.substr(0, indexDong + 1) + strTemp; } } else if (indexUb !=
	 * -1) { if (indexUb >= addr.length - 6) { var addrLength = addr.length; var
	 * strTempSub = addr.substr(0, addrLength - 9); return strTempSub + strTemp; }
	 * else { return addr.substr(0, indexUb + 1) + strTemp; } } else if
	 * (indexMyun != -1) { if (indexMyun >= addr.length - 6) { var addrLength =
	 * addr.length; var strTempSub = addr.substr(0, addrLength - 9); return
	 * strTempSub + strTemp; } else { return addr.substr(0, indexMyun + 1) +
	 * strTemp; } } else if (indexGa != -1) { if (indexGa >= addr.length - 6) {
	 * var addrLength = addr.length; var strTempSub = addr.substr(0, addrLength -
	 * 9); return strTempSub + strTemp; } else { return addr.substr(0, indexGa +
	 * 1) + strTemp; } } else { var addrLength = addr.length; var strTempSub =
	 * addr.substr(0, addrLength - 9); return strTempSub + strTemp; }
	 *  } else { return addr; }
	 */
}

/**
 * 주소 Mask 처리 - 기본 주소와 상세 주소가 나누어진 경우 "동 ", "읍 ", "면 ", "가 "로 찾아 뒤에는 모두 마스크 처리
 * 해당 케이스가 없을경우 " "으로 찾아 세번째 이후는 모두 마스크 처리
 * 
 * @param addr1 -
 *            String - 기본 주소 문자열
 * @param addr1 -
 *            String - 상세 주소 문자열
 * @returns
 */
function util_addrMaskTwo(addr1, addr2) {

	if (util_chkReturn(addr1, "s") == "") {
		return "";
	}

	if (util_chkReturn(addr2, "s") == "") {
		return "";
	}

	return util_addrMask(addr1 + " " + addr2);
}

/*******************************************************************************
 * 데이터 포메터 관련 함수
 ******************************************************************************/

/**
 * 소수점 이하 올림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * 
 * @param param - -
 *            변환할 data
 * @param nKey -
 *            Integer - 리턴할 자리수, 0일경우 올림처리 후 소수점이하 안보임
 * @param bNoStr -
 *            Boolean - true일 경우 숫자형 리턴, 미입력시 문자형
 * @returns
 */
function util_ceil(param, nKey, bNoStr) {
	if (util_chkReturn(param, "s") == "") {
		return "";
	} else if (util_chkReturn(nKey, "s") == "" || nKey < 0) {
		return param;
	}

	var nCipher = 1;
	for (var i = 0; i < nKey; i++) {
		nCipher = nCipher * 10;
	}

	if (util_chkReturn(bNoStr) == true && bNoStr == true) { // 숫자형으로 리턴을 원할경우 바로
		// 계산하여 리턴
		return (Math.ceil(Math.round(param * nCipher))) / nCipher;
	} else {
		var arrNumData = ((Math.ceil(Math.round(param * nCipher))) / nCipher)
				.toString().split(".");

		if (nKey == 0) { // 자리수가 0일경우 앞 정수부문 리턴
			return arrNumData[0];
		}

		var nCountLen = 0; // 뒷자리 length 초기화
		if (util_chkReturn(arrNumData[1], "s") != "") { // 뒷자리 null, undefined
			// 체크
			nCountLen = arrNumData[1].length; // 뒷자리 length값 세팅
		} else {
			arrNumData[1] = ""; // 뒷자리 빈스트링 세팅
		}

		for (nCountLen; nCountLen < nKey; nCountLen++) {
			arrNumData[1] = arrNumData[1] + "0"; // 원하는 자리수 만큼 0을 붙임
		}

		return arrNumData[0] + "." + arrNumData[1];

	}

	return "";
}

/**
 * 소수점 이하 반올림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * 
 * @param param - -
 *            변환할 data
 * @param nKey -
 *            Integer - 리턴할 자리수, 0일경우 반올림처리 후 소수점이하 안보임
 * @param bNoStr -
 *            Boolean - true일 경우 숫자형 리턴, 미입력시 문자형
 * @returns
 */
function util_round(param, nKey, bNoStr) {
	if (util_chkReturn(param, "s") == "") {
		return "";
	} else if (util_chkReturn(nKey, "s") == "" || nKey < 0) {
		return param;
	}

	var nCipher = 1;
	for (var i = 0; i < nKey; i++) {
		nCipher = nCipher * 10;
	}

	if (util_chkReturn(bNoStr) == true && bNoStr == true) { // 숫자형으로 리턴을 원할경우 바로
		// 계산하여 리턴
		return (Math.round(param * nCipher)) / nCipher;
	} else {
		var arrNumData = ((Math.round(param * nCipher)) / nCipher).toString()
				.split(".");

		if (nKey == 0) { // 자리수가 0일경우 앞 정수부문 리턴
			return arrNumData[0];
		}

		var nCountLen = 0; // 뒷자리 length 초기화
		if (util_chkReturn(arrNumData[1], "s") != "") { // 뒷자리 null, undefined
			// 체크
			nCountLen = arrNumData[1].length; // 뒷자리 length값 세팅
		} else {
			arrNumData[1] = ""; // 뒷자리 빈스트링 세팅
		}

		for (nCountLen; nCountLen < nKey; nCountLen++) {
			arrNumData[1] = arrNumData[1] + "0"; // 원하는 자리수 만큼 0을 붙임
		}

		return arrNumData[0] + "." + arrNumData[1];

	}

	return "";
}

/**
 * 소수점 이하 버림하여 몇자리까지 나오게 한다. (입력된 자리수 보다 작을경우 '0'을 붙여 리턴, 마지막 파라메타에 따라 숫자형 리턴)
 * 
 * @param param - -
 *            변환할 data
 * @param nKey -
 *            Integer - 리턴할 자리수, 0일경우 버림처리 후 소수점이하 안보임
 * @param bNoStr -
 *            Boolean - true일 경우 숫자형 리턴, 미입력시 문자형
 * @returns
 */
function util_floor(param, nKey, bNoStr) {
	if (util_chkReturn(param, "s") == "") {
		return "";
	} else if (util_chkReturn(nKey, "s") == "" || nKey < 0) {
		return param;
	}

	var nCipher = 1;
	for (var i = 0; i < nKey; i++) {
		nCipher = nCipher * 10;
	}

	if (util_chkReturn(bNoStr) == true && bNoStr == true) { // 숫자형으로 리턴을 원할경우 바로
		// 계산하여 리턴
		return (Math.floor(Math.round(param * nCipher))) / nCipher;
	} else {
		var arrNumData = ((Math.floor(Math.round(param * nCipher))) / nCipher)
				.toString().split(".");

		if (nKey == 0) { // 자리수가 0일경우 앞 정수부문 리턴
			return arrNumData[0];
		}

		var nCountLen = 0; // 뒷자리 length 초기화
		if (util_chkReturn(arrNumData[1], "s") != "") { // 뒷자리 null, undefined
			// 체크
			nCountLen = arrNumData[1].length; // 뒷자리 length값 세팅
		} else {
			arrNumData[1] = ""; // 뒷자리 빈스트링 세팅
		}

		for (nCountLen; nCountLen < nKey; nCountLen++) {
			arrNumData[1] = arrNumData[1] + "0"; // 원하는 자리수 만큼 0을 붙임
		}

		return arrNumData[0] + "." + arrNumData[1];

	}

	return "";
}

/**
 * 숫자의 3자리 자릿수 컴마 표시를 한다.(소수형도 같이 사용 가능) 입력된 값이 null, undefined, 빈스트링일 경우 대체
 * 텍스트를 표시할 수 있다.
 * 
 * @param strNum -
 *            String - 숫자형 문자열
 * @param strReText -
 *            String - 입력된 숫자형 문자열이 null, undefined일경우 대체 텍스트 미입력시 빈스트링 리턴
 * @returns
 */
function util_setCommas(strNum, strReText) {
	var bCheck = true;

	// 입력된 문자열이 숫자와 '.'으로만 이루어져 있는가? 빈스트링은 문자로 본다.
	if (util_isFloat(strNum) == false) {
		bCheck = false;
	}

	if (bCheck) {
		// strNum = String(Number(strNum));//13-
		strNum = strNum + "";
		var strfirstNum = strNum.split(".")[0];
		var strBackNum = "";

		if (strNum.split(".").length != 1) {
			strBackNum = "." + strNum.split(".")[1];
		}

		var re = /,|\s+/g;
		strfirstNum = strfirstNum.replace(re, "");

		re = /(-?\d+)(\d{3})/;
		while (re.test(strfirstNum)) {
			strfirstNum = strfirstNum.replace(re, "$1,$2");
		}

		return strfirstNum + strBackNum;
	} else {
		if (util_chkReturn(strReText, "s") == "") {
			return "";
		} else {
			return strReText;
		}
	}
}

/**
 * 숫자의 3자리 자릿수 컴마 삭제를 한다.(소수형도 같이 사용 가능) 입력된 값이 null, undefined, 빈스트링일 경우 대체
 * 텍스트를 표시할 수 있다.
 * 
 * @param strNum -
 *            String - 숫자형 문자열
 * @param strReText -
 *            String - 입력된 숫자형 문자열이 null, undefined일경우 대체 텍스트 미입력시 빈스트링 리턴
 * @returns
 */
function util_getCommas(strNum, strReText) {
	var bCheck = true;

	// null, undefined, 빈스트링 체크
	if (util_chkReturn(strNum, "s") == "") {
		bCheck = false;
	}

	if (bCheck) {
		var re = /,/g;
		return strNum.replace(re, "");
	} else {
		// null, undefined, 빈스트링 체크
		if (util_chkReturn(strReText, "s") == "") {
			return "";
		} else {
			return strReText;
		}
	}
}

/**
 * 텍스트 필드에 입력한 값에 3자리마다 콤마(,)를 붙인다. 텍스트 필드에 아래를 기입한다. onkeyup="toMoney(this)"
 * 
 * @param field
 *            텍스트 필드
 */
function util_setCommaInput(field) {
	var value = field.value;

	if (util_chkReturn(value, "s") == "") {
		return "";
	}

	var indexOfPoint = value.indexOf(".");
	if (indexOfPoint == -1) {
		// field.value = formatCommas(value);
		field.value = util_setCommas(value);

	} else {
		// field.value = formatCommas(value.substring(0, indexOfPoint)) +
		field.value = util_setCommas(value.substring(0, indexOfPoint))
				+ value.substring(indexOfPoint, value.length);
	}
}

/**
 * 입력데이터가 숫자이거나 숫자형 문자열일 경우 숫자를 반환, 그외의 경우 0을 반환하는 함수 0으로 반환되는 경우 null,
 * undefined, 문자열이면서 숫자형태가 아닌 데이터, 오브젝트 등.
 * 
 * @param num
 * @returns returnNum
 */
function util_Number(num) {
	var returnNum = 0;
	/*
	 * if(typeof num == "string"){ if((!/[^0-9\.\-]/.test(num)) &&
	 * (num.indexOf(".")==num.lastIndexOf(".")) &&
	 * (num.indexOf("-")==-1||num.indexOf("-")==0) ){ returnNum = Number(num); }
	 * }else if(typeof num == "number"){ returnNum = num; }
	 */
	if (!isNaN(num)) {
		returnNum = Number(num);
	}
	return returnNum;
}

/**
 * 숫자 및 금액 기본입력형 리턴, bType을 true로 주면 자리수 표시된 정수(금액)로 리턴한다.
 * 
 * @param num
 * @returns
 */
function util_setNum(num, bType) {
	if (util_chkReturn(num, "s") == "") {
		return 0;
	}
	if (isNaN(String(num).replace(/[,]/g, ""))) {
		return 0;
	}
	if (num == "") {
		return 0;
	}
	if (util_chkReturn(bType)) {
		if (bType) {
			return util_setCommas(util_floor(num, 0));
		}
	} else {
		return num;
	}

	return num;
}

/**
 * 비율 표시. 소숫점 2자리 이하를 버림하여 소수점 2자리까지 표시 데이터를 넣으면 숫자형이 아닐 경우 0.00 으로 출력 (문자열일 경우
 * 숫자형 변환이 가능할 경우 해당 숫자 출력) bType을 true로 주면 자리수 표시된 정수로 리턴한다.
 * 
 * @param rate
 * @returns
 */
function util_setRate(rate, bType) {
	rate = Number(rate);
	if (isNaN(rate)) {
		rate = 0.00;
	}
	rate = Math.floor(rate * 100) / 100;

	if (util_chkReturn(bType)) {
		return util_setCommas(rate.toFixed(2));
	} else {
		return rate.toFixed(2);
	}
}

/**
 * 빈값을 -로 출력하는 함수 데이터가 출력이 가능한 형태이면 해당값을 출력하고 이외의 경우나 공백일 경우는 - 를 출력한다.
 * 
 * @param str
 * @returns
 */
function util_setEmpty(str) {
	if ((typeof str == "string" && str.replace(/[\s]/g, "") != "")
			|| typeof str == "number") {
		return str;
	} else {
		return "-";
	}
}

function util_setStr(str) {
	return util_setEmpty(str);
}

/*******************************************************************************
 * 공백제거 및 문자열처리
 ******************************************************************************/

/**
 * 문자열 좌측의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * 
 * @param strParam
 * @returns
 */
function util_lTrim(strParam) {
	if (util_chkReturn(strParam, "s") == "") {
		return "";
	}

	while (strParam.substring(0, 1) == ' ') {
		strParam = strParam.substring(1, strParam.length);
	}

	return strParam;
}

/**
 * 문자열 중간의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * 
 * @param strParam
 * @returns
 */
function util_mTrim(strParam) {
	if (util_chkReturn(strParam, "s") == "") {
		return "";
	}

	for (var i = 0; i < strParam.length; i++) {
		if (strParam.substring(i, i + 1) == ' ')
			strParam = strParam.substring(0, i)
					+ strParam.substring(i + 1, strParam.length);
	}
	return strParam;
}

/**
 * 문자열 우측의 공백 제거 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * 
 * @param strParam
 * @returns
 */
function util_rTrim(strParam) {
	if (util_chkReturn(strParam, "s") == "") {
		return "";
	}

	while (strParam.substring(strParam.length - 1, strParam.length) == ' ')
		strParam = strParam.substring(0, strParam.length - 1);
	return strParam;
}

/**
 * 공백 제거(좌우) 처리 함수 (null, undefined, 빈스트링일경우 빈스트링 return)
 * 
 * @param strParam
 * @returns {String}
 */
function util_trim(strParam) {
	var strReData = "";
	strReData = util_lTrim(strParam);
	strReData = util_rTrim(strReData);

	return strReData;
}

/**
 * 문자열 치환
 * 
 * @param strString -
 *            String - 대상 문자열
 * @param strChar -
 *            String - 변경할 문자열
 * @param strChar -
 *            String - 변경될 문자열
 * @returns 변경된 문자열
 */
function util_replaceAll(strString, strAfter, strNext) {

	if (util_chkReturn(strString) == false) {
		return "";
	}

	if (util_chkReturn(strAfter, "s") == "" || util_chkReturn(strNext) == false) {
		alert("util_replaceAll : 파라메타를 확인해 주세요.");
	}

	// if (strAfter == "."){ // '.'은 정규식으로 처리되지 않아 별도 처리
	var tmpStr = strString;
	while (tmpStr.indexOf(strAfter) != -1) {
		tmpStr = tmpStr.replace(strAfter, strNext);
	}
	return tmpStr;
	// }

	// return strString.replace(eval("/" + strAfter + "/gi"), strNext);
}

/**
 * 입력된 str을 입력받은 길이만큼 왼쪽에서부터 잘라서 return 한다.
 * 
 * @param str -
 *            String - 대상 문자열
 * @param len -
 *            Integer - 자를 길이
 * @returns
 */
function util_subStrL(str, len, strToken) {
	if (util_chkReturn(str) == false || util_chkReturn(len, "s") == "") {
		// alert("util_subStr : 파라메터를 확인해 주십시오.");
		if (util_chkReturn(strToken, "s") == "") {
			return "";
		} else {
			return strToken;
		}
	}

	str = str.substr(0, len);
	return str;
}

/**
 * 입력된 str을 입력받은 길이만큼 오른쪽에서부터 잘라서 return 한다.
 * 
 * @param str -
 *            String - 대상 문자열
 * @param len -
 *            Integer - 자를 길이
 * @param strToken -
 *            String - 예외시 대체 문자열
 * @returns
 */
function util_subStrR(str, len, strToken) {
	if (util_chkReturn(str) == false || util_chkReturn(len, "s") == "") {
		// alert("util_subStrR : 파라메터를 확인해 주십시오.");
		if (util_chkReturn(strToken, "s") == "") {
			return "";
		} else {
			return strToken;
		}
	}

	str = str.substr(str.length - len, str.length);
	return str;
}

/**
 * 주민등록 번호 '-'를 추가한다.
 * 
 * @param strJumin -
 *            String - 문자열 주민등록번호
 */
function util_setJuminBar(strJumin) {
	if (util_chkReturn(strJumin, "s") == "") {
		return "";
	}

	var strJuminC = strJumin + "";
	if (strJuminC.length < 13) {
		return strJumin;
	} else if (strJuminC.length >= 13) {
		strJuminC = util_replaceAll(strJuminC, "-", "");
		if (strJuminC.length != 13) {
			return strJumin;
		}
	}

	return strJuminC.substring(0, 6) + "-" + strJuminC.substring(6, 13);
}

/**
 * 좌측에 0 채우기
 * 
 * @param input
 *            숫자 (문자열 또는 숫자)
 * @param cipher
 *            자릿수 (10자리이면 10입력)
 * @returns 55 입력 10자리로 호출시 0000000055를 리턴
 */
function util_setZero(input, cipher) {
	if (isNaN(input)) {
		return input;
	}
	input = String(input);
	var inputLen = input.length; // 입력값 자릿수
	var zeroLen = cipher - inputLen;

	if (zeroLen <= 0) {
		return input;
	}

	var zeroStr = "";
	for (var i = 0; i < zeroLen; i++) {
		zeroStr += "0";
	}
	return zeroStr + input;

}

/**
 * html특수문자 코드등을 html text로 변환한다.
 */
function util_setHtmlParsing(strData) {
	strData = util_replaceAll(strData, "&#33;", "!");
	strData = util_replaceAll(strData, "&#34;", '"');
	strData = util_replaceAll(strData, "&#35;", "#");
	strData = util_replaceAll(strData, "&#36;", "$");
	strData = util_replaceAll(strData, "&#37;", "%");
	strData = util_replaceAll(strData, "&#38;", "&");
	strData = util_replaceAll(strData, "&#39;", "'");
	strData = util_replaceAll(strData, "&#40;", "(");
	strData = util_replaceAll(strData, "&#41;", ")");
	strData = util_replaceAll(strData, "&#42;", "*");
	strData = util_replaceAll(strData, "&#43;", "+");
	strData = util_replaceAll(strData, "&#44;", ",");
	strData = util_replaceAll(strData, "&#45;", "-");
	strData = util_replaceAll(strData, "&#46;", ".");
	strData = util_replaceAll(strData, "&#47;", "/");

	strData = util_replaceAll(strData, "&#58;", ":");
	strData = util_replaceAll(strData, "&#59;", ";");
	strData = util_replaceAll(strData, "&#60;", "<");
	strData = util_replaceAll(strData, "&#61;", "=");
	strData = util_replaceAll(strData, "&#62;", ">");
	strData = util_replaceAll(strData, "&#63;", "?");
	strData = util_replaceAll(strData, "&#64;", "@");

	strData = util_replaceAll(strData, "&#91;", "[");
	strData = util_replaceAll(strData, "&#92;", "\\");
	strData = util_replaceAll(strData, "&#93;", "]");
	strData = util_replaceAll(strData, "&#94;", "^");
	strData = util_replaceAll(strData, "&#95;", "_");
	strData = util_replaceAll(strData, "&#96;", "`");

	strData = util_replaceAll(strData, "&#123;", "{");
	strData = util_replaceAll(strData, "&#124;", "|");
	strData = util_replaceAll(strData, "&#125;", "}");
	strData = util_replaceAll(strData, "&#126;", "~");

	strData = util_replaceAll(strData, "&amp;", "&");
	strData = util_replaceAll(strData, "&lt;", "<");
	strData = util_replaceAll(strData, "&gt;", ">");

	// strData = util_replaceAll(strData, "&nbsp;", " ");
	strData = util_replaceAll(strData, " ", "&nbsp;");
	strData = util_replaceAll(strData, "\r\n", "<br />");
	strData = util_replaceAll(strData, "\n;", "<br />");
	
	//암호화값
	strData = util_replaceAll(strData, "%60", "`");
	strData = util_replaceAll(strData, "%40", "@");
	strData = util_replaceAll(strData, "%23", "#");
	strData = util_replaceAll(strData, "%24", "$");
	strData = util_replaceAll(strData, "%25", "%");
	strData = util_replaceAll(strData, "%5E", "^");
	strData = util_replaceAll(strData, "%26", "&");
	strData = util_replaceAll(strData, "%5B", "[");
	strData = util_replaceAll(strData, "%5D", "[");
	strData = util_replaceAll(strData, "%7B", "{");
	strData = util_replaceAll(strData, "%7D", "}");
	strData = util_replaceAll(strData, "%3B", ";");
	strData = util_replaceAll(strData, "%3A", ":");
	strData = util_replaceAll(strData, "%7C", "|");
	strData = util_replaceAll(strData, "%3C", "<");
	strData = util_replaceAll(strData, "%3E", ">");
	strData = util_replaceAll(strData, "%2C", ",");
	strData = util_replaceAll(strData, "%2F", "/");
	strData = util_replaceAll(strData, "%3F", "?");
	
	return strData;
}

/**
 * html특수문자 코드등을 html text로 변환한다.
 */
function util_setHtmlParsing2(strData) {
	strData = util_replaceAll(strData, "&#33;", "!");
	strData = util_replaceAll(strData, "&#34;", '"');
	strData = util_replaceAll(strData, "&#35;", "#");
	strData = util_replaceAll(strData, "&#36;", "$");
	strData = util_replaceAll(strData, "&#37;", "%");
	strData = util_replaceAll(strData, "&#38;", "&");
	strData = util_replaceAll(strData, "&#39;", "'");
	strData = util_replaceAll(strData, "&#40;", "(");
	strData = util_replaceAll(strData, "&#41;", ")");
	strData = util_replaceAll(strData, "&#42;", "*");
	strData = util_replaceAll(strData, "&#43;", "+");
	strData = util_replaceAll(strData, "&#44;", ",");
	strData = util_replaceAll(strData, "&#45;", "-");
	strData = util_replaceAll(strData, "&#46;", ".");
	strData = util_replaceAll(strData, "&#47;", "/");

	strData = util_replaceAll(strData, "&#58;", ":");
	strData = util_replaceAll(strData, "&#59;", ";");
	strData = util_replaceAll(strData, "&#60;", "<");
	strData = util_replaceAll(strData, "&#61;", "=");
	strData = util_replaceAll(strData, "&#62;", ">");
	strData = util_replaceAll(strData, "&#63;", "?");
	strData = util_replaceAll(strData, "&#64;", "@");

	strData = util_replaceAll(strData, "&#91;", "[");
	strData = util_replaceAll(strData, "&#92;", "\\");
	strData = util_replaceAll(strData, "&#93;", "]");
	strData = util_replaceAll(strData, "&#94;", "^");
	strData = util_replaceAll(strData, "&#95;", "_");
	strData = util_replaceAll(strData, "&#96;", "`");

	strData = util_replaceAll(strData, "&#123;", "{");
	strData = util_replaceAll(strData, "&#124;", "|");
	strData = util_replaceAll(strData, "&#125;", "}");
	strData = util_replaceAll(strData, "&#126;", "~");

	strData = util_replaceAll(strData, "&amp;", "&");
	strData = util_replaceAll(strData, "&lt;", "<");
	strData = util_replaceAll(strData, "&gt;", ">");
	strData = util_replaceAll(strData, "&nbsp;", " ");
	
	//암호화값
	strData = util_replaceAll(strData, "%60", "`");
	strData = util_replaceAll(strData, "%40", "@");
	strData = util_replaceAll(strData, "%23", "#");
	strData = util_replaceAll(strData, "%24", "$");
	strData = util_replaceAll(strData, "%25", "%");
	strData = util_replaceAll(strData, "%5E", "^");
	strData = util_replaceAll(strData, "%26", "&");
	strData = util_replaceAll(strData, "%5B", "[");
	strData = util_replaceAll(strData, "%5D", "[");
	strData = util_replaceAll(strData, "%7B", "{");
	strData = util_replaceAll(strData, "%7D", "}");
	strData = util_replaceAll(strData, "%3B", ";");
	strData = util_replaceAll(strData, "%3A", ":");
	strData = util_replaceAll(strData, "%7C", "|");
	strData = util_replaceAll(strData, "%3C", "<");
	strData = util_replaceAll(strData, "%3E", ">");
	strData = util_replaceAll(strData, "%2C", ",");
	strData = util_replaceAll(strData, "%2F", "/");
	strData = util_replaceAll(strData, "%3F", "?");
	
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") > -1) {
		strData = util_replaceAll(strData, "\n", "\r");
	}

	return strData;
}

/**
 * 일정길이 만큼 텍스트를 잘라내고 접미어를 붙인다.
 * 
 * @param strTxt -
 *            String - 문자열
 * @param nCutLng -
 *            Integer - 잘라낼 길이
 * @param strReTxt -
 *            String - 접미어 : 미입력시 '...'
 * @returns
 */
function util_setStrCutDot(strTxt, nCutLng, strReTxt) {
	var strText = util_chkReturn(strTxt, "s");
	var nCutLeng = util_chkReturn(nCutLng, "n");
	var strReText = util_chkReturn(strReTxt, "s", "...");
	var nStrTextLen = strText.length;

	if (nCutLeng == 0 || nStrTextLen < nCutLeng) {
		return strText;
	}

	return strText.substring(0, nCutLeng) + strReText;

}

/*******************************************************************************
 * 데이터 체크 관련 함수
 ******************************************************************************/

/**
 * 입력된 문자열이 null, undefined이거나 length가 0일경우 false리턴 정상일경우 true 리턴
 * 
 * @param str
 * @returns {Boolean}
 */
function util_isEmpty(str) {
	if (str == null || str == undefined || str.length == 0) {
		return false;
	} else {
		return true;
	}
}

/**
 * 입력된 data가 null, undefined 인지 체크 판단하여 key 값에 따른 값을 리턴한다.
 * 
 * @param data
 *            체크할 data
 * @param strReKey
 *            입력안할 경우 : 정상일경우 true, 비정상일 경우 false b : 정상일 경우 true, 비정상일 경우 false
 *            s : 정상일 경우 입력된 data 반환, 비정상일 경우 빈스트링 반환 n : 정상일 경우 입력된 data 반환,
 *            비정상일 경우 0 반환
 * @param returnData
 *            비정상일경우 리턴할 data
 * @param rePlusEnd -
 *            String - 접미어 설정 strReKey 값이 "s"일경우 입력된 값이 정상일 경우 접미어를 붙여서 리턴
 *            비정상이거나 빈스트링일 경우 returnData 값을 리턴
 */
function util_chkReturn(data, strReKey, returnData, rePlusEnd) {

	var strType = jQuery.type(data);
	var bCheck = true;
	var bReturnData = true;
	var bRePlusEnd = false;
	var strRePlusEnd = "";

	if (strType == "null" || strType == "undefined") {
		bCheck = false;
	}

	if (jQuery.type(returnData) == "null"
			|| jQuery.type(returnData) == "undefined") {
		bReturnData = false;
	}

	strType = jQuery.type(strReKey);

	if (strType == "null" || strType == "undefined" || strReKey == "b"
			|| strReKey == "") {
		return bCheck;
	}

	if (rePlusEnd != null && rePlusEnd != undefined) {
		bRePlusEnd = true;
		strRePlusEnd = rePlusEnd;
	}

	if (bCheck == true) {
		if (strReKey == "s") {
			if (bRePlusEnd == true && data == "") {
				return returnData;
			} else if (bRePlusEnd == true) {
				return data + strRePlusEnd;
			} else {
				if (data == "" && bReturnData == true) {
					return returnData;
				} else {
					return data + "";
				}

			}
		} else {
			return data;
		}
	} else {
		if (strReKey == "s") {
			if (bReturnData) {
				return returnData;
			} else {
				return "";
			}
		} else if (strReKey == "n") {
			if (bReturnData) {
				return returnData;
			} else {
				return 0;
			}
		}
	}

	return bCheck;
}

/**
 * 입력된 문자열이 숫자로 이루어져 있는가? 빈스트링은 문자로 본다.
 * 
 * @returns {Boolean}
 */
function util_isDigit(strNum) {
	// null, undefined, 빈스트링 체크
	if (util_chkReturn(strNum, "s") == "") {
		return false;
	}

	var len = strNum.length;
	var c;

	for (var i = 0; i < len; i++) {
		c = strNum.charAt(i);
		if ((i == 0 && c == '-') || (c >= '0' && c <= '9')) {
			;
		} else {
			return false;
		}
	}

	return true;
}

/**
 * 입력된 문자열이 숫자와 '.'으로만 이루어져 있는가? 빈스트링은 문자로 본다.
 * 
 * @param strNum
 * @returns {Boolean}
 */
function util_isFloat(strNum) {
	// null, undefined, 빈스트링 체크
	if (util_chkReturn(strNum, "s") == "") {
		return false;
	}

	var cnt = 0;
	strNum = strNum + "";

	for (var i = 0; i < strNum.length; i++) {
		// Check that current character is number.
		var c = strNum.charAt(i);

		if (!util_isDigit(c)) {
			return true;
			if (c == ".") {
				if (cnt > 1) {
					return false;
				} else {
					cnt++;
				}
			} else {
				return false;
			}
		}
	}

	return true;
}

/**
 * 이메일 유효성 체크
 * 
 * @param email
 */
function util_isEmail(email, position) {
	// 이메일 유효성 체크
	// /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	// /^[a-z0-9_]{6,12}$/;

	var regExp;
	// if(undefined == position) regExp =
	// /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	// else if("1" == position) regExp = /([\w-\.])$/;
	// else if("2" == position) regExp =
	// /((\[[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (undefined == position)
		regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	else if ("1" == position)
		regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*$/;
	else if ("2" == position)
		regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if (!regExp.test(email)) {
		return false;
	}
	return true;
}

/**
 * 전화번호 유효성 체크
 * 
 * @param telno
 *            숫자 2~4자리 0으로시작 + "-" + 숫자3~4자리 + "-" + 숫자4자리
 */
function util_isTelno(telno, position) {
	// 전화번호 유효성 체크
	var regExp;
	if (undefined == position)
		regExp = /^(0([0-9]{1,3})([0-9]{3,4})([0-9]{4}))$/;
	else if ("-" == position)
		regExp = /^(0([0-9]{1,3})-([0-9]{3,4})-([0-9]{4}))$/;
	else if ("1" == position)
		regExp = /^(0([0-9]{1,3}))$/;
	else if ("2" == position)
		regExp = /^([0-9]{3,4})$/;
	else if ("3" == position)
		regExp = /^([0-9]{4})$/;
	else if ("4" == position)
		regExp = /^([0-9]{2,3})$/;

	if (!regExp.test(telno)) {
		return false;
	}
	return true;
}

/**
 * 주민번호 유효성 체크 (가벼운 유효성) 각 자리수에 들어갈 수 있는 숫자만 체크하여 결과를 반환합니다.
 * 
 * @param juminno
 */
function util_isJuminno(juminno, position) {
	// 주민번호 유효성 체크
	var regExp;
	if (undefined == position)
		regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))[1-8][0-9]{6}$/;
	else if ("-" == position)
		regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-8][0-9]{6}$/;
	else if ("1" == position)
		regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
	else if ("2" == position)
		regExp = /^[1-8][0-9]{6}$/;

	if (!regExp.test(juminno)) {
		return false;
	}
	return true;
}

/**
 * 주민번호 유효성 체크 (강한 유효성 - 전체 번호만 가능) 이함수는 주민번호 알고리즘을 이용하여 실제 가능한 주민번호가 아니면 false를
 * 반환합니다.
 * 
 * @param juminno
 */
function util_isJumin(juminno, position) {
	// 주민번호 유효성 체크
	var jumin1, jumin2;
	if (!util_isJuminno(juminno, position)
			|| juminno.replace(/[^0-9]/g, "").length != 13) {
		return false;
	}
	juminno = juminno.replace(/[^0-9]/g, "");

	var validTable = "234567892345";
	var vaildCd = 11;
	var userCd = Number(juminno.substr(12, 13));
	var validSum = 0;

	for (var i = 0; i < validTable.length; i++) {
		validSum += (Number(validTable.charAt(i)) * Number(juminno.charAt(i)));
	}
	if ((vaildCd - (validSum % vaildCd)) % 10 != userCd) {
		return false;
	}
	return true;
}

/**
 * 보안카드번호 유효성 체크
 * 
 * @param cardNo
 */
function util_isSecCard(cardNo) {
	var regExp = /^([0-9]{2})$/;
	if (!regExp.test(cardNo)) {
		return false;
	}
	return true;
}

/**
 * 신용카드번호 유효성 체크
 * 
 * @param cardNo
 */
function util_isCrdCard(cardNo, position) {
	var regExp = /^([0-9]{2})$/;
	if (undefined == position)
		regExp = /^([0-9]{16})$/;
	else if ("-" == position)
		regExp = /^(([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4}))$/;
	else if ("1" == position)
		regExp = /^([0-9]{4})$/;
	if (!regExp.test(cardNo)) {
		return false;
	}
	return true;
}

/**
 * 한글만 입력되었는지 체크
 * 
 * @param str
 */
function util_isKor(str) {
	if (/[^ㄱ-ㅎㅏ-ㅣ가-힣]/.test(str)) {
		return false;
	}
	return true;
}

/**
 * 영문만 입력되었는지 체크
 * 
 * @param str
 */
function util_isEng(str) {
	if (/[^a-zA-z]/.test(str)) {
		return false;
	}
	return true;
}

//

/**
 * 영문주소(영문,특수문,숫자만) 입력되었는지 체크
 * 
 * @param str
 */
function util_isEngAddr(str) {
	var strPattern = /[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm;
	if (strPattern.test(str)) {
		return false;
	}
	return true;
}

/**
 * 숫자만 입력되었는지 체크(소수점 미포함)
 * 
 * @param str
 */
function util_isNum(str) {
	return !isNaN(str);
	/*
	 * if(/[^0-9]/.test(str)){ return false; } return true;
	 */
}

chkPattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/;

/**
 * 금액만 입력되었는지 체크(소수점 미포함)
 * 
 * @param str
 */
function util_isAmt(str) {
	if (/[^0-9,]/.test(str)) {
		return false;
	}
	return true;
}

/**
 * 날짜 유효성 체크. 입력날짜가 현재 또는 입력된 기준일보다 후일일 경우 false 기준일 또는 이전일 경우 true반환
 * 
 * @param chkDate
 * @param stdDate
 * @returns {Boolean}
 */
function util_isValidDate(chkDate, stdDate) {
	if (util_chkReturn(stdDate, "s") == "") {
		stdDate = util_getDate();
	}
	stdDate = stdDate.replace(/[^0-9]/g, "");
	chkDate = chkDate.replace(/[^0-9]/g, "");
	if (util_isDate(chkDate) && stdDate >= chkDate) {
		return true;
	}
	return false;
}

/**
 * 문자열길이 체크.영문인지 한글인지 체크해서 길이를 반환
 * 
 * @param Value
 */
function util_calcBytes(Value) {
	var tcount = 0;

	var tmpStr = new String(Value);
	var temp = tmpStr.length;

	var onechar;
	for (k = 0; k < temp; k++) {
		onechar = tmpStr.charAt(k);
		if (escape(onechar).length > 4) {
			tcount += 2;// 한글
		} else {
			tcount += 1;// 영문
		}
	}

	return tcount;
}

// 입력체크 알림 플래그. 중복 알림 방지용
var utilChkInputAlertFlag = true;
/**
 * Function : 한글영문 Check 이벤트
 * 
 * @param option
 *            ├─symbol : 특수문자입력 ├─num : 숫자입력 ├─rep : 잘못된값 치환 └─msg : 입력체크시 메시지
 *            알림 설정 Note : 입력한 데이터의 한글및영문유효성 체크
 */
function util_inputKorEngChk($inpObject, option) {
	var chkFlag = true;
	var inpText = $inpObject.val() + '';

	if (typeof option != "object")
		option = {};
	if (undefined == option.symbol)
		option.symbol = false;
	if (undefined == option.num)
		option.num = false;
	if (undefined == option.rep)
		option.rep = true;
	if (undefined == option.msg)
		option.msg = false;
	if (undefined == option.space)
		option.space = false;

	var chkPatternStr = "ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z";

	if (option.num == true)
		chkPatternStr += "0-9";
	if (option.symbol == true)
		chkPatternStr += "!@#$%^&?*\\\-_+=`~()\"';:,.|<>{}\\\/";
	if (option.space == true)
		chkPatternStr += "\\\s";

	chkPatternStr = "[^" + chkPatternStr + "]";

	var chkPattern = new RegExp(chkPatternStr, [ '' ]);
	var replacePattern = new RegExp(chkPatternStr, [ 'g' ]);

	if (chkPattern.test(inpText)) {
		if (true == option.msg && utilChkInputAlertFlag) {
			utilChkInputAlertFlag = false;
			alert("특수문자는 입력이 불가능합니다.");
			utilChkInputAlertFlag = true;
		}
		if (true == option.rep) {
			inpText = inpText.replace(replacePattern, '');
		}
		chkFlag = false;
	}
	$inpObject.val(inpText);
	return chkFlag;
}

/**
 * Function : 한글 Check 이벤트
 * 
 * @param option
 *            ├─symbol : 특수문자입력 ├─num : 숫자입력 ├─rep : 잘못된값 치환 └─msg : 입력체크시 메시지
 *            알림 설정 Note : 입력한 데이터의 한글유효성 체크
 */
function util_inputKorChk($inpObject, option) {
	var chkFlag = true;
	var inpText = $inpObject.val() + '';
	var chkPattern;
	var replacePattern;

	if (typeof option != "object")
		option = {};
	if (undefined == option.symbol)
		option.symbol = false;
	if (undefined == option.num)
		option.num = false;
	if (undefined == option.rep)
		option.rep = true;
	if (undefined == option.msg)
		option.msg = false;

	// 숫자입력가능
	if (true == option.num) {
		// 특수문자입력가능
		if (true == option.symbol) {
			chkPattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣0-9!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/;
			replacePattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣0-9!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/g;
		}
		// 특수문자입력불가
		else {
			chkPattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣0-9]/;
			replacePattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣0-9]/g;
		}
	}
	// 숫자입력불가
	else {
		// 특수문자입력가능
		if (true == option.symbol) {
			chkPattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/;
			replacePattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/g;
		}
		// 특수문자입력불가
		else {
			chkPattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣]/;
			replacePattern = /[^ㄱ-ㅎㅏ-ㅣ가-힣]/g;
		}

	}

	if (chkPattern.test(inpText)) {
		if (true == option.msg && utilChkInputAlertFlag) {
			utilChkInputAlertFlag = false;
			alert("한글만 입력이 가능합니다.");
			utilChkInputAlertFlag = true;
		}
		if (true == option.rep) {
			inpText = inpText.replace(replacePattern, '');
		}
		chkFlag = false;
	}
	$inpObject.val(inpText);
	return chkFlag;
}

/**
 * Function : 영문 Check 이벤트
 * 
 * @param option
 *            ├─symbol : 특수문자입력 ├─num : 숫자입력 ├─rep : 잘못된값 치환 └─msg : 입력체크시 메시지
 *            알림 설정 Note : 입력한 데이터의 영문유효성 체크
 */
function util_inputEngChk($inpObject, option) {
	var chkFlag = true;
	var inpText = $inpObject.val() + '';
	var chkPattern;
	var replacePattern;

	if (typeof option != "object")
		option = {};
	if (undefined == option.symbol)
		option.symbol = false;
	if (undefined == option.num)
		option.num = false;
	if (undefined == option.rep)
		option.rep = true;
	if (undefined == option.msg)
		option.msg = false;

	// 숫자입력가능
	if (true == option.num) {
		// 특수문자입력가능
		if (true == option.symbol) {
			chkPattern = /[^a-zA-Z0-9!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/;
			replacePattern = /[^a-zA-Z0-9!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/g;
		}
		// 특수문자입력불가
		else {
			chkPattern = /[^a-zA-Z0-9]/;
			replacePattern = /[^a-zA-Z0-9]/g;
		}
	}
	// 숫자입력불가
	else {
		// 특수문자입력가능
		if (true == option.symbol) {
			chkPattern = /[^a-zA-Z!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/;
			replacePattern = /[^a-zA-Z!@#$%^&?*\-_+=`~()"';:,.|<>{}\/]/g;
		}
		// 특수문자입력불가
		else {
			chkPattern = /[^a-zA-Z]/;
			replacePattern = /[^a-zA-Z]/g;
		}

	}

	if (chkPattern.test(inpText)) {
		if (true == option.msg && utilChkInputAlertFlag) {
			utilChkInputAlertFlag = false;
			alert("영문만 입력이 가능합니다.");
			utilChkInputAlertFlag = true;
		}
		if (true == option.rep) {
			inpText = inpText.replace(replacePattern, '');
		}
		chkFlag = false;
	}
	$inpObject.val(inpText);
	return chkFlag;
}

/**
 * Function : 숫자 Check 이벤트
 * 
 * @param option
 *            ├─type : 입력숫자타입 ├─evt : 이벤트 처리시 ├─rep : 잘못된값 치환 └─msg : 입력체크시 메시지
 *            알림 설정 Note : 입력한 데이터의 숫자유효성 체크
 */
function util_inputNumChk($inpObject, option) {
	var chkFlag = true;
	var inpText = $inpObject.val() + '';
	var chkPattern;
	var replacePattern;

	if (typeof option != "object")
		option = {};
	if (undefined == option.type)
		option.type = "natural";
	if (undefined == option.rep)
		option.rep = true;
	if (undefined == option.msg)
		option.msg = false;

	/*
	 * if(option.evt != undefined){ return util_keyCodeNumChk(option.evt,
	 * chkFlag); //return chkFlag; }
	 */
	// 자연수
	if ("natural" == option.type) {
		chkPattern = /[^0-9]/;
		replacePattern = /[^0-9]/g;
	}
	// 금액
	else if ("amt" == option.type) {
		chkPattern = /[^0-9,]/;
		replacePattern = /[^0-9]/g;
	}
	// 정수
	else if ("integer" == option.type) {
		chkPattern = /^[+-]?\d*$/;
		// replacePattern = /[^0-9]/g;
	}
	// 실수
	else if ("float" == option.type) {
		// chkPattern = /^[+-]?\d+(\.?\d+)*$/;
		chkPattern = /[^0-9\.\-]/;
		replacePattern = /[^0-9\.\-]/g;
		// replacePattern = /[^0-9\-\.]/g;
	}

	if (chkPattern.test(inpText)) {
		if (true == option.msg && utilChkInputAlertFlag) {
			utilChkInputAlertFlag = false;
			alert("숫자만 입력이 가능합니다.");
			utilChkInputAlertFlag = true;
		}
		chkFlag = false;
	}
	if (chkFlag && option.rep) {
		inpText = inpText.replace(replacePattern, '');
		// 자연수
		if ("natural" == option.type) {
		}
		// 금액
		else if ("amt" == option.type) {
			inpText = util_setCommas(inpText);
		}
		// 정수
		else if ("integer" == option.type) {
		}
		// 실수
		else if ("float" == option.type) {
		}
		$inpObject.val(inpText);
		return chkFlag;
	}

	// 자연수
	if ("natural" == option.type) {
	}
	// 금액
	else if ("amt" == option.type) {
		inpText = inpText.replace(replacePattern, '');
		inpText = util_setCommas(inpText);
		$inpObject.val(inpText);
	}
	// 정수
	else if ("integer" == option.type) {
	}
	// 실수
	else if ("float" == option.type) {
	}

	return chkFlag;
}

/**
 * 숫자만 입력 처리 ex) $("#id").bind("keydown",function(event){ return
 * util_inputNumChk(event, false); });
 */
function util_keyCodeNumChk(e, decimal, initFlag) {

	var key;
	var keychar;
	if (window.event) { // 익스와 파폭 체크 !
		key = window.event.keyCode;
	} else if (e) {
		key = e.which;
	} else {
		return true;
	}
	keychar = String.fromCharCode(key);
	if (initFlag && (key == 48 || key == 96)) {
		return false;
	} else if ((key == null) || (key == 0) || (key == 8) || (key == 9)
			|| (key == 13) || (key == 27) || (key == 37) || (key == 39)
			|| key == 46) {// 괄호
		return true;
	} else if (96 <= key && key <= 105) {// Num Key pad
		return true;
	} else if ((("0123456789").indexOf(keychar) > -1)) {// 키보드 자판 숫자. 단 특수문자도 같은
		// 키캐릭터라 입력됨
		return true;
	} else if (decimal
			&& (key == 109 || key == 189 || key == 110 || key == 190)) {// -
		// .입력
		return true;
	} else
		return false;
}

/*******************************************************************************
 * 유효성 체크
 ******************************************************************************/

/*******************************************************************************
 * 이벤트 관련 함수
 ******************************************************************************/
/**
 * 키코드 정보 획득
 * 
 * @param event
 * @returns
 */
function fn_GetEvent(e) {
	if (navigator.appName == 'Netscape') {
		keyVal = e.which;
	} else {
		keyVal = event.keyCode;
	}
	return keyVal;
}

/**
 * 숫자만 입력되게 한다.
 * 
 * @param event
 * @returns 사용법 : onkeydown="util_numberonly(event);" style="ime-mode:disabled"
 */
function util_numberonly(e) {
	var key;

	if (window.event)
		key = window.event.keyCode; // IE
	else
		key = e.which; // firefox

	// backspace or delete or tab
	var event;
	if (key == 0 || key == 8 || key == 46 || key == 9) {
		event = e || window.event;
		if (typeof event.stopPropagation != "undefined") {
			event.stopPropagation();
		} else {
			event.cancelBubble = true;
		}
		return;
	}

	if (key < 48 || (key > 57 && key < 96) || key > 105 || e.shiftKey) {
		e.preventDefault ? e.preventDefault() : e.returnValue = false;
	}
}

/**
 * 영문만 입력되게 한다.
 * 
 * @param event
 * @returns 사용법 : onkeydown="util_engonly(event);" style="ime-mode:disabled"
 */
function util_engonly(e) {
	var key;

	if (window.event) {
		key = window.event.keyCode; // IE
	} else {
		key = e.which; // firefox
	}

	// backspace or delete or tab
	var event;
	if (key == 0 || key == 8 || key == 46 || key == 9) {
		event = e || window.event;
		if (typeof event.stopPropagation != "undefined") {
			event.stopPropagation();
		} else {
			event.cancelBubble = true;
		}
		return;
	}

	// key >= 65 && key <=90 : 한글, key >= 48 && key <=57 : 숫자, key >= 96 && key
	// <= 109 : 키패드, key == 16 || key == 0 : shift
	if ((key >= 65 && key <= 90) || (key >= 48 && key <= 57)
			|| (key >= 96 && key <= 109) || (key == 16 || key == 0)) {
	} else {
		e.preventDefault ? e.preventDefault() : e.returnValue = false;

	}
}

/**
 * 날짜 조회 입력시 유효성 체크 날짜가 입력될경우 조회시작일이 현재일보다 이후이면 현재일 종료일보다 이후이면 종료일을 조회시작일로 맞춘다.
 * 조회종료일이 현재일보다 이후이면 현재일 시작일보다 이전이면 시작일을 조회종료일로 맞춘다.
 * 
 * 
 * ex ) util_intDateValid("sDate", "eDate");
 */
function util_intDateValid(startDateId, endDateId) {
	if (util_chkReturn(startDateId, "s") == "") {
		startDateId = "startDate";
	}
	if (util_chkReturn(endDateId, "s") == "") {
		endDateId = "endDate";
	}
	$startDate = $("#" + startDateId);
	$endDate = $("#" + endDateId);
	// 조회시작일 유효성 체크
	$startDate.unbind().bind("change", function() {
		if (!util_isValidDate($(this).val())) {
			alert("조회시작일이 현재일보다 커질 수 없습니다.");
			$(this).val(util_getDate('-'));
		}
		if (!util_isValidDate($(this).val(), $endDate.val())) {
			// alert("조회시작일이 조회종료일일보다 커질 수 없습니다.");
			$endDate.val($(this).val());
		}
	});
	// 조회종료일
	$endDate.unbind().bind("change", function() {
		if (!util_isValidDate($(this).val())) {
			alert("조회종료일이 현재일보다 커질 수 없습니다.");
			$(this).val(util_getDate('-'));
		}
		if (!util_isValidDate($startDate.val(), $(this).val())) {
			// alert("조회종료일이 조회시작일보다 이전일 수 없습니다.");
			$startDate.val($(this).val());
		}
	});
}

/**
 * 주민번호입력 전필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputJumin1("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputJumin1() {
	var argElmt = util_inputJumin1.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "6");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(!util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isJuminno($inpObj.val(), "1")) {
				inputEventChk($inpObj, "주민번호 앞자리가 잘못되었습니다.");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 주민번호입력 후필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputJumin2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputJumin2() {
	var argElmt = util_inputJumin2.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "7");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);

		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 주민번호입력 후필드에대한 함수 사망보장 보험같은 경우 주민등록번호 6자리만 입력받습니다
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputJumin2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputJumin2_6() {
	var argElmt = util_inputJumin2_6.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "6");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);

		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 보안카드 입력에대한 체크함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputSecCard("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputSecCard() {
	var argElmt = util_inputSecCard.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "2");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(!util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isSecCard($inpObj.val())) {
				inputEventChk($inpObj, "보안카드번호가 잘못되었습니다.");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 카드번호 입력에대한 체크함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputCardNum("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputCardNum() {
	var argElmt = util_inputCardNum.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(!util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isCrdCard($inpObj.val(), "1")) {
				inputEventChk($inpObj, "카드 비밀번호가 잘못되었습니다.");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 금액필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputAmt("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputAmt() {
	var argElmt = util_inputAmt.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		util_inputAmtArea(argElmt[i]);
	}
}

/**
 * 금액필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputAmt("areaId01", {});
 */
function util_inputAmtArea(areaId, option) {
	var numMax = 12;
	var commaMax = 15;
	if (typeof option == "object") {
		if (option.maxlength != undefined && !isNaN(option.maxlength)) {
			numMax = Number(option.maxlength);
			commaMax = parseInt(numMax + ((numMax - 1) / 3), 10);
		}
	}

	var $idArea = $("#" + areaId);
	$idArea.attr("maxlength", commaMax);
	$idArea.css("text-align", "right");
	$idArea.unbind().bind("focusout", function(e) {
		var $inpObj = $(this);
		var inpAmt = $inpObj.val().replace(/[^0-9]/g, '');
		if (inpAmt.length > numMax) {
			inputEventChk($inpObj, "금액은 최대 " + numMax + "자리까지 입력가능합니다.");
			inpAmt = inpAmt.substring(0, numMax);
		}
		if (inpAmt != "") {
			$inpObj.val(util_setCommas(Number(inpAmt)));
		}
	}).bind("keydown", function(e) {
		/*
		 * var $inpObj = $(this); var initFlag = false; if($inpObj.val() == ""){
		 * initFlag = true; }
		 */
		return util_keyCodeNumChk(e, false);
	}).bind("keyup", function(e) {
		var key = 0;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;

		if (key != 37 && key != 39) {// 방향키 예외처리
			var $inpObj = $(this);
			util_inputNumChk($inpObj, {
				"msg" : true,
				"type" : "amt"
			});
		}
	});

}

/**
 * 숫자필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputNum("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputNum() {
	var argElmt = util_inputNum.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		util_inputNumArea(argElmt[i]);
	}
}

/**
 * 숫자필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputNum("areaId01", {});
 */
function util_inputNumArea(areaId, option) {
	var numMax = 12;
	if (typeof option == "object") {
		if (option.maxlength != undefined && !isNaN(option.maxlength)) {
			numMax = Number(option.maxlength);
		}
	}

	var $idArea = $("#" + areaId);
	$idArea.attr("maxlength", numMax);
	$idArea.css("text-align", "right");
	$idArea.unbind().bind("focusout", function(e) {
		var $inpObj = $(this);
		if (isNaN($inpObj.val())) {
			inputEventChk($inpObj, "올바른 숫자가 아닙니다.");
		} else if ($inpObj.val().length > numMax) {
			inputEventChk($inpObj, "숫자는 최대 " + numMax + "자리까지 입력가능합니다.");
		} else {
			// $inpObj.val(util_Number($inpObj.val()));

		}
	}).bind("keydown", function(e) {
		return util_keyCodeNumChk(e, true);
	}).bind("keyup", function(e) {
		var key = 0;
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;

		if (key != 37 && key != 39) {// 방향키 예외처리
			var $inpObj = $(this);
			util_inputNumChk($inpObj, {
				"msg" : true,
				"type" : "float"
			});
		}
	});

}
/**
 * 이메일 앞자리 필드에대한 함수
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputEmail1("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputEmail1() {
	var argElmt = util_inputEmail1.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "30");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			if ($inpObj.val() != "" && !util_isEmail($inpObj.val(), "1")) {
				inputEventChk($inpObj, "잘못된 이메일 주소입니다.");
			} else if ($inpObj.val().length > 30) {
				inputEventChk($inpObj, "이메일의 최대 입력값은 30자입니다.");
			}
		});
	}
}

/**
 * 이메일 뒷자리 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputEmail2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputEmail2() {
	var argElmt = util_inputEmail2.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "30");
		$idArea
				.unbind()
				.bind(
						"focusout",
						function(e) {
							var $inpObj = $(this);
							if ($inpObj.val() != ""
									&& !util_isEmail($inpObj.val(), "2")) {
								// alert("잘못된 이메일 주소입니다.");
								inputEventChk($inpObj, "잘못된 이메일 주소입니다.");
							} else if ($inpObj.val().length > 30) {
								inputEventChk($inpObj, "이메일의 최대 입력값은 30자입니다.");
							} else if ($inpObj.val().toLowerCase().indexOf(
									"yahoo.co.kr") != -1) {
								inputEventChk($inpObj,
										"정확한 메일 전송을 위해 yahoo.co.kr도메인 계정은 사용하실 수 없습니다.");
							}
						});
	}
}

/**
 * 이메일 뒷자리 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputEmail2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputEmail3() {
	var argElmt = util_inputEmail3.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "30");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			if ($inpObj.val() != "" && !util_isEmail($inpObj.val(), "2")) {
				alert("잘못된 이메일 주소입니다.");

			} else if ($inpObj.val().length > 30) {
				alert("이메일의 최대 입력값은 30자입니다.");
			}
		});
	}
}

/**
 * 팩스번호 지역번호 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputFax1() {
	var argElmt = util_inputFax1.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "1")) {
				inputEventChk($inpObj, "팩스번호 지역번호를 확인해주세요");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}
/**
 * 팩스번호 중간자리 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputFax2() {
	var argElmt = util_inputFax2.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "2")) {
				inputEventChk($inpObj, "팩스번호 앞자리를 확인해주세요");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 팩스번호 뒷자리 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputFax3() {
	var argElmt = util_inputFax3.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "3")) {
				inputEventChk($inpObj, "팩스번호 뒷자리를 확인해주세요");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 전화번호 첫번째 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel1("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputTel1() {
	var argElmt = util_inputTel1.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "3");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "1")) {
				inputEventChk($inpObj, "전화번호 국번이 잘못입력되었습니다.");
				alert($("#" + argElmt[i]).val());
				// $("#"+argElmt[i]).val("");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 전화번호 가운데 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel2("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputTel2() {
	var argElmt = util_inputTel2.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "2")) {
				inputEventChk($inpObj, "전화번호 중간자리가 잘못입력되었습니다.");
				$inpObj.val("");
			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 전화번호 마지막 필드에대한 함수 (직접입력)
 * 
 * @param input영역에
 *            대한 id
 * @ex) util_inputTel3("areaId01", "areaId02", "areaId03", ... );
 */
function util_inputTel3() {
	var argElmt = util_inputTel3.arguments;
	var arglen = argElmt.length;

	for (var i = 0; i < arglen; i++) {
		var $idArea = $("#" + argElmt[i]);
		$idArea.attr("maxlength", "4");
		$idArea.unbind().bind("focusout", function(e) {
			var $inpObj = $(this);
			/*
			 * if(util_inputNumChk($inpObj,{"msg":true, "evt":e})){
			 * inputEventChk($inpObj); } else
			 */if ($inpObj.val() != "" && !util_isTelno($inpObj.val(), "3")) {
				inputEventChk($inpObj, "전화번호 마지막자리가 잘못 입력되었습니다.");
				$inpObj.val("");

			}
		}).bind("keydown", function(e) {
			return util_keyCodeNumChk(e, false);
		});
	}
}

/**
 * 입력영역의 이벤트를 체크하는 함수
 * 
 * @param $inpObj
 * @param msg
 */
function inputEventChk($inpObj, msg) {
	if (typeof (msg) == "string") {
		alert(msg);
	}
	if ($inpObj.attr("type") == "password") {
		$inpObj.val("");
	}
	$inpObj.focus();
}

/*******************************************************************************
 * 화면 그리기 관련 함수
 ******************************************************************************/

/**
 * 리스트그리기 공통 화면에 리스트를 동적으로 생성 후 그립니다 리스트에 이벤트를 걸어줄 경우(delegate 등 사용) 해당 td와 해당
 * 객체에 row전체의 데이터가 매핑되어있습니다. 사용시 $(this).data("data")로 꺼내서 사용하시면 됩니다. 데이터의
 * 커스터마이징이 필요할 경우 커스텀후 넣어주시면 됩니다. 사용법은 HPME21S0.js 참고
 * 
 * @param responseList -
 *            String - 화면에 리스트로 그려질 배열
 * @param listId -
 *            String - 실재로 그려질 영역의 id
 * @param tempListId -
 *            String - 복사하여 사용할 임시 그려질 영역의 id
 * 
 * 
 * ex ) util_drawList(arrList, "listArea", "tempListArea");
 */
var utilObj_drawList_listAreaClone = new Object;
function util_drawList(responseList, listId, tempListId) {
	// 그려질 영역이 없을경우 디폴트명으로 설정
	if (util_chkReturn(listId, "s") == "") {
		listId = "listArea";
	}
	// 임시 그려질 영역이 없을경우 디폴트명으로 설정
	if (util_chkReturn(tempListId, "s") == "") {
		tempListId = "tempListArea";
	}

	// 지정된 id의 영역 설정
	var $listArea = $("#" + listId);
	var $tempListArea = $("#" + tempListId);

	// 지정된 id의 임시영역 복사 및 임시영역 삭제
	if (util_chkReturn(utilObj_drawList_listAreaClone[tempListId], "s") == "") {
		utilObj_drawList_listAreaClone[tempListId] = $tempListArea.children()
				.clone();
		jQuery.remove(tempListId); // temp 삭제
	}

	// 지정된 id의 기존 영역에 그려진 부분 삭제
	jQuery.removeChild(listId);

	var nCount = 0;
	if (util_chkReturn(responseList, "s") != "") {
		nCount = responseList.length; // 리스트의 길이
	}

	// 리스트가 없을경우 종료
	if (util_chkReturn(nCount, "s") == "" || nCount == 0) {
		// var nTdCount =
		// utilObj_drawList_listAreaClone[tempListId].children("tr").children("td").length;
		var nTdCount = utilObj_drawList_listAreaClone[tempListId]
				.children("td").length;
		$listArea.html("<tr><td colspan=\"" + nTdCount
				+ "\" class=\"noData\">조회된 내역이 없습니다.</td></tr>");
	} else {
		// 임시로 그려지는 영역 객체
		var srcArea = null;
		// 리스트의 길이만큼 수행되면서 모든 오브젝트의 키에 오브젝트의 값을 그린다. (화면 오브젝트의 클래스명과 실제 전문의 키값은
		// 동일하게 설정)
		for (var i = 0; i < nCount; i++) {
			var objData = responseList[i];
			srcArea = utilObj_drawList_listAreaClone[tempListId].clone();
			// 데이터가 포함된 row에 현재 rowData세팅
			srcArea.data("data", objData);
			for ( var item in objData) {
				if (item.indexOf("[") == -1 && item.indexOf("]") == -1) {
					// 현재 row의 column에 rowData세팅
					srcArea.find("[name=" + item + "]").data("data", objData);
					srcArea.find("[name=" + item + "]").val(objData[item]);
					srcArea.find("[name=" + item + "]").filter(":not(input)")
							.filter(":not(button)").filter(":not(img)").html(
									objData[item]);// 13-03-04
					// IE에서
					// 인풋태그
					// 오류로
					// 인한
					// 수정
					// .filter(":not(input)")추가
					// .filter(":not(img )")추가 2013-05-28 강종철
				}
			}

			$listArea.append(srcArea);
		}
	}
};

/**
 * 화면그리기 공통 (화면에 전문 데이터를 맞춰서 출력해줍니다)
 * 
 * @param :
 *            responseMap 화면에 매핑될 객체 mapId 실재로 그려질 영역의 id
 * 
 * 
 * util_drawMap(result.outData, "content");
 * 
 * 수정 : 20130213 정헌태 - param strDef 추가 : 화면에 추가될 Object의 내용이 없을경우 대체텍스트를 화면에
 * 표시한다.
 */
function util_drawMap(responseMap, mapId, strDef) {
	// 화면이 없을경우 종료
	if (undefined == responseMap) {
		return;
	}
	// 그려질 영역이 없을경우 디폴트명으로 설정
	if (undefined == mapId) {
		mapId = "content";
	}

	var bStrDef = false;
	if (util_chkReturn(strDef, "s") != "") {
		bStrDef = true;
	}

	var $mapArea = $("#" + mapId);

	for ( var item in responseMap) {
		if (item.indexOf("[") == -1 && item.indexOf("]") == -1) {
			if (util_chkReturn(responseMap[item], "s") == "" && bStrDef == true) {
				$mapArea.find("#" + item).val(strDef);
				$mapArea.find("#" + item).filter(":not(input)").html(strDef);
			} else {
				$mapArea.find("#" + item).val(responseMap[item]);
				$mapArea.find("#" + item).filter(":not(input)").html(
						responseMap[item]);
			}
		}
	}
};

/**
 * 셀렉트박스의 옵션을 자동으로 생성하는 함수
 * 
 * @param list :
 *            옵션 리스트
 * @param selectBoxId :
 *            옵션을 그려줄 selectBox의 id
 * @param option :
 *            옵션설정값 option = { initVal : 초기값 > 미입력: 첫번째값, 입력: 입력값 emptyFlag :
 *            <option>선택</option> 표기 > 미입력: 표시, 입력: 미표시, emptyText : 옵션에서 빈값
 *            내용. 기본값은 선택 valKey : value로 설정될 keyName 기본값 : cmnnCd textKey :
 *            화면에표시될 keyName 기본값 : cmnnCdHanNm dataKey : 셀렉트박스에 묶일 데이터 }
 * 
 * 
 * ex ) util_drawSelectBoxOption(arrList,"sBox",{"initVal":5});
 */
function util_drawSelectBoxOption(list, selectBoxId, option) {
	// 옵션입력여부
	var optionState = false;
	if (util_chkReturn(option, "s") != "" && typeof option === "object") {
		optionState = true;
	}
	// 셀렉트 박스 영역
	var $selectBoxArea = $("#" + selectBoxId);
	// 지정된 id의 기존 영역에 그려진 부분 삭제
	jQuery.removeChild(selectBoxId);
	if (typeof list === "object" && list.constructor == Array) {
		var valKey = "cmnnCd";
		var textKey = "cmnnCdHanNm";
		if (!optionState || util_chkReturn(option.emptyFlag, "s") == "") {
			var optionText = "선택";
			if (optionState && util_chkReturn(option.emptyText, "s") != "") {
				optionText = option.emptyText;
			}
			$selectBoxArea.append("<option value=''>" + optionText
					+ "</option>");
		}
		if (optionState && util_chkReturn(option.valKey, "s") != "") {
			valKey = option.valKey;
		}
		if (optionState && util_chkReturn(option.textKey, "s") != "") {
			textKey = option.textKey;
		}
		// 옵션그리기
		var strHtml;
		for (var i = 0; i < list.length; i++) {
			strHtml = "";
			strHtml += "<option value='" + list[i][valKey] + "'>"
					+ list[i][textKey] + "</option>";
			$selectBoxArea.append(strHtml);
			if (!optionState || util_chkReturn(option.dataKey, "s") != "") {// 옵션에
				// 전체
				// 데이터
				// 세팅
				// 옵션
				$selectBoxArea.find("option:last").data("data", list[i]);
			}
		}
		if (optionState && util_chkReturn(option.initVal, "s") != "") {
			$selectBoxArea.val(option.initVal);
		}
	}
};

/**
 * 라디오박스의 옵션을 자동으로 생성하는 함수
 * 
 * @param list :
 *            옵션 리스트
 * @param radioBoxId :
 *            옵션을 그려줄 radioBox의 id
 * @param option :
 *            옵션설정값 option = { initVal : 초기값 > 미입력: 선택없음, 입력: 입력값선택 emptyFlag :
 *            초기값 첫번째 option 선택구분 > 미입력: 선택, 입력: 미선택, valKey : value로 설정될
 *            keyName 기본값 : cmnnCd textKey : 화면에표시될 keyName 기본값 : cmnnCdHanNm
 *            preText : 화면에 표시될 텍스트명 예 ) 연간 3 = 연간 posText : 화면에 표시될 텍스트명 예 )
 *            1년부터 = 년부터 } id = radioBoxId1 .... radioBoxId99 ... name =
 *            radioBoxId 옵션없을시 value = cmnnCd 옵션없을시text = cmnnCdHanNm
 * 
 * 
 * ex ) util_drawRadioBoxOption(arrList,"rBox",{"initVal":5});
 */
function util_drawRadioBoxOption(list, radioBoxId, option) {
	// 옵션입력여부
	var optionState = false;
	if (util_chkReturn(option, "s") != "" && typeof option === "object") {
		optionState = true;
	}
	// 라디오 박스 영역
	var $radioBoxArea = $("#" + radioBoxId);
	// 지정된 id의 기존 영역에 그려진 부분 삭제
	jQuery.removeChild(radioBoxId);

	if (typeof list === "object" && list.constructor == Array) {
		var valKey = "cmnnCd";
		var textKey = "cmnnCdHanNm";
		if (optionState && util_chkReturn(option.valKey, "s") != "") {
			valKey = option.valKey;
		}
		if (optionState && util_chkReturn(option.textKey, "s") != "") {
			textKey = option.textKey;
		}

		if (optionState && util_chkReturn(option.preText, "s") != "") {
			preText = option.preText;
		}
		if (optionState && util_chkReturn(option.posText, "s") != "") {
			posText = option.posText;
		}

		// 옵션그리기
		var strHtml;
		for (var i = 0; i < list.length; i++) {
			strHtml = "";
			strHtml += "<input type='radio' id='" + radioBoxId + i + "' name='"
					+ radioBoxId + "' value='" + list[i][valKey]
					+ "' ><label for='" + radioBoxId + i + "'>"
					+ list[i][textKey] + "</label><br />";
			$radioBoxArea.append(strHtml);
		}
		if (optionState && util_chkReturn(option.initVal, "s") != "") {
			$("input[name='" + radioBoxId + "']").filter(
					'[value="' + option.initVal + '"]').attr('checked', true);
		} else {
			// 옵션 초기값 (첫번째값)
			if (!optionState || util_chkReturn(option.emptyFlag, "s") == "") {
				$("input[name='" + radioBoxId + "']").filter(
						'[id="' + radioBoxId + '0"]').attr('checked', true);
			}
		}
	}
};

/**
 * 입력 년월일 그려주는 함수 입력 년월일 셀렉트박스의 id에 각각을 넣으면 자동으로 그려준다 디폴트값은 inputY, inputM,
 * inputD
 * 
 * @param yId
 *            년도를 그려줄 영역 아이디 입력없을시 inputY
 * @param mId
 *            월수를 그려줄 영역 아이디 입력없을시 inputM
 * @param dId
 *            일수를 그려줄 영역 아이디 입력없을시 inputD
 * @param option
 *            ├─endAge : 그려줄 년도의 마지막 값에대한 나이 (현재나이로부터 계산) ├─startAge : 그려줄 년도의
 *            첫번째 값에대한 나이 (현재나이로부터 계산) └─initAge : 그려줄 년도의 초기값 (현재나이로부터 계산)
 * 
 * 
 * ex ) util_drawInputYMD("yDate","mDate","dDate");
 */
function util_drawInputYMD(yId, mId, dId, option) {
	if (typeof yId === 'object') {
		option = yId;
	} else {
		// 년도를 그려줄 영역 아이디
		if (util_chkReturn(yId, "s") == "") {
			yId = "inputY";
		}
		// 월수를 그려줄 영역 아이디
		if (util_chkReturn(mId, "s") == "") {
			mId = "inputM";
		}
		// 일수를 그려줄 영역 아이디
		if (util_chkReturn(dId, "s") == "") {
			dId = "inputD";
		}
	}
	var optionState = false;
	if (util_chkReturn(option, "s") != "" && typeof option === "object") {
		optionState = true;
	}

	var nowDate = util_getDate();

	// 년도그리기
	var startY = Number(nowDate.substring(0, 4)), endY = startY - 100, initY = startY - 30;// 년도
	// 그리기
	// 시작년도와
	// 종료년도,
	// 기본값

	if (optionState && (util_chkReturn(option.endAge, "s") != "")) {
		endY = startY - option.endAge;
	}

	if (optionState && (util_chkReturn(option.startAge, "s") != "")) {
		startY -= option.startAge;
	}

	if (optionState && (util_chkReturn(option.initAge, "s") != "")) {
		initY = Number(nowDate.substr(0, 4)) - option.initAge;
	}

	// alert("startY-->"+startY+"---"+endY);
	var arrY = new Array();
	for (var i = startY; i >= endY; i--) {
		arrY[arrY.length] = {
			"cmnnCd" : i,
			"cmnnCdHanNm" : i + "년"
		};
	}
	// alert("111-->"+arrY.length);
	util_drawSelectBoxOption(arrY, yId, {
		"initVal" : initY,
		"emptyFlag" : true
	});
	// 달그리기
	var arrM = new Array();
	for (var i = 1; i <= 12; i++) {
		arrM[arrM.length] = {
			"cmnnCd" : i,
			"cmnnCdHanNm" : i + "월"
		};
	}
	util_drawSelectBoxOption(arrM, mId, {
		"emptyFlag" : true
	});
	// 일그리기
	function drawInputDD(inputY, inputM) {
		var arrD = new Array();
		var curD = $("#" + dId).val();
		var endD = Number(util_getLastDay(inputY, inputM));
		if (curD > endD) {
			curD = endD;
		}
		for (var i = 1; i <= endD; i++) {
			arrD[arrD.length] = {
				"cmnnCd" : i,
				"cmnnCdHanNm" : i + "일"
			};
		}
		util_drawSelectBoxOption(arrD, dId, {
			"initVal" : curD,
			"emptyFlag" : true
		});
	}

	$("#" + yId).unbind().bind('change', function() {
		drawInputDD($("#" + yId).val(), $("#" + mId).val());
	});
	$("#" + mId).unbind().bind('change', function() {
		drawInputDD($("#" + yId).val(), $("#" + mId).val());
	});

	drawInputDD($("#" + yId).val(), $("#" + mId).val());
}

/**
 * 셀렉트박스에 숫자로 된 값의 옵션을 자동 생성. 사용법은 그릴영역의 id와 시작값 끝값을넘기면 된다. 옵션의 경우는 필요한 값만 선택적으로
 * 넘기면 된다.
 * 
 * @param selBoxId
 * @param option = {
 *            initVal : 초기값 > 미입력: 첫번째값, 입력: 입력값 emptyFlag : <option>선택</option>
 *            표기 > 미입력: 표시, 입력: 미표시, start : 시작값 기본값 1, end : 종료값 기본값 100, term :
 *            숫자간격 기본값 1, preText : 화면에 표시될 텍스트명 예 ) 연간 3 = 연간 posText : 화면에 표시될
 *            텍스트명 예 ) 1년부터 = 년부터 unit : 값 단위 예) 1000 = 화면표시는 1일경우 실제 값은 1000 }
 * 
 * 
 * ex ) util_drawSelectBoxOptionNumber("numSelBox", {"startNum":30,"endNum":50};
 */
function util_drawSelectBoxOptionNumber(selBoxId, option) {
	var start = 1;
	var end = 100;
	var term = 1;
	var preText = "";
	var posText = "";
	var unit = 1;
	// 옵션입력여부
	var optionState = false;
	if (util_chkReturn(option, "s") != "" && typeof option === "object") {
		optionState = true;
	}
	if (optionState && util_chkReturn(option.start, "s") != "") {
		start = option.start;
	}
	if (optionState && util_chkReturn(option.end, "s") != "") {
		end = option.end;
	}
	if (optionState && util_chkReturn(option.term, "s") != "") {
		term = option.term;
	}
	if (optionState && util_chkReturn(option.order, "s") != "") {
		order = option.order;
	}
	if (optionState && util_chkReturn(option.preText, "s") != "") {
		preText = option.preText;
	}
	if (optionState && util_chkReturn(option.posText, "s") != "") {
		posText = option.posText;
	}
	if (optionState && util_chkReturn(option.unit, "s") != "") {
		unit = option.unit;
	}
	var numArr = new Array();

	if (start <= end) {
		for (var i = start; i <= end; i += term) {
			numArr.push({
				"cmnnCd" : i * unit,
				"cmnnCdHanNm" : preText + i + posText
			});
		}
	} else {
		for (var i = start; i >= end; i -= term) {
			numArr.push({
				"cmnnCd" : i * unit,
				"cmnnCdHanNm" : preText + i + posText
			});
		}
	}
	util_drawSelectBoxOption(numArr, selBoxId, option);
}

/**
 * 테이블 리스트의 tbody에 "조회된 내역이 없습니다."를 넣어준다.
 * 
 * @param strTempListArea -
 *            String - 복사할 임시 tbody 미입력시 'tempListArea'을 기본으로 세팅
 * @param strListArea -
 *            String - 그려질 tbody 미입력시 'listArea'을 기본으로 세팅
 */
function util_drawListNon(strTempListArea, strListArea) {
	if (util_chkReturn(strTempListArea, "s") == "") {
		strTempListArea = "tempListArea";
	}

	if (util_chkReturn(strListArea, "s") == "") {
		strListArea = "listArea";
	}

	var nTdCount = $("#" + strTempListArea).children("tr").children("td").length;
	$("#" + strListArea).html(
			"<tr><td colspan=\"" + nTdCount
					+ "\" class=\"noData\">조회된 내역이 없습니다.</td></tr>");
}

/*******************************************************************************
 * 기타 함수
 ******************************************************************************/

/**
 * 입력된 숫자를 한글단위로 만든다 입력 : 숫자, 또는 숫자형 문자, 단위 출력 : 한글숫자 예) 111 입력시 백십일 출력
 * 
 * @param str
 *            숫자, 또는 숫자형 문자
 * @param unit
 *            단위. 1000 입력후 111 입력시 십일만천 출력
 * @returns {String}
 */
function util_makeHanNum(str, unit) {
	// 단위 배열
	var unitTen = [ "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" ];// 십단위
																		// 10
																		// (0~9)
	var unitTenThd = [ "", "십", "백", "천" ];// 만단위수
	var unitLimit = [ "", "만", "억", "조", "경", "해" ];// 이후단위 가변적 - 한계단위수
	var hanAmt = "";//

	str = String(str).replace(/[^0-9]/g, "");// 금액등의 입력에 대한 체크로 입력데이터를 숫자로 치환
	str = unit == undefined ? str : String(str * unit);
	strLen = str.length;// 숫자 자리수

	var curUnitTenThd = 0;// 최초단위의 만단위수
	var curUnitLimit = 0;// 최초단위의 한계단위수

	var limitViewCnt = 0; // 현재 변환중인 자릿수 저장
	var limitViewFlag = [ false, true, true, true, true, true ]; // 만단위를
																	// 표시할지에 대한
																	// 플래그

	// 숫자의 가장 뒷자리 부터 숫자를 체크하여 한글로 바꾼다)
	for (var i = strLen - 1; i >= 0; i--) {
		var limitFlag = curUnitLimit % 4 == 0 ? 1 : 0;// 한계단위 체크 1일경우 만단위 출력하는
		// 경우
		var curUT = Number(str.charAt(i));// 10단위
		var curUTT = curUT == 0 ? 0 : curUnitTenThd % 4;// 10000단위
		var curUL = parseInt(curUnitLimit / 4, 10) * limitFlag;// 한계단위

		// 만단위 (10,000~99,990,000)의 숫자가 없을경우 억단위 이상일경우 '만'을 지워주기 위한 처리
		if (curUT != 0) {
			limitViewFlag[parseInt(limitViewCnt / 4, 10)] = false;
		}

		if (curUT == 1 && limitFlag == 0)
			curUT = 0;

		hanAmt = unitTen[curUT] + unitTenThd[curUTT] + unitLimit[curUL]
				+ hanAmt;

		curUnitTenThd++;
		curUnitLimit++;
		limitViewCnt++;
	}
	// 만단위 삭제여부에 따라 치환
	for (var i = 1; i < limitViewFlag.length; i++) {
		if (limitViewFlag[i]) {
			hanAmt = hanAmt.replace(unitLimit[i], '');
		}
	}
	return hanAmt;
}

/**
 * 동적으로 inputTag를 만든다
 */
function util_makeInputTag(obj, parentKey) {
	var strHtml = "";
	var t = typeof (obj);
	if (t != "object" || obj === null) {
		return String("");
	} else {
		var n, v;
		for (n in obj) {
			v = obj[n];
			t = typeof (v);
			if (obj.hasOwnProperty(n)) {
				var inId, inName;
				if ("" == parentKey) {
					inId = n, inName = n;
				} else {
					inId = parentKey + n, inName = parentKey;
				}
				if (t == 'string') {
					strHtml += "<input name=\"" + inName + "\" id=\"" + inId
							+ "\" type=\"hidden\" value=\'" + v + "\' />";
				} else if (t == "object" && v !== null) {// 객체나 배열일 경우 같은
															// name으로
					// 묶어서 하위에서 재귀적 처리
					strHtml += util_makeInputTag(v, parentKey + "[" + n + "]");
				} else {
					strHtml += "<input name=\"" + inName + "\" id=\"" + inId
							+ "\" type=\"hidden\" value=\'" + v + "\' />";
				}
			}
		}
		return strHtml;
	}
}

/**
 * JSONObject 복사. JSON객체를 복사하여 반환한다. 화면에서 objCutData를 생성하여 화면에 그려주는 경우 등 오브젝트
 * 자체를 복사할 경우 사용합니다
 * 
 * @param :
 *            jsonObject
 * @ex : util_copyObj(jsonObject);
 */
function util_copyObj(obj) {
	if (typeof obj == "object") {
		return jQuery.parse(jQuery.stringify(obj));
	}
	alert("util_copyObj : 입력하신 데이터는 객체타입이 아닙니다. 입력값을 확인하세요.#1");
	return obj;
};

/**
 * JSONObject 원소삭제 오브젝트 내의 키값을 갖는 데이터를 삭제하여 결과 반환
 * 
 * @param :
 *            jsonObject , key1, key2, key3 ...
 * @ex : util_delKeyObj(jsonObject , key1, key2, key3 ...);
 */
function util_delKeyObj() {
	var argElmt = util_delKeyObj.arguments;
	var arglen = argElmt.length;

	if (arglen < 2 || typeof argElmt[0] != "object") {
		alert("util_delKeyObj함수 형식에 맞지않는 입력값입니다.");
		return {};
	}

	var key, copyFlag = true, obj = argElmt[0], returnObj = {};

	for ( var item in obj) {
		for (var i = 1; i < arglen; i++) {
			key = argElmt[i];
			if (item == key) {
				copyFlag = false;
				break;
			}
		}
		if (copyFlag) {
			returnObj[item] = obj[item];
		}
		copyFlag = true;
	}
	return returnObj;
};

/**
 * ajax 파일 업로드 초기화 successCb 결과값을 처리하는함수 동적 ajax 업로드 폼생성후 자동 이벤트 바인딩
 * 
 * 사용법 : util_makeFileUploadForm(successCb); successCb은 업로드 결과(result)를 받을시 수행할
 * 함수 업로드 완료후 수행할 함수가 없다면 미입력가능
 * 
 * 
 * 해당 함수를 initFunction에 선언하시면 동적으로 폼이 생성되어 파일업로드가 가능합니다. 파일업로드시 업로드 버튼의
 * type="submit"으로 설정하시면 됩니다.
 * 
 * 성공콜백함수는 업로드 완료후 결과표시, 화면이동 등 필요한 기능을 구현한 함수를 넣어주시면 됩니다.
 */
function util_makeFileUploadForm(successCb, formName, uploadCmd) {
	$("input[type=file]").attr("name", "attachFile");
	// 공통 업로드 cmd
	uploadCmd = uploadCmd == undefined ? "/common/file/FileUpload" : uploadCmd;
	var strHtml = "";
	strHtml += "<form id=\"uploadForm\" name=\"uploadForm\" method=\"POST\" enctype=\"multipart/form-data\" action=\""
			+ uploadCmd + ".ajax\">";
	strHtml += "<input id='CONTTYPE' name='CONTTYPE' value='text/html' type='hidden' />";
	strHtml += "</form>";

	var isPage = $("body").find(".wrapAll").length > 0; // 웹페이지인지 체크
	var isPop = $("body").find(".popWrap").length > 0; // 팝업페이지인지 체크

	if (util_chkReturn(formName, "s") == "") {
		if (isPage) {
			$("body").prepend(strHtml); // 화면에 form 등 생성
			$("#uploadForm").append($(".popAnnounce"));
			$("#uploadForm").append($(".wrapAll"));
		} else if (isPop) {
			$("body").prepend(strHtml); // 화면에 form 등 생성
			$("#uploadForm").append($(".popWrap"));
		} else {
			alert("util_makeFileUploadForm : 화면에 스타일시트가 없습니다.");
		}
	}

	$('#uploadForm').ajaxForm(function(response) {
		var returnResponse = jQuery.parse(response);
		if (typeof successCb == 'function') {
			successCb(returnResponse.result);
		}
	});
}

/*******************************************************************************
 * jQuery 확장 구현부
 ******************************************************************************/

/**
 * jQuery 확장 브라우저별 호환이 안되는 기능에 대해 직접 구현하여 jQuery에 확장하여 사용할 경우 추가되는 부분
 * 
 */

jQuery.extend({
	// JSON.stringify구현 JSON.Stringify 5대 브라우저 호환용
	stringify : function stringify(obj) {
		var t = typeof (obj);
		if (t != "object" || obj === null) {
			if (t == "string")
				obj = '"' + obj + '"';
			return String(obj);
		} else {
			var n, v, json = [], arr = (obj && obj.constructor == Array);
			for (n in obj) {
				v = obj[n];
				t = typeof (v);
				if (obj.hasOwnProperty(n)) {
					if (t == "string")
						v = '"' + v.replace(/"/g, "\\\"").replace(/'/g, "\\\'")
								+ '"'; // " 또는
					// '
					// 입력시
					// json이
					// 깨지는
					// 문제
					// 처리
					else if (t == "object" && v !== null)
						v = jQuery.stringify(v);
					json.push((arr ? "" : '"' + n + '":') + String(v));
				}
			}
			return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
		}
	},
	// JSON.parse구현 JSON.parse 5대 브라우저 호환용
	parse : function parse(str) {
		// 이곳에서 에러가 발생할 경우 대부분 입력값이 잘못된경우입니다.
		// 해당 스트링을 JSON.parse해보시고 같은 에러가 발생한다면 화면에서 수정해주세요
		if (typeof (str) != 'string'
				|| (str.substring(0, 1) != "{" && str.substring(0, 1) != "[")) {
			alert("입력하신 데이터는 parsing이 불가능 합니다. json객체 타입의 문자열을 입력하세요 "
					+ "\n 예상되는 이유 1: 통신에러, 네비게이터 에러시 결과가 HTML형식으로 나올경우 "
					+ "\n 예상되는 이유 2: 넣어준 값이 undefined거나 일반 스트링이나 일반 오브젝트일 경우"
					+ "\n 해당 내용에 로그를 찍어서 확인하시고 화면에서 수정하시면 됩니다."
					+ "\n 입력하신 스트링 :" + str);
			return str;
		}
		return eval("(" + str + ")");
	},
	// Object.keys구현 Object.keys 5대 브라우저 호환용
	keys : function keys(obj) {
		var arr = new Array();
		for ( var item in obj) {
			arr[arr.length] = item;
		}
		return arr;
	},
	// jQuery의 remove가 IE구버전에서 하위 엘리멘탈만 삭제하는 경우가 있음.
	// 본 함수는 JQuery.remove("id"); 로 사용하는 javascript 기반이다.
	// object 형식의 엘리먼트에 id가 있다면 처리할 수 있도록 수정 - 20130122 정헌태
	remove : function remove(strId) {
		if (util_chkReturn(strId, "s") != "") {
			if (jQuery.type(strId) == "object") {
				if (util_chkReturn(strId.attr("id"), "s") != "") {
					strId = strId.attr("id");
				}
			}

			var oPopForm = document.getElementById(strId);

			if (util_chkReturn(oPopForm, "s") == "") {
				return;
			} else {
				oPopForm.parentNode.removeChild(oPopForm);
			}
		} else {
			alert("jQuery.remove() : 잘못된 값으로 해당 function을 호출하였습니다.#1");
		}
	},
	// 특정 엘리먼트의 ID를 넘겨주면 그 하위 엘리먼트를 삭제한다.
	removeChild : function removeChild(tagId) {
		if (util_chkReturn(tagId, "s") == "") {
			return;
		}

		var elemId = "";

		if (jQuery.type(tagId) == "object") {
			if (util_chkReturn(tagId.attr("id"), "s") != "") {
				elemId = tagId.attr("id");
			}
		} else {
			elemId = tagId;
		}

		var elem = document.getElementById(elemId);

		if (util_chkReturn(elem, "s") != "") {
			while (elem.hasChildNodes()) {
				elem.removeChild(elem.lastChild);
			}
		} else {
			return;
		}
	}
});

/**
 * Array확장 배열의 원소를 중복제거하여 반환받는다.
 * 
 * @returns {Array}
 */

/**
 * javascript Map 을 HashMap으로 확장
 */
Map = function() {
	this.map = new Object();
};

/**
 * javascript Map.prototype
 */
Map.prototype = {
	put : function(key, value) {
		this.map[key] = value;
	},
	get : function(key) {
		return this.map[key];
	},
	containsKey : function(key) {
		return key in this.map;
	},
	containsValue : function(value) {
		for ( var prop in this.map) {
			if (this.map[prop] == value)
				return true;
		}
		return false;
	},
	isEmpty : function(key) {
		return (this.size() == 0);
	},
	clear : function() {
		for ( var prop in this.map) {
			delete this.map[prop];
		}
	},
	remove : function(key) {
		delete this.map[key];
	},
	keys : function() {
		var keys = new Array();
		for ( var prop in this.map) {
			keys.push(prop);
		}
		return keys;
	},
	values : function() {
		var values = new Array();
		for ( var prop in this.map) {
			values.push(this.map[prop]);
		}
		return values;
	},
	size : function() {
		var count = 0;
		for ( var prop in this.map) {
			count++;
		}
		return count;
	}
};

/*******************************************************************************
 * 날짜 처리 관련 함수
 ******************************************************************************/

/**
 * 서버 날짜를 yyyyMMdd 형식으로 추출한다. strToken 값을 줄경우 해당 구분자를 삽입한다.
 * 
 * @param strToken -
 *            String - 구분자 값, "-"을 입력하면 yyyy-MM-dd 형태로 리턴 "년월일"로 입력시 yyyy년 MM월
 *            dd일 형태로 리턴
 * @returns {String} - 날짜형식의 문자열
 */
function util_getDate(strToken) {

	var xhr = new XMLHttpRequest();

	/*
	 * if ((location.host).indexOf("localhost") == -1){
	 * xhr.open("POST",location.host, false); } else { xhr.open("POST",
	 * location.protocol + "//" + location.host, false); }
	 * 
	 * 
	 * xhr.open("POST", location.protocol + "//" + location.host, false);
	 * xhr.send(null);
	 * 
	 * var dtServerDate = new Date(xhr.getResponseHeader("Date")); 2013-05-23
	 * 강종철 아래 로직으로 수정합니다
	 */

	var dtServerDate = util_getServerTime();

	var strYear = "" + dtServerDate.getFullYear();
	var strMonth = "" + (dtServerDate.getMonth() + 1);
	var strDay = "" + dtServerDate.getDate();

	if (strMonth.length == 1) {
		strMonth = "0" + strMonth;
	}
	if (strDay.length == 1) {
		strDay = "0" + strDay;
	}

	var strNextToken = "";
	if (util_chkReturn(strToken, "s") != "") {
		if (strToken == "년월일") {
			return strYear + "년 " + strMonth + "월 " + strDay + "일";
		} else {
			strNextToken = strToken;
		}
	}

	return strYear + strNextToken + strMonth + strNextToken + strDay;
}

/**
 * 서버 시간 Object를 가져 옵니다
 */
function util_getServerTime() {
	var dtServerDate = null;
	/*
	 * 임시. 익스플로어에서 로컬 시간 사용.
	 */
    return 	new Date();
    
	if (navigator.appName.indexOf("Microsoft") != -1) {

		dtServerDate = new Date();

	} else {
		var xhr = new XMLHttpRequest();
		var url = location.protocol + "//" + location.host
//				+ "/common/view/MAXINACTIVEINTERVAL.ajax";
		        + "/cmm/intro/selectIntroFintechAppList.ajax";

		xhr.open("POST", url, false);
		// xhr.open("POST", location.protocol + "//"
		// +location.host+"/products/common/saveWeblog.ajax", false);

		// xhr.open("POST", location.host+"?inflow="+inflow, false);

		xhr.send(null);

		dtServerDate = new Date(xhr.getResponseHeader("Date"));

	}

	return dtServerDate;
}

/**
 * 서버 시간을 HHmm 형식으로 추출한다. strToken 값을 줄경우 해당 구분자를 삽입한다. strSec 값을 줄경우 초까지 리턴
 * 
 * @param strToken -
 *            String - 구분자 값, ":"을 입력하면 HH:mm 형태로 리턴
 * @param strSec -
 *            String - "s"삽입할 경우 초단위까지 리턴하게 된다.
 * @returns {String} - 시간형식의 문자열
 */
function util_getTime(strToken, strSec) {
	// var xhr = new XMLHttpRequest();
	/*
	 * if ((location.host).indexOf("localhost") == -1){
	 * xhr.open("POST",location.host, false); } else { xhr.open("POST",
	 * location.protocol + "//" + location.host, false); }
	 */
	/*
	 * xhr.open("POST", location.protocol + "//" + location.host, false);
	 * xhr.send(null);
	 * 
	 * var dtServerDate = new Date(xhr.getResponseHeader("Date"));
	 * 
	 * 
	 * 임시. 익스플로어에서 로컬 시간 사용.
	 * 
	 * if(navigator.appName.indexOf("Microsoft")!=-1){ dtServerDate = new
	 * Date(); }
	 */

	var dtServerDate = util_getServerTime();

	var strHour = "" + dtServerDate.getHours();
	var strMinute = "" + dtServerDate.getMinutes();
	var strSecond = "" + dtServerDate.getSeconds();

	if (strHour.length == 1) {
		strHour = "0" + strHour;
	}
	if (strMinute.length == 1) {
		strMinute = "0" + strMinute;
	}
	if (strSecond.length == 1) {
		strSecond = "0" + strSecond;
	}

	var strNextToken = "";
	if (util_chkReturn(strToken, "s") != "") {
		strNextToken = strToken;
	}

	if (util_chkReturn(strSec, "s") != "") {
		return strHour + strNextToken + strMinute + strNextToken + strSecond;
	}

	return strHour + strNextToken + strMinute;// + strNextToken + strSecond;
}

/**
 * 입력된 문자열이 yyyyMMdd의 날짜형식인지 확인한다.
 * 
 * @param -
 *            String - "YYYYMMDD" 형식의 날짜 스트링
 * @returns - Boolean - 날짜형식일경우 true, 날짜가 아닐경우 false
 */
function util_isDate(curdate) {
	var i, year, month, day;

	if (util_chkReturn(curdate) == false || curdate.length < 8) {
		return false;
	}

	for (i = 0; i < curdate.length; i++) {
		if ((curdate.charAt(i) < "0") || (curdate.charAt(i) > "9")) {
			return false;
		}
	}

	if (util_lTrim(curdate.substring(0, 4), "0").length == 0) {
		return false;
	} else {
		year = parseInt(util_lTrim(curdate.substring(0, 4), "0"), 10);
	}

	if (util_lTrim(curdate.substring(4, 6), "0").length == 0) {
		return false;
	} else {
		month = parseInt(util_lTrim(curdate.substring(4, 6), "0"), 10);
	}

	if (util_lTrim(curdate.substring(6, 8), "0").length == 0) {
		return false;
	} else {
		day = parseInt(util_lTrim(curdate.substring(6, 8), "0"), 10);
	}

	if (year == 0) {
		return false;
	}
	if (month == 0 || month > 12) {
		return false;
	}

	if (day == 0 || day > util_getLastDay(year, month)) {
		return false;
	}

	return true;
}

/**
 * 입력된 문자열이 yyyyMM의 날짜형식인지 확인한다.
 * 
 * @param -
 *            String - "YYYYMM" 형식의 날짜 스트링
 * @returns - Boolean - 날짜형식일경우 true, 날짜가 아닐경우 false
 */
function util_isDate6(curdate) {
	var i, year, month, day;

	if (util_chkReturn(curdate) == false || curdate.length < 6) {
		return false;
	}

	for (i = 0; i < curdate.length; i++) {
		if ((curdate.charAt(i) < "0") || (curdate.charAt(i) > "9")) {
			return false;
		}
	}

	if (util_lTrim(curdate.substring(0, 4), "0").length == 0) {
		return false;
	} else {
		year = parseInt(util_lTrim(curdate.substring(0, 4), "0"), 10);
	}

	if (util_lTrim(curdate.substring(4, 6), "0").length == 0) {
		return false;
	} else {
		month = parseInt(util_lTrim(curdate.substring(4, 6), "0"), 10);
	}

	if (month > 12) {
		return false;
	}

	return true;
}

/**
 * 해당 년월의 마지막날 구하기
 */
function util_getLastDay(year, month) {
	var day = new Date(new Date(year, month, 1) - 86400000).getDate();
	if (year == 9999) {
		day = 99;
	}
	if (day.length == 1) {
		day = "0" + day;
	}
	return day;
}

/**
 * YYYYMMDD 형식 문자열을 YYYY-MM-DD 형식으로 변환 strToken 미입력시는 "-"으로 기본 파싱하며 값이 있을경우
 * 해당값으로 파싱
 * 
 * @param strDate -
 *            String - YYYYMMDD 형식으로 문자열
 * @param strToken -
 *            String - "."등 형식으로 변환 원할경우 사용, 미입력시 "-"로 파싱 "년월일"일 경우 "YYYY년 MM월
 *            DD일"로 리턴한다.
 * @returns String - 변경된 문자열
 */
function util_setFmDate(strDate, strToken) {

	if (util_chkReturn(strDate, "s") == "") {
		return "";
	}

	var date = util_replaceAll(strDate, "-", "");
	date = util_replaceAll(date, ".", "");

	if (util_isDate6(date) == false) {
		//alert('util_setFmDate#1.올바른 날짜를 입력하여 주십시요.');
		alert('올바른 날짜를 입력하여 주십시요.');
		return strDate;
	}

	if (date.length == 8 && util_isDate(date) == false) {
		//alert('util_setFmDate#2.올바른 날짜를 입력하여 주십시요.');
		alert('올바른 날짜를 입력하여 주십시요.');
		return strDate;
	}

	var strSetToken = "-";
	if (util_chkReturn(strToken) == true) {
		strSetToken = strToken;
	}

	var strYear = date.substring(0, 4);
	var strMonth = date.substring(4, 6);
	var strDay = "";

	if (date.length == 8) {
		strDay = date.substring(6, 8);
		if (strSetToken == "년월일") {
			return strYear + "년 " + strMonth + "월 " + strDay + "일";
		} else {
			return strYear + strSetToken + strMonth + strSetToken + strDay;
		}
	}

	return strYear + strSetToken + strMonth;
};

/**
 * 년월일시분초(yyyyMMddHHmmss) 를 yyyy-MM-dd HH:mm:ss 포메팅한다. 혹은 yyyyMMddHHmm를
 * yyyy-MM-dd HH:mm로 변경 변수 nType을 6을 세팅할경우 yyyy-MM-dd만 리턴 변수 nType을 12을 세팅할경우
 * yyyy-MM-dd HH:mm만 리턴
 * 
 * @param strDateTime -
 *            String - 길이 12 or 14의 문자열 혹은 숫자
 * @param nType -
 *            Integer - 보일 타입
 * @returns yyyy-MM-dd HH:mm:ss 포메팅된 문자열
 */
function util_setFmDateTime(strDateTime, nType) {
	var strOrgDataTime = "";
	var strDate = "";
	var strTime = "";
	var nLength = 0;

	if (util_chkReturn(strDateTime, "s") == "") {
		return "";
	}

	strOrgDataTime = strDateTime + "";
	nLength = strOrgDataTime.length;

	if (nLength != 14 && nLength != 12) {
		return strDateTime;
	}

	strDate = strOrgDataTime.substring(0, 8);
	strDate = util_setFmDate(strDate);

	if (util_chkReturn(nType, "s") == "") {
		nType = nLength;
	} else if (nType != 12 && nType != 6) {
		nType = nLength;
	}

	if (nType == 6) {
		return strDate;
	}

	if (nLength == 12 || nType == 12) {
		strTime = strOrgDataTime.substring(8, 12);
	} else {
		strTime = strOrgDataTime.substring(8, 14);
	}

	if (nLength == 12 || nType == 12) {
		strTime = strTime.substring(0, 2) + ":" + strTime.substring(2, 4);
	} else {
		strTime = strTime.substring(0, 2) + ":" + strTime.substring(2, 4) + ":"
				+ strTime.substring(4, 6);
	}

	return strDate + " " + strTime;
}

/**
 * yyyyMMdd 타입의 날짜를 받아 년,월,일에 날자를 더하거나 뺀다. 변수 strReType에 'n'을 세팅하면 int형으로 리턴한다.
 * 
 * @param strDate -
 *            String - yyyyMMdd 형태의 날짜 스트링
 * @param strType -
 *            String - 더하거나 뺄 곳, 년: "y", 월: "m", 일: "d"
 * @param nValue -
 *            Intger - 더하건 뺄 값
 * @param strReType -
 *            String - 입력하지 않거나 빈스트링일경우 문자형 리턴, "n"등 입력할경우 int형 리턴
 * @returns 문자열 혹은 number 형 yyyyMMdd 리턴
 */
function util_addDate(strDate, strType, nValue, strReType) {
	if (util_isDate(strDate) == false) {
		alert('util_addDate.올바른 날짜를 입력하여 주십시요.');
		return strDate;
	}

	if (util_chkReturn(strType, "s") == "" || util_chkReturn(nValue, "s") == ""
			|| jQuery.type(nValue) != "number") {
		alert('올바른 파라메타를 입력해 주십시오.');
		return strDate;
	}

	var nYear = parseInt(strDate.substring(0, 4), 10);
	var nMonty = parseInt(strDate.substring(4, 6) - 1, 10);
	var nDate = parseInt(strDate.substring(6, 8), 10);

	if (strType == "y") {
		nYear = nYear + nValue;
	} else if (strType == "m") {
		nMonty = nMonty + nValue;
	} else if (strType == "d") {
		nDate = nDate + nValue;
	}

	var dtInDate = new Date(nYear, nMonty, nDate);
	var strYear = "" + dtInDate.getFullYear();
	var strMonth = "" + (dtInDate.getMonth() + 1);
	var strDay = "" + dtInDate.getDate();

	if (strMonth.length == 1) {
		strMonth = "0" + strMonth;
	}
	if (strDay.length == 1) {
		strDay = "0" + strDay;
	}

	if (util_chkReturn(strReType, "s") != "") {
		return parseInt(strYear + strMonth + strDay, 10);
	}

	return strYear + strMonth + strDay;
}

/**
 * Input Calendar 조회 시작일 7일전 날자, 조회 종료일 금일 날자 세팅 strStartCalendarId,
 * strEndCalendarId 미입력시 조회 시작일 클레스 calendar, 조회종료일 클레스 calendar2 로 잡고 세팅함
 * strStartCalendarId, strEndCalendarId 각 inputBox의 아이디를 넣어주면 해당 input 박스에 값 세팅
 * strStartCalendarId 만 입력할 경우 해당 곳에만 조회 시작일 7일전 날자 세팅
 * 
 * @param strStartCalendarId -
 *            String - Calendar inputBox ID
 * @param strEndCalendarId -
 *            String - Calendar inputBox ID
 */
function util_setCalendar1j(strStartCalendarId, strEndCalendarId) {
	util_setCalendarDate("d", -7, strStartCalendarId, strEndCalendarId);
}

/**
 * Input Calendar 조회 시작일 15일전 날자, 조회 종료일 금일 날자 세팅 strStartCalendarId,
 * strEndCalendarId 미입력시 조회 시작일 클레스 calendar, 조회종료일 클레스 calendar2 로 잡고 세팅함
 * strStartCalendarId, strEndCalendarId 각 inputBox의 아이디를 넣어주면 해당 input 박스에 값 세팅
 * strStartCalendarId 만 입력할 경우 해당 곳에만 조회 시작일 7일전 날자 세팅
 * 
 * @param strStartCalendarId -
 *            String - Calendar inputBox ID
 * @param strEndCalendarId -
 *            String - Calendar inputBox ID
 */
function util_setCalendar15d(strStartCalendarId, strEndCalendarId) {
	util_setCalendarDate("d", -15, strStartCalendarId, strEndCalendarId);
}

/**
 * Input Calendar 조회 시작일 1개월전 날자, 조회 종료일 금일 날자 세팅 strStartCalendarId,
 * strEndCalendarId 미입력시 조회 시작일 클레스 calendar, 조회종료일 클레스 calendar2 로 잡고 세팅함
 * strStartCalendarId, strEndCalendarId 각 inputBox의 아이디를 넣어주면 해당 input 박스에 값 세팅
 * strStartCalendarId 만 입력할 경우 해당 곳에만 조회 시작일 7일전 날자 세팅
 * 
 * @param strStartCalendarId -
 *            String - Calendar inputBox ID
 * @param strEndCalendarId -
 *            String - Calendar inputBox ID
 */
function util_setCalendar1m(strStartCalendarId, strEndCalendarId) {
	util_setCalendarDate("m", -1, strStartCalendarId, strEndCalendarId);
}

/**
 * Input Calendar 조회 시작일 3개월전 날자, 조회 종료일 금일 날자 세팅 strStartCalendarId,
 * strEndCalendarId 미입력시 조회 시작일 클레스 calendar, 조회종료일 클레스 calendar2 로 잡고 세팅함
 * strStartCalendarId, strEndCalendarId 각 inputBox의 아이디를 넣어주면 해당 input 박스에 값 세팅
 * strStartCalendarId 만 입력할 경우 해당 곳에만 조회 시작일 7일전 날자 세팅
 * 
 * @param strStartCalendarId -
 *            String - Calendar inputBox ID
 * @param strEndCalendarId -
 *            String - Calendar inputBox ID
 */
function util_setCalendar3m(strStartCalendarId, strEndCalendarId) {
	util_setCalendarDate("m", -3, strStartCalendarId, strEndCalendarId);
}

/**
 * 조회시작일, 조회종료일 inputbox에 날자를 세팅한다.(util_setCalendar1j 등에서 사용 한다.)
 * 
 * @param strType
 * @param nValue
 * @param strStartCalendarId
 * @param strEndCalendarId
 */
function util_setCalendarDate(strType, nValue, strStartCalendarId,
		strEndCalendarId) {
	if (util_chkReturn(strStartCalendarId, "s") == ""
			&& util_chkReturn(strEndCalendarId, "s") == "") {
		$(".calendar").val(
				util_setFmDate(util_addDate(util_getDate(), strType, nValue)));
		$(".calendar2").val(util_getDate("-"));
	} else if (util_chkReturn(strStartCalendarId, "s") != ""
			&& util_chkReturn(strEndCalendarId, "s") != "") {
		$("#" + strStartCalendarId).val(
				util_setFmDate(util_addDate(util_getDate(), strType, nValue)));
		$("#" + strEndCalendarId).val(util_getDate("-"));
	} else if (util_chkReturn(strStartCalendarId, "s") != ""
			&& util_chkReturn(strEndCalendarId, "s") == "") {
		$("#" + strStartCalendarId).val(
				util_setFmDate(util_addDate(util_getDate(), strType, nValue)));
	}
}

/**
 * 두 날짜의 차이를 일자로 구한다.(조회 종료일 - 조회 시작일)
 * 
 * @param strStartCalendarId -
 *            조회 시작일(날짜 ex.2002-01-01)
 * @param strEndCalendarId -
 *            조회 종료일(날짜 ex.2002-01-01)
 * @param strType -
 *            String - 리턴 년: "y", 월: "m", 일: "d"
 * @return 기간에 해당하는 일자
 */
function util_getDateRange(strStartCalendarId, strEndCalendarId, strType) {
	var result = "";
	var FORMAT = "-";

	// FORMAT을 포함한 길이 체크
	if (strStartCalendarId.length != 10 || strEndCalendarId.length != 10)
		return null;

	// FORMAT이 있는지 체크
	if (strStartCalendarId.indexOf(FORMAT) < 0
			|| strEndCalendarId.indexOf(FORMAT) < 0)
		return null;

	// 년도, 월, 일로 분리
	var start_dt = strStartCalendarId.split(FORMAT);
	var end_dt = strEndCalendarId.split(FORMAT);

	// 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
	// Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
	start_dt[1] = (Number(start_dt[1]) - 1) + "";
	end_dt[1] = (Number(end_dt[1]) - 1) + "";

	var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
	var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);
	var interval = (to_dt.getTime() - from_dt.getTime());

	// 년
	if (strType == 'y') {
		result = interval / 1000 / 60 / 60 / 24;

		// 월
	} else if (strType == 'm') {
		result = interval / 1000 / 60 / 60 / 24 / 30;

		// 일
	} else if (strType == 'm') {
		result = interval / 1000 / 60 / 60 / 24 / 30 / 12;

		// 일
	} else {
		result = interval / 1000 / 60 / 60 / 24 / 30 / 12;
	}

	return Math.floor(result);
}

/**
 * 조회시작일과 조회종료일을 비교하여 조회 시작일이 조회종료일 이후일경우 false 리턴 "조회시작일이 조회종료일 이후 입니다."
 * 
 * @param strStartDay -
 *            String - yyyyMMdd 혹은 yyyy-MM-dd형식의 문자열
 * @param strEndDay -
 *            String - yyyyMMdd 혹은 yyyy-MM-dd형식의 문자열
 * @returns {Boolean} - 정상일경우 true, 비정상일 경우 false
 */
function util_chkDateStartUp(strStartDay, strEndDay) {
	if (util_chkReturn(strStartDay, "s") == ""
			|| util_chkReturn(strEndDay, "s") == "") {
		alert("util_chkDateStartUp : 입력된 날짜를 확인해 주세요.");
		return false;
	}

	var nStartDay = parseInt(util_replaceAll(strStartDay, "-", ""), 10);
	var nEndDay = parseInt(util_replaceAll(strEndDay, "-", ""), 10);

	if (nStartDay > nEndDay) {
		return false;
	} else {
		return true;
	}
}

/**
 * 조회일이 금일 이후 날짜인지 판단하여 이후일경우 false 리턴 "조회기간은 금일 이후로 선택할 수 없습니다."
 * 
 * @param strDay -
 *            String - yyyyMMdd 혹은 yyyy-MM-dd형식의 문자열
 * @returns {Boolean} - 정상일경우 true, 비정상일 경우 false
 */
function util_chkDateToDay(strDay) {
	if (util_chkReturn(strDay, "s") == "") {
		alert("util_chkDateToDay : 입력된 날짜를 확인해 주세요.");
		return false;
	}

	var nStartDay = parseInt(util_replaceAll(strDay, "-", ""), 10);
	var nEndDay = parseInt(util_getDate(), 10);

	if (nStartDay > nEndDay) {
		return false;
	} else {
		return true;
	}
}

/**
 * 조회시작일과 조회종료일이 특정 일수 차이인지 판단하여 범위 밖일경우 false 리턴 조회종료일에 특정 일수를 가감하여 조회시작일과
 * 비교하도록 되어 있음. "조회기간을 1년 이내로 설정해 주세요."
 * 
 * @param strStartDay -
 *            String - yyyyMMdd 혹은 yyyy-MM-dd형식의 문자열
 * @param strEndDay -
 *            String - yyyyMMdd 혹은 yyyy-MM-dd형식의 문자열
 * @param strType -
 *            String - y 년, m 월, d 일
 * @param nValue -
 *            Integer - 가감할 숫자
 * @returns {Boolean} - 정상일경우 true, 비정상일 경우 false
 */
function util_chkDateGap(strStartDay, strEndDay, strType, nValue) {
	if (util_chkReturn(strStartDay, "s") == ""
			|| util_chkReturn(strEndDay, "s") == "") {
		alert("util_chkDateGap : 입력된 날짜를 확인해 주세요.");
		return false;
	}

	var nStartDay = parseInt(util_replaceAll(strStartDay, "-", ""), 10);
	var nEndDay = util_addDate(util_replaceAll(strEndDay, "-", ""), strType,
			nValue, "n");

	if (nStartDay < nEndDay) {
		return false;
	} else {
		return true;
	}
}

/**
 * 조회시작일과 조회종료일이 있는 두개의 Calendard의 벨리데이션 체크를 한다.(특정 일수 차이 1년으로 고정되어 있음.) 조회시작일과
 * 조회종료일을 비교하여 조회 시작일이 조회종료일 이후일경우 체크 조회일이 금일 이후 날짜인지 판단하여 이후일경우 체크 조회시작일과
 * 조회종료일이 특정 일수 차이인지 판단하여 범위 밖일경우 체크 class calendar, calendar2를 기본 체크하며, 파라메타값을
 * 줄경우 id로 체크
 * 
 * @param strStartCalendarId -
 *            String - 조회시작일 input의 id
 * @param strEndCalendarId -
 *            String - 조회종료일 input의 id
 * @returns {Boolean} - 정상일경우 true, 비정상일 경우 false
 */
function util_chkCalendar01(strStartCalendarId, strEndCalendarId) {
	var strStartKey = ".calendar";
	var strEndKey = ".calendar2";

	if (util_chkReturn(strStartCalendarId, "s") != "") {
		strStartKey = "#" + strStartCalendarId;
	}

	if (util_chkReturn(strEndCalendarId, "s") != "") {
		strEndKey = "#" + strEndCalendarId;
	}

	if (!util_chkDateToDay($(strStartKey).val())) {
		alert("조회기간은 금일 이후로 선택할 수 없습니다.");
		$(strStartKey).focus();
		return false;
	}

	if (!util_chkDateToDay($(strEndKey).val())) {
		alert("조회기간은 금일 이후로 선택할 수 없습니다.");
		$(strEndKey).focus();
		return false;
	}

	if (!util_chkDateStartUp($(strStartKey).val(), $(strEndKey).val())) {
		alert("조회시작일이 조회종료일 이후 입니다.");
		$(strStartKey).focus();
		return false;
	}

	if (!util_chkDateGap($(strStartKey).val(), $(strEndKey).val(), "y", -1)) {
		alert("조회기간을 1년 이내로 설정해 주세요.");
		$(strStartKey).focus();
		return false;
	}

	return true;
}

/**
 * 팝업 호출함수(modal || window)
 * 
 * @param popUrl(필수)
 * @param type
 *            (modal || window)[모달레이어 혹은 윈도우팝업]
 * @param width
 * @param height
 * @param opacityBg
 *            (전체화면 투명도)
 * @param opacity
 *            (팝업 투명도)
 * @param bz
 *            (전체화면 z-index 우선순위)
 * @param iz
 *            (팝업 z-index 우선순위)
 */
(function($) {
	$.fn.JQmodal = function(option) {
		var settings = $.extend({
			popUrl : 'about:blank',
			objParam : null,
			type : 'modal',
			width : 850,
			height : 630,
			opacityBg : 0.3,
			opacity : 1,
			bz : 1000001,
			iz : 1000002,
			loadingImgUrl : "",
			loadType : false,
			closeBtnClass : "btnClose",
			bgColor : "#fff"
		}, option);

		var strLoadingView01 = "";
		var strLoadingView02 = "";

		var paramHeight = "";
		if (util_chkReturn(option.height, "s") != "") {
			paramHeight = option.height;
		}
		var paramWidth = "";
		if (util_chkReturn(option.width, "s") != "") {
			paramWidth = option.width;
		}

		if (settings.loadType != true) {
			// if(loadingImgBg.src == 'undefined'){
			// loadingImgBg.src = "/images/cmm/bg_loadingWrap.png";
			// }
			// strLoadingView01 = "<div id=\"loadingWrap\"
			// style=\"background-image:url('" + loadingImgBg.src + "'); \">";
			var loadingImgBgSrc = "/images/cmm/bg_loadingWrap.png";
			strLoadingView01 = "<div id=\"loadingWrap\" style=\"background-image:url('"
					+ loadingImgBgSrc + "'); \">";
			strLoadingView02 = "</div>";
		}

		return this
				.each(function() {
					window.modalLauncher = $(this);
					switch (settings.type) {
					case 'modal':
						$(
								'<div id="modal_back" ' + 'style="z-index:'
										+ settings.bz + ';'
										+ 'position:absolute;' + 'left:0;'
										+ 'top:0;' + 'min-width:'
										+ document.documentElement.clientWidth
										+ 'px;' + 'width:100%;' + // document.documentElement.clientWidth+'px;'+
										'min-height:' + $(document).height()
										+ 'px;' + 'height:100%;' + // $(document).height()+'px;'+
										'background-color:#000;' + 'opacity:'
										+ settings.opacityBg + ';'
										+ 'filter:alpha(opacity='
										+ settings.opacityBg * 100 + ');'
										+ '"></div>').appendTo($('body'));

						$
								.each(
										$.browser,
										function(name, val) {
											if (name == 'msie'
													&& $.browser.version
															.substr(0, 3) == '6.0') {
												$(
														'<iframe src="about:blank" '
																+ 'width="'
																+ document.documentElement.clientWidth
																+ '"'
																+ 'height="'
																+ $(document)
																		.height()
																+ '"'
																+ 'frameborder="0" scrolling="no" transparency="true" '
																+ 'style="position:absolute;'
																+ 'left:0;'
																+ 'top:0;' +
																// 'opacity:'+settings.opacity+';'+
																// 'filter:alpha(opacity='+settings.opacity*100+');'+
																'z-index:-1;'
																+ '"></iframe>')
														.appendTo(
																$('#modalBack'));
											}
										});

						var appData = '<div id="modal_ifrmWrap" ' + 'style="'
								+ 'z-index:'
								+ settings.iz
								+ ';'
								+ 'position:absolute;'
								+ 'left:50%;'
								+ 'top:'
								+ ($(document).scrollTop() + document.documentElement.clientHeight / 2)
								+ 'px;'
								+ 'margin-left:'
								+ (-settings.width / 2)
								+ 'px;'
								+ 'margin-top:'
								+ (-settings.height / 2 + 70)
								+ 'px;'
								+ 'background-color:'
								+ settings.bgColor
								+ ';'
								+ 'opacity:'
								+ settings.opacity
								+ ';'
								+ 'border:0px solid #8a8a8a;'
								+ '">'
								+ '<iframe name="modalIframe" id="modalIfm" src="about:blank"'
								//+ settings.popUrl + '"';
						if (util_chkReturn(paramWidth, "s") == ""
								&& util_chkReturn(paramHeight, "s") == "") {
							appData += 'onload="javascript:gfn_modalPopupAutoResize(this);"';

						} else if (util_chkReturn(paramWidth, "s") == ""
								&& util_chkReturn(paramHeight, "s") != "") {
							appData += 'width='
									+ paramWidth
									+ 'px'
									+ ' onload="javascript:gfn_modalPopupAutoWidthResize(this);"';

						} else if (util_chkReturn(paramWidth, "s") != ""
								&& util_chkReturn(paramHeight, "s") == "") {
							appData += 'height='
									+ paramHeight
									+ 'px'
									+ ' onload="javascript:gfn_modalPopupAutoHeightResize(this);"';

						} else {
							appData += 'width="' + paramWidth + 'px" height="'
									+ paramHeight + 'px"';
						}
						appData += 'allowtransparency="1" frameborder="0" scrolling="no"></div>';

						$('body').append(appData);
												
						util_movePage(settings.popUrl, settings.objParam, "modalIframe");
						
						/*
						 * if(util_chkReturn(paramHeight, "s") == ""){ $('<div
						 * id="modal_ifrmWrap" '+ 'style="'+
						 * 'z-index:'+settings.iz+';'+ 'position:absolute;'+
						 * 'left:50%;'+
						 * 'top:'+($(document).scrollTop()+document.documentElement.clientHeight/2)+'px;'+
						 * 'margin-left:'+(-settings.width/2)+'px;'+
						 * 'margin-top:'+(-settings.height/2)+'px;'+
						 * 'background-color:'+ settings.bgColor + ';'+
						 * 'opacity:'+settings.opacity+';'+ 'border:0px solid
						 * #8a8a8a;'+ '">'+ '<iframe name="modalIframe"
						 * id="modalIfm" src="'+settings.popUrl+'"'+
						 * 'onload="javascript:gfn_modalPopupAutoResize(this);"'+
						 * 'allowtransparency="1" frameborder="0"
						 * scrolling="no">'+ '</div>').appendTo($('body'));
						 * }else{ $('<div id="modal_ifrmWrap" '+ 'style="'+
						 * 'z-index:'+settings.iz+';'+ 'position:absolute;'+
						 * 'left:50%;'+
						 * 'top:'+($(document).scrollTop()+document.documentElement.clientHeight/2)+'px;'+
						 * 'margin-left:'+(-settings.width/2)+'px;'+
						 * 'margin-top:'+(-settings.height/2)+'px;'+
						 * 'background-color:'+ settings.bgColor + ';'+
						 * 'opacity:'+settings.opacity+';'+ 'border:0px solid
						 * #8a8a8a;'+ '">'+ '<iframe name="modalIframe"
						 * id="modalIfm" src="'+settings.popUrl+'"'+
						 * 'width="'+paramWidth+'px"
						 * height="'+paramHeight+'px"'+ 'allowtransparency="1"
						 * frameborder="0" scrolling="no">'+ '</div>').appendTo($('body')); }
						 */

						$('#modalIfm').focus();
						$('#modalIfm')
								.load(
										function() {
											var targetFrame = $.browser.msie
													&& $.browser.version == "7.0" ? this.contentWindow.document
													: this.contentDocument;
											$(targetFrame)
													.find('.popWrap')
													.prepend(
															'<p class="'
																	+ settings.closeBtnClass
																	+ '"><input type="button" value="레이어 닫기" onclick="gfn_closeModal(event);" /></p>');
											$(targetFrame)
													.find('.popWrap')
													.css(
															{
																height : settings.height
																		+ 'px'
															});
										});
						// $('#modal_back').click(function(){
						// $(this).next().find('iframe').attr('src','').end().remove().end().remove();
						// });
						break;
					case 'window':
						windowModal = window.open(settings.popUrl, '', 'width='
								+ settings.width + ',height=' + settings.height
								+ ',toolbar=no' + ',menubar=no'
								+ ',location=no' + ',scrollbars=yes'
								+ ',status=no' + ',realzable=yes'
								+ ',fullscreen=no');
						windowModal.focus();
						break;
					case 'loading':
						$(
								'<div id="modal_loading_back" '
										+ 'style="z-index:' + settings.bz + ';'
										+ 'position:absolute;' + 'left:0;'
										+ 'top:0;' + 'min-width:'
										+ document.documentElement.clientWidth
										+ 'px;' + 'width:100%;' + // document.documentElement.clientWidth+'px;'+
										'min-height:' + $(document).height()
										+ 'px;' + 'height:100%;' + // $(document).height()+'px;'+
										'background-color:#000;' + 'opacity:'
										+ settings.opacityBg + ';'
										+ 'filter:alpha(opacity='
										+ settings.opacityBg * 100 + ');'
										+ '"></div>').appendTo($('body'));
						/*
						 * $.each($.browser, function(name, val) { if(name ==
						 * 'msie' && $.browser.version.substr(0,3) == '6.0'){
						 * $('<iframe src="about:blank" '+
						 * 'width="'+document.documentElement.clientWidth+'"'+
						 * 'height="'+$(document).height()+'"'+ 'frameborder="0"
						 * scrolling="no" transparency="true" '+
						 * 'style="position:absolute;'+ 'left:0;'+ 'top:0;'+
						 * //'opacity:'+settings.opacity+';'+
						 * //'filter:alpha(opacity='+settings.opacity*100+');'+
						 * 'z-index:-1;'+ '"></iframe>').appendTo($('#modalBack')) }
						 * });
						 */
						$(
								'<div id="modal_loading_wrap" ' + 'style="'
										+ 'z-index:'
										+ settings.iz
										+ ';'
										+ 'position:absolute;'
										+ 'left:50%;'
										+ 'top:'
										+ ($(document).scrollTop() + document.documentElement.clientHeight / 2)
										+ 'px;'
										+ 'margin-left:'
										+ (-settings.width / 2)
										+ 'px;'
										+ 'margin-top:'
										+ (-settings.height / 2)
										+ 'px;'
										+ 'background-color:#FFFFFF;'
										+
										// 'opacity:'+settings.opacity+';'+
										'border:0px solid #8a8a8a;'
										+
										// 'filter:alpha(opacity='+settings.opacityBg*100+');'+
										'">'
										+ strLoadingView01
										+ '<img id="imgLoading" alt=\"잠시만 기다려 주세요.\" src=\"'
										+ settings.loadingImgUrl
										+ '\">'
										+ strLoadingView02 + '</div>')
								.appendTo($('body'));
						$('#imgLoading').focus();
						/*
						 * $('#modalIfm').focus();
						 * $('#modalIfm').load(function(){
						 * $(this.contentDocument).find('.popWrap').prepend('<p class="btnClose"><input
						 * type="button" id="btn_popup_close" value="레이어 닫기"
						 * onclick="gfn_closeModal(event);" /></p>'); });
						 */
						break;
					}
				});
	};
})(jQuery);

/**
 * 팝업 호출함수(modal || window)
 *
 * @param popUrl(필수)
 * @param type
 *            (modal || window)[모달레이어 혹은 윈도우팝업]
 * @param width
 * @param height
 * @param opacityBg
 *            (전체화면 투명도)
 * @param opacity
 *            (팝업 투명도)
 * @param bz
 *            (전체화면 z-index 우선순위)
 * @param iz
 *            (팝업 z-index 우선순위)
 */
(function($) {
	$.fn.JQmodal2 = function(option) {
		var settings = $.extend({
			popUrl : 'about:blank',
			objParam : null,
			type : 'modal',
			width : 850,
			height : 630,
			opacityBg : 0.3,
			opacity : 1,
			bz : 1000001,
			iz : 1000002,
			loadingImgUrl : "",
			loadType : false,
			closeBtnClass : "btnClose",
			bgColor : "#fff"
		}, option);

		var strLoadingView01 = "";
		var strLoadingView02 = "";

		var paramHeight = "";
		if (util_chkReturn(option.height, "s") != "") {
			paramHeight = option.height;
		}
		var paramWidth = "";
		if (util_chkReturn(option.width, "s") != "") {
			paramWidth = option.width;
		}

		if (settings.loadType != true) {
			// if(loadingImgBg.src == 'undefined'){
			// loadingImgBg.src = "/images/cmm/bg_loadingWrap.png";
			// }
			// strLoadingView01 = "<div id=\"loadingWrap\"
			// style=\"background-image:url('" + loadingImgBg.src + "'); \">";
			var loadingImgBgSrc = "/images/cmm/bg_loadingWrap.png";
			strLoadingView01 = "<div id=\"loadingWrap\" style=\"background-image:url('"
				+ loadingImgBgSrc + "'); \">";
			strLoadingView02 = "</div>";
		}

		return this
			.each(function() {
				window.modalLauncher = $(this);
				switch (settings.type) {
					case 'modal':
						$(
							'<div id="modal_back" ' + 'style="z-index:'
							+ settings.bz + ';'
							+ 'position:absolute;' + 'left:0;'
							+ 'top:0;' + 'min-width:'
							+ document.documentElement.clientWidth
							+ 'px;' + 'width:100%;' + // document.documentElement.clientWidth+'px;'+
							'min-height:' + $(document).height()
							+ 'px;' + 'height:100%;' + // $(document).height()+'px;'+
							'background-color:#000;' + 'opacity:'
							+ settings.opacityBg + ';'
							+ 'filter:alpha(opacity='
							+ settings.opacityBg * 100 + ');'
							+ '"></div>').appendTo($('body'));

						$
							.each(
								$.browser,
								function(name, val) {
									if (name == 'msie'
										&& $.browser.version
											.substr(0, 3) == '6.0') {
										$(
											'<iframe src="about:blank" '
											+ 'width="'
											+ document.documentElement.clientWidth
											+ '"'
											+ 'height="'
											+ $(document)
												.height()
											+ '"'
											+ 'frameborder="0" scrolling="no" transparency="true" '
											+ 'style="position:absolute;'
											+ 'left:0;'
											+ 'top:0;' +
											// 'opacity:'+settings.opacity+';'+
											// 'filter:alpha(opacity='+settings.opacity*100+');'+
											'z-index:-1;'
											+ '"></iframe>')
											.appendTo(
												$('#modalBack'));
									}
								});

						var appData = '<div id="modal_ifrmWrap" ' + 'style="'
							+ 'z-index:'
							+ settings.iz
							+ ';'
							+ 'position:absolute;'
							+ 'left:50%;'
							+ 'top:'
							+ ($(document).scrollTop() + document.documentElement.clientHeight / 2)
							+ 'px;'
							+ 'margin-left:'
							+ (-settings.width / 2)
							+ 'px;'
							+ 'margin-top:'
							+ (-settings.height / 2)
							+ 'px;'
							+ 'background-color:'
							+ settings.bgColor
							+ ';'
							+ 'opacity:'
							+ settings.opacity
							+ ';'
							+ 'border:0px solid #8a8a8a;'
							+ '">'
							+ '<iframe name="modalIframe1" id="modalIfm" src="about:blank"'
						//+ settings.popUrl + '"';
						if (util_chkReturn(paramWidth, "s") == ""
							&& util_chkReturn(paramHeight, "s") == "") {
							appData += 'onload="javascript:gfn_modalPopupAutoResize(this);"';

						} else if (util_chkReturn(paramWidth, "s") == ""
							&& util_chkReturn(paramHeight, "s") != "") {
							appData += 'width='
								+ paramWidth
								+ 'px'
								+ ' onload="javascript:gfn_modalPopupAutoWidthResize(this);"';

						} else if (util_chkReturn(paramWidth, "s") != ""
							&& util_chkReturn(paramHeight, "s") == "") {
							appData += 'height='
								+ paramHeight
								+ 'px'
								+ ' onload="javascript:gfn_modalPopupAutoHeightResize(this);"';

						} else {
							appData += 'width="' + paramWidth + 'px" height="'
								+ paramHeight + 'px"';
						}
						appData += 'allowtransparency="1" frameborder="0" scrolling="no"></div>';

						$('body').append(appData);
						util_movePage(settings.popUrl, settings.objParam, "modalIframe1");

						$('#modalIfm').focus();
						$('#modalIfm')
							.load(
								function() {
									var targetFrame = $.browser.msie
									&& $.browser.version == "7.0" ? this.contentWindow.document
										: this.contentDocument;
									$(targetFrame)
										.find('.popWrap')
										.prepend(
											'<p class="'
											+ settings.closeBtnClass
											+ '"><input type="button" value="레이어 닫기" onclick="gfn_closeModal(event);" /></p>');
									$(targetFrame)
										.find('.popWrap')
										.css(
											{
												height : settings.height
												+ 'px'
											});
								});
						// $('#modal_back').click(function(){
						// $(this).next().find('iframe').attr('src','').end().remove().end().remove();
						// });
						break;
					case 'window':
						windowModal = window.open(settings.popUrl, '', 'width='
							+ settings.width + ',height=' + settings.height
							+ ',toolbar=no' + ',menubar=no'
							+ ',location=no' + ',scrollbars=yes'
							+ ',status=no' + ',realzable=yes'
							+ ',fullscreen=no');
						windowModal.focus();
						break;
					case 'loading':
						$(
							'<div id="modal_loading_back" '
							+ 'style="z-index:' + settings.bz + ';'
							+ 'position:absolute;' + 'left:0;'
							+ 'top:0;' + 'min-width:'
							+ document.documentElement.clientWidth
							+ 'px;' + 'width:100%;' + // document.documentElement.clientWidth+'px;'+
							'min-height:' + $(document).height()
							+ 'px;' + 'height:100%;' + // $(document).height()+'px;'+
							'background-color:#000;' + 'opacity:'
							+ settings.opacityBg + ';'
							+ 'filter:alpha(opacity='
							+ settings.opacityBg * 100 + ');'
							+ '"></div>').appendTo($('body'));

						$(
							'<div id="modal_loading_wrap" ' + 'style="'
							+ 'z-index:'
							+ settings.iz
							+ ';'
							+ 'position:absolute;'
							+ 'left:50%;'
							+ 'top:'
							+ ($(document).scrollTop() + document.documentElement.clientHeight / 2)
							+ 'px;'
							+ 'margin-left:'
							+ (-settings.width / 2)
							+ 'px;'
							+ 'margin-top:'
							+ (-settings.height / 2)
							+ 'px;'
							+ 'background-color:#FFFFFF;'
							+
							// 'opacity:'+settings.opacity+';'+
							'border:0px solid #8a8a8a;'
							+
							// 'filter:alpha(opacity='+settings.opacityBg*100+');'+
							'">'
							+ strLoadingView01
							+ '<img id="imgLoading" alt=\"잠시만 기다려 주세요.\" src=\"'
							+ settings.loadingImgUrl
							+ '\">'
							+ strLoadingView02 + '</div>')
							.appendTo($('body'));
						$('#imgLoading').focus();
						/*
						 * $('#modalIfm').focus();
						 * $('#modalIfm').load(function(){
						 * $(this.contentDocument).find('.popWrap').prepend('<p class="btnClose"><input
						 * type="button" id="btn_popup_close" value="레이어 닫기"
						 * onclick="gfn_closeModal(event);" /></p>'); });
						 */
						break;
				}
			});
	};
})(jQuery);

/**
 * 모달 로딩화면을 보이게 한다.
 */
function showLoading() {
	/*
	 * var strExpImgUrl = "products_loading"; // 로딩 이미지 키 사망보장보험 - 정기보험 var
	 * strExpImgUrl = "products_loading_2"; // 로딩 이미지 키 연금보험 var strExpImgUrl =
	 * "products_loading_3"; // 로딩 이미지 키 실손의료비보험 var strExpImgUrl =
	 * "products_loading_4"; // 로딩 이미지 키 사망보장보험 - 종신보험 var strExpImgUrl =
	 * "products_loading_5"; // 로딩 이미지 키 연금저축보험
	 * 
	 * 이미지 경로 /images/cont/products_loading.gif // 사망보장보험 - 정기보험
	 * /images/cont/products_loading_2.gif // 연금보험
	 * /images/cont/products_loading_3.gif // 실손의료비보험
	 * /images/cont/products_loading_4.gif // 사망보장보험 - 종신보험
	 * /images/cont/products_loading_5.gif // 연금저축보험
	 */

	var strExpImgUrl = ""; // 로딩 이미지 url

	if (typeof (strShowLoadingImgKey) == "undefined") {
		strExpImgUrl = "";
	} else {
		if (strShowLoadingImgKey != "") {

			switch (strShowLoadingImgKey) {
			case "products_loading":
				strExpImgUrl = "/images/cmm/viewLoading.gif"; // 사망보장보험 - 정기보험
				break;
			case "products_loading_2":
				strExpImgUrl = "/images/cmm/viewLoading_2.gif"; // 연금보험
				break;
			case "products_loading_3":
				strExpImgUrl = "/images/cmm/viewLoading_3.gif"; // 실손의료비보험
				break;
			case "products_loading_4":
				strExpImgUrl = "/images/cmm/viewLoading_4.gif"; // 사망보장보험 - 종신보험
				break;
			case "products_loading_5":
				strExpImgUrl = "/images/cmm/viewLoading_5.gif"; // 연금저축보험
				break;
			}
			// switch(strShowLoadingImgKey){
			// case "products_loading" : strExpImgUrl =
			// "/images/cont/products_loading.gif"; // 사망보장보험 - 정기보험
			// break;
			// case "products_loading_2" : strExpImgUrl =
			// "/images/cont/products_loading_2.gif"; // 연금보험
			// break;
			// case "products_loading_3" : strExpImgUrl =
			// "/images/cont/products_loading_3.gif"; // 실손의료비보험
			// break;
			// case "products_loading_4" : strExpImgUrl =
			// "/images/cont/products_loading_4.gif"; // 사망보장보험 - 종신보험
			// break;
			// case "products_loading_5" : strExpImgUrl =
			// "/images/cont/products_loading_5.gif"; // 연금저축보험
			// break;
			// }
		}
	}

	var loadingImg = new Image;
	var loadingImgBg = new Image;
	loadingImg.src = "/images/cmm/icon_viewLoading.gif";
	loadingImgBg.src = "/images/cmm/bg_loadingWrap.png";

	if (strExpImgUrl != "") {
		util_modalPage("/", {
			type : "loading",
			width : 480,
			height : 300,
			opacityBg : 0.3,
			loadType : true,
			loadingImgUrl : strExpImgUrl
		});
	} else {
		util_modalPage("/", {
			type : "loading",
			width : 240,
			height : 188,
			opacityBg : 0,
			loadingImgUrl : loadingImg.src
		});
		util_modalPage("/", {
			type : "loading",
			width : 240,
			height : 188,
			opacityBg : 0,
			loadingImgUrl : loadingImg.src
		});
	}

}

/**
 * 모달 로딩화면을 닫는다.
 */
function closeLoading() {
	$('#modal_loading_wrap', window.parent.document).remove();
	$('#modal_loading_back', window.parent.document).remove();
	$('#modal_loading_wrap').remove();
	$('#modal_loading_back').remove();
}

/**
 * 팝업닫기 호출함수(modal || window)
 * 
 * @param event객체(고정값)
 */
function gfn_closeModal(e) {
	if ($('#modal_ifrmWrap iframe', window.parent.document).length) {
		$(parent.modalLauncher).focus();
		parent.modalLauncher = null;
		$('#modal_ifrmWrap iframe', window.parent.document).attr('src', '');
		$('#modal_back', window.parent.document).remove();
		$('#modal_ifrmWrap', window.parent.document).remove();
	} else {
		$(window.parent).focus();
		window.close();
	}
	// e.preventDefault();
	// fn_preventDefaultHelper(e);
}

/* 팝업닫기에 해당하는 event.preventDefault 가 ie에 서 작동되지 않는점 보완한 함수 */
function fn_preventDefaultHelper(event) {
	var browerVersion = window.navigator.userAgent;
	if (browerVersion.indexOf('Mozilla/4.0') >= 0) {
		event.preventDefault();
	} else {
		event.returnValue = false;
	}
}

/**
 * 생년월일성별(8001011)을 가지고 외국인 및 성별을 가져 옵니다
 * 
 * dateString : 8001011 countryType : 내국인 (N), 외국인(F)
 */
function util_getGndrCd(dateString, countryType) {

	if ((dateString.length == 13) || (dateString.length == 7)) {

		// 현재년도
		var nowDate = util_getDate();
		var nowY2 = nowDate.substr(2, 2);
		var nowY1 = nowDate.substr(0, 2);
		var Y1 = "";
		var Y2 = dateString.substr(0, 2);

		/*
		 * 성별
		 */
		var GndrCd = dateString.substr(6, 1);

		if (Number(nowY2) < Number(Y2)) {
			Y1 = (Number(nowY1) - 1) + "";

		} else if (Number(nowY2) >= Number(Y2)) {
			Y1 = nowY1;
		}

		if ((countryType == undefined) || (countryType == null)
				|| (countryType == "") || (countryType == "N")) {// 내국인
			countryType = "N";

		} else {// 외국인
			countryType = "F";
		}

		if (countryType == "N") {

			if (Number(Y1) < 20) {

			} else {
				if (GndrCd == "1") {// 남자
					GndrCd = "3";
				} else if (GndrCd == "2") {// 여자
					GndrCd = "4";
				}
			}

		} else if (countryType == "F") {

			if (Number(Y1) < 20) {

				if (GndrCd == "1") {// 남자
					GndrCd = "5";
				} else if (GndrCd == "2") {// 여자
					GndrCd = "6";
				}

			} else {

				if (GndrCd == "1") {// 남자
					GndrCd = "7";
				} else if (GndrCd == "2") {// 여자
					GndrCd = "8";
				}

			}
		}

		// console.log(dateString+"<---GndrCd-->"+GndrCd+"---"+Y1);

		return GndrCd;
	} else {
		alert("형식이 잘못외었습니다.(예시(성별) : 8101011 또는 8101011234567)");

	}
}

function util_seBrithYear(dateString) {

	if ((dateString.length == 13) || (dateString.length == 7)
			|| (dateString.length == 6)) {
		// util_getGndrCd(dateString);
		/*
		 * var yy = dateString.substr(0,2);
		 * 
		 * var sex =dateString.substr(6,1);
		 * 
		 * if((sex=="1")||(sex=="2")||(sex=="5")||(sex=="6")){ dateString = "19" +
		 * dateString.substring(0,6); }else
		 * if((sex=="3")||(sex=="4")||(sex=="7")||(sex=="8")){ dateString = "20" +
		 * dateString.substring(0,6); }
		 */

		// 현재년도
		var nowDate = util_getDate();
		var nowY2 = nowDate.substr(2, 2);
		var nowY1 = nowDate.substr(0, 2);
		var Y1 = "";
		var Y2 = dateString.substr(0, 2);

		if (Number(nowY2) < Number(Y2)) {
			Y1 = (Number(nowY1) - 1) + "";

		} else if (Number(nowY2) >= Number(Y2)) {
			Y1 = nowY1;
		}

		dateString = Y1 + dateString.substring(0, 6);

		return dateString;

	} else {
		alert("형식이 잘못외었습니다.(예시(생일) : 8101011 또는 8101011234567)");

	}
}

/**
 * 만나이를 계산합니다 보험 가입이 만 19세부터 가입이 가능해서 보험나이가 19세이더라도 가입이 불가능 해서 에러가 발생하는 부분이 있어
 * 해당 로직을 추가해 보험금 확인을 불가하게 만들려고 추가합니다 dateString : 8902021(생년월일+성별)
 */

function util_getRealAge(dateString) {

	if (util_isJuminno(dateString.substring(0, 6), 1) == false) {
		return false;
	}

	// 기준일자
	var sdDate = new Date(util_setFmDate(util_getDate(), "/"));

	if ((dateString.length == 13) || (dateString.length == 7)
			|| (dateString.length == 6)) {

		dateString = util_seBrithYear(dateString);
	}

	var jmDate = new Date(util_setFmDate(dateString, "/"));

	var age = sdDate.getFullYear() - jmDate.getFullYear();

	if (sdDate.getMonth() < jmDate.getMonth())
		age--;
	if ((sdDate.getMonth() == jmDate.getMonth())
			&& (sdDate.getDate() < jmDate.getDate()))
		age--;

	return age;
}

/*
 * XSS 변경문자 치환 코드값 -> 문자열
 */

function convertHtmlCharToSpChar(str) {
	if ((str == null) || (str == ""))
		return "";

	var ret = str;
	ret = util_replaceAll(ret, "(&amp;)", "&");
	ret = util_replaceAll(ret, "&lt;", "<");
	ret = util_replaceAll(ret, "&gt;", ">");
	ret = util_replaceAll(ret, "&#39;", "\'");
	ret = util_replaceAll(ret, "&#34;", "\"");
	ret = util_replaceAll(ret, "&#35;", "#");
	ret = util_replaceAll(ret, "&#40;", "(");
	ret = util_replaceAll(ret, "&#41;", ")");
	ret = util_replaceAll(ret, "&quot;", "\"");

	return ret;
}

/**
 * 브라우저 뒤로가기 버튼막기
 * 
 * @author 김상종
 */
function changeHashOnLoad() {

	if (typeof history.pushState === "function") {
		history.pushState("jibberish", null, null);
		window.onpopstate = function() {
			history.pushState('newjibberish', null, null);
			// Handle the back (or forward) buttons here
			// Will NOT handle refresh, use onbeforeunload for this.
		};
	} else {
		var ignoreHashChange = true;
		window.onhashchange = function() {
			if (!ignoreHashChange) {
				ignoreHashChange = true;
				window.location.hash = Math.random();
				// Detect and redirect change here
				// Works in older FF and IE9
				// * it does mess with your hash symbol (anchor?) pound sign
				// delimiter on the end of the URL
			} else {
				ignoreHashChange = false;
			}
		};
	}

	// window.history.forward(0); // 임시
	// history.forward();

	// window.location.href += "#";
}

/**
 * 특수문자 검사 (예외: 괄호('('), 괄호(')'), 콤마(,), 공백, 슬러시(/), 쩜(.))
 */
function chkSpecialCharaters(val, msgFlag) {
	var result = true;
	var regexp = /[\{\}\[\]\?;:|*~`!^\-_+┼<>@\#$%&\'\"\\\=]/gi;
	if (regexp.test(val)) {
		var flag = false;
		if(util_chkReturn(msgFlag, "s") != ""){
			if(msgFlag) flag =  true; 
		}
		
		if(!flag) alert("특수문자는 입력하실수 없습니다.");
		result = false;
	}
	return result;
}

/**
 * 한글(공백포함)만 입력되었는지 체크
 * 
 * @param str
 */
function util_isKorWithSpace(str) {
	if (/[^ㄱ-ㅎㅏ-ㅣ가-힣\\\s]/.test(str)) {
		return false;
	}
	return true;
}

/*
 * 모질라 계열 keyup, keydown, keypress 안먹음 이벤트 처리 할려는 input name 명을 아래와 같이 선언할것 var
 * keyFix = new beta.fix('input name 명');
 */

if (typeof (beta) == "undefined") {
	_beta = beta = {};
}
if (typeof (_beta.fix) == "undefined") {
	_beta.fix = {};
} else {
	// alert("keyfix is already set!");
}

if (typeof (window.beta.instances) == "undefined")
	window.beta.instances = new Array();

_beta.fix = function(targetId) {
	// this fix is only for mozilla browsers
	if (jQuery.browser.mozilla == false)
		return false;

	var thisClass = this;
	this.keyEventCheck = null;
	this.db = null;
	this.targetId = targetId;
	window.beta.instances[this.targetId] = this;

	var focusFunc = function() {
		if (!thisClass.keyEventCheck)
			thisClass.watchInput();
	};

	var blurFunc = function() {
		if (thisClass.keyEventCheck) {
			window.clearInterval(thisClass.keyEventCheck);
			thisClass.keyEventCheck = null;
		}
	};

	$("#" + this.targetId).bind("focus", focusFunc);
	$("#" + this.targetId).bind("blur", blurFunc);
};

_beta.fix.prototype.watchInput = function() {
	if (this.db != $("#" + this.targetId).val()) {
		// trigger event
		$("#" + this.targetId).trigger('keyup');
	}
	this.db = $("#" + this.targetId).val();

	if (this.keyEventCheck)
		window.clearInterval(this.keyEventCheck);
	this.keyEventCheck = window.setInterval("window.beta.instances['"
			+ this.targetId + "'].watchInput()", 100);
};

/**
 * 설명 : 정렬 파라미터를 셋팅합니다. 사용방식 : gfn_setListOrderParam(해당 컬럼명) 주의 : 해당 컬럼명을 변환 시
 * 컬럼과 동일해야 합니다. 리턴 : 없음
 */
function util_setListOrderParam(colName) {

	// var listOrderButtonClickFlag = $("#listOrderButtonClickFlag").val();
	// if( listOrderButtonClickFlag == 'N' || listOrderButtonClickFlag == '' ||
	// listOrderButtonClickFlag == 'undefined'){
	// $("#listOrder").val( colName + " DESC" );
	// return;
	// }

	// alert('colName='+colName);

	// 정렬클래스작업
	// var listOrderArr = $("#listOrder").val().split(" ");
	// var tmpListOrderNm = '';
	// $.each(listOrderArr[0].split("_"), function(idx,data){
	// tmpListOrderNm += data;
	// });
	//	
	// var colName2 = colName.toUpperCase();
	// var tmpListOrderNm2 = tmpListOrderNm.toUpperCase();
	// var tmpListOrderNm2Arr = tmpListOrderNm2.split(".");
	// if(tmpListOrderNm2Arr.length == 2){
	// tmpListOrderNm2 = tmpListOrderNm2Arr[1];
	// }
	// 클릭한 콜네임과 클릭되어져있는 버튼과 일치하지 않는 경우
	// if(colName2 != tmpListOrderNm2){
	// $("#listOrder").val( colName + " DESC" );
	// }else{
	if ($("#listOrder")) {
		if ($("#listOrder").val() == "" || $("#listOrder").val().match("ASC")) {
			$("#listOrder").val(colName + " DESC");
		} else if ($("#listOrder").val().match("DESC")) {
			$("#listOrder").val(colName + " ASC");
		} else {
			$("#listOrder").val(colName + " DESC");
		}
	} else {
		alert("정렬에 필요한 속성이 없습니다. 관리자에게 문의하세요.");
		return;
	}
	// }
}

/**
 * 설명 : 년도 사용방식 : gfn_getDataListYear(Object option) 매개변수1 : option.term :
 * 년도표기년수 ex) 100년 >>> option.term = 100 매개변수2 : option.term2 : 현재년도기준 년도삭제년수
 * ex) 2016년 -10년 = 2006년 >>> option.term2 = 10 리턴 : array 호출샘플 : var objParam =
 * new Object(); objParam.term = 100; objParam.term2 = 10; var yyyy_arr =
 * gfn_getDataListYear(objParam); $.each(yyyy_arr, function(idx,data){
 * $("#customerBirthDayYYYY").append("<option
 * value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>"); });
 * 
 */
function gfn_getDataListYear(option) {
	option = option == undefined ? {} : option;
	var term = option.term == undefined ? 10 : Number(option.term);
	var term2 = option.term2 == undefined ? 10 : Number(option.term2);
	var unit = option.unit == undefined ? "" : option.unit;
	var date = util_getDate();
	var dateFullYear = Number(date.substring(0, 4));
	dateFullYear = dateFullYear - term2;
	var dateCode = new Array();
	for (var i = 0; i < term; i++) {
		var year = dateFullYear - i;
		dateCode[i] = {
			'cmnnCd' : year,
			'cmnnCdHanNm' : year + unit
		};
	}
	return dateCode;
}

/**
 * 설명 : 월 사용방식 : gfn_getDataListMonth() 주의 : 리턴 : array 호출샘플 : var mm_arr =
 * gfn_getDataListMonth(); $.each(mm_arr, function(idx,data){
 * $("#customerBirthDayMM").append("<option
 * value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>"); });
 */
function gfn_getDataListMonth() {
	var dateCode = new Array();
	for (var i = 0; i < 12; i++) {
		var ii = i + 1;
		if (ii < 10) {
			ii = '0' + ii;
		}
		dateCode[i] = {
			'cmnnCd' : ii,
			'cmnnCdHanNm' : ii
		};
	}
	return dateCode;
}

/**
 * 설명 : 일 사용방식 : gfn_getDataListDay() 주의 : 리턴 : array 호출샘플 : var dd_arr = gfn_
 * getDataListDay(); $.each(dd_arr, function(idx,data){
 * $("#customerBirthDayDD").append("<option
 * value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>"); });
 */
function gfn_getDataListDay() {
	var dateCode = new Array();
	for (var i = 0; i < 31; i++) {
		var ii = i + 1;
		if (ii < 10) {
			ii = '0' + ii;
		}
		dateCode[i] = {
			'cmnnCd' : ii,
			'cmnnCdHanNm' : ii
		};
	}
	return dateCode;
}