package kr.co.koscom.oppf.cmm.oauth.service.impl;

import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;




/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ComOauthTokenServiceImpl.java
* @Comment  : [OAuthToken]정보관리를 위한 Service 클래스
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
@Service("ComOauthTokenService")
public class ComOauthTokenServiceImpl implements ComOauthTokenService{
    
    @Resource(name = "ComOauthTokenDAO")
    private ComOauthTokenDAO comOauthTokenDAO;
    
   /**
     * @Method Name        : selectComOauthTokenList
     * @Method description : [OAuthToken]목록정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : List<ComOauthTokenVO>
     * @throws             : Exception
     */
    @Override
    public List<ComOauthTokenVO> selectComOauthTokenList(ComOauthTokenVO paramVO) throws Exception{
        return (List<ComOauthTokenVO>) comOauthTokenDAO.selectComOauthTokenList(paramVO);
    }
    
   /**
     * @Method Name        : selectComOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : ComOauthTokenVO
     * @throws             : Exception
     */
    @Override
    public ComOauthTokenVO selectComOauthTokenInfo(ComOauthTokenVO paramVO) throws Exception{
        ComOauthTokenVO rsComOauthTokenVO = (ComOauthTokenVO) comOauthTokenDAO.selectComOauthTokenInfo(paramVO);
        return rsComOauthTokenVO;
    }


  /**
    * @Method Name        : insertComOauthToken
    * @Method description : [OAuthToken]정보를 등록한다.
    * @param              : ComOauthTokenVO
    * @return             : String regNo 등록번호
    * @throws             : Exception
    */
   @Override
   @Transactional
   public String insertComOauthToken(ComOauthTokenVO paramVO) throws Exception{
       if(OppfStringUtil.isEmpty(paramVO.getClientId())){
           String clientId = OppfProperties.getProperty("Globals.oauth.token.clientId");
           paramVO.setClientId(clientId);
       }
       if(OppfStringUtil.isEmpty(paramVO.getSecretId())){
           String secretId = OppfProperties.getProperty("Globals.oauth.token.secretId");
           paramVO.setSecretId(secretId);
       }
       
       if(OppfStringUtil.isEmpty(paramVO.getScope())){
           String scope = OppfProperties.getProperty("Globals.oauth.token.scope");
           paramVO.setScope(scope);
       }
       String regNo = comOauthTokenDAO.insertComOauthToken(paramVO);
       return regNo;
   }
   
  /**
    * @Method Name        : updateComOauthToken
    * @Method description : [OAuthToken]정보를 수정한다.
    * @param              : ComOauthTokenVO
    * @return             : int
    * @throws             : Exception
    */
   @Override
   @Transactional
   public int updateComOauthToken(ComOauthTokenVO paramVO) throws Exception{
       return comOauthTokenDAO.updateComOauthToken(paramVO);
   }

}
