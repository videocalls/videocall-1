package kr.co.koscom.oppf.apt.api.service.impl;

import kr.co.koscom.oppf.apt.api.service.ApiManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiManageDAO.java
* @Comment  : api 관리를 위한 DAO
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
@Repository("ApiManageDAO")
public class ApiManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectApiManageListTotalCount
     * @Method description : api 목록의 총개수를 조회한다.
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectApiManageListTotalCount(ApiManageVO apiManageVO) throws Exception{
        return (Integer) select("apt.ApiManageDAO.selectApiManageListTotalCount", apiManageVO);
    }

    /**
     * @Method Name        : selectApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : List<ApiManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiManageVO> selectApiManageList(ApiManageVO apiManageVO) throws Exception{
        return (List<ApiManageVO>) list("apt.ApiManageDAO.selectApiManageList", apiManageVO);
    }
    
    /**
     * @Method Name        : selectApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : ApiManageVO
     * @return             : List<ApiManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiManageVO> selectApiManageListExcel(ApiManageVO apiManageVO) throws Exception{
        return (List<ApiManageVO>) list("apt.ApiManageDAO.selectApiManageListExcel", apiManageVO);
    }
    
    /**
     * @Method Name        : selectApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : ApiManageVO
     * @return             : ApiManageVO
     * @throws             : Exception
     */
    public ApiManageVO selectApiManageDtl(ApiManageVO apiManageVO) throws Exception{
        return (ApiManageVO) select("apt.ApiManageDAO.selectApiManageDtl", apiManageVO);
    }
    
    /**
     * @Method Name        : selectApiManageChkCnt
     * @Method description : api 저장 시 기존데이터가 있는지 여부를 가져온다.
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectApiManageChkCnt(ApiManageVO apiManageVO) throws Exception{
        return (Integer) select("apt.ApiManageDAO.selectApiManageChkCnt", apiManageVO);
    }
    
    /**
     * @Method Name        : insertApiManage
     * @Method description : api 등록한다.
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertApiManage(ApiManageVO apiManageVO) throws Exception{
        return (Integer) update("apt.ApiManageDAO.insertApiManage", apiManageVO);
    }
    
    /**
     * @Method Name        : updateApiManage
     * @Method description : api 수정한다.
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateApiManage(ApiManageVO apiManageVO) throws Exception{
        return (Integer) update("apt.ApiManageDAO.updateApiManage", apiManageVO);
    }
    
    /**
     * @Method Name        : insertApiManageHist
     * @Method description : api hist 정보를 insert한다.
     * @param              : ApiManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertApiManageHist(ApiManageVO apiManageVO) throws Exception{
        return (Integer) update("apt.ApiManageDAO.insertApiManageHist", apiManageVO);
    }
}
