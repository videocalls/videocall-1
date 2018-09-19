package kr.co.koscom.oppfm.oauth.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : OauthAppRes
 *
 * Description : Oauth Info Response
 *
 * Created by LHT on 2017. 5. 15..
 */
@Data
public class OauthAppRes extends CommonVO {

    private static final long serialVersionUID = 265718723783559976L;
    //App OAuth sessionID
    private String sessionId;
    //App client_id
    private String clientId;
    //App scope
    private String scope;
    //App appId
    private String appId;
    //App appName
    private String appName;
    //App Img
    private String appImg;
    //companyName
    private String companyName;

}
