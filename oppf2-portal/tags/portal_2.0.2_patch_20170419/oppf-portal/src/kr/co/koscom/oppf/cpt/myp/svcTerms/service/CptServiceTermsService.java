package kr.co.koscom.oppf.cpt.myp.svcTerms.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptServiceTermsService.java
* @Comment  : 기업회원 금융정보제공 동의서 관리 위한 service
* @author   : 신동진
* @since    : 2016.06.30
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.30  신동진        최초생성
*
*/
public interface CptServiceTermsService{
    
    /**
     * @Method Name        : selectCptServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : CptSeviceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptServiceTermsList(CptServiceTermsVO cptServiceTermsVO) throws Exception;
    
    /**
     * @Method Name        : selectCptServiceTermsListExcel
     * @Method description : 금융정보제공 동의서 excel 목록 조회
     * @param              : CptSeviceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptServiceTermsListExcel(CptServiceTermsVO cptServiceTermsVO) throws Exception;
            
}
