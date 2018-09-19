package kr.co.koscom.oppf.cpt.myp.api.service.impl;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import kr.co.koscom.oppf.cpt.myp.api.service.CptApiManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptApiManageDAO.java
* @Comment  : 기업사용자의 api 관리를 위한한 DAO
* @author   : 신동진
* @since    : 2016.05.27
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  신동진        최초생성
*
*/
@Repository("CptApiManageDAO")
public class CptApiManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCptApiManageListTotalCount
     * @Method description : api 목록의 총개수를 조회한다.
     * @param              : CptApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectCptApiManageListTotalCount(CptApiManageVO cptApiManageVO) throws Exception{
        return (Integer) select("cpt.myp.api.ApiManageDAO.selectCptApiManageListTotalCount", cptApiManageVO);
    }

    /**
     * @Method Name        : selectCptApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : List<CptApiManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptApiManageVO> selectCptApiManageList(CptApiManageVO cptApiManageVO) throws Exception{
        return (List<CptApiManageVO>) list("cpt.myp.api.ApiManageDAO.selectCptApiManageList", cptApiManageVO);
    }
    
    /**
     * @Method Name        : selectCptApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : List<CptApiManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CptApiManageVO> selectCptApiManageListExcel(CptApiManageVO cptApiManageVO) throws Exception{
        return (List<CptApiManageVO>) list("cpt.myp.api.ApiManageDAO.selectCptApiManageListExcel", cptApiManageVO);
    }
    
    /**
     * @Method Name        : selectCptApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : CptApiManageVO
     * @return             : CptApiManageVO
     * @throws             : Exception
     */
    public CptApiManageVO selectCptApiManageDtl(CptApiManageVO cptApiManageVO) throws Exception{
        return (CptApiManageVO) select("cpt.myp.api.ApiManageDAO.selectCptApiManageDtl", cptApiManageVO);
    }
}
