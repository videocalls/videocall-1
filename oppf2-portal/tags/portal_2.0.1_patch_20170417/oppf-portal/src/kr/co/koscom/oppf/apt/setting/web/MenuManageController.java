package kr.co.koscom.oppf.apt.setting.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.setting.service.MenuManageService;
import kr.co.koscom.oppf.apt.setting.service.MenuManageVO;
import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MenuManageController.java
* @Comment  : 관리자의 메뉴 관리를 위한 Controller
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
@Controller
public class MenuManageController {
	
	private static final Logger log = Logger.getLogger(MenuManageController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "MenuManageService")
    private MenuManageService menuManageService;
	
	
    /**
     * @Method Name        : menuManageList
     * @Method description : 메뉴관리 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/menuManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String menuManageList(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/setting/menuManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : 시스템 식별
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G015");//시스템 식별
        List<CmmFuncVO> systemKindIdList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("systemKindIdList", systemKindIdList);
        
        //셋팅 공통코드 : 시스템 식별
        cmmFuncVO.setSystem_grp_code("G020");//메뉴형태
        List<CmmFuncVO> menuTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("menuTypeList", menuTypeList);
        
        //param
		model.addAttribute("MenuManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectMenuManageList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectMenuManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMenuManageList(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = menuManageService.selectMenuManageList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectMenuManageDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectMenuManageDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMenuManageDtl(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		MenuManageVO resultDetail = menuManageService.selectMenuManageDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertMenu
     * @Method description : 메뉴 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/insertMenu.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertMenu(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		String menuId = menuManageService.insertMenu(paramVO);
		int result = 0;
		if(!"".equals(OppfStringUtil.isNullToString(menuId))) result = 1;
		
		
        model.addAttribute("result", result);
        model.addAttribute("menuId", menuId);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateMenu
     * @Method description : 메뉴 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/updateMenu.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateMenu(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = menuManageService.updateMenu(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteMenu
     * @Method description : 코드그룹 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/deleteMenu.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteMenu(@ModelAttribute("MenuManageVO") MenuManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = menuManageService.deleteMenu(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
}
