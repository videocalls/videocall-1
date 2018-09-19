package kr.co.koscom.oppf.apt.sptUsr.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceVO.java
* @Comment  : 서비스 연결 현황 관리를 위한 위한 VO
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
public class SptUserServiceVO extends ComDefaultListVO{
	private String customerRegNo;
	private String serviceRegNo;
	private String termsRegNo;
	private String accountRegNo;
	private String customerId;
	private String customerNameKor;
	private String customerNameEng;
	private String customerEmail;
	private String customerRegStatus;
	private String customerRegStatusName;
	private String appId;
	private String appName;
	private String subcompanyCodeId;
	private String subcompanyCodeName;
	private String apiId;
	private String apiName;
	private String pubcompanyCodeId;
	private String pubcompanyCodeName;
	private String customerRealaccountNo;
	private String customerVtaccountNo;
	private String svcAvailableYnName;
	
	/* 조회조건 */
	//계정상태
	private List<String> searchCustomerRegStatus;
	private String searchCustomerRegStatusAllYn;
	
	//앱 개발자
	private List<String> searchSubcompanyCodeId;
	private String searchSubcompanyCodeIdAllYn;
	
	//앱 이름
	private List<String> searchAppId;
	private String searchAppIdAllYn;
	
	//서비스 제공자
	private List<String> searchPubcompanyCodeId;
	private String searchPubcompanyCodeIdAllYn;
	
	//유효여부
	private List<String> searchSvcAvailableYn;
	private String searchSvcAvailableYnAllYn;
		
	//조회기간
	private String searchDateType;
	private String searchDateFrom;
	private String searchDateTo;


	private String searchCustomerRegNo;
	private String searchServiceRegNo;
	
	
	public String getCustomerRegNo() {
		return customerRegNo;
	}
	public void setCustomerRegNo(String customerRegNo) {
		this.customerRegNo = customerRegNo;
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
	public String getCustomerNameEng() {
		return customerNameEng;
	}
	public void setCustomerNameEng(String customerNameEng) {
		this.customerNameEng = customerNameEng;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerRegStatus() {
		return customerRegStatus;
	}
	public void setCustomerRegStatus(String customerRegStatus) {
		this.customerRegStatus = customerRegStatus;
	}
	public String getCustomerRegStatusName() {
		return customerRegStatusName;
	}
	public void setCustomerRegStatusName(String customerRegStatusName) {
		this.customerRegStatusName = customerRegStatusName;
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
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
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
	public String getCustomerRealaccountNo() {
		return customerRealaccountNo;
	}
	public void setCustomerRealaccountNo(String customerRealaccountNo) {
		this.customerRealaccountNo = customerRealaccountNo;
	}
	public String getCustomerVtaccountNo() {
		return customerVtaccountNo;
	}
	public void setCustomerVtaccountNo(String customerVtaccountNo) {
		this.customerVtaccountNo = customerVtaccountNo;
	}
	public String getSvcAvailableYnName() {
		return svcAvailableYnName;
	}
	public void setSvcAvailableYnName(String svcAvailableYnName) {
		this.svcAvailableYnName = svcAvailableYnName;
	}
	public List<String> getSearchCustomerRegStatus() {
		return searchCustomerRegStatus;
	}
	public void setSearchCustomerRegStatus(List<String> searchCustomerRegStatus) {
		this.searchCustomerRegStatus = searchCustomerRegStatus;
	}
	public String getSearchCustomerRegStatusAllYn() {
		return searchCustomerRegStatusAllYn;
	}
	public void setSearchCustomerRegStatusAllYn(String searchCustomerRegStatusAllYn) {
		this.searchCustomerRegStatusAllYn = searchCustomerRegStatusAllYn;
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
	public List<String> getSearchAppId() {
		return searchAppId;
	}
	public void setSearchAppId(List<String> searchAppId) {
		this.searchAppId = searchAppId;
	}
	public String getSearchAppIdAllYn() {
		return searchAppIdAllYn;
	}
	public void setSearchAppIdAllYn(String searchAppIdAllYn) {
		this.searchAppIdAllYn = searchAppIdAllYn;
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
	public List<String> getSearchSvcAvailableYn() {
		return searchSvcAvailableYn;
	}
	public void setSearchSvcAvailableYn(List<String> searchSvcAvailableYn) {
		this.searchSvcAvailableYn = searchSvcAvailableYn;
	}
	public String getSearchSvcAvailableYnAllYn() {
		return searchSvcAvailableYnAllYn;
	}
	public void setSearchSvcAvailableYnAllYn(String searchSvcAvailableYnAllYn) {
		this.searchSvcAvailableYnAllYn = searchSvcAvailableYnAllYn;
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
	public String getAccountRegNo() {
		return accountRegNo;
	}
	public void setAccountRegNo(String accountRegNo) {
		this.accountRegNo = accountRegNo;
	}
}