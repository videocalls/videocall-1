package kr.co.koscom.oppf.cmm.util;

public class CodeConstants {
	/**기업 핀테크 서비스 타입 : ALL - com_company_profile /  company_service_type */
	public static final String COMPANY_TYPE_ALL = "G002001";   
	/**기업 핀테크 서비스 타입 : 금투사 - com_company_profile /  company_service_type */
	public static final String COMPANY_TYPE_FSC = "G002002";    
	/**기업 핀테크 서비스 타입 : 핀테크기업 - com_company_profile /  company_service_type */
	public static final String COMPANY_TYPE_FINTECH = "G002003";    
		
	/**회원 가입 상태 : 미활성화 - spt_customer_info_profile / customer_reg_status */
	public static final String MEMBER_STATUS_NOACTIVE = "G005001";    
	/**회원 가입 상태 : 활성화 - spt_customer_info_profile / customer_reg_status */
	public static final String MEMBER_STATUS_ACTIVE = "G005002";    
	/**회원 가입 상태 : 정지 - spt_customer_info_profile / customer_reg_status */
	public static final String MEMBER_STATUS_STOP = "G005003";    
	/**회원 가입 상태 : 탈퇴 - spt_customer_info_profile / customer_reg_status */
	public static final String MEMBER_STATUS_QUIT = "G005004";    
	
	/** 회원 검증 : 본인인증 - spt_customer_verify_profile / customer_verify_type */
	public static final String VERIFY_TYPE_CI = "G007001";	 
	/** 회원 검증 : 공인인증서 - spt_customer_verify_profile / customer_verify_type */
	public static final String VERIFY_TYPE_DN = "G007002";	 
	
	/** 가상계좌 상태 : 미사용 - spt_customer_account_profile / customer_vtaccount_status */
	public static final String VTACCOUNT_STATUS_UNUSE  = "G009001";   
	/** 가상계좌 상태 : 사용 - spt_customer_account_profile / customer_vtaccount_status */
	public static final String VTACCOUNT_STATUS_USE = "G009002";    
	/** 가상계좌 상태 : 폐기 - spt_customer_account_profile / customer_vtaccount_status*/
	public static final String VTACCOUNT_STATUS_DISCARD = "G009003";  
	
	/** 메일발송 상태 : 데이터 연동 성공 - com_email_send_info / send_result, re_send_result*/
	public static final String Mail_SEND_001 = "G021001";
	/** 메일발송 상태 : 필수 파라미터 미입력, 데이터 값 오류 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_002 = "G021002";
    /** 메일발송 상태 : 서버 오류 서버 오류 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_003 = "G021003";
    /** 메일발송 상태 : 서버 통신 오류 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_004 = "G021004";
    /** 메일발송 상태 : 접근IP 오류 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_005 = "G021005";
    /** 메일발송 상태 : 포인트 확인 실패 또는 포인트 없음 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_006 = "G021006";
    /** 메일발송 상태 : 포인트 적용 실패 - com_email_send_info / send_result, re_send_result*/
    public static final String Mail_SEND_007 = "G021007";

	/** API 에러 오류 카테고리 */
	public static final String OP_SPT= "op-spt";

	/** 동의서파일종류 : 원본 */
	public static final String TERMS_FILE_TYPE_001 = "G027001";

	/** 동의서파일종류 : 전자서명파일 */
	public static final String TERMS_FILE_TYPE_002 = "G027002";

	/** 동의서파일종류 : TSA원본 */
	public static final String TERMS_FILE_TYPE_003 = "G027003";

	/** 동의서파일종류 : TSA적용 */
	public static final String TERMS_FILE_TYPE_004 = "G027004";
	
	/** 성별 */
	public static final String MEMBER_SEX_MALE = "G012001";
	
	/** 성별 */
	public static final String MEMBER_SEX_FEMALE = "G012002";
	public static final String TEST_PROFILE_MESSAGE = "본 동의서는 테스트용입니다.";
}

   