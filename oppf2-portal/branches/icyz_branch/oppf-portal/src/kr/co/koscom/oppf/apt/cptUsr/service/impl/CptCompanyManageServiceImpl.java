package kr.co.koscom.oppf.apt.cptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageService;
import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageVO;




/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptCompanyManageServiceImpl.java
* @Comment  : 기업정보 관리를 위한  service impl
* @author   : 신동진
* @since    : 2016.06.21
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  신동진        최초생성
*
*/
@Service("CptCompanyManageService")
public class CptCompanyManageServiceImpl implements CptCompanyManageService{
    
    @Resource(name = "CptCompanyManageDAO")
    private CptCompanyManageDAO cptCompanyManageDAO;
        
    /**
     * @Method Name        : selectCptCompanyManageList
     * @Method description : 기업정보관리 목록을 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptCompanyManageList(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	List<CptCompanyManageVO> result = cptCompanyManageDAO.selectCptCompanyManageList(cptCompanyManageVO);
    	int totCnt = cptCompanyManageDAO.selectCptCompanyManageListCnt(cptCompanyManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptCompanyManageListExcel
     * @Method description : 기업정보관리 excel 목록을 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptCompanyManageListExcel(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	List<CptCompanyManageVO> result = cptCompanyManageDAO.selectCptCompanyManageListExcel(cptCompanyManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptCompanyCodePopupList
     * @Method description : 기업 코드 팝업 리스트 조회
     * @param              : CptCompanyManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptCompanyCodePopupList(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	List<CptCompanyManageVO> result = cptCompanyManageDAO.selectCptCompanyCodePopupList(cptCompanyManageVO);
    	int totCnt = cptCompanyManageDAO.sselectCptCompanyCodePopupListCnt(cptCompanyManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptCompanyManageDtl
     * @Method description : 기업정보관리 상세를 조회한다.
     * @param              : CptCompanyManageVO
     * @return             : CptCompanyManageVO
     * @throws             : Exception
     */
    public CptCompanyManageVO selectCptCompanyManageDtl(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	return cptCompanyManageDAO.selectCptCompanyManageDtl(cptCompanyManageVO);
    }
    
    /**
     * @Method Name        : insertCptCompanyManage
     * @Method description : 기업정보관리 상세를 insert
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public String insertCptCompanyManage(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	String companyProfileRegNo = cptCompanyManageDAO.insertCptCompanyManage(cptCompanyManageVO);
    	cptCompanyManageVO.setCompanyProfileRegNo(companyProfileRegNo);
    	
    	//hist
    	int result = cptCompanyManageDAO.insertCptCompanyManageHist(cptCompanyManageVO);
    	//실패시 빈값으로 리턴
    	if(result <= 0) companyProfileRegNo = "";
    	
    	return companyProfileRegNo;
    }
    
    /**
     * @Method Name        : updateCptCompanyManage
     * @Method description : 기업정보관리 상세를 update
     * @param              : CptCompanyManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCptCompanyManage(CptCompanyManageVO cptCompanyManageVO) throws Exception{
    	int result = cptCompanyManageDAO.updateCptCompanyManage(cptCompanyManageVO);
    	result = cptCompanyManageDAO.insertCptCompanyManageHist(cptCompanyManageVO);
    	
    	return result;
    }
    
}
