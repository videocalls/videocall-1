package kr.co.koscom.oppfm.oauth.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.oauth.model.OauthAppReq;
import kr.co.koscom.oppfm.oauth.model.OauthAppRes;
import kr.co.koscom.oppfm.oauth.model.OauthScopeReq;
import kr.co.koscom.oppfm.oauth.model.OauthUserReq;
import kr.co.koscom.oppfm.oauth.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FileName : OauthController
 *
 * Description : OAuth Controller
 *
 * Created by LHT on 2017. 5. 15..
 */
@Api(value="Oauth-controller", description = "App to App > OAuth")
@Slf4j
@RestController
public class OauthController {

    @Autowired
    OauthService oauthService;

    /**
     * oauthInfo
     * */
    @ApiOperation(value="OAuth 정보 조회", response = OauthAppRes.class)
    @RequestMapping(value = "/apis/oauth", method = RequestMethod.POST)
    public CommonResponse getOauthInfo(@RequestBody @Validated(value = ValidationCreate.class) OauthAppReq oauthAppReq){

        return oauthService.getOauthInfo(oauthAppReq);
    }

    /**
     * getAuthenticate
     * */
    @ApiOperation(value="OAuth 사용자 인증", response = CommonResponse.class)
    @RequestMapping(value = "/apis/authenticate", method = RequestMethod.POST)
    public CommonResponse getAuthenticate(@RequestBody @Validated(value = ValidationCreate.class) OauthUserReq oauthUserReq, HttpServletResponse response){

        return oauthService.getAuthenticate(oauthUserReq, response);
    }

    /**
     * createScope
     * */
    @CheckAuth
    @ApiOperation(value="OAuth 인증 이력 등록", response = CommonResponse.class)
    @RequestMapping(value = "/apis/scope", method = RequestMethod.POST)
    public CommonResponse createScope(@RequestBody @Validated(value = ValidationCreate.class) OauthScopeReq oauthScopeReq, HttpServletRequest request){

        LoginRes user =  CookieUtil.getLoginInfo(request);
        oauthScopeReq.setUserId(user.getCustomerId());

        return oauthService.createScope(oauthScopeReq);
    }

    /**
     * createAuthorization
     * */
    @CheckAuth
    @ApiOperation(value="Authorization code 요청", response = CommonResponse.class)
    @RequestMapping(value = "/apis/authorization", method = RequestMethod.POST)
    public CommonResponse createAuthorization(@RequestBody OauthScopeReq oauthScopeReq, HttpServletRequest request){

        if(null == oauthScopeReq.getSessionId() || "".equals(oauthScopeReq.getSessionId())){
            throw new CommonException(ErrorCodeConstants.REQUIRED_PARAMETER_ERROR, new String[] {"sessionId"});
        }

        LoginRes user =  CookieUtil.getLoginInfo(request);
        oauthScopeReq.setUserId(user.getCustomerId());

        return oauthService.createAuthorization(oauthScopeReq);
    }

    /**
     * createToken
     * */
    @CheckAuth
    @ApiOperation(value="통합계좌 APP Access Token 발급", response = CommonResponse.class)
    @RequestMapping(value = "/apis/token", method = RequestMethod.POST)
    public CommonResponse createToken(@RequestBody OauthScopeReq oauthScopeReq, HttpServletRequest request){

        LoginRes user =  CookieUtil.getLoginInfo(request);
        
        user.setResponseType(oauthScopeReq.getResponseType());

        return oauthService.createToken(user);
    }

    /**
     * getAppCheck
     * */
    @CheckAuth
    @ApiOperation(value="OAuth 회원 App 신청조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/oauth/apps/{clientId}", method = RequestMethod.GET)
    public CommonResponse getAppCheck(@PathVariable String clientId, HttpServletRequest request){

        LoginRes user =  CookieUtil.getLoginInfo(request);
        OauthUserReq oauthUserReq = new OauthUserReq();
        oauthUserReq.setClientId(clientId);
        oauthUserReq.setUserId(user.getCustomerId());

        return oauthService.getAppCheck(oauthUserReq);
    }

    @ApiOperation(value="OAuth 로그인 OTP 사용여부 조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/oauth/otp/{userId}", method = RequestMethod.GET)
    public CommonResponse getOtpCheck(@PathVariable String userId){

        return oauthService.getOtpCheck(userId);
    }

}
