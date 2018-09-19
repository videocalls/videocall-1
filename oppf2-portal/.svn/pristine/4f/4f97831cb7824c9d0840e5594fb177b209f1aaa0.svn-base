package kr.co.koscom.oppf.cpt.myp.stats.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptStatsTrafficService.java
* @Comment  : 기업포털 Traffic 통계 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.08.19
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.19  신동진        최초생성
*
*/
public interface CptStatsTrafficService{
	
	/**
     * @Method Name        : selectStatsTrafficPubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficPubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiCategoryList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiServiceList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficSubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficAppNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    public List<CptStatsTrafficVO> selectStatsTrafficApiPlanNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectCompanyCodeId
     * @Method description : 로그인 사용자의 companyCodeId를 가져온다.
     * @param              : CptStatsTrafficVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectCompanyCodeId(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
	
    /**
     * @Method Name        : selectStatsTrafficApiList
     * @Method description : API 트레픽 통계 목록을 조회한다.
     * @param              : CptStatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficApiList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
    
    /**
     * @Method Name        : selectStatsTrafficAppList
     * @Method description : APP 트레픽 통계 목록을 조회한다.
     * @param              : CptStatsTrafficVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectStatsTrafficAppList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;

    Map<String, Object> selectStatsTrafficList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception;
}
