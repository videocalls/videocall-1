package kr.co.koscom.oppf.cmm.qna.service.impl;

import kr.co.koscom.oppf.cmm.qna.service.QnaVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : QnaDAO.java
* @Comment  : [Q&A]정보관리를 위한 DAO 클래스
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
@Repository("QnaDAO")
public class QnaDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectQnaListTotalCount
     * @Method description : [Q&A목록:총갯수]정보를 조회한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectQnaListTotalCount(QnaVO qnaVO) throws Exception{
        return (Integer) select("QnaDAO.selectQnaListTotalCount", qnaVO);
    }

    /**
     * @Method Name        : selectQnaList
     * @Method description : [Q&A목록:목록]정보를 조회한다.
     * @param              : QnaVO
     * @return             : List<QnaVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<QnaVO> selectQnaList(QnaVO qnaVO) throws Exception{
        return (List<QnaVO>) list("QnaDAO.selectQnaList", qnaVO);
    }
    
    /**
     * @Method Name        : selectQnaDetail
     * @Method description : [Q&A상세:상세]정보를 조회한다.
     * @param              : QnaVO
     * @return             : QnaVO
     * @throws             : Exception
     */
    public QnaVO selectQnaDetail(QnaVO qnaVO) throws Exception{
        return (QnaVO) select("QnaDAO.selectQnaDetail", qnaVO);
    }
    
    /**
     * @Method Name        : selectQnaBeforeAfterInfo
     * @Method description : [Q&A상세:이전글다음글]정보를 조회한다.
     * @param              : QnaVO
     * @return             : QnaVO
     * @throws             : Exception
     */
    public QnaVO selectQnaBeforeAfterInfo(QnaVO qnaVO) throws Exception{
        return (QnaVO) select("QnaDAO.selectQnaBeforeAfterInfo", qnaVO);
    }
    
    /**
     * @Method Name        : updateQnaReadCount
     * @Method description : [Q&A목록:해당건]을 클릭 시 조회수 증가
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateQnaReadCount(QnaVO qnaVO) throws Exception{
        int rs = update("QnaDAO.updateQnaReadCount", qnaVO);
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
        return (Integer) select("QnaDAO.selectQnaReadCount", qnaVO);
    }
    
    /**
     * @Method Name        : insertQna
     * @Method description : [Q&A목록:등록]을 한다.
     * @param              : QnaVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertQna(QnaVO qnaVO) throws Exception{
        int rs = update("QnaDAO.insertQna", qnaVO);
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
        int rs = update("QnaDAO.updateQna", qnaVO);
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
        int rs = update("QnaDAO.deleteQna", qnaVO);
        return rs;
    }
    
}
