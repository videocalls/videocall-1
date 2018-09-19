package kr.co.koscom.oppf.apt.setting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.setting.service.MenuManageService;
import kr.co.koscom.oppf.apt.setting.service.MenuManageVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : NotiManageServiceImpl.java
* @Comment  : 관리자의 기업코드 관리를 위한 Service Impl 클래스
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Service("MenuManageService")
public class MenuManageServiceImpl implements MenuManageService{
    
    @Resource(name = "MenuManageDAO")
    private MenuManageDAO menuManageDAO;
    
    
    /**
     * @Method Name        : selectMenuManageList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : MenuManageVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMenuManageList(MenuManageVO menuManageVO) throws Exception{
    	List<MenuManageVO> result = menuManageDAO.selectMenuManageList(menuManageVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectMenuManageDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : MenuManageVO
     * @return             : MenuManageVO
     * @throws             : Exception
     */
    public MenuManageVO selectMenuManageDtl(MenuManageVO menuManageVO) throws Exception{
    	return menuManageDAO.selectMenuManageDtl(menuManageVO);
    }
    
    /**
     * @Method Name        : insertMenu
     * @Method description : 메뉴 등록
     * @param              : MenuManageVO
     * @return             : String
     * @throws             : Exception
     */
    public String insertMenu(MenuManageVO menuManageVO) throws Exception{
    	return menuManageDAO.insertMenu(menuManageVO);
    }
    
    /**
     * @Method Name        : updateMenu
     * @Method description : 메뉴 수정
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateMenu(MenuManageVO menuManageVO) throws Exception{
    	return menuManageDAO.updateMenu(menuManageVO);
    }
    
    /**
     * @Method Name        : deleteMenu
     * @Method description : 메뉴 삭제
     * @param              : MenuManageVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteMenu(MenuManageVO menuManageVO) throws Exception{
    	return menuManageDAO.deleteMenu(menuManageVO);
    }
}
