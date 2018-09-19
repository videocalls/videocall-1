package kr.co.koscom.oppf.spt.cmm.service;

/**
* @Project  : 코스콤 오픈플렛폼 2차
* @FileName : SptOauthLoginService.java
* @Comment  : Oauth Login service
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
public interface SptOauthLoginService {

    /**
     * @Method Name        : selectOtpCheck
     * @Method description : OTP 사용여부 확인
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectOtpCheck(SptLoginVO sptLoginVO) throws Exception;

    /**
     * @Method Name        : insertOauthProfile
     * @Method description : Oauth 로그인 처리 이력.
     * @param              : sptOauthLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public void insertOauthProfile(SptOauthLoginVO sptOauthLoginVO) throws Exception;

}
