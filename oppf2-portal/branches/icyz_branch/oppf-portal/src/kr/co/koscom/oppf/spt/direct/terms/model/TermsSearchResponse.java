package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;

import kr.co.koscom.oppf.spt.direct.common.model.RequestResult;
import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsSearchResponse implements Serializable {
    
    private FinanceTermsSearchResponseBody financeTermsSearchResponseBody;
    
    private TermsInfo financeTermsInfo;
    
    private Resp resp;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceTermsSearchResponseBody implements Serializable {
        private RequestResult requestResult;
    }
}
