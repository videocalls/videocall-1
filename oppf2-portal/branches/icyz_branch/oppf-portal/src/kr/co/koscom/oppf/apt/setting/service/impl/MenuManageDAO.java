package kr.co.koscom.oppf.apt.setting.service.impl;

import kr.co.koscom.oppf.apt.setting.service.MenuManageVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MenuManageDAO.java
* @Comment  : 관리자의 메뉴 관리를 위한 DAO 클래스
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
@Repository("MenuManageDAO")
public class MenuManageDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectMenuManageList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : MenuManageVO
     * @return             : List<MenuManageVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) throws Exception{
        return (List<MenuManageVO>) list("apt.MenuManageDAO.selectMenuManageList", menuManageVO);
    }
    
    /**
     * @Method Name        : selectMenuManageDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : MenuManageVO
     * @return             : MenuManageVO
     * @throws             : Exception
     */
    public MenuManageVO selectMenuManageDtl(MenuManageVO menuManageVO) throws Exception{
    	return (MenuManageVO) select("apt.MenuManageDAO.selectMenuManageDtl", menuManageVO);
    }
    
    /**
     * @Method Name        : insertMenu
     * @Method description : 메뉴 등록
     * @param              : MenuManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertMenu(MenuManageVO menuManageVO) throws Exception{
    	MenuManageVO selectMenuId = (MenuManageVO) select("apt.MenuManageDAO.selectMenuId", menuManageVO);
    	menuManageVO.setMenuId(selectMenuId.getMenuId());
    	
    	String menuId = menuManageVO.getMenuId();
    	int result = update("apt.MenuManageDAO.insertMenu", menuManageVO);
    	
    	return menuId;
    }
    
    /**
     * @Method Name        : updateMenu
     * @Method description : 메뉴 수정
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMenu(MenuManageVO menuManageVO) throws Exception{
    	return update("apt.MenuManageDAO.updateMenu", menuManageVO);
    }
    
    /**
     * @Method Name        : deleteMenu
     * @Method description : 메뉴 삭제
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMenu(MenuManageVO menuManageVO) throws Exception{
    	return delete("apt.MenuManageDAO.deleteMenu", menuManageVO);
    }
    
}
