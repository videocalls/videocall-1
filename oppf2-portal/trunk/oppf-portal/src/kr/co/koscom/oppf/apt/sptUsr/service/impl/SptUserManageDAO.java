package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserManageDAO.java
* @Comment  : 일반 회원 관리를 위한 DAO
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
@Repository("SptUserManageDAO")
public class SptUserManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectSptUserManageList
     * @Method description : 일반 회원 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserManageList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserManageList", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserManageListCnt
     * @Method description : 일반 회원 목록의 total cnt를 조회한다.
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptUserManageListCnt(SptUserManageVO sptUserManageVO) throws Exception{
        return (Integer) select("apt.SptUserManageDAO.selectSptUserManageListCnt", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserManageListExcel
     * @Method description : 일반 회원 Excel 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserManageListExcel(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserManageListExcel", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserManageDtl
     * @Method description : 일반회원관리 상세 조회
     * @param              : SptUserManageVO
     * @return             : SptUserManageVO
     * @throws             : Exception
     */
    public SptUserManageVO selectSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{
        return (SptUserManageVO) select("apt.SptUserManageDAO.selectSptUserManageDtl", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserDeviceDtl
     * @Method description : 일반회원관리 디바이스 정보 조회
     * @param              : SptUserManageVO
     * @return             : SptUserManageVO
     * @throws             : Exception
     */
    public SptUserManageVO selectSptUserDeviceDtl(SptUserManageVO sptUserManageVO) throws Exception{
        return (SptUserManageVO) select("apt.SptUserManageDAO.selectSptUserDeviceDtl", sptUserManageVO);
    }
    
    
    
    /**
     * @Method Name        : selectSptUserManageDtlTermsList
     * @Method description : 일반회원관리 상세의 약관동의 목록 조회
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserManageDtlTermsList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserManageDtlTermsList", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserManageDtlSvcTermsList
     * @Method description : 일반회원관리 상세의 금융정보 제공동의 목록 조회
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserManageDtlSvcTermsList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserManageDtlSvcTermsList", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptUserManageDtlAccountList
     * @Method description : 일반회원관리 상세의 가상계좌목록 조회
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserManageDtlAccountList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserManageDtlAccountList", sptUserManageVO);
    }
    
    /**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원정보 수정
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{
    	return update("apt.SptUserManageDAO.updateSptUserManageDtl", sptUserManageVO);
    }
    
    /**
     * @Method Name        : insertSptUserManageDtlHist
     * @Method description : 일반회원정보 수정 hist를 insert한다.
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertSptUserManageDtlHist(SptUserManageVO sptUserManageVO) throws Exception{
    	return update("apt.SptUserManageDAO.insertSptUserManageDtlHist", sptUserManageVO);
    }

    

    /**
     * @Method Name        : selectSptWithdrawUserManageList
     * @Method description : 일반 회원 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptWithdrawUserManageList(SptUserManageVO sptUserManageVO) throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptWithdrawUserManageList", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptWithdrawUserManageListCnt
     * @Method description : 탈퇴 회원 목록의 total cnt를 조회한다.
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptWithdrawUserManageListCnt(SptUserManageVO sptUserManageVO) throws Exception{
        return (Integer) select("apt.SptUserManageDAO.selectSptWithdrawUserManageListCnt", sptUserManageVO);
    }
    
    /**
     * @Method Name        : selectSptWithdrawUserManageDtl
     * @Method description : 탈퇴회원관리 상세 조회
     * @param              : SptUserManageVO
     * @return             : SptUserManageVO
     * @throws             : Exception
     */
    public SptUserManageVO selectSptWithdrawUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception{
        return (SptUserManageVO) select("apt.SptUserManageDAO.selectSptWithdrawUserManageDtl", sptUserManageVO);
    }
    

    
    /**
     * @Method Name        : updateSptWithdrawUser
     * @Method description : 탈퇴 처리
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptWithdrawUser(SptUserManageVO sptUserManageVO) throws Exception{
    	return update("apt.SptUserManageDAO.updateSptWithdrawUser", sptUserManageVO);
    }

    
    
}
