package kr.co.koscom.oppf.spt.direct.common.util;

/**
 * CommonCodeConstants
 * <p>
 * Created by chungyeol.kim on 2017-05-25.
 */
public class CommonCodeConstants {

    public static final String TERMS_AUTH_TYPE_SIGN = "G032001"; // 인증타입 : 전자서명
    public static final String TERMS_AUTH_TYPE_ARS = "G032002";  // 인증타입 : ARS 인증
    public static final String TERMS_FILE_TYPE_ORIGINAL = "G027001";    // 파일타입 : 원본
    public static final String TERMS_FILE_TYPE_SIGN = "G027002";        // 파일타입 : 서명+원본
    public static final String TERMS_FILE_TYPE_TSA_ORIGINAL = "G027003";// 파일타입 : TSA 원본
    public static final String TERMS_FILE_TYPE_TSA = "G027004";         // 파일타입 : TSA 적용


    public static final String VERIFY_TYPE_CI = "G007001";      //VerifyType : CI
    public static final String VERIFY_TYPE_DN = "G007002";      //VerifyType : DN
        

    public static final String TERMS_TYPE_SERVICE = "G008001";  // 회원 동의 종류 : 서비스 이용 약관
    public static final String TERMS_TYPE_INDIVIDUAL = "G008002";  // 회원 동의 종류 : 개인정보 수집 및 이용동의
    public static final String TERMS_TYPE_THIRD = "G008003";  // 회원 동의 종류 : 개인정보 제 3자 제공 동의
    public static final String TERMS_TYPE_FINANCE = "G008020";  // 회원 동의 종류 : 금융정보제공동의서
    
    public static final String TERMS_TYPE_COMPANY_SERVICE = "G031001";  // 기업 동의 종류 : 서비스 이용 약관
    

    public static final String GRP_TYPE_ALL = "G003001";  // 그룹노출구분 : 전체
    public static final String GRP_TYPE_SVC = "G003002";  // 그룹노출구분 : 일반사용자
    public static final String GRP_TYPE_COMP = "G003003";  // 그룹노출구분 : 기업사용자
    
    public static final String MBR_JOIN_TYPE_W = "G039001";  // 회원 가입 경로 : 웹 사용자
    public static final String MBR_JOIN_TYPE_M = "G039002";  // 회원 가입 경로 : 모바일 사용자
    public static final String MBR_JOIN_TYPE_WM = "G039003";  // 회원 가입 경로 : 웹 모바일 사용자
    public static final String MBR_JOIN_TYPE_MW = "G039004";  // 회원 가입 경로 : 모바일 웹 사용자
    public static final String MBR_JOIN_TYPE_API = "G039005";  // 회원 가입 경로 : B2B API 사용자
    
    public static final String MBR_REG_STATUS_DEACTIVE = "G005001";     // 회원가입 상태 : 미활성화
    public static final String MBR_REG_STATUS_ACTIVE = "G005002";     // 회원가입 상태 : 활성화
    public static final String MBR_REG_STATUS_SUSPEND = "G005003";     // 회원가입 상태 : 정지
    public static final String MBR_REG_STATUS_WITHDRAW = "G005004";     // 회원가입 상태 : 탈퇴

    public static final String MBR_REG_STEP_VERIFIED = "G006001";     // 회원가입 Step : 본인인증
    public static final String MBR_REG_STEP_CERT_REGISTERED = "G006002";     // 회원가입 Step : 공인인증서등록
    public static final String MBR_REG_STEP_TERMS_AGREED = "G006003";     // 회원가입 Step : 약관동이
    public static final String MBR_REG_STEP_INFO_REGISTERED = "G006004";     // 회원가입 Step : 개인정보입력
    public static final String MBR_REG_STEP_EMAIL_VERIFIED = "G006005";     // 회원가입 Step : 이메일인증

    
    public static final String MBR_WITHDRAW_STATUS = "G005004";  // 
    
}
