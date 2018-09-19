package kr.co.koscom.oppfm.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FileName : OauthScopeReq
 *
 * Description : OauthScope Request
 *
 * Created by LHT on 2017. 5. 17..
 */
@Data
public class OauthScopeReq extends CommonVO {

    private static final long serialVersionUID = -4975130290852427929L;
    //OAuth sessionId
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String sessionId;
    //OAuth clientId
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String clientId;
    //OAuth scope
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String scope;
    //userId
    private String userId;
    
    private String responseType;

    //회원 OP 등록 번호
    @JsonIgnore
    private String customerRegNo;
    //providerSeq 번호
    @JsonIgnore
    private String providerSeq;

}
