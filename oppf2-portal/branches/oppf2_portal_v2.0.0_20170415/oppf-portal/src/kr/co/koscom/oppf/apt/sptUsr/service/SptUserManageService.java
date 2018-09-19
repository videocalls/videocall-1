package kr.co.koscom.oppf.apt.sptUsr.service;

import java.util.Map;

import kr.co.koscom.oppf.apt.aptUsr.service.AptUserManageVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserManageService.java
* @Comment  : 일반 회원 관리 service
* @author   : 신동진
* @since    : 2016.06.20
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.20  신동진        최초생성
*
*/
public interface SptUserManageService{
    
    /**
     * @Method Name        : selectSptUserManageList
     * @Method description : 일반 회원 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageList(SptUserManageVO sptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : selectSptUserManageListExcel
     * @Method description : 일반 회원 Excel 목록을 조회한다.
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageListExcel(SptUserManageVO sptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : selectSptUserManageDtl
     * @Method description : 일반회원관리 상세  조회
     * @param              : SptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : updateSptUserManageDtl
     * @Method description : 일반회원 정보 수정
     * @param              : sptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateSptUserManageDtl(SptUserManageVO sptUserManageVO) throws Exception;
        
}
