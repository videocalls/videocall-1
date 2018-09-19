package kr.co.koscom.oppf.cmm.noti.service;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiService.java
* @Comment  : [공지사항]정보관리를 위한 Service 인터페이스
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
public interface NotiService{
    
    /**
     * @Method Name        : selectNotiListTotalCount
     * @Method description : [공지사항목록:총갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectNotiListTotalCount(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiListFix
     * @Method description : [공지사항목록:Fix갯수]정보를 조회한다.
     * @param              : NotiVO
     * @return             : List<NotiVO>
     * @throws             : Exception
     */
    public List<NotiVO> selectNotiListFix(NotiVO notiVO) throws Exception;

    /**
     * @Method Name        : selectNotiList
     * @Method description : [공지사항목록:목록]정보를 조회한다.
     * @param              : NotiVO
     * @return             : List<NotiVO>
     * @throws             : Exception
     */
    public List<NotiVO> selectNotiList(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiDetail
     * @Method description : [공지사항상세:상세]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    public NotiVO selectNotiDetail(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiBeforeAfterInfo
     * @Method description : [공지사항상세:이전글다음글]정보를 조회한다.
     * @param              : NotiVO
     * @return             : NotiVO
     * @throws             : Exception
     */
    public NotiVO selectNotiBeforeAfterInfo(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : updateNotiReadCount
     * @Method description : [공지사항목록:해당건]을 클릭 시 조회수 증가
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNotiReadCount(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : selectReadCount
     * @Method description : [공지사항목록:해당건]조회수 증가 후 조회수 취득
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectNotiReadCount(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : insertNoti
     * @Method description : [공지사항목록:등록]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertNoti(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : updateNoti
     * @Method description : [공지사항상세:수정]을 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNoti(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : [공지사항상세:삭제]를 한다.
     * @param              : NotiVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteNoti(NotiVO notiVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiListPop
     * @Method description : [공지사항목록:팝업]정보를 조회한다.
     * @param              : NotiVO,ModelMap
     * @return             : jsonView
     * @throws             : Exception
     */
    public List<NotiVO> selectNotiListPop(NotiVO notiVO) throws Exception;
    
}
