package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class PortfolioResponseVO {
	/*private CommonHeaderVO commonHeader = new CommonHeaderVO();
	private MsgPortfolioResponseBodyVO portfolioResponseBody = new MsgPortfolioResponseBodyVO();
	private MsgCommonRespVO resp = new MsgCommonRespVO();*/
	private PortfolioListVO portfolioList = new PortfolioListVO();
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
	public MsgPortfolioResponseBodyVO getPortfolioResponseBody() {
		return portfolioResponseBody;
	}
	public void setPortfolioResponseBody(MsgPortfolioResponseBodyVO portfolioResponseBody) {
		this.portfolioResponseBody = portfolioResponseBody;
	}
	public MsgCommonRespVO getResp() {
		return resp;
	}
	public void setResp(MsgCommonRespVO resp) {
		this.resp = resp;
	}*/
	public PortfolioListVO getPortfolioList() {
		return portfolioList;
	}
	public void setPortfolioList(PortfolioListVO portfolioList) {
		this.portfolioList = portfolioList;
	}
}
