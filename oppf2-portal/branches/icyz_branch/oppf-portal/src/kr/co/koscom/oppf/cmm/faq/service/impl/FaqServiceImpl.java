package kr.co.koscom.oppf.cmm.faq.service.impl;

import kr.co.koscom.oppf.cmm.faq.service.FaqService;
import kr.co.koscom.oppf.cmm.faq.service.FaqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqServiceImpl.java
* @Comment  : [FAQ]정보관리를 위한 Service 클래스
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
@Slf4j
@Service("FaqService")
public class FaqServiceImpl implements FaqService{
    
    @Resource(name = "FaqDAO")
    private FaqDAO faqDAO;
    
    /**
     * @Method Name        : selectFaqListTotalCount
     * @Method description : [FAQ목록:총갯수]정보를 조회한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectFaqListTotalCount(FaqVO faqVO) throws Exception{
        log.info("------------- selectFaqListTotalCount START ------------------------");
        int totalCount = faqDAO.selectFaqListTotalCount(faqVO);
        log.info("------------- selectFaqListTotalCount END --------------------------");
        return totalCount;
    }

    /**
     * @Method Name        : selectFaqList
     * @Method description : [FAQ목록:목록]정보를 조회한다.
     * @param              : FaqVO
     * @return             : List<FaqVO>
     * @throws             : Exception
     */
    public List<FaqVO> selectFaqList(FaqVO faqVO) throws Exception{
        log.info("------------- selectFaqList START ------------------------");
        List<FaqVO> resultList = faqDAO.selectFaqList(faqVO);
        log.info("------------- selectFaqList END --------------------------");
        return resultList;
    }
    
    /**
     * @Method Name        : selectFaqDetail
     * @Method description : [FAQ상세:상세]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqDetail(FaqVO faqVO) throws Exception{
        log.info("------------- selectFaqDetail START ------------------------");
        FaqVO result = faqDAO.selectFaqDetail(faqVO);
        log.info("------------- selectFaqDetail END --------------------------");
        return result;
    }
    
    /**
     * @Method Name        : selectFaqBeforeAfterInfo
     * @Method description : [FAQ상세:이전글다음글]정보를 조회한다.
     * @param              : FaqVO
     * @return             : FaqVO
     * @throws             : Exception
     */
    public FaqVO selectFaqBeforeAfterInfo(FaqVO faqVO) throws Exception{
        log.info("------------- selectFaqBeforeAfterInfo START ------------------------");
        FaqVO result = faqDAO.selectFaqBeforeAfterInfo(faqVO);
        log.info("------------- selectFaqBeforeAfterInfo END --------------------------");
        return result;
    }
    
    /**
     * @Method Name        : updateFaqReadCount
     * @Method description : [FAQ목록:해당건]을 클릭 시 조회수 증가
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateFaqReadCount(FaqVO faqVO) throws Exception{
        log.info("------------- updateFaqReadCount START ------------------------");
        int rs = faqDAO.updateFaqReadCount(faqVO);
        log.info("------------- updateFaqReadCount END --------------------------");
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
        log.info("------------- selectFaqReadCount START ------------------------");
        int totalReadCount = faqDAO.selectFaqReadCount(faqVO);
        log.info("------------- selectFaqReadCount END --------------------------");
        return totalReadCount;
    }
    
    /**
     * @Method Name        : insertFaq
     * @Method description : [FAQ목록:등록]을 한다.
     * @param              : FaqVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertFaq(FaqVO faqVO) throws Exception{
        log.info("------------- insertFaq START ------------------------");
        int rs = faqDAO.insertFaq(faqVO);
        log.info("------------- insertFaq END --------------------------");
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
        log.info("------------- updateFaq START ------------------------");
        int rs = faqDAO.updateFaq(faqVO);
        log.info("------------- updateFaq END --------------------------");
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
        log.info("------------- deleteFaq START ------------------------");
        int rs = faqDAO.deleteFaq(faqVO);
        log.info("------------- deleteFaq END --------------------------");
        return rs;
    }
    
}
