package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceTermsDAO.java
* @Comment  : 금융정보제공 동의서 관리 DAO
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
@Repository("SptUserServiceTermsDAO")
public class SptUserServiceTermsDAO extends ComAbstractDAO{
    
    private static final Logger log = Logger.getLogger(SptUserServiceTermsDAO.class);
    
    /**
     * @Method Name        : selectSptUserServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : SptUserServiceTermsVO
     * @return             : List<SptUserServiceTermsVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserServiceTermsVO> selectSptUserServiceTermsList(SptUserServiceTermsVO sptUserServiceTermsVO) throws Exception{
        return (List<SptUserServiceTermsVO>) list("apt.SptUserServiceTermsDAO.selectSptUserServiceTermsList", sptUserServiceTermsVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceTermsListCnt
     * @Method description : 금융정보제공 동의서목록의 total cnt를 조회한다.
     * @param              : SptUserServiceTermsVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptUserServiceTermsListCnt(SptUserServiceTermsVO sptUserServiceTermsVO) throws Exception{
        return (Integer) select("apt.SptUserServiceTermsDAO.selectSptUserServiceTermsListCnt", sptUserServiceTermsVO);
    }
    
    /**
     * @Method Name        : selectSptUserServiceTermsListExcel
     * @Method description : 금융정보제공 동의서 Excel 목록 조회
     * @param              : SptUserServiceTermsVO
     * @return             : List<SptUserServiceTermsVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<SptUserServiceTermsVO> selectSptUserServiceTermsListExcel(SptUserServiceTermsVO sptUserServiceTermsVO) throws Exception{
        return (List<SptUserServiceTermsVO>) list("apt.SptUserServiceTermsDAO.selectSptUserServiceTermsListExcel", sptUserServiceTermsVO);
    }
}
