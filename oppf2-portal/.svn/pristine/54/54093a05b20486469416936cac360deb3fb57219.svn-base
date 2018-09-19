package kr.co.koscom.oppf.apt.plan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.plan.service.ApiPlanManageService;
import kr.co.koscom.oppf.apt.plan.service.ApiPlanManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiPlanManageServiceImpl.java
* @Comment  : api 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.05.27
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  신동진        최초생성
*
*/
@Service("ApiPlanManageService")
public class ApiPlanManageServiceImpl implements ApiPlanManageService{
    
    @Resource(name = "ApiPlanManageDAO")
    private ApiPlanManageDAO apiPlanManageDAO;
    
    /**
     * @Method Name        : selectApiPlanManageList
     * @Method description : api plan 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManageList(ApiPlanManageVO apiPlanManageVO) throws Exception{
    	List<ApiPlanManageVO> result = apiPlanManageDAO.selectApiPlanManageList(apiPlanManageVO);
    	int totCnt = apiPlanManageDAO.selectApiPlanManageListTotalCount(apiPlanManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiPlanManageListExcel
     * @Method description : api plan excel 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManageListExcel(ApiPlanManageVO apiPlanManageVO) throws Exception{
    	List<ApiPlanManageVO> result = apiPlanManageDAO.selectApiPlanManageListExcel(apiPlanManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiPlanManagePopupList
     * @Method description : api plan 등록 팝업 목록을 조회한다.
     * @param              : ApiPlanManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception{
    	List<ApiPlanManageVO> result = apiPlanManageDAO.selectApiPlanManagePopupList(apiPlanManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : saveApiPlanManagePopupList
     * @Method description : api plan을 저장한다.
     * @param              : ApiPlanManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveApiPlanManagePopupList(ApiPlanManageVO apiPlanManageVO) throws Exception{
    	int result = apiPlanManageDAO.saveApiPlanManagePopupListBefore(apiPlanManageVO);	//기존 data update
    	result = apiPlanManageDAO.saveApiPlanManagePopupList(apiPlanManageVO);				//저장
    	
    	return result;
    }
}
