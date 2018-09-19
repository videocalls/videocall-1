package kr.co.koscom.oppf.cmm.IntegratedAccount.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
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
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
@Service("CmmSIFInternalService")
public class CmmSIFInternalServiceImpl implements CmmSIFInternalService {

    private static final Logger log = Logger.getLogger(CmmSIFInternalServiceImpl.class);
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
        log.info(httpHeaders);
        log.info("------------- HttpMethod ---------------");
        log.info(httpMethod);
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
            log.error(e);
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            log.error(e);
            e.printStackTrace();
        }catch(KeyStoreException e){
            log.error(e);
            e.printStackTrace();
        }catch(Exception e) {
            log.error(e);
            e.printStackTrace();
        }
        return responseEntity;
    }
}
