package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class TransactionResponseViewVO {
	private MsgTransactionListViewVO transList = new MsgTransactionListViewVO();
	private MsgCommonAccInfoVO accInfo = new MsgCommonAccInfoVO();
	public MsgCommonAccInfoVO getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(MsgCommonAccInfoVO accInfo) {
		this.accInfo = accInfo;
	}
	public MsgTransactionListViewVO getTransList() {
		return transList;
	}
	public void setTransList(MsgTransactionListViewVO transList) {
		this.transList = transList;
	}
}
