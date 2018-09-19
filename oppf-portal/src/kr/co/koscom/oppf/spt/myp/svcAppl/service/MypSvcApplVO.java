package kr.co.koscom.oppf.spt.myp.svcAppl.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MypSvcApplVO.java
* @Comment  : [마이페이지>신청서비스]정보관리를 위한 VO 클래스
* @author   : 신동진
* @since    : 2016.06.11
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.11  신동진        최초생성
*
*/
@SuppressWarnings("serial")
public class MypSvcApplVO extends ComDefaultListVO{
	/* 일반회원서비스계좌프로파일 테이블 관련 */
	private String customerRegNo;
	private String customerId;
	private String serviceRegNo;
	private String accountRegNo;
	private String appId;
	private String apiId;
	private String customerRealaccountNo;
	
	/* 조회 */
	private String appName;
	private String subcompanyCodeId;
	private String subcompanyName;
	private String apiName;
	private String pubcompanyCodeId;
	private String pubcompanyName;
	private String customerVtaccountNo;
	private String customerVtaccountAlias;
	private String useYn;
	
	private String termsRegNo;
	private String termsRegNoEncryption;
	private String termsAuthYn;
	private String termsStartDate;
	private String termsExpireDate;
	private String termsAuthDateYn;
	
	private String termsDiscardDate;
	
	/* 가상계좌 삭제 시 처리 변수 */
	private String companyCodeId;
	private String termsCnt;
	private String accountCnt;

	/* 동의서 폐기 팝업 노출 앱 목록 */
	private List<String> appList;

	public List<String> getAppList() {
		return appList;
	}
	public void setAppList(List<String> appList) {
		this.appList = appList;
	}
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
	public String getAccountRegNo() {
		return accountRegNo;
	}
	public void setAccountRegNo(String accountRegNo) {
		this.accountRegNo = accountRegNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getCustomerRealaccountNo() {
		return customerRealaccountNo;
	}
	public void setCustomerRealaccountNo(String customerRealaccountNo) {
		this.customerRealaccountNo = customerRealaccountNo;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getTermsAuthYn() {
		return termsAuthYn;
	}
	public void setTermsAuthYn(String termsAuthYn) {
		this.termsAuthYn = termsAuthYn;
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
	public String getTermsAuthDateYn() {
		return termsAuthDateYn;
	}
	public void setTermsAuthDateYn(String termsAuthDateYn) {
		this.termsAuthDateYn = termsAuthDateYn;
	}
	public String getTermsDiscardDate() {
		return termsDiscardDate;
	}
	public void setTermsDiscardDate(String termsDiscardDate) {
		this.termsDiscardDate = termsDiscardDate;
	}
	public String getCompanyCodeId() {
		return companyCodeId;
	}
	public void setCompanyCodeId(String companyCodeId) {
		this.companyCodeId = companyCodeId;
	}
	public String getTermsCnt() {
		return termsCnt;
	}
	public void setTermsCnt(String termsCnt) {
		this.termsCnt = termsCnt;
	}
	public String getAccountCnt() {
		return accountCnt;
	}
	public void setAccountCnt(String accountCnt) {
		this.accountCnt = accountCnt;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}