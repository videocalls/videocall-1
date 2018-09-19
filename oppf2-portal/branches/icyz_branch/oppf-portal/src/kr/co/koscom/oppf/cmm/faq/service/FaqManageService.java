package kr.co.koscom.oppf.cmm.faq.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqManageService.java
* @Comment  : 관리자의 FAQ 관리를 위한 Service 인터페이스
* @author   : 신동진
* @since    : 2016.05.19
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.19  신동진        최초생성
*
*/
public interface FaqManageService{
	
    /**
     * @Method Name        : selectFaqManageList
     * @Method description : FAQ 목록을 조회한다.
     * @param              : FaqManageVO
     * @return             : List<FaqManageVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectFaqManageList(FaqManageVO faqManageVO) throws Exception;
    
    /**
     * @Method Name        : selectFaqManageListExcel
     * @Method description : FAQ Excel 목록을 조회한다.
     * @param              : faqVO
     * @return             : List<FaqVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectFaqManageListExcel(FaqManageVO faqManageVO) throws Exception;
    
    /**
     * @Method Name        : selectFaqManageDetail
     * @Method description : FAQ 상세정보를 조회한다.
     * @param              : FaqManageVO
     * @return             : FaqManageVO
     * @throws             : Exception
     */
    public FaqManageVO selectFaqManageDetail(FaqManageVO faqManageVO) throws Exception;
    
    /**
     * @Method Name        : insertFaq
     * @Method description : FAQ를 등록한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFaq(FaqManageVO faqManageVO) throws Exception;
    
    /**
     * @Method Name        : updateFaq
     * @Method description : FAQ를 수정한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaq(FaqManageVO faqManageVO) throws Exception;
    
    /**
     * @Method Name        : deleteFaq
     * @Method description : FAQ를 삭제한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteFaq(FaqManageVO faqManageVO) throws Exception;
    
}
