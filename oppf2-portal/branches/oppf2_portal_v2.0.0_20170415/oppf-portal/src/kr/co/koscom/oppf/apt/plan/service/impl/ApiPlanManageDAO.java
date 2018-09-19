package kr.co.koscom.oppf.apt.plan.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.plan.service.ApiPlanManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiPlanManageDAO.java
* @Comment  : api plan 관리를 위한 DAO
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
@Repository("ApiPlanManageDAO")
public class ApiPlanManageDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(ApiPlanManageDAO.class);
    
    /**
     * @Method Name        : selectApiPlanManageListTotalCount
     * @Method description : api plan 목록의 총개수를 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectApiPlanManageListTotalCount(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (Integer) select("apt.ApiPlanManageDAO.selectApiPlanManageListTotalCount", apiPlanManageVO);
    }

    /**
     * @Method Name        : selectApiPlanManageList
     * @Method description : api plan 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : List<ApiPlanManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiPlanManageVO> selectApiPlanManageList(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (List<ApiPlanManageVO>) list("apt.ApiPlanManageDAO.selectApiPlanManageList", apiPlanManageVO);
    }
    
    /**
     * @Method Name        : selectApiPlanManageListExcel
     * @Method description : api plan excel 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : List<ApiPlanManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiPlanManageVO> selectApiPlanManageListExcel(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (List<ApiPlanManageVO>) list("apt.ApiPlanManageDAO.selectApiPlanManageListExcel", apiPlanManageVO);
    }
    
    /**
     * @Method Name        : selectApiPlanManagePopupList
     * @Method description : api plan 등록 팝업 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : List<ApiPlanManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiPlanManageVO> selectApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (List<ApiPlanManageVO>) list("apt.ApiPlanManageDAO.selectApiPlanManagePopupList", apiPlanManageVO);
    }
    
    /**
     * @Method Name        : saveApiPlanManagePopupListBefore
     * @Method description : 기존 plan 정보를 리셋한다.
     * @param              : apiPlanManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveApiPlanManagePopupListBefore(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (Integer) update("apt.ApiPlanManageDAO.saveApiPlanManagePopupListBefore", apiPlanManageVO);
    }
    
    /**
     * @Method Name        : saveApiPlanManagePopupList
     * @Method description : plan 정보를 저장한다.
     * @param              : apiPlanManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception{
        return (Integer) update("apt.ApiPlanManageDAO.saveApiPlanManagePopupList", apiPlanManageVO);
    }
}
