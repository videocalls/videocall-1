package kr.co.koscom.oppf.apt.cmm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.cmm.service.AptLoginService;
import kr.co.koscom.oppf.apt.cmm.service.AptLoginVO;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : AptLoginServiceImpl.java
* @Comment  : admin Login serviceImpl
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
@Service("AptLoginService")
public class AptLoginServiceImpl implements AptLoginService{
    
    @Resource(name = "AptLoginDAO")
    private AptLoginDAO aptLoginDAO;
        
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectLoginProfile(AptLoginVO aptLoginVO) throws Exception{
        return aptLoginDAO.selectLoginProfile(aptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(AptLoginVO aptLoginVO) throws Exception{
    	return aptLoginDAO.updateLoginFailCnt(aptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(AptLoginVO aptLoginVO) throws Exception{
    	return aptLoginDAO.insertLoginHist(aptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectIdFind(AptLoginVO aptLoginVO) throws Exception{
        return aptLoginDAO.selectIdFind(aptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectPwFind(AptLoginVO aptLoginVO) throws Exception{
        return aptLoginDAO.selectPwFind(aptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(AptLoginVO aptLoginVO) throws Exception{
        return aptLoginDAO.updateTmpPassword(aptLoginVO);
    }
    
}
