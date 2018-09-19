package kr.co.koscom.oppf.apt.cptUsr.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptCompanyManageVO.java
* @Comment  : 기업정보 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.06.21
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CptCompanyManageVO extends ComDefaultListVO{
	/* com_company_code */
	private String companyCodeRegNo;
	private String companyCodeType;
	private String companyCodeTypeName;
	private String companyCodeKind;
	private String companyCodeKindName;
	private String companyDivCode;
	private String companyDivCodeName;	
	
	/* com_company_profile */
	private String companyProfileRegNo;
	private String companyCodeId;
	private String companyServiceType;
	private String issueVtaccount;
	private String issueAccountlist;
	private String companyNameKor;
	private String companyNameEng;
	private String companyNameKorAlias;
	private String companyNameEngAlias;
	private String companyBizregNo;
	private String companyPostNo;
	private String companyAddress;
	private String companyAddressDtl;
	private byte[] companyCi;
	private String ceoName;
	private String exposureYn;
	private String exposureOrder;
	
	private String companyServiceTypeName;
	private String issueVtaccountName;
	private String issueAccountlistName;
	
	/* 검색조건 */
	private String searchDateType;
	private String searchDateFrom;
	private String searchDateTo;
	
	//기업역할
	private List<String> searchCompanyServiceType;
	private String searchCompanyServiceTypeAllYn;
	
	/* 미등록 팝업 검색조건 */
	private String searchCompanyRegYn;
	
	//기업코드표 구분
	private List<String> searchCmpanyCodeType;
	private String searchCmpanyCodeTypeAllYn;
	
	//기업 종류
	private List<String> searchCompanyCodeKind;
	private String searchCompanyCodeKindAllYn;
	
	//기업 분류
	private List<String> searchCompanyDivCode;
	private String searchCompanyDivCodeAllYn;

	// 2017.03.07 추가
	private Long tokenLifeTime;
	private String tokenRefreshYn;

	// 2017.03.14 추가
	private Long tokenRefreshLifeTime;

	// 2017.03.21 추가
	private String termsTransmitYn;

	public Long getTokenRefreshLifeTime() {
		return tokenRefreshLifeTime;
	}

	public void setTokenRefreshLifeTime(Long tokenRefreshLifeTime) {
		this.tokenRefreshLifeTime = tokenRefreshLifeTime;
	}

	public String getCompanyCodeRegNo() {
		return companyCodeRegNo;
	}
	public void setCompanyCodeRegNo(String companyCodeRegNo) {
		this.companyCodeRegNo = companyCodeRegNo;
	}
	public String getCompanyCodeType() {
		return companyCodeType;
	}
	public void setCompanyCodeType(String companyCodeType) {
		this.companyCodeType = companyCodeType;
	}
	public String getCompanyCodeTypeName() {
		return companyCodeTypeName;
	}
	public void setCompanyCodeTypeName(String companyCodeTypeName) {
		this.companyCodeTypeName = companyCodeTypeName;
	}
	public String getCompanyCodeKind() {
		return companyCodeKind;
	}
	public void setCompanyCodeKind(String companyCodeKind) {
		this.companyCodeKind = companyCodeKind;
	}
	public String getCompanyCodeKindName() {
		return companyCodeKindName;
	}
	public void setCompanyCodeKindName(String companyCodeKindName) {
		this.companyCodeKindName = companyCodeKindName;
	}
	public String getCompanyDivCode() {
		return companyDivCode;
	}
	public void setCompanyDivCode(String companyDivCode) {
		this.companyDivCode = companyDivCode;
	}
	public String getCompanyDivCodeName() {
		return companyDivCodeName;
	}
	public void setCompanyDivCodeName(String companyDivCodeName) {
		this.companyDivCodeName = companyDivCodeName;
	}
	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}
	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getCompanyServiceType() {
		return companyServiceType;
	}
	public void setCompanyServiceType(String companyServiceType) {
		this.companyServiceType = companyServiceType;
	}
	public String getIssueVtaccount() {
		return issueVtaccount;
	}
	public void setIssueVtaccount(String issueVtaccount) {
		this.issueVtaccount = issueVtaccount;
	}
	public String getIssueAccountlist() {
		return issueAccountlist;
	}
	public void setIssueAccountlist(String issueAccountlist) {
		this.issueAccountlist = issueAccountlist;
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
	public String getCompanyBizregNo() {
		return companyBizregNo;
	}
	public void setCompanyBizregNo(String companyBizregNo) {
		this.companyBizregNo = companyBizregNo;
	}
	public String getCompanyPostNo() {
		return companyPostNo;
	}
	public void setCompanyPostNo(String companyPostNo) {
		this.companyPostNo = companyPostNo;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyAddressDtl() {
		return companyAddressDtl;
	}
	public void setCompanyAddressDtl(String companyAddressDtl) {
		this.companyAddressDtl = companyAddressDtl;
	}
	public byte[] getCompanyCi() {
		return companyCi;
	}
	public void setCompanyCi(byte[] companyCi) {
		this.companyCi = companyCi;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public String getExposureOrder() {
		return exposureOrder;
	}
	public void setExposureOrder(String exposureOrder) {
		this.exposureOrder = exposureOrder;
	}
	public String getCompanyServiceTypeName() {
		return companyServiceTypeName;
	}
	public void setCompanyServiceTypeName(String companyServiceTypeName) {
		this.companyServiceTypeName = companyServiceTypeName;
	}
	public String getIssueVtaccountName() {
		return issueVtaccountName;
	}
	public void setIssueVtaccountName(String issueVtaccountName) {
		this.issueVtaccountName = issueVtaccountName;
	}
	public String getIssueAccountlistName() {
		return issueAccountlistName;
	}
	public void setIssueAccountlistName(String issueAccountlistName) {
		this.issueAccountlistName = issueAccountlistName;
	}
	public String getSearchDateType() {
		return searchDateType;
	}
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}
	public String getSearchDateFrom() {
		return searchDateFrom;
	}
	public void setSearchDateFrom(String searchDateFrom) {
		this.searchDateFrom = searchDateFrom;
	}
	public String getSearchDateTo() {
		return searchDateTo;
	}
	public void setSearchDateTo(String searchDateTo) {
		this.searchDateTo = searchDateTo;
	}
	public List<String> getSearchCompanyServiceType() {
		return searchCompanyServiceType;
	}
	public void setSearchCompanyServiceType(List<String> searchCompanyServiceType) {
		this.searchCompanyServiceType = searchCompanyServiceType;
	}
	public String getSearchCompanyServiceTypeAllYn() {
		return searchCompanyServiceTypeAllYn;
	}
	public void setSearchCompanyServiceTypeAllYn(String searchCompanyServiceTypeAllYn) {
		this.searchCompanyServiceTypeAllYn = searchCompanyServiceTypeAllYn;
	}
	public String getSearchCompanyRegYn() {
		return searchCompanyRegYn;
	}
	public void setSearchCompanyRegYn(String searchCompanyRegYn) {
		this.searchCompanyRegYn = searchCompanyRegYn;
	}
	public List<String> getSearchCmpanyCodeType() {
		return searchCmpanyCodeType;
	}
	public void setSearchCmpanyCodeType(List<String> searchCmpanyCodeType) {
		this.searchCmpanyCodeType = searchCmpanyCodeType;
	}
	public String getSearchCmpanyCodeTypeAllYn() {
		return searchCmpanyCodeTypeAllYn;
	}
	public void setSearchCmpanyCodeTypeAllYn(String searchCmpanyCodeTypeAllYn) {
		this.searchCmpanyCodeTypeAllYn = searchCmpanyCodeTypeAllYn;
	}
	public List<String> getSearchCompanyCodeKind() {
		return searchCompanyCodeKind;
	}
	public void setSearchCompanyCodeKind(List<String> searchCompanyCodeKind) {
		this.searchCompanyCodeKind = searchCompanyCodeKind;
	}
	public String getSearchCompanyCodeKindAllYn() {
		return searchCompanyCodeKindAllYn;
	}
	public void setSearchCompanyCodeKindAllYn(String searchCompanyCodeKindAllYn) {
		this.searchCompanyCodeKindAllYn = searchCompanyCodeKindAllYn;
	}
	public List<String> getSearchCompanyDivCode() {
		return searchCompanyDivCode;
	}
	public void setSearchCompanyDivCode(List<String> searchCompanyDivCode) {
		this.searchCompanyDivCode = searchCompanyDivCode;
	}
	public String getSearchCompanyDivCodeAllYn() {
		return searchCompanyDivCodeAllYn;
	}
	public void setSearchCompanyDivCodeAllYn(String searchCompanyDivCodeAllYn) {
		this.searchCompanyDivCodeAllYn = searchCompanyDivCodeAllYn;
	}

	public Long getTokenLifeTime() {
		return tokenLifeTime;
	}

	public void setTokenLifeTime(Long tokenLifeTime) {
		this.tokenLifeTime = tokenLifeTime;
	}

	public String getTokenRefreshYn() {
		return tokenRefreshYn;
	}

	public void setTokenRefreshYn(String tokenRefreshYn) {
		this.tokenRefreshYn = tokenRefreshYn;
	}

	public String getTermsTransmitYn() {
		return termsTransmitYn;
	}

	public void setTermsTransmitYn(String termsTransmitYn) {
		this.termsTransmitYn = termsTransmitYn;
	}
}