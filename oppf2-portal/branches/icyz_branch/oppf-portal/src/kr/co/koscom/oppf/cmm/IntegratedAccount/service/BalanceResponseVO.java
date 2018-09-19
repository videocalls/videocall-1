package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class BalanceResponseVO {
	/*private CommonHeaderVO commonHeader = new CommonHeaderVO();*/
	/*private MsgBalanceResponseBodyVO balanceResponseBody = new MsgBalanceResponseBodyVO();*/
	private BalanceListVO balanceList = new BalanceListVO();
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
	}*/
	/*public MsgBalanceResponseBodyVO getBalanceResponseBody() {
		return balanceResponseBody;
	}
	public void setBalanceResponseBody(MsgBalanceResponseBodyVO balanceResponseBody) {
		this.balanceResponseBody = balanceResponseBody;
	}*/
	public BalanceListVO getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(BalanceListVO balanceList) {
		this.balanceList = balanceList;
	}
	/*public MsgCommonRespVO getResp() {
		return resp;
	}
	public void setResp(MsgCommonRespVO resp) {
		this.resp = resp;
	}*/
	
}
