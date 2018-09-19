package kr.co.koscom.oppf.cmm.IntegratedAccount.service;

public class MsgTransactionVO {
	private String isinCode;
	private String transDate;
	private String transType;
	private Long changeAmt;
	private Long changeQty;
	private Long qty;
	
	public String getIsinCode() {
		return isinCode;
	}
	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Long getChangeAmt() {
		return changeAmt;
	}
	public void setChangeAmt(Long changeAmt) {
		this.changeAmt = changeAmt;
	}
	public Long getChangeQty() {
		return changeQty;
	}
	public void setChangeQty(Long changeQty) {
		this.changeQty = changeQty;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
}
