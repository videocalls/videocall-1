package kr.co.koscom.oppf.apt.stats.web;

import kr.co.koscom.oppf.apt.stats.service.StatsServiceTrafficService;
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
 * StatsServiceTrafficController
 * <p>
 * Created by chungyeol.kim on 2017-03-03.
 */
@Controller
public class StatsServiceTrafficController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;

    @Resource(name = "StatsServiceTrafficService")
    private StatsServiceTrafficService statsServiceTrafficService;

    /**
     * 서비스별 트래픽 통계 화면으로 이동
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/apt/stats/statsServiceTrafficList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsServiceTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/stats/statsServiceTrafficList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        //API 서비스 제공자
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSearchCompanyServiceType("G002002");
        List<CmmFuncVO> pubCompanyList = cmmFuncService.selectCompanyCodeList(cmmFuncVO);
        model.addAttribute("pubCompanyList", pubCompanyList);

        //API 서비스 이름
        List<StatsTrafficVO> apiNameList = statsServiceTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);

        //Plan 종류
        List<StatsTrafficVO> apiPlanNameList = statsServiceTrafficService.selectStatsTrafficApiPlanNameList(paramVO);
        model.addAttribute("apiPlanNameList", apiPlanNameList);

        //param
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * @Method Name        : changePubCompanyList
     * @Method description : API 서비스 제공자 변경 시 하위 select 조회
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/stats/changeServiceCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String changeServiceCompanyList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        //API 서비스 이름
        List<StatsTrafficVO> apiNameList = statsServiceTrafficService.selectStatsTrafficApiNameList(paramVO);
        model.addAttribute("apiNameList", apiNameList);

        return "jsonView";
    }

    /**
     * 서비스별 트래픽 통계 조회
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/apt/stats/selectStatsServiceTrafficList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectStatsTrafficList(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String, Object> map = statsServiceTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/apt/stats/statsServiceTrafficListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String statsServiceTrafficListExcel(@ModelAttribute("StatsTrafficVO") StatsTrafficVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/stats/statsServiceTrafficListExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        Map<String, Object> map = statsServiceTrafficService.selectStatsTrafficList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("chartDisplayNames", map.get("chartDisplayNames"));
        model.addAttribute("maxValueData", map.get("maxValueData"));
        model.addAttribute("isAllData", map.get("isAllData"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }
}
