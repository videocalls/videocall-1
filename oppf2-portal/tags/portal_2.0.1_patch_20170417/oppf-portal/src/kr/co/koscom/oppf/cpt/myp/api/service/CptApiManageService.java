package kr.co.koscom.oppf.cpt.myp.api.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptApiManageService.java
* @Comment  : 기업사용자의 api 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
public interface CptApiManageService{
	
    /**
     * @Method Name        : selectCptApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptApiManageList(CptApiManageVO cptApiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptApiManageListExcel(CptApiManageVO cptApiManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptApiManageDtl
     * @Method description : api 상세정보를 조회한다.
     * @param              : CptApiManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public CptApiManageVO selectCptApiManageDtl(CptApiManageVO cptApiManageVO) throws Exception;   
    
}
