package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;
import java.util.List;

/**
* @Project  : 코스콤 오픈플렛폼
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
public class AccListAttributeREQVO extends ComDefaultListVO implements Serializable {
    private List<AccListREQVO> account;
    private String companyNameEngAlias;
    private String trCode;
    private String companyCode;
    private String requestUserId;
    private String requestUniqueId;
    private String userCi;
    private String userDn;
    
    /* OAuth Token 관련 */
    private String Accept;
    private String apiKey;
    private String Authorization;
    private String accessToken;
    
    public String getRequestUserId() {
        return requestUserId;
    }
    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }
    public String getRequestUniqueId() {
        return requestUniqueId;
    }
    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }
    public String getUserCi() {
        return userCi;
    }
    public void setUserCi(String userCi) {
        this.userCi = userCi;
    }
    public String getUserDn() {
        return userDn;
    }
    public void setUserDn(String userDn) {
        this.userDn = userDn;
    }
    public List<AccListREQVO> getAccount() {
        return account;
    }
    public void setAccount(List<AccListREQVO> account) {
        this.account = account;
    }
    public String getCompanyNameEngAlias() {
        return companyNameEngAlias;
    }
    public void setCompanyNameEngAlias(String companyNameEngAlias) {
        this.companyNameEngAlias = companyNameEngAlias;
    }
    public String getTrCode() {
        return trCode;
    }
    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getAccept() {
        return Accept;
    }
    public void setAccept(String accept) {
        Accept = accept;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getAuthorization() {
        return Authorization;
    }
    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
   
}
