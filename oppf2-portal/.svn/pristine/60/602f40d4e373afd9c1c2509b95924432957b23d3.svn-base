package kr.co.koscom.oppf.cmm.intro.web;

import kr.co.koscom.oppf.cmm.intro.service.IntroService;
import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.util.OppfSessionUtil;
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
* @FileName : IntroController.java
* @Comment  : [Intro]정보관리를 위한 Controller 클래스
* @author   : 신동진
* @since    : 2016.06.13
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.13  신동진        최초생성
*
*/
@Controller
public class IntroController {
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
    @Resource(name = "IntroService")
    private IntroService introService;
	
    /**
     * @Method Name        : introFintech
     * @Method description : [Intro]핀테크오픈플랫폼 소개
     * @param              : IntroVO
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introFintech.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fintechIntro(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
    	model.addAttribute("paramVO", paramVO);
    	
    	//modelView
        String systemKind = OppfSessionUtil.getSystemKind(request);
        String modelView = systemKind + "/cmm/intro/introFintech";
                
        return modelView;
    }
    
    /**
     * @Method Name        : introPartner
     * @Method description : [Intro]참여사 소개 페이지로 이동한다.
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introPartner.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String partnerIntro(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        model.addAttribute("paramVO", paramVO);
        
        //modelView
        String systemKind = OppfSessionUtil.getSystemKind(request);
        //String modelView = systemKind + "/cmm/intro/introPartner";
        String modelView = "/cmm/intro/introPartner";
        
        return modelView;
    }
    
    /**
     * @Method Name        : introOppfUse
     * @Method description : [Intro]오픈플랫폼 이용안내 페이지로 이동한다.
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introOppfUse.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String introOppfUse(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        model.addAttribute("paramVO", paramVO);
                
        return "cmm/intro/introOppfUse";
    }

    /**
     * @Method Name        : introSiseSupport
     * @Method description : [Intro]시세 정보 페이지로 이동한다.
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introSiseSupport.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String introSiseSupport(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        model.addAttribute("paramVO", paramVO);
                
        return "cmm/intro/introSiseSupport";
    }
    
    /**
     * @Method Name        : introFintechApp
     * @Method description : [Intro]핀테크 App 소개 페이지로 이동한다.
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introFintechApp.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fintechAppIntro(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//셋팅 공통코드 : app 구분
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G025");
        List<CmmFuncVO> appCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("appCategoryList", appCategoryList);
       
        //moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
        model.addAttribute("paramVO", paramVO);
        
        return "cmm/intro/introFintechApp";
    }
    
    /**
     * @Method Name        : selectIntroFintechAppList
     * @Method description : [핀테크 App 소개]핀테크 App 소개 리스트를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/intro/selectIntroFintechAppList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectIntroFintechAppList(@ModelAttribute("IntroVO") IntroVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		
		paramVO.setListOrder("a.exposure_order asc, a.app_name asc");
		
		Map<String, Object> map = introService.selectIntroFintechAppList(paramVO);
		
		model.addAttribute("resultAppList", map.get("resultAppList"));							//핀테크 서비스 목록
		model.addAttribute("resultAppListTotalCount", map.get("resultAppListTotalCount"));		//핀테크 서비스 목록 cnt
		
		model.addAttribute("resultAppPubcompanyList", map.get("resultAppPubcompanyList"));		//핀테크 서비스 금투사 목록
		
	    return "jsonView";
	}
    
    /**
     * @Method Name        : introSvcApi
     * @Method description : [서비스 소개] API 소개 페이지로 이동한다.
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introSvcApi.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String introSvcApi(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	CmmFuncVO cmmFuncVO = new CmmFuncVO();
    	
    	//셋팅 공통코드 : api 구분
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //정보제공자 리스트
        Map<String, Object> map = introService.selectIntroSvcApiCompanyList(paramVO);
        model.addAttribute("companyCodeList", map.get("resultList"));
    	
    	model.addAttribute("paramVO", paramVO);
        
        return "cmm/intro/introSvcApi";
    }
    
    /**
     * @Method Name        : selectIntroSvcApiList
     * @Method description : [서비스 소개]API 소개 리스트를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/intro/selectIntroSvcApiList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectIntroSvcApiList(@ModelAttribute("IntroVO") IntroVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		
		//	paramVO.setListOrder("a.exposure_order asc, a.api_title asc");
		//  API 소개 순서 변경
		paramVO.setListOrder("a.exposure_order asc, a.a.create_date desc");
		
		Map<String, Object> map = introService.selectIntroSvcApiList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("resultListTotalCount"));
		
	    return "jsonView";
	}
    
    /**
     * @Method Name        : introServiceTerms
     * @Method description : [Intro]서비스 이용약관
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introServiceTerms.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String introServiceTerms(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String systemKind = OppfSessionUtil.getSystemKind(request);
    	String modelView = systemKind + "/cmm/intro/introServiceTerms";
    	
    	paramVO.setSystemKind(systemKind);
    	
    	//개인은 기업약관까지 보여줘야 해서 ajax처리한다. 단, select box용 데이터를 가져온다.
    	if("spt".equals(systemKind)){
    		Map<String, Object> map = introService.selectIntroServiceTermsCodeList(paramVO);
    		model.addAttribute("termsCodeList", map.get("resultList"));
    	}
    	
    	IntroVO introVO = introService.selectIntroServiceTerms(paramVO);
    	model.addAttribute("introVO", introVO);
    	
    	
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
    	model.addAttribute("paramVO", paramVO);
    	        
        return modelView;
    }
    
    /**
     * @Method Name        : selectSptIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관(개인포털)의 정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/intro/selectSptIntroServiceTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptIntroServiceTerms(@ModelAttribute("IntroVO") IntroVO paramVO, HttpServletRequest  request, ModelMap model)throws Exception{
		String systemKind = OppfSessionUtil.getSystemKind(request);
		paramVO.setSystemKind(systemKind);
		
		IntroVO termsVO = null;
		//회원가입시 서비스 이용약관
		if("cust".equals(paramVO.getSearchTerms())){
			termsVO = introService.selectIntroServiceTerms(paramVO);
			
		//기업 서비스 이용약관
		}else{
			termsVO = introService.selectIntroServiceTermsCompanyTerms(paramVO);
		}
		
		model.addAttribute("termsData", termsVO);
		
	    return "jsonView";
	}
    
    /**
     * @Method Name        : introPrivacyPolicy
     * @Method description : [Intro]개인정보 취급방침
     * @param              : IntroVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/intro/introPrivacyPolicy.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String introPrivacyPolicy(@ModelAttribute("introVO") IntroVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//moblie여부 셋팅
    	boolean isMobile = OppfStringUtil.isMobile(request);
        model.addAttribute("isMobile", isMobile);
    	model.addAttribute("paramVO", paramVO);
        
        return "cmm/intro/introPrivacyPolicy";
    }

}
