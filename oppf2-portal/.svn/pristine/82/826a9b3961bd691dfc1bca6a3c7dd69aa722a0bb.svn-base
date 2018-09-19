package kr.co.koscom.oppf.apt.fixMsg.web;

import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.FixMessageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.SearchFixMessageVO;
import kr.co.koscom.oppf.apt.fixMsg.service.ViewFixMessageService;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import org.apache.log4j.Logger;
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
 * Created by LSH on 2017. 3. 2..
 */
@Controller
public class ViewFixMessageController {
    private static final Logger log = Logger.getLogger(ViewFixMessageController.class);

    @Resource(name = "ViewFixMessageService")
    private ViewFixMessageService viewFixMessageService;

    @Resource(name= "FixManageService")
    private FixManageService fixManageService;


    @RequestMapping(value="/apt/fixMsg/viewFixMessageList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewFixMessageList(@ModelAttribute("SearchFixMessageVO") SearchFixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/fixMsg/viewFixMessageList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        //공통코드
        //message type
//        paramVO.setCommonCodeId("msg_type");
//        List<SearchFixMessageVO> msgTypeList = viewFixMessageService.selectCommonCodeName(paramVO);
//        model.addAttribute("msgTypeList", msgTypeList);

        FixManageVO fixManageVO = new FixManageVO();

        if(paramVO.getTabDivision() == null || paramVO.getTabDivision().equals("acceptor")) {
            fixManageVO.setSearchId(OppfProperties.getProperty("Globals.fix.msgTypeReq"));
        } else {
            fixManageVO.setSearchId(OppfProperties.getProperty("Globals.fix.msgTypeRes"));
        }
        List<FixManageVO> msgTypeList = fixManageService.fixCodeListSearch(fixManageVO);
        model.addAttribute("msgTypeList", msgTypeList);

        //server type
        paramVO.setCommonCodeId("hub_tp");
        List<SearchFixMessageVO> hubTypeList = viewFixMessageService.selectCommonCodeName(paramVO);
        model.addAttribute("hubTypeList", hubTypeList);


        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/fixMsg/viewFixMessageInitiatorExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewFixMessageInitiatorExcel(@ModelAttribute("SearchFixMessageVO") SearchFixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/fixMsg/fixMeessageInitiatorExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        Map<String, Object> map = viewFixMessageService.viewFixMessageInitiatorExcel(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/fixMsg/viewFixMessageAccountExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewFixMessageAccountExcel(@ModelAttribute("SearchFixMessageVO") SearchFixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/fixMsg/fixMeessageAcceptorExcel";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        Map<String, Object> map = viewFixMessageService.viewFixMessageAccountExcel(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/fixMsg/viewFixMessageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectViewFixMessageList(@ModelAttribute("SearchFixMessageVO") SearchFixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        Map<String, Object> map = viewFixMessageService.selectFixMessageList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }

    @RequestMapping(value="/apt/fixMsg/viewInitFixMessageList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewInitFixMessageList(@ModelAttribute("SearchFixMessageVO") SearchFixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return "jsonView";
        }

        Map<String, Object> map = viewFixMessageService.viewInitFixMessageList(paramVO);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        model.addAttribute("paramVO", paramVO);
        return "jsonView";
    }


    /**
     * popup
     * */

    @RequestMapping(value="/apt/fixMsg/viewFixMessageDtlAcceptorPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewFixMessageDtlAcceptorPopup(@ModelAttribute("FixMessageVO") FixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/fixMsg/viewFixMessageDtlPopup";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        FixMessageVO fixMessage = viewFixMessageService.viewFixMessageDtlAcceptorPopup(paramVO);

        model.addAttribute("fixMessage", fixMessage);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }

    @RequestMapping(value="/apt/fixMsg/viewFixMessageDtlInitiatorPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String viewFixMessageDtlInitiatorPopup(@ModelAttribute("FixMessageVO") FixMessageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/fixMsg/viewFixMessageDtlPopup";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        FixMessageVO fixMessage = viewFixMessageService.viewFixMessageDtlInitiatorPopup(paramVO);

        model.addAttribute("fixMessage", fixMessage);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }


}
