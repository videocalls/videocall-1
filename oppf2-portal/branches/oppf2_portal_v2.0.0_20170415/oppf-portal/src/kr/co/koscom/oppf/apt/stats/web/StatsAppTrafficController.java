package kr.co.koscom.oppf.apt.stats.web;

import kr.co.koscom.oppf.apt.stats.service.StatsServiceTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficService;
import kr.co.koscom.oppf.apt.stats.service.StatsTrafficVO;
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
 * StatsAppTrafficController
 * <p>
 * Created by chungyeol.kim on 2017-03-03.
 */
@Controller
public class StatsAppTrafficController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;

    @Resource(name = "StatsTrafficService")
    private StatsTrafficService statsTrafficService;

    @Resource(name = "StatsServiceTrafficService")
    private StatsServiceTrafficService statsServiceTrafficService;

    @RequestMapping(value="/apt/stats/statsAppTrafficList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsAppTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/stats/statsAppTrafficList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //앱 개발자
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002003");
        List<CmmFuncVO> subCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("subCompanyList", subCompanyList);

        //앱이름
        List<StatsTrafficVO> appNameList = statsTrafficService.selectStatsTrafficAppNameList(paramVO);
        model.addAttribute("appNameList", appNameList);

        //Plan 종류
        List<StatsTrafficVO> apiPlanNameList = statsServiceTrafficService.selectStatsTrafficApiPlanNameList(paramVO);
        model.addAttribute("apiPlanNameList", apiPlanNameList);

        //param
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
    @RequestMapping(value="/apt/stats/selectStatsAppTrafficList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectStatsAppTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String, Object> map = statsServiceTrafficService.selectStatsAppTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/apt/stats/statsAppTrafficListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsAppTrafficListExcel(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/stats/statsAppTrafficListExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        Map<String, Object> map = statsServiceTrafficService.selectStatsAppTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }
}
