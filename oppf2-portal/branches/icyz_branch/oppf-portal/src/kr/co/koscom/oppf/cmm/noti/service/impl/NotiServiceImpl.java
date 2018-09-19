package kr.co.koscom.oppf.cmm.noti.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.noti.service.NotiService;
import kr.co.koscom.oppf.cmm.noti.service.NotiVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiServiceImpl.java
* @Comment  : [공지사항]정보관리를 위한 Service 클래스
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
@Service("NotiService")
public class NotiServiceImpl implements NotiService{
    
    @Resource(name = "NotiDAO")
    private NotiDAO notiDAO;
    
    /**
     * @Method Name        : selectNotiListTotalCount
     * @Method description : [공지사항목록:총갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int selectNotiListTotalCount(NotiVO notiVO) throws Exception{
        int totalCount = notiDAO.selectNotiListTotalCount(notiVO);
        return totalCount;
    }
    
    /**
     * @Method Name        : selectNotiListFix
     * @Method description : [공지사항목록:fix갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : List<NotiVO>
     * @throws             : Exception
     */
    @Transactional
    public List<NotiVO> selectNotiListFix(NotiVO notiVO) throws Exception{
        List<NotiVO> resultList = notiDAO.selectNotiListFix(notiVO);
        return resultList;
    }

    /**
     * @Method Name        : selectNotiList
     * @Method description : [공지사항목록:목록]정보를 조회한다.
     * @param              : NotiVO
     * @return             : List<NotiVO>
     * @throws             : Exception
     */
    @Transactional
    public List<NotiVO> selectNotiList(NotiVO notiVO) throws Exception{
        List<NotiVO> resultList = notiDAO.selectNotiList(notiVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectNotiDetail
     * @Method description : [공지사항상세:상세]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    @Transactional
    public NotiVO selectNotiDetail(NotiVO notiVO) throws Exception{
        NotiVO result = notiDAO.selectNotiDetail(notiVO);
        return result;
    }
    
    /**
     * @Method Name        : selectNotiBeforeAfterInfo
     * @Method description : [공지사항상세:이전글다음글]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    @Transactional
    public NotiVO selectNotiBeforeAfterInfo(NotiVO notiVO) throws Exception{
        NotiVO result = notiDAO.selectNotiBeforeAfterInfo(notiVO);
        return result;
    }
    
    /**
     * @Method Name        : updateNotiReadCount
     * @Method description : [공지사항목록:해당건]을 클릭 시 조회수 증가
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateNotiReadCount(NotiVO notiVO) throws Exception{
        int rs = notiDAO.updateNotiReadCount(notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [공지사항목록:해당건]조회수 증가 후 조회수 취득
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int selectNotiReadCount(NotiVO notiVO) throws Exception{       
        int totalReadCount = notiDAO.selectNotiReadCount(notiVO);
        return totalReadCount;
    }
    
    /**
     * @Method Name        : insertNoti
     * @Method description : [공지사항목록:등록]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertNoti(NotiVO notiVO) throws Exception{
        int rs = notiDAO.insertNoti(notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateNoti
     * @Method description : [공지사항상세:수정]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateNoti(NotiVO notiVO) throws Exception{
        int rs = notiDAO.updateNoti(notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : [공지사항상세:삭제]를 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteNoti(NotiVO notiVO) throws Exception{
        int rs = notiDAO.deleteNoti(notiVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectNotiListPop
     * @Method description : [공지사항목록:팝업]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    @Transactional
    public List<NotiVO> selectNotiListPop(NotiVO notiVO) throws Exception{
        List<NotiVO> resultList = notiDAO.selectNotiListPop(notiVO);
        return resultList;
    }
    
}
