package kr.co.koscom.oppf.cmm.service;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CommonLoginVO.java
* @Comment  : 공통 로그인 VO
* @author   : 신동진
* @since    : 2016.04.29
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  신동진        최초생성
*
*/

@SuppressWarnings("serial")
public class CmmEmailCronSvcTermsVO extends ComDefaultListVO {
    /* 금융정보 제공동의 */
	private String customerRegno = "";
	private String customerId = "";
    private String serviceRegNo = "";
    private String termsRegNo = "";
    private String termsAuthYn = "";
    private String subcompanyCodeId = "";
    private String subcompanyName = "";
    private String appId = "";
    private String appName = "";
    private String pubcompanyCodeId = "";
    private String pubcompanyName = "";
    private String apiId = "";
    private String apiName = "";
    private String customerVtaccountNo = "";
    private String customerVtaccountAlias = "";
    private String termsStartDate = "";
    private String termsExpireDate = "";
    
    private String SQL1;
    private String SQL2;
    
    private String emailSendType;
    
	public String getCustomerRegno() {
		return customerRegno;
	}
	public void setCustomerRegno(String customerRegno) {
		this.customerRegno = customerRegno;
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
	public String getTermsAuthYn() {
		return termsAuthYn;
	}
	public void setTermsAuthYn(String termsAuthYn) {
		this.termsAuthYn = termsAuthYn;
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
	public String getSQL1() {
		return SQL1;
	}
	public void setSQL1(String sQL1) {
		SQL1 = sQL1;
	}
	public String getSQL2() {
		return SQL2;
	}
	public void setSQL2(String sQL2) {
		SQL2 = sQL2;
	}
	public String getEmailSendType() {
		return emailSendType;
	}
	public void setEmailSendType(String emailSendType) {
		this.emailSendType = emailSendType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
