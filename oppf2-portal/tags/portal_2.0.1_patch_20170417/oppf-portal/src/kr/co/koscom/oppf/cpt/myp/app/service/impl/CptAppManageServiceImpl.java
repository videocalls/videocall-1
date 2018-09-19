package kr.co.koscom.oppf.cpt.myp.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cpt.myp.app.service.CptAppManageService;
import kr.co.koscom.oppf.cpt.myp.app.service.CptAppManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptAppManageServiceImpl.java
* @Comment  : 기업사용자의 app 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
@Service("CptAppManageService")
public class CptAppManageServiceImpl implements CptAppManageService{
    
    @Resource(name = "CptAppManageDAO")
    private CptAppManageDAO cptAppManageDAO;
    
    /**
     * @Method Name        : selectCptAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptAppManageList(CptAppManageVO cptAppManageVO) throws Exception{
    	List<CptAppManageVO> result = cptAppManageDAO.selectCptAppManageList(cptAppManageVO);
    	int totCnt = cptAppManageDAO.selectCptAppManageListTotalCount(cptAppManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptAppManageListExcel(CptAppManageVO cptAppManageVO) throws Exception{
    	List<CptAppManageVO> result = cptAppManageDAO.selectCptAppManageListExcel(cptAppManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public CptAppManageVO selectCptAppManageDtl(CptAppManageVO cptAppManageVO) throws Exception{
    	return cptAppManageDAO.selectCptAppManageDtl(cptAppManageVO);
    }
    
    /**
     * @Method Name        : selectCptAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : CptAppManageVO
     * @return             : List<CptAppManageVO>
     * @throws             : Exception
     */
    public List<CptAppManageVO> selectCptAppManageDtlApiList(CptAppManageVO cptAppManageVO) throws Exception{
    	return cptAppManageDAO.selectCptAppManageDtlApiList(cptAppManageVO);
    }
}
