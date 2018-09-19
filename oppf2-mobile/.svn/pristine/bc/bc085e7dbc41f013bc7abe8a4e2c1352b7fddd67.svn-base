package kr.co.koscom.oppfm.terms.web;

import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.app.model.AppCreateReq;
import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.terms.service.CommonTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * CommonTermsController
 * <p>
 * Created by chungyeol.kim on 2017-05-24.
 */
@RestController
@RequestMapping(value = "/apis")
public class CommonTermsController {

    @Autowired
    private CommonTermsService commonTermsService;

    @CheckAuth
    @ApiOperation(value = "(가)동의서 정보 조회", response = CommonResponse.class)
    @RequestMapping(value = "/terms/common", method = RequestMethod.GET)
    public CommonResponse getCommonTermsInfo(HttpServletRequest request) {
        return commonTermsService.getCommonTermsInfo(request);
    }

    @CheckAuth
    @ApiOperation(value = "(가)동의서 동의 여부 체크", response = CommonResponse.class)
    @RequestMapping(value = "/terms/common/check", method = RequestMethod.GET)
    public CommonResponse checkedCommonTerms(HttpServletRequest request,
                                             @RequestParam(value = "pubCompanyCodeId", required = false) String pubCompanyCodeId) {
        return commonTermsService.checkedCommonTerms(request, pubCompanyCodeId);
    }

    @CheckAuth
    @ApiOperation(value = "(가)동의서 재동의 체크", response = CommonResponse.class)
    @RequestMapping(value = "/terms/common/reSync/check", method = RequestMethod.GET)
    public CommonResponse checkedCommonTermsReSync(HttpServletRequest request) {
        return commonTermsService.checkedCommonTermsReSync(request);
    }

    @CheckAuth
    @ApiOperation(value = "(가)동의서 정보 등록", response = CommonResponse.class)
    @RequestMapping(value = "/terms/common", method = RequestMethod.POST)
    public CommonResponse createCommonTermsInfo(HttpServletRequest request, @RequestBody AppCreateReq appCreateReq) {
        return commonTermsService.createCommonTerms(request, appCreateReq);
    }

}
