package kr.co.koscom.oppf.cmm.service;

import com.google.gson.Gson;
import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2017-02-09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:resources/spring/appServlet/servlet-context.xml"})
public class CmmSIFInternalServiceTest {
    //test 통합계좌서비스 API KEY
    private static final String TEST_APIKEY = "l7xxb914cffcd0bd451091f42133b83e06a1";

    private static final String TEST_AUTHORIZATION = "Bearer ";
    private static final String TEST_ACCESSTOKEN = "26fdcb7c-bb29-41fe-a460-760cd023cd31";
    private static final Logger log = Logger.getLogger(CmmSIFInternalServiceTest.class);

    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    @Test
    public void test_IntegratedAccountBalance() throws Exception{
        log.info("------------- IntegratedAccountBalance Test START ---------------");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apiKey", TEST_APIKEY);
        httpHeaders.add("Authorization", TEST_AUTHORIZATION + TEST_ACCESSTOKEN);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String exconn = "https://10.10.10.101:8443";
        String authorizationUrl = exconn+"/v1/koscom/account";

        String payload = "{ \"balanceRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160665474469800099\"}}}";
        log.info("------------- Payload Data ---------------");
        log.info(payload);
        log.info("------------- Payload Data ---------------");
        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, httpHeaders);
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
            ResponseEntity<String> responseEntity = restTemplate.exchange(authorizationUrl, HttpMethod.POST, requestEntity, String.class);
            String strResBody = responseEntity.getBody();
            log.debug("ok:responseBody="+strResBody);
            log.debug("ok:상태코드:"+responseEntity.getStatusCode().value());

        }catch(KeyManagementException e){

            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){

            e.printStackTrace();
        }catch(KeyStoreException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_IntegratedAccountBalanceDataSet() throws Exception{
        log.info("------------- IntegratedAccountBalanceDataSet Test START ---------------");
        /***********************************************************************************
        통합계좌 잔고 조회 -
        회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 잔고조회 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/balance/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"balanceRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"123\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                BalanceResponseVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseVO.class);
                success.put("123",balanceResponseVO);
            }else{
                fail.put("123","");
            }

            payload = "{ \"balanceRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160665474469800099\"}}}";
            responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                BalanceResponseVO balanceResponseVO = new Gson().fromJson(jsonObject.toString(),BalanceResponseVO.class);
                success.put("160665474469800099",balanceResponseVO);
            }else{
                fail.put("160665474469800099","");
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        log.debug("success="+success);
        log.debug("fail="+fail);
    }

    @Test
    public void test_IntegratedAccountInterestDataSet() throws Exception{
        log.info("------------- AccountInterestDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 관심종목 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 관심종목 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/interest/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"interestSymbolListRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                InterestResponseVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseVO.class);
                success.put("160624362225600099",interestResponseVO);
            }else{
                fail.put("160624362225600099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160624362225600099","");
        }


        try{
            String payload = "{ \"interestSymbolListRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160613251214500099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                InterestResponseVO interestResponseVO = new Gson().fromJson(jsonObject.toString(),InterestResponseVO.class);
                success.put("160613251214500099",interestResponseVO);
            }else{
                fail.put("160613251214500099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160613251214500099","");
        }

        log.debug("success="+success);
        log.debug("portfolioFail="+fail);
    }

    @Test
    public void test_IntegratedAccountPortfolioDataSet() throws Exception{
        log.info("------------- IntegratedAccountPortfolioDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 자산 포토폴리오 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 자산포토폴리오 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/portfolio/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

            try{
                String payload = "{ \"portfolioRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
                ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
                if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                    JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                    PortfolioResponseVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseVO.class);
                    success.put("160624362225600099",portfolioResponseVO);
                }else{
                    fail.put("160624362225600099","");
                }
            }catch (Exception e){
                e.printStackTrace();
                fail.put("160624362225600099","");
            }

            try{
                String payload = "{ \"portfolioRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160612734676100099\"}}}";
                ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
                if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                    JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                    PortfolioResponseVO portfolioResponseVO = new Gson().fromJson(jsonObject.toString(),PortfolioResponseVO.class);
                    success.put("160612734676100099",portfolioResponseVO);
                }else{
                    fail.put("160612734676100099","");
                }
            }catch (Exception e){
                e.printStackTrace();
                fail.put("160612734676100099","");
            }

        log.debug("success="+success);
        log.debug("fail="+fail);
    }

    @Test
    public void test_IntegratedAccountTransactionDataSet() throws Exception{
        log.info("------------- IntegratedAccountTransactionDataSet Test START ---------------");
        /***********************************************************************************
         통합계좌 거내래역 조회 -
         회원이 통합계좌 서비스로 신청한 가상계좌 정보로 여러 건의 매핑된 증권사별 통합계좌 거래내역 화면을 출력
         ***********************************************************************************/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String exconn = "http://localhost:8443";
        String authorizationUrl = exconn+"/test/account/transaction/search";
        Map success = new HashMap<>();
        Map fail = new HashMap<String,String>();

        try{
            String payload = "{ \"transactionHistoryRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"160624362225600099\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                TransactionResponseVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseVO.class);
                success.put("160624362225600099",transactionResponseVO);
            }else{
                fail.put("160624362225600099","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("160624362225600099","");
        }


        try{
            String payload = "{ \"transactionHistoryRequestBody\" : { \"accInfo\": {\"vtAccNo\": \"123\"}}}";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(authorizationUrl, httpHeaders, HttpMethod.POST, payload);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
                JSONObject jsonObject =  new JSONObject(responseEntity.getBody());
                TransactionResponseVO transactionResponseVO = new Gson().fromJson(jsonObject.toString(),TransactionResponseVO.class);
                success.put("123",transactionResponseVO);
            }else{
                fail.put("123","");
            }
        }catch (Exception e){
            e.printStackTrace();
            fail.put("123","");
        }

        log.debug("success="+success);
        log.debug("fail="+fail);
    }
}
