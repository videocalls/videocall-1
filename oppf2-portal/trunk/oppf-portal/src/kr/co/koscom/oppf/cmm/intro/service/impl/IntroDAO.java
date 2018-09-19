package kr.co.koscom.oppf.cmm.intro.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : IntroDAO.java
* @Comment  : [Intro]정보관리를 위한 DAO 클래스
* @author   : 신동진
* @since    : 2016.06.13
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.13  신동진        최초생성
*
*/
@Repository("IntroDAO")
public class IntroDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectIntroFintechAppList
     * @Method description : [핀테크 App 소개]핀테크 App 목록을 조회한다.
     * @param              : IntroVO
     * @return             : List<IntroVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<IntroVO> selectIntroFintechAppList(IntroVO introVO) throws Exception{
        return (List<IntroVO>) list("cmm.IntroDAO.selectIntroFintechAppList", introVO);
    }
    
    /**
     * @Method Name        : selectIntroFintechAppListTotalCount
     * @Method description : [핀테크 App 소개]핀테크 App 목록의 총개수를 조회한다.
     * @param              : IntroVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectIntroFintechAppListTotalCount(IntroVO introVO) throws Exception{
        return (Integer) select("cmm.IntroDAO.selectIntroFintechAppListTotalCount", introVO);
    }
    
    /**
     * @Method Name        : selectIntroFintechAppPubcompanyList
     * @Method description : [핀테크 App 소개]핀테크 App 금투사 목록을 조회한다.
     * @param              : IntroVO
     * @return             : List<IntroVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<IntroVO> selectIntroFintechAppPubcompanyList(IntroVO introVO) throws Exception{
        return (List<IntroVO>) list("cmm.IntroDAO.selectIntroFintechAppPubcompanyList", introVO);
    }
    
    /**
     * @Method Name        : selectIntroSvcApiCompanyList
     * @Method description : [API 소개] 정보제공자 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : List<IntroVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<IntroVO> selectIntroSvcApiCompanyList(IntroVO introVO) throws Exception{
        return (List<IntroVO>) list("cmm.IntroDAO.selectIntroSvcApiCompanyList", introVO);
    }
    
    /**
     * @Method Name        : selectIntroSvcApiList
     * @Method description : [API 소개] API 소개 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : List<IntroVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<IntroVO> selectIntroSvcApiList(IntroVO introVO) throws Exception{
        return (List<IntroVO>) list("cmm.IntroDAO.selectIntroSvcApiList", introVO);
    }
    
    /**
     * @Method Name        : selectIntroSvcApiListTotalCount
     * @Method description : [API 소개] API 소개 리스트의 총개수를 조회한다.
     * @param              : IntroVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectIntroSvcApiListTotalCount(IntroVO introVO) throws Exception{
        return (Integer) select("cmm.IntroDAO.selectIntroSvcApiListTotalCount", introVO);
    }
    
    /**
     * @Method Name        : selectIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관의 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTerms(IntroVO introVO) throws Exception{
        return (IntroVO) select("cmm.IntroDAO.selectIntroServiceTerms", introVO);
    }
    
    /**
     * @Method Name        : selectIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관(개인포털)의 기업 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTermsCompanyTerms(IntroVO introVO) throws Exception{
        return (IntroVO) select("cmm.IntroDAO.selectIntroServiceTermsCompanyTerms", introVO);
    }
    
    /**
     * @Method Name        : selectIntroSvcApiList
     * @Method description : [Intro]서비스 이용약관(개인포털)의  이용약관(서비스이용약관 + 기업 서비스이용약관) 코드를 가져온다.
     * @param              : IntroVO
     * @return             : List<IntroVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<IntroVO> selectIntroServiceTermsCodeList(IntroVO introVO) throws Exception{
        return (List<IntroVO>) list("cmm.IntroDAO.selectIntroServiceTermsCodeList", introVO);
    }
    
}
