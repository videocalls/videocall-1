package kr.co.koscom.oppf.apt.plan.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiPlanManageVO.java
* @Comment  : api plan 관리를 위한 위한 VO
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
public class ApiPlanManageVO extends ComDefaultListVO{
	private int appId;
	private String appKey;
	private String appName;
	private String appStatus;
	private String appStatusName;
	private String apiPlanId;
	private String apiId;
	private String apiName;
	private String apicategory;
	private String apiCategoryName;
	private String apiCompanyCodeId;
	private String apiCompanyName;
	private String planType;
	private String planTypeName;
	private String createId;
	private String createIdName;
	private String createDate;
	
	/**
	 * 검색조건
	 */
	//excel type = excel : 엑셀, down: 다운
	private String excelType;
	
	//app 상태
	private List<String> searchAppStatus;
	private String searchAppStatusAllYn;
	
	//plan Type
	private List<String> searchPlanType;
	private String searchPlanTypeAllYn;	
	
	//api 종류
	private List<String> searchApiCategory;
	private String searchApiCategoryAllYn;
	
	//서비스 제공자
	private List<String> searchCompanyCodeId;
	private String searchCompanyCodeIdAllYn;
	
	//등록일
	private String searchDateFrom;
	private String searchDateTo;
	
	/* popup */
	private String systemGrpCode;
	private String systemCode; 
	
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
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppStatusName() {
		return appStatusName;
	}
	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}
	public String getApiPlanId() {
		return apiPlanId;
	}
	public void setApiPlanId(String apiPlanId) {
		this.apiPlanId = apiPlanId;
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
	public String getApicategory() {
		return apicategory;
	}
	public void setApicategory(String apicategory) {
		this.apicategory = apicategory;
	}
	public String getApiCategoryName() {
		return apiCategoryName;
	}
	public void setApiCategoryName(String apiCategoryName) {
		this.apiCategoryName = apiCategoryName;
	}
	public String getApiCompanyCodeId() {
		return apiCompanyCodeId;
	}
	public void setApiCompanyCodeId(String apiCompanyCodeId) {
		this.apiCompanyCodeId = apiCompanyCodeId;
	}
	public String getApiCompanyName() {
		return apiCompanyName;
	}
	public void setApiCompanyName(String apiCompanyName) {
		this.apiCompanyName = apiCompanyName;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getPlanTypeName() {
		return planTypeName;
	}
	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateIdName() {
		return createIdName;
	}
	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getExcelType() {
		return excelType;
	}
	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}
	public List<String> getSearchAppStatus() {
		return searchAppStatus;
	}
	public void setSearchAppStatus(List<String> searchAppStatus) {
		this.searchAppStatus = searchAppStatus;
	}
	public String getSearchAppStatusAllYn() {
		return searchAppStatusAllYn;
	}
	public void setSearchAppStatusAllYn(String searchAppStatusAllYn) {
		this.searchAppStatusAllYn = searchAppStatusAllYn;
	}
	public List<String> getSearchPlanType() {
		return searchPlanType;
	}
	public void setSearchPlanType(List<String> searchPlanType) {
		this.searchPlanType = searchPlanType;
	}
	public String getSearchPlanTypeAllYn() {
		return searchPlanTypeAllYn;
	}
	public void setSearchPlanTypeAllYn(String searchPlanTypeAllYn) {
		this.searchPlanTypeAllYn = searchPlanTypeAllYn;
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
	public String getSystemGrpCode() {
		return systemGrpCode;
	}
	public void setSystemGrpCode(String systemGrpCode) {
		this.systemGrpCode = systemGrpCode;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
}