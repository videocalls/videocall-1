package kr.co.koscom.oppf.apt.terms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.terms.service.CompanyTermsContentManageService;
import kr.co.koscom.oppf.apt.terms.service.CompanyTermsContentManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageServiceImpl.java
* @Comment  : 관리자의 약관동의 내용 관리를 위한 Service Impl 클래스
* @author   : 신동진
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  신동진        최초생성
*
*/
@Service("CompanyTermsContentManageService")
public class CompanyTermsContentManageServiceImpl implements CompanyTermsContentManageService{
    
    @Resource(name = "CompanyTermsContentManageDAO")
    private CompanyTermsContentManageDAO companyTermsContentManageDAO;
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업 약관동의의 기업정보를 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : List<CompanyTermsContentManageVO>
     * @throws             : Exception
     */
    public List<CompanyTermsContentManageVO> selectCompanyCodeList(CompanyTermsContentManageVO companytermsContentManageVO) throws Exception{
    	return companyTermsContentManageDAO.selectCompanyCodeList(companytermsContentManageVO);
    }
    
    /**
     * @Method Name        : selectCompanyTermsContentList
     * @Method description : 기업 약관동의 내용의 목록을 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCompanyTermsContentList(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	List<CompanyTermsContentManageVO> result = companyTermsContentManageDAO.selectCompanyTermsContentList(companyTermsContentManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCompanyTermsContentDtl
     * @Method description : 기업 약관동의 내용의 상세를 조회한다.
     * @param              : CompanyTermsContentManageVO
     * @return             : CompanyTermsContentManageVO
     * @throws             : Exception
     */
    public CompanyTermsContentManageVO selectCompanyTermsContentDtl(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	return companyTermsContentManageDAO.selectCompanyTermsContentDtl(companyTermsContentManageVO);
    }    
    
    /**
     * @Method Name        : insertCompanyTermsContent
     * @Method description : 기업 약관동의 내용 등록
     * @param              : CompanyTermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String insertCompanyTermsContent(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	String companyTermsContentRegSeq = companyTermsContentManageDAO.insertCompanyTermsContent(companyTermsContentManageVO);
    	companyTermsContentManageVO.setCompanyTermsContentRegSeq(companyTermsContentRegSeq);
    	
    	int result = companyTermsContentManageDAO.insertCompanyTermsContentHist(companyTermsContentManageVO);
    	//실패시 빈값으로 리턴
    	if(result <= 0) companyTermsContentRegSeq = "";
    	
    	return companyTermsContentRegSeq;
    }
    
    /**
     * @Method Name        : updateCompanyTermsContent
     * @Method description : 기업 약관동의 내용 수정
     * @param              : CompanyTermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateCompanyTermsContent(CompanyTermsContentManageVO companyTermsContentManageVO) throws Exception{
    	int result = companyTermsContentManageDAO.updateCompanyTermsContent(companyTermsContentManageVO);
    	result = companyTermsContentManageDAO.insertCompanyTermsContentHist(companyTermsContentManageVO);
    	
    	return result;
    }
}
