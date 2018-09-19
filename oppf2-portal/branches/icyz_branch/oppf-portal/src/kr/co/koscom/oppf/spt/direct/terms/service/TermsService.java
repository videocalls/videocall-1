package kr.co.koscom.oppf.spt.direct.terms.service;

import kr.co.koscom.oppf.spt.direct.terms.model.AgreementContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsContentResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsRevokeResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsSearchResponse;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitRequest;
import kr.co.koscom.oppf.spt.direct.terms.model.TermsTransmitResponse;


public interface TermsService {
    public TermsContentResponse selectTermsContent(TermsContentRequest request);

    public TermsSearchResponse selectTerms(TermsSearchRequest request);
    
    public TermsTransmitResponse transmitTerms(TermsTransmitRequest request);
    
    public TermsRevokeResponse revokeTerms(TermsRevokeRequest request);
    
    public AgreementContentResponse selectAgreementContent(String termCode, String companyCode);

}
