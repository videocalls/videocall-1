package kr.co.koscom.oppf.apt.usr.mbrReg.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.koscom.oppf.apt.sptUsr.service.SptSvcApplVO;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegService;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.tsa.service.CmmTsaVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerCompanyTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceAccountProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerServiceTermsProfileVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplService;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;


@Controller
public class MbrSvcAppController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
	@Resource(name = "NewMbrRegService")
	private NewMbrRegService newMbrRegService;
    
    @Resource(name = "SvcApplService")
    private SvcApplService svcApplService;
    
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
    @Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;
    
    private static final Logger log = Logger.getLogger(MbrSvcAppController.class);
    
    /* 동기식.do 요청 START */
    /**
     * @Method Name        : fintechSvcChoic
     * @Method description : [어드민 일반회원 관리:서비스 연결현황]서비스 연결추가 페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/usr/sptUserNewServiceAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String fintechSvcChoic(@ModelAttribute("SvcApplVO") SvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/sptUsr/sptUserServiceAdd";
    	
        //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            return modelView;
        }
        
        
        return modelView;
    }

	/**
     * @Method Name        : selectMemberInfo
     * @Method description : [기본]금융 투자사 선택
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sptUsr/selectMemberInfo.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMemberInfo(@ModelAttribute("NewMbrRegVO") NewMbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

        //2.[사용자]정보 CI&DN 취득
        NewMbrRegVO newMbrRegVO = newMbrRegService.selectMemberInfo(paramVO);
        
        
        if(newMbrRegVO != null){
        	log.debug("newMbrRegVO.getSearchAppId() = " +paramVO.getSearchAppId());
        	


            //사용자 정보 셋팅
            
        	SvcApplVO svcApplVO = new SvcApplVO();
        	svcApplVO.setCustomerRegNo(newMbrRegVO.getCustomerRegNo());
        	svcApplVO.setCreateId(newMbrRegVO.getCustomerRegNo());
        	
        	if(!OppfStringUtil.isEmpty(paramVO.getSearchAppId())){
        		svcApplVO.setAppId(paramVO.getSearchAppId());
                int result = svcApplService.insertFintechSvcDev(svcApplVO);
        	}
        	
        	
            
        	
            //사용자 정보 셋팅
            svcApplVO = new SvcApplVO();
            svcApplVO.setCustomerRegNo(newMbrRegVO.getCustomerRegNo());
            
            Map<String, Object> map = svcApplService.selectFintechSvcListDev(svcApplVO,paramVO);
            
    		model.addAttribute("resultSvcList", map.get("resultSvcList"));							//핀테크 서비스 목록
    		model.addAttribute("resultSvcPubcompanyList", map.get("resultSvcPubcompanyList"));		//핀테크 서비스 금투사 목록

     		if(!OppfStringUtil.isEmpty(paramVO.getSearchAppId())){
     			model.addAttribute("resultSvcAccountList", map.get("resultSvcAccountList"));			//핀테크 서비스 가상계좌 목록
     		} else {
     			model.addAttribute("resultSvcAccountList", null);			//핀테크 서비스 가상계좌 목록
     		}
        } 
	        
        model.addAttribute("resultVO", newMbrRegVO);
			
	    return "jsonView";
	}

    /**
     * @Method Name        : sptUserNewAccountAdd
     * @Method description : [어드민 일반회원 관리 :서비스연결현황]계좌 연결추가 페이지로 이동한다.
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/usr/sptUserNewAccountAdd.do",method = {RequestMethod.POST, RequestMethod.GET})
    private String sptUserNewAccountAdd(@ModelAttribute("SptUserServiceVO") SptUserServiceVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
    	//modelView
        String modelView = "apt/sptUsr/sptUserNewAccountAdd";
      //1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

        log.debug(paramVO.getSearchCustomerRegNo());
        log.debug(paramVO.getSearchServiceRegNo());
        //2.[사용자]정보 CI&DN 취득
        
        NewMbrRegVO regVo = new NewMbrRegVO();
        regVo.setCustomerRegNo(paramVO.getSearchCustomerRegNo());
        NewMbrRegVO newMbrRegVO = newMbrRegService.selectMemberInfo(regVo);
        model.addAttribute("resultVO", newMbrRegVO);
        model.addAttribute("serviceRegNo", paramVO.getSearchServiceRegNo());
        
        
        if(newMbrRegVO != null){
        	log.debug("newMbrRegVO.getSearchAppId() = " +paramVO.getSearchAppId());
        	
        	
            //사용자 정보 셋팅
            SvcApplVO svcApplVO = new SvcApplVO();
            svcApplVO.setCustomerRegNo(newMbrRegVO.getCustomerRegNo());
            

            if(!OppfStringUtil.isEmpty(paramVO.getSearchServiceRegNo())){
            	svcApplVO.setSearchServiceRegNo(paramVO.getSearchServiceRegNo());
            }
            
            Map<String, Object> map = svcApplService.selectFintechSvcListDev(svcApplVO,regVo);
            
    		model.addAttribute("resultSvcList", map.get("resultSvcList"));							//핀테크 서비스 목록
    		model.addAttribute("resultSvcPubcompanyList", map.get("resultSvcPubcompanyList"));		//핀테크 서비스 금투사 목록

    		model.addAttribute("resultSvcAccountList", map.get("resultSvcAccountList"));			//핀테크 서비스 가상계좌 목록
    		
        } 
	    
			
	    return modelView;
    }
	

	/**
     * @Method Name        : saveFintechSvcTerms
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 가상계좌 선택 정보를 저장한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/saveFintechSvcTermsDev.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String saveFintechSvcTermsDev(@RequestBody SptSvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        log.debug("saveFintechSvcTermsDev start");
        
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	log.debug("loginVO null");
            
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        String rsCd = "";
        //사용자 정보 셋팅
        //paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCreateId(paramVO.getCustomerRegNo());
        paramVO.setTermsPolicy(OppfProperties.getProperty("Globals.user.policy.terms.final"));
        
        log.debug("가상계좌 선택 정보를 저장한다.");
        // 가상계좌 선택 정보 저장
        int result = newMbrRegService.saveFintechSvcTerms(paramVO);
        
        
        //동의할 약관 리스트 조회
        Map<String, Object> map = newMbrRegService.selectSvcCompanyTermsConsntList(paramVO);
        List<SptCustomerCompanyTermsProfileVO> resultList = (List<SptCustomerCompanyTermsProfileVO>) map.get("resultList");

        //약관 리스트 추가
        paramVO.setSptCustomerCompanyTermsProfileVO(resultList);
        //사용자 정보 셋팅
        paramVO.setCreateId(paramVO.getCustomerRegNo());
        // 약관저장
        result = newMbrRegService.saveSvcCompanyTermsConsnt(paramVO);
                
        //사용자 정보 셋팅
        paramVO.setCreateId(paramVO.getCustomerRegNo());
        
        // 정보제공 동의 목록 조회
		Map<String, Object> svcTermConsntMap = newMbrRegService.selectSvcTermConsntList(paramVO);
		List<SptCustomerServiceAccountProfileVO> svcTermConsnList = (List<SptCustomerServiceAccountProfileVO>) svcTermConsntMap.get("resultList");
		
		if(svcTermConsnList != null){
			for(int i=0; i<svcTermConsnList.size(); i++){
				String termsRegNo = svcTermConsnList.get(i).getTermsRegNo();
				String termsAuthYn = svcTermConsnList.get(i).getTermsAuthYn();
				if("Y".equals(termsAuthYn)){ // termsAuthYn = 'N'인 경우에만 정보제공 동의


			        String tmpTermsRegNo = termsRegNo;
			        
			        if(!OppfStringUtil.isEmpty(tmpTermsRegNo)){
			        	tmpTermsRegNo = OppfStringUtil.base64Decoding(tmpTermsRegNo);
			        }
			        

			        //서비스 제공자 기업 id가 들어오면 해당 기업만보여준다.
			        String pubcompanyCodeId = OppfStringUtil.isNullToString(request.getParameter("pubcompanyCodeId"));

			        //1.로그인관련
			        String customerId = loginVO.getId();
			        //2.사용자 정보제공동의서 정보 DB조회
			        SptCustomerServiceTermsProfileVO pTermsVO = new SptCustomerServiceTermsProfileVO();
			        pTermsVO.setCustomerRegNo(paramVO.getCustomerRegNo());
			        pTermsVO.setTermsRegNo(termsRegNo);
			        pTermsVO.setTermsAuthYn(termsAuthYn);
			        pTermsVO.setTermsPolicyYear(OppfProperties.getProperty("Globals.user.policy.password.final"));
			        SptCustomerServiceTermsProfileVO rsTermsVO = svcApplService.selectSptCustomerServiceTermsProfile(pTermsVO);

			        /*
			        //3.사용자 정보제공동의서 기업정보 DB조회
			        SptCustomerServiceTermsPubcompanyProfileVO pPubcompanyVO = new SptCustomerServiceTermsPubcompanyProfileVO();
			        pPubcompanyVO.setCustomerRegNo(paramVO.getCustomerRegNo());
			        pPubcompanyVO.setTermsRegNo(termsRegNo);
			        pPubcompanyVO.setPubcompanyCodeId(pubcompanyCodeId);
			        List<SptCustomerServiceTermsPubcompanyProfileVO> rsPubcompanyList = svcApplService.selectSptCustomerServiceTermsPubcompanyProfileList(pPubcompanyVO);
			        */
			        //4.[사용자]정보 CI&DN 취득
			        String customerCi = "";
			        String customerDn = "";
			        MbrRegVO pMbrRegVO = new MbrRegVO();
			        pMbrRegVO.setCustomerRegNo(paramVO.getCustomerRegNo());
			        pMbrRegVO.setCustomerVerifyType("X");
			        List<MbrRegVO> rsVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(pMbrRegVO);
			        if(rsVerifyList != null){
			            for(int j=0; j<rsVerifyList.size(); j++){
			                String customerVerifyType = rsVerifyList.get(j).getCustomerVerifyType();
			                if("G007001".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
			                    customerCi = rsVerifyList.get(j).getCustomerVerify();
			                    
			                }else if("G007002".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
			                    customerDn = rsVerifyList.get(j).getCustomerVerify();
			                }
			            }
			        }
			        
			        log.debug("2.[사용자]정보 취득後:customerCi="+customerCi);
			        log.debug("2.[사용자]정보 취득後:customerDn="+customerDn);
			        
			        //5.본인인증단계(아이핀인증, 휴대폰인증)에서 저장한 사용자이름을 조회해 와서 공인인증등록시 공인인증서 DN값의 이름과 비교합니다.
			        MbrRegVO requestCustomerInfoVO = new MbrRegVO();
			        requestCustomerInfoVO.setCustomerRegNo(paramVO.getCustomerRegNo());
			        //MbrRegVO resultCustomerInfoVO = mbrRegService.selectSptCustomerInfoProfile(requestCustomerInfoVO);
			        

					CmmTsaVO cmmTsaVO = new CmmTsaVO();

					cmmTsaVO.setDn(customerDn);
					// 공인인증서 서명데이터 
					cmmTsaVO.setSignData(customerDn);
					// 의미없는값
					cmmTsaVO.setReqHtml("<html>");
					cmmTsaVO.setCustomerRegNo(paramVO.getCustomerRegNo());
					// 암호화한 TermsRegNo
					cmmTsaVO.setTermsRegNo(termsRegNo);
					//cmmTsaVO.setTermsRegNo(tmpTermsRegNo);
					cmmTsaVO.setTermsStartDate(rsTermsVO.getTermsStartDate());
					cmmTsaVO.setTermsPolicy(rsTermsVO.getTermsPolicy());
			        
			        //메일발송용 정보
					cmmTsaVO.setCustomerName(rsTermsVO.getCustomerName());
					cmmTsaVO.setCustomerEmail(rsTermsVO.getCustomerEmail());

					HashMap<String,Object> rsMap = newMbrRegService.procTsa(cmmTsaVO);

					
					model.addAttribute("rsCd", rsMap.get("rsCd"));			//핀테크 서비스 가상계좌 목록
		    		
				}
	        }
		}

		
	    return "jsonView";
	}
	
	
	
	

	/**
     * @Method Name        : saveFintechSvcTerms
     * @Method description : [핀테크서비스신청:핀테크서비스선택] 가상계좌 선택 정보를 저장한다.  
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/usr/svcAppl/deleteAccountSvcInfo.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String deleteAccountSvcInfo(@RequestBody SptSvcApplVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{

        log.debug("deleteAccountSvcInfo start");
        
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	log.debug("loginVO null");
            
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
/*
        if (OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())) {

        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        if (OppfStringUtil.isEmpty(paramVO.getServiceRegNo())) {

        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        if (OppfStringUtil.isEmpty(paramVO.getAccountRegNo())) {

        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        if (OppfStringUtil.isEmpty(paramVO.getTermsRegNo())) {

        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        int selCnt = newMbrRegService.selectCntSptCustomerServiceAccountProfile(paramVO);
        if(selCnt < 2){

        	model.addAttribute("selCnt", "1");
            model.addAttribute("rtn", 0);
            
    	    return "jsonView";
        }     
        */
        
        int rtn = newMbrRegService.deleteAccountSvcInfo(paramVO);
		
        model.addAttribute("rtn", rtn);
    	model.addAttribute("selCnt", "0");
        
	    return "jsonView";
	}
	
	
	
	
	
}
