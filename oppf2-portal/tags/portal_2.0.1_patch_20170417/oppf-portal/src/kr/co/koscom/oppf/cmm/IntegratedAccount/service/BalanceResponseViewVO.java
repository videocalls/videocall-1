package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class BalanceResponseViewVO {

	private BalanceListViewVO balanceList = new BalanceListViewVO();
	private MsgCommonAccInfoVO accInfo = new MsgCommonAccInfoVO();

	public MsgCommonAccInfoVO getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(MsgCommonAccInfoVO accInfo) {
		this.accInfo = accInfo;
	}

	public BalanceListViewVO getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(BalanceListViewVO balanceList) {
		this.balanceList = balanceList;
	}

}
