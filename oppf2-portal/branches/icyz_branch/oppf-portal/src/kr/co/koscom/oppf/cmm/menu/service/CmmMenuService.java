package kr.co.koscom.oppf.cmm.menu.service;

import java.util.List;
import java.util.Map;


/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : CmmMenuService.java
* @Comment  : 공통메뉴 관리를 위한 service
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
public interface CmmMenuService{
    
    /**
     * @Method Name        : selectCmmMenuList
     * @Method description : 메뉴 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuList(CmmMenuVO cmmMenuVO) throws Exception;
    
    /**
     * @Method Name        : selectCmmMenuTopList
     * @Method description : 메뉴Top 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuTopList(CmmMenuVO cmmMenuVO) throws Exception;
    
    /**
     * @Method Name        : selectCmmMenuLeftList
     * @Method description : 메뉴Left 목록을 조회한다.
     * @param              : CmmMenuVO
     * @return             : List<CmmMenuVO>
     * @throws             : Exception
     */
    public List<CmmMenuVO> selectCmmMenuLeftList(CmmMenuVO cmmMenuVO) throws Exception;
    
    /**
     * @Method Name        : selectCmmMenuDtl
     * @Method description : 메뉴의 상세정보를 조회한다.
     * @param              : CmmMenuVO
     * @return             : CmmMenuVO
     * @throws             : Exception
     */
    public CmmMenuVO selectCmmMenuDtl(CmmMenuVO cmmMenuVO) throws Exception;
}
