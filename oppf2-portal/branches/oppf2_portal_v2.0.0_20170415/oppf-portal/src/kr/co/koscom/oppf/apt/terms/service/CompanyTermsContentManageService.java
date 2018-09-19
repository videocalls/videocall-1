package kr.co.koscom.oppf.apt.terms.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyTermsContentManageService.java
* @Comment  : 관리자의 기업 약관동의 내용 관리를 위한 service
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
public interface CompanyTermsContentManageService{
	
	/**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업 약관동의 내용의 목록을 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : List<CompanyTermsContentManageVO>
     * @throws             : Exception
     */
    public List<CompanyTermsContentManageVO> selectCompanyCodeList(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCompanyTermsContentList
     * @Method description : 기업 약관동의 내용의 목록을 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCompanyTermsContentList(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCompanyTermsContentDtl
     * @Method description : 기업 약관동의 내용의 상세를 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : CompanyTermsContentManageVO
     * @throws             : Exception
     */
    public CompanyTermsContentManageVO selectCompanyTermsContentDtl(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : insertCompanyTermsContent
     * @Method description : 기업 약관동의 내용 등록
     * @param              : CompanyTermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCompanyTermsContent(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : updateCompanyTermsContent
     * @Method description : 기업 약관동의 내용 수정
     * @param              : CompanyTermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCompanyTermsContent(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception;
    
    
}
