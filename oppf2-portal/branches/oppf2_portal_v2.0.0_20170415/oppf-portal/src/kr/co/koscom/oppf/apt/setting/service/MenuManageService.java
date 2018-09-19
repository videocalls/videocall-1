package kr.co.koscom.oppf.apt.setting.service;

import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MenuManageService.java
* @Comment  : 관리자의 메뉴 관리를 위한 service
* @author   : 신동진
* @since    : 2016.05.23
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.23  신동진        최초생성
*
*/
public interface MenuManageService{
    
    /**
     * @Method Name        : selectMenuManageList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : MenuManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMenuManageList(MenuManageVO menuManageVO) throws Exception;
    
    /**
     * @Method Name        : selectMenuManageDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : MenuManageVO
     * @return             : MenuManageVO
     * @throws             : Exception
     */
    public MenuManageVO selectMenuManageDtl(MenuManageVO menuManageVO) throws Exception;
    
    /**
     * @Method Name        : insertMenu
     * @Method description : 메뉴 등록
     * @param              : MenuManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertMenu(MenuManageVO menuManageVO) throws Exception;
    
    /**
     * @Method Name        : updateMenu
     * @Method description : 메뉴 수정
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMenu(MenuManageVO menuManageVO) throws Exception;
    
    /**
     * @Method Name        : deleteMenu
     * @Method description : 메뉴 삭제
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMenu(MenuManageVO menuManageVO) throws Exception;
}
