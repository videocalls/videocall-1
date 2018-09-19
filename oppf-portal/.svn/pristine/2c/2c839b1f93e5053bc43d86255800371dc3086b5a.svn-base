package kr.co.koscom.oppf.cpt.myp.svcTerms.web;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.svcTerms.service.CptServiceTermsService;
import kr.co.koscom.oppf.cpt.myp.svcTerms.service.CptServiceTermsVO;
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
* @FileName : CptServiceTermsController.java
* @Comment  : 기업회원 금융정보제공 동의서 관리 Controller
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
public class CptServiceTermsController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptServiceTermsService")
    private CptServiceTermsService cptServiceTermsService;
	
    /**
     * @Method Name        : cptServiceTermsList
     * @Method description : 금융정보제공 동의서목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/svcTerms/cptServiceTermsList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptServiceTermsList(@ModelAttribute("CptSreviceTermsVO") CptServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "cpt/myp/svcTerms/cptServiceTermsList";
        
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
        
        //공토코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 금융정보 제공 동의서 상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G030");
        List<CmmFuncVO> termsStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("termsStatusList", termsStatusList);
		
		model.addAttribute("CptServiceTermsVO", paramVO);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectCptServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/svcTerms/selectCptServiceTermsList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptServiceTermsList(@ModelAttribute("CptServiceTermsVO") CptServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();		//기업 regNo
        String companyServiceType = loginVO.getCompany_service_type();			//기업 역할(둘다:G002001, 서비스 제공자:G002002, 앱개발자 : G002003 )
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();		//사용자 regNo
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setCompanyServiceType(companyServiceType);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);
		
        //order
        paramVO.setListOrder("sub.company_name_kor_alias asc, b.customer_name_kor asc, a.terms_reg_no desc, pub.company_name_kor_alias asc");
        
        Map<String, Object> map = cptServiceTermsService.selectCptServiceTermsList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : CptServiceTermsListExcel
     * @Method description : 금융정보제공 동의서목록 excel 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cpt/myp/svcTerms/cptServiceTermsListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptServiceTermsListExcel(@ModelAttribute("CptSreviceTermsVO") CptServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "cpt/myp/svcTerms/cptServiceTermsListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        String companyProfileRegNo = loginVO.getCompany_profile_reg_no();		//기업 regNo
        String companyServiceType = loginVO.getCompany_service_type();			//기업 역할(둘다:G002001, 서비스 제공자:G002002, 앱개발자 : G002003 )
        String operatorProfileRegNo = loginVO.getOperator_profile_reg_no();		//사용자 regNo
        if(OppfStringUtil.isEmpty(companyProfileRegNo) || OppfStringUtil.isEmpty(operatorProfileRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        //사용자 정보 셋팅
        paramVO.setCompanyProfileRegNo(companyProfileRegNo);
        paramVO.setCompanyServiceType(companyServiceType);
        paramVO.setOperatorProfileRegNo(operatorProfileRegNo);

        //order
        paramVO.setListOrder("sub.company_name_kor_alias asc, b.customer_name_kor asc, a.terms_reg_no desc, pub.company_name_kor_alias asc");
        
        Map<String, Object> map = cptServiceTermsService.selectCptServiceTermsListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("CptServiceTermsVO", paramVO);
		
	    return modelView;
	}
		
}
