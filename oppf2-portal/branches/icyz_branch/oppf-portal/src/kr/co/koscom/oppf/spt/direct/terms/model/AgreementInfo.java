package kr.co.koscom.oppf.spt.direct.terms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class AgreementInfo implements Serializable {
    
    @NotNull
    @Valid
    private String text;
    
    @NotNull
    @Valid
    private String version;
}