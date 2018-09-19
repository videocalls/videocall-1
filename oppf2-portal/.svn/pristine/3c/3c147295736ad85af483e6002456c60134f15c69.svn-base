package kr.co.koscom.oppf.cpt.myp.svcTerms.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.myp.svcTerms.service.CptServiceTermsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptServiceTermsDAO.java
* @Comment  : 기업회원 금융정보제공 동의서 관리 위한 DAO
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
@Repository("CptServiceTermsDAO")
public class CptServiceTermsDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCptServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : CptServiceTermsVO
     * @return             : List<CptServiceTermsVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptServiceTermsVO> selectCptServiceTermsList(CptServiceTermsVO cptServiceTermsVO) throws Exception{
        return (List<CptServiceTermsVO>) list("cpt.myp.svcTerms.CptServiceTermsDAO.selectCptServiceTermsList", cptServiceTermsVO);
    }
    
    /**
     * @Method Name        : selectCptServiceTermsListCnt
     * @Method description : 금융정보제공 동의서목록의 total cnt를 조회한다.
     * @param              : CptServiceTermsVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectCptServiceTermsListCnt(CptServiceTermsVO cptServiceTermsVO) throws Exception{
        return (Integer) select("cpt.myp.svcTerms.CptServiceTermsDAO.selectCptServiceTermsListCnt", cptServiceTermsVO);
    }
    
    /**
     * @Method Name        : selectCptServiceTermsListExcel
     * @Method description : 금융정보제공 동의서 Excel 목록 조회
     * @param              : CptServiceTermsVO
     * @return             : List<CptServiceTermsVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptServiceTermsVO> selectCptServiceTermsListExcel(CptServiceTermsVO cptServiceTermsVO) throws Exception{
        return (List<CptServiceTermsVO>) list("cpt.myp.svcTerms.CptServiceTermsDAO.selectCptServiceTermsListExcel", cptServiceTermsVO);
    }
}
