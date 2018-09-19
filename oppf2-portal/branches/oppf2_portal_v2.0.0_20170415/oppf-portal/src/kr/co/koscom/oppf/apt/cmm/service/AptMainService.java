package kr.co.koscom.oppf.apt.cmm.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AptMainService.java
* @Comment  : admin main 관리를 위한 위한 Service
* @author   : 신동진
* @since    : 2016.07.15
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.15  신동진        최초생성
*
*/
public interface AptMainService{
	/************************************************************************************************
	 * API 트래픽 현황 start
	 ***********************************************************************************************/
	
	/**
     * @Method Name        : selectMainStatsTraffic
     * @Method description : API 트래픽 요약 - 전체건수 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTraffic(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtl
     * @Method description : API 트래픽 요약 - 상세건수 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtl(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficApiTraffic
     * @Method description : API 서비스별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficApiTraffic(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficServiceTraffic
     * @Method description : 서비스 제동자별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficServiceTraffic(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficAppTraffic
     * @Method description : 핀테크 앱 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficAppTraffic(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficPlanTraffic
     * @Method description : 사용 Plan 별 트래픽 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficPlanTraffic(AptMainVO aptMainVO) throws Exception;
    
    /************************************************************************************************
	 * API 트래픽 현황 end
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * API 트래픽 상세 현황 start
	 ***********************************************************************************************/
    /**
     * @Method Name        : selectMainStatsTrafficDtlReqCnt
     * @Method description : API 트래픽 요약 - 요청 Count 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlReqCnt(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAvgTime
     * @Method description : API 트래픽 요약 - 평균 Time 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlAvgTime(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlAccList
     * @Method description : API 트래픽 현황 - 누적 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlAccList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcCategoryList
     * @Method description : API 트래픽 현황 - 개별(API 서비스 구분) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcCategoryList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcSubcompanyList
     * @Method description : API 트래픽 현황 - 개별(API 서비스  제공자) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcSubcompanyList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsTrafficDtlSvcPubcompanyList
     * @Method description : API 트래픽 현황 - 개별(핀테크 앱) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsTrafficDtlSvcPubcompanyList(AptMainVO aptMainVO) throws Exception;
    
    /************************************************************************************************
	 * API 트래픽 상세 현황 end 
	 ***********************************************************************************************/
    
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 start
	 ***********************************************************************************************/
    
    /**
     * @Method Name        : selectMainStatsServiceUserService
     * @Method description : 회원 가입 및 서비스 연결 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserService(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccount
     * @Method description : 가상계좌 발급 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccount(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceUserApp
     * @Method description : 핀테크 앱 연결 회원 현황(요약) 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserApp(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceSptUserList
     * @Method description : 금융투자 핀테크 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceSptUserList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceCptUserList
     * @Method description : 기업/금투사 포털 회원 가입 현황 요약 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceCptUserList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 가상계좌 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccountList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceUserAccountCompanyList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 서비스 제공자 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserAccountCompanyList(AptMainVO aptMainVO) throws Exception;
    
    /**
     * @Method Name        : selectMainStatsServiceUserServiceAppList
     * @Method description : 금융투자 핀테크 포털 회원 가상 계좌 발급 및 서비스 연결 현황 요약 - 앱 개발자 조회
     * @param              : AptMainVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMainStatsServiceUserServiceAppList(AptMainVO aptMainVO) throws Exception;
        
    /************************************************************************************************
	 * 회원가입 및 서비스 연결 현황 end
	 ***********************************************************************************************/
}
