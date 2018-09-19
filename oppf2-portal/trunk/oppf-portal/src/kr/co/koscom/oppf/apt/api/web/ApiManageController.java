package kr.co.koscom.oppf.apt.api.web;

import kr.co.koscom.oppf.apt.api.service.ApiManageService;
import kr.co.koscom.oppf.apt.api.service.ApiManageVO;
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
* @FileName : ApiManageController.java
* @Comment  : api 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.05.27
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  신동진        최초생성
*
*/
@Controller
public class ApiManageController {
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "ApiManageService")
    private ApiManageService apiManageService;
	
	/**
     * @Method Name        : apiManageList
     * @Method description : api 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/api/apiManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiManageList(@ModelAttribute("ApiManageVO") ApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/api/apiManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : api 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //셋팅 공통코드 : api 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> apiContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiContractCodeList", apiContractCodeList);
        
        //셋팅 공통코드 : 서비스 제공자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> companyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("companyCodeList", companyCodeList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/api/selectApiManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiManageList(@ModelAttribute("ApiManageVO") ApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = apiManageService.selectApiManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/api/selectApiManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiManageListExcel(@ModelAttribute("ApiManageVO") ApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/api/apiManageListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		Map<String, Object> map = apiManageService.selectApiManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		//param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : apiManageDtl
     * @Method description : api 상세 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/api/apiManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiManageDtl(@ModelAttribute("ApiManageVO") ApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/api/apiManageDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		//셋팅 공통코드 : api 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
		//셋팅 공통코드 : api 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> apiContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiContractCodeList", apiContractCodeList);
                
        //셋팅 공통코드 : 서비스 제공자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> companyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("companyCodeList", companyCodeList);
        
        //상세조회
        ApiManageVO resultDetail = apiManageService.selectApiManageDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : saveApiManage
     * @Method description : api 저장
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/api/saveApiManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveApiManage(@ModelAttribute("ApiManageVO") ApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = apiManageService.saveApiManage(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
