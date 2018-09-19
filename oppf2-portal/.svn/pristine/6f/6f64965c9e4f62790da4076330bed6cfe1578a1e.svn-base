package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SvcApplVO.java
* @Comment  : [서비스신청]정보관리를 위한 VO 클래스
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
public class SvcApplVO extends ComDefaultListVO{
    
    /* 공통쓰임 관련 */
    private String companyCodeId;        /* 기업프로파일.기업코드 */
        
    /* 기업코드 테이블 관련 */
    private List<ComCompanyCodeVO> comCompanyCodeList;
    private String companyNameKor;       /* 기업프로파일.기업이름한글 */
    private String companyNameEng;       /* 기업프로파일.기업이름영문 */
    private String companyNameKorAlias;  /* 기업프로파일.기업이름Alias한글 */
    private String companyNameEngAlias;  /* 기업프로파일.기업이름Alias영문 */
    
    /* 기업운영자프로파일 테이블 관련 */
    private List<ComCompanyOperatorProfileVO> comCompanyOperatorProfileList;
    
    /* 기업프로파일 테이블 관련 */
    private List<ComCompanyProfileVO> comCompanyProfileList;
    
    /* API사용계약관계 테이블 관련 */
    private List<ComContractProfileVO> comContractProfileList;
    
    /* 일반회원가상계좌프로파일 테이블 관련 */
    private List<SptCustomerAccountProfileVO> sptCustomerAccountProfileList;
    private String customerRegNo;                /* 일반회원가상계좌프로파일.회원OP가입번호 */
    private String companyCodeIdNm;              /* 일반회원가상계좌프로파일.기업코드명 */
    private String customerRealaccountNo;        /* 일반회원가상계좌프로파일.실계좌번호 */
    private String customerRealaccountNoMask;    /* 일반회원가상계좌프로파일.실계좌번호 마스크 */
    private String customerRealaccountNoEncryption; /* 일반회원가상계좌프로파일.실계좌번호암호화 */
    private String customerVtaccountNo;          /* 일반회원가상계좌프로파일.가상계좌번호 */
    private String customerVtaccountAlias;       /* 일반회원가상계좌프로파일.가상계좌별칭 */
    private String customerVtaccountStatus;      /* 일반회원가상계좌프로파일.가상계좌상태 */
    private String customerRealaccountType;      /* 일반회원가상계좌프로파일.실계좌유형 */
    private String customerRealaccountTypeNm;    /* 일반회원가상계좌프로파일.실계좌유형명 */
    private String customerRealaccountTypeNmEng; /* 일반회원가상계좌프로파일.실계좌유형영문명 */
    private String customerVtaccountRegDate;     /* 일반회원가상계좌프로파일.가상계좌발급일시 */
    private String customerVtaccountExpireDate;  /* 일반회원가상계좌프로파일.가상계좌폐기일시 */
    
    /**
     * 핀테크서비스신청:핀테크서비스선택
     */
    private String serviceRegNo;
    private String companyProfileRegNo;
    
    //금융투자사 정보
    private List<String> searchCompanyCodeId;
	private String searchCompanyCodeIdAllYn;
	
	//조회 appIds
	private List<String> searchAppId;
	
	//사용여부
	private String searchUseYn;
	
	//동의서 최종 기간
	private String termsPolicy;
	
	//저장용 변수 셋팅
	private List<SptCustomerServiceProfileVO> sptCustomerServiceProfileList;								//핀테크 서비스 선택 저장용
	private List<SptCustomerServiceAccountProfileVO> sptCustomerServiceAccountProfileVO;					//일반회원서비스 가상계좌 선택 저장용
	private List<SptCustomerServiceTermsProfileVO> sptCustomerServiceTermsProfileVO;						//일반회원서비스 약관 프로파일 저장용
	private List<SptCustomerServiceTermsPubcompanyProfileVO> sptCustomerServiceTermsPubcompanyProfileVO;	//일반회원서비스 약관 금투사 프로파일 저장용
	
	private List<SptCustomerCompanyTermsProfileVO> sptCustomerCompanyTermsProfileVO;						//기업약관 저장용
	
	/* 메인 TOP 5용 */
	private String appId;
	private String appName;
	private String appDlUrl;
	private String appKey;

	/* 데이터 클랜징용 */
	private String termsRegNo;
	

