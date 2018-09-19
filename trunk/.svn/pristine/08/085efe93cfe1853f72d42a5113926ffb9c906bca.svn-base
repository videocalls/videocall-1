package kr.co.koscom.oppfm.terms.service;

import kr.co.koscom.oppfm.app.model.AppCreateReq;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * CommonTermsService
 * <p>
 * Created by chungyeol.kim on 2017-05-24.
 */
public interface CommonTermsService {

    /**
     * (가)동의서 조회
     * @param request
     * @return
     */
    CommonResponse getCommonTermsInfo(HttpServletRequest request);

    /**
     * (가)동의서 동의 여부 체크
     * @param request, String pubCompanyCodeId
     * @return
     */
    CommonResponse checkedCommonTerms(HttpServletRequest request,String pubCompanyCodeId);

    CommonResponse checkedCommonTermsReSync(HttpServletRequest request);

    CommonResponse createCommonTerms(HttpServletRequest request, AppCreateReq appCreateReq);
}
