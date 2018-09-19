package kr.co.koscom.oppf.cmm.service.impl;

import kr.co.koscom.oppf.cmm.service.CmmFuncVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CmmFuncDAO.java
* @Comment  : 공통 기능을 제공하는 DAO
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
@Repository("CmmFuncDAO")
public class CmmFuncDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCmmnFuncCode
     * @Method description : 공통코드를 조회한다.
     * @param              : CmmFuncVO
     * @return             : List<CmmFuncVO>
     * @throws             : Exception
     */
    public List<CmmFuncVO> selectCmmnFuncCode(CmmFuncVO cmmFuncVO) throws Exception{
        return (List<CmmFuncVO>) list("CmmnFuncDAO.selectCmmnFuncCode", cmmFuncVO);
    }
    
    /**
     * @Method Name        : selectCompanyCodeList
     * @Method description : 기업코드를 조회한다(com_company_profile)
     * @param              : CmmFuncVO
     * @return             : List<CmmFuncVO>
     * @throws             : Exception
     */
    public List<CmmFuncVO> selectCompanyCodeList(CmmFuncVO cmmFuncVO) throws Exception{
        return (List<CmmFuncVO>) list("CmmnFuncDAO.selectCompanyCodeList", cmmFuncVO);
    }
    
    
}
