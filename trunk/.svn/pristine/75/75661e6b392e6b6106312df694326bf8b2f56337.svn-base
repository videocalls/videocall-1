package kr.co.koscom.oppfm.oauth.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.oauth.model.OauthAppReq;
import kr.co.koscom.oppfm.oauth.model.OauthScopeReq;
import kr.co.koscom.oppfm.oauth.model.OauthUserReq;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName : OauthService
 *
 * Description : Oauth Service
 *
 * Created by LHT on 2017. 5. 16..
 */
public interface OauthService {

    /**
     * getOauthInfo
     * */
    CommonResponse getOauthInfo(OauthAppReq oauthAppReq);

    /**
     * getAuthenticate
     * */
    CommonResponse getAuthenticate(OauthUserReq oauthUserReq, HttpServletResponse response);

    /**
     * createScope
     * */
    CommonResponse createScope(OauthScopeReq oauthScopeReq);

    /**
     * createAuthorization
     * */
    CommonResponse createAuthorization(OauthScopeReq oauthScopeReq);

    /**
     * createToken
     * */
    CommonResponse createToken(LoginRes user);

    /**
     * getAppCheck
     * */
    CommonResponse getAppCheck(OauthUserReq oauthUserReq);

    /**
     * getOtpCheck
     * */
    CommonResponse getOtpCheck(String userId);

}
