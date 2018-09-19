package kr.co.koscom.oppfm.sample.web;

import kr.co.koscom.oppfm.cmm.annotation.CheckAuth;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CookieUtil;
import kr.co.koscom.oppfm.login.model.LoginRes;
import kr.co.koscom.oppfm.sample.model.SampleReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * SampleController
 * <p>
 * Created by chungyeol.kim on 2017-05-12.
 */
@RestController
@Slf4j
public class SampleController {

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/apis/sample", method = RequestMethod.POST, consumes = {"application/json"})
    public CommonResponse createSample(@RequestBody @Validated(value = ValidationCreate.class) SampleReq sampleReq, HttpServletRequest request) {

        /**
        *
        *로그인 정보 가져오는 방법(공통)
         * -> 로그인 정보가 필요한 메소드나 클래스에 @CheckAuth 필수
         * */
        LoginRes loginRes = CookieUtil.getLoginInfo(request);

//        log.debug(String.valueOf(sampleReq.getSampleNumber()));
        log.debug(sampleReq.getSampleName());
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }

    @RequestMapping(value = "/apis/samples", method = RequestMethod.GET)
    public CommonResponse getSampleList(@RequestParam(required = false) String sampleName) {
//        log.debug(String.valueOf(sampleReq.getSampleNumber()));
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
    }

}
