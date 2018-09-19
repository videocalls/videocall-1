package kr.co.koscom.oppf.apt.cptUsr.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptUserManageService.java
* @Comment  : 기업회원 관리 service
* @author   : 신동진
* @since    : 2016.06.24
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.24  신동진        최초생성
*
*/
public interface CptUserManageService{
    
    /**
     * @Method Name        : selectCptUserManageList
     * @Method description : 기업회원 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageList(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptUserManageListExcel
     * @Method description : 기업회원 excel 목록을 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageListExcel(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : selectCptUserManageDtl
     * @Method description : 기업회원조회 상세를 조회한다.
     * @param              : CptUserManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptUserManageDtl(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : checkCptUserManageOperatorId
     * @Method description : 기업회원 ID 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorId(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : checkCptUserManageOperatorEmail
     * @Method description : 기업회원 email 중복체크
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int checkCptUserManageOperatorEmail(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : insertCptUserManage
     * @Method description : 기업회원 정보를 insert
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public String insertCptUserManage(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : updateCptUserManage
     * @Method description : 기업회원 정보를 update
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCptUserManage(CptUserManageVO cptUserManageVO) throws Exception;
    
    /**
     * @Method Name        : saveCptUserManagePwd
     * @Method description : 임시비밀번호 발급
     * @param              : CptUserManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int saveCptUserManagePwd(CptUserManageVO cptUserManageVO) throws Exception;
    
        
}
