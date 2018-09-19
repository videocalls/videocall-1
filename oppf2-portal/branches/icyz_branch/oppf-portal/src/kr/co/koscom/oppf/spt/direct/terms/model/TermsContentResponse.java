package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;

import kr.co.koscom.oppf.spt.direct.common.model.RequestResult;
import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentRequest.RequestParameters;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsContentResponse implements Serializable {
 
    private FinanceTermsContentResponseBody financeTermsContentResponseBody;
    
    private TermsInfo financeTermsInfo;
    
    private Resp resp;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceTermsContentResponseBody implements Serializable {
        private RequestParameters requestParameters;
        private RequestResult requestResult;
    }

}
