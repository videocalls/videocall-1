package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-13
 */
public class InteGratedAccountBalanceVO extends InteGratedAccountVO{

    private BalanceResultVO totalResult;

    public BalanceResultVO getTotalResult() {
        return totalResult;
    }
    public void setTotalResult(BalanceResultVO totalResult) {
        this.totalResult = totalResult;
    }

    public static class BalanceResultVO{
        private MsgBalanceSummaryVO summary;

        public MsgBalanceSummaryVO getSummary() {
            return summary;
        }
        public void setSummary(MsgBalanceSummaryVO summary) {
            this.summary = summary;
        }
    }
}
