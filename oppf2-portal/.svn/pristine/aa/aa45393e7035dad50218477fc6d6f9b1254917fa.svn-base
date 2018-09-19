package kr.co.koscom.oppf.cmm.oauth.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ComOauthTokenDAO.java
* @Comment  : [OAuthToken]정보관리를 위한 DAO 클래스
* @author   : 포털 이환덕
* @since    : 2016.06.21
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  이환덕        최초생성
*
*/
@Repository("ComOauthTokenDAO")
public class ComOauthTokenDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(ComOauthTokenDAO.class);
   
   /**
     * @Method Name        : selectComOauthTokenList
     * @Method description : [OAuthToken]목록정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : List<ComOauthTokenVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ComOauthTokenVO> selectComOauthTokenList(ComOauthTokenVO paramVO) throws Exception{
        return (List<ComOauthTokenVO>) list("cmm.oauth.ComOauthTokenDAO.selectComOauthTokenList",paramVO);
    }
   
   /**
     * @Method Name        : selectComOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : ComOauthTokenVO
     * @return             : ComOauthTokenVO
     * @throws             : Exception
     */
    public ComOauthTokenVO selectComOauthTokenInfo(ComOauthTokenVO paramVO) throws Exception{
        ComOauthTokenVO rsComOauthTokenVO = (ComOauthTokenVO) select("cmm.oauth.ComOauthTokenDAO.selectComOauthTokenInfo",paramVO);
        return rsComOauthTokenVO;
    }
   
  /**
    * @Method Name        : insertComOauthToken
    * @Method description : [OAuthToken]정보를 등록한다.
    * @param              : ComOauthTokenVO
    * @return             : String reg_no 등록번호
    * @throws             : Exception
    */
    public String insertComOauthToken(ComOauthTokenVO paramVO) throws Exception{
        return (String) insert("cmm.oauth.ComOauthTokenDAO.insertComOauthToken",paramVO);
    }
    
  /**
    * @Method Name        : updateComOauthToken
    * @Method description : [OAuthToken]정보를 수정한다.
    * @param              : ComOauthTokenVO
    * @return             : int
    * @throws             : Exception
    */
    public int updateComOauthToken(ComOauthTokenVO paramVO) throws Exception{
        return (int) update("cmm.oauth.ComOauthTokenDAO.updateComOauthToken",paramVO);
    }
}
