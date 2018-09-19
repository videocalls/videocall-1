package kr.co.koscom.oppf.cmm.faq.service;

import java.util.List;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqService.java
* @Comment  : [FAQ]정보관리를 위한 Service 인터페이스
* @author   : 포털 유제량
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  유제량        최초생성
*
*/
public interface FaqService{
    
    /**
     * @Method Name        : selectFaqListTotalCount
     * @Method description : [FAQ목록:총갯수]정보를 조회한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqListTotalCount(FaqVO faqVO) throws Exception;

    /**
     * @Method Name        : selectFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqVO
     * @return             : List<FaqVO>
     * @throws             : Exception
     */
    public List<FaqVO> selectFaqList(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : selectFaqDetail
     * @Method description : [FAQ상세:상세]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqDetail(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : selectFaqBeforeAfterInfo
     * @Method description : [FAQ상세:이전글다음글]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqBeforeAfterInfo(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : updateFaqReadCount
     * @Method description : [FAQ목록:해당건]을 클릭 시 조회수 증가
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaqReadCount(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [FAQ목록:해당건]조회수 증가 후 조회수 취득
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqReadCount(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : insertFaq
     * @Method description : [FAQ목록:등록]을 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFaq(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : updateFaq
     * @Method description : [FAQ상세:수정]을 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaq(FaqVO faqVO) throws Exception;
    
    /**
     * @Method Name        : deleteFaq
     * @Method description : [FAQ상세:삭제]를 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteFaq(FaqVO faqVO) throws Exception;
}
