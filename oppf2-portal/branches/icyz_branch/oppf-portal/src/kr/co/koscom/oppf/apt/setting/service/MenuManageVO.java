package kr.co.koscom.oppf.apt.setting.service;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MenuManageVO.java
* @Comment  : 관리자의 메뉴 관리를 위한 VO
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
@SuppressWarnings("serial")
public class MenuManageVO extends ComDefaultListVO{
	/**
	 * com_menu_info
	 */
	private String systemKindId;
	private String menuId;
	private String parentMenuId;
	private String menuName;
	private String menuType;
	private String menuOrder;
	private String menuUrl;
	private String loginYn;
	private String mobileYn;
	private String useYn;
	
	/**
	 * etc
	 */
	private String menuTypeName;
	private String treeMenuId;
	private String treeParentMenuId;
	private String treeLvl;
	private String treeOrder;
	
	private String searchSystemKindId;
	private String searchMenuId;
	
	public String getSystemKindId() {
		return systemKindId;
	}
	public void setSystemKindId(String systemKindId) {
		this.systemKindId = systemKindId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getLoginYn() {
		return loginYn;
	}
	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
	}
	public String getMobileYn() {
		return mobileYn;
	}
	public void setMobileYn(String mobileYn) {
		this.mobileYn = mobileYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMenuTypeName() {
		return menuTypeName;
	}
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	public String getTreeMenuId() {
		return treeMenuId;
	}
	public void setTreeMenuId(String treeMenuId) {
		this.treeMenuId = treeMenuId;
	}
	public String getTreeParentMenuId() {
		return treeParentMenuId;
	}
	public void setTreeParentMenuId(String treeParentMenuId) {
		this.treeParentMenuId = treeParentMenuId;
	}
	public String getTreeLvl() {
		return treeLvl;
	}
	public void setTreeLvl(String treeLvl) {
		this.treeLvl = treeLvl;
	}
	public String getTreeOrder() {
		return treeOrder;
	}
	public void setTreeOrder(String treeOrder) {
		this.treeOrder = treeOrder;
	}
	public String getSearchSystemKindId() {
		return searchSystemKindId;
	}
	public void setSearchSystemKindId(String searchSystemKindId) {
		this.searchSystemKindId = searchSystemKindId;
	}
	public String getSearchMenuId() {
		return searchMenuId;
	}
	public void setSearchMenuId(String searchMenuId) {
		this.searchMenuId = searchMenuId;
	}
}