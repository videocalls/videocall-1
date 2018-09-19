package kr.co.koscom.oppf.cmm.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.koscom.oppf.cmm.menu.service.CmmMenuService;
import kr.co.koscom.oppf.cmm.menu.service.CmmMenuVO;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuServiceImpl.java
* @Comment  : 공통메뉴 관리를 위한 Service Impl 클래스
* @author   : 이환덕
* @since    : 2016.05.24
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.24  이환덕        최초생성
*
*/
@Service("CmmMenuService")
public class CmmMenuServiceImpl implements CmmMenuService{
    
    @Resource(name = "CmmMenuDAO")
    private CmmMenuDAO cmmMenuDAO;
    
    
    /**
     * @Method Name        : selectCmmMenuList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuList(CmmMenuVO cmmMenuVO) throws Exception{
    	List<CmmMenuVO> resultList = cmmMenuDAO.selectCmmMenuList(cmmMenuVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectCmmMenuTopList
     * @Method description : 메뉴Top 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuTopList(CmmMenuVO cmmMenuVO) throws Exception{
        List<CmmMenuVO> resultList = cmmMenuDAO.selectCmmMenuTopList(cmmMenuVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectCmmMenuLeftList
     * @Method description : 메뉴Left 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuLeftList(CmmMenuVO cmmMenuVO) throws Exception{
        List<CmmMenuVO> resultList = cmmMenuDAO.selectCmmMenuLeftList(cmmMenuVO);
        return resultList;
    }
    
    /**
     * @Method Name        : selectCmmMenuDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : CmmMenuVO
     * @return             : CmmMenuVO
     * @throws             : Exception
     */
    public CmmMenuVO selectCmmMenuDtl(CmmMenuVO cmmMenuVO) throws Exception{
    	return cmmMenuDAO.selectCmmMenuDtl(cmmMenuVO);
    }
    
}
