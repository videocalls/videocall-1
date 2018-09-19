package kr.co.koscom.oppf.spt.myp.intAcnt.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

/**
 * @author lee
 * @date 2017-03-23
 */
public class IntAcntWorker implements Callable {

    private String accountApiUrl;
    private HttpHeaders httpHeaders;
    private String payload;
    private CmmSIFInternalService cmmSIFInternalService;
    private ResponseEntity<String> responseEntity;
    private String comId;
    private String vtAccNo;
    private String vtAccAlias;
    private String searchIsinType;
    private String isinCode;

    public IntAcntWorker() {}

    public IntAcntWorker(String accountApiUrl, HttpHeaders httpHeaders, String payload, CmmSIFInternalService cmmSIFInternalService
            , String comId, String vtAccNo, String vtAccAlias, String searchIsinType, String isinCode){
        this.accountApiUrl = accountApiUrl;
        this.httpHeaders = httpHeaders;
        this.payload = payload;
        this.cmmSIFInternalService = cmmSIFInternalService;
        this.comId = comId;
        this.vtAccNo = vtAccNo;
        this.vtAccAlias = vtAccAlias;
        this.searchIsinType = searchIsinType;
        this.isinCode = isinCode;
    }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public IntAcntWorker call() throws Exception {
        responseEntity = cmmSIFInternalService.sendRestTemplate(accountApiUrl, httpHeaders, HttpMethod.POST, payload);
        return this;
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }
    public void setResponseEntity(ResponseEntity<String> responseEntity) {
        this.responseEntity = responseEntity;
    }
    public String getComId() {
        return comId;
    }
    public void setComId(String comId) {
        this.comId = comId;
    }
    public String getVtAccNo() {
        return vtAccNo;
    }
    public void setVtAccNo(String vtAccNo) {
        this.vtAccNo = vtAccNo;
    }
    public String getVtAccAlias() {
        return vtAccAlias;
    }
    public void setVtAccAlias(String vtAccAlias) {
        this.vtAccAlias = vtAccAlias;
    }
    public String getIsinCode() {
        return isinCode;
    }
    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }
    public String getSearchIsinType() {
        return searchIsinType;
    }
    public void setSearchIsinType(String searchIsinType) {
        this.searchIsinType = searchIsinType;
    }
}
