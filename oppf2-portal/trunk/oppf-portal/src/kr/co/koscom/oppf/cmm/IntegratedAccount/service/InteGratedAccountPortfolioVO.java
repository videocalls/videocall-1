package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

/**
 * @author lee
 * @date 2017-02-13
 */
public class InteGratedAccountPortfolioVO extends InteGratedAccountVO{

    private PortfolioResultVO totalResult;

    public PortfolioResultVO getTotalResult() {
        return totalResult;
    }
    public void setTotalResult(PortfolioResultVO totalResult) {
        this.totalResult = totalResult;
    }

    public static class PortfolioResultVO{
        private MsgPortfolioCashVO cash;

        public MsgPortfolioCashVO getCash() {
            return cash;
        }
        public void setCash(MsgPortfolioCashVO cash) {
            this.cash = cash;
        }
    }
}
