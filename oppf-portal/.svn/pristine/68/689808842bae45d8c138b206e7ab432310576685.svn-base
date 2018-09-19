package kr.co.koscom.oppf.cpt.myp.api.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.api.service.CptApiManageService;
import kr.co.koscom.oppf.cpt.myp.api.service.CptApiManageVO;
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
* @FileName : CptApiManageController.java
* @Comment  : 기업사용자의 api 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.06.31
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.31  신동진        최초생성
*
*/
@Controller
public class CptApiManageController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptApiManageService")
    private CptApiManageService cptApiManageService;
	
	/**
     * @Method Name        : cptApiManageList
     * @Method description : api 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/api/cptApiManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptApiManageList(@ModelAttribute("CptApiManageVO") CptApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/api/cptApiManageList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();		//기업 regNo
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();		//사용자 regNo
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
            return modelView;
        }
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
		
		//셋팅 공통코드 : api 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //셋팅 공통코드 : api 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> apiContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiContractCodeList", apiContractCodeList);
                
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectCptApiManageList
     * @Method description : api 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/api/selectCptApiManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptApiManageList(@ModelAttribute("CptApiManageVO") CptApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();		//기업 regNo
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();		//사용자 regNo
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
        
		Map<String, Object> map = cptApiManageService.selectCptApiManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectCptApiManageListExcel
     * @Method description : api excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/api/selectCptApiManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptApiManageListExcel(@ModelAttribute("CptApiManageVO") CptApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/api/cptApiManageListExcel";
		
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	return modelView;
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();		//기업 regNo
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();		//사용자 regNo
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	return modelView;
        }
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
		
		Map<String, Object> map = cptApiManageService.selectCptApiManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		//param
		model.addAttribute("paramVO", paramVO);
		
		return modelView;
	}
	
	/**
     * @Method Name        : cptApiManageDtl
     * @Method description : api 상세 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/api/cptApiManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptApiManageDtl(@ModelAttribute("CptApiManageVO") CptApiManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/api/cptApiManageDtl";
        
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
        CptApiManageVO resultDetail = cptApiManageService.selectCptApiManageDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
}
