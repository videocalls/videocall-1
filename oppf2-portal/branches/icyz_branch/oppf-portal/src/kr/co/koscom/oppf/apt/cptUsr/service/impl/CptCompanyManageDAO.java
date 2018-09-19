package kr.co.koscom.oppf.apt.cptUsr.service.impl;

import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptCompanyManageDAO.java
* @Comment  : 기업정보 관리를 위한 DAO
* @author   : 신동진
* @since    : 2016.06.21
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  신동진        최초생성
*
*/
@Repository("CptCompanyManageDAO")
public class CptCompanyManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCptCompanyManageList
     * @Method description : 기업정보관리 목록을 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : List<CptCompanyManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptCompanyManageVO> selectCptCompanyManageList(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (List<CptCompanyManageVO>) list("apt.CptCompanyManageDAO.selectCptCompanyManageList", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : selectCptCompanyManageListCnt
     * @Method description : 기업정보관리 목록의 total cnt를 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectCptCompanyManageListCnt(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (Integer) select("apt.CptCompanyManageDAO.selectCptCompanyManageListCnt", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : selectCptCompanyManageListExcel
     * @Method description : 기업정보관리 excel 목록을 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : List<CptCompanyManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptCompanyManageVO> selectCptCompanyManageListExcel(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (List<CptCompanyManageVO>) list("apt.CptCompanyManageDAO.selectCptCompanyManageListExcel", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : selectCptCompanyCodePopupList
     * @Method description : 기업 코드 팝업 리스트 조회
     * @param              : CptCompanyManageVO
     * @return             : List<CptCompanyManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptCompanyManageVO> selectCptCompanyCodePopupList(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (List<CptCompanyManageVO>) list("apt.CptCompanyManageDAO.selectCptCompanyCodePopupList", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : sselectCptCompanyCodePopupListCnt
     * @Method description : 기업 코드 팝업 리스트의 total cnt를 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int sselectCptCompanyCodePopupListCnt(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (Integer) select("apt.CptCompanyManageDAO.sselectCptCompanyCodePopupListCnt", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : selectCptCompanyManageDtl
     * @Method description : 기업정보관리 상세를 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : CptCompanyManageVO
     * @throws             : Exception
     */
    public CptCompanyManageVO selectCptCompanyManageDtl(CptCompanyManageVO cptCompanyManageVO) throws Exception{
        return (CptCompanyManageVO) select("apt.CptCompanyManageDAO.selectCptCompanyManageDtl", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : insertCptCompanyManage
     * @Method description : 기업정보관리 상세 insert
     * @param              : CptCompanyManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCptCompanyManage(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	return (String) insert("apt.CptCompanyManageDAO.insertCptCompanyManage", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : updateCptCompanyManage
     * @Method description : 기업정보관리 상세 update
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptCompanyManage(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	return update("apt.CptCompanyManageDAO.updateCptCompanyManage", cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : insertCptCompanyManageHist
     * @Method description : 기업정보관리 상세 hist insert
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertCptCompanyManageHist(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	return update("apt.CptCompanyManageDAO.insertCptCompanyManageHist", cptCompanyManageVO);
    }
    
    
}
