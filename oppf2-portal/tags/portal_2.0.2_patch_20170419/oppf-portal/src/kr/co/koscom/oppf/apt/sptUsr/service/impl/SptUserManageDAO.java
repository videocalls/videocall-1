package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

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
    
    private static final Logger log = Logger.getLogger(SptUserManageDAO.class);
    
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
     * @Method Name        : selectSptUserDeleteList
     * @Method description : 탙퇴한 일반 회원중 발급한 가상계좌가 있는 회원 목록을 조회한다.
     * @return             : List<SptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserManageVO> selectSptUserDeleteList() throws Exception{
        return (List<SptUserManageVO>) list("apt.SptUserManageDAO.selectSptUserDeleteList");
    }

    /**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원정보 수정
     * @param              : SptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptUserWithdrawal(SptUserManageVO sptUserManageVO) throws Exception{
        return update("apt.SptUserManageDAO.updateSptUserWithdrawal", sptUserManageVO);
    }
}
