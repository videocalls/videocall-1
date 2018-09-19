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

import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageService;
import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageVO;
import kr.co.koscom.oppf.cmm.noti.service.NotiManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyCodeManageController.java
* @Comment  : 관리자의 기업코드 관리를 위한 Controller
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
public class CompanyCodeManageController {
	
	private static final Logger log = Logger.getLogger(CompanyCodeManageController.class);
	
	@Resource(name = "CompanyCodeManageService")
    private CompanyCodeManageService companyCodeManageService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	
    /**
     * @Method Name        : companyCodeList
     * @Method description : 기업코드 관리 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/companyCodeList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String companyCodeList(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/setting/companyCodeList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : 기업코드표구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G001");//기업코드표구분
        List<CmmFuncVO> companyCodeTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeTypeList", companyCodeTypeList);
        
        //셋팅 공통코드 : 기업 종류
        cmmFuncVO.setSystem_grp_code("G014");//기업 종류
        List<CmmFuncVO> companyCodeKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeKindList", companyCodeKindList);
        
        //셋팅 공통코드 : 기업 종류
        cmmFuncVO.setSystem_grp_code("G029");//기업 분류
        List<CmmFuncVO> companyDivCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyDivCodeList", companyDivCodeList);
		
        //param
		model.addAttribute("CompanyCodeManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectCompanyCodeList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCompanyCodeList(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = companyCodeManageService.selectCompanyCodeList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectCompanyCodeDtl
     * @Method description : 기업코드 상세를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/selectCompanyCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCompanyCodeDtl(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
		//상세조회
		CompanyCodeManageVO resultDetail = companyCodeManageService.selectCompanyCodeDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : chkCompanyCodeDtl
     * @Method description : 기업코드 저장가능 여부를 체크한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/chkCompanyCodeDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String chkCompanyCodeDtl(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		Map<String, Object> map = companyCodeManageService.chkCompanyCodeDtl(paramVO);
        
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
		
	/**
     * @Method Name        : insertCompanyCode
     * @Method description : 기업코드 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/insertCompanyCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertCompanyCode(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		String companyCodeRegNo = companyCodeManageService.insertCompanyCode(paramVO);
		int result = 0;
		if(!"".equals(OppfStringUtil.isNullToString(companyCodeRegNo))) result = 1;
		
        model.addAttribute("result", result);
        model.addAttribute("companyCodeRegNo", companyCodeRegNo);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateCompanyCode
     * @Method description : 기업코드 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/updateCompanyCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateCompanyCode(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = companyCodeManageService.updateCompanyCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : chkDeleteCompanyCode
     * @Method description : 기업코드 삭제가능 여부를 체크한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/chkDeleteCompanyCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String chkDeleteCompanyCode(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		int result = companyCodeManageService.chkDeleteCompanyCode(paramVO);
        
		model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : deleteCompanyCode
     * @Method description : 기업코드 삭제
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/setting/deleteCompanyCode.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteCompanyCode(@ModelAttribute("CompanyCodeManageVO") CompanyCodeManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = companyCodeManageService.deleteCompanyCode(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
