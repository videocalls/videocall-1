package kr.co.koscom.oppf.apt.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.api.service.ApiManageService;
import kr.co.koscom.oppf.apt.api.service.ApiManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiManageServiceImpl.java
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
@Service("ApiManageService")
public class ApiManageServiceImpl implements ApiManageService{
    
    @Resource(name = "ApiManageDAO")
    private ApiManageDAO apiManageDAO;
    
    /**
     * @Method Name        : selectApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiManageList(ApiManageVO apiManageVO) throws Exception{
    	List<ApiManageVO> result = apiManageDAO.selectApiManageList(apiManageVO);
    	int totCnt = apiManageDAO.selectApiManageListTotalCount(apiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiManageListExcel(ApiManageVO apiManageVO) throws Exception{
    	List<ApiManageVO> result = apiManageDAO.selectApiManageListExcel(apiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : ApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public ApiManageVO selectApiManageDtl(ApiManageVO apiManageVO) throws Exception{
    	return apiManageDAO.selectApiManageDtl(apiManageVO);
    }
    
    /**
     * @Method Name        : saveApiManage
     * @Method description : api 저장
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveApiManage(ApiManageVO apiManageVO) throws Exception{
    	int saveCnt = apiManageDAO.selectApiManageChkCnt(apiManageVO);
    	int result = 0;
    	
    	//insert
    	if(saveCnt <= 0){
    		result = apiManageDAO.insertApiManage(apiManageVO);
    	}else{
    		result = apiManageDAO.updateApiManage(apiManageVO);
    	}
    	result = apiManageDAO.insertApiManageHist(apiManageVO);
    	
    	return result;
    }
    
}
