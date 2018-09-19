package kr.co.koscom.oppf.cmm.qna.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.qna.service.QnaService;
import kr.co.koscom.oppf.cmm.qna.service.QnaVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : QnaServiceImpl.java
* @Comment  : [Q&A]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@Service("QnaService")
public class QnaServiceImpl implements QnaService{
    
    @Resource(name = "QnaDAO")
    private QnaDAO qnaDAO;
    
    /**
     * @Method Name        : selectQnaListTotalCount
     * @Method description : [Q&A목록:총갯수]정보를 조회한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectQnaListTotalCount(QnaVO qnaVO) throws Exception{
        int totalCount = qnaDAO.selectQnaListTotalCount(qnaVO);
        return totalCount;
    }

    /**
     * @Method Name        : selectQnaList
     * @Method description : [Q&A목록:목록]정보를 조회한다.
     * @param              : QnaVO
     * @return             : List<QnaVO>
     * @throws             : Exception
     */
    public List<QnaVO> selectQnaList(QnaVO qnaVO) throws Exception{
        List<QnaVO> resultList = qnaDAO.selectQnaList(qnaVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectQnaDetail
     * @Method description : [Q&A상세:상세]정보를 조회한다.
     * @param              : QnaVO
     * @return             : QnaVO
     * @throws             : Exception
     */
    public QnaVO selectQnaDetail(QnaVO qnaVO) throws Exception{
        QnaVO result = qnaDAO.selectQnaDetail(qnaVO);
        return result;
    }
    
    /**
     * @Method Name        : selectQnaBeforeAfterInfo
     * @Method description : [Q&A상세:이전글다음글]정보를 조회한다.
     * @param              : QnaVO
     * @return             : QnaVO
     * @throws             : Exception
     */
    public QnaVO selectQnaBeforeAfterInfo(QnaVO qnaVO) throws Exception{
        QnaVO result = qnaDAO.selectQnaBeforeAfterInfo(qnaVO);
        return result;
    }
    
    /**
     * @Method Name        : updateQnaReadCount
     * @Method description : [Q&A목록:해당건]을 클릭 시 조회수 증가
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateQnaReadCount(QnaVO qnaVO) throws Exception{
        int rs = qnaDAO.updateQnaReadCount(qnaVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [Q&A목록:해당건]조회수 증가 후 조회수 취득
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectQnaReadCount(QnaVO qnaVO) throws Exception{       
        int totalReadCount = qnaDAO.selectQnaReadCount(qnaVO);
        return totalReadCount;
    }
    
    /**
     * @Method Name        : insertQna
     * @Method description : [Q&A목록:등록]을 한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertQna(QnaVO qnaVO) throws Exception{
        int rs = qnaDAO.insertQna(qnaVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateQna
     * @Method description : [Q&A상세:수정]을 한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateQna(QnaVO qnaVO) throws Exception{
        int rs = qnaDAO.updateQna(qnaVO);
        return rs;
    }
    
    /**
     * @Method Name        : deleteQna
     * @Method description : [Q&A상세:삭제]를 한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteQna(QnaVO qnaVO) throws Exception{
        int rs = qnaDAO.deleteQna(qnaVO);
        return rs;
    }
    
}
