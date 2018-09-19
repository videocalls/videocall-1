package kr.co.koscom.oppfm.cmm.util;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

/**
 * @author lee
 * @date 2017-05-24
 */
@Data
public class IntAccountWorkerUtil implements Callable {

    private String accountApiUrl;
    private HttpHeaders httpHeaders;
    private String payload;
    private RestTemplateUtil restTemplateUtil;
    private ResponseEntity<String> responseEntity;
    private String comId;
    private String vtAccNo;
    private String vtAccAlias;

    public IntAccountWorkerUtil() {}

    public IntAccountWorkerUtil(String accountApiUrl, HttpHeaders httpHeaders, String payload, RestTemplateUtil restTemplateUtil
            , String comId, String vtAccNo, String vtAccAlias){
        this.accountApiUrl = accountApiUrl;
        this.httpHeaders = httpHeaders;
        this.payload = payload;
        this.restTemplateUtil = restTemplateUtil;
        this.comId = comId;
        this.vtAccNo = vtAccNo;
        this.vtAccAlias = vtAccAlias;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public IntAccountWorkerUtil call() throws Exception {
        responseEntity = restTemplateUtil.sendRestTemplate(accountApiUrl, httpHeaders, HttpMethod.POST, payload);
        return this;
    }
}
