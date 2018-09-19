package kr.co.koscom.oppfm.oauth.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppfm.cmm.eversafe.EversafeKeypadUtil;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.exception.ExternalInterfaceException;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.RestTemplateUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.member.dao.MemberMapper;
import kr.co.koscom.oppfm.oauth.dao.OauthMapper;
import kr.co.koscom.oppfm.oauth.model.OauthAppReq;
import kr.co.koscom.oppfm.oauth.model.OauthAppRes;
import kr.co.koscom.oppfm.oauth.model.OauthScopeReq;
import kr.co.koscom.oppfm.oauth.model.OauthTokenRes;
import kr.co.koscom.oppfm.oauth.model.OauthUserReq;
import kr.co.koscom.oppfm.oauth.service.OauthService;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName : OauthServiceImpl
 *
 * Description : Oauth Service
 *
 * Created by LHT on 2017. 5. 16..
 */
@Slf4j
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private OauthMapper oauthMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Value("#{config['Globals.oauth.authorize.url']}")
    String oauthLoginUrl;
    @Value("#{config['Globals.oauth.authentication.url']}")
    String authenticationUrl;
    @Value("#{config['Globals.oauth.token.url']}")
    String tokenUrl;
    @Value("#{config['Globals.oauth.authentication.requestId']}")
    String oauthRequestId;

    @Value("#{config['Globals.integrated.account.apiKey']}")
    String intAccountApiKey;
    @Value("#{config['Globals.integrated.account.secretId']}")
    String intAccountSecretId;
    @Value("#{config['Globals.integrated.account.callBackUrl']}")
    String intAccountCallbackUrl;
    @Value("#{config['Globals.integrated.account.grantType']}")
    String intAccountGrantType;
    @Value("#{config['Globals.integrated.account.scope']}")
    String intAccountScope;
    @Value("#{config['Globals.spt.domain']}")
    String sptDomain;

    private static final String AUTHENTICATE_SUCCESS = "200";

    /**
     * getOauthInfo
     *
     * @param oauthAppReq
     */
    @Override
    public CommonResponse getOauthInfo(OauthAppReq oauthAppReq) {

        Map<String, Object> body = new HashMap<>();
        OauthAppRes oauthAppRes = oauthMapper.selectSvcAppInfo(oauthAppReq);
        if(null != oauthAppRes){
            createSessionId(oauthAppReq, oauthAppRes);
            oauthAppRes.setAppImg(sptDomain + "/cmm/appImg/" + oauthAppRes.getAppId() + ".do");
        }else{
            //App NOT FOUND
            throw new CommonException(ErrorCodeConstants.FAIL_OAUTH_APP, null);
        }

        body.put("data", oauthAppRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getAuthenticate
     *
     * @param oauthUserReq
     */
    @Override
    public CommonResponse getAuthenticate(OauthUserReq oauthUserReq, HttpServletResponse response) {

        String pwdText = "";
        try {
            pwdText = EversafeKeypadUtil.RSADecoderSecureText(oauthUserReq.getPassword());
        } catch (GeneralSecurityException e) {
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_USER_ID, null);
        }
        //인증서버 헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // CA 공유 requestId
        httpHeaders.add("x-api-requestId", oauthRequestId);
        // 호출한 클라이언트 Id
        httpHeaders.add("x-api-clientId", oauthUserReq.getClientId());

        //인증서버 authenticationUrl
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("userId", oauthUserReq.getUserId());
        payloadJson.put("password", pwdText);

        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(authenticationUrl, httpHeaders, HttpMethod.POST, payloadJson.toString());
        JSONObject responseJson = new JSONObject(responseEntity.getBody());
        LoginRes loginRes = new LoginRes();
        //인증 성공인 경우 로그인 쿠키 생성
        if(AUTHENTICATE_SUCCESS.equals(responseJson.get("code").toString())){
            loginRes = memberMapper.selectMember(oauthUserReq.getUserId());
            if(null != loginRes){
			/*로그인 쿠키 생성(공통)*/
                CookieUtil.createLoginCookie(loginRes, response);
            }else{
                throw new CommonException(ErrorCodeConstants.NOT_FOUND_USER_ID, null);
            }
        }

        Map<String, Object> body = new HashMap<>();
        body.put("data", responseJson.toMap());
        body.put("loginRes", loginRes);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * createScope
     *
     * @param oauthScopeReq
     */
    @Transactional
    @Override
    public CommonResponse createScope(OauthScopeReq oauthScopeReq) {

        String customerRegNo = oauthMapper.selectCustomerRegNo(oauthScopeReq.getUserId());
        oauthScopeReq.setCustomerRegNo(customerRegNo);
        //회원 Provider 약관 동의 프로파일 등록
        oauthMapper.insertOauthTermsProfile(oauthScopeReq);
        // scope 이력
        String[] scopes = oauthScopeReq.getScope().split(" ");
        for(String scope : scopes){
            oauthScopeReq.setScope(scope);
            // scope 이력 등록
            oauthMapper.insertOauthScopeProfile(oauthScopeReq);
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }

    /**
     * createAuthorization
     *
     * @param oauthScopeReq
     */
    @Override
    public CommonResponse createAuthorization(OauthScopeReq oauthScopeReq) {

        //헤더정보
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //authorize Code 요청
        String authenticationUrl = oauthLoginUrl;
        String payload = "sessionID="+oauthScopeReq.getSessionId()+"&username="+oauthScopeReq.getUserId()+"&action=Grant";

        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(authenticationUrl, httpHeaders, HttpMethod.POST, payload);

        if(HttpStatus.FOUND.equals(responseEntity.getStatusCode())){
            log.info("++++++++++++++++++++++++++++++++++++ CallBack : {} ", responseEntity.getHeaders() );
            // APP callback URL 정보
            String location = String.valueOf(responseEntity.getHeaders().getLocation());

            CloseableHttpClient httpclient = getHttpClinet();
            try {
                // APP callback URL로 authorize Code 전송
                httpclient.execute(new HttpGet(location));
            } catch (IOException e) {
                log.error("++++++++++++++++++++++++++++++++++++ authorize code CallBack Error : {} ", e.getMessage() );
                throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_CALLBACK, new String[] {e.getMessage()}, e.getMessage());
            }

        }else{
            log.info("++++++++++++++++++++++++++++++++++++ authorize failed : {} ", responseEntity.getBody() );
            throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_AUTHORIZATION, null, responseEntity.toString());
        }

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }

    /**
     * createToken
     *
     * @param user
     */
    @Override
    public CommonResponse createToken(LoginRes user) {
        // #1 G/W OAuth 정보 조회
        // #2 OAuth 인증 이력 등록
        // #3 Authorization code 요청
        // #4 Access Token 발급
        Map<String, Object> body = new HashMap<>();

        OauthAppReq oauthAppReq = new OauthAppReq();
        oauthAppReq.setResponseType(user.getResponseType());
        oauthAppReq.setClientId(intAccountApiKey);
        oauthAppReq.setCallbackUrl(intAccountCallbackUrl);
        oauthAppReq.setScope(intAccountScope);
        //#1 G/W OAuth 정보 조회 (sessionID 획득)
        OauthAppRes oauthAppRes = new OauthAppRes();
        createSessionId(oauthAppReq, oauthAppRes);

        // #2 OAuth 인증 이력 등록
        OauthScopeReq oauthScopeReq = new OauthScopeReq();
        oauthScopeReq.setCustomerRegNo(user.getCustomerRegNo());
        oauthScopeReq.setClientId(intAccountApiKey);
        oauthScopeReq.setSessionId(oauthAppRes.getSessionId());
        oauthScopeReq.setScope(intAccountScope);
        //회원 Provider 약관 동의 프로파일 등록
        oauthMapper.insertOauthTermsProfile(oauthScopeReq);
        // scope 이력
        String[] scopes = oauthScopeReq.getScope().split(" ");
        for(String scope : scopes){
            oauthScopeReq.setScope(scope);
            // scope 이력 등록
            oauthMapper.insertOauthScopeProfile(oauthScopeReq);
        }

        // #3 Authorization code 요청
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //authorize Code 요청
        String authenticationUrl = oauthLoginUrl;
        String codePayload = "sessionID="+oauthAppRes.getSessionId()+"&username="+user.getCustomerId()+"&action=Grant";

        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity<String> responseEntity = restTemplateUtil.sendRestTemplate(authenticationUrl, httpHeaders, HttpMethod.POST, codePayload);

        if(HttpStatus.FOUND.equals(responseEntity.getStatusCode())){
            log.info("++++++++++++++++++++++++++++++++++++ CallBack : {} ", responseEntity.getHeaders() );
            // APP callback URL 정보
            String location = String.valueOf(responseEntity.getHeaders().getLocation());
            String[] locations = location.split("&");
            String authorizationCode = locations[0].substring( locations[0].indexOf("=") + 1, locations[0].length());
            String tokenPayload = "code="+authorizationCode+"&client_id="+intAccountApiKey+"&client_secret="+intAccountSecretId+"&redirect_uri="+intAccountCallbackUrl+"&grant_type="+intAccountGrantType;
            // #4 Access Token 발급
            responseEntity = restTemplateUtil.sendRestTemplate(tokenUrl, httpHeaders, HttpMethod.POST, tokenPayload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                String access_token = jsonObject.getString("access_token");
                OauthTokenRes oauthTokenRes = new OauthTokenRes();
                oauthTokenRes.setAccessToken(access_token);
                oauthTokenRes.setTokenType(jsonObject.getString("token_type"));
                oauthTokenRes.setExpiresIn(String.valueOf(jsonObject.getInt("expires_in")));
                oauthTokenRes.setRefreshToken(jsonObject.getString("refresh_token"));
                oauthTokenRes.setRspScope(jsonObject.getString("scope"));
                oauthTokenRes.setCustomerRegNo(user.getCustomerRegNo());
                //OauthToken 정보 초기화
                oauthMapper.deleteCustomerAcntOauthToken(oauthTokenRes);
                //OauthToken 정보 등록
                oauthMapper.insertCustomerAcntOauthToken(oauthTokenRes);

                Map<String, String> token = new HashMap<>();
                token.put("access_token", access_token);
                body.put("data", token);
                log.debug("++++++++++++++++++++++++++++++++++++ ACCESS_TOKEN : {} ", access_token );
            }else{
                log.debug("++++++++++++++++++++++++++++++++++++ token request  failed : {} ", responseEntity.getBody() );
                throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_TOKEN, null, responseEntity.toString());
            }
        }else{
            log.info("++++++++++++++++++++++++++++++++++++ authorize failed : {} ", responseEntity.getBody() );
            throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_AUTHORIZATION, null, responseEntity.toString());
        }


        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getAppCheck
     *
     * @param oauthUserReq
     */
    @Override
    public CommonResponse getAppCheck(OauthUserReq oauthUserReq) {

        Map<String, Object> body = new HashMap<>();
        int useCnt = oauthMapper.selectCustomerAppCheck(oauthUserReq);
        body.put("data", useCnt == 1 ? "Y" : "N");

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * getOtpCheck
     *
     * @param userId
     */
    @Override
    public CommonResponse getOtpCheck(String userId) {

        Map<String, Object> body = new HashMap<>();
        int useCnt = oauthMapper.selectOtpCheck(userId);
        body.put("data", useCnt == 1 ? "Y" : "N");

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    private  OauthAppRes createSessionId(OauthAppReq oauthAppReq, OauthAppRes oauthAppRes){

        String oauthUrl = getOauthUIrl(oauthAppReq);
        String location = null;
        log.info("Create sessionId Url : " + oauthUrl);
        CloseableHttpClient httpclient = getHttpClinet();
        try {
            HttpResponse response = httpclient.execute(new HttpGet(oauthUrl));
            if(HttpStatus.FOUND.value() == response.getStatusLine().getStatusCode()){
                location = response.getFirstHeader("Location").getValue();
                //https://10.10.10.103:8440/spt/cmm/authorize?sessionID=6f769d6a-519c-4bb4-8788-2e836b80a076&clientId=l7xx296eb0af278542b38a6ebd5073749ce0&scope=account profile
                String[] locations = location.split("&");
                String sessionId = null;
                String clientId = null;
                String scope = null;

                int idx = 0;
                for(String vo :  locations){
                    if(idx == 0)
                        sessionId = vo.substring( vo.indexOf("=") + 1, vo.length());
                    else if(idx == 1)
                        clientId = vo.substring( vo.indexOf("=") + 1, vo.length());
                    else
                        scope = vo.substring( vo.indexOf("=") + 1, vo.length());
                    idx++;
                }

                oauthAppRes.setSessionId(sessionId);
                oauthAppRes.setClientId(clientId);
                oauthAppRes.setScope(scope);

                log.info("++++++++++++++++++++++++++++++++++++ response : ", response.toString() );
            }else{
                throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_SESSIONID, null, response.toString());
            }
        }catch (ClientProtocolException e) {
            log.error("++++++++++++++++++++++++++++++++++++ OAuth Info ClientProtocolException Error : {} ", e.getMessage() );
            throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_EXCEPTION, new String[] {e.getMessage()}, e.getMessage());
        } catch (IOException e) {
            log.error("++++++++++++++++++++++++++++++++++++ OAuth Info IOException Error : {} ", e.getMessage() );
            throw new ExternalInterfaceException(ErrorCodeConstants.FAIL_OAUTH_EXCEPTION, new String[] {e.getMessage()}, e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("++++++++++++++++++++++++++++++++++++ httpclient close Error : {} ", e.getMessage() );
            }
        }

        return oauthAppRes;
    }

    private  String getOauthUIrl(OauthAppReq oauthAppReq){
        String responseType = null != oauthAppReq.getResponseType() && !"".equals(oauthAppReq.getResponseType()) ? oauthAppReq.getResponseType() : "code";
        String state = null != oauthAppReq.getState() && !"".equals(oauthAppReq.getState()) ? oauthAppReq.getState() : "";
        String oauthUrl = oauthLoginUrl + "?response_type=";
        oauthUrl += responseType + "&client_id=";
        oauthUrl += oauthAppReq.getClientId() + "&redirect_uri=";
        oauthUrl += oauthAppReq.getCallbackUrl() + "&scope=";
        oauthUrl += oauthAppReq.getScope() + "&state=";
        oauthUrl += state;
        oauthUrl = oauthUrl.replaceAll(" ","%20");
        return oauthUrl;
    }

    private CloseableHttpClient getHttpClinet(){
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                    .useProtocol("SSL")
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
                            return true;
                        }
                    }).build();
        } catch (Exception e) {
            log.error("++++++++++++++++++++++++++++++++++++ CloseableHttpClient Create Error : {} ", e.getMessage() );
        }
        httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier()).setSslcontext(sslcontext);
        CloseableHttpClient httpclient = httpClientBuilder.disableRedirectHandling().build();

        return httpclient;
    }
}
