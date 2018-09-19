package kr.co.koscom.oppf.apt.terms.web;

import kr.co.koscom.oppf.apt.terms.service.TermsContentManageService;
import kr.co.koscom.oppf.apt.terms.service.TermsContentManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
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
* @FileName : TermsContentManageController.java
* @Comment  : 관리자의 약관동의 내용 관리를 위한 Controller
* @author   : 신동진
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  신동진        최초생성
*
*/
@Controller
public class TermsContentManageController {
	
	@Resource(name = "TermsContentManageService")
    private TermsContentManageService termsContentManageService;
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	
    /**
     * @Method Name        : termsContentList
     * @Method description : 약관동의 관리 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/termsContentList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String termsContentList(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/terms/termsContentList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
		//셋팅 공통코드 : 회원동의종류
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G008");	//회원동의종류
        cmmFuncVO.setCodeExtend1("Y");			//확장코드1
        List<CmmFuncVO> termsTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("termsTypeList", termsTypeList);
        
        //시스템 구분
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G003");	//시스템 구분
        cmmFuncVO.setCodeExtend2("Y");			//확장코드2
        List<CmmFuncVO> termsSystemKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("termsSystemKindList", termsSystemKindList);
        
        //param
		model.addAttribute("TermsContentManageVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectTermsContentList
     * @Method description : 약관동의 내용의 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/selectTermsContentList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectTermsContentList(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = termsContentManageService.selectTermsContentList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectTermsContentDtl
     * @Method description : 약관동의 내용의 상세를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/selectTermsContentDtl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectTermsContentDtl(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//상세조회
		TermsContentManageVO resultDetail = termsContentManageService.selectTermsContentDtl(paramVO);
        
        model.addAttribute("resultDetail", resultDetail);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : insertTermsContent
     * @Method description : 약관동의 내용 등록
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/insertTermsContent.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String insertTermsContent(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//이메일 발송여부 셋팅
		paramVO.setEmailSendFlag("N");
		
		String customerTermsContentRegSeq = termsContentManageService.insertTermsContent(paramVO);
		int result = 0;
		if(!"".equals(OppfStringUtil.isNullToString(customerTermsContentRegSeq))) result = 1;
		
        model.addAttribute("result", result);
        model.addAttribute("customerTermsContentRegSeq", customerTermsContentRegSeq);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : updateTermsContent
     * @Method description : 약관동의 내용 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/updateTermsContent.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateTermsContent(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//이메일 발송여부 셋팅
		paramVO.setEmailSendFlag("N");
		
		int result = termsContentManageService.updateTermsContent(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : sendTermsContentEmail
     * @Method description : 이메일 발송
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/terms/sendTermsContentEmail.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String sendTermsContentEmail(@ModelAttribute("TermsContentManageVO") TermsContentManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
		
		//이메일 발송여부 셋팅
		paramVO.setEmailSendFlag("Y");
		
		//등록 수정 여부
		if(!"".equals(paramVO.getCustomerTermsContentRegSeq())){
			int result = termsContentManageService.updateTermsContent(paramVO);
	        model.addAttribute("result", result);
		}else{
			String customerTermsContentRegSeq = termsContentManageService.insertTermsContent(paramVO);
			int result = 0;
			if(!"".equals(OppfStringUtil.isNullToString(customerTermsContentRegSeq))) result = 1;
			
	        model.addAttribute("result", result);
	        model.addAttribute("customerTermsContentRegSeq", customerTermsContentRegSeq);
		}
				
	    return "jsonView";
	}
	
}
