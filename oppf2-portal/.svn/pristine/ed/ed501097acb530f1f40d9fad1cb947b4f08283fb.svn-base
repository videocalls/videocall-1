package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;

import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsTransmitResponse implements Serializable {
    
    private FinanceTermsTransmitResponseBody finanaceTermsTransmitResponseBody;
    
    private Resp resp;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceTermsTransmitResponseBody implements Serializable {
        private TermsInfo requestParameters;
    }
}
