package kr.co.koscom.oppf.apt.aptUsr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserInfoService;
import kr.co.koscom.oppf.apt.aptUsr.service.AptUserInfoVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserManageServiceImpl.java
* @Comment  : admin 포털 회원관리  serviceImpl
* @author   : 신동진
* @since    : 2016.05.10
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.10  신동진        최초생성
*
*/
@Service("AptUserInfoService")
public class AptUserInfoServiceImpl implements AptUserInfoService{
    
    @Resource(name = "AptUserInfoDAO")
    private AptUserInfoDAO aptUserInfoDAO;
    
    /**
     * @Method Name        : chkAptUserInfoPwConfirm
     * @Method description : admin 포탈 회원의 비밀번호 체크
     * @param              : AptUserInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int chkAptUserInfoPwConfirm(AptUserInfoVO aptUserInfoVO) throws Exception{
    	return aptUserInfoDAO.chkAptUserInfoPwConfirm(aptUserInfoVO);
    }
    
    /**
     * @Method Name        : selectAptUserDtl
     * @Method description : admin 포털 회원 상세를 조회한다.
     * @param              : AptUserManageVO
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
    public AptUserInfoVO selectAptUserInfoDtl(AptUserInfoVO aptUserInfoVO) throws Exception{
    	return aptUserInfoDAO.selectAptUserInfoDtl(aptUserInfoVO);
    }
    
    /**
     * @Method Name        : updateAptUser
     * @Method description : admin 포털 회원정보 수정
     * @param              : AptUserInfoVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateAptUserInfo(AptUserInfoVO aptUserInfoVO) throws Exception{
    	int result = aptUserInfoDAO.updateAptUserInfo(aptUserInfoVO);
    	result = aptUserInfoDAO.insertAptUserInfoHist(aptUserInfoVO);
    	
    	return result;
    }
}
