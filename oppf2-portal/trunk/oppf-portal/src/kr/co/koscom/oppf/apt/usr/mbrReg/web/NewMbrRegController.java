package kr.co.koscom.oppf.apt.usr.mbrReg.web;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegService;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.NewMbrRegVO;
import kr.co.koscom.oppf.apt.usr.mbrReg.service.impl.NewMbrRegDAO;
import kr.co.koscom.oppf.cmm.service.AccListAttributeREQVO;
import kr.co.koscom.oppf.cmm.service.AccListREQVO;
import kr.co.koscom.oppf.cmm.service.CmmEmailSendService;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import kr.co.koscom.oppf.cmm.service.CmmNiceIpinCheckplusService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.CommonUtil;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.TermsVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축
 * @FileName : NewMbrRegController.java
 * @Comment : [회원생성]정보관리를 위한 Controller 클래스
 * @author :
 * @since :
 * @version : 1.0
 * @see
 *
 * 		<< 개정이력(Modification Information) >> 수정일 수정자 수정내용 ----------- ------
 *      ----------
 * 
 *
 */
@Slf4j
@Controller
public class NewMbrRegController {

	@Resource(name = "CmmFuncService")
	private CmmFuncService cmmFuncService;

	@Resource(name = "NewMbrRegService")
	private NewMbrRegService newMbrRegService;

	@Resource(name = "CmmEmailSendService")
	private CmmEmailSendService cmmEmailSendService;

	@Resource(name = "CmmNiceIpinCheckplusService")
	private CmmNiceIpinCheckplusService cmmNiceIpinCheckplusService;

	@Resource(name = "NewMbrRegDAO")
	private NewMbrRegDAO newMbrRegDAO;
	
    @Resource(name = "MbrRegService")
    private MbrRegService mbrRegService;
    
	@Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;

	/* 동기식.do 요청 START */

	CmmFuncVO cmmFuncVO = new CmmFuncVO();

	/**
	 * @Method Name : mbrRegReg
	 * @Method description : [회원가입]페이지로 이동한다.
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : String
	 * @throws :
	 *             Exception
	 */
	@RequestMapping(value = "/apt/usr/mbrNewReg.do", method = { RequestMethod.POST, RequestMethod.GET })
	private String mbrReg(@ModelAttribute("mbrRegVO") NewMbrRegVO paramVO, HttpServletRequest request, ModelMap model)
			throws Exception {

		// 1.로그인관련
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null) {
			return "apt/sptUsr/sptUserManageDtl";
		}

		// 셋팅 공통코드:이메일
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G013");// 이메일
		List<CmmFuncVO> emailList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("emailList", emailList);

		// 셋팅 공통코드:휴대폰번호
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G011");// 휴대폰
		List<CmmFuncVO> hpList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("hpList", hpList);

		// 셋팅 공통코드:성별
		cmmFuncVO = new CmmFuncVO();
		cmmFuncVO.setSystem_grp_code("G012");// 성별
		List<CmmFuncVO> sexList = cmmFuncService.selectCmmnFuncCode(cmmFuncVO);
		model.addAttribute("sexList", sexList);

