package kr.co.koscom.oppfm.oauth.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FileName : OauthUserReq
 *
 * Description : OauthUserReq Request
 *
 * Created by LHT on 2017. 5. 17..
 */
@Data
public class OauthUserReq extends CommonVO {

    private static final long serialVersionUID = -4183211211266996432L;

    //App client_id
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String clientId;
    //userId
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String userId;
    //password
    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
    private String password;

}
