package kr.co.koscom.oppf.apt.sptUsr.web;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsVO;
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
* @FileName : SptUserServiceTermsController.java
* @Comment  : 금융정보제공 동의서 관리 Controller
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
public class SptUserServiceTermsController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SptUserServiceTermsService")
    private SptUserServiceTermsService sptUserServiceTermsService;
	
    /**
     * @Method Name        : sptUserServiceTermsList
     * @Method description : 금융정보제공 동의서목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserServiceTermsList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserServiceTermsList(@ModelAttribute("SptUserSreviceTermsVO") SptUserServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserServiceTermsList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //공토코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 앱개발자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> subcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("subcompanyCodeIdList", subcompanyCodeIdList);
        
        //셋팅 공통코드 : 서비스 제공자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubcompanyCodeIdList", pubcompanyCodeIdList);
        
        //셋팅 공통코드 : 금융정보 제공 동의서 상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G030");
        List<CmmFuncVO> termsStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("termsStatusList", termsStatusList);
        
        //셋팅 공통코드 : 인증방법
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G032");
        List<CmmFuncVO> termsAuthTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("termsAuthTypeList", termsAuthTypeList);
		
        
        
		model.addAttribute("SptUserServiceTermsVO", paramVO);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectSptUserServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectSptUserServiceTermsList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptUserServiceTermsList(@ModelAttribute("SptUserServiceTermsVO") SptUserServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("a.create_date desc, sub.company_name_kor_alias asc, b.customer_name_kor asc, a.terms_reg_no desc, pub.company_name_kor_alias asc");
        

     //   System.out.println("#########################################################################");
      //  System.out.println("paramVO.getTermsAuthTypeList() => "+paramVO.getTermsAuthTypeList());
      //  System.out.println("paramVO.getTermsAuthTypeListAllYn() => "+paramVO.getTermsAuthTypeListAllYn());
       // System.out.println("#########################################################################");
        
        Map<String, Object> map = sptUserServiceTermsService.selectSptUserServiceTermsList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : sptUserServiceTermsListExcel
     * @Method description : 금융정보제공 동의서목록 excel 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserServiceTermsListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserServiceTermsListExcel(@ModelAttribute("SptUserSreviceTermsVO") SptUserServiceTermsVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserServiceTermsListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("a.create_date desc, sub.company_name_kor_alias asc, b.customer_name_kor asc, a.terms_reg_no desc, pub.company_name_kor_alias asc");
        
        Map<String, Object> map = sptUserServiceTermsService.selectSptUserServiceTermsListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("SptUserServiceTermsVO", paramVO);
		
	    return modelView;
	}
		
}