		return "/apt/usr/mbrNewReg";

	}

	/**
	 * @Method Name : saveNewMbrReg
	 * @Method description : [회원가입]정보를 저장을 한다.(테스트)
	 * @param :
	 *            MbrRegVO,ModelMap
	 * @return : jsonView
	 * @throws :
	 *             Exception
	 */
	@RequestMapping("/apt/usr/saveNewMbrReg.ajax")
	private String saveNewMbrReg(@ModelAttribute("newMbrRegVO") NewMbrRegVO paramVO, HttpServletRequest request,
			ModelMap model) throws Exception {

		log.debug("------------- saveNewMbrReg.ajax START -------------------");

		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		if (loginVO == null) {
			model.addAttribute("error", "-1");
			return "jsonView";
		}
		NewMbrRegVO newMbrRegVO = new NewMbrRegVO();
		
		if("Y".equals(paramVO.getCiCustomerVerifyStatus()) ){
			
			newMbrRegVO.setCustomerVerify(paramVO.getCiCustomerVerify());
			newMbrRegVO.setCustomerVerifyType("G007001");
			
			// 인증값 중복 체크
			int resultCnt = newMbrRegService.sptUserVerifyDupChk(newMbrRegVO);
			if(resultCnt > 1){
	        	model.addAttribute("ciDupYn", "Y");
	        } else {
	        	model.addAttribute("ciDupYn", "N");
	        }
		}
		
		if("Y".equals(paramVO.getDnCustomerVerifyStatus()) ){
			
			newMbrRegVO = new NewMbrRegVO();
	
			newMbrRegVO.setCustomerVerify(paramVO.getDnCustomerVerify());
			newMbrRegVO.setCustomerVerifyType("G007002");

			// 인증값 중복 체크
			int resultCnt = newMbrRegService.sptUserVerifyDupChk(newMbrRegVO);
						
	        if(resultCnt > 1){
	        	model.addAttribute("dnDupYn", "Y");
	        } else {
	        	model.addAttribute("dnDupYn", "N");
	        }
			
		}
		
		log.debug("paramVO.getIntegrationAccountYn()= []",paramVO.getIntegrationAccountYn());
		paramVO.setIntegrationAccountYn(OppfProperties.getProperty("Globals.integration.account.yn"));
		
		int rs = newMbrRegService.saveNewMbrReg(paramVO);
		model.addAttribute("result", rs);

		log.debug("------------- saveNewMbrReg.ajax END ---------------------");
		return "jsonView";
		
	}

	/**
     * @Method Name        : updateSptUserManageDtlDev
     * @Method description : 일반회원 정보 수정 개발계
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/usr/updateSptUserManageDtlDev.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String updateSptUserManageDtlDev(@ModelAttribute("NewMbrRegVO") NewMbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }
        
        //사용자 regNo가 없는경우
        if(OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())){
        	model.addAttribute("user", "-1");
        	return "jsonView";
        }
        
        //System.out.println("paramVO.getCustomerDatasetLockYn()====> "+paramVO.getCustomerDatasetLockYn());
        
        //회원정보 수정
  		int result = newMbrRegService.updateSptUserManageDtlDev(paramVO);		 		
						
  		model.addAttribute("result", result);
	    return "jsonView";
	}
	

	/**
     * @Method Name        : selectMemberInfo
     * @Method description : [기본]회원정보를 조회한다.
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/usr/selectMemberInfo.ajax",method = {RequestMethod.POST, RequestMethod.GET})
	private String selectMemberInfo(@ModelAttribute("NewMbrRegVO") NewMbrRegVO paramVO, HttpServletRequest request, ModelMap model)throws Exception{
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return "jsonView";
        }

        //2.[사용자]정보 CI&DN 취득
        String customerCi = "";
        String customerDn = "";
        NewMbrRegVO newMbrRegVO = newMbrRegService.selectMemberInfo(paramVO);
        
        if(newMbrRegVO != null){
	        MbrRegVO pMbrRegVO = new MbrRegVO();
	        pMbrRegVO.setCustomerRegNo(newMbrRegVO.getCustomerRegNo());
	        pMbrRegVO.setCustomerVerifyType("");
	        List<MbrRegVO> rsVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(pMbrRegVO);
	        if(rsVerifyList != null){
	            for(int i=0; i<rsVerifyList.size(); i++){
	                String customerVerifyType = rsVerifyList.get(i).getCustomerVerifyType();
	                if("G007001".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
	                    customerCi = rsVerifyList.get(i).getCustomerVerify();
	                    
	                }else if("G007002".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
	                    customerDn = rsVerifyList.get(i).getCustomerVerify();
	                }
	            }
	        }
	    }
	        
       // System.out.println("2.[사용자]정보 취득後:customerCi="+customerCi);
       // System.out.println("2.[사용자]정보 취득後:customerDn="+customerDn);
        model.addAttribute("customerCiVO", customerCi);
        model.addAttribute("customerDnVO", customerDn);
        model.addAttribute("resultVO", newMbrRegVO);
				
	    return "jsonView";
	}
	

    /**
     * @Method Name        : procAccInfo
     * @Method description : 가상계좌[발급,교체,폐기]전문요청 처리
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/apt/usr/procAccInfo.ajax", method = RequestMethod.POST)
    public ModelAndView procAccInfo(@RequestBody AccListAttributeREQVO paramVO, ModelMap model)throws Exception{
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        
        String companyNameEngAlias = paramVO.getCompanyNameEngAlias();
        String trCode = paramVO.getTrCode();
        String companyCode = paramVO.getCompanyCode();
        List<AccListREQVO> account = paramVO.getAccount();
        String requestUserId = paramVO.getRequestUserId();
        String requestUniqueId = paramVO.getRequestUniqueId();
        String userCi = paramVO.getUserCi();
        String userDn = paramVO.getUserDn();
        String apiKey = paramVO.getApiKey();
        String Authorization = paramVO.getAuthorization();
        String accessToken = paramVO.getAccessToken();
        
        String customerRealaccountNo = "";
        

        if(OppfStringUtil.isEmpty(paramVO.getRequestUniqueId()) ||
        		OppfStringUtil.isEmpty(paramVO.getUserCi())){
        	MbrRegVO mbrRegVO = mbrRegService.selectSptCustomerCiInfo(paramVO);
        	if(mbrRegVO != null){
	        	userCi=mbrRegVO.getUserCi();
	        	requestUniqueId=mbrRegVO.getCustomerRegNo();
        	}
        } 
        
        //실계좌번호 복호화
        for(int i=0; i<account.size(); i++){
            String realAccNo = account.get(i).getRealAccNo();

            if(OppfStringUtil.isEmpty(account.get(i).getVtAccNo())){
	        	if("ISS".equals(paramVO.getTrCode())){	
	        		account.get(i).setVtAccNo(CommonUtil.generateVtAccountNo());
	        	}
            }
        	
            
            if("DIS".equals(paramVO.getTrCode())){	
            	log.debug("realAccNo : {} ", realAccNo);
                account.get(i).setRealAccNo(realAccNo);
            } else {
	            if(!OppfStringUtil.isEmpty(realAccNo)){
	                realAccNo = OppfStringUtil.base64Decoding(realAccNo);
	                log.debug("realAccNo : {} ", realAccNo);
	                account.get(i).setRealAccNo(realAccNo);
	                
	                customerRealaccountNo = realAccNo;
	            } else {
	            	realAccNo = CommonUtil.generateRealAccountNo();
	            	log.debug("realAccNo : {} ", realAccNo);
	                account.get(i).setRealAccNo(realAccNo);
	                
	                customerRealaccountNo = realAccNo;
	            }
            }
        }
        
        //oauthToken 값처리
        String clientId = OppfProperties.getProperty("Globals.oauth.token.clientId");
        String secretId = OppfProperties.getProperty("Globals.oauth.token.secretId");
        if(OppfStringUtil.isEmpty(apiKey)){
            apiKey = clientId;
        }
        if(OppfStringUtil.isEmpty(Authorization)){
//            Authorization = "Bearer "+OppfStringUtil.base64Incoding(clientId+":"+secretId);
            Authorization = "Bearer "+accessToken;
        }
        
        // generate request header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apiKey", apiKey);
        httpHeaders.add("Authorization", Authorization);
        httpHeaders.add("x-credential-userId", requestUserId);
        httpHeaders.add("x-api-requestId", requestUniqueId);
        httpHeaders.add("x-credential-ci", userCi);
        httpHeaders.add("x-credential-dn", userDn);
//        httpHeaders.add("x-api-clientId","b");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        
        log.debug("#############selectRealAccList.headers##################");
        log.debug("apiKey : {} ", apiKey);
        log.debug("Authorization : {} ", Authorization);
        log.debug("x-credential-userId : {} ", requestUserId);
        log.debug("x-api-requestId : {} ", requestUniqueId);
        log.debug("x-credential-ci : {} ", userCi);
        log.debug("#############selectRealAccList.headers##################");
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
		//개발/샌드박스(2차통합)/상용 모드
		String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");
		String authorizationUrl = null;
		
		//샌드박스(2차통합)인경우만 외부연계서버2로 분기처리
		//authorizationUrl = devMode.equals("snd") ? exconn+"/v2/common/account/vtnumber/"+companyNameEngAlias : exconn+"/v1/common/account/vtnumber/"+companyNameEngAlias;
		authorizationUrl = exconn+"/v1/common/account/vtnumber/"+companyNameEngAlias;
		
        ObjectMapper objMapper = new ObjectMapper();
        
        // exchange
        HttpEntity<String> requestEntity = new HttpEntity<String>("{\"account\":["+objMapper.writeValueAsString(account.get(0))+"],\"trCode\":\""+trCode+"\"}", httpHeaders);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        SSLContextBuilder builder = new SSLContextBuilder();
        
        try{
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslsf)
                    .build();
            
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(cm)
                    .build();
            requestFactory.setHttpClient(httpclient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            
            ResponseEntity<String> responseEntity = null;
            
            
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                    return false;
                }
                
                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                }
            });

            responseEntity = restTemplate.exchange(authorizationUrl, HttpMethod.POST, requestEntity, String.class);

            // handle response
            String responseBody = responseEntity.getBody();
           // System.out.println("ok responseBody="+responseBody);
            mav.addObject(responseBody);
            
            //System.out.println("ok:상태코드:"+responseEntity.getStatusCode().value());
            mav.addObject("statusCode", responseEntity.getStatusCode().value());
                  
            //삭제 일 경우
            if("DIS".equals(trCode)){
            	//결과 body 가져오기
            	JSONObject jsonBody = new JSONObject(responseBody);	
            	String statusCode = Integer.toString(responseEntity.getStatusCode().value());
            	
            	if("200".equals(statusCode) && jsonBody != null){
            		JSONObject json = jsonBody.getJSONObject("result");
            		JSONArray jsonAccount = jsonBody.getJSONArray("account");
            		if(json != null && jsonAccount != null){
            			String status = (String) json.get("status");
            			String message = (String) json.get("message");
            			
            			JSONObject jsonAccountObj = (JSONObject) jsonAccount.get(0);
            			String accountStatus = (String) jsonAccountObj.get("status");
            			
            			log.debug("############# 가상계좌 폐기 처리 상태 ################");
            			log.debug("status : {} ", status);
            			log.debug("message : {} ", message);
            			log.debug("accountStatus : {} ", accountStatus);
            			log.debug("############# 가상계좌 폐기 data #################");
            			log.debug("customerRegNo : {} ", requestUniqueId);
            			log.debug("companyCodeId : {} ", companyCode);
            			log.debug("customerRealaccountNo : {} ", customerRealaccountNo);
            			log.debug("##############################################");
            			
            			//성공 시에만 처리
            			//if("SUCCESS".equals(status) && "OK".equals(message)){
            			if("SUCCESS".equals(status) && !("FAILURE".equals(accountStatus) || "FAILED".equals(accountStatus))){
            				MypSvcApplVO dataVO = new MypSvcApplVO();
            				dataVO.setCustomerRegNo(requestUniqueId);
            				dataVO.setCompanyCodeId(companyCode);
            				dataVO.setCustomerRealaccountNo(customerRealaccountNo);
            				
            				//가상계좌 폐기에 따른 서비스 or 정보제공동의 정보 수정
            				int reuslt = mypSvcApplService.deleteCustomerAccountProc(dataVO);
            				mav.addObject("disResult", reuslt);
            			}
            		}
            	}
            }
            
        }catch(KeyManagementException e){
            
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            
            e.printStackTrace();
        }catch(KeyStoreException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace(System.out);
            
        }
        return mav;
    }


	/**
	 * @Method Name : saveNewMbrReg
	 * @Method description : [회원가입]정보를 저장을 한다.(테스트)
	 * @param : MbrRegVO,ModelMap
	 * @return : jsonView
	 * @throws :
	 *             Exception
	 */

    @RequestMapping(value="/apt/usr/commonTerms.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private String commonTerms(@ModelAttribute("TermsVO") TermsVO paramVO, HttpServletRequest request, ModelMap model) throws Exception {

        String customerRegNo = paramVO.getCustomerRegNo();

        //2.[사용자]정보 CI&DN 취득
        String customerCi = "";
        String customerDn = "";
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerRegNo(customerRegNo);
        pMbrRegVO.setCustomerVerifyType("X");
        List<MbrRegVO> rsVerifyList = mbrRegService.selectSptCustomerVerifyProfileList(pMbrRegVO);
        if(rsVerifyList != null){
            for(int i=0; i<rsVerifyList.size(); i++){
                String customerVerifyType = rsVerifyList.get(i).getCustomerVerifyType();
                if("G007001".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
                    customerCi = rsVerifyList.get(i).getCustomerVerify();
                    
                }else if("G007002".equals(customerVerifyType)){ //G007:회원검증종류(001:본인인증,002;공인인증서등록)
                    customerDn = rsVerifyList.get(i).getCustomerVerify();
                }
            }
        }
        
        if(OppfStringUtil.isEmpty(customerRegNo)){
            model.addAttribute("error", "-1");
            return "jsonView";
        }
        
        paramVO.setCustomerRegNo(customerRegNo);
        paramVO.setCustomerSignDn(customerDn);
        paramVO.setCustomerSignData(CodeConstants.TEST_PROFILE_MESSAGE);

        String rsCd = newMbrRegService.createCommonTermsTestMember(paramVO);

        model.addAttribute("rsCd", rsCd);

        return "jsonView";
    }

}
