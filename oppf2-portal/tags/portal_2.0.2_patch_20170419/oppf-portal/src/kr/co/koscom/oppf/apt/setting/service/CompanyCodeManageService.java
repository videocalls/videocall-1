package kr.co.koscom.oppf.apt.setting.service;

import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyCodeManageService.java
* @Comment  : 관리자의 기업코드 관리를 위한 service
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
public interface CompanyCodeManageService{
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCompanyCodeList(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : selectAptUserDtl
     * @Method description : 기업코드 상세를 조회한다.
     * @param              : AptUserManageVO
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
    public CompanyCodeManageVO selectCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : chkCompanyCodeDtl
     * @Method description : 기업코드 저장가능 여부를 체크한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> chkCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : insertCompanyCode
     * @Method description : 기업코드 등록
     * @param              : CompanyCodeManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : updateCompanyCode
     * @Method description : 기업코드 수정
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : chkDeleteCompanyCode
     * @Method description : 기업코드 삭제가능 여부를 체크한다.
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int chkDeleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
    /**
     * @Method Name        : deleteCompanyCode
     * @Method description : 기업코드 삭제
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception;
    
}
