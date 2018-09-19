/**
 * @Name  : string.js
 * @Description : string 관련 prototype 공통 셋팅
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
 * 설명 		: replace all
 * 사용방식 	: str.replaceAll('변환할대상 문자', '변경해야할 문자', '변경해야할 문자')
 * 주의 		: 없음
 * 리턴 		: 없음
 */
String.prototype.replaceAll = function(oldStr, newStr){
	var str = this;
	return str.split(oldStr).join(newStr);
};

/**
 * 설명 		: 정규식에 쓰이는 특수문자를 찾아서 이스케이프 한다.
 * 사용방식 	: pattern : 변경할 문자셋
 * 주의 		: 없음
 * 리턴 		: 특수문자를 없앤 문자
 */
String.prototype.remove = function(pattern) {
		return (pattern == null) ? this : eval("this.replace(/[" + pattern.meta() + "]/g, \"\")");
};

/**
 * 설명 		: 숫자로 구성되어 있는지 학인
 * 사용방식 	: arguments[0] : 허용할 문자셋
 * 주의 		: 없음
 * 리턴 		: true : 숫자, false : 숫자아님
 */
String.prototype.isNum = function() {
	return (/^[0-9]+$/).test(this.remove(arguments[0])) ? true : false;
};


/**
 * 설명 		: 핸드폰번호가 유효한지 여부
 * 사용방식 	: arguments[0] : 허용할 문자셋
 * 주의 		: 없음
 * 리턴 		: true : 유효, false : 불유효
 */
String.prototype.isMobileNumber = function() {
		var arg = arguments[0] ? arguments[0] : "";
		return eval("(/01[016789]" + arg + "[1-9]{1}[0-9]{2,3}" + arg + "[0-9]{4}$/).test(this)");
};