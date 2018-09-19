package kr.co.koscom.oppfm.app.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.app.model.AppCreateReq;
import kr.co.koscom.oppfm.app.model.AppSearchReq;
import kr.co.koscom.oppfm.app.model.AppTermsArsReq;
import kr.co.koscom.oppfm.app.service.AppService;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


/**
 * AppController
 * <p>
 * Created by chungyeol.kim on 2017-05-11.
 */
@Slf4j
@Api(description = "App 소개", produces = "application/json")
@RestController
public class AppController {
	
    @Autowired
    AppService appService;

    /**
     * Introduce App List
     * 로그인 전/후
     * @throws Exception 
     * */
    @ApiOperation(value="App 목록", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps", method = RequestMethod.GET)
    public CommonResponse getAppList(AppSearchReq appSearchReq, HttpServletRequest request) {
    	return appService.getAppList(appSearchReq,request);
    }
    
    
    /**
     * Introduce App Detail
     * 로그인 전/후
     * @throws Exception 
     * */   
    @ApiOperation(value = "App 상세조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/{appId}", method = RequestMethod.GET)
    public CommonResponse getAppDtl(@PathVariable String appId, HttpServletRequest request,@RequestParam(value="type") String type ){       
        return appService.getAppDtl(appId,request, type);
    }

    /**
     * 연결 계좌 정보 조회
     * 로그인 후
     * @throws Exception
     * */
    @CheckAuth
    @ApiOperation(value = "App신청 - 연결 계좌 정보 조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/{appId}/account", method = RequestMethod.GET)
    public CommonResponse getAccount(@PathVariable String appId,@RequestParam(value="type") String type, HttpServletRequest request){
        return appService.getAccountList(appId,type,request);
    }

    @CheckAuth
    @ApiOperation(value = "(나)동의서 정보 제공동의 체크", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/{appId}/terms/check", method = RequestMethod.GET)
    public CommonResponse checkAppTermsInfo(@PathVariable String appId, HttpServletRequest request,
                                            @RequestParam(value="checkedPubCompanyList", required = false) String checkedPubCompanyList,
                                            @RequestParam(value="subCompanyCodeId") String subCompanyCodeId) {
        return appService.checkTermsInfo(request, appId, checkedPubCompanyList, subCompanyCodeId);
    }

    /**
     * 정보제공 동의서 팝업  - (나)동의서)
     * @param appId
     * @param request
     * @return
     */
    @CheckAuth
    @ApiOperation(value = "(나)동의서 정보 조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/{appId}/terms", method = RequestMethod.GET)
    public CommonResponse getAppTermsInfo(@PathVariable String appId, HttpServletRequest request,
                                          @RequestParam(value="checkedPubCompanyList", required = false) String checkedPubCompanyList,
                                          @RequestParam(value="type", required = false) String type) {
        return appService.getTermsInfo(request, appId, checkedPubCompanyList, type);
    }

    @CheckAuth
    @ApiOperation(value = "ARS 정보 조회", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/terms/ars", method = RequestMethod.GET)
    public CommonResponse getTermsArsInfo(HttpServletRequest request) {
        return appService.getTermsArsInfo(request);
    }

    @CheckAuth
    @ApiOperation(value = "ARS 인증 요청", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/terms/ars", method = RequestMethod.POST)
    public CommonResponse requestTermsArs(HttpServletRequest request, @RequestBody AppTermsArsReq appTermsArsReq) {
        return appService.requestTermsArs(request, appTermsArsReq);
    }

    @CheckAuth
    @ApiOperation(value = "APP 등록", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps", method = RequestMethod.POST)
    public CommonResponse createApp(HttpServletRequest request, @Validated(value = ValidationCreate.class) @RequestBody AppCreateReq appCreateReq) throws Exception {
        CommonResponse commonResponse = appService.createApp(request, appCreateReq, false);
        String termsRegNo = commonResponse.getBody().get("termsRegNo").toString();
        /*if(commonResponse.getBody().containsKey("oldTermsRegNo")) {
            String oldTermsRegNo = commonResponse.getBody().get("oldTermsRegNo").toString();
            appService.sendAppTerms(request, oldTermsRegNo);
        }*/
        appService.sendAppTerms(request, termsRegNo);
        return commonResponse;
    }

    @CheckAuth
    @ApiOperation(value = "APP 수정", response = CommonResponse.class)
    @RequestMapping(value = "/apis/appsput", method = RequestMethod.POST)
    public CommonResponse modifyApp(HttpServletRequest request, @Validated(value = ValidationCreate.class) @RequestBody AppCreateReq appCreateReq) throws Exception {
        CommonResponse commonResponse = appService.modifyApp(request, appCreateReq);

        // 동의서 폐기/동의 sif 연동
        if(commonResponse.getBody().containsKey("oldTermsRegNo")) {
//            String oldTermsRegNo = commonResponse.getBody().get("oldTermsRegNo").toString();
            String newTermsRegNo = commonResponse.getBody().get("newTermsRegNo").toString();
//            appService.sendAppTerms(request, oldTermsRegNo);
            appService.sendAppTerms(request, newTermsRegNo);
        }
        return commonResponse;
    }

    @CheckAuth
    @ApiOperation(value = "APP 삭제", response = CommonResponse.class)
    @RequestMapping(value = "/apis/appsdelete/{appId}", method = RequestMethod.POST)
    public CommonResponse removeApp(HttpServletRequest request, @PathVariable String appId) {
        CommonResponse commonResponse = appService.removeApp(request, appId);

        if(commonResponse.getBody().containsKey("termsRegNo")) {
            String termsRegNo = commonResponse.getBody().get("termsRegNo").toString();
            appService.sendAppTerms(request, termsRegNo);
        }

        return commonResponse;
    }

    @CheckAuth
    @ApiOperation(value = "APP Terms 삭제", response = CommonResponse.class)
    @RequestMapping(value = "/apis/appsdelete/{appId}/terms", method = RequestMethod.POST)
    public CommonResponse removeAppTerms(HttpServletRequest request, @PathVariable String appId) {
        CommonResponse commonResponse = appService.removeAppTerms(request, appId);
        if(commonResponse.getBody().containsKey("termsRegNo")) {
            String termsRegNo = commonResponse.getBody().get("termsRegNo").toString();
            appService.sendAppTerms(request, termsRegNo);
        }
        return commonResponse;
    }

    @CheckAuth
    @ApiOperation(value = "APP Terms 재동의", response = CommonResponse.class)
    @RequestMapping(value = "/apis/apps/{appId}/terms", method = RequestMethod.POST)
    public CommonResponse createAppTerms(HttpServletRequest request, @PathVariable String appId,
                                         @Validated(value = ValidationCreate.class) @RequestBody AppCreateReq appCreateReq) {
        CommonResponse commonResponse = appService.createAppTerms(request, appCreateReq, appId);
        // 동의서 폐기/동의 sif 연동
        if(commonResponse.getBody().containsKey("oldTermsRegNo")) {
//            String oldTermsRegNo = commonResponse.getBody().get("oldTermsRegNo").toString();
            String newTermsRegNo = commonResponse.getBody().get("newTermsRegNo").toString();
//            appService.sendAppTerms(request, oldTermsRegNo);
            appService.sendAppTerms(request, newTermsRegNo);
        }
        return commonResponse;
    }
}
