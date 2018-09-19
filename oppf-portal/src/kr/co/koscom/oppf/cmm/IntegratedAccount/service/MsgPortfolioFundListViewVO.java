package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgPortfolioFundListViewVO {
	private String fundCode;
	private String fundName;
	private Double qty;
	private Double earningRate;
	private String maturity;
	private String isinName;
	private String comName;
	private String vtAccNo;

	public String getIsinName() {
		return isinName;
	}
	public void setIsinName(String isinName) {
		this.isinName = isinName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getVtAccNo() {
		return vtAccNo;
	}
	public void setVtAccNo(String vtAccNo) {
		this.vtAccNo = vtAccNo;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getEarningRate() {
		return earningRate;
	}
	public void setEarningRate(Double earningRate) {
		this.earningRate = earningRate;
	}
	public String getMaturity() {
		return maturity;
	}
	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}
}
