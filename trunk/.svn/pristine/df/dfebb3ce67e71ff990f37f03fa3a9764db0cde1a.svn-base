package kr.co.koscom.oppfm.terms.web;

import io.swagger.annotations.Api;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.terms.model.MobileTerms;
import kr.co.koscom.oppfm.terms.model.TermsReq;
import kr.co.koscom.oppfm.terms.service.TermsContentsService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName : TermsContentsConteroller
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 18..
 */
@Api(value = "Member-Join-Controller", description = "회원가입 > 약관동의내용", produces = "application/json")
@RequestMapping(value = "/apis")
@RestController
@Slf4j
public class TermsContentsConteroller {

    @Autowired
    TermsContentsService termsContentsService;

    /***************************
     * 약관동의(회원가입 시) 목록
     * *************************/
    @RequestMapping(value="/terms", method = RequestMethod.GET)
    public CommonResponse getTermsContents(TermsReq termsReq, @ApiIgnore HttpServletRequest request) throws Exception {

        CommonResponse response = termsContentsService.getTermsContents(termsReq);

        return response;
    }

    /***************************
     * 약관동의 상세
     * *************************/
    @RequestMapping(value="/terms/{termsSeq}", method = RequestMethod.GET)
    public CommonResponse getTermsContents(@PathVariable String termsSeq,@RequestParam String customerTermsType, @RequestParam String customerTermsSystemKind, @ApiIgnore TermsReq termsReq) throws Exception {

        termsReq.setCustomerTermsContentRegSeq(termsSeq);
        
        log.info("Seq = {}", termsReq.getCustomerTermsContentRegSeq());
        log.info("customerTermsSystemKind = {}", customerTermsSystemKind);

        termsReq.setCustomerTermsSystemKind(customerTermsSystemKind);
        termsReq.setCustomerTermsType(customerTermsType);
        CommonResponse response = termsContentsService.getTermsContentDetail(termsReq);


        return response;
    }
    

    /***************************
     * 통신사 이용 약관 조회
     * *************************/
    @RequestMapping(value="/mobileTerms", method = RequestMethod.GET)
    public CommonResponse getMobileTerms(@ApiIgnore MobileTerms mobileTerms, @ApiIgnore HttpServletRequest request) throws Exception {

        CommonResponse response = termsContentsService.getMobileTerms(mobileTerms);

        return response;
    }




}
