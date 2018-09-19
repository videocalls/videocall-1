package kr.co.koscom.oppf.cmm.oauth.web;

import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenReqBodyVO;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenService;
import kr.co.koscom.oppf.cmm.oauth.service.ComOauthTokenVO;
import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuController.java
* @Comment  : 공통 OAuthToken정보 관리를 위한 Controller
* @author   : 이환덕
* @since    : 2016.06.21
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.21  이환덕        최초생성
*
*/
@Slf4j
@Controller
public class ComOauthTokenController {
    
    @Resource(name = "CmmFuncService")
    private CmmFuncService cmmFuncService;
    
    @Resource(name = "ComOauthTokenService")
    private ComOauthTokenService comOauthTokenService;

    /**
     * @Method Name        : getToken
     * @Method description : [공통:OAuthToken]정보를 취득한다.
     * @param              : request,response,model
     * @return             : ModelAndView
     * @throws             : Exception
     */
    @RequestMapping(value="/cmm/oauth/getToken.ajax",method = {RequestMethod.POST, RequestMethod.GET})
    private ModelAndView getToken(
        @ModelAttribute("ComOauthTokenVO") ComOauthTokenVO paramVO
       ,HttpServletRequest  request
       ,HttpServletResponse response
       ,ModelMap model
    )throws Exception{
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        
        //1.로그인관련 필터링
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
            mav.addObject("rsCd","01");
            mav.addObject("rsMsg","로그인후 이용해 주십시요.");
            return mav;
        }
        String customerRegNo = loginVO.getCustomer_reg_no();
        if(OppfStringUtil.isEmpty(customerRegNo)){
            mav.addObject("rsCd","01");
            mav.addObject("rsMsg","로그인후 이용해 주십시요.");
            return mav;
        }
        log.debug("customerRegNo : {} ", customerRegNo);
        
        //2.정적값셋팅
        String circumventYn = OppfProperties.getProperty("Globals.oauth.token.circumventYn"); //우회여부(Y:우회, N:우회안함)
        String clientId = OppfProperties.getProperty("Globals.oauth.token.clientId");
        String secretId = OppfProperties.getProperty("Globals.oauth.token.secretId");
        String scope = OppfProperties.getProperty("Globals.oauth.token.scope");
        String grantType = OppfProperties.getProperty("Globals.oauth.token.grantType");
        String Authorization = "Basic "+OppfStringUtil.base64Incoding(clientId+":"+secretId);
        log.debug("clientId : {} ", clientId);
        log.debug("secretId : {} ", secretId);
        log.debug("grantType : {} ", grantType);
        log.debug("Authorization : {} ", Authorization);
        
        //3.셋팅:헤더
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("apiKey", clientId);
        httpHeaders.add("Authorization", Authorization);
        
        //4.셋팅:url
        String url = OppfProperties.getProperty("Globals.oauth.token.url");
        
        //5.요청parameter설정
        ComOauthTokenReqBodyVO reqBodyVO = new ComOauthTokenReqBodyVO();
        reqBodyVO.setGrant_type(grantType);
        reqBodyVO.setClientId(clientId);
        reqBodyVO.setClientSecret(secretId);
        reqBodyVO.setScope(scope);
        
        //grant_type=client_credentials&clientId=l7xxb914cffcd0bd451091f42133b83e06a1&clientSecret=37a14fc8cf544f81b2bc71f8397918e4&scope=common.account
        String reqStr = "grant_type="+grantType+"&clientId="+clientId+"&clientSecret="+secretId+"&scope="+scope;
        
        //6.전송처리
        HttpEntity<String> requestEntity = new HttpEntity<String>(reqStr, httpHeaders);
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
                   // System.out.println("hasError --> " + response.getStatusText());
                    mav.addObject("rsCd","10");
                    mav.addObject("rsMsg","처리중 에러가 발생 하였습니다.");
                    return false;
                }
                
                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                    //System.out.println("handleError --> " + response.getStatusText());
                    mav.addObject("rsCd","10");
                    mav.addObject("rsMsg","처리중 에러가 발생 하였습니다.");
                }
            });
            ResponseEntity<ComOauthTokenVO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ComOauthTokenVO.class);
            ComOauthTokenVO responseBody = responseEntity.getBody();
            log.debug("ok:responseBody : {} ", responseEntity.getBody());
            log.debug("ok:access_token : {} ", responseBody.getAccess_token());
            log.debug("ok:token_type : {} ", responseBody.getToken_type());
            log.debug("ok:expires_in : {} ", responseBody.getExpires_in());
            log.debug("ok:scope : {} ", responseBody.getScope());
            
            //6.DB등록
            ComOauthTokenVO pComOauthTokenVO = new ComOauthTokenVO();
            pComOauthTokenVO.setCustomerRegNo(customerRegNo);
            pComOauthTokenVO.setClientId(clientId);
            pComOauthTokenVO.setSecretId(secretId);
            pComOauthTokenVO.setScope(responseBody.getScope());
            pComOauthTokenVO.setAccessToken(responseBody.getAccess_token());
            pComOauthTokenVO.setTokenType(responseBody.getToken_type());
            String expiresIn = String.valueOf(responseBody.getExpires_in());
            pComOauthTokenVO.setExpiresIn(expiresIn);
            String regNo = comOauthTokenService.insertComOauthToken(pComOauthTokenVO);
            log.debug("DB등록:regNo : {} ", regNo);
            
            //8.DB등록후데이터취득
            pComOauthTokenVO = new ComOauthTokenVO();
            pComOauthTokenVO.setRegNo(regNo);
            ComOauthTokenVO rsComOauthTokenVO = comOauthTokenService.selectComOauthTokenInfo(pComOauthTokenVO);
            log.debug("DB조회:accessToken : {} ", rsComOauthTokenVO.getAccessToken());
            
            //9.취득후 response 셋팅
            mav.addObject("rsData",rsComOauthTokenVO);
            mav.addObject("rsCd","00");
            mav.addObject("rsMsg","정상적으로 처리되었습니다.");
            
        }catch(KeyManagementException e){
            e.printStackTrace();
            mav.addObject("rsCd","10");
            mav.addObject("rsMsg","처리중 에러가 발생 하였습니다.");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            mav.addObject("rsCd","10");
            mav.addObject("rsMsg","처리중 에러가 발생 하였습니다.");
        }catch(KeyStoreException e){
            e.printStackTrace();
            mav.addObject("rsCd","10");
            mav.addObject("rsMsg","처리중 에러가 발생 하였습니다.");
        }
        return mav;
    }

}
