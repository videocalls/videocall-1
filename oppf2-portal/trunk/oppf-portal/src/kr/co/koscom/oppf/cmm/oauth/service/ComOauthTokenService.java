package kr.co.koscom.oppf.cmm.oauth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ComOauthTokenService.java
* @Comment  : [OAuthToken]정보관리를 위한 Service 인터페이스
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
public interface ComOauthTokenService{
    
   
   /**
     * @Method Name        : selectComOauthTokenList
     * @Method description : [OAuthToken]목록정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : List<ComOauthTokenVO>
     * @throws             : Exception
     */
    public List<ComOauthTokenVO> selectComOauthTokenList(ComOauthTokenVO paramVO) throws Exception;
    
   /**
     * @Method Name        : selectComOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : ComOauthTokenVO
     * @throws             : Exception
     */
    public ComOauthTokenVO selectComOauthTokenInfo(ComOauthTokenVO paramVO) throws Exception;


   /**
     * @Method Name        : insertComOauthToken
     * @Method description : [OAuthToken]정보를 등록한다.
     * @param              : ComOauthTokenVO
     * @return             : String regNo 등록번호
     * @throws             : Exception
     */
    @Transactional
    public String insertComOauthToken(ComOauthTokenVO paramVO) throws Exception;
   
  /**
    * @Method Name        : updateComOauthToken
    * @Method description : [OAuthToken]정보를 수정한다.
    * @param              : ComOauthTokenVO
    * @return             : int
    * @throws             : Exception
    */
   @Transactional
   public int updateComOauthToken(ComOauthTokenVO paramVO) throws Exception;
}
