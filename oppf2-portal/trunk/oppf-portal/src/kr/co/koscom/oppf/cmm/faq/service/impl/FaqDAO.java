package kr.co.koscom.oppf.cmm.faq.service.impl;

import kr.co.koscom.oppf.cmm.faq.service.FaqVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqDAO.java
* @Comment  : [FAQ]정보관리를 위한 DAO 클래스
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
@Repository("FaqDAO")
public class FaqDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectFaqListTotalCount
     * @Method description : [FAQ목록:총갯수]정보를 조회한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqListTotalCount(FaqVO faqVO) throws Exception{
        return (Integer) select("cmm.FaqDAO.selectFaqListTotalCount", faqVO);
    }

    /**
     * @Method Name        : selectFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqVO
     * @return             : List<FaqVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<FaqVO> selectFaqList(FaqVO faqVO) throws Exception{
        return (List<FaqVO>) list("cmm.FaqDAO.selectFaqList", faqVO);
    }
    
    /**
     * @Method Name        : selectFaqDetail
     * @Method description : [FAQ상세:상세]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqDetail(FaqVO faqVO) throws Exception{
        return (FaqVO) select("cmm.FaqDAO.selectFaqDetail", faqVO);
    }
    
    /**
     * @Method Name        : selectFaqBeforeAfterInfo
     * @Method description : [FAQ상세:이전글다음글]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqBeforeAfterInfo(FaqVO faqVO) throws Exception{
        return (FaqVO) select("cmm.FaqDAO.selectFaqBeforeAfterInfo", faqVO);
    }
    
    /**
     * @Method Name        : updateFaqReadCount
     * @Method description : [FAQ목록:해당건]을 클릭 시 조회수 증가
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaqReadCount(FaqVO faqVO) throws Exception{
        int rs = update("cmm.FaqDAO.updateFaqReadCount", faqVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [FAQ목록:해당건]조회수 증가 후 조회수 취득
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqReadCount(FaqVO faqVO) throws Exception{       
        return (Integer) select("cmm.FaqDAO.selectFaqReadCount", faqVO);
    }
    
    /**
     * @Method Name        : insertFaq
     * @Method description : [FAQ목록:등록]을 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFaq(FaqVO faqVO) throws Exception{
        int rs = update("cmm.FaqDAO.insertFaq", faqVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateFaq
     * @Method description : [FAQ상세:수정]을 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaq(FaqVO faqVO) throws Exception{
        int rs = update("cmm.FaqDAO.updateFaq", faqVO);
        return rs;
    }
    
    /**
     * @Method Name        : deleteFaq
     * @Method description : [FAQ상세:삭제]를 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteFaq(FaqVO faqVO) throws Exception{
        int rs = update("cmm.FaqDAO.deleteFaq", faqVO);
        return rs;
    }
    
}
