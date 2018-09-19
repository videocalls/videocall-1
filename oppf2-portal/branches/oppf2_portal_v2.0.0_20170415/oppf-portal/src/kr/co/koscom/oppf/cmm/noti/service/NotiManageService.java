package kr.co.koscom.oppf.cmm.noti.service;

import java.util.List;
import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageService.java
* @Comment  : 관리자의 공지사항 관리를 위한 Service 인터페이스
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
public interface NotiManageService{
	
    /**
     * @Method Name        : selectNotiManageList
     * @Method description : 공지사항 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiManageVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectNotiManageList(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiManageListExcel
     * @Method description : 공지사항 Excel 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiManageVO>
     * @throws             : Exception
     */
    public Map<String, Object> selectNotiManageListExcel(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiManageDetail
     * @Method description : 공지사항 상세정보를 조회한다.
     * @param              : NotiManageVO
     * @return             : NotiManageVO
     * @throws             : Exception
     */
    public NotiManageVO selectNotiManageDetail(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectNotiManageDetailFileList
     * @Method description : 공지사항 첨부파일 목록을 조회한다.
     * @param              : NotiManageVO
     * @return             : List<NotiFileManageVO>
     * @throws             : Exception
     */
    public List<NotiFileManageVO> selectNotiManageDetailFileList(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : insertNoti
     * @Method description : 공지사항을 등록한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertNoti(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : updateNoti
     * @Method description : 공지사항을 수정한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateNoti(NotiManageVO notiManageVO) throws Exception;
    
    /**
     * @Method Name        : deleteNoti
     * @Method description : 공지사항을 삭제한다.
     * @param              : NotiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteNoti(NotiManageVO notiManageVO) throws Exception;
    
}
