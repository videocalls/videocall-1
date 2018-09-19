package kr.co.koscom.oppf.cpt.myp.app.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptAppManageVO.java
* @Comment  : 기업사용자의 app 관리를 위한 VO
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
public class CptAppManageVO extends ComDefaultListVO{
	/* 사용자 정보 */
	private String companyProfileRegNo;
	private String operatorProfileRegNo;
	
	/**
	 * com_app_view
	 */
	private String appId;
	private String appName;
	private String appKey;
	private String appStatus;
	private String operatorRegId;
	private String operatorRegName;
	private String companyCodeId;
	
	private String appStatusName;
	private String companyNameKor;	
	
	/**
	 * com_app_info
	 */
	private String appCategory;
	private String appDescription;
	private String appContractCode;
	private String appContractDate;
	private String appTermsStartDate;
	private String appTermsExpireDate;
	private byte[] appIcon;
	private String appDlUrl;
	private String exposureYn;
    private int exposureOrder;
	
	private String appCategoryName;
	private String appContractCodeName;
	private String createIdName;
	
	private String isAppIcon;
	
	private String apiId;
	private String apiName;
	private String apiContractCode;
	private String apiContractCodeName;
	private String apiTermsStartDate;
	private String apiTermsExpireDate;

	/**
	 * ca_dpt_lrsapplication_view
	 * */
	private String keySecret;
	private String oauthCallbackUrl;
	private String oauthScope;
	private String oauthType;
	
	/**
	 * 검색조건
	 */
	//excel type = excel : 엑셀, down: 다운
	private String excelType;
		
	//app 구분
	private List<String> searchAppCategory;
	private String searchAppCategoryAllYn;
	
	//app 상태
	private List<String> searchAppStatus;
	private String searchAppStatusAllYn;
	
	//app 계약여부
	private List<String> searchAppContractCode;
	private String searchAppContractCodeAllYn;	
	
	//서비스 제공자
	private List<String> searchCompanyCodeId;
	private String searchCompanyCodeIdAllYn;
	
	//등록일
	private String searchDateFrom;
	private String searchDateTo;
	
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
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getOperatorRegId() {
		return operatorRegId;
	}
	public void setOperatorRegId(String operatorRegId) {
		this.operatorRegId = operatorRegId;
	}
	public String getOperatorRegName() {
		return operatorRegName;
	}
	public void setOperatorRegName(String operatorRegName) {
		this.operatorRegName = operatorRegName;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getAppStatusName() {
		return appStatusName;
	}
	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}
	public String getCompanyNameKor() {
		return companyNameKor;
	}
	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}
	public String getAppCategory() {
		return appCategory;
	}
	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	public String getAppContractCode() {
		return appContractCode;
	}
	public void setAppContractCode(String appContractCode) {
		this.appContractCode = appContractCode;
	}
	public String getAppContractDate() {
		return appContractDate;
	}
	public void setAppContractDate(String appContractDate) {
		this.appContractDate = appContractDate;
	}
	public String getAppTermsStartDate() {
		return appTermsStartDate;
	}
	public void setAppTermsStartDate(String appTermsStartDate) {
		this.appTermsStartDate = appTermsStartDate;
	}
	public String getAppTermsExpireDate() {
		return appTermsExpireDate;
	}
	public void setAppTermsExpireDate(String appTermsExpireDate) {
		this.appTermsExpireDate = appTermsExpireDate;
	}
	public byte[] getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(byte[] appIcon) {
		this.appIcon = appIcon;
	}
	public String getAppDlUrl() {
		return appDlUrl;
	}
	public void setAppDlUrl(String appDlUrl) {
		this.appDlUrl = appDlUrl;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public int getExposureOrder() {
		return exposureOrder;
	}
	public void setExposureOrder(int exposureOrder) {
		this.exposureOrder = exposureOrder;
	}
	public String getAppCategoryName() {
		return appCategoryName;
	}
	public void setAppCategoryName(String appCategoryName) {
		this.appCategoryName = appCategoryName;
	}
	public String getAppContractCodeName() {
		return appContractCodeName;
	}
	public void setAppContractCodeName(String appContractCodeName) {
		this.appContractCodeName = appContractCodeName;
	}
	public String getCreateIdName() {
		return createIdName;
	}
	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}
	public String getIsAppIcon() {
		return isAppIcon;
	}
	public void setIsAppIcon(String isAppIcon) {
		this.isAppIcon = isAppIcon;
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
	public List<String> getSearchAppCategory() {
		return searchAppCategory;
	}
	public void setSearchAppCategory(List<String> searchAppCategory) {
		this.searchAppCategory = searchAppCategory;
	}
	public String getSearchAppCategoryAllYn() {
		return searchAppCategoryAllYn;
	}
	public void setSearchAppCategoryAllYn(String searchAppCategoryAllYn) {
		this.searchAppCategoryAllYn = searchAppCategoryAllYn;
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
	public List<String> getSearchAppContractCode() {
		return searchAppContractCode;
	}
	public void setSearchAppContractCode(List<String> searchAppContractCode) {
		this.searchAppContractCode = searchAppContractCode;
	}
	public String getSearchAppContractCodeAllYn() {
		return searchAppContractCodeAllYn;
	}
	public void setSearchAppContractCodeAllYn(String searchAppContractCodeAllYn) {
		this.searchAppContractCodeAllYn = searchAppContractCodeAllYn;
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

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getOauthCallbackUrl() {
        return oauthCallbackUrl;
    }

    public void setOauthCallbackUrl(String oauthCallbackUrl) {
        this.oauthCallbackUrl = oauthCallbackUrl;
    }

    public String getOauthScope() {
        return oauthScope;
    }

    public void setOauthScope(String oauthScope) {
        this.oauthScope = oauthScope;
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType;
    }
}