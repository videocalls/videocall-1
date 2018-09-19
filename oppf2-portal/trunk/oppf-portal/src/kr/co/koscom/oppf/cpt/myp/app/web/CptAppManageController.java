package kr.co.koscom.oppf.cpt.myp.app.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.app.service.CptAppManageService;
import kr.co.koscom.oppf.cpt.myp.app.service.CptAppManageVO;
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
* @FileName : CptAppManageController.java
* @Comment  : 기업사용자의 app 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
@Controller
public class CptAppManageController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptAppManageService")
    private CptAppManageService cptAppManageService;
	
	/**
     * @Method Name        : cptAppManageList
     * @Method description : app 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/app/cptAppManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptAppManageList(@ModelAttribute("CptAppManageVO") CptAppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/app/cptAppManageList";
        
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
		
		//셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);
        
        //셋팅 공통코드 : app 상태
        cmmFuncVO.setSystem_grp_code("G022");
        List<CmmFuncVO> appStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appStatusList", appStatusList);
        
        //셋팅 공통코드 : app 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> appContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appContractCodeList", appContractCodeList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectCptAppManageList
     * @Method description : app 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/app/selectCptAppManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptAppManageList(@ModelAttribute("CptAppManageVO") CptAppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
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
		
		Map<String, Object> map = cptAppManageService.selectCptAppManageList(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectCptAppManageListExcel
     * @Method description : app excel 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/app/selectCptAppManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptAppManageListExcel(@ModelAttribute("CptAppManageVO") CptAppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/app/cptAppManageListExcel";
		
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
        
		Map<String, Object> map = cptAppManageService.selectCptAppManageListExcel(paramVO);
		model.addAttribute("resultList", map.get("resultList"));
		
		return modelView;
	}
	
	/**
     * @Method Name        : appManageDtl
     * @Method description : app 상세 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/app/cptAppManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptAppManageDtl(@ModelAttribute("CptAppManageVO") CptAppManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "cpt/myp/app/cptAppManageDtl";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
		
		//셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);
        
        //셋팅 공통코드 : app 계약여부
        cmmFuncVO.setSystem_grp_code("G023");
        List<CmmFuncVO> appContractCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appContractCodeList", appContractCodeList);
                
        //상세조회
        CptAppManageVO resultDetail = cptAppManageService.selectCptAppManageDtl(paramVO);
        List<CptAppManageVO> resultApiList = cptAppManageService.selectCptAppManageDtlApiList(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
        model.addAttribute("resultApiList", resultApiList);
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
}
