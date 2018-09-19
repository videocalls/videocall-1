package kr.co.koscom.oppf.cpt.myp.app.service;

import java.util.List;
import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptAppManageService.java
* @Comment  : 기업사용자의 app 관리를 위한 Service
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
public interface CptAppManageService{
	
    /**
     * @Method Name        : selectCptAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptAppManageList(CptAppManageVO cptAppManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptAppManageListExcel(CptAppManageVO cptAppManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : CptAppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public CptAppManageVO selectCptAppManageDtl(CptAppManageVO cptAppManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : CptAppManageVO
     * @return             : List<CptAppManageVO>
     * @throws             : Exception
     */
    public List<CptAppManageVO> selectCptAppManageDtlApiList(CptAppManageVO cptAppManageVO) throws Exception;
        
}
