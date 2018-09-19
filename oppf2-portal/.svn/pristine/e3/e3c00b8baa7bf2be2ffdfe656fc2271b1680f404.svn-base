package kr.co.koscom.oppf.apt.stats.service.impl;

import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsTrafficDAO.java
* @Comment  : Traffic 통계 관리를 위한 위한 DAO
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
@Repository("StatsTrafficDAO")
public class StatsTrafficDAO extends ComAbstractDAO{
    

    /**
     * @Method Name        : selectStatsTrafficPubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficPubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficPubCompanyList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficApiCategoryList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficApiCategoryList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficApiNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficApiNameList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficApiServiceList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficApiServiceList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficSubCompanyList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficSubCompanyList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficAppNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficAppNameList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficApiPlanNameList(StatsTrafficVO statsTrafficVO) throws Exception{
    	return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficApiPlanNameList", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSearchDate
     * @Method description : 트레픽 통계 목록의 기준일을 조회한다.
     * @param              : StatsTrafficVO
     * @return             : List<StatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsTrafficVO> selectStatsTrafficSearchDate(StatsTrafficVO statsTrafficVO) throws Exception{
        return (List<StatsTrafficVO>) list("apt.StatsTrafficDAO.selectStatsTrafficSearchDate", statsTrafficVO);
    }
        
    /**
     * @Method Name        : selectStatsTrafficListCnt
     * @Method description : 트레픽 통계 목록을 조회한다. (건수 기준)
     * @param              : StatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsTrafficListCnt(StatsTrafficVO statsTrafficVO) throws Exception{
        return (List<HashMap>) list("apt.StatsTrafficDAO.selectStatsTrafficListCnt", statsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficListDuration
     * @Method description : 트레픽 통계 목록을 조회한다. (응답시간 기준)
     * @param              : StatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsTrafficListDuration(StatsTrafficVO statsTrafficVO) throws Exception{
        return (List<HashMap>) list("apt.StatsTrafficDAO.selectStatsTrafficListDuration", statsTrafficVO);
    }

}
