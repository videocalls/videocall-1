package kr.co.koscom.oppfm.signKorea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * SKSignedDataReq
 * <p>
 * Created by Yoojin Han on 2017-05-26.
 */

@Data
public class SKSignedDataReq {
    
    @JsonIgnore
    public byte[] signed_data;
    @JsonIgnore
    public byte   opcode;
    @JsonIgnore
    public int    status_flag;  /* OK : 0, NOT OK : 1 */
    @JsonIgnore
    public String sid;
    @JsonIgnore
    public String errmsg;
    @JsonIgnore
    public String signer_dn;
    @JsonIgnore
    public String certificate;
    @JsonIgnore
    public byte[] publickey;
    @JsonIgnore
    public byte[] plaintext;
    
}
