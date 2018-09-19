package kr.co.koscom.oppf.apt.contract.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiContractManageService.java
* @Comment  : api 계약 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
public interface ApiContractManageService{
	
    /**
     * @Method Name        : selectApiContractManageList
     * @Method description : api 계약 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiContractManageList(ApiContractManageVO apiContractManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiContractManageListExcel
     * @Method description : api 계약 excel 목록을 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectApiContractManageListExcel(ApiContractManageVO apiContractManageVO) throws Exception;
    
    /**
     * @Method Name        : selectApiContractManageDtl
     * @Method description : api 계약 상세정보를 조회한다.
     * @param              : ApiContractManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public ApiContractManageVO selectApiContractManageDtl(ApiContractManageVO apiContractManageVO) throws Exception;
    
    /**
     * @Method Name        : saveApiContractManage
     * @Method description : api 계약 저장
     * @param              : ApiContractManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveApiContractManage(ApiContractManageVO apiContractManageVO) throws Exception;
}
