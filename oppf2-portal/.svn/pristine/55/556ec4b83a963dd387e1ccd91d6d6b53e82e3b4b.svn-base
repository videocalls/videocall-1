package kr.co.koscom.oppf.cmm.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiExceptionMessage {

	// 이하는 금투사 리턴 코드 respCode
	/** 정상적으로 처리 했으나, 해당계좌가 없음  "No Content" */
	public static final int NOT_FOUND_ACCOUNT = 204;
	/** 정상적으로 처리 했으나, Dn과 일치하는 개인 없음 */
	public static final int NOT_FOUND_DN_EXTERNAL = 205;
	/** 요청 메시지 오류 */
	public static final int BAD_REQUEST = 400;
	/** 권한 오류, 인증실패, 사용불가한 API의 호출 */
	public static final int UNAUTHORIZED = 401;
	/** 처리시간 초과 */
	public static final int REQUEST_TIMEOUT = 408;
	/** 처리중 에러 발생 */
	public static final int INTERNAL_SERVER_ERROR = 500;
	/** 구현되지 않은 요처을 시도 */
	public static final int NOT_IMPLEMENTED = 501;
	/** 일시적 장애 또는 점검중으로 서비스를 할 수 없는 경우 */
	public static final int SERVICE_UNAVAILABLE = 503;
	/** 기타 오류 */
	public static final int ETC_ERROR = 999;

	////// 맵핑 작업 중 에러
	/** BALANCE 맵핑 에러 */
	public static final int ERR_MAPPING_BALANCE = 1501;
	/** INTEREST 맵핑 에러 */
	public static final int ERR_MAPPING_INTEREST = 1502;
	/** PORTFOLIO 맵핑 에러*/
	public static final int ERR_MAPPING_PORTFOLIO = 1503;
	/** TRANSACTION 맵핑 에러 */
	public static final int ERR_MAPPING_TRANSACTION = 1504;

	/** 프로그램 내부 에러(ex : DB에러) 가 발생했을 경우 */
	public static final String EX_UNKOWN = "3999";

	/** 요청한 금투사 api 연결정보를 찾을수 없는 경우 */
	public static final String EX_UNKOWN_APIKEY = "3002";

	/** 필수 항목이 누락된경우 */
	public static final String EX_REQUIRED_CHECK = "3003";

	/** 일시적 network 장애가 발생한 경우 */
	public static final String EX_SEND_CHECK = "3004";

	/** 요청한 ci로 사용자 dn값을 찾을 수 없을 경우 (DB조회)*/
	public static final String EX_NOT_FOUND_DN = "3006";

	/** 요청한 CI로 사용자를 찾을 수 없을 경우 (DB조회)*/
	public static final String EX_NOT_FOUND_USER = "3007";

	/** 요청한 금투사의 정보를 찾을 수 없을 경우 (DB조회)*/
	public static final String EX_NOT_FOUND_COMINFO = "3008";

	/** 실계좌번호를 찾을 수 없을 경우 */
	public static final String EX_NOT_FOUND_REALACCOUNT = "3009";

	/** 요청계좌가 폐기된 계좌일 경우 */
	public static final String EX_ACCOUNT_IS_EXPIRE = "3010";

	/** Json 메시지 규격에 오류가 있을 경우 */
	public static final String EX_JSON_PARSING = "3011";

	/** 요청계좌가 증권사에 존재하지 않을 경우 */
	public static final String ERR_UNKNOWN_ACNT = "3012";

	/** 등록되지 않은 계좌 유형의 경우(공통코드 G010에 등로되지 않은 code_name_eng이다.) */
	public static final String ERR_UNKNOWN_ACNT_TYPE = "3013";

	/** 요청한 dn값으로 사용자 ci값을 찾을 수 없을 경우 (DB조회)*/
	public static final String EX_NOT_FOUND_CI = "3014";

	/** 요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 (API 계약 해지, 증권사 계약해지) */
	public static final String EX_NOT_LINK_ACCOUNT = "3015";

	private String category;
	private int code;
	private String msg;
	private String message;
	private String description;
	
	public ApiExceptionMessage(String category, String code, String msg, String description) {
		this.category = category;
		this.code = Integer.parseInt(code);
		this.msg = msg;
		this.message = msg;
		this.description = description;
	}

	@JsonProperty("code")
	public int getCode() {
		return code;
	}
	@JsonProperty("message")
	public String getMsg() {
		return msg;
	}
	public String getMessage() {
		return message;
	}
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

}
