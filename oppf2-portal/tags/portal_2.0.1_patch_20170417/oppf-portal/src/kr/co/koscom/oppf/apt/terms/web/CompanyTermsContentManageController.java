package kr.co.koscom.oppf.apt.terms.web;

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

import kr.co.koscom.oppf.apt.terms.service.CompanyTermsContentManageService;
import kr.co.koscom.oppf.apt.terms.service.CompanyTermsContentManageVO;
import kr.co.koscom.oppf.apt.setting.service.CompanyCodeManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CompanyTermsContentManageController.java
* @Comment  : 관리자의 기업 약관동의 내용 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.08.01
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.01  신동진        최초생성
*
*/
@Controller
public class CompanyTermsContentManageController {
	
	private static final Logger log = Logger.getLogger(CompanyTermsContentManageController.class);
	
	@Resource(name = "CompanyTermsContentManageService")
    private CompanyTermsContentManageService companyTermsContentManageService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	
    /**
     * @Method Name        : companyTermsContentList
     * @Method description : 기업 약관동의 관리 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/companyTermsContentList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String companyTermsContentList(@ModelAttribute("CompanyTermsContentManageVO") CompanyTermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/terms/companyTermsContentList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //기업코드 조회
        List<CompanyTermsContentManageVO> companyCodeList = companyTermsContentManageService.selectCompanyCodeList(paramVO);
        model.addAttribute("companyCodeList", companyCodeList);
        
		//셋팅 공통코드 : 기업 동의종류
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G031");	//기업동의종류
        List<CmmFuncVO> companyTermsTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyTermsTypeList", companyTermsTypeList);
                
        //param
		model.addAttribute("CompanyTermsContentManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectCompanyTermsContentList
     * @Method description : 기업 약관동의 내용의 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/selectCompanyTermsContentList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCompanyTermsContentList(@ModelAttribute("CompanyTermsContentManageVO") CompanyTermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
                
		Map<String, Object> map = companyTermsContentManageService.selectCompanyTermsContentList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectCompanyTermsContentDtl
     * @Method description : 기업 약관동의 내용의 상세를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/selectCompanyTermsContentDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCompanyTermsContentDtl(@ModelAttribute("CompanyTermsContentManageVO") CompanyTermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
        CompanyTermsContentManageVO resultDetail = companyTermsContentManageService.selectCompanyTermsContentDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertCompanyTermsContent
     * @Method description : 기업 약관동의 내용 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/insertCompanyTermsContent.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertCompanyTermsContent(@ModelAttribute("CompanyTermsContentManageVO") CompanyTermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		String companyTermsContentRegSeq = companyTermsContentManageService.insertCompanyTermsContent(paramVO);
		int result = 0;
		if(!"".equals(OppfStringUtil.isNullToString(companyTermsContentRegSeq))) result = 1;
		
        model.addAttribute("result", result);
        model.addAttribute("companyTermsContentRegSeq", companyTermsContentRegSeq);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateCompanyTermsContent
     * @Method description : 기업 약관동의 내용 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/updateCompanyTermsContent.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateCompanyTermsContent(@ModelAttribute("CompanyTermsContentManageVO") CompanyTermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		int result = companyTermsContentManageService.updateCompanyTermsContent(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
}
