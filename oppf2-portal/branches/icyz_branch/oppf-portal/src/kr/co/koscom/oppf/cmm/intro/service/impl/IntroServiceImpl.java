package kr.co.koscom.oppf.cmm.intro.service.impl;

import kr.co.koscom.oppf.cmm.intro.service.IntroService;
import kr.co.koscom.oppf.cmm.intro.service.IntroVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : IntroServiceImpl.java
* @Comment  : [Intro]정보관리를 위한 Service 클래스
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
@Service("IntroService")
public class IntroServiceImpl implements IntroService{
    @Resource(name = "IntroDAO")
    private IntroDAO introDAO;
        
    /**
     * @Method Name        : selectIntroFintechAppIntroList
     * @Method description : [핀테크 App 소개]핀테크 App 소개 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroFintechAppList(IntroVO introVO) throws Exception{
    	List<IntroVO> resultAppList = null;					//핀테크 App 목록
    	List<IntroVO> resultAppPubcompanyList = null;		//핀테크 App 금투사 목록
    	int totCnt = 0;										//핀테크 App 목록 totcant
    	
    	//핀테크 서비스 목록 조회
    	resultAppList = introDAO.selectIntroFintechAppList(introVO);
    	totCnt = introDAO.selectIntroFintechAppListTotalCount(introVO);
    	
    	if(resultAppList != null && resultAppList.size() > 0){
    		List<String> searchAppId = new ArrayList<String>();

    		//조회된 appIds를 셋팅한다.
    		for(int i=0; i<resultAppList.size(); i++){
    			IntroVO data = (IntroVO) resultAppList.get(i);
    			searchAppId.add(data.getAppId());
    		}
    		introVO.setSearchAppId(searchAppId);
    	}
    	//핀테크 서비스 금투사 목록 조회
    	resultAppPubcompanyList = introDAO.selectIntroFintechAppPubcompanyList(introVO);
    	
    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultAppList", resultAppList);
   		map.put("resultAppListTotalCount", totCnt);
   		
   		map.put("resultAppPubcompanyList", resultAppPubcompanyList);
    	
        return map;
    }
    
    /**
     * @Method Name        : selectIntroSvcApiCompanyList
     * @Method description : [API 소개] 정보제공자 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroSvcApiCompanyList(IntroVO introVO) throws Exception{
    	List<IntroVO> resultList = introDAO.selectIntroSvcApiCompanyList(introVO);
    	    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultList", resultList);
    	
        return map;
    }
    
    /**
     * @Method Name        : selectIntroSvcApiList
     * @Method description : [API 소개] API 소개 리스트를 조회한다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroSvcApiList(IntroVO introVO) throws Exception{
    	List<IntroVO> resultList = introDAO.selectIntroSvcApiList(introVO);
    	int totCnt = introDAO.selectIntroSvcApiListTotalCount(introVO);
    	    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultList", resultList);
   		map.put("resultListTotalCount", totCnt);
    	
        return map;
    }
    
    /**
     * @Method Name        : selectIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관의 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTerms(IntroVO introVO) throws Exception{
    	return introDAO.selectIntroServiceTerms(introVO);
    }
    
    /**
     * @Method Name        : selectIntroServiceTerms
     * @Method description : [Intro]서비스 이용약관(개인포털)의 기업 이용약관을 조회한다.
     * @param              : IntroVO
     * @return             : IntroVO
     * @throws             : Exception
     */
    public IntroVO selectIntroServiceTermsCompanyTerms(IntroVO introVO) throws Exception{
    	return introDAO.selectIntroServiceTermsCompanyTerms(introVO);
    }
    
    /**
     * @Method Name        : selectIntroServiceTermsCodeList
     * @Method description : [Intro]서비스 이용약관(개인포털)의  이용약관(서비스이용약관 + 기업 서비스이용약관) 코드를 가져온다.
     * @param              : IntroVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectIntroServiceTermsCodeList(IntroVO introVO) throws Exception{
    	List<IntroVO> resultList = introDAO.selectIntroServiceTermsCodeList(introVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
   		map.put("resultList", resultList);
    	
        return map;
    }

	/**
	 * @Method Name        : selectIntroServiceTermsCompanyTerms
	 * @Method description : [App]핀테크 App 금투사 목록
	 * @param              : IntroVO
	 * @return             : IntroVO
	 * @throws             : Exception
	 */
	@Override
	public List<IntroVO> selectServiceAppPubcompanyList(String appId) throws Exception {
		IntroVO introVO = new IntroVO();
		List<String> list = new ArrayList<String>();
		list.add(appId);
		introVO.setSearchAppId(list);
		//핀테크 서비스 금투사 목록 조회
		List<IntroVO> resultAppPubcompanyList = introDAO.selectIntroFintechAppPubcompanyList(introVO);
		return resultAppPubcompanyList;
	}
}
