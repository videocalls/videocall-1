package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ComContractProfileVO.java
* @Comment  : [서비스신청:API사용계약관계]정보관리를 위한 VO 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  이환덕        최초생성
*
*/
@SuppressWarnings("serial")
public class ComContractProfileVO extends ComDefaultListVO{
    
    /* API사용계약관계 테이블 관련 */
    private String contractRegNo;           /* API사용계약관계.서비스계약등록번호 */
    private String contractId;              /* API사용계약관계.서비스계약코드 */
    private String contractName;            /* API사용계약관계.서비스계약명 */
    private String subcompanyCodeId;        /* API사용계약관계.핀테크기업코드 */
    private String subcompanyName;          /* API사용계약관계.핀테크기업이름 */
    private String appId;                   /* API사용계약관계.핀테크기업앱ID코드 */
    private String appKey;                  /* API사용계약관계.핀테크앱Key */
    private String appName;                 /* API사용계약관계.앱이름 */
    private String pubcompanyCodeId;        /* API사용계약관계.금투사기업코드 */
    private String pubcompanyName;          /* API사용계약관계.금투사기업이름 */
    private String apiId;                   /* API사용계약관계.APIID */
    private String apiKey;                  /* API사용계약관계.APIKey */
    private String apiName;                 /* API사용계약관계.API이름 */
    private String contractTermsStartDate;  /* API사용계약관계.해당계약시작유효일 */
    private String contractTermsExpireDate; /* API사용계약관계.해당계약종료유효일 */
    
    public String getContractRegNo() {
        return contractRegNo;
    }
    public void setContractRegNo(String contractRegNo) {
        this.contractRegNo = contractRegNo;
    }
    public String getContractId() {
        return contractId;
    }
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getContractName() {
        return contractName;
    }
    public void setContractName(String contractName) {
        this.contractName = contractName;
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
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
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
    
    
}