package kr.co.koscom.oppf.cmm.web;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.oppf.cmm.service.AccListAttributeREQVO;
import kr.co.koscom.oppf.cmm.service.AccListREQVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplService;
import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;

@Controller
public class CmmSIFInternalController {
	@Resource(name = "MypSvcApplService")
    private MypSvcApplService mypSvcApplService;
    
    public CmmSIFInternalController(){
        
    }
    
    private static final Logger log = Logger.getLogger(CmmSIFInternalController.class);
    
    /**
     * @Method Name        : selectRealAccList
     * @Method description : [실계좌]조회
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(
        value="/cmm/sif/selectRealAccList.ajax"
       ,method = RequestMethod.GET)
    public ModelAndView selectRealAccList(
         HttpServletRequest request
        ,ModelMap model
        ,@RequestHeader(value="apiKey",required=false) String apiKey
        ,@RequestHeader(value="Authorization",required=false) String Authorization
        ,@RequestHeader(value="x-credential-userId") String requestUserId
        ,@RequestHeader(value="x-api-requestId")     String requestUniqueId
        ,@RequestHeader(value="x-credential-ci")     String userCi
        ,@RequestHeader(value="x-credential-dn",required=false) String userDn
        ,@RequestParam(required=true) String companyNameEngAlias
        ,@RequestParam(required=true) String accessToken
    ){
        log.info("------------- selectRealAccList START ---------------");
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        
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
        
//        if(OppfStringUtil.isEmpty(apiKey)){
//            apiKey
//        }
        
        // generate request header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apiKey", apiKey);
        httpHeaders.add("Authorization", Authorization);
        httpHeaders.add("x-credential-userId", requestUserId);
        httpHeaders.add("x-api-requestId", requestUniqueId);
        httpHeaders.add("x-credential-ci", userCi);
        if(!OppfStringUtil.isEmpty(userDn)){
            httpHeaders.add("x-credential-dn", userDn);
        }
//        httpHeaders.add("x-api-clientId","b");
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        
        String exconn = OppfProperties.getProperty("Globals.domain.exconn");
        //개발/샌드박스(2차통합)/상용 모드
        String devMode = OppfProperties.getProperty("Globals.integrated.account.mode");
        String authorizationUrl = null;
        //샌드박스(2차통합)인경우만 외부연계서버2로 분기처리
        //authorizationUrl = devMode.equals("snd") ? exconn+"/v2/common/account/number/"+companyNameEngAlias : exconn+"/v1/common/account/number/"+companyNameEngAlias;
        authorizationUrl = exconn+"/v1/common/account/number/"+companyNameEngAlias;

        log.debug("#############selectRealAccList.headers##################");
        log.debug("apiKey="+apiKey);
        log.debug("Authorization="+Authorization);
        log.debug("x-credential-userId="+requestUserId);
        log.debug("x-api-requestId="+requestUniqueId);
        log.debug("x-credential-ci="+userCi);
        log.debug("#############selectRealAccList.headers##################");
        
        
        log.debug("requestUserId="+requestUserId);
        log.debug("requestUniqueId="+requestUniqueId);
        log.debug("userCi="+userCi);
        log.debug("userDn="+userDn);
        log.debug("companyNameEngAlias="+companyNameEngAlias);
        log.debug("exconn="+exconn);
        log.debug("authorizationUrl="+authorizationUrl);
        
        // exchange
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);
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
            
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                
                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                    //System.out.println("hasError --> " + response.getStatusText());
                    return false;
                }
                
                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                    //System.out.println("handleError --> " + response.getStatusText());
                }
            });
            ResponseEntity<String> responseEntity = restTemplate.exchange(authorizationUrl, HttpMethod.GET, requestEntity, String.class);
            String strResBody = responseEntity.getBody();
            log.debug("ok:responseBody="+responseEntity.getBody());
            log.debug("ok:상태코드:"+responseEntity.getStatusCode().value());
            mav.addObject(strResBody);
            mav.addObject("statusCode", responseEntity.getStatusCode().value());
            log.info("------------- selectRealAccList END -----------------");
        }catch(KeyManagementException e){
            
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            
            e.printStackTrace();
        }catch(KeyStoreException e){
            e.printStackTrace();
        }
        return mav;
    }
    
    
    /**
     * @Method Name        : procAccInfo
     * @Method description : 가상계좌[발급,교체,폐기]전문요청 처리
     * @param              : SvcApplVO,ModelMap
     * @return             : String
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/sif/procAccInfo.ajax", method = RequestMethod.POST)
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
        
        //실계좌번호 복호화
        for(int i=0; i<account.size(); i++){
            String realAccNo = account.get(i).getRealAccNo();
            if(!OppfStringUtil.isEmpty(realAccNo)){
                realAccNo = OppfStringUtil.base64Decoding(realAccNo);
                log.debug("realAccNo="+realAccNo);
                account.get(i).setRealAccNo(realAccNo);
                
                customerRealaccountNo = realAccNo;
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
        log.debug("apiKey="+apiKey);
        log.debug("Authorization="+Authorization);
        log.debug("x-credential-userId="+requestUserId);
        log.debug("x-api-requestId="+requestUniqueId);
        log.debug("x-credential-ci="+userCi);
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
            			log.debug("status="+status);
            			log.debug("message="+message);
            			log.debug("accountStatus="+accountStatus);
            			log.debug("############# 가상계좌 폐기 data #################");
            			log.debug("customerRegNo="+requestUniqueId);
            			log.debug("companyCodeId="+companyCode);
            			log.debug("customerRealaccountNo="+customerRealaccountNo);
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

}
