package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgPortfolioEtcListVO {
	private String assetType;
	private String assetName;
	private Double qty;
	private Double earningRate;
	private String isinCode;
	
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
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
	public String getIsinCode() {
		return isinCode;
	}
	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}
}
