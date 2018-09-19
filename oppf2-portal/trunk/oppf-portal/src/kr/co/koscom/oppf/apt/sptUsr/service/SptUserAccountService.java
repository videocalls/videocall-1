package kr.co.koscom.oppf.apt.sptUsr.service;

import java.util.Map;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SptUserAccountService.java
* @Comment  : 일반회원 가상계좌 관리 service
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
public interface SptUserAccountService{
	
	/**
     * @Method Name        : selectSptUserAccountList
     * @Method description : 가상계좌 목록 조회
     * @param              : SptUserAccountVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserAccountList(SptUserAccountVO sptUserAccountVO) throws Exception;
    
    /**
     * @Method Name        : selectSptUserAccountListExcel
     * @Method description : 가상계좌 excel 목록 조회
     * @param              : SptUserAccountVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectSptUserAccountListExcel(SptUserAccountVO sptUserAccountVO) throws Exception;
            
}
