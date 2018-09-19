package kr.co.koscom.oppf.spt.cmm.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.spt.cmm.service.SptOauthLoginVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : SptLoginDAO.java
* @Comment  : 일반사용자 LoginDAO
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
@Repository("SptLoginDAO")
@Slf4j
public class SptLoginDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectLoginProfile
     * @Method description : 로그인 정보를 조회한다.
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectLoginProfile(SptLoginVO sptLoginVO) throws Exception{
        return (SptLoginVO) select("spt.LoginDAO.selectLoginProfile", sptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCnt
     * @Method description : 로그인 실패 카운트를 update한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCnt(SptLoginVO sptLoginVO) throws Exception{

    	return update("spt.LoginDAO.updateLoginFailCnt", sptLoginVO);
    }
    
    /**
     * @Method Name        : insertLoginHist
     * @Method description : 로그인 hist를 insert한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertLoginHist(SptLoginVO sptLoginVO) throws Exception{
    	return update("spt.LoginDAO.insertLoginHist", sptLoginVO);
    }
    
    /**
     * @Method Name        : selectIdFind
     * @Method description : 아이디 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectIdFind(SptLoginVO sptLoginVO) throws Exception{
        return (SptLoginVO) select("spt.LoginDAO.selectIdFind", sptLoginVO);
    }
    
    /**
     * @Method Name        : selectPwFind
     * @Method description : 비밀번호 찾기
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectPwFind(SptLoginVO sptLoginVO) throws Exception{
        return (SptLoginVO) select("spt.LoginDAO.selectPwFind", sptLoginVO);
    }
    
    /**
     * @Method Name        : updateTmpPassword
     * @Method description : 임시비밀번호 발급
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTmpPassword(SptLoginVO sptLoginVO) throws Exception{
    	return update("spt.LoginDAO.updateTmpPassword", sptLoginVO);
    }
    
    /**
     * @Method Name        : updateLoginFailCntHalt
     * @Method description : 로그인 실패 카운트를 update하고 회원상태를 활성에서 정지상태로 update합니다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateLoginFailCntHalt(SptLoginVO sptLoginVO) throws Exception{
        return update("spt.LoginDAO.updateLoginFailCntHalt", sptLoginVO);
    }

    /**
     * @Method Name        : selectOtpCheck
     * @Method description : OTP 사용여부 확인
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    public SptLoginVO selectOtpCheck(SptLoginVO sptLoginVO) throws Exception{
        return (SptLoginVO) select("spt.LoginDAO.selectOtpCheck", sptLoginVO);
    }

    /**
     * @Method Name        : insertOauthTermsProfile
     * @Method description : Oauth 로그인 등록 이력.
     * @param              : sptOauthLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertOauthTermsProfile(SptOauthLoginVO sptOauthLoginVO) throws Exception{
        return (String) insert("spt.LoginDAO.insertOauthTermsProfile", sptOauthLoginVO);
    }

    /**
     * @Method Name        : insertOauthTermsProfile
     * @Method description : Oauth 로그인 등록 스코프 이력.
     * @param              : sptOauthLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertOauthScopeProfile(SptOauthLoginVO sptOauthLoginVO) throws Exception{
        return update("spt.LoginDAO.insertOauthScopeProfile", sptOauthLoginVO);
    }
    

    /**
     * @Method Name        : updateCustomerJoinType
     * @Method description : 가입경로 update한다.
     * @param              : SptLoginVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCustomerJoinType(SptLoginVO sptLoginVO) throws Exception{

    	return update("spt.LoginDAO.updateCustomerJoinType", sptLoginVO);
    }
   
}
