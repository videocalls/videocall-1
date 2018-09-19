package kr.co.koscom.oppfm.terms.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.terms.model.MobileTerms;
import kr.co.koscom.oppfm.terms.model.TermsReq;

/**
 * ClassName : TermsContentsService
 * *
 * Description :
 * <p>
 * Created by LSH on 2017. 5. 18..
 */
public interface TermsContentsService {
    CommonResponse getTermsContents(TermsReq termsReq);

    CommonResponse getTermsContentDetail(TermsReq termsReq);

	CommonResponse getMobileTerms(MobileTerms mobileTerms);
}
