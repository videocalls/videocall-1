package kr.co.koscom.oppf.apt.api.service;

import java.util.Map;

import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiManageService.java
* @Comment  : api 관리를 위한 Service
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
public interface ApiManageService{
	
    /**
     * @Method Name        : selectApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiManageList(ApiManageVO apiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiManageListExcel(ApiManageVO apiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public ApiManageVO selectApiManageDtl(ApiManageVO apiManageVO) throws Exception;
    
    /**
     * @Method Name        : saveApiManage
     * @Method description : api 저장
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveApiManage(ApiManageVO apiManageVO) throws Exception;
    
    
}
