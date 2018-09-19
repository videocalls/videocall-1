package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgPortfolioEquityListViewVO {
	private String assetType;
	private String isinCode;
	private Double qty;
	private Double earningRate;
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
