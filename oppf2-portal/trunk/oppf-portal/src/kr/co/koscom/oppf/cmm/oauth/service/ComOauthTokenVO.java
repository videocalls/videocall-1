package kr.co.koscom.oppf.cmm.oauth.service;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : ComOauthTokenInfoVO.java
* @Comment  : 공통 OAUTH토큰
* @author   : 이환덕
* @since    : 2016.06.21
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  이환덕        최초생성
*
*/
public class ComOauthTokenVO{
    
    /* 공통 */
    private String customerRegNo; //사용자번호
    
    /* DB정보 & Response OAuth Token */
    private String regNo;       //등록번호(yyyymmdd000)
    private String accessToken; //엑세스토큰
    private String clientId;    //클라이언트ID(=API Key)
    private String secretId;    //클라이언트시크릿ID
    private String scope;       //유효기간
    private String useYn;       //사용여부(Y:사용,N:사용안함)
    private String deleteDate;  //삭제일시
    private String updateDate;  //수정일시
    private String updateId;    //수정자ID
    private String createDate;  //생성일시
    private String createId;    //생성자ID
    private String tokenType;
    private String expiresIn;
    
    /* Response OAuth Token */
    private String access_token;
    private String token_type;
    private int expires_in;
    
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }
    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getSecretId() {
        return secretId;
    }
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getDeleteDate() {
        return deleteDate;
    }
    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateId() {
        return createId;
    }
    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public String getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public int getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
    
}
