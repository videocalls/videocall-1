package kr.co.koscom.oppf.apt.cptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.cptUsr.service.CptUserManageService;
import kr.co.koscom.oppf.apt.cptUsr.service.CptUserManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptUserManageServiceImpl.java
* @Comment  : 기업회원 관리를 위한  service impl
* @author   : 신동진
* @since    : 2016.06.24
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.24  신동진        최초생성
*
*/
@Service("CptUserManageService")
public class CptUserManageServiceImpl implements CptUserManageService{
    
    @Resource(name = "CptUserManageDAO")
    private CptUserManageDAO cptUserManageDAO;
        
    /**
     * @Method Name        : selectCptUserManageList
     * @Method description : 기업 회원 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageList(CptUserManageVO cptUserManageVO) throws Exception{
    	List<CptUserManageVO> result = cptUserManageDAO.selectCptUserManageList(cptUserManageVO);
    	int totCnt = cptUserManageDAO.selectCptUserManageListCnt(cptUserManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }    
    
    /**
     * @Method Name        : selectCptUserManageListExcel
     * @Method description : 기업 회원 excel 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageListExcel(CptUserManageVO cptUserManageVO) throws Exception{
    	List<CptUserManageVO> result = cptUserManageDAO.selectCptUserManageListExcel(cptUserManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptUserManageDtl
     * @Method description : 기업회원조회 상세를 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageDtl(CptUserManageVO cptUserManageVO) throws Exception{
    	CptUserManageVO resultDetail = cptUserManageDAO.selectCptUserManageDtl(cptUserManageVO);
    	CptUserManageVO resultDetailCompany = cptUserManageDAO.selectCptUserManageDtlCompany(cptUserManageVO);
    	
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultDetail", resultDetail);
    	map.put("resultDetailCompany", resultDetailCompany);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : checkCptUserManageOperatorId
     * @Method description : 기업회원 ID 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorId(CptUserManageVO cptUserManageVO) throws Exception{
    	return cptUserManageDAO.checkCptUserManageOperatorId(cptUserManageVO);
    }
    
    /**
     * @Method Name        : checkCptUserManageOperatorEmail
     * @Method description : 기업회원 email 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorEmail(CptUserManageVO cptUserManageVO) throws Exception{
    	return cptUserManageDAO.checkCptUserManageOperatorEmail(cptUserManageVO);
    }
    
    /**
     * @Method Name        : insertCptUserManage
     * @Method description : 기업회원 정보를 insert
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertCptUserManage(CptUserManageVO cptUserManageVO) throws Exception{
    	String operatorProfileRegNo = cptUserManageDAO.insertCptUserManage(cptUserManageVO);
    	cptUserManageVO.setOperatorProfileRegNo(operatorProfileRegNo);
    	
    	//hist
    	int result = cptUserManageDAO.insertCptUserManageHist(cptUserManageVO);
    	//실패시 빈값으로 리턴
    	if(result <= 0) operatorProfileRegNo = "";
    	
    	return operatorProfileRegNo;
    }
    
    /**
     * @Method Name        : updateCptUserManage
     * @Method description : 기업회원 정보를 update
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptUserManage(CptUserManageVO cptUserManageVO) throws Exception{
    	int result = cptUserManageDAO.updateCptUserManage(cptUserManageVO);
    	result = cptUserManageDAO.insertCptUserManageHist(cptUserManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : saveCptUserManagePwd
     * @Method description : 임시비밀번호 발급
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveCptUserManagePwd(CptUserManageVO cptUserManageVO) throws Exception{
    	int result = cptUserManageDAO.saveCptUserManagePwd(cptUserManageVO);
    	result = cptUserManageDAO.insertCptUserManageHist(cptUserManageVO);
    	
    	return result;
    }
}
