package kr.co.koscom.oppf.spt.myp.svcAppl.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.co.koscom.oppf.cmm.service.impl.ComAbstractDAO;

import kr.co.koscom.oppf.spt.myp.svcAppl.service.MypSvcApplVO;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MypSvcApplDAO.java
* @Comment  : [마이페이지>신청서비스]정보관리를 위한 DAO 클래스
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
@Repository("MypSvcApplDAO")
public class MypSvcApplDAO extends ComAbstractDAO{
    private static final Logger log = Logger.getLogger(MypSvcApplDAO.class);
   
    /**
	 * @Method Name        : selectFintechSvcList
	 * @Method description : [신청서비스] 신청서비스 목록을 조회한다.
	 * @param              : MypSvcApplVO
	 * @return             : List<MypSvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MypSvcApplVO> selectMypSvcApplList(MypSvcApplVO mypSvcApplVO) throws Exception{
		return (List<MypSvcApplVO>) list("spt.myp.svcAppl.MypSvcApplDAO.selectMypSvcApplList",mypSvcApplVO);
	}
	
	/**
	 * @Method Name        : selectFintechSvcDiscardList
	 * @Method description : [신청서비스] 폐기 정보제공동의 목록을 조회한다.
	 * @param              : MypSvcApplVO
	 * @return             : List<MypSvcApplVO>
	 * @throws             : Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MypSvcApplVO> selectFintechSvcDiscardList(MypSvcApplVO mypSvcApplVO) throws Exception{
		return (List<MypSvcApplVO>) list("spt.myp.svcAppl.MypSvcApplDAO.selectFintechSvcDiscardList",mypSvcApplVO);
	}
	
	/**
     * @Method Name        : cancelMypSvcAppl
     * @Method description : [신청서비스] 서비스 해지
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcAppl(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.cancelMypSvcAppl", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : insertMypSvcApplHist
     * @Method description : [신청서비스] spt_customer_service_profile_hist hist 저장
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertMypSvcApplHist(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.insertMypSvcApplHist", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : selectCancelMypSvcApplTermsList
     * @Method description : [신청서비스]서비스 해지 시 폐기될 동의서 목록 조회
     * @param              : MypSvcApplVO
     * @return             : List<MypSvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MypSvcApplVO> selectCancelMypSvcApplTermsList(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (List<MypSvcApplVO>) list("spt.myp.svcAppl.MypSvcApplDAO.selectCancelMypSvcApplTermsList",mypSvcApplVO);
    }
    
    /**
     * @Method Name        : updateCancelMypSvcAppl
     * @Method description : [신청서비스] 동의서 폐기 시 해당 서비스 해지
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCancelMypSvcAppl(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.updateCancelMypSvcAppl", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : updateCancelMypSvcApplHist
     * @Method description : [신청서비스] 동의서 폐기 시 해당 서비스 해지 hist 저장
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int updateCancelMypSvcApplHist(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.updateCancelMypSvcApplHist", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : cancelMypSvcApplTerms
     * @Method description : [신청서비스] 동의서 폐기
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int cancelMypSvcApplTerms(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.cancelMypSvcApplTerms", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : insertMypSvcApplTermsHist
     * @Method description : [신청서비스] spt_customer_service_terms_profile hist 저장
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertMypSvcApplTermsHist(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.insertMypSvcApplTermsHist", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : selectCustomerAccountProcList
     * @Method description : [가상계좌 삭제 공통]삭제한 가상계좌로 신청한 서비스 목록을 가져온다.
     * @param              : MypSvcApplVO
     * @return             : List<MypSvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MypSvcApplVO> selectCustomerAccountProcList(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (List<MypSvcApplVO>) list("spt.myp.svcAppl.MypSvcApplDAO.selectCustomerAccountProcList",mypSvcApplVO);
    }
    
    /**
     * @Method Name        : selectCustomerAccountDeleteList
     * @Method description : [가상계좌 삭제 공통]삭제할 서비스 가상계좌 목록을 조회한다. 
     * @param              : MypSvcApplVO
     * @return             : List<MypSvcApplVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MypSvcApplVO> selectCustomerAccountDeleteList(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (List<MypSvcApplVO>) list("spt.myp.svcAppl.MypSvcApplDAO.selectCustomerAccountDeleteList",mypSvcApplVO);
    }
    
    /**
     * @Method Name        : deleteCustomerAccount
     * @Method description : [가상계좌 삭제 공통] 서비스 가상계좌를 삭제한다.
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int deleteCustomerAccount(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.deleteCustomerAccount", mypSvcApplVO);
    }
    
    /**
     * @Method Name        : insertCustomerAccountHist
     * @Method description : [가상계좌 삭제 공통] 서비스 가상계좌 삭제 hist를 insert 한다.
     * @param              : mypSvcApplVO
     * @return             : int
     * @throws             : Exception
     */
    public int insertCustomerAccountHist(MypSvcApplVO mypSvcApplVO) throws Exception{
        return (int) update("spt.myp.svcAppl.MypSvcApplDAO.insertCustomerAccountHist", mypSvcApplVO);
    }
}
