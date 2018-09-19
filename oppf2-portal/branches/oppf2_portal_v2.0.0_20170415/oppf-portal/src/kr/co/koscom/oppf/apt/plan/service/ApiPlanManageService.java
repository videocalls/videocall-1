package kr.co.koscom.oppf.apt.plan.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiPlanManageService.java
* @Comment  : api plan 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
public interface ApiPlanManageService{
	
    /**
     * @Method Name        : selectApiPlanManageList
     * @Method description : api plan 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManageList(ApiPlanManageVO apiPlanManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiPlanManageListExcel
     * @Method description : api plan excel 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManageListExcel(ApiPlanManageVO apiPlanManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiPlanManagePopupList
     * @Method description : api plan 등록 팝업 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception;
    
    /**
     * @Method Name        : saveApiPlanManagePopupList
     * @Method description : api plan을 저장한다.
     * @param              : ApiPlanManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception;
        
    
}
