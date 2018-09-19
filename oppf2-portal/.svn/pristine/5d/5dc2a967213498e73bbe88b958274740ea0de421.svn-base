package kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.myp.cptMyInfo.service.CptMyInfoVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptMyInfoDAO.java
* @Comment  : [기업회원정보]정보관리를 위한 DAO 클래스
* @author   : 포털 유제량
* @since    : 2016.06.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.29  유제량        최초생성
*
*/
@Repository("CptMyInfoDAO")
public class CptMyInfoDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CptMyInfoDAO.class);
    
    /**
     * @Method Name        : selectCptMyInfo
     * @Method description : [기업회원정보상세:상세]정보를 조회한다.
     * @param              : CptMyInfoVO
     * @return             : CptMyInfoVO
     * @throws             : Exception
     */
    public CptMyInfoVO selectCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception{
        return (CptMyInfoVO) select("cpt.CptMyInfoDAO.selectCptMyInfo", cptMyInfoVO);
    }
    
    /**
     * @Method Name        : selectCheckPw
     * @Method description : [기업회원정보:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckPw(CptMyInfoVO cptMyInfoVO) throws Exception{
        String rs = (String) select("cpt.CptMyInfoDAO.selectCheckPw",cptMyInfoVO);
        return rs;
    }
     
     /**
      * @Method Name        : updateCptMyInfo
      * @Method description : [기업회원정보:수정]을 한다.
      * @param              : CptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateCptMyInfo(CptMyInfoVO cptMyInfoVO) throws Exception{
         int rs = update("cpt.CptMyInfoDAO.updateCptMyInfo", cptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : updateCptMyPwMod
      * @Method description : [기업회원정보:비밀번호변경]을 한다.
      * @param              : CptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateCptMyPwMod(CptMyInfoVO cptMyInfoVO) throws Exception{
         int rs = update("cpt.CptMyInfoDAO.updateCptMyPwMod", cptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : insertCptMyPwMod
      * @Method description : [기업회원정보:비밀번호변경]을 한다.(history update)
      * @param              : CptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public void insertCptMyPwMod(CptMyInfoVO cptMyInfoVO) throws Exception{
         insert("cpt.CptMyInfoDAO.insertCptMyPwMod", cptMyInfoVO);         
     }
     
     /**
      * @Method Name        : updateCptMyInfoCompanyProfile
      * @Method description : [기업회원정보:기업정보 변경]을 한다.
      * @param              : CptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int updateCptMyInfoCompanyProfile(CptMyInfoVO cptMyInfoVO) throws Exception{
         int rs = update("cpt.CptMyInfoDAO.updateCptMyInfoCompanyProfile", cptMyInfoVO);
         return rs;
     }
     
     /**
      * @Method Name        : insertCptMyInfoCompanyProfileHist
      * @Method description : [기업회원정보:기업정보 변경]을 한다.(history insert)
      * @param              : CptMyInfoVO
      * @return             : int
      * @throws             : Exception
      */
     public int insertCptMyInfoCompanyProfileHist(CptMyInfoVO cptMyInfoVO) throws Exception{
         return (Integer) update("cpt.CptMyInfoDAO.insertCptMyInfoCompanyProfileHist", cptMyInfoVO);         
     }
     
     
     /**
      * @Method Name        : selectCptOperatorInfoProfile
      * @Method description : [기업회원정보:기본]정보를 조회한다.
      * @param              : CptMyInfoVO
      * @return             : CptMyInfoVO
      * @throws             : Exception
      */
     public CptMyInfoVO selectCptOperatorInfoProfile(CptMyInfoVO paramVO) throws Exception{
         return (CptMyInfoVO) select("cpt.CptMyInfoDAO.selectCptOperatorInfoProfile",paramVO);
     }
    
}
