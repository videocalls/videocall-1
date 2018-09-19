package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class InterestResponseVO {
	/*private CommonHeaderVO commonHeader = new CommonHeaderVO();*/
	private MsgInterestListResponseBodyVO interestSymbolListResponseBody = new MsgInterestListResponseBodyVO();
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
	public MsgInterestListResponseBodyVO getInterestSymbolListResponseBody() {
		return interestSymbolListResponseBody;
	}
	public void setInterestSymbolListResponseBody(MsgInterestListResponseBodyVO interestSymbolListResponseBody) {
		this.interestSymbolListResponseBody = interestSymbolListResponseBody;
	}
	/*public MsgCommonRespVO getResp() {
		return resp;
	}
	public void setResp(MsgCommonRespVO resp) {
		this.resp = resp;
	}*/
}
