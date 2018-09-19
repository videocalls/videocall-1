package kr.co.koscom.oppf.apt.terms.service.impl;

import kr.co.koscom.oppf.apt.terms.service.TermsContentManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : TermsContentManageDAO.java
* @Comment  : 관리자의 약관동의 내용 관리를 위한 DAO 클래스
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
@Repository("TermsContentManageDAO")
public class TermsContentManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectTermsContentList
     * @Method description : 약관동의 내용의 목록을 조회한다.
     * @param              : TermsContentManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<TermsContentManageVO> selectTermsContentList(TermsContentManageVO termsContentManageVO) throws Exception{
    	 return (List<TermsContentManageVO>) list("apt.TermsContentManageDAO.selectTermsContentList", termsContentManageVO);
    }
    
    /**
     * @Method Name        : selectTermsContentDtl
     * @Method description : 약관동의 내용의 상세를 조회한다.
     * @param              : TermsContentManageVO
     * @return             : TermsContentManageVO
     * @throws             : Exception
     */
    public TermsContentManageVO selectTermsContentDtl(TermsContentManageVO termsContentManageVO) throws Exception{
    	return (TermsContentManageVO) select("apt.TermsContentManageDAO.selectTermsContentDtl", termsContentManageVO);
    }
    
    /**
     * @Method Name        : insertTermsContentHist
     * @Method description : 약관동의 내용 hist 등록
     * @param              : TermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public int insertTermsContentHist(TermsContentManageVO termsContentManageVO) throws Exception{
    	return update("apt.TermsContentManageDAO.insertTermsContentHist", termsContentManageVO);
    }
    
    
    /**
     * @Method Name        : insertTermsContent
     * @Method description : 약관동의 내용 등록
     * @param              : TermsContentManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertTermsContent(TermsContentManageVO termsContentManageVO) throws Exception{
    	return (String) insert("apt.TermsContentManageDAO.insertTermsContent", termsContentManageVO);
    }
    
    /**
     * @Method Name        : updateTermsContent
     * @Method description : 약관동의 내용 수정
     * @param              : TermsContentManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateTermsContent(TermsContentManageVO termsContentManageVO) throws Exception{
    	return update("apt.TermsContentManageDAO.updateTermsContent", termsContentManageVO);
    }
    
}
