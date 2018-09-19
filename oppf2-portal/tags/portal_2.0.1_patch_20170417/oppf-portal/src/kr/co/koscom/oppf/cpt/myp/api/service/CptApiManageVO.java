package kr.co.koscom.oppf.cpt.myp.api.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptApiManageVO.java
* @Comment  : 기업사용자의 api 관리를 위한 VO
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
public class CptApiManageVO extends ComDefaultListVO{
	/* 사용자 정보 */
	private String companyProfileRegNo;
	private String operatorProfileRegNo;
	
	/**
	 * com_api_view
	 */
	private String apiId;
	private String apiName;
	private String routingUri;
	
	/**
	 * com_api_info
	 */
	private String apiCategory;
	private String companyCodeId;
	private String apiTitle;
	private String apiDescription;
	private String apiAccountYn;
	private String apiContractCode;
	private String apiContractDate;
	private String apiTermsStartDate;
	private String apiTermsExpireDate;
	private String exposureYn;
    private int exposureOrder;
	
    private String apiAccountYnName;
	private String apiCategoryName;
	private String apiContractCodeName;
	private String companyNameKor;
	private String createIdName;
	
	/**
	 * 검색조건
	 */
	//excel type = excel : 엑셀, down: 다운
	private String excelType;
	
	//api 종류
	private List<String> searchApiCategory;
	private String searchApiCategoryAllYn;
	
	//api 계약여부
	private List<String> searchApiContractCode;
	private String searchApiContractCodeAllYn;	
		
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
	public String getRoutingUri() {
		return routingUri;
	}
	public void setRoutingUri(String routingUri) {
		this.routingUri = routingUri;
	}
	public String getApiCategory() {
		return apiCategory;
	}
	public void setApiCategory(String apiCategory) {
		this.apiCategory = apiCategory;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getApiTitle() {
		return apiTitle;
	}
	public void setApiTitle(String apiTitle) {
		this.apiTitle = apiTitle;
	}
	public String getApiDescription() {
		return apiDescription;
	}
	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}
	public String getApiAccountYn() {
		return apiAccountYn;
	}
	public void setApiAccountYn(String apiAccountYn) {
		this.apiAccountYn = apiAccountYn;
	}
	public String getApiContractCode() {
		return apiContractCode;
	}
	public void setApiContractCode(String apiContractCode) {
		this.apiContractCode = apiContractCode;
	}
	public String getApiContractDate() {
		return apiContractDate;
	}
	public void setApiContractDate(String apiContractDate) {
		this.apiContractDate = apiContractDate;
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
	public String getApiAccountYnName() {
		return apiAccountYnName;
	}
	public void setApiAccountYnName(String apiAccountYnName) {
		this.apiAccountYnName = apiAccountYnName;
	}
	public String getApiCategoryName() {
		return apiCategoryName;
	}
	public void setApiCategoryName(String apiCategoryName) {
		this.apiCategoryName = apiCategoryName;
	}
	public String getApiContractCodeName() {
		return apiContractCodeName;
	}
	public void setApiContractCodeName(String apiContractCodeName) {
		this.apiContractCodeName = apiContractCodeName;
	}
	public String getCompanyNameKor() {
		return companyNameKor;
	}
	public void setCompanyNameKor(String companyNameKor) {
		this.companyNameKor = companyNameKor;
	}
	public String getCreateIdName() {
		return createIdName;
	}
	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}
	public String getExcelType() {
		return excelType;
	}
	public void setExcelType(String excelType) {
		this.excelType = excelType;
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
	public List<String> getSearchApiContractCode() {
		return searchApiContractCode;
	}
	public void setSearchApiContractCode(List<String> searchApiContractCode) {
		this.searchApiContractCode = searchApiContractCode;
	}
	public String getSearchApiContractCodeAllYn() {
		return searchApiContractCodeAllYn;
	}
	public void setSearchApiContractCodeAllYn(String searchApiContractCodeAllYn) {
		this.searchApiContractCodeAllYn = searchApiContractCodeAllYn;
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