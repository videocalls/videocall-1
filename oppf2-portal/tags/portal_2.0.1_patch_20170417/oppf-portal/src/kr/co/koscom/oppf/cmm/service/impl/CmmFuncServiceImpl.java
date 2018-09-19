package kr.co.koscom.oppf.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.service.CmmFuncService;
import kr.co.koscom.oppf.cmm.service.CmmFuncVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmFuncServiceImpl.java
* @Comment  : 공통 기능을 제공하는 serviceImpl
* @author   : 신동진
* @since    : 2016.05.04
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.04  신동진        최초생성
*
*/
@Service("CmmFuncService")
public class CmmFuncServiceImpl implements CmmFuncService{
    
    @Resource(name = "CmmFuncDAO")
    private CmmFuncDAO cmmFuncDAO;
        
    /**
     * @Method Name        : selectCmmnFuncCode
     * @Method description : 공통코드를 조회한다.
     * @param              : CmmFuncVO
     * @return             : List<CmmFuncVO>
     * @throws             : Exception
     */
    public List<CmmFuncVO> selectCmmnFuncCode(CmmFuncVO cmmFuncVO) throws Exception{
        return cmmFuncDAO.selectCmmnFuncCode(cmmFuncVO);
    }
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드를 조회한다(com_company_profile)
     * @param              : CmmFuncVO
     * @return             : List<CmmFuncVO>
     * @throws             : Exception
     */
    public List<CmmFuncVO> selectCompanyCodeList(CmmFuncVO cmmFuncVO) throws Exception{
    	return cmmFuncDAO.selectCompanyCodeList(cmmFuncVO);
    }
    
    
}
