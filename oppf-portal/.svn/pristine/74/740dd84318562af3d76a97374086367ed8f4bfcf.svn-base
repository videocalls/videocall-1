package kr.co.koscom.oppf.cmm.IntegratedAccount.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * The interface Cmm sif internal service.
 *
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmSIFInternalService.java
 * @Comment : 통합계좌 인터페이스 기능을 제공하는 service
 * @see
 * @since : 2017.02.09
 */
@Slf4j
@Service("CmmSIFInternalService")
public class CmmSIFInternalServiceImpl implements CmmSIFInternalService {

    /**
     * Send rest template response entity.
     *
     * @param httpHeaders the http headers
     * @param httpMethod  the http method
     * @param payload     the payload
     * @return : ResponseEntity<String>
     * @throws Exception the exception
     * @Method Name : sendRestTemplate
     * @Method description : Rest API 통신후 ResponseEntity 반환.
     */
    @Override
    public ResponseEntity<String> sendRestTemplate(String restUrl, HttpHeaders httpHeaders, HttpMethod httpMethod, String payload) throws Exception {

        log.info("------------- restUrl ---------------");
        log.info(restUrl);
        log.info("------------- HttpHeaders ---------------");
        log.info(httpHeaders.toString());
        log.info("------------- HttpMethod ---------------");
        log.info(httpMethod.toString());
        log.info("------------- Payload Data ---------------");
        log.info(payload);
        log.info("------------------------------------------");

        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, httpHeaders);
        ResponseEntity<String> responseEntity = null;
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
            requestFactory.setConnectTimeout(10*1000);
            requestFactory.setReadTimeout(10*1000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            restTemplate.setErrorHandler(new ResponseErrorHandler() {

                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                	 log.info("hasError --> " + response.getStatusText());
                    return false;
                }

                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                	 log.info("handleError --> " + response.getStatusText());
                }
            });
            responseEntity = restTemplate.exchange(restUrl, httpMethod, requestEntity, String.class);
            log.info("ok:responseBody="+responseEntity.getBody());

        }catch(KeyManagementException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }catch(KeyStoreException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return responseEntity;
    }

    /**
     * @param customerId
     * @param termsRegNo
     * @Method Name        : sendServiceTerms
     * @Method description : 금융정보제공 동의서 전송 API 호출
     */
    @Override
    public void sendServiceTerms(String customerId, String termsRegNo) {

        log.debug("------------- 금융정보제공 동의서 전송 API 호출 START ---------------");

        String apiUrl = OppfProperties.getProperty("Globals.sifmanageapi") + "/financeterms/terms/transmit/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try{
            log.debug("customerRegNo : {}, termsRegNo : {}", customerId, termsRegNo);
            // http://외부연계IP:8093/financeterms/terms/transmit/{사용자아이디}/{계약등록번호}
            apiUrl += customerId + "/" + termsRegNo;
            //연계서버 금융정보 제공동의서 API 전송
            sendRestTemplate(apiUrl, httpHeaders, HttpMethod.POST, "{}");

        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.debug("------------- 금융정보제공 동의서 전송 API 호출 END ---------------");
    }
}
