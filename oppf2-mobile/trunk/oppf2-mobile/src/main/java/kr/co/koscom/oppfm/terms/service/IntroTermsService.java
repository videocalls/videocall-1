package kr.co.koscom.oppfm.terms.service;

import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.terms.model.IntroTermsReq;

/**
 * @author unionman
 *
 */
public interface IntroTermsService {

    
    /**
     * [Intro]서비스 이용약관(개인포털)의  이용약관(서비스이용약관 + 기업 서비스이용약관) 코드를 가져온다.
     * @param termsReq
     * @return
     */
    public CommonResponse getTermsServiceList(IntroTermsReq introTermsReq);
        
    
    /**
     * 약관이 등록된 기본, 업체 정보를 조회한다. 
     * @return
     */
    public CommonResponse getTermsServiceComponyList();
    

}
