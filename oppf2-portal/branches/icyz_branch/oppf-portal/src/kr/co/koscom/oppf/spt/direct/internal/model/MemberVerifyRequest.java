package kr.co.koscom.oppf.spt.direct.internal.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MemberVerifyRequest implements Serializable {
    @NotNull
    private String userCi;
    
    private String userDn;
    
    @JsonIgnore
    private String customerRegNo;
}