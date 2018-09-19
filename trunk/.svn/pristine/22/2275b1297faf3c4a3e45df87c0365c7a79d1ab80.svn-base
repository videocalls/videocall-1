package kr.co.koscom.oppfm.cmm.util;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * RestTemplateUtil
 * <p>
 * Created by chungyeol.kim on 2017-04-24.
 */
@Slf4j
public class RestTemplateUtil {

    public static ResponseEntity<String> sendRestTemplate(String restUrl, HttpHeaders httpHeaders, HttpMethod httpMethod, String payload) {

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
            requestFactory.setReadTimeout(10*1000);
            requestFactory.setConnectTimeout(10*1000);
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
}
