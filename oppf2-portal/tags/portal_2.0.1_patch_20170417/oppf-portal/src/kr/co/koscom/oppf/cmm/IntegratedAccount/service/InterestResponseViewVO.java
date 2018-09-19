package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class InterestResponseViewVO {
	private MsgInterestListResponseBodyViewVO interestSymbolListResponseBody = new MsgInterestListResponseBodyViewVO();
	private MsgCommonAccInfoVO accInfo = new MsgCommonAccInfoVO();
	
	public MsgCommonAccInfoVO getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(MsgCommonAccInfoVO accInfo) {
		this.accInfo = accInfo;
	}	
	public MsgInterestListResponseBodyViewVO getInterestSymbolListResponseBody() {
		return interestSymbolListResponseBody;
	}
	public void setInterestSymbolListResponseBody(MsgInterestListResponseBodyViewVO interestSymbolListResponseBody) {
		this.interestSymbolListResponseBody = interestSymbolListResponseBody;
	}
}
