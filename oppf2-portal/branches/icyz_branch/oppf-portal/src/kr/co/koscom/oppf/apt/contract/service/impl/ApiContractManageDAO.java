package kr.co.koscom.oppf.apt.contract.service.impl;

import kr.co.koscom.oppf.apt.contract.service.ApiContractManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiContractManageDAO.java
* @Comment  : api 게약 관리를 위한 DAO
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
@Repository("ApiContractManageDAO")
public class ApiContractManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectApiContractManageListTotalCount
     * @Method description : api 계약 목록의 총개수를 조회한다.
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectApiContractManageListTotalCount(ApiContractManageVO apiContractManageVO) throws Exception{
        return (Integer) select("apt.ApiContractManageDAO.selectApiContractManageListTotalCount", apiContractManageVO);
    }

    /**
     * @Method Name        : selectApiContractManageList
     * @Method description : api 계약 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : List<ApiContractManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiContractManageVO> selectApiContractManageList(ApiContractManageVO apiContractManageVO) throws Exception{
        return (List<ApiContractManageVO>) list("apt.ApiContractManageDAO.selectApiContractManageList", apiContractManageVO);
    }
    
    /**
     * @Method Name        : selectApiContractManageListExcel
     * @Method description : api 계약 excel 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : List<ApiContractManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<ApiContractManageVO> selectApiContractManageListExcel(ApiContractManageVO apiContractManageVO) throws Exception{
        return (List<ApiContractManageVO>) list("apt.ApiContractManageDAO.selectApiContractManageListExcel", apiContractManageVO);
    }
    
    /**
     * @Method Name        : selectApiContractManageDtl
     * @Method description : api 계약 상세정보를 조회한다.
     * @param              : ApiContractManageVO
     * @return             : ApiContractManageVO
     * @throws             : Exception
     */
    public ApiContractManageVO selectApiContractManageDtl(ApiContractManageVO apiContractManageVO) throws Exception{
        return (ApiContractManageVO) select("apt.ApiContractManageDAO.selectApiContractManageDtl", apiContractManageVO);
    }
    
    /**
     * @Method Name        : selectApiContractManageChkCnt
     * @Method description : api 계약 저장 시 기존데이터가 있는지 여부를 가져온다.
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectApiContractManageChkCnt(ApiContractManageVO apiContractManageVO) throws Exception{
        return (Integer) select("apt.ApiContractManageDAO.selectApiContractManageChkCnt", apiContractManageVO);
    }
    
    /**
     * @Method Name        : insertApiContractManage
     * @Method description : api 계약 등록한다.
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertApiContractManage(ApiContractManageVO apiContractManageVO) throws Exception{
        return (Integer) update("apt.ApiContractManageDAO.insertApiContractManage", apiContractManageVO);
    }
    
    /**
     * @Method Name        : updateApiContractManage
     * @Method description : api 계약 수정한다.
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateApiContractManage(ApiContractManageVO apiContractManageVO) throws Exception{
        return (Integer) update("apt.ApiContractManageDAO.updateApiContractManage", apiContractManageVO);
    }
    
    /**
     * @Method Name        : insertApiContractManageHist
     * @Method description : api 계약 hist 정보를 insert한다.
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertApiContractManageHist(ApiContractManageVO apiContractManageVO) throws Exception{
        return (Integer) update("apt.ApiContractManageDAO.insertApiContractManageHist", apiContractManageVO);
    }
}
