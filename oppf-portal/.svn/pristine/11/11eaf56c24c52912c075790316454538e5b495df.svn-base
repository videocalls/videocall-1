package kr.co.koscom.oppf.apt.terms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.apt.terms.service.TermsContentManageService;
import kr.co.koscom.oppf.apt.terms.service.TermsContentManageVO;


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
@Service("TermsContentManageService")
public class TermsContentManageServiceImpl implements TermsContentManageService{
    
    @Resource(name = "TermsContentManageDAO")
    private TermsContentManageDAO termsContentManageDAO;
    
    /**
     * @Method Name        : selectTermsContentList
     * @Method description : 약관동의 내용의 목록을 조회한다.
     * @param              : TermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectTermsContentList(TermsContentManageVO termsContentManageVO) throws Exception{
    	List<TermsContentManageVO> result = termsContentManageDAO.selectTermsContentList(termsContentManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectTermsContentDtl
     * @Method description : 약관동의 내용의 상세를 조회한다.
     * @param              : TermsContentManageVO
     * @return             : TermsContentManageVO
     * @throws             : Exception
     */
    public TermsContentManageVO selectTermsContentDtl(TermsContentManageVO termsContentManageVO) throws Exception{
    	return termsContentManageDAO.selectTermsContentDtl(termsContentManageVO);
    }    
    
    /**
     * @Method Name        : insertTermsContent
     * @Method description : 약관동의 내용 등록
     * @param              : TermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    @Transactional
    public String insertTermsContent(TermsContentManageVO termsContentManageVO) throws Exception{
    	String customerTermsContentRegSeq = termsContentManageDAO.insertTermsContent(termsContentManageVO);
    	termsContentManageVO.setCustomerTermsContentRegSeq(customerTermsContentRegSeq);
    	
    	int result = termsContentManageDAO.insertTermsContentHist(termsContentManageVO);
    	//실패시 빈값으로 리턴
    	if(result <= 0) customerTermsContentRegSeq = "";
    	
    	return customerTermsContentRegSeq;
    }
    
    /**
     * @Method Name        : updateTermsContent
     * @Method description : 약관동의 내용 수정
     * @param              : TermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateTermsContent(TermsContentManageVO termsContentManageVO) throws Exception{
    	int result = termsContentManageDAO.updateTermsContent(termsContentManageVO);
    	result = termsContentManageDAO.insertTermsContentHist(termsContentManageVO);
    	
    	return result;
    }
}
