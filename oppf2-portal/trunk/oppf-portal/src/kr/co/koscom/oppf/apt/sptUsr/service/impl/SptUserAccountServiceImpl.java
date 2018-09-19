package kr.co.koscom.oppf.apt.sptUsr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.apt.sptUsr.service.SptUserAccountService;
import kr.co.koscom.oppf.apt.sptUsr.service.SptUserAccountVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserAccountServiceImpl.java
* @Comment  : 일반회원 가상계좌 관리  service impl
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
@Service("SptUserAccountService")
public class SptUserAccountServiceImpl implements SptUserAccountService{
    
    @Resource(name = "SptUserAccountDAO")
    private SptUserAccountDAO sptUserAccountDAO;
    
    /**
     * @Method Name        : selectSptUserAccountList
     * @Method description : 가상계좌 목록 조회
     * @param              : SptUserAccountVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserAccountList(SptUserAccountVO sptUserAccountVO) throws Exception{
    	List<SptUserAccountVO> result = sptUserAccountDAO.selectSptUserAccountList(sptUserAccountVO);
    	int totCnt = sptUserAccountDAO.selectSptUserAccountListCnt(sptUserAccountVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
		map.put("totCnt", totCnt);
    	    	
        return map;
    }
    
    /**
     * @Method Name        : selectSptUserAccountListExcel
     * @Method description : 가상계좌 excel 목록 조회
     * @param              : SptUserAccountVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserAccountListExcel(SptUserAccountVO sptUserAccountVO) throws Exception{
    	List<SptUserAccountVO> result = sptUserAccountDAO.selectSptUserAccountListExcel(sptUserAccountVO);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", result);
    	    	
        return map;
    }
    
}
