package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-13
 */
public class InteGratedAccountTransactionVO extends InteGratedAccountVO{

    private MsgTransactionListVO totalResult;

    public MsgTransactionListVO getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(MsgTransactionListVO totalResult) {
        this.totalResult = totalResult;
    }
}
