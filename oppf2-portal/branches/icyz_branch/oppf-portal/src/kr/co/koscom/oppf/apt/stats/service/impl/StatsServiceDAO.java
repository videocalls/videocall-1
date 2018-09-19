package kr.co.koscom.oppf.apt.stats.service.impl;

import kr.co.koscom.oppf.apt.stats.service.StatsServiceVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsServiceDAO.java
* @Comment  : 서비스 연결 통계 관리를 위한 위한 DAO
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
@Repository("StatsServiceDAO")
public class StatsServiceDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectStatsServicePubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsServiceVO> selectStatsServicePubCompanyList(StatsServiceVO statsServiceVO) throws Exception{
    	return (List<StatsServiceVO>) list("apt.StatsServiceDAO.selectStatsServicePubCompanyList", statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsServiceVO> selectStatsServiceApiCategoryList(StatsServiceVO statsServiceVO) throws Exception{
    	return (List<StatsServiceVO>) list("apt.StatsServiceDAO.selectStatsServiceApiCategoryList", statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsServiceVO> selectStatsServiceSubCompanyList(StatsServiceVO statsServiceVO) throws Exception{
    	return (List<StatsServiceVO>) list("apt.StatsServiceDAO.selectStatsServiceSubCompanyList", statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsServiceVO> selectStatsServiceAppNameList(StatsServiceVO statsServiceVO) throws Exception{
    	return (List<StatsServiceVO>) list("apt.StatsServiceDAO.selectStatsServiceAppNameList", statsServiceVO);
    }
    
    /**
     * @Method Name        : selectStatsServiceSearchDate
     * @Method description : 서비스 연결 통계 목록의 기준일을 조회한다.
     * @param              : StatsServiceVO
     * @return             : List<StatsServiceVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<StatsServiceVO> selectStatsServiceSearchDate(StatsServiceVO statsServiceVO) throws Exception{
        return (List<StatsServiceVO>) list("apt.StatsServiceDAO.selectStatsServiceSearchDate", statsServiceVO);
    }
        
    /**
     * @Method Name        : selectStatsServiceList
     * @Method description : 서비스 연결 통계 목록을 조회한다.
     * @param              : StatsServiceVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsServiceList(StatsServiceVO statsServiceVO) throws Exception{
        return (List<HashMap>) list("apt.StatsServiceDAO.selectStatsServiceList", statsServiceVO);
    }

}
