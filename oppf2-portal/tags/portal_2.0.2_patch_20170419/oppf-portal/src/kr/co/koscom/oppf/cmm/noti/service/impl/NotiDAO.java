package kr.co.koscom.oppf.cmm.noti.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiDAO.java
* @Comment  : [공지사항]정보관리를 위한 DAO 클래스
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
@Repository("NotiDAO")
public class NotiDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(NotiDAO.class);
    
    /**
     * @Method Name        : selectNotiListTotalCount
     * @Method description : [공지사항목록:총갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectNotiListTotalCount(NotiVO notiVO) throws Exception{
        return (Integer) select("NotiDAO.selectNotiListTotalCount", notiVO);
    }
    
    /**
     * @Method Name        : selectNotiListFix
     * @Method description : [공지사항목록:fix갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NotiVO> selectNotiListFix(NotiVO notiVO) throws Exception{
        return (List<NotiVO>) list("NotiDAO.selectNotiListFix", notiVO);
    }

    /**
     * @Method Name        : selectNotiList
     * @Method description : [공지사항목록:목록]정보를 조회한다.
     * @param              : NotiVO
     * @return             : List<NotiVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NotiVO> selectNotiList(NotiVO notiVO) throws Exception{
        return (List<NotiVO>) list("NotiDAO.selectNotiList", notiVO);
    }
    
    /**
     * @Method Name        : selectNotiDetail
     * @Method description : [공지사항상세:상세]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    public NotiVO selectNotiDetail(NotiVO notiVO) throws Exception{
        return (NotiVO) select("NotiDAO.selectNotiDetail", notiVO);
    }
    
    /**
     * @Method Name        : selectNotiBeforeAfterInfo
     * @Method description : [공지사항상세:이전글다음글]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    public NotiVO selectNotiBeforeAfterInfo(NotiVO notiVO) throws Exception{
        return (NotiVO) select("NotiDAO.selectNotiBeforeAfterInfo", notiVO);
    }
    
    /**
     * @Method Name        : updateNotiReadCount
     * @Method description : [공지사항목록:해당건]을 클릭 시 조회수 증가
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNotiReadCount(NotiVO notiVO) throws Exception{
        int rs = update("NotiDAO.updateNotiReadCount", notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [공지사항목록:해당건]조회수 증가 후 조회수 취득
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectNotiReadCount(NotiVO notiVO) throws Exception{       
        return (Integer) select("NotiDAO.selectNotiReadCount", notiVO);
    }
    
    /**
     * @Method Name        : insertNoti
     * @Method description : [공지사항목록:등록]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertNoti(NotiVO notiVO) throws Exception{
        int rs = (int) update("NotiDAO.insertNoti", notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateNoti
     * @Method description : [공지사항상세:수정]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNoti(NotiVO notiVO) throws Exception{
        int rs = update("NotiDAO.updateNoti", notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : [공지사항상세:삭제]를 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteNoti(NotiVO notiVO) throws Exception{
        int rs = update("NotiDAO.deleteNoti", notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectNotiListPop
     * @Method description : [공지사항목록:팝업]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NotiVO> selectNotiListPop(NotiVO notiVO) throws Exception{
        return (List<NotiVO>) list("NotiDAO.selectNotiListPop", notiVO);
    }
    
}
