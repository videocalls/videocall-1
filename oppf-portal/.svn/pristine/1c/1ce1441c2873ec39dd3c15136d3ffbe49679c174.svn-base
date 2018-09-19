package kr.co.koscom.oppf.apt.app.service;

import java.util.List;
import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AppManageService.java
* @Comment  : app 관리를 위한 Service
* @author   : 신동진
* @since    : 2016.05.30
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.30  신동진        최초생성
*
*/
public interface AppManageService{
	
    /**
     * @Method Name        : selectAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAppManageList(AppManageVO appManageVO) throws Exception;
    
    /**
     * @Method Name        : selectAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectAppManageListExcel(AppManageVO appManageVO) throws Exception;
    
    /**
     * @Method Name        : selectAppManageDtl
     * @Method description : app 상세정보를 조회한다.
     * @param              : AppManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public AppManageVO selectAppManageDtl(AppManageVO appManageVO) throws Exception;
    
    /**
     * @Method Name        : selectAppManageDtlApiList
     * @Method description : app 상세의 서비스제공자 목록을 조회한다. 
     * @param              : AppManageVO
     * @return             : List<AppManageVO>
     * @throws             : Exception
     */
    public List<AppManageVO> selectAppManageDtlApiList(AppManageVO appManageVO) throws Exception;
    
    /**
     * @Method Name        : saveAppManage
     * @Method description : app 저장
     * @param              : AppManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveAppManage(AppManageVO appManageVO) throws Exception;
    
    
}
