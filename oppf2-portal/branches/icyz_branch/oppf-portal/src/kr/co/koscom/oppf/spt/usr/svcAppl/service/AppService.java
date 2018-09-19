package kr.co.koscom.oppf.spt.usr.svcAppl.service;

import kr.co.koscom.oppf.cmm.intro.service.IntroVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * AppService
 * <p>
 * Created by chungyeol.kim on 2017-06-15.
 */
public interface AppService {
    List<AppVO> getAppList(AppVO appVO);
    int getAppListTotalCount(AppVO appVO);
    AppVO getAppDetail(AppVO appVO);
    List<IntroVO> getAppPubCompanyList(AppVO appVO) throws Exception;
    List<AppAccountVO> getAppAccountList(AppVO appVO);
    List<AppAccountVO> getAccountList(AppVO appVO);
    int removeApp(AppVO appVO);
    int checkedCommonTerms(AppVO appVO);
    TermsVO checkedReSyncCommonTerms(AppVO appVO);
    Map<String, Object> getCommonTermsInfo(AppVO appVO);
    Map<String,Object> createCommonTerms(TermsVO termsVO) throws Exception;
    Map<String,Object> removeCommonTerms(AppVO appVO);
    String checkedAppTerms(AppVO appVO);
    Map<String, Object> getAppTermsInfo(AppVO appVO);
    Map<String, Object> createApp(TermsVO termsVO, boolean isModify, HttpServletRequest req) throws Exception;
    Map<String, Object> modifyApp(TermsVO termsVO, HttpServletRequest req) throws Exception;
    Map<String, Object> createAppTerms(TermsVO termsVO, boolean isModify) throws Exception;
}
