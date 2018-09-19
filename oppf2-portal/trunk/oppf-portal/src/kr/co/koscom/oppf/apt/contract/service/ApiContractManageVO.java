package kr.co.koscom.oppf.apt.contract.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiContractManageVO.java
* @Comment  : api 계약 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class ApiContractManageVO extends ComDefaultListVO{
	/**
	 * com_api_contract_info
	 */
	private String apiId;
	private String pubcompanyCodeId;
	private String supcompanyCodeId;
	private String serviceContractStatus;
	private String contractTermsStartDate;
	private String contractTermsExpireDate;
	
	/**
	 * 조회
	 */
	private int appId;
	private String appKey;
	private String appName;
	private String subcompanyCodeId;
	private String subcompanyName;
	private String apiName;
	private String apiCategory;
	private String apiCategoryName;
	private String pubcompanyName;
	private String serviceContractStatusName;
	private String createIdName;
	
	/**
	 * 상세
	 */
	private String apiContractCode;
	private String apiContractCodeName;
	private String apiTermsStartDate;
	private String apiTermsExpireDate; 
	
	/**
	 * 검색조건
	 */
	//excel type = excel : 엑셀, down: 다운
	private String excelType;
	
	//서비스 제공자
	private List<String> searchPubcompanyCodeId;
	private String searchPubcompanyCodeIdAllYn;
	
	//앱개발자
	private List<String> searchSubcompanyCodeId;
	private String searchSubcompanyCodeIdAllYn;
	
	//api 서비스 구분
	private List<String> searchApiCategory;
	private String searchApiCategoryAllYn;
	
	//api 서비스 계약 상태
	private List<String> searchServiceContractStatus;
	private String searchServiceContractStatusAllYn;
	
	//api 서비스 계약기간
	private String searchDateFrom;
	private String searchDateTo;
	
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getPubcompanyCodeId() {
		return pubcompanyCodeId;
	}
	public void setPubcompanyCodeId(String pubcompanyCodeId) {
		this.pubcompanyCodeId = pubcompanyCodeId;
	}
	public String getSupcompanyCodeId() {
		return supcompanyCodeId;
	}
	public void setSupcompanyCodeId(String supcompanyCodeId) {
		this.supcompanyCodeId = supcompanyCodeId;
	}
	public String getServiceContractStatus() {
		return serviceContractStatus;
	}
	public void setServiceContractStatus(String serviceContractStatus) {
		this.serviceContractStatus = serviceContractStatus;
	}
	public String getContractTermsStartDate() {
		return contractTermsStartDate;
	}
	public void setContractTermsStartDate(String contractTermsStartDate) {
		this.contractTermsStartDate = contractTermsStartDate;
	}
	public String getContractTermsExpireDate() {
		return contractTermsExpireDate;
	}
	public void setContractTermsExpireDate(String contractTermsExpireDate) {
		this.contractTermsExpireDate = contractTermsExpireDate;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
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
	public String getSubcompanyName() {
		return subcompanyName;
	}
	public void setSubcompanyName(String subcompanyName) {
		this.subcompanyName = subcompanyName;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiCategory() {
		return apiCategory;
	}
	public void setApiCategory(String apiCategory) {
		this.apiCategory = apiCategory;
	}
	public String getApiCategoryName() {
		return apiCategoryName;
	}
	public void setApiCategoryName(String apiCategoryName) {
		this.apiCategoryName = apiCategoryName;
	}
	public String getPubcompanyName() {
		return pubcompanyName;
	}
	public void setPubcompanyName(String pubcompanyName) {
		this.pubcompanyName = pubcompanyName;
	}
	public String getServiceContractStatusName() {
		return serviceContractStatusName;
	}
	public void setServiceContractStatusName(String serviceContractStatusName) {
		this.serviceContractStatusName = serviceContractStatusName;
	}
	public String getCreateIdName() {
		return createIdName;
	}
	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}
	public String getApiContractCode() {
		return apiContractCode;
	}
	public void setApiContractCode(String apiContractCode) {
		this.apiContractCode = apiContractCode;
	}
	public String getApiContractCodeName() {
		return apiContractCodeName;
	}
	public void setApiContractCodeName(String apiContractCodeName) {
		this.apiContractCodeName = apiContractCodeName;
	}
	public String getApiTermsStartDate() {
		return apiTermsStartDate;
	}
	public void setApiTermsStartDate(String apiTermsStartDate) {
		this.apiTermsStartDate = apiTermsStartDate;
	}
	public String getApiTermsExpireDate() {
		return apiTermsExpireDate;
	}
	public void setApiTermsExpireDate(String apiTermsExpireDate) {
		this.apiTermsExpireDate = apiTermsExpireDate;
	}
	public String getExcelType() {
		return excelType;
	}
	public void setExcelType(String excelType) {
		this.excelType = excelType;
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
	public List<String> getSearchApiCategory() {
		return searchApiCategory;
	}
	public void setSearchApiCategory(List<String> searchApiCategory) {
		this.searchApiCategory = searchApiCategory;
	}
	public String getSearchApiCategoryAllYn() {
		return searchApiCategoryAllYn;
	}
	public void setSearchApiCategoryAllYn(String searchApiCategoryAllYn) {
		this.searchApiCategoryAllYn = searchApiCategoryAllYn;
	}
	public List<String> getSearchServiceContractStatus() {
		return searchServiceContractStatus;
	}
	public void setSearchServiceContractStatus(List<String> searchServiceContractStatus) {
		this.searchServiceContractStatus = searchServiceContractStatus;
	}
	public String getSearchServiceContractStatusAllYn() {
		return searchServiceContractStatusAllYn;
	}
	public void setSearchServiceContractStatusAllYn(String searchServiceContractStatusAllYn) {
		this.searchServiceContractStatusAllYn = searchServiceContractStatusAllYn;
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
}