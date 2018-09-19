package kr.co.koscom.oppf.apt.stats.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsServiceService.java
* @Comment  : 서비스 연결 통계 관리를 위한 위한 Service
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
public interface StatsServiceService{
	
	/**
     * @Method Name        : selectStatsServicePubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServicePubCompanyList(StatsServiceVO statsServiceVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsServiceApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceApiCategoryList(StatsServiceVO statsServiceVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsServiceSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceSubCompanyList(StatsServiceVO statsServiceVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsServiceAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    public List<StatsServiceVO> selectStatsServiceAppNameList(StatsServiceVO statsServiceVO) throws Exception;
	
    /**
     * @Method Name        : selectStatsServiceList
     * @Method description : 서비스 연결 통계 목록을 조회한다.
     * @param              : StatsServiceVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsServiceList(StatsServiceVO statsServiceVO) throws Exception;
        
    
}
