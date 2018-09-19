package kr.co.koscom.oppf.apt.pushmng.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.pushmng.service.PushService;
import kr.co.koscom.oppf.apt.pushmng.service.PushVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;

@Controller
public class PushManagerController {

    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;      //공통코드서비스
    
    @Resource(name = "PushService")
    private PushService pushService;


    /**
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/apt/pushmng/pushSendList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String pushList(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/push/pushList";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        //공통코드 - 플랫폼(deviceType)
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G038");
        List<CmmFuncVO> deviceTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        for(int i=0; i<deviceTypeList.size(); i++){
        	if(deviceTypeList.get(i).getSystem_code().equals("G038001")){
        		deviceTypeList.remove(i);
        	}
        }
        
        //공통코드 - 푸시 메세지 타입 (deviceType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G041");
        List<CmmFuncVO> pushMessageTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        //공통코드 -  전송 유형 (sendType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G043");
        List<CmmFuncVO> sendTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);

        model.addAttribute("sendTypeList", sendTypeList);
        model.addAttribute("deviceTypeList", deviceTypeList);
        model.addAttribute("pushMessageTypeList", pushMessageTypeList);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }
    
    /**
     * @Method Name        : selectPushList
     * @Method description : push 메시지 조회
     * @param              :
     * @return             : 
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/pushmng/SelectPushSendList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectPushList(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }

        Map<String, Object> map = pushService.selectPushList(paramVO);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultListTotalCount", map.get("totCnt"));
        return "jsonView";
    }
    
    
    
    
    /**
     * @param paramVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/apt/pushmng/pushAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String pushAdd(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/push/pushAdd";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        //공통코드 - 플랫폼(deviceType)
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G038");
        List<CmmFuncVO> deviceTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        //공통코드 - 푸시 메세지 타입 (deviceType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G041");
        List<CmmFuncVO> pushMessageTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        //공통코드 -  전송 유형 (sendType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G043");
        List<CmmFuncVO> sendTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);

        model.addAttribute("sendTypeList", sendTypeList);
        model.addAttribute("deviceTypeList", deviceTypeList);
        model.addAttribute("pushMessageTypeList", pushMessageTypeList);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }
    
    
    /**
     * @Method Name        : NewPushAdd
     * @Method description : push 메시지 추가
     * @param              :
     * @return             : 
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/pushmng/pushAdd.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String insertPush(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        paramVO.setCreateId(loginVO.getAdmin_profile_reg_no());
        String result = pushService.insertPush(paramVO);
        
        model.addAttribute("result", result);
        return "jsonView";
    }
    
    
    /**
     * @Method Name        : pushDtl
     * @Method description : push 메시지 상세 조회한다
     * @param              :
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/pushmng/pushDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String mockUpServiceDtl(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
        //modelView
        String modelView = "apt/push/pushDtl";

        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        PushVO pushVO = pushService.selectPushDtl(paramVO);
        
        //공통코드 - 플랫폼(deviceType)
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G038");
        List<CmmFuncVO> deviceTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        //공통코드 - 푸시 메세지 타입 (deviceType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G041");
        List<CmmFuncVO> pushMessageTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        //공통코드 -  전송 유형 (sendType)
        cmmFuncVO = new CmmFuncVO();
        cmmFuncVO.setSystem_grp_code("G043");
        List<CmmFuncVO> sendTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        
        model.addAttribute("pushDtl", pushVO);
        model.addAttribute("sendTypeList", sendTypeList);
        model.addAttribute("deviceTypeList", deviceTypeList);
        model.addAttribute("pushMessageTypeList", pushMessageTypeList);
        model.addAttribute("paramVO", paramVO);

        return modelView;
    }
    
    
	/**
     * @Method Name        : pushUpdate
     * @Method description : Push 정보 수정
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/pushmng/pushUpdate.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSptUserManageDtlDev(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
               
        //Push 정보 수정
  		int result = pushService.updatePush(paramVO);								
  		model.addAttribute("result", result);
	    return "jsonView";
	}
	
	
	/**
     * @Method Name        : pushSendListExcel
     * @Method description : Push excel 목록 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/pushmng/pushSendListExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String pushListExcel(@ModelAttribute("PushVO") PushVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        //modelView
        String modelView = "apt/push/pushListExcel";
       
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }

        Map<String, Object> map = pushService.selectPushListExcel(paramVO);

		model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("PushVO", paramVO);
		
	    return modelView;
    }
	
	
	

}
