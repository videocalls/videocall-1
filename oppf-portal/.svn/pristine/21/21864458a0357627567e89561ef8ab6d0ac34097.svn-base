package kr.co.koscom.oppf.cpt.myp.svcTerms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.cpt.myp.svcTerms.service.CptServiceTermsService;
import kr.co.koscom.oppf.cpt.myp.svcTerms.service.CptServiceTermsVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CptServiceTermsServiceImpl.java
* @Comment  : 기업회원 금융정보제공 동의서 관리 위한  service impl
* @author   : 신동진
* @since    : 2016.06.20
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.20  신동진        최초생성
*
*/
@Service("CptServiceTermsService")
public class CptServiceTermsServiceImpl implements CptServiceTermsService{
    
    @Resource(name = "CptServiceTermsDAO")
    private CptServiceTermsDAO cptServiceTermsDAO;
        
    /**
     * @Method Name        : selectCptServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : CptServiceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptServiceTermsList(CptServiceTermsVO cptServiceTermsVO) throws Exception{
    	List<CptServiceTermsVO> result = cptServiceTermsDAO.selectCptServiceTermsList(cptServiceTermsVO);
    	for(int i=0; i<result.size(); i++){
            String termsRegNo = result.get(i).getTermsRegNo();
            if(!OppfStringUtil.isEmpty(termsRegNo)){
                termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
                result.get(i).setTermsRegNoEncryption(termsRegNo);
            }
        }
    	
    	int totCnt = cptServiceTermsDAO.selectCptServiceTermsListCnt(cptServiceTermsVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectCptServiceTermsListExcel
     * @Method description : 금융정보제공 동의서 Excel 목록 조회
     * @param              : CptServiceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectCptServiceTermsListExcel(CptServiceTermsVO cptServiceTermsVO) throws Exception{
    	List<CptServiceTermsVO> result = cptServiceTermsDAO.selectCptServiceTermsListExcel(cptServiceTermsVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
}
