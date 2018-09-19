package kr.co.koscom.oppfm.intro.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.intro.model.IntroFintechAppReq;
import kr.co.koscom.oppfm.intro.model.IntroFintechAppRes;
import kr.co.koscom.oppfm.intro.service.IntroManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FileName : IntroManageController
 *
 * Description : App 소개 Controller
 *
 * Created by LSH on 2017. 4. 20..
 */
@Api(value="intro-manage-controller", description = "메인 >  app 소개")
@RestController
public class IntroManageController {


    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    IntroManageService introManageService;


    /**
     * Introduce App List
     * */
    @ApiOperation(value="app 소개", response = IntroFintechAppRes.class)
    @RequestMapping(value = "/apis/introapps", method = RequestMethod.GET)
    public CommonResponse appList(IntroFintechAppReq introFintechAppReq){

        return introManageService.introFintechAppList(introFintechAppReq);

    }

    /**
     * Introduce App Detail
     * */
    @ApiOperation(value = "기업정보 조회", response = IntroFintechAppRes.class)
    @RequestMapping(value = "/apis/introapps/{appId}", method = RequestMethod.GET)
    public CommonResponse appCompanyList(@PathVariable String appId){
        if(appId == null){
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"AppID"});
        }

        IntroFintechAppRes introFintechAppRes = new IntroFintechAppRes();
        introFintechAppRes.setAppId(appId);
        List<IntroFintechAppRes> AppCompanyList = introManageService.introFintechAppCompanyList(introFintechAppRes);

        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);

        response.getBody().put("AppCompanyList", AppCompanyList);

        return response;
    }

}
