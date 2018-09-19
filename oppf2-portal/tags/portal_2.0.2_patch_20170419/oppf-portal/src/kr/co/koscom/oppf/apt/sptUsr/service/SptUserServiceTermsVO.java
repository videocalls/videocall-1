package kr.co.koscom.oppf.apt.sptUsr.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceTermsVO.java
* @Comment  : 금융정보제공 동의서 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class SptUserServiceTermsVO extends ComDefaultListVO{
	private String customerRegNo;
	private String customerId;
	private String customerNameKor;
	private String serviceRegNo;
	private String termsRegNo;
	private String termsRegNoEncryption;
	private String appId;
	private String subcompanyCodeId;
	private String subcompanyCodeName;
	private String pubcompanyCodeId;
	private String pubcompanyCodeName;
	private String termsStartDate;
	private String termsExpireDate;
	private String termsStatus;
	private String termsStatusName;
	
	/* 조회조건 */
	//앱 개발자
	private List<String> searchSubcompanyCodeId;
	private String searchSubcompanyCodeIdAllYn;
	
	//서비스 제공자
	private List<String> searchPubcompanyCodeId;
	private String searchPubcompanyCodeIdAllYn;
	
	//금융정보 제공 동의서 상태
	private List<String> searchTermsStatus;
	private String searchTermsStatusAllYn;
	
	//조회기간
	private String searchDateFrom;
	private String searchDateTo;
	
	// 약관동의방법
    private String termsAuthTypeNm;
    private String termsAuthType;
	private List<String> termsAuthTypeList;
    private String termsAuthTypeListAllYn;
	
	public String getCustomerRegNo() {
		return customerRegNo;
	}
	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNameKor() {
		return customerNameKor;
	}
	public void setCustomerNameKor(String customerNameKor) {
		this.customerNameKor = customerNameKor;
	}
	public String getServiceRegNo() {
		return serviceRegNo;
	}
	public void setServiceRegNo(String serviceRegNo) {
		this.serviceRegNo = serviceRegNo;
	}
	public String getTermsRegNo() {
		return termsRegNo;
	}
	public void setTermsRegNo(String termsRegNo) {
		this.termsRegNo = termsRegNo;
	}
	public String getTermsRegNoEncryption() {
        return termsRegNoEncryption;
    }
    public void setTermsRegNoEncryption(String termsRegNoEncryption) {
        this.termsRegNoEncryption = termsRegNoEncryption;
    }
    public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSubcompanyCodeId() {
		return subcompanyCodeId;
	}
	public void setSubcompanyCodeId(String subcompanyCodeId) {
		this.subcompanyCodeId = subcompanyCodeId;
	}
	public String getSubcompanyCodeName() {
		return subcompanyCodeName;
	}
	public void setSubcompanyCodeName(String subcompanyCodeName) {
		this.subcompanyCodeName = subcompanyCodeName;
	}
	public String getPubcompanyCodeId() {
		return pubcompanyCodeId;
	}
	public void setPubcompanyCodeId(String pubcompanyCodeId) {
		this.pubcompanyCodeId = pubcompanyCodeId;
	}
	public String getPubcompanyCodeName() {
		return pubcompanyCodeName;
	}
	public void setPubcompanyCodeName(String pubcompanyCodeName) {
		this.pubcompanyCodeName = pubcompanyCodeName;
	}
	public String getTermsStartDate() {
		return termsStartDate;
	}
	public void setTermsStartDate(String termsStartDate) {
		this.termsStartDate = termsStartDate;
	}
	public String getTermsExpireDate() {
		return termsExpireDate;
	}
	public void setTermsExpireDate(String termsExpireDate) {
		this.termsExpireDate = termsExpireDate;
	}
	public String getTermsStatus() {
		return termsStatus;
	}
	public void setTermsStatus(String termsStatus) {
		this.termsStatus = termsStatus;
	}
	public String getTermsStatusName() {
		return termsStatusName;
	}
	public void setTermsStatusName(String termsStatusName) {
		this.termsStatusName = termsStatusName;
	}
	public List<String> getSearchSubcompanyCodeId() {
		return searchSubcompanyCodeId;
	}
	public void setSearchSubcompanyCodeId(List<String> searchSubcompanyCodeId) {
		this.searchSubcompanyCodeId = searchSubcompanyCodeId;
	}
	public String getSearchSubcompanyCodeIdAllYn() {
		return searchSubcompanyCodeIdAllYn;
	}
	public void setSearchSubcompanyCodeIdAllYn(String searchSubcompanyCodeIdAllYn) {
		this.searchSubcompanyCodeIdAllYn = searchSubcompanyCodeIdAllYn;
	}
	public List<String> getSearchPubcompanyCodeId() {
		return searchPubcompanyCodeId;
	}
	public void setSearchPubcompanyCodeId(List<String> searchPubcompanyCodeId) {
		this.searchPubcompanyCodeId = searchPubcompanyCodeId;
	}
	public String getSearchPubcompanyCodeIdAllYn() {
		return searchPubcompanyCodeIdAllYn;
	}
	public void setSearchPubcompanyCodeIdAllYn(String searchPubcompanyCodeIdAllYn) {
		this.searchPubcompanyCodeIdAllYn = searchPubcompanyCodeIdAllYn;
	}
	public List<String> getSearchTermsStatus() {
		return searchTermsStatus;
	}
	public void setSearchTermsStatus(List<String> searchTermsStatus) {
		this.searchTermsStatus = searchTermsStatus;
	}
	public String getSearchTermsStatusAllYn() {
		return searchTermsStatusAllYn;
	}
	public void setSearchTermsStatusAllYn(String searchTermsStatusAllYn) {
		this.searchTermsStatusAllYn = searchTermsStatusAllYn;
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
	public String getTermsAuthTypeNm() {
		return termsAuthTypeNm;
	}
	public void setTermsAuthTypeNm(String termsAuthTypeNm) {
		this.termsAuthTypeNm = termsAuthTypeNm;
	}
	public String getTermsAuthType() {
		return termsAuthType;
	}
	public void setTermsAuthType(String termsAuthType) {
		this.termsAuthType = termsAuthType;
	}
	public List<String> getTermsAuthTypeList() {
		return termsAuthTypeList;
	}
	public void setTermsAuthTypeList(List<String> termsAuthTypeList) {
		this.termsAuthTypeList = termsAuthTypeList;
	}
	public String getTermsAuthTypeListAllYn() {
		return termsAuthTypeListAllYn;
	}
	public void setTermsAuthTypeListAllYn(String termsAuthTypeListAllYn) {
		this.termsAuthTypeListAllYn = termsAuthTypeListAllYn;
	}
	
	
}