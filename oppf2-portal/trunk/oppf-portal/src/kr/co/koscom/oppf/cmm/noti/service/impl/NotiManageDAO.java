package kr.co.koscom.oppf.cmm.noti.service.impl;

import kr.co.koscom.oppf.cmm.noti.service.NotiFileManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiDAO.java
* @Comment  : 관리자의 공지사항 관리를 위한 DAO 클래스
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
@Repository("NotiManageDAO")
public class NotiManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectNotiManageListTotalCount
     * @Method description : 공지사항 목록의 총개수를 조회한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectNotiManageListTotalCount(NotiManageVO notiManageVO) throws Exception{
        return (Integer) select("com.NotiManageDAO.selectNotiManageListTotalCount", notiManageVO);
    }

    /**
     * @Method Name        : selectNotiManageList
     * @Method description : 공지사항 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NotiManageVO> selectNotiManageList(NotiManageVO notiManageVO) throws Exception{
        return (List<NotiManageVO>) list("com.NotiManageDAO.selectNotiManageList", notiManageVO);
    }
    
    /**
     * @Method Name        : selectNotiManageDetail
     * @Method description : 공지사항 상세정보를 조회한다.
     * @param              : NotiManageVO
     * @return             : NotiManageVO
     * @throws             : Exception
     */
    public NotiManageVO selectNotiManageDetail(NotiManageVO notiManageVO) throws Exception{
        return (NotiManageVO) select("com.NotiManageDAO.selectNotiManageDetail", notiManageVO);
    }
    
    /**
     * @Method Name        : selectNotiManageDetailFileList
     * @Method description : 공지사항 첨부파일 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiFileManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<NotiFileManageVO> selectNotiManageDetailFileList(NotiManageVO notiManageVO) throws Exception{
        return (List<NotiFileManageVO>) list("com.NotiManageDAO.selectNotiManageDetailFileList", notiManageVO);
    }
    
    /**
     * @Method Name        : selectNotiFileId
     * @Method description : 공지사항 file id를 조회한다.
     * @param              : NotiManageVO
     * @return             : NotiManageVO
     * @throws             : Exception
     */
    public String selectNotiFileId(NotiManageVO notiManageVO) throws Exception{
        return (String) select("com.NotiManageDAO.selectNotiFileId", notiManageVO);
    }
    
    /**
     * @Method Name        : insertNotiFileData
     * @Method description : 공지사항 file을 등록한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertNotiFileData(NotiFileManageVO notiFileManageVO) throws Exception{
        return (Integer) update("com.NotiManageDAO.insertNotiFileData", notiFileManageVO);
    }
    
    /**
     * @Method Name        : deleteNotiFileData
     * @Method description : 공지사항 file을 삭제한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteNotiFileData(NotiFileManageVO notiFileManageVO) throws Exception{
        return (Integer) delete("com.NotiManageDAO.deleteNotiFileData", notiFileManageVO);
    }
    
    /**
     * @Method Name        : insertNoti
     * @Method description : 공지사항을 등록한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertNoti(NotiManageVO notiManageVO) throws Exception{
        return (Integer) update("com.NotiManageDAO.insertNoti", notiManageVO);
    }
    
    /**
     * @Method Name        : updateNoti
     * @Method description : 공지사항을 수정한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNoti(NotiManageVO notiManageVO) throws Exception{
        return (Integer) update("com.NotiManageDAO.updateNoti", notiManageVO);
    }
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : 공지사항을 삭제한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteNoti(NotiManageVO notiManageVO) throws Exception{
        return (Integer) update("com.NotiManageDAO.deleteNoti", notiManageVO);
    }
    
}
