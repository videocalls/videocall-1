package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class PortfolioResponseViewVO {

	private MsgResponseBodyVO portfolioResponseBody = new MsgResponseBodyVO();
	private PortfolioListViewVO portfolioList = new PortfolioListViewVO();
	private MsgCommonAccInfoVO accInfo = new MsgCommonAccInfoVO();
	
	public MsgCommonAccInfoVO getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(MsgCommonAccInfoVO accInfo) {
		this.accInfo = accInfo;
	}
	public PortfolioListViewVO getPortfolioList() {
		return portfolioList;
	}
	public void setPortfolioList(PortfolioListViewVO portfolioList) {
		this.portfolioList = portfolioList;
	}
	public MsgResponseBodyVO getPortfolioResponseBody() {
		return portfolioResponseBody;
	}
	public void setPortfolioResponseBody(MsgResponseBodyVO portfolioResponseBody) {
		this.portfolioResponseBody = portfolioResponseBody;
	}

}
