package kr.co.koscom.oppf.apt.setting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageService;
import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageServiceImpl.java
* @Comment  : 관리자의 기업코드 관리를 위한 Service Impl 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Service("CompanyCodeManageService")
public class CompanyCodeManageServiceImpl implements CompanyCodeManageService{
    
    @Resource(name = "CompanyCodeManageDAO")
    private CompanyCodeManageDAO companyCodeManageDAO;
    
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드 목록을 조회한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCompanyCodeList(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	List<CompanyCodeManageVO> result = companyCodeManageDAO.selectCompanyCodeList(companyCodeManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectAptUserDtl
     * @Method description : 기업코드 상세를 조회한다.
     * @param              : AptUserManageVO
     * @return             : AptUserManageVO
     * @throws             : Exception
     */
    public CompanyCodeManageVO selectCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return companyCodeManageDAO.selectCompanyCodeDtl(companyCodeManageVO);
    }
    
    /**
     * @Method Name        : chkCompanyCodeDtl
     * @Method description : 기업코드 저장가능 여부를 체크한다.
     * @param              : AptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> chkCompanyCodeDtl(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	List<CompanyCodeManageVO> result = companyCodeManageDAO.chkCompanyCodeDtl(companyCodeManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : insertCompanyCode
     * @Method description : 기업코드 등록
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public String insertCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	String companyCodeRegNo = companyCodeManageDAO.insertCompanyCode(companyCodeManageVO);
    	companyCodeManageVO.setCompanyCodeRegNo(companyCodeRegNo);
    	
    	int result = companyCodeManageDAO.insertCompanyCodeHist(companyCodeManageVO);
    	//실패시 빈값으로 리턴
    	if(result <= 0) companyCodeRegNo = "";
    	
    	return companyCodeRegNo;
    }
    
    /**
     * @Method Name        : updateCompanyCode
     * @Method description : 기업코드 수정
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	int result = companyCodeManageDAO.updateCompanyCode(companyCodeManageVO);
    	result = companyCodeManageDAO.insertCompanyCodeHist(companyCodeManageVO);
    	
    	return result;
    }
    
    /**
     * @Method Name        : chkDeleteCompanyCode
     * @Method description : 기업코드 삭제가능 여부를 체크한다.
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int chkDeleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	return companyCodeManageDAO.chkDeleteCompanyCode(companyCodeManageVO);
    }
    
    /**
     * @Method Name        : deleteCompanyCode
     * @Method description : 기업코드 삭제
     * @param              : CompanyCodeManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteCompanyCode(CompanyCodeManageVO companyCodeManageVO) throws Exception{
    	int result = companyCodeManageDAO.deleteCompanyCode(companyCodeManageVO);
    	result = companyCodeManageDAO.insertCompanyCodeHist(companyCodeManageVO);
    	
    	return result;
    }
    
}
