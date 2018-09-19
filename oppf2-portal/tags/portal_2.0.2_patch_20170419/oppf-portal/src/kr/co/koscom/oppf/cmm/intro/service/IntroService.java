package kr.co.koscom.oppf.cmm.intro.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : FaqService.java
* @Comment  : [Intro]정보관리를 위한 Service 인터페이스
* @author   : 신동진
* @since    : 2016.06.13
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.13  신동진        최초생성
*
*/
public interface IntroService{
    
    /**
     * @Method Name        : selectIntroFintechAppList
     * @Method description : [핀테크 App 소개]핀테크 App 소개 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroFintechAppList(IntroVO introVO) throws Exception;
    
    /**
     * @Method Name        : selectIntroSvcApiCompanyList
     * @Method description : [API 소개] 정보제공자 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroSvcApiCompanyList(IntroVO introVO) throws Exception;
    
    /**
     * @Method Name        : selectIntroSvcApiList
     * @Method description : [API 소개] API 소개 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroSvcApiList(IntroVO introVO) throws Exception;
    
    /**
     * @Method Name        : selectIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관의 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTerms(IntroVO introVO) throws Exception;
    
    /**
     * @Method Name        : selectIntroServiceTermsCompanyTerms
     * @Method description : [Intro]서비스 이용약관(개인포털)의 기업 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTermsCompanyTerms(IntroVO introVO) throws Exception;
    
    /**
     * @Method Name        : selectIntroServiceTermsCodeList
     * @Method description : [Intro]서비스 이용약관(개인포털)의  이용약관(서비스이용약관 + 기업 서비스이용약관) 코드를 가져온다.
     * @param              : IntroVO
     * @return             : IntroVOㅊ
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroServiceTermsCodeList(IntroVO introVO) throws Exception;

    /**
     * @Method Name        : selectIntroServiceTermsCompanyTerms
     * @Method description : [App]핀테크 App 금투사 목록
     * @param              : appId
     * @return             : IntroVO
     * @throws             : Exception
     */
    public List<IntroVO> selectServiceAppPubcompanyList(String appId) throws Exception;

}
