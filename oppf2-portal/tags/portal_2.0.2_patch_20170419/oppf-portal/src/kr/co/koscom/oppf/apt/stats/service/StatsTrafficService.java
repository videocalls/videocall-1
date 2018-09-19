package kr.co.koscom.oppf.apt.stats.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsTrafficService.java
* @Comment  : Traffic 통계 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.07.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.10  신동진        최초생성
*
*/
public interface StatsTrafficService{
	
	/**
     * @Method Name        : selectStatsTrafficPubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficPubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiCategoryList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiNameList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiServiceList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficSubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficAppNameList(StatsTrafficVO statsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    public List<StatsTrafficVO> selectStatsTrafficApiPlanNameList(StatsTrafficVO statsTrafficVO) throws Exception;
	
    /**
     * @Method Name        : selectStatsTrafficList
     * @Method description : 트레픽 통계 목록을 조회한다.
     * @param              : StatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficList(StatsTrafficVO statsTrafficVO) throws Exception;
        
    
}
