package kr.co.koscom.oppf.cpt.cmm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.cmm.service.CptLoginVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CptLoginDAO.java
* @Comment  : 기업사용자 LoginDAO
* @author   : 신동진
* @since    : 2016.04.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.28  신동진        최초생성
*
*/
@Repository("CptLoginDAO")
public class CptLoginDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CptLoginDAO.class);
    
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectLoginProfile(CptLoginVO cptLoginVO) throws Exception{
        return (CptLoginVO) select("cpt.LoginDAO.selectLoginProfile", cptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(CptLoginVO cptLoginVO) throws Exception{
    	return update("cpt.LoginDAO.updateLoginFailCnt", cptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(CptLoginVO cptLoginVO) throws Exception{
    	return update("cpt.LoginDAO.insertLoginHist", cptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectIdFind(CptLoginVO cptLoginVO) throws Exception{
        return (CptLoginVO) select("cpt.LoginDAO.selectIdFind", cptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectPwFind(CptLoginVO cptLoginVO) throws Exception{
        return (CptLoginVO) select("cpt.LoginDAO.selectPwFind", cptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(CptLoginVO cptLoginVO) throws Exception{
    	return update("cpt.LoginDAO.updateTmpPassword", cptLoginVO);
    }
}
