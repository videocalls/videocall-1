package kr.co.koscom.oppf.apt.fix.web;

import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageService;
import kr.co.koscom.oppf.apt.cptUsr.service.CptCompanyManageVO;
import kr.co.koscom.oppf.apt.fix.service.FixManageService;
import kr.co.koscom.oppf.apt.fix.service.FixManageVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
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



@Controller
public class FixManageController {
	
    @Resource(name = "FixManageService")
    private FixManageService fixManageService;
    
	@Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
	
	@Resource(name = "CptCompanyManageService")
    private CptCompanyManageService cptCompanyManageService;
    



    /**
     * fixBuySide
     * Buy-side 기업 관리 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideList(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixBuySideList";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
            return modelView;
        }
        //loginVO.getName_kor()
        
        // Queue 조회
        List<FixManageVO> queueList = fixManageService.selectQueueListCombo(fixManageVO);
        
        model.addAttribute("queueList", queueList);
        
        return modelView;
    }



    /**
     * fixBuySide
     * Buy-side 기업 관리 리스트 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideListSearch.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideListSearch(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
      //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        // 기업 리스트 조회
        List<FixManageVO> resultList = fixManageService.fixBuySideListSearch(fixManageVO);
        // 기업 리스트 총 카운트 조회
        int resultListTotalCount = fixManageService.resultListTotalCount(fixManageVO);
        

        model.addAttribute("resultList", resultList);

        model.addAttribute("resultListTotalCount", resultListTotalCount);
        

	    return "jsonView";
    }

	/**
     * @Method Name        : fixBuySideListSearchExcel
     * @Method description : buy-side 관리  excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/fix/fixBuySideListSearchExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String fixBuySideListSearchExcel(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "/apt/fix/fixBuySideListExcel";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        // 기업 리스트 조회
        List<FixManageVO> resultList = fixManageService.fixBuySideListSearchExcel(fixManageVO);

        model.addAttribute("resultList", resultList);

		model.addAttribute("FixManageVO", fixManageVO);
		
	    return modelView;
	}
	


    /**
     * fixBuySide
     * Buy-side 기업 관리 상세 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideDtl(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{

        String modelView = "apt/fix/fixBuySideUpdate";
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return modelView;
        }
      

        // Queue 조회
        List<FixManageVO> queueList = fixManageService.selectQueueListCombo(fixManageVO);
        

        FixManageVO result = fixManageService.fixBuySideDtl(fixManageVO);

        		
        model.addAttribute("result", result);
        
        model.addAttribute("queueList", queueList);
        
        return modelView;
    }
    

    /**
     * fixBuySide
     * compId 중복조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/countSenderCompId.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String countSenderCompId(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        // CompID 카운트 조회
        int resultCount = fixManageService.countSenderCompId(fixManageVO);

        if(resultCount>0){
        	model.addAttribute("resultCount", "Y");
        } else {
        	model.addAttribute("resultCount", "N");
        }

	    return "jsonView";
    }

    /**
     * fixBuySide
     * Buy-side 기업 관리 업데이트
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideDtlUpdate.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideUpdate(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("rs_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.restAcceptor.comps");

		String payload =  fixBuySideAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.PUT, payload);
		
		model.addAttribute("result", result);

        
        
        return "jsonView";
    }

    /**
     * fixBuySide
     * Buy-side 기업 관리 업데이트
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideDtlDelete.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideDtlDelete(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("rs_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.restAcceptor.comps");

		String payload =  fixBuySideAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.DELETE, payload);
		
		model.addAttribute("result", result);
        
        
        return "jsonView";
    }
    

	
	/**
     * @Method Name        : cptCompanyCodeListPopup
     * @Method description : 미 등록 기업 코드 팝업
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/fix/cptCompanyCodeListPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cptCompanyCodeListPopup(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "apt/fix/fixCompanyCodeListPopup";
        
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        CmmFuncVO cmmFuncVO = new CmmFuncVO();
        
        //셋팅 공통코드 : 기업 코드 표 구분
        cmmFuncVO.setSystem_grp_code("G001");
        List<CmmFuncVO> companyCodeTypeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeTypeList", companyCodeTypeList);
        
        //셋팅 공통코드 : 기업 종류
        cmmFuncVO.setSystem_grp_code("G014");
        List<CmmFuncVO> companyCodeKindList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyCodeKindList", companyCodeKindList);
        
        //셋팅 공통코드 : 기업 분류
        cmmFuncVO.setSystem_grp_code("G029");
        List<CmmFuncVO> companyDivCodeList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
        model.addAttribute("companyDivCodeList", companyDivCodeList);
		
		//model.addAttribute("CptCompanyManageVO", paramVO);
		model.addAttribute("callBakFunc", "fn_addFixCompanyCallBack");
		
		
	    return modelView;
	}

	/**
     * @Method Name        : selectCptCompanyCodeListPopup
     * @Method description : 기업 코드 팝업 리스트 조회
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/fix/selectCptCompanyCodePopupList.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectCptCompanyCodePopupList(@ModelAttribute("CptCompanyManageVO") CptCompanyManageVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
		
        //order
        paramVO.setListOrder("a.company_name_kor_alias asc,a.company_code_type asc,a.company_code_kind asc,a.company_div_code asc,a.company_code_reg_no desc");
        
        Map<String, Object> map = cptCompanyManageService.selectCptCompanyCodePopupList(paramVO);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultListTotalCount", map.get("totCnt"));
				
	    return "jsonView";
	}
	


    /**
     * selectCompanyIdCnt
     * companyId 중복조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/selectCompanyIdCnt.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String selectCompanyIdCnt(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
       // System.out.println("fixManageVO.getCompanyId() = "+fixManageVO.getCompanyId());
        
        // CompID 카운트 조회
        int resultCount = fixManageService.selectCompanyIdCnt(fixManageVO);

        if(resultCount>0){
        	model.addAttribute("resultCount", "Y");
        } else {
        	model.addAttribute("resultCount", "N");
        }

	    return "jsonView";
    }
    
    /**
     * RestAcceptor CompID 신규등록 json 생성
     * RestAcceptor CompID 변경 json 생성
     * @param fixManageVO
     * @param loginVO
     * @return
     */
    public String fixBuySideAdd(FixManageVO fixManageVO,LoginVO loginVO){

		String payload = 
				"{ \"body\" : { \"list\" : [ { "
						+ "\"senderCompId\" : \""+fixManageVO.getSenderCompId()+"\" , "
						+ "\"senderCompName\" : \""+fixManageVO.getSenderCompName()+"\" , "
						+ "\"companyId\" : \""+fixManageVO.getCompanyId()+"\" , "
						+ "\"fixQueueId\" : \""+fixManageVO.getFixQueueId()+"\" , "
						+ "\"sessionSenderCompId\" : \""+fixManageVO.getSessionSenderCompId()+"\" , "
						+ "\"useYn\" : \""+fixManageVO.getUseYnStatus()+"\" , "
						+ "\"userId\" : \""+loginVO.getAdmin_profile_reg_no()+"\" , "
						+ "\"userNm\" : \""+loginVO.getName_kor()+"\"  "
				+ "}]}}";
    	return payload;
    }
    /**
     * initiator 신규등록, 수정, 변경
     * @param fixManageVO
     * @param loginVO
     * @return
     */
    public String initiatorAdd(FixManageVO fixManageVO,LoginVO loginVO){

		String payload = 
				"{ \"body\" : { \"list\" : [ { "
						+ "\"initServerId\" : \""+fixManageVO.getInitServerId()+"\" , "
						+ "\"initServerIp\" : \""+fixManageVO.getInitServerIp()+"\" , "
						+ "\"userId\" : \""+loginVO.getAdmin_profile_reg_no()+"\" , "
						+ "\"userNm\" : \""+loginVO.getName_kor()+"\"  "
				+ "}]}}";
    	return payload;
    }
    

