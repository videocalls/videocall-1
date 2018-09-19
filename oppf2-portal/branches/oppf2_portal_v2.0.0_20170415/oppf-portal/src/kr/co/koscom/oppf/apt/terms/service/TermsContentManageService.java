package kr.co.koscom.oppf.apt.terms.service;

import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : TermsContentManageService.java
* @Comment  : 관리자의 약관동의 내용 관리를 위한 service
* @author   : 신동진
* @since    : 2016.05.23
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.23  신동진        최초생성
*
*/
public interface TermsContentManageService{
    
    /**
     * @Method Name        : selectTermsContentList
     * @Method description : 약관동의 내용의 목록을 조회한다.
     * @param              : TermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectTermsContentList(TermsContentManageVO termsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : selectTermsContentDtl
     * @Method description : 약관동의 내용의 상세를 조회한다.
     * @param              : TermsContentManageVO
     * @return             : TermsContentManageVO
     * @throws             : Exception
     */
    public TermsContentManageVO selectTermsContentDtl(TermsContentManageVO termsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : insertTermsContent
     * @Method description : 약관동의 내용 등록
     * @param              : TermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertTermsContent(TermsContentManageVO termsContentManageVO) throws Exception;
    
    /**
     * @Method Name        : updateTermsContent
     * @Method description : 약관동의 내용 수정
     * @param              : TermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTermsContent(TermsContentManageVO termsContentManageVO) throws Exception;
    
    
}
