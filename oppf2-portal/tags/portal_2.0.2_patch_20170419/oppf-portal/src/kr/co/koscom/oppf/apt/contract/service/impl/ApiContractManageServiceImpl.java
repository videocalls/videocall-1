package kr.co.koscom.oppf.apt.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.contract.service.ApiContractManageService;
import kr.co.koscom.oppf.apt.contract.service.ApiContractManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiContractManageServiceImpl.java
* @Comment  : api 계약 관리를 위한 Service
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
@Service("ApiContractManageService")
public class ApiContractManageServiceImpl implements ApiContractManageService{
    
    @Resource(name = "ApiContractManageDAO")
    private ApiContractManageDAO apiContractManageDAO;
    
    /**
     * @Method Name        : selectApiContractManageList
     * @Method description : api 계약 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiContractManageList(ApiContractManageVO apiContractManageVO) throws Exception{
    	List<ApiContractManageVO> result = apiContractManageDAO.selectApiContractManageList(apiContractManageVO);
    	int totCnt = apiContractManageDAO.selectApiContractManageListTotalCount(apiContractManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiContractManageListExcel
     * @Method description : api 계약 excel 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiContractManageListExcel(ApiContractManageVO apiContractManageVO) throws Exception{
    	List<ApiContractManageVO> result = apiContractManageDAO.selectApiContractManageListExcel(apiContractManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectApiContractManageDtl
     * @Method description : api 계약 상세정보를 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public ApiContractManageVO selectApiContractManageDtl(ApiContractManageVO apiContractManageVO) throws Exception{
    	return apiContractManageDAO.selectApiContractManageDtl(apiContractManageVO);
    }
    
    /**
     * @Method Name        : saveApiContractManage
     * @Method description : api 계약 저장
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveApiContractManage(ApiContractManageVO apiContractManageVO) throws Exception{
    	int saveCnt = apiContractManageDAO.selectApiContractManageChkCnt(apiContractManageVO);
    	int result = 0;
    	
    	//insert
    	if(saveCnt <= 0){
    		result = apiContractManageDAO.insertApiContractManage(apiContractManageVO);
    	}else{
    		result = apiContractManageDAO.updateApiContractManage(apiContractManageVO);
    	}
    	result = apiContractManageDAO.insertApiContractManageHist(apiContractManageVO);
    	
    	return result;
    }
    
}
