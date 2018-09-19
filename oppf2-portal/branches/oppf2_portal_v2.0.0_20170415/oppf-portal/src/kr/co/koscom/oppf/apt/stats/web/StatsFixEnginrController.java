package kr.co.koscom.oppf.apt.stats.web;

import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.stats.service.StatsServiceTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;

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
 * StatsFixEnginrController
 * <p>
 * Created by 최판광 on 2017-03-03.
 */
@Controller
public class StatsFixEnginrController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;

    @Resource(name = "StatsTrafficService")
    private StatsTrafficService statsTrafficService;

    @Resource(name = "StatsServiceTrafficService")
    private StatsServiceTrafficService statsServiceTrafficService;
    

    @Resource(name = "FixManageService")
    private FixManageService fixManageService;


    /**
     * Fix Engine 통계
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineListRequest.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineListRequest(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineListRequest";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

      //공통코드 : Buy-side
        FixManageVO fixManageVO = new FixManageVO();
        List<FixManageVO> resultList = fixManageService.fixBuySideListSearchCombo(fixManageVO);
        model.addAttribute("fixBuySideList", resultList);
        
        //공통코드 : MsgType
        fixManageVO = new FixManageVO();
        fixManageVO.setSearchId(OppfProperties.getProperty("Globals.fix.msgTypeReq"));
        List<FixManageVO> msgTypeList = fixManageService.fixCodeListSearch(fixManageVO);
        model.addAttribute("msgTypeList", msgTypeList);
        
        
        //param
        model.addAttribute("paramVO", paramVO);

	    return modelView;
    }


    /**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineRequestajax.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineRequestSearch(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineRequestList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }


        paramVO.setSearchDateTimeFrom(paramVO.getSearchDateTimeFrom().replaceAll(",", ""));
        paramVO.setSearchDateTimeTo(paramVO.getSearchDateTimeTo().replaceAll(",", ""));
        
        if(paramVO.getSearchReject().size()>1){
        	paramVO.setRejectYn("all");	
        }else{
        	paramVO.setRejectYn(paramVO.getSearchReject().get(0));
        }
        Map<String, Object> map = fixManageService.statsFixEngineRequestSearch(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
        
        

        return"jsonView";
    }
    /**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineRequestajaxExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineRequestajaxExcel(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineListRequestExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }


        paramVO.setSearchDateTimeFrom(paramVO.getSearchDateTimeFrom().replaceAll(",", ""));
        paramVO.setSearchDateTimeTo(paramVO.getSearchDateTimeTo().replaceAll(",", ""));

        if(paramVO.getSearchReject().size()>1){
        	paramVO.setRejectYn("all");	
        }else{
        	paramVO.setRejectYn(paramVO.getSearchReject().get(0));
        }
        //List<FixManageVO> resultList = fixManageService.statsFixEngineRequestSearch(paramVO); 
        
        Map<String, Object> map = fixManageService.statsFixEngineRequestSearch(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
        
        

        return modelView;
    }
    
    
    /**
     * App별 트래픽 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineReponseList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineReponseList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineListResponse";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //공통코드 : Buy-side
		FixManageVO fixManageVO = new FixManageVO();
		List<FixManageVO> resultList = fixManageService.fixBuySideListSearchCombo(fixManageVO);
		model.addAttribute("fixBuySideList", resultList);

		//공통코드 : MsgType
		fixManageVO = new FixManageVO();
		fixManageVO.setSearchId(OppfProperties.getProperty("Globals.fix.msgTypeRes"));
		List<FixManageVO> msgTypeList = fixManageService.fixCodeListSearch(fixManageVO);
		model.addAttribute("msgTypeList", msgTypeList);
		
		//공통코드 : initiatorList
		fixManageVO = new FixManageVO();
		List<FixManageVO> initiatorList = fixManageService.fixInitiatorListSearchCombo(fixManageVO);
		model.addAttribute("initiatorList", initiatorList);
        

        model.addAttribute("paramVO", paramVO);
        
	    return modelView;
    }

    /**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineResponseajax.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineResponseajax(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineRequestList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        paramVO.setSearchDateTimeFrom(paramVO.getSearchDateTimeFrom().replaceAll(",", ""));
        paramVO.setSearchDateTimeTo(paramVO.getSearchDateTimeTo().replaceAll(",", ""));
        
        Map<String, Object> map = fixManageService.statsFixEngineResponseSearch(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
        
        

        return"jsonView";
    }
    

    /**
     * Fix Engine 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
    */ 
    @RequestMapping(value="/apt/stats/statsFixEngineResponseajaxExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsFixEngineResponseajaxExcel(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/statsFixEngineListResponseExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        paramVO.setSearchDateTimeFrom(paramVO.getSearchDateTimeFrom().replaceAll(",", ""));
        paramVO.setSearchDateTimeTo(paramVO.getSearchDateTimeTo().replaceAll(",", ""));
        
        Map<String, Object> map = fixManageService.statsFixEngineResponseSearch(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);
        
        

        return modelView;
    }
    @RequestMapping(value="/apt/stats/mornitoring.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mornitoring(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
    	//modelView
        String modelView = "/apt/stats/mornitoring";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        
        return modelView;
    }
    
    
    
}
