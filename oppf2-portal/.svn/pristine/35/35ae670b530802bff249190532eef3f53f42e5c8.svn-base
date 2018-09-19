package kr.co.koscom.oppf.spt.cmm.service.impl;

import kr.co.koscom.oppf.spt.cmm.service.SptLoginVO;
import kr.co.koscom.oppf.spt.cmm.service.SptOauthLoginService;
import kr.co.koscom.oppf.spt.cmm.service.SptOauthLoginVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* @Project  : 코스콤 오픈플렛폼 2차
* @FileName : SptOauthLoginService.java
* @Comment  : Oauth Login service
* @author   : 이희태
* @since    : 2017.02.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.02.28  이희태        최초생성
*
*/
@Service("SptOauthLoginService")
public class SptOauthLoginServiceImpl implements SptOauthLoginService {
    
    @Resource(name = "SptLoginDAO")
    private SptLoginDAO sptLoginDAO;

    /**
     * @Method Name        : selectOtpCheck
     * @Method description : OTP 사용여부 확인
     * @param              : SptLoginVO
     * @return             : SptLoginVO
     * @throws             : Exception
     */
    @Override
    public SptLoginVO selectOtpCheck(SptLoginVO sptLoginVO) throws Exception {
        return sptLoginDAO.selectOtpCheck(sptLoginVO);
    }

    /**
     * @Method Name        : insertOauthProfile
     * @Method description : Oauth 로그인 처리 이력.
     * @param              : sptOauthLoginVO
     * @return             : int
     * @throws             : Exception
     */
    @Override
    @Transactional
    public void insertOauthProfile(SptOauthLoginVO sptOauthLoginVO) throws Exception {
        // 로그인 이력
        sptLoginDAO.insertOauthTermsProfile(sptOauthLoginVO);
        // scope 이력
        String[] scopes = sptOauthLoginVO.getScope().split(" ");
        for(String scope : scopes){
            sptOauthLoginVO.setScope(scope);
            sptLoginDAO.insertOauthScopeProfile(sptOauthLoginVO);
        }
    }
}
