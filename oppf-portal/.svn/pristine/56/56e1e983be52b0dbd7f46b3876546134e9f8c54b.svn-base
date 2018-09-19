package kr.co.koscom.oppf.apt.plan.web;

import kr.co.koscom.oppf.apt.plan.service.ApiPlanManageService;
import kr.co.koscom.oppf.apt.plan.service.ApiPlanManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiPlanManageController.java
* @Comment  : api plan 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.06.03
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.03  신동진        최초생성
*
*/
@Controller
public class ApiPlanManageController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "ApiPlanManageService")
    private ApiPlanManageService apiPlanManageService;
	
	/**
     * @Method Name        : apiPlanManageList
     * @Method description : api plan 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/apiPlanManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiPlanManageList(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/plan/apiPlanManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		//공통코드 셋팅
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		//셋팅 공통코드 : app 상태
        cmmFuncVO.setSystem_grp_code("G022");
        List<CmmFuncVO> appStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appStatusList", appStatusList);
		
        //셋팅 공통코드 : api plan 종류
        cmmFuncVO.setSystem_grp_code("G024");
        List<CmmFuncVO> planTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("planTypeList", planTypeList);
		
		//셋팅 공통코드 : api 구분
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //셋팅 공통코드 : api 서비스 제공자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> companyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("companyCodeList", companyCodeList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectApiPlanManageList
     * @Method description : api plan 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/selectApiPlanManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiPlanManageList(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//sort
		paramVO.setListOrder("a.app_id asc, api.create_date desc");
		
		Map<String, Object> map = apiPlanManageService.selectApiPlanManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectApiPlanManageListExcel
     * @Method description : api plan excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/selectApiPlanManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiPlanManageListExcel(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, ModelMap model)throws Exception{
		//sort
		paramVO.setListOrder("a.app_id asc, api.create_date desc");
		
		Map<String, Object> map = apiPlanManageService.selectApiPlanManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "apt/plan/apiPlanManageListExcel";
	}
	
	/**
     * @Method Name        : apiPlanManagePopup
     * @Method description : api plan 등록 팝업 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/apiPlanManagePopup.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiPlanManagePopup(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/plan/apiPlanManagePopup";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        //셋팅 공통코드 : api plan 종류
        cmmFuncVO.setSystem_grp_code("G024");
        List<CmmFuncVO> planTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("planTypeList", planTypeList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectApiPlanManagePopupList
     * @Method description : api plan 등록 팝업 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/selectApiPlanManagePopupList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiPlanManagePopupList(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        		
		Map<String, Object> map = apiPlanManageService.selectApiPlanManagePopupList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : saveApiPlanManagePopupList
     * @Method description : api plan을 저장한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/plan/saveApiPlanManagePopupList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveApiPlanManagePopupList(@ModelAttribute("ApiPlanManageVO") ApiPlanManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = apiPlanManageService.saveApiPlanManagePopupList(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
