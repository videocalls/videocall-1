package kr.co.koscom.oppfm.oauth.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import lombok.Data;

/**
 * FileName : OauthTokenRes
 *
 * Description : Oauth Info OauthToken Response
 *
 * Created by LHT on 2017. 5. 23..
 */
@Data
public class OauthTokenRes extends CommonVO {

    private static final long serialVersionUID = -2704745717218987658L;
    //App OAuth accessToken
    private String accessToken;
    //App tokenType
    private String tokenType;
    //App expiresIn
    private String expiresIn;
    //App refreshToken
    private String refreshToken;
    //App rspScope
    private String rspScope;
    //customerRegNo
    private String customerRegNo;
    //oauthSeq
    private String oauthSeq;

}
