package kr.co.koscom.oppf.apt.aptUsr.service.impl;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserInfoVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserInfoDAO.java
* @Comment  : admin 포털 내정보 관리 DAO
* @author   : 신동진
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  신동진        최초생성
*
*/
@Repository("AptUserInfoDAO")
public class AptUserInfoDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : chkAptUserInfoPwConfirm
     * @Method description : admin 포탈 회원의 비밀번호 체크
     * @param              : AptUserInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int chkAptUserInfoPwConfirm(AptUserInfoVO aptUserInfoVO) throws Exception{
    	return (Integer) select("apt.AptUserInfoDAO.chkAptUserInfoPwConfirm", aptUserInfoVO);
    }
    
    /**
     * @Method Name        : selectAptUserInfoDtl
     * @Method description : admin 포털 회원 상세를 조회한다.
     * @param              : AptUserInfoVO
     * @return             : AptUserInfoVO
     * @throws             : Exception
     */
    public AptUserInfoVO selectAptUserInfoDtl(AptUserInfoVO aptUserInfoVO) throws Exception{
        return (AptUserInfoVO) select("apt.AptUserInfoDAO.selectAptUserInfoDtl", aptUserInfoVO);
    }
    
    /**
     * @Method Name        : updateAptUserInfo
     * @Method description : admin 포털 회원정보 수정
     * @param              : AptUserInfoVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptUserInfo(AptUserInfoVO aptUserInfoVO) throws Exception{
    	return update("apt.AptUserInfoDAO.updateAptUserInfo", aptUserInfoVO);
    }
    
    /**
     * @Method Name        : insertAptUsertInfoHist
     * @Method description : admin 포털 회원정보 수정 hist를 insert한다.
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertAptUserInfoHist(AptUserInfoVO aptUserInfoVO) throws Exception{
    	return update("apt.AptUserInfoDAO.insertAptUserInfoHist", aptUserInfoVO);
    }
}
