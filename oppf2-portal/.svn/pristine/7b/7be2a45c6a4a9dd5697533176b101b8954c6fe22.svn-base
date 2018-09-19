package kr.co.koscom.oppf.cpt.myp.stats.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptStatsTrafficVO.java
* @Comment  : 기업포털 Traffic 통계 관리를 위한 위한 VO
* @author   : 신동진
* @since    : 2016.08.19
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.19  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class CptStatsTrafficVO extends ComDefaultListVO{
	//기업 프로파일 등록번호
    private String companyProfileRegNo;
    //기업 운영자 프로파일 등록번호
    private String operatorProfileRegNo;
    //기업 codeId
    private String companyCodeId;
	
	/* base */
	private String stdDate;
	private String apiId;
	private String appId;
	private String appKey;
	private String apiGroup;
	private String apiService;
	private String responseResultYn;
	private String apiMethod;
	private String apiResource;
	
	/* 조회 */
	private String appName;
	private String subcompanyCodeId;
	private String subcompanyName;
	private String apiName;
	private String pubcompanyCodeId;
	private String pubcompanyName;
	private String apiCategory;
	private String apiCategoryName;
	private String apiPlanId;
	private String apiPlanCode;
	private String apiPlanName;
	private String cntApiDuration;
	
	private String durationType;
	private String durationName;
	
	private String sql1;
	private String sql2;
	private String sql3;
	private String trafficTable;
	private String trafficTableCondition;
	
	/* 조회 조건 */
	//API 서비스 제공자
	private List<String> searchPubCompanyCodeId;
	private String searchPubCompanyCodeIdAllYn;
	
	//API 서비스 구분
	private List<String> searchApiCategory;
	private String searchApiCategoryAllYn;
	
	//API 서비스 이름
	private List<String> searchApiName;
	private String searchApiNameAllYn;
	
	//세부 API 서비스 코드
	private List<String> searchApiService;
	private String searchApiServiceAllYn;
	
	//앱 개발자
	private List<String> searchSubCompanyCodeId;
	private String searchSubCompanyCodeIdAllYn;
	
	//앱 이름
	private List<String> searchAppName;
	private String searchAppNameAllYn;
	
	//성공여부
	private List<String> searchResponseResultYn;
	private String searchResponseResultYnAllYn;
	
	//Plan 종류
	private List<String> searchApiPlanName;
	private String searchApiPlanNameAllYn;
	
	//구분 : min(분), 
	private String searchTrafficType;
	
	//통계기준 : cnt(건수기준), duration(응답시간)
	// 2017.03.07 수정 : appName(앱이름), apiName(API서비스이름), plan(Plan종류)
	private String searchStatsType;
	
	//조회기간 : 분(일자 + 시간), 시간(일자 + 시간), 일(일자), 개월(일자)
	private String searchDateTime;
	
	//header용 조회결과
	private String searchStdDate;
	private String searchStdDateText;

	// 2017.03.07 추가
	private	List<String> apiNameList;
	private	List<String> apiPlanNameList;
	private	List<String> appNameList;
	private String searchDateTimeFrom;
	private String searchDateTimeTo;
	private String searchDateFrom;
	private String searchDateTo;
	private String cntApiDurationTotal;
	private String cntApiDurationY;
	private String cntApiDurationN;
	private String apiDurationY;
	private String apiDurationN;
	private String sifDurationY;
	private String sifDurationN;
	private String Date;
		
	public String getCompanyProfileRegNo() {
		return companyProfileRegNo;
	}
	public void setCompanyProfileRegNo(String companyProfileRegNo) {
		this.companyProfileRegNo = companyProfileRegNo;
	}
	public String getOperatorProfileRegNo() {
		return operatorProfileRegNo;
	}
	public void setOperatorProfileRegNo(String operatorProfileRegNo) {
		this.operatorProfileRegNo = operatorProfileRegNo;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getStdDate() {
		return stdDate;
	}
	public void setStdDate(String stdDate) {
		this.stdDate = stdDate;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getApiGroup() {
		return apiGroup;
	}
	public void setApiGroup(String apiGroup) {
		this.apiGroup = apiGroup;
	}
	public String getApiService() {
		return apiService;
	}
	public void setApiService(String apiService) {
		this.apiService = apiService;
	}
	public String getResponseResultYn() {
		return responseResultYn;
	}
	public void setResponseResultYn(String responseResultYn) {
		this.responseResultYn = responseResultYn;
	}
	public String getApiMethod() {
		return apiMethod;
	}
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}
	public String getApiResource() {
		return apiResource;
	}
	public void setApiResource(String apiResource) {
		this.apiResource = apiResource;
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
	public String getPubcompanyCodeId() {
		return pubcompanyCodeId;
	}
	public void setPubcompanyCodeId(String pubcompanyCodeId) {
		this.pubcompanyCodeId = pubcompanyCodeId;
	}
	public String getPubcompanyName() {
		return pubcompanyName;
	}
	public void setPubcompanyName(String pubcompanyName) {
		this.pubcompanyName = pubcompanyName;
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
	public String getApiPlanId() {
		return apiPlanId;
	}
	public void setApiPlanId(String apiPlanId) {
		this.apiPlanId = apiPlanId;
	}
	public String getApiPlanCode() {
		return apiPlanCode;
	}
	public void setApiPlanCode(String apiPlanCode) {
		this.apiPlanCode = apiPlanCode;
	}
	public String getApiPlanName() {
		return apiPlanName;
	}
	public void setApiPlanName(String apiPlanName) {
		this.apiPlanName = apiPlanName;
	}
	public String getCntApiDuration() {
		return cntApiDuration;
	}
	public void setCntApiDuration(String cntApiDuration) {
		this.cntApiDuration = cntApiDuration;
	}
	public String getDurationType() {
		return durationType;
	}
	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}
	public String getDurationName() {
		return durationName;
	}
	public void setDurationName(String durationName) {
		this.durationName = durationName;
	}
	public String getSql1() {
		return sql1;
	}
	public void setSql1(String sql1) {
		this.sql1 = sql1;
	}
	public String getSql2() {
		return sql2;
	}
	public void setSql2(String sql2) {
		this.sql2 = sql2;
	}
	public String getSql3() {
		return sql3;
	}
	public void setSql3(String sql3) {
		this.sql3 = sql3;
	}
	public String getTrafficTable() {
		return trafficTable;
	}
	public void setTrafficTable(String trafficTable) {
		this.trafficTable = trafficTable;
	}
	public String getTrafficTableCondition() {
		return trafficTableCondition;
	}
	public void setTrafficTableCondition(String trafficTableCondition) {
		this.trafficTableCondition = trafficTableCondition;
	}
	public List<String> getSearchPubCompanyCodeId() {
		return searchPubCompanyCodeId;
	}
	public void setSearchPubCompanyCodeId(List<String> searchPubCompanyCodeId) {
		this.searchPubCompanyCodeId = searchPubCompanyCodeId;
	}
	public String getSearchPubCompanyCodeIdAllYn() {
		return searchPubCompanyCodeIdAllYn;
	}
	public void setSearchPubCompanyCodeIdAllYn(String searchPubCompanyCodeIdAllYn) {
		this.searchPubCompanyCodeIdAllYn = searchPubCompanyCodeIdAllYn;
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
	public List<String> getSearchApiName() {
		return searchApiName;
	}
	public void setSearchApiName(List<String> searchApiName) {
		this.searchApiName = searchApiName;
	}
	public String getSearchApiNameAllYn() {
		return searchApiNameAllYn;
	}
	public void setSearchApiNameAllYn(String searchApiNameAllYn) {
		this.searchApiNameAllYn = searchApiNameAllYn;
	}
	public List<String> getSearchApiService() {
		return searchApiService;
	}
	public void setSearchApiService(List<String> searchApiService) {
		this.searchApiService = searchApiService;
	}
	public String getSearchApiServiceAllYn() {
		return searchApiServiceAllYn;
	}
	public void setSearchApiServiceAllYn(String searchApiServiceAllYn) {
		this.searchApiServiceAllYn = searchApiServiceAllYn;
	}
	public List<String> getSearchSubCompanyCodeId() {
		return searchSubCompanyCodeId;
	}
	public void setSearchSubCompanyCodeId(List<String> searchSubCompanyCodeId) {
		this.searchSubCompanyCodeId = searchSubCompanyCodeId;
	}
	public String getSearchSubCompanyCodeIdAllYn() {
		return searchSubCompanyCodeIdAllYn;
	}
	public void setSearchSubCompanyCodeIdAllYn(String searchSubCompanyCodeIdAllYn) {
		this.searchSubCompanyCodeIdAllYn = searchSubCompanyCodeIdAllYn;
	}
	public List<String> getSearchAppName() {
		return searchAppName;
	}
	public void setSearchAppName(List<String> searchAppName) {
		this.searchAppName = searchAppName;
	}
	public String getSearchAppNameAllYn() {
		return searchAppNameAllYn;
	}
	public void setSearchAppNameAllYn(String searchAppNameAllYn) {
		this.searchAppNameAllYn = searchAppNameAllYn;
	}
	public List<String> getSearchResponseResultYn() {
		return searchResponseResultYn;
	}
	public void setSearchResponseResultYn(List<String> searchResponseResultYn) {
		this.searchResponseResultYn = searchResponseResultYn;
	}
	public String getSearchResponseResultYnAllYn() {
		return searchResponseResultYnAllYn;
	}
	public void setSearchResponseResultYnAllYn(String searchResponseResultYnAllYn) {
		this.searchResponseResultYnAllYn = searchResponseResultYnAllYn;
	}
	public List<String> getSearchApiPlanName() {
		return searchApiPlanName;
	}
	public void setSearchApiPlanName(List<String> searchApiPlanName) {
		this.searchApiPlanName = searchApiPlanName;
	}
	public String getSearchApiPlanNameAllYn() {
		return searchApiPlanNameAllYn;
	}
	public void setSearchApiPlanNameAllYn(String searchApiPlanNameAllYn) {
		this.searchApiPlanNameAllYn = searchApiPlanNameAllYn;
	}
	public String getSearchTrafficType() {
		return searchTrafficType;
	}
	public void setSearchTrafficType(String searchTrafficType) {
		this.searchTrafficType = searchTrafficType;
	}
	public String getSearchStatsType() {
		return searchStatsType;
	}
	public void setSearchStatsType(String searchStatsType) {
		this.searchStatsType = searchStatsType;
	}
	public String getSearchDateTime() {
		return searchDateTime;
	}
	public void setSearchDateTime(String searchDateTime) {
		this.searchDateTime = searchDateTime;
	}
	public String getSearchStdDate() {
		return searchStdDate;
	}
	public void setSearchStdDate(String searchStdDate) {
		this.searchStdDate = searchStdDate;
	}
	public String getSearchStdDateText() {
		return searchStdDateText;
	}
	public void setSearchStdDateText(String searchStdDateText) {
		this.searchStdDateText = searchStdDateText;
	}

	public List<String> getApiNameList() {
		return apiNameList;
	}

	public void setApiNameList(List<String> apiNameList) {
		this.apiNameList = apiNameList;
	}

	public List<String> getApiPlanNameList() {
		return apiPlanNameList;
	}

	public void setApiPlanNameList(List<String> apiPlanNameList) {
		this.apiPlanNameList = apiPlanNameList;
	}

	public List<String> getAppNameList() {
		return appNameList;
	}

	public void setAppNameList(List<String> appNameList) {
		this.appNameList = appNameList;
	}

	public String getSearchDateTimeFrom() {
		return searchDateTimeFrom;
	}

	public void setSearchDateTimeFrom(String searchDateTimeFrom) {
		this.searchDateTimeFrom = searchDateTimeFrom;
	}

	public String getSearchDateTimeTo() {
		return searchDateTimeTo;
	}

	public void setSearchDateTimeTo(String searchDateTimeTo) {
		this.searchDateTimeTo = searchDateTimeTo;
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

	public String getCntApiDurationTotal() {
		return cntApiDurationTotal;
	}

	public void setCntApiDurationTotal(String cntApiDurationTotal) {
		this.cntApiDurationTotal = cntApiDurationTotal;
	}

	public String getCntApiDurationY() {
		return cntApiDurationY;
	}

	public void setCntApiDurationY(String cntApiDurationY) {
		this.cntApiDurationY = cntApiDurationY;
	}

	public String getCntApiDurationN() {
		return cntApiDurationN;
	}

	public void setCntApiDurationN(String cntApiDurationN) {
		this.cntApiDurationN = cntApiDurationN;
	}

	public String getApiDurationY() {
		return apiDurationY;
	}

	public void setApiDurationY(String apiDurationY) {
		this.apiDurationY = apiDurationY;
	}

	public String getApiDurationN() {
		return apiDurationN;
	}

	public void setApiDurationN(String apiDurationN) {
		this.apiDurationN = apiDurationN;
	}

	public String getSifDurationY() {
		return sifDurationY;
	}

	public void setSifDurationY(String sifDurationY) {
		this.sifDurationY = sifDurationY;
	}

	public String getSifDurationN() {
		return sifDurationN;
	}

	public void setSifDurationN(String sifDurationN) {
		this.sifDurationN = sifDurationN;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}