package kr.co.koscom.oppf.cmm.faq.service.impl;

import kr.co.koscom.oppf.cmm.faq.service.FaqManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqDAO.java
* @Comment  : 관리자의 FAQ 관리를 위한 DAO 클래스
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
@Repository("FaqManageDAO")
public class FaqManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectFaqManageListTotalCount
     * @Method description : FAQ 목록의 총개수를 조회한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqManageListTotalCount(FaqManageVO faqManageVO) throws Exception{
        return (Integer) select("com.FaqManageDAO.selectFaqManageListTotalCount", faqManageVO);
    }

    /**
     * @Method Name        : selectFaqManageList
     * @Method description : FAQ 목록을 조회한다.
     * @param              : FaqManageVO
     * @return             : List<FaqManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<FaqManageVO> selectFaqManageList(FaqManageVO faqManageVO) throws Exception{
        return (List<FaqManageVO>) list("com.FaqManageDAO.selectFaqManageList", faqManageVO);
    }
    
    /**
     * @Method Name        : selectFaqManageDetail
     * @Method description : FAQ 상세정보를 조회한다.
     * @param              : FaqManageVO
     * @return             : FaqManageVO
     * @throws             : Exception
     */
    public FaqManageVO selectFaqManageDetail(FaqManageVO faqManageVO) throws Exception{
        return (FaqManageVO) select("com.FaqManageDAO.selectFaqManageDetail", faqManageVO);
    }
    
    /**
     * @Method Name        : insertFaq
     * @Method description : FAQ를 등록한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFaq(FaqManageVO faqManageVO) throws Exception{
        return (Integer) update("com.FaqManageDAO.insertFaq", faqManageVO);
    }
    
    /**
     * @Method Name        : updateFaq
     * @Method description : FAQ를 수정한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaq(FaqManageVO faqManageVO) throws Exception{
        return (Integer) update("com.FaqManageDAO.updateFaq", faqManageVO);
    }
    
    /**
     * @Method Name        : deleteFaq
     * @Method description : FAQ를 삭제한다.
     * @param              : FaqManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteFaq(FaqManageVO faqManageVO) throws Exception{
        return (Integer) update("com.FaqManageDAO.deleteFaq", faqManageVO);
    }
    
}
