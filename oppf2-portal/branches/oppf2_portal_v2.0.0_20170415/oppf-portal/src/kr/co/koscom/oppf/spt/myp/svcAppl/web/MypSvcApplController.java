package kr.co.koscom.oppf.spt.myp.svcAppl.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MypSvcApplController.java
* @Comment  : [마이페이지>신청서비스]정보관리를 위한 Controller 클래스
* @author   : 신동진
* @since    : 2016.06.11
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.11  신동진        최초생성
*
*/
@Controller
public class MypSvcApplController {
	private static final Logger log = Logger.getLogger(MypSvcApplController.class);
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    /**
     * @Method Name        : svcApplList
     * @Method description : [신청서비스]페이지로 이동한다.
     * @param              : MypSvcApplVO
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/spt/myp/svcAppl/mypSvcApplList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fintechSvcChoic(@ModelAttribute("MypSvcApplVO") MypSvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "spt/myp/svcAppl/mypSvcApplList";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
        model.addAttribute("loginVO", loginVO);
        model.addAttribute("paramVO", paramVO);
        
        return modelView;
    }
    
    /**
     * @Method Name        : selectMypSvcApplList
     * @Method description : [신청서비스] 신청서비스 목록을 조회한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/myp/svcAppl/selectMypSvcApplList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFintechSvcList(@ModelAttribute("MypSvcApplVO") MypSvcApplVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = mypSvcApplService.selectMypSvcApplList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : selectFintechSvcDiscardList
     * @Method description : [신청서비스] 폐기 정보제공동의 목록을 조회한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/myp/svcAppl/selectMypSvcApplDiscardList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectFintechSvcDiscardList(@ModelAttribute("MypSvcApplVO") MypSvcApplVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        
		Map<String, Object> map = mypSvcApplService.selectFintechSvcDiscardList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cancelMypSvcAppl
     * @Method description : [신청서비스] 서비스 해지
     * @param              : MypSvcApplVO
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/myp/svcAppl/cancelMypSvcAppl.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String cancelMypSvcAppl(@ModelAttribute("MypSvcApplVO") MypSvcApplVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        
        int result = mypSvcApplService.cancelMypSvcAppl(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : cancelMypSvcApplTerms
     * @Method description : [신청서비스] 동의서 폐기
     * @param              : MypSvcApplVO
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/myp/svcAppl/cancelMypSvcApplTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String cancelMypSvcApplTerms(@ModelAttribute("MypSvcApplVO") MypSvcApplVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 정보 셋팅
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(customerRegNo);
        
        //약관등록번호 복호화
        String termsRegNo = paramVO.getTermsRegNo();
        if(!OppfStringUtil.isEmpty(termsRegNo)){
            termsRegNo = OppfStringUtil.base64Decoding(termsRegNo);
        }
        paramVO.setTermsRegNo(termsRegNo);
        
        int result = mypSvcApplService.cancelMypSvcApplTerms(paramVO);
        model.addAttribute("result", result);
		
	    return "jsonView";
	}
	
    
}
