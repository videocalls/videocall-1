package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgPortfolioEquityListVO {
	private String assetType;
	private String isinCode;
	private Double qty;
	private Double earningRate;
	
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getIsinCode() {
		return isinCode;
	}
	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
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
}
