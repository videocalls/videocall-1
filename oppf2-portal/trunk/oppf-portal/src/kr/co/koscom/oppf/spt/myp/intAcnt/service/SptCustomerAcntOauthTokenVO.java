package kr.co.koscom.oppf.spt.myp.intAcnt.service;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptCustomerAcntOauthTokenVO.java
* @Comment  : 통합계좌 OAUTH토큰
* @author   : 이환덕
* @since    : 2017.03.08
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  이환덕        최초생성
*
*/
public class SptCustomerAcntOauthTokenVO {
    
    /* 공통 */
    private String customerRegNo; //사용자번호
    
    /* DB정보 & Response OAuth Token */
    private String oauthSeq;       //등록번호(yyyymmdd000)
    private String accessToken; //엑세스토큰
    private String refreshToken; //리플레쉬토큰
    private String scope;       //스코프
    private String tokenType;       //토큰타입
    private String expiresIn;   //유효기간
    private String useYn;       //사용여부(Y:사용,N:사용안함)
    private String deleteDate;  //삭제일시
    private String updateDate;  //수정일시
    private String updateId;    //수정자ID
    private String createDate;  //생성일시
    private String createId;    //생성자ID

    public String getCustomerRegNo() {
        return customerRegNo;
    }

    public void setCustomerRegNo(String customerRegNo) {
        this.customerRegNo = customerRegNo;
    }

    public String getOauthSeq() {
        return oauthSeq;
    }

    public void setOauthSeq(String oauthSeq) {
        this.oauthSeq = oauthSeq;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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
}
