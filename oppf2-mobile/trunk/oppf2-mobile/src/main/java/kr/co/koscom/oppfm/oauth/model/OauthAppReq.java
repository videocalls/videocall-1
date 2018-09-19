package kr.co.koscom.oppfm.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FileName : OauthAppReq
 *
 * Description : Oauth Info Request
 *
 * Created by LHT on 2017. 5. 15..
 */
@Data
public class OauthAppReq extends CommonVO {
    private static final long serialVersionUID = -1250499869007670574L;
    //App OAuth response_type
    private String responseType;
    //state
    private String state;
    //App client_id
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String clientId;
    //App redirect_uri
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String callbackUrl;
    //App scope
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String scope;

}
