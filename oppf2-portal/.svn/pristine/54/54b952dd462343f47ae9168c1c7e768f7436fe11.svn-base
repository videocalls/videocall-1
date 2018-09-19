package kr.co.koscom.oppf.apt.cmm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.apt.cmm.service.AptLoginVO;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : AptLoginDAO.java
* @Comment  : admin LoginDAO
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
@Repository("AptLoginDAO")
public class AptLoginDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(AptLoginDAO.class);
    
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectLoginProfile(AptLoginVO aptLoginVO) throws Exception{
        return (AptLoginVO) select("apt.LoginDAO.selectLoginProfile", aptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(AptLoginVO aptLoginVO) throws Exception{
    	return update("apt.LoginDAO.updateLoginFailCnt", aptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(AptLoginVO aptLoginVO) throws Exception{
    	return update("apt.LoginDAO.insertLoginHist", aptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectIdFind(AptLoginVO aptLoginVO) throws Exception{
        return (AptLoginVO) select("apt.LoginDAO.selectIdFind", aptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : AptLoginVO
     * @return             : AptLoginVO
     * @throws             : Exception
     */
    public AptLoginVO selectPwFind(AptLoginVO aptLoginVO) throws Exception{
        return (AptLoginVO) select("apt.LoginDAO.selectPwFind", aptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : AptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(AptLoginVO aptLoginVO) throws Exception{
        return update("apt.LoginDAO.updateTmpPassword", aptLoginVO);
    }
    
}
