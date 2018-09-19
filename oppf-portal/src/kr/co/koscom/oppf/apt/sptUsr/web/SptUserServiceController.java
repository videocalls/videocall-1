package kr.co.koscom.oppf.apt.sptUsr.web;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
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
* @FileName : SptUserServiceController.java
* @Comment  : 서비스 연결 현황 관리 Controller
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
public class SptUserServiceController {
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "SptUserServiceService")
    private SptUserServiceService sptUserServiceService;
	
    /**
     * @Method Name        : sptUserServiceList
     * @Method description : 서비스 연결 현황 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserServiceList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserServiceList(@ModelAttribute("SptUserServiceVO") SptUserServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserServiceList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //공토코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 계정상태
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G005");
        List<CmmFuncVO> customerRegStatusList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("customerRegStatusList", customerRegStatusList);
        
        //셋팅 공통코드 : 앱개발자 
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> subcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("subcompanyCodeIdList", subcompanyCodeIdList);
        
        //앱이름
        List<SptUserServiceVO> appList = sptUserServiceService.selectSptUserServiceAppList(paramVO);
        model.addAttribute("appList", appList);
        
        //셋팅 공통코드 : 서비스 제공자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubcompanyCodeIdList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubcompanyCodeIdList", pubcompanyCodeIdList);
        		
		model.addAttribute("SptUserServiceVO", paramVO);


		String serverType = CommonUtil.checkServerType(OppfProperties.getProperty("Globals.server.type"));
		model.addAttribute("serverType", serverType);
		
	    return modelView;
	}
		
	/**
     * @Method Name        : selectSptUserServiceList
     * @Method description : 서비스 연결 현황 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectSptUserServiceList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectSptUserServiceList(@ModelAttribute("SptUserServiceVO") SptUserServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("a.customer_name_kor asc, a.customer_reg_no desc, a.service_reg_no desc, subcompany_exposure_order asc, pubcompany_exposure_order asc, a.update_date desc");
        
        Map<String, Object> map = sptUserServiceService.selectSptUserServiceList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : sptUserServiceListExcel
     * @Method description : 서비스 연결 현황 excel 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/sptUserServiceListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String sptUserServiceListExcel(@ModelAttribute("SptUserServiceVO") SptUserServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/sptUsr/sptUserServiceListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //order
        paramVO.setListOrder("a.customer_name_kor asc, a.customer_reg_no desc, a.service_reg_no desc, subcompany_exposure_order asc, pubcompany_exposure_order asc, a.update_date desc");
        
        Map<String, Object> map = sptUserServiceService.selectSptUserServiceListExcel(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("SptUserServiceVO", paramVO);
		
	    return modelView;
	}

    /**
     * @Method Name        : changeSubCompanyList
     * @Method description : 앱 개발자 변경 시 하위 select 조회
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/sptUsr/changeSubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String changeSubCompanyList(@ModelAttribute("SptUserServiceVO") SptUserServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        //앱이름
        List<SptUserServiceVO> appList = sptUserServiceService.selectSptUserServiceAppList(paramVO);
        model.addAttribute("appList", appList);

        return "jsonView";
    }
		
}
