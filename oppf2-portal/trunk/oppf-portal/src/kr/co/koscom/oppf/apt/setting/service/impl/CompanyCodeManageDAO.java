package kr.co.koscom.oppf.apt.setting.service.impl;

import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyCodeManageDAO.java
* @Comment  : 관리자의 기업코드 관리를 위한 DAO 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Repository("CompanyCodeManageDAO")
public class CompanyCodeManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드 목록을 조회한다.
     * @param              : CompanyCodeManageVO
     * @return             : List<CompanyCodeManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CompanyCodeManageVO> selectCompanyCodeList(CompanyCodeManageVO companyCodeManageVO) throws Exception{
        return (List<CompanyCodeManageVO>) list("apt.CompanyCodeManageDAO.selectCompanyCodeList", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : selectNotiManageDetail
     * @Method description : 기업코드 상세를 조회한다.
     * @param              : NotiManageVO
     * @return             : NotiManageVO
     * @throws             : Exception
     */
    public CompanyCodeManageVO selectCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception{
        return (CompanyCodeManageVO) select("apt.CompanyCodeManageDAO.selectCompanyCodeDtl", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : chkCompanyCodeDtl
     * @Method description : 기업코드 저장가능 여부를 체크한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CompanyCodeManageVO> chkCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception{
        return (List<CompanyCodeManageVO>) list("apt.CompanyCodeManageDAO.chkCompanyCodeDtl", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : insertCompanyCodeHist
     * @Method description : 기업코드 hist 등록 
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertCompanyCodeHist(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return update("apt.CompanyCodeManageDAO.insertCompanyCodeHist", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : insertCompanyCode
     * @Method description : 기업코드 등록
     * @param              : CompanyCodeManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return (String) insert("apt.CompanyCodeManageDAO.insertCompanyCode", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : updateCompanyCode
     * @Method description : 기업코드 수정
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return update("apt.CompanyCodeManageDAO.updateCompanyCode", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : chkDeleteCompanyCode
     * @Method description : 기업코드 삭제가능 여부를 체크한다.
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int chkDeleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return (Integer) select("apt.CompanyCodeManageDAO.chkDeleteCompanyCode", companyCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteCompanyCode
     * @Method description : 기업코드 삭제
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return update("apt.CompanyCodeManageDAO.deleteCompanyCode", companyCodeManageVO);
    }
    
}
