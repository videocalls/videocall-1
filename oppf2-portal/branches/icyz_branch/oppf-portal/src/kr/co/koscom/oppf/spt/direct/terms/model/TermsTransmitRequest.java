package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsTransmitRequest implements Serializable {
    @NotNull
    private Partner partner;
    
    @NotNull
    private CommonHeader commonHeader;
    
    private DevInfo devInfo;
    
    @NotNull
    private FinanceTermsTransmitRequestBody financeTermsTransmitRequestBody;
    
    @NotNull
    private JsonWebSignature jws;
    
    @SuppressWarnings("serial")
    @Data
    public static class FinanceTermsTransmitRequestBody implements Serializable {
        private TermsInfo requestParameters;
    }
    
    @SuppressWarnings("serial")
    @Data
    public static class JsonWebSignature implements Serializable {
        @NotNull
        private String payload;
        
        @JsonProperty("protected")
        @NotNull
        private String protected0;
        
        @NotNull
        private String signature;
    }


//    @SuppressWarnings("serial")
//    @Data
//    public static class RequestParameter implements Serializable {
//        private String text;
//        private String customerBirthDay;
//        private String customerEmail;
//        private String customerPhone;
//        private String customerUserNm;
//        private String version;
//        private String termsDate;
//        private String termsStartDate;
//        private String termsEndDate;
//        private List<String> companyList;
//    }
}