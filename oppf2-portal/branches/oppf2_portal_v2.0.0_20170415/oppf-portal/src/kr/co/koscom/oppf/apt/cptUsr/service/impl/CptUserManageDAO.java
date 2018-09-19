package kr.co.koscom.oppf.apt.cptUsr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageVO;
import kr.co.koscom.oppf.apt.cptUsr.service.CptUserManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptUserManageDAO.java
* @Comment  : 기업 회원 관리를 위한 DAO
* @author   : 신동진
* @since    : 2016.06.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.24  신동진        최초생성
*
*/
@Repository("CptUserManageDAO")
public class CptUserManageDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CptUserManageDAO.class);
    
    /**
     * @Method Name        : selectCptUserManageList
     * @Method description : 기업 회원 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : List<CptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptUserManageVO> selectCptUserManageList(CptUserManageVO cptUserManageVO) throws Exception{
        return (List<CptUserManageVO>) list("apt.CptUserManageDAO.selectCptUserManageList", cptUserManageVO);
    }
    
    /**
     * @Method Name        : selectCptUserManageListCnt
     * @Method description : 기업 회원 목록의 total cnt를 조회한다.
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectCptUserManageListCnt(CptUserManageVO cptUserManageVO) throws Exception{
        return (Integer) select("apt.CptUserManageDAO.selectCptUserManageListCnt", cptUserManageVO);
    }
    
    /**
     * @Method Name        : selectCptUserManageListExcel
     * @Method description : 기업 회원 excel 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : List<CptUserManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptUserManageVO> selectCptUserManageListExcel(CptUserManageVO cptUserManageVO) throws Exception{
        return (List<CptUserManageVO>) list("apt.CptUserManageDAO.selectCptUserManageListExcel", cptUserManageVO);
    }
    
    /**
     * @Method Name        : selectCptUserManageDtl
     * @Method description : 기업회원조회 상세를 조회한다.
     * @param              : CptUserManageVO
     * @return             : CptUserManageVO
     * @throws             : Exception
     */
    public CptUserManageVO selectCptUserManageDtl(CptUserManageVO cptUserManageVO) throws Exception{
        return (CptUserManageVO) select("apt.CptUserManageDAO.selectCptUserManageDtl", cptUserManageVO);
    }
    
    /**
     * @Method Name        : selectCptUserManageDtlCompany
     * @Method description : 기업회원조회 상세의 기업정보를 조회한다.
     * @param              : CptUserManageVO
     * @return             : CptUserManageVO
     * @throws             : Exception
     */
    public CptUserManageVO selectCptUserManageDtlCompany(CptUserManageVO cptUserManageVO) throws Exception{
        return (CptUserManageVO) select("apt.CptUserManageDAO.selectCptUserManageDtlCompany", cptUserManageVO);
    }
    
    /**
     * @Method Name        : checkCptUserManageOperatorId
     * @Method description : 기업회원 ID 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorId(CptUserManageVO cptUserManageVO) throws Exception{
        return (Integer) select("apt.CptUserManageDAO.checkCptUserManageOperatorId", cptUserManageVO);
    }
    
    /**
     * @Method Name        : checkCptUserManageOperatorEmail
     * @Method description : 기업회원 email 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorEmail(CptUserManageVO cptUserManageVO) throws Exception{
        return (Integer) select("apt.CptUserManageDAO.checkCptUserManageOperatorEmail", cptUserManageVO);
    }
    
    /**
     * @Method Name        : insertCptUserManage
     * @Method description : 기업회원 정보 insert
     * @param              : CptUserManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCptUserManage(CptUserManageVO cptUserManageVO) throws Exception{
    	return (String) insert("apt.CptUserManageDAO.insertCptUserManage", cptUserManageVO);
    }
    
    /**
     * @Method Name        : updateCptUserManage
     * @Method description : 기업회원 정보 update
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptUserManage(CptUserManageVO cptUserManageVO) throws Exception{
    	return update("apt.CptUserManageDAO.updateCptUserManage", cptUserManageVO);
    }
    
    /**
     * @Method Name        : insertCptUserManageHist
     * @Method description : 기업회원 정보 hist insert
     * @param              : CptUserManageVO
     * @return             : String
     * @throws             : Exception
     */
    public int insertCptUserManageHist(CptUserManageVO cptUserManageVO) throws Exception{
    	return update("apt.CptUserManageDAO.insertCptUserManageHist", cptUserManageVO);
    }
    
    /**
     * @Method Name        : saveCptUserManagePwd
     * @Method description : 임시비밀번호 발급
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveCptUserManagePwd(CptUserManageVO cptUserManageVO) throws Exception{
    	return update("apt.CptUserManageDAO.saveCptUserManagePwd", cptUserManageVO);
    }
    
}
