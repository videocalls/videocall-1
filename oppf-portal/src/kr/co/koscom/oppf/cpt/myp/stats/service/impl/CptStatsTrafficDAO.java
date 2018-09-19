package kr.co.koscom.oppf.cpt.myp.stats.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.myp.stats.service.CptStatsTrafficVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptStatsTrafficDAO.java
* @Comment  : 기업포털 Traffic 통계 관리를 위한 위한 DAO
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
@Repository("CptStatsTrafficDAO")
public class CptStatsTrafficDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectStatsTrafficPubCompanyList
     * @Method description : API 서비스 제공자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficPubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficPubCompanyList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiCategoryList
     * @Method description : API 서비스 구분 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficApiCategoryList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiCategoryList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiNameList
     * @Method description : API 서비스 이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficApiNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiNameList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiServiceList
     * @Method description : 세부 API 서비스 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficApiServiceList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiServiceList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSubCompanyList
     * @Method description : 앱 개발자 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficSubCompanyList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficSubCompanyList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficAppNameList
     * @Method description : 앱이름 코드 조회
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficAppNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficAppNameList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiPlanNameList
     * @Method description : Plan 종류
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficApiPlanNameList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiPlanNameList", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectCompanyCodeId
     * @Method description : 로그인 사용자의 companyCodeId를 가져온다.
     * @param              : CptStatsTrafficVO
     * @return             : String
     * @throws             : Exception
     */
    public String selectCompanyCodeId(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
    	return (String) select("cpt.myp.CptStatsTrafficDAO.selectCompanyCodeId", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficSearchDate
     * @Method description : 트레픽 통계 목록의 기준일을 조회한다.
     * @param              : CptStatsTrafficVO
     * @return             : List<CptStatsTrafficVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptStatsTrafficVO> selectStatsTrafficSearchDate(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficSearchDate", cptStatsTrafficVO);
    }
    
    /**
     * @Method Name        : selectStatsTrafficApiList
     * @Method description : API 트레픽 통계 목록을 조회한다. (건수 기준)
     * @param              : CptStatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsTrafficApiList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<HashMap>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiList", cptStatsTrafficVO);
    }
        
    /**
     * @Method Name        : selectStatsTrafficAppList
     * @Method description : APP 트레픽 통계 목록을 조회한다. (건수 기준)
     * @param              : CptStatsTrafficVO
     * @return             : List<HashMap>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> selectStatsTrafficAppList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<HashMap>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficAppList", cptStatsTrafficVO);
    }

    /**
     * 검색 일자 데이터 조회
     * @param cptStatsTrafficVO
     * @return
     * @throws Exception
     */
    public List<CptStatsTrafficVO> selectDayStdList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.searchDayStdList", cptStatsTrafficVO);
    }

    /**
     * 전체 검색
     * @param cptStatsTrafficVO
     * @return
     * @throws Exception
     */
    public List<HashMap<String,Object>> selectStatsTrafficListAll(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<HashMap<String,Object>>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficListAll", cptStatsTrafficVO);
    }

    /**
     * 통계 기준별 검색
     * @param cptStatsTrafficVO
     * @return
     * @throws Exception
     */
    public List<HashMap<String,Object>> selectStatsTrafficList(CptStatsTrafficVO cptStatsTrafficVO) throws Exception{
        return (List<HashMap<String,Object>>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficList", cptStatsTrafficVO);
    }

    public List<CptStatsTrafficVO> selectStatsTrafficApiNameListForApp(CptStatsTrafficVO cptStatsTrafficVO) throws Exception {
        return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficApiNameListForApp", cptStatsTrafficVO);
    }

    public List<CptStatsTrafficVO> selectStatsTrafficSubCompanyListForApi(CptStatsTrafficVO cptStatsTrafficVO) throws Exception {
        return (List<CptStatsTrafficVO>) list("cpt.myp.CptStatsTrafficDAO.selectStatsTrafficSubCompanyListForApi", cptStatsTrafficVO);
    }
}