    /**
     * session 신규등록, 수정, 변경
     * @param fixManageVO
     * @param loginVO
     * @return
     */
    public String sessionAdd(FixManageVO fixManageVO,LoginVO loginVO){

		String payload = 
				"{ \"body\" : { \"list\" : [ { "
						+ "\"sessionTargetCompId\" : \""+fixManageVO.getSessionTargetCompId()+"\" , "
						+ "\"sessionSenderCompId\" : \""+fixManageVO.getSessionSenderCompId()+"\" , "
						+ "\"initServerId\" : \""+fixManageVO.getInitServerId()+"\" , "
						+ "\"connectionHost\" : \""+fixManageVO.getConnectionHost()+"\" , "
						+ "\"connectionPort\" : \""+fixManageVO.getConnectionPort()+"\"  "
				+ "}]}}";
    	return payload;
    }

    /**
     * session 가동, 중지
     * @param fixManageVO
     * @param loginVO
     * @return
     */
    public String sessionInfo(FixManageVO fixManageVO){

		String payload = 
				"{ \"body\" : { \"list\" : [ { "
						+ "\"sessionTargetCompId\" : \""+fixManageVO.getSessionTargetCompId()+"\" , "
						+ "\"sessionSenderCompId\" : \""+fixManageVO.getSessionSenderCompId()+"\" "
				+ "}]}}";
    	return payload;
    }
    
    
    
