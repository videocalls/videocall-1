package kr.co.koscom.oppf.spt.myp.asumAcnt.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.usr.svcAppl.service.SptCustomerAccountProfileVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : AsumAcntService.java
* @Comment  : [마이페이지:가상계좌]정보관리를 위한 Service 인터페이스
* @author   : 포털 이환덕
* @since    : 2016.05.27
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.27  이환덕        최초생성
*
*/
public interface AsumAcntService{
    
   
   /**
     * @Method Name        : selectSptCustAccInfo
     * @Method description : [일반회원가상계좌+기업코드]정보를 조회한다.
     * @param              : AsumAcntVO
     * @return             : HashMap<String,Object>
     * @throws             : Exception
     */
    public HashMap<String,Object> selectSptCustAccInfo(AsumAcntVO paramVO) throws Exception;
    
   /**
     * @Method Name        : selectSptCustAccList
     * @Method description : [일반회원가상계좌+기업코드]목록정보를 조회한다.
     * @param              : AsumAcntVO
     * @return             : List<AsumAcntVO>
     * @throws             : Exception
     */
    public List<AsumAcntVO> selectSptCustAccList(AsumAcntVO paramVO) throws Exception;
    
    
   /**
     * @Method Name        : updateSptCustomerAccountProfile
     * @Method description : [일반회원가상계좌]정보를 수정한다.
     * @param              : SptCustomerAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
    
   /**
     * @Method Name        : deleteSptCustomerAccountProfile
     * @Method description : [일반회원가상계좌]정보를 삭제한다.
     * @param              : SptCustomerAccountProfileVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteSptCustomerAccountProfile(SptCustomerAccountProfileVO sptCustomerAccountProfileVO) throws Exception;
    
    
}
