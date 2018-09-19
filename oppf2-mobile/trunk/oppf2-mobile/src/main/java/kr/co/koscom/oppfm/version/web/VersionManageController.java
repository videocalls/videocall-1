package kr.co.koscom.oppfm.version.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.version.service.VersionManageService;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


/**
 * VersionManageController
 * <p>
 * Created by Yoojin Han on 2017-05-17.
 */

@Slf4j
@Api(tags = "version-controller", description = "버전 관리")
@RestController
@RequestMapping(value = "/apis")
public class VersionManageController {

    @Autowired
    MessageUtil messageUtil;
    
    @Autowired
    VersionManageService versionManageService;

    @Value("#{config['Globals.app.install.ios']}")
    String appInstallUrl;

    @ApiOperation(value = "앱설치 ",  response=CommonResponse.class)
    @RequestMapping(value = "/version/ios", method = RequestMethod.GET)
    public CommonResponse getVersionIos()  {
        
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        
        response.getBody().put("appInstallUrl", appInstallUrl);
        
        return response;
    }


    /**
     * 버전 확인 (service.json파일)
     * @MethodName              getVersionList
     * @return                  CommonResponse
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name ="refrashcookie", dataType ="String", paramType ="query", value = "세션초기화 default = null" )
    })
    @ApiOperation(value = "service.json 조회",  response=CommonResponse.class)
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public CommonResponse getVersionList(@ApiIgnore @RequestParam(value="refrashcookie", required = false) String refrashcookie
                                            , HttpServletResponse response)  {
        
        // 최초 호출시(앱기동시에만 호출) 세션 클리어
        if( !OppfStringUtil.equals(refrashcookie, "1")){
            log.debug("CookieUtil.removeLoginCookie() 실행");
            CookieUtil.removeLoginCookie(response);
        }

        String fileName = "/resources/service.json";
        
        return versionManageService.getVersionList(fileName);
    }
    
}
