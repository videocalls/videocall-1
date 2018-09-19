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

import kr.co.koscom.oppf.apt.stats.service.StatsServiceService;
import kr.co.koscom.oppf.apt.stats.service.StatsServiceVO;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : StatsServiceController.java
* @Comment  : 회원 통계 관리를 위한 위한 Controller
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
public class StatsServiceController {
	
	private static final Logger log = Logger.getLogger(StatsServiceController.class);
	
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "StatsServiceService")
    private StatsServiceService statsServiceService;
	
	/**
     * @Method Name        : statsServiceList
     * @Method description : 회원 통계 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsServiceList.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsServiceList(@ModelAttribute("StatsServiceVO") StatsServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsServiceList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        //API 서비스 제공자
        List<StatsServiceVO> pubCompanyList = statsServiceService.selectStatsServicePubCompanyList(paramVO);
        model.addAttribute("pubCompanyList", pubCompanyList);
        
        //API 서비스 구분
        List<StatsServiceVO> apiCategoryList = statsServiceService.selectStatsServiceApiCategoryList(paramVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
        
        //앱 개발자
        List<StatsServiceVO> subCompanyList = statsServiceService.selectStatsServiceSubCompanyList(paramVO);
        model.addAttribute("subCompanyList", subCompanyList);
        
        //앱이름
        List<StatsServiceVO> appNameList = statsServiceService.selectStatsServiceAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);
        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
	
	/**
     * @Method Name        : selectStatsServiceList
     * @Method description : 서비스 연결 통계 목록을 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/selectStatsServiceList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectStatsServiceList(@ModelAttribute("StatsServiceVO") StatsServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		Map<String, Object> map = statsServiceService.selectStatsServiceList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeStatsServicePubCompanyList
     * @Method description : API 서비스 제공자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changeStatsServicePubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeStatsServicePubCompanyList(@ModelAttribute("StatsServiceVO") StatsServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //API 서비스 구분
        List<StatsServiceVO> apiCategoryList = statsServiceService.selectStatsServiceApiCategoryList(paramVO);
        model.addAttribute("apiCategoryList", apiCategoryList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : changeStatsServicePubCompanyList
     * @Method description : 앱 개발자 변경 시 하위 select 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/changeStatsServiceSubCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String changeStatsServiceSubCompanyList(@ModelAttribute("StatsServiceVO") StatsServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //앱이름
        List<StatsServiceVO> appNameList = statsServiceService.selectStatsServiceAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);
				
	    return "jsonView";
	}
	
	/**
     * @Method Name        : statsServiceListExcel
     * @Method description : 회원 통계 Excel 조회 화면으로 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/stats/statsServiceListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String statsServiceListExcel(@ModelAttribute("StatsServiceVO") StatsServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//modelView
        String modelView = "apt/stats/statsServiceListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        Map<String, Object> map = statsServiceService.selectStatsServiceList(paramVO);
		model.addAttribute("resultHeader", map.get("resultHeader"));
		model.addAttribute("resultList", map.get("resultList"));
		        
        //param
		model.addAttribute("paramVO", paramVO);
		
	    return modelView;
	}
}
