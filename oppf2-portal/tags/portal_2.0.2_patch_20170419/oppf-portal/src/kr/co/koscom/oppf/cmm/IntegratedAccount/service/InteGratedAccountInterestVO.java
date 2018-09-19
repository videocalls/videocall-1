package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-13
 */
public class InteGratedAccountInterestVO extends InteGratedAccountVO{

    private MsgInterestGroupListVO totalResult;

    public MsgInterestGroupListVO getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(MsgInterestGroupListVO totalResult) {
        this.totalResult = totalResult;
    }
}
