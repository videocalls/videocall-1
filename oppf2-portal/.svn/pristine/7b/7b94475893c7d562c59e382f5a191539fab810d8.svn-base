package kr.co.koscom.oppf.spt.direct.company.model;

import java.io.Serializable;
import java.util.List;

import kr.co.koscom.oppf.spt.direct.common.model.RequestResult;
import kr.co.koscom.oppf.spt.direct.common.model.Resp;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CompanyResponse implements Serializable {
 
    private FinanceListResponseBody financeListResponseBody;
    
    private List<Company> financeList;
    
    private Resp resp;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceListResponseBody implements Serializable {
        private RequestResult requestResult;
    }

}
