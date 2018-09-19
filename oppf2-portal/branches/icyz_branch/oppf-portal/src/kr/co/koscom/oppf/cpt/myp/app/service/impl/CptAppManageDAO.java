package kr.co.koscom.oppf.cpt.myp.app.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.myp.app.service.CptAppManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptAppManageDAO.java
* @Comment  : 기업사용자의 app 관리를 위한 DAO
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
@Repository("CptAppManageDAO")
public class CptAppManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCptAppManageListTotalCount
     * @Method description : app 목록의 총개수를 조회한다.
     * @param              : CptAppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectCptAppManageListTotalCount(CptAppManageVO cptAppManageVO) throws Exception{
        return (Integer) select("cpt.myp.api.CptAppManageDAO.selectCptAppManageListTotalCount", cptAppManageVO);
    }

    /**
     * @Method Name        : selectCptAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : List<CptAppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptAppManageVO> selectCptAppManageList(CptAppManageVO cptAppManageVO) throws Exception{
        return (List<CptAppManageVO>) list("cpt.myp.api.CptAppManageDAO.selectCptAppManageList", cptAppManageVO);
    }
    
    /**
     * @Method Name        : selectCptAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : List<CptAppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptAppManageVO> selectCptAppManageListExcel(CptAppManageVO cptAppManageVO) throws Exception{
        return (List<CptAppManageVO>) list("cpt.myp.api.CptAppManageDAO.selectCptAppManageListExcel", cptAppManageVO);
    }
    
    /**
     * @Method Name        : selectCptAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : CptAppManageVO
     * @return             : CptAppManageVO
     * @throws             : Exception
     */
    public CptAppManageVO selectCptAppManageDtl(CptAppManageVO cptAppManageVO) throws Exception{
        return (CptAppManageVO) select("cpt.myp.api.CptAppManageDAO.selectCptAppManageDtl", cptAppManageVO);
    }
    
    /**
     * @Method Name        : selectCptAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : CptAppManageVO
     * @return             : List<CptAppManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptAppManageVO> selectCptAppManageDtlApiList(CptAppManageVO cptAppManageVO) throws Exception{
        return (List<CptAppManageVO>) list("cpt.myp.api.CptAppManageDAO.selectCptAppManageDtlApiList", cptAppManageVO);
    }
}
