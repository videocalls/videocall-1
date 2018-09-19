package kr.co.koscom.oppf.cpt.myp.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cpt.myp.api.service.CptApiManageService;
import kr.co.koscom.oppf.cpt.myp.api.service.CptApiManageVO;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiManageServiceImpl.java
* @Comment  : 기업사용자의 api 관리를 위한 Service
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
@Service("CptApiManageService")
public class CptApiManageServiceImpl implements CptApiManageService{
    
    @Resource(name = "CptApiManageDAO")
    private CptApiManageDAO cptApiManageDAO;
    
    /**
     * @Method Name        : selectCptApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptApiManageList(CptApiManageVO cptApiManageVO) throws Exception{
    	List<CptApiManageVO> result = cptApiManageDAO.selectCptApiManageList(cptApiManageVO);
    	int totCnt = cptApiManageDAO.selectCptApiManageListTotalCount(cptApiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptApiManageListExcel(CptApiManageVO cptApiManageVO) throws Exception{
    	List<CptApiManageVO> result = cptApiManageDAO.selectCptApiManageListExcel(cptApiManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public CptApiManageVO selectCptApiManageDtl(CptApiManageVO cptApiManageVO) throws Exception{
    	return cptApiManageDAO.selectCptApiManageDtl(cptApiManageVO);
    }
    
}
