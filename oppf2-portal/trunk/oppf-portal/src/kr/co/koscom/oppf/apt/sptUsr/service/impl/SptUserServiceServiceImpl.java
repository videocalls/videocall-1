package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceServiceImpl.java
* @Comment  : 일반회원 관리를 위한  service impl
* @author   : 신동진
* @since    : 2016.06.20
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.20  신동진        최초생성
*
*/
@Service("SptUserServiceService")
public class SptUserServiceServiceImpl implements SptUserServiceService{
    
    @Resource(name = "SptUserServiceDAO")
    private SptUserServiceDAO sptUserServiceDAO;
    
    /**
     * @Method Name        : selectSptUserServiceAppList
     * @Method description : 앱이름 목록 조회
     * @param              : SptUserSeviceVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public List<SptUserServiceVO> selectSptUserServiceAppList(SptUserServiceVO sptUserServiceVO) throws Exception{
    	return sptUserServiceDAO.selectSptUserServiceAppList(sptUserServiceVO);
    }
        
    /**
     * @Method Name        : selectSptUserServiceList
     * @Method description : 서비스 연결 현황 목록 조회
     * @param              : SptUserServiceVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserServiceList(SptUserServiceVO sptUserServiceVO) throws Exception{
    	List<SptUserServiceVO> result = sptUserServiceDAO.selectSptUserServiceList(sptUserServiceVO);
    	int totCnt = sptUserServiceDAO.selectSptUserServiceListCnt(sptUserServiceVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectSptUserServiceListExcel
     * @Method description : 서비스 연결 현황 excel 목록 조회
     * @param              : SptUserServiceVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserServiceListExcel(SptUserServiceVO sptUserServiceVO) throws Exception{
    	List<SptUserServiceVO> result = sptUserServiceDAO.selectSptUserServiceListExcel(sptUserServiceVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
}
