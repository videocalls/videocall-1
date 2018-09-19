package kr.co.koscom.oppf.cmm.menu.service.impl;

import kr.co.koscom.oppf.cmm.menu.service.CmmMenuVO;
import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuDAO.java
* @Comment  : 공통메뉴 관리를 위한 DAO 클래스
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
@Repository("CmmMenuDAO")
public class CmmMenuDAO extends ComAbstractDAO{
    
    /**
     * @Method Name        : selectCmmMenuList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmMenuVO> selectCmmMenuList(CmmMenuVO cmmMenuVO) throws Exception{
        return (List<CmmMenuVO>) list("cmm.CmmMenuDAO.selectCmmMenuList", cmmMenuVO);
    }
    
    /**
     * @Method Name        : selectCmmMenuTopList
     * @Method description : 메뉴Top 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmMenuVO> selectCmmMenuTopList(CmmMenuVO cmmMenuVO) throws Exception{
        return (List<CmmMenuVO>) list("cmm.CmmMenuDAO.selectCmmMenuTopList", cmmMenuVO);
    }
    
    /**
     * @Method Name        : selectCmmMenuLeftList
     * @Method description : 메뉴Left 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmMenuVO> selectCmmMenuLeftList(CmmMenuVO cmmMenuVO) throws Exception{
        return (List<CmmMenuVO>) list("cmm.CmmMenuDAO.selectCmmMenuLeftList", cmmMenuVO);
    }
    
    /**
     * @Method Name        : selectCmmMenuDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : CmmMenuVO
     * @return             : CmmMenuVO
     * @throws             : Exception
     */
    public CmmMenuVO selectCmmMenuDtl(CmmMenuVO cmmMenuVO) throws Exception{
    	return (CmmMenuVO) select("cmm.CmmMenuDAO.selectCmmMenuDtl", cmmMenuVO);
    }
    
}