	/* 검색(DEV)용 */
	private String searchCustomerRegNo;
	private String searchServiceRegNo;

	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public List<ComCompanyCodeVO> getComCompanyCodeList() {
		return comCompanyCodeList;
	}
	public void setComCompanyCodeList(List<ComCompanyCodeVO> comCompanyCodeList) {
		this.comCompanyCodeList = comCompanyCodeList;
	}
	public String getCompanyNameKor() {
		return companyNameKor;
	}
	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}
	public String getCompanyNameEng() {
		return companyNameEng;
	}
	public void setCompanyNameEng(String companyNameEng) {
		this.companyNameEng = companyNameEng;
	}
	public String getCompanyNameKorAlias() {
		return companyNameKorAlias;
	}
	public void setCompanyNameKorAlias(String companyNameKorAlias) {
		this.companyNameKorAlias = companyNameKorAlias;
	}
	public String getCompanyNameEngAlias() {
		return companyNameEngAlias;
	}
	public void setCompanyNameEngAlias(String companyNameEngAlias) {
		this.companyNameEngAlias = companyNameEngAlias;
	}
	public List<ComCompanyOperatorProfileVO> getComCompanyOperatorProfileList() {
		return comCompanyOperatorProfileList;
	}
	public void setComCompanyOperatorProfileList(List<ComCompanyOperatorProfileVO> comCompanyOperatorProfileList) {
		this.comCompanyOperatorProfileList = comCompanyOperatorProfileList;
	}
	public List<ComCompanyProfileVO> getComCompanyProfileList() {
		return comCompanyProfileList;
	}
	public void setComCompanyProfileList(List<ComCompanyProfileVO> comCompanyProfileList) {
		this.comCompanyProfileList = comCompanyProfileList;
	}
	public List<ComContractProfileVO> getComContractProfileList() {
		return comContractProfileList;
	}
	public void setComContractProfileList(List<ComContractProfileVO> comContractProfileList) {
		this.comContractProfileList = comContractProfileList;
	}
	public List<SptCustomerAccountProfileVO> getSptCustomerAccountProfileList() {
		return sptCustomerAccountProfileList;
	}
	public void setSptCustomerAccountProfileList(List<SptCustomerAccountProfileVO> sptCustomerAccountProfileList) {
		this.sptCustomerAccountProfileList = sptCustomerAccountProfileList;
	}
	public String getCustomerRegNo() {
		return customerRegNo;
	}
	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
	}
	public String getCompanyCodeIdNm() {
		return companyCodeIdNm;
	}
	public void setCompanyCodeIdNm(String companyCodeIdNm) {
		this.companyCodeIdNm = companyCodeIdNm;
	}
	public String getCustomerRealaccountNo() {
		return customerRealaccountNo;
	}
	public void setCustomerRealaccountNo(String customerRealaccountNo) {
		this.customerRealaccountNo = customerRealaccountNo;
	}
	public String getCustomerRealaccountNoMask() {
        return customerRealaccountNoMask;
    }
    public void setCustomerRealaccountNoMask(String customerRealaccountNoMask) {
        this.customerRealaccountNoMask = customerRealaccountNoMask;
    }
    public String getCustomerRealaccountNoEncryption() {
        return customerRealaccountNoEncryption;
    }
    public void setCustomerRealaccountNoEncryption(String customerRealaccountNoEncryption) {
        this.customerRealaccountNoEncryption = customerRealaccountNoEncryption;
    }
    public String getCustomerVtaccountNo() {
		return customerVtaccountNo;
	}
	public void setCustomerVtaccountNo(String customerVtaccountNo) {
		this.customerVtaccountNo = customerVtaccountNo;
	}
	public String getCustomerVtaccountAlias() {
		return customerVtaccountAlias;
	}
	public void setCustomerVtaccountAlias(String customerVtaccountAlias) {
		this.customerVtaccountAlias = customerVtaccountAlias;
	}
	public String getCustomerVtaccountStatus() {
		return customerVtaccountStatus;
	}
	public void setCustomerVtaccountStatus(String customerVtaccountStatus) {
		this.customerVtaccountStatus = customerVtaccountStatus;
	}
	public String getCustomerRealaccountType() {
		return customerRealaccountType;
	}
	public void setCustomerRealaccountType(String customerRealaccountType) {
		this.customerRealaccountType = customerRealaccountType;
	}
	public String getCustomerRealaccountTypeNm() {
		return customerRealaccountTypeNm;
	}
	public void setCustomerRealaccountTypeNm(String customerRealaccountTypeNm) {
		this.customerRealaccountTypeNm = customerRealaccountTypeNm;
	}
	public String getCustomerRealaccountTypeNmEng() {
		return customerRealaccountTypeNmEng;
	}
	public void setCustomerRealaccountTypeNmEng(String customerRealaccountTypeNmEng) {
		this.customerRealaccountTypeNmEng = customerRealaccountTypeNmEng;
	}
	public String getCustomerVtaccountRegDate() {
		return customerVtaccountRegDate;
	}
	public void setCustomerVtaccountRegDate(String customerVtaccountRegDate) {
		this.customerVtaccountRegDate = customerVtaccountRegDate;
	}
	public String getCustomerVtaccountExpireDate() {
		return customerVtaccountExpireDate;
	}
	public void setCustomerVtaccountExpireDate(String customerVtaccountExpireDate) {
		this.customerVtaccountExpireDate = customerVtaccountExpireDate;
	}
	public String getServiceRegNo() {
		return serviceRegNo;
	}
	public void setServiceRegNo(String serviceRegNo) {
		this.serviceRegNo = serviceRegNo;
	}
	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}
	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}
	public List<String> getSearchCompanyCodeId() {
		return searchCompanyCodeId;
	}
	public void setSearchCompanyCodeId(List<String> searchCompanyCodeId) {
		this.searchCompanyCodeId = searchCompanyCodeId;
	}
	public String getSearchCompanyCodeIdAllYn() {
		return searchCompanyCodeIdAllYn;
	}
	public void setSearchCompanyCodeIdAllYn(String searchCompanyCodeIdAllYn) {
		this.searchCompanyCodeIdAllYn = searchCompanyCodeIdAllYn;
	}
	public List<String> getSearchAppId() {
		return searchAppId;
	}
	public void setSearchAppId(List<String> searchAppId) {
		this.searchAppId = searchAppId;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public String getTermsPolicy() {
		return termsPolicy;
	}
	public void setTermsPolicy(String termsPolicy) {
		this.termsPolicy = termsPolicy;
	}
	public List<SptCustomerServiceProfileVO> getSptCustomerServiceProfileList() {
		return sptCustomerServiceProfileList;
	}
	public void setSptCustomerServiceProfileList(List<SptCustomerServiceProfileVO> sptCustomerServiceProfileList) {
		this.sptCustomerServiceProfileList = sptCustomerServiceProfileList;
	}
	public List<SptCustomerServiceAccountProfileVO> getSptCustomerServiceAccountProfileVO() {
		return sptCustomerServiceAccountProfileVO;
	}
	public void setSptCustomerServiceAccountProfileVO(
			List<SptCustomerServiceAccountProfileVO> sptCustomerServiceAccountProfileVO) {
		this.sptCustomerServiceAccountProfileVO = sptCustomerServiceAccountProfileVO;
	}
	public List<SptCustomerServiceTermsProfileVO> getSptCustomerServiceTermsProfileVO() {
		return sptCustomerServiceTermsProfileVO;
	}
	public void setSptCustomerServiceTermsProfileVO(
			List<SptCustomerServiceTermsProfileVO> sptCustomerServiceTermsProfileVO) {
		this.sptCustomerServiceTermsProfileVO = sptCustomerServiceTermsProfileVO;
	}
	public List<SptCustomerServiceTermsPubcompanyProfileVO> getSptCustomerServiceTermsPubcompanyProfileVO() {
		return sptCustomerServiceTermsPubcompanyProfileVO;
	}
	public void setSptCustomerServiceTermsPubcompanyProfileVO(
			List<SptCustomerServiceTermsPubcompanyProfileVO> sptCustomerServiceTermsPubcompanyProfileVO) {
		this.sptCustomerServiceTermsPubcompanyProfileVO = sptCustomerServiceTermsPubcompanyProfileVO;
	}
	public List<SptCustomerCompanyTermsProfileVO> getSptCustomerCompanyTermsProfileVO() {
		return sptCustomerCompanyTermsProfileVO;
	}
	public void setSptCustomerCompanyTermsProfileVO(
			List<SptCustomerCompanyTermsProfileVO> sptCustomerCompanyTermsProfileVO) {
		this.sptCustomerCompanyTermsProfileVO = sptCustomerCompanyTermsProfileVO;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDlUrl() {
		return appDlUrl;
	}
	public void setAppDlUrl(String appDlUrl) {
		this.appDlUrl = appDlUrl;
	}
	public String getTermsRegNo() {
		return termsRegNo;
	}
	public void setTermsRegNo(String termsRegNo) {
		this.termsRegNo = termsRegNo;
	}
	public String getSearchCustomerRegNo() {
		return searchCustomerRegNo;
	}
	public void setSearchCustomerRegNo(String searchCustomerRegNo) {
		this.searchCustomerRegNo = searchCustomerRegNo;
	}
	public String getSearchServiceRegNo() {
		return searchServiceRegNo;
	}
	public void setSearchServiceRegNo(String searchServiceRegNo) {
		this.searchServiceRegNo = searchServiceRegNo;
	}
	
	
}