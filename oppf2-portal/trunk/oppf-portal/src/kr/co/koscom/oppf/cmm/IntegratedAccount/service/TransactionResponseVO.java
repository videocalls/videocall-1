package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class TransactionResponseVO {
	/*private CommonHeaderVO commonHeader = new CommonHeaderVO();*/
	/*private MsgTransactionHistoryResponseBodyVO transactionHistoryResponseBody = new MsgTransactionHistoryResponseBodyVO();*/
	private MsgTransactionListVO transList = new MsgTransactionListVO();
	/*private MsgCommonRespVO resp = new MsgCommonRespVO();*/
	private MsgCommonAccInfoVO accInfo = new MsgCommonAccInfoVO();
	
	public MsgCommonAccInfoVO getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(MsgCommonAccInfoVO accInfo) {
		this.accInfo = accInfo;
	}
	/*public CommonHeaderVO getCommonHeader() {
		return commonHeader;
	}
	public void setCommonHeader(CommonHeaderVO commonHeader) {
		this.commonHeader = commonHeader;
	}
	public MsgTransactionHistoryResponseBodyVO getTransactionHistoryResponseBody() {
		return transactionHistoryResponseBody;
	}
	public void setTransactionHistoryResponseBody(MsgTransactionHistoryResponseBodyVO transactionHistoryResponseBody) {
		this.transactionHistoryResponseBody = transactionHistoryResponseBody;
	}
	public MsgCommonRespVO getResp() {
		return resp;
	}
	public void setResp(MsgCommonRespVO resp) {
		this.resp = resp;
	}*/
	public MsgTransactionListVO getTransList() {
		return transList;
	}
	public void setTransList(MsgTransactionListVO transList) {
		this.transList = transList;
	}
}
