package kr.co.koscom.oppf.cmm.service;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.*;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.IntAcntSearchVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.svcAppl.service.SvcApplVO;

import java.util.List;

/**
 * @author : 이희태
 * @version : 1.0
 * @Project : 자본시장 공동 핀테크 오픈플랫폼 구축 2차
 * @FileName : CmmIntegratedAccountService.java
 * @Comment : 통합계좌 DATA SET 기능을 제공하는 service
 * @see
 * @since : 2017.02.13
 */
public interface CmmIntegratedAccountService {

    /**
     * @Method Name        : setBalanceSummary
     * @Method description : 잔고조회 summary 통합 기능
     * @param              : MsgBalanceSummaryVO 통합
     * @param              : MsgBalanceSummaryVO
     */
    public void setBalanceSummary(MsgBalanceSummaryVO msgBalanceSummaryVO_org, MsgBalanceSummaryVO msgBalanceSummaryVO);

    /**
     * @Method Name        : setPrtfolioCashSummary
     * @Method description : 자산 포토폴리오 현금 summary 통합 기능
     * @param              : MsgBalanceSummaryVO 통합
     * @param              : MsgBalanceSummaryVO
     */
    public void setPrtfolioCashSummary(MsgPortfolioCashVO msgPortfolioCashVO_org, MsgPortfolioCashVO msgPortfolioCashVO);

    /**
     * @Method Name        : setPrtfolioCashSummary
     * @Method description : 회원 CI, 한글 이름 조회
     * @param              : customerId
     */
    public MbrRegVO selectSptCustomerVerifyProfileInfo(String customerId) throws Exception;

    /**
     * @Method Name        : selectTestApiCommonMemberConsentList
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회(TEST API용)
     * @param              : comId
     * @param              : srvId
     * @param              : customerCi
     * @param              : customerNameKor
     */
    public VtAccountListVO selectTestApiCommonMemberConsentList(String comId, String srvId, String customerCi, String customerNameKor) throws Exception;

    /**
     * @Method Name        : selectCommonMemberConsentList
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회
     * @param              : comId
     * @param              : srvId
     * @param              : customerCi
     * @param              : customerNameKor
     */
    public VtAccountListVO selectCommonMemberConsentList(String comId, String srvId, String customerCi, String customerNameKor, IntAcntSearchVO paramVO) throws Exception;

    /**
     * @Method Name        : selectCommonMemberConsentListDev
     * @Method description : 핀테크 서비스 연계확인 계좌정보 리스트 조회(개발용)
     * @param              : comId
     * @param              : srvId
     * @param              : customerCi
     * @param              : customerNameKor
     */
    public VtAccountListVO selectCommonMemberConsentListDev(SvcApplVO svcApplVO, IntAcntSearchVO paramVO) throws Exception;

    /**
     * @Method Name        : setBalanceResponseVO
     * @Method description : 잔고조회 result List view set
     * @param              : BalanceViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     */
    public BalanceResponseViewVO setBalanceResponseVO(BalanceResponseViewVO balanceResponseViewVO, String comId, String vtAccNo) throws Exception;

    /**
     * @Method Name        : setTransactionResponseVO
     * @Method description : 거래내역 result List view set
     * @param              : TransactionResponseViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     * @param              : String searchIsinType
     * @param              : String isinCode
     */
    public TransactionResponseViewVO setTransactionResponseVO(TransactionResponseViewVO transactionResponseViewVO, String comId, String vtAccNo, String searchIsinType, String isinCode) throws Exception;

    /**
     * @Method Name        : setPortfolioResponseVO
     * @Method description : 포토폴리오 result List view set
     * @param              : PortfolioResponseViewVO
     * @param              : String comId
     * @param              : String vtAccNo
     */
    public PortfolioResponseViewVO setPortfolioResponseVO(PortfolioResponseViewVO portfolioResponseViewVO, String comId, String vtAccNo) throws Exception;

    /**
     * @Method Name        : setInterestResponseVO
     * @Method description : 포토폴리오 result List view set
     * @param              : InterestResponseViewVO
     * @param              : String comId
     */
    public InterestResponseViewVO setInterestResponseVO(InterestResponseViewVO interestResponseViewVO, String comId) throws Exception;

    /**
     * @Method Name        : setCommonAccountFail
     * @Method description : 요청한 계좌정보가 서비스 연결 계좌 리스트에 존재하지 않는경우 실패 처리 (API 계약 해지, 증권사 계약해지)
     * @param              : List<VtAccountVO>
     * @param              : List<CommonFailViewVO>
     * @param              : int failCount
     */
    public int setCommonAccountFail(List<VtAccountVO> accountList, List<CommonFailViewVO> failList) throws Exception;

    /**
     * @Method Name        : setLinkAccountList
     * @Method description : 서비스 연결 계좌 리스트 비교 (API 계약 해지, 증권사 계약해지)
     * @param              : internalAccountList
     * @param              : apiAccountList
     */
    public void setLinkAccountList(List<VtAccountVO> internalAccountList, List<VtAccountVO> apiAccountList) throws Exception;
}
