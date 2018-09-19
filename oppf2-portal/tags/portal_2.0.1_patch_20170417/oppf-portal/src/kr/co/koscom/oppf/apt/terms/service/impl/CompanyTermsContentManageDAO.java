package kr.co.koscom.oppf.apt.terms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.terms.service.CompanyTermsContentManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyTermsContentManageDAO.java
* @Comment  : 관리자의 기업 약관동의 내용 관리를 위한 DAO 클래스
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@Repository("CompanyTermsContentManageDAO")
public class CompanyTermsContentManageDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(CompanyTermsContentManageDAO.class);
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업 약관동의의 기업정보를 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CompanyTermsContentManageVO> selectCompanyCodeList(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	 return (List<CompanyTermsContentManageVO>) list("apt.CompanyTermsContentManageDAO.selectCompanyCodeList", companyTermsContentManageVO);
    }
    
    /**
     * @Method Name        : selectCompanyTermsContentList
     * @Method description : 기업 약관동의 내용의 목록을 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CompanyTermsContentManageVO> selectCompanyTermsContentList(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	 return (List<CompanyTermsContentManageVO>) list("apt.CompanyTermsContentManageDAO.selectCompanyTermsContentList", companyTermsContentManageVO);
    }
    
    /**
     * @Method Name        : selectCompanyTermsContentDtl
     * @Method description : 기업 약관동의 내용의 상세를 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : CompanyTermsContentManageVO
     * @throws             : Exception
     */
    public CompanyTermsContentManageVO selectCompanyTermsContentDtl(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	return (CompanyTermsContentManageVO) select("apt.CompanyTermsContentManageDAO.selectCompanyTermsContentDtl", companyTermsContentManageVO);
    }
    
    /**
     * @Method Name        : insertCompanyTermsContentHist
     * @Method description : 기업 약관동의 내용 hist 등록
     * @param              : CompanyTermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public int insertCompanyTermsContentHist(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	return update("apt.CompanyTermsContentManageDAO.insertCompanyTermsContentHist", companyTermsContentManageVO);
    }
    
    
    /**
     * @Method Name        : insertCompanyTermsContent
     * @Method description : 기업 약관동의 내용 등록
     * @param              : CompanyTermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCompanyTermsContent(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	return (String) insert("apt.CompanyTermsContentManageDAO.insertCompanyTermsContent", companyTermsContentManageVO);
    }
    
    /**
     * @Method Name        : updateCompanyTermsContent
     * @Method description : 기업 약관동의 내용 수정
     * @param              : CompanyTermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCompanyTermsContent(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	return update("apt.CompanyTermsContentManageDAO.updateCompanyTermsContent", companyTermsContentManageVO);
    }
    
}
