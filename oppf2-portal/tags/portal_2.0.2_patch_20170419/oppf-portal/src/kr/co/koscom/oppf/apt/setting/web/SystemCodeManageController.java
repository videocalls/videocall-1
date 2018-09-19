package kr.co.koscom.oppf.apt.setting.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageService;
import kr.co.koscom.oppf.apt.setting.service.SystemCodeManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SystemCodeManageController.java
* @Comment  : 관리자의 공통코드 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.05.16
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.16  신동진        최초생성
*
*/
@Controller
public class SystemCodeManageController {
	
	private static final Logger log = Logger.getLogger(SystemCodeManageController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SystemCodeManageService")
    private SystemCodeManageService systemCodeManageService;
	
	
    /**
     * @Method Name        : systemCodeList
     * @Method description : 공통코드 관리 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/systemCodeList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String systemCodeList(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/setting/systemCodeList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //param
		model.addAttribute("SystemCodeManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectSystemCodeList
     * @Method description : 공통코드 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectSystemCodeList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSystemCodeList(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = systemCodeManageService.selectSystemCodeList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectSystemGrpCodeDetail
     * @Method description : 코드그룹의 상세정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectSystemGrpCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSystemGrpCodeDtl(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request,ModelMap model)throws Exception{
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		SystemCodeManageVO resultDetail = systemCodeManageService.selectSystemGrpCodeDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
		
	/**
     * @Method Name        : chkSystemGrpCodeDtl
     * @Method description : 그룹코드의 저장가능 여부를 체크한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/chkSystemGrpCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String chkSystemGrpCodeDtl(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		int result = systemCodeManageService.chkSystemGrpCodeDtl(paramVO);
        
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertSystemGrpCode
     * @Method description : 코드그룹 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/insertSystemGrpCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertSystemGrpCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.insertSystemGrpCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateSystemGrpCode
     * @Method description : 코드그룹 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/updateSystemGrpCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSystemGrpCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.updateSystemGrpCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteSystemGrpCode
     * @Method description : 코드그룹 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/deleteSystemGrpCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteSystemGrpCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.deleteSystemGrpCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectSystemCodeDtl
     * @Method description : 코드의 상세정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectSystemCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSystemCodeDtl(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		SystemCodeManageVO resultDetail = systemCodeManageService.selectSystemCodeDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : chkSystemCodeDtl
     * @Method description : 코드의 저장가능 여부를 체크한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/chkSystemCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String chkSystemCodeDtl(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		int result = systemCodeManageService.chkSystemCodeDtl(paramVO);
        
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertSystemCode
     * @Method description : 코드 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/insertSystemCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertSystemCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.insertSystemCode(paramVO);
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateSystemCode
     * @Method description : 코드 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/updateSystemCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSystemCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.updateSystemCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteSystemCode
     * @Method description : 코드 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/deleteSystemCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteSystemCode(@ModelAttribute("SystemCodeManageVO") SystemCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = systemCodeManageService.deleteSystemCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
}
