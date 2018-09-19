package kr.co.koscom.oppf.cpt.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cpt.cmm.service.CptLoginService;
import kr.co.koscom.oppf.cpt.cmm.service.CptLoginVO;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CptLoginServiceImpl.java
* @Comment  : 기업사용자 Login serviceImpl
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
@Service("CptLoginService")
public class CptLoginServiceImpl implements CptLoginService{
    
    @Resource(name = "CptLoginDAO")
    private CptLoginDAO cptLoginDAO;
        
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectLoginProfile(CptLoginVO cptLoginVO) throws Exception{
        return cptLoginDAO.selectLoginProfile(cptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(CptLoginVO cptLoginVO) throws Exception{
    	return cptLoginDAO.updateLoginFailCnt(cptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(CptLoginVO cptLoginVO) throws Exception{
    	return cptLoginDAO.insertLoginHist(cptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectIdFind(CptLoginVO cptLoginVO) throws Exception{
        return cptLoginDAO.selectIdFind(cptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : CptLoginVO
     * @return             : CptLoginVO
     * @throws             : Exception
     */
    public CptLoginVO selectPwFind(CptLoginVO cptLoginVO) throws Exception{
    	return cptLoginDAO.selectPwFind(cptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : CptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(CptLoginVO cptLoginVO) throws Exception{
    	return cptLoginDAO.updateTmpPassword(cptLoginVO);
    }
    
}