    /**
     * fixBuySide
     * [Buy-side 기업 관리] 등록 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideAdd
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideAdd(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixBuySideAdd";

        // Queue 조회
        List<FixManageVO> queueList = fixManageService.selectQueueListCombo(fixManageVO);

        fixManageVO.setSenderCompName(fixManageVO.getCompanyNameKor());
        fixManageVO.setCompanyId(fixManageVO.getCompanyCodeId());
        
        model.addAttribute("result", fixManageVO);
        
        model.addAttribute("queueList", queueList);
        
        return modelView;
    }

    /**
     * fixBuySide
     * [Buy-side 기업 관리] 등록 
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixBuySideAdd
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixBuySideInsert.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixBuySideInsert(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("rs_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.restAcceptor.comps");
		
		String payload =  fixBuySideAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.POST, payload);
        
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }

    

    /**
     * Initiator 서버관리 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiator.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiator(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixInitiatorList";

    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return modelView;
        }

        return modelView;
    }
    

    /**
     * Initiator 서버관리 리스트 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return jsonView
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorList.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorList(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{

    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        // 기업 리스트 조회
        List<FixManageVO> resultList = fixManageService.fixInitiatorListSearch(fixManageVO);
        // 기업 리스트 총 카운트 조회
        
		model.addAttribute("resultList", resultList);
		
        return "jsonView";
    }

    

	/**
     * @Method Name        : fixInitiatorListhExcel
     * @Method description : Initiator excel 목록 이동
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/fix/fixInitiatorListhExcel.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String fixInitiatorListhExcel(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
		
		//modelView
        String modelView = "/apt/fix/fixInitiatorListExcel";
        
        
        System.out.println("fixManageVO.getExcelType()="+fixManageVO.getExcelType());
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        // 기업 리스트 조회
        List<FixManageVO> resultList = fixManageService.fixInitiatorListhExcel(fixManageVO);

        model.addAttribute("resultList", resultList);

		model.addAttribute("FixManageVO", fixManageVO);
		
	    return modelView;
	}
    
    /**
     * Initiator 서버등록 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorAdd(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixInitiatorAdd";
        return modelView;
    }
    
    /**
     * Initiator 서버 IP 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatoServerIpDupChk.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatoServerIpDupChk(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
        int cnt = fixManageService.fixInitiatorServerIpDupChk(fixManageVO);
        model.addAttribute("ipCnt", cnt);

        return "jsonView";
    }

    /**
     * Initiator senderCompId 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixCompIdDupChk.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixCompIdDupChk(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	int cnt = fixManageService.fixCompIdDupChk(fixManageVO);
        model.addAttribute("idCnt", cnt);

        return "jsonView";
    }


    /**
     * Initiator Socket Port 중복 조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSocketDupChk.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSocketDupChk(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	int cnt = fixManageService.fixSocketDupChk(fixManageVO);
        model.addAttribute("idCnt", cnt);

        return "jsonView";
    }

    

    /**
     * fixInitiatorStart
     * [Initiator 서버 관리] 서버 시작 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorStart.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorStart(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		/*
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        */


        String restUrl = OppfProperties.getProperty("Globals.fix.startAdmin");
        	
        String port = OppfProperties.getProperty("Globals.fix.initiator.port");
		
		String payload =  sessionInfo(fixManageVO);
		
