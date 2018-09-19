package kr.co.koscom.oppf.spt.myp.intAcnt.service;

/**
 * @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
 * @FileName : IntAcntOauthService.java
 * @Comment  : [OAuthToken]통합계좌 Service 인터페이스
 * @author   : 포털 이희태
 * @since    : 2017.03.08
 * @version  : 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 * 수정일                수정자        수정내용
 * ----------- ------  ----------
 * 2017.03.08  이희태        최초생성
 *
 */
public interface IntAcntOauthService {

    /**
     * @Method Name        : selectUserOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @param              : tokenCheck
     * @return             : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    public SptCustomerAcntOauthTokenVO selectCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO, boolean tokenCheck) throws Exception;

    /**
     * @Method Name        : insertCustomerAcntOauthToken
     * @Method description : [OAuthToken]정보를 등록한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    public String insertCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception;

    /**
     * @Method Name        : deleteCustomerAcntOauthToken
     * @Method description : [OAuthToken]정보를 삭제한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    public int deleteCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception;

}
