package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

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
public interface CmmSIFInternalService {

    /**
     * Send rest template response entity.
     *
     * @param restUrl     the rest url
     * @param httpHeaders the http headers
     * @param httpMethod  the http method
     * @param payload     the payload
     * @return : ResponseEntity<String>
     * @throws Exception the exception
     * @Method Name : sendRestTemplate
     * @Method description : Rest API 통신후 ResponseEntity 반환.
     */
    public ResponseEntity<String> sendRestTemplate(String restUrl, HttpHeaders httpHeaders, HttpMethod httpMethod, String payload) throws Exception;

    /**
     * @Method Name        : sendServiceTerms
     * @Method description : 금융정보제공 동의서 전송 API 호출
     * @param              : customerId
     * @param              : termsRegNo
     */
    public void sendServiceTerms(String customerId, String termsRegNo);

}
