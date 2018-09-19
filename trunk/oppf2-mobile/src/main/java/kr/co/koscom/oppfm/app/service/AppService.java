package kr.co.koscom.oppfm.app.service;


import kr.co.koscom.oppfm.app.model.AppCreateReq;
import kr.co.koscom.oppfm.app.model.AppSearchReq;
import kr.co.koscom.oppfm.app.model.AppTermsArsReq;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * AppService
 * <p>
 * Created by chungyeol.kim on 2017-05-11.
 */
public interface AppService {
	
	/**
     * @Method Name        : getAppList
     * @Method description : [핀테크 App 소개]핀테크 App 목록을 조회한다.
     * @param              : AppSearchReq, HttpServletRequest request
     * @return             : CommonResponse
     * @throws             : Exception
     */
    CommonResponse getAppList(AppSearchReq appSearchReq, HttpServletRequest request);
        
    /**
     * @Method Name        : getAppDtl
     * @Method description : [핀테크 App 소개]핀테크 App 상세 조회한다.
     * @param              : String appId, HttpServletRequest request, String type(앱소개 에서 들어왔을때)
     * @return             : CommonResponse
     * @throws             : Exception
     */
    CommonResponse getAppDtl(String appId, HttpServletRequest request, String type);
    
    /**
     * @Method Name        : getAccountList
     * @Method description : [핀테크 App 신청] 연결계좌 선택 - 발급된 계좌 목록 전체 조회한다.
     * @param              : String appId, String type, HttpServletRequest request
     * @return             : CommonResponse
     * @throws             : Exception
     */
    CommonResponse getAccountList(String appId, String type, HttpServletRequest request);

    /**
     * 앱 삭제 처리
     * @param request
     * @param appId
     * @return
     * @throws Exception
     */
    CommonResponse removeApp(HttpServletRequest request, String appId);

    CommonResponse removeAppTerms(HttpServletRequest request, String appId);

    /**
     * 앱 등록 처리
     * @param request
     * @param appCreateReq
     * @return
     * @throws Exception
     */
    CommonResponse createApp(HttpServletRequest request, AppCreateReq appCreateReq, boolean isModify);

    /**
     * 앱 수정 처리
     * @param request
     * @param appCreateReq
     * @return
     * @throws Exception
     */
    CommonResponse modifyApp(HttpServletRequest request, AppCreateReq appCreateReq);

    CommonResponse checkTermsInfo(HttpServletRequest request, String appId, String checkedPubCompanyList, String subCompanyCodeId);

    /**
     * (나)동의서 조회
     * @param request
     * @param appId
     * @return
     */
    CommonResponse getTermsInfo(HttpServletRequest request, String appId, String checkedPubCompanyList, String type);

    CommonResponse getTermsArsInfo(HttpServletRequest request);

    /**
     * ARS 인증 요청
     * @param request
     * @param appTermsArsReq
     * @return
     * @throws Exception
     */
    CommonResponse requestTermsArs(HttpServletRequest request, AppTermsArsReq appTermsArsReq);

    void createTermsFile(AppCreateReq appCreateReq);

    CommonResponse createAppTerms(HttpServletRequest request, AppCreateReq appCreateReq, String appId);

    void sendAppTerms(HttpServletRequest request, String termsRegNo);

}

