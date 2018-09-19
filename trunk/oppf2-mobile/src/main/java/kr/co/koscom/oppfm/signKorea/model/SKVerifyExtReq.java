package kr.co.koscom.oppfm.signKorea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * SKVerifyExtReq
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

@Data
public class SKVerifyExtReq {

    @JsonIgnore
    public String version;
    @JsonIgnore
    public String serial;
    @JsonIgnore
    public String issuer;
    @JsonIgnore
    public String subject;
    @JsonIgnore
    public String from;
    @JsonIgnore
    public String to;
    @JsonIgnore
    public String policy;
    @JsonIgnore
    public String distributionPoints;
    @JsonIgnore
    public String authorityKeyId;
    @JsonIgnore
    public String subjectKeyId;
    @JsonIgnore
    public String publicKey;
    
}
