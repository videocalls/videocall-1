package kr.co.koscom.oppf.apt.stats.web;

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

import kr.co.koscom.oppf.apt.stats.service.StatsTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsTrafficController.java
* @Comment  : Traffic 통계 관리를 위한 위한 Controller
* @author   : 신동진
* @since    : 2016.07.10
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.07.10  신동진        최초생성
*
*/
@Controller
public class StatsTrafficController {
	
	private static final Logger log = Logger.getLogger(StatsTrafficController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "StatsTrafficService")
    private StatsTrafficService statsTrafficService;
	
	/**
     * @Method Name        : statsTrafficList
     * @Method description : 트레픽 통계 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsTrafficList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsTrafficList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //공통코드
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //API 서비스 제공자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubCompanyList", pubCompanyList);
        /*
        //API 서비스 제공자
        List<StatsTrafficVO> pubCompanyList = statsTrafficService.selectStatsTrafficPubCompanyList(paramVO);
        model.addAttribute("pubCompanyList", pubCompanyList);
        */
        
        //API 서비스 구분
        cmmFuncVO.setSystem_grp_code("G026");
        List<CmmFuncVO> apiCategoryList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        /*
        //API 서비스 구분
        List<StatsTrafficVO> apiCategoryList = statsTrafficService.selectStatsTrafficApiCategoryList(paramVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        */
        
        //API 서비스 이름
        List<StatsTrafficVO> apiNameList = statsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        
        //세부 API 서비스
        /*
        List<StatsTrafficVO> apiServiceList = statsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
        */
        
        //앱 개발자
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> subCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("subCompanyList", subCompanyList);
        /*
        //앱 개발자
        List<StatsTrafficVO> subCompanyList = statsTrafficService.selectStatsTrafficSubCompanyList(paramVO);
        model.addAttribute("subCompanyList", subCompanyList);
        */
        
        //앱이름
        List<StatsTrafficVO> appNameList = statsTrafficService.selectStatsTrafficAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);
        
        //Plan 종류
        List<StatsTrafficVO> apiPlanNameList = statsTrafficService.selectStatsTrafficApiPlanNameList(paramVO);
        model.addAttribute("apiPlanNameList", apiPlanNameList);
		        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectStatsTrafficList
     * @Method description : 트레픽 통계 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/selectStatsTrafficList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectStatsTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = statsTrafficService.selectStatsTrafficList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
		
		model.addAttribute("paramVO", paramVO);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changePubCompanyList
     * @Method description : API 서비스 제공자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changePubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changePubCompanyList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //API 서비스 이름
        List<StatsTrafficVO> apiNameList = statsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        
        //세부 API 서비스
        List<StatsTrafficVO> apiServiceList = statsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeApiCategoryList
     * @Method description : API 서비스 구분 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changeApiCategoryList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeApiCategoryList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //API 서비스 이름
        List<StatsTrafficVO> apiNameList = statsTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);
        
        //세부 API 서비스
        List<StatsTrafficVO> apiServiceList = statsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeApiNameList
     * @Method description : API 서비스 이름 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changeApiNameList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeApiNameList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //세부 API 서비스
        List<StatsTrafficVO> apiServiceList = statsTrafficService.selectStatsTrafficApiServiceList(paramVO);
        model.addAttribute("apiServiceList", apiServiceList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeSubCompanyList
     * @Method description : 앱 개발자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changeSubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeSubCompanyList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //앱이름
        List<StatsTrafficVO> appNameList = statsTrafficService.selectStatsTrafficAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : statsTrafficListExcel
     * @Method description : 트레픽 통계 Excel 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsTrafficListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsTrafficListExcel(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsTrafficListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        Map<String, Object> map = statsTrafficService.selectStatsTrafficList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
		        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
}
