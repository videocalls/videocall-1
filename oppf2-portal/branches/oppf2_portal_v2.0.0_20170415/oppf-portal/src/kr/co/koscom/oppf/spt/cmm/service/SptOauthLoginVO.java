package kr.co.koscom.oppf.spt.cmm.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 코스콤 오픈플렛폼 2차
* @FileName : SptOauthLoginVO.java
* @Comment  : Oauth 로그인 사용자의 login VO
* @author   : 이희태
* @since    : 2017.02.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.02.28  이희태        최초생성
*
*/
@SuppressWarnings("serial")
public class SptOauthLoginVO extends ComDefaultListVO{
	//CA return sessionID
	private String sessionID;
	//회원 ID
	private String customer_id;
    //회원 비밀번호
    private String customer_password;
	//로그인 요청 클라이언트 정보
	private String clientId;
    //로그인 요청 app_name
    private String app_name;
    //로그인 요청 app_Id
    private String appId;
	//scope정보
	private String scope;
    //회원 OP 등록 번호
    private String customer_reg_no;
    //provider 등록 번호
    private String provider_seq;

    public String getSessionID() {
        return sessionID;
    }
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getCustomer_password() {
        return customer_password;
    }
    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }
    public String getApp_name() {
        return app_name;
    }
    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
    public String getCustomer_reg_no() {
        return customer_reg_no;
    }
    public void setCustomer_reg_no(String customer_reg_no) {
        this.customer_reg_no = customer_reg_no;
    }
    public String getProvider_seq() {
        return provider_seq;
    }
    public void setProvider_seq(String provider_seq) {
        this.provider_seq = provider_seq;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
}