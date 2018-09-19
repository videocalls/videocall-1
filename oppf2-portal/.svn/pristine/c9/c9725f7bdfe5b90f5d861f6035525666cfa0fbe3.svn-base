package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TermsInfo implements Serializable {
    
    @NotNull
    @Valid
    private String text;
    
    @NotNull
    @Valid
    private String customerBirthDay;
    
    @NotNull
    @Valid
    private String customerEmail;
    
    @NotNull
    @Valid
    private String customerPhone;
    
    @NotNull
    @Valid
    private String customerUserNm;

    @NotNull
    @Valid
    private String version;
    
    @NotNull
    @Valid
    private String termsDate;

    @NotNull
    @Valid
    private String termsStartDate;

    @NotNull
    @Valid
    private String termsEndDate;
    
    private List<String> companyList;
}