        String result = fixManageService.sendRestTemplate("http://"+fixManageVO.getInitServerIp()+port+restUrl,HttpMethod.POST, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    

    /**
     * fixInitiatorInsert
     * [Initiator 서버 관리] 서버 중지 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorStop.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorStop(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        /*String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
		*/
        
		String restUrl = OppfProperties.getProperty("Globals.fix.stopAdmin");

    	
        String port = OppfProperties.getProperty("Globals.fix.initiator.port");

		String payload =  sessionInfo(fixManageVO);
		
        String result = fixManageService.sendRestTemplate("http://"+fixManageVO.getInitServerIp()+port+restUrl,HttpMethod.POST, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    
    /**
     * fixInitiatorInsert
     * [Initiator 서버 관리] 등록 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorInsert.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorInsert(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.comps");
		
		String payload =  initiatorAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.POST, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    
    /**
     * Initiator 서버수정 페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorUpdate.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorUpdateSearch(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixInitiatorUpdate";
        

        FixManageVO result = fixManageService.fixInitiatorUpdateSearchDtl(fixManageVO);

		model.addAttribute("result", result);
        
        return modelView;
    }
    
    /**
     * fixInitiatorInsert
     * [Initiator 서버 관리] 수정 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorUpdate.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorUpdate(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.comps");
		
		String payload =  initiatorAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.PUT, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    
    

    /**
     * fixInitiatorInsert
     * [Initiator 서버 관리] 수정 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorDel.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorDel(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.comps");
		
		String payload =  initiatorAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.DELETE, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    /**
     * Initiator 기업 정보 조회 Popup페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorCompPopup.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorCompPopup(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/fix/fixCompanyPopup";
        
        model.addAttribute("result", fixManageVO);
        return modelView;
    }


    	
    /**
     * Initiator Session 추가 Popup페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSessionAdd(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/fix/fixSessionAdd";

        model.addAttribute("result", fixManageVO);

    	//공통코드 : initiatorList
    	fixManageVO = new FixManageVO();
    	List<FixManageVO> initiatorList = fixManageService.fixInitiatorListSearchCombo(fixManageVO);
    	model.addAttribute("initiatorList", initiatorList);
        
        return modelView;
    }

    /**
     * Session 상세조회
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionDtl.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSessionDtl(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	

        String modelView = "apt/fix/fixSessionUpdate";
        
    	//modelView
        model.addAttribute("fixVo", fixManageVO);

    	// fix session 상세조회
    	FixManageVO result = fixManageService.fixSessionAddDtl(fixManageVO);
    	model.addAttribute("result", result);
    	
    	//공통코드 : initiatorList
    	fixManageVO = new FixManageVO();
    	List<FixManageVO> initiatorList = fixManageService.fixInitiatorListSearchCombo(fixManageVO);
    	model.addAttribute("initiatorList", initiatorList);
    	
        
        return modelView;
    }
    
    

    /**
     * fixSessionInsert
     * [Initiator 세션] 등록 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionInsert.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSessionInsert(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.session");
		
		String payload =  sessionAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.POST, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }
    

    /**
     * fixSessionInsert
     * [Initiator 세션] 등록 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionUpdate.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSessionUpdate(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.session");
		
		String payload =  sessionAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.PUT, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }

    /**
     * fixSessionInsert
     * [Initiator 세션] 등록 
     * @param fixManageVO
     * @param request
     * @param model
     * @return 
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionDel.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixSessionDel(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

		//인증서버
		//String url = OppfProperties.getProperty("Globals.fix.url");
        
        fixManageVO.setFixServerId("fx_comp");
        fixManageVO.setFixServerCode("ip");
		
        String url = fixManageService.fixServerIpSearch(fixManageVO);
		
        if(OppfStringUtil.isEmpty(url)){
        	model.addAttribute("fixUrlError", "-1");
        	return "jsonView";
        }
        
		String restUrl = OppfProperties.getProperty("Globals.fix.fixInitiator.session");
		
		String payload =  sessionAdd(fixManageVO,loginVO);
		
        String result = fixManageService.sendRestTemplate("http://"+url+restUrl,HttpMethod.DELETE, payload);
        
		model.addAttribute("result", result);
        
        return "jsonView";
    }

    /**
     * Initiator Session 수정 Popup페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixSessionUpdate.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sessionUdate(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView
        String modelView = "apt/fix/fixSessionUpdate";

        model.addAttribute("result", fixManageVO);

    	//공통코드 : initiatorList
    	fixManageVO = new FixManageVO();
    	List<FixManageVO> initiatorList = fixManageService.fixInitiatorListSearchCombo(fixManageVO);
    	model.addAttribute("initiatorList", initiatorList);
        
        return modelView;
    }
    

    
    /**
     * Initiator 기업 정보 조회 Popup페이지로 이동
     * @param fixManageVO
     * @param request
     * @param model
     * @return fixInitiatorList
     * @throws Exception
     */
    @RequestMapping(value="/apt/fix/fixInitiatorCompPopupSearch.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String fixInitiatorCompPopupSearch(@ModelAttribute("FixManageVO") FixManageVO fixManageVO, HttpServletRequest request, ModelMap model)throws Exception{
    	
    	//modelView

        List<FixManageVO> resultList = fixManageService.fixInitiatorCompSearch(fixManageVO);
        int resultListTotalCount = fixManageService.fixInitiatorCompSearchTotal(fixManageVO);

        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);
        
        
        return "jsonView";
    }
        

}
