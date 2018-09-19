package kr.co.koscom.oppf.spt.myp.svcAppl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MypSvcApplService.java
* @Comment  : [마이페이지>신청서비스]정보관리를 위한 Service 인터페이스
* @author   : 신동진
* @since    : 2016.06.11
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.06.11  신동진        최초생성
*
*/
public interface MypSvcApplService{
   
    /**
     * @Method Name        : selectMypSvcApplList
     * @Method description : [신청서비스] 신청서비스 목록을 조회한다.
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectMypSvcApplList(MypSvcApplVO mypSvcApplVO) throws Exception;
    
    /**
     * @Method Name        : selectFintechSvcDiscardList
     * @Method description : [신청서비스] 폐기 정보제공동의 목록을 조회한다.  
     * @param              : SvcApplVO
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    public Map<String, Object> selectFintechSvcDiscardList(MypSvcApplVO mypSvcApplVO) throws Exception;
    
    /**
     * @Method Name        : cancelMypSvcAppl
     * @Method description : [신청서비스] 서비스 해지
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcAppl(MypSvcApplVO mypSvcApplVO) throws Exception;
    
    /**
     * @Method Name        : cancelMypSvcApplTerms
     * @Method description : [신청서비스] 동의서 폐기
     * @param              : SvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcApplTerms(MypSvcApplVO mypSvcApplVO) throws Exception;
    
    /**
     * @Method Name        : deleteCustomerAccountProc
     * @Method description : [가상계좌 삭제 공통]서비스 신청 또는 마이페이지 가상계좌에서 가상계좌 삭제 시 연관 정보 처리
     * @param              : MypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int deleteCustomerAccountProc(MypSvcApplVO mypSvcApplVO) throws Exception;
}
