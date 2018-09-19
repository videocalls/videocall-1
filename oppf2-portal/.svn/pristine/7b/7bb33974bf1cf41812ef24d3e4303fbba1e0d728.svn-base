package kr.co.koscom.oppf.apt.queue.web;

import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.fix.service.FixQueueVO;
import kr.co.koscom.oppf.apt.queue.service.FixQueueManageService;
import kr.co.koscom.oppf.apt.queue.service.FixQueueManageVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2017. 3. 8..
 */
@Controller
public class FixQueueManageController {

    @Resource(name="FixQueueManageService")
    private FixQueueManageService fixQueueManageService;

    @Resource(name="FixManageService")
    private FixManageService fixManageService;

    /**
     * fixBuySide
     * Buy-side 기업 관리 페이지로 이동
     * @param paramVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/fixQueueManageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixQueueManageList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/queue/FixQueueManageList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return modelView;
        }

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    /**
     * Excel
     * Queue Excel
     * @param paramVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/selectFixQueueManageListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectFixQueueManageListExcel(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/queue/queueManagementExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return modelView;
        }

        Map<String, Object> map = fixQueueManageService.selectFixQueueManageListExcel(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }



    /**
     * fixBuySide
     * Buy-side 기업 관리 페이지로 이동
     * @param paramVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/selectFixQueueManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectFixQueueManageList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String,Object> map = fixQueueManageService.selectFixQueueManageList(paramVO);
        List<FixQueueManageVO> serverInfo = fixQueueManageService.selectInitiatorServerIdList(paramVO);
        map.put("serverInfo", serverInfo);

        model.addAttribute("serverInfo", map.get("serverInfo"));
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }


    /**
     * senderCompDtlQueuePopup
     * Queue데이터로 SenderCompId List Popup
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/senderCompDtlQueuePopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String senderCompDtlPopup(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/queue/senderCompDtlPopup";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return modelView;
        }

        model.addAttribute("paramVO", paramVO);

        return modelView;
    }


    /**
     * fixBuySide
     * Buy-side 기업 관리 페이지로 이동
     * @param paramVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/selectQueueCompanyList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectQueueCompanyList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String,Object> map = fixQueueManageService.selectQueueCompanyList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }


    /**
     * fixBuySide
     * 수정
     * @param paramVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/updateFixQueueManageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    private String updateFixQueueManageList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

         /*Fix Server Connection 공통코드 관련*/
        FixManageVO fixManageVO = new FixManageVO();
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
        String url = fixManageService.fixServerIpSearch(fixManageVO);

        String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.fixQueue");

        String payload = "";

        /* 삭제 */
        paramVO.setActiveQueueYn("N");
        payload = queueUpdate(paramVO, loginVO);
        fixManageService.sendRestTemplate("http://" + url + restUrl, HttpMethod.PUT, payload);

        /* 삭제 후 수정 API */
        paramVO.setActiveQueueYn("Y");
        paramVO.setInitServerId(paramVO.getAfterInitServerIp());
        payload = queueUpdate(paramVO, loginVO);
        fixManageService.sendRestTemplate("http://" + url + restUrl, HttpMethod.PUT, payload);

        model.addAttribute("paramVO", paramVO);

        return "jsonView";
    }

    private String queueUpdate(FixQueueManageVO paramVO, LoginVO loginVO) {
        String payload = "{\n" +
                "\n" +
                "\n" +
                "\"body\": {\n" +
                "\"list\": [\n" +
                "{\n" +
                "\"fixQueueId\": \"" + paramVO.getFixQueueId() + "\",\n" +
                "\"initServerId\": \"" + paramVO.getInitServerId() + "\",\n" +
                "\"activeQueueYn\":\"" + paramVO.getActiveQueueYn() + "\"\n" +
                "\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "}";
        return payload;
    }

    /**
     * FixQueueMornitoringList
     * Queue 모니터링 페이지 이동
     * @param paramVO
     * @param request
     * @param model
     * @return FixQueueMornitoringList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/FixQueueMornitoringList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String FixQueueMornitoringList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

		//modelView
		String modelView = "apt/queue/FixQueueMornitoringList";
		
		//1.로그인관련
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if(loginVO == null){
		    model.addAttribute("error", "-1");
		    return modelView;
		}   
		
		if(OppfStringUtil.isEmpty(paramVO.getIntervalTime())){
			paramVO.setIntervalTime("30000");
		}
		
		model.addAttribute("paramVO", paramVO);
		
		return modelView;
    }

    /**
     * selectFixQueueMornitoringList
     * Queue 모니터링 조회
     * @param paramVO
     * @param request
     * @param model
     * @return FixQueueMornitoringList
     * @throws Exception
     */
    @RequestMapping(value="/apt/queue/selectFixQueueMornitoringList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectFixQueueMornitoringList(@ModelAttribute("FixQueueManageVO") FixQueueManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

		
		/*Fix Server Connection 공통코드 관련*/
		FixManageVO fixManageVO = new FixManageVO();
		fixManageVO.setFixServerId("rs_comp");
		fixManageVO.setFixServerCode("ip");
		String url = fixManageService.fixServerIpSearch(fixManageVO);
		
		String restUrl = OppfProperties.getProperty("Globals.fix.restAcceptor.queue");
		String payload="";
		   
		List<FixQueueVO> resultList = fixManageService.sendRestTemplateList("http://" + url + restUrl, HttpMethod.GET, payload);
		
		FixManageVO fixVO = new FixManageVO();
		   
		fixVO.setQueuelist(resultList);

		if(OppfStringUtil.isEmpty(paramVO.getIntervalTime())){
			paramVO.setIntervalTime("30000");
		}
		

		model.addAttribute("resultList", fixVO.getQueuelist());
		model.addAttribute("resultListCnt", fixVO.getQueuelist().size());
		model.addAttribute("intervalTime", paramVO.getIntervalTime());
		
		   

        return "jsonView";
    }


}
