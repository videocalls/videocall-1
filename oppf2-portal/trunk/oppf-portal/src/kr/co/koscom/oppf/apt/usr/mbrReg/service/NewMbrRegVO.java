package kr.co.koscom.oppf.apt.usr.mbrReg.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegVO.java
* @Comment  : [회원가입]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class NewMbrRegVO extends ComDefaultListVO{
    
    /* [기본정보]관련 일반회원프로파일(spt_customer_info_profile)테이블 */
    private String customerRegNo="";              //회원 OP 등록 번호( 일반사용자 : C+YYYYMMDD+seq(3), 기업 : O+YYYYMMDD+seq(3), admin : A+YYYYMMDD+seq(3) )
    private String customerRegNoPrefix="C";       //회원 OP 등록 번호 prefix( 일반사용자 : C, 기업 : O, admin : A )
    private String customerId="";                 //회원 아이디
    private String customerPassword="";           //회원 비밀번호
    private String customerTempPasswordYn="N";    //회원 비밀번호 임시여부
    private String customerChgPasswordDate="";    //회원 비밀번호 변경일자
    private String customerPasswordFailCnt="0";   //비밀번호 실패횟수
    private String customerRegStatus="G005002";   //회원 가입 상태[G005(001:미활성화, 002:활성화, 003:정지, 004: 탈퇴)]
    private String customerStep="G006005";        //회원 가입 Step[G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)]
    private String customerNameKor="";            //회원명 한글
    private String customerNameEng="";            //회원명 영문
    private String customerPhone="";              //회원 휴대폰 번호
    private String customerEmail="";              //회원 이메일 주소
    private String customerEmailAuthYn="Y";       //회원 이메일 인증여부
    private String customerEmailRegDate="";       //회원 이메일 인증일시
    private String customerExpirePasswordDate=""; //비밀번호 변경 예정일
    private String customerFinalPasswordDate="";  //비밀번호 변경 최종 예정일
    private String customerBirthDay="";           //생년월일
    private String customerSex="G012001";             //성별코드[G012(001:남자, 002:여자)]
    private String customerSexName="";            //성별이름
    private String customerEmailReceiveYn="";    //이메일 수신동의여부
    private String customerEmailReceiveDate="";   //이메일 수신동의 여부일시
    private String customerRegDate="";            //회원가입일시
    
    
    /* [본인인증]관련 일반회원검증(spt_customer_verify_profile)테이블 */
    private String ciCustomerVerifyStatus="";   	//본인인증 상태동의여부
    private String ciCustomerVerify="";				//본인인증값
    private String dnCustomerVerifyStatus="";       //공인인증서등록상태 동의여부
    private String dnCustomerVerify="";				//공인인증서값
    
    private String customerVerifyType=""; //회원검증종류[G007(001:본인인증, 002:공인인증서)]
    private String customerVerify="";            //회원검증값
    private String customerVerifyDate;           //회원검증일
    
    /* [약관]관련 일반회원약관동의(spt_customer_terms_profile)테이블 */
    private List<NewMbrRegTermsVO> customerTermsList;
    private List<String> customerTermsTypeList;
    private List<String> customerTermsAuthYnList;
    
    /* 아이핀 인증 관련 */
    private int iRtn;                             //아이핀 fnRequest 메소드 결과값
    private String sRtnMsg="";                    //아이핀 처리결과 메세지
    private String sEncData="";                   //아이핀 암호화된 데이타
    private String domainSpt="";                  //ipin_process로 리턴할 URL Globals.domain.spt값
    
    /* 휴대폰 인증(안심본인인증) 관련 */
    private String sMessage="";                   //휴대폰인증 init 결과 메시지
    private String sEncDataMobile="";             //휴대폰인증 init 암호화된 데이터
    
    /* 이메일 발송 관련 */
    private String mailCode ="";                  //이메일발송 리턴코드
    private String mailMsg  ="";                  //이메일발송 리턴메시지
    
    private String customerPasswordYn ="";        //DB암호화에 따른 비밀번호값 비교 vo
        
    /* 스마트 OTP 관련 */
    private String customerOtpYn = "";
    private String customerOtpId = "";
    
    //DN 정보
    private String customerVerifyDn;
    
    private String serviceTerms;				// 서비스 이용약관
    private String indTerms;					// 개인정보수집 및 이용동의
    private String thirdProvideTerms;			// 개인정보 제3자 제공 동의
    private String certiTerms;					// 공인인증서 등록 약관동의

    // 2.0 추가
    private String integrationAccountYn;		// 통합계좌사용여부
    private String temporaryMemberYn;			// 임시 회원 여부
    private String customerJoinType;			// 회원 가입 경로
    private String customerWithdrawalProcYn;	// 회원 탈퇴 처리 여부
    private String customerDatasetLockYn;		// 데이터셋 잠금 여부
    private String customerMobilePushYn;		// 모바일 push 수신 여부
    
    
    
    private String searchAppId;					// AppId

	private String searchCustomerRegNo;
	private String searchServiceRegNo;
	
	
    
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
    public String getCustomerRegNoPrefix() {
        return customerRegNoPrefix;
    }
    public void setCustomerRegNoPrefix(String customerRegNoPrefix) {
        this.customerRegNoPrefix = customerRegNoPrefix;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerPassword() {
        return customerPassword;
    }
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
    public String getCustomerTempPasswordYn() {
        return customerTempPasswordYn;
    }
    public void setCustomerTempPasswordYn(String customerTempPasswordYn) {
        this.customerTempPasswordYn = customerTempPasswordYn;
    }
    public String getCustomerChgPasswordDate() {
        return customerChgPasswordDate;
    }
    public void setCustomerChgPasswordDate(String customerChgPasswordDate) {
        this.customerChgPasswordDate = customerChgPasswordDate;
    }
    public String getCustomerPasswordFailCnt() {
        return customerPasswordFailCnt;
    }
    public void setCustomerPasswordFailCnt(String customerPasswordFailCnt) {
        this.customerPasswordFailCnt = customerPasswordFailCnt;
    }
    public String getCustomerRegStatus() {
        return customerRegStatus;
    }
    public void setCustomerRegStatus(String customerRegStatus) {
        this.customerRegStatus = customerRegStatus;
    }
    public String getCustomerStep() {
        return customerStep;
    }
    public void setCustomerStep(String customerStep) {
        this.customerStep = customerStep;
    }
    public String getCustomerNameKor() {
        return customerNameKor;
    }
    public void setCustomerNameKor(String customerNameKor) {
        this.customerNameKor = customerNameKor;
    }
    public String getCustomerNameEng() {
        return customerNameEng;
    }
    public void setCustomerNameEng(String customerNameEng) {
        this.customerNameEng = customerNameEng;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCustomerEmailAuthYn() {
        return customerEmailAuthYn;
    }
    public void setCustomerEmailAuthYn(String customerEmailAuthYn) {
        this.customerEmailAuthYn = customerEmailAuthYn;
    }
    public String getCustomerEmailRegDate() {
        return customerEmailRegDate;
    }
    public void setCustomerEmailRegDate(String customerEmailRegDate) {
        this.customerEmailRegDate = customerEmailRegDate;
    }
    public String getCustomerExpirePasswordDate() {
        return customerExpirePasswordDate;
    }
    public void setCustomerExpirePasswordDate(String customerExpirePasswordDate) {
        this.customerExpirePasswordDate = customerExpirePasswordDate;
    }
    public String getCustomerFinalPasswordDate() {
        return customerFinalPasswordDate;
    }
    public void setCustomerFinalPasswordDate(String customerFinalPasswordDate) {
        this.customerFinalPasswordDate = customerFinalPasswordDate;
    }
    public String getCustomerBirthDay() {
        return customerBirthDay;
    }
    public void setCustomerBirthDay(String customerBirthDay) {
        this.customerBirthDay = customerBirthDay;
    }
    public String getCustomerSex() {
        return customerSex;
    }
    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }
    public String getCustomerSexName() {
        return customerSexName;
    }
    public void setCustomerSexName(String customerSexName) {
        this.customerSexName = customerSexName;
    }
    public String getCustomerEmailReceiveYn() {
        return customerEmailReceiveYn;
    }
    public void setCustomerEmailReceiveYn(String customerEmailReceiveYn) {
        this.customerEmailReceiveYn = customerEmailReceiveYn;
    }
    public String getCustomerEmailReceiveDate() {
        return customerEmailReceiveDate;
    }
    public void setCustomerEmailReceiveDate(String customerEmailReceiveDate) {
        this.customerEmailReceiveDate = customerEmailReceiveDate;
    }
    public String getCustomerRegDate() {
        return customerRegDate;
    }
    public void setCustomerRegDate(String customerRegDate) {
        this.customerRegDate = customerRegDate;
    }
    public String getCustomerVerifyType() {
        return customerVerifyType;
    }
    public void setCustomerVerifyType(String customerVerifyType) {
        this.customerVerifyType = customerVerifyType;
    }
    public String getCustomerVerify() {
        return customerVerify;
    }
    public void setCustomerVerify(String customerVerify) {
        this.customerVerify = customerVerify;
    }
    public String getCustomerVerifyDate() {
        return customerVerifyDate;
    }
    public void setCustomerVerifyDate(String customerVerifyDate) {
        this.customerVerifyDate = customerVerifyDate;
    }
    public List<NewMbrRegTermsVO> getCustomerTermsList() {
        return customerTermsList;
    }
    public void setCustomerTermsList(List<NewMbrRegTermsVO> customerTermsList) {
        this.customerTermsList = customerTermsList;
    }
    public List<String> getCustomerTermsTypeList() {
        return customerTermsTypeList;
    }
    public void setCustomerTermsTypeList(List<String> customerTermsTypeList) {
        this.customerTermsTypeList = customerTermsTypeList;
    }
    public List<String> getCustomerTermsAuthYnList() {
        return customerTermsAuthYnList;
    }
    public void setCustomerTermsAuthYnList(List<String> customerTermsAuthYnList) {
        this.customerTermsAuthYnList = customerTermsAuthYnList;
    }
    public int getiRtn() {
        return iRtn;
    }
    public void setiRtn(int iRtn) {
        this.iRtn = iRtn;
    }
    public String getsRtnMsg() {
        return sRtnMsg;
    }
    public void setsRtnMsg(String sRtnMsg) {
        this.sRtnMsg = sRtnMsg;
    }
    public String getsEncData() {
        return sEncData;
    }
    public void setsEncData(String sEncData) {
        this.sEncData = sEncData;
    }
    public String getDomainSpt() {
        return domainSpt;
    }
    public void setDomainSpt(String domainSpt) {
        this.domainSpt = domainSpt;
    }
    public String getsMessage() {
        return sMessage;
    }
    public void setsMessage(String sMessage) {
        this.sMessage = sMessage;
    }
    public String getsEncDataMobile() {
        return sEncDataMobile;
    }
    public void setsEncDataMobile(String sEncDataMobile) {
        this.sEncDataMobile = sEncDataMobile;
    }
    public String getMailCode() {
        return mailCode;
    }
    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }
    public String getMailMsg() {
        return mailMsg;
    }
    public void setMailMsg(String mailMsg) {
        this.mailMsg = mailMsg;
    }
    public String getCustomerPasswordYn() {
        return customerPasswordYn;
    }
    public void setCustomerPasswordYn(String customerPasswordYn) {
        this.customerPasswordYn = customerPasswordYn;
    }
	public String getCustomerOtpYn() {
		return customerOtpYn;
	}
	public void setCustomerOtpYn(String customerOtpYn) {
		this.customerOtpYn = customerOtpYn;
	}
	public String getCustomerOtpId() {
		return customerOtpId;
	}
	public void setCustomerOtpId(String customerOtpId) {
		this.customerOtpId = customerOtpId;
	}
	public String getCustomerVerifyDn() {
		return customerVerifyDn;
	}
	public void setCustomerVerifyDn(String customerVerifyDn) {
		this.customerVerifyDn = customerVerifyDn;
	}
	public String getCiCustomerVerifyStatus() {
		return ciCustomerVerifyStatus;
	}
	public void setCiCustomerVerifyStatus(String ciCustomerVerifyStatus) {
		this.ciCustomerVerifyStatus = ciCustomerVerifyStatus;
	}
	public String getCiCustomerVerify() {
		return ciCustomerVerify;
	}
	public void setCiCustomerVerify(String ciCustomerVerify) {
		this.ciCustomerVerify = ciCustomerVerify;
	}	
	public String getDnCustomerVerifyStatus() {
		return dnCustomerVerifyStatus;
	}
	public void setDnCustomerVerifyStatus(String dnCustomerVerifyStatus) {
		this.dnCustomerVerifyStatus = dnCustomerVerifyStatus;
	}
	public String getDnCustomerVerify() {
		return dnCustomerVerify;
	}
	public void setDnCustomerVerify(String dnCustomerVerify) {
		this.dnCustomerVerify = dnCustomerVerify;
	}
	public String getServiceTerms() {
		return serviceTerms;
	}
	public void setServiceTerms(String serviceTerms) {
		this.serviceTerms = serviceTerms;
	}
	public String getThirdProvideTerms() {
		return thirdProvideTerms;
	}
	public void setThirdProvideTerms(String thirdProvideTerms) {
		this.thirdProvideTerms = thirdProvideTerms;
	}
	public String getIndTerms() {
		return indTerms;
	}
	public void setIndTerms(String indTerms) {
		this.indTerms = indTerms;
	}
	public String getCertiTerms() {
		return certiTerms;
	}
	public void setCertiTerms(String certiTerms) {
		this.certiTerms = certiTerms;
	}
	public String getSearchAppId() {
		return searchAppId;
	}
	public void setSearchAppId(String searchAppId) {
		this.searchAppId = searchAppId;
	}
	public String getSearchServiceRegNo() {
		return searchServiceRegNo;
	}
	public void setSearchServiceRegNo(String searchServiceRegNo) {
		this.searchServiceRegNo = searchServiceRegNo;
	}
	public String getSearchCustomerRegNo() {
		return searchCustomerRegNo;
	}
	public void setSearchCustomerRegNo(String searchCustomerRegNo) {
		this.searchCustomerRegNo = searchCustomerRegNo;
	}
	public String getCustomerDatasetLockYn() {
		return customerDatasetLockYn;
	}
	public void setCustomerDatasetLockYn(String customerDatasetLockYn) {
		this.customerDatasetLockYn = customerDatasetLockYn;
	}
    public String getIntegrationAccountYn() {
        return integrationAccountYn;
    }
    public void setIntegrationAccountYn(String integrationAccountYn) {
        this.integrationAccountYn = integrationAccountYn;
    }
	public String getTemporaryMemberYn() {
		return temporaryMemberYn;
	}
	public void setTemporaryMemberYn(String temporaryMemberYn) {
		this.temporaryMemberYn = temporaryMemberYn;
	}
	public String getCustomerJoinType() {
		return customerJoinType;
	}
	public void setCustomerJoinType(String customerJoinType) {
		this.customerJoinType = customerJoinType;
	}
	public String getCustomerWithdrawalProcYn() {
		return customerWithdrawalProcYn;
	}
	public void setCustomerWithdrawalProcYn(String customerWithdrawalProcYn) {
		this.customerWithdrawalProcYn = customerWithdrawalProcYn;
	}
	public String getCustomerMobilePushYn() {
		return customerMobilePushYn;
	}
	public void setCustomerMobilePushYn(String customerMobilePushYn) {
		this.customerMobilePushYn = customerMobilePushYn;
	}
}