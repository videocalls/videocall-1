package kr.co.koscom.oppf.spt.direct.terms.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.koscom.oppf.spt.direct.common.exception.ApiException;
import kr.co.koscom.oppf.spt.direct.common.exception.ApiExceptionCode;
import kr.co.koscom.oppf.spt.direct.terms.model.AgreementContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitResponse;
import kr.co.koscom.oppf.spt.direct.terms.service.TermsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TermsController {
    @Autowired
    private TermsService termsService;

    @RequestMapping(
            path = "/direct/terms/financeterms/content",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public TermsContentResponse getFinanceTermsContent(@Valid @RequestBody TermsContentRequest request, BindingResult bindingResult) {
        log.info("============== /direct/terms/financeterms/content =================");
        
        if(bindingResult.hasErrors()) {
            log.info(bindingResult.toString());
            throw new ApiException(ApiExceptionCode.INVALID_REQUEST);
        }
        
        return termsService.selectTermsContent(request);
    }
    

    @RequestMapping(
            path = "/direct/terms/financeterms/search",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public TermsSearchResponse searchFinanceTerms(@Valid @RequestBody TermsSearchRequest request, BindingResult bindingResult) {
        log.info("============== /direct/terms/financeterms/search =================");
        
        if(bindingResult.hasErrors()) {
            throw new ApiException(ApiExceptionCode.INVALID_REQUEST);
        }
                
        return termsService.selectTerms(request);
    }    
    

    @RequestMapping(
            path = "/direct/terms/financeterms/transmit",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public TermsTransmitResponse searchFinanceTerms(@Valid @RequestBody TermsTransmitRequest request, BindingResult bindingResult) {
        log.info("============== /direct/terms/financeterms/transmit =================");

        if(bindingResult.hasErrors()) {
            throw new ApiException(ApiExceptionCode.INVALID_REQUEST);
        }
                
        return termsService.transmitTerms(request);
    }
    
    @RequestMapping(
            path = "/direct/terms/financeterms/revoke",
            method = RequestMethod.POST,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public TermsRevokeResponse revokeFinanceTerms(@Valid @RequestBody TermsRevokeRequest request, BindingResult bindingResult) {
        log.info("============== /direct/terms/financeterms/revoke =================");

        if(bindingResult.hasErrors()) {
            throw new ApiException(ApiExceptionCode.INVALID_REQUEST);
        }
                
        return termsService.revokeTerms(request);
    }    


    @RequestMapping(
            path = "/direct/terms/agreement/content/{termCode}/{companyCode}",
            method = RequestMethod.GET,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public AgreementContentResponse getAgreementContentCompany(@Valid @NotNull @PathVariable String termCode, @Valid @NotNull @PathVariable String companyCode) {
        log.info("============== /direct/terms/agreement/content =================");
        
        
        return termsService.selectAgreementContent(termCode, companyCode);
    }
    
    @RequestMapping(
            path = "/direct/terms/agreement/content/{termCode}",
            method = RequestMethod.GET,
            produces = { "application/json;charset=UTF-8" },
            consumes = { "application/json;charset=UTF-8" })
    public AgreementContentResponse getAgreementContent(@Valid @NotNull @PathVariable String termCode) {
        log.info("============== /direct/terms/agreement/content =================");
                
        return termsService.selectAgreementContent(termCode, null);
    }

}