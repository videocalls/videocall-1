package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserServiceTermsVO;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserServiceTermsServiceImpl.java
* @Comment  : 금융정보제공 동의서 관리를 위한  service impl
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
@Service("SptUserServiceTermsService")
public class SptUserServiceTermsServiceImpl implements SptUserServiceTermsService{
    
    @Resource(name = "SptUserServiceTermsDAO")
    private SptUserServiceTermsDAO sptUserServiceTermsDAO;
        
    /**
     * @Method Name        : selectSptUserServiceTermsList
     * @Method description : 금융정보제공 동의서목록 조회
     * @param              : SptUserServiceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserServiceTermsList(SptUserServiceTermsVO sptUserServiceTermsVO) throws Exception{
    	List<SptUserServiceTermsVO> resultList = sptUserServiceTermsDAO.selectSptUserServiceTermsList(sptUserServiceTermsVO);
        for(int i=0; i<resultList.size(); i++){
            String termsRegNo = resultList.get(i).getTermsRegNo();
            if(!OppfStringUtil.isEmpty(termsRegNo)){
                termsRegNo = OppfStringUtil.base64Incoding(termsRegNo);
                resultList.get(i).setTermsRegNoEncryption(termsRegNo);
            }
        }
    	int totCnt = sptUserServiceTermsDAO.selectSptUserServiceTermsListCnt(sptUserServiceTermsVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", resultList);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectSptUserServiceTermsListExcel
     * @Method description : 금융정보제공 동의서 Excel 목록 조회
     * @param              : SptUserServiceTermsVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserServiceTermsListExcel(SptUserServiceTermsVO sptUserServiceTermsVO) throws Exception{
    	List<SptUserServiceTermsVO> result = sptUserServiceTermsDAO.selectSptUserServiceTermsListExcel(sptUserServiceTermsVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
}
