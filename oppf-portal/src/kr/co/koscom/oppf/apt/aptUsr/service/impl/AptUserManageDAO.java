package kr.co.koscom.oppf.apt.aptUsr.service.impl;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptUserManageDAO.java
* @Comment  : admin 포털 회원관리 DAO
* @author   : 신동진
* @since    : 2016.05.09
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.09  신동진        최초생성
*
*/
@Repository("AptUserManageDAO")
public class AptUserManageDAO extends ComAbstractDAO{

    /**
     * @Method Name        : selectAptUserList
     * @Method description : admin 포털 회원 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : List<AptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<AptUserManageVO> selectAptUserList(AptUserManageVO userManageVO) throws Exception{
        return (List<AptUserManageVO>) list("apt.AptUserManageDAO.selectAptUserList", userManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserListCnt
     * @Method description : admin 포털 회원 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : List<AptUserManageVO>
     * @throws             : Exception
     */
    public int selectAptUserListCnt(AptUserManageVO userManageVO) throws Exception{
        return (Integer) select("apt.AptUserManageDAO.selectAptUserListCnt", userManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserListExcel
     * @Method description : admin 포털 회원 Excel 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : List<AptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<AptUserManageVO> selectAptUserListExcel(AptUserManageVO userManageVO) throws Exception{
        return (List<AptUserManageVO>) list("apt.AptUserManageDAO.selectAptUserListExcel", userManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserDtl
     * @Method description : admin 포털 회원 상세를 조회한다.
     * @param              : AptUserManageVO
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
    public AptUserManageVO selectAptUserDtl(AptUserManageVO userManageVO) throws Exception{
        return (AptUserManageVO) select("apt.AptUserManageDAO.selectAptUserDtl", userManageVO);
    }
    
    /**
     * @Method Name        : updateAptUser
     * @Method description : admin 포털 회원정보 수정
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptUser(AptUserManageVO userManageVO) throws Exception{
    	return update("apt.AptUserManageDAO.updateAptUser", userManageVO);
    }
    
    /**
     * @Method Name        : insertAptUsertHist
     * @Method description : admin 포털 회원정보 수정 hist를 insert한다.
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertAptUsertHist(AptUserManageVO userManageVO) throws Exception{
    	return update("apt.AptUserManageDAO.insertAptUsertHist", userManageVO);
    }
    
    /**
     * @Method Name        : updateAptUserTmpPwd
     * @Method description : 임시비밀번호 발급
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateAptUserTmpPwd(AptUserManageVO userManageVO) throws Exception{
    	return update("apt.AptUserManageDAO.updateAptUserTmpPwd", userManageVO);
    }
    
    /**
     * @Method Name        : selectAptUserIdChk
     * @Method description : admin 포털 id 중복확인
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectAptUserIdChk(AptUserManageVO userManageVO) throws Exception{
    	return (Integer) select("apt.AptUserManageDAO.selectAptUserIdChk", userManageVO);
    }
    
    /**
     * @Method Name        : insertAptUserReg
     * @Method description : admin 포털 회원 등록
     * @param              : AptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertAptUserReg(AptUserManageVO userManageVO) throws Exception{
    	return (String) insert("apt.AptUserManageDAO.insertAptUserReg", userManageVO);
    }
    
}
