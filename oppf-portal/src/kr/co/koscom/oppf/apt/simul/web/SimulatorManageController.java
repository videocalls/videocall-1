package kr.co.koscom.oppf.apt.simul.web;

import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.simul.service.SimulatorManageService;
import kr.co.koscom.oppf.apt.simul.service.SimulatorManageVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
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
 * Created by LSH on 2017. 3. 6..
 */
@Controller
public class SimulatorManageController {

    @Resource(name="SimulatorManageService")
    private SimulatorManageService simulatorManageService;

    @Resource(name="FixManageService")
    private FixManageService fixManageService;

    /**
     * @Method Name        : sptUserAccountList
     * @Method description : 가상계좌 목록 이동
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/simul/simulatorManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptUserAccountList(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/simulator/simulatorManageList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        FixManageVO fixManageVO = new FixManageVO();
        List<FixManageVO> list = fixManageService.fixBuySideListCommon(fixManageVO);
        model.addAttribute("fixBuySideList", list);

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/simul/selectSimulatorManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectSimulatorManageList(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        Map<String,Object> map = simulatorManageService.selectSimulatorManageList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    @RequestMapping(value="/apt/simul/simulatorManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String simulatorManageListExcel(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/simulator/simulatorManageListExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        Map<String,Object> map = simulatorManageService.simulatorManageListExcel(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));

        return modelView;
    }

    @RequestMapping(value="/apt/simul/simulatorCompPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String simulatorCompPopup(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/simulator/simulatorCompPopup";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/simul/simulatorCompPopupList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String simulatorCompPopupList(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        Map<String,Object> map = simulatorManageService.simulatorCompPopupList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    /**
     * 상세
     * */
    @RequestMapping(value="/apt/simul/simulatorManageDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String simulatorManageDtl(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/simulator/simulatorManageDtl";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        SimulatorManageVO resultData = simulatorManageService.simulatorManageDtl(paramVO);

        model.addAttribute("paramVO", paramVO);
        model.addAttribute("resultData", resultData);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * 저장 : 입력, 수정
     * */
    @RequestMapping(value="/apt/simul/saveSimulatorManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String saveSimulatorManage(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
        paramVO.setCreateName(loginVO.getName_kor());

        //입력한적이 있는지 조회
        int insertCount = simulatorManageService.simulatorManageDtlCount(paramVO);

        /*Fix Server Connection*/
        FixManageVO fixManageVO = new FixManageVO();
        fixManageVO.setFixServerId("ss_conf");
        fixManageVO.setFixServerCode("ip");
        String url = fixManageService.fixServerIpSearch(fixManageVO);

        if(OppfStringUtil.isEmpty(url)){
            model.addAttribute("fixUrlError", "-1");
            return "jsonView";
        }

        String restUrl = OppfProperties.getProperty("Globals.fix.conf");

        String payload = simulatorAdd(paramVO, loginVO);

        String result = "";
        if(insertCount == 0){
            result = fixManageService.sendRestTemplate("http://" + url + restUrl, HttpMethod.POST, payload);
            model.addAttribute("afterState", "insert");
        }else{
            result = fixManageService.sendRestTemplate("http://" + url + restUrl, HttpMethod.PUT, payload);
            model.addAttribute("afterState", "update");
        }

        model.addAttribute("result", result);

        return "jsonView";
    }

    /**
     * 입력 JSON
     * */
    private String simulatorAdd(SimulatorManageVO simulatorManageVO, LoginVO loginVO){
        String payload =
                "{\n" +
                    "\"body\": {\n" +
                    "\"list\": [\n" +
                            "{\n" +
                                "\"senderCompId\":\"" + simulatorManageVO.getSenderCompId() + "\",\n" +
                                "\"excutionRate\": \"" + simulatorManageVO.getExcutionRate() + "\",\n" +
                                "\"rejectRate\": \"" + simulatorManageVO.getRejectRate() + "\",\n" +
                                "\"companyId\":\"" + simulatorManageVO.getCompanyId() + "\",\n" +
                                "\"senderCompName\":\"" + simulatorManageVO.getSenderCompName() + "\",\n" +
                                "\"userId\":\"" + simulatorManageVO.getCreateId() + "\",\n" +
                                "\"userNm\":\"" + simulatorManageVO.getCreateName() + "\"\n" +
                            "}\n" +
                        "]" +
                    "}" +
                "}";

        return payload ;
    }

    /**
     * 삭제
     * */
    @RequestMapping(value="/apt/simul/deleteSimulatorManage.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String deleteSimulatorManage(@ModelAttribute("SimulatorManageVO") SimulatorManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }


        /*Fix Server Connection*/
        FixManageVO fixManageVO = new FixManageVO();
        fixManageVO.setFixServerId("ss_conf");
        fixManageVO.setFixServerCode("ip");
        String url = fixManageService.fixServerIpSearch(fixManageVO);

        if(OppfStringUtil.isEmpty(url)){
            model.addAttribute("fixUrlError", "-1");
            return "jsonView";
        }

        String restUrl = OppfProperties.getProperty("Globals.fix.conf");

        String payload = simulatorDel(paramVO, loginVO);

        String result = fixManageService.sendRestTemplate("http://" + url + restUrl, HttpMethod.DELETE, payload);


        model.addAttribute("result", result);

        return "jsonView";
    }

    /**
     * 삭제 JSON
     * */
    private String simulatorDel(SimulatorManageVO simulatorManageVO, LoginVO loginVO){
        String payload =
                "{\n" +
                        "\"body\": {\n" +
                        "\"list\": [\n" +
                        "{\n" +
                        "\"senderCompId\":\"" + simulatorManageVO.getSenderCompId() + "\"\n" +
                        "}\n" +
                        "]" +
                        "}" +
                        "}";

        return payload ;
    }
}
