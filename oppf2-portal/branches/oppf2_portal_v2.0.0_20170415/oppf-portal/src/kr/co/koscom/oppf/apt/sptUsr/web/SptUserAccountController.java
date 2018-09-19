package kr.co.koscom.oppf.apt.sptUsr.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserAccountService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserAccountVO;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.ComCompanyProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceController.java
* @Comment  : 일반회원 가상계좌 관리 Controller
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
public class SptUserAccountController {
	
	private static final Logger log = Logger.getLogger(SptUserAccountController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SptUserAccountService")
    private SptUserAccountService sptUserAccountService;
	
    @Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
    
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;
	
    /**
     * @Method Name        : sptUserAccountList
     * @Method description : 가상계좌 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserAccountList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserAccountList(@ModelAttribute("SptUserAccountVO") SptUserAccountVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserAccountList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        String customerRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
		
        //공토코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 서비스 제공자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubcompanyCodeIdList", pubcompanyCodeIdList);
        
        //셋팅 공통코드 : 계정상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
        
        //셋팅 공통코드 : 가상계좌 상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G009");
        List<CmmFuncVO> customerVtaccountStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerVtaccountStatusList", customerVtaccountStatusList);
                		
		model.addAttribute("SptUserAccountVO", paramVO);

        //6.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);
        

		String serverType = CommonUtil.checkServerType(OppfProperties.getProperty("Globals.server.type"));
		model.addAttribute("serverType", serverType);
	    return modelView;
	}
		
	/**
     * @Method Name        : selectSptUserAccountList
     * @Method description : 가상계좌 목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectSptUserAccountList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptUserAccountList(@ModelAttribute("SptUserAccountVO") SptUserAccountVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //order
        paramVO.setListOrder("b.customer_name_kor asc, a.update_date desc");
        
        Map<String, Object> map = sptUserAccountService.selectSptUserAccountList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
		

				
	    return "jsonView";
	}

	/**
     * @Method Name        : sptUserAccountListExcel
     * @Method description : 가상계좌 excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserAccountListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserAccountListExcel(@ModelAttribute("SptUserAccountVO") SptUserAccountVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserAccountListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("b.customer_name_kor asc, a.update_date desc");
        
        Map<String, Object> map = sptUserAccountService.selectSptUserAccountListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("SptUserAccountVO", paramVO);
		
	    return modelView;
	}
	
	
	/**
     * @Method Name        : sptUserAccountListExcel
     * @Method description : 가상계좌 추가 팝업 페이지 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/usr/sptUserAccountReg.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserAccountReg(HttpServletRequest request, ModelMap model)throws Exception{
		//System.out.println("1");
		//modelView
        String modelView = "apt/sptUsr/sptUserAccountReg";
       // System.out.println("2");
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        //System.out.println(loginVO.getAdmin_profile_reg_no());
        
        String customerRegNo = loginVO.getAdmin_profile_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            return modelView;
        }
       // System.out.println("4");
        
        // [금융투자사목록]정보 취득
        ComCompanyProfileVO pComCompanyProfileVO = new ComCompanyProfileVO();
        pComCompanyProfileVO.setCompanyServiceType("G002002");
        List<ComCompanyProfileVO> companyProfileList = svcApplService.selectComCompanyProfileList(pComCompanyProfileVO);
        model.addAttribute("companyProfileList", companyProfileList);
        

       // System.out.println("5");
        // 셋팅 공통코드:실계좌유형
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G010");
        List<CmmFuncVO> cmmAccTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        log.debug("5.셋팅 공통코드:실계좌유형 취득後:cmmAccTypeList="+cmmAccTypeList);
        model.addAttribute("cmmAccTypeList", cmmAccTypeList);
        

        //System.out.println("6");
        //6.oauthToken정보 취득
        ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
        pComOauthTokenVO.setCustomerRegNo(customerRegNo);
        ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
        model.addAttribute("rsComOauthTokenVO", rsComOauthTokenVO);

       // System.out.println("7");
	    return modelView;
	}
	
}
