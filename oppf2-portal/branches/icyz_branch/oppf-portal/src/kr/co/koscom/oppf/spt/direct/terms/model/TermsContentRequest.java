package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsContentRequest implements Serializable {
    
    @NotNull
    private Partner partner;
    
    @NotNull
    private CommonHeader commonHeader;
    
    private DevInfo devInfo;
    
    @NotNull
    private FinanceTermsContentRequestBody financeTermsContentRequestBody;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceTermsContentRequestBody implements Serializable {
        private RequestParameters requestParameters;
    }

    @SuppressWarnings("serial")
    @Data
    public static class RequestParameters implements Serializable {
        @NotNull
        private String customerBirthDay;
        
        @NotNull
        private String customerEmail;
        
        @NotNull
        private String customerPhone;
        
        @NotNull
        private String customerUserNm;
        
        @NotNull
        private List<String> companyList;
    }
    
}
