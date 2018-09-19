package kr.co.koscom.oppf.spt.cmm.service.impl;

import kr.co.koscom.oppf.spt.cmm.service.SptLoginService;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptLoginServiceImpl.java
* @Comment  : 일반사용자 Login serviceImpl
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
@Service("SptLoginService")
public class SptLoginServiceImpl implements SptLoginService{
    
    @Resource(name = "SptLoginDAO")
    private SptLoginDAO sptLoginDAO;
        
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectLoginProfile(SptLoginVO sptLoginVO) throws Exception{
        return sptLoginDAO.selectLoginProfile(sptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(SptLoginVO sptLoginVO) throws Exception{
    	return sptLoginDAO.updateLoginFailCnt(sptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(SptLoginVO sptLoginVO) throws Exception{
    	return sptLoginDAO.insertLoginHist(sptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectIdFind(SptLoginVO sptLoginVO) throws Exception{
        return sptLoginDAO.selectIdFind(sptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectPwFind(SptLoginVO sptLoginVO) throws Exception{
    	return sptLoginDAO.selectPwFind(sptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(SptLoginVO sptLoginVO) throws Exception{
    	return sptLoginDAO.updateTmpPassword(sptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCntHalt
     * @Method description : 로그인 실패 카운트를 update하고 회원상태를 활성에서 정지상태로 update합니다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCntHalt(SptLoginVO sptLoginVO) throws Exception{
        return sptLoginDAO.updateLoginFailCntHalt(sptLoginVO);
    }
    
    /**
     * @Method Name        : updateCustomerJoinType
     * @Method description : 가입경로 update한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCustomerJoinType(SptLoginVO sptLoginVO) throws Exception{
    	return sptLoginDAO.updateCustomerJoinType(sptLoginVO);
    }

}
