package kr.co.koscom.oppf.apt.contract.web;

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

import kr.co.koscom.oppf.apt.contract.service.ApiContractManageService;
import kr.co.koscom.oppf.apt.contract.service.ApiContractManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : ApiContractManageController.java
* @Comment  : api 계약 관리를 위한 Controller
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
public class ApiContractManageController {
	
	private static final Logger log = Logger.getLogger(ApiContractManageController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "ApiContractManageService")
    private ApiContractManageService apiContractManageService;
	
	/**
     * @Method Name        : apiContractManageList
     * @Method description : api 계약 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/contract/apiContractManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiContractManageList(@ModelAttribute("ApiContractManageVO") ApiContractManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/contract/apiContractManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		//공통
		CmmFuncVO cmmFuncVO = new CmmFuncVO();
		
		//셋팅 공통코드 : api 구분
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //셋팅 공통코드 : api 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> serviceContractStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("serviceContractStatusList", serviceContractStatusList);
		
        //셋팅 공통코드 : 서비스 제공자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubCompanyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubCompanyCodeList", pubCompanyCodeList);
        
        //셋팅 공통코드 : 앱 개발자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> subCompanyCodeList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("subCompanyCodeList", subCompanyCodeList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectApiContractManageList
     * @Method description : api 계약 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/contract/selectApiContractManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiContractManageList(@ModelAttribute("ApiContractManageVO") ApiContractManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//sort
		paramVO.setListOrder("ifnull(a.pubcompany_code_id, 'Z'), ifnull(a.subcompany_code_id, 'Z'), a.api_id");
		
		Map<String, Object> map = apiContractManageService.selectApiContractManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectApiContractManageListExcel
     * @Method description : api 계약 excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/contract/selectApiContractManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectApiContractManageListExcel(@ModelAttribute("ApiContractManageVO") ApiContractManageVO paramVO, ModelMap model)throws Exception{
		//sort
		paramVO.setListOrder("ifnull(a.pubcompany_code_id, 'Z'), ifnull(a.subcompany_code_id, 'Z'), a.api_id");
		
		Map<String, Object> map = apiContractManageService.selectApiContractManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "apt/contract/apiContractManageListExcel";
	}
	
	/**
     * @Method Name        : apiContractManageDtl
     * @Method description : api 계약 상세 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/contract/apiContractManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String apiContractManageDtl(@ModelAttribute("ApiContractManageVO") ApiContractManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/contract/apiContractManageDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		//공통
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
		//셋팅 공통코드 : 서비스 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> serviceContractStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("serviceContractStatusList", serviceContractStatusList);
                
        //상세조회
        ApiContractManageVO resultDetail = apiContractManageService.selectApiContractManageDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : saveApiContractManage
     * @Method description : api 계약 저장
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/contract/saveApiContractManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveApiContractManage(@ModelAttribute("ApiContractManageVO") ApiContractManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = apiContractManageService.saveApiContractManage(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
